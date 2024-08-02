package zona_fit.datos;

import zona_fit.conexion.Conexion;
import zona_fit.dominio.Cliente;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import static zona_fit.conexion.Conexion.getConexion;

public class ClienteDAO implements IClienteDAO {

    @Override
    public List<Cliente> listarClientes() {
        List<Cliente> clientes = new ArrayList<>();
        PreparedStatement ps; // Objeto que realiza la consulta
        ResultSet rs; // Objeto que obtiene el resultado de la consulta
        Connection con = getConexion(); // Obtiene la conexion a la base de datos

        // Consulta sql
        String sql = "SELECT * FROM cliente ORDER BY id"; // Consulta a la base de datos

        try{
            ps = con.prepareStatement(sql); // Prepara la consulta
            rs = ps.executeQuery(); // Ejecuta la consulta

            // Recorre los resultados de la consulta
            while(rs.next()) {
                Cliente cliente = new Cliente(); // Crea un nuevo cliente
                cliente.setId(rs.getInt("id")); // Obtiene el id del cliente
                cliente.setNombre(rs.getString("nombre")); // Obtiene el nombre del cliente
                cliente.setApellido(rs.getString("apellido")); // Obtiene el apellido del cliente
                cliente.setMembresia(rs.getInt("membresia")); // Obtiene la membresia del cliente

                // Guardar cliente en lista clientes
                clientes.add(cliente);
            }

        }catch (Exception e) {
            System.out.println("Error al listar los clientes: " + e.getMessage());
        }

        finally {
            try {
                con.close(); // Cierra la conexion
            } catch (Exception e) {
                System.out.println("Error al cerrar la conexion: " + e.getMessage());
            }
        }

        return clientes;
    }

    @Override
    public boolean buscarClientePorId(Cliente cliente) {
        PreparedStatement ps; // Objeto que realiza la consulta
        ResultSet rs; // Objeto que obtiene el resultado de la consulta
        Connection con = getConexion();
        String sql = "SELECT * FROM cliente WHERE id = ?"; // Consulta a la base de datos

        try{
            ps = con.prepareStatement(sql); // Prepara la consulta
            ps.setInt(1, cliente.getId()); // Asigna el id del cliente a la consulta
            rs = ps.executeQuery(); // Ejecuta la consulta

            if(rs.next()) {
                cliente.setNombre(rs.getString("nombre")); // Obtiene el nombre del cliente
                cliente.setApellido(rs.getString("apellido")); // Obtiene el apellido del cliente
                cliente.setMembresia(rs.getInt("membresia")); // Obtiene la membresia del cliente
                return true;
            }
        }catch (Exception e){
            System.out.println("Error al buscar el cliente: " + e.getMessage());
        }

        finally {
            try {
                con.close(); // Cierra la conexion
            } catch (Exception e) {
                System.out.println("Error al cerrar la conexion: " + e.getMessage());
            }
        }

        return false;
    }

    @Override
    public boolean agregarCliente(Cliente cliente) {
        PreparedStatement ps;
        Connection con = getConexion();
        String sql = "INSERT INTO cliente (nombre, apellido, membresia) VALUES (?, ?, ?)";

        try{
            ps = con.prepareStatement(sql);
            ps.setString(1, cliente.getNombre());
            ps.setString(2, cliente.getApellido());
            ps.setInt(3, cliente.getMembresia());
            ps.execute();

            return true;
        }catch (Exception e){
            System.out.println("Error al agregar el cliente: " + e.getMessage());
        }

        finally {
            try {
                con.close(); // Cierra la conexion
            } catch (Exception e) {
                System.out.println("Error al cerrar la conexion: " + e.getMessage());
            }
        }

        return false;
    }

    @Override
    public boolean modificarCliente(Cliente cliente) {
        PreparedStatement ps;
        Connection con = getConexion();
        String sql = "UPDATE cliente SET nombre = ?, apellido = ?, membresia = ? WHERE id = ?";

        try{
            ps = con.prepareStatement(sql);
            ps.setString(1, cliente.getNombre());
            ps.setString(2, cliente.getApellido());
            ps.setInt(3, cliente.getMembresia());
            ps.setInt(4, cliente.getId());
            ps.execute();

            return true;
        }catch (Exception e){
            System.out.println("Error al modificar el cliente: " + e.getMessage());
        }

        finally {
            try {
                con.close(); // Cierra la conexion
            } catch (Exception e) {
                System.out.println("Error al cerrar la conexion: " + e.getMessage());
            }
        }

        return false;
    }

    @Override
    public boolean eliminarCliente(Cliente cliente) {
        PreparedStatement ps;
        Connection con = getConexion();
        String sql = "DELETE FROM cliente WHERE id = ?";

        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, cliente.getId());
            ps.execute();

            return true;
        } catch (Exception e){
            System.out.println("Error al eliminar el cliente: " + e.getMessage());
        }

        return false;
    }

    // Sistema de prueba
    /*public static void main(String[] args) {
        ClienteDAO clienteDAO = new ClienteDAO();

        // Buscar cliente por id
        System.out.println("*** Buscar Cliente por Id ***");
        Cliente cliente = new Cliente(5);
        System.out.println("Cliente: " + cliente);

        // Buscar cliente
        if(clienteDAO.buscarClientePorId(cliente)) {
            System.out.println("Cliente encontrado: " + cliente);
        } else {
            System.out.println("Cliente no encontrado");
        }*/

        // Agregar cliente
       /* Cliente newCliente = new Cliente("Rutilio", "Ruiz", 200);
        System.out.println("\n*** Agregar Cliente ***");

        if(clienteDAO.agregarCliente(newCliente)) {
            System.out.println("Cliente agregado: " + newCliente);
        } else {
            System.out.println("No se agrego el cliente: "+ newCliente);
        }*/

        // Modificar cliente
        /*Cliente modificarCliente = new Cliente(3, "Jacobito", "Ruiz", 300);
        System.out.println("\n*** Modificar Cliente ***");

        if(clienteDAO.modificarCliente(modificarCliente)) {
            System.out.println("Cliente modificado: " + modificarCliente);
        } else {
            System.out.println("No se modifico el cliente: " + modificarCliente);
        }*/

        // Eliminar cliente
        /*System.out.println("\n*** Eliminar Cliente ***");
        Cliente eliminarCliente = new Cliente(3);

        if (clienteDAO.eliminarCliente(eliminarCliente)) {
            System.out.println("Cliente eliminado: " + eliminarCliente);
        } else {
            System.out.println("No se elimino el cliente: " + eliminarCliente);
        }

        // Listar clientes
        System.out.println("\n*** Listar CLientes ***");
        List<Cliente> clientes = clienteDAO.listarClientes();
        clientes.forEach(System.out::println);
    }*/
}
