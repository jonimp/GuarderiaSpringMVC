package guarderiaSpring.modelo;

import guarderiaSpring.enumerador.TipoUsuario;

public class Administrador extends Usuario {

    private String nombre, dni;

    public Administrador(String u, String p, String nombre, String dni) {
        setUsuario(u);
        setPassword(p);
        this.nombre = nombre;
        this.dni = dni;
    }

    public Administrador(){};
    
    @Override
    public TipoUsuario getTipo() {
        return TipoUsuario.ADMINISTRADOR;
    }
    
    @Override
    public String getRutaInicio(){
        return "/admin";
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }
    
    

}
