package guarderiaSpring.repositorio;

import guarderiaSpring.modelo.Empleado;
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
    
}