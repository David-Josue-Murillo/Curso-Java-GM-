package dm.tareas.controlador;

import dm.tareas.modelo.Tarea;
import dm.tareas.servicio.TareaServicio;
import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.cell.PropertyValueFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.scene.control.*;

@Component
public class IndexControlador implements Initializable {
    private static final Logger logger = LoggerFactory.getLogger(IndexControlador.class);

    private Integer idTareaInterno;

    @Autowired
    private TareaServicio tareaServicio;

    @FXML
    private TableView<Tarea> tareaTabla;

    @FXML
    private TableColumn<Tarea, Integer> idTareaColumna;

    @FXML
    private TableColumn<Tarea, String> nombreTareaColumna;

    @FXML
    private TableColumn<Tarea, String> responsableColumna;

    @FXML
    private TableColumn<Tarea, String> estatusColumna;

    @FXML
    private TextField nombreTareaTexto;

    @FXML
    private TextField responsableTexto;

    @FXML
    private  TextField estatusTexto;

    private final ObservableList<Tarea> tareaList = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Solo permite agarrar una fila de la tabla
        tareaTabla.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        configurarColumnas();
        listarTareas();
    }

    private  void listarTareas() {
        tareaList.clear();
        tareaList.setAll(tareaServicio.listarTarea()); // Se obtiene los datos de la db
        tareaTabla.setItems(tareaList); // Se muestra en la tabla
    }

    private void configurarColumnas() {
        logger.info("Ejecutando estado de tareas");
        idTareaColumna.setCellValueFactory(new PropertyValueFactory<>("idTarea"));
        nombreTareaColumna.setCellValueFactory(new PropertyValueFactory<>("nombreTarea"));
        responsableColumna.setCellValueFactory(new PropertyValueFactory<>("responsable"));
        estatusColumna.setCellValueFactory(new PropertyValueFactory<>("estatus"));
    }

    public void agregarTarea() {
        if(nombreTareaTexto.getText().isEmpty()){
            mostrarMensaje("Error Validación", "El campo nombre de tarea es requerido");
            nombreTareaTexto.requestFocus(); //Enfoca el input
            return;
        } else {
            Tarea tarea = new Tarea();
            recolctarDatosFormulario(tarea);
            tarea.setIdTarea(null);
            tareaServicio.guardarTarea(tarea);
            mostrarMensaje("Información", "Tarea guardada correctamente");
            listarTareas();
            limpiarCampos();
        }
    }

    public void modificarTarea() {
        if(idTareaInterno == null){
            mostrarMensaje("Información", "Debe seleccionar una tarea de la tabla");
            return;
        }

        if(nombreTareaTexto.getText().isEmpty()){
            mostrarMensaje("Error Validación", "El campo nombre de tarea es requerido");
            nombreTareaTexto.requestFocus(); //Enfoca el input
            return;
        }

        Tarea tarea = tareaServicio.buscarTareaPorId(idTareaInterno);
        recolctarDatosFormulario(tarea);
        tareaServicio.guardarTarea(tarea);
        mostrarMensaje("Información", "Tarea modificada correctamente");
        listarTareas();
        limpiarCampos();
    }

    public void cargarTareaFormulario() {
        Tarea tarea = tareaTabla.getSelectionModel().getSelectedItem();
        if(tarea != null){
            idTareaInterno = tarea.getIdTarea();
            nombreTareaTexto.setText(tarea.getNombreTarea());
            responsableTexto.setText(tarea.getResponsable());
            estatusTexto.setText(tarea.getEstatus());
        }
    }

    public void eliminarTarea() {
        Tarea tarea = tareaTabla.getSelectionModel().getSelectedItem();
        if(tarea != null){
            logger.info("Registro a eliminar: "+ tarea.toString());
            tareaServicio.eliminarTarea(tarea);
            mostrarMensaje("Información", "Tarea eliminada " + tarea.getIdTarea());
            listarTareas();
            limpiarCampos();
        } else {
            mostrarMensaje("Información", "Debe seleccionar una tarea de la tabla");
        }
    }

    private void recolctarDatosFormulario(Tarea tarea){
        if(idTareaInterno != null){
            tarea.setIdTarea(idTareaInterno);
        }
        tarea.setNombreTarea(nombreTareaTexto.getText());
        tarea.setResponsable(responsableTexto.getText());
        tarea.setEstatus(estatusTexto.getText());
    }

    private void mostrarMensaje(String titulo, String mensaje){
        Alert alerta = new Alert(Alert.AlertType.INFORMATION);
        alerta.setTitle(titulo);
        alerta.setHeaderText(null);
        alerta.setContentText(mensaje);
        alerta.showAndWait();
    }

    public void limpiarCampos() {
        idTareaInterno = null;
        nombreTareaTexto.clear();
        responsableTexto.clear();
        estatusTexto.clear();
    }
}
