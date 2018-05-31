package tp10;

public class AttaqueCroquer extends AttaquePhysique {
	public AttaqueCroquer() {
		super("croquer", 80, 100, 15);
	}

	@Override
	public AttaqueCroquer genAttaque() {
		return new AttaqueCroquer();
	}

}
