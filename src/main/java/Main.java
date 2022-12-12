public class Main {

    public static void main(String[] args) {
        User testUser1 = new User("Anton","Zimmermann", "1234","A.Zimmermann@Zmail.de");

        AppController AppCtrl = new AppController();

        AppCtrl.logIn(testUser1,"A.Zimmermann@Zmail.de", "1234", Database.Userlist);

        /*User printtest = Database.Userlist.get(0);
        System.out.println(printtest.firstName);*/
    }

}
