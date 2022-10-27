import helpers.EntityManagerHelper;
import models.dominio.Usuario;
import models.dominio.organizacion.AgenteMunicipal;
import models.dominio.organizacion.AgenteProvincial;
import models.dominio.organizacion.Organizacion;
import models.dominio.organizacion.Sector;
import models.dominio.persona.Miembro;
import models.dominio.persona.Tramo;
import models.dominio.persona.Trayecto;
import models.dominio.transporte.Ubicacion;
import models.dominio.transporte.combustibles.*;
import models.dominio.transporte.medios.*;
import models.dominio.transporte.vehiculos.Vehiculo;
import org.apache.poi.ss.formula.functions.T;
import org.checkerframework.checker.units.qual.K;
import org.junit.Assert;
import org.junit.Test;

public class ScriptDatosDB {

    @Test
    public void crearDatosEInsertarEnDB(){

        //INSTANCIACION DE CLASES
        AgenteProvincial agProv = new AgenteProvincial();

        AgenteMunicipal agMun1 = new AgenteMunicipal();
        AgenteMunicipal agMun2 = new AgenteMunicipal();

        Organizacion org1 = new Organizacion();
        Organizacion org2 = new Organizacion();
        Organizacion org3 = new Organizacion();

        Sector sec1 = new Sector();
        Sector sec2 = new Sector();
        Sector sec3 = new Sector();
        Sector sec4 = new Sector();

        Miembro miem1 = new Miembro();
        Miembro miem2 = new Miembro();
        Miembro miem3 = new Miembro();
        Miembro miem4 = new Miembro();

        Usuario userAP = new Usuario();
        Usuario userAM1 = new Usuario();
        Usuario userAM2 = new Usuario();
        Usuario userOrg1 = new Usuario();
        Usuario userOrg2 = new Usuario();
        Usuario userOrg3 = new Usuario();
        Usuario userMiem1 = new Usuario();
        Usuario userMiem2 = new Usuario();
        Usuario userMiem3 = new Usuario();
        Usuario userMiem4 = new Usuario();

        Trayecto tray1 = new Trayecto();
        Trayecto tray2 = new Trayecto();
        Trayecto tray3 = new Trayecto();
        Trayecto tray4 = new Trayecto();

        Tramo tramo1 = new Tramo(miem1);
        Tramo tramo2 = new Tramo(miem3);
        Tramo tramo3 = new Tramo(miem3);
        Tramo tramo4 = new Tramo(miem4);
        Tramo tramo5 = new Tramo(miem1); //Copia del compartido

        Particular transPart = new Particular();
        Publico tren = new Publico();
        Ecologico transEco1 = new Ecologico();
        Ecologico transEco2 = new Ecologico();

        Vehiculo vehiculo = new Vehiculo();

        ParadasTransporte paradasTrans1 = new ParadasTransporte();
        ParadasTransporte paradasTrans2 = new ParadasTransporte();
        Parada parada1 = new Parada();
        Parada parada2 = new Parada();

        Ubicacion ubParada1 = new Ubicacion();
        Ubicacion ubParada2 = new Ubicacion();
        Ubicacion ubParticular1 = new Ubicacion();
        Ubicacion ubParticular2 = new Ubicacion();
        Ubicacion ubEco1 = new Ubicacion();
        Ubicacion ubEco2 = new Ubicacion();
        Ubicacion ubEco3 = new Ubicacion();

        //APARTADO COMBUSTIBLES
        //TODO: Setear factores de emision de cada uno

        GasNatural gasNatural = new GasNatural();
        FuelOil fuelOil = new FuelOil();
        Carbon carbon = new Carbon();
        Lenia lenia = new Lenia();
        Kerosene kerosene = new Kerosene();
        CarbonLenia carbonLenia = new CarbonLenia();
        GNC gnc = new GNC();
        Nafta nafta = new Nafta();
        Electrico electrico = new Electrico();
        Gasoil gasoil = new Gasoil();

        //HIDRATADO DE INSTANCIAS
        parada1.setNombre("Chilavert");
        parada1.setUbicacion(ubParada1);
        paradasTrans1.setParadaActual(parada1);
        paradasTrans1.setParadaSiguiente(paradasTrans2);
        paradasTrans1.setDistanciaAlaSiguiente(1500.0);
        paradasTrans1.setTransportePublico(tren);

        parada2.setNombre("V. Ballester");
        parada2.setUbicacion(ubParada2);
        paradasTrans2.setParadaActual(parada2);
        paradasTrans2.setParadaSiguiente(null);
        paradasTrans2.setDistanciaAlaSiguiente(0.0);
        paradasTrans2.setTransportePublico(tren);

        tren.setNombre("Linea Mitre - J. L. Suarez");
        tren.agregarParadas(paradasTrans1,paradasTrans2);
        tren.setTipoTransporte(TipoTransporte.TREN);
        tren.setFactorEmision(2.0);

        tramo2.setTransporte(tren);
        tramo2.setCompartido(false);
        tramo2.setInicioTramo(ubParada1);
        tramo2.setFinTramo(ubParada2);
        tramo2.setTrayecto(tray3);

        tramo3.setInicioTramo(ubParada2);
        tramo3.setFinTramo(ubEco1);
        tramo3.setTransporte(transEco1);
        tramo3.setCompartido(false);
        tramo3.setTrayecto(tray3);

        tray3.setMiembro(miem3);
        tray3.setInicio(ubParada1);
        tray3.setFin(ubEco1);
        tray3.agregarTramos(tramo2,tramo3);

        miem3.agregarTrayectos(tray3);




        transPart.setVehiculo(vehiculo);

        tramo1.setInicioTramo(ubParticular1);
        tramo1.setFinTramo(ubParticular2);
        tramo1.setCompartido(true);
        tramo1.setTransporte(transPart);



        //GUARDADO DE DATOS EN BASE
        EntityManagerHelper.beginTransaction();

        EntityManagerHelper.persist(gasNatural);
        EntityManagerHelper.persist(fuelOil);
        EntityManagerHelper.persist(carbon);
        EntityManagerHelper.persist(lenia);
        EntityManagerHelper.persist(kerosene);
        EntityManagerHelper.persist(carbonLenia);
        EntityManagerHelper.persist(gnc);
        EntityManagerHelper.persist(nafta);
        EntityManagerHelper.persist(electrico);
        EntityManagerHelper.persist(gasoil);

        EntityManagerHelper.commit();
    }

}
