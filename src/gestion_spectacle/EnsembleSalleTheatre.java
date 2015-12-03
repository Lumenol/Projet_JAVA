package gestion_spectacle;

public class EnsembleSalleTheatre extends EnsembleSalle {

    public EnsembleSalleTheatre() {
    }

    @Override
    public void ajouter(Salle salle) {
	if (salle instanceof SalleTheatre)
	    super.ajouter(salle);
    }

}
