package vistas;

import java.sql.Connection;
import java.sql.DriverManager;

public class Conexion {
    private static String server = "127.0.0.1";
    private static String user =  "adminrestaurante";
    private static String pass ="123";
    private static String db = "restaurante";
    public static  Connection conexion;
    public static void createConexion(){
        try{
            Class.forName("org.mariadb.jdbc.Driver");
            conexion = DriverManager.getConnection("jdbc:mariadb://"+server+":53306/"+db,user,pass);
        }catch(Exception e){
            e.printStackTrace();

        }
    }
}
