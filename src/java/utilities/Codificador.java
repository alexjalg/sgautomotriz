package utilities;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.logging.*;

public class Codificador 
{   
    public static String getEncoded(String texto, String algoritmo) 
    {
        String output="";
        try 
        {   
            byte[] textBytes = texto.getBytes();
            MessageDigest md = MessageDigest.getInstance(algoritmo);
            md.update(textBytes);
            byte[] codigo = md.digest();
            output = new String(codigo);
        } 
        catch (NoSuchAlgorithmException ex) 
        {
            Logger.getLogger(Codificador.class.getName()).log(Level.SEVERE, null, ex);
        }
        return output;

    }
}