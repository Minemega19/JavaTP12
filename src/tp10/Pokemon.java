package tp10;

import java.util.ArrayList;

public class Pokemon {
	// TP 10
	private int numeroPokedex;
	
	// les attributs du TP1
	private String nom;
	private String type;
	private int niveau;
	private boolean diurne;

	// attributs ajoutes lors du TP2
	private String nomDonne;
	private Joueur monJoueur;
	
	// TP3
	private int appetit;
	private int satisfaction;
	private int loyaute;

	// TP6
	
	private int attaque;
	private int defense;
	private int attaqueSpeciale;
	private int defenseSpeciale;
	private int hp;

	// change pour TP 9
	// private Attaque[] sesAttaques;
	private ArrayList<Attaque> sesAttaques = new ArrayList<>();

	// TP 6 mise a jour
	public Pokemon(int numeroPokedex, String nom, String type, int niveau, boolean diurne, 
			int attaque, int defense, int attaqueSpeciale, int defenseSpeciale, 
			Attaque[] sesAttaques) {
		this(numeroPokedex, nom, type, niveau, diurne, null, null, attaque, defense, attaqueSpeciale, defenseSpeciale, sesAttaques);
		
	}

	// TP2
	// TP 6 mise a jour
	// TP 10 mise a jour
	public Pokemon(int numeroPokedex, String nom, String type, int niveau, boolean diurne, 
			String nomDonne, Joueur monJoueur, int attaque, int defense, 
			int attaqueSpeciale, int defenseSpeciale, Attaque[] sesAttaques) {

		// TP 10
		this.numeroPokedex = numeroPokedex;
		
		this.nom = nom;
		this.type = type;
		this.niveau = niveau;
		this.diurne = diurne;

		// TP 2
		this.nomDonne = nomDonne;
		this.monJoueur = monJoueur;

		// TP 3
		this.appetit = 50;
		this.satisfaction = 50;
		this.loyaute = 0;
		
		// TP 6
		
		this.attaque = attaque;
		this.defense = defense;
		this.attaqueSpeciale = attaqueSpeciale;
		this.defenseSpeciale = defenseSpeciale;
		this.hp = 30;
		int length = 4;
		if (sesAttaques.length > 4) {
			System.out.println("Vous avez mis une liste trop longue d'attaques; je ne prendrai en compte que les premiers 4."); 
		}
		else {
			length = sesAttaques.length;
		}
		
		for (int i = 0; i < length; i++) {
			this.sesAttaques.add(sesAttaques[i]);
		}
	}
	
	public int getNumeroPokedex() {
		return this.numeroPokedex;
	}
	
	public String getNom() {
		return this.nom;
	}

	public String getType() {
		return this.type;
	}

	public int getNiveau() {
		return this.niveau;
	}

	public String getNomDonne() {
		return this.nomDonne;
	}

	public Joueur getMonJoueur() {
		return this.monJoueur;
	}
	
	// TP6
	public int getAttaque() {
		return this.attaque;
	}
	
	public int getDefense() {
		return this.defense;
	}
	
	public int getAttaqueSpeciale() {
		return this.attaqueSpeciale;
	}
	
	public int getDefenseSpeciale() {
		return this.defenseSpeciale;
	}
	
	public ArrayList<Attaque> getSesAttaques() {
		return this.sesAttaques;
	}

	// TP 4
	public int getAppetit() {
		return this.appetit;
	}
		
	public int getSatisfaction() {
		return this.satisfaction;
	}
		
	public int getLoyaute() {
		return this.loyaute;
	}
		
	private int getIndex() {
		if (null != this.monJoueur) {
			int i = 0;
			while (i < this.monJoueur.getPokemons().length) {
				if (this.equals(this.monJoueur.getPokemons()[i])) {
					return i;
				}
				i++;
			}
		}	
		return -1;
	}
		
	public void setNomDonne(String nomDonne) {
		// TP3 
		// premiere fois qu'on nous donne un nom, la loyaute monte par 10
		// chaque fois après, ça baisse par 10
		if (this.nomDonne == null) {
			this.monteLoyaute(10);
		}
		else {
			this.baisseLoyaute(10);
		}
				
		
		// TP 2
		this.nomDonne = nomDonne;
	}

	public void setMonJoueur(Joueur joueur) {
		this.monJoueur = joueur;
	}
	
	// TP6 
	
	// methode commente en TP 9
	/*
	public void addAttaque(Attaque a) {
		boolean ajoute = false;
		boolean incompatibiliteTrouvee = false;
		int i = 0;
		
		if (a.estCompatible(this)) {
			while(!ajoute && i < this.sesAttaques.length) {
				if (null == sesAttaques[i]) {
					sesAttaques[i] = a;
					ajoute = true;
				}
				i++;
			}
		}
		else {
			System.out.println("Votre pokemon n'est pas compatible avec cette attaque");
			incompatibiliteTrouvee = true;
		}
		
		if (!ajoute && !incompatibiliteTrouvee) {
			System.out.println("Votre pokemon a deja le nombre maximale d'attaques possible. Enlevez une attaque et ressayez.");
		}
	}
	*/
	
	
	public void addAttaque(Attaque a) {
	
		if (a.estCompatible(this)) {
			if(this.sesAttaques.size() == 4) {
				System.out.println("Votre pokemon a deja le nombre maximale d'attaques possible. Enlevez une attaque et ressayez.");
			}
			else {
				this.sesAttaques.add(a);
			}
		}
		else {
			System.out.println("Votre pokemon n'est pas compatible avec cette attaque");
		}
		
	}
	
	
	public void removeAttaque(int index) {
		if (index >= 0 && index <this.sesAttaques.size()) {
			this.sesAttaques.remove(index);
		}
	}
	
	
	public void regarderAttaques() {
		for (int i = 0; i < this.sesAttaques.size(); i++) {
			System.out.println(i + " : " + this.sesAttaques.get(i).getNom() + ", repetitions restantes " + this.sesAttaques.get(i).getRepetitionsRestantes());
		}
	}
	
