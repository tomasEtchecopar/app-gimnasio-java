package Clases.manejoJSON;

import Clases.Gimnasio.Plantilla;
import Clases.Usuario.Admin;
import Clases.Usuario.Persona;
import Clases.Usuario.Usuario;
import Excepciones.UsuarioNoExisteException;
import Excepciones.UsuarioYaExisteException;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.FileNotFoundException;

public class JSONPersona {
    private static final String ARCHIVO = "src/datos/usuarios.json"; //ruta del archivo

    // Crea el archivo de Usuarios
    public static void crearArchivo(Persona persona) throws JSONException, IllegalAccessException {
        JSONObject Jpersona = JSONUtiles.objetoToJSONOBJECT(persona);
        JSONArray archivo = new JSONArray();
        archivo.put(Jpersona);

        JSONUtiles.grabar(archivo, ARCHIVO);
    }

    // Pone los datos en el JSON.
    public static void escribirJSON(Persona persona) throws JSONException, IllegalAccessException {
        JSONTokener tokenerArchivo = JSONUtiles.leer(ARCHIVO);
        if(tokenerArchivo==null){
            crearArchivo(persona);
        }else{
            JSONArray archivo = new JSONArray(tokenerArchivo);
            if(!existeUsuario(archivo, persona)) {
                JSONObject Jpersona = JSONUtiles.objetoToJSONOBJECT(persona);
                archivo.put(Jpersona);
                JSONUtiles.grabar(archivo, ARCHIVO);
            }else{
                throw new UsuarioYaExisteException("El nombre de usuario que intenta usar no se encuentra disponible.");
            }
        }
    }

    // Retorna una Persona del JSON buscada mediante sus datos de Usuario.
    public static Persona getFromJSON(Usuario persona) throws FileNotFoundException, JSONException, IllegalAccessException, UsuarioNoExisteException {
        JSONTokener tokenerArchivo = JSONUtiles.leer(ARCHIVO);
        Persona p = null;
        if(tokenerArchivo==null){
            throw new FileNotFoundException("El archivo no se encuentra en el directorio especificado.");
        }else{
            JSONArray archivo = new JSONArray(tokenerArchivo);
                for (int i = 0; i < archivo.length(); i++) {
                    JSONObject usuario = archivo.getJSONObject(i);
                    String nombreUsuario = usuario.getString("usuario");
                    String contrasenia = usuario.getString("contrasenia");
                    if(nombreUsuario.equals(persona.getUsuario()) && contrasenia.equals(persona.getContrasenia())){
                        if(nombreUsuario.equalsIgnoreCase("admin")){ //para cargar admins
                            p = JSONUtiles.jsonObjectToObjeto(usuario, Admin.class);
                        }else{
                            p = JSONUtiles.jsonObjectToObjeto(usuario, Usuario.class);
                        }
                        break;
                    }
               }
        }
        if(p==null){
            throw   new UsuarioNoExisteException("El usuario o contrasenia son incorrectos.");
        }
        return p;
    }

    // Se fija si existe un Usuario
    public static boolean existeUsuario(JSONArray archivo, Persona persona) throws JSONException {
       boolean ret = false;
        for(int i = 0; i<archivo.length();i++){
            JSONObject usuario = archivo.getJSONObject(i);
            String usuarioNombre = usuario.getString("usuario");
            if (usuarioNombre.equals(persona.getUsuario())){
                ret=true;
                break;
            }
        }
        return ret;
    }
}
