package com.example.demo1;
import com.example.demo1.Components.Hilo;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import sistemasOperativos.Memoria;
import vistas.*;
import com.example.demo1.models.Conexion;

import java.io.IOException;
import java.util.Optional;

public class HelloApplication extends Application {

    private Scene escena;
    private BorderPane borderPane;
    private MenuBar menuBar;
    private Menu menuParcial1, menuParcial2,menuParcial3,menuSistemasOperativos,menuSalir;
    private MenuItem mitCalculadora,mitLoteria,mitSalir,mitRestaurante,mitOrden,mitPista,mitMemoria,mitImpresora;

    private void CrearUI(){
        mitCalculadora = new MenuItem("Calculadora");
        mitCalculadora.setOnAction((event)->new Calculadora());

        mitLoteria= new MenuItem("Loteria");
        mitLoteria.setOnAction((event)->new Loteria());


        menuParcial1 = new Menu("Parcial 1");
        menuParcial1.getItems().addAll(mitCalculadora,mitLoteria);//aqui se estan instanciando todas las opciones en parcial 1


        mitRestaurante=new MenuItem("Restaurante");
        mitRestaurante.setOnAction((event)-> new Restaurante());
        mitOrden= new MenuItem("Orden");
        mitOrden.setOnAction(event -> new Orden());
        mitPista=new MenuItem("Pista");
        mitPista.setOnAction(event -> new PistaAtletismo());
        mitImpresora=new MenuItem("Impresora");
        mitImpresora.setOnAction(event -> new Impresora());
        menuParcial2 = new Menu("Parcial 2");
        menuParcial2.getItems().addAll(mitRestaurante,mitOrden,mitPista,mitImpresora);


        menuParcial3 = new Menu("Parcial 3");
        menuParcial3.getItems().addAll();

        mitMemoria=new MenuItem("Memoria");
        mitMemoria.setOnAction(event -> new Memoria());
        menuSistemasOperativos=new Menu("Sistemas Operativos");
        menuSistemasOperativos.getItems().addAll(mitMemoria);

        menuSalir = new Menu("Mas opciones");
        mitSalir = new MenuItem("Salir");
        mitSalir.setOnAction((event)->Salir());
        menuSalir.getItems().add(mitSalir);
        menuBar = new MenuBar(menuParcial1,menuParcial2,menuParcial3,menuSistemasOperativos,menuSalir);
    }

    private void Salir() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Mensaje del sistema");
        alert.setHeaderText("Confirmar cerrar sitema?");
        Optional<ButtonType> option =alert.showAndWait();
        if(option.get() == ButtonType.OK){
            System.exit(0);
        }
    }

    public void start(Stage stage) throws IOException {


        connectToDatabase();
        CrearUI();
        borderPane = new BorderPane();
        borderPane.setTop(menuBar);
        escena = new Scene(borderPane, 200, 300);
        escena.getStylesheets().add(getClass().getResource("/estilos/estilos.css").toString());
        stage.setScene(escena);
        stage.setMaximized(true);
        stage.show();
    }
public void connectToDatabase(){
    Conexion.createConexion();
    System.out.println("Conexion establecida");
}

    public static void main(String[] args) {
        launch();
    }
}