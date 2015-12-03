package gestion_spectacle;

public class Salle {
    private int capacite, nbPlacesStandard;

    private String nomSalle;

    private double tarif;

    public Salle(String nomSalle, int capacite, int nbPlacesStandard, double tarif) {
	super();
	this.nomSalle = nomSalle;
	this.capacite = capacite;
	this.nbPlacesStandard = nbPlacesStandard;
	this.tarif = tarif;
    }

    @Override
    public boolean equals(Object obj) {
	if (this == obj)
	    return true;
	if (obj == null)
	    return false;
	if (getClass() != obj.getClass())
	    return false;
	Salle other = (Salle) obj;
	if (nomSalle == null) {
	    if (other.nomSalle != null)
		return false;
	} else if (!nomSalle.equals(other.nomSalle))
	    return false;
	return true;
    }

    public int getCapacite() {
	return capacite;
    }

    public String getNomSalle() {
	return nomSalle;
    }

    public double getTarif() {
	// TODO Auto-generated method stub
	return tarif;
    }

    @Override
    public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + ((nomSalle == null) ? 0 : nomSalle.hashCode());
	return result;
    }

    public void setNomSalle(String nomSalle) {
	this.nomSalle = nomSalle;
    }

    @Override
    public String toString() {
	return "Salle [capacite=" + capacite + ", nbPlacesStandard=" + nbPlacesStandard + ", nomSalle=" + nomSalle
		+ ", tarif=" + tarif + "]";
    }

}
