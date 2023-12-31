package controllers;

import spark.ModelAndView;
import spark.Request;
import spark.Response;
import models.dominio.*;
import helpers.EntityManagerHelper;

public class LoginController {

    public ModelAndView pantallaDeLogin(Request request, Response response) {
        return new ModelAndView(null, "login.hbs");
    }

    public Response login(Request request, Response response) {
        try {
            String query = "from "
                    + Usuario.class.getName()
                    +" WHERE nickname='"
                    + request.queryParams("usuario")
                    +"' AND contrasenia='"
                    + request.queryParams("contrasenia")
                    +"'";
            Usuario usuario = (Usuario) EntityManagerHelper
                    .getEntityManager()
                    .createQuery(query)
                    .getSingleResult();

            if(usuario != null) {
                request.session(true);
                request.session().attribute("id",Integer.toString(usuario.getId()));
                response.redirect("/home");
            }
            else {
                response.redirect("/login");
            }
        }
        catch (Exception exception) {
            response.redirect("/login");
        }
        return response;
    }

    public Response logout(Request request, Response response) {
        request.session().invalidate();
        response.redirect("/login");
        return response;
    }

    public ModelAndView prohibido(Request request, Response response) {
        return new ModelAndView(null, "/index");//TODO prohibido
    }

    public ModelAndView signup(Request request, Response response) {
        return new ModelAndView(null, "/signup.hbs");
    }

    public Response mandarALogin(Request request, Response response) {
        response.redirect("/login");
        return response;
    }
}
