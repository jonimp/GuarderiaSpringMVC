package prog2.guarderiaspring;

import java.sql.SQLException;
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

    public void actualizar(Usuario u) throws SQLException {
        usuariosDAO.actualizar(u);
    }
    
    public void eliminar(Usuario u) throws SQLException {
        usuariosDAO.eliminar(u);
    }

    public void guardar(Usuario u) throws SQLException {
        usuariosDAO.guardar(u);
    }
    
    public List<Socio> obtenerSocios() {
        return usuariosDAO.obtenerSocios();
    }
}
