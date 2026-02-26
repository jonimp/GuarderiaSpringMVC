package prog2.guarderiaspring;

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
    public String getTipo() {
        return "administrador";
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
