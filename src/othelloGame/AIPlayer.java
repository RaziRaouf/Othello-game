package othelloGame;

/**
 * Represents an AI player in the Othello game.
 */
public interface AIPlayer {
    /**
     * Executes the alpha-beta pruning algorithm to determine the best move for the AI player.
     *
     * @param ob     The current Othello board.
     * @param og     The current Othello game.
     * @param op     The AI player making the move.
     * @param depth  The depth of the search tree.
     * @param alpha  The alpha value.
     * @param beta   The beta value.
     * @return The beta value.
     */
    public static int alphaBeta(OthelloBoard ob, OthelloGame og, OthelloPlayer op, int depth, int alpha, int beta) {
        return beta;
    }

    /**
     * Retrieves the best move for the AI player.
     *
     * @param board  The current Othello board.
     * @param og     The current Othello game.
     * @param player The AI player making the move.
     * @param depth  The depth of the search tree.
     * @return An array containing the row and column of the best move.
     */
    public default int[] getBestMove(OthelloBoard board, OthelloGame og, OthelloPlayer player, int depth) {
        return null;
    }
}
