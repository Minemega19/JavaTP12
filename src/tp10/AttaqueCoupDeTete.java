package tp10;

public class AttaqueCoupDeTete extends AttaquePhysique {
	public AttaqueCoupDeTete() {
		super("coup de tete", 70, 100, 15);
	}
	
	@Override
	public AttaqueCoupDeTete genAttaque() {
		return new AttaqueCoupDeTete();
	}

}
