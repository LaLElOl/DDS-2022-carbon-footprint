package controllers;

import com.google.gson.Gson;
import models.services.distancias.Provincia;
import models.services.distancias.RetrofitServicioGeo;
import spark.Request;
import spark.Response;

import java.io.IOException;
import java.util.List;

public class ServiceGeoController {

    public String obtenerProvincias(Request request, Response response) {

        List<Provincia> provincias = RetrofitServicioGeo.getInstancia().provincias();
        return new Gson().toJson(provincias);
    }
}
