package Clases.manejoJSON;

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

public class JSONUsuario {
    private static final String ARCHIVO = "src/datos/usuarios.json"; //ruta del archivo

    private static void crearArchivo(Usuario usuario) throws JSONException, IllegalAccessException {
        JSONObject Jpersona = JSONUtiles.objetoToJSONOBJECT(usuario);
        JSONArray archivo = new JSONArray();
        archivo.put(Jpersona);

        JSONUtiles.grabar(archivo, ARCHIVO);
    }

    private static void escribirJSON(Usuario usuario) throws JSONException, IllegalAccessException {
        JSONTokener tokenerArchivo = JSONUtiles.leer(ARCHIVO);
        if(tokenerArchivo==null){
            crearArchivo(usuario);
        }else{
            JSONArray archivo = new JSONArray(tokenerArchivo);
            if(!existeUsuario(archivo, usuario)) {
                JSONObject Jpersona = JSONUtiles.objetoToJSONOBJECT(usuario);
                archivo.put(Jpersona);
                JSONUtiles.grabar(archivo, ARCHIVO);
            }else{
                throw new UsuarioYaExisteException("El nombre de usuario que intenta usar no se encuentra disponible.");
            }
        }
    }

    private static Usuario getFromJSON(Usuario persona) throws FileNotFoundException, JSONException, IllegalAccessException, UsuarioNoExisteException {
        JSONTokener tokenerArchivo = JSONUtiles.leer(ARCHIVO);
        Usuario p = null;
        if(tokenerArchivo==null){
            throw new FileNotFoundException("El archivo no se encuentra en el directorio especificado.");
        }else{
            JSONArray archivo = new JSONArray(tokenerArchivo);
                for (int i = 0; i < archivo.length(); i++) {
                    JSONObject usuario = archivo.getJSONObject(i);
                    String nombreUsuario = usuario.getString("usuario");
                    String contrasenia = usuario.getString("contrasenia");
                    if(nombreUsuario.equals(persona.getUsuario()) && contrasenia.equals(persona.getContrasenia())){
                        p = JSONUtiles.jsonObjectToObjeto(usuario, Usuario.class);
                        break;
                    }
               }
        }
        if(p==null){
            throw new UsuarioNoExisteException("El usuario o contrasenia son incorrectos.");
        }
        return p;
    }

    public static List<Usuario> getAllUsuarios() throws  JSONException, IllegalAccessException {
        JSONTokener tokenerArchivo = JSONUtiles.leer(ARCHIVO);
        List<Usuario> usuarios = new ArrayList<>();
        if(!(tokenerArchivo ==null)) {
            JSONArray Jusuarios = new JSONArray(tokenerArchivo);

            for (int i = 0; i < Jusuarios.length(); i++) {
                Usuario p = JSONUtiles.jsonObjectToObjeto(Jusuarios.getJSONObject(i), Usuario.class);
                usuarios.add(p);
            }
        }
        return usuarios;
    }

    public static boolean existeUsuario(JSONArray archivo, Usuario persona) throws JSONException {
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

    public static Usuario iniciarSesion(String nombreUsuario, String contrasenia){
        Usuario usuario=null;
        try {
            usuario = JSONUsuario.getFromJSON(new Usuario(nombreUsuario, contrasenia));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return usuario;
    }

    public static void registro(Usuario usuario){
        try {
            JSONUsuario.escribirJSON(usuario);
        } catch (JSONException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    public static void actualizarUsuario(Usuario usuario){
        try {
            List<Usuario> usuarios = getAllUsuarios();
            usuarios.remove(usuario); //elimina el usuario que contenga el mismo nombre de usuario
            usuarios.add(usuario); //agrega usuario modificado

            sobreescribirJSONUsuarios(usuarios); //sobreescribe el json
        } catch ( JSONException | IllegalAccessException e) {
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



        } catch ( IllegalAccessException | JSONException e) {
            throw new RuntimeException(e);
        }
    }
    public static int getMaxID(){
        List<Usuario> usuarios = new ArrayList<>();
        int mayorId=0;
        try{
            usuarios = getAllUsuarios();
            for(Usuario usuario: usuarios){
                if(usuario.getId()>mayorId){
                    mayorId = usuario.getId();
                }
            }
        } catch ( IllegalAccessException | JSONException e) {

        }
        return mayorId;
    }
}
