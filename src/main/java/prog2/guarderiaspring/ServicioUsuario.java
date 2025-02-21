package prog2.guarderiaspring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ServicioUsuario {

    @Autowired
    private UsuariosDAO usuarioDAO;

    public Usuario validarUsuario(String usuario, String contrasena) {
        try {
            Usuario user = usuarioDAO.obtenerUsuarioPorNombre(usuario);
            return (user != null && user.getContrasena().equals(contrasena)) ? user : null;
        } catch (Exception e) {
            return null; // Si el usuario no existe o hay error en la consulta
        }
    }
}


