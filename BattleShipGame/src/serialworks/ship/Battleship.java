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
public class Battleship extends Ship{
    
    private final String resource = "/serialworks/ship/image/battleshipShow.PNG";
    
    public Battleship()
    {
        super();
        this.image = new ImageIcon( getClass().getResource( resource ) );
        size = 4;
    }
    
    public ShipType getType(){
        return ShipType.BATTLESHIP;
    }
    
    public static void main(String[] args) {
        
        ImageIcon aux = (new Battleship() ).getImage();
        System.out.println("H:"+ aux.getIconHeight() + ", W:"+ aux.getIconWidth());
    }
    
}
