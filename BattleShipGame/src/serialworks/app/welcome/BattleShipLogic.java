/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package serialworks.app.welcome;

import javax.swing.JOptionPane;
import serialworks.app.build.field.BuildUtils;
import serialworks.app.i18n.International;
import serialworks.ship.Ship;

/**
 * Got some logic for BuildField.
 *
 * @author jscruz
 */
public class BattleShipLogic {

    /**
     * It obtains the initial data needed for the instantiation of a BattleField
     *
     * @param ships
     * @return
     */
    public InitData obtainShips(Ship[] ships) {
        boolean server = true;

        String positive = International.getInstance().getMessage("msg.build.field.option.sure");
        String negative = International.getInstance().getMessage("msg.build.field.option.no_way");

        String optionPaneTitle = International.getInstance().getMessage("msg.battle.initial.option.title");
        String optionPaneQuestion = International.getInstance().getMessage("msg.battle.initial.option.question");

        String[] options = {positive, negative};

        boolean goOut = false;
        String ip = null;

        do {
            int ansOptionServer = JOptionPane.showOptionDialog(null,
                    optionPaneQuestion, //mensaje
                    optionPaneTitle, //titulo
                    JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE,
                    null, //don't use a custom Icon
                    options, //the titles of buttons
                    positive);                           //the title of the default button

            if (ansOptionServer == JOptionPane.YES_OPTION)
            {
                goOut = true;
                server = false;

                do {
                    String msgEnterIP = International.getInstance().getMessage("msg.battle.initial.ip");
                    ip = JOptionPane.showInputDialog(msgEnterIP);
                    System.out.println(ip);

                    if (ip == null /*|| inputValue.equals("") */) {
                        ip = "?";
                    }

                } while ((!BuildUtils.ipValida(ip)));

            } else if (ansOptionServer == JOptionPane.NO_OPTION) {
                goOut = true;
                server = true;

            } else if (ansOptionServer == JOptionPane.CLOSED_OPTION) {
                System.out.println("Dont close it");
                
            } else {
                System.out.println("Do it again!");
            }
        } while (!goOut);

        InitData initData = new InitData();
        initData.setServidor(server);
        initData.setShips(ships);
        initData.setIp(ip);

        return initData;
    }
}
