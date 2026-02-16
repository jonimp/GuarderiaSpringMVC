package prog2.guarderiaspring;

public class UsuarioListado {

    private String usuario;
    private String nombre;
    private String tipo;

    public UsuarioListado(String usuario, String nombre, String tipo) {
        this.usuario = usuario;
        this.nombre = nombre;
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
