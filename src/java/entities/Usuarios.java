/*
 * Entidad: Usuarios
 * Creado por: Angelo Ccoicca
 * Fecha de creación: 07-03-2014
 * Modificado por                   Fecha de Modificación
 * - 
 * -
 * -
 * 
 */
package entities;

public class Usuarios {

    private String idUsu = "";
    private String idUsu_f = "";
    private String desApeUsu = "";
    private String desNomUsu = "";
    private String otrClaUsu = "";
    private String desUsu = "";
    private String desUsu_f = "";
    private String desUsuFull = "";
    private int idTipUsu = 0;
    private int idTipUsu_f = 0;
    private String desTipUsu="";
    private int idCon = 0;
    private String desCon = "";
    private int idLocCon = 0;
    private String desLocCon = "";
    private String numTelFij = "";
    private String numAnexo = "";
    private String numTelMov1 = "";
    private String numTelMov2 = "";
    private String numTelRP1 = "";
    private String numTelRP2 = "";
    private String numTelRP3 = "";
    private String otrEmaTra = "";
    private String fecUltCamCla = "";
    private String indClaRes = "";
    private String otrNueClaUsu = "";
    private String otrNueClaUsu2 = "";
    private String indBloUsu = "";
    private int numIntfal = 0;
    private String edoUsu = "";
    private String edoUsu_f = "";
    private String fecReg = "";
    private String idUsuReg = "";

    public String getIdUsu() {
        idUsu = idUsu.trim();
        
        return idUsu;
    }

    public void setIdUsu(String idUsu) {
        this.idUsu = idUsu;
    }

    public String getOtrClaUsu() {
        otrClaUsu = otrClaUsu.trim();
        
        return otrClaUsu;
    }

    public void setOtrClaUsu(String otrClaUsu) {
        this.otrClaUsu = otrClaUsu;
    }

    public String getEdoUsu() {
        edoUsu = edoUsu.trim();
                
        return edoUsu;
    }

    public void setEdoUsu(String edoUsu) {
        this.edoUsu = edoUsu;
    }

    /**
     * @return the desApeUsu
     */
    public String getDesApeUsu() {
        desApeUsu = desApeUsu.trim();
        
        return desApeUsu;
    }

    /**
     * @param desApeUsu the desApeUsu to set
     */
    public void setDesApeUsu(String desApeUsu) {
        this.desApeUsu = desApeUsu;
    }

    /**
     * @return the desNomUsu
     */
    public String getDesNomUsu() {
        desNomUsu = desNomUsu.trim();
        
        return desNomUsu;
    }

    /**
     * @param desNomUsu the desNomUsu to set
     */
    public void setDesNomUsu(String desNomUsu) {
        this.desNomUsu = desNomUsu;
    }

    /**
     * @return the desUsu
     */
    public String getDesUsu() {
        desUsu = desUsu.trim();
        
        return desUsu;
    }

    /**
     * @param desUsu the desUsu to set
     */
    public void setDesUsu(String desUsu) {
        this.desUsu = desUsu;
    }

    /**
     * @return the desUsuFull
     */
    public String getDesUsuFull() {
        desUsuFull = desUsuFull.trim();
        
        return desUsuFull;
    }

    /**
     * @param desUsuFull the desUsuFull to set
     */
    public void setDesUsuFull(String desUsuFull) {
        this.desUsuFull = desUsuFull;
    }

    /**
     * @return the idUsu_f
     */
    public String getIdUsu_f() {
        idUsu_f = idUsu_f.trim();
                
        return idUsu_f;
    }

    /**
     * @param idUsu_f the idUsu_f to set
     */
    public void setIdUsu_f(String idUsu_f) {
        this.idUsu_f = idUsu_f;
    }

    /**
     * @return the desUsu_f
     */
    public String getDesUsu_f() {
        desUsu_f = desUsu_f.trim();
        
        return desUsu_f;
    }

    /**
     * @param desUsu_f the desUsu_f to set
     */
    public void setDesUsu_f(String desUsu_f) {
        this.desUsu_f = desUsu_f;
    }

    /**
     * @return the edoUsu_f
     */
    public String getEdoUsu_f() {
        edoUsu_f = edoUsu_f.trim();
        
        return edoUsu_f;
    }

    /**
     * @param edoUsu_f the edoUsu_f to set
     */
    public void setEdoUsu_f(String edoUsu_f) {
        this.edoUsu_f = edoUsu_f;
    }

    /**
     * @return the idCon
     */
    public int getIdCon() {
        return idCon;
    }

