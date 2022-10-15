package models.services.cronJob;

import models.dominio.organizacion.Organizacion;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.TimerTask;

@Getter
@Setter
public class RecomendacionJob extends TimerTask {

    private String linkPagina;

    @Override
    public void run() {
        //TODO: Obtiene todas las organizaciones por DB
        List<Organizacion> organizaciones = new ArrayList<>();
        organizaciones.forEach(o -> o.notificarRecomendacion(linkPagina));
    }
}
