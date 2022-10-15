package models.dominio.validorDeContrasenias.requisitos;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public class NoEsContraseniaInvalida implements Requisito{
    String direccionArchivoPasswords =  "src/main/resources/files/10-million-password-list-top-10000.txt";

    @Override
    public boolean validar(String contrasenia) {
        return verificarEnArchivo(contrasenia);
    }
    public Boolean verificarEnArchivo(String contrasenia) {
        File archivo;
        FileReader lectorCaracter = null; // lee caracter a caracter
        BufferedReader lectorPalabra; // lee una palabra al detectar un \n
        try {
            archivo = new File(direccionArchivoPasswords);
            lectorCaracter = new FileReader (archivo);
            lectorPalabra = new BufferedReader(lectorCaracter);
            String linea;
            while((linea=lectorPalabra.readLine())!=null)// mientras no lleguemos al final del archivo
                if(linea.contentEquals(contrasenia))
                {
                    return !(linea.contentEquals(contrasenia));
                }
        }
        catch(Exception e){
            e.printStackTrace();
        }finally{
            // cierra el archivo
            try{
                if( lectorCaracter !=  null ){
                    lectorCaracter.close();
                }
            }catch (Exception e2){
                e2.printStackTrace();
            }
        }
        return true;
    }
}
