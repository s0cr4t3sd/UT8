/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejercicio11;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.DirectoryNotEmptyException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import org.jdom2.Attribute;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

/**
 *
 * @author moral
 */
public class Programa {
    public static void crearDirectorio(String ruta){
        File directorio = new File(ruta);
        if (!directorio.exists()) {
            if (directorio.mkdirs()) {
                System.out.println("Directorio creado");
            } else {
                System.out.println("Error al crear directorio");
            }
        }
    }
    
    public static void leerFicheroTXT(String archivo){
        String[] tokens;
        String linea;
        try (Scanner datosFichero = new Scanner(new File(archivo))){           
            while (datosFichero.hasNextLine()) {

                linea = datosFichero.nextLine();

                // Se guarda en el array de String cada elemento de la
                // línea en función del carácter separador coma
                tokens = linea.split(";");

                for (String string : tokens) {
                    System.out.print(string + "\t");
                }

                System.out.println();
            }
            
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        } 

    }
    
    public static void crearFicheroTXT(String url, ArrayList<App> lista){
        try (BufferedWriter flujo = new BufferedWriter(new FileWriter(url))){			
            for (App i : lista) {
                // Usamos metodo write() para escribir en el buffer
                flujo.write(i.toString());
                // Metodo newLine() añade línea en blanco
                flujo.newLine();
            }
            // Metodo fluh() guarda cambios en disco 
            flujo.flush();

        } catch (IOException e) {
            System.out.println(e.getMessage());
        } 
    }
    
    public static void crearFicheroTXT(String url, String contenido){
        try (BufferedWriter flujo = new BufferedWriter(new FileWriter(url))){			            
            // Usamos metodo write() para escribir en el buffer
            flujo.write(contenido);              
            // Metodo fluh() guarda cambios en disco 
            flujo.flush();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        } 
    }
    
    public static void borrarFicheroTXT(String ruta){
        Path element = Paths.get(ruta);
        try {
            Files.delete(element);
        } catch (IOException e) {
                System.out.println("Problema borrando el archivo.");
                System.out.println(e.toString());
        }
    }
    
    
    public static void crearFicheroXML(String url, ArrayList<App> lista) {
        // Se crea el elemento raíz
        Element raiz = new Element("APLICACIONES");
       
        // Creo el árbol de nodos a partir del elemento raíz
        Document arbolNodos = new Document(raiz);

        // Por cada mueble en la lista
        for (App i : lista) {
            // Nuevo elemento Mueble 
            Element appElement = new Element("APP");

            Element nombre = new Element("NOMBRE");
            nombre.setText(i.getNombre());
            appElement.addContent(nombre);
            
            Element descripcion = new Element("DESCRIPCION");
            descripcion.setText(i.getDescripcion());
            appElement.addContent(descripcion);
            
            Element numDescargas = new Element("NUM_DESCARGAS");
            numDescargas.setText(Integer.toString(i.getNumDescargas()));
            appElement.addContent(numDescargas);
            
            Element kb = new Element("TAM_EN_KB");
            kb.setText(Double.toString(i.getKb()));
            appElement.addContent(kb);

           
            // Se añade el elemento Mueble con sus descendientes al árbol de nodos
            arbolNodos.getRootElement().addContent(appElement);
        }

        // La clase XMLOutputter sirve para escribir un fichero JDOM en el disco
        XMLOutputter xmlOutput = new XMLOutputter();
        // La siguente línea hace que se genere el documento en formato de árbol de etiquetas
        xmlOutput.setFormat(Format.getPrettyFormat());

        try {

            // Creación del fichero en el disco usando el objeto XMLOutputter y a partir del árbol de nodos
            xmlOutput.output(arbolNodos, new FileWriter(url));

            System.out.println("Fichero creado");

        } catch (IOException io) {
            System.out.println(io.getMessage());
        }
    }
    

