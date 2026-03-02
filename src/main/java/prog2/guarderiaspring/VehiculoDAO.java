package prog2.guarderiaspring;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

@Repository
public class VehiculoDAO {

    private final String dbFullURL;
    private final String dbUser;
    private final String dbPswd;

    @Autowired
    public VehiculoDAO(
            @Qualifier("dbName") String dbName,
            @Qualifier("dbURL") String dbURL,
            @Qualifier("dbUser") String dbUser,
            @Qualifier("dbPswd") String dbPswd) {
        dbFullURL = "jdbc:mysql://" + dbURL + "/" + dbName;
        this.dbUser = dbUser;
        this.dbPswd = dbPswd;
    }

    public void agregarVehiculo(Vehiculo v) throws SQLException {

        String sql = "INSERT INTO vehiculos (matricula, dniSocio, nombre, tipoVehiculo) VALUES (?, ?, ?, ?)";

        try (Connection con = DriverManager.getConnection(dbFullURL, dbUser, dbPswd); PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, v.getMatricula());
            ps.setString(2, v.getDniSocio());
            ps.setString(3, v.getNombre());
            ps.setString(4, v.getTipoVehiculo().name());

            System.out.println("INSERTANDO EN BD...");

            ps.executeUpdate();
        }
    }

    public List<Vehiculo> obtenerVehiculosPorDni(String dniSocio) {

        List<Vehiculo> lista = new ArrayList<>();
        String sql = "SELECT * FROM vehiculos WHERE dniSocio = ?";

        try (Connection con = DriverManager.getConnection(dbFullURL, dbUser, dbPswd); PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, dniSocio);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                Vehiculo v = new Vehiculo(
                        rs.getString("matricula"),
                        rs.getString("dniSocio"),
                        rs.getString("nombre"),
                        TipoVehiculo.valueOf(rs.getString("tipoVehiculo"))
                );

                lista.add(v);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return lista;
    }

    public Vehiculo obtenerPorMatricula(String matricula) {

        String sql = "SELECT * FROM vehiculos WHERE matricula = ?";

        try (Connection con = DriverManager.getConnection(dbFullURL, dbUser, dbPswd); PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, matricula);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {

                return new Vehiculo(
                        rs.getString("matricula"),
                        rs.getString("dniSocio"),
                        rs.getString("nombre"),
                        TipoVehiculo.valueOf(rs.getString("tipoVehiculo"))
                );
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

   

} //---FIN DE CLASE----

/*
    
    

    // =========================
    // BUSCAR POR MATRICULA
    // =========================
    public Vehiculo buscarVehiculo(String matricula) {

        String sql = "SELECT * FROM vehiculos WHERE matricula = ?";

        try (Connection con = DriverManager.getConnection(dbFullURL, dbUser, dbPswd); PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, matricula);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return new Vehiculo(
                        rs.getString("matricula"),
                        rs.getString("dniSocio"),
                        rs.getString("nombre"),
                        TipoVehiculo.valueOf(rs.getString("tipoVehiculo"))
                );
            }

        } catch (SQLException e) {
        }

        return null;
    }

    // =========================
    // ELIMINAR VEHICULO
    // =========================
    public boolean eliminarVehiculo(String matricula) {

        String sql = "DELETE FROM vehiculos WHERE matricula = ?";

        try (Connection con = DriverManager.getConnection(dbFullURL, dbUser, dbPswd); PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, matricula);

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
        }

        return false;
    }
 */
