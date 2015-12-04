package gestion_spectacle;

public class SalleTheatre extends Salle {

    private int nbFauteuils;

    private double prixFauteuil;

    public SalleTheatre(Salle salle, int nbFauteuils, double prixFauteuil) {
	this(salle.getNomSalle(), salle.getNbPlacesStandard(), salle.getTarif(), nbFauteuils, prixFauteuil);
    }

    public SalleTheatre(String nomSalle, int nbPlacesStandard, double tarif, int nbFauteuils, double prixFauteuil) {
	super(nomSalle, nbPlacesStandard + nbFauteuils, nbPlacesStandard, tarif);
	this.nbFauteuils = nbFauteuils;
	this.prixFauteuil = prixFauteuil;
    }

    public int getNbFauteuils() {
	return nbFauteuils;
    }

    public double getPrixFauteuil() {
	return prixFauteuil;
    }

    @Override
    public String toString() {
	return super.toString() + " SalleTheatre [nbFauteuils=" + nbFauteuils + ", prixFauteuil=" + prixFauteuil + "]";
    }

}
