package view;

import controller.*;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * This class implements HangmanView interface and realizes functions in the interface.
 */
public class HangmanViewImpl extends JFrame implements HangmanView {
    // Panels
    private JPanel mainPanel;
    private JPanel titlePanel;
    private JPanel imagePanel;
    private JPanel resultPanel;
    private JPanel askWordPanel;
    private JPanel listCharPanel;
    private JPanel resetPanel;

    // Labels to add images and mutable strings
    private JLabel titleLabel; // can be changed to HANGMAN with snow covered on it
    private JLabel imageLabel;
    private JLabel progressLabel;
    private JLabel revealedWord;
    private JLabel askForWord;
    private JLabel askForChar;

    // guessWord input
    private JTextField wordInput;
    private JButton submitButton;

    // guessChar input
    private JButton aButton;
    private JButton bButton;
    private JButton cButton;
    private JButton dButton;
    private JButton eButton;
    private JButton fButton;
    private JButton gButton;
    private JButton hButton;
    private JButton iButton;
    private JButton jButton;
    private JButton kButton;
    private JButton lButton;
    private JButton mButton;
    private JButton nButton;
    private JButton oButton;
    private JButton pButton;
    private JButton qButton;
    private JButton rButton;
    private JButton sButton;
    private JButton tButton;
    private JButton uButton;
    private JButton vButton;
    private JButton wButton;
    private JButton xButton;
    private JButton yButton;
    private JButton zButton;
    private ArrayList<JButton> buttonArrayList;

    // reset game
    private JButton resetButton;

    // controller
    private HangmanController controller;

    /**
     * Constructs an empty window with a title "HANGMAN by Yuheng".
     */
    public HangmanViewImpl() {
        super("HANGMAN by Yuheng");
    }

    @Override
    public void start(HangmanController controller) {
        this.controller = controller;
        initialize();
    }

    /**
     * Helper method to initialize the game window.
     */
    private void initialize() {
        // main panel
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setPreferredSize(new Dimension(1000, 1000));
        this.setContentPane(mainPanel);
        mainPanel.setBorder(new EmptyBorder(25, 25, 25, 25));
        mainPanel.setBackground(Color.decode("#E7DEC8"));

        // title panel: top most panel
        titlePanel.setPreferredSize(new Dimension(950, 50));
        titlePanel.setBackground(Color.decode("#E7DEC8"));
        titleLabel.setText("Hangman");
        titleLabel.setForeground(Color.decode("#064420"));
        titleLabel.setFont(new Font("Rockwell", Font.BOLD, 40));

        // reset panel: just below title panel
        resetPanel.setPreferredSize(new Dimension(600, 50));
        resetPanel.setBackground(Color.decode("#E7DEC8"));
        resetButton.setOpaque(true);
        resetButton.setBorderPainted(false);
        resetButton.setBackground(Color.WHITE);
        resetButton.setForeground(Color.decode("#865858"));
        resetButton.setToolTipText("Click to reset the game");
        resetButton.setFont(new Font("Rockwell", Font.BOLD, 15));

        // image panel: to the left
        imagePanel.setPreferredSize(new Dimension(550, 850));
        imagePanel.setBorder(new EmptyBorder(0, 25, 0, 0));
        imagePanel.setBackground(Color.decode("#E7DEC8"));
        imageLabel.setIcon(
                new ImageIcon(
                        loadImage(8, false).
                                getScaledInstance(500, 800, Image.SCALE_DEFAULT)
                )
        );

        // reveal word panel: top right panel
        resultPanel.setPreferredSize(new Dimension(400, 100));
        resultPanel.setBorder(new EmptyBorder(25, 25, 25, 25));
        resultPanel.setBackground(Color.decode("#E4EFE7"));
        progressLabel.setFont(new Font("Rockwell", Font.BOLD, 20));
        revealedWord.setFont(new Font("Rockwell", Font.BOLD, 20));

        // text entry panel: middle right panel
        askWordPanel.setPreferredSize(new Dimension(400, 50));
        askWordPanel.setBorder(new EmptyBorder(25, 25, 25, 25));
        askWordPanel.setBackground(Color.decode("#FBF7F0"));
        askForWord.setFont(new Font("Rockwell", Font.BOLD, 15));
        wordInput.setPreferredSize(new Dimension(300, 30));
        wordInput.setBackground(Color.WHITE);
        wordInput.setOpaque(true);
        submitButton.setOpaque(true);
        submitButton.setBorderPainted(false);
        submitButton.setBackground(Color.WHITE);
        submitButton.setToolTipText("Click to submit your answer");

        // char selection panel: bottom right panel
        listCharPanel.setPreferredSize(new Dimension(400, 750));
        listCharPanel.setBorder(new EmptyBorder(25, 25, 25, 25));
        listCharPanel.setBackground(Color.decode("#FBF7F0"));
        askForChar.setFont(new Font("Rockwell", Font.BOLD, 15));
        buttonArrayList = new ArrayList<>(Arrays.asList(aButton, bButton, cButton, dButton, eButton,
                fButton, gButton, hButton, iButton, jButton, kButton, lButton, mButton, nButton, oButton, pButton,
                qButton, rButton, sButton, tButton, uButton, vButton, wButton, xButton, yButton, zButton));
        for (JButton btn : buttonArrayList) {
            btn.setOpaque(true);
            btn.setBorderPainted(false);
            btn.setBackground(Color.WHITE);
            btn.setToolTipText("Click to guess " + btn.getText());
        }
        this.pack();
        this.addListeners();
        controller.updateView();
        this.setVisible(true);
    }

