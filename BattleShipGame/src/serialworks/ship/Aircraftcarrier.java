/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package serialworks.ship;

import javax.swing.ImageIcon;

/**
 *
 * @author bastian
 */
public class Aircraftcarrier extends Ship {
    
    private final String resource = "/serialworks/ship/image/aircraftcarrier.PNG";
    
    public Aircraftcarrier()
    {
        super();
        this.image = new ImageIcon( getClass().getResource( resource ) );
        size = 5;
    }
    
    public ShipType getType(){
        return ShipType.AIRCRAFTCARRIER;
    }
    
//    public static void main(String[] args) {
//        
//        ImageIcon aux = (new Aircraftcarrier() ).getImage();
//        System.out.println("H:"+ aux.getIconHeight() + ", W:"+ aux.getIconWidth());
//    }
}
