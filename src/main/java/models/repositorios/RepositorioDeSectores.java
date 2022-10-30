package models.repositorios;

import helpers.EntityManagerHelper;
import models.dominio.organizacion.Sector;

import java.util.List;

public class RepositorioDeSectores {

    public List buscarTodos() {
        return EntityManagerHelper
                .getEntityManager()
                .createQuery("from " + Sector.class.getName())
                .getResultList();
    }

    public Sector buscar(Integer id) {
        return EntityManagerHelper
                .getEntityManager()
                .find(Sector.class, id);
    }

    public void guardar(Sector sector) {
        EntityManagerHelper.beginTransaction();
        EntityManagerHelper
                .getEntityManager()
                .persist(sector);
        EntityManagerHelper.commit();
    }


}
