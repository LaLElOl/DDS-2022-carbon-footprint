package models.dominio.validorDeContrasenias;

import dominio.validorDeContrasenias.requisitos.*;
import models.dominio.validorDeContrasenias.requisitos.*;

import java.util.Arrays;
import java.util.List;

public class ValidadorContrasenias {


    String direccionArchivoPasswords =  "src/main/resources/files/10-million-password-list-top-10000.txt";

        private static final ValidadorContrasenias instance = new ValidadorContrasenias();
        private static Requisito r[]=	new Requisito []{
                new ContieneMayuscula(),
                new ContieneNumero(),
                new LargoContrasenia(),
                new NoEsContraseniaInvalida(),
                new ContieneMinuscula(),
        };

        static List<Requisito> requisitos = Arrays.asList(r);


        public static ValidadorContrasenias getInstance() {
            return instance;
        }

        static void agregarRequisitos(){
            ContieneMayuscula arcContMay = new ContieneMayuscula();
            ContieneMinuscula arcContMin = new ContieneMinuscula();
            ContieneNumero arcContNum = new ContieneNumero();
            LargoContrasenia largoContrasenia = new LargoContrasenia();
            NoEsContraseniaInvalida noEsContraseniaInvalida = new NoEsContraseniaInvalida();
            requisitos.add(arcContMay);
            requisitos.add(arcContNum);
            requisitos.add(largoContrasenia);
            requisitos.add(noEsContraseniaInvalida);
            requisitos.add(arcContMin);
        }
        public boolean validar(String contrasenia) {
            return requisitos.stream().allMatch(requisito -> requisito.validar(contrasenia));
        }



}
