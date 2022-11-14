package controllers;

import models.dominio.organizacion.Sector;
import models.repositorios.RepositorioDeSectores;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

import java.util.HashMap;
import java.util.List;

public class SectorController {

    private RepositorioDeSectores repositorioDeSectores;

    public SectorController(){repositorioDeSectores = new RepositorioDeSectores();}


    public ModelAndView mostrarTodos(Request request, Response response) {
        List<Sector> todosLosSectores = this.repositorioDeSectores.buscarTodos();
        return new ModelAndView(new HashMap<String, Object>(){{
            put("sector", todosLosSectores);
        }}, "sectores.hbs");
    }

    public ModelAndView mostrar(Request request, Response response) {
        String idBuscado = request.params("id");
        Sector sectorBuscado = this.repositorioDeSectores.buscar(new Integer(idBuscado));

        return new ModelAndView(new HashMap<String, Object>(){{
            put("sector", sectorBuscado);
            //put("cant_miembros", sectorBuscado.cantMiembros());
        }}, "sector.hbs");
    }

    public ModelAndView crear(Request request, Response response) {
        return new ModelAndView(null, "form_sector.html");
    }

    public Response guardar(Request request,Response response) {

        Sector sector = new Sector();

        sector.setNombre(request.queryParams("nombre"));
        //TODO sector.setOrganizacion();

        this.repositorioDeSectores.guardar(sector);

        response.redirect("/alta_sector");
        return response;
    }


    public ModelAndView editar(Request request, Response response) {
        String idBuscado = request.params("id");
        Sector sectorBuscado = this.repositorioDeSectores.buscar(new Integer(idBuscado));

        return new ModelAndView(new HashMap<String, Object>(){{
            put("sector", sectorBuscado);
            //put("cant_miembros", sectorBuscado.cantMiembros());
        }}, "sector.hbs");
    }


    public Response modificar(Request request, Response response) {
        String idBuscado = request.params("id");
        Sector sectorBuscado = this.repositorioDeSectores.buscar(new Integer(idBuscado));

        sectorBuscado.setNombre(request.queryParams("nombre"));

        this.repositorioDeSectores.guardar(sectorBuscado);

        response.redirect("/sectores");
        return response;
    }
}
