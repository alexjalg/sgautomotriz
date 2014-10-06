/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

/**
 *
 * @author Angel
 */
public class Vehiculos {
    
    
private String idVeh="";
private String idVeh_f=""; 
private String idMar="";
private String idModMar="";
private String idVerMod="";
private String idColExt="";
private String idColInt="";
private String numAnoFab="";
private String numAnoMod="";
private String desNumCha="";
private String desNumCha_f="";
private String desNumMot="";
private String desNumMot_f="";
private String desPolImp="";
private String fecFacPrv="";
private String fecFacPrv_f="";
private String desNumFacPrv="";
private String codMonFacPrv="";
private String impTipCamFP="";
private String impMonLoc="";
private String impMonExt="";

private String impPreVenAsi="";

private int idAmbUbi=0;
private int idUbiAmb=0;
private String numKimAct="";
private String codSitVeh="";
private String fecSitVeh="";
private String idUsuSitVeh="";
private String codSitVen="";
private String fecSitVen="";

private int idModVen;
private String desModVen="";
private String idCli="";
private String idCli_f="";
private String desCli="";
private String desCli_f="";
/*private String fecVenVeh="";*/
private String fecVenVeh_f="";
private String idTipDocVen="";
private String desTipDocVen="";
private String idTipDocVen_f="0";
private String desNumDocVen="";
private String desNumDocVen_f="";
/*private int codMonDocVen=0;*/
/*private String impTipCamVen="";*/
/*private String impMonLocVen="";*/

/*private String impMonExtVen="";*/

/*private String idCam="";*/
private String idNumIntRV="";
private String fecEmiDocVen="";

private String desNumPla="";
private String desNumPla_f="";
private String fecEntCli="";
private String fecEntCli_f="";
private String desObs="" ;
private String idCon="";
private String idLocCon="";
private String indVeh="";
private String edoVeh="";
private String fecReg="";
private String idUsu="";
private String desUsu="";
private String desCon="";
private String desLocCon="";

    /**
     * @return the idVeh
     */
    public String getIdVeh() {
        return idVeh;
    }

    /**
     * @param idVeh the idVeh to set
     */
    public void setIdVeh(String idVeh) {
        this.idVeh = idVeh;
    }

    /**
     * @return the idVeh_f
     */
    public String getIdVeh_f() {
        return idVeh_f;
    }

    /**
     * @param idVeh_f the idVeh_f to set
     */
    public void setIdVeh_f(String idVeh_f) {
        this.idVeh_f = idVeh_f;
    }

    /**
     * @return the idMar
     */
    public String getIdMar() {
        return idMar;
    }

    /**
     * @param idMar the idMar to set
     */
    public void setIdMar(String idMar) {
        this.idMar = idMar;
    }

    /**
     * @return the idModMar
     */
    public String getIdModMar() {
        return idModMar;
    }

    /**
     * @param idModMar the idModMar to set
     */
    public void setIdModMar(String idModMar) {
        this.idModMar = idModMar;
    }

    /**
     * @return the idVerMod
     */
    public String getIdVerMod() {
        return idVerMod;
    }

    /**
     * @param idVerMod the idVerMod to set
     */
    public void setIdVerMod(String idVerMod) {
        this.idVerMod = idVerMod;
    }

    /**
     * @return the idColExt
     */
    public String getIdColExt() {
        return idColExt;
    }

    /**
     * @param idColExt the idColExt to set
     */
    public void setIdColExt(String idColExt) {
        this.idColExt = idColExt;
    }

    /**
     * @return the idColInt
     */
    public String getIdColInt() {
        return idColInt;
    }

    /**
     * @param idColInt the idColInt to set
     */
    public void setIdColInt(String idColInt) {
        this.idColInt = idColInt;
    }

    /**
     * @return the numAnoFab
     */
    public String getNumAnoFab() {
        return numAnoFab;
    }

    /**
     * @param numAnoFab the numAnoFab to set
     */
    public void setNumAnoFab(String numAnoFab) {
        this.numAnoFab = numAnoFab;
    }

    /**
     * @return the numAnoMod
     */
    public String getNumAnoMod() {
        return numAnoMod;
    }

    /**
     * @param numAnoMod the numAnoMod to set
     */
    public void setNumAnoMod(String numAnoMod) {
        this.numAnoMod = numAnoMod;
    }

    /**
     * @return the desNumCha
     */
    public String getDesNumCha() {
        return desNumCha;
    }

    /**
     * @param desNumCha the desNumCha to set
     */
    public void setDesNumCha(String desNumCha) {
        this.desNumCha = desNumCha;
    }

