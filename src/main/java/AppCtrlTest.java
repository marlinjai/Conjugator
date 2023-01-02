import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class AppCtrlTest {


    @Test
    void registerNewUser(){
        //User testUser1 = new User.UserBuilder("A.Zimmermann@Zmail.de","1234").setFirstName("Anton").setLastName("Zimmermann").build();
        AppCtrl.registerUser("D.Hammermann@Bmail.de", "27245721", "Daniela", "Hammermann");

        assertEquals("Daniela", Database.Userlist.get(Database.Userlist.size()-1).getFirstName());
        assertEquals("Hammermann", Database.Userlist.get(Database.Userlist.size()-1).getLastName());
    }
    @Test
    void logInCorrectData() {
        //User testUser1 = new User("Anton","Zimmermann", "1234","A.Zimmermann@Zmail.de");
        User testUser1 = new User.UserBuilder("M.Mustermann@Cmail.de","5678")
                .setFirstName("Max").setLastName("Mustermann").build();
        assertTrue(AppCtrl.logIn(testUser1,"M.Mustermann@Cmail.de","5678", Database.Userlist));
    }
    @Test
    void LogInFalseData(){
        //User testUser1 = new User("Anton","Zimmermann", "1234","A.Zimmermann@Zmail.de");
        User testUser1 = new User.UserBuilder("A.Zimmermann@Zmail.de","1234")
                .setFirstName("Anton").setLastName("Zimmermann").build();
        assertFalse(AppCtrl.logIn(testUser1, "A.Zimmermann@Zmail.de", "6243", Database.Userlist));
    }
    @Test
    void securePWCorrect(){
        //User testUser1 = new User("Anton","Zimmermann", "1234","A.Zimmermann@Zmail.de");
        User testUser1 = new User.UserBuilder("A.Zimmermann@Zmail.de","1234")
                .setFirstName("Anton").setLastName("Zimmermann").build();
        assertEquals(testUser1.getPassword(), AppCtrl.securePW("1234", testUser1.salt));
    }
    @Test
    void securePwCheckDB(){
        //User testUser1 = new User("Anton","Zimmermann", "1234","A.Zimmermann@Zmail.de");
        User testUser1 = new User.UserBuilder("A.Zimmermann@Zmail.de","1234").setFirstName("Anton").setLastName("Zimmermann").build();
        assertNotEquals(testUser1.getPassword(),"1234");
    }

}