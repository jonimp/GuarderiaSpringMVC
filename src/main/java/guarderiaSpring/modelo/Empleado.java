package guarderiaSpring.modelo;

import guarderiaSpring.enumerador.TipoUsuario;

public class Empleado extends Usuario {
    
    private String direccion, telefono, especialidad;
    
    public Empleado(String u, String p, String nombre, String dni, String direccion, String telefono, String especialidad){
        setUsuario(u);
        setPassword(p);
        setNombre(nombre);
        setDni(dni);
        this.direccion = direccion;
        this.telefono = telefono;
        this.especialidad = especialidad;
    }
    
    public Empleado(){};
    
    @Override
    public TipoUsuario getTipo() {
      return TipoUsuario.EMPLEADO;
    }

    @Override
    public String getRutaInicio(){
        return "/empleado";
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
