package prog2.guarderiaspring;

public class Empleado extends Usuario {
    
    private String dni, nombre, direccion, telefono, especialidad;
    
    public Empleado(String u, String p, String nombre, String dni, String direccion, String telefono, String especialidad){
        setUsuario(u);
        setPassword(p);
        this.nombre = nombre;
        this.dni = dni;
        this.direccion = direccion;
        this.telefono = telefono;
        this.especialidad = especialidad;
    }
    
    public Empleado(){};
    
    @Override
    public String getTipo() {
      return "empleado";
    }

    
    public String getDni() {
        return dni;
    }

       
    public void setDni(String dni) {
        this.dni = dni;
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
