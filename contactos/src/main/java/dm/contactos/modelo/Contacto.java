package dm.contactos.modelo;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity // JPA - anotacion de entidad
@Data  // Incluye los metodos get y set
@NoArgsConstructor // Constructor vacio
@AllArgsConstructor // Constructor con todos los atributos
@ToString // Agregando metodo toString
public class Contacto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer idContacto;
    String nombre;
    String celular;
    String email;
}
