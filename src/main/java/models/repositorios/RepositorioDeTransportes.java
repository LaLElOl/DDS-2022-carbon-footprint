package models.repositorios;

import helpers.EntityManagerHelper;
import models.dominio.transporte.medios.ServicioContratado;
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

    public List buscarServiciosContratados() {
        return EntityManagerHelper
                .getEntityManager()
                .createQuery("from " + Transporte.class.getName() + " where tipo_transporte ='servicio_contratado'")
                .getResultList();
    }

    public List buscarPublicos() {
        return EntityManagerHelper
                .getEntityManager()
                .createQuery("from " + Transporte.class.getName() + " where tipo_transporte ='publico'")
                .getResultList();
    }

    public ServicioContratado buscarServicioContratado(Integer id) {
        return EntityManagerHelper
                .getEntityManager()
                .find(ServicioContratado.class, id);
    }

}
