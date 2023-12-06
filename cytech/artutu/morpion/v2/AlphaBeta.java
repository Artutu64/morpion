package cytech.artutu.morpion.v2;

import cytech.artutu.morpion.MinMax;
import cytech.artutu.morpion.MinMax.Coup;
import cytech.artutu.morpion.Morpion;

public class AlphaBeta {
	
	public static Coup calc(Morpion morpion, boolean Max) {
		if(MinMax.victoire(morpion) || MinMax.defaite(morpion) || MinMax.egalite(morpion)) {
			return new Coup(MinMax.evaluation(morpion), -1);
		} else {
			if (Max) {
                return maxPlayer(morpion, Integer.MIN_VALUE, Integer.MAX_VALUE);
            } else {
                return minPlayer(morpion, Integer.MIN_VALUE, Integer.MAX_VALUE);
            }
        }
    }

    private static Coup maxPlayer(Morpion morpion, int alpha, int beta) {
        Coup meilleurCoup = new Coup(Integer.MIN_VALUE, -1);
        for (int coup : morpion.getCoupsPossibles()) {
            Morpion clonedMorpion = morpion.clone();
            clonedMorpion.play(coup);
            Coup result = calc(clonedMorpion, false);
            result.place = coup;

            if (result.value > meilleurCoup.value) {
            	meilleurCoup = result;
            }
            alpha = max(alpha, meilleurCoup.value);

            if (beta <= alpha) {
                break;
            }
        }
        return meilleurCoup;
    }

    private static Coup minPlayer(Morpion morpion, int alpha, int beta) {
        Coup meilleurCoup = new Coup(Integer.MAX_VALUE, -1);
        for (int coup : morpion.getCoupsPossibles()) {
            Morpion clonedMorpion = morpion.clone();
            clonedMorpion.play(coup);
            Coup result = calc(clonedMorpion, true);
            result.place = coup;

            if (result.value < meilleurCoup.value) {
            	meilleurCoup = result;
            }
            beta = min(beta, meilleurCoup.value);

            if (beta <= alpha) {
                break;
            }
        }
        return meilleurCoup;
    }

    private static int min(int a, int b) {
        return (a < b) ? a : b;
    }

    private static int max(int a, int b) {
        return (a > b) ? a : b;
    }

}
