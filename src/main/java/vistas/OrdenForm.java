package vistas;

import com.example.demo1.models.OrdenDAO;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class OrdenForm extends Stage {
    private Scene escena;
    private HBox hBox;
    private Button btnGuardar;
    private TextField txtNombrePlatillo;
    public TextField txtCantidad;
    public TextField txtPrecio;
    private OrdenDAO ordenDAO;
    private TableView<OrdenDAO> tbvOrdenes;

    public OrdenForm(TableView<OrdenDAO> tbvOrdenes, OrdenDAO objOrdenDAO) {
        this.tbvOrdenes = tbvOrdenes;
        this.ordenDAO = objOrdenDAO == null ? new OrdenDAO() : objOrdenDAO;
        CrearUI();
        escena = new Scene(hBox);
        this.setTitle("Gestion de Ordenes");
        this.setScene(escena);
        this.show();
    }

    private void CrearUI() {

        TextField txtCantidad = new TextField();
        txtCantidad.setText(String.valueOf(ordenDAO.getCantidad()));
        txtCantidad.setPromptText("Cantidad");



        btnGuardar = new Button("Guardar");
        btnGuardar.setOnAction(event -> guardarOrden());

        hBox = new HBox( txtCantidad, btnGuardar);
        hBox.setSpacing(10);
        hBox.setPadding(new Insets(10));
    }

    private void guardarOrden() {
        ordenDAO.setCantidad(Integer.parseInt(txtCantidad.getText()));
        if (ordenDAO.getId_orden() > 0) {
           ordenDAO.ACTUALIZAR();
        } else {
            //ordenDAO.INSERTAR();
        tbvOrdenes.setItems(ordenDAO.LISTARCATEGORIAS());
        tbvOrdenes.refresh();
        //this.Close();
        }
    }
}

