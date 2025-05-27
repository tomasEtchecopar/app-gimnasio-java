package Clases.Usuario;

public class Usuario extends Persona{
    public Usuario(String usuario, String contrasenia, String nombre, String apellido, int edad, double peso, double altura) {
        super(usuario, contrasenia, nombre, apellido, edad, peso, altura);
    }

    public Usuario() {
    }



    @Override
    public void borrarUsuario() {

    }

    @Override
    public void editarUsuario() {

    }
}
