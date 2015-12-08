package gestion_spectacle.salle;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.Scanner;

public class EnsembleTheatre extends EnsembleSalle {

    static public EnsembleTheatre charger(String nomFichier) throws FileNotFoundException, IOException {
	ObjectInputStream ois = new ObjectInputStream(new FileInputStream(nomFichier));
	Object obj;
	try {
	    obj = ois.readObject();
	    if (obj.getClass() != EnsembleTheatre.class) {
		obj = null;
	    }
	} catch (ClassNotFoundException e) {
	    obj = null;
	}
	ois.close();
	return (EnsembleTheatre) obj;

    }

    public static EnsembleTheatre ensembleSalleTheatre() {
	Scanner sc = new Scanner(System.in);
	boolean loop = true;
	String in;
	EnsembleTheatre salles = new EnsembleTheatre();
	System.out.println("Création ensemble theatre");
	while (loop) {
	    System.out.println("(q)uitter (a)jouter");
	    in = sc.nextLine();
	    switch (in) {
	    case "q":
		loop = false;
		break;
	    case "a":
		salles.ajouter(SalleTheatre.salleTheatre());
		break;
	    }
	}
	return salles;
    }

    public EnsembleTheatre() {
    }

    @Override
    public void ajouter(Salle salle) {
	if (salle instanceof SalleTheatre)
	    super.ajouter(salle);
    }

}
