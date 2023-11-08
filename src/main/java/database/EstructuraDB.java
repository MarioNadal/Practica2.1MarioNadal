package database;

import java.sql.*;
import java.util.Arrays;
import java.util.List;

public class EstructuraDB {
    public static void crearDB(){
        try (Connection miCon = ConexionBD.conectar()) {
            if (miCon != null) {
                DatabaseMetaData meta = miCon.getMetaData();
                System.out.println("Base de datos creada.");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void crearTablas(){
        //al utilizar el try with resources con un objeto closeable, este se cerrará en cualquier caso.
        //TODO NO ENTRA EN ESTA CONEXION PONE QUE NO SE PUEDE CONECTAR A LA BASE DE DATOS
        try(Connection miCon = ConexionBD.conectar()) {
            //sentencias SQL para crear tabla asignaturas
            String tablaAsig = "CREATE TABLE C1_ASIGNATURAS (\n" +
                    " COD_ASIG CHAR(6) NOT NULL PRIMARY KEY" +
                    " NOMBRE_ASI VARCHAR(30)" +
                    ");";
            //sentencias SQL para añadir los valores de varios departamentos a la tabla
            List<String> addAsig = Arrays.asList("INSERT INTO C1_ASIGNATURAS VALUES ('IF0001','DAHC')",
                    "INSERT INTO C1_ASIGNATURAS VALUES ('IF0001','DAHC')",
                    "INSERT INTO C1_ASIGNATURAS VALUES ('IF0002','RAL')",
                    "INSERT INTO C1_ASIGNATURAS VALUES ('IF0003','IMSI')",
                    "INSERT INTO C1_ASIGNATURAS VALUES ('IF0004','DPEG')",
                    "INSERT INTO C1_ASIGNATURAS VALUES ('IF0005','PLE')",
                    "INSERT INTO C1_ASIGNATURAS VALUES ('IF0005','FPE')",
                    "INSERT INTO C1_ASIGNATURAS VALUES ('LG0001','Lengua 1 ESO')",
                    "INSERT INTO C1_ASIGNATURAS VALUES ('LG0002','Lengua 2 ESO')",
                    "INSERT INTO C1_ASIGNATURAS VALUES ('LG0003','Lengua 3 ESO')",
                    "INSERT INTO C1_ASIGNATURAS VALUES ('LG0004','Lengua 4 ESO')",
                    "INSERT INTO C1_ASIGNATURAS VALUES ('DB0001','Plástica')",
                    "INSERT INTO C1_ASIGNATURAS VALUES ('DB0002','Taller cerámica')",
                    "INSERT INTO C1_ASIGNATURAS VALUES ('DB0003','Dibujo Técnico')",
                    "INSERT INTO C1_ASIGNATURAS VALUES ('MT0001','Matemáticas 1 BAC')",
                    "INSERT INTO C1_ASIGNATURAS VALUES ('MT0002','Matemáticas 2 BAC')"
                    );
            String tablaCentros = "CREATE TABLE C1_CENTROS\n" +
                    "(\n" +
                    "\tCOD_CENTRO NUMERIC(4) NOT NULL PRIMARY KEY ,\n" +
                    "\tNOM_CENTRO VARCHAR(20),\n" +
                    "\tDIRECTOR NUMERIC(4),\n" +
                    "\tDIRECCION VARCHAR(25),\n" +
                    "\tLOCALIDAD VARCHAR(20),\n" +
                    "\tPROVINCIA VARCHAR(20)\n" +
                    "\n" +
                    ") ;";
            List<String> addCentros = Arrays.asList("INSERT INTO C1_CENTROS VALUES (1000,'IES El Quijote', 1000, 'Avda. Los Molinos 25', 'GUADALAJARA', 'GUADALAJARA')",
                    "INSERT INTO C1_CENTROS VALUES (1015,'CP Los Danzantes', 1010,'C/Las Musas s/n','PASTRANA', 'GUADALAJARA')",
                    "INSERT INTO C1_CENTROS VALUES (1022, 'IES Planeta Tierra',2000,'C/Mina 45', 'AZUQUECA', 'GUADALAJARA')",
                    "INSERT INTO C1_CENTROS VALUES (1045, 'CP Manuel Hidalgo', NULL,'C/Granada 5', 'GUADALAJARA', 'GUADALAJARA')",
                    "INSERT INTO C1_CENTROS VALUES (1050, 'IES Antoñete', NULL,'C/Los Toreros 21', 'SIGUENZA', 'GUADALAJARA')"
                    );
            String tablaEspecialidad = "CREATE TABLE C1_ESPECIALIDAD\n" +
                    "(\n" +
                    "\tESPECIALIDAD CHAR(2) NOT NULL PRIMARY KEY,\n" +
                    "\tNOMBRE_ESPE VARCHAR(25)\n" +
                    ") ;";
            List<String> addEspecialidad = Arrays.asList("INSERT INTO C1_ESPECIALIDAD VALUES ('IF','Informática')",
                    "INSERT INTO C1_ESPECIALIDAD VALUES ('IN','Inglés')",
                    "INSERT INTO C1_ESPECIALIDAD VALUES ('FQ','Física y Química')",
                    "INSERT INTO C1_ESPECIALIDAD VALUES ('GH','Geografía e Historia')",
                    "INSERT INTO C1_ESPECIALIDAD VALUES ('TG','Tecnología')",
                    "INSERT INTO C1_ESPECIALIDAD VALUES ('LG','Lengua')",
                    "INSERT INTO C1_ESPECIALIDAD VALUES ('DB','Dibujo')",
                    "INSERT INTO C1_ESPECIALIDAD VALUES ('MT','Matemáticas')"
            );
            String tablaProfesores = "CREATE TABLE C1_PROFESORES\n" +
                    "(\n" +
                    "\tCOD_PROF NUMERIC(4) NOT NULL PRIMARY KEY, \n" +
                    "\tNOMBRE_APE VARCHAR(30),\n" +
                    "\tESPECIALIDAD CHAR(2) REFERENCES C1_ESPECIALIDAD(ESPECIALIDAD ),\n" +
                    "\tJEFE_DEP NUMERIC(4),\n" +
                    "\tFECHA_NAC DATE,\n" +
                    "\tSEXO CHAR(1),\n" +
                    "\tCOD_CENTRO NUMERIC(4) NOT NULL REFERENCES C1_CENTROS (COD_CENTRO)\n" +
                    ");";
            List<String> addProfesores = Arrays.asList("INSERT INTO C1_PROFESORES VALUES (1000,'Martínez Salas, Fernando', 'IF', 1001, '1961-09-07', 'H', 1000)",
            "INSERT INTO C1_PROFESORES VALUES (1001,'Bueno Zarco, Elisa', 'IF',NULL, '1960-02-17', 'M', 1000)",
            "INSERT INTO C1_PROFESORES VALUES (2002,'Rivera Silvestre, Ana','DB',3000, '1950-10-10', 'M',1000)",
            "INSERT INTO C1_PROFESORES VALUES (3000,'De Lucas Fdez, M.Angel','DB',NULL, '1980-09-09','M',1000)",
            "INSERT INTO C1_PROFESORES VALUES (1010,'Montes García, M.Pilar', 'MT', 1011,'1970-10-10', 'M', 1015)",
            "INSERT INTO C1_PROFESORES VALUES (1011,'Arroba Conde, Manuel', 'MT', NULL,'1970-10-12', 'H', 1015)",
            "INSERT INTO C1_PROFESORES VALUES (1022,'Ruiz Lafuente, Manuel','MT',1011, '1966-11-11', 'H',1015)",
            "INSERT INTO C1_PROFESORES VALUES (2000,'Ramos Ruiz, Luis','LG',2003, '1963-08-08', 'H',1022 )",
            "INSERT INTO C1_PROFESORES VALUES (2003,'Segura Molina, Irene','LG',NULL, '1963-07-08', 'M',1022 )",
            "INSERT INTO C1_PROFESORES VALUES (1045,'Serrano Laguía, María','IF',NULL,'1976-01-02', 'M', 1022)"
            );
            String tablaAsigProfesores = "CREATE TABLE C1_ASIGPROF\n" +
                    "(\n" +
                    "\tCOD_ASIG CHAR(6) NOT NULL REFERENCES C1_ASIGNATURAS (COD_ASIG),\n" +
                    "\tCOD_PROF NUMERIC(4) NOT NULL REFERENCES C1_PROFESORES (COD_PROF),\n" +
                    "    PRIMARY KEY (COD_ASIG,COD_PROF)\n" +
                    ") ;";
            List<String> addAsigProfesores = Arrays.asList("INSERT INTO C1_ASIGPROF VALUES ('IF0002',1001);\n" +
                    "INSERT INTO C1_ASIGPROF VALUES ('IF0003',1001)",
                    "INSERT INTO C1_ASIGPROF VALUES ('IF0001',1000)",
                    "INSERT INTO C1_ASIGPROF VALUES ('LG0001',2000)",
                    "INSERT INTO C1_ASIGPROF VALUES ('LG0002',2000)",
                    "INSERT INTO C1_ASIGPROF VALUES ('LG0003',2003)",
                    "INSERT INTO C1_ASIGPROF VALUES ('LG0004',2003)",
                    "INSERT INTO C1_ASIGPROF VALUES ('DB0001',2002)",
                    "INSERT INTO C1_ASIGPROF VALUES ('DB0002',2002)",
                    "INSERT INTO C1_ASIGPROF VALUES ('DB0003',3000)",
                    "INSERT INTO C1_ASIGPROF VALUES ('MT0001',1010)",
                    "INSERT INTO C1_ASIGPROF VALUES ('MT0001',1011)",
                    "INSERT INTO C1_ASIGPROF VALUES ('MT0001',1022)",
                    "INSERT INTO C1_ASIGPROF VALUES ('MT0002',1010)"
            );
            //variable Statement para ejecutar las sentencias SQL en la conexión
            Statement crearTablas = miCon.createStatement();
            //borrado de las tablas antes de crearlas para no incurrir en violaciones de integridad
            crearTablas.executeUpdate("DROP TABLE IF EXISTS C1_ASIGPROF");
            crearTablas.executeUpdate("DROP TABLE IF EXISTS C1_PROFESORES");
            crearTablas.executeUpdate("DROP TABLE IF EXISTS C1_ESPECIALIDAD");
            crearTablas.executeUpdate("DROP TABLE IF EXISTS C1_ASIGNATURAS");
            crearTablas.executeUpdate("DROP TABLE IF EXISTS C1_CENTROS");
            //CREO LA TABLA ASIGNATURAS Y SUS DATOS INICIALES
            crearTablas.executeUpdate(tablaAsig);
            for (String d : addAsig) {
                crearTablas.executeUpdate(d);
            }
            //CREO LA TABLA CENTRO Y SUS DATOS INICIALES
            crearTablas.executeUpdate(tablaCentros);
            for (String d : addCentros) {
                crearTablas.executeUpdate(d);
            }
            crearTablas.executeUpdate(tablaEspecialidad);
            for (String d : addEspecialidad) {
                crearTablas.executeUpdate(d);
            }
            crearTablas.executeUpdate(tablaProfesores);
            for (String d : addProfesores) {
                crearTablas.executeUpdate(d);
            }
            crearTablas.executeUpdate(tablaAsigProfesores);
            for (String d : addAsigProfesores) {
                crearTablas.executeUpdate(d);
            }
        }catch (SQLSyntaxErrorException e) {
            System.out.println("Error en la sintaxis de la sentencia SQL" + e.getMessage());
        }catch (SQLIntegrityConstraintViolationException e) {
            System.out.println("La sentencia SQL no cumple con los requisitos de integridad " +
                    "de la base de datos" + e.getMessage());
        }catch (SQLException e) {
            System.out.println("No se puede conectar a la base de datos");
        }
    }

}
