import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class AppCtrlTest {

    @Test
    void logInCorrectData() {
        User testUser1 = new User("Anton","Zimmermann", "1234","A.Zimmermann@Zmail.de");
        assertTrue(AppCtrl.logIn(testUser1,"A.Zimmermann@Zmail.de","1234", Database.Userlist));
    }
    @Test
    void LogInFalseData(){
        User testUser1 = new User("Anton","Zimmermann", "1234","A.Zimmermann@Zmail.de");
        assertFalse(AppCtrl.logIn(testUser1, "A.Zimmermann@Zmail.de", "6243", Database.Userlist));
    }
    @Test
    void securePWCorrect(){
        User testUser1 = new User("Anton","Zimmermann", "1234","A.Zimmermann@Zmail.de");
        assertEquals(testUser1.getPassword(), AppCtrl.securePW("1234", testUser1.salt));
    }
    @Test
    void securePwCheckDB(){
        User testUser1 = new User("Anton","Zimmermann", "1234","A.Zimmermann@Zmail.de");
        assertNotEquals(testUser1.getPassword(),"1234");
    }

}