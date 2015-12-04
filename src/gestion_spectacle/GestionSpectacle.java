package gestion_spectacle;

import java.util.Scanner;
import java.util.StringTokenizer;

public class GestionSpectacle {

    public static Scanner sc = new Scanner(System.in);

    public static EnsembleSalle ensembleSalle() {
	boolean loop = true;
	String in;
	while (loop) {
	    in = sc.next();
	}
	return null;
    }

    public static void main(String[] args) {
	System.out.println(salleTheatre());
    }

    public static Salle salle() {
	String nomSalle;
	StringTokenizer toka;
	int nbPlacesStandard = 0;
	double tarif = -1;
	do {
	    System.out.println("Nom de la salle");
	    nomSalle = sc.nextLine().trim();
	} while (nomSalle.equals(""));
	do {
	    System.out.println("nbPlacesStandard");
	    toka = new StringTokenizer(sc.nextLine());
	    if (toka.hasMoreTokens()) {
		try {
		    nbPlacesStandard = Integer.valueOf(toka.nextToken());
		} catch (NumberFormatException e) {
		}
	    }
	} while (nbPlacesStandard <= 0);
	do {
	    System.out.println("tarif");
	    toka = new StringTokenizer(sc.nextLine());
	    if (toka.hasMoreTokens()) {
		try {
		    tarif = Double.valueOf(toka.nextToken());
		} catch (NumberFormatException e) {
		}
	    }
	} while (tarif < 0);
	return new Salle(nomSalle, nbPlacesStandard, tarif);
    }

    public static SalleTheatre salleTheatre() {
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

}
