package gestion_spectacle;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class EnsembleSalle implements Iterable {
    private Set<Salle> salles;

    public EnsembleSalle() {
	salles = new HashSet<Salle>();
    }

    public void ajouter(Salle salle) {
	salles.add(salle);
    }

    @Override
    public boolean equals(Object obj) {
	if (this == obj)
	    return true;
	if (obj == null)
	    return false;
	if (getClass() != obj.getClass())
	    return false;
	EnsembleSalle other = (EnsembleSalle) obj;
	if (salles == null) {
	    if (other.salles != null)
		return false;
	} else if (!salles.equals(other.salles))
	    return false;
	return true;
    }

    @Override
    public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + ((salles == null) ? 0 : salles.hashCode());
	return result;
    }

    public Iterator<Salle> iterator() {
	return salles.iterator();
    }

    public Salle salle(String nom) {
	Iterator<Salle> it = salles.iterator();
	while (it.hasNext()) {
	    Salle salle = it.next();
	    if (salle.getNomSalle().equals(nom)) {
		return salle;
	    }
	}
	return null;
    }

    @Override
    public String toString() {
	StringBuffer b = new StringBuffer("EnsembleSalle\n");
	Iterator<Salle> it = salles.iterator();
	while (it.hasNext()) {
	    Salle salle = it.next();
	    b.append(salle.toString() + "\n");
	}
	return b.toString();
    }

}
