import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

// Wrapper class to initialize a list of words from a txt.
public class LangDict {
    private List<String> words;

    // INIT OVERLOADS
    LangDict(String filePath) throws IOException {
        this.words = Files.readAllLines(Paths.get(filePath)).stream()
                .filter(word -> !(word.contains(".") || word.contains("-") || word.contains(",") || word.contains("'")))
                .map(word -> word.toLowerCase())
                .collect(Collectors.toList());
    }

    // WordLength (int) will filter out words of any other length.
    LangDict(String filePath, int wordLength) throws IOException {
        this.words = Files.readAllLines(Paths.get(filePath)).stream()
                .filter(word -> word.length() == wordLength
                        && !(word.contains(".") || word.contains("-") || word.contains(",") || word.contains("'")))
                .map(word -> word.toLowerCase())
                .collect(Collectors.toList());
    }

    public boolean contains(String word) {
        return words.contains(word);
    }

    public String randomWord() {
        return words.get(new Random().nextInt(words.size()));
    }
}
