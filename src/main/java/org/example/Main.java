package org.example;

import database.BorrarBD;
import database.ConexionBD;
import database.EstructuraDB;
import database.ListarProfesores;

public class Main {
    public static void main(String[] args) {
        boolean salir = false;
        int opcion;
        do {
            System.out.println(
                    "1. Crear Base de Datos \n" +
                    "2. Borrar Base de Datos \n"+
                    "3. Lista Profesores Especialidad \n"+
                    "4. Insertar Profesor \n"+
                    "0. Salir ");

            opcion = libs.Leer.introduceEntero("Introduce una opción");
            switch (opcion) {
                case 1 -> {
                    EstructuraDB.crearDB();
                    EstructuraDB.crearTablas();

                }
                case 2 -> BorrarBD.borrar();
                case 3 -> ListarProfesores.listar();
                case 0 -> salir = true;
                default -> System.out.println("Opción incorrecta");
            }
        } while (!salir);
    }


}