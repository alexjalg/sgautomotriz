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
    private String impTipCamInt="";
    private String impTipCamLeg="";
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
     * @return the impTipCam
     */
    public String getImpTipCamInt() {
        return impTipCamInt;
    }

    /**
     * @param impTipCam the impTipCam to set
     */
    public void setImpTipCamInt(String impTipCamInt) {
        this.impTipCamInt = impTipCamInt;
    }

    /**
     * @return the impTipCamLeg
     */
    public String getImpTipCamLeg() {
        return impTipCamLeg;
    }

    /**
     * @param impTipCamLeg the impTipCamLeg to set
     */
    public void setImpTipCamLeg(String impTipCamLeg) {
        this.impTipCamLeg = impTipCamLeg;
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
}
