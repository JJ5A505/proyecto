package com.example.demo1.Components;

import javafx.scene.control.ProgressBar;

public class Hilo extends Thread {
private ProgressBar pgbCorredor;

    public Hilo(String nombre,ProgressBar pgb) {
        super(nombre);
        this.pgbCorredor =pgb;

    }

    @Override
    public void run() {
        super.run();
        try {
double avance =0;

           while(avance<=1) {
                //System.out.println("Corredor" + this.getName() + " Llego al km " + i);
                sleep((long) (Math.random() * 1500));
                avance += Math.random()/10;
                pgbCorredor.setProgress(avance);
            }
           // System.out.println("Corredor "+this.getName()+" llego a la meta");
        }catch (Exception e){

        }
    }
}