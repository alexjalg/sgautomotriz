/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

/**
 *
 * @author EMPRESA
 */
public class TipoDocumento {
    private int idTipDoc;
    private String desTipDoc;
    private String otrTipCli;
    private int numLonVal;

    /**
     * @return the idTipDoc
     */
    public int getIdTipDoc() {
        return idTipDoc;
    }

    /**
     * @param idTipDoc the idTipDoc to set
     */
    public void setIdTipDoc(int idTipDoc) {
        this.idTipDoc = idTipDoc;
    }

    /**
     * @return the desTipDoc
     */
    public String getDesTipDoc() {
        return desTipDoc;
    }

    /**
     * @param desTipDoc the desTipDoc to set
     */
    public void setDesTipDoc(String desTipDoc) {
        this.desTipDoc = desTipDoc;
    }

    /**
     * @return the otrTipCli
     */
    public String getOtrTipCli() {
        return otrTipCli;
    }

    /**
     * @param otrTipCli the otrTipCli to set
     */
    public void setOtrTipCli(String otrTipCli) {
        this.otrTipCli = otrTipCli;
    }

    /**
     * @return the numLonVal
     */
    public int getNumLonVal() {
        return numLonVal;
    }

    /**
     * @param numLonVal the numLonVal to set
     */
    public void setNumLonVal(int numLonVal) {
        this.numLonVal = numLonVal;
    }
}
