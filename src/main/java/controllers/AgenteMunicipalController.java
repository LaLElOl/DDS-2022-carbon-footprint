package controllers;

import com.twilio.rest.api.v2010.account.incomingphonenumber.Local;
import models.dominio.ReporteHuellaCarbono;
import models.dominio.TipoUsuario;
import models.dominio.Usuario;
import models.dominio.organizacion.AgenteMunicipal;
import models.dominio.organizacion.AgenteProvincial;
import models.dominio.organizacion.Organizacion;
import models.dominio.organizacion.datos.EPeriodicidad;
import models.repositorios.RepositorioDeAgentesMunicipales;
import models.repositorios.RepositorioDeAgentesProvinciales;
import models.repositorios.RepositorioDeOrganizaciones;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.List;

public class AgenteMunicipalController {

    private RepositorioDeAgentesMunicipales repositorioAgenteMunicipal;
    private RepositorioDeAgentesProvinciales repositorioDeAgentesProvinciales;
    private RepositorioDeOrganizaciones repositorioDeOrganizaciones;

    public AgenteMunicipalController(){
        repositorioAgenteMunicipal = new RepositorioDeAgentesMunicipales();
        repositorioDeAgentesProvinciales =  new RepositorioDeAgentesProvinciales();
        repositorioDeOrganizaciones = new RepositorioDeOrganizaciones();
    }

    public ModelAndView mostrarTodos(Request request, Response response) {
        List<AgenteMunicipal> todosLosAgentesMunicipales = this.repositorioAgenteMunicipal.buscarTodos();
        return new ModelAndView(new HashMap<String, Object>(){{
            put("agente_municipal", todosLosAgentesMunicipales);
        }}, "agentes_municipales.hbs");
    }
    public ModelAndView mostrarOrganizaciones(Request request, Response response) {
        Integer id = new Integer(request.session().attribute("id"));
        AgenteMunicipal agenteMunicipal = repositorioAgenteMunicipal.buscarPorUsuario(id);
        List<Organizacion> organizaciones = this.repositorioDeOrganizaciones.buscarPorAgenteMunicipal(agenteMunicipal.getId());
        return new ModelAndView(new HashMap<String, Object>(){{
            put("organizacion", organizaciones);
        }}, "organizaciones.hbs");
    }



    public ModelAndView mostrar(Request request, Response response) {
        String idBuscado = request.params("id");
        AgenteMunicipal agenteMunicipalBuscado = this.repositorioAgenteMunicipal.buscar(new Integer(idBuscado));

        return new ModelAndView(new HashMap<String, Object>(){{
            put("agente_municipal", agenteMunicipalBuscado);
        }}, "agente_municipal.hbs");//TODO
    }

    public ModelAndView crear(Request request, Response response) {
        List<AgenteProvincial> agentesProvinciales = this.repositorioDeAgentesProvinciales.buscarTodos();
        return new ModelAndView(new HashMap<String, Object>(){{
            put("agente_provincial", agentesProvinciales);
        }}, "form_agente_municipal.hbs");
    }

