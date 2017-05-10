/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Control;

import Model.Cancion;
import java.util.ArrayList;

/**
 *
 * @author SheshoVega
 */
public class test {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Control ctrl = new Control();
        
        ArrayList<Cancion> canciones = null;
        String valor = "m";
//        canciones = ctrl.getTodasLasCanciones();
        canciones = ctrl.getCanciones(valor);
        for(Cancion cancion : canciones){
            System.out.println(cancion.toString());
        }
    }
    
}
