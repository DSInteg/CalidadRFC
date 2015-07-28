/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package interfazGrafica;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Eli
 *
 */
public class Configuracion {
    
    public Statement statement;
    public ResultSet resultSet;
    //final String carpetaLocal="\\\\ServExpedientes\\";
    final String carpetaLocal = "c:\\escaneos\\Local\\";
    final String carpetaRemota = "c:\\escaneos\\";
    final String carpetaTemporal = "c:\\escaneos\\Local\\Temporal\\";
    final String USUARIO="root";
    final String PASSWORD="";
    final String DATABASE_URL="jdbc:mysql://127.0.0.1/curp_uset";
    
    public Connection connection;
    public Configuracion(){}
    
     public void conectarBD()
    {
        try
        {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(DATABASE_URL,USUARIO,PASSWORD);       
        }
        catch (ClassNotFoundException | SQLException sqlException)
        {
            sqlException.printStackTrace();
        }       
    }  
     public void cerrarConexion()
    {
        try 
        {
            connection.close();
        } 
        catch (SQLException error) 
        {
            Logger.getLogger(Configuracion.class.getName()).log(Level.SEVERE, null, error);
        }
    }   
    
}
