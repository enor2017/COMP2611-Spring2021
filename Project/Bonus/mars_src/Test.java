import mars.ext.game.GameScreen;
import mars.util.SystemIO;

public class Test {
    public static void main(String[] args) {
        try {
            GameScreen.createIntance("/game/properties.txt");
            GameScreen gameScreen = GameScreen.getInstance();
            gameScreen.playSound(0, false);
        }
        catch (Exception exception) {
            SystemIO.printString("In Creating Game, internal error has happened. Try restarting the MARS!");
        }
    }
}
