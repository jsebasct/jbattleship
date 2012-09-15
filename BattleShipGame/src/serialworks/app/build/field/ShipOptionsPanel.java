/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package serialworks.app.build.field;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import serialworks.data.ShipContainer;
import serialworks.ship.ShipType;

/**
 * Its a panel that shows the different types of ships you can choose.
 *
 * @author bastian
 */
public class ShipOptionsPanel extends JPanel implements ActionListener {

    private ShipContainer shipContainer;
    private ImageIcon[] imageShips;
    private JButton[] buttons;
    private PropertyChangeSupport pcs;

    public ShipOptionsPanel(int tam) {
        init(tam);
        initComponents(tam);
        loadComponents();
        addComponents();

        setBounds(0, 0, 140, 400);
    }

    private void init(int tam) {
        this.setLayout(new GridLayout(tam, 1));
        this.pcs = new PropertyChangeSupport(this);
    }

    private void initComponents(int tam) {
        shipContainer = new ShipContainer(this);

        buttons = new JButton[tam];
        imageShips = new ImageIcon[tam];
    }

    private void loadComponents() {
        imageShips = shipContainer.getImageShips();
        Color color = new Color(0, 103, 102);

        for (int i = 0; i < buttons.length; i++) {
            buttons[i] = new JButton();
            buttons[i].setBackground(color);
            buttons[i].addActionListener(this);
            buttons[i].setIcon(imageShips[i]);

            buttons[i].setActionCommand(ShipType.values()[i].name());
        }
    }

    private void addComponents() {
        for (JButton aButton : buttons) {
            this.add(aButton);
        }
    }

    @Override
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        this.pcs.addPropertyChangeListener(listener);
    }

    @Override
    public void removePropertyChangeListener(PropertyChangeListener listener) {
        this.pcs.removePropertyChangeListener(listener);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton btnAuxiliar = (JButton) e.getSource();
        String command = btnAuxiliar.getActionCommand();

        //--------Eso he modificado para arreglar los problemas de la interfaz-
        shipContainer.getMap().habilitar(true);
        //-------------------------------------------------------------------

        if (command.equals(ShipType.AIRCRAFTCARRIER.name())) {
            System.out.println(ShipType.AIRCRAFTCARRIER.name());
            shipContainer.updateSelectedShip(ShipType.AIRCRAFTCARRIER);

        } else if (command.equals(ShipType.BATTLESHIP.name())) {
            System.out.println(ShipType.BATTLESHIP.name());
            shipContainer.updateSelectedShip(ShipType.BATTLESHIP);

        } else if (command.equals(ShipType.FRIGATE.name())) {
            System.out.println(ShipType.FRIGATE.name());
            shipContainer.updateSelectedShip(ShipType.FRIGATE);

        } else if (command.equals(ShipType.MINESWEEPER.name())) {
            System.out.println(ShipType.MINESWEEPER.name());
            shipContainer.updateSelectedShip(ShipType.MINESWEEPER);

        } else if (command.equals(ShipType.SUBMARINE.name())) {
            System.out.println(ShipType.SUBMARINE.name());
            shipContainer.updateSelectedShip(ShipType.SUBMARINE);
        }

    }

    private void verificarFin() {
        boolean res = false;
        int i = 0;

        while ((i < buttons.length) && (res == false)) {
            if (buttons[i].isEnabled() == true) {
                res = true;
            }
            i++;
        }

        if (res == false) {
            System.out.println("\t All options are disabled");
            //btnComplete.setEnabled(true);
            pcs.firePropertyChange("AllShipsInPlace", false, true);
        }
    }

    public ShipContainer getShipContainer() {
        return shipContainer;
    }

    public void disableOption(ShipType st, int x) {
        buttons[x].setEnabled(false);
        verificarFin();
    }

    //testing
    public void probarEnVentana() {
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(3);
        frame.setBounds(200, 10, 200, 400);
        frame.getContentPane().add(this);
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        ShipOptionsPanel shipOptionsPanel = new ShipOptionsPanel(5);
        shipOptionsPanel.probarEnVentana();
    }
}
