package AI;

import java.util.List;

import othelloGame.*;


public class HardAI implements AIPlayer{
	private static int evaluate(OthelloBoard board, OthelloPlayer player) {
	    int borderScore = 0;

	    // Définir les bords du plateau
	    int[] borders = {0, 7};

	    // Parcourir les positions du plateau
	    for (int i = 0; i < 8; i++) {
	        for (int j = 0; j < 8; j++) {
	            // Vérifier si la position est sur un bord
	            if (isOnBorder(i, j, borders)) {
	                // Vérifier si la position est occupée par le joueur
	                if (board.getGrid()[i][j] == player.getColor()) {
	                    // Ajouter un score positif si la position est contrôlée par le joueur
	                    borderScore++;
	                } else {
	                    // Soustraire un score négatif si la position est contrôlée par l'adversaire
	                    borderScore--;
	                }
	            }
	        }
	    }

	    return borderScore;
	}

	// Méthode pour vérifier si une position est sur un bord
	private static boolean isOnBorder(int row, int col, int[] borders) {
	    return row == borders[0] || row == borders[1] || col == borders[0] || col == borders[1];
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
	public int[] getBestMove(OthelloBoard board,OthelloGame og, OthelloPlayer player, int depth) {
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
	 public boolean hasValidMoves(OthelloBoard board, OthelloPlayer player) {
	        List<int[]> validMoves = board.getValidMoves(player);
	        return !validMoves.isEmpty();
	    }
}
