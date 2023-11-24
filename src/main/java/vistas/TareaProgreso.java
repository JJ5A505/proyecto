package vistas;

import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.scene.control.ProgressBar;

import java.io.File;

public class TareaProgreso extends Task<Void> {
    private ProgressBar progressBar;

    public TareaProgreso(ProgressBar progressBar) {
        this.progressBar = progressBar;
    }

    @Override
    protected Void call() throws Exception {
        double avance = 0;

        while (avance <= 1) {
            Thread.sleep((long) (Math.random() * 1500));

            avance += Math.random() / 10;

            // Actualiza el progreso en la interfaz de usuario
            updateProgress(avance, 1);

            // Simula la inserción de datos
            Platform.runLater(() -> {
                // Aquí puedes realizar la inserción de datos en tu aplicación
            });
        }

        // Alcanzó el 100% de progreso, elimina la información del archivo
        Platform.runLater(() -> {
            eliminarInformacionArchivo();
        });

        return null;
    }

    private void eliminarInformacionArchivo() {
        try {
            // Aquí puedes realizar la lógica para eliminar la información del archivo
            File archivo = new File("ruta_del_archivo.txt");

            if (archivo.exists()) {
                archivo.delete();
                System.out.println("Información del archivo eliminada.");
            } else {
                System.out.println("El archivo no existe.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
