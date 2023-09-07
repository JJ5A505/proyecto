package vistas;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
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

public class Loteria extends Stage {
    private Scene escena;
    private HBox hPrincipal,hBtnSeleccion;
    private VBox vTablilla, vMazo;

    private ImageView  imgCarta;
    private Button[][] arBtnTablilla= new Button[4][4];
    private Button btnAnterior,btnSiguiente,btnIniciar;
    private GridPane grdTablilla;
    public Loteria(){
        CrearUI();
        escena=new Scene(hPrincipal,800,600);
        this.setTitle("Loteria");
        this.setScene(escena);
        this.show();
    }

    private void CrearUI() {//creacion de la interfaz para el usuario :User Interfaz
        CrearTablilla();
CrearMazo();

        btnAnterior= new Button("<");
        btnAnterior.setPrefSize(200,100);//definicion de los tamanos del boton ant

        btnSiguiente= new Button(">");
        btnSiguiente.setPrefSize(200,100);//definicion de los tamanod del boton sig
        hBtnSeleccion= new HBox(btnAnterior,btnSiguiente);
        vTablilla = new VBox(grdTablilla,hBtnSeleccion);
        vTablilla.setSpacing(20);//crear el espacio entre su perimetro


        hPrincipal = new HBox(vTablilla,vMazo);//dentro de esta se instancian las vertical tablilla y mazo
        hPrincipal.setPadding(new Insets(20));

    }

    private void CrearMazo() {
        Image imgDorso=new Image(new File("src/main/java/imagenes/anverso.jpg").toURI().toString());

        imgCarta = new ImageView(imgDorso);
        btnIniciar = new Button("Iniciar");
        vMazo = new VBox(imgCarta,btnIniciar);

    }

    private void CrearTablilla() {
        String [] arImagenes = {"sandia.jpg","rana.jpg","pino.jpg","pescado.jpg","nopal.jpg"
                ,"mateceta.jpg","gorrito.jpg","garza.jpg","escalera.jpg","diablo.jpg","corazon.jpg"
                ,"camaron.jpg","barril.jpg","alacran.jpg","dama.jpg","catrin.jpg"};
        //aqui es donde van las imagenes sus rutas este es un arreglo de imagenes para poder cargalas mas facilmente
                // isctorres github
        grdTablilla = new GridPane();
        int pos=0;
        for(int i=0;i<4;i++){
            for(int j=0;j<4;j++){
                ImageView imv;

                try{
                    InputStream stream = new FileInputStream("src/main/java/imagenes/"+arImagenes[pos]);
                    Image imgCartaP = new Image(stream);
                    imv = new ImageView(imgCartaP);
                    pos++;
                }catch (FileNotFoundException e){
                    throw new RuntimeException(e);
                }
              //  Image imgCartaP = new Image(new File("src/main/java/imagenes/sandia.jpg").toURI().toString());
                //\src\main\java\imagenes\
                //ruta de la misma carpeta de imagenes de la imagen
                //.toURI()--> es para obtener la ruta padre de la imagen
                // y el .toString me sirve para convertirla y de esta manera poder obtner bien la ruta pero que no
                // sea desde c: y que esten cargadas en la carpeta del proyecto en este caso "imagenes"

                       // ImageView imv = new ImageView(imgCartaP);
                        imv.setFitHeight(100);
                        imv.setFitWidth(100);
                arBtnTablilla[i][j]= new Button();
                arBtnTablilla[i][j].setGraphic(imv);
                arBtnTablilla[i][j].setPrefSize(100,140);//se define el tamano de los botones de la loteria
                grdTablilla.add(arBtnTablilla[i][j],i,j);
            }
        }

    }
}
