public class Flashcard {
    private String word;
    private String translation;

    /**
     * @author Mai
     * @param word
     * @param translation
     */

    public Flashcard(String word, String translation) {
        this.word = word;
        this.translation = translation;
    }

    public String getWord() {
        return word;
    }

    public String getTranslation() {
        return translation;
    }
}