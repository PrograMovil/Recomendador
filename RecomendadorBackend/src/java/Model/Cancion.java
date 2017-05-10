/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

/**
 *
 * @author SheshoVega
 */
public class Cancion {
    private int id;
    private String nombre;
    private String artista;
    private String album;
    private String genero;

    public Cancion(int id, String nombre, String artista, String album, String genero) {
        this.id = id;
        this.nombre = nombre;
        this.artista = artista;
        this.album = album;
        this.genero = genero;
    }

    public Cancion(String nombre, String artista, String album, String genero) {
        this.nombre = nombre;
        this.artista = artista;
        this.album = album;
        this.genero = genero;
    }

    public Cancion() {
        this.id = -1;
        this.nombre = "";
        this.artista = "";
        this.album = null;
        this.genero = null;
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getArtista() {
        return artista;
    }

    public void setArtista(String artista) {
        this.artista = artista;
    }

    public String getAlbum() {
        return album;
    }

    public void setAlbum(String album) {
        this.album = album;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    @Override
    public String toString() {
        return "Cancion {" + " id : " + id + ", nombre : " + nombre + ", artista : " + artista + ", album : " + album + ", genero : " + genero + '}';
    }  
    
}
