package tp11;

public class AttaqueMorsure extends AttaquePhysique {
	
	public AttaqueMorsure() {
		super("morsure", 60, 100, 25);
	}
	
	@Override
	public AttaqueMorsure genAttaque() {
		return new AttaqueMorsure();
	}

}
