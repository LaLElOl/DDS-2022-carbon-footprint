package models.repositorios;

import helpers.EntityManagerHelper;
import models.dominio.organizacion.Organizacion;
import models.dominio.organizacion.Sector;
import models.dominio.organizacion.datos.DatoConsumo;

import java.util.List;


public class RepositorioDeDatosConsumo {


        public void guardar(DatoConsumo datoConsumo) {
            EntityManagerHelper.beginTransaction();
            EntityManagerHelper
                    .getEntityManager()
                    .persist(datoConsumo);
            EntityManagerHelper.commit();
        }

    public List buscarTodos(String org_id) {
        return EntityManagerHelper
                .getEntityManager()
                .createQuery("from " + DatoConsumo.class.getName() + " where organizacion_id = " + org_id )
                .getResultList();
    }
}