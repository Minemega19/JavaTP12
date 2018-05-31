package tp10;

public class AttaquePistoleEau extends AttaqueSpeciale {
	public AttaquePistoleEau() {
		super("pistole d'eau", new String[] {"EAU"}, 40, 100, 25);
	}
	
	@Override
	public AttaquePistoleEau genAttaque() {
		return new AttaquePistoleEau();
	}

}
