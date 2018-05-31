package tp10;

import java.util.Random;

public class Outil extends Item implements ChangeItems {

	public Outil(String nom, int frequence, int utilisations) {
		super(nom, frequence, utilisations);
	}
	
	@Override
	public void utiliser(Modifiable item) {
		if (this.getUtilisationsRestantes() > 0) {
			item.modifier();
			this.baisserUtilisations(1);
		}
	}

	@Override
	public Item genAlea() {
		if (this.seraGenere()) {
			return new Outil(this.getNom(), this.getFrequence(), this.getUtilisations());
		}
		return null;
	}

}
