package guarderiaSpring.modelo;

import guarderiaSpring.enumerador.TipoUsuario;

public class Socio extends Usuario {

    private String direccion, telefono;

    public Socio(String u, String p, String nombre, String dni, String telefono, String direccion) {
        setUsuario(u);
        setPassword(p);
        setNombre(nombre);
        setDni(dni);
        this.telefono = telefono;
        this.direccion = direccion;
    }

    public Socio(){};
    
    @Override
    public TipoUsuario getTipo() {
        return TipoUsuario.SOCIO;
    }

    @Override
    public String getRutaInicio(){
        return "/socio";
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


}
