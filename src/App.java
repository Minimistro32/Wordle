import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;

public class App {
    private final static boolean DEBUG_MODE = false; // This DEBUG_MODE will display the answer for testing purposes.
    private final static String HISTORY_PATH = "history.txt"; // This contains the path to the history text file.

    public static void main(String[] args) throws Exception {
        // initalize the WordleBoard and Scanner for user input.
        WordleBoard board = new WordleBoard("words.txt", 6);
        Scanner input = new Scanner(System.in);

        while (!board.isGameOver()) {
            Display.clear();
            Display.print(board);

            if (DEBUG_MODE) {
                Display.printAnswer(board.getAnswer());
            }

            Display.promptForWord();
            board.guess(input.nextLine().toLowerCase());
        }

        Display.clear();
        Display.print(board);
        Display.printAnswer(board.getAnswer());
        input.close();
        recordGame(board);
    }

    // ARGS: (WordleBoard) Takes a board to record it's game.
    // The games played counter at the HISTORY_PATH is incremented. If the board was
    // completed successfully that is also recorded along with the winning word.
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

        Files.writeString(Path.of(HISTORY_PATH),
                String.format("GamesPlayed: %d\nGamesWon: %d\n%s", numGamesPlayed, numGamesWon, winningWords));
    }
}
