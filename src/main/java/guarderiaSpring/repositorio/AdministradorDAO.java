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

                return admin;
            },
            usuario
    );
}
    
}
