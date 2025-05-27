package Clases;

import java.util.ArrayList;
import java.util.List;

//clase para leer los json
public class Lectora<T>{
    List<T> datos;
    String nombreArchivo;

    public Lectora(String nombreArchivo) {
        datos = new ArrayList<>();
        this.nombreArchivo = nombreArchivo;
        datos = leerJSON(nombreArchivo);
    }


    /////metodos para cargar/leer json desde la lista datos.
}
