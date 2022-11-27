package controllers;

import models.dominio.transporte.Ubicacion;
import models.dominio.transporte.medios.Parada;
import models.repositorios.RepositorioDeParadas;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

import java.util.HashMap;
import java.util.List;

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
        ubicacion.setLocalidad(Integer.valueOf(request.queryParams("localidad")));
        ubicacion.setProvincia(request.queryParams("provincia"));
        ubicacion.setMunicipio(request.queryParams("municipio"));

        parada.setUbicacion(ubicacion);

        this.repositorioDeParadas.guardar(parada);

        response.redirect("/home");
        return response;
    }

    public ModelAndView mostrarParadas(Request request, Response response) {
        List<Parada> paradas = this.repositorioDeParadas.buscarTodos();

        return new ModelAndView(new HashMap<String, Object>(){{
            put("parada", paradas);
        }}, "paradas.hbs");
    }
}
