package prog2.guarderiaspring;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import java.sql.SQLException;
import java.util.List;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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

        vehiculoService.agregarVehiculo(matricula, dniSocio, nombre, tipoVehiculo);

        return "redirect:/admin/registrarVehiculo";
    }

    @GetMapping("/gestionVehiculo")
    public String gestionarVehiculo(
            @RequestParam(value = "dni", required = false) String dni,
            Model model) {

        List<Socio> socios = usuarioService.obtenerSocios();
        model.addAttribute("socios", socios);

        EstadoZona estadoMotorhome = vehiculoService.calcularEstadoZona(TipoVehiculo.MOTORHOME);
        EstadoZona estadoCasaRodante = vehiculoService.calcularEstadoZona(TipoVehiculo.CASA_RODANTE);
        EstadoZona estadoTrailer = vehiculoService.calcularEstadoZona(TipoVehiculo.TRAILER);
        model.addAttribute("estadoMotorhome", estadoMotorhome);
        model.addAttribute("estadoCasaRodante", estadoCasaRodante);
        model.addAttribute("estadoTrailer", estadoTrailer);

        if (dni != null) {
            List<Vehiculo> vehiculos = vehiculoService.obtenerVehiculos(dni);
            model.addAttribute("vehiculos", vehiculos);
            model.addAttribute("dniSeleccionado", dni);
        }

        return "zonaGarages";
    }

  
    @GetMapping("/asignarEspacio")
    public String mostrarAsignacion(
            @RequestParam("matricula") String matricula,
            @RequestParam("dni") String dni,
            Model model) {

        Vehiculo vehiculo = vehiculoService.buscarPorMatricula(matricula);

        if (vehiculo == null) {
            model.addAttribute("error", "Vehículo no encontrado");
            return "vistaError";
        }

        model.addAttribute("vehiculo", vehiculo);
        model.addAttribute("dni", dni);

        return "asignarEspacio";
    }

    @GetMapping("/zonasGarage")
    public String zonasGarage(@RequestParam(value = "dni", required = false) String dni,
            Model model) {

        // 1️⃣ Cargar estado general
        model.addAttribute("motorhomes", vehiculoService.obtenerEspaciosLibresPorTipo(TipoVehiculo.MOTORHOME));
        model.addAttribute("casaRodante", vehiculoService.obtenerEspaciosLibresPorTipo(TipoVehiculo.CASA_RODANTE));
        model.addAttribute("trailer", vehiculoService.obtenerEspaciosLibresPorTipo(TipoVehiculo.TRAILER));

        // 2️⃣ Si hay DNI seleccionado
        if (dni != null && !dni.isEmpty()) {

            List<Vehiculo> vehiculos = vehiculoService.obtenerVehiculosPorDniSocio(dni);

            model.addAttribute("vehiculos", vehiculos);
            model.addAttribute("dniSeleccionado", dni);
        }

        return "zonaGarages";
    }

    @PostMapping("/confirmarAsignacion")
    public String confirmarAsignacion(@RequestParam("matricula") String matricula,
            RedirectAttributes redirectAttributes) {

        Vehiculo vehiculo = vehiculoService.buscarPorMatricula(matricula);

        if (vehiculo == null) {
            redirectAttributes.addFlashAttribute("error", "Vehículo no encontrado");
            return "redirect:/admin/zonaGarage";
        }

        boolean asignado = vehiculoService.asignarEspacio(vehiculo);

        if (!asignado) {
            redirectAttributes.addFlashAttribute("error", "No hay espacios disponibles");
        } else {
            redirectAttributes.addFlashAttribute("mensaje", "Espacio asignado correctamente");
        }

        return "redirect:/admin/gestionVehiculo";
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
