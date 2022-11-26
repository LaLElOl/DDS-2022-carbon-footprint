package controllers;

import models.dominio.organizacion.Sector;
import models.repositorios.RepositorioDeSectores;
import models.repositorios.RepositorioDeSolicitudes;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

import java.util.HashMap;

public class SolicitudController {

    private RepositorioDeSolicitudes repositorioDeSolicitudes;
    private RepositorioDeSectores repositorioDeSectores;


    public SolicitudController(){
        repositorioDeSolicitudes = new RepositorioDeSolicitudes();
        repositorioDeSectores = new RepositorioDeSectores();
    }


    public ModelAndView mostrarSegunSector(Request request, Response response) {
        String sector_id = request.params("id_sector");
        Sector sector = this.repositorioDeSectores.buscar(Integer.valueOf(sector_id));

        return new ModelAndView(new HashMap<String, Object>(){{
            put("solicitud", sector.getSolicitudes());
        }}, "solicitudes.hbs");

    }
}
