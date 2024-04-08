package Test;

import java.util.List;

import AI.*;
import othelloGame.*;

public class TestAI {
	   public static void main(String[] args) {
	        testMatch(); // Modifier la profondeur selon vos besoins}
	   }
	   public static void testMatch() {
		   
		   int [] depths= {1,2,4,6,8,10};
		   
		   for (int i=0;i<depths.length;i++) {
			   for(int j=0;j<depths.length;j++) {
				   
			  
		    // Boucle pour exécuter plusieurs matchs
		        OthelloGame game = new OthelloGame(); // Création d'une nouvelle partie à chaque match
		        OthelloBoard board = game.getBoard();
		        int turn = 0;
		        ProAI proAI=new ProAI();
		        HardAI hardAI=new HardAI();

		        while (!game.isGameOver()) {
		          //  System.out.println("Turn: " + turn);
		            System.out.println("Current player: " + game.getCurrentPlayer().getColor());

		            OthelloPlayer currentPlayer = game.getCurrentPlayer();
		            int[] move; // Déclaration de la variable move à l'extérieur du bloc if

		            if (currentPlayer.getColor() == 'w') {
		                move = proAI.getBestMove(game.getBoard(), game, currentPlayer, depths[i]);
		                
		            } else {
		                move = hardAI.getBestMove(board, game, currentPlayer, depths[j]);
		            }

		            // Vaérification des mouvements valides
	                if (move == null || !game.getBoard().isValidMove(move[0], move[1], currentPlayer)) {
	                    System.out.println("Invalid move for " + currentPlayer.getColor() + ". Skipping turn.");
	                    game.endTurn(); // Fin du tour si le mouvement est invalide
	                    continue; // Passer au prochain tour
	                }
		            game.getBoard().makeMove(move[0], move[1], currentPlayer);
		            
		            
		            
		            
		            game.endTurn();
		            turn++;
		        }

		        // Affichage du plateau et du résultat final
		        board.printBoard();
		        System.out.println("Score Final: w- ("+depths[i]+")" + game.getBoard().countWhite() + "b- ("+depths[j]+")" + game.getBoard().countBlack());
		        System.out.println("Game Over!");
		    }
		   }
	   }
}