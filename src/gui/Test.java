package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import othelloGame.*;
import AI.*;
public class Test extends JFrame {
    private OthelloGame game;
    private JButton[][] buttons;
    private JLabel whiteCountLabel;
    private JLabel blackCountLabel;
    private FacileIA hardAi;
    public Test() {
        super("Othello");
        game = new OthelloGame();
        buttons = new JButton[8][8];
        whiteCountLabel = new JLabel("White Count: ");
        blackCountLabel = new JLabel("Black Count: ");
        hardAi=new FacileIA();
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
    }

    // Met à jour l'affichage du plateau de jeu
    private void updateBoardDisplay() {
        char[][] grid = game.getBoard().getGrid();
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                char token = grid[i][j];
                buttons[i][j].setText(String.valueOf(token));
            }
        }
     // JPanel countPanel = new JPanel();
        whiteCountLabel.setText("White Count: " + game.getBoard().countWhite());
        blackCountLabel.setText("Black Count: " + game.getBoard().countBlack());
    }

   
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
            
            // Vérifier si le joueur actuel a des coups valides
            if (game.getBoard().getValidMoves(currentPlayer).isEmpty()) {
                // Passer au tour de l'IA
                OthelloPlayer opponent = (currentPlayer == game.getPlayer1()) ? game.getPlayer2() : game.getPlayer1();
                int[] aiMove = hardAi.getBestMove(game.getBoard(), game, opponent, 8);
                game.getBoard().makeMove(aiMove[0], aiMove[1], opponent);
                updateBoardDisplay(); // Mettre à jour l'affichage du plateau après le mouvement de l'IA

                // Vérifier si le jeu est terminé après le tour de l'IA
                if (game.isGameOver()) {
                    // Afficher un message de fin de jeu et terminer le jeu
                    JOptionPane.showMessageDialog(null, "Game Over!");
                    return;
                }
                
                // Mettre à jour le currentPlayer après le tour de l'IA
                currentPlayer = opponent;
            } else {
                // Si le joueur a des coups valides, procéder normalement au tour du joueur
                if (game.getBoard().isValidMove(row, col, currentPlayer)) {
                    game.getBoard().makeMove(row, col, currentPlayer);
                    updateBoardDisplay(); // Mettre à jour l'affichage du plateau après le mouvement du joueur

                    // Vérifier si le jeu est terminé
                    if (game.isGameOver()) {
                        // Afficher un message de fin de jeu et terminer le jeu
                        JOptionPane.showMessageDialog(null, "Game Over!");
                        return;
                    }

                    // Passer au tour de l'IA
                    OthelloPlayer opponent = (currentPlayer == game.getPlayer1()) ? game.getPlayer2() : game.getPlayer1();
                    int[] aiMove = hardAi.getBestMove(game.getBoard(), game, opponent, 8);
                    game.getBoard().makeMove(aiMove[0], aiMove[1], opponent);
                    updateBoardDisplay(); // Mettre à jour l'affichage du plateau après le mouvement de l'IA

                    // Vérifier si le jeu est terminé après le tour de l'IA
                    if (game.isGameOver()) {
                        // Afficher un message de fin de jeu et terminer le jeu
                        JOptionPane.showMessageDialog(null, "Game Over!");
                    }
                } else {
                    // Afficher un message d'erreur si le mouvement du joueur n'est pas valide
                    JOptionPane.showMessageDialog(null, "Invalid move. Try again.");
                }
            }
        }

    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(Test::new);
    }
}
