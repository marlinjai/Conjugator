import java.awt.datatransfer.StringSelection;
import java.lang.reflect.Array;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.math.BigInteger;

public class AppCtrl {

    /**
     * is verifying the loginData of a User.
     * @param User
     * @param eMail
     * @param password
     * @param Userlist
     * @return
     */
    public static boolean logIn(User User,String eMail, String password, ArrayList<User> Userlist){
        // decrypt password method
        User isUser = Userlist.get(Userlist.indexOf(User));
        return eMail.equals(isUser.eMailAddress) && securePW(password, isUser.salt).equals(isUser.password);
    }

    /**
     * The values for this Method should be taken from the registerform of the user, otherwise it would be useless
     * to write a method that calls a builder which is then creating the actual object.
     * @param eMail
     * @param password
     * @param firstName
     * @param lastName
     */
    public static void registerUser(String eMail, String password, String firstName, String lastName){
        new User.UserBuilder(eMail,password).setFirstName(firstName).setLastName(lastName).build();
    }

    /**
     * generates salt
     * @return
     */
    public static String genSalt(){
        String salt="";
        String aboveTen= "0";
        for (int i = 0; i < 10; i++) {
        int tmpSalt =(int)(64*Math.random()-1);
        if(tmpSalt < 10) salt = salt.concat(aboveTen.concat(Integer.toString(tmpSalt)));
        else salt = salt.concat(Integer.toString(tmpSalt));
        }
        return salt;
    }

    /**
     * encrypts the password with a given salt, unclear if that isn't redundant.
     * If a hacker has the salt and tries to guess the password then it doesn't make a difference
     * if it is salted and hashed once or ten times right? gotta ask in the next lab.
     *
     * @param pw
     * @param salt
     * @return
     */
    public static String securePW(String pw, String salt){

        try{
            pw=hashToHexString(hashPW(pw));
            for (int i = 0; i < salt.length(); i+=2) {
                //get saltindex, read hash with saltvalue, concat to hash, hash
                int saltindex = Integer.parseInt(salt.substring(i,i+2));
                pw = pw.substring(saltindex).concat(pw);
                pw = hashToHexString(hashPW(pw));
            }
        } catch(NoSuchAlgorithmException e){
            System.out.println("wrong Algorithm: " + e);
        }
        return pw;
    }

    /**
     * hashes the password, source: geeksforgeeks.org/sha-256-hash-in-java/
     * @param password
     * @return
     * @throws NoSuchAlgorithmException
     */
    public static byte[] hashPW(String password) throws NoSuchAlgorithmException {
        MessageDigest msgDigest = MessageDigest.getInstance("SHA-256");
        return msgDigest.digest(password.getBytes(StandardCharsets.UTF_8));
    }

    /**
     * converts the hash values into hexadecimal values and returns it as string, source: geeksforgeeks.org/sha-256-hash-in-java/
     * @param hash
     * @return
     */
    public static String hashToHexString(byte[] hash){
        BigInteger number = new BigInteger(1, hash);

        StringBuilder hexString = new StringBuilder(number.toString(16));

        /*while(hexString.length() < 64){
            hexString.insert(0,"0");
        }*/
        return hexString.toString();
    }

}
