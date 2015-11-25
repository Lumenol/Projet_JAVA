package gestion_spectacle;

public class SalleTheatre extends Salle {

    private int nbFauteuils;
    private double prixFauteuil;

    public SalleTheatre(String nomSalle, int nbPlacesStandard, double tarif, int nbFauteuils, double prixFauteuil) {
	super(nomSalle, nbPlacesStandard + nbFauteuils, nbPlacesStandard, tarif);
	this.nbFauteuils = nbFauteuils;
	this.prixFauteuil = prixFauteuil;
    }

}
