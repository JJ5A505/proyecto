package vistas;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import com.example.demo1.models.CategoriasDAO;

public class CategoriasForm extends Stage {
    private Scene escena;
    private HBox hBox;
    private Button btnGuardar;
    private TextField txtNameCat;
    private CategoriasDAO categoriasDAO;
TableView<CategoriasDAO>tbvCategorias;
    public CategoriasForm(TableView<CategoriasDAO>tbvCat,CategoriasDAO objCatDAO){
        this.tbvCategorias = tbvCat;
        this.categoriasDAO = objCatDAO==null ? new CategoriasDAO() : objCatDAO;
        CrearUI();
        escena= new Scene(hBox);
        this.setTitle("Gestion de Categorias");
        this.setScene(escena);
        this.show();
    }

    private void CrearUI() {

        txtNameCat = new TextField();
        txtNameCat.setText(categoriasDAO.getNomCategoria());
        txtNameCat.setPromptText("Nombre de la categoria");
        btnGuardar = new Button("Guardar");
        btnGuardar.setOnAction(event -> guardarCategoria());
        hBox = new HBox(txtNameCat,btnGuardar);
        hBox.setSpacing(10);
        hBox.setPadding(new Insets(10));


    }
    private void guardarCategoria(){
        categoriasDAO.setNomCategoria(txtNameCat.getText());
        if (categoriasDAO.getIdCategoria() >0)
            categoriasDAO.ACTUALIZAR();
        else
        categoriasDAO.INSERTAR();
        tbvCategorias.setItems(categoriasDAO.LISTARCATEGORIAS());
        tbvCategorias.refresh();
        this.close();
    }
}
