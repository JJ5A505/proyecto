package vistas;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.kordamp.bootstrapfx.BootstrapFX;
import org.kordamp.bootstrapfx.scene.layout.Panel;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class Orden extends Stage {
    private Button[][] arBoton = new Button[2][2];

    private GridPane grdTablilla;
    private ImageView imv;

    public Orden() {
        CrearUI();
    }

    private void CrearUI() {
        CrearMenu();

        Panel panel = new Panel("Menu");
        panel.getStyleClass().add("panel-primary");

        BorderPane content = new BorderPane();
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
}