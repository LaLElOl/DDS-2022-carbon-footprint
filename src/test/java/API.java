import org.junit.Assert;
import org.junit.Test;
import service.RetrofitServicioGeo;
import service.entities.Distancia;
import service.entities.Provincia;

import java.io.IOException;
import java.util.List;

public class API {

    @Test
    public void chequearConexionAApi() throws IOException {

        RetrofitServicioGeo servicioGeo = RetrofitServicioGeo.getInstancia();
        List<Provincia> provincias =servicioGeo.provincias();
        Distancia distancia =servicioGeo.distancia(1,"maipu","100",457,"O'Higgins",200);

        Assert.assertFalse(provincias.isEmpty());
        Assert.assertNotNull(distancia.valor, distancia.valor);
    }
}
