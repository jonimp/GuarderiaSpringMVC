package prog2.guarderiaspring;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

@Repository
public class GarageDAO {

    private final String dbFullURL;
    private final String dbUser;
    private final String dbPswd;

    @Autowired
    public GarageDAO(
            @Qualifier("dbName") String dbName,
            @Qualifier("dbURL") String dbURL,
            @Qualifier("dbUser") String dbUser,
            @Qualifier("dbPswd") String dbPswd) {
        dbFullURL = "jdbc:mysql://" + dbURL + "/" + dbName;
        this.dbUser = dbUser;
        this.dbPswd = dbPswd;
    }

    public void ocuparEspacio(int idEspacio, String matricula) {
        String sql = "UPDATE espacios_garage SET ocupado = true, matricula_vehiculo = ? WHERE id = ? AND ocupado = false";

    }

    public List<EspacioGarage> obtenerPorTipo(TipoVehiculo tipo) {

        List<EspacioGarage> lista = new ArrayList<>();

        String sql = "SELECT * FROM espacio_garage WHERE tipo = ?";

        try (Connection con = DriverManager.getConnection(dbFullURL, dbUser, dbPswd); PreparedStatement pstmt = con.prepareStatement(sql); ResultSet rs = pstmt.executeQuery()) {

            pstmt.setString(1, tipo.name());

            while (rs.next()) {
                EspacioGarage espacio = new EspacioGarage();

                espacio.setId(rs.getInt("id"));
                espacio.setNumeroEspacio(rs.getInt("numero_espacio"));
                espacio.setTipoZona(TipoVehiculo.valueOf(rs.getString("tipo_zona")));
                espacio.setOcupado(rs.getBoolean("ocupado"));

                lista.add(espacio);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return lista;
    }

    /*----------------------------------------------------------------------------*/

 /*----------------------------------------------------------------------------*/
    public List<EspacioGarage> obtenerEspaciosLibresPorTipo(TipoVehiculo tipo) {

        List<EspacioGarage> lista = new ArrayList<>();

        String sql = "SELECT id, numero_espacio, ocupado, tipo_zona FROM espacios_garage WHERE tipo_zona = ? AND ocupado = 0";

        try (Connection con = DriverManager.getConnection(dbFullURL, dbUser, dbPswd); PreparedStatement pstmt = con.prepareStatement(sql)) {

            pstmt.setString(1, tipo.name());
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                EspacioGarage espacio = new EspacioGarage();

                espacio.setId(rs.getInt("id"));
                espacio.setNumeroEspacio(rs.getInt("numero_espacio"));
                espacio.setOcupado(rs.getBoolean("ocupado"));
                espacio.setTipoZona(TipoVehiculo.valueOf(rs.getString("tipo_zona")));

                lista.add(espacio);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return lista;
    }

    public void asignarEspacio(int idEspacio, String matricula) {

        String sql = """
        UPDATE espacios_garage
        SET ocupado = true,
            matricula_vehiculo = ?
        WHERE id = ?
    """;

        try (Connection con = DriverManager.getConnection(dbFullURL, dbUser, dbPswd); PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, matricula);
            ps.setInt(2, idEspacio);

            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }




    public void marcarComoOcupado(int idEspacio) {

        String sql = "UPDATE espacio_garage SET ocupado = true WHERE id = ?";

        try (Connection con = DriverManager.getConnection(dbFullURL, dbUser, dbPswd); PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, idEspacio);
            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    
    public boolean existeAsignacion(String matricula) {

    String sql = "SELECT 1 FROM espacios_garage WHERE matricula_vehiculo = ? LIMIT 1";

    try (Connection con = DriverManager.getConnection(dbFullURL, dbUser, dbPswd);
         PreparedStatement ps = con.prepareStatement(sql)) {

        ps.setString(1, matricula);   // 🔥 ESTO ES CLAVE

        ResultSet rs = ps.executeQuery();

        if (rs.next()) {
            return true;  // ya existe asignación
        }

    } catch (SQLException e) {
        e.printStackTrace();
    }

    return false;  // no existe
}
    
    
    
    
    
    
    
    
    
    
    
    
    
} //FIN DE CLASE
