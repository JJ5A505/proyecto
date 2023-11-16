package vistas;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;

import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.stage.Stage;
import org.kordamp.bootstrapfx.BootstrapFX;
import org.kordamp.bootstrapfx.scene.layout.Panel;

public class Impresora extends Stage {
    private VBox vBox;
    private Scene scene;
    public Impresora(){
        CrearUI();
        scene = new Scene(vBox,200,200);
        Panel panel = new Panel("Menu ");
        panel.getStyleClass().add("panel-primary");                            //(2)
        BorderPane content = new BorderPane();
        content.setPadding(new Insets(20));
        panel.setBody(content);
        Scene scene = new Scene(panel);
        scene.getStylesheets().add(BootstrapFX.bootstrapFXStylesheet());       //(3)
        this.setTitle("Cafeteria XD");
        this.setScene(scene);
        this.sizeToScene();
        this.show();

    }
    public void CrearUI(){
        vBox=new VBox();

    }
}

