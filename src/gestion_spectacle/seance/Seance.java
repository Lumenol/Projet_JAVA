package gestion_spectacle.seance;

import java.io.Serializable;
import java.util.Scanner;
import java.util.StringTokenizer;

import gestion_spectacle.Heure;

public abstract class Seance implements Comparable<Seance>, Serializable {
    /**
     * demande le jour et l'heure d'une s�ance pour cr�e des s�ances de cin�ma
     * ou de th��tre
     *
     * @return [0] objet de type Heure [1] entier pour le jour
     */
    protected static Object[] seance() {
	Scanner sc = new Scanner(System.in);
	Object[] r = new Object[2];
	int jour = 0, heure = -1, minute = -1;
	StringTokenizer toka;
	do {
	    System.out.println("jour");
	    toka = new StringTokenizer(sc.nextLine());
	    if (toka.hasMoreTokens()) {
		try {
		    jour = Integer.valueOf(toka.nextToken());
		} catch (NumberFormatException e) {
		}
	    }
	} while (jour <= 0 || jour > 7);
	do {
	    System.out.println("heure");
	    toka = new StringTokenizer(sc.nextLine());
	    if (toka.hasMoreTokens()) {
		try {
		    heure = Integer.valueOf(toka.nextToken());
		} catch (NumberFormatException e) {
		}
	    }
	} while (heure < 0 || heure > 23);
	do {
	    System.out.println("minutes");
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

    /**
     * retourne le chiffre d'affaire d'un s�ance
     *
     * @return chiffre d'affaire
     */
    public abstract double chiffreAffaire();

    @Override
    /**
     * Tri par ordre chronologique
     */
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

    /**
     * @return nombre de place disponible
     */
    public abstract int nbPlacesDispo();

    public abstract String nomSalle();

    /**
     * donne le taux de remplissage en pourcentage
     *
     * @return
     */
    public abstract double tauxRemplissage();

    @Override
    public String toString() {
	return "Seance [jour=" + jour + ", nbPlacesVenduesTN=" + nbPlacesVenduesTN + ", horaire=" + horaire + "]";
    }

    /**
     *
     * @return nombre de place vendu au total
     */
    public abstract int totalVendu();

    /**
     * permet de vendre des place de mani�re interactive
     */
    public abstract void vendre();

    /**
     * vend des place au tarif normal
     * 
     * @param nbre
     *            nombre de place a vendre
     */
    public void vendrePlacesTN(int nbre) {
	nbPlacesVenduesTN += nbre;
    }

}
