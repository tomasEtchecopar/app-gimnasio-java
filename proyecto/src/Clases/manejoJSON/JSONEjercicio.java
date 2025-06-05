package Clases.manejoJSON;

import Clases.Gimnasio.Ejercicio;
import Excepciones.EjercicioYaExisteException;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;

public class JSONEjercicio{
    private static final String ARCHIVO = "src/datos/ejercicios.json"; // Ruta del archivo.

    // Crea un JSON de Ejercicios.
    private static void crearArchivo(Ejercicio ejercicio) throws JSONException, IllegalAccessException {
        JSONObject JEjercicio = JSONUtiles.objetoToJSONOBJECT(ejercicio);
        JSONArray archivo = new JSONArray();
        archivo.put(JEjercicio);

        JSONUtiles.grabar(archivo, ARCHIVO);
    }

    // Escribe los datos al JSON de ejercicios teniendo en cuenta si ya se ha creado o no.
    public static void escribirJSON(Ejercicio ejercicio) throws JSONException, IllegalAccessException {
        JSONTokener tokenerArchivo = JSONUtiles.leer(ARCHIVO);
        if(tokenerArchivo==null){
            crearArchivo(ejercicio);
        }else{
            JSONArray archivo = new JSONArray(tokenerArchivo);
            if(!existeEjercicio(archivo, ejercicio)) {
                JSONObject JEjercicio = JSONUtiles.objetoToJSONOBJECT(ejercicio);
                archivo.put(JEjercicio);
                JSONUtiles.grabar(archivo, ARCHIVO);
            }else{
                throw new EjercicioYaExisteException("El ejercicio ya se encuentra creado.");
            }
        }
    }
    // Pone en un Mapa todos los ejercicios del JSON.
    public static Map<String, Ejercicio> getFromJSON() throws JSONException, FileNotFoundException {
        JSONTokener tokenerArchivo = JSONUtiles.leer(ARCHIVO);

        if(tokenerArchivo==null){
            throw new FileNotFoundException("El archivo no se encuntra en el directorio especificado");
        }
        JSONArray Jejercicios = new JSONArray(tokenerArchivo);
        Map<String, Ejercicio> ejercicios = new HashMap<>();

        for (int i = 0; i < Jejercicios.length(); i++) {
            Ejercicio ejercicio = null;
            try {
                ejercicio = JSONUtiles.jsonObjectToObjeto((Jejercicios.getJSONObject(i)), Ejercicio.class);
                ejercicios.put(ejercicio.getNombre(), ejercicio);
            } catch (IllegalAccessException e) {
                System.out.println(e.getMessage());
            }
        }

        return ejercicios;
    }

    // Chequea si el ejercicio existe.
    public static boolean existeEjercicio(JSONArray archivo, Ejercicio ejercicio) throws JSONException {
        boolean ret = false;
        for(int i = 0; i<archivo.length();i++){
            JSONObject JEjercicio = archivo.getJSONObject(i);
            String nombreEjercicio = JEjercicio.getString("nombre");
            if (nombreEjercicio.equals(ejercicio.getNombre())){
                ret=true;
                break;
            }
        }
        return ret;
    }

    public static void sobreescribirJSONEjercicios(Map<String, Ejercicio> mapa) throws JSONException, IllegalAccessException {
        JSONArray jEjercicios = new JSONArray();

        for(Ejercicio e : mapa.values()){
            jEjercicios.put(e);
            System.out.println(e);
        }

        JSONUtiles.grabar(jEjercicios, ARCHIVO);
    }
}
