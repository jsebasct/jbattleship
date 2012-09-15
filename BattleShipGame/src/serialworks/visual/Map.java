/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package serialworks.visual;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JPanel;
import serialworks.data.ShipContainer;

/**
 * The battle map !!
 *
 * @author bastian
 */
public class Map extends JPanel {

    private int filas;
    private int columnas;
    private int tamBarco;
    private boolean click;//true if se hizo click en la posicion actual
    private LabelBuild[][] matriz;
    private Dimension currentPosition;
    private ShipContainer shipContainer;
    private ArrayList<Dimension> posicionesPintar;
    private ArrayList<Dimension> posicionesFijas;

    public Map(int filas, int columnas) {

        this.filas = filas;
        this.columnas = columnas;

        click = false;
        posicionesPintar = new ArrayList<Dimension>();
        posicionesFijas = new ArrayList<Dimension>();

        currentPosition = new Dimension(-1, -1);
        matriz = new LabelBuild[filas][columnas];

        init();
    }

    public Map(ShipContainer padre, int filas, int columnas) {
        this(filas, columnas);
        this.shipContainer = (ShipContainer) padre;
    }

    public void setShipSize(int tam) {
        this.tamBarco = tam;
    }

    private void init() {
        this.setBounds(0, 0, 350, 350);
        this.setLayout(new GridLayout(filas, columnas));

        initPushLabels();
        agregarComponentes();
    }

