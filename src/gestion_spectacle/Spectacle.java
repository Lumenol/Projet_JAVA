package gestion_spectacle;

public abstract class Spectacle {
    private String titre, interpretes;

    public Spectacle(String titre, String interpretes) {
	super();
	this.titre = titre;
	this.interpretes = interpretes;
    }

    @Override
    public boolean equals(Object obj) {
	if (this == obj)
	    return true;
	if (obj == null)
	    return false;
	if (getClass() != obj.getClass())
	    return false;
	Spectacle other = (Spectacle) obj;
	if (interpretes == null) {
	    if (other.interpretes != null)
		return false;
	} else if (!interpretes.equals(other.interpretes))
	    return false;
	if (titre == null) {
	    if (other.titre != null)
		return false;
	} else if (!titre.equals(other.titre))
	    return false;
	return true;
    }

    public String getInterpretes() {
	return interpretes;
    }

    public String getTitre() {
	return titre;
    }

    @Override
    public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + ((interpretes == null) ? 0 : interpretes.hashCode());
	result = prime * result + ((titre == null) ? 0 : titre.hashCode());
	return result;
    }

    @Override
    public String toString() {
	return "Spectacle [titre=" + titre + ", interpretes=" + interpretes + "]";
    }

}