    public static void copiarArchivo(String orig, String dest){
        Path origen = Paths.get(orig);
        Path destino = Paths.get(dest);
        try {
            Files.copy(origen, destino);
        } catch (IOException e) {
            System.out.println("Problema copiando el archivo.");
            System.out.println(e.toString());
        }
    }
    
    public static void main(String[] args) {
        //Crea 50 aplicaciones usando el constructor por defecto, guárdalas en una lista y muéstralas por pantalla. 
        ArrayList<App> lista = new ArrayList<>();
        for(int i = 0; i < 50; i++){
            App app = new App();
            lista.add(app);
        }
        
        for(App i : lista){
            System.out.println(i.toString());
        }
        
        //Guarda los datos de todas las App de la lista, en un fichero de texto llamado aplicacionestxt.txt, dentro del directorio “./appstxt”.
        crearDirectorio("./appstxt");
        crearFicheroTXT("./appstxt/aplicacionestxt.txt",lista);
        
        //Guarda los datos de todas las App de la lista, en un fichero XML llamado aplicacionesxml.xml, dentro del directorio “./appsxml”.
        crearDirectorio("./appsxml");
        crearFicheroXML("./appsxml/aplicacionesxml.xml",lista);
        
        //Crea una carpeta “./copias” y realiza una copia de los ficheros anteriores dentro de ella.
        crearDirectorio("./copias");        
        copiarArchivo("./appstxt/aplicacionestxt.txt","./copias/aplicacionestxt.txt");
        copiarArchivo("./appsxml/aplicacionesxml.xml","./copias/aplicacionesxml.xml");
        
        //En una carpeta “./aplicaciones” crea un archivo de texto por cada aplicación que haya en la lista. 
        //El archivo se llamará igual que la app y contendrá los datos de la aplicación, separando los campos por el carácter (;).
        String[] token;        
        String conten;
        crearDirectorio("./aplicaciones");
        for(App i : lista){
            token = i.toString().split(";");
            conten = token[1]+";"+token[2]+";"+token[3]+";";
            
            crearFicheroTXT("./aplicaciones/"+token[0]+".txt",conten);
        }
        
        
        
        //EJERCICIO 13
        Scanner  scn = new Scanner(System.in);
        int cont = 0;
        String[] nombreArchivo;
        
        File f = new File("./aplicaciones");
        nombreArchivo = f.list();
        if (f.exists()){
            File[] ficheros = f.listFiles();
            
            for (File file2 : ficheros) {
                System.out.println(cont+") "+file2.getName());
                cont++;
            }
        }else {    System.out.println("El directorio a listar no existe");
        }
        
        System.out.println("");
        
        int selec=0;
        boolean salir = false;
        
        while(!salir){
            System.out.println("Dime cual quieres seleccionar: ");
            try{
                selec = scn.nextInt();
                if(selec > -1 && selec < 50){salir = true;}
            }catch(InputMismatchException  IME){
                scn.nextLine();
            }
        }
               
        
        try (Scanner datosFichero = new Scanner(new File("./aplicaciones/"+nombreArchivo[selec]))){      
            String linea;
            while (datosFichero.hasNextLine()) {

                linea = datosFichero.nextLine();

                String[] tokn;
                tokn = linea.split(";");

                System.out.println("");
                for(int i = 0; i < tokn.length; i++){
                    System.out.println("tokn["+i+"] = "+tokn[i]);
                }
                App objeto = new App(Integer.parseInt(tokn[1]), nombreArchivo[selec], tokn[0], Double.parseDouble(tokn[2]));
                System.out.println(objeto);

                borrarFicheroTXT("./aplicaciones/"+nombreArchivo[selec]);

                System.out.println(nombreArchivo[selec]);
        
                File ff = new File("./aplicaciones");
                nombreArchivo = ff.list();
                if (ff.exists()){
                    File[] ficheros = ff.listFiles();

                    for (File file2 : ficheros) {
                        System.out.println(file2.getName());
                    }
                }else {    System.out.println("El directorio a listar no existe");
                }
            }
            
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        } 
        System.out.println("");
        
        
        
        
    }
}
