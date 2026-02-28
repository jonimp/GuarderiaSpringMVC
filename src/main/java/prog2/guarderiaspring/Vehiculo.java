package prog2.guarderiaspring;

public class Vehiculo {
    
    private String matricula, dniSocio, nombre;
    private TipoVehiculo tipoVehiculo;
    
    public Vehiculo(String matricula, String dniSocio, String nombre, TipoVehiculo tipoVehiculo){
        this.matricula = matricula;
        this.dniSocio = dniSocio;
        this.nombre = nombre;
        this.tipoVehiculo = tipoVehiculo;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public String getDniSocio() {
        return dniSocio;
    }

    public void setDniSocio(String dniSocio) {
        this.dniSocio = dniSocio;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public TipoVehiculo getTipoVehiculo() {
        return tipoVehiculo;
    }

    public void setTipoVehiculo(TipoVehiculo tipoVehiculo) {
        this.tipoVehiculo = tipoVehiculo;
    }
    
    
}
