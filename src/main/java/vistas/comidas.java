package vistas;

import com.example.demo1.models.Conexion;
import com.example.demo1.models.OrdenDAO;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.kordamp.bootstrapfx.BootstrapFX;
import org.kordamp.bootstrapfx.scene.layout.Panel;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.sql.Statement;
import java.util.Optional;

public class comidas extends Stage  {
    private Button[][] arBoton = new Button[2][2];
    private GridPane grdTablilla;
    private int cantidad;
    private TableView<OrdenDAO> tbvOrdenes;
    private OrdenDAO ordenDAO;
    private int contador;
    private Button agregar,quitar;
    private VBox vBox;
    private Cantidad cnt;

    public comidas(TableView<OrdenDAO>tbvOrdenes, OrdenDAO ordenDAO) {
this.tbvOrdenes=tbvOrdenes;
this.ordenDAO=ordenDAO;
        CrearUI();
    }

    private void CrearUI() {
        tblcomidas();
        Panel panel = new Panel("Comidas");
        panel.getStyleClass().add("panel-primary");
        BorderPane content = new BorderPane(vBox);
        content.setPadding(new Insets(20));
        content.setCenter(grdTablilla);
        panel.setBody(content);

        Scene scene = new Scene(panel);
        scene.getStylesheets().add(BootstrapFX.bootstrapFXStylesheet());
        this.setTitle("Cafeteria XD");
        this.setScene(scene);
        this.sizeToScene();
        this.setMinWidth(320);
        this.setMinHeight(480);
        this.show();
    }

    private void tblcomidas() {
        String[] arImagenes = {"hamburgesa.jpg", "alitas.jpg", "pizza.jpg", "ensalada.jpg"};
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
        arBoton[0][0].setOnAction(event -> {
            preguntar();
            contador=0;
            while (cantidad>0 && contador==0) {
                try {
                    String query = "insert into detalle(id_orden, id_platillo, cantidad)(select max(id_orden),1," + cantidad + " from orden);";
                    Statement stmt = Conexion.conexion.createStatement();
                    stmt.execute(query);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                actualizarTabla();
                contador++;
            }
        });
        arBoton[1][0].setOnAction(event -> {
            preguntar();
            contador=0;
            while (cantidad>0 && contador==0) {

                try {
                    String query = "insert into detalle(id_orden, id_platillo, cantidad)(select max(id_orden),2," + cantidad + " from orden);";
                    Statement stmt = Conexion.conexion.createStatement();
                    stmt.execute(query);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                actualizarTabla();
                contador++;
            }
        });
        arBoton[0][1].setOnAction(event -> {
            preguntar();
            contador=0;
            while (cantidad>0 &&contador==0) {
                try {
                    String query = "insert into detalle(id_orden, id_platillo, cantidad)(select max(id_orden),3," + cantidad + " from orden);";
                    Statement stmt = Conexion.conexion.createStatement();
                    stmt.execute(query);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                actualizarTabla();
                contador++;
            }
        });
        arBoton[1][1].setOnAction(event -> {
            preguntar();
            contador=0;
            while (cantidad>0 && contador ==0) {
                try {
                    String query = "insert into detalle(id_orden, id_platillo, cantidad)(select max(id_orden),4," + cantidad + " from orden);";
                    Statement stmt = Conexion.conexion.createStatement();
                    stmt.execute(query);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                actualizarTabla();
                contador++;
            }
        });

    }

    private void preguntar() {
   cnt=new Cantidad();
    }

    private void actualizarTabla() {
        tbvOrdenes.setItems(ordenDAO.LISTARCATEGORIAS());
        tbvOrdenes.refresh();
    }

}

