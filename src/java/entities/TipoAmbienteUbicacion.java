/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

/**
 *
 * @author EMPRESA
 */
public class TipoAmbienteUbicacion {

    private String idTipAmbUbi;
    private String desTipAmbUbi;

    public TipoAmbienteUbicacion(String idTipAmbUbi, String desTipAmbUbi) {
        this.idTipAmbUbi = idTipAmbUbi;
        this.desTipAmbUbi = desTipAmbUbi;
    }

    /**
     * @return the idTipAmbUbi
     */
    public String getIdTipAmbUbi() {
        return idTipAmbUbi;
    }

    /**
     * @param idTipAmbUbi the idTipAmbUbi to set
     */
    public void setIdTipAmbUbi(String idTipAmbUbi) {
        this.idTipAmbUbi = idTipAmbUbi;
    }

    /**
     * @return the desTipAmbUbi
     */
    public String getDesTipAmbUbi() {
        return desTipAmbUbi;
    }

    /**
     * @param desTipAmbUbi the desTipAmbUbi to set
     */
    public void setDesTipAmbUbi(String desTipAmbUbi) {
        this.desTipAmbUbi = desTipAmbUbi;
    }
}
