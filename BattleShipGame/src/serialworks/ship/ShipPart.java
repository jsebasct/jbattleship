/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package serialworks.ship;

import java.awt.Dimension;

/**
 *
 * @author bastian
 */
public class ShipPart
{
    private Dimension position;
    private boolean damage;
    
    public ShipPart( Dimension d )
    {
        position = d;
        damage = false;
    }
    
    public boolean isDamage() {
        return damage;
    }
    
    /**
     * 
     * @param shut
     * @return true if somebody acerto el tiro
     */
    public boolean match( Dimension shut )
    {
        boolean res = false;
        
        if( ( position.toString() ) .equals( shut.toString() ) )
        {
            res = true;
            damage = true;
        }
            
        return res;
    }
    
    /*
    public void destroy() {
        damage = true;
    }*/
    
    
    public Dimension getPosition( )
    {
        return position;
    }
    
    
    @Override
    public String toString()
    {
        return position.toString();
    }
}
