/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package relacionEjercicios.ejercicio4;



/**
 *
 * @author carlos
 */
public class Furgoneta extends Vehiculo {
    
    private int carga; // En kg
    private int volumen; // En m3

    public Furgoneta(int carga, int volumen, Long bastidor, String matricula, String marca, String modelo, String color, double tarifa, boolean disponible) {
        super(bastidor, matricula, marca, modelo, color, tarifa, disponible);
        this.carga = carga;
        this.volumen = volumen;
    }

    public Furgoneta() {
        this.carga = 1000;
        this.volumen = 8;
    }

    public int getCarga() {
        return carga;
    }

    public void setCarga(int carga) {
        this.carga = carga;
    }

    public int getVolumen() {
        return volumen;
    }

    public void setVolumen(int volumen) {
        this.volumen = volumen;
    }

    @Override
    public String toString() {
        return super.toString() + ":" + carga + ":" + volumen ;
    }   
    
    // Un método final implica que si hay descendencia de esta clase
    // este método no se pueda sobrescribir
    public final void metodoFurgoneta(){
        System.out.println("Este método es de la clase Furgoneta");
    }
    
    public void metodoX(){
        System.out.println("");
    }
}
