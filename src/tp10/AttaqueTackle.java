package tp10;

public class AttaqueTackle extends AttaquePhysique {
	public AttaqueTackle() {
		super("tackle", 40, 100, 35);
	}
	
	@Override
	public AttaqueTackle genAttaque() {
		return new AttaqueTackle();
	}

}
