package dm.empleados.modelo;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity //Indica que la clase es una entidad
@Data //Genera el metodo set, get y constructores de manera automatica
@NoArgsConstructor //Genera un constructor vacio
@AllArgsConstructor //Genera un constructor con todos los atributos
@ToString //Genera el metodo toString
public class Empleado {
    @Id //Indica que el atributo es la clave primaria
    @GeneratedValue(strategy = GenerationType.IDENTITY) //Genera un valor automatico
    Integer idEmpleado;
    String nombreEmpleado;
    String departamento;
    Double sueldo;
}
