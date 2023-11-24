package vistas;
import com.example.demo1.Components.Archivo;
import com.example.demo1.Components.Hilo2;
import javafx.application.Platform;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.converter.LocalDateTimeStringConverter;
import org.kordamp.bootstrapfx.BootstrapFX;
import org.kordamp.bootstrapfx.scene.layout.Panel;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicReference;


public class Impresora extends Stage {
    private VBox vBox;
    private Button btnImprimir, btnApagar, btnEncender;
    private TableView<Map<String, Object>> tbvArchivo;
    private ObservableList<Map<String, Object>> archivoList;
    private SimpleObjectProperty<ObservableList<Map<String, Object>>> archivosProperty;
    private ProgressBar progressBar;
    private Label lblProcesoActual;
    private ExecutorService executorService;
    private BlockingQueue<Map<String, Object>> colaImpresiones;
    private AtomicReference<String> procesoActual;  // Variable atómica para almacenar el nombre del proceso actual
    private volatile boolean continuarEjecucion = true;  // Variable de bandera para indicar si el hilo debe continuar ejecutándose

    public Impresora() {
        vBox = new VBox();
        archivoList = FXCollections.observableArrayList();
        archivosProperty = new SimpleObjectProperty<>(archivoList);
        tbvArchivo = new TableView<>();
        progressBar = new ProgressBar();
        progressBar.setPrefSize(180, 50);
        lblProcesoActual = new Label("Imprimiendo: Ninguno");
        procesoActual = new AtomicReference<>("Ninguno");  // Inicializa la variable atómica
        CrearUI();



        Scene scene = new Scene(vBox);
        Panel panel = new Panel("Impresora");
        panel.getStyleClass().add("panel-primary");
        BorderPane conten =new BorderPane();
        conten.setPadding(new Insets(20));
        conten.setCenter(vBox);
        panel.setBody(conten);

        scene=new Scene(panel);
        scene.getStylesheets().add(BootstrapFX.bootstrapFXStylesheet());
        this.setTitle("Impresora");
        this.setScene(scene);
        this.sizeToScene();
        this.show();

        // Inicializa el ExecutorService y la cola de impresiones
        executorService = Executors.newSingleThreadExecutor();
        colaImpresiones = new LinkedBlockingQueue<>();

        // Inicia el hilo para procesar las impresiones desde la cola
        ejecutarProcesoImpresion();
    }

    public void CrearUI() {
        tbvArchivo.itemsProperty().bind(archivosProperty);

        CrearTable();
        btnImprimir = new Button("Imprimir nuevo archivo");
        btnImprimir.getStylesheets().add(getClass().getResource("/estilos/calculadora.css").toString());
        btnImprimir.setOnAction(event -> ProcesoImpresion());
        btnApagar = new Button("Apagar");
        btnApagar.getStylesheets().add(getClass().getResource("/estilos/calculadora.css").toString());
        btnApagar.setOnAction(event -> apagarHilo());
        btnEncender = new Button("Encender");
        btnEncender.getStylesheets().add(getClass().getResource("/estilos/calculadora.css").toString());
        btnEncender.setOnAction(event -> encenderHilo());
        vBox = new VBox(tbvArchivo, btnImprimir, btnApagar, btnEncender, progressBar, lblProcesoActual);
    }

    private void CrearTable() {
        TableColumn<Map<String, Object>, Integer> idCol = new TableColumn<>("id");
        TableColumn<Map<String, Object>, String> nombreCol = new TableColumn<>("nombre");
        TableColumn<Map<String, Object>, Integer> hojasCol = new TableColumn<>("hojas");
        TableColumn<Map<String, Object>, String> fechaHoraCol = new TableColumn<>("Fecha y Hora");
        idCol.setCellValueFactory(cellData -> new SimpleObjectProperty<>(Integer.parseInt(cellData.getValue().get("id").toString())));
        nombreCol.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().get("nombre").toString()));
        hojasCol.setCellValueFactory(cellData -> new SimpleObjectProperty<>(Integer.parseInt(cellData.getValue().get("hojas").toString())));
        fechaHoraCol.setCellValueFactory(cellData -> new SimpleObjectProperty<>(LocalDateTime.now().toString()));
        tbvArchivo.getColumns().addAll(idCol, nombreCol, hojasCol,fechaHoraCol);
        vBox.getChildren().add(tbvArchivo);
    }
    private void ProcesoImpresion() {
        Random random = new Random();
        int numeroDeHojas = random.nextInt(20) + 1;

        Map<String, Object> nuevoArchivo = new HashMap<>();
        nuevoArchivo.put("id", archivoList.size() + 1);
        nuevoArchivo.put("nombre", "Nuevo Archivo " + archivoList.size());
        nuevoArchivo.put("hojas", numeroDeHojas);
        nuevoArchivo.put("fecha y hora", LocalDateTime.now());

        archivoList.add(nuevoArchivo);
        tbvArchivo.refresh();

        // Añade el nuevo archivo a la cola de impresiones
        colaImpresiones.offer(nuevoArchivo);
    }

    private void ejecutarProcesoImpresion() {
        executorService.submit(() -> {
            try {
                while (continuarEjecucion) {
                    // Bloquea hasta que haya un nuevo archivo en la cola
                    Map<String, Object> archivo = colaImpresiones.take();

                    // Actualiza la variable atómica con el nombre del proceso actual
                    procesoActual.set(archivo.get("nombre").toString());

                    // Actualiza la etiqueta desde el hilo principal
                    Platform.runLater(() -> lblProcesoActual.setText("Imprimiendo: " + procesoActual.get()));

                    int numeroDeHojas = (int) archivo.get("hojas");
                    Hilo2 hilo2 = new Hilo2("Hilo2", progressBar, numeroDeHojas);
                    hilo2.start();

                    // Espera a que el hilo de impresión termine antes de procesar el siguiente archivo
                    hilo2.join();

                    // Elimina el archivo de la lista después de completar la impresión
                    Platform.runLater(() -> archivoList.remove(archivo));
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });
    }


    private void apagarHilo() {
        continuarEjecucion = false;
    }

    private void encenderHilo() {
        continuarEjecucion = true;
        ejecutarProcesoImpresion();
    }
}