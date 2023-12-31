package models.repositorios;

import helpers.EntityManagerHelper;
import models.dominio.organizacion.Clasificacion;
import models.dominio.organizacion.Organizacion;
import models.dominio.organizacion.TipoOrganizacion;

import java.util.List;


public class RepositorioDeOrganizaciones {

    public List buscarTodos() {
        return EntityManagerHelper
                .getEntityManager()
                .createQuery("from " + Organizacion.class.getName())
                .getResultList();
    }

    public Organizacion buscar(Integer id) {
        return EntityManagerHelper
                .getEntityManager()
                .find(Organizacion.class, id);
    }

    public void guardar(Organizacion organizacion) {
        EntityManagerHelper.beginTransaction();

        EntityManagerHelper.persist(organizacion.getUsuario());
        EntityManagerHelper.persist(organizacion.getClasificacion());
        EntityManagerHelper.persist(organizacion.getUbicacion());
        EntityManagerHelper.persist(organizacion);

        EntityManagerHelper.commit();
    }

    public Organizacion buscarPorUsuario(Integer id) {
        return (Organizacion) EntityManagerHelper
                .getEntityManager()
                .createQuery("from " + Organizacion.class.getName() + " where usuario_id =" + id)
                .getSingleResult();
    }

    public List buscarPorAgenteMunicipal(Integer id) {
        return  EntityManagerHelper
                .getEntityManager()
                .createQuery("from " + Organizacion.class.getName() + " where agente_municipal_id =" + id)
                .getResultList();
    }

    /*public List buscarPorTipo(TipoOrganizacion tipoOrganizacion) {
        return  EntityManagerHelper
                .getEntityManager()
                .createQuery("from " + Organizacion.class.getName() + "," + Clasificacion.class.getName() +
                         " tipo_organizacion =" + tipoOrganizacion)
                .getResultList();
    }*/


}
