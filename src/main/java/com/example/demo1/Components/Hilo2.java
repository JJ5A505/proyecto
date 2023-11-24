package com.example.demo1.Components;

import javafx.application.Platform;
import javafx.scene.control.ProgressBar;
public class Hilo2 extends Thread {
    private ProgressBar imprecion;
    private int numeroDeHojas;
    private boolean ejecucionPausada = false;  // Variable para controlar la pausa

    public Hilo2(String nombre, ProgressBar pgb, int numeroDeHojas) {
        super(nombre);
        this.imprecion = pgb;
        this.numeroDeHojas = numeroDeHojas;
    }

    @Override
    public void run() {
        super.run();
        try {
            double avance = 0;
            while (avance <= 1) {
                sleep((long) (Math.random() * 1500));

                // Verifica si la ejecución está pausada
                while (ejecucionPausada) {
                    sleep(100);  // Espera corta antes de verificar de nuevo
                }

                // Ajusta la velocidad en función del número de hojas
                avance += Math.random() / (numeroDeHojas);

                // Actualiza el ProgressBar en el hilo de la interfaz de usuario
                double finalAvance = avance;
                Platform.runLater(() -> imprecion.setProgress(finalAvance));
            }
        } catch (Exception e) {
            e.printStackTrace(); // Manejar excepciones adecuadamente en tu aplicación
        }
    }
}
