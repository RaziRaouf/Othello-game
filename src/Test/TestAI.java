package Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.List;
import AI.*;
import othelloGame.*;

public class TestAI {
    public static void main(String[] args) {
        testMatch(new ProAI(),new StabiltyAI());
    }
    public static void testMatch(AIPlayer aiPlayer1,AIPlayer aiPlayer2) {
        int[] depths = {1,2,3,4,6};
        
        // Initialisation des compteurs de victoires pour chaque IA
        int[] aiPlayer1Wins = new int[depths.length];
        int[] aiPlayer2Wins = new int[depths.length];

        for (int i = 0; i < depths.length; i++) {
            for (int j = 0; j < depths.length; j++) {
                // Initialisation du jeu pour chaque match
                OthelloGame game = new OthelloGame();
                OthelloBoard board = game.getBoard();
                int turn = 0;

                while (!game.isGameOver()) {
                    OthelloPlayer currentPlayer = game.getCurrentPlayer();
                    int[] move;

                    if (currentPlayer.getColor() =='w') {
                        move =aiPlayer1.getBestMove(game.getBoard(), game, currentPlayer, depths[i]);
                    } else {
                        move = aiPlayer2.getBestMove(board, game, currentPlayer, depths[j]);
                    }

                    if (move == null || !game.getBoard().isValidMove(move[0], move[1], currentPlayer)) {
                        System.out.println("Invalid move for " + currentPlayer.getColor() + ". Skipping turn.");
                        game.endTurn();
                        continue;
                    }
                    game.getBoard().makeMove(move[0], move[1], currentPlayer);
                    game.endTurn();
                    turn++;
                }

                // Affichage du plateau et du résultat final de chaque match
                board.printBoard();
                System.out.println("Score Final at depth " + depths[i] + " (aiPlayer1): " + game.getBoard().countWhite());
                System.out.println("Score Final at depth " + depths[j] + " (aiPlayer2): " + game.getBoard().countBlack());

                // Vérification du résultat du match et incrémentation des compteurs de victoires
                if (game.getBoard().countWhite() > game.getBoard().countBlack()) {
                    aiPlayer1Wins[i]++;
                } else if (game.getBoard().countWhite() < game.getBoard().countBlack()) {
                    aiPlayer2Wins[j]++;
                }
            }
        }
        for (int i = 0; i < depths.length; i++) {
            System.out.println("aiPlayer1 wins at depth " + depths[i] + ": " + aiPlayer1Wins[i]);
            System.out.println("aiPlayer2 wins at depth " + depths[i] + ": " + aiPlayer2Wins[i]);
        }
    }
}
