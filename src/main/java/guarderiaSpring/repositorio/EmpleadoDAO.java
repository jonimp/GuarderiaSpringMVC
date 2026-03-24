package guarderiaSpring.repositorio;

import guarderiaSpring.modelo.Empleado;
import guarderiaSpring.modelo.Socio;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class EmpleadoDAO {

    private final JdbcTemplate jdbcTemplate;

    public EmpleadoDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Empleado buscarEmpleado(String usuario) {

        String sql = "SELECT * FROM empleados WHERE usuario = ?";

        return jdbcTemplate.queryForObject(
                sql,
                (rs, rowNum) -> {
                    Empleado empl = new Empleado();
                    empl.setUsuario(rs.getString("usuario"));
                    empl.setPassword(rs.getString("password"));
                    empl.setNombre(rs.getString("nombre"));
                    empl.setDni(rs.getString("dni"));
                    empl.setDireccion(rs.getString("direccion"));
                    empl.setTelefono(rs.getString("telefono"));
                    empl.setEspecialidad(rs.getString("especialidad"));
                                        
                    return empl;
                },
                usuario
        );
    }

    
    public void guardarEmpleado(Empleado empl){
        
        String sql = "INSERT INTO empleados (usuario, password, nombre, dni, direccion, telefono, especialidad, tipo) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
                
        jdbcTemplate.update(sql,
        empl.getUsuario(),
        empl.getPassword(),
        empl.getNombre(),
        empl.getDni(),
        empl.getDireccion(),
        empl.getTelefono(),
        empl.getEspecialidad(),
        empl.getTipo().name().toLowerCase()
        );
    }
    
    
} //FIN DE CLASE
