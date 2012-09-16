/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package serialworks.app.i18n;

import java.util.Locale;
import java.util.ResourceBundle;

/**
 * This class is meant to give a global access to the internationalization
 * resource bundle.
 *
 * @author jscruz
 */
public class International {

    private static volatile International uniqueInstance;
    private Locale locale;
    private ResourceBundle resource;

    private International() {
        locale = Locale.getDefault();
        resource = ResourceBundle.getBundle("serialworks.app.i18n.messages", locale);
    }

    public static International getInstance() {
        if (uniqueInstance == null) {
            synchronized (International.class) {
                if (uniqueInstance == null) {
                    uniqueInstance = new International();
                }
            }
        }
        return uniqueInstance;
    }

    public String getMessage(String aKey) {
        return resource.getString(aKey);
    }
}
