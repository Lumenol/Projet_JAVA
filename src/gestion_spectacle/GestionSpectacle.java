package gestion_spectacle;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.StringTokenizer;

public class GestionSpectacle {

    public static Scanner sc = new Scanner(System.in);

    static public Serializable charger(String nomFichier, Class<Serializable> c)
	    throws FileNotFoundException, IOException {
	ObjectInputStream ois = new ObjectInputStream(new FileInputStream(nomFichier));
	Object obj;
	try {
	    obj = ois.readObject();
	    if (obj.getClass() != c) {
		obj = null;
	    }
	} catch (ClassNotFoundException e) {
	    obj = null;
	}
	ois.close();
	return (Serializable) obj;
    }

    public static void main(String[] args) {
	List<ProgrammationSemaine> lesProgrammations = new ArrayList<ProgrammationSemaine>();
	EnsembleSalle salles = null;
	EnsembleTheatre sallesTheatre = null;

	salles = EnsembleSalle.ensembleSalle();
	sallesTheatre = EnsembleTheatre.ensembleSalleTheatre();

	// try {
	// salles = EnsembleSalle.charger("salles"); //
	// sallesTheatre = EnsembleTheatre.charger("salleTheatre");
	//
	// lesProgrammations = (List<ProgrammationSemaine>)
	// charger("progSemaine",
	// (Class<Serializable>) lesProgrammations.getClass());
	// } catch (IOException e) {
	// e.printStackTrace();
	// }

	// System.out.println(salles);
	// System.out.println(sallesTheatre);

	// System.out.println(lesProgrammations);

	// System.out.println(salles.choisirSalle());

	// try {
	// ProgrammationSemaine.programmationSemaine(0, salles,
	// sallesTheatre).sauvegarder("progSemaine");
	// } catch (IllegalArgumentException | IOException e) {
	// // TODO Auto-generated catch block
	// e.printStackTrace();
	// }

	boolean loop = true;

	while (loop) {
	    System.out.println("(q)uitter (a)jouter programmation semaine (m)odifier (v)endre (c)harger (s)auvegarder");
	    StringTokenizer toka;
	    int s = -1;
	    switch (sc.nextLine()) {
	    case "q":
		loop = false;
		break;
	    case "a":
		lesProgrammations.add(
			ProgrammationSemaine.programmationSemaine(lesProgrammations.size(), salles, sallesTheatre));
		break;

	    case "m":
		if (!lesProgrammations.isEmpty()) {
		    do {
			System.out.println("Il y a " + lesProgrammations.size()
				+ " semaines enregistre la quelle voulez-vous modifier ?");
			toka = new StringTokenizer(sc.nextLine());
			if (toka.hasMoreTokens()) {
			    try {
				s = Integer.valueOf(toka.nextToken());
			    } catch (NumberFormatException e) {
			    }
			}
		    } while (s < 0 || s >= lesProgrammations.size());
		    lesProgrammations.get(s).modification(salles, sallesTheatre);
		} else {
		    System.out.println("Il n'y a aucune programmation");
		}

		break;

	    case "v":
		if (!lesProgrammations.isEmpty()) {
		    do {
			System.out.println("Il y a " + lesProgrammations.size()
				+ " semaines enregistre pour la quelle voulez-vous vendre des places ?");
			toka = new StringTokenizer(sc.nextLine());
			if (toka.hasMoreTokens()) {
			    try {
				s = Integer.valueOf(toka.nextToken());
			    } catch (NumberFormatException e) {
			    }
			}
		    } while (s < 0 || s >= lesProgrammations.size());
		    lesProgrammations.get(s).vendre();
		} else {
		    System.out.println("Il n'y a aucune programmation");
		}
		break;
	    case "s":
		try {
		    salles.sauvegarder("salles");
		    sallesTheatre.sauvegarder("salleTheatre");
		    sauvegarder("prodSemaine", (Serializable) lesProgrammations);
		} catch (IOException e) {
		    e.printStackTrace();
		}
		break;

	    case "c":
		try {
		    salles = EnsembleSalle.charger("salles");
		    sallesTheatre = EnsembleTheatre.charger("salleTheatre");

		    lesProgrammations = (List<ProgrammationSemaine>) charger("prodSemaine",
			    (Class<Serializable>) lesProgrammations.getClass());
		} catch (IOException e) {
		    e.printStackTrace();
		}
		break;
	    default:
		break;
	    }
	}
    }

    public static void sauvegarder(String nomFichier, Serializable obj) throws FileNotFoundException, IOException {
	ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(nomFichier));
	oos.writeObject(obj);
	oos.close();
    }

}
