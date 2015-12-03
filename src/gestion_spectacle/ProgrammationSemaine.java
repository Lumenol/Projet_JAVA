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

    @Override
    public String toString() {
	StringBuffer b = new StringBuffer("ProgrammationSemaine : semaine : +semaine");
	for (Spectacle spectacle : programation.keySet()) {
	    b.append(spectacle.toString() + "\n" + programation.get(spectacle).toString() + "\n");
	}
	return b.toString();
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
