import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.Before;
import model.*;

/**
 * JUnit test for hangman model.
 */
public class HangmanModelImplTest {
    private HangmanModel h;

    @Before
    public void setUp() {
        h = new HangmanModelImpl("hello");
    }

    /**
     * Tests whether input dictionary.txt has been successfully converted to an array list.
     */
    @Test
    public void testDictToList() {
        Dictionary dict = new Dictionary("dictionary.txt");
        for (String s : dict.getDictArray()) {
            System.out.println(s);
        }
        assertEquals(50, dict.getDictArray().size());
    }

    /**
     * Tests whether IllegalArgumentException can be thrown properly.
     */
    @Test (expected = IllegalArgumentException.class)
    public void testFailingDict() {
        Dictionary dict = new Dictionary("dictionary");
    }

    /**
     * Test getter methods.
     */
    @Test
    public void testConstructor() {
        assertTrue(h.getSecretWord().equals("HELLO"));
        assertTrue(h.getRevealedWord().equals("_ _ _ _ _"));
        assertTrue(h.getGuessedLetter().equals(""));
        assertEquals(8, h.getNumGuessesLeft());
        assertEquals(5, h.getNumLettersLeft());
    }

    /**
     * Test correct guess made by the player.
     */
    @Test
    public void testCorrectGuess() {
        String msg = h.guessByCharacter('H');
        assertTrue(msg.equals("You got it!"));
        assertTrue(h.getRevealedWord().equals("H _ _ _ _"));
        assertTrue(h.getGuessedLetter().equals("H"));
        assertEquals(8, h.getNumGuessesLeft());
        assertEquals(4, h.getNumLettersLeft());
        assertFalse(h.wins());
        assertFalse(h.isGameOver());
    }

    /**
     * Test wrong guess made by the player.
     */
    @Test
    public void testWrongGuess() {
        h.guessByCharacter('H');
        String msg = h.guessByCharacter('a');
        assertTrue(msg.equals("Sorry, wrong guess."));
        assertTrue(h.getRevealedWord().equals("H _ _ _ _"));
        assertTrue(h.getGuessedLetter().contains("A"));
        assertTrue(h.getGuessedLetter().contains("H"));
        assertEquals(7, h.getNumGuessesLeft());
        assertEquals(4, h.getNumLettersLeft());
        assertFalse(h.wins());
        assertFalse(h.isGameOver());
    }

    /**
     * Test illegal guess made by the player.
     */
    @Test (expected = IllegalArgumentException.class)
    public void testIllegalGuess() {
        h.guessByCharacter('-');
    }

    /**
     * Player wins.
     */
    @Test
    public void testWinGame() {
        h.guessByCharacter('h');
        assertTrue(h.getRevealedWord().equals("H _ _ _ _"));
        h.guessByCharacter('e');
        assertTrue(h.getRevealedWord().equals("H E _ _ _"));
        h.guessByCharacter('l');
        assertTrue(h.getRevealedWord().equals("H E L L _"));
        String msg = h.guessByCharacter('l');
        assertTrue(msg.equals("You have made the guess."));
        h.guessByWord("Hello");
        assertTrue(h.getRevealedWord().equals("H E L L O"));
        assertTrue(h.wins());
        assertTrue(h.isGameOver());
    }

    /**
     * Player loses by only guessing characters.
     */
    @Test
    public void testLostGame1() {
        h.guessByCharacter('h');
        assertTrue(h.getRevealedWord().equals("H _ _ _ _"));
        h.guessByCharacter('e');
        assertTrue(h.getRevealedWord().equals("H E _ _ _"));
        h.guessByCharacter('l');
        assertTrue(h.getRevealedWord().equals("H E L L _"));
        String msg = h.guessByCharacter('l');
        assertTrue(msg.equals("You have made the guess."));
        h.guessByCharacter('a');
        h.guessByCharacter('b');
        h.guessByCharacter('i');
        h.guessByCharacter('z');
        h.guessByCharacter('v');
        h.guessByCharacter('y');
        h.guessByCharacter('c');
        h.guessByCharacter('d');
        assertTrue(h.getRevealedWord().equals("H E L L _"));
        assertFalse(h.wins());
        assertTrue(h.isGameOver());
    }

    /**
     * Player loses by guessing the wrong word.
     */
    @Test
    public void testLostGame2() {
        h.guessByCharacter('h');
        assertTrue(h.getRevealedWord().equals("H _ _ _ _"));
        h.guessByWord("ello");
        assertTrue(h.getRevealedWord().equals("_ _ _ _ _"));
        assertFalse(h.wins());
        assertTrue(h.isGameOver());
    }
}