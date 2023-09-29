package vistas;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import modelos.CategoriasDAO;
import org.kordamp.bootstrapfx.BootstrapFX;
import org.kordamp.bootstrapfx.scene.layout.Panel;

public class Restaurante extends Stage {
    private VBox vBox;
    private TableView<CategoriasDAO> tbvCategorias;
    private Button btnAgregar,btnElininar;
    private CategoriasDAO categoriasDAO;
    public Restaurante(){
        CrearUI();
        Panel panel = new Panel("This is the title");
        panel.getStyleClass().add("panel-primary");                            //(2)
        BorderPane content = new BorderPane();
        content.setPadding(new Insets(20));
content.setCenter(vBox);
panel.setBody(content);
        //Button button = new Button("Hello BootstrapFX");
        //button.getStyleClass().setAll("btn","btn-danger");                     //(2)
        //content.setCenter(button);

        panel.setBody(content);

        Scene scene = new Scene(panel);
        scene.getStylesheets().add(BootstrapFX.bootstrapFXStylesheet());       //(3)

        this.setTitle("BootstrapFX");
        this.setScene(scene);
        this.sizeToScene();
        this.show();
    }
    private void CrearUI(){
        categoriasDAO = new CategoriasDAO();
        tbvCategorias = new TableView<CategoriasDAO>();
        CrearTable();
        btnAgregar= new Button();
        btnAgregar.getStylesheets().setAll("btn","btn-success");
        btnAgregar.setOnAction((event)->{});
        vBox = new VBox(tbvCategorias,btnAgregar);

    }
    private void CrearTable(){
        tbvCategorias.setItems(categoriasDAO.LISTARCATEGORIAS());
    }
}
