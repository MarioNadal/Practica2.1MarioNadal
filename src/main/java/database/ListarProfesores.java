package database;

import libs.Leer;

import java.sql.*;

public class ListarProfesores {
    public static void listar(){
        boolean validarEspecialidad = false;
        String especialidadIntroducida = "";
        try(Connection miCon = ConexionBD.conectar()){
            try{
                Statement stmt = miCon.createStatement();
                ResultSet resultSet2 = null;
                while(!validarEspecialidad) {
                    //Recogo de la base de datos todas las especialidades
                    ResultSet resultSet = stmt.executeQuery("SELECT ESPECIALIDAD FROM C1_ESPECIALIDAD");
                    System.out.println("ESPECIALIDADES: ");
                    System.out.println("--------------------------------------------------------------------------------------");
                    //Saco por pantalla todas las especialidades que hay
                    while (resultSet.next()) {
                        System.out.println(resultSet.getString("ESPECIALIDAD"));
                    }
                    //El usuario introduce la especialidad de la cúal quiere ver los profesores
                    especialidadIntroducida = Leer.introduceString("Introduce los  os carácteres de la Especialidad: ");
                    ResultSet resultSet1 = stmt.executeQuery("SELECT ESPECIALIDAD FROM C1_ESPECIALIDAD");
                    while(resultSet1.next()){
                        if(resultSet1.getString("ESPECIALIDAD").equals(especialidadIntroducida)){
                            validarEspecialidad = true;
                        }
                    }
                    if(!validarEspecialidad){
                        System.out.println("Introduce una especialidad válida");
                    }else{
                        //Recogo los profesores de la especialidad introducida por el usuario
                        PreparedStatement pstmt = miCon.prepareStatement("SELECT NOMBRE_APE FROM C1_PROFESORES WHERE ESPECIALIDAD LIKE ?");
                        pstmt.setString(1, especialidadIntroducida);
                        resultSet2 = pstmt.executeQuery();
                    }
                }
                //Recogo el nombre de la especialidad para mostrarlo bonito
                PreparedStatement pstmt1 = miCon.prepareStatement("SELECT NOMBRE_ESPE FROM C1_ESPECIALIDAD WHERE ESPECIALIDAD LIKE ?");
                pstmt1.setString(1,especialidadIntroducida);
                ResultSet resultSet3 = pstmt1.executeQuery();
                System.out.println("--------------------------------------------------------------------------------------");
                System.out.println("PROFESORES DE LA ESPECIALIDAD: " + resultSet3.getString("NOMBRE_ESPE"));
                System.out.println("--------------------------------------------------------------------------------------");
                //Muestro todos los nombres de los profesores de la especialidad seleccionada
                while(resultSet2.next()){
                    System.out.println(resultSet2.getString("NOMBRE_APE"));
                }
                System.out.println("--------------------------------------------------------------------------------------");
            }catch(SQLIntegrityConstraintViolationException ex){
                System.out.println("Error en la integración del SQL.");
            }catch(SQLSyntaxErrorException ex){
                System.out.println("Error en el lenguaje de SQL");
            }
        }catch(SQLException ex){
            System.out.println("Error en la conexión de la base de datos al listar profesores.");
        }
    }
}
