package models.services.distancias;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Query;

import java.util.List;

public interface GeoService {
    @GET("provincias")
    Call<List<Provincia>> provincias(@Query("offset")Integer numero, @Header("Authorization")String token);

    @GET("distancia")
    Call<Distancia> distancia(@Query("localidadOrigenId")Integer localidadOrigenId,
                              @Query("calleOrigen")String calleOrigen,
                              @Query("alturaOrigen")String alturaOrigen,
                              @Query("localidadDestinoId")Integer localidadDestinoId,
                              @Query("calleDestino")String calleDestino,
                              @Query("alturaDestino")Integer alturaDestino,
                              @Header("Authorization")String token);

    @GET("municipios")
    Call<List<Municipio>> municipios(@Query("offset")Integer numero,
                                     @Query("provinciaId") Integer provId,
                                     @Header("Authorization")String token);

    @GET("localidades")
    Call<List<Localidad>> localidad(@Query("offset")Integer numero,
                                    @Query("municipioId") Integer munId,
                                    @Header("Authorization")String token);

}
