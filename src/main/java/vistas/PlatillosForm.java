package vistas;

import com.example.demo1.models.PlatillosDAO;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class PlatillosForm extends Stage {
    private Scene scene;
    private HBox hBox;
    private Button btnGuardar;
    private TextField txtNamePla,txtPrecioPla,txtIdCat;
    private PlatillosDAO platillosDAO;
    TableView<PlatillosDAO>tbvPlatillos;
    public PlatillosForm(TableView<PlatillosDAO>tbvPlat,PlatillosDAO objPlatDAO){
        this.tbvPlatillos=tbvPlat;
        this.platillosDAO= objPlatDAO==null ? new PlatillosDAO() : objPlatDAO;
        CrearUI();
        scene=new Scene(hBox);
        this.setTitle("Gestion de Platilos");
        this.setScene(scene);
        this.show();
    }
    private void CrearUI(){
        txtNamePla = new TextField();
        txtNamePla.setText(platillosDAO.getNomPlatillo());
        txtNamePla.setPromptText("Nombre del platillo");

        txtPrecioPla = new TextField();
        txtNamePla.setText(""+platillosDAO.getPrePlatillo());
        txtPrecioPla.setPromptText("Precio del platillo");


        txtIdCat=new TextField();
        txtIdCat.setText(""+platillosDAO.getIdPlatillo());
        txtIdCat.setPromptText("Id categoria");

        btnGuardar = new Button("Guardar");
        btnGuardar.setOnAction(event -> guardarPlatillo());
        hBox = new HBox(txtNamePla,txtPrecioPla,txtIdCat,btnGuardar);
        hBox.setSpacing(10);
        hBox.setPadding(new Insets(10));

    }
    private void guardarPlatillo(){
        platillosDAO.setNomPlatillo(txtNamePla.getText());
        platillosDAO.setPrePlatillo(Double.valueOf(txtPrecioPla.getText()));
        platillosDAO.setIdCat(Integer.valueOf(txtIdCat.getText()));
        if(platillosDAO.getIdPlatillo()>0)
            platillosDAO.ACTUALIZAR();
        else
            platillosDAO.INSERTAR();
        tbvPlatillos.setItems(platillosDAO.LISTARPLATILLOS());
        tbvPlatillos.refresh();
        this.close();
    }
}
