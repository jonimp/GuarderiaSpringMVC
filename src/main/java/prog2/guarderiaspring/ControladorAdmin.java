package prog2.guarderiaspring;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.sql.SQLException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/admin")
public class ControladorAdmin {

    @Autowired
    UsuarioService usuarioService;

    @GetMapping
    public String vistaAdmin(HttpSession session, HttpServletResponse response) {

        Usuario u = (Usuario) session.getAttribute("usuarioLogeado");

        if (u == null || !(u instanceof Administrador)) {
            return "redirect:/";
        }

        return "vistaAdministrador";
    }

    @GetMapping("/buscar")
    public String buscarUsuarios(
            @RequestParam(value = "tipoUsuario", required = false) String tipoUsuario,
            @RequestParam(value = "nombre", required = false) String nombre,
            Model model,
            HttpSession session) {

        Usuario u = (Usuario) session.getAttribute("usuarioLogeado");

        if (u == null || !(u instanceof Administrador)) {
            return "redirect:/";
        }

        List<UsuarioListado> usuarios
                = usuarioService.buscar(tipoUsuario, nombre);
        model.addAttribute("usuarios", usuarios != null ? usuarios : List.of());

        return "buscarYgestionarUsuario";
    }

    @GetMapping("/ver/{usuario}")
    public String verUsuario(@PathVariable("usuario") String usuario, Model model, HttpSession session) {

        Usuario uSesion = (Usuario) session.getAttribute("usuarioLogeado");

        if (uSesion == null || !(uSesion instanceof Administrador)) {
            return "redirect:/";
        }

        Usuario u = usuarioService.buscarPorUsuario(usuario);

        model.addAttribute("usuario", u);

        return "mostrarDatos";
    }

    @GetMapping("/editar/{usuario}")
    public String editarUsuario(@PathVariable("usuario") String username,
            Model model,
            HttpSession session) {

        Usuario admin = (Usuario) session.getAttribute("usuarioLogeado");

        if (admin == null || !(admin instanceof Administrador)) {
            return "redirect:/";
        }

        Usuario u = usuarioService.buscarPorUsuario(username);
        model.addAttribute("usuario", u);
        session.setAttribute("usuarioEditar", u);

        if (u instanceof Empleado) {
            return "editarEmpleado";
        }

        if (u instanceof Socio) {
            return "editarSocio";
        }

        if (u instanceof Administrador) {
            return "editarAdmin";
        }

        return "redirect:/";
    }

    @PostMapping("/actualizarDatos")
    public String actualizarDatos(HttpServletRequest request, HttpSession session) throws SQLException {

        Usuario u = (Usuario) session.getAttribute("usuarioEditar");

        if (u == null) {
            return "redirect:/";
        }

        u.setUsuario(request.getParameter("usuario"));
        u.setPassword(request.getParameter("password"));

        if (u instanceof Administrador adm) {
            adm.setNombre(request.getParameter("nombre"));
         
        }

        if (u instanceof Empleado emp) {
            emp.setNombre(request.getParameter("nombre"));
            emp.setTelefono(request.getParameter("telefono"));
            emp.setDireccion(request.getParameter("direccion"));
            emp.setEspecialidad(request.getParameter("especialidad"));
        }

        if (u instanceof Socio socio) {
            socio.setNombre(request.getParameter("nombre"));
            socio.setTelefono(request.getParameter("telefono"));
            socio.setDireccion(request.getParameter("direccion"));
        }
        
        usuarioService.actualizar(u);

        return "redirect:/admin/buscar";
    }

    @PostMapping("/eliminar/{usuario}")
    public String eliminarUsuario(@PathVariable("usuario") String usuario) throws SQLException {

        Usuario u = usuarioService.buscarPorUsuario(usuario);

        if (u != null) {
            usuarioService.eliminar(u);
        }

        return "redirect:/admin/buscar";
    }

    @GetMapping("/registrar")
    public String mostrarFormulario() {
        return "registrarUsuario";
    }

    @PostMapping("/registrar")
    public String registrarUsuario(HttpServletRequest request) throws SQLException {

        String tipo = request.getParameter("tipoUsuario");
        String usuario = request.getParameter("usuario");
        String password = request.getParameter("password");
        String nombre = request.getParameter("nombre");
        String dni = request.getParameter("dni");
        
        if (null != tipo) switch (tipo) {
            case "administrador" -> {
                Administrador admin = new Administrador();
                admin.setUsuario(usuario);
                admin.setPassword(password);
                admin.setNombre(nombre);
                admin.setDni(dni);
                usuarioService.guardar(admin);
                }
            case "empleado" -> {
                String direccion = request.getParameter("direccion");
                String telefono = request.getParameter("telefono");
                String especialidad = request.getParameter("especialidad");
                Empleado emp = new Empleado();
                emp.setUsuario(usuario);
                emp.setPassword(password);
                emp.setDni(dni);
                emp.setNombre(nombre);
                emp.setDireccion(direccion);
                emp.setTelefono(telefono);
                emp.setEspecialidad(especialidad);
                usuarioService.guardar(emp);
                }
            case "socio" -> {
                String direccion = request.getParameter("direccion");
                String telefono = request.getParameter("telefono");
                Socio socio = new Socio();
                socio.setUsuario(usuario);
                socio.setPassword(password);
                socio.setNombre(nombre);
                socio.setDni(dni);
                socio.setDireccion(direccion);
                socio.setTelefono(telefono);
                usuarioService.guardar(socio);
                }
            default -> {
                throw new IllegalArgumentException("Tipo de usuario desconocido: " + tipo);
            }
        }

        return "redirect:/admin";
    }

}
