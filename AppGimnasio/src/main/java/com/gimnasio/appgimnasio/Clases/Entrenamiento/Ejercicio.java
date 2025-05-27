package com.gimnasio.appgimnasio.Clases.Entrenamiento;

public class Ejercicio {
    private String nombre;
    private String descripcion;

    public Ejercicio(String nombre, String descripcion) {
        this.nombre = nombre;
        this.descripcion = descripcion;
    }

    public Ejercicio(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @Override
    public String toString() {
        return "-" + this.getNombre() + "\n" + this.getDescripcion();
    }
}
