import com.google.gson.Gson;
import javafx.css.CssParser;
import spark.ModelAndView;
import spark.template.jade.JadeTemplateEngine;

import java.io.StringReader;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map;

import static spark.Spark.*;

public class FrontendController {
    public static void main(String[] args) {
        staticFileLocation("public");
        RemoteDatabaseController userDBController = new RemoteDatabaseController();

        // http://localhost:4567/home
        get("/home", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            ModelAndView modelAndView = new ModelAndView(model, "home");
            return modelAndView;
        }, new JadeTemplateEngine());


        // http://localhost:4567/login

        get("/login", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            ModelAndView modelAndView = new ModelAndView(model, "login");
            return modelAndView;
        }, new JadeTemplateEngine());

        // http://localhost:4567/registration

        get("/registration", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            ModelAndView modelAndView = new ModelAndView(model, "registration");
            return modelAndView;
        }, new JadeTemplateEngine());

        post("/submit-registration", (request, response) -> {

            String firstNameInit = request.queryParams("name");
            String lastNameInit = null;
            String passwordInit = request.queryParams("password");
            String emailInit = request.queryParams("email");
            // Do something with the data

            System.out.println("Password: " + passwordInit);
            System.out.println("Email: " + emailInit);
            System.out.println("Name: " + firstNameInit);

            User newUser = new User.UserBuilder(emailInit, passwordInit).setFirstName(firstNameInit).setLastName(lastNameInit).build();

            userDBController.insertNewUserToDB(newUser.getFirstName(), newUser.getLastName(), newUser.getPassword(), newUser.geteMailAddress(), newUser.salt);

            return "registration successful";

        });

        post("/submit-login", (request, response) -> {

            String password = request.queryParams("password");
            String email = request.queryParams("email");

            ResultSet login = userDBController.fetchData("Select salt AS Salz,PASSWORD AS Passwort from users.userdata where email like '"+email+"';");
            String pwFromDB = "";
            String salz = "";
            while (login.next()) {
                pwFromDB = login.getString("passwort");
                salz = login.getString("salz");

            }
            String hashedPWToCheck = AppCtrl.securePW(password,salz);
            if (hashedPWToCheck.equals(pwFromDB)){return "password correct";}
            else{ return "password false";}


        });

        // http://localhost:4567/category-lists

        get("/category-lists", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            ModelAndView modelAndView = new ModelAndView(model, "categoryLists");
            return modelAndView;
        }, new JadeTemplateEngine());


        // http://localhost:4567/personal-lists

        get("/personal-lists", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            ModelAndView modelAndView = new ModelAndView(model, "personalLists");
            return modelAndView;
        }, new JadeTemplateEngine());

        // http://localhost:4567/translate

        get("/translate", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            ModelAndView modelAndView = new ModelAndView(model, "Translate");
            return modelAndView;
        }, new JadeTemplateEngine());


        post("/submit-translation", (request, response) -> {
            Gson gson = new Gson();

            //System.out.println(request.body());
            System.out.println("Content-Type header: " + request.headers("Content-Type"));
            System.out.println("Request Body: " + request.body());

            TranslationData userData = gson.fromJson(request.body(), TranslationData.class);


            DeepLTranslator translator = new DeepLTranslator();
            if (userData != null)System.out.println(userData.targetLanguage);
            DeepLTranslator.TargetLanguages tg = DeepLTranslator.TargetLanguages.VarLanguage;
            tg.setCode(userData.targetLanguage);
            String ret = translator.translateME(userData.textData, tg);
            String printToClient = "";

            Map<String, String> responseData = new HashMap<>();
            responseData.put("transalted:", printToClient);


            // Return the response data as JSON
            response.type("application/json");
            return gson.toJson(responseData);
        });

    }
}
