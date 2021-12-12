import controller.HangmanController;
import controller.HangmanControllerImpl;
import model.HangmanModel;
import model.HangmanModelImpl;
import view.HangmanViewImpl;

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
