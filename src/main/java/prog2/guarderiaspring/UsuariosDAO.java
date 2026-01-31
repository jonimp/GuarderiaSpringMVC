package prog2.guarderiaspring;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
        
        try (Connection con = DriverManager.getConnection(dbFullURL, dbUser, dbPswd);
            PreparedStatement pstmt = con.prepareStatement(sql)) {
           
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
                        case "administrador" -> buscarAdministrador(usuario);
                        case "empleado" -> buscarEmpleado(usuario);
                        case "socio" -> buscarSocio(usuario);
                        default -> null;
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
   public Administrador buscarAdministrador(String usuario) {
    String sql = "SELECT * FROM administradores WHERE usuario = ?";

    try (Connection con = DriverManager.getConnection(dbFullURL, dbUser, dbPswd);
         PreparedStatement pstmt = con.prepareStatement(sql)) {

        pstmt.setString(1, usuario);

        try (ResultSet rs = pstmt.executeQuery()) {
            if (rs.next()) {
                return new Administrador(
                    rs.getString("usuario"),
                    rs.getString("password")
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
public Empleado buscarEmpleado(String usuario) {
    String sql = "SELECT * FROM empleados WHERE usuario = ?";

    try (Connection con = DriverManager.getConnection(dbFullURL, dbUser, dbPswd);
         PreparedStatement pstmt = con.prepareStatement(sql)) {

        pstmt.setString(1, usuario);

        try (ResultSet rs = pstmt.executeQuery()) {
            if (rs.next()) {
                return new Empleado(
                    rs.getString("usuario"),
                    rs.getString("password"),
                    rs.getString("codigo"),
                    rs.getString("nombre"),
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

        try (Connection con = DriverManager.getConnection(dbFullURL, dbUser, dbPswd);
             PreparedStatement pstmt = con.prepareStatement(sql)) {

            pstmt.setString(1, usuario);

            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return new Socio(
                        rs.getString("usuario"),
                        rs.getString("password"),
                        rs.getString("dni"),
                        rs.getString("nombre"),
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
}

