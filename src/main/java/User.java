public class User {
    String firstName;
    String lastName;
    String password;
    String eMailAddress;

    String salt;

    /**
     * unclear if saving the salt like that is secure
     * @param builder
     */
    private User(UserBuilder builder) {
        this.firstName = builder.firstName;
        this.lastName = builder.lastName;
        this.eMailAddress = builder.eMailAddress;
        this.salt = builder.salt;
        this.password = builder.password;
        //Database.Userlist.add(this);
    }
    public String getFirstName() {
        return firstName;
    }
    public String getLastName() {
        return lastName;
    }
    public String getPassword() {
        return password;
    }
    public String geteMailAddress() {
        return eMailAddress;
    }
    public static class UserBuilder {
        //necessary Attributes
        String eMailAddress;
        String password;
        String salt;
        //not necessary Attributes
        String firstName;
        String lastName;

        /**
         * factory class to build a user with more clarity
         * @param eMailAddress
         * @param password
         */
        public UserBuilder(String eMailAddress,String password) {
            if(AppCtrl.checkMail(eMailAddress)) this.eMailAddress = eMailAddress;
            //else // boolean invalidMail;
            this.salt = AppCtrl.genSalt();
            this.password = AppCtrl.securePW(password, salt);

        }
        public UserBuilder setFirstName(String firstName) {
            this.firstName = firstName;
            return this;
        }
        public UserBuilder setLastName(String lastName) {
            this.lastName = lastName;
            return this;
        }
        public UserBuilder setPassword(String password) {
            this.salt = AppCtrl.genSalt();
            this.password = AppCtrl.securePW(password, salt);
            return this;
        }
        public UserBuilder seteMailAddress(String eMailAddress) {
            this.eMailAddress = eMailAddress;
            return this;
        }
        public User build(){
            return new User(this);
        }
    }

}
