package gestion_spectacle.salle;

import java.util.Scanner;

public class EnsembleTheatre extends EnsembleSalle {
    /**
     * cr�e un ensemble de salle de th��tre par saisie interactive
     * 
     * @return ensemble de salle de th��tre
     */
    public static EnsembleTheatre ensembleSalleTheatre() {
	Scanner sc = new Scanner(System.in);
	boolean loop = true;
	String in;
	EnsembleTheatre salles = new EnsembleTheatre();
	System.out.println("Cr�ation ensemble theatre");
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
	super();
    }

    @Override
    public void ajouter(Salle salle) {
	if (salle instanceof SalleTheatre)
	    super.ajouter(salle);
    }

}
