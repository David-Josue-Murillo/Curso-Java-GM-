package dm.rh.servicio;

import dm.rh.modelo.Empleado;

import java.util.List;

public interface IEmpleadoServicio {
    public List<Empleado> listaEmpleados();
    public Empleado buscarEmpleado(Integer idEmpleado);
    public Empleado guardarEmpleado(Empleado empleado);
    public  void eliminarEmpleado(Integer idEmpleado);
}
