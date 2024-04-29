package AI;

import java.util.List;

import othelloGame.AIPlayer;
import othelloGame.OthelloBoard;
import othelloGame.OthelloGame;
import othelloGame.OthelloPlayer;

/**
 * Represents a hard AI player for Othello.
 */
public class HardAI implements AIPlayer {

    /**
     * Evaluates the current state of the Othello board for the given player.
     *
     * @param board  The current Othello board.
     * @param player The player for whom to evaluate the board.
     * @return The evaluation score.
     */
    private static int evaluate(OthelloBoard board, OthelloPlayer player) {
        int borderScore = 0;

        // Define the borders of the board
        int[] borders = {0, 7};

        // Iterate through the board positions
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                // Check if the position is on a border
                if (isOnBorder(i, j, borders)) {
                    // Check if the position is occupied by the player
                    if (board.getGrid()[i][j] == player.getColor()) {
                        // Add a positive score if the position is controlled by the player
                        borderScore++;
                    } else {
                        // Subtract a negative score if the position is controlled by the opponent
                        borderScore--;
                    }
                }
            }
        }

        return borderScore;
    }

    // Method to check if a position is on a border
    private static boolean isOnBorder(int row, int col, int[] borders) {
        return row == borders[0] || row == borders[1] || col == borders[0] || col == borders[1];
    }

    /**
     * Implements the alpha-beta pruning algorithm to find the best move for the AI player.
     *
     * @param ob     The current Othello board.
     * @param og     The current Othello game.
     * @param op     The AI player making the move.
     * @param depth  The depth of the search tree.
     * @param alpha  The alpha value.
     * @param beta   The beta value.
     * @return The evaluation score.
     */
    public static int alphaBeta(OthelloBoard ob, OthelloGame og, OthelloPlayer op, int depth, int alpha, int beta) {
        if (depth == 0 || og.isGameOver()) {
            return evaluate(ob, op);
        }
        OthelloPlayer opponent = (op.getColor() == 'b') ? new OthelloPlayer('w') : new OthelloPlayer('b');
        List<int[]> validMoves = ob.getValidMoves(op);

        if (validMoves.isEmpty()) {
            return alphaBeta(ob, og, opponent, depth - 1, alpha, beta);
        }

        if (op.getColor() == 'w') {
            for (int[] move : validMoves) {
                OthelloBoard copyBoard = new OthelloBoard(ob);
                copyBoard.makeMove(move[0], move[1], op);

                int value = alphaBeta(copyBoard, og, opponent, depth - 1, alpha, beta);
                alpha = Math.max(alpha, value);
                if (alpha >= beta) {
                    break; // Beta cut-off
                }
            }
            return alpha;
        } else {
            for (int[] move : validMoves) {
                OthelloBoard copyBoard = new OthelloBoard(ob);
                copyBoard.makeMove(move[0], move[1], op);

                int value = alphaBeta(copyBoard, og, opponent, depth - 1, alpha, beta);
                beta = Math.min(beta, value);
                if (beta <= alpha) {
                    break; // Alpha cut-off
                }
            }
            return beta;
        }
    }

    /**
     * Finds the best move for the AI player using the alpha-beta pruning algorithm.
     *
     * @param board  The current Othello board.
     * @param og     The current Othello game.
     * @param player The AI player making the move.
     * @param depth  The depth of the search tree.
     * @return An array containing the row and column of the best move.
     */
    public int[] getBestMove(OthelloBoard board, OthelloGame og, OthelloPlayer player, int depth) {
        List<int[]> validMoves = board.getValidMoves(player);
        int bestValue = Integer.MIN_VALUE;
        int[] bestMove = null;

        char opponentColor = (player.getColor() == 'b') ? 'w' : 'b'; // Opponent's color
        OthelloPlayer opponent = new OthelloPlayer(opponentColor); // Create opponent player

        for (int[] move : validMoves) {
            OthelloBoard copyBoard = new OthelloBoard(board);
            copyBoard.makeMove(move[0], move[1], player);

            int value = alphaBeta(copyBoard, og, opponent, depth - 1, Integer.MIN_VALUE, Integer.MAX_VALUE);
            if (value > bestValue) {
                bestValue = value;
                bestMove = move;
            }
        }

        return bestMove;
    }
}
