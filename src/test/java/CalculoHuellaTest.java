import dominio.persona.Miembro;
import dominio.persona.Tramo;
import dominio.persona.Trayecto;
import org.junit.*;

import java.io.IOException;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


public class CalculoHuellaTest {

    private Tramo tramo1;
    private Tramo tramo2;

    @Before
    public void init() {
        this.tramo1 = mock(Tramo.class);
        this.tramo2 = mock(Tramo.class);
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
}
