package models.repositorios;

import helpers.EntityManagerHelper;
import models.dominio.transporte.medios.Parada;

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

}
