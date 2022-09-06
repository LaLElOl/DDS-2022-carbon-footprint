package dominio;

import lombok.Getter;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;


@MappedSuperclass
@Getter
public class EntidadPersistente {

    @Id
    @GeneratedValue
    private Integer id;
}
