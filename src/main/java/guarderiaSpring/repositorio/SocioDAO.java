package guarderiaSpring.repositorio;

import guarderiaSpring.modelo.Socio;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;


@Repository
public class SocioDAO {
    private final JdbcTemplate jdbcTemplate;

    public SocioDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    
    
    
    public Socio buscarSocio(String usuario) {

    String sql = "SELECT * FROM socios WHERE usuario = ?";

    return jdbcTemplate.queryForObject(
            sql,
            (rs, rowNum) -> {
                Socio soc = new Socio();

                soc.setUsuario(rs.getString("usuario"));
                soc.setPassword(rs.getString("password"));

                return soc;
            },
            usuario
    );
}
    
}