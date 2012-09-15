/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package serialworks.app.welcome;

import serialworks.ship.Ship;

/**
 *
 * @author jscruz
 */
public class InitData {

    private String ip;
    private Ship[] ships;
    private boolean servidor;

    public InitData() {
    }

    public InitData(String ip, Ship[] ships, boolean servidor) {
        this.ip = ip;
        this.ships = ships;
        this.servidor = servidor;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public Ship[] getShips() {
        return ships;
    }

    public void setShips(Ship[] ships) {
        this.ships = ships;
    }

    public boolean isServidor() {
        return servidor;
    }

    public void setServidor(boolean servidor) {
        this.servidor = servidor;
    }

}
