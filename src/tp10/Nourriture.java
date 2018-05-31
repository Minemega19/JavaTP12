package tp10;

public class Nourriture extends Item implements Utilisable {
	private int apport;
	private String[] compatibilites;
	
	public Nourriture(String nom, int frequence, int apport, String[] compatibilites) {
		super(nom, frequence, 1);
		this.apport = apport;
		this.compatibilites = compatibilites;
	}
	
	public int getApport() {
		return apport;
	}


	public String[] getCompatibilites() {
		return compatibilites;
	}

	public boolean estCompatible(String type) {
		for (int i = 0; i < this.compatibilites.length; i++) {
			if (type.equals(this.compatibilites[i]))
				return true;
		}

		return false;
	}
	

	@Override
	public Item genAlea() {
		if (this.seraGenere())
			return new Nourriture(this.getNom(), this.getFrequence(), this.apport, this.compatibilites);
		
		return null;
	}

	@Override
	public void utiliser(Joueur j, int indexPokemon) {
		if (indexPokemon >= j.getPokemons().length) {
			System.out.println("Index non-valide");
		}
		else {
			Pokemon mangeur = j.getPokemons()[indexPokemon];
			if (null != mangeur) {
				if (this.estCompatible(mangeur.getType())) {
					mangeur.baisseAppetit(this.apport);
					this.baisserUtilisations(1);
				}
				else {
					System.out.println("Votre pokemon ne pourra pas manger cette nourriture, qui n'est pas compatible avec son type.");
				}
			}
			else {
				System.out.println("Pas de pokemon a cette position !");
			}
		}
		
		
	}
	
	@Override
	public String toString() {
		String compatibilites = this.compatibilites[0];
		for (int i = 1; i < this.compatibilites.length; ++i) {
			compatibilites = compatibilites + ", " + this.compatibilites[i];
		}

		return (this.getNom()
			+ " : " + this.getFrequence() + ", " + this.apport
			+ this.getUtilisationsRestantes() + "/" + this.getUtilisations() 
			+ ", " + compatibilites );
	}

}
