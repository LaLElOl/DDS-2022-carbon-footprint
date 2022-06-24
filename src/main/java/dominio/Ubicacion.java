package dominio;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Ubicacion {
    private Integer altura;
    private String calle;
    private Integer latitud;
    private Integer localidad;
    private Integer longitud;
    private String provincia;
}
