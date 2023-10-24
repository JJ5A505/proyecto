package com.example.demo1.models;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Optional;

public class OrdenDAO {
    public int id_orden;
    public int id_categoria;

    public int getId_platillo() {
        return id_platillo;
    }

    public void setId_platillo(int id_platillo) {
        this.id_platillo = id_platillo;
    }

    public int id_platillo;

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

    public double getImporte() {
        return importe;
    }

    public void setImporte(double importe) {
        this.importe = importe;
    }

    public double importe;

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String fecha;

    public ObservableList<OrdenDAO> LISTARCATEGORIAS() {
        ObservableList<OrdenDAO> listOrdenes = FXCollections.observableArrayList();
        OrdenDAO objO;

        try {
            String query = "select d.id_orden,d.id_platillo,p.nombre,d.cantidad,p.precio ,(d.cantidad*p.precio)as importe,o.fecha\n" +
                    "from detalle d join orden o on d.id_orden = o.id_orden\n" +
                    "join platillos p on d.id_platillo = p.id_platillo";

            Statement stmt = Conexion.conexion.createStatement();
            ResultSet res = stmt.executeQuery(query);

            while (res.next()) {
                objO = new OrdenDAO();
                objO.id_orden = res.getInt("id_orden");
                objO.id_platillo = res.getInt("id_platillo");
                objO.nombre = res.getString("nombre");
                objO.cantidad = res.getInt("cantidad");
                objO.precio = res.getInt("precio");
                objO.importe = res.getInt("importe");
                objO.fecha = res.getString("fecha");


                listOrdenes.add(objO);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return listOrdenes;

    }

    public void ELIMINAR() {
        try {
            String query = "delete from detalle where id_orden=" + this.id_orden + " and id_platillo=" + this.id_platillo + " and cantidad=" + this.cantidad;
            Statement stmt = Conexion.conexion.createStatement();
            stmt.executeUpdate(query);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void ACTUALIZAR() {
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
            // Haz algo con el valor
            this.cantidad = cantidad;
            if(cantidad>0){
                try {
                    String query = "UPDATE detalle SET cantidad = '"+this.cantidad+"'"+
                            "WHERE id_orden = "+this.id_orden+" and id_platillo="+this.id_platillo;
                    Statement stmt = Conexion.conexion.createStatement();
                    stmt.executeUpdate(query);
                }catch (Exception e){
                    e.printStackTrace();

                }

            }else {
                if(cantidad<0){
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Valor invalido");
                    alert.setHeaderText(null);
                    alert.setContentText("No se puede ponerse esa cantidad");
                    alert.showAndWait();
                }
            }
        }
    }
}