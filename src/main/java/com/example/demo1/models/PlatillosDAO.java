package com.example.demo1.models;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.ResultSet;
import java.sql.Statement;

public class PlatillosDAO {
    public int idPlatillo;
    public String nomPlatillo;
    public Double prePlatillo;

    public int getIdPlatillo() {
        return idPlatillo;
    }

    public void setIdPlatillo(int idPlatillo) {
        this.idPlatillo = idPlatillo;
    }

    public String getNomPlatillo() {
        return nomPlatillo;
    }

    public void setNomPlatillo(String nomPlatillo) {
        this.nomPlatillo = nomPlatillo;
    }

    public Double getPrePlatillo() {
        return prePlatillo;
    }

    public void setPrePlatillo(Double prePlatillo) {
        this.prePlatillo = prePlatillo;
    }

    public Integer getIdCat() {
        return idCat;
    }

    public void setIdCat(Integer idCat) {
        this.idCat = idCat;
    }

    public Integer idCat;

    public ObservableList<PlatillosDAO> LISTARPLATILLOS() {
        ObservableList<PlatillosDAO> listCat = FXCollections.observableArrayList();
        PlatillosDAO objC;
        try {
            String query = "SELECT * FROM platillos";
            Statement stmt = Conexion.conexion.createStatement();
            ResultSet res = stmt.executeQuery(query);
            while (res.next()) {
                objC = new PlatillosDAO();
                objC.idPlatillo = res.getInt("id_platillo");
                objC.nomPlatillo = res.getString("nombre");
                objC.prePlatillo= res.getDouble("precio");
                objC.idCat= res.getInt("id_categoria");
                listCat.add(objC);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listCat;
    }

    public void INSERTAR(){
        try{
            String query = "INSERT INTO platillos (id_platillo, nombre, precio, id_categoria)" +
                    "SELECT (MAX(id_platillo) + 1), '"+this.nomPlatillo+"',"+ this.prePlatillo+","+this.idCat+
                    "FROM platillos;";

            Statement stmt = Conexion.conexion.createStatement();
            stmt.execute(query);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public void ACTUALIZAR(){
        try {
            String query = "UPDATE platillos SET nombre = '"+this.nomPlatillo+"' and precio ="+this.prePlatillo+
                    "WHERE id_platillo = "+this.idPlatillo;
            Statement stmt = Conexion.conexion.createStatement();
            stmt.executeUpdate(query);
        }catch (Exception e){
            e.printStackTrace();

        }
    }
    public void ELIMINAR(){
        try {
            String query = "DELETE FROM platillos WHERE id_platillo = "+this.idPlatillo;
            Statement stmt = Conexion.conexion.createStatement();
            stmt.executeUpdate(query);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}