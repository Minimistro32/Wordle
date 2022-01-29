public class Display {
    private static String title = "===========\nW O R D L E\n===========\n";
    private static String leftPadding = " ";

    private enum Color {
        BLACK("\033[0;100m"), // , "\u001B[30m"),
        RED("\033[0;101m"), // , "\u001B[31m"),
        GREEN("\033[0;102m"), // , "\u001B[32m"),
        YELLOW("\033[0;103m"), // , "\u001B[33m"),
        BLUE("\033[0;104m"), // , "\u001B[34m"),
        PURPLE("\033[0;105m"), // , "\u001B[35m"),
        CYAN("\033[0;106m"), // , "\u001B[36m"),
        WHITE("\033[0;107m");// , "\u001B[37m");

        private final String ansi_background;
        private final static String ANSI_RESET = "\u001B[0m";

        private Color(String ansi_background) {
            this.ansi_background = ansi_background;
        }
    }

    // SETTERS
    public static void setTitle(String str) {
        title = str;
    }

    public static void setLeftPadding(String str) {
        leftPadding = str;
    }

    // PUBLIC METHODS
    public static void print(WordleBoard board) {
        System.out.println(title);
        for (int i = 0; i < board.height(); i++) {
            if (i < board.countGuesses()) {
                Display.printComparison(board.nthGuess(i), board.getAnswer(), "|");
            } else {
                paddedPrint(" |".repeat(board.width() - 1));
            }

            if (i != board.height() - 1) {
                paddedPrint("-".repeat(board.width() * 2 - 1));
            } else {
                paddedPrint("");
            }
        }
    }

    public static void promptForWord() {
        System.out.print("Enter a word:\n ");
    }

    public static void printAnswer(String str) {
        System.out.println("The answer is:\n " + str);
    }

    public static void clear() {
        System.out.print("\033\143");
        System.out.flush();
    }

    // PRINT HELPERS
    private static void printComparison(String toPrint, String forComparison, String delimeter) {
        String output = "";
        for (int n = 0; n < toPrint.length(); n++) {
            char nthChar = toPrint.charAt(n);
            if (n < forComparison.length() && nthChar == forComparison.charAt(n)) {
                output += highlightText(Character.toString(nthChar), Color.GREEN);
            } else if (forComparison.contains(String.valueOf(nthChar))) {
                output += highlightText(Character.toString(nthChar), Color.YELLOW);
            } else {
                output += nthChar;
            }

            if (n != toPrint.length() - 1) {
                output += delimeter;
            }
        }

        paddedPrint(output);
    }

    private static void paddedPrint(String str) {
        System.out.println(leftPadding + str);
    }

    // ANSI TEXT COLORING
    private static String highlightText(String text, Color forHighlight) {
        return forHighlight.ansi_background + text + Color.ANSI_RESET;
    }
}
