package dm.empleados.controlador;

import dm.empleados.modelo.Empleado;
import dm.empleados.servicio.EmpleadoServicio;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import java.util.List;

@Controller
public class IndexControlador {
    private static final Logger logger = LoggerFactory.getLogger(IndexControlador.class);

    @Autowired // Inyeccion de dependencias
    EmpleadoServicio empleadoServicio;

    // Aqui se implementaran los metodos para el controlador
    @RequestMapping(value="/", method = RequestMethod.GET) // Mapeo de la URL
    public String iniciar(ModelMap modelo){
        // Recuperar la lista de empleados
        List<Empleado> empleados = empleadoServicio.listarEmpleados();
        empleados.forEach((empleado) -> logger.info(empleado.toString()));

        // Retornar el modelo con la vista
        modelo.put("empleados", empleados);
        return "index"; // index.jsp
    }

    @RequestMapping(value="/agregar", method = RequestMethod.GET)
    public String mostrarAgregar(){
        return "agregar"; // agregar.jsp
    }
}
