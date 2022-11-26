package controllers;

import models.dominio.organizacion.Organizacion;
import models.dominio.organizacion.Sector;
import models.repositorios.RepositorioDeOrganizaciones;
import models.repositorios.RepositorioDeSectores;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;

public class SectorController {

    private RepositorioDeSectores repositorioDeSectores;
    private RepositorioDeOrganizaciones repositorioDeOrganizaciones;

    public SectorController(){
        repositorioDeSectores = new RepositorioDeSectores();
        repositorioDeOrganizaciones = new RepositorioDeOrganizaciones();
    }


    public ModelAndView mostrarTodos(Request request, Response response) {
        Integer id = new Integer(request.session().attribute("id"));
        Organizacion org = this.repositorioDeOrganizaciones.buscarPorUsuario(id);
        List<Sector> todosLosSectores = this.repositorioDeSectores.buscarTodos(org.getId().toString());
        return new ModelAndView(new HashMap<String, Object>(){{
            put("sector", todosLosSectores);
            put("organizacion_id",org.getId());
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
        return new ModelAndView(null, "form_sector.hbs");
    }

    public Response guardar(Request request,Response response) {

        Sector sector = new Sector();

        Integer id = new Integer(request.session().attribute("id"));
        Organizacion org = this.repositorioDeOrganizaciones.buscarPorUsuario(id);

        sector.setNombre(request.queryParams("nombre"));
        sector.setOrganizacion(org);
        sector.setFechaUltimoCalculoHuella(LocalDate.now());
        sector.setHuellaCarbonoActual(0.0);
        sector.setPromedioHCMiembro(0.0);

        this.repositorioDeSectores.guardar(sector);

        response.redirect("/home");
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
