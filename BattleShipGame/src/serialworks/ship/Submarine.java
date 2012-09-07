/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package serialworks.ship;

//import serialworks.ship.image;
import javax.swing.ImageIcon;

/**
 *
 * @author bastian
 */
public class Submarine extends Ship {
        
    private final String resource = "/serialworks/ship/image/submarineShow.PNG";
    
    public Submarine()
    {
        this.image = new ImageIcon( getClass().getResource( resource ) );
        size = 3;
    }
    
    public ShipType getType(){
        return ShipType.SUBMARINE;
    }
    /*
    public static void main(String[] args) {
        Submarine s = new Submarine();
        ImageIcon aux = s.getImage();
        System.out.println("H:"+ aux.getIconHeight() + ", W:"+ aux.getIconWidth());
    }
    */
    
}
