package modelos;

import vistas.Conexion;

import java.sql.Statement;

public class CategoriasDAO {
    private int idCategoria;
    private String nomCategoria;
    public void INSERTAR(){
        try{
            String query = "INSERTO INTO categorias"+"(nomCategoria) VALUES('"+this.nomCategoria+"')";
          //  Statement stmt = Conexion.conexion.createStatement();
           // stmt.execute(query);
        }catch (Exception e){
            e.printStackTrace();
        }

    }
    public void ACTUALIZAR(){

    }

}
