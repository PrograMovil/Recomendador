/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Control;

import AccesoDatos.Canciones;
import AccesoDatos.Database;
import Model.Cancion;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author SheshoVega
 */
public class Control {
    private Database db;
    private Canciones canciones;

    public Control() {
        this.db = new Database();
        this.canciones = new Canciones(db);
    }
    
    public ArrayList<Cancion> getCanciones(String valor){
        try {
            return canciones.obtenerCanciones(valor);
        } catch (Exception ex) {
            Logger.getLogger(Control.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public ArrayList<Cancion> getTodasLasCanciones(){
        try {
            return canciones.obtenerTodasLasCanciones();
        } catch (Exception ex) {
            Logger.getLogger(Control.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
