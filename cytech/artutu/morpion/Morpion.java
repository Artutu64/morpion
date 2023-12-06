package cytech.artutu.morpion;

import java.util.ArrayList;

public class Morpion {
	
	private String[] cases;
	
	private Morpion(String[] cases) {
		this.cases = cases;
	}
	
	public Morpion() {
		this(new String[] { ".", ".", ".", 
							".", ".", ".", 
						 ".", ".", "." });
	}
	
	@Override
	public Morpion clone() {
		Morpion morpion = new Morpion();
		for(int i = 0; i < cases.length; i++) {
			morpion.cases[i] = cases[i];
		}
		morpion.lettre = lettre;
		return morpion;
	}
	
	
	
	private String lettre = "X";
	
	public void setLettre(String lettre) {
		this.lettre = lettre;
	}
	
	public ArrayList<Integer> getCoupsPossibles() {
		ArrayList<Integer> coups = new ArrayList<>();
		for(int i = 0; i < cases.length; i++) {
			if(cases[i].equals(".")) {
				coups.add(i);
			}
		}
		return coups;
	}
	
	public String getCaseCentrale() {
		return cases[4];
	}
	
	@Override
	public String toString() {
		StringBuilder str = new StringBuilder();
		for(int i = 0; i < 3; i++) {
			str.append("+---+---+---+     +---+---+---+\n|");
			for(int j = 0; j < 3; j++) {
				int k = i*3 + j;
				str.append(" " + cases[k] + " |");
			}
			str.append("     |");
			for(int j = 0; j < 3; j++) {
				int k = i*3 + j;
				str.append(" " + k + " |");
			}
			str.append("\n");
		}
		str.append("+---+---+---+     +---+---+---+\n");
		return str.toString();
	}
	
	public boolean play(int place) {
		ArrayList<Integer> coups = getCoupsPossibles();
		if(!(coups.contains((Integer) place))) {
			System.out.println("Erreur, vous ne pouvez pas poser de pièces ici.");
			return false;
		}
		cases[place] = lettre;
		switchLetter();
		return true;
	}
	
	private void switchLetter() {
		if(lettre.equals("X")) {
			lettre = "O";
		} else {
			lettre = "X";
		}
	}
	
	/*
	 *  -1 le joueur 1 remporte la victoire (X)
	 *   0 pas de vainqueurs
	 *   1 le joueur 2 remporte la victoire (O)
	 */
	public int isAWinner() {
		String res = ".";
		// LIGNES
		if(notEmptyCase(0) && cases[0] == cases[1] && cases[1] == cases[2]) {
			res = cases[0];
		}
		if(notEmptyCase(3) && cases[3] == cases[4] && cases[4] == cases[5]) {
			res = cases[3];
		}
		if(notEmptyCase(6) && cases[6] == cases[7] && cases[7] == cases[8]) {
			res = cases[6];
		}
		// Collones
		if(notEmptyCase(0) && cases[0] == cases[3] && cases[3] == cases[6]) {
			res = cases[0];
		}
		if(notEmptyCase(1) && cases[1] == cases[4] && cases[4] == cases[7]) {
			res = cases[1];
		}
		if(notEmptyCase(2) && cases[2] == cases[5] && cases[5] == cases[8]) {
			res = cases[2];
		}
		// diagonales
		if(notEmptyCase(0) && cases[0] == cases[4] && cases[4] == cases[8]) {
			res = cases[0];
		}
		if(notEmptyCase(2) && cases[2] == cases[4] && cases[4] == cases[6]) {
			res = cases[2];
		}
		
		if(res.equals("X")) return -1;
		else if(res.equals("O")) return 1;
		else return 0;
	}
	
	public boolean notEmptyCase(int i) {
		return !(cases[i].equals("."));
	}
}
