package gestion_spectacle.spectacle;

import java.util.Scanner;
import java.util.StringTokenizer;

public class PieceTheatre extends Spectacle {
    public static PieceTheatre pieceTheatre() {
	String[] spectacle = Spectacle.spectacle();
	Scanner sc = new Scanner(System.in);
	String metteurEnScene = "";
	int nbEntractes = -1;
	StringTokenizer toka;
	do {
	    System.out.println("metteurEnScene");
	    if (sc.hasNextLine())
		metteurEnScene = sc.nextLine().trim();

	} while (metteurEnScene.equals(""));
	do {
	    System.out.println("nbEntractes");
	    toka = new StringTokenizer(sc.nextLine());
	    if (toka.hasMoreTokens()) {
		try {
		    nbEntractes = Integer.valueOf(toka.nextToken());
		} catch (NumberFormatException e) {
		}
	    }
	} while (nbEntractes < 0);
	return new PieceTheatre(spectacle[0], spectacle[1], metteurEnScene, nbEntractes);
    }

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
	return super.toString() + " PieceTheatre [metteurEnScene=" + metteurEnScene + ", nbEntractes=" + nbEntractes
		+ "]";
    }
}
