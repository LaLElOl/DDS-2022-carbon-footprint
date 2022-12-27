package controllers;

import models.dominio.ReporteHuellaCarbono;
import models.dominio.TipoUsuario;
import models.dominio.Usuario;
import models.dominio.organizacion.AgenteMunicipal;
import models.dominio.organizacion.Clasificacion;
import models.dominio.organizacion.Organizacion;
import models.dominio.organizacion.TipoOrganizacion;
import models.dominio.organizacion.datos.DatoConsumo;
import models.dominio.organizacion.datos.EPeriodicidad;
import models.dominio.transporte.Ubicacion;
import models.repositorios.RepositorioDeAgentesMunicipales;
import models.repositorios.RepositorioDeDatosConsumo;
import models.repositorios.RepositorioDeOrganizaciones;
import models.repositorios.RepositorioDeReportes;
import models.services.lectorExcel.ApachePOIExcel;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

import javax.servlet.MultipartConfigElement;
import javax.servlet.ServletException;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.stream.Collectors;

public class OrganizacionesController {

    private RepositorioDeOrganizaciones repositorioDeOrganizaciones;
    private RepositorioDeAgentesMunicipales repositorioDeAgentesMunicipales;
    private ApachePOIExcel apachePoi;
    private RepositorioDeDatosConsumo repositorioDeDatosConsumo;
    private RepositorioDeReportes repositorioDeReportes;

