package controller;

import model.HangmanModel;
import view.HangmanView;

/**
 * This class implements HangmanController interface and realizes functions in the interface.
 */
public class HangmanControllerImpl implements HangmanController {
    private HangmanModel model;
    private HangmanView view;

    /**
     * Construct a hangman controller.
     * @param model hangman model.
     * @param view hangman view.
     */
    public HangmanControllerImpl(HangmanModel model, HangmanView view) {
        this.model = model;
        this.view = view;
    }

    @Override
    public void start() {
        this.view.start(this);
    }

    @Override
    public void guessChar(char c) {
        if (model.isGameOver() || model.wins()) {
            return;
        }
        view.displayProgress(model.guessByCharacter(c));
        updateView();
    }

    @Override
    public void guessWord(String s) {
        if (model.isGameOver() || model.wins()) {
            return;
        }
        view.displayProgress(model.guessByWord(s));
        updateView();
    }

    @Override
    public void updateView() {
        view.revealWord(model.getRevealedWord());
        view.drawHangman(model.getNumGuessesLeft(), model.wins());
        if (model.wins() || model.isGameOver()) {
            view.displayProgress("ANSWER: " + model.getSecretWord());
            view.disablePlayerAction();
        }
    }

    @Override
    public void restart() {
        model.initialize();
        view.restart();
    }
}
