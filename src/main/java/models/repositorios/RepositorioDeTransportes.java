package models.repositorios;

import helpers.EntityManagerHelper;
import models.dominio.transporte.medios.Transporte;

import java.util.List;

public class RepositorioDeTransportes {

    public List buscarTodos() {
        return EntityManagerHelper
                .getEntityManager()
                .createQuery("from " + Transporte.class.getName())
                .getResultList();
    }

    public Transporte buscar(Integer id) {
        return EntityManagerHelper
                .getEntityManager()
                .find(Transporte.class, id);
    }

    public void guardar(Transporte transporte) {
        EntityManagerHelper.beginTransaction();
        EntityManagerHelper
                .getEntityManager()
                .persist(transporte);
        EntityManagerHelper.commit();
    }


}
