package tp10;

public class PotionMagique extends Nourriture {
	
	public PotionMagique(String nom, int frequence) {
		super(nom, frequence, 0, new String[]{"PLANTE", "POISON", "FEU", "DRAGON", "VOL", "EAU", "INSECTE", "NORMAL", "FONCE", "ELECTRIQUE", "TELEPATIQUE", "GLACE", "ACIER", "TERRE", "FEE", "COMBAT", "ROCHE", "FANTOME"});
	}
	
	@Override
	public void utiliser(Joueur j, int indexPokemon) {
		super.utiliser(j, indexPokemon);
		if (indexPokemon >= j.getPokemons().length ) {
			System.out.println("Index trop gros !");
		}
		else {
			if (null != j.getPokemons()[indexPokemon]) {
				j.getPokemons()[indexPokemon].miseANiveau();
			}
			else {
				System.out.println("Pas de pokemon à cette position !");
			}
		}

	}
	
	@Override
	public Item genAlea() {
		if (this.seraGenere()){
			return new PotionMagique(this.getNom(), this.getFrequence());
		}
		return null;
	}

}
