package gestion_spectacle.seance;

import java.io.Serializable;
import java.util.Scanner;
import java.util.StringTokenizer;

import gestion_spectacle.Heure;
import gestion_spectacle.salle.EnsembleSalle;

public abstract class Seance implements Comparable<Seance>, Serializable {
    protected static Object[] seance() {
	Scanner sc = new Scanner(System.in);
	Object[] r = new Object[2];
	int jour = 0, heure = -1, minute = -1;
	StringTokenizer toka;
	do {
	    System.out.println("jour (1-7):");
	    toka = new StringTokenizer(sc.nextLine());
	    if (toka.hasMoreTokens()) {
		try {
		    jour = Integer.valueOf(toka.nextToken());
		} catch (NumberFormatException e) {
		}
	    }
	} while (jour <= 0 || jour > 7);
	do {
	    System.out.println("heure :");
	    toka = new StringTokenizer(sc.nextLine());
	    if (toka.hasMoreTokens()) {
		try {
		    heure = Integer.valueOf(toka.nextToken());
		} catch (NumberFormatException e) {
		}
	    }
	} while (heure < 0 || heure > 23);
	do {
	    System.out.println("minutes :");
	    toka = new StringTokenizer(sc.nextLine());
	    if (toka.hasMoreTokens()) {
		try {
		    minute = Integer.valueOf(toka.nextToken());
		} catch (NumberFormatException e) {
		}
	    }
	} while (minute < 0 || minute > 59);

	r[0] = new Heure(heure, minute);
	r[1] = jour;
	return r;
    }

    private Heure horaire;

    private int jour, nbPlacesVenduesTN;

    public Seance(Heure horaire, int jour) {
	super();
	this.horaire = horaire;
	this.jour = jour;
	nbPlacesVenduesTN = 0;
    }

    public abstract double chiffreAffaire();

    @Override
    public int compareTo(Seance o) {
	if (jour < o.jour) {
	    return -1;
	} else if (jour > o.jour) {
	    return 1;
	} else {
	    return horaire.compareTo(o.horaire);
	}
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

    public Heure getHoraire() {
	return horaire;
    }

    public int getJour() {
	return jour;
    }

    public int getNbPlacesVenduesTN() {
	return nbPlacesVenduesTN;
    }

    @Override
    public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + jour;
	return result;
    }

    public abstract int nbPlacesDispo();

    public abstract String nomSalle();

    public abstract double tauxRemplissage();

    @Override
    public String toString() {
	return "Seance [jour=" + jour + ", nbPlacesVenduesTN=" + nbPlacesVenduesTN + ", horaire=" + horaire + "]";
    }

    public abstract int totalVendu();

    public abstract void vendre();

    public void vendrePlacesTN(int nbre) {
	nbPlacesVenduesTN += nbre;
    }

}
