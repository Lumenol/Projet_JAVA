package gestion_spectacle;

public class EnsembleTheatre extends EnsembleSalle {

    public EnsembleTheatre() {
    }

    @Override
    public void ajouter(Salle salle) {
	if (salle instanceof SalleTheatre)
	    super.ajouter(salle);
    }

}
