package vistas;

import com.example.demo1.Components.ButtonCellPlatillo;
import com.example.demo1.models.PlatillosDAO;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Callback;
import org.kordamp.bootstrapfx.BootstrapFX;
import org.kordamp.bootstrapfx.scene.layout.Panel;

import java.sql.Statement;

public class Platillo extends Stage {
    private VBox vBox;
    private TableView<PlatillosDAO> tbvPlatillos;
    private Button btnAgregar,btnEliminar;
    private PlatillosDAO platillosDAO;
    public Platillo(){

        CrearUI();
        Panel panel = new Panel("This is the title");
        panel.getStyleClass().add("panel-primary");                            //(2)
        BorderPane content = new BorderPane();
        content.setPadding(new Insets(20));
        content.setCenter(vBox);
        panel.setBody(content);
        panel.setBody(content);
        Scene scene = new Scene(panel);
        scene.getStylesheets().add(BootstrapFX.bootstrapFXStylesheet());       //(3)
        this.setTitle("BootstrapFX");
        this.setScene(scene);
        this.sizeToScene();
        this.show();
    }
    private void CrearUI(){
        platillosDAO=new PlatillosDAO();
        tbvPlatillos=new TableView<PlatillosDAO>();
        CreateTable();
        btnAgregar=new Button("Agregar");
        btnAgregar.getStylesheets().setAll("btn","btn-success");
        btnAgregar.setOnAction((event) -> new PlatillosForm(tbvPlatillos, null));
        vBox=new VBox(tbvPlatillos,btnAgregar);
    }
private void CreateTable(){
        TableColumn<PlatillosDAO,Integer>tbcIdPla = new TableColumn<PlatillosDAO,Integer>("ID");
        tbcIdPla.setCellValueFactory(new PropertyValueFactory<>("idPlatillo"));

        TableColumn<PlatillosDAO,String>tbcNombre = new TableColumn<PlatillosDAO,String>("Platillos");
        tbcNombre.setCellValueFactory(new PropertyValueFactory<>("nomPlatillo"));

        TableColumn<PlatillosDAO,Double>tbcPrecio = new TableColumn<PlatillosDAO,Double>("Precio");
        tbcPrecio.setCellValueFactory(new PropertyValueFactory<>("prePlatillo"));

        TableColumn<PlatillosDAO,Integer>tbcIdCat = new TableColumn<PlatillosDAO,Integer>("IdCategoria");
        tbcIdCat.setCellValueFactory(new PropertyValueFactory<>("idCat"));

        TableColumn<PlatillosDAO,String>tbcEditar = new TableColumn<>("Editar");
        tbcEditar.setCellFactory(
                new Callback<TableColumn<PlatillosDAO, String>, TableCell<PlatillosDAO, String>>() {
                    @Override
                    public TableCell<PlatillosDAO, String> call(TableColumn<PlatillosDAO, String> param) {
                        return new ButtonCellPlatillo(1);
                    }
                }
        );
        TableColumn<PlatillosDAO,String>tbcEliminar = new TableColumn<>("Eliminar");
        tbcEliminar.setCellFactory(

                new Callback<TableColumn<PlatillosDAO, String>, TableCell<PlatillosDAO, String>>() {
                    @Override
                    public TableCell<PlatillosDAO, String> call(TableColumn<PlatillosDAO, String> param) {
                        return new ButtonCellPlatillo(2);
                    }
                }
        );
        tbvPlatillos.getColumns().addAll(tbcIdPla,tbcNombre,tbcPrecio,tbcIdCat,tbcEditar,tbcEliminar);
        tbvPlatillos.setItems(platillosDAO.LISTARPLATILLOS());
}
}
