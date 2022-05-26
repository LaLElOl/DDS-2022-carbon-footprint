package dominio.validorDeContrasenias.requisitos;

public class ContieneMayuscula implements Requisito{
    @Override
    public boolean validar(String contrasenia) {
        for (int i = 0; i < contrasenia.length(); i++) {
            if (Character.isUpperCase(contrasenia.charAt(i))) {
                return true;
            }
        }
        return false;
    }
}
