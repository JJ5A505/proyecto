package vistas;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Calculadora extends Stage {

    private Scene escena;
    private VBox vBox;
    private GridPane grdTeclado;
    private TextField txtresultado;
    private Button[][] arTeclas = new Button[4][4];
    private char[] arDigitos = {'7', '4', '1', '.', '8', '5', '2', '0', '9', '6', '3', '-', '/', '*', '+', '='};
    private boolean nuevaEntrada = true;
    private char operador = ' ';
    private double numeroUno = 0.0;  // Variable para el primer número
    private double numeroDos = 0.0;  // Variable para el segundo número

    public Calculadora() {
        CrearUI();
        escena = new Scene(vBox, 200, 200);
        escena.getStylesheets().add(getClass().getResource("/estilos/calculadora.css").toString());
        this.setTitle("Calculadora");
        this.setScene(escena);
        this.show();
    }

    private void CrearUI() {
        int pos = 0;
        grdTeclado = new GridPane();
        vBox = new VBox();
        txtresultado = new TextField("0");
        txtresultado.setAlignment(Pos.BASELINE_RIGHT);
        txtresultado.setEditable(false);

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                arTeclas[i][j] = new Button(String.valueOf(arDigitos[pos]));
                arTeclas[i][j].setPrefSize(50, 50);
                int finalPos = pos;
                arTeclas[i][j].setOnAction((event) -> GenerarExprecion(String.valueOf(arDigitos[finalPos])));
                grdTeclado.add(arTeclas[i][j], i, j);
                pos++;
            }
        }

        vBox = new VBox(txtresultado, grdTeclado);
    }
    private void GenerarExprecion(String simbolo) {
        if (Character.isDigit(simbolo.charAt(0))) {
            // Si se presiona un dígito, se agrega al número actual
            if (nuevaEntrada) {
                // Si es una nueva entrada, se borra el contenido anterior
                txtresultado.clear();
                nuevaEntrada = false;
            }
            txtresultado.appendText(simbolo);
        } else if (simbolo.equals("=")) {
            // Si se presiona "=", se realiza la operación
            numeroDos = Double.parseDouble(txtresultado.getText());
            double resultado = realizarOperacion();
            txtresultado.setText(String.valueOf(resultado));
            nuevaEntrada = true;
        } else {
            // Si se presiona un operador, se almacena el número actual como númeroUno
            numeroUno = Double.parseDouble(txtresultado.getText());
            operador = simbolo.charAt(0);
            nuevaEntrada = true;
        }
    }
    // Método para realizar la operación
    private double realizarOperacion() {
        switch (operador) {
            case '+':
                return numeroUno + numeroDos;
            case '-':
                return numeroUno - numeroDos;
            case '*':
                return numeroUno * numeroDos;
            case '/':
                if (numeroDos == 0.0) {
                    txtresultado.setText("Error: División por cero");
                    nuevaEntrada = true;
                    return Double.NaN; // Usar NaN (Not-a-Number)
                }
                return numeroUno / numeroDos;

            default:
                txtresultado.setText("Error: Operador no válido");
                nuevaEntrada = true;
                return Double.NaN; // "Not-a-Number" (No es un número)
        }
    }
}
