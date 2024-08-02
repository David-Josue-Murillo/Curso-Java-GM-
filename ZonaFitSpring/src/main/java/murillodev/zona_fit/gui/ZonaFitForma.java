package murillodev.zona_fit.gui;

import murillodev.zona_fit.modelo.Cliente;
import murillodev.zona_fit.servicio.ClienteServicio;
import murillodev.zona_fit.servicio.IClienteServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

//@Component
public class ZonaFitForma extends JFrame {
    private JPanel panelPrincipal;
    private JTable clientesTabla;
    private JTextField nombreTexto;
    private JTextField apellitoTexto;
    private JTextField membresiaTexto;
    private JButton guardarButton;
    private JButton eliminarButton;
    private JButton limpiarButton;
    IClienteServicio clienteServicio;
    private DefaultTableModel tablaModeloCLientes;
    private Integer idCliente;

    //@Autowired
    public ZonaFitForma(ClienteServicio clienteServicio) {
        this.clienteServicio = clienteServicio;
        iniciarForma();
        guardarButton.addActionListener(e -> guardarCliente());
        eliminarButton.addActionListener(e -> eliminarCliente());
        clientesTabla.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                cargarClienteSeleccionado();
            }
        });
        limpiarButton.addActionListener(e-> limpiarFormulario());
    }

    private void iniciarForma() {
        setContentPane(panelPrincipal);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(900, 700);
        setLocationRelativeTo(null);
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
        this.tablaModeloCLientes = new DefaultTableModel(0, 4) {    // Se crea el modelo de la tabla
            // Se bloquea la posibilidad de editar directamente las celdas de la tabla
            public boolean isCellEditable(int row, int col) {
                return false;
            }
        };

        String[] cabeceros = {"Id", "Nombre", "Apellido", "Membresia"};
        this.tablaModeloCLientes.setColumnIdentifiers(cabeceros); // Agregamos las cabeceras en la tabla
        this.clientesTabla = new JTable(tablaModeloCLientes);

        //Restingimos la seleccion de la tabla a un solo registro
        this.clientesTabla.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        // Cargar listado de clientes
        listarClientes();
    }

    private void listarClientes() {
        this.tablaModeloCLientes.setNumRows(0);

        // Obtener clientes de la db
        var clientes = this.clienteServicio.listarCliente();
        clientes.forEach(cliente -> { // Se itera los clientes
            // Creamos un objeto de cada cliente
            Object[] renglonCliente = {
                    cliente.getId(),
                    cliente.getNombre(),
                    cliente.getApellido(),
                    cliente.getMembresia()
            };

            // Agregamos el objeto cliente a la tabla
            this.tablaModeloCLientes.addRow(renglonCliente);
        });
    }

    private void guardarCliente() {
        if(nombreTexto.getText().equals("")) {
            mostrarMensaje("Proporciona un nombre"); // Mensaje en caso de estar vacio el campo
            nombreTexto.requestFocusInWindow(); //El borde del input se ilumina
            return;
        }

        if(apellitoTexto.getText().equals("")){
            mostrarMensaje("Proporciona un apellido"); // Mensaje en caso de estar vacio el campo
            nombreTexto.requestFocusInWindow(); //El borde del input se ilumina
            return;
        }

        if(membresiaTexto.getText().equals("")){
            mostrarMensaje("Proporciona una membresia"); // Mensaje en caso de estar vacio el campo
            nombreTexto.requestFocusInWindow(); //El borde del input se ilumina
            return;
        }

        // Recuperamos los datos del formulario
        var nombre = nombreTexto.getText();
        var apellido = apellitoTexto.getText();
        int membresia = Integer.parseInt(membresiaTexto.getText());
        Cliente cliente = new Cliente(this.idCliente, nombre, apellido, membresia); // Se crea el objeto cliente con los datos del formulario

        // Insertar el cliente en la base de datos
        this.clienteServicio.guardarCliente(cliente);

        // Mostrar la accion realizada
        if(this.idCliente == null){
            mostrarMensaje("Se agrego el nuevo cliente");
        } else {
            mostrarMensaje("Se actualizo el cliente");
        }

        // Limpiar formulario
        limpiarFormulario();
        listarClientes(); // Actualiza la tbala de clientes
    }

    private void eliminarCliente() {
        var renglon = clientesTabla.getSelectedRow();

        if(renglon != -1){
            String id = clientesTabla.getModel().getValueAt(renglon, 0).toString();
            this.idCliente = Integer.parseInt(id);

            // Objeto del cliente a eliminar
            Cliente eliminarCliente = new Cliente();
            eliminarCliente.setId(this.idCliente);
            clienteServicio.eliminarCliente(eliminarCliente);
            mostrarMensaje("Cliente con id " +this.idCliente+ " Eliminado");

            // Limpiar el formulario y recargar la tabla
            limpiarFormulario();
            listarClientes();
        } else {
            mostrarMensaje("Debe seleccionar un cliente a eliminar");
        }
    }

    private void cargarClienteSeleccionado() {
         var renglon = clientesTabla.getSelectedRow();
         if(renglon != -1) { // -1 significa que no selecciono ningun registro

             // Autorellenamos los campos del formulario
             String id = clientesTabla.getModel().getValueAt(renglon, 0).toString();
            this.idCliente = Integer.parseInt(id);

            String nombre = clientesTabla.getModel().getValueAt(renglon, 1).toString();
            this.nombreTexto.setText(nombre);

            String apellido = clientesTabla.getModel().getValueAt(renglon, 2).toString();
            this.apellitoTexto.setText(apellido);

            String membresia = clientesTabla.getModel().getValueAt(renglon, 3).toString();
            this.membresiaTexto.setText(membresia);
         }
    }

    private void mostrarMensaje(String mensaje){
        JOptionPane.showMessageDialog(this, mensaje); //Crea una ventana de alerta con el mensaje
    }

    private void limpiarFormulario() {
        nombreTexto.setText("");
        apellitoTexto.setText("");
        membresiaTexto.setText("");

        // Limpiar la id del cliente seleccionado
        this.idCliente = null;

        // Deseleccionar el registro seleccionado de la tabla
        this.clientesTabla.getSelectionModel().clearSelection();
    }
}
