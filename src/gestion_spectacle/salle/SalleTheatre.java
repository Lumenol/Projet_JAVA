package gestion_spectacle.salle;

import java.util.Scanner;
import java.util.StringTokenizer;

public class SalleTheatre extends Salle {

    public static SalleTheatre salleTheatre() {
	Scanner sc = new Scanner(System.in);
	Salle salle = salle();
	int nbFauteuils = -1;
	StringTokenizer toka;
	double prixFauteuil = -1;
	do {
	    System.out.println("nbFauteuils");
	    toka = new StringTokenizer(sc.nextLine());
	    if (toka.hasMoreTokens()) {
		try {
		    nbFauteuils = Integer.valueOf(toka.nextToken());
		} catch (NumberFormatException e) {
		}
	    }
	} while (nbFauteuils < 0);
	do {
	    System.out.println("prixFauteuil");
	    toka = new StringTokenizer(sc.nextLine());
	    if (toka.hasMoreTokens()) {
		try {
		    prixFauteuil = Double.valueOf(toka.nextToken());
		} catch (NumberFormatException e) {
		}
	    }
	} while (prixFauteuil < 0);
	return new SalleTheatre(salle, nbFauteuils, prixFauteuil);
    }

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
