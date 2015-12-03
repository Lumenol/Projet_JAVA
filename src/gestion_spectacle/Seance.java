package gestion_spectacle;

public abstract class Seance implements Comparable<Seance> {
    private Heure horaire;

    private int jour, nbPlacesVenduesTN;

    public Seance(Heure horaire, int jour) {
	super();
	this.horaire = horaire;
	this.jour = jour;
	nbPlacesVenduesTN = 0;
    }

    public Heure getHoraire() {
	return horaire;
    }

    public int getJour() {
	return jour;
    }

    public int getNbPlacesVenduesTN() {
	return nbPlacesVenduesTN;
    }

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
