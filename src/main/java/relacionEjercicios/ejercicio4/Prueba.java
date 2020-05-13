/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package relacionEjercicios.ejercicio4;


import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

/**
 *
 * @author carlos
 */
public class Prueba {
    public static void main(String[] args) {
        ArrayList<Vehiculo> lista = new ArrayList<>();
        
        Ficheros ficheros = new Ficheros();

        
        for (int i = 0; i < 10; i++) {
            lista.add(new Turismo());
            lista.add(new Deportivo());
            lista.add(new Furgoneta());
        }
        
        
        String idfichero = "vehiculos.txt";
        try (BufferedWriter flujo = new BufferedWriter(new FileWriter(idfichero))){
			
            for (Vehiculo i : lista) {

                if(i instanceof Turismo){
                    flujo.write("0-"+i.toString());
                }
                if(i instanceof Deportivo){
                    flujo.write("1-"+i.toString());
                }
                if(i instanceof Furgoneta){
                    flujo.write("2-"+i.toString());
                }

                flujo.newLine();
            }
            // Metodo fluh() guarda cambios en disco 
            flujo.flush();
			
        } catch (IOException e) {
                System.out.println(e.getMessage());
        }
        
        String[] tokens, tipo;
        String linea,s;
        ArrayList<Vehiculo> listaVehiculos = new ArrayList<>();
        
        Long bastidor;
        double tarifa;
        boolean disponible;
        
        //Furgoneta
        int carga;
        int volumen;
        
        //Turismo
        int numeroPuertas;
        
        //Cilindrada
        int cilindrada;
        
        try (Scanner datosFichero = new Scanner(new FileReader(idfichero))){

            while (datosFichero.hasNextLine()) {
                linea = datosFichero.nextLine();

                tokens = linea.split(":");
                tipo = linea.split("-");               
                
                bastidor = Long.parseLong(tipo[1].substring(0,1));
                tarifa = Double.parseDouble(tokens[5]);
                disponible = Boolean.parseBoolean(tokens[6]);
                
                switch (tipo[0]){
                    case "0":
                        numeroPuertas = Integer.parseInt(tokens[7]);
                        listaVehiculos.add(new Turismo(numeroPuertas, bastidor, tokens[1], tokens[2], tokens[3], tokens[4], tarifa, disponible));
                        break;
                    case "1":
                        cilindrada = Integer.parseInt(tokens[7]);
                        listaVehiculos.add(new Deportivo(cilindrada, bastidor, tokens[1], tokens[2], tokens[3], tokens[4], tarifa, disponible));
                        break;
                    case "2":
                        carga = Integer.parseInt(tokens[7]);
                        volumen = Integer.parseInt(tokens[8]);
                        listaVehiculos.add(new Furgoneta(carga, volumen, bastidor, tokens[1], tokens[2], tokens[3], tokens[4], tarifa, disponible));
                        break;
                }
                            
            }
            
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        } 
        
        
        ArrayList<Furgoneta> listaFurgonetas = new ArrayList<>();
        ArrayList<Turismo> listaTurismos = new ArrayList<>();
        ArrayList<Deportivo> listaDeportivos = new ArrayList<>();
        
        for(Vehiculo i : listaVehiculos){
            if(i instanceof Furgoneta){
                listaFurgonetas.add((Furgoneta) i);
            }
            
            if(i instanceof Turismo){
                listaTurismos.add((Turismo) i);
            }
            
            if(i instanceof Deportivo){
                listaDeportivos.add((Deportivo) i);
            }
        }
        
        try (BufferedWriter flujo = new BufferedWriter(new FileWriter("Furgoneta.txt"))){		
            for (Furgoneta i : listaFurgonetas) {
                flujo.write(i.toString());
                flujo.newLine();
            }
            // Metodo fluh() guarda cambios en disco 
            flujo.flush();	
        } catch (IOException e) {
                System.out.println(e.getMessage());
        }
        
        try (BufferedWriter flujo = new BufferedWriter(new FileWriter("Turismo.txt"))){		
            for (Turismo i : listaTurismos) {
                flujo.write(i.toString());
                flujo.newLine();
            }
            // Metodo fluh() guarda cambios en disco 
            flujo.flush();	
        } catch (IOException e) {
                System.out.println(e.getMessage());
        }
        
        try (BufferedWriter flujo = new BufferedWriter(new FileWriter("Deportivo.txt"))){		
            for (Deportivo i : listaDeportivos) {
                flujo.write(i.toString());
                flujo.newLine();
            }
            // Metodo fluh() guarda cambios en disco 
            flujo.flush();	
        } catch (IOException e) {
                System.out.println(e.getMessage());
        }
        
        
        /*Ejercicio 10*/
        ficheros.crearDirectorio("./copias");
        
        ficheros.copiarFichero(Paths.get("Deportivo.txt"), Paths.get("./copias/Deportivo.txt"));
        ficheros.copiarFichero(Paths.get("Turismo.txt"), Paths.get("./copias/Turismo.txt"));
        ficheros.copiarFichero(Paths.get("Furgoneta.txt"), Paths.get("./copias/Furgoneta.txt"));
        
        
        ArrayList<Vehiculo> cVehiculos = new ArrayList<>();
        
        idfichero = "./copias/Deportivo.txt";
        try (Scanner datosFichero = new Scanner(new FileReader(idfichero))){
            while (datosFichero.hasNextLine()) {
                linea = datosFichero.nextLine();

                tokens = linea.split(":");              
                
                bastidor = Long.parseLong(tokens[0].substring(0,1));
                tarifa = Double.parseDouble(tokens[5]);
                disponible = Boolean.parseBoolean(tokens[6]);
                
                cilindrada = Integer.parseInt(tokens[7]);
                cVehiculos.add(new Deportivo(cilindrada, bastidor, tokens[1], tokens[2], tokens[3], tokens[4], tarifa, disponible));             
            }
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        } 
        
        idfichero = "./copias/Furgoneta.txt";
        try (Scanner datosFichero = new Scanner(new FileReader(idfichero))){
            while (datosFichero.hasNextLine()) {
                linea = datosFichero.nextLine();

                tokens = linea.split(":");              
                
                bastidor = Long.parseLong(tokens[0].substring(0,1));
                tarifa = Double.parseDouble(tokens[5]);
                disponible = Boolean.parseBoolean(tokens[6]);

                carga = Integer.parseInt(tokens[7]);
                volumen = Integer.parseInt(tokens[8]);
                cVehiculos.add(new Furgoneta(carga, volumen, bastidor, tokens[1], tokens[2], tokens[3], tokens[4], tarifa, disponible));            
            }
            
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        } 
        
        idfichero = "./copias/Turismo.txt";
        try (Scanner datosFichero = new Scanner(new FileReader(idfichero))){
            while (datosFichero.hasNextLine()) {
                linea = datosFichero.nextLine();

                tokens = linea.split(":");              
                
                bastidor = Long.parseLong(tokens[0].substring(0,1));
                tarifa = Double.parseDouble(tokens[5]);
                disponible = Boolean.parseBoolean(tokens[6]);
                
                numeroPuertas = Integer.parseInt(tokens[7]);
                cVehiculos.add(new Turismo(numeroPuertas, bastidor, tokens[1], tokens[2], tokens[3], tokens[4], tarifa, disponible));                    
            }
            
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        } 
        cVehiculos.get(0).setBastidor(2L);
        for(Vehiculo i : cVehiculos){
            System.out.println(i);
        }
        System.out.println("");
        
        cVehiculos.sort(Comparator.comparing((v) -> v.getBastidor()));
        
        for(Vehiculo i : cVehiculos){
            System.out.println(i);
        }
        
        ficheros.eliminarFichero(Paths.get("Deportivo.txt"));
        ficheros.eliminarFichero(Paths.get("Turismo.txt"));
        ficheros.eliminarFichero(Paths.get("Furgoneta.txt"));
        ficheros.eliminarFichero(Paths.get("vehiculos.txt"));
       
        
        System.out.println("");
        System.out.println("Contenido del directorio:");
        File f = new File(".");
        if (f.exists()){
            File[] fic = f.listFiles();
            for (File file2 : fic) {
                System.out.println(file2.getName());
            }
        }else {    System.out.println("El directorio a listar no existe");
        }
    }
}
