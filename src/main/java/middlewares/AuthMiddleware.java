package middlewares;

import models.dominio.TipoUsuario;
import models.dominio.Usuario;
import models.repositorios.RepositorioDeUsuarios;
import spark.Request;
import spark.Response;

public class AuthMiddleware {

    RepositorioDeUsuarios repositorioDeUsuarios =  new RepositorioDeUsuarios();

    public Response verificarSesion(Request request, Response response) {
        if(request.session().isNew() || request.session().attribute("id") == null) {
            response.redirect("/login");
        }
        return response;
    }

    public  Response verificarOrganizacion(Request request, Response response) {
        Integer id = Integer.parseInt(request.session().attribute("id"));
        Usuario usuario = repositorioDeUsuarios.buscar(id);
        if(usuario.getTipoUsuario() != TipoUsuario.ORGANIZACION) {
            response.redirect("/home");
        }
        return response;
    }

    public  Response verificarMiembro(Request request, Response response) {
        Integer id = Integer.parseInt(request.session().attribute("id"));
        Usuario usuario = repositorioDeUsuarios.buscar(id);
        if(usuario.getTipoUsuario() != TipoUsuario.MIEMBRO) {
            response.redirect("/home");
        }
        return response;
    }

    public  Response verificarAgenteMunicipal(Request request, Response response) {
        Integer id = Integer.parseInt(request.session().attribute("id"));
        Usuario usuario = repositorioDeUsuarios.buscar(id);
        if(usuario.getTipoUsuario() != TipoUsuario.AGENTE_MUNICIPAL) {
            response.redirect("/home");
        }
        return response;
    }

    public  Response verificarAgenteProvincial(Request request, Response response) {
        Integer id = Integer.parseInt(request.session().attribute("id"));
        Usuario usuario = repositorioDeUsuarios.buscar(id);
        if(usuario.getTipoUsuario() != TipoUsuario.AGENTE_PROVINCIAL) {
            response.redirect("/home");
        }
        return response;
    }

    public  Response verificarAdministrador(Request request, Response response) {
        Integer id = Integer.parseInt(request.session().attribute("id"));
        Usuario usuario = repositorioDeUsuarios.buscar(id);
        if(usuario.getTipoUsuario() != TipoUsuario.ADMINISTRADOR) {
            response.redirect("/home");
        }
        return response;
    }
}
