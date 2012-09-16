/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package serialworks.app;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import javax.swing.JFrame;
import serialworks.app.build.field.BuildEnum;
import serialworks.app.build.field.BuildField;
import serialworks.app.welcome.BattleShipLogic;
import serialworks.app.welcome.EventEnum;
import serialworks.app.welcome.InitData;
import serialworks.app.welcome.MainWelcomePanel;
import serialworks.ship.Ship;
import serialworks.visual.BattleField;

/**
 * BattleShip starter class. This class is the join point of all battle
 * components.
 *
 * @author jscruz
 */
public class BattleShipApp implements PropertyChangeListener {

    private static final int DEFAULT_NUM_SHIPS = 5;
    private JFrame frameMain;
    private MainWelcomePanel panelMain;
    private BuildField buildField;
    private BattleField battleField;

    public BattleShipApp() {
        frameMain = new JFrame();
        frameMain.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        panelMain = new MainWelcomePanel();
        panelMain.addPropertyChangeListener(this);

        buildField = new BuildField(DEFAULT_NUM_SHIPS);
        buildField.addPropertyChangeListener(this);
    }

    /**
     * Adds
     * <code>panelMain</code> content to a frame, center it and show it.
     */
    public void run() {
        frameMain.getContentPane().add(panelMain);
        centerScreen();

        frameMain.pack();
        frameMain.setVisible(true);
    }

    public void showBuildField() {
        //TODO after the first task (decoupling) review this part
        buildField.setVisible(true);
    }

    /**
     * Process some events from its subjects.
     *
     * @param pce
     */
    @Override
    public void propertyChange(PropertyChangeEvent pce) {
        if (pce.getPropertyName().equals(EventEnum.START_GAME.name())) {
            showBuildField();

        } else if (pce.getPropertyName().equals(EventEnum.END_GAME.name())) {
            System.exit(0);

        } else if (pce.getPropertyName().equals(BuildEnum.FINISHED.name())) {

            Object builded = pce.getNewValue();
            if (builded != null && builded instanceof Ship[]) {
                Ship[] theShips = (Ship[]) builded;
                System.out.println("==>" + theShips.length);

                obtainShips(theShips);
            }
        }
    }

    private void centerScreen() {
        Dimension pan = Toolkit.getDefaultToolkit().getScreenSize();
        int x = pan.width / 2 - (250);
        int y = pan.height / 2 - 200;
        frameMain.setBounds(x, y, frameMain.getWidth(), frameMain.getHeight());
    }

    /**
     * @todo this method has to be renamed.
     * @param ships
     */
    private void obtainShips(Ship[] ships) {

        BattleShipLogic logic = new BattleShipLogic();
        InitData obtainShips = logic.obtainShips(ships);

        battleField = new BattleField(ships, obtainShips.isServidor(), obtainShips.getIp());
        frameMain.setVisible(false);
        battleField.setVisible(true);
    }
}
