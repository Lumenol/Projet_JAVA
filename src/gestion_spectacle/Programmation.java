package gestion_spectacle;

import java.util.Iterator;
import java.util.TreeSet;

public abstract class Programmation<T extends Seance> implements Iterable<T> {

    private TreeSet<T> seances = new TreeSet<T>();

    public void ajouter(T s) {
	seances.add(s);
    }

    public double chiffreAffaire() {
	double chiffreAffaire = 0;
	for (T seance : seances) {
	    chiffreAffaire += seance.chiffreAffaire();
	}
	return chiffreAffaire;

    }

    @Override
    public boolean equals(Object obj) {
	if (this == obj)
	    return true;
	if (obj == null)
	    return false;
	if (getClass() != obj.getClass())
	    return false;
	Programmation other = (Programmation) obj;
	if (seances == null) {
	    if (other.seances != null)
		return false;
	} else if (!seances.equals(other.seances))
	    return false;
	return true;
    }

    @Override
    public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + ((seances == null) ? 0 : seances.hashCode());
	return result;
    }

    @Override
    public Iterator<T> iterator() {
	// TODO Auto-generated method stub
	return seances.iterator();
    }

    public void supprimer(T s) {
	seances.remove(s);
    }

    @Override
    public String toString() {
	return "Programmation [seances=" + seances + "]";
    }

    public TreeSet<T> seances(int jour) {
	TreeSet<T> set = new TreeSet<T>();
	Iterator<T> it = seances.iterator();
	int j = -1;
	while (it.hasNext() && j <= jour) {
	    T seance = it.next();
	    j = seance.getJour();
	    if (j == jour) {
		set.add(seance);
	    }
	}
	return set;

    }

}
