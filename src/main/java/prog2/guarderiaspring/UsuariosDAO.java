package prog2.guarderiaspring;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

@Repository
public class UsuariosDAO {
    
    private final String dbFullURL;
    private final String dbUser;
    private final String dbPswd;
    
    @Autowired
    public UsuariosDAO(
            @Qualifier("dbName") String dbName,
            @Qualifier("dbURL") String dbURL,
            @Qualifier("dbUser") String dbUser,
            @Qualifier("dbPswd") String dbPswd) {
        dbFullURL = "jdbc:mysql://" + dbURL + "/" + dbName;
        this.dbUser = dbUser;
        this.dbPswd = dbPswd;
    }
    
    
    public Usuario obtenerUsuario(String usuario) {
         try {
            Connection con = DriverManager.getConnection(dbFullURL, dbUser, dbPswd);
            Statement stmt = con.createStatement();
            stmt.execute("SELECT " + usuario + " FROM usuarios");
            ResultSet rs = stmt.getResultSet();
            while (rs.next()) {
                
            }
            con.close();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        

        return jdbcTemplate.queryForObject(sql, new Object[]{usuario}, (rs, rowNum) -> {
            String tipo = rs.getString("nivelAcceso");

            switch (tipo) {
                case "administrador":
                    return new Administrador(rs.getString("usuario"), rs.getString("password"), rs.getString("nivelAcceso"));
                case "empleado":
                    return new Empleado(rs.getString("usuario"), rs.getString("password"), rs.getString("nivelAcceso"));
                case "socio":
                    return new Socio(rs.getString("usuario"), rs.getString("password"), rs.getString("nivelAcceso"));
                default:
                    throw new IllegalArgumentException("Tipo de usuario no reconocido: " + tipo);
            }
        });
    }
    
    /*
    public void consultar(String nombre) {
        if (!nombre.isEmpty()) {
            connection = obtenerConexion();
            String q = "SELECT " + campoNombre + ", " + campoTelefono
                    + " FROM " + tabla
                    + " WHERE " + campoNombre + "='" + nombre + "'";
            try {
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(q);
                resultadoConsulta = "";
                while (resultSet.next()) {
                    resultadoConsulta = resultadoConsulta
                            + resultSet.getString(1) + ": "
                            + resultSet.getString(2) + System.getProperty("line.separator");
                }
                if (resultadoConsulta.isEmpty()) {
                    resultadoConsulta = tituloConsultaVacia + nombre + ".";
                }
                resultSet.close();
                statement.close();
            } catch (SQLException ex) {
                reportException(ex.getMessage());
            }
        }
    }
    
    public void darDeAlta(String nombre, String telefono) {
        if (!nombre.isEmpty()) {
            connection = obtenerConexion();
            String u = "INSERT INTO " + tabla
                    + " (" + campoNombre + ", " + campoTelefono + ") "
                    + " VALUES ('" + nombre + "', '" + telefono + "')";
            try {
                Statement statement = connection.createStatement();
                cantidadRegistros = statement.executeUpdate(u);
                statement.close();
            } catch (SQLException ex) {
                reportException(ex.getMessage());
            }
        }
    }

    public void darDeBaja(String nombre) {
        if (!nombre.isEmpty()) {
            connection = obtenerConexion();
            String u = "DELETE FROM " + tabla
                    + " WHERE " + campoNombre + "='" + nombre + "'";
            try {
                Statement statement = connection.createStatement();
                cantidadRegistros = statement.executeUpdate(u);
                statement.close();
            } catch (SQLException ex) {
                reportException(ex.getMessage());
            }
        }
    }

    public void modificar(String nombre, String telefono) {
        if (!nombre.isEmpty()) {
            connection = obtenerConexion();
            String u = "UPDATE " + tabla
                    + " SET " + campoNombre + "='" + nombre + "', "
                    + campoTelefono + "='" + telefono + "' "
                    + " WHERE " + campoNombre + "='" + nombre + "'";
            try {
                Statement statement = connection.createStatement();
                cantidadRegistros = statement.executeUpdate(u);
                statement.close();
            } catch (SQLException ex) {
                reportException(ex.getMessage());
            }
        }
    }

    */

    public Usuario obtenerUsuarioPorNombre(String usuario) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
