package Test;

import AI.FacileIA;
import AI.HardAI;
import AI.ProAI;
import othelloGame.AIPlayer;
import othelloGame.OthelloBoard;
import othelloGame.OthelloGame;
import othelloGame.OthelloPlayer;

public class TestAI {
    public static void main(String[] args) {
        TestAI test = new TestAI();
        test.testAIs();
    }

    public void testAIs() {
        OthelloGame game = new OthelloGame();
        OthelloPlayer player1 = new OthelloPlayer('b'); // Joueur noir
        OthelloPlayer player2 = new OthelloPlayer('w'); // Joueur blanc
        FacileIA facileAI = new FacileIA();
        ProAI proAI = new ProAI();

        while (!game.isGameOver()) {
            OthelloPlayer currentPlayer = game.getCurrentPlayer();
            int[] move;

            // Choix du meilleur mouvement pour chaque IA
            if (currentPlayer.getColor() == 'b') {
                move = facileAI.getBestMove(game.getBoard(), game, currentPlayer, 8);
            } else {
                move = proAI.getBestMove(game.getBoard(), game, currentPlayer, 8);
            }

            // Exécuter le mouvement
            game.getBoard().makeMove(move[0], move[1], currentPlayer);
            game.endTurn();
        }

        // Affichage des résultats
        System.out.println("Game Over!");
        System.out.println("White Count: " + game.getBoard().countWhite());
        System.out.println("Black Count: " + game.getBoard().countBlack());
    }
}