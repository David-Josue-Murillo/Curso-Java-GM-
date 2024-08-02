package murillodev.zona_fit.servicio;

import murillodev.zona_fit.modelo.Cliente;
import murillodev.zona_fit.repositorio.IClienteRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ClienteServicio implements IClienteServicio{

    @Autowired
    private IClienteRepositorio clienteRepositorio;

    @Override
    public List<Cliente> listarCliente() {
        List<Cliente> clientes = clienteRepositorio.findAll();
        return clientes;
    }

    @Override
    public Cliente buscarClientePorId(Integer IdCliente) {
        Cliente cliente = clienteRepositorio.findById(IdCliente).orElse(null);
        return cliente;
    }

    @Override
    public void guardarCliente(Cliente cliente) {
        clienteRepositorio.save(cliente);
    }

    @Override
    public void eliminarCliente(Cliente cliente) {
        clienteRepositorio.delete(cliente);
    }
}
