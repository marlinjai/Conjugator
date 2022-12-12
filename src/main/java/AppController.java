import java.util.ArrayList;

public class AppController {

    /**
     * is verifying the loginData of a User.
     * @param User
     * @param eMail
     * @param password
     * @param Userlist
     * @return
     */
    public boolean logIn(User User,String eMail, String password, ArrayList<User> Userlist){
        // decrypt password method
        User isUser = Userlist.get(Userlist.indexOf(User));
        return eMail.equals(isUser.eMailAddress) && password.equals(isUser.password);
    }

}
