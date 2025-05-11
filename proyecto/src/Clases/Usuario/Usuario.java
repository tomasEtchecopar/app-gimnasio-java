package Clases.Usuario;

public class Usuario extends Persona{
    public Usuario(String nombreApellido, String mail, String contrasenia, int edad, double peso, int alturaCM, boolean premium) {
        super(nombreApellido, mail, contrasenia, edad, peso, alturaCM, premium);
    }



    @Override
    public void borrarUsuario() {

    }

    @Override
    public void editarUsuario() {

    }
}
