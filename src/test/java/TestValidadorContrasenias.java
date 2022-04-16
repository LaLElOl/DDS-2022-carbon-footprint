import com.sun.org.apache.xpath.internal.operations.Bool;
import dominio.ValidadorContrasenias;
import org.junit.Assert;
import org.junit.Test;

public class TestValidadorContrasenias {

    @Test
    public void validarContraseniaSinMayus(){

        String contrasenia =  "holaiasd0";

        Boolean validada = new ValidadorContrasenias().verificarContrasenia(contrasenia);

        Assert.assertFalse(validada);
    }

    @Test
    public void validarContraseniaSinMinus(){

        String contrasenia =  "HOLASDASD0";

        Boolean validada = new ValidadorContrasenias().verificarContrasenia(contrasenia);

        Assert.assertFalse(validada);
    }

    @Test
    public void validarContraseniaSinNum(){

        String contrasenia =  "HOLASDASD";

        Boolean validada = new ValidadorContrasenias().verificarContrasenia(contrasenia);

        Assert.assertFalse(validada);
    }

    @Test
    public void validarContraseniaCorta(){

        String contrasenia =  "HOLAS";

        Boolean validada = new ValidadorContrasenias().verificarContrasenia(contrasenia);

        Assert.assertFalse(validada);
    }

    @Test
    public void validarContraseniaDentroDeArchivo(){

        String contrasenia =  "12345qwerT";

        Boolean validada = new ValidadorContrasenias().verificarContrasenia(contrasenia);

        Assert.assertFalse(validada);
    }

    @Test
    public void validarContraseniaBuena(){

        String contrasenia =  "Hoalsdfgasd0";

        Boolean validada = new ValidadorContrasenias().verificarContrasenia(contrasenia);

        Assert.assertTrue(validada);
    }
}
