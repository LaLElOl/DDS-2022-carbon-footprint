package models.services.distancias;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.io.IOException;
import java.util.List;

public class RetrofitServicioGeo implements AdapterGeoService {
    private static RetrofitServicioGeo instancia = null;
    private static final String urlAPI = "https://ddstpa.com.ar/api/";
    private final Retrofit retrofit;


    public RetrofitServicioGeo() {
        this.retrofit = new Retrofit.Builder()
                .baseUrl(urlAPI)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public static RetrofitServicioGeo getInstancia() {
        if(instancia == null) {
            instancia = new RetrofitServicioGeo();
        }
        return instancia;
    }

    public List<Provincia> provincias() throws IOException {
        GeoService geoService = this.retrofit.create(GeoService.class);
        Call<List<Provincia>> requestProvinciasArg = geoService.provincias(1,"Bearer Yi/6XlA+LZc7PKtkXwWFa8dSWE9zT7dEEj3aHrmztYw=");
        Response<List<Provincia>> responseProvinciasArg = requestProvinciasArg.execute();
        return responseProvinciasArg.body();
    }

    public Distancia distancia(Integer localidadOrigenId,String calleOrigen,
                               String alturaOrigen, Integer localidadDestinoId,
                               String calleDestino, Integer alturaDestino) throws IOException {
        GeoService geoService = this.retrofit.create(GeoService.class);
        Call<Distancia> requestDistancia = geoService.distancia(
                localidadOrigenId,
                calleOrigen,
                alturaOrigen,
                localidadDestinoId,
                calleDestino,
                alturaDestino,
                "Bearer Yi/6XlA+LZc7PKtkXwWFa8dSWE9zT7dEEj3aHrmztYw=");
        Response<Distancia> responseDistancia = requestDistancia.execute();
        return responseDistancia.body();
    }
}
