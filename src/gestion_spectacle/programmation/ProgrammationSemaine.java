package gestion_spectacle.programmation;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Scanner;

import gestion_spectacle.exception.PasDeSalleException;
import gestion_spectacle.exception.PasDeSeanceException;
import gestion_spectacle.exception.PasDeSpectacleException;
import gestion_spectacle.salle.EnsembleSalle;
import gestion_spectacle.salle.EnsembleTheatre;
import gestion_spectacle.seance.Seance;
import gestion_spectacle.seance.SeanceCinema;
import gestion_spectacle.seance.SeanceTheatre;
import gestion_spectacle.spectacle.Film;
import gestion_spectacle.spectacle.PieceTheatre;
import gestion_spectacle.spectacle.Spectacle;

public class ProgrammationSemaine implements Serializable {

    static public ProgrammationSemaine charger(String nomFichier) throws FileNotFoundException, IOException {
	ObjectInputStream ois = new ObjectInputStream(new FileInputStream(nomFichier));
	Object obj;
	try {
	    obj = ois.readObject();
	    if (obj.getClass() != ProgrammationSemaine.class) {
		obj = null;
	    }
	} catch (ClassNotFoundException e) {
	    obj = null;
	}
	ois.close();
	return (ProgrammationSemaine) obj;
    }

    public static ProgrammationSemaine programmationSemaine(int semaine, EnsembleSalle salles, EnsembleTheatre theatre)
	    throws PasDeSalleException {
	if ((salles == null || salles.isEmpty()) && (theatre == null || theatre.isEmpty())) {
	    throw new PasDeSalleException("Il n'y a pas de salle de spectacle");
	}
	ProgrammationSemaine programmation = new ProgrammationSemaine(semaine);
	boolean loop = true;
	Scanner sc = new Scanner(System.in);
	do {
	    System.out.println("f-film p-pièce (r)etour");
	    switch (sc.nextLine()) {
	    case "f":
		try {
		    programmation.ajouterFilm(salles);
		} catch (PasDeSalleException e) {
		    System.out.println("Il n'y a pas de salle de cinéma");
		}
		break;

	    case "p":
		try {
		    programmation.ajouterPiece(theatre);
		} catch (PasDeSalleException e) {
		    System.out.println("Il n'y a pas de salle de theatre");
		}
		break;

	    case "r":
		loop = false;
		break;
	    }

	} while (loop);

	return programmation;
    }

    private Map<Spectacle, Programmation> programation;

    private int semaine;

    public ProgrammationSemaine(int semaine) throws IllegalArgumentException {
	if (semaine < 0 || semaine >= 52) {
	    throw new IllegalArgumentException("numero de semaine incorrecte");
	}
	this.semaine = semaine;
	programation = new HashMap<Spectacle, Programmation>();

    }

    public void ajouterFilm(EnsembleSalle salles) throws PasDeSalleException {
	if (salles.isEmpty()) {
	    throw new PasDeSalleException();
	}
	Film f = Film.film();
	ajouterSeance(f, SeanceCinema.seanceCinema(salles));
	boolean loop2 = true;
	Scanner sc = new Scanner(System.in);
	while (loop2) {
	    System.out.println("(r)etour (a)jouter ");
	    switch (sc.nextLine()) {
	    case "r":
		loop2 = false;
		break;

	    case "a":
		ajouterSeance(f, SeanceCinema.seanceCinema(salles));
		break;
	    }
	}

    }

    public void ajouterPiece(EnsembleTheatre salles) throws PasDeSalleException {
	if (salles.isEmpty()) {
	    throw new PasDeSalleException();
	}
	PieceTheatre p = PieceTheatre.pieceTheatre();
	ajouterSeance(p, SeanceTheatre.seanceTheatre(salles));
	Scanner sc = new Scanner(System.in);
	boolean loop2 = true;
	while (loop2) {
	    System.out.println("(r)etour (a)jouter ");
	    switch (sc.nextLine()) {
	    case "r":
		loop2 = false;
		break;

	    case "a":
		ajouterSeance(p, SeanceTheatre.seanceTheatre(salles));
		break;
	    }
	}
    }

