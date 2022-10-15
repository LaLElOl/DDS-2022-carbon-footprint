package models.services.distancias;

import java.io.IOException;

public interface AdapterGeoService {

    Distancia distancia(Integer localidadOrigenId,
                               String calleOrigen,
                               String alturaOrigen,
                               Integer localidadDestinoId,
                               String calleDestino,
                               Integer alturaDestino) throws IOException;
}
