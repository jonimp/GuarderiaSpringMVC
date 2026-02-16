package prog2.guarderiaspring;

public class Administrador extends Usuario {

    private String nombre;

    public Administrador(String u, String p, String nombre) {
        setUsuario(u);
        setPassword(p);
        this.nombre = nombre;
    }

    @Override
    public String getTipo() {
        return "administrador";
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

}
