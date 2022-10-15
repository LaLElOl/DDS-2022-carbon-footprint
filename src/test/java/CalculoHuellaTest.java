import models.dominio.organizacion.Organizacion;
import models.dominio.organizacion.Sector;
import models.dominio.organizacion.datos.DatoConsumo;
import models.dominio.persona.Miembro;
import models.dominio.persona.Tramo;
import models.dominio.persona.Trayecto;
import org.junit.*;

import java.io.IOException;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


public class CalculoHuellaTest {

    private Tramo tramo1;
    private Tramo tramo2;
    private Sector sector;
    private DatoConsumo dato1;
    private DatoConsumo dato2;
    private Organizacion organizacion;

    @Before
    public void init() {
        this.tramo1 = mock(Tramo.class);
        this.tramo2 = mock(Tramo.class);
        this.dato1 = mock(DatoConsumo.class);
        this.dato2 = mock(DatoConsumo.class);
        this.sector = mock(Sector.class);
    }

    @Test
    public void calculoDeHuellaDeTrayecto() throws IOException {
        Miembro persona = new Miembro();
        Trayecto trayecto = new Trayecto();

        trayecto.agregarTramos(tramo1,tramo2);
        persona.agregarTrayectos(trayecto);

        when(this.tramo1.calcularHuella(persona)).thenReturn(5.0);
        when(this.tramo2.calcularHuella(persona)).thenReturn(10.0);

        Assert.assertEquals(15.0,persona.calcularHuella(),0.0);
    }

    /*
    TODO: Para que ande este test necesitamos desacoplar la funcion de org de obtenerConsumos
    @Test
    public void calculoDeHuellaOrganizacion(){
        this.organizacion = new Organizacion();
        List<DatoConsumo> datos = new ArrayList<>();

        Collections.addAll(datos,dato1,dato2);
        organizacion.agregarSectores(sector);


        when(sector.calcularHuella()).thenReturn(10.0);

        when(dato1.calcularHuella()).thenReturn(5.0);
        when(dato2.calcularHuella()).thenReturn(6.8);

        Assert.assertEquals(21.8,organizacion.calcularHuella(1,2022),0.0);
    }*/
}
