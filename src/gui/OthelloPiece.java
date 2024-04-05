package gui;
import javax.swing.*;
import java.awt.*;


public class OthelloPiece extends JPanel{
	 private Color color;

	    public OthelloPiece(Color color) {
	        this.color = color;
	        setPreferredSize(new Dimension(50, 50)); // Taille du cercle
	    }

	    protected void paintComponent(Graphics g) {
	        super.paintComponent(g);
	        Graphics2D g2d = (Graphics2D) g;
	        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
	        g2d.setColor(color);
	        g2d.fillOval(0, 0, getWidth(), getHeight()); // Dessine un cercle
	    }

	    public static void main(String[] args) {
	        JFrame frame = new JFrame("Othello Pieces");
	        frame.setLayout(new FlowLayout());

	        // Création de pièces blanches et noires
	        OthelloPiece whitePiece = new OthelloPiece(Color.WHITE);
	        OthelloPiece blackPiece = new OthelloPiece(Color.BLACK);

	        // Ajout des pièces au cadre
	        frame.add(whitePiece);
	        frame.add(blackPiece);

	        // Configuration du cadre
	        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	        frame.pack();
	        frame.setVisible(true);
	    }
}
