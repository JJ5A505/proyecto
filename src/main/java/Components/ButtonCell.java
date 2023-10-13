package Components;

import javafx.scene.control.*;
import modelos.CategoriasDAO;
import vistas.CategoriasForm;

import java.util.Optional;

public class ButtonCell extends TableCell<CategoriasDAO,String> {
    private Button btnCelda;
    private int opc;
    private TableView<CategoriasDAO>tbvCategorias;
    private CategoriasDAO objCat;

    public ButtonCell(int opc){
        this.opc=opc;

        String txtBtn = this.opc == 1 ? "Editar" : "Eliminar";
        btnCelda = new Button(txtBtn);
        btnCelda.setOnAction(event -> accionBoton());
    }

    private void accionBoton() {
        tbvCategorias = ButtonCell.this.getTableView();
        objCat= tbvCategorias.getItems().get(ButtonCell.this.getIndex());
        if(this.opc ==1){
            new CategoriasForm(tbvCategorias,objCat);

        }else{
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Topicos Avanzados de Programacion");
            alert.setHeaderText("Confirmacion del Sistema");
            alert.setContentText("Deseas eliminar la categoria?");

            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK){
                objCat.ELIMINAR();
                tbvCategorias.setItems(objCat.LISTARCATEGORIAS());
                tbvCategorias.refresh();
                // ... user chose OK
            }
        }
    }
    @Override
    protected void updateItem(String s, boolean b) {
        super.updateItem(s, b);
        if(!b){
            this.setGraphic(btnCelda);
        }
    }
}
