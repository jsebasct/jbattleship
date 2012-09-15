/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package serialworks.app.welcome;

import javax.swing.JOptionPane;
import serialworks.ship.Ship;

/**
 * Presenter class for BuildField
 * @author jscruz
 */
public class BattleShipLogic {

    public InitData obtainShips(Ship[] ships)
    {
        boolean servidor = true;

        if (ships == null) {
            System.out.println("Ships en Principal es NULL");
            System.exit(-1);
        }

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

                } while ((!ipValida(ip)));


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

        InitData initData = new InitData();
        initData.setServidor(servidor);
        initData.setShips(ships);
        initData.setIp(ip);

        return initData;

//        battleField = new BattleField(ships, servidor, ip);
//
//        this.setVisible(false);
//
//        battleField.setVisible(true);
    }

    private boolean ipValida(String input) {
        boolean res = true;

        if (!input.equalsIgnoreCase("localhost")) {
            String[] result = input.split("\\.");


            for (int x = 0; x < result.length; x++) {
                System.out.println(result[x]);
            }

            if (result.length != 4) {

                res = false;

            } else {

                int i = 0;

                while ((i < result.length) && (res == true)) {
                    String aux = result[i];
                    int num = 0;

                    try {
                        num = Integer.parseInt(aux);
                        if (num < 0 && num > 255) {
                            System.out.println(num);
                            res = false;
                        }
                    } catch (NumberFormatException ex) {
                        res = false;
                    }
                    //finally{
                    i++;
                    //}
                }
            }
        }

        return res;
    }

}
