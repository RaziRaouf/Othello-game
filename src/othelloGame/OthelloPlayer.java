package othelloGame;

public class OthelloPlayer {
private char color;
private AIPlayer aiPlayer;
public OthelloPlayer(char color) {
	this.color=color;
}
public OthelloPlayer(char color, AIPlayer aiPlayer) {
    this.color = color;
    this.aiPlayer = aiPlayer;
}
public void setColor(char color) throws Exception{
	if(color=='w'|| color=='b') {
		this.color=color;
	}
	else {
		throw new Exception("invalid color");
	}
}

public char getColor() {
	return color;
}
public AIPlayer getAIPlayer() {
    return aiPlayer;
}

}
