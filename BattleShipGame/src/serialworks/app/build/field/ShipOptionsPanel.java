/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package serialworks.app.build.field;

import serialworks.ship.ShipType;
import serialworks.data.ShipContainer;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;


/**
 *
 * @author bastian
 */
public class ShipOptionsPanel extends JPanel implements ActionListener {
    
    private ImageIcon[] imageShips;
    private JButton[] buttons;
    private ShipContainer shipContainer;
    private JButton complete;
    
    public ShipOptionsPanel( int tam , JButton complete)
    {        
        this.complete = complete;
        initComponents( tam );
        loadComponents();
        addComponents();
        
        this.setBounds( 0, 0, 140, 400 );
        //probarEnVentana();//sin esto no funcionaba hasta que le di setBounds()
    }
    
    private void initComponents( int tam ) {
        
        shipContainer = new ShipContainer(this);
        this.setLayout( new GridLayout(tam, 1) );
        buttons = new JButton[tam];
        imageShips = new ImageIcon[tam];
    }
    
    private void loadComponents() {
        
        imageShips = shipContainer.getImageShips();
        Color color = new Color(0, 103, 102);
                
        for( int i = 0; i < buttons.length; i++ )  {      
            buttons[i] = new JButton();
            buttons[i].setBackground( color );
            buttons[i].addActionListener( this );
            buttons[i].setIcon( imageShips[i] );
        }
        
        buttons[0].setActionCommand( ShipType.AIRCRAFTCARRIER.toString() );
        buttons[1].setActionCommand( ShipType.BATTLESHIP.toString() );
        buttons[2].setActionCommand( ShipType.FRIGATE.toString() );
        buttons[3].setActionCommand( ShipType.MINESWEEPER.toString() );
        buttons[4].setActionCommand( ShipType.SUBMARINE.toString() );
        
    }
    
    private void addComponents() {
        
        for( JButton b : buttons )
        {
            this.add( b );
        }
    }
    
    private void probarEnVentana()
    {
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(3);
        //frame.setBounds( 200, 10, 200, 300 );
        frame.getContentPane().add(this);
        frame.setVisible(true);
    }
    
    
    public void actionPerformed(ActionEvent e) {
        
        JButton aux = (JButton)e.getSource();
        String command = aux.getActionCommand();
		
	//--------Eso he modificado para arreglar los problemas de la interfaz-
	shipContainer.getMap().habilitar(true);
	//-------------------------------------------------------------------
        
        if( command.equals( ShipType.AIRCRAFTCARRIER.toString() ) )  {
            System.out.println("AircraftCarrier");
            shipContainer.updateSelectedShip( ShipType.AIRCRAFTCARRIER );
                    
        }else if( command.equals( ShipType.BATTLESHIP.toString() ) ){
            System.out.println("BattleShip");
            shipContainer.updateSelectedShip( ShipType.BATTLESHIP );
            
        }else if( command.equals( ShipType.FRIGATE.toString() ) ){
            System.out.println("Frigate");
            shipContainer.updateSelectedShip( ShipType.FRIGATE );
            
        }else if( command.equals( ShipType.MINESWEEPER.toString() ) ){
            System.out.println("MinesSweeper");
            shipContainer.updateSelectedShip( ShipType.MINESWEEPER );
            
        }else if( command.equals( ShipType.SUBMARINE.toString() ) ){
            System.out.println("Submarine");
            shipContainer.updateSelectedShip( ShipType.SUBMARINE );            
        }
        
        
    }
    
    private void verificarFin()
    {
        boolean res = false;
        int i = 0;
        
        while( ( i < buttons.length ) && ( res == false )  )
        {
            if( buttons[i].isEnabled() == true )
                res = true;
            i++;
        }
                
        if( res == false )
        {
            //shipContainer.getMap().disable();
            //shipContainer.getMap().habilitar( false );
            //shipContainer.getMap().setTamShip(0);
            System.out.println("\t Todos estan desabilitados");
            complete.setEnabled(true);
        }
    }
    
    public ShipContainer getShipContainer(){
            return shipContainer;
    }
    
    public void disableOption( ShipType st, int x )
    {
        buttons[x].setEnabled( false );
        verificarFin();
    }
    
//    public static void main(String[] args) {
//        new ShipOptionsPanel( 5 );
//    }

}
