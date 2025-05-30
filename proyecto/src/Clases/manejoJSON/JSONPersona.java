package Clases.manejoJSON;

import Clases.Usuario.Persona;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

public class JSONPersona {
    private static final String ARCHIVO = "src/datos/usuarios.json";
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
        //falta cargar historial

    }
    public static void agregarUsuario(Persona persona) throws JSONException {
        JSONArray archivo= new JSONArray(JSONUtiles.leer(ARCHIVO));
        //chequear si existe el archivo
        //despues chequear si el usuario ya esta cargado en el archivo, tirar excepcion

    }
}
