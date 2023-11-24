package sistemasOperativos;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import org.kordamp.bootstrapfx.BootstrapFX;
import org.kordamp.bootstrapfx.scene.layout.Panel;

import java.util.Optional;

public class Memoria extends Stage {
    private HBox hbox;
    private VBox vBox;
    private Scene scene;
    private Button btnproceso, btnquitarP;
    private int cantidad;
    private int tamanioM[]=new int[100];
    public int q[]=new int[100];
    public int ProcesosPendientes[]=new int[100];
    public int cola=0;
    public int noP=1;

    public Memoria() {
        crearUI();
    }

    public void crearUI() {

        btnproceso = new Button("Agregar Proceso");
        btnproceso.setPrefSize(150, 50);
        btnproceso.getStylesheets().add(getClass().getResource("/estilos/calculadora.css").toString());
        btnproceso.setOnAction(event -> crearP());

        btnquitarP = new Button("Eliminar Proceso");
        btnquitarP.setPrefSize(150, 50);
        btnquitarP.getStylesheets().add(getClass().getResource("/estilos/calculadora.css").toString());
        btnquitarP.setOnAction(event -> eliminarP());


        vBox = new VBox();
        vBox.setPrefSize(100, 500);
        vBox.getStylesheets().add(getClass().getResource("/estilos/siso.css").toString());


        hbox = new HBox(vBox, btnproceso, btnquitarP);



        scene = new Scene(hbox, 1000, 600);


        Panel panel = new Panel("Sistemas Operativos ");
        panel.getStyleClass().add("panel-primary");
        BorderPane content = new BorderPane();
        content.setPadding(new Insets(20));
        content.setCenter(hbox);
        panel.setBody(content);


        scene = new Scene(panel);
        scene.getStylesheets().add(BootstrapFX.bootstrapFXStylesheet());


        this.setTitle("Procesos");
        this.setScene(scene);
        this.sizeToScene();
        this.show();
    }

    private void eliminarP() {
        TextField txtCantidad = new TextField();
        txtCantidad.setPromptText("No.Proceso");
        txtCantidad.setText(String.valueOf(0));

        // Abre una ventana emergente
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("No.Proceso");
        dialog.setContentText("Ingrese el numero de proceso");
        dialog.getEditor().setText(txtCantidad.getText());

        Optional<String> result = dialog.showAndWait();

        if (result.isPresent()) {

            int cantidad = Integer.parseInt(result.get());

            this.cantidad = cantidad;
            for (int i = 0; i < tamanioM.length; i++) {
                if (tamanioM[i]==cantidad){
                    tamanioM[i]=0;
                }
            }
            queque();
            dibujo();
        }else{
            this.cantidad=0;
        }



    }

    private void crearP() {
        TextField txtCantidad = new TextField();
        txtCantidad.setPromptText("Cantidad");
        txtCantidad.setText(String.valueOf(1));

        // Abre una ventana emergente
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Cantidad");
        dialog.setContentText("Ingrese la cantidad");
        dialog.getEditor().setText(txtCantidad.getText());

        Optional<String> result = dialog.showAndWait();
        if (result.isPresent()) {
            // El usuario ingresó un valor
            int cantidad = Integer.parseInt(result.get());

            this.cantidad = cantidad;
            buscarEs(cantidad);
            dibujo();
        }else{
            this.cantidad=0;
        }

    }
    private void queque() {
        for (int i = 0; i < q.length; i++) {
            int tamanioProcesoCola = q[i];

            while (tamanioProcesoCola != 0) {
                boolean asignado = false;

                for (int j = 0; j <= tamanioM.length - tamanioProcesoCola; j++) {
                    if (tamanioM[j] == 0) {
                        boolean espacioDisponible = true;

                        for (int k = j; k < j + tamanioProcesoCola; k++) {
                            if (tamanioM[k] != 0) {
                                espacioDisponible = false;
                                break;
                            }
                        }

                        if (espacioDisponible) {
                            int cantidadPendiente = ProcesosPendientes[i];

                            for (int k = j; k < j + tamanioProcesoCola; k++) {
                                tamanioM[k] = cantidadPendiente;
                            }


                            q[i] = 0;
                            asignado = true;
                            break; // Salir del bucle interno
                        }
                    }
                }

                if (!asignado) {
                    break;
                }

                // Actualizar el tamaño del proceso en la cola
                tamanioProcesoCola = q[i];
            }
        }
    }





    private void buscarEs(int tamanioProceso) {
        if (tamanioProceso > tamanioM.length) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Error");
            alert.setHeaderText("Error");
            alert.setContentText("No se puede hacer un proceso de ese tamaño");

            alert.showAndWait();
        } else if (tamanioProceso > 0) {
            boolean espacioDisponible = false;

            for (int i = 0; i <= tamanioM.length - tamanioProceso; i++) {
                if (tamanioM[i] == 0) {
                    espacioDisponible = true;

                    for (int j = i; j < i + tamanioProceso; j++) {
                        if (tamanioM[j] != 0) {
                            espacioDisponible = false;
                            break;
                        }
                    }

                    if (espacioDisponible) {
                        for (int j = i; j < i + tamanioProceso; j++) {
                            tamanioM[j] = noP;
                        }
                        noP++;
                        dibujo();
                        return;
                    }
                }
            }

            // Si llegamos aquí, significa que no se encontró espacio en ninguna posición
            // Añadir el proceso a la cola de procesos pendientes
            if (espacioDisponible == false) {
                ProcesosPendientes[cola] = noP;
                q[cola] = tamanioProceso;
                noP++;
                cola++;
            }
        }
    }


    private void dibujo() {
        vBox.getChildren().clear(); // Limpiar la VBox antes de dibujar nuevamente

        int anchoRectangulo = 50;
        int altoRectangulo = 5;

        for (int i = 0; i < tamanioM.length; i++) {
            Rectangle rect = new Rectangle(anchoRectangulo, altoRectangulo);

            switch (tamanioM[i]) {
                case 0:
                    rect.setFill(Color.WHITE);
                    break;
                case 1:
                    rect.setFill(Color.RED);
                    break;
                case 2:
                    rect.setFill(Color.GREEN);
                    break;
                case 3:
                    rect.setFill(Color.BLUE);
                    break;
                case 4:
                    rect.setFill(Color.YELLOW);
                    break;
                case 5:
                    rect.setFill(Color.ORANGE);
                    break;
                case 6:
                    rect.setFill(Color.PURPLE);
                    break;
                case 7:
                    rect.setFill(Color.CYAN);
                    break;
                case 8:
                    rect.setFill(Color.MAGENTA);
                    break;
                case 9:
                    rect.setFill(Color.BROWN);
                    break;
                case 10:
                    rect.setFill(Color.PINK);
                    break;
                case 11:
                    rect.setFill(Color.GRAY);
                    break;
                case 12:
                    rect.setFill(Color.LIGHTBLUE);
                    break;
                case 13:
                    rect.setFill(Color.LIGHTGREEN);
                    break;
                case 14:
                    rect.setFill(Color.DARKRED);
                    break;
                case 15:
                    rect.setFill(Color.DARKBLUE);
                    break;
                // Agrega más casos
                default:
                    rect.setFill(Color.BLACK); // Color predeterminado para otros valores
                    break;
            }
            vBox.getChildren().add(rect);
        }
    }
}