    /**
     * @return the desNumCha_f
     */
    public String getDesNumCha_f() {
        return desNumCha_f;
    }

    /**
     * @param desNumCha_f the desNumCha_f to set
     */
    public void setDesNumCha_f(String desNumCha_f) {
        this.desNumCha_f = desNumCha_f;
    }

    /**
     * @return the desNumMot
     */
    public String getDesNumMot() {
        return desNumMot;
    }

    /**
     * @param desNumMot the desNumMot to set
     */
    public void setDesNumMot(String desNumMot) {
        this.desNumMot = desNumMot;
    }

    /**
     * @return the desNumMot_f
     */
    public String getDesNumMot_f() {
        return desNumMot_f;
    }

    /**
     * @param desNumMot_f the desNumMot_f to set
     */
    public void setDesNumMot_f(String desNumMot_f) {
        this.desNumMot_f = desNumMot_f;
    }

    /**
     * @return the desPolImp
     */
    public String getDesPolImp() {
        return desPolImp;
    }

    /**
     * @param desPolImp the desPolImp to set
     */
    public void setDesPolImp(String desPolImp) {
        this.desPolImp = desPolImp;
    }

    /**
     * @return the fecFacPrv
     */
    public String getFecFacPrv() {
        return fecFacPrv;
    }

    /**
     * @param fecFacPrv the fecFacPrv to set
     */
    public void setFecFacPrv(String fecFacPrv) {
        this.fecFacPrv = fecFacPrv;
    }

    /**
     * @return the desNumFacPrv
     */
    public String getDesNumFacPrv() {
        return desNumFacPrv;
    }

    /**
     * @param desNumFacPrv the desNumFacPrv to set
     */
    public void setDesNumFacPrv(String desNumFacPrv) {
        this.desNumFacPrv = desNumFacPrv;
    }

    /**
     * @return the codMonFacPrv
     */
    public String getCodMonFacPrv() {
        return codMonFacPrv;
    }

    /**
     * @param codMonFacPrv the codMonFacPrv to set
     */
    public void setCodMonFacPrv(String codMonFacPrv) {
        this.codMonFacPrv = codMonFacPrv;
    }

    /**
     * @return the impTipCamFP
     */
    public String getImpTipCamFP() {
        return impTipCamFP;
    }

    /**
     * @param impTipCamFP the impTipCamFP to set
     */
    public void setImpTipCamFP(String impTipCamFP) {
        this.impTipCamFP = impTipCamFP;
    }

    /**
     * @return the impMonLoc
     */
    public String getImpMonLoc() {
        return impMonLoc;
    }

    /**
     * @param impMonLoc the impMonLoc to set
     */
    public void setImpMonLoc(String impMonLoc) {
        this.impMonLoc = impMonLoc;
    }

    /**
     * @return the impMonExt
     */
    public String getImpMonExt() {
        return impMonExt;
    }

    /**
     * @param impMonExt the impMonExt to set
     */
    public void setImpMonExt(String impMonExt) {
        this.impMonExt = impMonExt;
    }

    /**
     * @return the idAmbUbi
     */
    public int getIdAmbUbi() {
        return idAmbUbi;
    }

    /**
     * @param idAmbUbi the idAmbUbi to set
     */
    public void setIdAmbUbi(int idAmbUbi) {
        this.idAmbUbi = idAmbUbi;
    }

    /**
     * @return the idUbiAmb
     */
    public int getIdUbiAmb() {
        return idUbiAmb;
    }

    /**
     * @param idUbiAmb the idUbiAmb to set
     */
    public void setIdUbiAmb(int idUbiAmb) {
        this.idUbiAmb = idUbiAmb;
    }

    /**
     * @return the numKimAct
     */
    public String getNumKimAct() {
        return numKimAct;
    }

    /**
     * @param numKimAct the numKimAct to set
     */
    public void setNumKimAct(String numKimAct) {
        this.numKimAct = numKimAct;
    }

    /**
     * @return the codSitVeh
     */
    public String getCodSitVeh() {
        return codSitVeh;
    }

    /**
     * @param codSitVeh the codSitVeh to set
     */
    public void setCodSitVeh(String codSitVeh) {
        this.codSitVeh = codSitVeh;
    }

    /**
     * @return the fecSitVeh
     */
    public String getFecSitVeh() {
        return fecSitVeh;
    }

    /**
     * @param fecSitVeh the fecSitVeh to set
     */
    public void setFecSitVeh(String fecSitVeh) {
        this.fecSitVeh = fecSitVeh;
    }

