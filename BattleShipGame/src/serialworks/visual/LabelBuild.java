/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package serialworks.visual;

import java.awt.event.MouseEvent;

/**
 *
 * @author bastian
 */
public class LabelBuild  extends MiLabel
{
    private Map mapContainer;
    
    public LabelBuild(Map container, int x, int y) {
        
        super( x, y );
        mapContainer = container;
    }    
    
    @Override
    protected void processMouseEvent(MouseEvent evt) {
        
        if (oyente)
        {
            switch ( evt.getID() )
            {
                case MouseEvent.MOUSE_ENTERED:
                    //this.setBorder(mouseBorder);
                    //if( vetado != true ) {                        
                        mapContainer.updatePos(posX, posY);                   
                    //}
                    break;
				
                case MouseEvent.MOUSE_EXITED:
                    this.setBorder(defectBorder);
                    mapContainer.mouseSalio();
		    //vetado = false;
                    break;

                case MouseEvent.MOUSE_CLICKED:
                    //vetado = true;
                    //this.setBorder(mouseBorder);
                    System.out.println("Click en LabelBuild: " + posX + ", "+posY );                    
	 	    mapContainer.almacenarPos();
                    break;				
            }
        }
    }

}
