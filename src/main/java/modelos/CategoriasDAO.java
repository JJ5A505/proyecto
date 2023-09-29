package modelos;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import vistas.Conexion;

import java.sql.ResultSet;
import java.sql.Statement;

public class CategoriasDAO {
    public int idCategoria;
    public String nomCategoria;
    public void INSERTAR(){
        try{
            String query = "INSERTO INTO categorias"+"(nomCategoria) VALUES('"+this.nomCategoria+"')";
            Statement stmt = Conexion.conexion.createStatement();
            stmt.execute(query);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public void ACTUALIZAR(){
try {
    String query = "UPDATE categorias SET nomCategoria = '"+this.nomCategoria+"'"+
            "WHERE id_categoria = "+this.idCategoria;
    Statement stmt = Conexion.conexion.createStatement();
    stmt.executeUpdate(query);
}catch (Exception e){
    e.printStackTrace();

}
    }
    public void ELIMINAR(){
        try {
            String query = "DELETE FROM categorias WHERE id_categoria = "+this.idCategoria;
            Statement stmt = Conexion.conexion.createStatement();
            stmt.executeUpdate(query);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public ObservableList<CategoriasDAO> LISTARCATEGORIAS(){
        ObservableList<CategoriasDAO> listCat = FXCollections.observableArrayList();
        CategoriasDAO objC;
        try{
            String query = "SELECT * FROM categorias";
            Statement stmt = Conexion.conexion.createStatement();
            ResultSet res = stmt.executeQuery(query);
            while(res.next()){
                objC = new CategoriasDAO();
                objC.idCategoria = res.getInt("id_categoria");
                objC.nomCategoria = res.getString("categoria");
                listCat.add(objC);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return listCat;
    }
}
