//package dominio.organizacion;
//
//import dominio.EntidadPersistente;
//import dominio.persona.Miembro;
//import lombok.Getter;
//import lombok.Setter;
//
//import javax.persistence.Entity;
//import javax.persistence.JoinColumn;
//import javax.persistence.ManyToOne;
//import javax.persistence.Table;
//
//
//@Getter
//@Setter
//
//@Entity
//@Table(name = "empleado")
//public class Empleado extends EntidadPersistente {
//
//    @ManyToOne
//    @JoinColumn(name = "miembro_id", referencedColumnName = "id")
//    private Miembro miembro;
//
//    @ManyToOne
//    @JoinColumn(name = "sector_id", referencedColumnName = "id")
//    private Sector sector;
//}
