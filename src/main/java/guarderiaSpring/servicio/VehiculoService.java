package guarderiaSpring.servicio;

import guarderiaSpring.dto.RegistroVehiculoDTO;
import guarderiaSpring.dto.ZonaGaragesDTO;
import guarderiaSpring.repositorio.GarageDAO;
import guarderiaSpring.repositorio.VehiculoDAO;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import guarderiaSpring.modelo.EspacioGarage;
import guarderiaSpring.modelo.EstadoZona;
import guarderiaSpring.enumerador.TipoVehiculo;
import guarderiaSpring.modelo.Vehiculo;

@Service
public class VehiculoService {

    @Autowired
    private VehiculoDAO vehiculoDAO;

    @Autowired
    private GarageDAO garageDAO;

    public void registrarVehiculo(RegistroVehiculoDTO vDto) {
        Vehiculo vehiculo = new Vehiculo();
        vehiculo.setMatricula(vDto.getMatricula());
        vehiculo.setDniSocio(vDto.getDniSocio());
        vehiculo.setNombre(vDto.getNombre());
        vehiculo.setTipoVehiculo(vDto.getTipoVehiculo());
        vehiculoDAO.agregarVehiculo(vehiculo);
    }

    /*
    public List<Vehiculo> obtenerVehiculos(String dni) {
        return vehiculoDAO.obtenerVehiculosPorDni(dni);
    }
     */
    public EstadoZona calcularEstadoZona(TipoVehiculo tipo) {

        List<EspacioGarage> espacios = garageDAO.obtenerPorTipo(tipo);

        int total = espacios.size();
        int ocupados = (int) espacios.stream()
                .filter(EspacioGarage::isOcupado)
                .count();

        return new EstadoZona(total, ocupados);
    }

    public ZonaGaragesDTO obtenerEstadoZonaCompleto() {

        ZonaGaragesDTO zona = new ZonaGaragesDTO();

        zona.setMotorhomes(garageDAO.obtenerPorTipo(TipoVehiculo.MOTORHOME));
        zona.setCasasRodantes(garageDAO.obtenerPorTipo(TipoVehiculo.CASA_RODANTE));
        zona.setTrailers(garageDAO.obtenerPorTipo(TipoVehiculo.TRAILER));

        zona.setEstadoMotorhome(calcularEstadoZona(TipoVehiculo.MOTORHOME));
        zona.setEstadoCasaRodante(calcularEstadoZona(TipoVehiculo.CASA_RODANTE));
        zona.setEstadoTrailer(calcularEstadoZona(TipoVehiculo.TRAILER));

        return zona;
    }

    public Vehiculo buscarPorMatricula(String matricula) {
        return vehiculoDAO.obtenerPorMatricula(matricula);
    }

    public List<Vehiculo> obtenerVehiculosPorDni(String dni) {
        return vehiculoDAO.obtenerVehiculosPorDni(dni);
    }

    public boolean asignarEspacio(String dni, String matricula) {

        Vehiculo vehiculo = vehiculoDAO.obtenerPorMatricula(matricula);

        if (vehiculo == null) {
            return false;
        }

        if (garageDAO.existeAsignacion(matricula)) {
            return false;
        }

        List<EspacioGarage> libres = garageDAO.obtenerEspaciosLibresPorTipo(vehiculo.getTipoVehiculo());

        if (libres.isEmpty()) {
            return false;
        }

        EspacioGarage espacio = libres.get(0);
        garageDAO.asignarEspacio(espacio.getId(), matricula);

        return true;
    }

    public boolean liberarEspacio(String matricula) {

        // 🔍 Verificar que exista asignación
        if (!garageDAO.existeAsignacion(matricula)) {
            return false;
        }

        // 🔓 Liberar
        garageDAO.liberarEspacio(matricula);

        return true;
    }

} //FIN DE CLASE    
