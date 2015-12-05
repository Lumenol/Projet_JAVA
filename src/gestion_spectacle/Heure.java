package gestion_spectacle;

import java.io.Serializable;

public class Heure implements Comparable<Heure>, Serializable {

    private int minutes;

    public Heure(int minutes) {
	this.minutes = minutes;
    }

    public Heure(int heures, int minutes) {
	this(minutes + heures * 60);
    }

    @Override
    public int compareTo(Heure o) {
	if (minutes < o.minutes) {
	    return -1;
	} else if (minutes > o.minutes) {
	    return 1;
	} else {
	    return 0;
	}
    }

    @Override
    public boolean equals(Object obj) {
	if (this == obj)
	    return true;
	if (obj == null)
	    return false;
	if (getClass() != obj.getClass())
	    return false;
	Heure other = (Heure) obj;
	if (minutes != other.minutes)
	    return false;
	return true;
    }

    public int getHeures() {
	return minutes / 60;
    }

    public int getMinutes() {
	return minutes % 60;
    }

    @Override
    public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + minutes;
	return result;
    }

    @Override
    public String toString() {
	return getHeures() + " h : " + getMinutes() + " min";
    }

}