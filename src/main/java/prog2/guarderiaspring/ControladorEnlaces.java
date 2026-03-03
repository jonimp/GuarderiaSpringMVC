package prog2.guarderiaspring;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ControladorEnlaces {
    
    @GetMapping("/")
    public String mostrarInicio() {
        return "index";
    }

    @GetMapping("/servicios")
    public String servicios() {
        return "servicios";
    }

    @GetMapping("/galeria")
    public String galeria() {
        return "galeria";
    }

    @GetMapping("/contacto")
    public String contacto() {
        return "contacto";
    }
}
