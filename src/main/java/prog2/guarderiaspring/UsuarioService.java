package prog2.guarderiaspring;

import prog2.guarderiaspring.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioDAO usuarioDAO;

    public List<Usuario> buscar(String tipo, String nombre) {
        return usuarioDAO.buscar(tipo, nombre);
    }
}