    public Response guardar(Request request,Response response) {

        Usuario usuario = new Usuario();
        AgenteMunicipal agenteMunicipal = new AgenteMunicipal();
        Integer id_provincial = Integer.valueOf(request.queryParams("id_provincial"));
        AgenteProvincial agenteProvincial = this.repositorioDeAgentesProvinciales.buscar(id_provincial);

        usuario.setNickname(request.queryParams("usuario"));
        usuario.setContrasenia(request.queryParams("contrasenia"));
        usuario.setEmail(request.queryParams("email"));
        usuario.setTipoUsuario(TipoUsuario.AGENTE_MUNICIPAL);
        agenteMunicipal.setNombre(request.queryParams("nombre"));
        agenteMunicipal.setMunicipio(request.queryParams("municipio"));
        agenteMunicipal.setUsuario(usuario);
        agenteMunicipal.setAgenteProvincial(agenteProvincial);
        agenteMunicipal.setFechaUltimoCalculoHuellaAnual(LocalDate.now());
        agenteMunicipal.setFechaUltimoCalculoHuellaMensual(LocalDate.now());
        agenteMunicipal.setHuellaCarbonoActualAnual(0.0);
        agenteMunicipal.setHuellaCarbonoActualMensual(0.0);

        agenteProvincial.agregarAgentesMunicipales(agenteMunicipal);


        this.repositorioAgenteMunicipal.guardar(agenteMunicipal);
        this.repositorioDeAgentesProvinciales.guardar(agenteProvincial);

        response.redirect("/home");
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

    public Response calcularHuellaCarbono(Request request, Response response) {

        int mes = new Integer(request.queryParams("mes"));
        EPeriodicidad periodicidad = EPeriodicidad.ANUAL;
        LocalDate fecha = LocalDate.now();
        if(mes != 0) {
            mes = fecha.getMonthValue();
            periodicidad = EPeriodicidad.MENSUAL;
        }
        Integer id = new Integer(request.session().attribute("id"));
        AgenteMunicipal agenteMunicipal = this.repositorioAgenteMunicipal.buscarPorUsuario(id);

        int anio = fecha.getYear();
        agenteMunicipal.calcularHuella(mes,anio);

        this.repositorioAgenteMunicipal.guardar(agenteMunicipal);

        response.redirect("/agente_municipal/huella_carbono");
        return response;
    }

    public ModelAndView mostrarHuellaCarbono(Request request, Response response){

        Integer id = new Integer(request.session().attribute("id"));
        AgenteMunicipal agenteMunicipal = this.repositorioAgenteMunicipal.buscarPorUsuario(id);

        return new ModelAndView(new HashMap<String, Object>(){{
            put("fechaMensual",agenteMunicipal.getFechaUltimoCalculoHuellaMensual());
            put("valorMensual",agenteMunicipal.getHuellaCarbonoActualMensual());
            put("fechaAnual",agenteMunicipal.getFechaUltimoCalculoHuellaAnual());
            put("valorAnual",agenteMunicipal.getHuellaCarbonoActualAnual());
        }}, "huella_carbono.hbs");
    }

    public ModelAndView mostrarTotalHuella(Request request, Response response){

        Integer id = new Integer(request.session().attribute("id"));
        AgenteMunicipal agenteMunicipal = this.repositorioAgenteMunicipal.buscarPorUsuario(id);
        Double huellaTotal = agenteMunicipal.huellaTotal();

        return new ModelAndView(new HashMap<String, Object>(){{
            put("municipio",agenteMunicipal.getMunicipio());
            put("huella",huellaTotal);
        }}, "total_huella_municipio.hbs");
    }

    public ModelAndView mostrarComposicionHuella(Request request, Response response){

        Integer id = new Integer(request.session().attribute("id"));
        AgenteMunicipal agenteMunicipal = this.repositorioAgenteMunicipal.buscarPorUsuario(id);

        return new ModelAndView(new HashMap<String, Object>(){{
            put("municipio",agenteMunicipal.getMunicipio());
            put("fija",1);
            put("movil",1);
            put("electricidad",1);
            put("logistica",1);
        }}, "composicion_huella_municipio.hbs");
    }

    public ModelAndView mostrarEvolucionHuella(Request request, Response response){

        Integer id = new Integer(request.session().attribute("id"));
        AgenteMunicipal agenteMunicipal = this.repositorioAgenteMunicipal.buscarPorUsuario(id);

        return new ModelAndView(new HashMap<String, Object>(){{
            put("municipio",agenteMunicipal.getMunicipio());
            put("enero",1);
            put("febrero",1);
            put("marzo",1);
            put("abril",1);
            put("mayo",1);
            put("junio",1);
            put("julio",1);
            put("agosto",1);
            put("septiembre",1);
            put("octubre",1);
            put("noviembre",1);
            put("diciembre",1);
        }}, "evolucion_huella_municipio.hbs");
    }
}
