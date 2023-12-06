package cytech.artutu.morpion;

import java.util.Scanner;

import cytech.artutu.morpion.MinMax.Coup;
import cytech.artutu.morpion.v2.AlphaBeta;

public class Main {

	public static void main(String[] args) {
		
		if(args.length == 0) {
			System.out.println("Usage du fichier: java -jar <fichier>.jar <minmax/alphabeta>");
			return ;
		}
		
		if(!(args[0].equals("minmax") || args[0].equals("alphabeta"))) {
			System.out.println("Usage du fichier: java -jar <fichier>.jar <minmax/alphabeta>");
			return ;
		}
		
		Morpion morpion = new Morpion();
		int vainqueur = 0;
		while(vainqueur == 0 && (morpion.getCoupsPossibles().size() > 0)) {
			System.out.println(morpion);
			morpion.play(getCoup(morpion));
			vainqueur = morpion.isAWinner();
			if(vainqueur == 0 && morpion.getCoupsPossibles().size() > 0) {
				Coup coup;
				if(args[0].equals("minmax")) {
					coup = MinMax.minmax(morpion, 9, true);
				} else {
					coup = AlphaBeta.calc(morpion, true);
				}
				System.out.println("Case voulue par l'ordinateur: " + coup.place);
				morpion.play(coup.place);
				vainqueur = morpion.isAWinner();
			}
		}
		System.out.println("\n\nVictoire du joueur: " + ((vainqueur == -1) ? "VOUS" : ((vainqueur == 0) ? "EGALITE" : "ORDINATEUR")));
		System.out.println(morpion);
	}
	
	@SuppressWarnings("resource")
	public static int getCoup(Morpion morpion) {
		Scanner scanner = new Scanner(System.in);
		int coup = -1;
		while(coup == -1) {
			System.out.print("Choisissez la case voulue: ");
			try {
				coup = scanner.nextInt();
			} catch(Exception e) {
				System.out.println("Erreur, ceci n'est pas un nombre.");
			}
			if(!(morpion.getCoupsPossibles().contains((Integer) coup))){
				coup = -1;
				System.out.println("Ce coups n'est pas possible !");
			}
		}
		return coup;
	}

}
