package sistemasOperativos;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
public class Memoria extends Stage {
    private HBox hbox;
    private VBox vBox;
    private Button btnproceso, btnquitarP;

    public Memoria() {

        crearUI();
    }

    public void crearUI() {
// Crear un gráfico de barras
    /*    BarChart<String, Number> paginacion = new BarChart<>(new CategoryAxis(), new NumberAxis());
        paginacion.setTitle("Paginación");

        // Crear un arreglo para almacenar las páginas
        int[] paginas = new int[101];

        // Crear un botón para agregar un proceso
        btnproceso = new Button("Agregar proceso");
        btnproceso.setOnAction(event -> {
            // Crear un nuevo proceso
            int tamanoProceso = (int) Math.random() * 100;

            // Buscar un espacio disponible
            int posicionDisponible = -1;
            for (int i = 0; i < paginas.length; i++) {
                if (paginas[i] == 0) {
                    posicionDisponible = i;
                    break;
                }
            }

            // Si se encuentra un espacio disponible, marcarlo como ocupado
            if (posicionDisponible != -1) {
                paginas[posicionDisponible] = tamanoProceso;

                // Agregar una barra roja al gráfico
                BarChart.Data<String, Number> barra = new BarChart.Data<>("Página " + (posicionDisponible + 1), tamanoProceso);
                paginacion.getData().add(barra);
            }
        });

        // Crear un botón para quitar un proceso
        btnquitarP = new Button("Quitar proceso");
        btnquitarP.setOnAction(event -> {
            // Obtener la barra seleccionada
            BarChart.Data<String, Number> barraSeleccionada = paginacion.getData().get(paginacion.getSelectionModel().getSelectedIndex());

            // Obtener la posición de la barra seleccionada
            int posicionBarraSeleccionada = paginacion.getData().indexOf(barraSeleccionada);

            // Marcar el espacio como disponible
            paginas[posicionBarraSeleccionada] = 0;

            // Eliminar la barra del gráfico
            paginacion.getData().remove(posicionBarraSeleccionada);
        });

        // Agregar los botones al VBox
        vBox.getChildren().add(btnproceso);
        vBox.getChildren().add(btnquitarP);

        // Agregar el BarChart al HBox
        hbox.getChildren().add(paginacion);

        // Establecer el layout de la ventana
        this.setScene(new Scene(hbox));
        this.setTitle("Memoria");
        this.show();
    }

*/
    }
}