package dominio.validorDeContrasenias.requisitos;

public class LargoContrasenia implements Requisito {
    @Override
    public boolean validar(String contrasenia) {
        return contrasenia.length()>=8 ;
    }
}
