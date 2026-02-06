package prog2.guarderiaspring;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
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
/*
    @GetMapping("/buscar")
    public String vistaBuscar(HttpSession session) {

        Usuario u = (Usuario) session.getAttribute("usuarioLogeado");

        if (u == null || !(u instanceof Administrador)) {
            return "redirect:/";
        }

        return "buscarYgestionarUsuario";
    }
*/
    @GetMapping("/buscar")
    public String buscarUsuarios(
            @RequestParam(required = false) String tipoUsuario,
            @RequestParam(required = false) String nombre,
            Model model,
            HttpSession session) {

        Usuario u = (Usuario) session.getAttribute("usuarioLogeado");

        if (u == null || !(u instanceof Administrador)) {
            return "redirect:/";
        }

        List<Usuario> usuarios =
        usuarioService.buscar(tipoUsuario, nombre);
        model.addAttribute("usuarios", usuarios);


        return "buscarYgestionarUsuario";
    }



}
