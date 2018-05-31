package tp10;

public class AttaqueFeinte extends AttaquePhysique {
	public AttaqueFeinte() {
		super("feinte", 30, 100, 10);
	}
	
	@Override
	public AttaqueFeinte genAttaque() {
		return new AttaqueFeinte();
	}

}
