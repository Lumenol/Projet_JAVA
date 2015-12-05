package gestion_spectacle;

import java.io.Serializable;
import java.util.Collection;
import java.util.Iterator;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.util.TreeSet;

public abstract class Programmation<T extends Seance> implements Iterable<T>, Serializable {

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

    public Seance choisirSeance() {
	Scanner sc = new Scanner(System.in);
	StringTokenizer toka;
	int index = -1, i = 0;
	StringBuffer buff = new StringBuffer();
	for (Iterator iterator = seances.iterator(); iterator.hasNext();) {
	    Seance seance = (Seance) iterator.next();
	    buff.append(i + "-" + seance.getJour() + " " + seance.getHoraire() + " " + seance.nomSalle());
	    i++;
	}
	do {
	    System.out.println(buff.toString());
	    toka = new StringTokenizer(sc.nextLine());
	    if (toka.hasMoreTokens()) {
		try {
		    index = Integer.valueOf(toka.nextToken());
		} catch (NumberFormatException e) {
		}
	    }
	} while (index < 0 || index >= seances.size());
	return (Seance) seances.toArray()[index];
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

    public void supprimer(T s) {
	seances.remove(s);
    }

    @Override
    public String toString() {
	StringBuffer b = new StringBuffer();
	for (Iterator iterator = seances.iterator(); iterator.hasNext();) {
	    T t = (T) iterator.next();
	    b.append(t.toString() + "\n");
	}
	return b.toString();
    }

    protected double tauxRemplissage(Collection<T> c) {
	double taux = 0;
	for (Iterator iterator = c.iterator(); iterator.hasNext();) {
	    T t = (T) iterator.next();
	    taux += t.tauxRemplissage();
	}
	return taux / c.size();
    }

}
