/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package serialworks.comm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author bastian
 */
public class ClientSender
{
    static final int DEFAULT_PORT = 11000;
    static final String HANDSHAKE = "naval";
    
    private Socket conection;
    
    private PrintWriter ps;
    private BufferedReader reader;
    
    private String recibido;    
    private int port;
    private String ipServer;

    /**
     * Hay que arreglar esto ahorita se conecta a la misma maquina
     * @param ip
     */
    public ClientSender( /*BattleField bf*/int ip) {

        //this.bf = bf;

        //this.port = puerto;
        ipServer = "localhost";
        connect();
    }
    
    /**
     * Este metodo ya intenta comunicarse con su servidor, un saludo en realidad
     */
    private void connect()
    {
        port = DEFAULT_PORT;

        try {

            System.out.println("Connecting to " + ipServer + " on port " + port);

            conection = new Socket(ipServer, port);

            reader = new BufferedReader(new 
                                          InputStreamReader
                                                (conection.getInputStream()));
            System.out.println("obtuve el reader ");
            
            ps = new PrintWriter(conection.getOutputStream());
            System.out.println("obtuve el writer ");
            
            ps.println(HANDSHAKE);
            ps.flush();
            
            String aux = reader.readLine();            
            System.out.println( "this fue leido en cliente: " + aux );
            
            if(  HANDSHAKE.equals ( aux  ) )
            {
                System.out.println( "----------------------" );
                System.out.println("SALUDO EN CLIENTE IGUAL");
                System.out.println( "----------------------" );
            }


        } catch (Exception e) {
            System.out.println("Error en clientSender class...");
            System.exit(-1);
        }
    }
    
    
    /**
     * Utilizado exclusivamente para enviar Un intento 
     * @param x posX
     * @param y posY
     * @return  el resultado de el intento dado
     */
    public String comunicationSend(int x, int y)
    {
        String res = "fallaste";
        
        ps.println("" + x + "" + y);
        ps.flush();
        
        //ahora espero la respuesta
        try {

            System.out.println("Esperando respuesta de servidor...");
            
            recibido = reader.readLine();
            
            if (recibido.equalsIgnoreCase("ganaste"))
            {
                System.out.println("FIN DEL JUEGO");
                System.out.println("Ganaste!!!");
                
                JOptionPane.showMessageDialog(null, "Felicidades Ganaste!!!;)");
                
                this.sendAnswer("perdiste");
                
                ps.close();
                reader.close();
                conection.close();
                
                res = "ganaste";
                
            } else if (recibido.equalsIgnoreCase("acertaste")) {
                res = "acertaste";
                System.out.println("El cliente da de nuevo");
            }
        } catch (IOException ex) {
            Logger.getLogger(ClientSender.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("ClientServerError");
        }
                
        return res;
    }

    //public boolean comunicationReceive( )
    public String comunicationReceive() {
        
        String res = "";
        
        try {

            System.out.println("WAITING...comunication receive");
            recibido = reader.readLine();
            
            if( recibido.equals("perdiste") ){
                System.out.println("FIN DEL JUEGO");
                System.out.println("Intenta de nuevo!!!");
                
                JOptionPane.showMessageDialog(null, "Intenta de nuevo!!!;)");
                
                ps.close();
                reader.close();
                conection.close();
                
                
                System.exit( 0 );
                
                res = "perdiste";
                
                
                
            }else{
            
                res = recibido;
            
                //-------SOLO PRUEBA---------------------
                System.out.println("\t" + res);
                //---------------------------------------
            }
            
        } catch (IOException ex) {
            Logger.getLogger(ClientSender.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return res;
    }
    
    public void sendAnswer( String msm )
    {
        ps.println( msm );
        ps.flush();        
    }
    
    public void disconect()
    {
        //conection.close();
    }
    
}
