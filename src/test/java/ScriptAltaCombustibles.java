/*import helpers.EntityManagerHelper;
import models.dominio.transporte.combustibles.*;
import org.junit.Test;

public class ScriptAltaCombustibles {

    @Test
    public void subirCombustiblesABaseDeDatos(){
        GasNatural gasNatural = new GasNatural();
        gasNatural.setFactorEmision(56.10);

        FuelOil fuelOil = new FuelOil();
        fuelOil.setFactorEmision(77.4);

        Carbon carbon = new Carbon();
        carbon.setFactorEmision(94.6);

        Lenia lenia = new Lenia();
        lenia.setFactorEmision(112.0);

        Kerosene kerosene = new Kerosene();
        kerosene.setFactorEmision(71.5);

        CarbonLenia carbonLenia = new CarbonLenia();
        carbonLenia.setFactorEmision(97.5);

        GNC gnc = new GNC();
        gnc.setFactorEmision(74.1);

        Nafta nafta = new Nafta();
        nafta.setFactorEmision(69.3);

        Electrico electrico = new Electrico();
        electrico.setFactorEmision(0.0);

        Gasoil gasoil = new Gasoil();
        gasoil.setFactorEmision(74.1);

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
*/