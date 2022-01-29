import java.io.IOException;
import java.util.ArrayList;

public class WordleBoard {
    private int rowCount;
    private int colCount;
    private ArrayList<String> guesses = new ArrayList<String>();
    private String answer;
    private LangDict wordBank;

    // INIT OVERLOADS
    public WordleBoard(String filePath, int size, int wordLength) throws IOException {
        this.rowCount = size;
        this.colCount = wordLength;
        this.wordBank = new LangDict(filePath, wordLength);
        this.answer = wordBank.randomWord();
    }

    public WordleBoard(String filePath, int size) throws IOException {
        this.rowCount = size;
        this.colCount = 5;
        this.wordBank = new LangDict(filePath, 5);
        this.answer = wordBank.randomWord();
    }

    public WordleBoard(String filePath) throws IOException {
        this.rowCount = 6;
        this.colCount = 5;
        this.wordBank = new LangDict(filePath, 5);
        this.answer = wordBank.randomWord();
    }

    // GETTERS
    public int height() {
        return rowCount;
    }

    public int width() {
        return colCount;
    }

    public int countGuesses() {
        return guesses.size();
    }

    public final String nthGuess(int n) {
        return guesses.get(n);
    }

    public final String getAnswer() {
        return answer;
    }
    
    public boolean isGameOver() {
        return didWin() || countGuesses() == height();
    }

    public boolean didWin() {
        return guesses.contains(getAnswer());
    }
    
    // MUTATOR
    public void guess(String str) {
        if (str.length() == width() && wordBank.contains(str)) {
            guesses.add(str);
        }
    }
}


