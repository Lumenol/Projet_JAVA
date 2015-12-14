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
import gestion_spectacle.salle.Salle;
import gestion_spectacle.salle.SalleTheatre;

public class GestionSpectacle {

    public static Scanner sc = new Scanner(System.in);

    static public Serializable charger(String nomFichier, Class<Serializable> c)
	    throws FileNotFoundException, IOException, DonneIntrouvableExecption {
	ObjectInputStream ois = new ObjectInputStream(new FileInputStream(nomFichier));
	Object obj;
	try {
	    obj = ois.readObject();
	    if (obj.getClass() != c) {
		throw new DonneIntrouvableExecption("Le fichier ne contient pas les données recherche");
	    }
	} catch (ClassNotFoundException e) {
	    throw new DonneIntrouvableExecption("Le fichier ne contient pas les données recherche");
	} finally {
	    ois.close();
	}

	return (Serializable) obj;
    }

    public static void main(String[] args) {
	List<ProgrammationSemaine> lesProgrammations = new ArrayList<ProgrammationSemaine>();
	EnsembleSalle salles = new EnsembleSalle();
	EnsembleTheatre sallesTheatre = new EnsembleTheatre();

	// salles = EnsembleSalle.ensembleSalle();
	// sallesTheatre = EnsembleTheatre.ensembleSalleTheatre();

	boolean loop = true, loop2;

	while (loop) {
	    System.out.println(
		    "(q)uitter (a)jouter programmation semaine (m)odifier (v)endre (c)harger (s)auvegarder (d)ebug (salle) (co)nsultation");
	    StringTokenizer toka;
	    int s = -1;
	    switch (sc.nextLine()) {
	    case "q":
		loop = false;
		break;

	    case "co":
		if (!lesProgrammations.isEmpty()) {
		    do {
			System.out.println("Il y a " + lesProgrammations.size()
				+ " semaines enregistre la quelle voulez-vous consulter ?");
			toka = new StringTokenizer(sc.nextLine());
			if (toka.hasMoreTokens()) {
			    try {
				s = Integer.valueOf(toka.nextToken());
			    } catch (NumberFormatException e) {
			    }
			}
		    } while (s < 0 || s >= lesProgrammations.size());
		    try {
			lesProgrammations.get(s).consultation();
		    } catch (PasDeSpectacleException e) {
			System.out.println("Erreur " + e.getMessage());
		    }
		} else {
		    System.out.println("Il n'y a aucune programmation");
		}

		break;

	    case "salle":
		loop2 = true;
		while (loop2) {
		    System.out.println("(r)etour (s)tandard (t)heatre");
		    switch (sc.nextLine()) {
		    case "r":
			loop2 = false;
			break;

		    case "s":
			salles.add(Salle.salle());
			break;

		    case "t":
			sallesTheatre.add(SalleTheatre.salleTheatre());
			break;

		    }
		}
		break;

	    case "a":
		if (lesProgrammations.size() < 52) {
		    try {
			lesProgrammations.add(ProgrammationSemaine.programmationSemaine(lesProgrammations.size(),
				salles, sallesTheatre));
		    } catch (PasDeSalleException e) {
			System.out.println(e.getMessage());
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
		loop2 = true;
		while (loop2) {
		    System.out.println("(r)etour (s)alles (t)heatre (p)rogammation");
		    switch (sc.nextLine()) {
		    case "r":
			loop2 = false;
			break;

		    case "s":
			System.out.println("Entre nom fichier");
			try {
			    sauvegarder(sc.nextLine(), salles);
			    System.out.println("Fichier sauvegarder");
			} catch (Exception e) {
			    System.out.println("Erreur " + e.getMessage());
			}
			break;

		    case "t":
			System.out.println("Entre nom fichier");
			try {
			    sauvegarder(sc.nextLine(), sallesTheatre);
			    System.out.println("Fichier sauvegarder");
			} catch (Exception e) {
			    System.out.println("Erreur " + e.getMessage());
			}

			break;

		    case "p":
			System.out.println("Entre nom fichier");
			try {
			    sauvegarder(sc.nextLine(), (Serializable) lesProgrammations);
			    System.out.println("Fichier sauvegarder");
			} catch (Exception e) {
			    System.out.println("Erreur " + e.getMessage());
			}
			break;
		    }
		}
		break;

	    case "c":

		loop2 = true;
		while (loop2) {
		    System.out.println("(r)etour (s)alles (t)heatre (p)rogammation");
		    switch (sc.nextLine()) {
		    case "r":
			loop2 = false;
			break;

		    case "s":
			System.out.println("Entre nom fichier");
			try {
			    salles = (EnsembleSalle) charger(sc.nextLine(), (Class<Serializable>) salles.getClass());
			    System.out.println("Fichier chargé");
			} catch (Exception e) {
			    System.out.println("Erreur " + e.getMessage());
			}
			break;

		    case "t":
			System.out.println("Entre nom fichier");
			try {
			    sallesTheatre = (EnsembleTheatre) charger(sc.nextLine(),
				    (Class<Serializable>) sallesTheatre.getClass());
			    System.out.println("Fichier chargé");
			} catch (Exception e) {
			    System.out.println("Erreur " + e.getMessage());
			}

			break;

		    case "p":
			System.out.println("Entre nom fichier");
			try {
			    lesProgrammations = (List<ProgrammationSemaine>) charger(sc.nextLine(),
				    (Class<Serializable>) lesProgrammations.getClass());
			    System.out.println("Fichier chargé");
			} catch (Exception e) {
			    System.out.println("Erreur " + e.getMessage());
			}
			break;
		    }
		}
		break;

	    case "d":
		System.out.println(salles);
		System.out.println(sallesTheatre);
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
