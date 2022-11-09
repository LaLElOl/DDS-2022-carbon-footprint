package models.repositorios;

import helpers.EntityManagerHelper;
import models.dominio.Usuario;

public class RepositorioDeUsuarios {

    public Usuario buscar(Integer id) {
        return EntityManagerHelper
                .getEntityManager()
                .find(Usuario.class, id);
    }

}
