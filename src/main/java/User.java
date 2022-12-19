public class User {
    String firstName;
    String lastName;
    String password;
    String eMailAddress;

    String salt;

    /**
     * unclear if saving the salt like that is secure
     * @param firstName
     * @param lastName
     * @param password
     * @param eMailAddress
     */
    public User(String firstName, String lastName, String password, String eMailAddress) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.eMailAddress = eMailAddress;
        this.salt = AppCtrl.genSalt();
        this.password = AppCtrl.securePW(password, salt);
        Database.Userlist.add(this);
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String geteMailAddress() {
        return eMailAddress;
    }

    public void seteMailAddress(String eMailAddress) {
        this.eMailAddress = eMailAddress;
    }
}
