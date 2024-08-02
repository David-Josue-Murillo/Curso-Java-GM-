package dm.tareas.repositorio;

import dm.tareas.modelo.Tarea;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ITareaRepositorio extends JpaRepository<Tarea, Integer> {
}
