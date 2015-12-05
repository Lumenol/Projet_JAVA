package gestion_spectacle;

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
	    throws IllegalArgumentException {
	ProgrammationSemaine programmation = new ProgrammationSemaine(semaine);
	boolean loop = true, loop2;
	Scanner sc = new Scanner(System.in);
	do {
	    System.out.println("f-film p-pièce");
	    switch (sc.nextLine()) {
	    case "f":
		Film f = Film.film();
		programmation.ajouterSeance(f, SeanceCinema.seanceCinema(salles));
		loop2 = true;
		while (loop2) {
		    System.out.println("(r)etour (a)jouter ");
		    switch (sc.nextLine()) {
		    case "r":
			loop2 = false;
			break;

		    case "a":
			programmation.ajouterSeance(f, SeanceCinema.seanceCinema(salles));
			break;
		    }
		}
		loop = false;
		break;

	    case "p":
		PieceTheatre p = PieceTheatre.pieceTheatre();
		programmation.ajouterSeance(p, SeanceTheatre.seanceTheatre(theatre));
		loop2 = true;
		while (loop2) {
		    System.out.println("(r)etour (a)jouter ");
		    switch (sc.nextLine()) {
		    case "r":
			loop2 = false;
			break;

		    case "a":
			programmation.ajouterSeance(p, SeanceTheatre.seanceTheatre(theatre));
			break;
		    }
		}
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
	ajouterProgrammation(p, new ProgrammationFilm());
	ajouterSeance((Spectacle) p, s);
    }

    public ProgrammationFilm chercherFilm(String titre) {

	return (ProgrammationFilm) chercher(titre, films());
    }

    public ProgrammationTheatre chercherPiece(String titre) {

	return (ProgrammationTheatre) chercher(titre, pieces());
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
	boolean loop = true, loop2;
	Scanner sc = new Scanner(System.in);
	do {
	    System.out.println("f-film p-pièce (r)etour");
	    switch (sc.nextLine()) {
	    case "f":
		Film f = (Film) Spectacle.choisirSpectacle(this.films());
		loop2 = true;
		while (loop2) {
		    System.out.println("(r)etour (a)jouter (s)upprimer");
		    switch (sc.nextLine()) {
		    case "r":
			loop2 = false;
			break;

		    case "a":
			ajouterSeance(f, SeanceCinema.seanceCinema(salles));
			break;

		    case "s":
			supprimerSeance(f, programation(f).choisirSeance());
			break;
		    }
		}
		break;

	    case "p":
		PieceTheatre p = (PieceTheatre) Spectacle.choisirSpectacle(this.pieces());
		loop2 = true;
		while (loop2) {
		    System.out.println("(r)etour (a)jouter (s)upprimer");
		    switch (sc.nextLine()) {
		    case "r":
			loop2 = false;
			break;

		    case "a":
			ajouterSeance(p, SeanceTheatre.seanceTheatre(theatre));
			break;

		    case "s":
			supprimerSeance(p, programation(p).choisirSeance());
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

    public void vendre() {
	boolean loop = true, loop2, loop3;
	Scanner sc = new Scanner(System.in);
	do {
	    System.out.println("f-film p-pièce (r)etour");
	    switch (sc.nextLine()) {
	    case "f":
		Film f = (Film) Spectacle.choisirSpectacle(this.films());
		loop3 = true;
		while (loop3) {
		    System.out.println("(r)etour (c)hoisir seance");
		    switch (sc.nextLine()) {
		    case "r":
			loop3 = false;
			break;

		    case "c":
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
			break;
		    }

		}

		break;

	    case "p":
		if (!this.pieces().isEmpty()) {
		    PieceTheatre p = (PieceTheatre) Spectacle.choisirSpectacle(this.pieces());
		    loop3 = true;
		    while (loop3) {
			System.out.println("(r)etour (c)hoisir seance");
			switch (sc.nextLine()) {
			case "r":
			    loop2 = false;
			    break;

			case "c":
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
			    break;
			}

		    }
		} else {
		    System.out.println("Il n'y a pas de piece de programmer");
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
