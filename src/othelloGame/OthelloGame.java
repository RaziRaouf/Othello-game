package othelloGame;

import java.util.List;
import java.util.Scanner;

public class OthelloGame {
    private OthelloBoard board;
    private OthelloPlayer player1;
    private OthelloPlayer player2;
    private OthelloPlayer currentPlayer;
    private Scanner scanner;

    public OthelloGame() {
        board = new OthelloBoard();
        player1 = new OthelloPlayer('b');
        player2 = new OthelloPlayer('w');
        currentPlayer = player1;
        scanner = new Scanner(System.in);
    }
    public void startGame() {
    
    System.out.println("Welcome to Othello!");

        while (!isGameOver()) {
            board.printBoard();

            List<int[]> validMoves = board.getValidMoves(currentPlayer);

            if (validMoves.isEmpty()) {
                System.out.println("No valid moves for " + currentPlayer.getColor() + ". Skipping turn.");
                currentPlayer = (currentPlayer == player1) ? player2 : player1;
                continue;
            }

            System.out.println("Valid moves for " + currentPlayer.getColor() + ": ");
            board.printValidMoves(currentPlayer);

            int[] move = getPlayerMove();

            int row = move[0];
            int col = move[1];

            if (board.isValidMove(row, col, currentPlayer)) {
                board.makeMove(row, col, currentPlayer);
                currentPlayer = (currentPlayer == player1) ? player2 : player1;
            } else {
                System.out.println("Invalid move. Try again.");
            }
        }

        board.printBoard();
        System.out.println("Game Over!!");
    }

    private int[] getPlayerMove() {
        System.out.print("Enter row and column (0-7) separated by space: ");
        int row = scanner.nextInt();
        int col = scanner.nextInt();
        return new int[]{row, col};
    }
    public boolean isGameOver() {
        return board.getValidMoves(player1).isEmpty() && board.getValidMoves(player2).isEmpty();
    }
	public OthelloBoard getBoard() {
		return board;
	}
	public OthelloPlayer getCurrentPlayer() {
		return currentPlayer;
	}
	public OthelloPlayer getPlayer1() {
		return player1;
	}
	public OthelloPlayer getPlayer2() {
		return player2;
	}
	public void setCurrentPlayer(OthelloPlayer currentPlayer) {
	    this.currentPlayer = currentPlayer;


}
	
	public void endTurn() {
	    currentPlayer = (currentPlayer == player1) ? player2 : player1;
	}
}