    /**
     * @return the idUsuSitVeh
     */
    public String getIdUsuSitVeh() {
        return idUsuSitVeh;
    }

    /**
     * @param idUsuSitVeh the idUsuSitVeh to set
     */
    public void setIdUsuSitVeh(String idUsuSitVeh) {
        this.idUsuSitVeh = idUsuSitVeh;
    }

    /**
     * @return the codSitVen
     */
    public String getCodSitVen() {
        return codSitVen;
    }

    /**
     * @param codSitVen the codSitVen to set
     */
    public void setCodSitVen(String codSitVen) {
        this.codSitVen = codSitVen;
    }

    /**
     * @return the fecSitVen
     */
    public String getFecSitVen() {
        return fecSitVen;
    }

    /**
     * @param fecSitVen the fecSitVen to set
     */
    public void setFecSitVen(String fecSitVen) {
        this.fecSitVen = fecSitVen;
    }

    /**
     * @return the idCli
     */
    public String getIdCli() {
        return idCli;
    }

    /**
     * @param idCli the idCli to set
     */
    public void setIdCli(String idCli) {
        this.idCli = idCli;
    }

    /**
     * @return the desCli_f
     */
    public String getDesCli_f() {
        return desCli_f;
    }

    /**
     * @param desCli_f the desCli_f to set
     */
    public void setDesCli_f(String desCli_f) {
        this.desCli_f = desCli_f;
    }

  

    /**
     * @return the fecVenVeh_f
     */
    public String getFecVenVeh_f() {
        return fecVenVeh_f;
    }

    /**
     * @param fecVenVeh_f the fecVenVeh_f to set
     */
    public void setFecVenVeh_f(String fecVenVeh_f) {
        this.fecVenVeh_f = fecVenVeh_f;
    }

    /**
     * @return the idTipDocVen
     */
    public String getIdTipDocVen() {
        return idTipDocVen;
    }

    /**
     * @param idTipDocVen the idTipDocVen to set
     */
    public void setIdTipDocVen(String idTipDocVen) {
        this.idTipDocVen = idTipDocVen;
    }

    /**
     * @return the desNumDocVen
     */
    public String getDesNumDocVen() {
        return desNumDocVen;
    }

    /**
     * @param desNumDocVen the desNumDocVen to set
     */
    public void setDesNumDocVen(String desNumDocVen) {
        this.desNumDocVen = desNumDocVen;
    }

    

    
     

     

     

    /**
     * @return the desNumPla
     */
    public String getDesNumPla() {
        return desNumPla;
    }

    /**
     * @param desNumPla the desNumPla to set
     */
    public void setDesNumPla(String desNumPla) {
        this.desNumPla = desNumPla;
    }

    /**
     * @return the desNumPla_f
     */
    public String getDesNumPla_f() {
        return desNumPla_f;
    }

    /**
     * @param desNumPla_f the desNumPla_f to set
     */
    public void setDesNumPla_f(String desNumPla_f) {
        this.desNumPla_f = desNumPla_f;
    }

    /**
     * @return the fecEntCli
     */
    public String getFecEntCli() {
        return fecEntCli;
    }

    /**
     * @param fecEntCli the fecEntCli to set
     */
    public void setFecEntCli(String fecEntCli) {
        this.fecEntCli = fecEntCli;
    }

    /**
     * @return the fecEntCli_f
     */
    public String getFecEntCli_f() {
        return fecEntCli_f;
    }

    /**
     * @param fecEntCli_f the fecEntCli_f to set
     */
    public void setFecEntCli_f(String fecEntCli_f) {
        this.fecEntCli_f = fecEntCli_f;
    }

    /**
     * @return the desObs
     */
    public String getDesObs() {
        return desObs;
    }

    /**
     * @param desObs the desObs to set
     */
    public void setDesObs(String desObs) {
        this.desObs = desObs;
    }

    /**
     * @return the idCon
     */
    public String getIdCon() {
        return idCon;
    }

    /**
     * @param idCon the idCon to set
     */
    public void setIdCon(String idCon) {
        this.idCon = idCon;
    }

    /**
     * @return the idLocCon
     */
    public String getIdLocCon() {
        return idLocCon;
    }

    /**
     * @param idLocCon the idLocCon to set
     */
    public void setIdLocCon(String idLocCon) {
        this.idLocCon = idLocCon;
    }

    /**
     * @return the indVeh
     */
    public String getIndVeh() {
        return indVeh;
    }

    /**
     * @param indVeh the indVeh to set
     */
    public void setIndVeh(String indVeh) {
        this.indVeh = indVeh;
    }

    /**
     * @return the edoVeh
     */
    public String getEdoVeh() {
        return edoVeh;
    }

