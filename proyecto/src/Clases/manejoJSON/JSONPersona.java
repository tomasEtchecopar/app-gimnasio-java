package Clases.manejoJSON;

import Clases.Usuario.Persona;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

public class JSONPersona {
    private static final String ARCHIVO = "src/datos/usuarios.json"; //ruta del archivo

    public static void crearArchivoUsuarios(Persona persona) throws JSONException {
        JSONObject Jpersona = new JSONObject();

        Jpersona.put("usuario", persona.getUsuario());
        Jpersona.put("contrasenia", persona.getContrasenia());
        Jpersona.put("nombre", persona.getNombre());
        Jpersona.put("apellido", persona.getApellido());
        Jpersona.put("edad", persona.getEdad());
        Jpersona.put("peso", persona.getPeso());
        Jpersona.put("altura", persona.getAltura());
        Jpersona.put("premium", persona.isPremium());
        JSONUtiles.grabar(Jpersona, ARCHIVO);
    }
    public static void agregarUsuario(Persona persona) throws JSONException {
        JSONTokener archivo = JSONUtiles.leer(ARCHIVO);
        if(archivo==null){
            crearArchivoUsuarios(persona);
        }
        // chequear si el usuario ya esta cargado en el archivo, tirar excepcion

    }
}
