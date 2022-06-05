package service;


import service.entities.Distancia;
import service.entities.Provincia;

import java.io.IOException;
import java.util.List;

public class Prrueba {
    public static void main(String[] args) throws IOException {
        ServicioGeo servicioGeo = ServicioGeo.getInstancia();
        System.out.println("Prueba api");
        List<Provincia> provincias =servicioGeo.provincias();

        System.out.println("Provincias");
        for(Provincia provincia: provincias) {
            System.out.println(provincia.nombre);
        }

        System.out.println("Distancia");
        Distancia distancia =servicioGeo.distancia(1,"maipu","100",457,"O'Higgins",200);

        System.out.println(distancia.valor);
    }
}