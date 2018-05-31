package tp10;

public class AttaqueBulle extends AttaqueSpeciale {
	public AttaqueBulle() {
		super("bulle", new String[] {"EAU"}, 40, 100, 30);
	}
	
	@Override
	public AttaqueBulle genAttaque() {
		return new AttaqueBulle();
	}

}
