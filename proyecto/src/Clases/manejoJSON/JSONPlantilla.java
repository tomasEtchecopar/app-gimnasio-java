package Clases.manejoJSON;

import Clases.Gimnasio.Plantilla;
import Excepciones.RutinaYaExisteException;
import Excepciones.UsuarioNoExisteException;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.FileNotFoundException;
import java.util.ArrayList;

import java.util.List;


public class JSONPlantilla {
    private static final String ARCHIVO = "src/datos/plantillas.json"; //ruta del archivo

    // Crea un archivo json mediante una instancia de la clase Plantilla pasada por parametro.
    public static void crearArchivo(Plantilla plantilla) throws JSONException, IllegalAccessException {
        JSONObject Jplantilla = JSONUtiles.objetoToJSONOBJECT(plantilla);
        JSONArray archivo = new JSONArray();
        archivo.put(Jplantilla);

        JSONUtiles.grabar(archivo, ARCHIVO);
    }

    // Persiste los datos de la Plantilla en el archivo JSON y controla que la rutina no exista.
    public static void escribirJSON(Plantilla plantilla) throws JSONException, IllegalAccessException {
        JSONTokener tokenerArchivo = JSONUtiles.leer(ARCHIVO);
        if(tokenerArchivo==null){
            crearArchivo(plantilla);
        }else{
            JSONArray archivo = new JSONArray(tokenerArchivo);
            if(!existePlantilla(archivo, plantilla)) {
                JSONObject Jplantilla = JSONUtiles.objetoToJSONOBJECT(plantilla);
                archivo.put(Jplantilla);
                JSONUtiles.grabar(archivo, ARCHIVO);
            }else{
                throw new RutinaYaExisteException("Ya existe una rutina con el mismo nombre.");
            }
        }

    }

    // Pasa del JSON a una lista de Plantillas la informacion dentro del archivo.
    public static List<Plantilla> getFromJSON() throws FileNotFoundException, JSONException, IllegalAccessException, UsuarioNoExisteException {
        JSONTokener tokenerArchivo = JSONUtiles.leer(ARCHIVO);
        if(tokenerArchivo==null){
            throw new FileNotFoundException("El archivo no se encuntra en el directorio especificado");
        }
        JSONArray Jplantillas = new JSONArray(tokenerArchivo);
        List<Plantilla> plantillas = new ArrayList<>();
        for (int i = 0; i < Jplantillas.length(); i++) {
            Plantilla plantilla = null;
            try {
                plantilla = JSONUtiles.jsonObjectToObjeto((Jplantillas.getJSONObject(i)), Plantilla.class);
                plantillas.add(plantilla);
            } catch (IllegalAccessException e) {
                System.out.println(e.getMessage());
            }
        }
        return plantillas;
    }

    // Chequea si la Plantilla ya existe.
    public static boolean existePlantilla(JSONArray archivo, Plantilla plantilla) throws JSONException {
        boolean ret = false;
        for(int i = 0; i<archivo.length();i++){
            JSONObject p = archivo.getJSONObject(i);
            String nombrePlantilla = p.getString("nombre");
            if (nombrePlantilla.equals(plantilla.getNombre())){
                ret=true;
                break;
            }
        }
        return ret;
    }
}
