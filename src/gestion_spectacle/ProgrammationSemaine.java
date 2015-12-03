package gestion_spectacle;

import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class ProgrammationSemaine {
    private Map<Spectacle, Programmation> programation;

    private int semaine;

    public ProgrammationSemaine(int semaine) throws IllegalArgumentException {
	if (semaine < 1 || semaine > 52) {
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

    private boolean ajouterProgrammation(Spectacle s, Programmation p) {
	if (!programation.containsKey(s)) {
	    programation.put(s, p);
	    return true;
	} else {
	    return false;
	}
    }

    public void ajouterSeance(Film f, SeanceCinema s) {
	ajouterProgrammation(f, new ProgrammationFilm());
	ajouterSeance((Spectacle) f, s);
    }

    public void ajouterSeance(PieceTheatre p, SeanceTheatre s) {
	ajouterProgrammation(p, new ProgrammationFilm());
	ajouterSeance((Spectacle) p, s);
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

    public ProgrammationFilm chercherFilm(String titre) {

	return (ProgrammationFilm) chercher(titre, films());
    }

    public ProgrammationTheatre chercherPiece(String titre) {

	return (ProgrammationTheatre) chercher(titre, pieces());
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

    public void supprimerSeance(Spectacle sp, Seance se) {
	if (programation.containsKey(sp)) {
	    programation.get(sp).supprimer(se);
	}
    }

}
