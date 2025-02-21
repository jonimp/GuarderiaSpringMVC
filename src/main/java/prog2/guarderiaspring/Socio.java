package prog2.guarderiaspring;

import java.time.LocalDateTime;

public class Socio extends Usuario {
    
    private String DNI, nombre, direccion, telefono, nivelAcceso;
    private LocalDateTime fechaAlta;
        
    public Socio(String u, String p, String DNI, String nombre, String telefono, String direccion, String nivelAcceso){
        setUsuario(u);
        setPassword(p);
        this.nombre = nombre;
        this.direccion = direccion;
        this.telefono = telefono;
        this.DNI = DNI;
        this.nivelAcceso = nivelAcceso;
    }

    @Override
    public String getTipo(){
        return "socio";
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
    
    public String getNivelAcceso(){
        return nivelAcceso;
    }
}
