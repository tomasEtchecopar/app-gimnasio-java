package Clases.Menu.Admin;

import Clases.Gimnasio.Plantilla;
import Clases.Gimnasio.Rutina;
import Clases.Menu.MainMenu;
import Clases.Menu.Utiles.LecturaTeclado;
import Clases.Usuario.Persona;
import Clases.Usuario.Usuario;
import Clases.manejoJSON.JSONPersona;
import org.json.JSONException;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

import static Clases.Menu.Utiles.LecturaTeclado.leerEntero;

public class MenuRutinasAdmin {
    public static void menuRutinas(Scanner teclado){
        int opcion=-1;
        while(opcion!=5){
            MainMenu.limpiarConsola();
            System.out.println("--RUTINAS--");
            System.out.println("1) Ver rutinas");
            System.out.println("2) Agregar rutina");
            System.out.println("3) Borrar rutina");
            System.out.println("4) Editar rutina");
            System.out.println("5) Salir");

            opcion = leerEntero(teclado, 1, 5);
            switch(opcion){
                //case 1 ->
            }
        }
    }

    private static Rutina cargarRutinaPorTeclado(Scanner teclado) throws RuntimeException {
        int id=0;
        List<Usuario> usuarios = new ArrayList<>();
        try{
            usuarios = JSONPersona.getAllUsuarios();
        } catch (JSONException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
        //se le asigna una id a la plantilla
        usuarios.sort(Comparator.comparing(Persona::getId));
        System.out.println("Ingrese para que usuario es la rutina: ");
        System.out.println("0. Todos los usuarios");

        for(Usuario u : usuarios){
            System.out.println(u.getId() +". " +u.getNombre() +" "+ u.getApellido());
        }
        id= LecturaTeclado.leerEntero(teclado, 0, usuarios.getLast().getId());
    }
}

