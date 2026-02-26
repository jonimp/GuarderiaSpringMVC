package prog2.guarderiaspring;

import java.time.LocalDateTime;

public class Socio extends Usuario {

    private String dni, nombre, direccion, telefono;
    private LocalDateTime fechaAlta;

    public Socio(String u, String p, String nombre, String dni, String telefono, String direccion) {
        setUsuario(u);
        setPassword(p);
        this.nombre = nombre;
        this.dni = dni;
        this.telefono = telefono;
        this.direccion = direccion;
        this.fechaAlta = LocalDateTime.now();
    }

    public Socio(){};
    
    @Override
    public String getTipo() {
        return "socio";
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
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
