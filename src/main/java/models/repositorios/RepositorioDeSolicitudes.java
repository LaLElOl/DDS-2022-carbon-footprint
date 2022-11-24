package models.repositorios;

import helpers.EntityManagerHelper;
import models.dominio.organizacion.Solicitud;

import java.util.List;

public class RepositorioDeSolicitudes {

    public List buscarTodos() {
        return EntityManagerHelper
                .getEntityManager()
                .createQuery("from " + Solicitud.class.getName())
                .getResultList();
    }

    public Solicitud buscar(Integer id) {
        return EntityManagerHelper
                .getEntityManager()
                .find(Solicitud.class, id);
    }

    public void guardar(Solicitud solicitud) {
        EntityManagerHelper.beginTransaction();
        EntityManagerHelper.persist(solicitud);

        EntityManagerHelper.commit();
    }

    public Solicitud buscarPorUsuario(Integer id) {
        return (Solicitud) EntityManagerHelper
                .getEntityManager()
                .createQuery("from " + Solicitud.class.getName() + " where usuario_id =" + id)
                .getSingleResult();
    }
}
