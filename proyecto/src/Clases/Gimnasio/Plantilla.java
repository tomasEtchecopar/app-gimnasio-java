package Clases.Gimnasio;

import Clases.Usuario.Usuario;

/// CLASE PLANTILLA: son las rutinas que el usuario puede elegir. Se almacenan en el JSON 'plantillas.json'.
public class Plantilla extends Rutina{
    private Usuario usuario;

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Plantilla(String nombre, Usuario usuario) {
        super(nombre);
        this.usuario = usuario;
    }

    public Plantilla(String nombre) {
        super(nombre);
    }

    @Override
    public void mostrarRutina(){
        System.out.println("--" + this.getNombre());
        mostrarSeriesPorEjercicio();
    }

    @Override
    public void mostrarSeriesPorEjercicio(){
        if (this.getSeries() == null || this.getSeries().isEmpty()) {
            System.out.println("La rutina está vacía.");
        }else {
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
