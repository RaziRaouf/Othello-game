package AI;

import java.util.List;

import othelloGame.AIPlayer;
import othelloGame.OthelloBoard;
import othelloGame.OthelloGame;
import othelloGame.OthelloPlayer;

public class ProAI implements AIPlayer{
	private static final int[][] WEIGHTS = {
	        {100, -20, 10, 5, 5, 10, -20, 100},
	        {-20, -50, -2, -2, -2, -2, -50, -20},
	        {10, -2, 8, 1, 1, 8, -2, 10},
	        {5, -2, 1, 3, 3, 1, -2, 5},
	        {5, -2, 1, 3, 3, 1, -2, 5},
	        {10, -2, 8, 1, 1, 8, -2, 10},
	        {-20, -50, -2, -2, -2, -2, -50, -20},
	        {100, -20, 10, 5, 5, 10, -20, 100}
	    };
	public static int alphaBeta(OthelloBoard ob, OthelloGame og,OthelloPlayer op, int depth, int alpha, int beta) {
		if (depth == 0 || og.isGameOver()) {
	        return evaluate(ob,op);
	    }
	    OthelloPlayer opponent = (op.getColor() == 'b') ? new OthelloPlayer('w') : new OthelloPlayer('b');
	    List<int[]> validMoves = ob.getValidMoves(op);

	    if (validMoves.isEmpty()) {
	        return alphaBeta(ob,og, opponent, depth - 1, alpha, beta);
	    }

	    if (op.getColor() == 'w') {
	        for (int[] move : validMoves) {
	        	OthelloBoard copyBoard = new OthelloBoard(ob);
	            copyBoard.makeMove(move[0], move[1],op);

	            int value = alphaBeta(copyBoard, og,opponent, depth - 1, alpha, beta);
	            alpha = Math.max(alpha, value);
	            if (alpha >= beta) {
	                break; // Coupe Beta
	            }
	        }
	        return alpha;
	    } else {
	        for (int[] move : validMoves) {
	        	OthelloBoard copyBoard = new OthelloBoard(ob);
	            copyBoard.makeMove(move[0], move[1], op);

	            int value = alphaBeta(copyBoard,og, opponent, depth - 1, alpha, beta);
	            beta = Math.min(beta, value);
	            if (beta <= alpha) {
	                break; // Coupe Alpha
	            }
	        }
	        return beta;
	    }
		}
		public static  int[] getBestMove(OthelloBoard board,OthelloGame og, OthelloPlayer player, int depth) {
			List<int[]> validMoves = board.getValidMoves(player);
		    int bestValue = Integer.MIN_VALUE;
		    int [] bestMove = null;

		    OthelloPlayer opponent = (player.getColor() == 'b') ? new OthelloPlayer('w') : new OthelloPlayer('b');

		    for (int[] move : validMoves) {
		    	OthelloBoard copyBoard = new OthelloBoard(board);
		        copyBoard.makeMove(move[0], move[1], player);

		        int value = alphaBeta(copyBoard,og, opponent, depth - 1, Integer.MIN_VALUE, Integer.MAX_VALUE);
		        if (value > bestValue) {
		            bestValue = value;
		            bestMove = move;
		        }
		    }

		    return bestMove;
		}
		public static int evaluate(OthelloBoard ob,OthelloPlayer op) {
			int score=0;
			char [][] board=ob.getGrid();
			for(int i=0;i<8;i++) {
				for(int j=0;j<8;j++) {
					if(board[i][j]==op.getColor()) {
						score+=WEIGHTS[i][j];
					
					if((i==0 ||i==7)&&(j==0 || j==7)) {
						score+=100;
					}
					else if((i==0||i==7||j==0||j==7)) {
						score+=10;
					}
					}
				}
			}
			return score;
		};
}
