package gestion_spectacle;

public class PieceTheatre extends Spectacle {
private String metteurEnScene;
    private int nbEntractes;

    public PieceTheatre(String titre, String interpretes, String metteurEnScene, int nbEntractes) {
	super(titre, interpretes);
	this.metteurEnScene = metteurEnScene;
	this.nbEntractes = nbEntractes;
    }
}
