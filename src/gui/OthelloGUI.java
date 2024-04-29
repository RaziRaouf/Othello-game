package gui;

import javax.swing.*;

import othelloGame.OthelloGame;
import othelloGame.OthelloPlayer;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
/**
 * Represents a graphical user interface (GUI) for the Othello game with an easy AI opponent.
 */
public class OthelloGUI extends JFrame {
    private OthelloGame game;
    private JButton[][] buttons;
    private JLabel whiteCountLabel;
    private JLabel blackCountLabel;
    
    public OthelloGUI() {
        super("Othello");
        game = new OthelloGame();
        buttons = new JButton[8][8];
        whiteCountLabel = new JLabel("White Count: ");
        blackCountLabel = new JLabel("Black Count: ");
        

        JPanel boardPanel = new JPanel(new GridLayout(8, 8));

        // Création des boutons pour représenter le plateau de jeu
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                JButton button = new JButton();
                button.setPreferredSize(new Dimension(60, 60)); // Taille des boutons
                button.addActionListener(new ButtonClickListener(i, j));
                buttons[i][j] = button;
                boardPanel.add(button);
            }
        }

        JPanel countPanel = new JPanel();
        countPanel.add(whiteCountLabel);
        countPanel.add(blackCountLabel);

        getContentPane().add(boardPanel, BorderLayout.CENTER);
        getContentPane().add(countPanel, BorderLayout.SOUTH);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 650); // Ajustez la taille pour inclure les labels
        setVisible(true);
        updateBoardDisplay();
        highlightValidMoves(); // Mettre en surbrillance les coups valides initialement
    }

    // Met à jour l'affichage du plateau de jeu
    private void updateBoardDisplay() {
    	char[][] grid = game.getBoard().getGrid();
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                char token = grid[i][j];
                buttons[i][j].removeAll(); // Supprimer tout contenu existant
                if (token == 'b') {
                    buttons[i][j].add(new OthelloPiece(Color.BLACK)); // Ajouter un pion noir
                } else if (token == 'w') {
                    buttons[i][j].add(new OthelloPiece(Color.WHITE)); // Ajouter un pion blanc
                }
                buttons[i][j].revalidate(); // Mettre à jour l'affichage du bouton
            }
        }
        whiteCountLabel.setText("White Count: " + game.getBoard().countWhite());
        blackCountLabel.setText("Black Count: " + game.getBoard().countBlack());
    }

    // Met en surbrillance les coups valides pour le joueur actuel
    private void highlightValidMoves() {
        OthelloPlayer currentPlayer = game.getCurrentPlayer();
        char[][] grid = game.getBoard().getGrid();
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (game.getBoard().isValidMove(i, j, currentPlayer)) {
                    buttons[i][j].setBackground(Color.GRAY); // Mettre en surbrillance en vert
                }
            }
        }
    }

    // Gestionnaire d'événements pour les clics sur les boutons
    private class ButtonClickListener implements ActionListener {
        private int row;
        private int col;

        public ButtonClickListener(int row, int col) {
            this.row = row;
            this.col = col;
        }
        @Override
       public void actionPerformed(ActionEvent e) {
            OthelloPlayer currentPlayer = game.getCurrentPlayer();
            if (game.getBoard().isValidMove(row, col, currentPlayer)) {
                game.getBoard().makeMove(row, col, currentPlayer);
                updateBoardDisplay();
                resetButtonColors(); // Réinitialiser les couleurs des boutons
                if (game.isGameOver()) {
                    JOptionPane.showMessageDialog(null, "Game Over!!");
                } else {
                    // Changer de joueur après chaque coup
                    game.setCurrentPlayer((currentPlayer == game.getPlayer1()) ? game.getPlayer2() : game.getPlayer1());
                    highlightValidMoves();
                    OthelloPlayer nextPlayer=game.getCurrentPlayer();
                    if(game.getBoard().getValidMoves(nextPlayer).isEmpty()) {
                    	 game.setCurrentPlayer((nextPlayer == game.getPlayer1()) ? game.getPlayer2() : game.getPlayer1());
                         highlightValidMoves(); // Mettre en surbrillance les coups valides pour le nouveau joueur
                    }
                }
            } else {
                JOptionPane.showMessageDialog(null, "Invalid move. Try again.");
            }
        }
    }

    // Réinitialiser les couleurs des boutons
    private void resetButtonColors() {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                buttons[i][j].setBackground(null); // Réinitialiser la couleur à la couleur par défaut
            }
        }
    }
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new OthelloGUI());
    }
}
