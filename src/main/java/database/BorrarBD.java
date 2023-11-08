package database;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;

public class BorrarBD {
    public static void borrar(){
        try{
            Files.delete(Path.of("target/instituto.dat"));
        }catch (NoSuchFileException ex){
            System.out.println("No se ha podido borrar la base de datos porque no existe");
        }catch (SecurityException ex){
            System.out.println("No se ha podido borrar la base de datos por motivos de seguridad");
        }catch (IOException ex){
            System.out.println("No se ha podido borrar la base de datos");
        }
    }

}
