package cytech.artutu.morpion;

import java.util.ArrayList;

public class MinMax {
	
	public static class Coup {
		
		public int value = 0;
		public int place = -1;
		
		public Coup(int value, int place) {
			this.value = value;
			this.place = place;
		}
		
	}
	
	public static boolean victoire(Morpion morpion) {
		int v = morpion.isAWinner();
		return (v == 1);
	}
	
	public static boolean defaite(Morpion morpion) {
		int v = morpion.isAWinner();
		return (v == -1);
	}
	
	public static boolean egalite(Morpion morpion) {
		int v = morpion.isAWinner();
		if(v == 0 && morpion.getCoupsPossibles().size() == 0) {
			return true;
		}
		return false;
	}
	
	public static int evaluation(Morpion morpion) {
		int value = 0;
		if(morpion.getCaseCentrale().equals("O")) {
			value += 50;
		}
		if(victoire(morpion)) {
			value += 100;
		}
		if(defaite(morpion)){
			value -= 100;
		}
		return value;
	}
	
	public static Coup minmax(Morpion morpion, int profondeur, boolean evalMax) {
		if(profondeur == 0 || victoire(morpion) || defaite(morpion) || egalite(morpion)) {
			return new Coup(evaluation(morpion), -1);
		} else {
			if(evalMax) {
				ArrayList<Integer> casesPossibles = morpion.getCoupsPossibles();
				Coup cc = null;
				for(int casee : casesPossibles) {
					Morpion morpionCloned = morpion.clone();
					morpionCloned.play(casee);
					Coup c = minmax(morpionCloned, profondeur-1, !evalMax);
					c.place = casee;
					if(cc == null || cc.value < c.value) {
						cc = c;
					}
				}
				return cc;
			} else {
				ArrayList<Integer> casesPossibles = morpion.getCoupsPossibles();
				Coup cc = null;
				for(int casee : casesPossibles) {
					Morpion morpionCloned = morpion.clone();
					morpionCloned.play(casee);
					Coup c = minmax(morpionCloned, profondeur-1, !evalMax);
					c.place = casee;
					if(cc == null || cc.value > c.value) {
						cc = c;
					}
				}
				return cc;
			}
		}
	}

}
