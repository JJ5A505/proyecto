module com.example.demo {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.demo1 to javafx.fxml;
    exports com.example.demo1;
    requires org.kordamp.bootstrapfx.core;

    requires java.sql;
    requires mariadb.java.client;

    //opens com.example.demo1.modelos;

}
