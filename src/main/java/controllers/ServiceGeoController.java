package controllers;

import com.google.gson.Gson;
import models.services.distancias.Localidad;
import models.services.distancias.Municipio;
import models.services.distancias.Provincia;
import models.services.distancias.RetrofitServicioGeo;
import retrofit2.Retrofit;
import spark.Request;
import spark.Response;

import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class ServiceGeoController {

    public String obtenerProvincias(Request request, Response response) {

        List<Provincia> provincias = RetrofitServicioGeo.getInstancia().provincias();
        return new Gson().toJson(provincias);
    }

    public String obtenerProvincia(Request request, Response response){
        List<Provincia> provincias = RetrofitServicioGeo
                        .getInstancia()
                        .provincias().stream()
                        .filter(p-> Objects.equals(p.getId(), request.params("id")))
                        .collect(Collectors.toList());
        return new Gson().toJson(provincias);

    }

    public String obtenerMunicipios(Request request, Response response) {
        List<Municipio> municipios = RetrofitServicioGeo.getInstancia().municipios(new Integer(request.params("id")));
        return new Gson().toJson(municipios);
    }

    public String obtenerMunicipio(Request request, Response response) {
        List<Municipio> municipios = RetrofitServicioGeo
                .getInstancia()
                .municipios(new Integer(request.params("provid")))
                .stream()
                .filter(m-> Objects.equals(m.getId(), request.params("id")))
                .collect(Collectors.toList());
        return new Gson().toJson(municipios);
    }

    public String obtenerLocalidades(Request request, Response response) {
        List<Localidad> localidades = RetrofitServicioGeo.getInstancia().localidades(new Integer(request.params("id")));
        return new Gson().toJson(localidades);
    }

    public String obtenerLocalidad(Request request, Response response) {
        List<Localidad> localidades = RetrofitServicioGeo
                .getInstancia()
                .localidades(new Integer(request.params("munid")))
                .stream().filter(l -> Objects.equals(l.getId(), request.params("id")))
                .collect(Collectors.toList());
        return new Gson().toJson(localidades);
    }
}
