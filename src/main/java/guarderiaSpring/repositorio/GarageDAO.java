package guarderiaSpring.repositorio;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Repository;
import guarderiaSpring.modelo.EspacioGarage;
import guarderiaSpring.enumerador.TipoVehiculo;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;

@Repository
public class GarageDAO {

    private final JdbcTemplate jdbcTemplate;

    public GarageDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void ocuparEspacio(int idEspacio, String matricula) {
        String sql = "UPDATE espacios_garage SET ocupado = true, matricula_vehiculo = ? WHERE id = ? AND ocupado = false";

    }

    public List<EspacioGarage> obtenerPorTipo(TipoVehiculo tipo) {

        String sql = "SELECT * FROM espacios_garage WHERE tipo_zona = ?";

        return jdbcTemplate.query(
                sql,
                (rs, rowNum) -> {
                    EspacioGarage eg = new EspacioGarage();

                    eg.setId(Integer.parseInt(rs.getString("id")));
                    eg.setTipoZona(tipo);
                    eg.setNumeroEspacio(Integer.parseInt(rs.getString("numero_espacio")));
                    eg.setOcupado(rs.getBoolean("ocupado"));
                    eg.setMatriculaVehiculo(rs.getString("matricula_vehiculo"));
                    eg.setEmpleadoAsignado(rs.getString("empleado_asignado"));

                    return eg;
                },
                tipo.name()
        );
    }

    public boolean existeAsignacion(String matricula) {

        String sql = "SELECT 1 FROM espacios_garage WHERE matricula_vehiculo = ? LIMIT 1";

        Integer count = jdbcTemplate.queryForObject(sql, Integer.class, matricula);

        return count != null && count > 0;

    }
 /*---------------------------------------------------------------------------*/   
 /*---------------------------------------------------------------------------*/
    
public void asignarEspacio(int idEspacio, String matricula) {
    String sql = "UPDATE espacios_garage SET ocupado = true, matricula_vehiculo = ? WHERE id = ?";
    jdbcTemplate.update(sql, matricula, idEspacio);
}

/*----------------------------------------------------------------------------*/   
/*----------------------------------------------------------------------------*/  

public List<EspacioGarage> obtenerEspaciosLibresPorTipo(TipoVehiculo tipo) {
    List<EspacioGarage> lista = new ArrayList<>();
    String sql = "SELECT id, numero_espacio, ocupado, tipo_zona FROM espacios_garage WHERE tipo_zona = ? AND ocupado = 0";      

    return lista;
}
     
/*----------------------------------------------------------------------------*/   
/*----------------------------------------------------------------------------*/

public void liberarEspacio(String matricula) {
    String sql = "UPDATE espacios_garage SET ocupado = false, matricula_vehiculo = NULL WHERE matricula_vehiculo = ?";
    jdbcTemplate.update(sql, matricula);
}

/*----------------------------------------------------------------------------
   

    

    public void marcarComoOcupado(int idEspacio) {

        String sql = "UPDATE espacio_garage SET ocupado = true WHERE id = ?";

        try (Connection con = DriverManager.getConnection(dbFullURL, dbUser, dbPswd); PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, idEspacio);
            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    
    

    public void actualizarEspacio(EspacioGarage espacio) {

        String sql = "UPDATE espacios_garage SET ocupado = ? WHERE id = ?";

        try (Connection conn = DriverManager.getConnection(dbFullURL, dbUser, dbPswd); PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setBoolean(1, espacio.isOcupado());
            ps.setInt(2, espacio.getId());

            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean liberarEspacioPorMatricula(String matricula) {

        String sql = """
        UPDATE espacios_garage 
        SET ocupado = false, 
            matricula_vehiculo = NULL
        WHERE matricula_vehiculo = ?
    """;

        try (Connection conn = DriverManager.getConnection(dbFullURL, dbUser, dbPswd); PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, matricula);

            int filasAfectadas = ps.executeUpdate();
            return filasAfectadas > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<EspacioGarage> obtenerEspaciosOcupados() {

        List<EspacioGarage> lista = new ArrayList<>();

        String sql = """
        SELECT * FROM espacios_garage
        WHERE ocupado = true
        AND matricula_vehiculo IS NOT NULL
    """;

        try (Connection conn = DriverManager.getConnection(dbFullURL, dbUser, dbPswd); PreparedStatement ps = conn.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                EspacioGarage espacio = new EspacioGarage(
                        rs.getInt("id"),
                        TipoVehiculo.valueOf(rs.getString("tipo_zona")),
                        rs.getInt("numero_espacio"),
                        rs.getBoolean("ocupado"),
                        rs.getString("matricula_vehiculo"),
                        rs.getString("empleado_asignado")
                );
                lista.add(espacio);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return lista;
    }

    public void asignarEmpleado(int idEspacio, String dniEmpleado) {
        String sql = "UPDATE espacios_garage SET empleado_asignado=? WHERE id=?";

        try (Connection conn = DriverManager.getConnection(dbFullURL, dbUser, dbPswd); PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, dniEmpleado);
            ps.setInt(2, idEspacio);

            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void quitarEmpleado(int idEspacio) {
        String sql = "UPDATE espacios_garage SET empleado_asignado=NULL WHERE id=?";

        try (Connection conn = DriverManager.getConnection(dbFullURL, dbUser, dbPswd); PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, idEspacio);
            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public List<EspacioGarage> obtenerEspacios() {

        List<EspacioGarage> lista = new ArrayList<>();

        String sql = "SELECT * FROM espacios_garage";

        try (Connection conn = DriverManager.getConnection(dbFullURL, dbUser, dbPswd); PreparedStatement ps = conn.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                EspacioGarage espacio = new EspacioGarage(
                        rs.getInt("id"),
                        TipoVehiculo.valueOf(rs.getString("tipo_zona")),
                        rs.getInt("numero_espacio"),
                        rs.getBoolean("ocupado"),
                        rs.getString("matricula_vehiculo"),
                        rs.getString("empleado_asignado")
                );
                lista.add(espacio);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return lista;
    }

    public List<EspacioGarage> buscarEspaciosPorEmpleado(String dniEmpleado) {

        List<EspacioGarage> lista = new ArrayList<>();

        String sql = "SELECT * FROM espacios_garage WHERE empleado_asignado=?";

        try (Connection conn = DriverManager.getConnection(dbFullURL, dbUser, dbPswd); PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, dniEmpleado);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                EspacioGarage e = new EspacioGarage();
                e.setId(rs.getInt("id"));
                e.setTipoZona(TipoVehiculo.valueOf(rs.getString("tipo_zona")));
                lista.add(e);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return lista;
    }


     */
} //FIN DE CLASE
