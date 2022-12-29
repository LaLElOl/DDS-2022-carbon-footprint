package models.repositorios;

import helpers.EntityManagerHelper;
import models.dominio.organizacion.AgenteMunicipal;
import models.dominio.organizacion.AgenteProvincial;

import java.util.List;

public class RepositorioDeAgentesProvinciales {

    public List buscarTodos() {
        return EntityManagerHelper
                .getEntityManager()
                .createQuery("from " + AgenteProvincial.class.getName())
                .getResultList();
    }

    public AgenteProvincial buscar(Integer id) {
        return EntityManagerHelper
                .getEntityManager()
                .find(AgenteProvincial.class, id);
    }

    public void guardar(AgenteProvincial agenteProvincial) {
        EntityManagerHelper.beginTransaction();

        EntityManagerHelper.persist(agenteProvincial.getUsuario());
        EntityManagerHelper.persist(agenteProvincial);

        EntityManagerHelper.commit();
    }

    public AgenteProvincial buscarPorUsuario(Integer id) {
        return (AgenteProvincial) EntityManagerHelper
                .getEntityManager()
                .createQuery("from " + AgenteProvincial.class.getName() + " where usuario_id =" + id)
                .getSingleResult();
    }

}
