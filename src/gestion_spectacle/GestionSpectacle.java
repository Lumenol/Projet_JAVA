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

import gestion_spectacle.exception.DonneIntrouvableExecption;
import gestion_spectacle.exception.PasDeSalleException;
import gestion_spectacle.exception.PasDeSpectacleException;
import gestion_spectacle.programmation.ProgrammationSemaine;
import gestion_spectacle.salle.EnsembleSalle;
import gestion_spectacle.salle.EnsembleTheatre;

public class GestionSpectacle {

    public static Scanner sc = new Scanner(System.in);

    static public Serializable charger(String nomFichier, Class<Serializable> c)
	    throws FileNotFoundException, IOException, DonneIntrouvableExecption {
	ObjectInputStream ois = new ObjectInputStream(new FileInputStream(nomFichier));
	Object obj;
	try {
	    obj = ois.readObject();
	    if (obj.getClass() != c) {
		throw new DonneIntrouvableExecption();
	    }
	} catch (ClassNotFoundException e) {
	    throw new DonneIntrouvableExecption();
	} finally {
	    ois.close();
	}

	return (Serializable) obj;
    }

    public static void main(String[] args) {
	List<ProgrammationSemaine> lesProgrammations = new ArrayList<ProgrammationSemaine>();
	EnsembleSalle salles = new EnsembleSalle();
	EnsembleTheatre sallesTheatre = new EnsembleTheatre();

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
	    System.out.println(
		    "(q)uitter (a)jouter programmation semaine (m)odifier (v)endre (c)harger (s)auvegarder (d)ebug");
	    StringTokenizer toka;
	    int s = -1;
	    switch (sc.nextLine()) {
	    case "q":
		loop = false;
		break;
	    case "a":
		if (lesProgrammations.size() < 52) {
		    try {
			lesProgrammations.add(ProgrammationSemaine.programmationSemaine(lesProgrammations.size(),
				salles, sallesTheatre));
		    } catch (PasDeSalleException e) {
			System.out.println("Il n'y a pas de salle");
		    }
		} else {
		    System.out.println("il y a deja 52 semaines de programmer");
		}
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
		    try {
			lesProgrammations.get(s).vendre();
		    } catch (PasDeSpectacleException e) {
			System.out.println("Il n'y a pas de spectacle de programmer");
		    }
		} else {
		    System.out.println("Il n'y a aucune programmation");
		}
		break;
	    case "s":
		try {
		    sauvegarder("salles.es", salles);
		    sauvegarder("salleTheatre.et", sallesTheatre);
		    sauvegarder("prodSemaine.ps", (Serializable) lesProgrammations);
		} catch (IOException e) {
		    e.printStackTrace();
		}
		break;

	    case "c":
		try {
		    salles = (EnsembleSalle) charger("salles.es", (Class<Serializable>) salles.getClass());

		    sallesTheatre = (EnsembleTheatre) charger("salleTheatre.et",
			    (Class<Serializable>) sallesTheatre.getClass());

		    lesProgrammations = (List<ProgrammationSemaine>) charger("prodSemaine.ps",
			    (Class<Serializable>) lesProgrammations.getClass());
		} catch (Exception e) {
		    e.printStackTrace();
		}
		break;

	    case "d":
		System.out.println(lesProgrammations);
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
