import controller.*;
import model.*;
import view.*;

/**
 * Driver class for hangman game.
 */
public class HangmanDriver {

    /**
     * Main method to run the hangman game.
     * @param args default argument.
     */
    public static void main(String[] args) {
        HangmanModel model = new HangmanModelImpl();
        HangmanViewImpl view = new HangmanViewImpl();
        HangmanController controller = new HangmanControllerImpl(model, view);
        controller.start();
    }
}
