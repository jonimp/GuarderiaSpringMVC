package guarderiaSpring.modelo;

import guarderiaSpring.enumerador.TipoUsuario;

public abstract class Usuario {
    
    private String usuario;
    private String password;
    private String nombre;
    private String dni;
    
    public String getUsuario(){
        return usuario;
    }
    
    public void setUsuario(String usuario){
        this.usuario = usuario;
    }
    
    public String getPassword(){
        return password;
    }
    
    public void setPassword(String password){
        this.password = password;
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
   
    
    
    public abstract String getRutaInicio();
    
    public abstract TipoUsuario getTipo();

    
    
}
