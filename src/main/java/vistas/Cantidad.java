package vistas;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.kordamp.bootstrapfx.BootstrapFX;
import org.kordamp.bootstrapfx.scene.layout.Panel;

public class Cantidad extends Stage {
    private VBox vBox;
    private Button btnagregar,btnquitar;
    public int cantidad;
    private Scene scene;
    private Label label;
    public Cantidad(){

        CrearUI();

        scene=new Scene(vBox,400,150);
        Panel panel = new Panel("Menu ");
        panel.getStyleClass().add("panel-primary");                            //(2)
        BorderPane content = new BorderPane();
        content.setPadding(new Insets(20));
        content.setCenter(vBox);
        panel.setBody(content);
        Scene scene = new Scene(panel);
        scene.getStylesheets().add(BootstrapFX.bootstrapFXStylesheet());       //(3)
        this.setTitle("Cafeteria XD");
        this.setScene(scene);
        this.sizeToScene();
        this.show();
    }
    private void CrearUI(){
        cantidad=1;
        label = new Label("Cantidad: " + cantidad);

        btnagregar=new Button("+");
        btnagregar.setLineSpacing(30);
        btnagregar.setPrefSize(100,50);
        btnagregar.getStylesheets().add(getClass().getResource("/estilos/Orden.css").toString());
        btnagregar.setOnAction(event -> {
            cantidad++;
            actualizarCantidadLabel();
        });

        btnquitar=new Button("-");
        btnquitar.setLineSpacing(30);
        btnquitar.setPrefSize(100,50);
        btnquitar.getStylesheets().add(getClass().getResource("/estilos/Orden.css").toString());
        btnquitar.setOnAction(event -> {
            if(cantidad>1) {
                cantidad--;
                actualizarCantidadLabel();
            }
        });
        vBox=new VBox(btnagregar,btnquitar,label);
        vBox.setPrefSize(150,75);
        actualizarCantidadLabel();
    }

    private void actualizarCantidadLabel() {
        label.setText("Cantidad: " + cantidad);
    }
}
