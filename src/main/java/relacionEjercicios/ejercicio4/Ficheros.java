/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package relacionEjercicios.ejercicio4;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

/**
 *
 * @author moral
 */
public class Ficheros {
    
    public boolean crearDirectorio(String ruta){
        File directorio = new File(ruta);
        if(!directorio.exists()){
           directorio.mkdir();
           return true;
        }else{
            return false;
        }
    }
    
    public boolean copiarFichero(Path origen, Path destino){
        try{  
            Files.copy(origen, destino,StandardCopyOption.REPLACE_EXISTING);
            return true;
        }catch(IOException e) {     
            System.out.println("Problema copiando el archivo.");
            System.out.println(e.toString());
            return false;
        }
    }
    
    public boolean eliminarFichero(Path eliminar){
        try{  
            Files.delete(eliminar);
            return true;
        }catch(IOException e) {     
            System.out.println("Problema borrando el archivo.");
            System.out.println(e.toString());
            return false;
        }
    }
}
