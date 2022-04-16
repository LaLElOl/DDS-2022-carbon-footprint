package dominio;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public class ValidadorContrasenias {

    String direccionArchivoPasswords =  "src/main/resources/files/10-million-password-list-top-10000.txt";

    public Boolean verificarContrasenia(String contrasenia){

        int contadorMayus = 0;
        int contadorMinus = 0;
        int contadorNum = 0;
//        Que la longitud de la contraseña sea >=8.
        if(contrasenia.length() < 8)
            return false;

        for(int i = 0; i < contrasenia.length() ; i++){
//          Que contenga al menos una letra mayúscula.
            if(Character.isUpperCase(contrasenia.charAt(i)))
                contadorMayus++;
//          Que contenga al menos una letra minúscula.
            if(Character.isLowerCase((contrasenia.charAt(i))))
                contadorMinus++;
//          Que contenga al menos un numero.
            if(Character.isDigit(contrasenia.charAt(i)))
                contadorNum++;
        }
        if (contadorMayus == 0 || contadorMinus == 0 || contadorNum == 0)
            return false;

//      Que la contraseña no forme parte del top 10.000 de peores contraseñas.
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
