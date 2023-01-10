import javafx.css.CssParser;
import spark.ModelAndView;
import spark.template.jade.JadeTemplateEngine;

import java.util.HashMap;
import java.util.Map;

import static spark.Spark.*;

public class FrontendController {
    public static void main(String[] args) {
        staticFileLocation("public");

        get("/", (req, res) -> {return "hello <b>HTW</b>";} );
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
            CssParser z = new CssParser();

            Map<String, Object> model = new HashMap<>();
            ModelAndView modelAndView = new ModelAndView(model, "login");
            return modelAndView;
        }, new JadeTemplateEngine());
       /*
       post("/sample", (req, res) -> {
           return "handled post request";
       });
        */
    }
}
