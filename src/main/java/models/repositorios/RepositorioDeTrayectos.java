package models.repositorios;

import helpers.EntityManagerHelper;
import models.dominio.persona.Trayecto;

import java.util.List;

public class RepositorioDeTrayectos {

    public List buscarTodos() {
        return EntityManagerHelper
                .getEntityManager()
                .createQuery("from " + Trayecto.class.getName())
                .getResultList();
    }

    public Trayecto buscar(Integer id) {
        return EntityManagerHelper
                .getEntityManager()
                .find(Trayecto.class, id);
    }

    public void guardar(Trayecto trayecto) {
        EntityManagerHelper.beginTransaction();

        EntityManagerHelper.persist(trayecto.getInicio());
        EntityManagerHelper.persist(trayecto.getFin());
        EntityManagerHelper.persist(trayecto);

        EntityManagerHelper.commit();
    }


}
