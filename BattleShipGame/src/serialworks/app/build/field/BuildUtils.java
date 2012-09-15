/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package serialworks.app.build.field;

/**
 * Some utility methods that helps on the construction of the battle field.
 *
 * @author jscruz
 */
public class BuildUtils {

    /**
     * Check if an IP address is valid.
     *
     * @param input an string that represents an IP address
     * @return true if the input is an valid IP address
     */
    public static boolean ipValida(String input)
    {
        boolean res = true;

        if (!input.equalsIgnoreCase("localhost"))
        {
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
