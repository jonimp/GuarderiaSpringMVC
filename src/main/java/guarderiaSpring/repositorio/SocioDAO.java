package guarderiaSpring.repositorio;

import guarderiaSpring.dto.RegistroUsuarioDTO;
import guarderiaSpring.modelo.Socio;
import java.util.List;
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
                    soc.setNombre(rs.getString("nombre"));
                    soc.setDni(rs.getString("dni"));
                    soc.setDireccion(rs.getString("direccion"));
                    soc.setTelefono(rs.getString("telefono"));
                    
                    return soc;
                },
                usuario
        );
    }

    
    public void guardarSocio(Socio soc){
        
        String sql = "INSERT INTO socios (usuario, password, nombre, dni, direccion, telefono, tipo) VALUES (?, ?, ?, ?, ?, ?, ?)";
                
        jdbcTemplate.update(
        sql,
        soc.getUsuario(),
        soc.getPassword(),
        soc.getNombre(),
        soc.getDni(),
        soc.getDireccion(),
        soc.getTelefono(),
        soc.getTipo().name().toLowerCase()
        );
    }
    
    
    public void editarSocio(RegistroUsuarioDTO soc) {

        String sql = "UPDATE socios SET usuario=?, password=?, nombre=? WHERE usuario = ?";
        
        jdbcTemplate.update(
            sql,
            soc.getUsuario(),
            soc.getPassword(),
            soc.getNombre(),
            soc.getUsuarioOriginal()
        );
    }
    
    
    public void eliminarSocio(String socio){
        String sql = "DELETE FROM socios WHERE usuario = ?";
        jdbcTemplate.update(sql, socio);
    }
    
    
    public List<Socio> obtenerLista(){
        
        String sql = "SELECT * FROM socios";
        
        return (List<Socio>) jdbcTemplate.query(
                sql,
                (rs, rowNum) -> {
                    Socio soc = new Socio();

                    soc.setUsuario(rs.getString("usuario"));
                    soc.setPassword(rs.getString("password"));
                    soc.setNombre(rs.getString("nombre"));
                    soc.setDni(rs.getString("dni"));
                    soc.setDireccion(rs.getString("direccion"));
                    soc.setTelefono(rs.getString("telefono"));
                    
                    return soc;
                }                
        );
    }
} // FIN DE CLASE