    /**
     * @param idCon the idCon to set
     */
    public void setIdCon(int idCon) {
        this.idCon = idCon;
    }

    /**
     * @return the idLocCon
     */
    public int getIdLocCon() {
        return idLocCon;
    }

    /**
     * @param idLocCon the idLocCon to set
     */
    public void setIdLocCon(int idLocCon) {
        this.idLocCon = idLocCon;
    }

    /**
     * @return the idTipUsu
     */
    public int getIdTipUsu() {
        return idTipUsu;
    }

    /**
     * @param idTipUsu the idTipUsu to set
     */
    public void setIdTipUsu(int idTipUsu) {
        this.idTipUsu = idTipUsu;
    }

    /**
     * @return the fecReg
     */
    public String getFecReg() {
        fecReg = fecReg.trim();
        
        return fecReg;
    }

    /**
     * @param fecReg the fecReg to set
     */
    public void setFecReg(String fecReg) {
        if(fecReg.trim().equals("")) {
            fecReg = "0000-00-00";
        }
        
        this.fecReg = fecReg;
    }

    /**
     * @return the fecUltCamCla
     */
    public String getFecUltCamCla() {
        fecUltCamCla = fecUltCamCla.trim();
        
        return fecUltCamCla;
    }

    /**
     * @param fecUltCamCla the fecUltCamCla to set
     */
    public void setFecUltCamCla(String fecUltCamCla) {
        if(fecUltCamCla.trim().equals("")) {
            fecUltCamCla = "0000-00-00";
        }
        
        this.fecUltCamCla = fecUltCamCla;
    }

    /**
     * @return the indClaRes
     */
    public String getIndClaRes() {
        indClaRes = indClaRes.trim();
        
        return indClaRes;
    }

    /**
     * @param indClaRes the indClaRes to set
     */
    public void setIndClaRes(String indClaRes) {
        this.indClaRes = indClaRes;
    }

    /**
     * @return the desCon
     */
    public String getDesCon() {
        desCon = desCon.trim();
        
        return desCon;
    }

    /**
     * @param desCon the desCon to set
     */
    public void setDesCon(String desCon) {
        this.desCon = desCon;
    }

    /**
     * @return the desLocCon
     */
    public String getDesLocCon() {
        desLocCon = desLocCon.trim();
        
        return desLocCon;
    }

    /**
     * @param desLocCon the desLocCon to set
     */
    public void setDesLocCon(String desLocCon) {
        this.desLocCon = desLocCon;
    }

    /**
     * @return the otrNueClaUsu
     */
    public String getOtrNueClaUsu() {
        otrNueClaUsu = otrNueClaUsu.trim();
        
        return otrNueClaUsu;
    }

    /**
     * @param otrNueClaUsu the otrNueClaUsu to set
     */
    public void setOtrNueClaUsu(String otrNueClaUsu) {
        this.otrNueClaUsu = otrNueClaUsu;
    }

    /**
     * @return the otrNueClaUsu2
     */
    public String getOtrNueClaUsu2() {
        otrNueClaUsu2 = otrNueClaUsu2.trim();
        
        return otrNueClaUsu2;
    }

    /**
     * @param otrNueClaUsu2 the otrNueClaUsu2 to set
     */
    public void setOtrNueClaUsu2(String otrNueClaUsu2) {
        this.otrNueClaUsu2 = otrNueClaUsu2;
    }

    /**
     * @return the numTelFij
     */
    public String getNumTelFij() {
        numTelFij = numTelFij.trim();
        
        return numTelFij;
    }

    /**
     * @param numTelFij the numTelFij to set
     */
    public void setNumTelFij(String numTelFij) {
        numTelFij = numTelFij.trim();
        if (numTelFij.equals("")) {
            numTelFij="0";
        }
        
        this.numTelFij = numTelFij;
    }

    /**
     * @return the numAnexo
     */
    public String getNumAnexo() {
        numAnexo = numAnexo.trim();
        
        return numAnexo;
    }

    /**
     * @param numAnexo the numAnexo to set
     */
    public void setNumAnexo(String numAnexo) {
        numAnexo = numAnexo.trim();
        if (numAnexo.equals("")) {
            numAnexo="0";
        }
        
        this.numAnexo = numAnexo;
    }

    /**
     * @return the numTelMov1
     */
    public String getNumTelMov1() {
        numTelMov1 = numTelMov1.trim();
        
        return numTelMov1;
    }

