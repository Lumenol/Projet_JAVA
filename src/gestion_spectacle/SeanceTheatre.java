package gestion_spectacle;

public class SeanceTheatre extends Seance {
    private SalleTheatre salleTheatre;
    private int nbFauteuilsVendus;

    @Override
    public int nbPlacesDispo() {
	// TODO Auto-generated method stub
	return 0;
    }

    @Override
    public int totalCendu() {
	// TODO Auto-generated method stub
	return 0;
    }

    @Override
    public double tauxRemplissage() {
	// TODO Auto-generated method stub
	return 0;
    }

    public int nbFauteuilsDispo() {
	return nbFauteuilsVendus;

    }

    public void vendrePlacesFauteuil(int nbre) {

    }

}
