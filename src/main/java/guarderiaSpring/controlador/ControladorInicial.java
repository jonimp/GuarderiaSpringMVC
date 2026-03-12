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
    public String usuarioLogeado(@ModelAttribute LoginForm login, Model model) {
        
        Usuario usuario = usuarioService.validarUsuario(login.getUsuario(), login.getPassword());
        
        if (usuario != null) {
            model.addAttribute("usuarioLogeado", usuario); // Mas tarde implementar Spring Security
            return "redirect:" + usuario.getRutaInicio();  
        } 
        else {
            model.addAttribute("error", "Usuario o contraseña incorrectos");
            return "autenticacion/index";
        }
    }

} //FIN DE CLASE
