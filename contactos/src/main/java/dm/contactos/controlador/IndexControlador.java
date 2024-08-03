package dm.contactos.controlador;

import dm.contactos.modelo.Contacto;
import dm.contactos.servicio.IContactoServicio;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller // Indica que es un controlador
public class IndexControlador {
    private static final Logger logger = LoggerFactory.getLogger(IndexControlador.class);

    @Autowired // Inyectando dependencias
    IContactoServicio contactoServicio;

    @GetMapping("/") // Mapeo de la URL
    public String iniciar(ModelMap modelo){
        List<Contacto> contactos = contactoServicio.listarContactos();

        contactos.forEach((contacto -> {
            logger.info(contacto.toString());
        }));

        // Agregando atributos a la vista (IU)
        modelo.put("contactos", contactos);
        return "index";
    }

    @GetMapping("/agregar")
    public String mostrarAgregar() {
        return "agregar";
    }

    @PostMapping("/agregar")
    public String agregar(@ModelAttribute("formContacto") Contacto contacto) {
        contactoServicio.guardarContacto(contacto);
        return "redirect:/";
    }

    @GetMapping("/editar/{id}")
    public String mostrarEditar(@PathVariable(value="id") int idContacto, ModelMap modelo){
        Contacto contacto = contactoServicio.buscarContactoPorId(idContacto);
        modelo.put("contacto", contacto);
        return "editar";
    }

    @PostMapping("/editar")
    public String editar(@ModelAttribute("contacto") Contacto contacto){
        contactoServicio.guardarContacto(contacto);
        return "redirect:/";
    }
}
