package controllers;

import models.dominio.ReporteHuellaCarbono;
import models.dominio.TipoUsuario;
import models.dominio.Usuario;
import models.dominio.organizacion.AgenteMunicipal;
import models.dominio.organizacion.AgenteProvincial;
import models.dominio.organizacion.Organizacion;
import models.dominio.organizacion.datos.EPeriodicidad;
import models.repositorios.RepositorioDeAgentesMunicipales;
import models.repositorios.RepositorioDeAgentesProvinciales;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;

public class AgenteProvincialController {

    private RepositorioDeAgentesProvinciales repositorioAgenteProvincial;
    private RepositorioDeAgentesMunicipales repositorioDeAgentesMunicipales;

    public AgenteProvincialController(){
        repositorioAgenteProvincial = new RepositorioDeAgentesProvinciales();
        repositorioDeAgentesMunicipales = new RepositorioDeAgentesMunicipales();
    }

    public ModelAndView mostrarTodos(Request request, Response response) {
        List<AgenteProvincial> todosLosAgentesProvinciales = this.repositorioAgenteProvincial.buscarTodos();
        return new ModelAndView(new HashMap<String, Object>(){{
            put("agente_provincial", todosLosAgentesProvinciales);
        }}, "agentes_provinciales.hbs");
    }

    public ModelAndView mostrar(Request request, Response response) {
        String idBuscado = request.params("id");
        AgenteProvincial agenteProvincialBuscado = this.repositorioAgenteProvincial.buscar(new Integer(idBuscado));

        return new ModelAndView(new HashMap<String, Object>(){{
            put("agente_provincial", agenteProvincialBuscado);
        }}, "agente_provincial.hbs");
    }

    public ModelAndView crear(Request request, Response response) {
        return new ModelAndView(null, "form_agente_provincial.hbs");
    }

    public Response guardar(Request request,Response response) {

        Usuario usuario = new Usuario();
        AgenteProvincial agenteProvincial = new AgenteProvincial();

        usuario.setNickname(request.queryParams("usuario"));
        usuario.setContrasenia(request.queryParams("contrasenia"));
        usuario.setTipoUsuario(TipoUsuario.AGENTE_PROVINCIAL);
        usuario.setEmail(request.queryParams("email"));
        agenteProvincial.setNombre(request.queryParams("nombre"));
        agenteProvincial.setProvincia(request.queryParams("provincia"));
        agenteProvincial.setUsuario(usuario);

        this.repositorioAgenteProvincial.guardar(agenteProvincial);

        response.redirect("/home");
        return response;
    }

    public ModelAndView editar(Request request, Response response) {
        String idBuscado = request.params("id");
        AgenteProvincial agenteProvincialBuscado = this.repositorioAgenteProvincial.buscar(new Integer(idBuscado));

        return new ModelAndView(new HashMap<String, Object>(){{
            put("agente_provincial", agenteProvincialBuscado);
        }}, "form_agente_provincial.hbs");
    }


    public Response modificar(Request request, Response response) {
        String idBuscado = request.params("id");
        AgenteProvincial agenteProvincialBuscado = this.repositorioAgenteProvincial.buscar(new Integer(idBuscado));

        agenteProvincialBuscado.setNombre(request.queryParams("nombre"));
        agenteProvincialBuscado.setProvincia(request.queryParams("provincia"));

        this.repositorioAgenteProvincial.guardar(agenteProvincialBuscado);

        response.redirect("/agente_provincial");
        return response;
    }

    public ModelAndView mostrarAgMunicipales(Request request, Response response) {
        Integer id = new Integer(request.session().attribute("id"));
        AgenteProvincial agenteProvincial = this.repositorioAgenteProvincial.buscarPorUsuario(id);
        List<AgenteMunicipal> agMunicipales = this.repositorioDeAgentesMunicipales.buscarPorAgenteProvincial(agenteProvincial.getId());
        return new ModelAndView(new HashMap<String, Object>(){{
            put("agente_municipal", agMunicipales);
        }}, "agentes_municipales.hbs");
    }

    public ModelAndView verReporteProvincias(Request request, Response response) {
        List<AgenteProvincial> todosLosAgentesProvinciales = this.repositorioAgenteProvincial.buscarTodos();
        return new ModelAndView(new HashMap<String, Object>(){{
            put("agente_provincial", todosLosAgentesProvinciales);
        }}, "huella_provincias.hbs");
    }

    public ModelAndView mostrarHuellaDeCarbono(Request request, Response response) {
        return new ModelAndView(null, "/huella_provincial.hbs");
    }

    public ModelAndView mostrarHuellaDeCarbonoMensual(Request request, Response response) {
        Integer id = new Integer(request.session().attribute("id"));
        AgenteProvincial agenteProvincial = this.repositorioAgenteProvincial.buscarPorUsuario(id);
        List<Integer> anios = agenteProvincial.aniosDatosConsumos();

        return new ModelAndView(new HashMap<String, Object>(){{
            put("fechaMensual",agenteProvincial.getFechaUltimoCalculoHuellaMensual());
            put("valorMensual",agenteProvincial.getHuellaCarbonoActualMensual());
            put("anio",anios);
        }}, "huella_mensual.hbs");
    }

    public ModelAndView mostrarHuellaDeCarbonoAnual(Request request, Response response) {
        Integer id = new Integer(request.session().attribute("id"));
        AgenteProvincial agenteProvincial = this.repositorioAgenteProvincial.buscarPorUsuario(id);
        List<Integer> anios = agenteProvincial.aniosDatosConsumos();

        return new ModelAndView(new HashMap<String, Object>(){{
            put("fechaAnual",agenteProvincial.getFechaUltimoCalculoHuellaAnual());
            put("valorAnual",agenteProvincial.getHuellaCarbonoActualAnual());
            put("anio",anios);
        }}, "huella_anual.hbs");
    }


    public Response calcularHuellaCarbonoMensual(Request request, Response response) {

        Integer mes = new Integer(request.queryParams("mes"));

        Integer id = new Integer(request.session().attribute("id"));
        AgenteProvincial agenteProvincial = this.repositorioAgenteProvincial.buscarPorUsuario(id);

        Integer anio = new Integer(request.queryParams("anio"));
        agenteProvincial.calcularHuella(mes,anio);

        this.repositorioAgenteProvincial.guardar(agenteProvincial);


        response.redirect("/agente_provincial/huella_mensual");
        return response;
    }


    public Response calcularHuellaCarbonoAnual(Request request, Response response) {


        Integer id = new Integer(request.session().attribute("id"));
        AgenteProvincial agenteProvincial = this.repositorioAgenteProvincial.buscarPorUsuario(id);

        int anio = new Integer(request.queryParams("anio"));
        agenteProvincial.calcularHuella(0,anio);

        this.repositorioAgenteProvincial.guardar(agenteProvincial);

        response.redirect("/agente_provincial/huella_anual");
        return response;
    }

    public ModelAndView mostrarTotalHuella(Request request, Response response){


        Integer id = new Integer(request.session().attribute("id"));
        AgenteProvincial agenteProvincial = this.repositorioAgenteProvincial.buscarPorUsuario(id);

        Double huellaTotal = agenteProvincial.huellaTotal();
        agenteProvincial.setHuellaTotal(huellaTotal);
        this.repositorioAgenteProvincial.guardar(agenteProvincial);


        return new ModelAndView(new HashMap<String, Object>(){{
            put("provincia",agenteProvincial.getProvincia());
            put("huella", agenteProvincial.huellaTotal());
        }}, "total_huella_provincia.hbs");
    }
}
