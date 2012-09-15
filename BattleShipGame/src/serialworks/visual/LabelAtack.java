/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package serialworks.visual;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.MouseEvent;
import javax.swing.ImageIcon;

/**
 *
 * @author bastian
 */
public class LabelAtack extends MiLabel {

    private BattleField fieldOwner = null;
    private final String iconString = "/serialworks/visual/image/herido.PNG";
    
    private ImageIcon icon = new ImageIcon( iconString );
    
    public LabelAtack(int x, int y) {

        super(x, y);
    }
    
    public LabelAtack( BattleField fieldOwner, int x, int y) {

        super(x, y);
        this.fieldOwner = fieldOwner;
    }
    
    public void estadoAcertado() {
        
        this.setBackground( Color.PINK );
        this.setForeground( Color.black );
        //System.out.println(" Hemos puesto estado hacertado " );
    }
    public void estadoIntento() {
        
        this.setBackground( Color.ORANGE );
        this.setForeground( Color.ORANGE );
        this.setIcon(icon);
        this.setText("o");
        
        System.out.println(" estado intento en  " + posX + ", "+ posY);
    }
    
    public void estadoDaniado(  )
    {        
        this.setBackground( Color.RED );
        //this.setForeground( Color.black );
        //System.out.println(" Hemos puesto estado dañado " );
    }

    @Override
    protected void processMouseEvent(MouseEvent evt) {

        if (listener) {
            switch (evt.getID()) {
                case MouseEvent.MOUSE_ENTERED:
                    this.setBorder(mouseBorder);
                    //if( vetado != true ) {

                    //mapContainer.updatePos(posX, posY);
                    //System.out.println("Mouse entro");
                    
                    break;

                case MouseEvent.MOUSE_EXITED:
                    this.setBorder(defaultBorder);
                    //mapContainer.mouseSalio();
                    //vetado = false;
                    break;

                case MouseEvent.MOUSE_CLICKED:

                    //vetado = true;
                    //this.setBorder(mouseBorder);
                    System.out.println("Click en: " + posX + ", " + posY);
                    //if( fieldOwner != null )
                    fieldOwner.registrarPos(  new Dimension(posX, posY ) );
                    //fieldOwner.registrarPos(  posX, posY );
                    break;
            }
        }
    }
}
