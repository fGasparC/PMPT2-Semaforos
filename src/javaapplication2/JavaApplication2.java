/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication2;

import java.util.concurrent.Semaphore;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
Crear una clase en java que utilizando 5 hilos cuente el número de
vocales que hay en un determinado texto. Cada uno de los hilos se
encargará de contar una vocal en concreto pero todos irán
actualizando una variable común para saber el número total de
vocales que hay.
 */
public class JavaApplication2 {

    static int cantidadVocales;
    static String texto;
    static Semaphore sem;
    
    static class HiloContador extends Thread{
        char vocal;
        HiloContador(char v){
            vocal=v;
        }
        @Override
        public void run(){
            try {
                sem.acquire();
            } catch (InterruptedException ex) {
                Logger.getLogger(JavaApplication2.class.getName()).log(Level.SEVERE, null, ex);
            }
            for (int i = 0; i < texto.length(); i++) {
                if(texto.charAt(i)==vocal){
                    cantidadVocales++;
                }
            sem.release();
            }
        }
    }
    
    public static void main(String[] args) throws InterruptedException {
       texto="Hoy es viernes y el cuerpo lo sabe";
       sem=new Semaphore(1); 
       HiloContador h1= new HiloContador('a');
       HiloContador h2= new HiloContador('e');
       HiloContador h3= new HiloContador('i');
       HiloContador h4= new HiloContador('o');
       HiloContador h5= new HiloContador('u');
       h1.start();
       h2.start();
       h3.start();
       h4.start();
       h5.start();
       
       h1.join();
       h2.join();
       h3.join();
       h4.join();
       h5.join();
        System.out.println("La cantidad de vocales es: " + cantidadVocales);
        }
    }
    