	public void resetAttaques() {
		for (int i = 0; i < this.sesAttaques.size(); i++) {
			this.sesAttaques.get(i).resetRepetitions();
		}
	}
	
	public boolean sEstEvanoui() {
		return (this.hp == 0);
	}
	
	public void estBlesse(int domage) {
		this.hp = this.hp - domage;
		if(this.hp < 0) {
			this.hp = 0;
		}
	}
	
	public void utiliseAttaque(int index, Pokemon victime) {
		
		// si le pokemon s'est evanoui, il ne peut plus utiliser les attaques
		if (this.hp != 0) {
			if(index >= 0 && index <this.sesAttaques.size()) {
				this.sesAttaques.get(index).utiliser(this, victime);
			}
		}
	}
	
	public void direBonjour(String periodeJournee) {
		String salut; 
		
		if (periodeJournee.equals("jour")){
			salut = "bonjour";
		}
		else {
			salut = "bonsoir";
		}
		
		if ((periodeJournee.equals("jour") && this.diurne) || (periodeJournee.equals("nuit") && !this.diurne)) {
			System.out.println(this.nom + " dit " +salut + " !");
		} 
		else {
			System.out.println(nom + " dit zzzzzzzzzz !");
		}
	}

	// TP 3 mise a jour
	// TP 6 mise a jour
	
	public String toString() {
		String resultat = "";
		
		if (monJoueur != null) {
			if (this.nomDonne != null){
				resultat += (this.nomDonne + " est un pokemon du genre " + this.nom + ", du type " + this.type + ", qui a le niveau " + this.niveau + ". Ce pokemon appartient a " + monJoueur.getNom() + " " + monJoueur.getPrenom() + ". Il/Elle a un appetit de " + this.appetit + ", un niveau de satisfaction de " + this.satisfaction + " et une loyaute aupres son maitre de " + this.loyaute + ".");

			}
			else {
				resultat += ("Voici un pokemon du genre " + this.nom + ", du type " + this.type + ", qui a le niveau " + this.niveau + ". Ce pokemon appartient a " + this.monJoueur.getNom() + " " + this.monJoueur.getPrenom() + ". Il/Elle a un appetit de " + this.appetit + ", un niveau de satisfaction de " + this.satisfaction + " et une loyaute aupres son maitre de " + this.loyaute + ".");
			}
			
		} else {
			resultat += "Voici un pokemon du genre " + this.nom + ", du type " + this.type + ", qui a le niveau " + this.niveau + ". Ce pokemon n'a pas encore de maitre. Il/Elle a un appetit de " + this.appetit + " une satisfaction de " + this.satisfaction + " et une loyaute de " + this.loyaute + ".";
		}
		
		resultat += " Ce pokemon a une attaque de " + this.attaque + ", une defense de " + this.defense + ", une attaque speciale de " + this.attaqueSpeciale + ", une defense speciale " + this.defenseSpeciale + " et les attaques suivantes : " ;
		
		for (int i = 0; i < this.sesAttaques.size(); i++) {
			resultat += this.sesAttaques.get(i).getNom() + ", ";
		}
		
		return resultat;
	}
	
	
	// TP 3: lorsqu'on est capture, la loyaute baisse a 0
	// satisfaction baisse a 10
	// appetit baisse a 10
	public void estCapture() {
		this.loyaute = 0;
		if (this.satisfaction > 10) {
			this.satisfaction = 10;
		}
		if (this.appetit > 10) {
			this.appetit = 10;
		}
	}
	
	// TP 3 : liberer un pokemon veut dire que la satisfaction
	// baisse par la difference entre sa loyaute actuelle et 10
	public void estLibere() {
		if (this.loyaute > 10) {
			this.baisseSatisfaction(this.loyaute - 10);
		}
		this.loyaute = 0;
	}
	
	public void baisseAppetit(int difference) {
		this.appetit = this.appetit - difference;
		if (this.appetit < 0) {
			this.appetit = 0;
		}
	}
	
	public void monteAppetit(int difference) {
		this.appetit = this.appetit + difference;
		if (this.appetit > 100) {
			this.appetit = 100;
		}
	}
	
	public void baisseSatisfaction(int difference) {
		this.satisfaction = this.satisfaction - difference;
		if (this.satisfaction < 0) {
			this.satisfaction = 0;
		}
	}
	
	public void monteSatisfaction(int difference) {
		this.satisfaction = this.satisfaction + difference;
		if (this.satisfaction > 100) {
			this.satisfaction = 100;
		}
	}

	public void baisseLoyaute(int difference) {
		this.loyaute = this.loyaute - difference;
		if (this.loyaute < 0) {
			this.loyaute = 0;
		}
	}
	
	public void monteLoyaute(int difference) {
		this.loyaute = this.loyaute + difference;
		if (this.loyaute > 100) {
			this.loyaute = 100;
		}
	}

	
	
	public void utiliser(Utilisable item) {
		int index = this.getIndex();
		if(index != -1) {
			item.utiliser(this.monJoueur, index);
		}
	}
	
	public void miseANiveau() {
		this.niveau = this.niveau + 1;
	}


}
