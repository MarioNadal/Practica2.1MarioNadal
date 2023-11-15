package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class ListarProfesores {
    public static void listar(){
        try(Connection miCon = ConexionBD.conectar()){
            Statement stmt = miCon.createStatement();
            stmt.executeUpdate("SELECT NOMBRE_ESPE FROM C1_ESPECIALIDAD");
            //PreparedStatement pstmt = miCon.prepareStatement("SELECT NOMBRE_APE FROM C1_PROFESORES WHERE ESPECIALIDAD = ?");
            //pstmt.setString(1,);
        }catch(SQLException ex){
            System.out.println("Error en la conexión de la base de datos al listar profesores");
        }
    }
}
