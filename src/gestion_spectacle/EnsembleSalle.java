package gestion_spectacle;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Set;
import java.util.StringTokenizer;

public class EnsembleSalle implements Iterable, Serializable {
    static public EnsembleSalle charger(String nomFichier) throws FileNotFoundException, IOException {
	ObjectInputStream ois = new ObjectInputStream(new FileInputStream(nomFichier));
	Object obj;
	try {
	    obj = ois.readObject();
	    if (obj.getClass() != EnsembleSalle.class) {
		obj = null;
	    }
	} catch (ClassNotFoundException e) {
	    obj = null;
	}
	ois.close();
	return (EnsembleSalle) obj;
    }

    public static EnsembleSalle ensembleSalle() {
	Scanner sc = new Scanner(System.in);
	boolean loop = true;
	String in;
	EnsembleSalle salles = new EnsembleSalle();
	while (loop) {
	    System.out.println("q pour quitter any key for create new room");
	    in = sc.nextLine();
	    switch (in) {
	    case "q":
		loop = false;
		break;
	    default:
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

    public void ajouter(Salle salle) {
	salles.add(salle);
    }

    public Salle choisirSalle() {
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

    public void sauvegarder(String nomFichier) throws FileNotFoundException, IOException {
	ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(nomFichier));
	oos.writeObject(this);
	oos.close();
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
