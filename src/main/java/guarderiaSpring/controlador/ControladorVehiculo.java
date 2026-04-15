package guarderiaSpring.controlador;

import guarderiaSpring.dto.RegistroVehiculoDTO;
import guarderiaSpring.dto.ZonaGaragesDTO;
import guarderiaSpring.enumerador.TipoVehiculo;
import guarderiaSpring.modelo.Administrador;
import guarderiaSpring.modelo.EspacioGarage;
import guarderiaSpring.modelo.EstadoZona;
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
import org.springframework.web.bind.annotation.RequestParam;

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

    @GetMapping("/gestionVehiculo")
    public String gestionarVehiculo(@RequestParam(name = "dni", required = false) String dni, HttpSession sesion, Model model) {

        Usuario usuario = (Usuario) sesion.getAttribute("usuarioLogeado");

        if (usuario == null || !(usuario instanceof Administrador)) {
            return "redirect:/";
        }

        ZonaGaragesDTO zona = vehiculoService.obtenerEstadoZonaCompleto();

        model.addAttribute("motorhomes", zona.getMotorhomes());
        model.addAttribute("casasRodantes", zona.getCasasRodantes());
        model.addAttribute("trailers", zona.getTrailers());

        model.addAttribute("estadoMotorhome", zona.getEstadoMotorhome());
        model.addAttribute("estadoCasaRodante", zona.getEstadoCasaRodante());
        model.addAttribute("estadoTrailer", zona.getEstadoTrailer());

        model.addAttribute("socios", usuarioService.obtenerSocios());
        model.addAttribute("zonas", vehiculoService.obtenerEstadoZonaCompleto());

        if (dni != null && !dni.isEmpty()) {
            model.addAttribute("vehiculos", vehiculoService.obtenerVehiculosPorDni(dni));
            model.addAttribute("dniSeleccionado", dni);
        }

        return "administrador/zonaGarages";
    }

    @GetMapping("/asignarEspacio")
    public String guardarVehiculo(@RequestParam("dni") String dni, @RequestParam("matricula") String matricula, HttpSession sesion) {

        Usuario usuario = (Usuario) sesion.getAttribute("usuarioLogeado");

        if (usuario == null || !(usuario instanceof Administrador)) {
            return "redirect:/";
        }

        vehiculoService.asignarEspacio(dni, matricula);

        return "redirect:/vehiculo/gestionVehiculo?dni=" + dni;
    }

    @PostMapping("/liberarEspacio")
    public String liberarEspacio(
            @RequestParam String matricula,
            HttpSession sesion) {

        Usuario usuario = (Usuario) sesion.getAttribute("usuarioLogeado");

        if (usuario == null || !(usuario instanceof Administrador)) {
            return "redirect:/";
        }

        vehiculoService.liberarEspacio(matricula);

        return "redirect:/vehiculo/gestionVehiculo";
    }

} //FIN DE CLASE
