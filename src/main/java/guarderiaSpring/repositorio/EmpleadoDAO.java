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

                    return empl;
                },
                usuario
        );
    }

    
    public void guardarEmpleado(Empleado empl){
        
        String sql = "INSERT INTO empleados (usuario, password, nombre, dni, direccion, telefono, tipo) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
                
        jdbcTemplate.update(sql,
        empl.getUsuario(),
        empl.getPassword(),
        empl.getNombre(),
        empl.getDni(),
        empl.getDireccion(),
        empl.getTelefono(),
        empl.getEspecialidad(),
        empl.getTipo().name()
        );
    }
    
    
} //FIN DE CLASE
