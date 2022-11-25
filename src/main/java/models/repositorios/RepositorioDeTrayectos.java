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

    public List buscarTrayectosDeMiembro(Integer id){
        return EntityManagerHelper
                .getEntityManager()
                .createQuery("from " + Trayecto.class.getName() + " where miembro_id = " + id)
                .getResultList();
    }

    public void guardar(Trayecto trayecto) {
        EntityManagerHelper.beginTransaction();

        EntityManagerHelper.persist(trayecto.getInicio());
        EntityManagerHelper.persist(trayecto.getFin());
        EntityManagerHelper.persist(trayecto);

        EntityManagerHelper.commit();
    }


}
