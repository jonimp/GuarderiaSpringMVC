package guarderiaSpring.dto;

import guarderiaSpring.modelo.EspacioGarage;
import guarderiaSpring.modelo.EstadoZona;
import java.util.List;

public class ZonaGaragesDTO {
    
    private List<EspacioGarage> motorhomes;
    private List<EspacioGarage> casasRodantes;
    private List<EspacioGarage> trailers;

    private EstadoZona estadoMotorhome;
    private EstadoZona estadoCasaRodante;
    private EstadoZona estadoTrailer;
    
    public ZonaGaragesDTO () {}

    public List<EspacioGarage> getMotorhomes() {
        return motorhomes;
    }

    public void setMotorhomes(List<EspacioGarage> motorhomes) {
        this.motorhomes = motorhomes;
    }

    public List<EspacioGarage> getCasasRodantes() {
        return casasRodantes;
    }

    public void setCasasRodantes(List<EspacioGarage> casasRodantes) {
        this.casasRodantes = casasRodantes;
    }

    public List<EspacioGarage> getTrailers() {
        return trailers;
    }

    public void setTrailers(List<EspacioGarage> trailers) {
        this.trailers = trailers;
    }

    public EstadoZona getEstadoMotorhome() {
        return estadoMotorhome;
    }

    public void setEstadoMotorhome(EstadoZona estadoMotorhome) {
        this.estadoMotorhome = estadoMotorhome;
    }

    public EstadoZona getEstadoCasaRodante() {
        return estadoCasaRodante;
    }

    public void setEstadoCasaRodante(EstadoZona estadoCasaRodante) {
        this.estadoCasaRodante = estadoCasaRodante;
    }

    public EstadoZona getEstadoTrailer() {
        return estadoTrailer;
    }

    public void setEstadoTrailer(EstadoZona estadoTrailer) {
        this.estadoTrailer = estadoTrailer;
    }
    
    
}
