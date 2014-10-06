
package entities;

/**
 *
 * @author Administrador
 */
public class TipoDocumentoVenta {
    
    private String idTipDocVen="";
    private String desTipDocVen="";
    private String otrCodSun="";
    private String otrValTipCli="";

    /**
     * @return the idTipDocVen
     */
    public String getIdTipDocVen() {
        idTipDocVen = idTipDocVen.trim();
        
        return idTipDocVen;
    }

    /**
     * @param idTipDocVen the idTipDocVen to set
     */
    public void setIdTipDocVen(String idTipDocVen) {
        idTipDocVen = idTipDocVen.trim();
        
        this.idTipDocVen = idTipDocVen;
    }

    /**
     * @return the desTipDocVen
     */
    public String getDesTipDocVen() {
        desTipDocVen = desTipDocVen.trim();
        
        return desTipDocVen;
    }

    /**
     * @param desTipDocVen the desTipDocVen to set
     */
    public void setDesTipDocVen(String desTipDocVen) {
        desTipDocVen = desTipDocVen.trim();
        
        this.desTipDocVen = desTipDocVen;
    }

    public String getOtrCodSun() {
        otrCodSun = otrCodSun.trim();
        
        return otrCodSun;
    }

    public void setOtrCodSun(String otrCodSun) {
        otrCodSun = otrCodSun.trim();
        
        this.otrCodSun = otrCodSun;
    }

    public String getOtrValTipCli() {
        otrValTipCli = otrValTipCli.trim();
        
        return otrValTipCli;
    }

    public void setOtrValTipCli(String otrValTipCli) {
        otrValTipCli = otrValTipCli.trim();
        
        this.otrValTipCli = otrValTipCli;
    }
    
}
