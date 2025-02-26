package prog2.guarderiaspring;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class Controlador {

    @Autowired
    private UsuariosDAO usDAO;
    
   
    @GetMapping("/")
    public String mostrarInicio() {
        return "index";
    }

    @PostMapping("/acceso")
    public String usuarioLogeado(@RequestParam String usuario, @RequestParam String password, Model model) {

        Usuario user = usDAO.validarUsuario(usuario, password);
        if (user != null) {
            if (user instanceof Administrador) {
                return "vistaAdministrador";
            } else if (user instanceof Empleado) {
                return "vistaEmpleado";
            } else if (user instanceof Socio) {
                return "vistaSocio";
            } else {
                model.addAttribute("error", "Tipo de usuario desconocido");
                return "index";
            }
        } else {
            model.addAttribute("error", "Usuario o contraseña incorrectos");
            return "index";
        }
    }
    
    @GetMapping("/logout")
    public String logout(HttpSession session, Model model) {
        session.invalidate();
        return "index";
    }
    
    @GetMapping(/buscar)

}
