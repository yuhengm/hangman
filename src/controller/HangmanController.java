package controller;

/**
 * Interface for the controller of hangman game.
 */
public interface HangmanController {

    /**
     * Start the game and show game GUI.
     */
    void start();

    /**
     * Play the game with hangman model:
     * Given a letter from view and pass to the model.
     */
    void guessChar(char c);

    /**
     * Play the game with hangman model:
     * Given a word from view and pass to the model.
     * @param s
     */
    void guessWord(String s);

    /**
     * Controller calls view to update view after the player makes a guess.
     */
    void updateView();

    /**
     * Restart the view if instructed.
     */
    void restart();
}
