package gestion_spectacle;

public class SalleTheatre extends Salle {

    private int nbFauteuils;

    private double prixFauteuil;

    public SalleTheatre(String nomSalle, int nbPlacesStandard, double tarif, int nbFauteuils, double prixFauteuil) {
	super(nomSalle, nbPlacesStandard + nbFauteuils, nbPlacesStandard, tarif);
	this.nbFauteuils = nbFauteuils;
	this.prixFauteuil = prixFauteuil;
    }

    @Override
    public boolean equals(Object obj) {
	if (this == obj)
	    return true;
	if (obj == null)
	    return false;
	if (getClass() != obj.getClass())
	    return false;
	SalleTheatre other = (SalleTheatre) obj;
	if (nbFauteuils != other.nbFauteuils)
	    return false;
	if (Double.doubleToLongBits(prixFauteuil) != Double.doubleToLongBits(other.prixFauteuil))
	    return false;
	return true;
    }

    public int getNbFauteuils() {
	return nbFauteuils;
    }

    public double getPrixFauteuil() {
	return prixFauteuil;
    }

    @Override
    public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + nbFauteuils;
	long temp;
	temp = Double.doubleToLongBits(prixFauteuil);
	result = prime * result + (int) (temp ^ (temp >>> 32));
	return result;
    }

    @Override
    public String toString() {
	return "SalleTheatre [nbFauteuils=" + nbFauteuils + ", prixFauteuil=" + prixFauteuil + "]";
    }

}
