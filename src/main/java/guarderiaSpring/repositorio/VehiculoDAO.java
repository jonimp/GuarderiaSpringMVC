package guarderiaSpring.repositorio;

import guarderiaSpring.enumerador.TipoVehiculo;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import guarderiaSpring.modelo.Vehiculo;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;

@Repository
public class VehiculoDAO {

@Autowired
private JdbcTemplate jdbcTemplate;

public void agregarVehiculo(Vehiculo v) {

    String sql = "INSERT INTO vehiculos (matricula, dniSocio, nombre, tipoVehiculo) VALUES (?, ?, ?, ?)";
        
        jdbcTemplate.update(sql,
        v.getMatricula(),
        v.getDniSocio(),
        v.getNombre(),
        v.getTipoVehiculo().name().toLowerCase()
        );
        
    }
    
    public Vehiculo obtenerPorMatricula(String matricula) {
        
        String sql = "SELECT matricula, dniSocio, nombre, tipoVehiculo FROM vehiculos WHERE matricula = ?";
        
        return jdbcTemplate.queryForObject(
                sql,
                (rs, rowNum) -> {
                   Vehiculo vehiculo = new Vehiculo(); 
                   vehiculo.setMatricula(rs.getString("matricula"));
                   vehiculo.setNombre((rs.getString("nombre")));
                   vehiculo.setDniSocio(rs.getString("dniSocio"));
                   vehiculo.setTipoVehiculo(TipoVehiculo.valueOf(rs.getString("tipoVehiculo").toUpperCase()));
                   return vehiculo;
                },
                matricula
        );
    }
    
    
    public List<Vehiculo> obtenerVehiculosPorDni(String dni){
        
        String sql = "SELECT matricula, dniSocio, nombre, tipoVehiculo FROM vehiculos WHERE dniSocio = ?";
        
        return jdbcTemplate.query(
                sql,
                (rs, rowNum) -> {
                   Vehiculo vehiculo = new Vehiculo(); 
                   vehiculo.setMatricula(rs.getString("matricula"));
                   vehiculo.setNombre((rs.getString("nombre")));
                   vehiculo.setDniSocio(rs.getString("dniSocio"));
                   vehiculo.setTipoVehiculo(TipoVehiculo.valueOf(rs.getString("tipoVehiculo").toUpperCase()));
                   return vehiculo;
                },
                dni
        );       
        
    }

    
    public Vehiculo buscarPorMatricula(String matricula) {

    String sql = "SELECT * FROM vehiculos WHERE matricula = ?";

    try {
        return jdbcTemplate.queryForObject(
                sql,
                (rs, rowNum) -> {
                    Vehiculo v = new Vehiculo();
                    v.setMatricula(rs.getString("matricula"));
                    v.setDniSocio(rs.getString("dniSocio"));
                    v.setTipoVehiculo(
                            TipoVehiculo.valueOf(rs.getString("tipoVehiculo"))
                    );
                    return v;
                },
                matricula
        );
    } catch (EmptyResultDataAccessException e) {
        return null;
    }
}
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    

} //---FIN DE CLASE----
