package gestion_spectacle;

public abstract class Seance implements Comparable<Seance> {
    protected Heure horaire;

    protected int jour, nbPlacesVenduesTN;

    public abstract double chiffreAffaire();

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

    public abstract int nbPlacesDispo();

    public abstract double tauxRemplissage();

    @Override
    public String toString() {
	return "Seance [jour=" + jour + ", nbPlacesVenduesTN=" + nbPlacesVenduesTN + ", horaire=" + horaire + "]";
    }

    public abstract int totalVendu();

    public void vendrePlacesTN(int nbre) {
	nbPlacesVenduesTN += nbre;
    }

}
