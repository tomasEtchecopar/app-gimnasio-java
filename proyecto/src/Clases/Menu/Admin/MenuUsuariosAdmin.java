package Clases.Menu.Admin;

import Clases.Menu.RegistroMenu;
import Clases.Menu.Utiles.Editores;
import Clases.Usuario.Persona;
import Clases.Usuario.Usuario;
import Clases.manejoJSON.JSONPersona;
import org.json.JSONException;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import static Clases.Menu.Utiles.LecturaTeclado.leerEntero;

public class MenuUsuariosAdmin {
    public static void menuUsuarios(Scanner teclado){
        int opcion=-1;
        while(opcion!=5) {
            List<Usuario> usuarios;
            try{
                usuarios = JSONPersona.getAllUsuarios();
            } catch (FileNotFoundException | JSONException | IllegalAccessException e) {
                throw new RuntimeException(e);
            }

            System.out.println("\n\n--MENU DE USUARIOS--");
            System.out.println("1) Ver Usuarios");
            System.out.println("2) Agregar Usuarios");
            System.out.println("3) Borrar Usuarios");
            System.out.println("4) Editar Usuario");
            System.out.println("5) Salir");

            opcion = leerEntero(teclado, 1, 5);

            switch (opcion){
                case 1 -> verUsuarios(usuarios);
                case 2 -> JSONPersona.registro(RegistroMenu.formularioRegistro(teclado));
                case 3 -> {
                    System.out.printf("\nIngrese el nombre del usuario a eliminar: ");
                    JSONPersona.borrarUsuario(teclado.nextLine());
                }
                case 4 -> {
                    System.out.printf("\nIngrese el nombre del usuario a editar: ");
                    String nombreUsuario = teclado.nextLine();
                    editar(usuarios, nombreUsuario, teclado);
                }
                case 5 -> System.out.printf("Adios!\n");
            }

        }
    }

    public static void verUsuarios(List<Usuario> usuarios){
        for(int i=0; i< usuarios.size(); i++){
            System.out.printf("\nUsuario (" + (i+1) + "): \n"+ usuarios.get(i));
        }
    }

    public static void editar(List<Usuario> usuarios, String nombreUsuario, Scanner teclado){
        Usuario us = new Usuario();
        us.setUsuario(nombreUsuario);
        for(int i=0; i< usuarios.size(); i++){
            if(usuarios.get(i).getUsuario().equalsIgnoreCase(nombreUsuario)){
                us = usuarios.get(i);
            }
        }

        Editores.menuEdicionDeUsuario(teclado, us);
    }
}
