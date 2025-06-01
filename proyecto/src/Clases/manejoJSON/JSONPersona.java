package Clases.manejoJSON;

import Clases.Usuario.Persona;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

public class JSONPersona {
    private static final String ARCHIVO = "src/datos/usuarios.json"; //ruta del archivo

    public static void crearArchivoUsuarios(Persona persona) throws JSONException, IllegalAccessException {
        JSONObject Jpersona = new JSONObject();


        Jpersona = JSONUtiles.objetoToJSONOBJECT(persona);
        JSONArray archivo = new JSONArray();
        archivo.put(Jpersona);

        JSONUtiles.grabar(archivo, ARCHIVO);
    }
    public static void agregarUsuario(Persona persona) throws JSONException, IllegalAccessException {
        JSONTokener tokenerArchivo = JSONUtiles.leer(ARCHIVO);
        if(tokenerArchivo==null){
            crearArchivoUsuarios(persona);
        }else{
            JSONArray archivo = new JSONArray(tokenerArchivo);
        }

    }
}
