package Clases.Gimnasio;

public class Serie {
    Ejercicio ejercicio;
    int repeticiones;
    double peso; // en kg

    public Serie(Ejercicio ejercicio, int repeticiones, double peso) {
        this.ejercicio = ejercicio;
        this.repeticiones = repeticiones;
        this.peso = peso;
    }

    public Ejercicio getEjercicio() {
        return ejercicio;
    }

    public void setEjercicio(Ejercicio ejercicio) {
        this.ejercicio = ejercicio;
    }

    public int getRepeticiones() {
        return repeticiones;
    }

    public void setRepeticiones(int repeticiones) {
        this.repeticiones = repeticiones;
    }

    public double getPeso() {
        return peso;
    }

    public void setPeso(double peso) {
        this.peso = peso;
    }

    @Override
    public String toString() {
        return "Ejercicio: " + this.ejercicio.getNombre() + "\n" + this.getPeso() + " KG x " + this.getRepeticiones();
    }
}
