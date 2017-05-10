/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AccesoDatos;

import Model.Cancion;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author SheshoVega
 */
public class Canciones extends AccesoDatos {
    
    public Canciones(Database DB) {
        super(DB);
    }
    
    private Cancion toCancion(ResultSet rs) throws Exception {
        Cancion obj = new Cancion();
        obj.setId(rs.getInt("id"));
        obj.setNombre(rs.getString("nombre"));
        obj.setArtista(rs.getString("artista"));
        obj.setAlbum(rs.getString("album"));
        obj.setGenero(rs.getString("genero"));
        return obj;
    }
    
    public Cancion obtenerPorId(int id) throws Exception{
        String tableName = "canciones";
        String param = "id = '%s'";
        param = String.format(param, id);
        ResultSet rs = super.obtener(tableName, param);
        if (rs.next()) {
            return toCancion(rs);
        } else {
            return null;
        }
    }
    
    public ArrayList<Cancion> obtenerCanciones(String valor) throws Exception{
        String tableName = "canciones";
        String param = "nombre like '%%%" + valor +"%%%' or artista like '%%%" + valor +"%%%' or album like '%%%" + valor +"%%%' or genero like '%%%" + valor +"%%%'";
//        System.out.println(param);
        ResultSet rs = super.obtener(tableName, param);
        ArrayList<Cancion> lista=new ArrayList();
        while (rs.next()) {
            lista.add(toCancion(rs));
        }
        return lista;
    }
    
    public ArrayList<Cancion> obtenerTodasLasCanciones() throws Exception{
        String tableName = "canciones";
        ResultSet rs = super.obtenerTodo(tableName);
        ArrayList<Cancion> lista=new ArrayList();
        while (rs.next()) {
            lista.add(toCancion(rs));
        }
        return lista;
    }
}
