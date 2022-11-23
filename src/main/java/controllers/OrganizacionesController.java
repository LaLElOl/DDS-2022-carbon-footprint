package controllers;

import models.dominio.TipoUsuario;
import models.dominio.Usuario;
import models.dominio.organizacion.AgenteMunicipal;
import models.dominio.organizacion.Clasificacion;
import models.dominio.organizacion.Organizacion;
import models.dominio.organizacion.TipoOrganizacion;
import models.dominio.organizacion.datos.DatoConsumo;
import models.dominio.transporte.Ubicacion;
import models.repositorios.RepositorioDeAgentesMunicipales;
import models.repositorios.RepositorioDeOrganizaciones;
import models.services.lectorExcel.ApachePOIExcel;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

import java.io.IOException;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Objects;

public class OrganizacionesController {

    private RepositorioDeOrganizaciones repositorioDeOrganizaciones;
    private RepositorioDeAgentesMunicipales repositorioDeAgentesMunicipales;
    private ApachePOIExcel apachePoi;

    public OrganizacionesController(){
        repositorioDeOrganizaciones = new RepositorioDeOrganizaciones();
        repositorioDeAgentesMunicipales = new RepositorioDeAgentesMunicipales();
        apachePoi = new ApachePOIExcel();
    }

    public ModelAndView mostrarTodos(Request request, Response response) {
        List<Organizacion> todosLasOrganizaciones = this.repositorioDeOrganizaciones.buscarTodos();
        return new ModelAndView(new HashMap<String, Object>(){{
            put("organizacion", todosLasOrganizaciones);
        }}, "organizaciones.hbs");
    }

    public ModelAndView mostrar(Request request, Response response) {
        String idBuscado = request.params("id");
        Organizacion organizacionBuscada = this.repositorioDeOrganizaciones.buscar(new Integer(idBuscado));

        //TODO: ver porque la session es null
        if(organizacionBuscada.getUsuario().getId() != request.session().attribute("id")){
            response.redirect("/index");
        }

        return new ModelAndView(new HashMap<String, Object>(){{
            put("organizacion", organizacionBuscada);
            //put("cant_tareas", servicioBuscado.cantTareas());
        }}, "organizacion.hbs");
    }

    public ModelAndView crear(Request request, Response response) {
        List<AgenteMunicipal> agentesMunicipales = this.repositorioDeAgentesMunicipales.buscarTodos();
        return new ModelAndView(new HashMap<String,Object>(){{
            put("agenteMunicipal",agentesMunicipales);
        }}, "form_organizacion.hbs");
    }

    public Response guardar(Request request,Response response) {

        Organizacion org = new Organizacion();
        Clasificacion clasif = new Clasificacion();
        Usuario usuario = new Usuario();
        Ubicacion ubicacion = new Ubicacion();
        AgenteMunicipal agMun;

        if(!Objects.equals(request.queryParams("agente_municipal"), "null")){
            agMun = this.repositorioDeAgentesMunicipales.buscar(new Integer(request.queryParams("agente_municipal")));
            org.setAgenteMunicipal(agMun);
        }
        usuario.setNickname(request.queryParams("usuario"));
        usuario.setContrasenia(request.queryParams("contrasenia"));
        usuario.setEmail(request.queryParams("email"));
        usuario.setTipoUsuario(TipoUsuario.ORGANIZACION);
        org.setCuit(request.queryParams("cuit"));
        org.setRazonSocial(request.queryParams("razon_social"));
        TipoOrganizacion tipo = TipoOrganizacion.valueOf(request.queryParams("tipo_organizacion").toUpperCase(Locale.ROOT));
        ubicacion.setLocalidad(Integer.valueOf(request.queryParams("codigo_postal")));
        ubicacion.setCalle(request.queryParams("calle"));
        ubicacion.setAltura(Integer.valueOf(request.queryParams("altura")));
        clasif.setClasificacion(request.queryParams("clasificacion"));
        clasif.setTipoOrganizacion(tipo);
        org.setUsuario(usuario);
        org.setClasificacion(clasif);
        org.setUbicacion(ubicacion);

        this.repositorioDeOrganizaciones.guardar(org);

        response.redirect("/home");
        return response;
    }


