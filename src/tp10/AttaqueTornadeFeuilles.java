package tp10;

public class AttaqueTornadeFeuilles extends AttaqueSpeciale {
	public AttaqueTornadeFeuilles() {
		super("tornade feuilles", new String[] {"PLANTE"}, 65, 90, 10);
	}
	
	@Override
	public AttaqueTornadeFeuilles genAttaque() {
		return new AttaqueTornadeFeuilles();
	}

}
