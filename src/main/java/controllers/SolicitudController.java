package controllers;

import models.dominio.organizacion.EstadoSolicitud;
import models.dominio.organizacion.Organizacion;
import models.dominio.organizacion.Sector;
import models.dominio.organizacion.Solicitud;
import models.dominio.persona.Miembro;
import models.repositorios.RepositorioDeMiembros;
import models.repositorios.RepositorioDeOrganizaciones;
import models.repositorios.RepositorioDeSectores;
import models.repositorios.RepositorioDeSolicitudes;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

import java.time.LocalDate;
import java.util.HashMap;

public class SolicitudController {

    private RepositorioDeSolicitudes repositorioDeSolicitudes;
    private RepositorioDeSectores repositorioDeSectores;
    private RepositorioDeMiembros repositorioDeMiembros;
    private RepositorioDeOrganizaciones repositorioDeOrganizaciones;


    public SolicitudController(){
        repositorioDeSolicitudes = new RepositorioDeSolicitudes();
        repositorioDeSectores = new RepositorioDeSectores();
        repositorioDeMiembros = new RepositorioDeMiembros();
        repositorioDeOrganizaciones = new RepositorioDeOrganizaciones();
    }

    public Response generarSolicitud(Request request, Response response){
        Sector sector =this.repositorioDeSectores.buscar(new Integer(request.queryParams("sector_id")));
        Integer id = new Integer(request.session().attribute("id"));
        Miembro miembro = this.repositorioDeMiembros.buscarPorUsuario(id);
        Solicitud solicitud = new Solicitud();

        solicitud.setSolicitante(miembro);
        solicitud.setSector(sector);
        sector.agregarMiembroSolicitante(solicitud);
        solicitud.setFecha_creacion(LocalDate.now());
        solicitud.setEstado(EstadoSolicitud.PENDIENTE);

        this.repositorioDeSolicitudes.guardar(solicitud);
        this.repositorioDeSectores.guardar(sector);
        response.redirect("/home");

        return response;
    }


    public ModelAndView mostrarSegunSector(Request request, Response response) {
        String sector_id = request.params("id_sector");
        Sector sector = this.repositorioDeSectores.buscar(Integer.valueOf(sector_id));
        String organizacion_id = request.params("id");
        Organizacion organizacion = this.repositorioDeOrganizaciones.buscar(Integer.valueOf(organizacion_id));

        return new ModelAndView(new HashMap<String, Object>(){{
            put("solicitud", sector.getSolicitudes());
            put("id_organizacion",organizacion.getId());
            put("id_sector",sector.getId());
        }}, "solicitudes.hbs");

    }

    public Response aceptarSolicitud(Request request, Response response){
        Integer id_solicitud = Integer.valueOf(request.params("id_solicitud"));
        Solicitud solicitud = this.repositorioDeSolicitudes.buscar(id_solicitud);
        Integer id_sector = Integer.valueOf(request.params("id_sector"));
        Sector sector = this.repositorioDeSectores.buscar(id_sector);

        sector.darDeAltaAMiembro(solicitud);

        this.repositorioDeSolicitudes.guardar(solicitud);
        this.repositorioDeSectores.guardar(sector);

        response.redirect("/home");

        return response;
    }

    public Response rechazarSolicitud(Request request, Response response){
        Integer id_solicitud = Integer.valueOf(request.params("id_solicitud"));
        Solicitud solicitud = this.repositorioDeSolicitudes.buscar(id_solicitud);
        Integer id_sector = Integer.valueOf(request.params("id_sector"));
        Sector sector = this.repositorioDeSectores.buscar(id_sector);

        sector.rechazarSolicitud(solicitud);

        this.repositorioDeSolicitudes.guardar(solicitud);
        this.repositorioDeSectores.guardar(sector);

        response.redirect("/home");

        return response;
    }




}
