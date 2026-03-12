package guarderiaSpring.modelo;

import guarderiaSpring.enumerador.TipoUsuario;

public abstract class Usuario {
    
    private String usuario;
    private String password;
    
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
    
    public abstract String getRutaInicio();
    
    public abstract TipoUsuario getTipo();

    
    
}
