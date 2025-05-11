package Clases.Gimnasio;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
    public void agregarSerie(Serie serie){
        this.series.add(serie);
    }

    public Date getFecha() {
        return fecha;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }


}
