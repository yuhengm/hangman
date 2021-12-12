package model;
/**
 * Interface for the model of hangman game.
 */
public interface HangmanModel {
    /**
     * A player guesses a character letter and some messages will be displayed.
     * The following information in the model will be updated:
     * 1. Number of guesses left.
     * 2. Number of letters left.
     * 3. Word revealed to the player.
     * 4. Letters that have been guessed.
     *
     * @param c character guessed by the player.
     * @return a message depending on the circumstances:
     *         1. If the letter the player guessed is in the secret word,
     *            "You got it!" will be displayed.
     *         2. If the letter the player guessed is not in the secret word,
     *            "Sorry, wrong guess." will be displayed.
     *         3. If the letter the player guessed has been guessed before,
     *            "You have made the guess." will be displayed.
     * @throws IllegalArgumentException if c is not among [A-Za-z].
     */
    String guessByCharacter(char c) throws IllegalArgumentException;

    /**
     * A player guesses a word and messages will he displayed:
     *      1. "You got it!" if guessed correctly.
     *      2. "Sorry, wrong guess." if guessed wrong.
     * The following information int eh model will be updated:
     * 1. Number of guesses left.
     * 2. Number of letters left.
     * 3. Word revealed to the player.
     * 4. Letters that have been guessed.
     * @param s word input by player.
     * @return a message depending on circumstances.
     */
    String guessByWord(String s);

    /**
     * Determine whether the game is over or not.
     * @return true if the player wins or there is no more guesses left.
     */
    boolean isGameOver();

    /**
     * Determine whether the player wins.
     * @return true if the player has guessed the answer before or at the moment when guess allowance ends.
     */
    boolean wins();

    /**
     * Get number of guesses left.
     * @return the number of guesses left.
     */
    int getNumGuessesLeft();

    /**
     * Get number of letters left.
     * @return the number of letters left.
     */
    int getNumLettersLeft();

    /**
     * Get the word revealed to the player.
     * @return a word string that gets revealed to the player.
     */
    String getRevealedWord();

    /**
     * Get letters that have been guessed.
     * @return a String of letters that has been guessed.
     */
    String getGuessedLetter();

    /**
     * Get secret word.
     * @return a word string representing the secret word.
     */
    String getSecretWord();

    /**
     * Initialize the model.
     */
    void initialize();
}
