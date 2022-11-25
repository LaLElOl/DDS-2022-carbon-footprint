package models.repositorios;

import helpers.EntityManagerHelper;
import models.dominio.organizacion.Organizacion;
import models.dominio.organizacion.datos.DatoConsumo;


public class RepositorioDeDatosConsumo {


        public void guardar(DatoConsumo datoConsumo) {
            EntityManagerHelper.beginTransaction();
            EntityManagerHelper
                    .getEntityManager()
                    .persist(datoConsumo);
            EntityManagerHelper.commit();
        }

        public Organizacion buscarPorUsuario(Integer id) {
            return (Organizacion) EntityManagerHelper
                    .getEntityManager()
                    .createQuery("from " + Organizacion.class.getName() + " where" +  + id)
                    .getSingleResult();
        }
}