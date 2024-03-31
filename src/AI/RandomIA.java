package AI;
import java.util.List;
import java.util.Random;
public class RandomIA {
	public static int[] getRandomMove(List<int[]> validMoves) {
        Random random = new Random();
        int index = random.nextInt(validMoves.size());
        return validMoves.get(index);
    }
}
