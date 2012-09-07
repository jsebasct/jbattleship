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
public class Frigate extends Ship{
    
    private final String resource = "/serialworks/ship/image/frigateShow.PNG";
    
    public Frigate()
    {
        super();
        this.image = new ImageIcon( getClass().getResource( resource ) );
        size = 3;
    }
    public ShipType getType(){
        return ShipType.FRIGATE;
    }
//    public static void main(String[] args) {
//        
//        ImageIcon aux = (new Frigate() ).getImage();
//        System.out.println("H:"+ aux.getIconHeight() + ", W:"+ aux.getIconWidth());
//    }
    
}