    public OrganizacionesController(){
        repositorioDeOrganizaciones = new RepositorioDeOrganizaciones();
        repositorioDeAgentesMunicipales = new RepositorioDeAgentesMunicipales();
        repositorioDeDatosConsumo = new RepositorioDeDatosConsumo();
        repositorioDeReportes = new RepositorioDeReportes();
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

        usuario.setNickname(request.queryParams("usuario"));
        usuario.setContrasenia(request.queryParams("contrasenia"));
        usuario.setEmail(request.queryParams("email"));
        usuario.setTipoUsuario(TipoUsuario.ORGANIZACION);
        org.setCuit(request.queryParams("cuit"));
        org.setRazonSocial(request.queryParams("razon_social"));
        TipoOrganizacion tipo = TipoOrganizacion.valueOf(request.queryParams("tipo_organizacion").toUpperCase(Locale.ROOT));
        ubicacion.setLocalidadId(Integer.valueOf(request.queryParams("codigo_postal")));
        ubicacion.setCalle(request.queryParams("calle"));
        ubicacion.setAltura(Integer.valueOf(request.queryParams("altura")));
        clasif.setClasificacion(request.queryParams("clasificacion"));
        clasif.setTipoOrganizacion(tipo);
        org.setUsuario(usuario);
        org.setClasificacion(clasif);
        org.setUbicacion(ubicacion);

        if(!Objects.equals(request.queryParams("agente_municipal"), "null")){
            agMun = this.repositorioDeAgentesMunicipales.buscar(new Integer(request.queryParams("agente_municipal")));
            org.setAgenteMunicipal(agMun);
            agMun.agregarOrganizaciones(org);
            this.repositorioDeOrganizaciones.guardar(org);
            this.repositorioDeAgentesMunicipales.guardar(agMun);
        }
        else{
            this.repositorioDeOrganizaciones.guardar(org);
        }

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
        ubicacion.setLocalidadId(Integer.valueOf(request.queryParams("codigo_postal")));
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

    public Response guardarConsumo(Request request, Response response) throws IOException, ServletException {
        Integer id = new Integer(request.session().attribute("id"));
        Organizacion org = this.repositorioDeOrganizaciones.buscarPorUsuario(id);

        File uploadDir = new File("upload");
        uploadDir.mkdir(); // create the upload directory if it doesn't exist

        Path tempFile = Files.createTempFile(uploadDir.toPath(), "", ".xlsx");

        request.attribute("org.eclipse.jetty.multipartConfig", new MultipartConfigElement("/temp"));

        try (InputStream input = request.raw().getPart("excel").getInputStream()) { // getPart needs to use same "name" as input field in form
            Files.copy(input, tempFile, StandardCopyOption.REPLACE_EXISTING);
        }

        List<DatoConsumo> datosConsumo = this.apachePoi.leerExcel("upload/" + tempFile.getFileName().toString(),org);

        datosConsumo.forEach(dato -> this.repositorioDeDatosConsumo.guardar(dato));
        org.agregarDatosConsumo(datosConsumo);

        this.repositorioDeOrganizaciones.guardar(org);

        uploadDir.delete();

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
        EPeriodicidad periodicidad = EPeriodicidad.ANUAL;
        LocalDate fecha = LocalDate.now();
        if(mes != 0) {
            mes = fecha.getMonthValue();
            periodicidad = EPeriodicidad.MENSUAL;
        }
        Integer id = new Integer(request.session().attribute("id"));
        Organizacion org = this.repositorioDeOrganizaciones.buscarPorUsuario(id);

        int anio = fecha.getYear();
        org.calcularHuella(mes,anio);

        this.repositorioDeOrganizaciones.guardar(org);

        if(
                LocalDate.now().minus(30, ChronoUnit.DAYS).isBefore(org.getFechaUltimoCalculoHuellaMensual())
        ) {
            ReporteHuellaCarbono reporte = org.generarReporte(fecha, periodicidad);
            this.repositorioDeReportes.guardar(reporte);
        }

        response.redirect("/organizacion/huella_carbono");
        return response;
    }

    public ModelAndView mostrarHuellaDeCarbono(Request request, Response response) {
        return new ModelAndView(null, "/huella_de_carbono.hbs");
    }

    public ModelAndView mostrarHuellaDeCarbonoMensual(Request request, Response response) {
        Integer id = new Integer(request.session().attribute("id"));
        Organizacion org = this.repositorioDeOrganizaciones.buscarPorUsuario(id);
        return new ModelAndView(new HashMap<String, Object>(){{
            put("fechaMensual",org.getFechaUltimoCalculoHuellaMensual());
            put("valorMensual",org.getHuellaCarbonoActualMensual());
        }}, "/huella_mensual.hbs");
    }

    public ModelAndView mostrarHuellaDeCarbonoAnual(Request request, Response response) {
        Integer id = new Integer(request.session().attribute("id"));
        Organizacion org = this.repositorioDeOrganizaciones.buscarPorUsuario(id);

        return new ModelAndView(new HashMap<String, Object>(){{
            put("fechaAnual",org.getFechaUltimoCalculoHuellaAnual());
            put("valorAnual",org.getHuellaCarbonoActualAnual());
        }}, "/huella_anual.hbs");
    }


    public Response calcularHuellaCarbonoMensual(Request request, Response response) {

        int mes = new Integer(request.queryParams("mes"));
        EPeriodicidad periodicidad = EPeriodicidad.MENSUAL;
        LocalDate fecha = LocalDate.now();

        Integer id = new Integer(request.session().attribute("id"));
        Organizacion org = this.repositorioDeOrganizaciones.buscarPorUsuario(id);

        int anio = new Integer(request.queryParams("anio"));
        org.calcularHuella(mes,anio);

        this.repositorioDeOrganizaciones.guardar(org);

        /*if(
                LocalDate.now().minus(30, ChronoUnit.DAYS).isBefore(org.getFechaUltimoCalculoHuellaMensual())
        ) {*/
            ReporteHuellaCarbono reporte = org.generarReporte(fecha, periodicidad);
            this.repositorioDeReportes.guardar(reporte);
       // }

        response.redirect("/organizacion/huella_mensual");
        return response;
    }


    public Response calcularHuellaCarbonoAnual(Request request, Response response) {


        EPeriodicidad periodicidad = EPeriodicidad.ANUAL;
        LocalDate fecha = LocalDate.now();

        Integer id = new Integer(request.session().attribute("id"));
        Organizacion org = this.repositorioDeOrganizaciones.buscarPorUsuario(id);

        int anio = new Integer(request.queryParams("anio"));
        org.calcularHuella(0,anio);

        this.repositorioDeOrganizaciones.guardar(org);

        /*if(
                LocalDate.now().minus(30, ChronoUnit.DAYS).isBefore(org.getFechaUltimoCalculoHuellaMensual())
        ) {*/
        ReporteHuellaCarbono reporte = org.generarReporte(fecha, periodicidad);
        this.repositorioDeReportes.guardar(reporte);
        // }

        response.redirect("/organizacion/huella_anual");
        return response;
    }


    public ModelAndView mostrarComposicionHuella(Request request, Response response){

        Integer id = new Integer(request.session().attribute("id"));
        Organizacion org = this.repositorioDeOrganizaciones.buscarPorUsuario(id);
        double huellaFija = org.huellaSegunActividad("Combustion fija");
        double huellaMovil = org.huellaSegunActividad("Combustion movil");
        double huellaElecticidad = org.huellaSegunActividad("Electricidad adquirida y consumida");
        double huellaLogistica = org.huellaSegunActividad("Logística de productos y residuos");
        return new ModelAndView(new HashMap<String, Object>(){{
            put("organizacion",org.getRazonSocial());
            put("fija",huellaFija);
            put("movil",huellaMovil);
            put("electricidad",huellaElecticidad);
            put("logistica",huellaLogistica);
        }}, "composicion_huella_org.hbs");
    }

    public ModelAndView mostrarEvolucionHuella(Request request, Response response){

        Integer id = new Integer(request.session().attribute("id"));
        Organizacion org = this.repositorioDeOrganizaciones.buscarPorUsuario(id);
        List<DatoConsumo> datos = this.repositorioDeDatosConsumo.buscarTodos(org.getId().toString());
        //List<Integer> anios = (List<Integer>) datos.stream().mapToInt(DatoConsumo::anio).distinct().collect(Collectors.toList());
        List<Integer> anios = datos.stream().map(DatoConsumo::anio).distinct().collect(Collectors.toList());

        String anio = request.params("anio");

        if(anio != null){

            int year = new Integer(anio);

            String huellaEnero = new DecimalFormat("#.##").format(org.calcularHuella(1,year));
            Double huellaFebrero = org.calcularHuella(2,year);
            Double huellaMarzo = org.calcularHuella(3,year);
            Double huellaAbril = org.calcularHuella(4,year);
            Double huellaMayo = org.calcularHuella(5,year);
            Double huellaJunio = org.calcularHuella(6,year);
            Double huellaJulio = org.calcularHuella(7,year);
            String huellaAgosto = new DecimalFormat("#.##").format(org.calcularHuella(8,year));
            Double huellaSeptiembre = org.calcularHuella(9,year);
            Double huellaOctubre = org.calcularHuella(10,year);
            Double huellaNoviembre = org.calcularHuella(11,year);
            Double huellaDiciembre = org.calcularHuella(12,year);

            return new ModelAndView(new HashMap<String, Object>(){{
                put("anio",anios);
                put("organizacion",org.getRazonSocial());
                put("enero",huellaEnero);
                put("febrero",huellaFebrero.shortValue());
                put("marzo",huellaMarzo);
                put("abril",huellaAbril);
                put("mayo",huellaMayo);
                put("junio",huellaJunio);
                put("julio",huellaJulio);
                put("agosto",huellaAgosto);
                put("septiembre",huellaSeptiembre);
                put("octubre",huellaOctubre);
                put("noviembre",huellaNoviembre);
                put("diciembre",huellaDiciembre);
            }}, "evolucion_huella_org.hbs");

        }
        else{
            return new ModelAndView(new HashMap<String, Object>(){{
                put("anio",anios);
            }}, "evolucion_huella_org.hbs");
        }


    }

    public ModelAndView verReporteOrganizaciones(Request request, Response response) {
        double huellaGubernamental = huellaPorTipoOrg(TipoOrganizacion.GUBERNAMENTAL);
        double huellaONG = huellaPorTipoOrg(TipoOrganizacion.ONG);
        double huellaEmpresa = huellaPorTipoOrg(TipoOrganizacion.EMPRESA);
        double huellaInstitucion = huellaPorTipoOrg(TipoOrganizacion.INSTITUCION);

        return new ModelAndView(new HashMap<String, Object>(){{
            put("gubernamental", huellaGubernamental);
            put("ong",huellaONG);
            put("empresa",huellaEmpresa);
            put("institucion",huellaInstitucion);
        }}, "/huella_tipo_org.hbs");
    }

    public Double huellaPorTipoOrg(TipoOrganizacion tipoOrganizacion){
        double huellaGubernamental = 0.0;
        List<Organizacion> gubernamentales = this.repositorioDeOrganizaciones.buscarTodos();
        gubernamentales = gubernamentales.stream().filter(o->o.esTipoOrganizacion(tipoOrganizacion)).
                collect(Collectors.toList());

        huellaGubernamental += gubernamentales.stream().mapToDouble(Organizacion::huellaTotal).sum();

        return huellaGubernamental;
    }


}
