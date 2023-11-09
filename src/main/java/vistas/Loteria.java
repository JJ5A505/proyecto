package vistas;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.TimerTask;

public class Loteria extends Stage {
    private Scene escena;
    private HBox hPrincipal, hBtnSeleccion;
    private VBox vTablilla, vMazo;

    private ImageView imgCarta;
    private Button[][] arBtnTablilla = new Button[4][4];
    private Button btnAnterior, btnSiguiente, btnIniciar;
    private GridPane grdTablilla;
    private int indice = 0; // Cambié el índice inicial a 0
    String[] arImagenes1 = {"sandia.jpg", "rana.jpg", "pino.jpg", "pescado.jpg", "nopal.jpg"
            , "mateceta.jpg", "gorrito.jpg", "garza.jpg", "escalera.jpg", "diablo.jpg", "corazon.jpg"
            , "camaron.jpg", "barril.jpg", "alacran.jpg", "dama.jpg", "catrin.jpg"};
    //aquí es donde van las imágenes sus rutas, este es un arreglo de imágenes para poder cargarlas más fácilmente
    // isctorres github
    String[] arImagenes2 = {"bandera.jpg", "borracho.jpg", "campana.jpg", "cantarito.jpg", "cazo.jpg"
            , "cotorro.jpg", "estrella.jpg", "gallo.jpg", "jaras.jpg", "luna.jpg", "mundo.jpg"
            , "musico.jpg", "negrito.jpg", "palma.jpg", "sirena.jpg", "venado.jpg"};

    String[] arImagenes3 = {"nopal.jpg", "negrito.jpg", "pino.jpg", "rana.jpg", "sandia.jpg"
            , "alacran.jpg", "bandera.jpg", "borracho.jpg", "camaron.jpg", "catrin.jpg"
            , "dama.jpg", "diablo.jpg", "cazo.jpg", "escalera.jpg", "gallo.jpg", "luna.jpg"};

    String[] arImagenes4 = {"camaron.jpg", "cantarito.jpg", "garza.jpg", "barril.jpg", "corazon.jpg"
            , "pino.jpg", "pescado.jpg", "catrin.jpg", "cazo.jpg", "jaras.jpg", "mateceta.jpg"
            , "musico.jpg", "mundo.jpg", "venado.jpg", "dama.jpg", "diablo.jpg", "alacran.jpg"};

    String[] arImagenes5 = {"borracho.jpg", "catrin.jpg", "dama.jpg", "diablo.jpg", "gallo.jpg"
            , "garza.jpg", "musico.jpg", "negrito.jpg", "pescado.jpg", "rana.jpg", "sirena.jpg", "venado.jpg"
            , "mundo.jpg", "cotorro.jpg", "camaron.jpg", "alacran.jpg", "luna.jpg"};

    String[] arImagenes0 = {"bandera.jpg", "barril.jpg", "campana.jpg", "cantarito.jpg", "cazo.jpg", "escalera.jpg"
            , "estrella.jpg", "gorrito.jpg", "jaras.jpg", "mateceta.jpg", "nopal.jpg", "palma.jpg"
            , "pino.jpg", "sandia.jpg", "corazon.jpg", "gallo.jpg", "diablo.jpg"};
    String[][] arImagenes = {arImagenes0, arImagenes1, arImagenes2, arImagenes3, arImagenes4, arImagenes5};
    private Label lblTablilla; // Agregar Label para mostrar el texto
    String[] arrcartas ={"alacran.jpg","bandera.jpg","barril.jpg","borracho.jpg"
            ,"camaron.jpg","campana.jpg","cantarito.jpg","catrin.jpg"
            ,"cazo.jpg","corazon.jpg","cotorro.jpg","dama.jpg"
            ,"diablo.jpg","escalera.jpg","estrella.jpg","gallo.jpg"
            ,"garza.jpg","gorrito.jpg","jaras.jpg","luna.jpg"
            ,"mateceta.jpg","mundo.jpg","musico.jpg","negrito.jpg",
            "nopal.jpg","palma.jpg","pescado.jpg","pino.jpg","rana.jpg","sandia.jpg"
            ,"sirena.jpg","venado.jpg"};
    private java.util.Timer timer;
    private String [] ordenCartas;
    private int posicionActual = 0;
    private int contadorAciertos = 0;
    private boolean bandera=false;


    public Loteria() {
        grdTablilla = new GridPane();
        CrearUI();
        escena = new Scene(hPrincipal, 800, 600);
        this.setTitle("Loteria");
        this.setScene(escena);
        this.show();
    }

    private void CrearUI() {
        CrearTablilla();
        CrearMazo();

        lblTablilla = new Label("Tablilla " + indice);
        lblTablilla.setStyle("-fx-font-size: 24px;");

        btnAnterior = new Button("<");
        btnAnterior.setOnAction(event -> cambiarArreglo(-1));
        btnAnterior.setPrefSize(200, 100);
        btnAnterior.getStylesheets().add(getClass().getResource("/estilos/calculadora.css").toString());

        btnSiguiente = new Button(">");
        btnSiguiente.setOnAction(event -> cambiarArreglo(1));
        btnSiguiente.setPrefSize(200, 100);
        btnSiguiente.getStylesheets().add(getClass().getResource("/estilos/calculadora.css").toString());

        hBtnSeleccion = new HBox(btnAnterior, btnSiguiente);
        vTablilla = new VBox(grdTablilla, hBtnSeleccion);
        vTablilla.setSpacing(20);

        hPrincipal = new HBox(vTablilla, vMazo);
        hPrincipal.setPadding(new Insets(20));
        hPrincipal.setSpacing(15);

    }

