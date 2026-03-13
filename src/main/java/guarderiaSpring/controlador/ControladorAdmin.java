package guarderiaSpring.controlador;

import guarderiaSpring.modelo.Usuario;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class ControladorAdmin {

    @GetMapping("/inicio")
    public String inicioAdmin(HttpSession session, Model model) {

        Usuario usuario = (Usuario) session.getAttribute("usuarioLogeado");
        model.addAttribute("usuario", usuario);

        return "administrador/vistaAdministrador";
    }

}

