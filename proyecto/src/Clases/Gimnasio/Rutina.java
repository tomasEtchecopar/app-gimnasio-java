package Clases.Gimnasio;

import java.util.ArrayList;
import java.util.List;


/// CLASE RUTINA: clase abstracta para almacenar un conjunto de ejercicios y catalogar al mismo con un nombre.
public abstract class Rutina {
    //atributos
    private String nombre; //nombre de la rutina
    private List<Serie> series; //lista de series

    //constructor
    public Rutina(String nombre) {
        this.nombre = nombre;
        this.series = new ArrayList<>(); //Uso de arraylist para mantener el orden de insercion.

    }

    //getters y setters
    public String getNombre() {
        return nombre;
    }


    public List<Serie> getSeries() {
        return series;
    }

    public void setSeries(List<Serie> series) {
        this.series = series;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    //manejo de series
    public void agregarSerie(Serie serie){
        this.series.add(serie);
    }
    public void borrarSerie(int numSerie){
        this.series.remove(numSerie);
    }
    public Serie getSerie(int numSerie){ /// todo: acceder a la serie a traves del indice del array y modificarla a traves de un segundo metodo.
        return this.series.get(numSerie);
    }
    public void editarSerie(int numSerie, Serie newSerie){
        this.getSeries().set(numSerie, newSerie);
    }

    //metodos abstractos
    public abstract void mostrarRutina();
    public abstract void mostrarSeriesPorEjercicio();

    //toString
    @Override
    public String toString() {
        return "--" + this.getNombre() + "\n" + this.series;
    }
}
