package vistas;

import com.example.demo1.Components.ButtonCell;
import com.example.demo1.models.CategoriasDAO;
import com.example.demo1.models.Conexion;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.util.Callback;
import org.kordamp.bootstrapfx.BootstrapFX;
import org.kordamp.bootstrapfx.scene.layout.Panel;

import java.sql.Statement;

public class Platillo extends Stage {
    private HBox hBoxprincipal;
    private Scene scene;
    private Restaurante restaurante;
    private Button mostrarcat, btnaceptar;
    private TextField txtfid, textfnom ,txtfpre;

    public Platillo() {
        CrearUI();
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

    public void CrearUI() {
        mostrarcat = new Button("Mostrar Categorias");
        mostrarcat.setLineSpacing(15);
        mostrarcat.setPrefSize(100, 50);
        mostrarcat.getStylesheets().add(getClass().getResource("/estilos/Orden.css").toString());
        mostrarcat.setOnAction(event -> new Restaurante());
        txtfid = new TextField();
        txtfid.setPromptText("ID de la categoria");
        textfnom = new TextField();
        textfnom.setPromptText("Nombre Platillo");
        txtfpre=new TextField();
        txtfpre.setPromptText("Precio del platillo");
        btnaceptar = new Button("Aceptar");
        btnaceptar.setLineSpacing(15);
        btnaceptar.setPrefSize(100, 50);
        btnaceptar.getStylesheets().add(getClass().getResource("/estilos/Orden.css").toString());
        btnaceptar.setOnAction(event -> insert());


        hBoxprincipal = new HBox(mostrarcat, btnaceptar, txtfid, textfnom,txtfpre);

    }

    private void insert() {
        String id = txtfid.getText();
        String nombre = textfnom.getText();
        String precio= txtfpre.getText();
        try {
            String query = "insert into platillos(id_platillo,nombre,precio,id_categoria)(select max(id_platillo)+1,"+"'"+nombre+"'"+"," + precio + "," + id + " from platillos);";
            Statement stmt = Conexion.conexion.createStatement();
            stmt.execute(query);
        } catch (Exception e) {
            e.printStackTrace();
        }
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Platillos");
        alert.setHeaderText(null);
        alert.setContentText("Si pusiste los datos correctas se hizo correctamente\n" +
                "sino caso contrario\n" +
                "puedes cambiar los datos para insertar otro platillo");

        alert.showAndWait();

    }
}

