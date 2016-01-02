package gestion_spectacle.programmation;

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
    /**
     * crée une programmation pour une semaine par saisie interactive
     *
     * @param semaine
     *            numéro de la semaine
     * @param salles
     *            ensemble des salle standard
     * @param theatre
     *            ensemble des salles de cinéma
     * @return programmation de la semaine
     * @throws PasDeSalleException
     *             si il n'y a pas de salle standard n'y de salle de théâtres
     */
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

    /**
     *
     * @param semaine
     *            numéro de la semaine de 0 à 52
     * @throws IllegalArgumentException
     *             si le numéro n'est pas entre 0 et 52
     */
    public ProgrammationSemaine(int semaine) throws IllegalArgumentException {
	if (semaine < 0 || semaine >= 52) {
	    throw new IllegalArgumentException("numero de semaine incorrecte");
	}
	this.semaine = semaine;
	programation = new HashMap<Spectacle, Programmation>();

    }

    /**
     * programme un film par saisie interactive
     *
     * @param salles
     *            ensemble de salle standard
     * @throws PasDeSalleException
     *             si il n'y a pas de salle
     */
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

    /**
     * programme une pièce par saisi interactive
     *
     * @param salles
     *            ensemble de salle de théâtre
     * @throws PasDeSalleException
     *             si il n'y a pas de salle
     */
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

    /**
     * ajoute une programmation de film a un film a la programmation de la
     * semaine
     *
     * @param film
     * @param p
     *            programmation de film
     * @return vrai si a été ajouté sinon faux
     */
    public boolean ajouterProgrammation(Film film, ProgrammationFilm p) {
	return ajouterProgrammation((Spectacle) film, p);
    }

    /**
     * ajoute une programmation de théatre a une pièce
     *
     * @param piece
     * @param p
     *            programmation de théatre
     * @return vrai si ajouté sinon faux
     */
    public boolean ajouterProgrammation(PieceTheatre piece, ProgrammationTheatre p) {

	return ajouterProgrammation((Spectacle) piece, p);
    }

    /**
     * ajoute une séance a un film
     *
     * @param f
     *            film
     * @param s
     *            séance
     */
    public void ajouterSeance(Film f, SeanceCinema s) {
	ajouterProgrammation(f, new ProgrammationFilm());
	ajouterSeance((Spectacle) f, s);
    }

    /**
     * ajoute une séance a une pièce
     *
     * @param p
     *            pièce
     * @param s
     *            séance
     */
    public void ajouterSeance(PieceTheatre p, SeanceTheatre s) {
	ajouterProgrammation(p, new ProgrammationTheatre());
	ajouterSeance((Spectacle) p, s);
    }

    /**
     * recherche un film par son titre
     *
     * @param titre
     * @return film si trouve
     */
    public ProgrammationFilm chercherFilm(String titre) {

	return (ProgrammationFilm) chercher(titre, films());
    }

    /**
     * recherche une pièce par son titre
     *
     * @param titre
     *            titre
     * @return pièce si trouve
     */
    public ProgrammationTheatre chercherPiece(String titre) {

	return (ProgrammationTheatre) chercher(titre, pieces());
    }

    /**
     * permet de consulter l'ensemble de la programmation de la semaine de
     * manière interactive
     *
     * @throws PasDeSpectacleException
     *             si il n'y a pas de spectacle programme cette semaine
     */
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
				System.out.println("Place vendu tarif normal : " + s.getNbPlacesVenduesTN());
				System.out.println("Place vendu tarif réduit : " + s.getNbPlacesVenduesTR());
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
				System.out.println("Chiffre affaire : " + s.chiffreAffaire());
				System.out.println("Taux de remplissage : " + s.tauxRemplissage());
				System.out.println("Place vendu tarif normal : " + s.getNbPlacesVenduesTN());
				System.out.println("Place fauteuils vendu : " + s.getNbFauteuilsVendus());

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

    /**
     *
     * @return ensemble de films programmer cette semaine
     */
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

    /**
     * permet de modifier la programmation de la semaine de manière interactive
     *
     * @param salles
     *            ensemble salle standard
     * @param theatre
     *            ensemble salle théâtre
     */
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

    /**
     * ensemble des pièce de théâtre programmer cette semaine
     *
     * @return ensemble de pièce
     */
    public LinkedList<PieceTheatre> pieces() {
	LinkedList<PieceTheatre> piece = new LinkedList<PieceTheatre>();
	for (Spectacle sp : programation.keySet()) {
	    if (sp instanceof PieceTheatre) {
		piece.addFirst((PieceTheatre) sp);
	    }
	}
	return piece;
    }

    /**
     * retourne la programmation associer a un film
     *
     * @param f
     *            film
     * @return programmation
     */
    public ProgrammationFilm programation(Film f) {
	return (ProgrammationFilm) programation.get(f);
    }

    /**
     * retourne la programmation associer a une pièce
     *
     * @param p
     *            pièce
     * @return
     */
    public ProgrammationTheatre programation(PieceTheatre p) {
	return (ProgrammationTheatre) programation.get(p);
    }

    /**
     * supprimer une séance associer a un spectacle
     *
     * @param sp
     *            spectacle
     * @param se
     *            séance
     */
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

    /**
     * permet de vendre des place pour les spectacle de la semaine de manière
     * interactive
     *
     * @throws PasDeSpectacleException
     *             si il n'y a pas de spectacle programmer
     */
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

    /**
     * ajoute une programmation a un spectacle
     *
     * @param s
     *            spectacle
     * @param p
     *            programmation
     * @return vrai si associe sinon faux
     */
    private boolean ajouterProgrammation(Spectacle s, Programmation p) {
	if (!programation.containsKey(s)) {
	    programation.put(s, p);
	    return true;
	} else {
	    return false;
	}
    }

    /**
     * ajoute une séance a un spectacle
     *
     * @param sp
     *            spectacle
     * @param se
     *            séance
     */
    private void ajouterSeance(Spectacle sp, Seance se) {
	programation.get(sp).ajouter(se);
    }

    /**
     * cherche un spectacle avec son titre dans la collection c
     *
     * @param titre
     *            titre
     * @param c
     *            collection de spectacle
     * @return spectacle trouve
     */
    private Programmation chercher(String titre, Collection<? extends Spectacle> c) {
	for (Spectacle spectacle : c) {
	    if (spectacle.getTitre().equals(titre)) {
		return programation.get(spectacle);
	    }
	}
	return null;
    }

}
