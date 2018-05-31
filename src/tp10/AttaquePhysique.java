package tp10;

public class AttaquePhysique extends Attaque {

	public AttaquePhysique(String nom, int puissance, int precision, int repetitionsAttaque) {
		super(nom, puissance, precision, repetitionsAttaque);
	}
	
	public boolean estCompatible(Pokemon p) {
		return true;
	}

	@Override
	public AttaquePhysique genAttaque() {
		return new AttaquePhysique(this.getNom(), this.getPuissance(), this.getPrecision(), this.getRepetitionsAttaque());
	}
	
	@Override
	public void utiliser(Pokemon attaquant, Pokemon victime) {
		// premierement on verifie si le score d'attaque de l'attaquant est superieur a la defence de la victime
		// puis on vérifie si la precision indique un succes
		
		if(this.getRepetitionsRestantes()>0 && attaquant.getAttaque() > victime.getDefense() && Math.random()*100 < this.getPrecision()) {
			victime.estBlesse(this.getPuissance()/4);
			this.baisseRepetitions();
			System.out.println("L'attaque a eu succes, il cause un domage de " + this.getPuissance()/4);
		}
		else {
			if (this.getRepetitionsRestantes() <= 0) {
				System.out.println("Pas assez de répétitions.");
			}
			else {
				System.out.println("Votre attaque n'a pas eu de succes.");
				this.baisseRepetitions();
			}
		}
		
	}

}
