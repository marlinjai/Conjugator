import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


public class WordListTest {

    @Test
    public void testAddTranslation() {
        WordList list = new WordList("Animals", "Nature");
        list.addTranslation("dog", "perro");
        assertEquals(1, list.getFlashcards().size());
    }


}