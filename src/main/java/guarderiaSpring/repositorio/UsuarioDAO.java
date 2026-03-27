package guarderiaSpring.repositorio;

import guarderiaSpring.dto.RegistroUsuarioDTO;
import guarderiaSpring.enumerador.TipoUsuario;
import guarderiaSpring.mapper.UsuarioListadoRowMapper;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import guarderiaSpring.modelo.Administrador;
import guarderiaSpring.modelo.Empleado;
import guarderiaSpring.modelo.Socio;
import guarderiaSpring.modelo.Usuario;
import guarderiaSpring.modelo.UsuarioListado;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;

@Repository
public class UsuarioDAO {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    /*----------------------------------------------------------------------------*/

 /*----------------------------------------------------------------------------*/
    public TipoUsuario validarUsuario(String usuario, String password) {

        String sql = "SELECT tipo FROM usuarios WHERE usuario=? AND password=?";

        try {
            String tipo = jdbcTemplate.queryForObject(
                    sql,
                    (rs, rowNum) -> rs.getString("tipo"),
                    usuario,
                    password
            );
            return TipoUsuario.valueOf(tipo.toUpperCase());

        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    /*----------------------------------------------------------------------------*/

 /*----------------------------------------------------------------------------*/
    public List<UsuarioListado> buscarListado(String nombre, String tipoUsuario) {

        StringBuilder sql = new StringBuilder("""
        SELECT usuario, nombre, tipo FROM (
            SELECT usuario, nombre, 'administrador' AS tipo FROM administradores
            UNION ALL
            SELECT usuario, nombre, 'empleado' AS tipo FROM empleados
            UNION ALL
            SELECT usuario, nombre, 'socio' AS tipo FROM socios
        ) AS usuarios
        WHERE 1=1
    """);

        List<Object> params = new ArrayList<>();

        if (tipoUsuario != null && !tipoUsuario.isBlank()) {
            sql.append(" AND tipo = ?");
            params.add(tipoUsuario);
        }

        if (nombre != null && !nombre.isBlank()) {
            sql.append(" AND nombre LIKE ?");
            params.add("%" + nombre + "%");
        }

        return jdbcTemplate.query(
                sql.toString(),
                params.toArray(),
                new UsuarioListadoRowMapper()
        );
    }
/*----------------------------------------------------------------------------*/

/*----------------------------------------------------------------------------*/
    public List<UsuarioListado> buscarPorTipo(String tipoUsuario) {

        List<UsuarioListado> lista = new ArrayList<>();

        String sql;

        if (tipoUsuario == null || tipoUsuario.isEmpty()) {
            sql = """
            SELECT usuario, NULL AS nombre, 'Administrador' AS tipo FROM administradores
            UNION ALL
            SELECT usuario, nombre, 'Empleado' FROM empleados
            UNION ALL
            SELECT usuario, nombre, 'Socio' FROM socios
        """;
        } else {
            sql = switch (tipoUsuario) {
                case "administrador" ->
                    "SELECT usuario, NULL AS nombre, 'Administrador' AS tipo FROM administradores";
                case "empleado" ->
                    "SELECT usuario, nombre, 'Empleado' FROM empleados";
                case "socio" ->
                    "SELECT usuario, nombre, 'Socio' FROM socios";
                default ->
                    null;
            };
        }

        if (sql == null) {
            return lista;
        }

     

        return lista;
    }
/*----------------------------------------------------------------------------*/

/*----------------------------------------------------------------------------*/
    public String buscarTipoUsuario(String nombreUsuario){
        String sql = "SELECT tipo FROM usuarios WHERE usuario = ?";
        
        return jdbcTemplate.queryForObject(
                sql,
                String.class,
                nombreUsuario
        );
    }
/*----------------------------------------------------------------------------*/

/*----------------------------------------------------------------------------*/   
    public void guardarUsuarioBase(RegistroUsuarioDTO us){
        String sql = "INSERT INTO usuarios (usuario, password, tipo) VALUES (?, ?, ?)";
        
        jdbcTemplate.update(
            sql,
            us.getUsuario(),
            us.getPassword(),
            us.getTipo().name().toLowerCase()
        );
    }    
/*----------------------------------------------------------------------------*/

/*----------------------------------------------------------------------------*/
    public void actualizarUsuarioBase(RegistroUsuarioDTO us){
        String sql = "UPDATE usuarios SET usuario=?, password=? WHERE usuario = ?";
        
        jdbcTemplate.update(
            sql,
            us.getUsuario(),
            us.getPassword(),
            us.getUsuarioOriginal()
        );
    }
/*----------------------------------------------------------------------------*/

/*----------------------------------------------------------------------------*/    
    public void eliminarUsuario(String usuario) {
        String sql = "DELETE FROM usuarios WHERE usuario = ?";
        jdbcTemplate.update(sql, usuario);
    }
/*----------------------------------------------------------------------------*/

/*----------------------------------------------------------------------------*/
    public List<Socio> obtenerSocios() {

        List<Socio> lista = new ArrayList<>();
        String sql = "SELECT * FROM socios";

         return lista;
    }

    
    public List<Empleado> obtenerEmpleados() {

        List<Empleado> lista = new ArrayList<>();
        String sql = "SELECT * FROM empleados";
       
        return lista;
    }





} //FIN DE CLASE
