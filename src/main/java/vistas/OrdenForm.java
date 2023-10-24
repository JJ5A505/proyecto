package vistas;

import com.example.demo1.models.Conexion;
import com.example.demo1.models.OrdenDAO;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.sql.Statement;
import java.util.Optional;

public class OrdenForm extends Stage {
    private Scene escena;
    private HBox hBox;
    private Button btnGuardar;

    public TextField txtCantidad;

    private OrdenDAO ordenDAO;
    private TableView<OrdenDAO> tbvOrdenes;
    private int cantidad;

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
        txtCantidad.setPromptText("Cantidad");
        txtCantidad.setText(String.valueOf(1));

        // Abre una ventana emergente
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Cantidad");
        dialog.setContentText("Ingrese la cantidad");
        dialog.getEditor().setText(txtCantidad.getText());

        Optional<String> result = dialog.showAndWait();
        if (result.isPresent()) {
            // El usuario ingresó un valor
            int cantidad = Integer.parseInt(result.get());
            // Haz algo con el valor
            this.cantidad = cantidad;
            if(cantidad>0){
                try {
                    String query = "UPDATE detalle SET cantidad = '"+this.cantidad+"'"+
                            "WHERE id_orden = "+this.ordenDAO.id_orden+" and id_platillo="+this.ordenDAO.id_platillo;
                    Statement stmt = Conexion.conexion.createStatement();
                    stmt.executeUpdate(query);
                }catch (Exception e){
                    e.printStackTrace();

                }
                tbvOrdenes.setItems(ordenDAO.LISTARCATEGORIAS());
                tbvOrdenes.refresh();
            }
        }else {
            this.cantidad=0;
        }
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

