package tp10;

public abstract class Item{
	private String nom;
	private int frequence;
	private int utilisations;
	private int utilisationsRestantes;
	
	public Item (String nom, int frequence, int utilisations) {
		this.nom = nom;
		this.frequence = frequence;
		this.utilisations = utilisations;
		this.utilisationsRestantes = utilisations;
	}
	
	public String getNom() {
		return this.nom;
	}
	
	public void setNom(String nom) {
		this.nom = nom;
	}
	
	public int getFrequence() {
		return this.frequence;
	}
	
	public int getUtilisations() {
		return this.utilisations;
	}
	
	public int getUtilisationsRestantes() {
		return this.utilisationsRestantes;
	}
	
	public void baisserUtilisations(int difference) {
		this.utilisationsRestantes -= difference;
	}
	
	public void monterUtilisations(int difference) {
		this.utilisationsRestantes += difference;
	}
	
	public void resetUtilisations() {
		this.utilisationsRestantes = this.utilisations;
	}
	
	public boolean seraGenere() {
		return (100 * Math.random() < this.frequence);
	}
	
	public abstract Item genAlea();	
	

	public String toString() {
		return this.nom + " : " + this.frequence + ", " + this.utilisationsRestantes + "/" + this.utilisations;
	}

}
