package prog2.guarderiaspring;

import java.sql.SQLException;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

@Service
public class VehiculoService {

    @Autowired
    private VehiculoDAO vehiculoDAO;

    @Autowired
    private GarageDAO garageDAO;

    public void agregarVehiculo(String matricula, String dni, String nombre, TipoVehiculo tipoVehiculo) throws SQLException {

        Vehiculo v = new Vehiculo(matricula, dni, nombre, tipoVehiculo);
        vehiculoDAO.agregarVehiculo(v);
    }

    public List<Vehiculo> obtenerVehiculos(String dni) {
        return vehiculoDAO.obtenerVehiculosPorDni(dni);
    }

    public EstadoZona calcularEstadoZona(TipoVehiculo tipo) {

        List<EspacioGarage> espacios = garageDAO.obtenerPorTipo(tipo);

        int total = espacios.size();
        int ocupados = (int) espacios.stream()
                .filter(EspacioGarage::isOcupado)
                .count();

        return new EstadoZona(total, ocupados);
    }

    public Vehiculo buscarPorMatricula(String matricula) {
        return vehiculoDAO.obtenerPorMatricula(matricula);
    }

    public List<EspacioGarage> obtenerEspaciosLibresPorTipo(TipoVehiculo tipo) {
        return garageDAO.obtenerEspaciosLibresPorTipo(tipo);
    }

    public List<Vehiculo> obtenerVehiculosPorDniSocio(String dni) {
        return vehiculoDAO.obtenerVehiculosPorDni(dni);
    }

    public boolean asignarEspacio(Vehiculo vehiculo) {

        if (garageDAO.existeAsignacion(vehiculo.getMatricula())) {
            return false;
        }

        List<EspacioGarage> libres
                = garageDAO.obtenerEspaciosLibresPorTipo(
                        vehiculo.getTipoVehiculo());

        if (libres.isEmpty()) {
            return false;
        }

        EspacioGarage espacio = libres.get(0);

        garageDAO.asignarEspacio(
                espacio.getId(),
                vehiculo.getMatricula()
        );

        return true;
    }

} //FIN DE CLASE    
/*
    

    public void eliminarVehiculo(String matricula) {
        vehiculoDAO.eliminarVehiculo(matricula);
    }
 */
