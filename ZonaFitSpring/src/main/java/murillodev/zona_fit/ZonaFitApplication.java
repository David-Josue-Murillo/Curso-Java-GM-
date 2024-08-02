package murillodev.zona_fit;

import murillodev.zona_fit.modelo.Cliente;
import murillodev.zona_fit.servicio.IClienteServicio;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.sql.Struct;
import java.util.List;
import java.util.Scanner;

//@SpringBootApplication
public class ZonaFitApplication implements CommandLineRunner {

	@Autowired
	private IClienteServicio clienteServicio;

	private static final Logger logger = LoggerFactory.getLogger(ZonaFitApplication.class);

	public static void main(String[] args) {
		logger.info("Iniciando la aplicación"); // Levantando Spring
		SpringApplication.run(ZonaFitApplication.class, args);
		logger.info("Finalizando la aplicación"); // Levantando Spring
	}

	@Override
	public void run(String... args) throws Exception {
		zonaFitApp();
	}

	private void zonaFitApp() {
		logger.info("");
		logger.info("*** Aplicación Zona Fit Gym ***");

		//Variables
		Boolean salir = false;
		Scanner consola = new Scanner(System.in);

		while(!salir) {
			int opcion = mostrarMenu(consola);
			salir = ejecutarOpciones(consola, opcion);
		}
	}

	private int mostrarMenu(Scanner consola) {
		logger.info("""
				1. Listar Clientes
				2. Buscar Cliente
				3. Agregar Cliente
				4. Modificar Cliente
				5. Eliminar Cliente
				6. Salir
				Elige una opcion: """);

		return Integer.parseInt(consola.nextLine());
	}

	private boolean ejecutarOpciones(Scanner consola, int opcion) {
		boolean salir = false;

		switch (opcion){
			case 1-> {
				logger.info("--- Listado de clientes ---");
				List<Cliente> clientes = clienteServicio.listarCliente();
				clientes.forEach(cliente -> logger.info(cliente.toString()));
				logger.info("");
			}

			case 2->{
				logger.info("--- Buscar cliente por Id ---");
				int idCliente = Integer.parseInt(consola.nextLine());
				Cliente cliente = clienteServicio.buscarClientePorId(idCliente);

				// Verificar si no exite el cliente
				if(cliente != null){
					logger.info("Cliente encontrado: " + cliente);
				} else {
					logger.info("Cliente no encontrado: " + cliente);
				}
			}

			case 3->{
				logger.info("--- Agregar Cliente ---");

				logger.info("Nombre: ");
				String nombre = consola.nextLine();

				logger.info("Apellido: ");
				String apellido = consola.nextLine();

				logger.info("Membresia: ");
				int membresia = Integer.parseInt(consola.nextLine());

				// Creamos el cliente
				Cliente cliente = new Cliente();
				cliente.setNombre(nombre);
				cliente.setApellido(apellido);
				cliente.setMembresia(membresia);

				// Creamos el nuevo cliente en la db
				clienteServicio.guardarCliente(cliente);
				logger.info("Cliente agregado: "+ cliente);

			}

			case 4->{
				logger.info("--- Modificar Cliente ---");

				logger.info("Id CLiente: ");
				int idCliente = Integer.parseInt(consola.nextLine());
				Cliente cliente = clienteServicio.buscarClientePorId(idCliente);

				if(cliente != null){
					logger.info("Nombre: ");
					String nombre = consola.nextLine();

					logger.info("Apellido: ");
					String apellido = consola.nextLine();

					logger.info("Membresia: ");
					int membresia = Integer.parseInt(consola.nextLine());

					// Modificando el cliente
					cliente.setNombre(nombre);
					cliente.setApellido(apellido);
					cliente.setMembresia(membresia);
					clienteServicio.guardarCliente(cliente);

					logger.info("Cliente modificado: "+ cliente);

				} else {
					logger.info("Cliente no encontrado: "+ cliente);
				}
			}

			case 5->{
				logger.info("--- Eliminar Cliente ---");

				logger.info("Id cliente: ");
				int idCliente = Integer.parseInt(consola.nextLine());
				Cliente cliente = clienteServicio.buscarClientePorId(idCliente);

				if(cliente != null) {
					clienteServicio.eliminarCliente(cliente);
					logger.info("Cliente eliminado: "+ cliente);
				} else {
					logger.info("Cliente no encontrado: "+ cliente);
				}
			}

			case 6->{
				logger.info("Hasta luego, vuelve pronto!");
				salir = true;
			}

			default -> {logger.info("Opción invalida: "+ opcion);}
		}

		return salir;
	}
}
