package models.repositorios;

import helpers.EntityManagerHelper;
import models.dominio.transporte.medios.Parada;
import models.dominio.transporte.medios.ParadasTransporte;

import java.util.List;

public class RepositorioDeParadas {

    public List buscarTodos() {
        return EntityManagerHelper
                .getEntityManager()
                .createQuery("from " + Parada.class.getName())
                .getResultList();
    }

    public Parada buscar(Integer id) {
        return EntityManagerHelper
                .getEntityManager()
                .find(Parada.class, id);
    }

    public void guardar(Parada parada) {
        EntityManagerHelper.beginTransaction();

        EntityManagerHelper.persist(parada.getUbicacion());
        EntityManagerHelper.persist(parada);

        EntityManagerHelper.commit();
    }

    public void guardarParadaTransporte(ParadasTransporte parada) {
        EntityManagerHelper.beginTransaction();

        EntityManagerHelper.persist(parada);

        EntityManagerHelper.commit();
    }

    public List buscarParadasTransporte(String id) {
        return EntityManagerHelper
                .getEntityManager()
                .createQuery("from " + ParadasTransporte.class.getName() + " where transporte_id=" + id)
                .getResultList();
    }
}
