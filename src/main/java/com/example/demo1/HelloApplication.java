package com.example.demo1;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import vistas.Calculadora;
import vistas.Loteria;

import java.io.IOException;

public class HelloApplication extends Application {

    private Scene escena;
    private BorderPane borderPane;
    private MenuBar menuBar;
    private Menu menuParcial1, menuParcial2;
    private MenuItem mitCalculadora,mitLoteria;

    private void CrearUI(){
        mitCalculadora = new MenuItem("Calculadora");
        mitCalculadora.setOnAction((event)->new Calculadora());

        mitLoteria= new MenuItem("Loteria");
        mitLoteria.setOnAction((event)->new Loteria());


        menuParcial1 = new Menu("Parcial 1");
        menuParcial1.getItems().addAll(mitCalculadora,mitLoteria);//aqui se estan instanciando todas las opciones en parcial 1



        menuParcial2 = new Menu("Parcial 2");
        menuBar = new MenuBar(menuParcial1,menuParcial2);
    }
    public void start(Stage stage) throws IOException {
        CrearUI();
        borderPane = new BorderPane();
        borderPane.setTop(menuBar);
        escena = new Scene(borderPane, 200, 300);
        stage.setScene(escena);
        stage.setMaximized(true);
        stage.show();
    }


    public static void main(String[] args) {
        launch();
    }
}