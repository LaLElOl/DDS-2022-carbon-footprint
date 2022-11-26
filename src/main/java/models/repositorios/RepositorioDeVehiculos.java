package models.repositorios;

import helpers.EntityManagerHelper;
import models.dominio.transporte.vehiculos.Vehiculo;

import java.util.List;

public class RepositorioDeVehiculos {

    public List buscarTodos(){
        return EntityManagerHelper
                .getEntityManager()
                .createQuery("from " + Vehiculo.class.getName())
                .getResultList();
    }

    public Vehiculo buscar(Integer id) {
        return EntityManagerHelper
                .getEntityManager()
                .find(Vehiculo.class, id);
    }

    public void guardar(Vehiculo vehiculo) {
        EntityManagerHelper.beginTransaction();
        EntityManagerHelper
                .getEntityManager()
                .persist(vehiculo);
        EntityManagerHelper.commit();
    }

    public List buscarTodosSegunMiembro(String miembro_id) {
        return EntityManagerHelper
                .getEntityManager()
                .createQuery("from " + Vehiculo.class.getName() + " where miembro_id = " + miembro_id )
                .getResultList();
    }


}
