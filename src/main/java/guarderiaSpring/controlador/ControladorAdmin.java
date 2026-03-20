package guarderiaSpring.controlador;

import guarderiaSpring.dto.BusquedaUsuarioForm;
import guarderiaSpring.dto.RegistroUsuario;
import guarderiaSpring.modelo.Administrador;
import guarderiaSpring.modelo.Usuario;
import guarderiaSpring.modelo.UsuarioListado;
import guarderiaSpring.servicio.UsuarioService;
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
@RequestMapping("/admin")
public class ControladorAdmin {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping("/inicio")
    public String inicioAdmin(HttpSession session, Model model) {

        Usuario usuario = (Usuario) session.getAttribute("usuarioLogeado");
        model.addAttribute("usuario", usuario);

        return "administrador/vistaAdministrador";
    }

    @GetMapping("/buscar")
    public String buscarUsuarios(
            @ModelAttribute BusquedaUsuarioForm busqueda,
            Model model,
            HttpSession session) {

        Usuario usuario = (Usuario) session.getAttribute("usuarioLogeado");

        if (usuario == null || !(usuario instanceof Administrador)) {
            return "redirect:/";
        }

        List<UsuarioListado> lista = usuarioService.listarUsuarios(busqueda);
        model.addAttribute("usuarios", lista);

        return "administrador/buscarYgestionarUsuario";
    }

    @GetMapping("/registrar")
    public String mostrarFormulario(Model model) {
        model.addAttribute("usuario", new RegistroUsuario());
        return "administrador/registrarUsuario";
    }
    
    @PostMapping("/registrar")
    public String registrarUsuario(@ModelAttribute("usuario") RegistroUsuario dto){
        usuarioService.registrarUsuario(dto);
        return "redirect:/admin";
    }

}
