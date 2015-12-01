package gestion_spectacle;

public class SeanceTheatre extends Seance {
    private int nbFauteuilsVendus;

    private SalleTheatre salleTheatre;

    @Override
    public boolean equals(Object obj) {
	if (this == obj)
	    return true;
	if (!super.equals(obj))
	    return false;
	if (getClass() != obj.getClass())
	    return false;
	SeanceTheatre other = (SeanceTheatre) obj;
	if (nbFauteuilsVendus != other.nbFauteuilsVendus)
	    return false;
	if (salleTheatre == null) {
	    if (other.salleTheatre != null)
		return false;
	} else if (!salleTheatre.equals(other.salleTheatre))
	    return false;
	return true;
    }

    @Override
    public int hashCode() {
	final int prime = 31;
	int result = super.hashCode();
	result = prime * result + nbFauteuilsVendus;
	result = prime * result + ((salleTheatre == null) ? 0 : salleTheatre.hashCode());
	return result;
    }

    public int nbFauteuilsDispo() {
	return 0;

    }

    @Override
    public int nbPlacesDispo() {
	// TODO Auto-generated method stub
	return 0;
    }

    @Override
    public double tauxRemplissage() {
	// TODO Auto-generated method stub
	return 0;
    }

    @Override
    public String toString() {
	return "SeanceTheatre [salleTheatre=" + salleTheatre + ", nbFauteuilsVendus=" + nbFauteuilsVendus + "]";
    }

    @Override
    public int totalVendu() {
	// TODO Auto-generated method stub
	return 0;
    }

    public void vendrePlacesFauteuil(int nbre) {

    }

}
