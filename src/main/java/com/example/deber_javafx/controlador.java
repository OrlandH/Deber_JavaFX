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
        int codigo = Integer.parseInt(codigot.getText());
        int cedula = Integer.parseInt(cedulat.getText());
        String nombre = nombret.getText();
        String fecha = fechat.getText();

        estudiante Est1 = new estudiante(codigo, cedula, nombre, fecha);

        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS)) {
            String SQL_QUERY_UPDATE = "UPDATE est_prueba2 SET cedula=?, nombre=?, fecha_nac=? WHERE codigo_est=?";
            try (PreparedStatement pstmt = conn.prepareStatement(SQL_QUERY_UPDATE)) {
                pstmt.setInt(1, Est1.getCed());
                pstmt.setString(2, Est1.getNombre());
                pstmt.setString(3, Est1.getFecha());
                pstmt.setInt(4, Est1.getCod());

                int filasActualizadas = pstmt.executeUpdate();
                if (filasActualizadas > 0) {
                    mostrarMensajeExito("Estudiante actualizado con éxito");
                } else {
                    mostrarMensajeError("Error al actualizar el estudiante");
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

    }

    private void borrarRegistro() {
        int codigo = Integer.parseInt(codigot.getText());

        estudiante Est1 = new estudiante(codigo, 0, "", "");

        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS)) {
            String SQL_QUERY_DELETE = "DELETE FROM est_prueba2 WHERE codigo_est=?";
            try (PreparedStatement pstmt = conn.prepareStatement(SQL_QUERY_DELETE)) {
                pstmt.setInt(1, Est1.getCod());
                int filasEliminadas = pstmt.executeUpdate();
                if (filasEliminadas > 0) {
                    mostrarMensajeExito("Estudiante eliminado con éxito");
                    codigot.setText("");
                    cedulat.setText("");
                    nombret.setText("");
                    fechat.setText("");
                } else {
                    mostrarMensajeError("No se encontró el estudiante para eliminar");
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

    }

    private void ingresarRegistro() {

    }

    private void limpiarFormulario() {

    }
}