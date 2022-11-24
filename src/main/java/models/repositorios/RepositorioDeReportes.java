package models.repositorios;

import helpers.EntityManagerHelper;
import models.dominio.ReporteHuellaCarbono;
import models.dominio.organizacion.Organizacion;

import java.util.List;

public class RepositorioDeReportes {

    public List buscarTodos() {
        return EntityManagerHelper
                .getEntityManager()
                .createQuery("from " + ReporteHuellaCarbono.class.getName())
                .getResultList();
    }

    public ReporteHuellaCarbono buscar(Integer id) {
        return EntityManagerHelper
                .getEntityManager()
                .find(ReporteHuellaCarbono.class, id);
    }

    public List buscarPorOrganizacion(String organizacionId) {
        return EntityManagerHelper
                .getEntityManager()
                .createQuery("from " + ReporteHuellaCarbono.class.getName() + "where organizacion_id = " + organizacionId)
                .getResultList();
    }

    public void guardar(ReporteHuellaCarbono reporteHuellaCarbono) {
        EntityManagerHelper.beginTransaction();

        EntityManagerHelper.persist(reporteHuellaCarbono);

        EntityManagerHelper.commit();
    }
}
