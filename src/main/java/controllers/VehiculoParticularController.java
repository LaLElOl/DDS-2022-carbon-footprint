package controllers;

import models.dominio.organizacion.Organizacion;
import models.dominio.transporte.vehiculos.TipoVehiculo;
import models.dominio.transporte.vehiculos.Vehiculo;
import models.repositorios.RepositorioDeVehiculos;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

import java.util.HashMap;
import java.util.List;
import java.util.Locale;

public class VehiculoParticularController {

    private RepositorioDeVehiculos repositorioDeVehiculos;

    public VehiculoParticularController(){repositorioDeVehiculos = new RepositorioDeVehiculos();}

    public ModelAndView mostrarTodos(Request request, Response response) {
        List<Vehiculo> todosLosVehiculosParticulares = this.repositorioDeVehiculos.buscarTodos();
        return new ModelAndView(new HashMap<String, Object>(){{
            put("transporte", todosLosVehiculosParticulares);
        }}, "transportes.hbs");
    }

    public ModelAndView mostrar(Request request, Response response) {
        String idBuscado = request.params("id");
        Vehiculo vehiculoBuscado = this.repositorioDeVehiculos.buscar(new Integer(idBuscado));

        return new ModelAndView(new HashMap<String, Object>(){{
            put("vehiculo", vehiculoBuscado);
        }}, "transporte.hbs");// TODO DEFINIR PANTALLAS TRANSPORTES
    }

    public ModelAndView crear(Request request, Response response) {
        return new ModelAndView(null, "form_vehiculo_particular.html");
    }

    public Response guardar(Request request,Response response) {

        Vehiculo vehiculo = new Vehiculo();

        vehiculo.setFactorVehiculo(Double.valueOf(request.queryParams("factorVehiculo")));
        TipoVehiculo tipoVehiculo = TipoVehiculo.valueOf(request.queryParams("tipoVehiculo").toUpperCase(Locale.ROOT));
        //TODO Setear Factor Emision con combustible
        vehiculo.setTipoVehiculo(tipoVehiculo);

        this.repositorioDeVehiculos.guardar(vehiculo);

        response.redirect("/alta_vehiculo_particular");
        return response;
    }

    public ModelAndView editar(Request request, Response response) {
        String idBuscado = request.params("id");
        Vehiculo vehiculoBuscado = this.repositorioDeVehiculos.buscar(new Integer(idBuscado));

        return new ModelAndView(new HashMap<String, Object>(){{
            put("vehiculo", vehiculoBuscado);
        }}, "transporte.hbs");// TODO DEFINIR PANTALLAS TRANSPORTES
    }

    public Response modificar(Request request,Response response) {

        String idBuscado = request.params("id");
        Vehiculo vehiculoBuscado = this.repositorioDeVehiculos.buscar(new Integer(idBuscado));

        vehiculoBuscado.setFactorVehiculo(Double.valueOf(request.queryParams("factorVehiculo")));
        TipoVehiculo tipoVehiculo = TipoVehiculo.valueOf(request.queryParams("tipoVehiculo").toUpperCase(Locale.ROOT));
        //TODO Setear Factor Emision con combustible
        vehiculoBuscado.setTipoVehiculo(tipoVehiculo);

        this.repositorioDeVehiculos.guardar(vehiculoBuscado);

        response.redirect("/transportes");
        return response;
    }
}
