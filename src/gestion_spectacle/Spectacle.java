package gestion_spectacle;

import java.io.Serializable;
import java.util.Collection;
import java.util.Iterator;
import java.util.Scanner;
import java.util.StringTokenizer;

public abstract class Spectacle implements Serializable {
    public static Spectacle choisirSpectacle(Collection<? extends Spectacle> ens) {
	Scanner sc = new Scanner(System.in);
	StringTokenizer toka;
	int index = -1, i = 0;
	StringBuffer buff = new StringBuffer();
	for (Iterator iterator = ens.iterator(); iterator.hasNext();) {
	    Spectacle spectacle = (Spectacle) iterator.next();
	    buff.append(i + "-" + spectacle.getTitre() + " ");
	    i++;
	}
	do {
	    System.out.println(buff.toString());
	    toka = new StringTokenizer(sc.nextLine());
	    if (toka.hasMoreTokens()) {
		try {
		    index = Integer.valueOf(toka.nextToken());
		} catch (NumberFormatException e) {
		}
	    }
	} while (index < 0 || index >= ens.size());
	return (Spectacle) ens.toArray()[index];
    }

    protected static String[] spectacle() {
	Scanner sc = new Scanner(System.in);
	String[] r = new String[2];
	do {
	    System.out.println("titre");
	    r[0] = sc.nextLine().trim();
	} while (r[0].equals(""));
	do {
	    System.out.println("interpretes");
	    r[1] = sc.nextLine().trim();
	} while (r[1].equals(""));
	return r;
    }

    private String titre, interpretes;

    public Spectacle(String titre, String interpretes) {
	super();
	this.titre = titre;
	this.interpretes = interpretes;
    }

    @Override
    public boolean equals(Object obj) {
	if (this == obj)
	    return true;
	if (obj == null)
	    return false;
	if (getClass() != obj.getClass())
	    return false;
	Spectacle other = (Spectacle) obj;
	if (interpretes == null) {
	    if (other.interpretes != null)
		return false;
	} else if (!interpretes.equals(other.interpretes))
	    return false;
	if (titre == null) {
	    if (other.titre != null)
		return false;
	} else if (!titre.equals(other.titre))
	    return false;
	return true;
    }

    public String getInterpretes() {
	return interpretes;
    }

    public String getTitre() {
	return titre;
    }

    @Override
    public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + ((interpretes == null) ? 0 : interpretes.hashCode());
	result = prime * result + ((titre == null) ? 0 : titre.hashCode());
	return result;
    }

    @Override
    public String toString() {
	return "Spectacle [titre=" + titre + ", interpretes=" + interpretes + "]";
    }

}
