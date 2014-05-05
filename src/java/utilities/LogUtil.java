/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package utilities;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Angelo
 */
public final class LogUtil {
    
    private static Logger logger = null;
    
    private LogUtil() {
        
    }

    public static void registroMensaje(String mensaje) {
        if (logger != null) {
            logger.log(Level.INFO, mensaje);
        }
    }

    public static void registroError(String mensaje) {
        if (logger != null) {
            logger.log(Level.SEVERE, mensaje);
        }
    }
}
