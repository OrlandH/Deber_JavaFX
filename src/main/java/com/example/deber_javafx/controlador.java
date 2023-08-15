package com.example.deber_javafx;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

import java.sql.*;

public class controlador {
    @FXML
    private TextField codigot;
    @FXML
    private TextField cedulat;
    @FXML
    private TextField nombret;
    @FXML
    private TextField fechat;
    @FXML
    private Button buscarcod;
    @FXML
    private Button actualizarElPresenteRegistroButton;
    @FXML
    private Button borrarElPresenteRegistroButton;
    @FXML
    private Button ingresarElPresenteRegistroButton;
    @FXML
    private Button limpiarElFormularioButton;
    private static final String DB_URL = "jdbc:mysql://localhost/POOPRUEBA2";
    private static final String USER = "root";
    private static final String PASS = "24@Diolove";
    private void mostrarMensajeExito(String mensaje) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Éxito");
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
    private void mostrarMensajeError(String mensaje) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }

    @FXML
    private void initialize() {
        buscarcod.setOnAction(event -> buscarPorCodigo());
        actualizarElPresenteRegistroButton.setOnAction(event -> actualizarRegistro());
        borrarElPresenteRegistroButton.setOnAction(event -> borrarRegistro());
        ingresarElPresenteRegistroButton.setOnAction(event -> ingresarRegistro());
        limpiarElFormularioButton.setOnAction(event -> limpiarFormulario());
    }

    private void buscarPorCodigo() {
        int codigo = Integer.parseInt(codigot.getText());

        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS)) {
            String SQL_Query_select = "SELECT * FROM est_prueba2 WHERE codigo_est = ?";
            try (PreparedStatement pstm = conn.prepareStatement(SQL_Query_select)) {
                pstm.setInt(1, codigo);
                try (ResultSet rs = pstm.executeQuery()) {
                    if (rs.next()) {
                        estudiante Est1 = new estudiante(rs.getInt("codigo_est"), rs.getInt("cedula"), rs.getString("nombre"), rs.getString("fecha_nac"));

                        cedulat.setText(String.valueOf(Est1.getCed()));
                        nombret.setText(Est1.getNombre());
                        fechat.setText(Est1.getFecha());

                        mostrarMensajeExito("Estudiante encontrado");
                    } else {
                        mostrarMensajeError("No se encontró el estudiante");
                        codigot.setText(" ");
                        cedulat.setText(" ");
                        nombret.setText(" ");
                        fechat.setText(" ");
                    }
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    private void actualizarRegistro() {

    }

    private void borrarRegistro() {

    }

    private void ingresarRegistro() {

    }

    private void limpiarFormulario() {

    }
}