package murillodev.zona_fit.modelo;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

@Entity
@Data // Crear los metodos getter, setter
@NoArgsConstructor // Crear un constructor sin argumentos, osea vacio
@AllArgsConstructor // Crear un constructor con todos los argumentos
@ToString // Crear un metodo toString
@EqualsAndHashCode // Crear los metodos equals y hashCode
public class Cliente {
    @Id // Indica que es la llave primaria
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Indica que se autoincrementa
    private Integer id;
    private String nombre;
    private String apellido;
    private Integer membresia;
}
