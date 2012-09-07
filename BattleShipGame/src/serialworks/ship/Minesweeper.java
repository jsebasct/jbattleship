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
public class Minesweeper extends Ship{

    private final String resource = "/serialworks/ship/image/minesweeperShow.PNG";
    
    public Minesweeper()
    {
        super();
        this.image = new ImageIcon( getClass().getResource( resource ) );
        size = 2;
        //this.set
    }
    
    public ShipType getType(){
        return ShipType.MINESWEEPER;
    }
    
//    public static void main(String[] args) {
//        
//        ImageIcon aux = (new Minesweeper() ).getImage();
//        System.out.println("H:"+ aux.getIconHeight() + ", W:"+ aux.getIconWidth());
//    }
}
