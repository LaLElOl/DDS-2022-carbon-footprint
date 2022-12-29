package models.repositorios;

import helpers.EntityManagerHelper;
import models.dominio.organizacion.AgenteMunicipal;
import models.dominio.organizacion.Organizacion;

import java.util.List;

public class RepositorioDeAgentesMunicipales {

    public List buscarTodos() {
        return EntityManagerHelper
                .getEntityManager()
                .createQuery("from " + AgenteMunicipal.class.getName())
                .getResultList();
    }

    public AgenteMunicipal buscar(Integer id) {
        return EntityManagerHelper
                .getEntityManager()
                .find(AgenteMunicipal.class, id);
    }

    public void guardar(AgenteMunicipal agenteMunicipal) {
        EntityManagerHelper.beginTransaction();

        EntityManagerHelper.persist(agenteMunicipal.getUsuario());
        EntityManagerHelper.persist(agenteMunicipal);

        EntityManagerHelper.commit();
    }

    public AgenteMunicipal buscarPorUsuario(Integer id) {
        return (AgenteMunicipal) EntityManagerHelper
                .getEntityManager()
                .createQuery("from " + AgenteMunicipal.class.getName() + " where usuario_id =" + id)
                .getSingleResult();
    }

    public List buscarPorAgenteProvincial(Integer id) {
        return  EntityManagerHelper
                .getEntityManager()
                .createQuery("from " + AgenteMunicipal.class.getName() + " where agente_provincial_id =" + id)
                .getResultList();
    }


}
