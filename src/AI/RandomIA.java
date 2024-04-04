package AI;

import java.util.List;
import java.util.Random;

import othelloGame.AIPlayer;
import othelloGame.OthelloGame;
import othelloGame.OthelloPlayer;

public class RandomIA implements AIPlayer {
        public static int[] getBestMove(OthelloGame og, OthelloPlayer op) {
                Random random = new Random();
                int index = random.nextInt(og.getBoard().getValidMoves(op).size());
                return og.getBoard().getValidMoves(op).get(index);
        }

}
