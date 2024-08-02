package murillodev.zona_fit.repositorio;

import murillodev.zona_fit.modelo.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IClienteRepositorio extends JpaRepository<Cliente, Integer> {

}
