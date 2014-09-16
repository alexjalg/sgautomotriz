/*
 * Entidad: Tipo de Cambio
 * Creado por: Angelo Ccoicca
 * Fecha de creación: 22-05-2014
 * Modificado por                   Fecha de Modificación
 * - 
 * -
 * -
 * 
 */
package entities;

public class TipoCambio 
{
    private String fecTipCam="";
    private String impTCIntCom="";
    private String impTCIntVen="";
    private String impTCLegCom="";
    private String impTCLegVen="";
    private String indMod="";

    /**
     * @return the fecTipCam
     */
    public String getFecTipCam() {
        return fecTipCam;
    }

    /**
     * @param fecTipCam the fecTipCam to set
     */
    public void setFecTipCam(String fecTipCam) {
        this.fecTipCam = fecTipCam;
    }

    /**
     * @return the indMod
     */
    public String getIndMod() {
        return indMod;
    }

    /**
     * @param indMod the indMod to set
     */
    public void setIndMod(String indMod) {
        this.indMod = indMod;
    }

    /**
     * @return the impTCIntCom
     */
    public String getImpTCIntCom() {
        return impTCIntCom;
    }

    /**
     * @param impTCIntCom the impTCIntCom to set
     */
    public void setImpTCIntCom(String impTCIntCom) {
        this.impTCIntCom = impTCIntCom;
    }

    /**
     * @return the impTCIntVen
     */
    public String getImpTCIntVen() {
        return impTCIntVen;
    }

    /**
     * @param impTCIntVen the impTCIntVen to set
     */
    public void setImpTCIntVen(String impTCIntVen) {
        this.impTCIntVen = impTCIntVen;
    }

    /**
     * @return the impTCLegCom
     */
    public String getImpTCLegCom() {
        return impTCLegCom;
    }

    /**
     * @param impTCLegCom the impTCLegCom to set
     */
    public void setImpTCLegCom(String impTCLegCom) {
        this.impTCLegCom = impTCLegCom;
    }

    /**
     * @return the impTCLegVen
     */
    public String getImpTCLegVen() {
        return impTCLegVen;
    }

    /**
     * @param impTCLegVen the impTCLegVen to set
     */
    public void setImpTCLegVen(String impTCLegVen) {
        this.impTCLegVen = impTCLegVen;
    }
}
