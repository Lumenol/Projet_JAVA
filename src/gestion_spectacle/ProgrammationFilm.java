package gestion_spectacle;

import java.util.TreeSet;

public class ProgrammationFilm extends Programmation {

    public ProgrammationFilm() {
	seances = new TreeSet<SeanceCinema>();
    }

    public double tauxRemplissage(int jour, Heure h) {
	return 0;
    }

}
