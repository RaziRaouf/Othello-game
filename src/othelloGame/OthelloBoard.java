package othelloGame;

import java.util.ArrayList;
import java.util.List;

public class OthelloBoard {

private char grid [][];
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
public char[][] getGrid () {
	return grid;
}
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
public void printBoard() {
	for (int i=0;i<8;i++) {
	 for (int j=0;j<8;j++) {
		 System.out.print(grid[i][j]+"");
	 }
	 System.out.println();
	}
}

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
public void makeMove(int i, int j, OthelloPlayer player) {
    if (isValidMove(i, j, player)) {
        grid[i][j] = player.getColor(); // Placer le pion du joueur sur la case spécifiée

        // Retourner les pions adverses capturés dans les directions appropriées
        flippedPieces(i, j, player);
    }
}
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
public void printValidMoves(OthelloPlayer player) {
    List<int[]> validMoves = this.getValidMoves(player);
    for (int i = 0; i < validMoves.size(); i++) {
        int[] move = validMoves.get(i);
        System.out.println("(" + move[0] + ", " + move[1] + ")");
    }
}
public boolean hasValidMoves(OthelloPlayer player) {
    List<int[]> validMoves = getValidMoves(player);
    return !validMoves.isEmpty();
}

}
