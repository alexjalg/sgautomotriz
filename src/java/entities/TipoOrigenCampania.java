/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

/**
 *
 * @author EMPRESA
 */
public class TipoOrigenCampania {

    private int idOriCam = 0;
    private String desOriCam;

    public TipoOrigenCampania(int idOriCam, String desOriCam) {
        this.idOriCam = idOriCam;
        this.desOriCam = desOriCam;
    }

    /**
     * @return the idOriCam
     */
    public int getIdOriCam() {
        return idOriCam;
    }

    /**
     * @param idOriCam the idOriCam to set
     */
    public void setIdOriCam(int idOriCam) {
        this.idOriCam = idOriCam;
    }

    /**
     * @return the desOriCam
     */
    public String getDesOriCam() {
        return desOriCam;
    }

    /**
     * @param desOriCam the desOriCam to set
     */
    public void setDesOriCam(String desOriCam) {
        this.desOriCam = desOriCam;
    }
}
