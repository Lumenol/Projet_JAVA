package gestion_spectacle;

public class SeanceTheatre extends Seance {
    private int nbFauteuilsVendus;

    private SalleTheatre salleTheatre;

    public SeanceTheatre(Heure horaire, int jour, SalleTheatre salleTheatre) {
	super(horaire, jour);
	this.salleTheatre = salleTheatre;
	nbFauteuilsVendus = 0;
    }

    @Override
    public double chiffreAffaire() {
	// TODO Auto-generated method stub
	return getNbPlacesVenduesTN() * salleTheatre.getTarif() + nbFauteuilsVendus * salleTheatre.getPrixFauteuil();
    }

    public int nbFauteuilsDispo() {
	return salleTheatre.getNbFauteuils() - nbFauteuilsVendus;

    }

    @Override
    public int nbPlacesDispo() {
	// TODO Auto-generated method stub
	return salleTheatre.getCapacite() - nbFauteuilsVendus - getNbPlacesVenduesTN();
    }

    @Override
    public double tauxRemplissage() {
	// TODO Auto-generated method stub
	return totalVendu() / salleTheatre.getCapacite();
    }

    @Override
    public String toString() {
	return "SeanceTheatre [salleTheatre=" + salleTheatre + ", nbFauteuilsVendus=" + nbFauteuilsVendus + "]";
    }

    @Override
    public int totalVendu() {
	// TODO Auto-generated method stub
	return getNbPlacesVenduesTN() + nbFauteuilsVendus;
    }

    public void vendrePlacesFauteuil(int nbre) throws IllegalArgumentException {
	if (nbFauteuilsDispo() < nbre) {
	    throw new IllegalArgumentException("Nombre de fauteuil insuffisant");
	}
	nbFauteuilsVendus += nbre;
    }

    @Override
    public void vendrePlacesTN(int nbre) throws IllegalArgumentException {
	if (nbPlacesDispo() < nbre) {
	    throw new IllegalArgumentException("Nombre de place insuffisant");
	}
	super.vendrePlacesTN(nbre);
    }

}
