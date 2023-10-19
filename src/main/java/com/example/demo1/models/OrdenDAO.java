package com.example.demo1.models;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.ResultSet;
import java.sql.Statement;

public class OrdenDAO {
    public int id_orden;
    public int id_categoria;

    public int getPrecio() {
        return precio;
    }

    public void setPrecio(int precio) {
        this.precio = precio;
    }

    public int precio;

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }


    public int cantidad;

    public int getId_orden() {
        return id_orden;
    }

    public void setId_orden(int id_orden) {
        this.id_orden = id_orden;
    }

    public int getId_categoria() {
        return id_categoria;
    }

    public void setId_categoria(int id_categoria) {
        this.id_categoria = id_categoria;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String nombre;
    public ObservableList<OrdenDAO> LISTARCATEGORIAS() {
        ObservableList<OrdenDAO> listOrdenes = FXCollections.observableArrayList();
        OrdenDAO objO;

        try {
            String query = "SELECT o.id_orden, p.nombre, o.cantidad, (o.cantidad * p.precio) AS precio " +
                    "FROM orden o " +
                    "JOIN platillos p ON o.id_platillo = p.id_platillo";

            Statement stmt = Conexion.conexion.createStatement();
            ResultSet res = stmt.executeQuery(query);

            while (res.next()) {
                objO = new OrdenDAO();
                objO.id_orden = res.getInt("id_orden");
                objO.nombre = res.getString("nombre");
                objO.cantidad = res.getInt("cantidad");
                objO.precio = res.getInt("precio");

                listOrdenes.add(objO);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return listOrdenes;

    }

}
