/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package interfazGrafica;

import java.io.File;

/**
 *
 * @author Thinkpad T430
 */
class DocumentoRFC {
    String nombre;
    String ruta;
    String rutatemporal;
    String rutades;
    String nuevoNombre;

    
    public DocumentoRFC(){

    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getRuta() {
        return ruta;
    }

    public void setRuta(String ruta) {
        this.ruta = ruta;
    }
    
    public DocumentoRFC renombrar(){ 
        System.out.println("Entre a renombrar");
        File archivo=new File(ruta+nombre);
        File nuevo=new File(rutades+this.nuevoNombre); 
        File nuevo_nombre = new File(ruta+nuevoNombre);
        System.out.println(archivo.toString());
        System.out.println(nuevo.toString());
        
        int pre=nuevoNombre.indexOf(".");
        
        String nombre=nuevoNombre.substring(0,pre);
        String prefijo=nuevoNombre.substring(pre);
        System.out.println("********************");
        System.out.println(archivo.toString());
        System.out.println(this.nombre);
        System.out.println(nuevoNombre);
        System.out.println(this.ruta);
        System.out.println(this.rutades);
        if(archivo.renameTo(nuevo_nombre)){System.out.println("SI lo renombro");}
        System.out.println(archivo.toString());
        System.out.println("********************");
        //le quitamos el prefijo
        pre=nuevoNombre.indexOf(".");
        String soloNombre=nuevoNombre.substring(0,pre);
        
        return this;
        
    }
    
}
