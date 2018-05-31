package tp10;

public class AttaqueEnfer extends AttaqueSpeciale {
	public AttaqueEnfer() {
		super("enfer", new String[] {"FEU"}, 100, 50, 5);
	}
	
	@Override
	public AttaqueEnfer genAttaque() {
		return new AttaqueEnfer();
	}

}
