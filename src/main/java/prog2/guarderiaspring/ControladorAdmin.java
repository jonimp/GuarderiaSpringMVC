package prog2.guarderiaspring;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
    public String verUsuario(@PathVariable ("usuario") String usuario, Model model, HttpSession session) {

        Usuario uSesion = (Usuario) session.getAttribute("usuarioLogeado");

        if (uSesion == null || !(uSesion instanceof Administrador)) {
            return "redirect:/";
        }

        Usuario u = usuarioService.buscarPorUsuario(usuario);

        model.addAttribute("usuario", u);

        return "mostrarDatos";
    }

}
