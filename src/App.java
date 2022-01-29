import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;

public class App {
    private final static boolean DEBUG_MODE = false;
    private final static String HISTORY_PATH = "history.txt";

    public static void main(String[] args) throws Exception {
        WordleBoard board = new WordleBoard("words.txt", 6);
        Scanner input = new Scanner(System.in);
        
        while (!board.isGameOver()) {
            Display.clear();            
            Display.print(board);

            if (DEBUG_MODE) { Display.printAnswer(board.getAnswer()); }
            
            Display.promptForWord();
            board.guess(input.nextLine().toLowerCase());
        }

        Display.clear();
        Display.print(board);
        Display.printAnswer(board.getAnswer());
        input.close();
        recordGame(board);
    }

    public static void recordGame(WordleBoard board) throws Exception {
        Scanner reader = new Scanner(new File(HISTORY_PATH));
        String gamesPlayed = reader.nextLine();
        String gamesWon = reader.nextLine();
        String winningWords = "";
        while (reader.hasNextLine()) {
            winningWords += reader.nextLine() + '\n';
        }
        reader.close();

        int numGamesPlayed = Integer.parseInt(gamesPlayed.substring(gamesPlayed.indexOf(": ") + 2).trim()) + 1;
        int numGamesWon = Integer.parseInt(gamesWon.substring(gamesWon.indexOf(": ") + 2).trim());

        if (board.didWin()) {
            numGamesWon += 1;
            winningWords += board.getAnswer();
        }

        Files.writeString(Path.of(HISTORY_PATH), String.format("GamesPlayed: %d\nGamesWon: %d\n%s", numGamesPlayed, numGamesWon, winningWords));
    }
}
 