    public boolean ajouterProgrammation(Film film, ProgrammationFilm p) {
	return ajouterProgrammation((Spectacle) film, p);
    }

    public boolean ajouterProgrammation(PieceTheatre piece, ProgrammationTheatre p) {

	return ajouterProgrammation((Spectacle) piece, p);
    }

    public void ajouterSeance(Film f, SeanceCinema s) {
	ajouterProgrammation(f, new ProgrammationFilm());
	ajouterSeance((Spectacle) f, s);
    }

    public void ajouterSeance(PieceTheatre p, SeanceTheatre s) {
	ajouterProgrammation(p, new ProgrammationTheatre());
	ajouterSeance((Spectacle) p, s);
    }

    public ProgrammationFilm chercherFilm(String titre) {

	return (ProgrammationFilm) chercher(titre, films());
    }

    public ProgrammationTheatre chercherPiece(String titre) {

	return (ProgrammationTheatre) chercher(titre, pieces());
    }

    public void consultation() throws PasDeSpectacleException {
	if (films().isEmpty() && pieces().isEmpty()) {
	    throw new PasDeSpectacleException("Il n'y a pas de spectacle dans cette programmation");
	}
	boolean loop = true, loop2, loop3;
	Scanner sc = new Scanner(System.in);
	do {
	    System.out.println("f-film p-pièce (r)etour");
	    switch (sc.nextLine()) {
	    case "f":
		try {
		    Film f = (Film) Spectacle.choisirSpectacle(this.films());
		    loop3 = true;
		    while (loop3) {
			System.out.println("(r)etour (c)hoisir seance");
			switch (sc.nextLine()) {
			case "r":
			    loop3 = false;
			    break;

			case "c":
			    try {
				SeanceCinema s = (SeanceCinema) programation(f).choisirSeance();
				System.out.println("Chiffre affaire : " + s.chiffreAffaire());
				System.out.println("Taux de remplissage : " + s.tauxRemplissage());

			    } catch (PasDeSeanceException e) {
				System.out.println("il n'y a pas de seance pour ce film");
			    }
			    break;
			}

		    }
		} catch (PasDeSpectacleException e) {
		    System.out.println("Il n'y a pas de film");
		}
		break;

	    case "p":
		try {
		    PieceTheatre p = (PieceTheatre) Spectacle.choisirSpectacle(this.pieces());
		    loop3 = true;
		    while (loop3) {
			System.out.println("(r)etour (c)hoisir seance");
			switch (sc.nextLine()) {
			case "r":
			    loop2 = false;
			    break;

			case "c":
			    try {
				SeanceCinema s = (SeanceCinema) programation(p).choisirSeance();
				System.out.println("Chiffre affaire : " + s.chiffreAffaire());
				System.out.println("Taux de remplissage : " + s.tauxRemplissage());

			    } catch (PasDeSeanceException e) {
				System.out.println("il n'y a pas de seance pour ce film");
			    }
			    break;
			}

		    }
		} catch (PasDeSpectacleException e) {
		    System.out.println("il n'y a pas de piece");
		}
		break;
	    case "r":
		loop = false;
		break;
	    }

	} while (loop);

    }

    @Override
    public boolean equals(Object obj) {
	if (this == obj)
	    return true;
	if (obj == null)
	    return false;
	if (getClass() != obj.getClass())
	    return false;
	ProgrammationSemaine other = (ProgrammationSemaine) obj;
	if (programation == null) {
	    if (other.programation != null)
		return false;
	} else if (!programation.equals(other.programation))
	    return false;
	if (semaine != other.semaine)
	    return false;
	return true;
    }

