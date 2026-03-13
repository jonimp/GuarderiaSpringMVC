package guarderiaSpring.controlador;

import guarderiaSpring.servicio.UsuarioService;
import guarderiaSpring.dto.LoginForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import guarderiaSpring.modelo.Usuario;
import jakarta.servlet.http.HttpSession;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@SessionAttributes("usuarioLogeado")
public class ControladorInicial {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping("/")
    public String mostrarLogin() {
        return "autenticacion/index";
    }

    @PostMapping("/acceso")
    public String usuarioLogeado(@ModelAttribute LoginForm login, HttpSession session, Model model) {

        Usuario usuario = usuarioService.validarUsuario(login.getUsuario(), login.getPassword());

        if (usuario != null) {
            session.setAttribute("usuarioLogeado", usuario); // Mas tarde implementar Spring Security
            return "redirect:" + usuario.getRutaInicio() + "/inicio";
        } else {
            model.addAttribute("error", "Usuario o contraseña incorrectos");
            return "autenticacion/index";
        }
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.removeAttribute("usuarioLogeado");
        session.invalidate();  
        return "redirect:/";
    }

} //FIN DE CLASE
