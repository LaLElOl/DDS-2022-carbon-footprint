package models.dominio.validorDeContrasenias.requisitos;

public class ContieneMinuscula implements Requisito{
    @Override
    public boolean validar(String contrasenia) {
        for (int i = 0; i < contrasenia.length(); i++) {
            if (Character.isLowerCase(contrasenia.charAt(i))) {
                return true;
            }
        }
        return false;    }
}
