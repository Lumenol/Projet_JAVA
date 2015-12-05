package gestion_spectacle;

public class SeanceCinema extends Seance {

    private int nbPlacesVenduesTR;

    private Salle salle;

    // public static seanceCinema(){
    // Object[] seance = Seance.seance();
    // return new
    // SeanceCinema(seance[0],seance[1],EnsembleSalle.choisirSalle());
    // }

    public SeanceCinema(Heure horaire, int jour, Salle salle) {
	super(horaire, jour);
	this.salle = salle;
	nbPlacesVenduesTR = 0;
    }

    @Override
    public double chiffreAffaire() {
	// TODO Auto-generated method stub
	return salle.getTarif() * (getNbPlacesVenduesTN() + 0.6 * nbPlacesVenduesTR);
    }

    @Override
    public boolean equals(Object obj) {
	if (this == obj)
	    return true;
	if (obj == null)
	    return false;
	if (getClass() != obj.getClass())
	    return false;
	SeanceCinema other = (SeanceCinema) obj;
	if (getHoraire() == null) {
	    if (other.getHoraire() != null)
		return false;
	} else if (!getHoraire().equals(other.getHoraire()))
	    return false;
	if (salle == null) {
	    if (other.salle != null)
		return false;
	} else if (!salle.equals(other.salle))
	    return false;
	if (getJour() != other.getJour())
	    return false;
	return true;
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
