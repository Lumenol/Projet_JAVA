package gestion_spectacle.seance;

import java.util.Scanner;
import java.util.StringTokenizer;

import gestion_spectacle.Heure;
import gestion_spectacle.exception.PasDeSalleException;
import gestion_spectacle.salle.EnsembleTheatre;
import gestion_spectacle.salle.SalleTheatre;

public class SeanceTheatre extends Seance {
    public static SeanceTheatre seanceTheatre(EnsembleTheatre ens) throws PasDeSalleException {
	Object[] seance = Seance.seance();
	return new SeanceTheatre((Heure) seance[0], (int) seance[1], (SalleTheatre) ens.choisirSalle());
    }

    private int nbFauteuilsVendus;

    private SalleTheatre salleTheatre;

    public SeanceTheatre(Heure horaire, int jour, SalleTheatre salleTheatre) {
	super(horaire, jour);
	this.salleTheatre = salleTheatre;
	nbFauteuilsVendus = 0;
    }

    @Override
    public double chiffreAffaire() {
	// TODO Auto-generated method stub
	return getNbPlacesVenduesTN() * salleTheatre.getTarif() + nbFauteuilsVendus * salleTheatre.getPrixFauteuil();
    }

    public int getNbFauteuilsVendus() {
	return nbFauteuilsVendus;
    }

    public int nbFauteuilsDispo() {
	return salleTheatre.getNbFauteuils() - nbFauteuilsVendus;

    }

    @Override
    public int nbPlacesDispo() {
	// TODO Auto-generated method stub
	return nbFauteuilsDispo() + nbPlaceStandardDispo();
    }

    public int nbPlaceStandardDispo() {
	return salleTheatre.getNbPlacesStandard() - getNbPlacesVenduesTN();
    }

    @Override
    public String nomSalle() {
	// TODO Auto-generated method stub
	return salleTheatre.getNomSalle();
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
	return getNbPlacesVenduesTN() + nbFauteuilsVendus;
    }

    @Override
    public void vendre() {
	boolean loop = true;
	Scanner sc = new Scanner(System.in);
	int nbPlace = -1;
	StringTokenizer toka;
	do {
	    System.out.println("(n)ormal (t)arif-reduit (r)etour");
	    switch (sc.nextLine()) {
	    case "n":
		do {
		    System.out.println("Place disponible : " + nbPlaceStandardDispo());
		    System.out.println("Combient voulez-vous en vendre ?");
		    toka = new StringTokenizer(sc.nextLine());
		    if (toka.hasMoreTokens()) {
			try {
			    nbPlace = Integer.valueOf(toka.nextToken());
			} catch (NumberFormatException e) {
			}
		    }
		} while (nbPlace < 0 || nbPlace > nbPlaceStandardDispo());
		vendrePlacesTN(nbPlace);
		break;

	    case "t":
		do {
		    System.out.println("Fauteuils disponible : " + nbFauteuilsDispo());
		    System.out.println("Combient voulez-vous en vendre ?");
		    toka = new StringTokenizer(sc.nextLine());
		    if (toka.hasMoreTokens()) {
			try {
			    nbPlace = Integer.valueOf(toka.nextToken());
			} catch (NumberFormatException e) {
			}
		    }
		} while (nbPlace < 0 || nbPlace > nbFauteuilsDispo());
		vendrePlacesFauteuil(nbPlace);
		break;

	    case "r":
		loop = false;
		break;
	    }

	} while (loop);
    }

    public void vendrePlacesFauteuil(int nbre) throws IllegalArgumentException {
	if (nbFauteuilsDispo() < nbre) {
	    throw new IllegalArgumentException("Nombre de fauteuil insuffisant");
	}
	nbFauteuilsVendus += nbre;
    }

    @Override
    public void vendrePlacesTN(int nbre) throws IllegalArgumentException {
	if (nbPlacesDispo() < nbre) {
	    throw new IllegalArgumentException("Nombre de place insuffisant");
	}
	super.vendrePlacesTN(nbre);
    }

}
