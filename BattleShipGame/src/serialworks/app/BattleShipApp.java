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
import javax.swing.JOptionPane;
import serialworks.app.build.field.BuildField;
import serialworks.app.build.field.BuildUtils;
import serialworks.app.welcome.EventEnum;
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
     * Adds <code>panelMain</code> content to a frame, center it and show it.
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
     * @param pce
     */
    @Override
    public void propertyChange(PropertyChangeEvent pce) {
        if (pce.getPropertyName().equals(EventEnum.START.name())) {
            showBuildField();

        } else if (pce.getPropertyName().equals(EventEnum.END.name())) {
            System.exit(0);

        } else if (pce.getPropertyName().equals("buildFinished")) {

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
        boolean servidor = true;

        if (ships == null) {
            System.out.println("Ships en Principal es NULL");
            System.exit(-1);
        }

        //---------------------------

        String string1 = "Si, por favor";
        String string2 = "De ninguna manera";
        Object[] options = {string1, string2};

        boolean salir = false;
        String ip = null;

        do {

            int n = JOptionPane.showOptionDialog(null,
                    "Deseas conectarte a alguien?",//mensaje
                    "Cliente o Servidor",//titulo
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.QUESTION_MESSAGE,
                    null, //don't use a custom Icon
                    options, //the titles of buttons
                    string1); //the title of the default button

            if (n == JOptionPane.YES_OPTION) {
                //System.out.println("You're kidding!");
                salir = true;
                servidor = false;


                do {
                    ip = JOptionPane.showInputDialog("Porfa Ingrese el IP "
                            + "\n" + "al que te conectaras");
                    System.out.println(ip);

                    if (ip == null /*|| inputValue.equals("") */) {
                        ip = "?";
                    }

                } while ((!BuildUtils.ipValida(ip)));


            } else if (n == JOptionPane.NO_OPTION) {
                //System.out.println("I don't like them, either.");
                salir = true;
                servidor = true;

            } else if (n == JOptionPane.CLOSED_OPTION) {
                System.out.println("No lo cierres");
            } else {
                System.out.println("Do it again!");
            }

        } while (!salir);

        battleField = new BattleField(ships, servidor, ip);
        frameMain.setVisible(false);
        battleField.setVisible(true);
    }
}
