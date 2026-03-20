package guarderiaSpring.dto;

public class BusquedaUsuarioForm {

    private String usuario;
    private String nombre;
    private String tipoUsuario;
    
    
    public BusquedaUsuarioForm() {}

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }
    
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    public String getTipoUsuario() {
        return tipoUsuario;
    }

    public void setTipoUsuario(String tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }

} //FIN DE CLASE
