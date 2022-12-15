import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WordList {
    private String name;
    private String category;
    private Map <String, String> translationMap;

    /**
     *
     * @param name
     * @param category
     */

    public WordList(String name, String category) {
        this.name = name;
        this.category = category;
        this.translationMap = new HashMap<>();
    }

    public void addTranslation(String word, String translation) {
        translationMap.put(word, translation);
    }

    public List <Flashcard> getFlashcards() {
        List <Flashcard> flashcards = new ArrayList<>();
        for (Map.Entry<String, String> entry : translationMap.entrySet()) {
            flashcards.add(new Flashcard(entry.getKey(), entry.getValue()));
        }
        return flashcards;
    }

    public String getName() {
        return name;
    };
    public String getCategory() {
        return category;
    }
    public Map <String, String> getTranslationMap(){ return translationMap;}

}


