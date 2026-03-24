package guarderiaSpring.repositorio;

import guarderiaSpring.enumerador.TipoVehiculo;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import guarderiaSpring.modelo.Vehiculo;
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

    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    

} //---FIN DE CLASE----
