package com.example.demo1.Components;

import com.example.demo1.models.OrdenDAO;
import javafx.scene.control.*;
import vistas.OrdenForm;

import java.util.Optional;

public class ButtonCellOrden extends TableCell<OrdenDAO, String> {
    private Button btnCelda;
    private int opc;
    private TableView<OrdenDAO> tbvOrdenes;
    private OrdenDAO objO;

    public ButtonCellOrden(int opc) {
        this.opc = opc;

        String txtBtn = this.opc == 1 ? "Editar" : "Eliminar";
        btnCelda = new Button(txtBtn);
        btnCelda.setOnAction(event -> accionBoton());
    }

    private void accionBoton() {
        tbvOrdenes = ButtonCellOrden.this.getTableView();
        objO = tbvOrdenes.getItems().get(ButtonCellOrden.this.getIndex());

        if (this.opc == 1) {
         objO.ACTUALIZAR();
            tbvOrdenes.setItems(objO.LISTARCATEGORIAS());
            tbvOrdenes.refresh();
        } else {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Topicos Avanzados de Programacion");
            alert.setHeaderText("Confirmacion del Sistema");
            alert.setContentText("Deseas eliminar la orden?");

            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK) {
                objO.ELIMINAR();
                tbvOrdenes.setItems(objO.LISTARCATEGORIAS());
                tbvOrdenes.refresh();
                // ... user chose OK
            }
        }
    }


    @Override
    protected void updateItem(String s, boolean b) {
        super.updateItem(s, b);
        if (!b) {
            this.setGraphic(btnCelda);
        }
    }
}
