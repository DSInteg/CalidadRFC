/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfazGrafica;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;
import javax.swing.JOptionPane;

/**
 *
 * @author Thinkpad T430
 */
public class RFCescaneado {
    
    Configuracion config=new Configuracion();
    String error="";
    
    public RFCescaneado(){}

    
    //public void obtenerArchivoRemoto(int numero){
    public int obtenerArchivoRemoto(){     
        
        String ruta = config.carpetaRemota+"CarpetaRFC\\";
        File f = new File(ruta);
        ArrayList<String> names = new ArrayList<>(Arrays.asList(f.list()));
        if (names.isEmpty()){
            
        }
        else{
            System.out.println("");
            System.out.println("____________________________Aqui es donde repeti los métodos___________________________________________");
            copiarACarpetaTemporal(names.get(0));
            moverACarpetaLocal(names.get(0));
        }
        return names.size();
    }  
    
    public DocumentoRFC obtenerArchivosExp(){

        System.out.println("Entre a obtenerArchivosExp");
        String ruta = config.carpetaTemporal/**/;
        DocumentoRFC nombredoc = null;   
        File f = new File(ruta);
        String[] arregloauxdoc = f.list();
        
        if (arregloauxdoc.length>1){
            
            nombredoc = new DocumentoRFC();
                nombredoc.setNombre(arregloauxdoc[1/*0*/]);
                nombredoc.setRuta(ruta);
                System.out.println("Entre a : ObtenerArchivosExp");
                
                File directorio = new File("C:\\escaneos\\Local\\Temporal\\");
            File [] ficheros = directorio.listFiles();
            System.out.println("Entré a borrar temporal");
   
            ficheros[0].delete();
            System.out.println("Borraré: "+ficheros[0]);
        }
        else
        {
            if (arregloauxdoc.length>0)      
            {
                nombredoc = new DocumentoRFC();
                nombredoc.setNombre(arregloauxdoc[0/*0*/]);
                nombredoc.setRuta(ruta);
                System.out.println("Entre a : ObtenerArchivosExp");
            }
            else
            {
                JOptionPane.showMessageDialog(null, "No hay documentos que obtener de la carpeta");
            }    
        }
        return nombredoc;    
    }

public boolean moverACarpetaLocal(String narchivo){
        
        try{
            File archivo=new File(config.carpetaRemota+"CarpetaRFC\\"+narchivo);
            File destino=new File(config.carpetaLocal+"CarpetaRFC"+"\\"+narchivo);
            
            System.out.println("Origen: "+archivo.toString());
            System.out.println("Destino: "+destino.toString());
            
            File rutaDestino=new File(config.carpetaLocal+"\\CarpetaRFC"+"\\");
            
            if(archivo.exists()){ 
                
                if(!rutaDestino.exists()){
                    rutaDestino.mkdir();
                }
                
                if(!destino.exists()){

                    destino.createNewFile();
                    FileChannel in = (new FileInputStream(archivo)).getChannel();
                    FileChannel out = (new FileOutputStream(destino)).getChannel();
                    in.transferTo(0, archivo.length(), out);
                    in.close();
                    out.close();
                    archivo.delete();
                }
                
            }else{
                System.out.println("No existe el archivo");
            }
            
        }catch(FileNotFoundException fe){
            System.out.println("No existe el archivo");
            return false;
        }catch(IOException fe){
            System.out.println("No se pudo copiar el archivo");
            return false;
        }
        
        return true;
   
    }
    
    public boolean copiarACarpetaTemporal(String narchivo){
        
        try{
           
            
            System.out.println("entre al método de copiar a carpeta temporañ");
            
            File origenrem=new File(config.carpetaRemota+"CarpetaRFC\\"+narchivo);
            File destinotemp=new File(config.carpetaTemporal+narchivo);          
            
            System.out.println("Origen: "+origenrem.toString());
            System.out.println("Destino: "+destinotemp.toString());
            
            File rutaDestinotemp=new File(config.carpetaTemporal);
            

            if(origenrem.exists()){ 
                
                if(!rutaDestinotemp.exists()){
                    rutaDestinotemp.mkdir();
                }
                
                if(!destinotemp.exists()){
                    destinotemp.createNewFile();
                    FileChannel in = (new FileInputStream(origenrem)).getChannel();
                    FileChannel out = (new FileOutputStream(destinotemp)).getChannel();
                    in.transferTo(0,origenrem.length(), out);
                    in.close();
                    out.close();
                }
                
            }else{
                    error="Archivo ya existe";
                    return false;
            }
            
        }catch(FileNotFoundException fe){
            System.out.println("No existe el archivo");
            return false;
        }catch(IOException fe){
            System.out.println("No se pudo copiar el archivo");
            return false;
        }
        
        return true;
   
    }
    
    
   public void borrartemporal(){

       File directorio = new File("C:\\escaneos\\Local\\Temporal\\");
       File [] ficheros = directorio.listFiles();
       System.out.println("Entré a borrar temporal");
       ficheros[0].delete();
       System.out.println("Borraré: "+ficheros[0]);
    }
    
   public void borrarlocal(){

       File directorio = new File("C:\\escaneos\\Local\\CarpetaRFC\\");
       File [] ficheros = directorio.listFiles();
       System.out.println("Entré a borrar temporal");
       
       ficheros[0].delete();
       System.out.println("Borraré: "+ficheros[0]);
    }
   
    public String obtenerError(){
        return error;
    }
    
   
}

