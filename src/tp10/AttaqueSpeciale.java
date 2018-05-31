package tp10;

public class AttaqueSpeciale extends Attaque {

	public AttaqueSpeciale(String nom, String[] typesCompatibles, int puissance, int precision, int repetitionsAttaque) {
		super(nom, typesCompatibles, puissance, precision, repetitionsAttaque);
	}

	@Override
	public AttaqueSpeciale genAttaque() {
		return new AttaqueSpeciale(this.getNom(), this.getTypesCompatibles(), this.getPuissance(), this.getPrecision(), this.getRepetitionsAttaque());
	}

	
	public boolean estCompatible(Pokemon p) {
		for(int i = 0; i < this.getTypesCompatibles().length; i++) {
			if (p.getType().equals(this.getTypesCompatibles()[i])) {
				return true;
			}
		}
		return false;
	}
	
	@Override
	public void utiliser(Pokemon attaquant, Pokemon victime) {
		
		// Dans la classe Pokemon, on verifie deja la compatibilite entre le type de l'attaque et le type du pokemon
		
		// Si la victime est compatible, alors il faut prendre en compte son score defenseSpeciale; sinon il faut considerer juste la defense
		int scoreDefense = victime.getDefenseSpeciale();
		if (! this.estCompatible(victime)) {
			scoreDefense = victime.getDefense();
		}
		
		// On verifie si le score d'attaque de l'attaquant est superieur a la defence de la victime
		// puis on vérifie si la precision indique un succes
		
		if(this.getRepetitionsRestantes() > 0 && attaquant.getAttaqueSpeciale() > scoreDefense && Math.random()*100 < this.getPrecision()) {
			victime.estBlesse(this.getPuissance()/4);
			this.baisseRepetitions();
			System.out.println("L'attaque a eu succes, il cause un domage de " + this.getPuissance()/4);
		}
		else {
			if (this.getRepetitionsRestantes()<=0) {
				System.out.println("Pas assez de répétitions.");
			}
			else {
				System.out.println("Votre attaque n'a pas eu de succes.");
				this.baisseRepetitions();
			}
		}
	}


}
