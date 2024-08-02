package dm.tareas.servicio;

import dm.tareas.modelo.Tarea;
import dm.tareas.repositorio.ITareaRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class TareaServicio implements ITareaServicio{

    @Autowired
    private ITareaRepositorio tareaRepositorio;

    @Override
    public List<Tarea> listarTarea() {
        return tareaRepositorio.findAll();
    }

    @Override
    public Tarea buscarTareaPorId(Integer idTarea) {
        return tareaRepositorio.findById(idTarea).orElse(null);
    }

    @Override
    public void guardarTarea(Tarea tarea) {
        tareaRepositorio.save(tarea);
    }

    @Override
    public void eliminarTarea(Tarea tarea) {
        tareaRepositorio.delete(tarea);
    }
}
