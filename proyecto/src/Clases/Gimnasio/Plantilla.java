package Clases.Gimnasio;

/// CLASE PLANTILLA: son las rutinas que el usuario puede elegir. Se almacenan en el JSON 'plantillas.json'.
public class Plantilla extends Rutina{
    public Plantilla(String nombre) {
        super(nombre);
    }

    // Mostrado de rutina completa.
    @Override
    public void mostrarRutina(){
        System.out.println("--" + this.getNombre());
        mostrarSeriesPorEjercicio();
    }

    // Mostrado de series por ejercicio.
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
