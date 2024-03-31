package gui;
import othelloGame.*;
import AI.*;

public class TestAI {
    public static void main(String[] args) {
        // Initialisation des deux IA
        FacileIA player1 = new FacileIA();
        HardAI player2 = new HardAI();

        // Initialisation du jeu avec les deux IA
        OthelloGame game = new OthelloGame();

        // Boucle de jeu jusqu'à ce que la partie soit terminée
        while (!game.isGameOver()) {
            // Récupération du joueur actuel
            OthelloPlayer currentPlayer = game.getCurrentPlayer();

            // Récupération du meilleur mouvement pour le joueur actuel
            int[] move=new int[2];
            if (currentPlayer.getColor() == 'b') {
                move = player1.getBestMove(game.getBoard(), game, currentPlayer, 4);
            } else {
                move = player2.getBestMove(game.getBoard(), game, currentPlayer, 4);
            }

            // Application du mouvement sur le plateau
            game.getBoard().makeMove(move[0], move[1], currentPlayer);

            // Passage au tour suivant
            game.endTurn();
        }
        int countPlayer1 = game.getBoard().countBlack();
        int countPlayer2 = game.getBoard().countWhite();

        // Détermination du gagnant
        char winner = (countPlayer1 > countPlayer2) ? 'b' : ((countPlayer1 < countPlayer2) ? 'w' : ' ');

        // Affichage du résultat
        if (winner == ' ') {
            System.out.println("It's a tie!");
        } else {
            System.out.println("Winner: " + winner);
        }
    }
    }

