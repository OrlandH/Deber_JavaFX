package com.example.deber_javafx;

public class estudiante {
    int cod;
    int ced;
    String nombre;
    String fecha;


    public estudiante(int cod, int ced, String nombre, String fecha) {
        this.cod = cod;
        this.ced = ced;
        this.nombre = nombre;
        this.fecha = fecha;

    }

    public int getCod() {
        return cod;
    }

    public void setCod(int cod) {
        this.cod = cod;
    }

    public int getCed() {
        return ced;
    }

    public void setCed(int ced) {
        this.ced = ced;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }


}