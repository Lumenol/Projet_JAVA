package gestion_spectacle;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class GestionSpectacle {

    public static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
	List<ProgrammationSemaine> lesProgrammations;// = new
						     // ArrayList<ProgrammationSemaine>();
	EnsembleSalle salles = null;
	EnsembleTheatre sallesTheatre = null;

	// salles = EnsembleSalle.ensembleSalle();
	// sallesTheatre = EnsembleTheatre.ensembleSalleTheatre();

	try {
	    salles = EnsembleSalle.charger("salles"); //
	    sallesTheatre = EnsembleTheatre.charger("salleTheatre");
	} catch (IOException e) {
	    e.printStackTrace();
	}

	// try {
	// salles.sauvegarder("salles");
	// sallesTheatre.sauvegarder("salleTheatre");
	// } catch (IOException e) {
	// e.printStackTrace();
	// }

	System.out.println(salles);
	System.out.println(sallesTheatre);
	boolean loop = true;

	while (loop) {
	    System.out.println("q pour quitter");
	    switch (sc.nextLine()) {
	    case "q":
		loop = false;
		break;

	    case "c":

		break;

	    case "m":

		break;

	    case "v":

		break;
	    default:
		break;
	    }
	}
    }

}
