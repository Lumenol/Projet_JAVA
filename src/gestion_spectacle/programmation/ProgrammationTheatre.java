package gestion_spectacle.programmation;

import gestion_spectacle.seance.SeanceTheatre;

public class ProgrammationTheatre extends Programmation<SeanceTheatre> {

    public ProgrammationTheatre() {
    }

    /**
     * taux de remplissage pour un jour
     * 
     * @param jour
     * @return taux en pourcentage
     */
    public double tauxRemplissage(int jour) {
	return tauxRemplissage(seances(jour));
    }

    @Override
    public String toString() {
	return "ProgrammationTheatre \n" + super.toString();
    }

}
