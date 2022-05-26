package dominio.validorDeContrasenias.requisitos;

public class ContieneNumero implements Requisito{
    @Override
    public boolean validar(String contrasenia) {
        for (int i = 0; i < contrasenia.length(); i++) {
            if (Character.isDigit(contrasenia.charAt(i))) {
                return true;
            }
        }
        return false;
    }
}
