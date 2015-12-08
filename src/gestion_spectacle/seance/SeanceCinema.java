package gestion_spectacle.seance;

import java.util.Scanner;
import java.util.StringTokenizer;

import gestion_spectacle.Heure;
import gestion_spectacle.exception.PasDeSalleException;
import gestion_spectacle.salle.EnsembleSalle;
import gestion_spectacle.salle.Salle;

public class SeanceCinema extends Seance {

    public static SeanceCinema seanceCinema(EnsembleSalle ens) throws PasDeSalleException {
	Object[] seance = Seance.seance();
	return new SeanceCinema((Heure) seance[0], (int) seance[1], ens.choisirSalle());
    }

    private int nbPlacesVenduesTR;

    private Salle salle;

    public SeanceCinema(Heure horaire, int jour, Salle salle) {
	super(horaire, jour);
	this.salle = salle;
	nbPlacesVenduesTR = 0;
    }

    @Override
    public double chiffreAffaire() {
	// TODO Auto-generated method stub
	return salle.getTarif() * (getNbPlacesVenduesTN() + 0.6 * nbPlacesVenduesTR);
    }

    @Override
    public boolean equals(Object obj) {
	if (this == obj)
	    return true;
	if (obj == null)
	    return false;
	if (getClass() != obj.getClass())
	    return false;
	SeanceCinema other = (SeanceCinema) obj;
	if (getHoraire() == null) {
	    if (other.getHoraire() != null)
		return false;
	} else if (!getHoraire().equals(other.getHoraire()))
	    return false;
	if (salle == null) {
	    if (other.salle != null)
		return false;
	} else if (!salle.equals(other.salle))
	    return false;
	if (getJour() != other.getJour())
	    return false;
	return true;
    }

    @Override
    public int nbPlacesDispo() {
	return salle.getCapacite() - totalVendu();
    }

    @Override
    public String nomSalle() {
	// TODO Auto-generated method stub
	return salle.getNomSalle();
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
	return getNbPlacesVenduesTN() + nbPlacesVenduesTR;
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
		    System.out.println("Place disponible : " + nbPlacesDispo());
		    System.out.println("Combient voulez-vous en vendre ?");
		    toka = new StringTokenizer(sc.nextLine());
		    if (toka.hasMoreTokens()) {
			try {
			    nbPlace = Integer.valueOf(toka.nextToken());
			} catch (NumberFormatException e) {
			}
		    }
		} while (nbPlace < 0 || nbPlace > nbPlacesDispo());
		vendrePlacesTN(nbPlace);
		break;

	    case "t":
		do {
		    System.out.println("Place disponible : " + nbPlacesDispo());
		    System.out.println("Combient voulez-vous en vendre ?");
		    toka = new StringTokenizer(sc.nextLine());
		    if (toka.hasMoreTokens()) {
			try {
			    nbPlace = Integer.valueOf(toka.nextToken());
			} catch (NumberFormatException e) {
			}
		    }
		} while (nbPlace < 0 || nbPlace > nbPlacesDispo());
		vendrePlacesTR(nbPlace);
		break;

	    case "r":
		loop = false;
		break;
	    }

	} while (loop);
    }

    @Override
    public void vendrePlacesTN(int nbre) throws IllegalArgumentException {
	if (nbPlacesDispo() < nbre) {
	    throw new IllegalArgumentException("Nombre de place insuffisant");
	}
	super.vendrePlacesTN(nbre);
    }

    public void vendrePlacesTR(int nbre) throws IllegalArgumentException {
	if (nbPlacesDispo() < nbre) {
	    throw new IllegalArgumentException("Nombre de place insuffisant");
	}
	nbPlacesVenduesTR += nbre;
    }

}
