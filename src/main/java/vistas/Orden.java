package vistas;
import com.example.demo1.Components.ButtonCell;
import com.example.demo1.Components.ButtonCellOrden;
import com.example.demo1.models.CategoriasDAO;
import com.example.demo1.models.OrdenDAO;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Callback;
import org.kordamp.bootstrapfx.BootstrapFX;
import org.kordamp.bootstrapfx.scene.layout.Panel;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class Orden extends Stage {
    private Button[][] arBoton = new Button[2][2];

    private GridPane grdTablilla;
    private ImageView imv;
    private TableView<OrdenDAO> tbvOrden;
    private OrdenDAO ordenDAO;
    private VBox vBox,vBoxtable;
    private HBox hBoxprincipal;

    private Scene scene;



    public Orden() {
        CrearUI();
        grdTablilla=new GridPane();
        scene=new Scene(hBoxprincipal,800,600);

        Panel panel = new Panel("This is the title");
        panel.getStyleClass().add("panel-primary");                            //(2)
        BorderPane content = new BorderPane();
        content.setPadding(new Insets(20));
        content.setCenter(hBoxprincipal);
        panel.setBody(content);
        Scene scene = new Scene(panel);
        scene.getStylesheets().add(BootstrapFX.bootstrapFXStylesheet());       //(3)
        this.setTitle("BootstrapFX");
        this.setScene(scene);
        this.sizeToScene();
        this.show();
    }

    private void CrearUI() {
        CrearMenu();
        ordenDAO = new OrdenDAO();
        tbvOrden = new TableView<OrdenDAO>();
        CrearTable();
        vBoxtable= new VBox(tbvOrden);
        vBox=new VBox(grdTablilla);
        hBoxprincipal= new HBox(vBox,vBoxtable);

       /* Panel panel = new Panel("Menu");
        panel.getStyleClass().add("panel-primary");

        BorderPane content = new BorderPane();
        content.setPadding(new Insets(20));
        content.setCenter(hBoxprincipal);
        panel.setBody(content);

        Scene scene = new Scene(panel);
        scene.getStylesheets().add(BootstrapFX.bootstrapFXStylesheet());

        this.setTitle("Cafeteria XD");
        this.setScene(scene);
        this.sizeToScene();
        this.setMinWidth(320);
        this.setMinHeight(480);
        this.show();*/
    }


    private void CrearMenu() {
        String[] arImagenes = {"comidas.jpg", "bebidas.jpg", "postres.jpg", "frappes.jpg"};
        grdTablilla = new GridPane();
        int pos = 0;



        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 2; j++) {
                ImageView imv;
                try {
                    InputStream stream = new FileInputStream("src/main/java/imvRestaurante/" + arImagenes[pos]);
                    Image imgCartaP = new Image(stream);
                    imv = new ImageView(imgCartaP);
                    pos++;
                } catch (FileNotFoundException e) {
                    throw new RuntimeException(e);
                }
                imv.setFitHeight(110);
                imv.setFitWidth(90);
                arBoton[i][j] = new Button();
                arBoton[i][j].setGraphic(imv);
                arBoton[i][j].setPrefSize(90, 110);
                grdTablilla.add(arBoton[i][j], i, j);



        }
    }
        arBoton[0][0].setOnAction(event -> new comidas());
        arBoton[0][1].setOnAction(event -> new bebidas());
        arBoton[1][0].setOnAction(event -> new postres());
        arBoton[1][1].setOnAction(event -> new frappes());
    }
    private void CrearTable() {
        TableColumn<OrdenDAO, Integer> tbcIdOrden = new TableColumn<OrdenDAO, Integer>("ID");
        tbcIdOrden.setCellValueFactory(new PropertyValueFactory<>("id_orden"));

        TableColumn<OrdenDAO, String> tbcNombrePlatillo = new TableColumn<OrdenDAO, String>("Nombre Platillo");
        tbcNombrePlatillo.setCellValueFactory(new PropertyValueFactory<>("nombre"));

        TableColumn<OrdenDAO, Integer> tbcCantidad = new TableColumn<OrdenDAO, Integer>("Cantidad");
        tbcCantidad.setCellValueFactory(new PropertyValueFactory<>("cantidad"));

        TableColumn<OrdenDAO, Double> tbcPrecio = new TableColumn<OrdenDAO, Double>("Precio");
        tbcPrecio.setCellValueFactory(new PropertyValueFactory<>("precio"));

        TableColumn<OrdenDAO, String> tbcEditar = new TableColumn<>("Editar");
        tbcEditar.setCellFactory(
                new Callback<TableColumn<OrdenDAO, String>, TableCell<OrdenDAO, String>>() {
                    @Override
                    public TableCell<OrdenDAO, String> call(TableColumn<OrdenDAO, String> param) {
                        return new ButtonCellOrden(1);
                    }
                }
        );

        TableColumn<OrdenDAO, String> tbcEliminar = new TableColumn<>("Eliminar");
        tbcEliminar.setCellFactory(
                new Callback<TableColumn<OrdenDAO, String>, TableCell<OrdenDAO, String>>() {
                    @Override
                    public TableCell<OrdenDAO, String> call(TableColumn<OrdenDAO, String> param) {
                        return new ButtonCellOrden(2);
                    }
                }
        );

        tbvOrden.getColumns().addAll(tbcIdOrden, tbcNombrePlatillo, tbcCantidad, tbcPrecio, tbcEditar, tbcEliminar);

        // Crear ListarCategorias();
        tbvOrden.setItems(ordenDAO.LISTARCATEGORIAS());
    }

}