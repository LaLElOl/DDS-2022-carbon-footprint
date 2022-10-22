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

        //Organizaciones
        Spark.get("/alta_organizacion", organizacionesController::crear, engine);
        Spark.post("/alta_organizacion", organizacionesController::guardar);

        //Agente Municipal
        Spark.get("/alta_agente_municipal", agenteMunicipalController::crear, engine);
        Spark.post("/alta_agente_municipal", agenteMunicipalController::guardar);

        //Agente Provincial
        Spark.get("/alta_agente_Provincial", agenteProvincialController::crear, engine);
        Spark.post("/alta_agente_Provincial", agenteProvincialController::guardar);

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
