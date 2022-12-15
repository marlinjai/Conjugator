import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AppControllerTest {

    @Test
    void logInCorrectData() {
        AppController testCtrl = new AppController();
        User testUser1 = new User("Anton","Zimmermann", "1234","A.Zimmermann@Zmail.de");
        assertTrue(testCtrl.logIn(testUser1,"A.Zimmermann@Zmail.de","1234", Database.Userlist));
    }
    @Test
    void LogInFalseData(){
        AppController testCtrl = new AppController();
        User testUser1 = new User("Anton","Zimmermann", "1234","A.Zimmermann@Zmail.de");
        assertFalse(testCtrl.logIn(testUser1, "A.Zimmermann@Zmail.de", "6243", Database.Userlist));
    }

}