    public ModelAndView editar(Request request, Response response) {
        String idBuscado = request.params("id");
        Organizacion organizacionBuscada = this.repositorioDeOrganizaciones.buscar(new Integer(idBuscado));
        List<AgenteMunicipal> agentesMunicipales = this.repositorioDeAgentesMunicipales.buscarTodos();

        return new ModelAndView(new HashMap<String, Object>(){{
            put("organizacion", organizacionBuscada);
            put("agenteMunicipal", agentesMunicipales);
            //put("cant_tareas", servicioBuscado.cantTareas());
        }}, "form_organizacion.hbs");
    }

    public Response modificar(Request request, Response response) {
        String idBuscado = request.params("id");
        Organizacion organizacionBuscada = this.repositorioDeOrganizaciones.buscar(new Integer(idBuscado));
        Clasificacion clasif = new Clasificacion();
        Ubicacion ubicacion = new Ubicacion();
        AgenteMunicipal agMun;

        //TODO: ver si entra por este caso
        if(!Objects.equals(request.queryParams("agente_municipal"), "null")){
            agMun = this.repositorioDeAgentesMunicipales.buscar(new Integer(request.queryParams("agente_municipal")));
            organizacionBuscada.setAgenteMunicipal(agMun);
        }

        organizacionBuscada.setCuit(request.queryParams("cuit"));
        organizacionBuscada.setRazonSocial(request.queryParams("razon_social"));
        TipoOrganizacion tipo = TipoOrganizacion.valueOf(request.queryParams("tipo_organizacion").toUpperCase(Locale.ROOT));
        clasif.setTipoOrganizacion(tipo);
        ubicacion.setLocalidad(Integer.valueOf(request.queryParams("codigo_postal")));
        ubicacion.setCalle(request.queryParams("calle"));
        ubicacion.setAltura(Integer.valueOf(request.queryParams("altura")));
        clasif.setClasificacion(request.queryParams("clasificacion"));
        organizacionBuscada.setClasificacion(clasif);
        organizacionBuscada.setUbicacion(ubicacion);

        this.repositorioDeOrganizaciones.guardar(organizacionBuscada);

        response.redirect("/organizacion/"+request.params("id"));
        return response;
    }

    public ModelAndView excel(Request request, Response response) {
        return new ModelAndView(null, "cargar_excel.hbs");
    }

    public Response guardarConsumo(Request request, Response response) throws IOException {
        String path = request.queryParams("excel");
        Integer id = request.session().attribute("id");
        Organizacion org = this.repositorioDeOrganizaciones.buscarPorUsuario(id);
        List<DatoConsumo> datosConsumo = this.apachePoi.leerExcel(path,org);
        List<DatoConsumo> datosConsumoAnteriores = org.getDatosConsumo();

        this.repositorioDeOrganizaciones.guardar(org);
        response.redirect("/home");
        return response;
    }

    public ModelAndView mostrarHuellaCarbono(Request request, Response response){

        Integer id = new Integer(request.session().attribute("id"));
        Organizacion org = this.repositorioDeOrganizaciones.buscarPorUsuario(id);

        return new ModelAndView(new HashMap<String, Object>(){{
            put("fechaMensual",org.getFechaUltimoCalculoHuellaMensual());
            put("valorMensual",org.getHuellaCarbonoActualMensual());
            put("fechaAnual",org.getFechaUltimoCalculoHuellaAnual());
            put("valorAnual",org.getHuellaCarbonoActualAnual());
        }}, "huella_carbono.hbs");
    }

    public Response calcularHuellaCarbono(Request request, Response response) {

        int mes = new Integer(request.queryParams("mes"));
        if(mes != 0)
            mes = LocalDate.now().getMonthValue();
        Integer id = new Integer(request.session().attribute("id"));
        Organizacion org = this.repositorioDeOrganizaciones.buscarPorUsuario(id);

        int anio = LocalDate.now().getYear();
        org.calcularHuella(mes,anio);

        this.repositorioDeOrganizaciones.guardar(org);

        response.redirect("/organizacion/huella_carbono");
        return response;
    }
}
