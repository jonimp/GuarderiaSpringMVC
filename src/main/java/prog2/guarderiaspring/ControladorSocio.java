package prog2.guarderiaspring;

import jakarta.servlet.http.HttpSession;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/socio")
public class ControladorSocio {

    @Autowired
    VehiculoService vehiculoService;

    @GetMapping("/misVehiculos")
    public String verMisVehiculos(HttpSession session, Model model) {

        Socio socio = (Socio) session.getAttribute("usuarioLogeado");

        if (socio == null) {
            return "redirect:/";
        }

        List<Vehiculo> vehiculos = vehiculoService.obtenerVehiculosPorDniSocio(socio.getDni());

        model.addAttribute("vehiculos", vehiculos);

        return "vistaVehiculoSocio";
    }

    @GetMapping("/misEspacios")
    public String verMisEspacios(HttpSession session, Model model) {

        Empleado empleado = (Empleado) session.getAttribute("usuarioLogeado");

        if (empleado == null) {
            return "redirect:/";
        }

        List<EspacioGarage> espacios = vehiculoService.obtenerEspaciosPorEmpleado(empleado.getDni());

        model.addAttribute("espacios", espacios);

        return "vistaEspaciosEmpleado";
    }

}