    /**
     * Helper method to add listener so that player can input required information.
     */
    private void addListeners() {
        submitButton.addActionListener(l -> this.clickSubmitButton());
        resetButton.addActionListener(l -> this.clickResetButton());
        for (JButton btn : buttonArrayList) {
            btn.addActionListener(l -> this.clickCharButton(btn));
        }
    }

    /**
     * Helper method to set listener action to reset button.
     */
    private void clickResetButton() {
        controller.restart();
    }

    /**
     * Helper method to set listener action to submit button.
     */
    private void clickSubmitButton() {
        String word = wordInput.getText().replace(" ", "").toUpperCase();
        if (word == null || word.length() == 0) {
            return;
        }
        submitButton.setEnabled(false);
        controller.guessWord(word);
    }

    /**
     * Helper method to set listener action to character buttons.
     *
     * @param button specified character button.
     */
    private void clickCharButton(JButton button) {
        char character = button.getText().toUpperCase().charAt(0);
        button.setEnabled(false);
        controller.guessChar(character);
    }

    @Override
    public void displayProgress(String progress) {
        progressLabel.setText(progress);
    }

    @Override
    public void revealWord(String word) {
        revealedWord.setText(word);
    }

    @Override
    public void drawHangman(int numGuessesLeft, boolean wins) {
        Image image = loadImage(numGuessesLeft, wins);
        if (image == null) {
            return;
        }
        image.getScaledInstance(500, 800, Image.SCALE_DEFAULT);
        imageLabel.setIcon(new ImageIcon(image));
        imagePanel.repaint();
    }

    /**
     * Helper method to load image from the resource folder according player's progress.
     *
     * @param numGuessesLeft number of guesses left.
     * @param wins           whether the player wins or not.
     * @return an Image object.
     */
    private Image loadImage(int numGuessesLeft, boolean wins) {
        try {
            InputStream imageStream = null;
            if (wins) {
                imageStream = getClass().getClassLoader().getResourceAsStream("wins.png");
            } else {
                imageStream = getClass().getClassLoader().getResourceAsStream("img" + numGuessesLeft + ".png");
            }
            return ImageIO.read(imageStream);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void disablePlayerAction() {
        submitButton.setEnabled(false);
        for (JButton btn : buttonArrayList) {
            btn.setEnabled(false);
        }
    }

    @Override
    public void restart() {
        submitButton.setEnabled(true);
        for (JButton btn : buttonArrayList) {
            btn.setEnabled(true);
            ;
        }
        controller.updateView();
    }
}
