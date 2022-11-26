package controllers;

import models.dominio.persona.Miembro;
import models.dominio.transporte.combustibles.Combustible;
import models.dominio.transporte.vehiculos.TipoVehiculo;
import models.dominio.transporte.vehiculos.Vehiculo;
import models.repositorios.*;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

import java.util.HashMap;
import java.util.List;
import java.util.Locale;

public class VehiculoController {

    private RepositorioDeCombustibles repositorioDeCombustibles;
    private RepositorioDeMiembros repositorioDeMiembros;
    private RepositorioDeVehiculos repositorioDeVehiculos;


    public VehiculoController(){
        repositorioDeMiembros = new RepositorioDeMiembros();
        repositorioDeCombustibles = new RepositorioDeCombustibles();
        repositorioDeVehiculos = new RepositorioDeVehiculos();
    }

    public ModelAndView altaVehiculo(Request request, Response response) {
        List<Combustible> combustibles = this.repositorioDeCombustibles.buscarTodos();

        return new ModelAndView(new HashMap<String,Object>(){{
            put("combustible",combustibles);
        }}, "form_vehiculo_particular.hbs");
    }

    public Response guardarVehiculo(Request request, Response response) {
        Integer id_miembro = new Integer(request.session().attribute("id"));
        Miembro miembro = this.repositorioDeMiembros.buscarPorUsuario(id_miembro);
        Vehiculo vehiculo =  new Vehiculo();
        Integer id_combustible = new Integer(request.queryParams("combustible_id"));
        Combustible combustible = this.repositorioDeCombustibles.buscar(id_combustible);

        TipoVehiculo tipo_vehiculo = TipoVehiculo.valueOf(request.queryParams("tipo_vehiculo").toUpperCase(Locale.ROOT));
        vehiculo.setTipoVehiculo(tipo_vehiculo);
        vehiculo.setFactorVehiculo(Double.valueOf(request.queryParams("factor_vehiculo")));
        vehiculo.setCombustible(combustible);
        vehiculo.setNombre(request.queryParams("nombre"));
        vehiculo.setMiembro(miembro);

        this.repositorioDeVehiculos.guardar(vehiculo);

        response.redirect("/home");

        return  response;

    }
}
