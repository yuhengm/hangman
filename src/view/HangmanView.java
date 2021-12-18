package view;

import controller.HangmanController;

/**
 * Interface for the view of hangman game.
 */
public interface HangmanView {
    /**
     * Initialize the panel and call controller.
     * @param controller controller of hangman game.
     */
    void start(HangmanController controller);

    /**
     * Display the game progress to user (called by controller):
     * 1. Whether the player wins or not.
     * 2. Remaining guess allowance.
     * @param progress progress string to be displayed.
     */
    void displayProgress(String progress);

    /**
     * Display the revealed word according to what has been guessed (called by controller):.
     * @param revealedWord revealed word during the game.
     */
    void revealWord(String revealedWord);

    /**
     * Draw current state of hangman depending on the progress of the game (called by controller):.
     * @param numGuessesLeft number of guesses remaining for guess.
     * @param wins whether the player wins or not.
     */
    void drawHangman(int numGuessesLeft, boolean wins);

    /**
     * Disable player actions when the game is over.
     */
    void disablePlayerAction();

    /**
     * Restart the game when the restart button is clicked (called by controller):.
     */
    void restart();
}
