import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class LangDict {
    private List<String> words;

    LangDict(String filePath) throws IOException {
        this.words = Files.readAllLines(Paths.get(filePath)).stream()
                .filter(word -> !(word.contains(".") || word.contains("-") || word.contains(",") || word.contains("'")))
                .map(word -> word.toLowerCase())
                .collect(Collectors.toList());
    }

    LangDict(String filePath, int wordLength) throws IOException {
        this.words = Files.readAllLines(Paths.get(filePath)).stream()
                .filter(word -> word.length() == wordLength && !(word.contains(".") || word.contains("-") || word.contains(",") || word.contains("'")))
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
