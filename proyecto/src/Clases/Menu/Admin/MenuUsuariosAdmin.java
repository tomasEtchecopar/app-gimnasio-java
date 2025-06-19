package Clases.Menu.Admin;

import Clases.Menu.MainMenu;
import Clases.Menu.RegistroMenu;
import Clases.Menu.Utiles.Editores;
import Clases.Menu.Utiles.LecturaTeclado;
import Clases.Usuario.Usuario;
import Clases.manejoJSON.JSONUsuario;
import org.json.JSONException;

import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import static Clases.Menu.Utiles.LecturaTeclado.leerEntero;

public class MenuUsuariosAdmin {
    public static void menuUsuarios(Scanner teclado, Usuario usuario){
        int opcion=-1;
        while(opcion!=5) {
            List<Usuario> usuarios;
            try{
                usuarios = JSONUsuario.getAllUsuarios();
                usuarios.remove(usuario); //para no borrarse a si mismo
                usuarios.sort(Comparator.comparing(Usuario::getId));
            } catch ( JSONException | IllegalAccessException e) {
                throw new RuntimeException(e);
            }

            MainMenu.limpiarConsola();
            System.out.println("\n\n--MENU DE USUARIOS--");
            System.out.println("1) Ver Usuarios");
            System.out.println("2) Agregar Usuarios");
            System.out.println("3) Borrar Usuarios");
            System.out.println("4) Editar Usuario");
            System.out.println("5) Salir");

            opcion = leerEntero(teclado, 1, 5);

            switch (opcion){
                case 1 -> verUsuarios(usuarios);
                case 2 -> JSONUsuario.registro(RegistroMenu.formularioRegistro(teclado));
                case 3 -> menuBorrarUsuarios(teclado, usuarios);
                case 4 -> {
                    verUsuarios(usuarios);
                    System.out.printf("\nIngrese la id del usuario a editar: ");
                    int id = LecturaTeclado.leerEntero(teclado, usuarios.getFirst().getId(), usuarios.getLast().getId());
                    editar(usuarios, id, teclado);
                }
                case 5 -> {
                    return;
                }
            }
            LecturaTeclado.continuar(teclado);
        }
    }

    private static void menuBorrarUsuarios(Scanner teclado, List<Usuario> usuarios){
        int opcion=-1;
        while(opcion!=3){
            MainMenu.limpiarConsola();
            System.out.println("1) Borrar por nombre");
            System.out.println("2) Borrar por id");
            System.out.println("3) Volver");
            opcion = LecturaTeclado.leerEntero(teclado, 1, 3);

            switch(opcion){
                case 1-> {
                    verUsuarios(usuarios);
                    System.out.printf("\nIngrese el nombre del usuario a eliminar: ");
                    String nombreDeUsuario = teclado.nextLine();
                    for(Usuario u : usuarios){
                        if(u.getUsuario().equalsIgnoreCase(nombreDeUsuario)){
                            System.out.println("Mostrando informacion de " +nombreDeUsuario+".");
                            System.out.println(u);
                            System.out.println("Esta seguro que desea eliminarlo? (s/n)");
                            if(LecturaTeclado.leerBooleanSN(teclado)) {
                                JSONUsuario.borrarUsuario(u);
                            }
                        }
                    }
                }
                case 2 -> {
                    verUsuarios(usuarios);
                    System.out.println("\nIngrese la id del usuario a eliminar: ");
                    int id = LecturaTeclado.leerEntero(teclado, usuarios.getFirst().getId(), usuarios.getLast().getId());
                    for(Usuario u :usuarios){
                        if(u.getId()==id){
                            System.out.println("Mostrando informacion de " + u.getUsuario()+".");
                            System.out.println(u);
                            System.out.println("Esta seguro que desea eliminarlo? (s/n)");
                            if(LecturaTeclado.leerBooleanSN(teclado)) {
                                JSONUsuario.borrarUsuario(u);
                            }
                        }
                    }
                }
                case 3 ->{
                    return;
                }
            }
            LecturaTeclado.continuar(teclado);
        }
    }


    public static void verUsuarios(List<Usuario> usuarios){
        for(int i=0; i< usuarios.size(); i++){
            System.out.printf("\n\nUsuario (ID: " + usuarios.get(i).getId() + "): \n"+ usuarios.get(i));
        }
    }

    public static void editar(List<Usuario> usuarios, int id, Scanner teclado){
        Usuario us = null;

        for (Usuario usuario : usuarios) {
            if (usuario.getId() == id) {
                us = usuario;
            }
        }

        if(us!=null) {
            Editores.menuEdicionDeUsuario(teclado, us);
        }
    }
}
