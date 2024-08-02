package murillodev.zona_fit.servicio;

import murillodev.zona_fit.modelo.Cliente;
import java.util.List;

public interface IClienteServicio {
    public List<Cliente> listarCliente();
    public Cliente buscarClientePorId(Integer IdCliente);
    public void guardarCliente(Cliente cliente);
    public void eliminarCliente(Cliente cliente);
}
