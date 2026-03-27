package guarderiaSpring.servicio;

import guarderiaSpring.dto.BusquedaUsuarioFormDTO;
import guarderiaSpring.dto.RegistroUsuarioDTO;
import guarderiaSpring.enumerador.TipoUsuario;
import static guarderiaSpring.enumerador.TipoUsuario.ADMINISTRADOR;
import static guarderiaSpring.enumerador.TipoUsuario.EMPLEADO;
import static guarderiaSpring.enumerador.TipoUsuario.SOCIO;
import guarderiaSpring.modelo.Administrador;
import guarderiaSpring.repositorio.UsuarioDAO;
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

            case ADMINISTRADOR ->
                administradorDAO.buscarAdministrador(usuario);
            case EMPLEADO ->
                empleadoDAO.buscarEmpleado(usuario);
            case SOCIO ->
                socioDAO.buscarSocio(usuario);
            default ->
                null;
        };
    }

    public List<UsuarioListado> listarUsuarios(BusquedaUsuarioFormDTO busqueda) {
        return usuarioDAO.buscarListado(busqueda.getNombre(), busqueda.getTipoUsuario());
    }
    
    public List<Socio> obtenerSocios(){
        return socioDAO.obtenerLista();
    }

    public void registrarUsuario(RegistroUsuarioDTO dto) {

        if (dto.getTipo() == null) {
            throw new IllegalArgumentException("El tipo de usuario es obligatorio");
        }

        usuarioDAO.guardarUsuarioBase(dto);

        switch (dto.getTipo()) {

            case ADMINISTRADOR -> {
                Administrador admin = new Administrador();
                mapearDatosBasicos(admin, dto);
                administradorDAO.guardarAdministrador(admin);
            }

            case EMPLEADO -> {
                Empleado emp = new Empleado();
                mapearDatosBasicos(emp, dto);
                emp.setDireccion(dto.getDireccion());
                emp.setTelefono(dto.getTelefono());
                emp.setEspecialidad(dto.getEspecialidad());
                empleadoDAO.guardarEmpleado(emp);
            }

            case SOCIO -> {
                Socio socio = new Socio();
                mapearDatosBasicos(socio, dto);
                socio.setDireccion(dto.getDireccion());
                socio.setTelefono(dto.getTelefono());
                socioDAO.guardarSocio(socio);
            }

            default ->
                throw new IllegalArgumentException("Tipo desconocido");
        }
    }

    private void mapearDatosBasicos(Usuario usuario, RegistroUsuarioDTO dto) {
        usuario.setUsuario(dto.getUsuario());
        usuario.setPassword(dto.getPassword());
        usuario.setNombre(dto.getNombre());
        usuario.setDni(dto.getDni());
    }

    public void eliminarUsuario(String usuario) {

        String tipoUsuario = usuarioDAO.buscarTipoUsuario(usuario);

        switch (tipoUsuario.toUpperCase()) {

            case "ADMINISTRADOR" ->
                administradorDAO.eliminarAdministrador(usuario);

            case "EMPLEADO" ->
                empleadoDAO.eliminarEmpleado(usuario);

            case "SOCIO" ->
                socioDAO.eliminarSocio(usuario);

            default ->
                throw new RuntimeException("Tipo desconocido");
        }

        
        usuarioDAO.eliminarUsuario(usuario);
    }

    public Usuario buscarPorUsuario(String nombreUsuario) {

        String tipoUsuario = usuarioDAO.buscarTipoUsuario(nombreUsuario);

        return switch (tipoUsuario.toUpperCase()) {

            case "ADMINISTRADOR" ->
                administradorDAO.buscarAdministrador(nombreUsuario);
            case "EMPLEADO" ->
                empleadoDAO.buscarEmpleado(nombreUsuario);
            case "SOCIO" ->
                socioDAO.buscarSocio(nombreUsuario);
            default ->
                null;
        };

    }

    public void actualizarUsuario(RegistroUsuarioDTO dto) {

        String tipoUsuario = usuarioDAO.buscarTipoUsuario(dto.getUsuarioOriginal());
        usuarioDAO.actualizarUsuarioBase(dto);

        switch (tipoUsuario.toUpperCase()) {
            case "ADMINISTRADOR" ->
                administradorDAO.editarAdministrador(dto);
            case "EMPLEADO" ->
                empleadoDAO.editarEmpleado(dto);
            case "SOCIO" ->
                socioDAO.editarSocio(dto);
            default ->
                throw new RuntimeException("Tipo de usuario desconocido");
        }
    }

} //FIN DE CLASE
