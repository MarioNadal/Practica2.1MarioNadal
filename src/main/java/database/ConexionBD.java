package database;

import java.lang.module.InvalidModuleDescriptorException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionBD {
    //definimos como constantes los datos de la conexión a la base de datos
    //como es una base de datos portable será un archivo en nuestro sistema de archivos (sin puerto).
    private static final String URL = "jdbc:sqlite:target/instituto.dat";
    //no necesitamos usuario ni clave.
    //private static final String USUARIO = "";
    //private static final String CLAVE = "";

    public static Connection conectar() {
        Connection conexion = null;
        try {
            //si la base de datos no existe la crea.
            conexion = DriverManager.getConnection((URL));
            System.out.println("Conexión OK.");
        }catch (SQLException e) {
            System.out.println("Error en la conexión. " + e.getMessage());
        } catch (InvalidModuleDescriptorException e) {
            System.out.println("Error PAM");
        }
        return conexion;
    }
}
