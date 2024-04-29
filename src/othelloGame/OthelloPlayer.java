package othelloGame;

/**
 * Represents a player in the Othello game.
 */
public class OthelloPlayer {
	 /**
     * Constructs an Othello player with the specified color.
     *
     * @param color The color of the player ('w' for white, 'b' for black).
     */
private char color;
/**
 * Sets the color of the player.
 *
 * @param color The color to set for the player ('w' for white, 'b' for black).
 * @throws Exception If the color is invalid.
 */
public OthelloPlayer(char color) {
	this.color=color;
}
/**
 * Sets the color of the player.
 *
 * @param color The color to set for the player ('w' for white, 'b' for black).
 * @throws Exception If the color is invalid.
 */
public void setColor(char color) throws Exception{
	if(color=='w'|| color=='b') {
		this.color=color;
	}
	else {
		throw new Exception("invalid color");
	}
}
/**
 * Retrieves the color of the player.
 *
 * @return The color of the player ('w' for white, 'b' for black).
 */
public char getColor() {
	return color;
}

}
