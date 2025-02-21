package prog2.guarderiaspring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class Controlador {
    
    @Autowired
    private ServicioUsuario ServicioUsuario;
    
    @GetMapping("/")
    public String mostrarInicio() {
        return "index";
    }
    
    @PostMapping("/acceso")
    public String usuarioLogeado(@RequestParam String usuario, 
                                @RequestParam String contrasena, 
                                Model model) {
        Usuario user = ServicioUsuario.validarUsuario(usuario, contrasena);
        
        if (user != null) {
            switch (user.getTipo()) {
                case "administrador":
                    return "redirect:/admin";
                case "empleado":
                    return "redirect:/empleado";
                case "socio":
                    return "redirect:/socio";
                default:
                    model.addAttribute("error", "Tipo de usuario desconocido");
                    return "index";
            }
        } else {
            model.addAttribute("error", "Usuario o contraseña incorrectos");
            return "index";
        }
        
       // return "opcion";
    }
     

    
}
