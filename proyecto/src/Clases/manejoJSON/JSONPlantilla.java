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

    private static void crearArchivo(Plantilla plantilla) throws JSONException, IllegalAccessException {
        JSONObject Jplantilla = JSONUtiles.objetoToJSONOBJECT(plantilla);
        JSONArray archivo = new JSONArray();
        archivo.put(Jplantilla);

        JSONUtiles.grabar(archivo, ARCHIVO);
    }

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
                throw new RutinaYaExisteException("El usuario ya tiene una rutina con el mismo nombre.");
            }
        }

    }

    private static List<Plantilla> getFromJSON() throws FileNotFoundException, JSONException, IllegalAccessException, UsuarioNoExisteException {
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

    private static boolean existePlantilla(JSONArray archivo, Plantilla plantilla) throws JSONException {
        boolean ret = false;
        for(int i = 0; i<archivo.length();i++){
            JSONObject p = archivo.getJSONObject(i);
            String nombrePlantillaEnArchivo = p.getString("nombre");
            int idPlantillaEnArchivo = p.getInt("id");
            if (nombrePlantillaEnArchivo.equals(plantilla.getNombre()) && idPlantillaEnArchivo==plantilla.getId()){
                ret=true;
                break;
            }
        }
        return ret;
    }

    public static List<Plantilla> leerPlantillas(){
        List<Plantilla> plantillas = new ArrayList<>();
        try {
            plantillas = JSONPlantilla.getFromJSON();
        } catch (FileNotFoundException | JSONException | IllegalAccessException e) {
            System.out.println(e.getMessage());
        }
        return plantillas;
    }

    public static void sobrecargarPlantillas(List<Plantilla> lista) throws JSONException, IllegalAccessException {
        JSONArray jPlantilla = new JSONArray();

        for(Plantilla p : lista){
            jPlantilla.put(JSONUtiles.objetoToJSONOBJECT(p));
        }

        JSONUtiles.grabar(jPlantilla, ARCHIVO);
    }

    public static void borrarPlantilla(int id){
        List<Plantilla> plantillas;
        try {
            plantillas = JSONPlantilla.getFromJSON();
            plantillas.removeIf(p -> p.getId() == id);
            sobrecargarPlantillas(plantillas);

        } catch (RuntimeException | FileNotFoundException | JSONException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }
}
