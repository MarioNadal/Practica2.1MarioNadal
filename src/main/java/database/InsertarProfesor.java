package database;

import libs.Leer;

import java.sql.*;
import java.util.Date;

public class InsertarProfesor {
    public static void insertar() {
        //TODO VA TODO BIEN PERO NO ME LO AÑADE A LOS PROFESORES, AL COMPROBARLO NO ME SALE EL PROFESOR NUEVO
        String especialidadIntroducida = "", apellidosNombreIntroducido, sexoIntroducido;
        Date fechaNacimientoIntroducido;
        int codProfesorIntroducido, codigoCentroIntroducido = 0;
        boolean validarEspecialidad = false, validarCodCentro = false;
        try (Connection miCon = ConexionBD.conectar()) {
            Statement stmt = miCon.createStatement();
            ResultSet rs1 = stmt.executeQuery("SELECT ESPECIALIDAD FROM C1_ESPECIALIDAD");
            while(!validarEspecialidad){
                System.out.println("ESPECIALIDADES: ");
                System.out.println("--------------------------------------------------------------------------------------");
                //Saco por pantalla todas las especialidades que hay
                while(rs1.next()){
                    System.out.println(rs1.getString("ESPECIALIDAD"));
                }
            ResultSet rs2 = stmt.executeQuery("SELECT ESPECIALIDAD FROM C1_ESPECIALIDAD");
                //Recogo la especialidad que quiere el usuario para el nuevo profesor
                especialidadIntroducida = Leer.introduceString("Introduce la especialidad del nuevo profesor: ");
                while(rs2.next()){
                    if(rs1.getString("ESPECIALIDAD").equals(especialidadIntroducida)){
                        validarEspecialidad = true;
                    }
                }
            }
            ResultSet rs3 = stmt.executeQuery("SELECT COD_CENTRO FROM C1_CENTROS");
            while(!validarCodCentro){
                System.out.println("CÓDIGOS CENTROS");
                System.out.println("--------------------------------------------------------------------------------------");
                //Saco por la pantalla todos los códigos de los centros que hay
                while(rs3.next()){
                    System.out.println(rs3.getInt("COD_CENTRO"));
                }
                //Recogo el código del centro que quiere el usuario para el nuevo profesor
                codigoCentroIntroducido = Leer.introduceEntero("Introduce el código del centro del nuevo profesor");
            ResultSet rs4 = stmt.executeQuery("SELECT COD_CENTRO FROM C1_CENTROS");
                while(rs4.next()){
                    if(rs4.getInt("COD_CENTRO") == codigoCentroIntroducido){
                        validarCodCentro = true;
                    }
                }
            }
            //Recogo los datos que faltan para poder crear al profesor
            codProfesorIntroducido = Leer.introduceEntero("Introduce el código del profesor");
            apellidosNombreIntroducido = Leer.introduceString("Introduce los apellidos, una coma y el nombre del profesor");
            fechaNacimientoIntroducido = Leer.introduceDate("Introduce la fecha de nacimiento del usuario");
            do{
                sexoIntroducido = Leer.introduceLetra("Introduce sexo del profesor(H/M)");
            }while(!sexoIntroducido.equals("H")&&!sexoIntroducido.equals("M"));
            PreparedStatement pstmt1 = miCon.prepareStatement("INSERT INTO C1_PROFESORES VALUES (?,?,?,null,?,?,?)");
            pstmt1.setInt(1,codProfesorIntroducido);
            pstmt1.setString(2,apellidosNombreIntroducido);
            pstmt1.setString(3,especialidadIntroducida);
            pstmt1.setString(4, fechaNacimientoIntroducido.toString());
            pstmt1.setString(5,sexoIntroducido);
            pstmt1.setInt(6,codigoCentroIntroducido);
            //EJECUTO EL PREPARED STATEMENT Y AÑADO EL PROFESOR
            pstmt1.execute();
            System.out.println("--------------------------------------------------------------------------------------");
            System.out.println("Profesor introducido correctamente");
            System.out.println("--------------------------------------------------------------------------------------");
            //Comprobación de que se ha añaido el nuevo profesor
            ResultSet rs6 = stmt.executeQuery("SELECT NOMBRE_APE FROM C1_PROFESORES");
            while(rs6.next()){
                System.out.println(rs6.getString("NOMBRE_APE"));
            }
            System.out.println("--------------------------------------------------------------------------------------");

        } catch (SQLException ex) {
            System.out.println("Error en la conexión de la base de datos al listar profesores " + ex.getMessage());
        }
    }
}
