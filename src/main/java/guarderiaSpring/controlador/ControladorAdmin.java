package guarderiaSpring.controlador;

import guarderiaSpring.dto.BusquedaUsuarioFormDTO;
import guarderiaSpring.dto.RegistroUsuarioDTO;
import guarderiaSpring.dto.RegistroVehiculoDTO;
import guarderiaSpring.modelo.Administrador;
import guarderiaSpring.modelo.Usuario;
import guarderiaSpring.modelo.UsuarioListado;
import guarderiaSpring.servicio.UsuarioService;
import guarderiaSpring.servicio.VehiculoService;
import jakarta.servlet.http.HttpSession;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class ControladorAdmin {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private VehiculoService vehiculoService;

    @GetMapping("/inicio")
    public String inicioAdmin(HttpSession session, Model model) {

        Usuario usuario = (Usuario) session.getAttribute("usuarioLogeado");
        model.addAttribute("usuario", usuario);

        return "administrador/vistaAdministrador";
    }

    @GetMapping("/buscar")
    public String buscarUsuarios(
            @ModelAttribute BusquedaUsuarioFormDTO busqueda,
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
    public String mostrarFormulario(@ModelAttribute("usuario") RegistroUsuarioDTO dto) {
        return "administrador/registrarUsuario";
    }

    @PostMapping("/registrar")
    public String registrarUsuario(@ModelAttribute("usuario") RegistroUsuarioDTO dto) {
        usuarioService.registrarUsuario(dto);
        return "redirect:/admin/inicio";
    }

    @GetMapping("/registrarVehiculo")
    public String registrarVehiculo(@ModelAttribute("vehiculo") RegistroVehiculoDTO vDto) {
        return "administrador/registrarVehiculo";
    }

    @PostMapping("/registrarVehiculo")
    public String confirmarRegistro(@ModelAttribute("vehiculo") RegistroVehiculoDTO vDto) {
        vehiculoService.registrarVehiculo(vDto);
        return "redirect:/admin/inicio";
    }

    @GetMapping("/ver/{usuario}")
    public String verUsuario(@PathVariable("usuario") String nombreUsuario, Model model, HttpSession session) {

        Usuario usuarioLogeado = (Usuario) session.getAttribute("usuarioLogeado");

        if (usuarioLogeado == null || !(usuarioLogeado instanceof Administrador)) {
            return "redirect:/";
        }

        Usuario usuario = usuarioService.buscarPorUsuario(nombreUsuario);
        model.addAttribute("usuario", usuario);

        return "administrador/mostrarDatos";
    }

    @GetMapping("/editar/{usuario}")
    public String editarUsuario(@PathVariable("usuario") String nombreUsuario, Model model, HttpSession session) {

        Usuario usuarioLogeado = (Usuario) session.getAttribute("usuarioLogeado");

        if (usuarioLogeado == null || !(usuarioLogeado instanceof Administrador)) {
            return "redirect:/";
        }

        Usuario usuario = usuarioService.buscarPorUsuario(nombreUsuario);
        model.addAttribute("usuario", usuario);

        return "administrador/editarDatos";
    }

    @PostMapping("/editar")
    public String actualizarUsuario(@ModelAttribute RegistroUsuarioDTO dto) {

        usuarioService.actualizarUsuario(dto);

        return "redirect:/admin";
    }

} //FIN DE CLASE
