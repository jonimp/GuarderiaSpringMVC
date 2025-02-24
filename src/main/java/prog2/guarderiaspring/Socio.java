package prog2.guarderiaspring;

import java.time.LocalDateTime;

public class Socio extends Usuario {
    
    private String DNI, nombre, direccion, telefono;
    private final String nivelAcceso = "socio";
    private LocalDateTime fechaAlta;
        
    public Socio(String u, String p, String DNI, String nombre, String telefono, String direccion){
        setUsuario(u);
        setPassword(p);
        this.DNI = DNI;
        this.nombre = nombre;
        this.telefono = telefono;
        this.direccion = direccion;     
        this.fechaAlta = LocalDateTime.now();
    }

    @Override
    public String getNivelAcceso(){
        return nivelAcceso;
    }
    
    public String getDNI() {
        return DNI;
    }

    public void setDNI(String DNI) {
        this.DNI = DNI;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public LocalDateTime getFechaAlta() {
        return fechaAlta;
    }

    public void setFechaAlta(LocalDateTime fechaAlta) {
        this.fechaAlta = fechaAlta;
    }
 
}
