/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfazGrafica;
import com.mxrck.autocompleter.TextAutoCompleter;
import java.io.File;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

import javax.swing.*;

import com.sun.pdfview.*;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.io.FileUtils;

public class frmMoverRFC extends javax.swing.JFrame {

    PagePanel panelpdf2;
    JFileChooser selector;
    int indice = 0;
    RFCescaneado expe = new RFCescaneado();
    RFCescaneado temp= new RFCescaneado();
    String Nombre_Archivo="";
    DocumentoRFC lisExp;
    String textoCURPCapturado="";
    DocumentoRFC doc=new DocumentoRFC();
    Configuracion ruta=new Configuracion();
    String nombre_archivo="";
    DefaultListModel lista = new DefaultListModel();
    
     
    public frmMoverRFC() {
        initComponents();
        panelpdf2 = new PagePanel();
        panelPDF.add(panelpdf2);
        panelPDF.setLayout(null);
        panelpdf2.setSize(panelPDF.getWidth(), panelPDF.getHeight());
        
        try{
        temp.borrartemporal();/*esto no estaba, yo lo agregue____________________________________________________________*/
        temp.borrarlocal();/*es para borrar los documentos "Residuo que hayan quedado en la carpeta*/
        }
        catch(Exception e){}   
                
        expe.obtenerArchivoRemoto();
        
        System.out.println("Entre a frmMoverRFC");
        expe.copiarACarpetaTemporal(expe.toString());
        System.out.println("valor de expe: "+expe.toString());
        mostrarPDF();
        autocompletaCURP();
        
        
    }
    
    public void mostrarPDF(){
        String curp="";
        curp=txtCapturaCurp.getText();
        ArrayList<DocumentoRFC> Docs = new ArrayList<>();
        DocumentoRFC sigExp;
        DocumentoRFC temporal;
        RFCescaneado tempo = new RFCescaneado();

        //tempo.borrartemporal();
        sigExp = expe.obtenerArchivosExp();
        Nombre_Archivo=sigExp.getNombre();
        nombreArchivo.setText(Nombre_Archivo);

        if(Nombre_Archivo!=""){
                        doc=sigExp;
                        System.out.println("Obtuvo el nombre del archivo.");
                        System.out.println(doc.ruta+doc.nombre);
                        String file = "C:\\escaneos\\Local\\Temporal\\"+doc.nombre;
                        File arch=new File(file);
                        System.out.println("Encontró el siguiente archivo:");
                        System.out.println(file);
                        System.out.println("");
                        if (arch.exists()){
                            System.out.println("El archivo existe");
                        }
                        try { 
                            System.out.println("Entré al try");
                            RandomAccessFile raf = new RandomAccessFile(file, "r");
                            System.out.println("Reconocí el archivo"+file);
                            FileChannel channel = raf.getChannel();
                            System.out.println("Se abrio el canal");
                            ByteBuffer buf = channel.map(FileChannel.MapMode.READ_ONLY, 0, channel.size());
                            System.out.println("Channel map");
                            PDFFile pdffile = new PDFFile(buf);
                            System.out.println("Creando un pdf file");
                            PDFPage page = pdffile.getPage(0);
                            System.out.println("Obteniendo la pagina con "+0);
                           
                            panelpdf2.showPage(page);
                            System.out.println("mostrando el panel pdf2");
                            repaint();
                            System.gc();
                            
                            buf.clear();
                            raf.close();
                            
                            System.gc();
                           
                    } catch (Exception ioe) {
                        JOptionPane.showMessageDialog(null, "Error al abrir el archivo");
                        ioe.printStackTrace();
                    }              
                       
        }
       // tempo.borrartemporal();
    }
    
