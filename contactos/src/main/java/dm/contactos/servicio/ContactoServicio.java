package dm.contactos.servicio;

import dm.contactos.modelo.Contacto;
import dm.contactos.repositorio.ContactoRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service // Bean de Spring
public class ContactoServicio implements IContactoServicio{

    @Autowired // Inyeccion de dependencias
    private ContactoRepositorio contactoRepositorio;

    @Override
    public List<Contacto> listarContactos() {
        return contactoRepositorio.findAll();
    }

    @Override
    public Contacto buscarContactoPorId(Integer idContacto) {
        return contactoRepositorio.findById(idContacto).orElse(null);
    }

    @Override
    public void guardarContacto(Contacto contacto) {
        contactoRepositorio.save(contacto);
    }

    @Override
    public void eliminarContacto(Integer idContacto) {
        contactoRepositorio.deleteById(idContacto);
    }
}
