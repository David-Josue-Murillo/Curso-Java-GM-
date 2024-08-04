package dm.rh.modelo;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity // Crea de forma automatica la tabla en la base de datos
@Data // Genera los metodos get y set automaticamente
@NoArgsConstructor // Constructor vacio
@AllArgsConstructor // Constructor con todos los argumentos
@ToString // Agrega el metodo
public class Empleado {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer idEmpleado;
    String nombre;
    String departamento;
    Double sueldo;
}
