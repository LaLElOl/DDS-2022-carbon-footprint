package models.repositorios;

import helpers.EntityManagerHelper;
import models.dominio.persona.Tramo;

import java.util.List;

public class RepositorioDeTramos {

    public List buscarTodos(Integer trayecto_id) {
        return EntityManagerHelper
                .getEntityManager()
                .createQuery("from " + Tramo.class.getName() + " where trayecto_id =" + trayecto_id)
                .getResultList();
    }

    public Tramo buscar(Integer id) {
        return EntityManagerHelper
                .getEntityManager()
                .find(Tramo.class, id);
    }

    public void guardar(Tramo tramo) {
        EntityManagerHelper.beginTransaction();

        EntityManagerHelper.persist(tramo.getInicioTramo());
        EntityManagerHelper.persist(tramo.getFinTramo());
        EntityManagerHelper.persist(tramo);

        EntityManagerHelper.commit();
    }

}
