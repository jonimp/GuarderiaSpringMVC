package prog2.guarderiaspring;

import java.sql.SQLException;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

@Service
public class VehiculoService {

    @Autowired
    private VehiculoDAO vehiculoDAO;
    
    public void agregarVehiculo(String matricula, String dni, String nombre, TipoVehiculo tipoVehiculo) throws SQLException {

        Vehiculo v = new Vehiculo(matricula, dni, nombre, tipoVehiculo);
        vehiculoDAO.agregarVehiculo(v);
    }
    
    
    
/*
    public List<Vehiculo> obtenerVehiculosSocio(String dni) {
        return vehiculoDAO.obtenerVehiculosPorDni(dni);
    }


    public void eliminarVehiculo(String matricula) {
        vehiculoDAO.eliminarVehiculo(matricula);
    }
*/
}