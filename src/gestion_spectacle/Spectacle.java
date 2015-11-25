package gestion_spectacle;

public abstract class Spectacle {
    private String titre, interpretes;

    public Spectacle(String titre, String interpretes) {
	super();
	this.titre = titre;
	this.interpretes = interpretes;
    }

    public String getTitre() {
        return titre;
    }

    public String getInterpretes() {
        return interpretes;
    }

}
