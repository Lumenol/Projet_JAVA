package gestion_spectacle;

public class SeanceCinema extends Seance {

    public SeanceCinema(Heure horaire, int jour, Salle salle) {
	super(horaire, jour);
	this.salle = salle;
	nbPlacesVenduesTR = 0;
    }

    private int nbPlacesVenduesTR;

    private Salle salle;

    @Override
    public double chiffreAffaire() {
	// TODO Auto-generated method stub
	return salle.getTarif() * (getNbPlacesVenduesTN() + 0.6 * nbPlacesVenduesTR);
    }

    @Override
    public int nbPlacesDispo() {
	return salle.getCapacite() - totalVendu();
    }

    @Override
    public double tauxRemplissage() {
	// TODO Auto-generated method stub
	return totalVendu() * 100.0 / salle.getCapacite();
    }

    @Override
    public String toString() {
	return "SeanceCinema [salle=" + salle + ", nbPlacesVenduesTR=" + nbPlacesVenduesTR + "]";
    }

    @Override
    public int totalVendu() {
	return getNbPlacesVenduesTN() + nbPlacesVenduesTR;
    }

    @Override
    public void vendrePlacesTN(int nbre) throws IllegalArgumentException {
	if (nbPlacesDispo() < nbre) {
	    throw new IllegalArgumentException("Nombre de place insuffisant");
	}
	super.vendrePlacesTN(nbre);
    }

    public void vendrePlacesTR(int nbre) throws IllegalArgumentException {
	if (nbPlacesDispo() < nbre) {
	    throw new IllegalArgumentException("Nombre de place insuffisant");
	}
	nbPlacesVenduesTR += nbre;
    }

}
