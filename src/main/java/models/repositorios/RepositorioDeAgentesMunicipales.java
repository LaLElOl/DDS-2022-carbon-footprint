package models.repositorios;

import helpers.EntityManagerHelper;
import models.dominio.organizacion.AgenteMunicipal;

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
        EntityManagerHelper
                .getEntityManager()
                .persist(agenteMunicipal);
        EntityManagerHelper.commit();
    }


}
