import Clases.Gimnasio.Ejercicio;
import Clases.Gimnasio.Rutina;
import Clases.Gimnasio.Serie;
import Clases.Menu.MainMenu;
import Clases.Usuario.Admin;
import Clases.manejoJSON.JSONPersona;
import Clases.manejoJSON.JSONUtiles;
import org.json.JSONException;

public class Main {
    public static void main(String[] args) {
        try{
            MainMenu.run();
        } catch (Exception e) { // por si se lanza alguna excepcion no capturada en nuestro codigo
            System.err.println("Excepción capturada en main");
            e.printStackTrace();
        }
    }

}
