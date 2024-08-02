package zona_fit.presentacion;

import zona_fit.datos.ClienteDAO;
import zona_fit.datos.IClienteDAO;
import zona_fit.dominio.Cliente;

import java.util.Scanner;

public class ZonaFitApp {
    public static void main(String[] args) {
        zonaFitApp();
    }

    private static void zonaFitApp() {
        // Variables
        boolean salir = false;
        Scanner consola = new Scanner(System.in);

        // Creamo el objeto con los metodos para acceder a la base de datos
        IClienteDAO clienteDao = new ClienteDAO();

        // Menu
        while (!salir){
            try{
                int opcion = mostrarMenu(consola);
                salir = ejecutarOpciones(consola, clienteDao, opcion);
            }catch (Exception e){
                System.out.println("Error al ejecutar opciones" + e.getMessage());
            }

            // Crar un salto de linea
            System.out.println();
        }
    }

    private static int mostrarMenu(Scanner consola) {
        System.out.print("""
                *** Zona Fit Panamá ***
                1. Listar Clientes
                2. Buscar Clientes
                3. Agregar Cliente
                4. Modificar Cliente
                5. Eliminar Cliente
                6. Salir
                Elije una opcion:\s""");
        return Integer.parseInt(consola.nextLine());
    }

    private static boolean ejecutarOpciones(Scanner consola, IClienteDAO clienteDAO, int opcion) {
        var salir = false;

        switch (opcion){
            case 1-> {
                System.out.println("*** Listado de Clientes ***");
                clienteDAO.listarClientes().forEach(System.out::println);
            }

            case 2-> {
                System.out.println("*** Busqueda de cliente ***");

                // Obtener el id del cliente
                System.out.print("Ingrese el id del cliente: ");
                int id = Integer.parseInt(consola.nextLine());
                Cliente idCliente = new Cliente(id);

                //Ejecutar la busqueda
                if(clienteDAO.buscarClientePorId(idCliente)){
                    System.out.println("Cliente encontrado: " + idCliente);
                } else {
                    System.out.println("Cliente no encontrado");
                }
            }

            case 3-> {
                System.out.println("*** Agregar Cliente ***");

                // Datos del nuevo cliente
                System.out.print("Ingrese el nombre del cliente: ");
                String nombre = consola.nextLine();
                System.out.print("Ingrese el apellido del cliente: ");
                String apellido = consola.nextLine();
                System.out.print("Ingrese la membresia del cliente: ");
                int membresia = Integer.parseInt(consola.nextLine());

                // Creando un objeto cliente con los datos del nuevo cliente
                Cliente nuevoCliente = new Cliente(nombre, apellido, membresia);

                // Ejecutar la insercion
                if(clienteDAO.agregarCliente(nuevoCliente)) {
                    System.out.println("Cliente agregado: " + nuevoCliente);
                } else {
                    System.out.println("No se agrego el cliente: " + nuevoCliente);
                }
            }

            case 4-> {
                System.out.println("*** Modificar Cliente ***");

                // Datos del cliente a modificar
                System.out.print("Ingrese el id del cliente a modificar: ");
                int idCliente = Integer.parseInt(consola.nextLine());
                System.out.print("Ingrese el nombre a modificar: ");
                String nombre = consola.nextLine();
                System.out.print("Ingrese el apellido a modificar: ");
                String apellido = consola.nextLine();
                System.out.print("Ingrese la membresia a modificar: ");
                int membresia = Integer.parseInt(consola.nextLine());

                Cliente modificarCliente = new Cliente(idCliente, nombre, apellido, membresia);

                // Ejecutar la modificacion
                if(clienteDAO.modificarCliente(modificarCliente)) {
                    System.out.println("Cliente modificado: "+ modificarCliente);
                } else {
                    System.out.println("Error al modificar cliente: " + modificarCliente);
                }
            }

            case 5-> {

                System.out.println("*** Eliminar Cliente ***");

                // Id del cliente que se va a eliminar
                System.out.print("Ingrese el id del cliente a eliminar: ");
                int idCLiente = Integer.parseInt(consola.nextLine());

                // Crear el objeto Cliente
                Cliente eliminarCliente = new Cliente(idCLiente);

                // Ejecutar sentencia
                if(clienteDAO.eliminarCliente(eliminarCliente)) {
                    System.out.println("Cliente eliminado: " + eliminarCliente);
                } else {
                    System.out.println("Error al eliminar el cliente: "+ eliminarCliente);
                }
            }

            case 6-> {
                System.out.println("\nSaliendo de la aplicacion - Hasta Pronto!");
                salir = true;
            }

            default -> System.out.println("Opcion no valida: " + opcion);
        }

        return salir;
    }
}
