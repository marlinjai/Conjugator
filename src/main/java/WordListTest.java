import org.junit.Test;
import static org.junit.Assert.*;


public class WordListTest {

    @Test
    public void testAddTranslation() {
        WordList list = new WordList("Animals", "Nature");
        list.addTranslation("dog", "perro");
        assertEquals(1, list.getFlashcards().size());
    }


}