    private void cambiarArreglo(int incremento) {
        indice += incremento;

        if (indice < 0) {
            indice = arImagenes.length - 1;
        } else if (indice >= arImagenes.length) {
            indice = 0;
        }
        lblTablilla.setText("Tablilla " + (indice + 1));
        CrearTablilla();
    }

    private void CrearMazo() {
        Image imgDorso = new Image(new File("src/main/java/imagenes/anverso.jpg").toURI().toString());

        imgCarta = new ImageView(imgDorso);
        imgCarta.setFitWidth(300);
        imgCarta.setFitHeight(450);
        btnIniciar = new Button("Iniciar");
        btnIniciar.setPrefSize(300, 50);
        btnIniciar.getStylesheets().add(getClass().getResource("/estilos/calculadora.css").toString());
        btnIniciar.setOnAction(event -> {
            if (timer == null){
                iniciarJuego();
            }else {
                reiniciarJuego();
            }
        });
        vMazo = new VBox(imgCarta, btnIniciar);
        vMazo.setSpacing(70);
    }

    private boolean iniciarJuego() {
        bandera=true;
        for (int a = 0; a < 4; a++) {
            for (int b = 0; b < 4; b++) {
                arBtnTablilla[a][b].setDisable(false);
            }
        }
        if (contadorAciertos != 16) {
            contadorAciertos = 0;
            mezclarOrdenCartas();
            btnAnterior.setDisable(true);
            btnSiguiente.setDisable(true);
            btnIniciar.setDisable(true);

            timer = new java.util.Timer();
            timer.scheduleAtFixedRate(new TimerTask() {
                @Override
                public void run() {
                    if (posicionActual< ordenCartas.length-1) {
                        Platform.runLater(() -> mostrarCartaEnMazo(ordenCartas[posicionActual]));
                        posicionActual++;

                    } else {
                        timer.cancel();
                        btnAnterior.setDisable(false);
                        btnSiguiente.setDisable(false);
                        btnIniciar.setDisable(false);
                    }
                    if(contadorAciertos==16){
                        timer.cancel();
                        btnAnterior.setDisable(false);
                        btnSiguiente.setDisable(false);
                        btnIniciar.setDisable(false);
                    }
                }
            }, 0, 2000);
        }
        bandera=false;
        return false;
    }



    private void reiniciarJuego(){
        posicionActual=0;
        iniciarJuego();
    }

    private void mostrarCartaEnMazo(String nombreCarta) {
        try {
            InputStream stream = new FileInputStream("src/main/java/imagenes/" + nombreCarta);
            Image imgCartaP = new Image(stream);
            imgCarta.setImage(imgCartaP);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private void mezclarOrdenCartas() {
        Integer[] orden = new Integer[arrcartas.length];
        for (int i = 0; i < orden.length; i++) {
            orden[i] = i;
        }
        List<Integer> listaOrden = Arrays.asList(orden);
        Collections.shuffle(listaOrden);
        ordenCartas = new String[listaOrden.size()];
        for (int i = 0; i < listaOrden.size(); i++) {
            ordenCartas[i] = arrcartas[listaOrden.get(i)];
        }
    }
    private void CrearTablilla() {
        grdTablilla.getChildren().clear();

        int pos = 0;

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                final int fila = i;
                final int columna = j;
                ImageView imv;

                try {
                    InputStream stream = new FileInputStream("src/main/java/imagenes/" + arImagenes[indice][pos]);
                    Image imgCartaP = new Image(stream);
                    imv = new ImageView(imgCartaP);
                    pos++;
                } catch (FileNotFoundException e) {
                    throw new RuntimeException(e);
                }

                if (imv.getImage() != null) { // Comprobación de que la imagen se cargó correctamente
                    imv.setFitHeight(100);
                    imv.setFitWidth(100);
                    arBtnTablilla[i][j] = new Button();
                    arBtnTablilla[i][j].setGraphic(imv);
                    arBtnTablilla[i][j].setPrefSize(100, 140);
                    grdTablilla.add(arBtnTablilla[i][j], i, j);
                    arBtnTablilla[i][j].getStylesheets().add(getClass().getResource("/estilos/botones.css").toString());
                    arBtnTablilla[i][j].setDisable(true);
                    if(bandera=true){
                        arBtnTablilla[i][j].setOnAction(event -> {
                            if (posicionActual < ordenCartas.length - 1) {
                                String cartaEnMazo = ordenCartas[posicionActual];
                                String cartaEnTablilla = arImagenes[indice][fila * 4 + columna];
                                if (cartaEnMazo.equals(cartaEnTablilla)) {
                                    contadorAciertos++;
                                    arBtnTablilla[fila][columna].setDisable(true);
                                    if (contadorAciertos == 16) {
                                        mensaje();
                                        System.out.println("has ganado");


                                    }
                                    System.out.println(contadorAciertos);

                                }
                            }
                        });
                    }
                }
            }
        }

        Label lblTablilla = new Label("Tablilla " + (indice + 1));
        lblTablilla.setStyle("-fx-font-size: 25px; -fx-text-fill: rgb(0, 100, 100);");
        grdTablilla.add(lblTablilla, 2, 4);
    }
    public void mensaje(){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Loteria!!");
        alert.setHeaderText(null);
        alert.setContentText("Has ganado");
        alert.showAndWait();
    }
}