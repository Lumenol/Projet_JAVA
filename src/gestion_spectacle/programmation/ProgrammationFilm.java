package gestion_spectacle.programmation;

import java.util.Iterator;
import java.util.TreeSet;

import gestion_spectacle.Heure;
import gestion_spectacle.seance.SeanceCinema;

public class ProgrammationFilm extends Programmation<SeanceCinema> {

    public ProgrammationFilm() {
    }

    /**
     * retourne les séances pour un jour et une heure donnée
     * 
     * @param jour
     * @param heure
     * @return ensemble de séance trouve
     */
    public TreeSet<SeanceCinema> seances(int jour, Heure heure) {
	TreeSet<SeanceCinema> set = new TreeSet<SeanceCinema>();
	Iterator<SeanceCinema> it = iterator();
	Heure h = null;
	while (it.hasNext() && h.compareTo(heure) <= 0) {
	    SeanceCinema seance = it.next();
	    h = seance.getHoraire();
	    if (h.compareTo(heure) == 0) {
		set.add(seance);
	    }
	}
	return set;

    }

    /**
     * taux de remplissage pour un jour et une heure
     * 
     * @param jour
     * @param h
     * @return taux en pourcentage
     */
    public double tauxRemplissage(int jour, Heure h) {
	return tauxRemplissage(seances(jour, h));
    }

    @Override
    public String toString() {
	return "ProgrammationFilm \n" + super.toString();
    }

}
