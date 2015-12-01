package gestion_spectacle;

public class SeanceCinema extends Seance {

    private int nbPlacesVenduesTR;

    private Salle salle;

    
    @Override
    public double chiffreAffaire() {
	// TODO Auto-generated method stub
	return salle.getTarif()*(nbPlacesVenduesTN+0.6*nbPlacesVenduesTR);
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

    @Override
    public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + nbPlacesVenduesTR;
	result = prime * result + ((salle == null) ? 0 : salle.hashCode());
	return result;
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
	return nbPlacesVenduesTN + nbPlacesVenduesTR;
    }

    @Override
    public void vendrePlacesTN(int nbre) throws IllegalArgumentException {
	if (nbPlacesDispo() < nbre) {
	    throw new IllegalArgumentException("Nombre de place insufisant");
	}
	super.vendrePlacesTN(nbre);
    }

    public void vendrePlacesTR(int nbre) throws IllegalArgumentException {
	if (nbPlacesDispo() < nbre) {
	    throw new IllegalArgumentException("Nombre de place insufisant");
	}
	nbPlacesVenduesTR += nbre;
    }

}
