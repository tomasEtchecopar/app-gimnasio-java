package Clases.Gimnasio;

import java.util.Objects;

public class Ejercicio {
    private String nombre;
    private GrupoMuscular grupoMuscular;
    private String descripcion;

    public Ejercicio(String nombre, GrupoMuscular grupoMuscular, String descripcion) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.grupoMuscular = grupoMuscular;
    }

    public Ejercicio() {
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

    public GrupoMuscular getGrupoMuscular() {
        return grupoMuscular;
    }

    public void setGrupoMuscular(GrupoMuscular grupoMuscular) {
        this.grupoMuscular = grupoMuscular;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Ejercicio ejercicio = (Ejercicio) o;
        return Objects.equals(nombre, ejercicio.nombre);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(nombre);
    }

    @Override
    public String toString() {
        return "-" + this.getNombre() + "\n" + this.getDescripcion();
    }


}
