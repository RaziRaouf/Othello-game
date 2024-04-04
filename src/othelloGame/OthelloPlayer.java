package othelloGame;

public class OthelloPlayer {
private char color;
public OthelloPlayer(char color) {
	this.color=color;
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

}
