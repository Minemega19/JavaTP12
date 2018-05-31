package tp10;

public class Jouet extends Item implements Utilisable, Modifiable {

	private int apportAppetit;
	private int apportSatisfaction;
	private int apportLoyaute;
	
	public Jouet(String nom, int frequence, int utilisations, int apportAppetit,
			int apportSatisfaction, int apportLoyaute) {
		super(nom, frequence, utilisations);
		this.apportAppetit = apportAppetit;
		this.apportSatisfaction = apportSatisfaction;
		this.apportLoyaute = apportLoyaute;
	}
	
	@Override
	public void modifier() {
		this.setNom(this.getNom() + " magique");
		this.apportAppetit += 20;
		this.apportSatisfaction += 10;
		this.apportLoyaute += 15;
		this.resetUtilisations();
	}

	@Override
	public void utiliser(Joueur j, int indexPokemon) {
		if (indexPokemon > j.getPokemons().length) {
			System.out.println("Index trop gros !");
		}
		else {
			if(null != j.getPokemons()[indexPokemon] && this.getUtilisationsRestantes() > 0) {
				Pokemon p = j.getPokemons()[indexPokemon];
				p.monteAppetit(this.apportAppetit);
				p.monteSatisfaction(this.apportSatisfaction);
				p.monteLoyaute(this.apportLoyaute);
				this.baisserUtilisations(1);
			}
			else{
				System.out.println("Vous ne pouvez pas utiliser cet objet sur ce pokemon !");
			}
		}
	}

	@Override
	public Item genAlea() {
		if (this.seraGenere())
			return new Jouet(this.getNom(), this.getFrequence(), this.apportAppetit, 
			this.apportSatisfaction, this.apportLoyaute, this.getUtilisations());
		
		return null;
	}

	public String toString() {
		return (this.getNom() + " : " + this.getFrequence() + ", " + this.apportAppetit + ", " + this.apportSatisfaction + ", " + this.apportLoyaute + ", " + this.getUtilisationsRestantes() + "/" + this.getUtilisations());
	}
}
