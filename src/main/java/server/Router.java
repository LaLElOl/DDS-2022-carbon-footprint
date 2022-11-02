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
        VehiculoParticularController vehiculoParticularController = new VehiculoParticularController();
        VehiculoPublicoController vehiculoPublicoController = new VehiculoPublicoController();
        LoginController loginController = new LoginController();

        Spark.path("/login", () -> {
            Spark.get("", loginController::pantallaDeLogin, engine);
            Spark.post("", loginController::login);
            Spark.post("/logout", loginController::logout);
        });


        Spark.get("/index",(request,response)->"SOY EL INDEX");

        //Organizaciones
        Spark.path("/organizacion", () -> {

            Spark.before("",AuthMiddleware::verificarSesion);
            Spark.before("/*",AuthMiddleware::verificarSesion);

            Spark.get("",organizacionesController::mostrarTodos, engine);
            Spark.get("/:id",organizacionesController::mostrar, engine);
            Spark.get("/editar/:id",organizacionesController::editar, engine);
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
            Spark.post("/agente_municipal/editar/:id", agenteMunicipalController::modificar);

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
        Spark.get("/alta_miembro", miembroController::crear, engine);
        Spark.post("/alta_miembro", miembroController::guardar);

        //Sector
        Spark.get("/alta_sector", sectorController::crear, engine);
        Spark.post("/alta_sector", sectorController::guardar);

        //Vehiculo particular
        Spark.get("/alta_vehiculo_particular", vehiculoParticularController::crear, engine);
        Spark.post("/alta_vehiculo_particular", vehiculoParticularController::guardar);

        //Vehiculo publico
        Spark.get("/alta_vehiculo_publico", vehiculoPublicoController::crear, engine);
        Spark.post("/alta_vehiculo_publico", vehiculoPublicoController::guardar);
    }
}
