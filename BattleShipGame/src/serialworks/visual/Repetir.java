/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package serialworks.visual;
//
///**
// *
// * @author bastian
// */
public class Repetir// extends Thread
{
//
//    BattleField bf;
//    
//    public Repetir( BattleField bf ){
//            this.bf = bf;
//    }
//        
//       @Override
//    public void run(){
//            iterar();
//    }
//       
//    private boolean procesar(String pos) {
//        boolean res = false;
//
//        //if( pos.equalsIgnoreCase("") ) 
//
//        try {
//            int aux = Integer.parseInt(pos);
//
//
//            int x = aux / 10;
//            int y = aux % 10;
//
//            for (int i = 0; i < ships.length; i++) {
//                if (ships[i].damage(new Dimension(x, y))) {
//                    res = true;
//                    bf.campoEnemigo[x][y].estadoClik();
//                }
//
//                campoEnemigo[x][y].putUp();
//                campoEnemigo[x][y].setOyente(false);
//            }
//
//        } catch (Exception e) {
//
//            System.out.println("Algo paso extraño... en BAttleField");
//            ganaste = true;//o tal vez perdiste... quien sabe
//
//        } finally {
//            return res;
//        }
//    }
//
//    private /*synchronized*/ void iterar() {
//        String aux;
//        try {
//            //while( ganaste == false ) 
//            while (true) {
//                if (servidor == true) {
//                    enviar = "fallaste";
//
//                    while ((procesar(ss.comunicationReceive())) == true)
//                    {
//                        if (flotaDestruida() == true) {
//                            enviar = "ganaste";
//                        } else {
//                            enviar = "lediste";
//                        }
//                        ss.sendAnswer(enviar);
//                    }
//                    ss.sendAnswer(enviar);
//
//
//                    do {
//                        synchronized( this ){
//                            Thread.currentThread().wait();                            
//                        }
//
//                        aux = ss.comunicationSend(currentX, currentY);
//
//                    } while (aux.equals("lediste"));
//
//                    if (aux.equals("ganaste")) {
//                        System.out.println("Ganaste");
//                    //hacer algo
//                    }
//
//                } else {
//                    do {
//
//                        synchronized (this) {
//                            Thread.currentThread().wait();
//                        }
//                        aux = cs.comunicationSend(currentX, currentY);
//
//                    } while (aux.equals("lediste"));
//
//                    if (aux.equals("ganaste")) {
//                        System.out.println("Ganaste");
//                    //hacer algo
//                    }
//
//                    //fallaste                    
//                    enviar = "fallaste";
//
//                    while ((procesar(cs.comunicationReceive())) == true) {
//                        if (flotaDestruida() == true) {
//                            enviar = "ganaste";
//                        } else {
//                            enviar = "lediste";
//                        }
//                        cs.sendAnswer(enviar);
//                    }
//                    cs.sendAnswer(enviar);
//                }
//            }
//        } catch (Exception e) {
//            System.out.println("En Battlefield mwetodo iterar");
//            e.printStackTrace();
//        //System.exit( -1 );
//        }
//
//    }
}
