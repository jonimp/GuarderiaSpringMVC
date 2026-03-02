package prog2.guarderiaspring;

public class EspacioGarage {

    private int id;
    private TipoVehiculo tipoZona;
    private int numeroEspacio;
    private boolean ocupado;
    private String matriculaVehiculo;

    public EspacioGarage(int id, TipoVehiculo tipoZona, int numeroEspacio, boolean ocupado, String matriculaVehiculo){
        this.id = id;
        this.tipoZona = tipoZona;
        this.numeroEspacio = numeroEspacio;
        this.ocupado = ocupado;
        this.matriculaVehiculo = matriculaVehiculo;
    }
    
    public EspacioGarage(){}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public TipoVehiculo getTipoZona() {
        return tipoZona;
    }

    public void setTipoZona(TipoVehiculo tipoZona) {
        this.tipoZona = tipoZona;
    }

    public int getNumeroEspacio() {
        return numeroEspacio;
    }

    public void setNumeroEspacio(int numeroEspacio) {
        this.numeroEspacio = numeroEspacio;
    }

    public boolean isOcupado() {
        return ocupado;
    }

    public void setOcupado(boolean ocupado) {
        this.ocupado = ocupado;
    }

    public String getMatriculaVehiculo() {
        return matriculaVehiculo;
    }

    public void setMatriculaVehiculo(String matriculaVehiculo) {
        this.matriculaVehiculo = matriculaVehiculo;
    }
    
    
    
    
}