    private void initPushLabels() {
        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < matriz[i].length; j++) {

                matriz[i][j] = new LabelBuild(this, i, j);
                matriz[i][j].setOyente(false);
            }
        }
    }

    private void agregarComponentes() {
        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < matriz[i].length; j++) {

                this.add(matriz[i][j]);
            }
        }
    }

    public void updatePos(int x, int y) {
        currentPosition.setSize(x, y);
        click = false;//esta la macana que estaba medio mal, me drogo

        if (habilitado()) {
            //System.out.println("Esta Habilitado to paint");
            cargarPosicionesPintar();
            pintar();
        } else {
            System.out.println("No puedes construir ahi");
        }
    }

    /**
     * Este comprueba que la posicion actual del mouse es sufifiente para el
     * largo del barco
     */
    private boolean habilitado() {
        boolean enable = false;

        int fila = getFila();
        int columna = getColumna();
        int limiteDer = matriz[fila].length;

        //como solo estoy haciendo en horizontal:
        if ((limiteDer - columna) >= tamBarco) {
            for (int i = columna; i < (columna + tamBarco); i++) {
                posicionesPintar.add(new Dimension(fila, i));
            }

            if (!unElementoIgual(posicionesPintar, posicionesFijas)) {
                enable = true;
            }
        }
        return enable;
    }

    private boolean unElementoIgual(ArrayList<Dimension> pp, ArrayList<Dimension> pf) {
        boolean res = false;
        int i = 0;
        int j = 0;
        int ppmax = pp.size();
        int pfmax = pf.size();
        Dimension a = new Dimension();
        Dimension b = new Dimension();

        //System.out.println("PP tam: " + ppmax);
        //System.out.println("PF tam: " + pfmax);

//        for (Dimension d : pp) {
//            System.out.println("\t\t\tEn pp: " + d.toString());
//        }
//        for (Dimension d : pf) {
//            System.out.println("\t\t\t\tEn pf: " + d.toString());
//        }

        while ((i < ppmax) && (res == false)) {
            a = pp.get(i);
            //System.out.println("En pp: " + a.toString());

            j = 0;

            while ((j < pfmax) && (res == false)) {
                b = pf.get(j);
                //System.out.println("	En pf:" + b.toString());

                if ((a.getWidth() == b.getWidth()) && (a.getHeight() == b.getHeight())) {
                    res = true;
                    //System.out.println("Esas posiciones son iguales");
                }
                j++;
            }
            i++;
        }
        posicionesPintar.clear();//esto porque tenemos elementos que comparar
        //que todavia no han sido fijados
        return res;
    }

    private void cargarPosicionesPintar() {
        int fila = getFila();
        int columna = getColumna();

//        int max = ( (matriz.length) - columna );        
//        if( max >= tamBarco ) {
//            max = tamBarco + columna;
//        }
        //System.out.println("\t MAX:" + max);
        //berfore:         
        //for (int i = columna; i < max; i++) {
        for (int i = columna; i < (columna + tamBarco); i++) {
            posicionesPintar.add(new Dimension(fila, i));
        }
    }

    private void pintar() {

        int fil = 0;
        int col = 0;

        for (Dimension d : posicionesPintar) {
            fil = (int) d.getWidth();
            col = (int) d.getHeight();
            matriz[fil][col].putUp();
            //System.out.println("Pinte( " + fil + ", " + col + ")");
        }
    }

    /**
     * Called when the user does click in a casilla from MiLabel. So if the size
     * of the current ship's size fits then it will be included otherwise a
     * message will be printed.
     */
    public void almacenarPos() {
        if (tamBarco == posicionesPintar.size()) {
            for (Dimension d : posicionesPintar) {
                posicionesFijas.add(d);

                int f = (int) d.getWidth();
                int c = (int) d.getHeight();
                System.out.println("\t\t Se agrego a PF( " + f + ", " + c + ")");
                //matriz[f][c].putUp();//y adeberian estar en putUp
                matriz[f][c].setOyente(false);
            }
            //--------------------------------------------
            shipContainer.setPosSomeShip(posicionesPintar);
            //----------------------------------------

            click = true;
            posicionesPintar.clear();

            setShipSize(0);
            habilitar(false);
        } else {
            //TODO replace this for a logger.
            System.out.println("Cant be done");
        }
    }

    //HAY QUE AUMENTAR LA PARTE QEN QUE GUARDA LAS POCIIONES DE MEMORIA EN EL MAPA
    public void mouseSalio() {

        //System.out.println("Mouse Salio: " + getFila() + ", " + getColumna());
        //System.out.println("Click es: " + click);

        if (click == false) {
            int f = getFila();
            int c = getColumna();
            int max = posicionesPintar.size();
            //for (Dimension d : posicionesPintar) {
            for (int i = 0; i < max; i++) {
                f = (int) (posicionesPintar.get(i)).getWidth();
                c = (int) (posicionesPintar.get(i)).getHeight();
                //System.out.println("\t\t\t DesPintaras( " + f + ", " + c + ")");
                matriz[f][c].putDown();
            }
        } else {
            //System.out.println("No se hizo click! ");
            //matriz[f][c].putUp();				//y adeberian estar en putUp
            //matriz[f][c].setOyente(false);
        }

        //----------------independientemente de lo que pase, hacemos esto-----------------------		
        //System.out.println("\t \t \t pp clear: " + posicionesPintar.size());
        posicionesPintar.clear();
        click = false;
        //System.out.println("Click es: " + click);
        //System.out.println("\t \t \t CantIDAD in pp after clear: " + posicionesPintar.size());
        //-----------------------------------------------------------------------------------------------------
    }

    /* Este metodo me entrega la actual fila y columna 
     **/
    private int getFila() {
        return (int) currentPosition.getWidth();
    }

    private int getColumna() {
        return (int) currentPosition.getHeight();
    }

    /**
     * Pone o NO segun el parametro oyentes a las casillas.
     *
     * @param estado
     */
    public void habilitar(boolean estado) {
        //public void setE( estado ){
        for (int i = 0; i < matriz.length; i++) {

            for (int j = 0; j < matriz[i].length; j++) {

                if (estado) {
                    matriz[i][j].setOyente(true);
                } else {
                    matriz[i][j].setOyente(false);
                }
            }
        }
        int i = 0;
        int j = 0;

        for (Dimension d : posicionesFijas) {
            i = (int) d.getWidth();
            j = (int) d.getHeight();

            matriz[i][j].setOyente(false);
        }
    }

    //testing
    public void remover(Map map) {
        JFrame frame = new JFrame();
        frame.getContentPane().add(map);

        frame.setDefaultCloseOperation(3);
        frame.setBounds(500, 10, 400, 400);

        frame.setVisible(true);
    }

    public static void main(String[] args) {
        Map map = new Map(10, 10);
        map.remover(map);
    }
}
