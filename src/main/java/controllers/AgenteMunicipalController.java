package controllers;

import models.dominio.TipoUsuario;
import models.dominio.Usuario;
import models.dominio.organizacion.AgenteMunicipal;
import models.repositorios.RepositorioDeAgentesMunicipales;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

import java.util.HashMap;
import java.util.List;

public class AgenteMunicipalController {

    private RepositorioDeAgentesMunicipales repositorioAgenteMunicipal;

    public AgenteMunicipalController(){repositorioAgenteMunicipal = new RepositorioDeAgentesMunicipales();}

    public ModelAndView mostrarTodos(Request request, Response response) {
        List<AgenteMunicipal> todosLosAgentesMunicipales = this.repositorioAgenteMunicipal.buscarTodos();
        return new ModelAndView(new HashMap<String, Object>(){{
            put("agente_municipal", todosLosAgentesMunicipales);
        }}, "agentes_municipales.hbs");
    }


    public ModelAndView mostrar(Request request, Response response) {
        String idBuscado = request.params("id");
        AgenteMunicipal agenteMunicipalBuscado = this.repositorioAgenteMunicipal.buscar(new Integer(idBuscado));

        return new ModelAndView(new HashMap<String, Object>(){{
            put("agente_municipal", agenteMunicipalBuscado);
        }}, "agente_municipal.hbs");//TODO
    }

    public ModelAndView crear(Request request, Response response) {
        return new ModelAndView(null, "form_agente_municipal.hbs");
    }

    public Response guardar(Request request,Response response) {

        Usuario usuario = new Usuario();
        AgenteMunicipal agenteMunicipal = new AgenteMunicipal();

        usuario.setNickname(request.queryParams("usuario"));
        usuario.setContrasenia(request.queryParams("contrasenia"));
        usuario.setTipoUsuario(TipoUsuario.AGENTE_MUNICIPAL);
        agenteMunicipal.setNombre(request.queryParams("nombre"));
        agenteMunicipal.setMunicipio(request.queryParams("municipio"));
        agenteMunicipal.setUsuario(usuario);


        this.repositorioAgenteMunicipal.guardar(agenteMunicipal);

        response.redirect("/home_agente");
        return response;
    }

    public ModelAndView editar(Request request, Response response) {
        String idBuscado = request.params("id");
        AgenteMunicipal agenteMunicipalBuscado = this.repositorioAgenteMunicipal.buscar(new Integer(idBuscado));

        return new ModelAndView(new HashMap<String, Object>(){{
            put("agente_municipal", agenteMunicipalBuscado);
        }}, "form_agente_municipal.hbs");//TODO
    }


    public Response modificar(Request request, Response response) {
        String idBuscado = request.params("id");
        AgenteMunicipal agenteMunicipalBuscado = this.repositorioAgenteMunicipal.buscar(new Integer(idBuscado));

        agenteMunicipalBuscado.setNombre(request.queryParams("nombre"));
        agenteMunicipalBuscado.setMunicipio(request.queryParams("municipio"));

        this.repositorioAgenteMunicipal.guardar(agenteMunicipalBuscado);

        response.redirect("/agente_municipal");
        return response;
    }
}
