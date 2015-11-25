package gestion_spectacle;

public abstract class Seance implements Comparable<Seance> {
    private int jour, nbPlacesVenduesTN;
    private Heure horaire;

    public abstract int nbPlacesDispo();

    public abstract int totalCendu();

    public abstract double tauxRemplissage();

    public void vendrePlacesTN(int nbre) {
    }

    @Override
    public int compareTo(Seance o) {
	if (jour < o.jour) {
	    return -1;
	} else if (jour > o.jour) {
	    return 1;
	} else {
	    return horaire.compareTo(o.horaire);
	}
    }

    @Override
    public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + ((horaire == null) ? 0 : horaire.hashCode());
	result = prime * result + jour;
	return result;
    }

    @Override
    public boolean equals(Object obj) {
	if (this == obj)
	    return true;
	if (obj == null)
	    return false;
	if (getClass() != obj.getClass())
	    return false;
	Seance other = (Seance) obj;
	if (horaire == null) {
	    if (other.horaire != null)
		return false;
	} else if (!horaire.equals(other.horaire))
	    return false;
	if (jour != other.jour)
	    return false;
	return true;
    }

}
