package prog2.guarderiaspring;

import jakarta.servlet.http.HttpSession;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/empleado")
public class ControladorEmpleado {

    @Autowired
    VehiculoService vehiculoService;


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
