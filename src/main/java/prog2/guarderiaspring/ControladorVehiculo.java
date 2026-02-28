package prog2.guarderiaspring;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import jakarta.servlet.http.HttpSession;
import java.sql.SQLException;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class ControladorVehiculo {

    @Autowired
    private VehiculoService vehiculoService;

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping("/registrarVehiculo")
    public String mostrarFormularioRegistrarVehiculo(Model model) {
        List<Socio> socios = usuarioService.obtenerSocios();

        model.addAttribute("socios", socios);

        return "registrarVehiculo";
    }

    @PostMapping("/confirmarRegistro")
    public String guardarVehiculo(
            @RequestParam("matricula") String matricula,
            @RequestParam("dniSocio") String dniSocio,
            @RequestParam("nombre") String nombre,
            @RequestParam("tipoVehiculo") TipoVehiculo tipoVehiculo
    ) throws SQLException {

        System.out.println("ENTRO AL METODO");
        System.out.println(matricula + " - " + dniSocio + " - " + nombre + " - " + tipoVehiculo);

        vehiculoService.agregarVehiculo(matricula, dniSocio, nombre, tipoVehiculo);

        return "redirect:/admin/registrarVehiculo";
    }

} //FIN DE LA CLASE

/*
    
    
    
    @GetMapping
    public String listarVehiculos(HttpSession session, Model model) {

        Usuario usuario = (Usuario) session.getAttribute("usuario");

        if (usuario == null) {
            return "redirect:/login";
        }

        model.addAttribute("vehiculos",
                vehiculoService.obtenerVehiculosSocio(usuario.getDni()));

        return "socio/listaVehiculos";
    }

    @GetMapping("/nuevo")
    public String mostrarFormulario() {
        return "socio/nuevoVehiculo";
    }

    @PostMapping("/guardar")
    public String guardarVehiculo(
            @RequestParam String matricula,
            @RequestParam String nombre,
            @RequestParam String tipoVehiculo,
            HttpSession session
    ) {

        Usuario usuario = (Usuario) session.getAttribute("usuario");

        if (usuario == null) {
            return "redirect:/login";
        }

        vehiculoService.agregarVehiculo(
                matricula,
                usuario.getDni(),
                nombre,
                tipoVehiculo
        );

        return "redirect:/socio/vehiculos";
    }

    @GetMapping("/eliminar")
    public String eliminarVehiculo(@RequestParam String matricula) {

        vehiculoService.eliminarVehiculo(matricula);

        return "redirect:/socio/vehiculos";
    }


 */
