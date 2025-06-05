package Clases.manejoJSON;

import Clases.Gimnasio.Ejercicio;
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
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class JSONPersona {
    private static final String ARCHIVO = "src/datos/usuarios.json"; //ruta del archivo

    private static void crearArchivo(Persona persona) throws JSONException, IllegalAccessException {
        JSONObject Jpersona = JSONUtiles.objetoToJSONOBJECT(persona);
        JSONArray archivo = new JSONArray();
        archivo.put(Jpersona);

        JSONUtiles.grabar(archivo, ARCHIVO);
    }

    private static void escribirJSON(Persona persona) throws JSONException, IllegalAccessException {
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

    private static Persona getFromJSON(Usuario persona) throws FileNotFoundException, JSONException, IllegalAccessException, UsuarioNoExisteException {
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

    public static List<Usuario> getAllUsuarios() throws FileNotFoundException, JSONException, IllegalAccessException {
        JSONTokener tokenerArchivo = JSONUtiles.leer(ARCHIVO);
        List<Usuario> usuarios = new ArrayList<>();
        if(tokenerArchivo==null){
            throw new FileNotFoundException("El archivo no se encuentra en el directorio especificado.");
        }else{
            JSONArray Jusuarios = new JSONArray(tokenerArchivo);

            for (int i = 0; i < Jusuarios.length(); i++) {
                Usuario p = JSONUtiles.jsonObjectToObjeto(Jusuarios.getJSONObject(i), Usuario.class);
                usuarios.add(p);
            }
        }
        return usuarios;
    }

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

    public static void sobreescribirJSONUsuarios(List<Usuario> usuarios) throws JSONException, IllegalAccessException {
        JSONArray jUsuarios = new JSONArray();

        for(Usuario u : usuarios){
            jUsuarios.put(JSONUtiles.objetoToJSONOBJECT(u));
        }

        JSONUtiles.grabar(jUsuarios, ARCHIVO);
    }

    public static Persona iniciarSesion(String nombreUsuario, String contrasenia){
        Persona usuario=null;
        try {
            usuario = JSONPersona.getFromJSON(new Usuario(nombreUsuario, contrasenia));
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        return usuario;
    }

    public static void registro(Usuario usuario){
        try {
            JSONPersona.escribirJSON(usuario);
        } catch (JSONException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    public static void editarUsuario(Usuario usuario){
        try {
            List<Usuario> usuarios = getAllUsuarios();
            usuarios.remove(usuario); //elimina el usuario que contenga el mismo nombre de usuario
            usuarios.add(usuario); //agrega usuario modificado

            sobreescribirJSONUsuarios(usuarios);
        } catch (FileNotFoundException | JSONException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    public static void borrarUsuario(String nombre){
        try{
            List<Usuario> usuarios = getAllUsuarios();
            int index=0;
            for(int i=0;i<usuarios.size();i++){
                System.out.println(usuarios.get(i).getNombre());
                if(usuarios.get(i).getNombre().equalsIgnoreCase(nombre)){
                    System.out.println("test");
                    index = i;
                }
            }

            usuarios.remove(index);



        } catch (FileNotFoundException | IllegalAccessException | JSONException e) {
            throw new RuntimeException(e);
        }
    }
}
