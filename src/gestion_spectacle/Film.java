package gestion_spectacle;

public class Film extends Spectacle {
    private String realisateur;
    private Heure duree;

    public Film(String titre, String interpretes, String realisateur, Heure duree) {
	super(titre, interpretes);
	this.realisateur = realisateur;
	this.duree = duree;
    }
}
