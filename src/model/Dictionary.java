package model;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * This class turns a dictionary txt file into an array list of words.
 */
public class Dictionary {
    private ArrayList<String> dictArray;

    /**
     * Constructs a dictionary object.
     * Note that this constructor only considers the case in which words in dictionary are separated
     * by new line characters.
     * @throws IllegalArgumentException if no txt file exists for analysis.
     */
    public Dictionary(String filename) throws IllegalArgumentException {
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream(filename);
        if (inputStream == null) {
            throw new IllegalArgumentException("The file path does not exist!");
        }

        try {
            ArrayList<String> array = new ArrayList<>();
            Scanner scanner = new Scanner(inputStream).useDelimiter("\n");
            while (scanner.hasNext()) {
                array.add(scanner.next());
            }
            this.dictArray = array;
        } catch (Exception e) {
            e.printStackTrace();
            throw new IllegalArgumentException("IllegalArgumentException thrown.");
        }
    }

    /**
     * Gets the array of dictionary words.
     * @return a copy of the original array of dictionary words to prevent external mutation.
     */
    public ArrayList<String> getDictArray() {
        ArrayList<String> copyArray = new ArrayList<>(dictArray);
        return copyArray;
    }
}
