package guarderiaSpring.modelo;

public class UsuarioListado {

    private String usuario;
    private String nombre;
    private String tipo;

    public UsuarioListado(String usuario, String nombre, String tipo) {
        this.usuario = usuario;
        this.nombre = nombre;
        this.tipo = tipo;
    }

    public UsuarioListado () {}
    
    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getUsuario() {
        return usuario;
    }

    public String getNombre() {
        return nombre;
    }

    public String getTipo() {
        return tipo;
    }
}