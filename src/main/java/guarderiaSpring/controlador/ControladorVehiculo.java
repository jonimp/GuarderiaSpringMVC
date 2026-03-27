package guarderiaSpring.controlador;

import guarderiaSpring.dto.RegistroVehiculoDTO;
import guarderiaSpring.modelo.Administrador;
import guarderiaSpring.modelo.Socio;
import guarderiaSpring.modelo.Usuario;
import guarderiaSpring.servicio.UsuarioService;
import guarderiaSpring.servicio.VehiculoService;
import jakarta.servlet.http.HttpSession;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/vehiculo")
public class ControladorVehiculo {

    @Autowired
    private VehiculoService vehiculoService;

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping("/registrarVehiculo")
    public String registrarVehiculo(HttpSession sesion, Model model) {

        Usuario usuario = (Usuario) sesion.getAttribute("usuarioLogeado");
        
        if (usuario == null || !(usuario instanceof Administrador)) {
            return "redirect:/"; 
        }
        List<Socio> socios = usuarioService.obtenerSocios();
        model.addAttribute("socios", socios);
        
        return "administrador/registrarVehiculo";
    }

    @PostMapping("/registrarVehiculo")
    public String confirmarRegistro(@ModelAttribute("vehiculo") RegistroVehiculoDTO vDto) {
        vehiculoService.registrarVehiculo(vDto);
        return "redirect:/admin/inicio";
    }

    
} //FIN DE CLASE
