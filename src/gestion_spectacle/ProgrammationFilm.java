package gestion_spectacle;

import java.util.Iterator;
import java.util.TreeSet;

public class ProgrammationFilm extends Programmation<SeanceCinema> {

    public ProgrammationFilm() {
    }

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

    public double tauxRemplissage(int jour, Heure h) {
	return 0;
    }

}
