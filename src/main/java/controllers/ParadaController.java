package controllers;

import models.dominio.transporte.Ubicacion;
import models.dominio.transporte.medios.Parada;
import models.dominio.transporte.medios.ParadasTransporte;
import models.dominio.transporte.medios.Publico;
import models.repositorios.RepositorioDeParadas;
import models.repositorios.RepositorioDeTransportes;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

import java.util.HashMap;
import java.util.List;

public class ParadaController {

    private RepositorioDeParadas repositorioDeParadas;
    private RepositorioDeTransportes repositorioDeTransportes;

    public ParadaController(){
        repositorioDeParadas = new RepositorioDeParadas();
        repositorioDeTransportes = new RepositorioDeTransportes();
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

    public ModelAndView agregarParada(Request request, Response response) {
        List<Parada> paradas = this.repositorioDeParadas.buscarTodos();

        return new ModelAndView(new HashMap<String, Object>(){{
            put("parada", paradas);
        }}, "form_parada_transporte.hbs");
    }

    public Response guardarParadaTransporte(Request request, Response response) {
        Parada parada = this.repositorioDeParadas.buscar(Integer.valueOf(request.queryParams("parada_id")));
        Publico publico = (Publico) this.repositorioDeTransportes.buscar(Integer.valueOf(request.params("id")));
        ParadasTransporte paradaTransporte = new ParadasTransporte();
        paradaTransporte.setTransportePublico(publico);
        paradaTransporte.setParadaActual(parada);
        paradaTransporte.setDistanciaAlaSiguiente(Double.valueOf(request.queryParams("distancia")));
        paradaTransporte.setParadaSiguiente(null);

        if(publico.getCantidadParadas() > 0) {
            ParadasTransporte ultimaParada = publico.agregarParada(paradaTransporte);
            this.repositorioDeParadas.guardarParadaTransporte(paradaTransporte);
            this.repositorioDeParadas.guardarParadaTransporte(ultimaParada);
        } else {
            this.repositorioDeParadas.guardarParadaTransporte(paradaTransporte);
            publico.agregarParada(paradaTransporte);
        }

        this.repositorioDeTransportes.guardar(publico);
        response.redirect("/home");
        return response;
    }

    public ModelAndView mostrarParadasTransporte(Request request, Response response) {
        List<ParadasTransporte> paradas = this.repositorioDeParadas.buscarParadasTransporte(request.params("id"));

        return new ModelAndView(new HashMap<String, Object>(){{
            put("parada", paradas);
        }}, "paradas_transporte.hbs");
    }
}