    /**
     * @param numTelMov1 the numTelMov1 to set
     */
    public void setNumTelMov1(String numTelMov1) {
        numTelMov1 = numTelMov1.trim();
        if (numTelMov1.equals("")) {
            numTelMov1="0";
        }
        
        this.numTelMov1 = numTelMov1;
    }

    /**
     * @return the numTelMov2
     */
    public String getNumTelMov2() {
        numTelMov2 = numTelMov2.trim();
        
        return numTelMov2;
    }

    /**
     * @param numTelMov2 the numTelMov2 to set
     */
    public void setNumTelMov2(String numTelMov2) {
        numTelMov2 = numTelMov2.trim();
        if (numTelMov2.equals("")) {
            numTelMov2="0";
        }
        
        this.numTelMov2 = numTelMov2;
    }

    /**
     * @return the numTelRP1
     */
    public String getNumTelRP1() {
        numTelRP1 = numTelRP1.trim();
        
        return numTelRP1;
    }

    /**
     * @param numTelRP1 the numTelRP1 to set
     */
    public void setNumTelRP1(String numTelRP1) {
        numTelRP1 = numTelRP1.trim();
        if(numTelRP1.equals("RPM") || numTelRP1.equals("RPC") || numTelRP1.equals("Nextel"))
            numTelRP1 = "";
        
        this.numTelRP1 = numTelRP1;
    }

    /**
     * @return the numTelRP2
     */
    public String getNumTelRP2() {
        numTelRP2 = numTelRP2.trim();
        
        return numTelRP2;
    }

    /**
     * @param numTelRP2 the numTelRP2 to set
     */
    public void setNumTelRP2(String numTelRP2) {
        numTelRP2 = numTelRP2.trim();
        if(numTelRP2.equals("RPM") || numTelRP2.equals("RPC") || numTelRP2.equals("Nextel"))
            numTelRP2 = "";
        
        this.numTelRP2 = numTelRP2;
    }

    /**
     * @return the numTelRP3
     */
    public String getNumTelRP3() {
        numTelRP3 = numTelRP3.trim();
        
        return numTelRP3;
    }

    /**
     * @param numTelRP3 the numTelRP3 to set
     */
    public void setNumTelRP3(String numTelRP3) {
        numTelRP3 = numTelRP3.trim();
        if(numTelRP3.equals("RPM") || numTelRP3.equals("RPC") || numTelRP3.equals("Nextel"))
            numTelRP3 = "";
        
        this.numTelRP3 = numTelRP3;
    }

    /**
     * @return the otrEmaTra
     */
    public String getOtrEmaTra() {
        otrEmaTra = otrEmaTra.trim();
        
        return otrEmaTra;
    }

    /**
     * @param otrEmaTra the otrEmaTra to set
     */
    public void setOtrEmaTra(String otrEmaTra) {
        this.otrEmaTra = otrEmaTra;
    }

    /**
     * @return the indBloUsu
     */
    public String getIndBloUsu() {
        indBloUsu = indBloUsu.trim();
        
        return indBloUsu;
    }

    /**
     * @param indBloUsu the indBloUsu to set
     */
    public void setIndBloUsu(String indBloUsu) {
        this.indBloUsu = indBloUsu;
    }

    /**
     * @return the numIntfal
     */
    public int getNumIntfal() {
        return numIntfal;
    }

    /**
     * @param numIntfal the numIntfal to set
     */
    public void setNumIntfal(int numIntfal) {
        this.numIntfal = numIntfal;
    }

    /**
     * @return the idUsuReg
     */
    public String getIdUsuReg() {
        idUsuReg = idUsuReg.trim();
        
        return idUsuReg;
    }

    /**
     * @param idUsuReg the idUsuReg to set
     */
    public void setIdUsuReg(String idUsuReg) {
        this.idUsuReg = idUsuReg;
    }

    /**
     * @return the desTipUsu
     */
    public String getDesTipUsu() {
        desTipUsu = desTipUsu.trim();
                
        return desTipUsu;
    }

    /**
     * @param desTipUsu the desTipUsu to set
     */
    public void setDesTipUsu(String desTipUsu) {
        this.desTipUsu = desTipUsu;
    }

    /**
     * @return the idTipUsu_f
     */
    public int getIdTipUsu_f() {
        return idTipUsu_f;
    }

    /**
     * @param idTipUsu_f the idTipUsu_f to set
     */
    public void setIdTipUsu_f(int idTipUsu_f) {
        this.idTipUsu_f = idTipUsu_f;
    }
}
