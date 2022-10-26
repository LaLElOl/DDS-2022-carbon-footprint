import helpers.EntityManagerHelper;
import models.dominio.organizacion.AgenteMunicipal;
import models.dominio.organizacion.AgenteProvincial;
import models.dominio.organizacion.Organizacion;
import models.dominio.organizacion.Sector;
import models.dominio.persona.Miembro;
import models.dominio.persona.Tramo;
import models.dominio.persona.Trayecto;
import models.dominio.transporte.combustibles.*;
import models.dominio.transporte.medios.Ecologico;
import models.dominio.transporte.medios.Particular;
import models.dominio.transporte.medios.Publico;
import models.dominio.transporte.vehiculos.Vehiculo;
import org.apache.poi.ss.formula.functions.T;
import org.checkerframework.checker.units.qual.K;
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

        Trayecto tray1 = new Trayecto();
        Trayecto tray2 = new Trayecto();
        Trayecto tray3 = new Trayecto();
        Trayecto tray4 = new Trayecto();

        Tramo tramo1 = new Tramo(miem1);
        Tramo tramo2 = new Tramo(miem1);
        Tramo tramo3 = new Tramo(miem2);
        Tramo tramo4 = new Tramo(miem4);

        Ecologico trans11 = new Ecologico();
        Publico trans12 = new Publico();
        Particular trans2 = new Particular();

        Vehiculo vehiculo = new Vehiculo();

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
        //TODO: Agregar todos los setters en las instancias



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
