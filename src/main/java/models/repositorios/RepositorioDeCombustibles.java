package models.repositorios;

import helpers.EntityManagerHelper;
import models.dominio.ReporteHuellaCarbono;
import models.dominio.organizacion.Organizacion;
import models.dominio.transporte.combustibles.Combustible;

import java.util.List;

public class RepositorioDeCombustibles {

    public List buscarTodos() {
        return EntityManagerHelper
                .getEntityManager()
                .createQuery("from " + Combustible.class.getName())
                .getResultList();
    }

    public Combustible buscar(String nombre) {
        return (Combustible) EntityManagerHelper
                .getEntityManager()
                .createQuery("from " + Combustible.class.getName() + " where nombre =" + nombre)
                .getSingleResult();
    }

    public void guardar(ReporteHuellaCarbono reporteHuellaCarbono) {
        EntityManagerHelper.beginTransaction();

        EntityManagerHelper.persist(reporteHuellaCarbono);

        EntityManagerHelper.commit();
    }
}
