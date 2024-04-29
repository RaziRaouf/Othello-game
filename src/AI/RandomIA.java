package AI;
import java.util.List;
import java.util.Random;

import othelloGame.AIPlayer;
import othelloGame.OthelloGame;
import othelloGame.OthelloPlayer;
public class RandomIA implements AIPlayer{
	public static int[] getBestMove(OthelloGame og,OthelloPlayer op) {
		List<int[]> validMoves = og.getBoard().getValidMoves(op);
	    if (validMoves.isEmpty()) {
	        return null; // Pas de mouvements valides
	    }
	    Random random = new Random();
	    int index = random.nextInt(validMoves.size());
	    return validMoves.get(index);
    }
}
