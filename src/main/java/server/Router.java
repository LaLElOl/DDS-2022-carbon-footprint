package server;

import controllers.*;
import middlewares.AuthMiddleware;

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
        TransporteController vehiculoParticularController = new TransporteController();
        LoginController loginController = new LoginController();
        HomeController homeController = new HomeController();


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

            Spark.post("/alta_sector", sectorController::guardar);
            Spark.post("/huella_carbono",organizacionesController::calcularHuellaCarbono);
            Spark.post("/excel", organizacionesController::guardarConsumo);
            Spark.post("/editar/:id",organizacionesController::modificar);
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

            Spark.get("/trayectos",trayectoController::mostrarTodos, engine);
            Spark.get("/alta_tramo", tramoController::crear, engine);
            Spark.get("/tramos",tramoController::mostrarTodos, engine);
            Spark.get("/editar/:id", miembroController::editar, engine);
            Spark.get("/tipo_transporte",miembroController::tipoTransporte,engine);

            Spark.get("/:id", miembroController::mostrar, engine);
            Spark.post("/alta_trayecto", trayectoController::guardar);
            Spark.post("/alta_tramo", tramoController::guardar);
            Spark.post("/unirse_org", miembroController::recibirOrganizacion);
            Spark.post("/unirse_sector/:id", miembroController::generarSolicitud);
            Spark.post("/editar/:id", miembroController::modificar);
        });

        Spark.get("/alta_miembro", miembroController::crear, engine);
        Spark.post("/alta_miembro", miembroController::guardar);




        //Tramo


        //Vehiculo particular
        Spark.get("/alta_vehiculo_particular", vehiculoParticularController::crear, engine);
        Spark.post("/alta_vehiculo_particular", vehiculoParticularController::guardar);


    }
}
