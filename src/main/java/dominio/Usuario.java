package dominio;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Getter
@Setter

@Entity
@Table(name = "usuario")

public class Usuario extends EntidadPersistente{

    @Column(name = "email")
    private String email;

    @Column(name = "contrasenia")
    private String contrasenia;

    @Column(name = "nickname")
    private String nickname;
}
