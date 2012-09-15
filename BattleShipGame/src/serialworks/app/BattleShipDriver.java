/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package serialworks.app;

import serialworks.visual.Principal;

/**
 * Main Driver class. The hole application starting point.
 *
 * @author jscruz
 */
public class BattleShipDriver {

    /**
     * Starts the ship battle !!
     * @param args
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
//                new Principal().setVisible(true);

                BattleShipApp mainDriver = new BattleShipApp();
                mainDriver.run();
            }
        });
    }
}
