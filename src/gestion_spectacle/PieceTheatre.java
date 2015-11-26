package gestion_spectacle;

public class PieceTheatre extends Spectacle {
    private String metteurEnScene;

    private int nbEntractes;

    public PieceTheatre(String titre, String interpretes, String metteurEnScene, int nbEntractes) {
	super(titre, interpretes);
	this.metteurEnScene = metteurEnScene;
	this.nbEntractes = nbEntractes;
    }

    @Override
    public boolean equals(Object obj) {
	if (this == obj)
	    return true;
	if (!super.equals(obj))
	    return false;
	if (getClass() != obj.getClass())
	    return false;
	PieceTheatre other = (PieceTheatre) obj;
	if (metteurEnScene == null) {
	    if (other.metteurEnScene != null)
		return false;
	} else if (!metteurEnScene.equals(other.metteurEnScene))
	    return false;
	if (nbEntractes != other.nbEntractes)
	    return false;
	return true;
    }

    @Override
    public int hashCode() {
	final int prime = 31;
	int result = super.hashCode();
	result = prime * result + ((metteurEnScene == null) ? 0 : metteurEnScene.hashCode());
	result = prime * result + nbEntractes;
	return result;
    }

    @Override
    public String toString() {
	return "PieceTheatre [metteurEnScene=" + metteurEnScene + ", nbEntractes=" + nbEntractes + "]";
    }
}