    public void autocompletaCURP(){
        int x = txtCapturaCurp.getText().length();
        textoCURPCapturado=txtCapturaCurp.getText();
        
        
       TextAutoCompleter txtAutoCompletaCURP = new TextAutoCompleter(txtCapturaCurp);
        //if (txtExpediente.getText().length()>3){
            
            String qconsultaCURP="";

            qconsultaCURP="select curp from curp_rfc where "+ "curp like '"+textoCURPCapturado+"%'";
            Configuracion  bd = new Configuracion();
            bd.conectarBD();
            try {
                bd.statement = bd.connection.createStatement();
                bd.resultSet = bd.statement.executeQuery(qconsultaCURP);
                while (bd.resultSet.next()== true)
                {
                    txtAutoCompletaCURP.addItem(bd.resultSet.getString(1));

                }
                bd.cerrarConexion();
            } catch (SQLException ex) {                   
            }

    }
    

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        ventanaGeneral = new javax.swing.JPanel();
        panelGeneral = new javax.swing.JPanel();
        nombreArchivo = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        listaArchivos = new javax.swing.JList();
        panelPDF = new javax.swing.JPanel();
        botonAnt = new javax.swing.JButton();
        botonSig = new javax.swing.JButton();
        BotonLigarCURPRFC = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtCapturaCurp = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(0, 0, 0));

        ventanaGeneral.setBackground(new java.awt.Color(233, 233, 233));
        ventanaGeneral.setForeground(new java.awt.Color(227, 227, 227));
        ventanaGeneral.setMinimumSize(new java.awt.Dimension(1300, 500));

        panelGeneral.setBackground(new java.awt.Color(255, 255, 255));
        panelGeneral.setForeground(new java.awt.Color(102, 102, 102));
        panelGeneral.setPreferredSize(new java.awt.Dimension(900, 600));

        nombreArchivo.setFont(new java.awt.Font("Arial", 0, 36)); // NOI18N
        nombreArchivo.setForeground(new java.awt.Color(51, 51, 51));
        nombreArchivo.setText(Nombre_Archivo);

        jScrollPane1.setBackground(new java.awt.Color(255, 255, 255));
        jScrollPane1.setBorder(null);
        jScrollPane1.setForeground(new java.awt.Color(255, 255, 255));

        listaArchivos.setBackground(new java.awt.Color(240, 240, 240));
        listaArchivos.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        listaArchivos.setForeground(new java.awt.Color(102, 102, 102));
        listaArchivos.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        listaArchivos.setFocusable(false);
        listaArchivos.setOpaque(false);
        listaArchivos.setRequestFocusEnabled(false);
        listaArchivos.setSelectionBackground(new java.awt.Color(153, 153, 153));
        listaArchivos.setVerifyInputWhenFocusTarget(false);
        listaArchivos.setVisibleRowCount(0);
        jScrollPane1.setViewportView(listaArchivos);

        panelPDF.setBackground(new java.awt.Color(204, 204, 255));

        javax.swing.GroupLayout panelPDFLayout = new javax.swing.GroupLayout(panelPDF);
        panelPDF.setLayout(panelPDFLayout);
        panelPDFLayout.setHorizontalGroup(
            panelPDFLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1008, Short.MAX_VALUE)
        );
        panelPDFLayout.setVerticalGroup(
            panelPDFLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        botonAnt.setBackground(new java.awt.Color(204, 204, 204));
        botonAnt.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/zoomOut.png"))); // NOI18N
        botonAnt.setToolTipText("Clic para alejar");
        botonAnt.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        botonAnt.setFocusable(false);
        botonAnt.setRequestFocusEnabled(false);
        botonAnt.setRolloverEnabled(false);
        botonAnt.setVerifyInputWhenFocusTarget(false);
        botonAnt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonAntActionPerformed(evt);
            }
        });

        botonSig.setBackground(new java.awt.Color(204, 204, 204));
        botonSig.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/zoomIn.png"))); // NOI18N
        botonSig.setToolTipText("Clic para acercar");
        botonSig.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        botonSig.setFocusable(false);
        botonSig.setRequestFocusEnabled(false);
        botonSig.setRolloverEnabled(false);
        botonSig.setVerifyInputWhenFocusTarget(false);
        botonSig.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonSigActionPerformed(evt);
            }
        });

        BotonLigarCURPRFC.setBackground(new java.awt.Color(255, 255, 255));
        BotonLigarCURPRFC.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        BotonLigarCURPRFC.setText("Ligar CURP a RFC");
        BotonLigarCURPRFC.setActionCommand("Asignar CURP al RFC");
        BotonLigarCURPRFC.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(238, 100, 100), 1, true));
        BotonLigarCURPRFC.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        BotonLigarCURPRFC.setFocusable(false);
        BotonLigarCURPRFC.setRequestFocusEnabled(false);
        BotonLigarCURPRFC.setRolloverEnabled(false);
        BotonLigarCURPRFC.setVerifyInputWhenFocusTarget(false);
        BotonLigarCURPRFC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BotonLigarCURPRFCActionPerformed(evt);
            }
        });

        jLabel1.setText("Alejar área Ampliada");

        jLabel2.setText("Ampliar Área");

        jLabel3.setText("Teclea el CURP:");

        txtCapturaCurp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCapturaCurpActionPerformed(evt);
            }
        });
        txtCapturaCurp.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtCapturaCurpKeyTyped(evt);
            }
        });

        javax.swing.GroupLayout panelGeneralLayout = new javax.swing.GroupLayout(panelGeneral);
        panelGeneral.setLayout(panelGeneralLayout);
        panelGeneralLayout.setHorizontalGroup(
            panelGeneralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelGeneralLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelGeneralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelGeneralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 211, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelGeneralLayout.createSequentialGroup()
                            .addComponent(botonSig)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(29, 29, 29)))
                    .addGroup(panelGeneralLayout.createSequentialGroup()
                        .addComponent(botonAnt)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(BotonLigarCURPRFC, javax.swing.GroupLayout.PREFERRED_SIZE, 211, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(panelGeneralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(txtCapturaCurp, javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 211, Short.MAX_VALUE)))
                .addGroup(panelGeneralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelGeneralLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(panelPDF, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(panelGeneralLayout.createSequentialGroup()
                        .addGap(46, 46, 46)
                        .addComponent(nombreArchivo)))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27))
        );
        panelGeneralLayout.setVerticalGroup(
            panelGeneralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelGeneralLayout.createSequentialGroup()
                .addGroup(panelGeneralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelGeneralLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(nombreArchivo)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panelGeneralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 616, Short.MAX_VALUE)
                            .addComponent(panelPDF, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(panelGeneralLayout.createSequentialGroup()
                        .addGap(89, 89, 89)
                        .addGroup(panelGeneralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(botonSig, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(26, 26, 26)
                        .addGroup(panelGeneralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(botonAnt, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(73, 73, 73)
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(txtCapturaCurp, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(134, 134, 134)
                        .addComponent(BotonLigarCURPRFC, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(173, 173, 173)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout ventanaGeneralLayout = new javax.swing.GroupLayout(ventanaGeneral);
        ventanaGeneral.setLayout(ventanaGeneralLayout);
        ventanaGeneralLayout.setHorizontalGroup(
            ventanaGeneralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ventanaGeneralLayout.createSequentialGroup()
                .addComponent(panelGeneral, javax.swing.GroupLayout.PREFERRED_SIZE, 1311, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 9, Short.MAX_VALUE))
        );
        ventanaGeneralLayout.setVerticalGroup(
            ventanaGeneralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ventanaGeneralLayout.createSequentialGroup()
                .addComponent(panelGeneral, javax.swing.GroupLayout.PREFERRED_SIZE, 687, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 22, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(ventanaGeneral, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(ventanaGeneral, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void BotonLigarCURPRFCActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BotonLigarCURPRFCActionPerformed
        String curp="";
        curp=txtCapturaCurp.getText();
        String ct="";
        String nombre_archivo="";
        String sigExp="";
        String qconsultaCURP="";      
        RFCescaneado NombreArchivo= new RFCescaneado();
        nombre_archivo=NombreArchivo.obtenerArchivosExp().nombre;
        
        
        //nombreArchivo.setText(Nombre_Archivo);
        System.out.println("Nombre del archivo obtenido de obtenerArchivosExp"+nombre_archivo);
        qconsultaCURP="select ct from curp_rfc where curp ='" + curp + "'";
        System.out.println(""+qconsultaCURP);
        Configuracion  bd = new Configuracion();
        bd.conectarBD();
        try {
                bd.statement = bd.connection.createStatement();
                System.out.println("cree el statement:");
                bd.resultSet = bd.statement.executeQuery(qconsultaCURP);
                if (bd.resultSet.next()){
                    ct=bd.resultSet.getString("ct");
                    System.out.println("Ct correspondiente de la bd al curp :"+ct);
                }
                bd.cerrarConexion();
        } 
        catch (SQLException ex) 
        {   
            System.out.println("Entre al catch");
        }
        
        
        doc = this.RenombrarArchivo(ct, nombre_archivo, curp);
        
        File origen = new File("C:\\escaneos\\Local\\CarpetaRFC\\"+doc.nuevoNombre);
        File dest = new File(doc.rutades+doc.nuevoNombre);
        File tempo = new File ("C:\\escaneos\\Local\\Temporal\\"/*+doc.nuevoNombre*/);
        //File local = new File ()
        try {

            FileUtils.moveFile(origen,dest);

        } catch (IOException ex) {
            JOptionPane.showMessageDialog(botonAnt, "Ya existe un RFC ligado a ese CURP favor de revisar que sea correcto");
            Logger.getLogger(frmMoverRFC.class.getName()).log(Level.SEVERE, null, ex);
        }

            if (tempo.listFiles().length>1)
            {
                System.out.println("Existen documentos en temporal ");
                 File directorio = new File("C:\\escaneos\\Local\\Temporal\\");
                 File [] ficheros = directorio.listFiles();
                 System.out.println("Borre el archivo temporal"+ ficheros.toString());
    
            
            ficheros[0].delete();
            System.out.println("Borraré: "+ficheros[0]);
            }

        expe.obtenerArchivoRemoto();
        
        expe.copiarACarpetaTemporal(expe.toString());
        expe.moverACarpetaLocal(expe.toString());
        txtCapturaCurp.setText("");
        mostrarPDF();

        
        
    }//GEN-LAST:event_BotonLigarCURPRFCActionPerformed

    private void botonSigActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonSigActionPerformed
        panelpdf2.useZoomTool(true);
    }//GEN-LAST:event_botonSigActionPerformed

    private void botonAntActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonAntActionPerformed
        panelpdf2.setClip(null);
        panelpdf2.useZoomTool(false);
    }//GEN-LAST:event_botonAntActionPerformed

    private void txtCapturaCurpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCapturaCurpActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCapturaCurpActionPerformed

    private void txtCapturaCurpKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCapturaCurpKeyTyped
        char caracter = evt.getKeyChar();
        if (Character.isDigit(caracter) || Character.isLetter(caracter))
        {
            String texto = txtCapturaCurp.getText()+caracter;
            txtCapturaCurp.setText(texto.toUpperCase());
            evt.consume();
            this.repaint();            
        }
        else
        {
            getToolkit().beep();
            evt.consume();        
        }            
    }//GEN-LAST:event_txtCapturaCurpKeyTyped

    public DocumentoRFC RenombrarArchivo(String ct, String nombrearchivo, String curp){
        nombre_archivo=nombrearchivo;
        if(!nombre_archivo.equals("")){
            System.out.println("hola"+curp);
            String nombreFinal=curp+"_"+"RFC"+".pdf";
            doc.nuevoNombre = nombreFinal;
            doc.setRuta(ruta.carpetaLocal+"CarpetaRFC\\");
            doc.rutades=ruta.carpetaRemota+"aceptados\\";
            //Aqui es donde debemos de cambiar lo de la carpeta remota
            doc = doc.renombrar();
        }
        return doc;
    }
          
    public static void main(String args[]) {

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                frmMoverRFC VentanaPrincipal = new frmMoverRFC();
                VentanaPrincipal.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BotonLigarCURPRFC;
    private javax.swing.JButton botonAnt;
    private javax.swing.JButton botonSig;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JList listaArchivos;
    private javax.swing.JLabel nombreArchivo;
    private javax.swing.JPanel panelGeneral;
    private javax.swing.JPanel panelPDF;
    private javax.swing.JTextField txtCapturaCurp;
    private javax.swing.JPanel ventanaGeneral;
    // End of variables declaration//GEN-END:variables
}
