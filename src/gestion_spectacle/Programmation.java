package gestion_spectacle;

import java.util.TreeSet;

public abstract class Programmation {

    protected TreeSet<? extends Seance> seances;

    public double chiffreAffaire() {
	return 0;

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
    public String toString() {
	return "Programmation [seances=" + seances + "]";
    }

}
