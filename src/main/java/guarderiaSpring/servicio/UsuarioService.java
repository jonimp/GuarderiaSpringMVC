package guarderiaSpring.servicio;

import guarderiaSpring.enumerador.TipoUsuario;
import guarderiaSpring.repositorio.UsuarioDAO;
import java.sql.SQLException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import guarderiaSpring.modelo.Empleado;
import guarderiaSpring.modelo.Socio;
import guarderiaSpring.modelo.Usuario;
import guarderiaSpring.modelo.UsuarioListado;
import guarderiaSpring.repositorio.AdministradorDAO;
import guarderiaSpring.repositorio.EmpleadoDAO;
import guarderiaSpring.repositorio.SocioDAO;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioDAO usuarioDAO;

    @Autowired
    private AdministradorDAO administradorDAO;

    @Autowired
    private EmpleadoDAO empleadoDAO;
    
    @Autowired
    private SocioDAO socioDAO;
    
    public Usuario validarUsuario(String usuario, String password) {

        TipoUsuario tipo = usuarioDAO.validarUsuario(usuario, password);

        if (tipo == null) {
            return null;
        }

        return switch (tipo) {

            case ADMINISTRADOR -> administradorDAO.buscarAdministrador(usuario);

            case EMPLEADO -> empleadoDAO.buscarEmpleado(usuario);

            case SOCIO -> socioDAO.buscarSocio(usuario);

            default -> null;
        };
    }
    
    
    
    /*
    public List<UsuarioListado> buscar(String tipoUsuario, String nombre) {
        return usuarioDAO.buscarListado(tipoUsuario, nombre);
    }

    public Usuario buscarPorUsuario(String usuario) {
        return usuarioDAO.buscarPorUsuario(usuario);
    }

    public void actualizar(Usuario u) throws SQLException {
        usuarioDAO.actualizar(u);
    }
    
    public void eliminar(Usuario u) throws SQLException {
        usuarioDAO.eliminar(u);
    }

    public void guardar(Usuario u) throws SQLException {
        usuarioDAO.guardar(u);
    }
    
    public List<Socio> obtenerSocios() {
        return usuarioDAO.obtenerSocios();
    }
    
    public List<Empleado> obtenerEmpleados() {
        return usuarioDAO.obtenerEmpleados();
    }
*/
    
}
