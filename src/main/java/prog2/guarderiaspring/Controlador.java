package prog2.guarderiaspring;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class Controlador {

    @Autowired
    private UsuarioDAO usDAO;

    @GetMapping("/")
    public String mostrarInicio() {
        return "index";
    }

    @PostMapping("/acceso")
    public String usuarioLogeado(
            @RequestParam("usuario") String usuario,
            @RequestParam("password") String password,
            Model model,
            HttpSession session) {

        Usuario user = usDAO.validarUsuario(usuario, password);
        if (user != null) {

            session.setAttribute("usuarioLogeado", user);

            if (user instanceof Administrador) {
                return "redirect:/admin";
            } else if (user instanceof Empleado) {
                return "redirect:/empleado";
            } else if (user instanceof Socio) {
                return "redirect:/socio";
            } else {
                model.addAttribute("error", "Tipo de usuario desconocido");
                return "index";
            }
        } else {
            model.addAttribute("error", "Usuario o contraseña incorrectos");
            return "index";
        }
    }

    @GetMapping("/empleado")
    public String vistaEmpleado(HttpSession session, HttpServletResponse response) {

        Usuario u = (Usuario) session.getAttribute("usuarioLogeado");

        if (u == null || !(u instanceof Empleado)) {
            return "redirect:/";
        }

        return "vistaEmpleado";
    }

    @GetMapping("/socio")
    public String vistaSocio(HttpSession session, HttpServletResponse response) {

        Usuario u = (Usuario) session.getAttribute("usuarioLogeado");

        if (u == null || !(u instanceof Socio)) {
            return "redirect:/";
        }

        return "vistaSocio";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();   // destruye la sesión
        return "redirect:/";
    }

    @ModelAttribute
    public void noCache(HttpServletResponse response) {
        response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
        response.setHeader("Pragma", "no-cache");
        response.setDateHeader("Expires", 0);
    }

    @GetMapping("/consultarDatos")
    public String consultarDatos(HttpSession session, Model model) {

        Usuario usuario = (Usuario) session.getAttribute("usuarioLogeado");

        if (usuario == null) {
            return "redirect:/";
        }

        model.addAttribute("usuario", usuario);

        return "mostrarDatos";
    }

    @GetMapping("/panel")
    public String volverPanel(HttpSession session) {

        Usuario usuario = (Usuario) session.getAttribute("usuarioLogeado");

        if (usuario == null) {
            return "redirect:/";
        }

        if (usuario instanceof Administrador) {
            return "buscarYgestionarUsuario";
        }

        if (usuario instanceof Empleado) {
            return "vistaEmpleado";
        }

        if (usuario instanceof Socio) {
            return "vistaSocio";
        }

        return "redirect:/";
    }

    @GetMapping("/contacto")
    public String mostrarContacto() {
        return "contacto";
    }

}
