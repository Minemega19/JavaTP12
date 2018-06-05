package tp11;
import java.util.Scanner;

import java.util.Map;
import java.util.Random;
import java.io.IOException;
import java.util.HashMap;
import java.io.*;
import java.util.ArrayList;
import java.util.InputMismatchException;

public class ChasseAuxPokemons {

	public static void main(String[] args) {

		
		
		// TP 10
		final Map<String, Integer> mappePokemons = new HashMap<>();
		
		
		
		try(FileReader lecteur = new FileReader("C:\\Users\\iut\\Desktop\\JavaTP12-master\\src\\tp11\\pokedexComplet.txt")){//changer selon le pc
			Scanner s = new Scanner(lecteur);
			while (s.hasNext()) {
				int numeroPokedex = s.nextInt();
				String nom = s.next();
				mappePokemons.put(nom, numeroPokedex);
			}
			s.close();
		}
		catch(IOException e) {
			e.printStackTrace();
		}
		
		
		// nos attaques
		final Map<String, Attaque> mappeAttaques = new HashMap<>();
		mappeAttaques.put("tackle", new AttaqueTackle());
		mappeAttaques.put("croquer", new AttaqueCroquer());
		mappeAttaques.put("morsure", new AttaqueMorsure());
		mappeAttaques.put("pistoleEau", new AttaquePistoleEau());
		mappeAttaques.put("enfer", new AttaqueEnfer());
		mappeAttaques.put("feinte", new AttaqueFeinte());
		mappeAttaques.put("tornadeFeuilles", new AttaqueTornadeFeuilles());
		mappeAttaques.put("bulle", new AttaqueBulle());
		mappeAttaques.put("coupDeTete", new AttaqueCoupDeTete());

		final ArrayList<Pokemon> pokemonList = new ArrayList<>();
		try(FileReader lecteur = new FileReader("C:\\Users\\iut\\Desktop\\JavaTP12-master\\src\\tp11\\InputFile.txt")){//changer selon le pc
			Scanner s = new Scanner(lecteur);
			while(s.hasNext()) {
				String nom = s.next();
				// TP 10
				System.out.println(nom);
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
			s.close();
		}
		catch (IOException e) {
			e.printStackTrace();
		}
				
		
		// TP 11 -- remplace le code de la bataille du TP 6
		
		// final Bataille b = new Bataille(piplup, rowlet);
		// b.run();
		
		
		final Joueur moi = new Joueur("Onete", "Cristina", 20, new Pokemon[5]);
		
		
		try {
			moi.getPokedex().charger("C:/Users/crist/eclipse-workspace/TP11/src/tp11/monPokedex.txt");//changer selon le pc
		}
		catch (FileNotFoundException e) {
			System.err.println("Je ne peux pas charger le pokedex du joueur " +moi.getPrenom() + " " +moi.getNom() + ". Ce fichier n'existe encore pas, mais il sera genere lors de votre prochaine session.");
		}
		catch (IOException e) {
			System.err.println("Je ne peux pas charger le pokedex du joueur " +moi.getPrenom() + " " +moi.getNom() + ". " + e.getMessage());
			e.printStackTrace();
			System.exit(1);
		}
		catch (InputMismatchException e) {
			System.err.println("Je ne peux pas charger le pokedex du joueur " +moi.getPrenom() + " " +moi.getNom() + ". Le fichier a un mauvais format. " + e.getMessage());
			e.printStackTrace();
			System.exit(1);
		}
		
		final Random alea = new Random();
		moi.capturer(pokemonList.get(alea.nextInt(pokemonList.size())));
		
		
		Scanner lecteur = new Scanner(System.in);
		System.out.println();

		String reponse = "";
		@SuppressWarnings("unused")
		int index;
		int reponseEntier;
		
		while (!reponse.equals("stop")) {

			// interaction 1 : regarder ses pokemons
			System.out.println("Voulez-vous regarder vos pokemons ? Tapez <<oui>> ou <<non>>.");
			reponse = lecteur.next();
			while (!reponse.equals("non") && !reponse.equals("oui")) {
				System.out.println("Refaites votre choix. Tapez <<oui>> ou << non >>.");
				reponse = lecteur.next();
			}
			if (reponse.equals("oui")) {
				System.out.println(moi.afficherPokemons());
				System.out.println("Lequel de vos pokemons voulez-vous regarder ? Tapez son index ou tapez <<non>> pour passer a la prochaine etape.");
				reponse = lecteur.next();
				while(!reponse.equals("non")) {
					System.out.println(moi.getPokemons()[Integer.parseInt(reponse)]);
					System.out.println("Voulez-vous regarder un autre pokemon ? Tapez son index ou tapez <<non>> pour passer a la prochaine etape");
					reponse = lecteur.next();
				}
			}
			
			/*
			// interaction 2 : regarder sa caisse nourriture
			System.out.println("Voulez-vous regarder votre caisse nourriture ? Tapez <<oui>> ou <<non>>.");
			reponse = lecteur.next();
			while (!reponse.equals("non") && !reponse.equals("oui")) {
				System.out.println("Refaites votre choix. Tapez <<oui>> ou << non >>.");
				reponse = lecteur.next();
			}
			if (reponse.equals("oui")) {
				System.out.println(moi.contenusCaisse());
			}
			
			// interaction 3 : nourrir des pokemons
			System.out.println("Voulez-vous nourrir vos pokemons ? Tapez <<oui>> ou <<non>>.");
			reponse = lecteur.next();
			while (!reponse.equals("non") && !reponse.equals("oui")) {
				System.out.println("Refaites votre choix. Tapez <<oui>> ou << non >>.");
				reponse = lecteur.next();
			}
			if (reponse.equals("oui")) {
				System.out.println("Lequel de vos pokemons voulez-vous nourrir ? Tapez son index ou tapez <<non>> pour passer a la prochaine etape.");
				reponse = lecteur.next();
				while(!reponse.equals("non")) {
					index = Integer.parseInt(reponse);
					System.out.println("Donnez l'index (dans la caisse) de la nourriture que vous voulez donner a votre pokemon ou tapez <<non>> pour passer a la prochaine etape.");
					reponse = lecteur.next();
					if (!reponse.equals("non")) {
						moi.nourrirPokemon(moi.getPokemons()[index], Integer.parseInt(reponse));
					}
				}
			}

			*/
			// interaction 4 : generer/se battre avec des pokemons 
			
			Pokemon p = pokemonList.get(alea.nextInt(pokemonList.size())).genAlea();
			if (p == null) {
				System.out.println("Vous n'avez rien trouve. Si vous voulez vous arreter, tapez << stop >> . Sinon, tapez << non >> .");
				reponse = lecteur.next();
				while (!reponse.equals("non") && !reponse.equals("stop")) {
					System.out.println("Refaites votre choix. Tapez << stop >> ou << non >>");
					reponse = lecteur.next();
				}
			} 
			else {
				moi.rencontrer(p);
				System.out.println("Vous avez trouve un pokemon " + p.getNom() + " de niveau " + p.getNiveau() + ".");
				System.out.println("Voulez vous lui faire la bataille ? << oui / non / stop>>");
				reponse = lecteur.next();
				
				while (!reponse.equals("oui") && !reponse.equals("non") && !reponse.equals("stop")) {
					System.out.println("Repetez votre choix s.v.p. Il faut taper << oui >>, << non >> ou << stop >>");
					reponse = lecteur.next();
				}

				if (reponse.equals("oui")) {
					System.out.println("Voici les pokemons actuellement dans votre entourage : " + moi.afficherPokemons());
					System.out.println("Lequel de vos pokemons voulez-vous utiliser dans cette bataille ? Tapez son index.");
					reponseEntier = lecteur.nextInt();
					
					while (reponseEntier < 0 || reponseEntier >= moi.getPokemons().length || (reponseEntier >= 0 && reponseEntier < moi.getPokemons().length && moi.getPokemons()[reponseEntier] == null)) {
						System.out.println("Il faut faire un choix valide. Tapez un index valide " + moi.afficherPokemons());
						reponseEntier = lecteur.nextInt();
					}
					
					Bataille maBataille = new Bataille(moi.getPokemons()[reponseEntier], p);
					maBataille.run();
					System.out.println("Voulez-vous vous arreter ? Tapez << stop >>. Sinon, tapez << non >>");
					reponse = lecteur.next();
					while (!reponse.equals("non") && !reponse.equals("stop")) {
						System.out.println("Refaites votre choix. Tapez << stop >> ou << non >>");
						reponse = lecteur.next();
					}
				}
					
			}

			
			/*
			// interaction 4 : generer/collectionner nourriture
			Nourriture n = tartiflette.genAlea();
			if (n == null) {
				System.out.println("Vous n'avez rien trouve. Si vous voulez vous arreter, tapez << stop >> . Sinon, tapez << non >> .");
				reponse = lecteur.next();
				while (!reponse.equals("non") && !reponse.equals("stop")) {
					System.out.println("Refaites votre choix. Tapez << stop >> ou << non >>");
					reponse = lecteur.next();
				}
			} else {
				System.out.println("Vous avez trouve un(e) " + n.getIngredient() + ".");
				System.out.println("Voulez vous la prendre ? << oui / non / stop>>");
				reponse = lecteur.next();
				
				while (!reponse.equals("oui") && !reponse.equals("non") && !reponse.equals("stop")) {
					System.out.println("Repetez votre choix s.v.p. Il faut taper << oui >>, << non >> ou << stop >>");
					reponse = lecteur.next();
				}

				if (reponse.equals("oui")) {
					moi.addNourriture(n);
					System.out.println("Voulez-vous vous arreter ? Tapez << stop >>. Sinon, tapez << non >>");
					reponse = lecteur.next();
					while (!reponse.equals("non") && !reponse.equals("stop")) {
						System.out.println("Refaites votre choix. Tapez << stop >> ou << non >>");
						reponse = lecteur.next();
					}
				}
					
			}
			*/

			System.out.println();
		}

		
		// TP 10
		try {
			moi.getPokedex().sauvegarder("C:\\Users\\iut\\Desktop\\JavaTP12-master\\src\\tp11\\monPokedex.txt");//changer selon le pc
		}
		catch (IOException e) {
			System.err.println("Je ne peux pas sauvegarder le pokedex du joueur " + moi.getPrenom() + " " +moi.getNom());
			e.printStackTrace();
		}
				
		lecteur.close();	

	}
	

}
