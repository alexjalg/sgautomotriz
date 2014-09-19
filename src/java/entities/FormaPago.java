/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

/**
 *
 * @author EMPRESA
 */
public class FormaPago {
    private int idForPag;
    private String desForPag;
    private int numDiaPag;

    /**
     * @return the idForPag
     */
    public int getIdForPag() {
        return idForPag;
    }

    /**
     * @param idForPag the idForPag to set
     */
    public void setIdForPag(int idForPag) {
        this.idForPag = idForPag;
    }

    /**
     * @return the desForPag
     */
    public String getDesForPag() {
        return desForPag;
    }

    /**
     * @param desForPag the desForPag to set
     */
    public void setDesForPag(String desForPag) {
        this.desForPag = desForPag;
    }

    /**
     * @return the numDiaPag
     */
    public int getNumDiaPag() {
        return numDiaPag;
    }

    /**
     * @param numDiaPag the numDiaPag to set
     */
    public void setNumDiaPag(int numDiaPag) {
        this.numDiaPag = numDiaPag;
    }
}
