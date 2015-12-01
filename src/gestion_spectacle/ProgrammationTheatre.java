package gestion_spectacle;

import java.util.TreeSet;

public class ProgrammationTheatre extends Programmation {

    public ProgrammationTheatre() {
	seances = new TreeSet<SeanceTheatre>();
    }

    public double tauxRemplissage(int jour) {
	return 0;
    }
}
