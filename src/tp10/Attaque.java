package tp10;

public abstract class Attaque {
	private String nom;
	private String[] typesCompatibles;
	private int puissance;
	private int precision;
	private int repetitionsAttaque;
	private int repetitionsRestantes;
	
	// TP 6
	public static String[] tousTypes = {"PLANTE", "POISON", "FEU", "DRAGON", "VOL", "EAU", "INSECTE", "NORMAL", "FONCE", "ELECTRIQUE", "TELEPATIQUE", "GLACE", "ACIER", "TERRE", "FEE", "COMBAT", "ROCHE", "FANTOME"};

	public Attaque(String nom, String[] typesCompatibles, int puissance, int precision, int repetitionsAttaque) {
		this.nom = nom;
		this.typesCompatibles = typesCompatibles;
		this.puissance = puissance;
		this.precision = precision;
		this.repetitionsAttaque = repetitionsAttaque;
		this.repetitionsRestantes = repetitionsAttaque;
	}
	
	public Attaque(String nom, int puissance, int precision, int repetitionsAttaque) {
		this(nom, tousTypes, puissance, precision, repetitionsAttaque);
	}
	
	public abstract Attaque genAttaque();
	
	
	public String getNom() {
		return this.nom;
	}
	
	public String[] getTypesCompatibles() {
		return this.typesCompatibles;
	}
		
	public int getPuissance() {
		return this.puissance;
	}
	
	public int getPrecision() {
		return this.precision;
	}
	
	public int getRepetitionsAttaque() {
		return this.repetitionsAttaque;
	}
	
	public int getRepetitionsRestantes() {
		return this.repetitionsRestantes;
	}

	public void baisseRepetitions(){
		this.repetitionsRestantes = this.repetitionsRestantes-1;
		if (this.repetitionsRestantes < 0) {
			this.repetitionsRestantes = 0;
		}
	}
	public void resetRepetitions() {
		this.repetitionsRestantes = this.repetitionsAttaque;
	}
		
	public String toString() {
		String resultat = this.nom + " : " + this.puissance + ", " + this.precision + ", " + this.repetitionsAttaque + ", " + this.repetitionsRestantes + ", compatible avec ";
		if (this.typesCompatibles.equals(tousTypes)) {
			resultat += "tous types de Pokemon.";
		}
		else {
			for (int i = 0; i < this.typesCompatibles.length; i++) {
				resultat += "type " + this.typesCompatibles[i] + ", ";
			}
		}
		return resultat;
	}
	
	public abstract void utiliser(Pokemon attaquant, Pokemon victime);

	public abstract boolean estCompatible(Pokemon p);
	
}
