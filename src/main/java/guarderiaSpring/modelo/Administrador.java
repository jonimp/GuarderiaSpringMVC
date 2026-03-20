package guarderiaSpring.modelo;

import guarderiaSpring.enumerador.TipoUsuario;

public class Administrador extends Usuario {

    public Administrador(String u, String p, String nombre, String dni) {
        setUsuario(u);
        setPassword(p);
        setNombre(nombre);
        setDni(dni);
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
    

}