    public LinkedList<Film> films() {
	LinkedList<Film> film = new LinkedList<Film>();
	for (Spectacle sp : programation.keySet()) {
	    if (sp instanceof Film) {
		film.addFirst((Film) sp);
	    }
	}
	return film;
    }

    @Override
    public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + ((programation == null) ? 0 : programation.hashCode());
	result = prime * result + semaine;
	return result;
    }

    public void modification(EnsembleSalle salles, EnsembleTheatre theatre) {
	boolean loop = true, loop2, loop3;
	Scanner sc = new Scanner(System.in);
	do {
	    System.out.println("f-film p-pièce (r)etour");
	    switch (sc.nextLine()) {
	    case "f":
		loop3 = true;
		while (loop3) {
		    System.out.println("(m)odifier exiantant (a)jouter Film (r)etour");
		    switch (sc.nextLine()) {

		    case "r":
			loop3 = false;
			break;
		    case "a":
			try {
			    ajouterFilm(salles);
			} catch (PasDeSalleException e1) {
			    System.out.println("il n'y a pas de salle de cinema");
			}
			break;

		    case "m":
			try {
			    Film f = (Film) Spectacle.choisirSpectacle(this.films());
			    loop2 = true;
			    while (loop2) {
				System.out.println("(r)etour (a)jouter (s)upprimer");
				switch (sc.nextLine()) {
				case "r":
				    loop2 = false;
				    break;

				case "a":
				    try {
					ajouterSeance(f, SeanceCinema.seanceCinema(salles));
				    } catch (PasDeSalleException e) {
					System.out.println("il n'y a pas de salle de cinema");
				    }
				    break;

				case "s":
				    try {
					supprimerSeance(f, programation(f).choisirSeance());
				    } catch (PasDeSeanceException e) {
					System.out.println("Il n'y a pas de sceance pour ce film");
				    }
				    break;
				}
			    }
			} catch (PasDeSpectacleException e) {
			    System.out.println("il n'y a pas de film");
			}
			break;
		    }
		}

		break;

	    case "p":
		loop3 = true;
		while (loop3) {
		    System.out.println("(m)odifier exiantant (a)jouter Piece (r)etour");
		    switch (sc.nextLine()) {

		    case "r":
			loop3 = false;
			break;
		    case "a":
			try {
			    ajouterPiece(theatre);
			} catch (PasDeSalleException e1) {
			    System.out.println("il n'y a pas de salle de cinema");
			}
			break;

		    case "m":
			try {
			    PieceTheatre p = (PieceTheatre) Spectacle.choisirSpectacle(this.pieces());
			    loop2 = true;
			    while (loop2) {
				System.out.println("(r)etour (a)jouter (s)upprimer");
				switch (sc.nextLine()) {
				case "r":
				    loop2 = false;
				    break;

				case "a":

				    try {
					ajouterSeance(p, SeanceTheatre.seanceTheatre(theatre));
				    } catch (PasDeSalleException e) {
					System.out.println("il n'y a pas de salle de theatre");
				    }

				    break;

				case "s":
				    try {
					supprimerSeance(p, programation(p).choisirSeance());
				    } catch (PasDeSeanceException e) {
					System.out.println("il n'y a pas de seance pour cette piece");
				    }
				    break;
				}
			    }
			} catch (PasDeSpectacleException e) {
			    System.out.println("il n'y a pas de piece");
			}
			break;
		    }
		}

		break;
	    case "r":
		loop = false;
		break;
	    }

	} while (loop);
    }

    public LinkedList<PieceTheatre> pieces() {
	LinkedList<PieceTheatre> piece = new LinkedList<PieceTheatre>();
	for (Spectacle sp : programation.keySet()) {
	    if (sp instanceof PieceTheatre) {
		piece.addFirst((PieceTheatre) sp);
	    }
	}
	return piece;
    }

    public ProgrammationFilm programation(Film f) {
	return (ProgrammationFilm) programation.get(f);
    }

    public ProgrammationTheatre programation(PieceTheatre f) {
	return (ProgrammationTheatre) programation.get(f);
    }

    public void sauvegarder(String nomFichier) throws FileNotFoundException, IOException {
	ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(nomFichier));
	oos.writeObject(this);
	oos.close();
    }

    public void supprimerSeance(Spectacle sp, Seance se) {
	if (programation.containsKey(sp)) {
	    programation.get(sp).supprimer(se);
	}
    }

    @Override
    public String toString() {
	StringBuffer b = new StringBuffer("ProgrammationSemaine : semaine : " + semaine + "\n");
	for (Spectacle spectacle : programation.keySet()) {
	    b.append(spectacle.toString() + "\n" + programation.get(spectacle).toString() + "\n");
	}
	return b.toString();
    }

    public void vendre() throws PasDeSpectacleException {
	if (films().isEmpty() && pieces().isEmpty()) {
	    throw new PasDeSpectacleException();
	}
	boolean loop = true, loop2, loop3;
	Scanner sc = new Scanner(System.in);
	do {
	    System.out.println("f-film p-pièce (r)etour");
	    switch (sc.nextLine()) {
	    case "f":
		try {
		    Film f = (Film) Spectacle.choisirSpectacle(this.films());
		    loop3 = true;
		    while (loop3) {
			System.out.println("(r)etour (c)hoisir seance");
			switch (sc.nextLine()) {
			case "r":
			    loop3 = false;
			    break;

			case "c":
			    try {
				SeanceCinema s = (SeanceCinema) programation(f).choisirSeance();
				loop2 = true;
				while (loop2) {
				    System.out.println("(r)etour (v)endre");
				    System.out.println("Place Libre" + s.nbPlacesDispo());
				    switch (sc.nextLine()) {
				    case "r":
					loop2 = false;
					break;

				    case "v":
					s.vendre();
					break;
				    }
				}
			    } catch (PasDeSeanceException e) {
				System.out.println("il n'y a pas de seance pour ce film");
			    }
			    break;
			}

		    }
		} catch (PasDeSpectacleException e) {
		    System.out.println("Il n'y a pas de film");
		}
		break;

	    case "p":
		try {
		    PieceTheatre p = (PieceTheatre) Spectacle.choisirSpectacle(this.pieces());
		    loop3 = true;
		    while (loop3) {
			System.out.println("(r)etour (c)hoisir seance");
			switch (sc.nextLine()) {
			case "r":
			    loop2 = false;
			    break;

			case "c":
			    try {
				SeanceTheatre s = (SeanceTheatre) programation(p).choisirSeance();
				loop2 = true;
				while (loop2) {
				    System.out.println("(r)etour (v)endre");
				    System.out.println("Place Libre" + s.nbPlacesDispo());
				    switch (sc.nextLine()) {
				    case "r":
					loop2 = false;
					break;

				    case "v":
					s.vendre();
					break;
				    }
				}
			    } catch (PasDeSeanceException e) {
				System.out.println("il n'y a pas de sceance pour cette piece");
			    }
			    break;
			}

		    }
		} catch (PasDeSpectacleException e) {
		    System.out.println("il n'y a pas de piece");
		}
		break;
	    case "r":
		loop = false;
		break;
	    }

	} while (loop);
    }

    private boolean ajouterProgrammation(Spectacle s, Programmation p) {
	if (!programation.containsKey(s)) {
	    programation.put(s, p);
	    return true;
	} else {
	    return false;
	}
    }

    private void ajouterSeance(Spectacle sp, Seance se) {
	programation.get(sp).ajouter(se);
    }

    private Programmation chercher(String titre, Collection<? extends Spectacle> c) {
	for (Spectacle spectacle : c) {
	    if (spectacle.getTitre().equals(titre)) {
		return programation.get(spectacle);
	    }
	}
	return null;
    }

}
