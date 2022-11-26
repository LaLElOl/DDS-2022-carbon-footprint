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

    public List buscarPorSector(String sector_id) {
        return EntityManagerHelper
                .getEntityManager()
                .createQuery("from " + Miembro.class.getName() + " where id =(Select miembro_id from empleados_sector where sector_id =" + sector_id + ")")
                .getResultList();
    }

}
