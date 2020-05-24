/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejercicio11;

import java.util.Random;

/**
 *
 * @author moral
 */
public class App {
    private static int cod = 0;
    private int numDescargas;
    private String nombre, descripcion;
    double kb;

    public App(int numDescargas, String nombre, String descripcion, double kb) {
        this.numDescargas = numDescargas;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.kb = kb;
        this.cod++;
    }
    
    public App(){
        Random rnd = new Random();        
        this.nombre = "app"+this.cod+((char) (rnd.nextInt(26) + 'a'));
        this.descripcion = descrip();
        this.kb = rnd.doubles(1, 100.0, 1024.0).findFirst().getAsDouble();
        this.numDescargas = rnd.ints(1, 0, 50000).findFirst().getAsInt();
        this.cod++;
    }

    public static int getCod() {
        return cod;
    }

    public static void setCod(int cod) {
        App.cod = cod;
    }

    public int getNumDescargas() {
        return numDescargas;
    }

    public void setNumDescargas(int numDescargas) {
        this.numDescargas = numDescargas;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public double getKb() {
        return kb;
    }

    public void setKb(double kb) {
        this.kb = kb;
    }

    @Override
    public String toString() {
        return nombre + ";" + descripcion + ";" + numDescargas + ";" + kb + ";";
    }
    
    private String descrip(){
        Random rnd = new Random();   
        String[] descrip = new String[10];
        for(int i = 0; i < descrip.length; i++){
            descrip[i] = "Descripcion "+(i+1);
        }

        return descrip[rnd.nextInt(10)];
    }

}
