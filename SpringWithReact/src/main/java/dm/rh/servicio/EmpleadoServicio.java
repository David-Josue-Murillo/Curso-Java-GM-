package dm.rh.servicio;

import dm.rh.modelo.Empleado;
import dm.rh.repositorio.EmpleadoRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class EmpleadoServicio implements IEmpleadoServicio{

    @Autowired // Inyeccion de dependencias
    EmpleadoRepositorio empleadoRepositorio;

    @Override
    public List<Empleado> listaEmpleados() {
        return  empleadoRepositorio.findAll();
    }

    @Override
    public Empleado buscarEmpleado(Integer idEmpleado) {
        return empleadoRepositorio.findById(idEmpleado).orElse(null);
    }

    @Override
    public void guardarEmpleado(Empleado empleado) {
        empleadoRepositorio.save(empleado);
    }

    @Override
    public void eliminarEmpleado(Integer idEmpleado) {
        empleadoRepositorio.deleteById(idEmpleado);
    }
}
