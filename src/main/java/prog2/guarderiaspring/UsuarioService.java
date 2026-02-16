package prog2.guarderiaspring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioDAO usuariosDAO;

    public List<UsuarioListado> buscar(String tipoUsuario, String nombre) {
        return usuariosDAO.buscarListado(tipoUsuario, nombre);
    }

    public Usuario buscarPorUsuario(String usuario) {
        return usuariosDAO.buscarPorUsuario(usuario);
    }

}
