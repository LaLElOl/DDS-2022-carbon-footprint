package models.repositorios;

import helpers.EntityManagerHelper;
import models.dominio.organizacion.Organizacion;
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

        EntityManagerHelper.persist(miembro.getUsuario());
        EntityManagerHelper.persist(miembro.getContacto());
        EntityManagerHelper.persist(miembro.getDomicilio());
        EntityManagerHelper.persist(miembro);

        EntityManagerHelper.commit();
    }

    public Miembro buscarPorUsuario(Integer id) {
        return (Miembro) EntityManagerHelper
                .getEntityManager()
                .createQuery("from " + Miembro.class.getName() + " where usuario_id =" + id)
                .getSingleResult();
    }


}
