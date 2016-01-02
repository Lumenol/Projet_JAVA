package gestion_spectacle.spectacle;

import java.util.Scanner;
import java.util.StringTokenizer;

import gestion_spectacle.Heure;

public class Film extends Spectacle {
    /**
     * crée un film par saisie interactive
     * 
     * @return le film crée
     */
    public static Film film() {
	String[] spectacle = Spectacle.spectacle();
	Scanner sc = new Scanner(System.in);
	String realisateur;
	int duree = 0;
	StringTokenizer toka;
	do {
	    System.out.println("realisateur");
	    realisateur = sc.nextLine().trim();
	} while (realisateur.equals(""));

	do {
	    System.out.println("duree en min");
	    toka = new StringTokenizer(sc.nextLine());
	    if (toka.hasMoreTokens()) {
		try {
		    duree = Integer.valueOf(toka.nextToken());
		} catch (NumberFormatException e) {
		}
	    }
	} while (duree <= 0);

	return new Film(spectacle[0], spectacle[1], realisateur, new Heure(duree));
    }

    private Heure duree;

    private String realisateur;

    public Film(String titre, String interpretes, String realisateur, Heure duree) {
	super(titre, interpretes);
	this.realisateur = realisateur;
	this.duree = duree;
    }

    @Override
    public boolean equals(Object obj) {
	if (this == obj)
	    return true;
	if (!super.equals(obj))
	    return false;
	if (getClass() != obj.getClass())
	    return false;
	Film other = (Film) obj;
	if (duree == null) {
	    if (other.duree != null)
		return false;
	} else if (!duree.equals(other.duree))
	    return false;
	if (realisateur == null) {
	    if (other.realisateur != null)
		return false;
	} else if (!realisateur.equals(other.realisateur))
	    return false;
	return true;
    }

    @Override
    public int hashCode() {
	final int prime = 31;
	int result = super.hashCode();
	result = prime * result + ((duree == null) ? 0 : duree.hashCode());
	result = prime * result + ((realisateur == null) ? 0 : realisateur.hashCode());
	return result;
    }

    @Override
    public String toString() {
	return super.toString() + " Film [duree=" + duree + ", realisateur=" + realisateur + "]";
    }
}
