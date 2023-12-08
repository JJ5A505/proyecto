package com.example.demo1.Components;
import com.example.demo1.models.PlatillosDAO;
import javafx.scene.control.*;
import vistas.PlatillosForm;

import java.util.Optional;

public class ButtonCellPlatillo extends TableCell<PlatillosDAO, String> {
    private Button btnCelda;
    private int opc;
    private TableView<PlatillosDAO>tbvPlatillos;
    private PlatillosDAO objP;
    public ButtonCellPlatillo(int opc){
        this.opc=opc;

        String txtBtn = this.opc ==1 ? "Editar" : "Eliminar";
        btnCelda=new Button(txtBtn);
        btnCelda.setOnAction(event -> accionBoton());

    }
    private void accionBoton(){
        tbvPlatillos = ButtonCellPlatillo.this.getTableView();
        objP = tbvPlatillos.getItems().get(ButtonCellPlatillo.this.getIndex());
        if(this.opc==1){
            new PlatillosForm(tbvPlatillos,objP);
        }else{
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Topicos Avanzados de Programacion");
            alert.setHeaderText("Confirmacion del sistema");
            alert.setContentText("Deseas eliminar el platillo");

            Optional<ButtonType> result = alert.showAndWait();
            if(result.get()== ButtonType.OK){
                objP.ELIMINAR();
                tbvPlatillos.setItems(objP.LISTARPLATILLOS());
                tbvPlatillos.refresh();
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
