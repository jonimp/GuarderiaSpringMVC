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

    @Autowired
    private GarageDAO garageDAO;

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

        List<EspacioGarage> motorhomes = garageDAO.obtenerPorTipo(TipoVehiculo.MOTORHOME);
        model.addAttribute("motorhomes", motorhomes);
        List<EspacioGarage> casasRodantes = garageDAO.obtenerPorTipo(TipoVehiculo.CASA_RODANTE);
        model.addAttribute("casasRodantes", casasRodantes);
        List<EspacioGarage> trailers = garageDAO.obtenerPorTipo(TipoVehiculo.TRAILER);
        model.addAttribute("trailers", trailers);

        model.addAttribute("estadoMotorhome", estadoMotorhome);
        model.addAttribute("estadoCasaRodante", estadoCasaRodante);
        model.addAttribute("estadoTrailer", estadoTrailer);

        if (dni != null) {
            List<Vehiculo> vehiculos = vehiculoService.obtenerVehiculos(dni);
            model.addAttribute("vehiculos", vehiculos);
            model.addAttribute("dniSeleccionado", dni);
        }

        List<EspacioGarage> espaciosOcupados = vehiculoService.obtenerEspaciosOcupados();
        model.addAttribute("espaciosOcupados", espaciosOcupados);

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

        model.addAttribute("motorhomes", vehiculoService.obtenerEspaciosLibresPorTipo(TipoVehiculo.MOTORHOME));
        model.addAttribute("casaRodante", vehiculoService.obtenerEspaciosLibresPorTipo(TipoVehiculo.CASA_RODANTE));
        model.addAttribute("trailer", vehiculoService.obtenerEspaciosLibresPorTipo(TipoVehiculo.TRAILER));

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

    @PostMapping("/liberarEspacio")
    public String liberarEspacio(
            @RequestParam("matricula") String matricula,
            RedirectAttributes redirectAttributes) {

        boolean liberado = vehiculoService.liberarEspacio(matricula);

        if (!liberado) {
            redirectAttributes.addFlashAttribute("error",
                    "El vehículo no tiene espacio asignado");
        } else {
            redirectAttributes.addFlashAttribute("mensaje",
                    "Espacio liberado correctamente");
        }

        return "redirect:/admin/gestionVehiculo";
    }

    @GetMapping("/asignarEmpleado")
    public String vistaAsignacion(Model model) {

        model.addAttribute("espacios", vehiculoService.obtenerTodosLosEspacios());
        model.addAttribute("empleados", usuarioService.obtenerEmpleados());

        return "asignarEmpleado";
    }

    @PostMapping("/asignarEmpleado")
    public String asignarEmpleado(
            @RequestParam("idEspacio") int idEspacio,
            @RequestParam("dniEmpleado") String dniEmpleado) {

        vehiculoService.asignarEmpleado(idEspacio, dniEmpleado);

        return "redirect:/admin";
    }

    @PostMapping("/desasignarEmpleado")
    public String desasignarEmpleado(@RequestParam("idEspacio") int idEspacio) {

        vehiculoService.desasignarEmpleado(idEspacio);

        return "redirect:/admin/asignarEmpleado";
    }

} //FIN DE LA CLASE

