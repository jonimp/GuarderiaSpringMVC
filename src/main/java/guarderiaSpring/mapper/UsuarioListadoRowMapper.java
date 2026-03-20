package guarderiaSpring.mapper;

import guarderiaSpring.modelo.UsuarioListado;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

public class UsuarioListadoRowMapper implements RowMapper<UsuarioListado>{
    @Override
    public UsuarioListado mapRow(ResultSet rs, int rowNum) throws SQLException {
        UsuarioListado usuario = new UsuarioListado();
        usuario.setUsuario(rs.getString("usuario"));
        usuario.setNombre(rs.getString("nombre"));
        usuario.setTipo(rs.getString("tipo"));
        return usuario;
    }
}
