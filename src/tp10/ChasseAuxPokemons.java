package tp10;
import java.util.Scanner;
import java.util.Map;
import java.io.IOException;
import java.util.HashMap;
import java.io.*;
import java.util.ArrayList;
import java.util.InputMismatchException;


public class ChasseAuxPokemons {

	public static void main(String[] args) {

		// TP 10
		final Map<String, Integer> mappePokemons = new HashMap<>();
		
		try(FileReader lecteur = new FileReader("C:/Users/crist/eclipse-workspace/TP10/src/tp10/pokedexComplet.txt")){
			Scanner s = new Scanner(lecteur);
			while (s.hasNext()) {
				int numeroPokedex = s.nextInt();
				String nom = s.next();
				mappePokemons.put(nom, numeroPokedex);
			}
		}
		catch(IOException e) {
			e.printStackTrace();
		}
		
		
		// nos attaques
		final Map<String, Attaque> mappeAttaques = new HashMap<>();
		mappeAttaques.put("tackle", new AttaqueTackle());
		mappeAttaques.put("morsure", new AttaqueMorsure());
		mappeAttaques.put("pistoleEau", new AttaquePistoleEau());
		mappeAttaques.put("enfer", new AttaqueEnfer());
		mappeAttaques.put("feinte", new AttaqueFeinte());
		mappeAttaques.put("tornadeFeuilles", new AttaqueTornadeFeuilles());
		mappeAttaques.put("bulle", new AttaqueBulle());
		mappeAttaques.put("coupDeTete", new AttaqueCoupDeTete());

		final ArrayList<Pokemon> pokemonList = new ArrayList<>();
		try(FileReader lecteur = new FileReader("C:/Users/crist/eclipse-workspace/TP9/src/tp9/InputFile.txt")){
			Scanner s = new Scanner(lecteur);
			while(s.hasNext()) {
				String nom = s.next();
				// TP 10
				
				int numeroPokedex = mappePokemons.get(nom);
				
				String type = s.next();
				int niveau = s.nextInt();
				boolean diurne = s.nextBoolean();
				int attaque = s.nextInt();
				int defense = s.nextInt();
				int attaqueSpeciale = s.nextInt();
				int defenseSpeciale = s.nextInt();
				ArrayList<Attaque> sesAttaques = new ArrayList<>();
				String nomAttaque = s.next();
				while(! nomAttaque.equals("END")) {
					sesAttaques.add(mappeAttaques.get(nomAttaque).genAttaque());
					nomAttaque = s.next();
				}
				Attaque[] sesAttaquesTableau = new Attaque[sesAttaques.size()];
				for(int i = 0; i<sesAttaques.size(); i++) {
					sesAttaquesTableau[i] = sesAttaques.get(i);
				}
				pokemonList.add(new Pokemon(numeroPokedex, nom, type, niveau, diurne, attaque, defense, attaqueSpeciale, defenseSpeciale, sesAttaquesTableau));
			}
		}
		catch (IOException e) {
			e.printStackTrace();
		}
				
		// nos pokemons
		//final Pokemon piplup = new Pokemon("Piplup", "EAU", 5, true, 51, 53, 61, 56, new Attaque[] {new AttaqueTackle(), new AttaqueMorsure(), new AttaquePistoleEau(), new AttaqueEnfer()});
		final Pokemon piplup = pokemonList.get(0);
		final Pokemon rowlet = pokemonList.get(1);
		final Pokemon totodile = pokemonList.get(2);
		//final Pokemon rowlet = new Pokemon("Rowlet", "PLANTE", 10, false, 55, 55, 50, 55, new Attaque[] {new AttaqueMorsure(), new AttaqueFeinte(), new AttaqueTornadeFeuilles()});
		//final Pokemon totodile = new Pokemon("Totodile", "EAU", 8, true, 65, 64, 44, 48, new Attaque[] {new AttaqueBulle(), new AttaqueCoupDeTete()});

		// nos joueurs
		final Joueur jeanDupont = new Joueur("Dupont", "Jean", 20, new Pokemon[5]);
		final Joueur gabrielleMartin = new Joueur("Martin", "Gabrielle", 18, new Pokemon[5]);
				
		System.out.println(piplup);
		// Jean capture un pokemon, il le nomme "Rascal" et le donne a Gabrielle
		jeanDupont.capturer(piplup);
		jeanDupont.nommer(piplup, "Rascal");
		jeanDupont.donner(piplup, gabrielleMartin); // resultat : Jean n'a pas de pokemon, Gabrielle en a un
		jeanDupont.capturer(rowlet);
		jeanDupont.nommer(rowlet, "Fuzzy");
		jeanDupont.echanger(rowlet, gabrielleMartin, piplup);


		// TP 3
		
		// la nourriture
		final Nourriture tartiflette = new Nourriture("tartiflette", 30, 35, new String[] {"DRAGON", "FEU", "COMBAT", "EAU", "ELECTRIQUE"});
		final Nourriture ratatouille = new Nourriture("ratatouille", 30, 10, new String[] {"PLANTE", "EAU", "VOL", "FEU", "NORMAL", "ELECTRIQUE", "COMBAT"}); 

		// TP 4
		
		final Gourmandise barreChocolatee = new Gourmandise("Barre Chocolatee", 10, 20, new String[] {"DRAGON", "FEU", "COMBAT", "EAU", "ELECTRIQUE"}, 5, 7);
		final PotionMagique mojito = new PotionMagique("mojito", 2);
		
		
		// TP 5
		jeanDupont.addNourriture(mojito);
		jeanDupont.addNourriture(barreChocolatee);
		jeanDupont.capturer(totodile);
		jeanDupont.nommer(totodile, "Schnappi");

		
		// TP 6
		// une bataille entre piplup et rowlet
		/*
		Scanner lecteur = new Scanner(System.in);
		System.out.println();
		
		final Pokemon p1 = piplup;
		final Pokemon p2 = rowlet;
		
		boolean batailleFinie = false;
	
		while (!batailleFinie) {
			if (p1.sEstEvanoui() || p2.sEstEvanoui()) {
				batailleFinie = true;
			}
			if(!batailleFinie) {
				p1.regarderAttaques();
				System.out.println("Pour pokemon" +p1.getNom() + ", choisissez l'index de votre attaque contre le pokemon " + p2.getNom());
				int reponseIndex = lecteur.nextInt();
				while (reponseIndex < 0 || reponseIndex > p1.getSesAttaques().length) {
					System.out.println("Refaites votre choix.");
					reponseIndex = lecteur.nextInt();
				}
				p1.utiliseAttaque(reponseIndex, p2);
				if (p2.sEstEvanoui()) {
					batailleFinie = true;
				}
			}
			
			if(!batailleFinie && !p2.sEstEvanoui()) {
				p2.regarderAttaques();
				System.out.println("Pour pokemon" +p2.getNom() + ", choisissez l'index votre attaque contre le pokemon " + p1.getNom());
				int reponseIndex = lecteur.nextInt();
				while (reponseIndex < 0 || reponseIndex > p2.getSesAttaques().length) {
					System.out.println("Refaites votre choix.");
					reponseIndex = lecteur.nextInt();
				}
				p2.utiliseAttaque(reponseIndex, p1);
				if (p1.sEstEvanoui()) {
					batailleFinie = true;
				}
			}
		}

		if (p1.sEstEvanoui()) {
			System.out.println("Le vainqueur est " + p2.getNom());
		}
		else {
			System.out.println("Le vainqueur est " + p1.getNom());
		}
		
		p1.resetAttaques();
		p2.resetAttaques();
		*/
		
		// TP 7
		final Jouet balle = new Jouet("balle", 20, 30, 10, 15, 5);
		final Outil marteau = new Outil("marteau", 10, 5);
		
		System.out.println(balle);
		System.out.println(marteau);
		
		jeanDupont.addItem(balle);
		System.out.println(jeanDupont.contenusSac());
		jeanDupont.addItem(marteau);
		System.out.println(jeanDupont.contenusSac());
		
		jeanDupont.donnerItem(0, 0);
		System.out.println(jeanDupont.contenusSac());
		jeanDupont.modifierItem(1, 0);
		System.out.println(jeanDupont.contenusSac());

		// TP 10
		
		try {
			jeanDupont.getPokedex().charger("C:/Users/crist/eclipse-workspace/TP10/src/tp10/jeanDupontPokedex.txt");
		}
		catch (FileNotFoundException e) {
			System.err.println("Je ne peux pas charger le pokedex du joueur " +jeanDupont.getPrenom() + " " +jeanDupont.getNom() + ". Ce fichier n'existe encore pas, mais il sera genere lors de votre prochaine session.");
		}
		catch (IOException e) {
			System.err.println("Je ne peux pas charger le pokedex du joueur " +jeanDupont.getPrenom() + " " +jeanDupont.getNom() + ". " + e.getMessage());
			e.printStackTrace();
			System.exit(1);
		}
		catch (InputMismatchException e) {
			System.err.println("Je ne peux pas charger le pokedex du joueur " +jeanDupont.getPrenom() + " " +jeanDupont.getNom() + ". Le fichier a un mauvais format. " + e.getMessage());
			e.printStackTrace();
			System.exit(1);
		}
		
		try {
			jeanDupont.getPokedex().sauvegarder("C:/Users/crist/eclipse-workspace/TP10/src/tp10/jeanDupontPokedex.txt");
		}
		catch (IOException e) {
			System.err.println("Je ne peux pas sauvegarder le pokedex du joueur " + jeanDupont.getPrenom() + " " +jeanDupont.getNom());
			e.printStackTrace();
		}
		
	}
	

}
