package gestion_spectacle;

public class Salle {
    private String nomSalle;
    private int capacite, nbPlacesStandard;
    private double tarif;

    public Salle(String nomSalle, int capacite, int nbPlacesStandard, double tarif) {
	super();
	this.nomSalle = nomSalle;
	this.capacite = capacite;
	this.nbPlacesStandard = nbPlacesStandard;
	this.tarif = tarif;
    }
    
    
}
