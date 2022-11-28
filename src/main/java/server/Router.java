package server;

import controllers.*;
import middlewares.AuthMiddleware;

import models.repositorios.RepositorioDeVehiculos;
import spark.utils.BooleanHelper;
import spark.utils.HandlebarsTemplateEngineBuilder;
import spark.Spark;
import spark.template.handlebars.HandlebarsTemplateEngine;

public class Router {
    private static HandlebarsTemplateEngine engine;

    private static void initEngine() {
        Router.engine = HandlebarsTemplateEngineBuilder
                .create()
                .withDefaultHelpers()
                .withHelper("isTrue", BooleanHelper.isTrue)
                .build();
    }

    public static void init() {
        Router.initEngine();
        Spark.staticFileLocation("/public");
        Router.configure();
    }

    private static void configure(){
        OrganizacionesController organizacionesController = new OrganizacionesController();
        AgenteMunicipalController agenteMunicipalController = new AgenteMunicipalController();
        AgenteProvincialController agenteProvincialController = new AgenteProvincialController();
        MiembroController miembroController = new MiembroController();
        SectorController sectorController = new SectorController();
        TrayectoController trayectoController =new TrayectoController();
        TramoController tramoController = new TramoController();
        TransporteController transporteController = new TransporteController();
        LoginController loginController = new LoginController();
        HomeController homeController = new HomeController();
        VehiculoController vehiculoController = new VehiculoController();
        SolicitudController solicitudController = new SolicitudController();
        ParadaController paradaController = new ParadaController();


        Spark.path("/home", () -> {
            Spark.before("",AuthMiddleware::verificarSesion);
            Spark.before("/*",AuthMiddleware::verificarSesion);

            Spark.get("", homeController::mostrarInicio,engine);
        });


        Spark.path("/login", () -> {
            Spark.get("", loginController::pantallaDeLogin, engine);
            Spark.post("", loginController::login);
            //Spark.post("/logout", loginController::logout);
        });
        Spark.get("/logout", loginController::logout);

        Spark.get("/signup", loginController::signup,engine);


        Spark.path("/administrador", () -> {

            Spark.get("/alta_contratado", transporteController::crearServicioContratado,engine);
            Spark.get("/alta_publico", transporteController::crearTransportePublico,engine);
            Spark.get("/publicos", transporteController::mostrarPublicos,engine);
            Spark.get("/servicios_contratados", transporteController::mostrarServiciosContratados,engine);
            Spark.get("/alta_parada", paradaController::crearParada,engine);
            Spark.get("/paradas", paradaController::mostrarParadas,engine);
            Spark.get("/publico/:id/agregar_parada", paradaController::agregarParada,engine);
            Spark.get("/publico/:id/paradas", paradaController::mostrarParadasTransporte,engine);


            Spark.post("/alta_contratado", transporteController::guardarServicioContratado);
            Spark.post("/alta_parada", paradaController::guardarParada);
            Spark.post("/alta_publico", transporteController::guardarTransportePublico);
            Spark.post("/publico/:id/agregar_parada", paradaController::guardarParadaTransporte);
        });

        //Organizaciones
        Spark.path("/organizacion", () -> {

            Spark.before("",AuthMiddleware::verificarSesion);
            Spark.before("/*",AuthMiddleware::verificarSesion);


            Spark.get("",organizacionesController::mostrarTodos, engine);
            Spark.get("/excel",organizacionesController::excel,engine);
            Spark.get("/alta_sector", sectorController::crear, engine);
            Spark.get("/sectores",sectorController::mostrarTodos, engine);
            Spark.get("/huella_carbono",organizacionesController::mostrarHuellaCarbono, engine);

            Spark.get("/editar/:id",organizacionesController::editar, engine);
            Spark.get("/sector/:id",sectorController::mostrar, engine);
            Spark.get("/:id",organizacionesController::mostrar, engine);

            Spark.get("/:id/sectores/:id_sector/miembros",miembroController::mostrarSegunSector,engine);
            Spark.get("/:id/sectores/:id_sector/solicitudes",solicitudController::mostrarSegunSector,engine);

            Spark.post("/alta_sector", sectorController::guardar);
            Spark.post("/huella_carbono",organizacionesController::calcularHuellaCarbono);
            Spark.post("/excel", organizacionesController::guardarConsumo);
            Spark.post("/editar/:id",organizacionesController::modificar);
            Spark.post("/:id/sectores/:id_sector/solicitudes",solicitudController::responderSolicitud);

        });

        Spark.get("/alta_organizacion", organizacionesController::crear, engine);
        Spark.post("/alta_organizacion", organizacionesController::guardar);


        //Agente Municipal
        Spark.path("/agente_municipal", () -> {

            Spark.before("",AuthMiddleware::verificarSesion);
            Spark.before("/*",AuthMiddleware::verificarSesion);

            Spark.get("",agenteMunicipalController::mostrarTodos, engine);
            Spark.get("/:id",agenteMunicipalController::mostrar, engine);
            Spark.get("/editar/:id",agenteMunicipalController::editar, engine);
            Spark.post("/editar/:id", agenteMunicipalController::modificar);

        });


        Spark.get("/alta_agente_municipal", agenteMunicipalController::crear, engine);
        Spark.post("/alta_agente_municipal", agenteMunicipalController::guardar);



        //Agente Provincial

        Spark.path("/agente_provincial", () -> {
            Spark.get("",agenteProvincialController::mostrarTodos, engine);
            Spark.get("/:id",agenteProvincialController::mostrar, engine);
            Spark.get("/editar/:id",agenteProvincialController::editar, engine);
            Spark.post("/agente_municipal/editar/:id", agenteProvincialController::modificar);

        });
        Spark.get("/alta_agente_provincial", agenteProvincialController::crear, engine);
        Spark.post("/alta_agente_provincial", agenteProvincialController::guardar);

        //Miembro
        Spark.path("/miembro", ()-> {

            Spark.before("",AuthMiddleware::verificarSesion);
            Spark.before("/*",AuthMiddleware::verificarSesion);

            Spark.get("", miembroController::mostrarTodos, engine);

            Spark.get("/unirse_org", miembroController::unirseAOrg, engine);
            Spark.get("/unirse_sector/:id", miembroController::mostrarSectoresOrganizacion, engine);
            Spark.get("/alta_trayecto", trayectoController::crear, engine);
            Spark.get("/huella_carbono", miembroController::huellaCarbono, engine);

            Spark.get("/empleos", sectorController::mostrarSegunMiembro, engine);
            Spark.get("/trayectos",trayectoController::mostrarTodos, engine);
            Spark.get("/tramos",tramoController::mostrarTodos, engine);
            Spark.get("/editar/:id", miembroController::editar, engine);


            Spark.get("/alta_vehiculo", vehiculoController::altaVehiculo, engine);
            Spark.get("/:id/trayecto/:id_trayecto/tipo_transporte",miembroController::tipoTransporte,engine);
            Spark.get("/:id/trayecto/:id_trayecto/tramo_ecologico",tramoController::tramoEcologico,engine);
            Spark.get("/:id/trayecto/:id_trayecto/tramo_contratado",tramoController::tramoContratado,engine);
            Spark.get("/:id/trayecto/:id_trayecto/tramo_particular",tramoController::tramosParticular,engine);
            Spark.get("/:id/trayecto/:id_trayecto/tramos", tramoController::mostrarTodos, engine);
            Spark.get("/:id", miembroController::mostrar, engine);

            Spark.post("/alta_trayecto", trayectoController::guardar);
            Spark.post("/alta_tramo", tramoController::guardar);
            Spark.post("/unirse_org", miembroController::recibirOrganizacion);
            Spark.post("/unirse_sector/:id", solicitudController::generarSolicitud);
            Spark.post("/editar/:id", miembroController::modificar);
            Spark.post("/alta_vehiculo", vehiculoController::guardarVehiculo);
            Spark.post("/:id/trayecto/:id_trayecto/tramo_ecologico",tramoController::guardarTramoEcologico);
            Spark.post("/:id/trayecto/:id_trayecto/tramo_contratado",tramoController::guardarTramoContratado);
            Spark.post("/:id/trayecto/:id_trayecto/tramo_particular",tramoController::guardarTramoParticular);
        });

        Spark.get("/alta_miembro", miembroController::crear, engine);
        Spark.post("/alta_miembro", miembroController::guardar);



        //Vehiculo particular
        Spark.get("/alta_vehiculo_particular", transporteController::crear, engine);
        Spark.post("/alta_vehiculo_particular", transporteController::guardar);


    }
}
