package model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;
import java.util.stream.Collectors;

/**
 * This class implements HangmanModel interface and realizes functions in the interface.
 */
public class HangmanModelImpl implements HangmanModel {
    private Dictionary dictArray;
    private String secretWord;
    private int numGuessesLeft;
    private int numLettersLeft;
    private char[] revealedWord;
    private HashSet<Character> guessedLetters;

    /**
     * Constructor that initialize the game to default values.
     */
    public HangmanModelImpl() throws IllegalArgumentException {
        this.dictArray = new Dictionary("dictionary.txt");
        initialize();
    }

    /**
     * Constructor that takes in a user-defined secret word.
     * This constructor is used for unit tests.
     * @param secretWord user-defined secret word to be guessed.
     */
    public HangmanModelImpl(String secretWord) {
        this.secretWord = secretWord.toUpperCase();
        this.numGuessesLeft = 8;
        this.numLettersLeft = secretWord.length();
        this.revealedWord = new char[secretWord.length()];
        this.guessedLetters = new HashSet<>();

        for (int i = 0; i<revealedWord.length; i++) {
            revealedWord[i] = '_';
        }
    }

    /**
     * Helper function to get a random word from the provided dictionary library.
     * @return a String that will serve as the secrete word to be guessed.
     * @throws IllegalArgumentException if the dictionary is empty.
     */
    private String randomWordGenerator() throws IllegalArgumentException {
        ArrayList<String> dictionary = dictArray.getDictArray();
        if (dictionary == null || dictionary.size() < 1) {
            throw new IllegalArgumentException("Empty dictionary.");
        }

        Random rand = new Random();
        int randInt = rand.nextInt(dictionary.size());
        return dictionary.get(randInt);
    }

    @Override
    public String guessByCharacter(char c) throws IllegalArgumentException {
        String input = String.valueOf(c).toUpperCase();
        if (!input.matches("[A-Z]")) {
            throw new IllegalArgumentException("Input character should be english letters.");
        }

        StringBuilder printMessage = new StringBuilder();

        if (getGuessedLetter().contains(input)) {
            printMessage.append("You have made the guess.");
        } else {
            if (!getSecretWord().contains(input)) {
                printMessage.append("Sorry, wrong guess.");
                this.numGuessesLeft--;
            } else {
                printMessage.append("You got it!");
                for (int i = 0; i < getSecretWord().length(); i++) {
                    if (getSecretWord().charAt(i) == c || getSecretWord().toLowerCase().charAt(i) == c) {
                        this.revealedWord[i] = c;
                        this.numLettersLeft--;
                    }
                }
            }
            this.guessedLetters.add(c);
        }

        return printMessage.toString();
    }

    @Override
    public String guessByWord(String s) {
        String guess = s.toUpperCase();
        StringBuilder printMessage = new StringBuilder();
        int i;
        if (guess.equals(getSecretWord())) {
            printMessage.append("You got it!");
            this.numLettersLeft = 0;
            for (i = 0; i < guess.length(); i++) {
                this.revealedWord[i] = getSecretWord().charAt(i);
            }
        } else {
            printMessage.append("Sorry, wrong guess.");
            for (i = 0; i < getSecretWord().length(); i++) {
                this.revealedWord[i] = '_';
            }
        }
        for (i = 0; i < guess.length(); i++) {
            this.guessedLetters.add(guess.charAt(i));
        }
        this.numGuessesLeft = 0;
        return printMessage.toString();
    }

    @Override
    public boolean isGameOver() {
        return getNumGuessesLeft() == 0 || wins();
    }

    @Override
    public boolean wins() {
        return getNumGuessesLeft() >= 0 && getNumLettersLeft() == 0;
    }

    @Override
    public void initialize() {
        this.secretWord = randomWordGenerator().toUpperCase();
        this.numGuessesLeft = 8;
        this.numLettersLeft = secretWord.length();
        this.revealedWord = new char[secretWord.length()];
        this.guessedLetters = new HashSet<>();

        for (int i = 0; i<revealedWord.length; i++) {
            revealedWord[i] = '_';
        }
    }

    // ====================================================================== Getters
    @Override
    public int getNumGuessesLeft() {
        return this.numGuessesLeft;
    }

    @Override
    public int getNumLettersLeft() {
        return this.numLettersLeft;
    }

    @Override
    public String getRevealedWord() {
        StringBuilder result = new StringBuilder();
        for (char item : revealedWord) {
            result.append(item).append(" ");
        }
        return result.toString().toUpperCase().trim();
    }

    @Override
    public String getGuessedLetter() {
        return this.guessedLetters.stream().map(c -> String.valueOf(c).toUpperCase()).collect(Collectors.joining(" "));
    }

    @Override
    public String getSecretWord() {
        return this.secretWord;
    }

}
