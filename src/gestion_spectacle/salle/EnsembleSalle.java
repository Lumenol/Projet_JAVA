package gestion_spectacle.salle;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Set;
import java.util.StringTokenizer;

import gestion_spectacle.exception.PasDeSalleException;

public class EnsembleSalle implements Iterable, Serializable {
    /**
     * cr�e un ensemble de salle par saisie interactive
     * 
     * @return ensemble de salle
     */
    public static EnsembleSalle ensembleSalle() {
	Scanner sc = new Scanner(System.in);
	boolean loop = true;
	String in;
	EnsembleSalle salles = new EnsembleSalle();
	System.out.println("Cr�ation ensemble salle");
	while (loop) {
	    System.out.println("(q)uitter (a)jouter");
	    in = sc.nextLine();
	    switch (in) {
	    case "q":
		loop = false;
		break;
	    case "a":
		salles.ajouter(Salle.salle());
		break;
	    }
	}
	return salles;
    }

    private Set<Salle> salles;

    public EnsembleSalle() {
	salles = new HashSet<Salle>();
    }

    public boolean add(Salle e) {
	return salles.add(e);
    }

    public void ajouter(Salle salle) {
	salles.add(salle);
    }

    /**
     * Permet de s�lectionner une salle dans l'ensemble par saisie interactive
     * 
     * @return Salle s�lectionner
     * @throws PasDeSalleException
     *             si l'ensemble est vide
     */
    public Salle choisirSalle() throws PasDeSalleException {
	if (salles == null || salles.isEmpty()) {
	    throw new PasDeSalleException();
	}
	Scanner sc = new Scanner(System.in);
	StringTokenizer toka;
	int index = -1, i = 0;
	String[] nom = new String[salles.size()];
	StringBuffer buff = new StringBuffer();
	for (Iterator iterator = salles.iterator(); iterator.hasNext();) {
	    Salle salle = (Salle) iterator.next();
	    buff.append(i + "-" + salle.getNomSalle() + " ");
	    nom[i] = salle.getNomSalle();
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
	} while (index < 0 || index >= salles.size());
	return salle(nom[index]);
    }

    @Override
    public boolean equals(Object obj) {
	if (this == obj)
	    return true;
	if (obj == null)
	    return false;
	if (getClass() != obj.getClass())
	    return false;
	EnsembleSalle other = (EnsembleSalle) obj;
	if (salles == null) {
	    if (other.salles != null)
		return false;
	} else if (!salles.equals(other.salles))
	    return false;
	return true;
    }

    @Override
    public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + ((salles == null) ? 0 : salles.hashCode());
	return result;
    }

    public boolean isEmpty() {
	return salles.isEmpty();
    }

    public Iterator<Salle> iterator() {
	return salles.iterator();
    }

    public Salle salle(String nom) {
	Iterator<Salle> it = salles.iterator();
	while (it.hasNext()) {
	    Salle salle = it.next();
	    if (salle.getNomSalle().equals(nom)) {
		return salle;
	    }
	}
	return null;
    }

    @Override
    public String toString() {
	StringBuffer b = new StringBuffer("EnsembleSalle\n");
	Iterator<Salle> it = salles.iterator();
	while (it.hasNext()) {
	    Salle salle = it.next();
	    b.append(salle.toString() + "\n");
	}
	return b.toString();
    }

}
