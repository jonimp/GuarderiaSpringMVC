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
public class UsuarioDAO {

    private final String dbFullURL;
    private final String dbUser;
    private final String dbPswd;

    @Autowired
    public UsuarioDAO(
            @Qualifier("dbName") String dbName,
            @Qualifier("dbURL") String dbURL,
            @Qualifier("dbUser") String dbUser,
            @Qualifier("dbPswd") String dbPswd) {
        dbFullURL = "jdbc:mysql://" + dbURL + "/" + dbName;
        this.dbUser = dbUser;
        this.dbPswd = dbPswd;
    }

    /*----------------------------------------------------------------------------*/

 /*----------------------------------------------------------------------------*/
    public Usuario validarUsuario(String usuario, String password) {

        String sql = """
        SELECT usuario, password, acceso, 'administrador' as tipo 
        FROM administradores 
        WHERE usuario = ? AND password = ?
        UNION ALL
        SELECT usuario, password, acceso, 'empleado' as tipo 
        FROM empleados 
        WHERE usuario = ? AND password = ?
        UNION ALL
        SELECT usuario, password, acceso, 'socio' as tipo 
        FROM socios 
        WHERE usuario = ? AND password = ?;
        """;

        try (Connection con = DriverManager.getConnection(dbFullURL, dbUser, dbPswd); PreparedStatement pstmt = con.prepareStatement(sql)) {

            pstmt.setString(1, usuario);
            pstmt.setString(2, password);
            pstmt.setString(3, usuario);
            pstmt.setString(4, password);
            pstmt.setString(5, usuario);
            pstmt.setString(6, password);

            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    String tipoAcceso = rs.getString("tipo");

                    // Creamos el usuario según su tipo
                    return switch (tipoAcceso.toLowerCase()) {
                        case "administrador" ->
                            buscarAdministrador(usuario);
                        case "empleado" ->
                            buscarEmpleado(usuario);
                        case "socio" ->
                            buscarSocio(usuario);
                        default ->
                            null;
                    };
                }
            }
            return null; // Usuario no encontrado

        } catch (SQLException e) {
            System.err.println("Error al validar usuario: " + e.getMessage());
            return null;
        }

    }

    /*----------------------------------------------------------------------------*/

 /*----------------------------------------------------------------------------*/
    public List<UsuarioListado> buscarListado(String tipoUsuario, String nombre) {

        List<UsuarioListado> lista = new ArrayList<>();

        String sql = """
        SELECT usuario, nombre, tipo
        FROM (
            SELECT usuario, nombre, 'administrador' AS tipo
            FROM administradores

            UNION ALL

            SELECT usuario, nombre, 'empleado' AS tipo
            FROM empleados

            UNION ALL

            SELECT usuario, nombre, 'socio' AS tipo
            FROM socios
        ) AS usuarios
        WHERE (? IS NULL OR ? = '' OR tipo = ?)
          AND (? IS NULL OR ? = '' OR nombre LIKE CONCAT('%', ?, '%'))
    """;

        try (Connection con = DriverManager.getConnection(dbFullURL, dbUser, dbPswd); PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, tipoUsuario);
            ps.setString(2, tipoUsuario);
            ps.setString(3, tipoUsuario);

            ps.setString(4, nombre);
            ps.setString(5, nombre);
            ps.setString(6, nombre);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    lista.add(new UsuarioListado(
                            rs.getString("usuario"),
                            rs.getString("nombre"),
                            rs.getString("tipo")
                    ));
                }
            }

        } catch (SQLException e) {
            System.err.println("Error en buscarListado: " + e.getMessage());
        }

        return lista;
    }

    /*-----------------------------------------------------------------------------*/
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

        try (Connection con = DriverManager.getConnection(dbFullURL, dbUser, dbPswd); PreparedStatement ps = con.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                lista.add(new UsuarioListado(
                        rs.getString("usuario"),
                        rs.getString("nombre"),
                        rs.getString("tipo")
                ));
            }

        } catch (SQLException e) {
        }

        return lista;
    }

    /*----------------------------------------------------------------------------*/

 /*----------------------------------------------------------------------------*/
    public Empleado buscarEmpleado(String usuario) {
        String sql = "SELECT * FROM empleados WHERE usuario = ?";

        try (Connection con = DriverManager.getConnection(dbFullURL, dbUser, dbPswd); PreparedStatement pstmt = con.prepareStatement(sql)) {

            pstmt.setString(1, usuario);

            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return new Empleado(
                            rs.getString("usuario"),
                            rs.getString("password"),
                            rs.getString("nombre"),
                            rs.getString("dni"),
                            rs.getString("direccion"),
                            rs.getString("telefono"),
                            rs.getString("especialidad")
                    );
                }
            }
        } catch (SQLException e) {
            System.err.println("Error al buscar empleado: " + e.getMessage());
        }
        return null;
    }

    /*----------------------------------------------------------------------------*/

 /*----------------------------------------------------------------------------*/
    public Socio buscarSocio(String usuario) {
        String sql = "SELECT * FROM socios WHERE usuario = ?";

        try (Connection con = DriverManager.getConnection(dbFullURL, dbUser, dbPswd); PreparedStatement pstmt = con.prepareStatement(sql)) {

            pstmt.setString(1, usuario);

            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return new Socio(
                            rs.getString("usuario"),
                            rs.getString("password"),
                            rs.getString("nombre"),
                            rs.getString("dni"),
                            rs.getString("telefono"),
                            rs.getString("direccion")
                    );
                }
            }
        } catch (SQLException e) {
            System.err.println("Error al buscar socio: " + e.getMessage());
        }
        return null;
    }

    /*----------------------------------------------------------------------------*/

 /*----------------------------------------------------------------------------*/
    public Administrador buscarAdministrador(String usuario) {
        String sql = "SELECT * FROM administradores WHERE usuario = ?";

        try (Connection con = DriverManager.getConnection(dbFullURL, dbUser, dbPswd); PreparedStatement pstmt = con.prepareStatement(sql)) {

            pstmt.setString(1, usuario);

            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return new Administrador(
                            rs.getString("usuario"),
                            rs.getString("password"),
                            rs.getString("nombre"),
                            rs.getString("dni")
                    );
                }
            }
        } catch (SQLException e) {
            System.err.println("Error al buscar administrador: " + e.getMessage());
        }
        return null; // Si no se encuentra
    }

    /*----------------------------------------------------------------------------*/

 /*----------------------------------------------------------------------------*/
    public Usuario buscarPorUsuario(String usuario) {

        Usuario u = buscarAdministrador(usuario);
        if (u != null) {
            return u;
        }

        u = buscarEmpleado(usuario);
        if (u != null) {
            return u;
        }

        return buscarSocio(usuario);
    }

    /*----------------------------------------------------------------------------*/
 /*----------------------------------------------------------------------------*/
    public void actualizar(Usuario u) throws SQLException {

        if (u instanceof Administrador administrador) {
            actualizarAdministrador(administrador);
        } else if (u instanceof Empleado empleado) {
            actualizarEmpleado(empleado);
        } else if (u instanceof Socio socio) {
            actualizarSocio(socio);
        }
    }

    /*----------------------------------------------------------------------------*/
 /*----------------------------------------------------------------------------*/
    private void actualizarAdministrador(Administrador a) throws SQLException {

        String sql = "UPDATE administradores SET usuario=?, password=?, nombre=? WHERE dni=?";

        try (Connection con = DriverManager.getConnection(dbFullURL, dbUser, dbPswd); PreparedStatement pstmt = con.prepareStatement(sql)) {

            pstmt.setString(1, a.getUsuario());
            pstmt.setString(2, a.getPassword());
            pstmt.setString(3, a.getNombre());
            pstmt.setString(4, a.getDni());

            pstmt.executeUpdate();
        }
    }

    /*----------------------------------------------------------------------------*/
 /*----------------------------------------------------------------------------*/
    private void actualizarEmpleado(Empleado e) throws SQLException {

        String sql = "UPDATE empleados SET usuario=?, password=?, nombre=?, direccion=?, telefono=?, especialidad=? WHERE dni=?";

        try (Connection con = DriverManager.getConnection(dbFullURL, dbUser, dbPswd); PreparedStatement pstmt = con.prepareStatement(sql)) {

            pstmt.setString(1, e.getUsuario());
            pstmt.setString(2, e.getPassword());
            pstmt.setString(3, e.getNombre());
            pstmt.setString(4, e.getDireccion());
            pstmt.setString(5, e.getTelefono());
            pstmt.setString(6, e.getEspecialidad());
            pstmt.setString(7, e.getDni());

            pstmt.executeUpdate();
        }

    }

    /*----------------------------------------------------------------------------*/
 /*----------------------------------------------------------------------------*/
    private void actualizarSocio(Socio s) throws SQLException {

        String sql = "UPDATE socios SET usuario=?, password=?, nombre=?, direccion=?, telefono=? WHERE dni=?";

        try (Connection con = DriverManager.getConnection(dbFullURL, dbUser, dbPswd); PreparedStatement pstmt = con.prepareStatement(sql)) {

            pstmt.setString(1, s.getUsuario());
            pstmt.setString(2, s.getPassword());
            pstmt.setString(3, s.getNombre());
            pstmt.setString(4, s.getDireccion());
            pstmt.setString(5, s.getTelefono());
            pstmt.setString(6, s.getDni());

            int filas = pstmt.executeUpdate();
            System.out.println("Filas actualizadas: " + filas);
        }

    }
    /*----------------------------------------------------------------------------*/
 /*----------------------------------------------------------------------------*/
    public void eliminar(Usuario u) throws SQLException {

        if (u instanceof Administrador administrador) {
            eliminarAdministrador(administrador);
        } else if (u instanceof Empleado empleado) {
            eliminarEmpleado(empleado);
        } else if (u instanceof Socio socio) {
            eliminarSocio(socio);
        }
    }

    /*----------------------------------------------------------------------------*/
 /*----------------------------------------------------------------------------*/
    private void eliminarAdministrador(Administrador a) throws SQLException {

        String sql = "DELETE FROM administradores WHERE usuario=?";

        try (Connection con = DriverManager.getConnection(dbFullURL, dbUser, dbPswd); PreparedStatement pstmt = con.prepareStatement(sql)) {

            pstmt.setString(1, a.getUsuario());
            pstmt.executeUpdate();
        }
    }

    /*----------------------------------------------------------------------------*/
 /*----------------------------------------------------------------------------*/
    private void eliminarEmpleado(Empleado e) throws SQLException {

        String sql = "DELETE FROM empleados WHERE usuario=?";

        try (Connection con = DriverManager.getConnection(dbFullURL, dbUser, dbPswd); PreparedStatement pstmt = con.prepareStatement(sql)) {
            
            pstmt.setString(1, e.getUsuario());
            pstmt.executeUpdate();
        }

    }

    /*----------------------------------------------------------------------------*/
 /*----------------------------------------------------------------------------*/
    private void eliminarSocio(Socio s) throws SQLException {

        String sql = "DELETE FROM socios WHERE usuario=?";

        try (Connection con = DriverManager.getConnection(dbFullURL, dbUser, dbPswd); PreparedStatement pstmt = con.prepareStatement(sql)) {
            
            pstmt.setString(1, s.getUsuario());
            pstmt.executeUpdate();
        }

    }
  /*----------------------------------------------------------------------------*/
 /*----------------------------------------------------------------------------*/  
    public void guardar(Usuario u) throws SQLException {
        
        if (u instanceof Administrador administrador) {
            guardarAdministrador(administrador);
        } else if (u instanceof Empleado empleado) {
            guardarEmpleado(empleado);
        } else if (u instanceof Socio socio) {
            guardarSocio(socio);
        }
    }

    private void guardarAdministrador(Administrador a) throws SQLException{
        String sql = "INSERT INTO administradores (usuario, password, nombre, dni, acceso) VALUES (?, ?, ?, ?, ?)";

        try (Connection con = DriverManager.getConnection(dbFullURL, dbUser, dbPswd); PreparedStatement pstmt = con.prepareStatement(sql)) {

            pstmt.setString(1, a.getUsuario());
            pstmt.setString(2, a.getPassword());
            pstmt.setString(3, a.getNombre());
            pstmt.setString(4, a.getDni());
            pstmt.setString(5, a.getTipo());
            
            pstmt.executeUpdate();
        }
    }
    
    private void guardarEmpleado(Empleado e) throws SQLException{
        String sql = "INSERT INTO empleados (usuario, password, nombre, dni, direccion, telefono, especialidad, acceso) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection con = DriverManager.getConnection(dbFullURL, dbUser, dbPswd); PreparedStatement pstmt = con.prepareStatement(sql)) {

            pstmt.setString(1, e.getUsuario());
            pstmt.setString(2, e.getPassword());
            pstmt.setString(3, e.getNombre());
            pstmt.setString(4, e.getDni());
            pstmt.setString(5, e.getDireccion());
            pstmt.setString(6, e.getTelefono());
            pstmt.setString(7, e.getEspecialidad());
            pstmt.setString(8, e.getTipo());
            
            pstmt.executeUpdate();
        }
    }
    
    private void guardarSocio(Socio s) throws SQLException{
        String sql = "INSERT INTO socios (usuario, password, nombre, dni, direccion, telefono, acceso) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (Connection con = DriverManager.getConnection(dbFullURL, dbUser, dbPswd); PreparedStatement pstmt = con.prepareStatement(sql)) {

            pstmt.setString(1, s.getUsuario());
            pstmt.setString(2, s.getPassword());
            pstmt.setString(3, s.getNombre());
            pstmt.setString(4, s.getDni());
            pstmt.setString(5, s.getDireccion());
            pstmt.setString(6, s.getTelefono());
            pstmt.setString(7, s.getTipo());
            
            pstmt.executeUpdate();
        }
    }
    
    
    public List<Socio> obtenerSocios() {

        List<Socio> lista = new ArrayList<>();
        String sql = "SELECT * FROM socios";

        try (Connection con = DriverManager.getConnection(dbFullURL, dbUser, dbPswd); PreparedStatement pstmt = con.prepareStatement(sql); ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {

                Socio s = new Socio(
                        rs.getString("usuario"),
                        rs.getString("password"),
                        rs.getString("nombre"),
                        rs.getString("dni"),
                        rs.getString("telefono"),
                        rs.getString("direccion")
                );

                lista.add(s);
            }

        } catch (SQLException e) {
            System.err.println("Error al obtener socios: " + e.getMessage());
        }

        return lista;
    }
    
    
    
    
    
    
    
}
