package othelloGame;

import java.util.ArrayList;
import java.util.List;
/**
 * Represents the Othello game board.
 */
public class OthelloBoard {
	

private char grid [][];
/**
 * Constructs an empty Othello board with initial setup.
 */

public OthelloBoard() {
	grid=new char[8][8];
	for(int i=0;i<8;i++) {
		for(int j=0;j<8;j++) {
			grid[i][j]=' ';
		}
	}
	grid[3][3]='w';
	grid[3][4]='b';
	grid[4][3]='b';
	grid[4][4]='w';
}
/**
 * Constructs a copy of the given Othello board.
 *
 * @param ob The Othello board to copy.
 */

public OthelloBoard(OthelloBoard ob) {
    int rows = 8;
    int cols = 8;
    this.grid = new char[rows][cols];
    for (int i = 0; i < rows; i++) {
        for (int j = 0; j < cols; j++) {
            this.grid[i][j] = ob.grid[i][j];
        }
    }
}
/**
 * Retrieves the grid representing the Othello board.
 *
 * @return The grid representing the Othello board.
 */
public char[][] getGrid () {
	return grid;
}
/**
 * Counts the number of white pieces on the board.
 *
 * @return The number of white pieces.
 */
public int countWhite() {
	int count=0;
	for(int i=0;i<8;i++) {
		for(int j=0;j<8;j++) {
			if(this.grid[i][j]=='w') { 
				count++;
		}
	}
}
	return count;
}
/**
 * Counts the number of black pieces on the board.
 *
 * @return The number of black pieces.
 */
public int countBlack() {
	int count=0;
	for(int i=0;i<8;i++) {
		for(int j=0;j<8;j++) {
			if(this.grid[i][j]=='b') { 
				count++;
		}
	}
}
	return count;
}
/**
 * Prints the current state of the Othello board.
 */
public void printBoard() {
	for (int i=0;i<8;i++) {
	 for (int j=0;j<8;j++) {
		 System.out.print(grid[i][j]+"");
	 }
	 System.out.println();
	}
}
/**
 * Checks if a given move is valid for the specified player.
 *
 * @param i      The row index of the move.
 * @param j      The column index of the move.
 * @param player The player making the move.
 * @return True if the move is valid, false otherwise.
 */
public boolean isValidMove(int i, int j, OthelloPlayer player) {
    if (grid[i][j] != ' ') {
        return false; // La case n'est pas vide, le mouvement n'est pas valide
    }

    char currentPlayerColor = player.getColor();
    char opponentColor = (currentPlayerColor == 'b') ? 'w' : 'b';

    // Vérification des huit directions autour de la case (i, j)
    for (int di = -1; di <= 1; di++) {
        for (int dj = -1; dj <= 1; dj++) {
            if (di == 0 && dj == 0) {
                continue; // Ne pas vérifier la case elle-même
            }

            int row = i + di;
            int col = j + dj;
            boolean foundOpponent = false;

            // Parcourir dans la direction (di, dj)
            while (row >= 0 && row < 8 && col >= 0 && col < 8) {
                if (grid[row][col] == opponentColor) {
                    foundOpponent = true;
                } else if (grid[row][col] == currentPlayerColor) {
                    if (foundOpponent) {
                        return true; // Au moins un pion de l'adversaire est capturé dans cette direction
                    } else {
                        break; // Pas de pion de l'adversaire capturé, mouvement invalide dans cette direction
                    }
                } else {
                    break; // Case vide, mouvement invalide dans cette direction
                }
                // Avancer dans la direction
                row += di;
                col += dj;
            }
        }
    }

    return false; // Aucun pion de l'adversaire capturé dans toutes les directions, mouvement invalide
}
/**
 * Makes a move on the board for the specified player.
 *
 * @param i      The row index of the move.
 * @param j      The column index of the move.
 * @param player The player making the move.
 */
public boolean flippedPieces(int i, int j, OthelloPlayer player) {
    char currentPlayerColor = player.getColor();
    char opponentColor = (currentPlayerColor == 'b') ? 'w' : 'b';

    boolean flipped = false;

    // Vérification des huit directions autour de la case (i, j)
    for (int di = -1; di <= 1; di++) {
        for (int dj = -1; dj <= 1; dj++) {
            if (di == 0 && dj == 0) {
                continue; // Ne pas vérifier la case elle-même
            }

            int row = i + di;
            int col = j + dj;
            boolean foundOpponent = false;

            // Liste pour stocker les pions à retourner dans cette direction
            List<int[]> flippedPiecesInDirection = new ArrayList<>();

            // Parcourir dans la direction (di, dj)
            while (row >= 0 && row < 8 && col >= 0 && col < 8) {
                if (grid[row][col] == opponentColor) {
                    foundOpponent = true;
                    flippedPiecesInDirection.add(new int[]{row, col}); // Ajouter le pion adverse à retourner
                } else if (grid[row][col] == currentPlayerColor) {
                    if (foundOpponent) {
                        // Retourner les pions adverses dans cette direction
                        for (int[] flippedPiece : flippedPiecesInDirection) {
                            int flippedRow = flippedPiece[0];
                            int flippedCol = flippedPiece[1];
                            grid[flippedRow][flippedCol] = currentPlayerColor;
                        }
                        flipped = true;
                    }
                    break; // Sortir de la boucle, aucune pion à retourner
                } else {
                    break; // Case vide, aucun pion à retourner
                }
                // Avancer dans la direction
                row += di;
                col += dj;
            }
        }
    }

    return flipped; // Retourner vrai si au moins un pion a été retourné dans une direction
}
/**
 * Makes a move on the board for the specified player.
 *
 * @param i      The row index of the move.
 * @param j      The column index of the move.
 * @param player The player making the move.
 */
public void makeMove(int i, int j, OthelloPlayer player) {
    if (isValidMove(i, j, player)) {
        grid[i][j] = player.getColor(); // Placer le pion du joueur sur la case spécifiée

        // Retourner les pions adverses capturés dans les directions appropriées
        flippedPieces(i, j, player);
    }
}
/**
 * Retrieves a list of valid moves for the specified player.
 *
 * @param player The player for whom to find valid moves.
 * @return A list of valid moves.
 */
public List<int[]> getValidMoves(OthelloPlayer player) {
    List<int[]> validMoves = new ArrayList<>();
    for (int i = 0; i < 8; i++) {
        for (int j = 0; j < 8; j++) {
            if (isValidMove(i, j, player)) {
                validMoves.add(new int[]{i, j});
            }
        }
    }
    return validMoves;
}
/**
 * Prints the valid moves for the specified player.
 *
 * @param player The player for whom to print valid moves.
 */
public void printValidMoves(OthelloPlayer player) {
    List<int[]> validMoves = this.getValidMoves(player);
    for (int i = 0; i < validMoves.size(); i++) {
        int[] move = validMoves.get(i);
        System.out.println("(" + move[0] + ", " + move[1] + ")");
    }
}
/**
 * Checks if the specified player has any valid moves.
 *
 * @param player The player to check for valid moves.
 * @return True if the player has valid moves, false otherwise.
 */
public boolean hasValidMoves(OthelloPlayer player) {
    List<int[]> validMoves = getValidMoves(player);
    return !validMoves.isEmpty();
}
/**
 * Counts the number of opponent's pieces that would be flipped by making each valid move.
 *
 * @param player The player for whom to count flipped pieces.
 * @return The total number of pieces that would be flipped.
 */

public int countFlippedPieces(OthelloPlayer player) {
	char currentPlayerColor = player.getColor();
    char opponentColor = (currentPlayerColor == 'b') ? 'w' : 'b';

    int flipped = 0;

    // Parcourir toutes les cases du plateau
    for (int row = 0; row < 8; row++) {
        for (int col = 0; col < 8; col++) {
            // Vérifier si le mouvement est valide pour le joueur donné
            if (isValidMove(row, col, player)) {
                // Vérifier les pièces retournées pour ce mouvement
                int flippedInMove = 0;
                for (int di = -1; di <= 1; di++) {
                    for (int dj = -1; dj <= 1; dj++) {
                        if (di == 0 && dj == 0) {
                            continue; // Ne pas vérifier la case elle-même
                        }

                        int r = row + di;
                        int c = col + dj;
                        boolean foundOpponent = false;

                        // Parcourir dans la direction (di, dj)
                        while (r >= 0 && r < 8 && c >= 0 && c < 8) {
                            if (grid[r][c] == opponentColor) {
                                foundOpponent = true;
                            } else if (grid[r][c] == currentPlayerColor) {
                                if (foundOpponent) {
                                    // Compter le nombre de pièces retournées dans cette direction
                                    flippedInMove += Math.max(Math.abs(r - row), Math.abs(c - col)) - 1;
                                }
                                break; // Sortir de la boucle, aucun pion à retourner
                            } else {
                                break; // Case vide, aucun pion à retourner
                            }
                            // Avancer dans la direction
                            r += di;
                            c += dj;
                        }
                    }
                }
                flipped += flippedInMove;
            }
        }
    }

    return flipped;
}



}
