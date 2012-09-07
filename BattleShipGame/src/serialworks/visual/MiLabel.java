/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package serialworks.visual;

import java.awt.AWTEvent;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.MouseEvent;
import javax.swing.JLabel;
import javax.swing.border.LineBorder;
import javax.swing.border.SoftBevelBorder;

/**
 * Debes sobreescribirla siempre para que pueda responder a eventos de mouse
 * 
 * @author bastian
 */
public abstract class MiLabel extends JLabel
{
    protected int posX;
    protected int posY;
    protected boolean oyente;    
    protected SoftBevelBorder mouseBorder;
    protected LineBorder defectBorder;
    
    public MiLabel(int x, int y) {

        super("");
        posX = x;
        posY = y;
        oyente = true;

        init();
    }

    public void init() {
        mouseBorder = new SoftBevelBorder(SoftBevelBorder.RAISED);
        defectBorder = new LineBorder(Color.BLACK, 2);
        setBorder(defectBorder);        
        
        enableEvents(AWTEvent.MOUSE_EVENT_MASK);
    }

    public void setOyente(boolean o) {
        oyente = o;
    }

    public Dimension getPos() {
        Dimension d = new Dimension(posX, posY);
        return d;
    }

    public void estadoClik() 
    {
        this.setBackground(Color.ORANGE);
        this.setForeground(Color.black);
        System.out.println("Entro a estado clic en ( " + posX + ", "+ posY + ")" );
    }

    @Override
    protected abstract void processMouseEvent(MouseEvent evt); //{    }
	
    public void putUp() {
        this.setBorder(mouseBorder);
    }

    public void putDown() {
        this.setBorder(defectBorder);
    }
}
