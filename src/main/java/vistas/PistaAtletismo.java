package vistas;

import com.example.demo1.Components.Hilo;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class PistaAtletismo extends Stage {
    private ProgressBar[] pgbCorredores= new ProgressBar[6];
    private Hilo[] thrCorredores=new Hilo[6];
    private VBox vBox;
    private Scene escena;
    private Button btnIniciar;
    private String[] strCorredores ={"Martina","Rodrigo","Yunno","German","Vanessa","Julian"};

    public PistaAtletismo(){
        CrearUI();
        escena= new Scene(vBox);
        this.setTitle("Pista de Atletismo");
        this.setScene(escena);
        this.show();
    }
    private void CrearUI(){
         vBox=new VBox();
         for (int i = 0; i < pgbCorredores.length; i++) {
            pgbCorredores[i]= new ProgressBar(0);
            thrCorredores[i] =new Hilo(strCorredores[i],pgbCorredores[i]);
            vBox.getChildren().add(pgbCorredores[i]);

        }
        btnIniciar = new Button("Iniciar Carrera");
         btnIniciar.setOnAction(event -> {
             for (int i = 0; i < pgbCorredores.length; i++) {
                 thrCorredores[i].start();
             }

         });
        vBox.getChildren().add(btnIniciar);
    }
}
