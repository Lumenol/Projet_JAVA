package gestion_spectacle.programmation;

import gestion_spectacle.seance.SeanceTheatre;

public class ProgrammationTheatre extends Programmation<SeanceTheatre> {

    public ProgrammationTheatre() {
    }

    public double tauxRemplissage(int jour) {
	return tauxRemplissage(seances(jour));
    }

    @Override
    public String toString() {
	return "ProgrammationTheatre \n" + super.toString();
    }

}
