package controllers;

import models.dominio.transporte.Ubicacion;
import models.dominio.transporte.medios.Parada;
import models.repositorios.RepositorioDeParadas;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

public class ParadaController {

    private RepositorioDeParadas repositorioDeParadas;

    public ParadaController(){
        repositorioDeParadas = new RepositorioDeParadas();
    }


    public ModelAndView crearParada(Request request, Response response) {
        return new ModelAndView(null, "form_parada.hbs");
    }


    public Response guardarParada(Request request, Response response) {
        Parada parada = new Parada();
        Ubicacion ubicacion = new Ubicacion();

        parada.setNombre(request.queryParams("nombre"));
        ubicacion.setCalle(request.queryParams("calle"));
        ubicacion.setAltura(Integer.valueOf(request.queryParams("altura")));
        //TODO
        return response;
    }
}
