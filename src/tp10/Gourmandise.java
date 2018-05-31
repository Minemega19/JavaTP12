package tp10;

public class Gourmandise extends Nourriture {

	private int apportSatisfaction;
	private int apportLoyaute;
	
	public Gourmandise(String nom, int frequence, int apport, String[] compatibilites, int apportSatisfaction, int apportLoyaute) {
		super(nom, frequence, apport, compatibilites);
		this.apportSatisfaction = apportSatisfaction;
		this.apportLoyaute = apportLoyaute;
	}

	@Override
	public void utiliser(Joueur j, int indexPokemon) {
		if (indexPokemon >= j.getPokemons().length) {
			System.out.println("Index trop gros !");
		}
		else {
			Pokemon mangeur = j.getPokemons()[indexPokemon];
			if (null != mangeur) {
				super.utiliser(j, indexPokemon);
				mangeur.monteSatisfaction(this.apportSatisfaction);
				mangeur.monteLoyaute(this.apportLoyaute);
				
			}
			else {
				System.out.println("Pas de pokemon a cet index !");
			}
		}
	}

	
	// TP 4
	
	@Override
	public Item genAlea() {
		if (this.seraGenere()){
			return new Gourmandise(this.getNom(), this.getFrequence(), this.getApport(), this.getCompatibilites(), this.apportSatisfaction, this.apportLoyaute);
		}
		return null;
	}

	
	@Override
	public String toString() {
		String compatibilites = this.getCompatibilites()[0];
		for (int i = 1; i < this.getCompatibilites().length; ++i) {
			compatibilites = compatibilites + ", " + this.getCompatibilites()[i];
		}

		return (this.getNom() + " : " + this.getFrequence() + ", " 
				+ this.getApport() + ", " + this.apportSatisfaction
				+ ", " + this.apportLoyaute + ", "
				+ this.getUtilisationsRestantes() + "/" + this.getUtilisations() 
				+ ", " + compatibilites );
		}	


}
