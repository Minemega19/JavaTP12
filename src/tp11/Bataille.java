package tp11;

import java.util.Random;
import java.util.Scanner;

public class Bataille {
	
	private Scanner lecteur;
	private Pokemon p1;
	private Pokemon p2;
	private boolean batailleFinie;
	
	public Bataille(Pokemon p1, Pokemon p2) {
		this.lecteur = new Scanner(System.in);
		this.p1 = p1;
		this.p2 = p2;
		this.batailleFinie = false;
	}
	
	private void choisirAction(Pokemon pokemonActif, Pokemon pokemonPassif) {
		if(null != pokemonActif.getMonJoueur()) {
			pokemonActif.regarderAttaques();
			System.out.println("Pour pokemon " + pokemonActif.getNom() + ", choisissez l'index de votre attaque contre le pokemon " + pokemonPassif.getNom());
			int reponseIndex = this.lecteur.nextInt();
			while (reponseIndex < 0 || reponseIndex > pokemonActif.getSesAttaques().size()) {
				System.out.println("Refaites votre choix.");
				reponseIndex = this.lecteur.nextInt();
			}
			pokemonActif.utiliseAttaque(reponseIndex, pokemonPassif);
			if (pokemonPassif.sEstEvanoui()) {
				this.batailleFinie = true;
				System.out.println("Le vainqueur est " + pokemonActif.getNom());
			
				Joueur maitre = pokemonActif.getMonJoueur();
				if (null != maitre) {
					if (null == pokemonPassif.getMonJoueur()) {
						System.out.println("Voulez-vous capturer ce pokemon ? Tapez <<oui>> ou <<non>>");
						String reponseText = this.lecteur.next();
						
						while(!reponseText.equals("oui") && !reponseText.equals("non")) {
							System.out.println("Re-faites votre choix. Il faut taper <<oui>> ou <<non>>.");
							reponseText = this.lecteur.next();
						}
						
						if (reponseText.equals("oui")) {
							maitre.capturer(pokemonPassif);
							if (null == pokemonPassif.getMonJoueur()) {
								// on n'a pas la place pour le prendre
								System.out.println("Meme si vous n'avez pas la place pour ce pokemon, vous pourriez faire de la place en liberant un de vos pokemons actuels " + maitre.afficherPokemons());
								System.out.println("Voulez-vous liberer un de vos pokemons ? Tapez <<oui>> ou <<non>>.");
								reponseText = this.lecteur.next();
								while(!reponseText.equals("oui") && !reponseText.equals("non")) {
									System.out.println("Re-faites votre choix. Il faut taper <<oui>> ou <<non>>.");
									reponseText = this.lecteur.next();
								}
								if (reponseText.equals("oui")) {
									System.out.println("Lequel de vos pokemons voulez-vous eliberer pour capturer " +pokemonPassif.getNom() + " ? Tapez un index.");
									reponseIndex = this.lecteur.nextInt();
									while (reponseIndex < 0 || reponseIndex > maitre.getPokemons().length) {
										System.out.println("Refaites votre choix.");
										reponseIndex = this.lecteur.nextInt();
									}
									maitre.liberer(maitre.getPokemons()[reponseIndex]);
									maitre.capturer(pokemonPassif);
								}
								
							}
						}
					}
				}
			}
		}
		else {
			Random alea = new Random();
			pokemonActif.utiliseAttaque(alea.nextInt(pokemonActif.getSesAttaques().size()), pokemonPassif);
			if (pokemonPassif.sEstEvanoui()) {
				this.batailleFinie = true;
				System.out.println("Le vainqueur est " + pokemonActif.getNom());
			}
		}
	}
	

	
	
	public void run() {
			
		while (!this.batailleFinie) {
			if (this.p1.sEstEvanoui() || this.p2.sEstEvanoui()) {
				this.batailleFinie = true;
			}
			if(!this.batailleFinie) {
				this.choisirAction(this.p1, this.p2);
			}
			
			if(!this.batailleFinie && !this.p2.sEstEvanoui()) {
				this.choisirAction(this.p2, this.p1);
			}
		}
		
		this.p1.resetAttaques();
		this.p2.resetAttaques();

	}
	
}
