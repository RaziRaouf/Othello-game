package AI;

import java.util.List;

import othelloGame.*;
public class StabiltyAI implements AIPlayer{
	public static int evaluate(OthelloBoard board, OthelloPlayer player) {
	    int stabilityScore = 0;
	    char[][] grid = board.getGrid();
	    int[] corners = {0, 7}; // Positions des coins du plateau

	    // Parcours du plateau pour évaluer la stabilité des pièces
	    for (int i = 0; i < 8; i++) {
	        for (int j = 0; j < 8; j++) {
	            // Vérifier si la case est occupée par le joueur
	            if (grid[i][j] == player.getColor()) {
	                // Vérifier si la case est un coin ou un bord
	                if ((i == corners[0] || i == corners[1]) && (j == corners[0] || j == corners[1])) {
	                    // La pièce est dans un coin
	                    stabilityScore += 10;
	                } else if (i == corners[0] || i == corners[1] || j == corners[0] || j == corners[1]) {
	                    // La pièce est sur un bord
	                    stabilityScore += 5;
	                } else {
	                    // La pièce est à l'intérieur du plateau
	                    stabilityScore += 1;
	                }
	            }
	        }
	    }
	    return stabilityScore;
	}
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
}
