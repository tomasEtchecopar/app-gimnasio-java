package Clases.Menu;

import Clases.Usuario.Usuario;
import Clases.manejoJSON.JSONPersona;
import org.json.JSONException;

import java.util.Scanner;

public class RegistroMenu {
    public static void mostrar(Scanner teclado) throws IllegalArgumentException{
        System.out.println("Bienvenido a nuestra app! A continuacion podrá llenar el formulario de registro:");
        Usuario usuario = LecturaTeclado.formularioRegistro(teclado);
        try {
            JSONPersona.escribirJSON(usuario); // lo agrego al json
        } catch (JSONException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }
}
