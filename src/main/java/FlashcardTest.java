
import org.testng.annotations.Test;
import java.util.List;
import static org.junit.Assert.*;



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
