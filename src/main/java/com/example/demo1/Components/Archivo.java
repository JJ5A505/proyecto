package com.example.demo1.Components;

import java.time.LocalDateTime;
import java.util.Date;

public class Archivo {
    int id;
    String nombre;
    int hojas;
    LocalDateTime acceso;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getHojas() {
        return hojas;
    }

    public void setHojas(int hojas) {
        this.hojas = hojas;
    }

    public LocalDateTime getAcceso() {
        return acceso;
    }

    public void setAcceso(LocalDateTime acceso) {
        this.acceso = acceso;
    }

    public Archivo() {

    }
}