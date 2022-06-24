package service;

import service.entities.Distancia;

import java.io.IOException;

public interface AdapterGeoService {

    public Distancia distancia(Integer localidadOrigenId,
                               String calleOrigen,
                               String alturaOrigen,
                               Integer localidadDestinoId,
                               String calleDestino,
                               Integer alturaDestino) throws IOException;
}
