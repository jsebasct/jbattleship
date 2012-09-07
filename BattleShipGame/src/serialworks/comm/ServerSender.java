/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package serialworks.comm;

//import java.net.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author bastian
 */
public class ServerSender {  
    
    static final int DEFAULT_PORT = 11000;
    static final String HANDSHAKE = "naval";
    
    private ServerSocket server;
    private Socket enemigo;
    
    private String recibido;
    
    private BufferedReader reader;
    private PrintWriter pw;
    
    public ServerSender( ){       

        int port = DEFAULT_PORT;
        escuchar(port);
    }
    
    private void escuchar( int puerto ){
        try {

            server = new ServerSocket(puerto);
            System.out.println("Escuchando en puerto " + server.getLocalPort());
            
            enemigo = server.accept();            
            System.out.println("Cliente aceptado ");            
            
            server.close();//no queremos aceptar mas clientes               
            System.out.println("Cerramos la conexion ");
            
            reader = new BufferedReader(new 
                                InputStreamReader(
                                                    enemigo.getInputStream()));            
            System.out.println("obtuve el reader ");
            
            pw = new  PrintWriter( enemigo.getOutputStream() );
            System.out.println("obtuve el writer ");
            
            pw.println(HANDSHAKE);  // Send handshake to client.
            pw.flush();
            
            String aux = reader.readLine();
            System.out.println( "esto fue leido en el servidor: " + aux );
            
            if(  HANDSHAKE.equals ( aux  ) )
            {
                System.out.println( "----------------------" );
                System.out.println("Saludo IGUAL en servidor");
                System.out.println( "----------------------" );
            }            
            
        } catch (IOException ex) {
            Logger.getLogger(ServerSender.class.getName()).log(Level.SEVERE, null, ex);            
            System.out.println("Error en ServerSender...");
            //System.err(  );
        }        
    }
        
    //public void comunicationSend(int x, int y) {
    public String comunicationSend(int x, int y) {
        
        String res = "fallaste";
        
        pw.println("" + x + "" + y);
        pw.flush();
        
        
        //ahora espero la respuesta        

        try {

            System.out.println("Esperando la respuesta en Servidor...");
            
            recibido = reader.readLine();

            if (recibido.equalsIgnoreCase("ganaste"))
            {
                System.out.println("Ganaste!!!");
                
                this.sendAnswer("perdiste");
                
                JOptionPane.showMessageDialog(null, "Felicidades Ganaste!!!;)");
                
                pw.close();
                reader.close();
                enemigo.close();
                
                res = "ganaste";
                
            } else if (recibido.equalsIgnoreCase("acertaste")) {
                res = "acertaste";
                System.out.println("Das de nuevo");                
            }
            
        } catch (IOException ex) {
            Logger.getLogger(ClientSender.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("ClientServer");
        }
                
        return res;
    }

    //public boolean comunicationReceive( )
    public String comunicationReceive() {
        
        String res = "";
        
        try {

            System.out.println("esperando por la comunicacion...");
            recibido = reader.readLine();
            
            if( recibido.equals("perdiste") ){
                System.out.println("FIN DEL JUEGO");
                System.out.println("Intenta de nuevo!!!");
                
                JOptionPane.showMessageDialog(null, "Intenta de nuevo!!!;)");
                
                pw.close();
                reader.close();
                enemigo.close();
                
                System.exit( 0 );
                
                res = "perdiste";
                
            }
            else{
                res = recibido;
                System.out.println("Recibido..." + res );    
            }
            
        } catch (IOException ex) {
            Logger.getLogger(ClientSender.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return res;
    }
    
    public void sendAnswer( String msm )
    {
        pw.println( msm );
        pw.flush();        
    }
    
    public void disconect()
    {
        try {
            enemigo.close();
        } catch (IOException ex) {
            Logger.getLogger(ServerSender.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("En disconnect");
        }
    }
    
}
