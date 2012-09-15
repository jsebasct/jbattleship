/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package serialworks.ship;

import java.awt.Dimension;
import javax.swing.ImageIcon;

/**
 *
 * @author bastian
 */
public abstract class Ship {

    protected ImageIcon image;
    protected byte size;
    protected ShipPart[] parts;
    protected boolean destroyed;

    public boolean damage(Dimension shut) {
        boolean res = false;

        ShipPart aux = null;
        int i = 0;

        while ((i < parts.length) && (res == false)) {
            aux = parts[ i];

            if (aux.match(shut)) { //si math es true la parte es destruida automaticamnte
                res = true;
            }
            i++;
        }
        return res;
    }

    public Dimension[] getPositions() {
        Dimension[] pos = new Dimension[parts.length];

        for (int i = 0; i < parts.length; i++) {
            pos[i] = parts[i].getPosition();
        }

        return pos;
    }

    public boolean isDestroyed() {
        boolean res = true;
        int i = 0;

        while ((i < parts.length) && (res == true)) {
            if (parts[i].isDamage() == false) {
                res = false;
            }
            i++;
        }

        return res;
    }

    public void setPositions(Dimension[] pos) {
        size = (byte) pos.length;

        parts = new ShipPart[pos.length];

        for (int i = 0; i < pos.length; i++) {
            parts[i] = new ShipPart(pos[i]);
        }
    }

    public ImageIcon getImage() {
        return image;
    }

    public int getSize() {
        return size;
    }

    public abstract ShipType getType();
}
