package gestion_spectacle.salle;

import java.io.Serializable;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Salle implements Serializable {
    public static Salle salle() {
	Scanner sc = new Scanner(System.in);
	String nomSalle;
	StringTokenizer toka;
	int nbPlacesStandard = 0;
	double tarif = -1;
	do {
	    System.out.println("Nom de la salle :");
	    nomSalle = sc.nextLine().trim();
	} while (nomSalle.equals(""));
	do {
	    System.out.println("Nombre de places standard de la salle :");
	    toka = new StringTokenizer(sc.nextLine());
	    if (toka.hasMoreTokens()) {
		try {
		    nbPlacesStandard = Integer.valueOf(toka.nextToken());
		} catch (NumberFormatException e) {
		}
	    }
	} while (nbPlacesStandard <= 0);
	do {
	    System.out.println("Prix de la place :");
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

    private int capacite, nbPlacesStandard;

    private String nomSalle;

    private double tarif;

    public Salle(String nomSalle, int nbPlacesStandard, double tarif) {
	this(nomSalle, nbPlacesStandard, nbPlacesStandard, tarif);
    }

    public Salle(String nomSalle, int capacite, int nbPlacesStandard, double tarif) {
	super();
	this.nomSalle = nomSalle;
	this.capacite = capacite;
	this.nbPlacesStandard = nbPlacesStandard;
	this.tarif = tarif;
    }

    @Override
    public boolean equals(Object obj) {
	if (this == obj)
	    return true;
	if (obj == null)
	    return false;
	if (getClass() != obj.getClass())
	    return false;
	Salle other = (Salle) obj;
	if (nomSalle == null) {
	    if (other.nomSalle != null)
		return false;
	} else if (!nomSalle.equals(other.nomSalle))
	    return false;
	return true;
    }

    public int getCapacite() {
	return capacite;
    }

    public int getNbPlacesStandard() {
	return nbPlacesStandard;
    }

    public String getNomSalle() {
	return nomSalle;
    }

    public double getTarif() {
	// TODO Auto-generated method stub
	return tarif;
    }

    @Override
    public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + ((nomSalle == null) ? 0 : nomSalle.hashCode());
	return result;
    }

    public void setNomSalle(String nomSalle) {
	this.nomSalle = nomSalle;
    }

    @Override
    public String toString() {
	return "Salle [capacite=" + capacite + ", nbPlacesStandard=" + nbPlacesStandard + ", nomSalle=" + nomSalle
		+ ", tarif=" + tarif + "]";
    }

}
