import javafx.css.CssParser;
import spark.ModelAndView;
import spark.template.jade.JadeTemplateEngine;

import java.util.HashMap;
import java.util.Map;

import static spark.Spark.*;

public class FrontendController {
    public static void main(String[] args) {
        staticFileLocation("public");

        get("/", (req, res) -> {
            return "hello <b>HTW</b>";
        });
        // http://localhost:4567/hello/berta
        get("/hello/:name", (req, res) -> {
            return "hello, " + req.params(":name");
        });
        // http://localhost:4567/hello?name=dora
        get("/hello", (req, res) -> {
            return "hello, " + req.queryParams("name");
        });
        // http://localhost:4567/clock
        get("/clock", (req, res) -> {
            long time = System.currentTimeMillis();
            Map<String, Object> model = new HashMap<>();
            model.put("time", time);
            ModelAndView modelAndView = new ModelAndView(model, "clock");
            return modelAndView;
        }, new JadeTemplateEngine());
        // http://localhost:4567/login
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

            String nameInit = request.queryParams("name");
            String passwordInit = request.queryParams("password");
            String emailInit= request.queryParams("email");
            // Do something with the data
            //if ()
            System.out.println("Password: " + passwordInit);
            System.out.println("Email: " + emailInit);
            System.out.println("Name: "+ nameInit);



            return "registration successful";

        });

        post("/submit-login", (request, response) -> {

            String password = request.queryParams("password");
            String email = request.queryParams("email");
            // Do something with the data
            //if ()
            System.out.println("Password: " + password);
            System.out.println("Email: " + email);

            return "password correct";

        });
       /*
       post("/sample", (req, res) -> {
           return "handled post request";
       });
        */
    }
}
