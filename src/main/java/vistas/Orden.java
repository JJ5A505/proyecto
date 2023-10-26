package vistas;
import com.example.demo1.Components.ButtonCell;
import com.example.demo1.Components.ButtonCellOrden;
import com.example.demo1.models.CategoriasDAO;
import com.example.demo1.models.Conexion;
import com.example.demo1.models.OrdenDAO;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
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
import java.sql.Statement;
import java.util.Date;
import java.util.Optional;

public class Orden extends Stage {
    private Button[][] arBoton = new Button[2][2];
    private GridPane grdTablilla;

    private TableView<OrdenDAO> tbvOrden;
    private OrdenDAO ordenDAO;
    private VBox vBox, vBoxtable;
    private HBox hBoxprincipal;
    private Button btnGuardar, btnNuevaCategoria, btnNuevoPlatillo;
    private Scene scene;
    private Restaurante restaurante;


    public Orden() {

        CrearUI();
        grdTablilla = new GridPane();
        scene = new Scene(hBoxprincipal, 1000, 600);

        Panel panel = new Panel("Menu ");
        panel.getStyleClass().add("panel-primary");                            //(2)
        BorderPane content = new BorderPane();
        content.setPadding(new Insets(20));
        content.setCenter(hBoxprincipal);
        panel.setBody(content);
        Scene scene = new Scene(panel);
        scene.getStylesheets().add(BootstrapFX.bootstrapFXStylesheet());       //(3)
        this.setTitle("Cafeteria XD");
        this.setScene(scene);
        this.sizeToScene();
        this.show();
    }

    private void CrearUI() {
        CrearMenu();
        ordenDAO = new OrdenDAO();
        tbvOrden = new TableView<OrdenDAO>();
        CrearTable();
        btnGuardar = new Button("Guardar Orden");
        btnGuardar.setLineSpacing(15);
        btnGuardar.getStylesheets().add(getClass().getResource("/estilos/Orden.css").toString());
        btnGuardar.setPrefSize(100, 50);
        btnGuardar.setOnAction(event -> {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setHeaderText(null);
            alert.setTitle("Confirmación");
            alert.setContentText("¿Estas seguro de quieres guardar la orden?");
            Optional<ButtonType> action = alert.showAndWait();
            if (action.get() == ButtonType.OK) {
                try {
                    String query = "insert into orden(id_orden,fecha) (select max(id_orden+1),current_timestamp from orden);";
                    Statement stmt = Conexion.conexion.createStatement();
                    stmt.execute(query);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

        });
        btnNuevaCategoria = new Button("Nueva Categoria");
        btnNuevaCategoria.setLineSpacing(15);
        btnNuevaCategoria.getStylesheets().add(getClass().getResource("/estilos/Orden.css").toString());
        btnNuevaCategoria.setPrefSize(100, 50);
        btnNuevaCategoria.setOnAction(event -> new Restaurante());
        btnNuevoPlatillo = new Button("Nuevo Platillo");
        btnNuevoPlatillo.setLineSpacing(15);
        btnNuevoPlatillo.getStylesheets().add(getClass().getResource("/estilos/Orden.css").toString());
        btnNuevoPlatillo.setPrefSize(100, 50);
       // btnNuevoPlatillo.setOnAction(event ->);
        vBoxtable = new VBox(tbvOrden);
        vBoxtable.getStylesheets().add(getClass().getResource("/estilos/calculadora.css").toString());


        //vBox.getStylesheets().add(getClass().getResource("/estilos/Orden.css").toString());
        vBox = new VBox(grdTablilla, btnGuardar, btnNuevaCategoria,btnNuevoPlatillo);
        hBoxprincipal = new HBox(vBox, vBoxtable);


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
        grdTablilla.getStylesheets().add(getClass().getResource("/estilos/botones.css").toString());
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
        arBoton[0][0].setOnAction(event -> new comidas(tbvOrden, ordenDAO));
        arBoton[0][1].setOnAction(event -> new bebidas(tbvOrden, ordenDAO));
        arBoton[1][0].setOnAction(event -> new postres(tbvOrden, ordenDAO));
        arBoton[1][1].setOnAction(event -> new frappes(tbvOrden, ordenDAO));
    }

    private void CrearTable() {
        TableColumn<OrdenDAO, Integer> tbcIdOrden = new TableColumn<OrdenDAO, Integer>("Orden");
        tbcIdOrden.setCellValueFactory(new PropertyValueFactory<>("id_orden"));

        TableColumn<OrdenDAO, Integer> tbcIdPlatillo = new TableColumn<OrdenDAO, Integer>("id_platillo");
        tbcIdPlatillo.setCellValueFactory(new PropertyValueFactory<>("id_platillo"));


        TableColumn<OrdenDAO, String> tbcNombrePlatillo = new TableColumn<OrdenDAO, String>("Nombre Platillo");
        tbcNombrePlatillo.setCellValueFactory(new PropertyValueFactory<>("nombre"));

        TableColumn<OrdenDAO, Integer> tbcCantidad = new TableColumn<OrdenDAO, Integer>("Cantidad");
        tbcCantidad.setCellValueFactory(new PropertyValueFactory<>("cantidad"));

        TableColumn<OrdenDAO, Double> tbcPrecio = new TableColumn<OrdenDAO, Double>("Precio Unitario");
        tbcPrecio.setCellValueFactory(new PropertyValueFactory<>("precio"));

        TableColumn<OrdenDAO, Double> tbcImporte = new TableColumn<OrdenDAO, Double>("Importe");
        tbcImporte.setCellValueFactory(new PropertyValueFactory<>("importe"));

        TableColumn<OrdenDAO, Date> tbcFecha = new TableColumn<OrdenDAO, Date>("Fecha");
        tbcFecha.setCellValueFactory(new PropertyValueFactory<>("fecha"));


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

        tbvOrden.getColumns().addAll(tbcIdOrden, tbcIdPlatillo, tbcNombrePlatillo, tbcCantidad, tbcPrecio, tbcImporte, tbcFecha, tbcEditar, tbcEliminar);
        // Crear ListarCategorias();
        tbvOrden.setItems(ordenDAO.LISTARCATEGORIAS());
    }

    public void agregar_platillo() {

    }
}