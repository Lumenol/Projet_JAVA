package gestion_spectacle;

public class SeanceTheatre extends Seance {
    private int nbFauteuilsVendus;

    private SalleTheatre salleTheatre;

    @Override
    public double chiffreAffaire() {
	// TODO Auto-generated method stub
	return nbPlacesVenduesTN * salleTheatre.getTarif() + nbFauteuilsVendus * salleTheatre.getPrixFauteuil();
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
	if (jour != other.jour)
	    return false;
	return true;
    }

    @Override
    public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + nbFauteuilsVendus;
	result = prime * result + ((salleTheatre == null) ? 0 : salleTheatre.hashCode());
	return result;
    }

    public int nbFauteuilsDispo() {
	return salleTheatre.getNbFauteuils() - nbFauteuilsVendus;

    }

    @Override
    public int nbPlacesDispo() {
	// TODO Auto-generated method stub
	return salleTheatre.getCapacite() - nbFauteuilsVendus - nbPlacesVenduesTN;
    }

    @Override
    public double tauxRemplissage() {
	// TODO Auto-generated method stub
	return totalVendu() / salleTheatre.getCapacite();
    }

    @Override
    public String toString() {
	return "SeanceTheatre [salleTheatre=" + salleTheatre + ", nbFauteuilsVendus=" + nbFauteuilsVendus + "]";
    }

    @Override
    public int totalVendu() {
	// TODO Auto-generated method stub
	return nbPlacesVenduesTN + nbFauteuilsVendus;
    }

    public void vendrePlacesFauteuil(int nbre) throws IllegalArgumentException {
	if (nbFauteuilsDispo() < nbre) {
	    throw new IllegalArgumentException("Nombre de fauteuil insufisant");
	}
	nbFauteuilsVendus += nbre;
    }

    @Override
    public void vendrePlacesTN(int nbre) throws IllegalArgumentException {
	if (nbPlacesDispo() < nbre) {
	    throw new IllegalArgumentException("Nombre de place insufisant");
	}
	super.vendrePlacesTN(nbre);
    }

}
