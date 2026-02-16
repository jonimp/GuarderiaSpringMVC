package prog2.guarderiaspring;

public class Empleado extends Usuario {
    
    private String codigo, nombre, direccion, telefono, especialidad;
    
    public Empleado(String u, String p, String codigo, String nombre, String direccion, String telefono, String especialidad){
        setUsuario(u);
        setPassword(p);
        this.codigo = codigo;
        this.nombre = nombre;
        this.direccion = direccion;
        this.telefono = telefono;
        this.especialidad = especialidad;
    }
    
    
    
    @Override
    public String getTipo() {
      return "empleado";
    }

    
    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
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

    public String getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
    }
  
    
   
}
