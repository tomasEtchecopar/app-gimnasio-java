package Clases.Gimnasio;

import java.util.Date;

/// CLASE ENTRENAMIENTO: es la que se almacena en el historial del usuario. Tiene un atributo fecha que le facilita la busqueda de entrenamientos al usuario
public class Entrenamiento extends Rutina{
    private Date fecha;

    //constructor
    public Entrenamiento(String nombre, Date fecha) {
        super(nombre);
        this.fecha = fecha;
    }
    //getters y setters


    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    //mostrado
    @Override
    public void mostrarRutina(){
        System.out.println("--" + this.getNombre());
        System.out.println(this.getFecha());
         mostrarSeriesPorEjercicio();
    }

    //metodo para imprimir la rutina, printea el nombre del ejercicio solo si es dintinto al de la serie anterior
    @Override
    public void mostrarSeriesPorEjercicio(){
        if (this.getSeries() == null || this.getSeries().isEmpty()) {
            System.out.println("La rutina está vacía.");
        }
        else {
            String ejercicioAnterior = "";

            for (Serie serie : this.getSeries()) {
                String nombreEjercicio = serie.getEjercicio().getNombre();

                if (!nombreEjercicio.equals(ejercicioAnterior)) {
                    System.out.println("Ejercicio: " + nombreEjercicio);
                    ejercicioAnterior = nombreEjercicio;
                }

                System.out.println(serie.getPeso() + " KG" + " x " + serie.getRepeticiones());
            }
        }
    }
}
