package models.repositorios;

import helpers.EntityManagerHelper;
import models.dominio.persona.Miembro;

import java.util.List;

public class RepositorioDeMiembros {

    public List buscarTodos() {
        return EntityManagerHelper
                .getEntityManager()
                .createQuery("from " + Miembro.class.getName())
                .getResultList();
    }

    public Miembro buscar(Integer id) {
        return EntityManagerHelper
                .getEntityManager()
                .find(Miembro.class, id);
    }

    public void guardar(Miembro miembro) {
        EntityManagerHelper.beginTransaction();
        EntityManagerHelper
                .getEntityManager()
                .persist(miembro);
        EntityManagerHelper.commit();
    }

}
