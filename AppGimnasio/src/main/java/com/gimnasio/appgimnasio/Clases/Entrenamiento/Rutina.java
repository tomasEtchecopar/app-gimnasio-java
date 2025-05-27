package com.gimnasio.appgimnasio.Clases.Entrenamiento;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

//clase rutina, es el objeto el cual se accede cuando el usuario quiera iniciar un entrenamiento. Tambien se guarda como plantilla
public class Rutina {
    private String nombre;
    private List<Serie> series;
    private Date fecha;

    public Rutina(String nombre) {
        this.nombre = nombre;
        this.series = new ArrayList<>();
        this.fecha = new Date();
    }

    public String getNombre() {
        return nombre;
    }


    public Date getFecha() {
        return fecha;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void agregarSerie(Serie serie){
        this.series.add(serie);
    }

    public void mostrarRutina(){
        System.out.println("--" + this.getNombre());
        System.out.println(this.getFecha());
        mostrarSeriesPorEjercicio();
    }
    //metodo para imprimir la rutina, printea el nombre del ejercicio solo si es dintinto al de la serie anterior
    public void mostrarSeriesPorEjercicio(){
        if (series == null || series.isEmpty()) {
            System.out.println("La rutina está vacía.");
            return;
        }
        String ejercicioAnterior = "";

        for (Serie serie : series){
            String nombreEjercicio = serie.getEjercicio().getNombre();

            if (!nombreEjercicio.equals(ejercicioAnterior)) {
                System.out.println("Ejercicio: " + nombreEjercicio);
                ejercicioAnterior = nombreEjercicio;
            }

            System.out.println( serie.getPeso() + " KG" + " x " + serie.getRepeticiones());
        }

    }

    @Override
    public String toString() {
        return "--" + this.getNombre() + "\n" + this.series;
    }
}
