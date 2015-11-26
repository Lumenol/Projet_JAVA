package gestion_spectacle;

public class SeanceCinema extends Seance {

    private int nbPlacesVenduesTR;

    private Salle salle;

    @Override
    public boolean equals(Object obj) {
	if (this == obj)
	    return true;
	if (!super.equals(obj))
	    return false;
	if (getClass() != obj.getClass())
	    return false;
	SeanceCinema other = (SeanceCinema) obj;
	if (nbPlacesVenduesTR != other.nbPlacesVenduesTR)
	    return false;
	if (salle == null) {
	    if (other.salle != null)
		return false;
	} else if (!salle.equals(other.salle))
	    return false;
	return true;
    }

    @Override
    public int hashCode() {
	final int prime = 31;
	int result = super.hashCode();
	result = prime * result + nbPlacesVenduesTR;
	result = prime * result + ((salle == null) ? 0 : salle.hashCode());
	return result;
    }

    @Override
    public int nbPlacesDispo() {
	// TODO Auto-generated method stub
	return 0;
    }

    @Override
    public double tauxRemplissage() {
	// TODO Auto-generated method stub
	return 0;
    }

    @Override
    public String toString() {
	return "SeanceCinema [salle=" + salle + ", nbPlacesVenduesTR=" + nbPlacesVenduesTR + "]";
    }

    @Override
    public int totalCendu() {
	// TODO Auto-generated method stub
	return 0;
    }

    public void vendrePlacesTR(int nbre) {

    }

}