    /**
     * @param edoVeh the edoVeh to set
     */
    public void setEdoVeh(String edoVeh) {
        this.edoVeh = edoVeh;
    }

    /**
     * @return the fecReg
     */
    public String getFecReg() {
        return fecReg;
    }

    /**
     * @param fecReg the fecReg to set
     */
    public void setFecReg(String fecReg) {
        this.fecReg = fecReg;
    }

    /**
     * @return the idUsu
     */
    public String getIdUsu() {
        return idUsu;
    }

    /**
     * @param idUsu the idUsu to set
     */
    public void setIdUsu(String idUsu) {
        this.idUsu = idUsu;
    }

    /**
     * @return the desCli
     */
    public String getDesCli() {
        return desCli;
    }

    /**
     * @param desCli the desCli to set
     */
    public void setDesCli(String desCli) {
        this.desCli = desCli;
    }

    /**
     * @return the idTipDocVen_f
     */
    public String getIdTipDocVen_f() {
        return idTipDocVen_f;
    }

    /**
     * @param idTipDocVen_f the idTipDocVen_f to set
     */
    public void setIdTipDocVen_f(String idTipDocVen_f) {
        this.idTipDocVen_f = idTipDocVen_f;
    }

    /**
     * @return the desTipDocVen
     */
    public String getDesTipDocVen() {
        return desTipDocVen;
    }

    /**
     * @param desTipDocVen the desTipDocVen to set
     */
    public void setDesTipDocVen(String desTipDocVen) {
        this.desTipDocVen = desTipDocVen;
    }

    /**
     * @return the desNumDocVen_f
     */
    public String getDesNumDocVen_f() {
        return desNumDocVen_f;
    }

    /**
     * @param desNumDocVen_f the desNumDocVen_f to set
     */
    public void setDesNumDocVen_f(String desNumDocVen_f) {
        this.desNumDocVen_f = desNumDocVen_f;
    }

    /**
     * @return the fecFacPrv_f
     */
    public String getFecFacPrv_f() {
        return fecFacPrv_f;
    }

    /**
     * @param fecFacPrv_f the fecFacPrv_f to set
     */
    public void setFecFacPrv_f(String fecFacPrv_f) {
        this.fecFacPrv_f = fecFacPrv_f;
    }

    /**
     * @return the idCli_f
     */
    public String getIdCli_f() {
        return idCli_f;
    }

    /**
     * @param idCli_f the idCli_f to set
     */
    public void setIdCli_f(String idCli_f) {
        this.idCli_f = idCli_f;
    }

    /**
     * @return the idNumIntRV
     */
    public String getIdNumIntRV() {
        return idNumIntRV;
    }

    /**
     * @param idNumIntRV the idNumIntRV to set
     */
    public void setIdNumIntRV(String idNumIntRV) {
        this.idNumIntRV = idNumIntRV;
    }

    /**
     * @return the fecEmiDocVen
     */
    public String getFecEmiDocVen() {
        return fecEmiDocVen;
    }

    /**
     * @param fecEmiDocVen the fecEmiDocVen to set
     */
    public void setFecEmiDocVen(String fecEmiDocVen) {
        this.fecEmiDocVen = fecEmiDocVen;
    }

    /**
     * @return the impPreVenAsi
     */
    public String getImpPreVenAsi() {
        return impPreVenAsi;
    }

    /**
     * @param impPreVenAsi the impPreVenAsi to set
     */
    public void setImpPreVenAsi(String impPreVenAsi) {
        this.impPreVenAsi = impPreVenAsi;
    }

    /**
     * @return the idModVen
     */
    public int getIdModVen() {
        return idModVen;
    }

    /**
     * @param idModVen the idModVen to set
     */
    public void setIdModVen(int idModVen) {
        this.idModVen = idModVen;
    }

    /**
     * @return the desModVen
     */
    public String getDesModVen() {
        return desModVen;
    }

    /**
     * @param desModVen the desModVen to set
     */
    public void setDesModVen(String desModVen) {
        this.desModVen = desModVen;
    }

    /**
     * @return the desUsu
     */
    public String getDesUsu() {
        return desUsu;
    }

    /**
     * @param desUsu the desUsu to set
     */
    public void setDesUsu(String desUsu) {
        this.desUsu = desUsu;
    }

    /**
     * @return the desCon
     */
    public String getDesCon() {
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
        return desLocCon;
    }

    /**
     * @param desLocCon the desLocCon to set
     */
    public void setDesLocCon(String desLocCon) {
        this.desLocCon = desLocCon;
    }

     
    
    
}
