import helpers.EntityManagerHelper;
import models.dominio.TipoUsuario;
import models.dominio.Usuario;
import models.dominio.organizacion.*;
import models.dominio.persona.Miembro;
import models.dominio.persona.Tramo;
import models.dominio.persona.Trayecto;
import models.dominio.transporte.Ubicacion;
import models.dominio.transporte.combustibles.*;
import models.dominio.transporte.medios.*;
import models.dominio.transporte.vehiculos.TipoVehiculo;
import models.dominio.transporte.vehiculos.Vehiculo;
import org.apache.poi.ss.formula.functions.T;
import org.checkerframework.checker.units.qual.K;
import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

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

        Clasificacion clasifOrg1 = new Clasificacion();
        Clasificacion clasifOrg2 = new Clasificacion();
        Clasificacion clasifOrg3 = new Clasificacion();

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
        Tramo tramo2 = new Tramo(miem1);//Copia del compartido
        Tramo tramo3 = new Tramo(miem3);
        Tramo tramo4 = new Tramo(miem3);
        Tramo tramo5 = new Tramo(miem4);

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

        //MIEMBRO 1-----------------------------------------------------

        userMiem1.setNickname("Persona1");
        userMiem1.setContrasenia("niniorata");
        userMiem1.setEmail("asd@tumama1.com");

        miem1.setNombre("Carla");
        miem1.setApellido("Chumacera");
        miem1.setUsuario(userMiem3);
        miem1.setNumDoc(12312312);

        vehiculo.setFactorVehiculo(1.5);
        vehiculo.setTipoVehiculo(TipoVehiculo.AUTO);
        //TODO: tengo que setear el generico que tenga el factor de emision cargado
        vehiculo.setCombustible(nafta);

        transPart.setVehiculo(vehiculo);

        tramo1.setInicioTramo(ubParticular1);
        tramo1.setFinTramo(ubParticular2);
        tramo1.setCompartido(true);
        tramo1.setTransporte(transPart);
        tramo1.setTrayecto(tray1);

        tray1.setInicio(ubParticular1);
        tray1.setFin(ubParticular2);
        tray1.agregarTramos(tramo1);
        tray1.setMiembro(miem1);

        miem1.agregarTrayectos(tray1);

        //MIEMBRO 2-----------------------------------------------------

        userMiem2.setNickname("Persona2");
        userMiem2.setContrasenia("niniorata");
        userMiem2.setEmail("asd@tumama2.com");

        miem2.setNombre("Carmen");
        miem2.setApellido("Sanchez");
        miem2.setUsuario(userMiem3);
        miem2.setNumDoc(12312313);

        //Comparte tramo con el primer miembro pero se arma una instancia nueva con el dueño seteado en el miem1 y el comrpartido en false
        tramo2.setInicioTramo(ubParticular1);
        tramo2.setFinTramo(ubParticular2);
        tramo2.setCompartido(false);
        tramo2.setTransporte(transPart);
        tramo2.setTrayecto(tray2);

        tray2.setMiembro(miem2);
        tray2.setInicio(ubParticular1);
        tray2.setFin(ubParticular2);
        tray2.agregarTramos(tramo2);

        miem2.agregarTrayectos(tray2);

        //MIEMBRO 3-----------------------------------------------------

        userMiem3.setNickname("Persona3");
        userMiem3.setContrasenia("niniorata");
        userMiem3.setEmail("asd@tumama3.com");

        miem3.setNombre("Carlos");
        miem3.setApellido("Chumacero");
        miem3.setUsuario(userMiem3);
        miem3.setNumDoc(12312311);

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

        tramo3.setTransporte(tren);
        tramo3.setCompartido(false);
        tramo3.setInicioTramo(ubParada1);
        tramo3.setFinTramo(ubParada2);
        tramo3.setTrayecto(tray3);

        tramo4.setInicioTramo(ubParada2);
        tramo4.setFinTramo(ubEco1);
        tramo4.setTransporte(transEco1);
        tramo4.setCompartido(false);
        tramo4.setTrayecto(tray3);

        tray3.setMiembro(miem3);
        tray3.setInicio(ubParada1);
        tray3.setFin(ubEco1);
        tray3.agregarTramos(tramo3,tramo4);

        miem3.agregarTrayectos(tray3);

        //MIEMBRO 4-----------------------------------------------------

        userMiem4.setNickname("Persona4");
        userMiem4.setContrasenia("memetieron7");
        userMiem4.setEmail("asd@tumama4.com");

        miem2.setNombre("David");
        miem2.setApellido("Luiz");
        miem2.setUsuario(userMiem3);
        miem2.setNumDoc(12312314);

        tramo5.setInicioTramo(ubEco2);
        tramo5.setFinTramo(ubEco3);
        tramo5.setCompartido(false);
        tramo5.setTransporte(transEco2);
        tramo5.setTrayecto(tray4);

        tray4.setMiembro(miem4);
        tray4.setInicio(ubEco2);
        tray4.setFin(ubEco3);
        tray4.agregarTramos(tramo5);

        miem4.agregarTrayectos(tray4);

        //ORG 1-----------------------------------------------------
        clasifOrg1.setClasificacion("Clasif 1");
        clasifOrg1.setTipoOrganizacion(TipoOrganizacion.ONG);

        userOrg1.setNickname("Org1");
        userOrg1.setContrasenia("skere");
        userOrg1.setEmail("asd@tuorg1.com");
        userOrg1.setTipoUsuario(TipoUsuario.ORGANIZACION);

        sec1.setNombre("Finanzas");
        //TODO: Controlar este add, sino tengo que hacer la solicitud
        sec1.getMiembros().add(miem1);
        sec1.setOrganizacion(org1);
        sec2.setNombre("Distribucion");
        //TODO: Controlar este add, sino tengo que hacer la solicitud
        sec2.getMiembros().add(miem2);
        sec2.setOrganizacion(org1);

        miem1.agregarSectores(sec1);
        miem2.agregarSectores(sec1);

        org1.setRazonSocial("Organizacion ONG");
        org1.setCuit("33333333333");
        org1.setUbicacion(null);
        org1.setClasificacion(clasifOrg1);
        org1.setContactosANotificar(null);
        org1.setUsuario(userOrg1);
        org1.agregarSectores(sec1,sec2);

        //ORG 2-----------------------------------------------------

        clasifOrg2.setClasificacion("Clasif 2");
        clasifOrg2.setTipoOrganizacion(TipoOrganizacion.GUBERNAMENTAL);

        userOrg2.setNickname("Org2");
        userOrg2.setContrasenia("skere");
        userOrg2.setEmail("asd@tuorg2.com");

        sec3.setNombre("IT");
        //TODO: Controlar este add, sino tengo que hacer la solicitud
        sec3.getMiembros().add(miem3);
        sec3.setOrganizacion(org2);

        miem3.agregarSectores(sec3);

        org2.setRazonSocial("Organizacion GUB");
        org2.setCuit("33333333334");
        org2.setUbicacion(null);
        org2.setClasificacion(clasifOrg2);
        org2.setContactosANotificar(null);
        org2.setUsuario(userOrg2);
        org2.agregarSectores(sec3);

        //ORG 3-----------------------------------------------------

        clasifOrg3.setClasificacion("Clasif 3");
        clasifOrg3.setTipoOrganizacion(TipoOrganizacion.EMPRESA);

        userOrg3.setNickname("Org3");
        userOrg3.setContrasenia("skere");
        userOrg3.setEmail("asd@tuorg2.com");

        sec4.setNombre("IT");
        //TODO: Controlar este add, sino tengo que hacer la solicitud
        sec4.getMiembros().add(miem4);
        sec4.setOrganizacion(org3);

        miem4.agregarSectores(sec4);

        org3.setRazonSocial("Organizacion EMP");
        org3.setCuit("33333333334");
        org3.setUbicacion(null);
        org3.setClasificacion(clasifOrg3);
        org3.setContactosANotificar(null);
        org3.setUsuario(userOrg3);
        org3.agregarSectores(sec4);

        //AM 1-----------------------------------------------------

        userAM1.setNickname("AM1");
        userAM1.setContrasenia("skere");
        userAM1.setEmail("asd@tuam1.com");

        agMun1.setNombre("Ag. Mun1");
        agMun1.setUsuario(userAM1);
        agMun1.setMunicipio("Ituzaingo");
        agMun1.agregarOrganizaciones(org1,org2);
        agMun1.setAgenteProvincial(agProv);

        //AM 2-----------------------------------------------------

        userAM2.setNickname("AM2");
        userAM2.setContrasenia("skere");
        userAM2.setEmail("asd@tuam2.com");

        agMun2.setNombre("Ag. Mun2");
        agMun2.setUsuario(userAM2);
        agMun2.setMunicipio("Vicente Lopez");
        agMun2.agregarOrganizaciones(org3);
        agMun2.setAgenteProvincial(agProv);

        //AP 1-----------------------------------------------------

        userAP.setNickname("AP");
        userAP.setContrasenia("skere");
        userAP.setEmail("asd@tuam1.com");

        agProv.setNombre("Ag. Prov");
        agProv.setUsuario(userAM1);
        agProv.setProvincia("Buenos Aires");
        agProv.agregarAgentesMunicipales(agMun1,agMun2);

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

        EntityManagerHelper.persist(ubParada1);
        EntityManagerHelper.persist(ubParada2);
        EntityManagerHelper.persist(ubParticular1);
        EntityManagerHelper.persist(ubParticular2);
        EntityManagerHelper.persist(ubEco1);
        EntityManagerHelper.persist(ubEco2);
        EntityManagerHelper.persist(ubEco3);

        EntityManagerHelper.persist(userMiem1);
        EntityManagerHelper.persist(userMiem2);
        EntityManagerHelper.persist(userMiem3);
        EntityManagerHelper.persist(userMiem4);
        EntityManagerHelper.persist(userOrg1);
        EntityManagerHelper.persist(userOrg2);
        EntityManagerHelper.persist(userOrg3);
        EntityManagerHelper.persist(userAM1);
        EntityManagerHelper.persist(userAM2);
        EntityManagerHelper.persist(userAP);

        //MIEM 1
        EntityManagerHelper.persist(vehiculo);
        EntityManagerHelper.persist(transPart);
        EntityManagerHelper.persist(tramo1);
        EntityManagerHelper.persist(tray1);
        EntityManagerHelper.persist(miem1);

        //MIEM 2
        EntityManagerHelper.persist(tramo2);
        EntityManagerHelper.persist(tray2);
        EntityManagerHelper.persist(miem2);

        //MIEM 3
        EntityManagerHelper.persist(parada1);
        EntityManagerHelper.persist(paradasTrans1);
        EntityManagerHelper.persist(parada2);
        EntityManagerHelper.persist(paradasTrans2);
        EntityManagerHelper.persist(tren);
        EntityManagerHelper.persist(tramo3);
        EntityManagerHelper.persist(transEco1);
        EntityManagerHelper.persist(tramo4);
        EntityManagerHelper.persist(tray3);
        EntityManagerHelper.persist(miem3);

        //MIEM 4
        EntityManagerHelper.persist(transEco2);
        EntityManagerHelper.persist(tramo5);
        EntityManagerHelper.persist(tray4);
        EntityManagerHelper.persist(miem4);

        //ORG 1
        EntityManagerHelper.persist(clasifOrg1);
        EntityManagerHelper.persist(sec1);
        EntityManagerHelper.persist(sec2);
        EntityManagerHelper.persist(org1);

        //ORG 2
        EntityManagerHelper.persist(clasifOrg2);
        EntityManagerHelper.persist(sec3);
        EntityManagerHelper.persist(org2);

        //ORG 3
        EntityManagerHelper.persist(clasifOrg3);
        EntityManagerHelper.persist(sec4);
        EntityManagerHelper.persist(org3);

        //AGENTES
        EntityManagerHelper.persist(agMun1);
        EntityManagerHelper.persist(agMun2);
        EntityManagerHelper.persist(agProv);

        EntityManagerHelper.commit();

        Assertions.assertNotNull(EntityManagerHelper.getEntityManager());
    }

}
