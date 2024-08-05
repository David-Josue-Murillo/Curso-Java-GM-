package dm.rh.controlador;

import dm.rh.excepcion.RecursoNoEncontradoExcepcion;
import dm.rh.modelo.Empleado;
import dm.rh.servicio.EmpleadoServicio;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("rh-app") // (http:/localhost/8080/rh-app/
@CrossOrigin(value = "http://localhost:5173")
public class EmpleadoControlador {
    private static final Logger logger = LoggerFactory.getLogger(EmpleadoControlador.class);

    @Autowired
    EmpleadoServicio empleadoServicio;

    @GetMapping("/empleados")
    public List<Empleado> obtenerEmpleados() {
        List<Empleado> empleados = empleadoServicio.listaEmpleados();
        empleados.forEach((empleado) -> logger.info(empleado.toString()));
        return empleados;
    }

    @PostMapping("/empleados")
    public Empleado agregarEmpleado(@RequestBody Empleado empleado) {
        logger.info(empleado.toString());
        return empleadoServicio.guardarEmpleado(empleado);
    }

    @GetMapping("/empleados/{id}")
    public ResponseEntity<Empleado> obtenerEmpleado(@PathVariable Integer id) {
        Empleado empleado = empleadoServicio.buscarEmpleado(id);
        if (empleado == null) {
            throw new RecursoNoEncontradoExcepcion("Empleado no encontrado con el id: " + id);
        }
        return ResponseEntity.ok(empleado);
    }

    @PutMapping("/empleados/{id}")
    public ResponseEntity<Empleado> actualizarEmpleado(@PathVariable Integer id, @RequestBody Empleado empleado) {
        Empleado empleadoActual = empleadoServicio.buscarEmpleado(id);
        if (empleadoActual == null) {
            throw new RecursoNoEncontradoExcepcion("Empleado no encontrado con el id: " + id);
        }
        empleadoActual.setNombre(empleado.getNombre());
        empleadoActual.setDepartamento(empleado.getDepartamento());
        empleadoActual.setSueldo(empleado.getSueldo());
        empleadoServicio.guardarEmpleado(empleadoActual);
        return ResponseEntity.ok(empleadoActual);
    }

    @DeleteMapping("/empleados/{id}")
    public ResponseEntity<Map<String, Boolean>> eliminarEmpleado(@PathVariable Integer id) {
        Empleado empleado = empleadoServicio.buscarEmpleado(id);
        if (empleado == null) {
            throw new RecursoNoEncontradoExcepcion("Empleado no encontrado con el id: " + id);
        }
        empleadoServicio.eliminarEmpleado(id);
        Map<String, Boolean> respuesta = new HashMap<>();
        respuesta.put("Eliminado", Boolean.TRUE);
        return ResponseEntity.ok(respuesta);
    }
}
