package guarderiaSpring.repositorio;

import guarderiaSpring.modelo.Administrador;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class AdministradorDAO {

    private final JdbcTemplate jdbcTemplate;

    public AdministradorDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Administrador buscarAdministrador(String usuario) {

        String sql = "SELECT * FROM administradores WHERE usuario = ?";

        return jdbcTemplate.queryForObject(
                sql,
                (rs, rowNum) -> {
                    Administrador admin = new Administrador();

                    admin.setUsuario(rs.getString("usuario"));
                    admin.setPassword(rs.getString("password"));
                    admin.setNombre(rs.getString("nombre"));
                    admin.setDni(rs.getString("dni"));
                    
                    return admin;
                },
                usuario
        );
    }

    public void guardarAdministrador(Administrador admin) {

        String sql = "INSERT INTO administradores (usuario, password, nombre, dni, tipo) VALUES (?, ?, ?, ?, ?)";
        
        jdbcTemplate.update(
            sql,
            admin.getUsuario(),
            admin.getPassword(),
            admin.getNombre(),
            admin.getDni(),
            admin.getTipo().name().toLowerCase()
        );
    }

} //FIN DE CLASE
