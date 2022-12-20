import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.List;




public class FlashcardTest {
    @Test
    public void testGetFlashcards() {
        WordList list = new WordList("Colors", "Art");
        list.addTranslation("red", "rojo");
        list.addTranslation("blue", "azul");
        List<Flashcard> flashcards = list.getFlashcards();
        assertEquals(2, flashcards.size());
        assertEquals("red", flashcards.get(0).getWord());
        assertEquals("blue", flashcards.get(1).getWord());
        assertEquals("rojo", flashcards.get(0).getTranslation());
    }


}
