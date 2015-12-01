package gestion_spectacle;

public class ProgrammationSemaine {
	private int semaine;
	
	public ProgrammationSemaine(int semaine) throws IllegalArgumentException{
		if(semaine<1 || semaine >52){
			throw new IllegalArgumentException("numero de semaine incorrecte");
		}
		this.semaine = semaine;
			
		}
	}
