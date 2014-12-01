package entities;

/**
 *
 * @author Angel
 */
public class Vehiculos {

    private String idVeh = "";
    private String idVeh_f = "";
    private String idMar = "";
    private String desMar = "";
    private String idModMar = "";
    private String desModMar = "";
    private String idVerMod = "";
    private String desVerMod = "";
    private String desVeh = "";
    private String desVeh_f = "";
    private String idColExt = "";
    private String desColExt = "";
    private String idColInt = "";
    private String desColInt = "";
    private String numAnoFab = "";
    private String numAnoMod = "";
    private String desNumCha = "";
    private String desNumCha_f = "";
    private String desNumMot = "";
    private String desNumMot_f = "";
    private String desPolImp = "";
    private String fecFacPrv = "";
    private String fecFacPrv_f = "";
    private String desNumFacPrv = "";
    private String codMonFacPrv = "";
    private String desMonFacPrv = "";
    private String impTipCamFP = "";
    private String impMonLoc = "";
    private String impMonExt = "";
    private String impPreVenAsi = "";
    private int idAmbUbi = 99;
    private String desAmbUbi = "";
    private int idUbiAmb = 99;
    private String desUbiAmb = "";
    private String numKimAct = "";
    private String codSitVeh = "";
    private String codSitVeh_f = "";
    private String desSitVeh = "";
    private String fecSitVeh = "";
    private String idUsuSitVeh = "";
    private String codSitVen = "";
    private String codSitVen_f = "";
    private String desSitVen = "";
    private String fecSitVen = "";
    private int idModVen;
    private String desModVen = "";
    private String idCli = "";
    private String idCli_f = "";
    private String desCli = "";
    private String desCli_f = "";
    private String idTipDocVen = "";
    private String desTipDocVen = "";
    private String idTipDocVen_f = "0";
    private String desNumDocVen = "";
    private String desNumDocVen_f = "";
    private String idNumIntRV = "";
    private String fecEmiDocVen = "";
    private String fecEmiDocVen_f = "";
    private String desNumPla = "";
    private String desNumPla_f = "";
    private String fecEntCli = "";
    private String fecEntCli_f = "";
    private String desObs = "";
    private String idCon = "";
    private String idLocCon = "";
    private String indVeh = "";
    private String edoVeh = "";
    private String fecReg = "";
    private String idUsu = "";
    private String desUsu = "";
    private String desCon = "";
    private String desLocCon = "";

    /**
     * @return the idVeh
     */
    public String getIdVeh() {
        idVeh = idVeh.trim();
        
        return idVeh;
    }

    /**
     * @param idVeh the idVeh to set
     */
    public void setIdVeh(String idVeh) {
        idVeh = idVeh.trim();
        
        this.idVeh = idVeh;
    }

    /**
     * @return the idVeh_f
     */
    public String getIdVeh_f() {
        idVeh_f = idVeh_f.trim();
        
        return idVeh_f;
    }

    /**
     * @param idVeh_f the idVeh_f to set
     */
    public void setIdVeh_f(String idVeh_f) {
        idVeh_f = idVeh_f.trim();
        
        this.idVeh_f = idVeh_f;
    }

    /**
     * @return the idMar
     */
    public String getIdMar() {
        idMar = idMar.trim();
        
        return idMar;
    }

    /**
     * @param idMar the idMar to set
     */
    public void setIdMar(String idMar) {
        idMar = idMar.trim();
        
        this.idMar = idMar;
    }

    /**
     * @return the idModMar
     */
    public String getIdModMar() {
        idModMar = idModMar.trim();
        
        return idModMar;
    }

    /**
     * @param idModMar the idModMar to set
     */
    public void setIdModMar(String idModMar) {
        idModMar = idModMar.trim();
        
        this.idModMar = idModMar;
    }

    /**
     * @return the idVerMod
     */
    public String getIdVerMod() {
        idVerMod = idVerMod.trim();
        
        return idVerMod;
    }

    /**
     * @param idVerMod the idVerMod to set
     */
    public void setIdVerMod(String idVerMod) {
        idVerMod = idVerMod.trim();
        
        this.idVerMod = idVerMod;
    }

    /**
     * @return the idColExt
     */
    public String getIdColExt() {
        idColExt = idColExt.trim();
        
        return idColExt;
    }

    /**
     * @param idColExt the idColExt to set
     */
    public void setIdColExt(String idColExt) {
        idColExt = idColExt.trim();
        
        this.idColExt = idColExt;
    }

    /**
     * @return the idColInt
     */
    public String getIdColInt() {
        idColInt = idColInt.trim();
        
        return idColInt;
    }

    /**
     * @param idColInt the idColInt to set
     */
    public void setIdColInt(String idColInt) {
        idColInt = idColInt.trim();
        
        this.idColInt = idColInt;
    }

    /**
     * @return the numAnoFab
     */
    public String getNumAnoFab() {
        numAnoFab = numAnoFab.trim();
        
        if(numAnoFab.equals("")) {
            numAnoFab = "0";
        }
        
        return numAnoFab;
    }

    /**
     * @param numAnoFab the numAnoFab to set
     */
    public void setNumAnoFab(String numAnoFab) {
        numAnoFab = numAnoFab.trim();
        
        if(numAnoFab.equals("")) {
            numAnoFab = "0";
        }
        
        this.numAnoFab = numAnoFab;
    }

    /**
     * @return the numAnoMod
     */
    public String getNumAnoMod() {
        numAnoMod = numAnoMod.trim();
        
        if(numAnoMod.equals("")) {
            numAnoMod = "0";
        }
        
        return numAnoMod;
    }

    /**
     * @param numAnoMod the numAnoMod to set
     */
    public void setNumAnoMod(String numAnoMod) {
        numAnoMod = numAnoMod.trim();
        
        if(numAnoMod.equals("")) {
            numAnoMod = "0";
        }
        
        this.numAnoMod = numAnoMod;
    }

    /**
     * @return the desNumCha
     */
    public String getDesNumCha() {
        desNumCha = desNumCha.trim();
        
        return desNumCha;
    }

    /**
     * @param desNumCha the desNumCha to set
     */
    public void setDesNumCha(String desNumCha) {
        desNumCha = desNumCha.trim();
        
        this.desNumCha = desNumCha;
    }

    /**
     * @return the desNumCha_f
     */
    public String getDesNumCha_f() {
        desNumCha_f = desNumCha_f.trim();
        
        return desNumCha_f;
    }

    /**
     * @param desNumCha_f the desNumCha_f to set
     */
    public void setDesNumCha_f(String desNumCha_f) {
        desNumCha_f = desNumCha_f.trim();
        
        this.desNumCha_f = desNumCha_f;
    }

    /**
     * @return the desNumMot
     */
    public String getDesNumMot() {
        desNumMot = desNumMot.trim();
        
        return desNumMot;
    }

    /**
     * @param desNumMot the desNumMot to set
     */
    public void setDesNumMot(String desNumMot) {
        desNumMot = desNumMot.trim();
        
        this.desNumMot = desNumMot;
    }

    /**
     * @return the desNumMot_f
     */
    public String getDesNumMot_f() {
        desNumMot_f = desNumMot_f.trim();
        
        return desNumMot_f;
    }

    /**
     * @param desNumMot_f the desNumMot_f to set
     */
    public void setDesNumMot_f(String desNumMot_f) {
        desNumMot_f = desNumMot_f.trim();
        
        this.desNumMot_f = desNumMot_f;
    }

    /**
     * @return the desPolImp
     */
    public String getDesPolImp() {
        desPolImp = desPolImp.trim();
        
        return desPolImp;
    }

    /**
     * @param desPolImp the desPolImp to set
     */
    public void setDesPolImp(String desPolImp) {
        desPolImp = desPolImp.trim();
        
        this.desPolImp = desPolImp;
    }

    /**
     * @return the fecFacPrv
     */
    public String getFecFacPrv() {
        fecFacPrv = fecFacPrv.trim();
        
        return fecFacPrv;
    }

    /**
     * @param fecFacPrv the fecFacPrv to set
     */
    public void setFecFacPrv(String fecFacPrv) {
        fecFacPrv = fecFacPrv.trim();
        
        this.fecFacPrv = fecFacPrv;
    }

    /**
     * @return the desNumFacPrv
     */
    public String getDesNumFacPrv() {
        desNumFacPrv = desNumFacPrv.trim();
        
        return desNumFacPrv;
    }

    /**
     * @param desNumFacPrv the desNumFacPrv to set
     */
    public void setDesNumFacPrv(String desNumFacPrv) {
        desNumFacPrv = desNumFacPrv.trim();
        
        this.desNumFacPrv = desNumFacPrv;
    }

    /**
     * @return the codMonFacPrv
     */
    public String getCodMonFacPrv() {
        codMonFacPrv = codMonFacPrv.trim();
        
        if(codMonFacPrv.equals("")) {
            codMonFacPrv = "0";
        }
        
        return codMonFacPrv;
    }

    /**
     * @param codMonFacPrv the codMonFacPrv to set
     */
    public void setCodMonFacPrv(String codMonFacPrv) {
        codMonFacPrv = codMonFacPrv.trim();
        
        if(codMonFacPrv.equals("")) {
            codMonFacPrv = "0";
        }
        
        this.codMonFacPrv = codMonFacPrv;
    }

    /**
     * @return the impTipCamFP
     */
    public String getImpTipCamFP() {
        impTipCamFP = impTipCamFP.trim();
        
        if(impTipCamFP.equals("")) {
            impTipCamFP = "0.0000";
        }
        
        return impTipCamFP;
    }

    /**
     * @param impTipCamFP the impTipCamFP to set
     */
    public void setImpTipCamFP(String impTipCamFP) {
        impTipCamFP = impTipCamFP.trim();
        
        if(impTipCamFP.equals("")) {
            impTipCamFP = "0.0000";
        }
        
        this.impTipCamFP = impTipCamFP;
    }

    /**
     * @return the impMonLoc
     */
    public String getImpMonLoc() {
        impMonLoc = impMonLoc.trim();
        
        if(impMonLoc.equals("")) {
            impMonLoc = "0.00";
        }
        
        return impMonLoc;
    }

    /**
     * @param impMonLoc the impMonLoc to set
     */
    public void setImpMonLoc(String impMonLoc) {
        impMonLoc = impMonLoc.trim();
        
        if(impMonLoc.equals("")) {
            impMonLoc = "0.00";
        }
        
        this.impMonLoc = impMonLoc;
    }

    /**
     * @return the impMonExt
     */
    public String getImpMonExt() {
        impMonExt = impMonExt.trim();
        
        if(impMonLoc.equals("")) {
            impMonExt = "0.00";
        }
        
        return impMonExt;
    }

    /**
     * @param impMonExt the impMonExt to set
     */
    public void setImpMonExt(String impMonExt) {
        impMonExt = impMonExt.trim();
        
        if(impMonLoc.equals("")) {
            impMonExt = "0.00";
        }
        
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
        numKimAct = numKimAct.trim();
        
        if(numKimAct.equals("")) {
            numKimAct = "0";
        }
        
        return numKimAct;
    }

    /**
     * @param numKimAct the numKimAct to set
     */
    public void setNumKimAct(String numKimAct) {
        numKimAct = numKimAct.trim();
        
        if(numKimAct.equals("")) {
            numKimAct = "0";
        }
        
        this.numKimAct = numKimAct;
    }

    /**
     * @return the codSitVeh
     */
    public String getCodSitVeh() {
        codSitVeh = codSitVeh.trim();
        
        return codSitVeh;
    }

    /**
     * @param codSitVeh the codSitVeh to set
     */
    public void setCodSitVeh(String codSitVeh) {
        codSitVeh = codSitVeh.trim();
        
        this.codSitVeh = codSitVeh;
    }

    /**
     * @return the fecSitVeh
     */
    public String getFecSitVeh() {
        fecSitVeh = fecSitVeh.trim();
        
        return fecSitVeh;
    }

    /**
     * @param fecSitVeh the fecSitVeh to set
     */
    public void setFecSitVeh(String fecSitVeh) {
        fecSitVeh = fecSitVeh.trim();
        
        this.fecSitVeh = fecSitVeh;
    }

    /**
     * @return the idUsuSitVeh
     */
    public String getIdUsuSitVeh() {
        idUsuSitVeh = idUsuSitVeh.trim();
        
        return idUsuSitVeh;
    }

    /**
     * @param idUsuSitVeh the idUsuSitVeh to set
     */
    public void setIdUsuSitVeh(String idUsuSitVeh) {
        idUsuSitVeh = idUsuSitVeh.trim();
        
        this.idUsuSitVeh = idUsuSitVeh;
    }

    /**
     * @return the codSitVen
     */
    public String getCodSitVen() {
        codSitVen = codSitVen.trim();
        
        return codSitVen;
    }

    /**
     * @param codSitVen the codSitVen to set
     */
    public void setCodSitVen(String codSitVen) {
        codSitVen = codSitVen.trim();
        
        this.codSitVen = codSitVen;
    }

    /**
     * @return the fecSitVen
     */
    public String getFecSitVen() {
        fecSitVen = fecSitVen.trim();
        
        return fecSitVen;
    }

    /**
     * @param fecSitVen the fecSitVen to set
     */
    public void setFecSitVen(String fecSitVen) {
        fecSitVen = fecSitVen.trim();
        
        this.fecSitVen = fecSitVen;
    }

    /**
     * @return the idCli
     */
    public String getIdCli() {
        idCli = idCli.trim();
        
        return idCli;
    }

    /**
     * @param idCli the idCli to set
     */
    public void setIdCli(String idCli) {
        idCli = idCli.trim();
        
        this.idCli = idCli;
    }

    /**
     * @return the desCli_f
     */
    public String getDesCli_f() {
        desCli_f = desCli_f.trim();
        
        return desCli_f;
    }

    /**
     * @param desCli_f the desCli_f to set
     */
    public void setDesCli_f(String desCli_f) {
        desCli_f = desCli_f.trim();
        
        this.desCli_f = desCli_f;
    }

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
     * @return the desNumDocVen
     */
    public String getDesNumDocVen() {
        desNumDocVen = desNumDocVen.trim();
        
        return desNumDocVen;
    }

    /**
     * @param desNumDocVen the desNumDocVen to set
     */
    public void setDesNumDocVen(String desNumDocVen) {
        desNumDocVen = desNumDocVen.trim();
        
        this.desNumDocVen = desNumDocVen;
    }

    /**
     * @return the desNumPla
     */
    public String getDesNumPla() {
        desNumPla = desNumPla.trim();
        
        return desNumPla;
    }

    /**
     * @param desNumPla the desNumPla to set
     */
    public void setDesNumPla(String desNumPla) {
        desNumPla = desNumPla.trim();
        
        this.desNumPla = desNumPla;
    }

    /**
     * @return the desNumPla_f
     */
    public String getDesNumPla_f() {
        desNumPla_f = desNumPla_f.trim();
        
        return desNumPla_f;
    }

    /**
     * @param desNumPla_f the desNumPla_f to set
     */
    public void setDesNumPla_f(String desNumPla_f) {
        desNumPla_f = desNumPla_f.trim();
        
        this.desNumPla_f = desNumPla_f;
    }

    /**
     * @return the fecEntCli
     */
    public String getFecEntCli() {
        fecEntCli = fecEntCli.trim();
        
        return fecEntCli;
    }

    /**
     * @param fecEntCli the fecEntCli to set
     */
    public void setFecEntCli(String fecEntCli) {
        fecEntCli = fecEntCli.trim();
        
        this.fecEntCli = fecEntCli;
    }

    /**
     * @return the fecEntCli_f
     */
    public String getFecEntCli_f() {
        fecEntCli_f = fecEntCli_f.trim();
        
        return fecEntCli_f;
    }

    /**
     * @param fecEntCli_f the fecEntCli_f to set
     */
    public void setFecEntCli_f(String fecEntCli_f) {
        fecEntCli_f = fecEntCli_f.trim();
        
        this.fecEntCli_f = fecEntCli_f;
    }

    /**
     * @return the desObs
     */
    public String getDesObs() {
        desObs = desObs.trim();
        
        return desObs;
    }

    /**
     * @param desObs the desObs to set
     */
    public void setDesObs(String desObs) {
        desObs = desObs.trim();
        
        this.desObs = desObs;
    }

    /**
     * @return the idCon
     */
    public String getIdCon() {
        idCon = idCon.trim();
        
        if(idCon.equals("")) {
            idCon = "0";
        }
        
        return idCon;
    }

    /**
     * @param idCon the idCon to set
     */
    public void setIdCon(String idCon) {
        idCon = idCon.trim();
        
        if(idCon.equals("")) {
            idCon = "0";
        }
        
        this.idCon = idCon;
    }

    /**
     * @return the idLocCon
     */
    public String getIdLocCon() {
        idLocCon = idLocCon.trim();
        
        if(idLocCon.equals("")) {
            idLocCon = "0";
        }
        
        return idLocCon;
    }

    /**
     * @param idLocCon the idLocCon to set
     */
    public void setIdLocCon(String idLocCon) {
        idLocCon = idLocCon.trim();
        
        if(idLocCon.equals("")) {
            idLocCon = "0";
        }
        
        this.idLocCon = idLocCon;
    }

    /**
     * @return the indVeh
     */
    public String getIndVeh() {
        indVeh = indVeh.trim();
        
        return indVeh;
    }

    /**
     * @param indVeh the indVeh to set
     */
    public void setIndVeh(String indVeh) {
        indVeh = indVeh.trim();
        
        this.indVeh = indVeh;
    }

    /**
     * @return the edoVeh
     */
    public String getEdoVeh() {
        edoVeh = edoVeh.trim();
        
        return edoVeh;
    }

    /**
     * @param edoVeh the edoVeh to set
     */
    public void setEdoVeh(String edoVeh) {
        edoVeh = edoVeh.trim();
        
        this.edoVeh = edoVeh;
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
        fecReg = fecReg.trim();
        
        this.fecReg = fecReg;
    }

    /**
     * @return the idUsu
     */
    public String getIdUsu() {
        idUsu = idUsu.trim();
         
        return idUsu;
    }

    /**
     * @param idUsu the idUsu to set
     */
    public void setIdUsu(String idUsu) {
        idUsu = idUsu.trim();
        
        this.idUsu = idUsu;
    }

    /**
     * @return the desCli
     */
    public String getDesCli() {
        desCli = desCli.trim();
        
        return desCli;
    }

    /**
     * @param desCli the desCli to set
     */
    public void setDesCli(String desCli) {
        desCli = desCli.trim();
        
        this.desCli = desCli;
    }

    /**
     * @return the idTipDocVen_f
     */
    public String getIdTipDocVen_f() {
        idTipDocVen_f = idTipDocVen_f.trim();
        
        return idTipDocVen_f;
    }

    /**
     * @param idTipDocVen_f the idTipDocVen_f to set
     */
    public void setIdTipDocVen_f(String idTipDocVen_f) {
        idTipDocVen_f = idTipDocVen_f.trim();
        
        this.idTipDocVen_f = idTipDocVen_f;
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

    /**
     * @return the desNumDocVen_f
     */
    public String getDesNumDocVen_f() {
        desNumDocVen_f = desNumDocVen_f.trim();
        
        return desNumDocVen_f;
    }

    /**
     * @param desNumDocVen_f the desNumDocVen_f to set
     */
    public void setDesNumDocVen_f(String desNumDocVen_f) {
        desNumDocVen_f = desNumDocVen_f.trim();
        
        this.desNumDocVen_f = desNumDocVen_f;
    }

    /**
     * @return the fecFacPrv_f
     */
    public String getFecFacPrv_f() {
        fecFacPrv_f = fecFacPrv_f.trim();
        
        return fecFacPrv_f;
    }

    /**
     * @param fecFacPrv_f the fecFacPrv_f to set
     */
    public void setFecFacPrv_f(String fecFacPrv_f) {
        fecFacPrv_f = fecFacPrv_f.trim();
        
        this.fecFacPrv_f = fecFacPrv_f;
    }

    /**
     * @return the idCli_f
     */
    public String getIdCli_f() {
        idCli_f = idCli_f.trim();
        
        return idCli_f;
    }

    /**
     * @param idCli_f the idCli_f to set
     */
    public void setIdCli_f(String idCli_f) {
        idCli_f = idCli_f.trim();
         
        this.idCli_f = idCli_f;
    }

    /**
     * @return the idNumIntRV
     */
    public String getIdNumIntRV() {
        idNumIntRV = idNumIntRV.trim();
         
        return idNumIntRV;
    }

    /**
     * @param idNumIntRV the idNumIntRV to set
     */
    public void setIdNumIntRV(String idNumIntRV) {
        idNumIntRV = idNumIntRV.trim();
        
        this.idNumIntRV = idNumIntRV;
    }

    /**
     * @return the fecEmiDocVen
     */
    public String getFecEmiDocVen() {
        fecEmiDocVen = fecEmiDocVen.trim();
        
        return fecEmiDocVen;
    }

    /**
     * @param fecEmiDocVen the fecEmiDocVen to set
     */
    public void setFecEmiDocVen(String fecEmiDocVen) {
        fecEmiDocVen = fecEmiDocVen.trim();
        
        this.fecEmiDocVen = fecEmiDocVen;
    }

    /**
     * @return the impPreVenAsi
     */
    public String getImpPreVenAsi() {
        impPreVenAsi = impPreVenAsi.trim();
        
        if(impPreVenAsi.equals("")) {
            impPreVenAsi = "0.00";
        }
        
        return impPreVenAsi;
    }

    /**
     * @param impPreVenAsi the impPreVenAsi to set
     */
    public void setImpPreVenAsi(String impPreVenAsi) {
        impPreVenAsi = impPreVenAsi.trim();
        
        if(impPreVenAsi.equals("")) {
            impPreVenAsi = "0.00";
        }
        
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
        desModVen = desModVen.trim();
        
        return desModVen;
    }

    /**
     * @param desModVen the desModVen to set
     */
    public void setDesModVen(String desModVen) {
        desModVen = desModVen.trim();
        
        this.desModVen = desModVen;
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
        desUsu = desUsu.trim();
        
        this.desUsu = desUsu;
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
        desCon = desCon.trim();
        
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
        desLocCon = desLocCon.trim();
        
        this.desLocCon = desLocCon;
    }

    /**
     * @return the fecEmiDocVen_f
     */
    public String getFecEmiDocVen_f() {
        fecEmiDocVen_f = fecEmiDocVen_f.trim();
        
        return fecEmiDocVen_f;
    }

    /**
     * @param fecEmiDocVen_f the fecEmiDocVen_f to set
     */
    public void setFecEmiDocVen_f(String fecEmiDocVen_f) {
        fecEmiDocVen_f = fecEmiDocVen_f.trim();
        
        this.fecEmiDocVen_f = fecEmiDocVen_f;
    }

    public String getDesMar() {
        desMar = desMar.trim();
        
        return desMar;
    }

    public void setDesMar(String desMar) {
        desMar = desMar.trim();
        
        this.desMar = desMar;
    }

    public String getDesModMar() {
        desModMar = desModMar.trim();
        
        return desModMar;
    }

    public void setDesModMar(String desModMar) {
        desModMar = desModMar.trim();
        
        this.desModMar = desModMar;
    }

    public String getDesVerMod() {
        desVerMod = desVerMod.trim();
        
        return desVerMod;
    }

    public void setDesVerMod(String desVerMod) {
        desVerMod = desVerMod.trim();
        
        this.desVerMod = desVerMod;
    }

    public String getDesColExt() {
        desColExt = desColExt.trim();
        
        return desColExt;
    }

    public void setDesColExt(String desColExt) {
        desColExt = desColExt.trim();
        
        this.desColExt = desColExt;
    }

    public String getDesColInt() {
        desColInt = desColInt.trim();
        
        return desColInt;
    }

    public void setDesColInt(String desColInt) {
        desColInt = desColInt.trim();
        
        this.desColInt = desColInt;
    }

    public String getDesMonFacPrv() {
        desMonFacPrv = desMonFacPrv.trim();
        
        return desMonFacPrv;
    }

    public void setDesMonFacPrv(String desMonFacPrv) {
        desMonFacPrv = desMonFacPrv.trim();
        
        this.desMonFacPrv = desMonFacPrv;
    }

    public String getDesAmbUbi() {
        desAmbUbi = desAmbUbi.trim();
        
        return desAmbUbi;
    }

    public void setDesAmbUbi(String desAmbUbi) {
        desAmbUbi = desAmbUbi.trim();
        
        this.desAmbUbi = desAmbUbi;
    }

    public String getDesUbiAmb() {
        desUbiAmb = desUbiAmb.trim();
        
        return desUbiAmb;
    }

    public void setDesUbiAmb(String desUbiAmb) {
        desUbiAmb = desUbiAmb.trim();
        
        this.desUbiAmb = desUbiAmb;
    }

    public String getDesSitVeh() {
        desSitVeh = desSitVeh.trim();
        
        return desSitVeh;
    }

    public void setDesSitVeh(String desSitVeh) {
        desSitVeh = desSitVeh.trim();
        
        this.desSitVeh = desSitVeh;
    }

    public String getDesSitVen() {
        desSitVen = desSitVen.trim();
        
        return desSitVen;
    }

    public void setDesSitVen(String desSitVen) {
        desSitVen = desSitVen.trim();
        
        this.desSitVen = desSitVen;
    }

    public String getDesVeh() {
        desVeh = desVeh.trim();
        
        return desVeh;
    }

    public void setDesVeh(String desVeh) {
        desVeh = desVeh.trim();
        
        this.desVeh = desVeh;
    }

    public String getDesVeh_f() {
        desVeh_f = desVeh_f.trim();
        
        return desVeh_f;
    }

    public void setDesVeh_f(String desVeh_f) {
        desVeh_f = desVeh_f.trim();
        
        this.desVeh_f = desVeh_f;
    }

    public String getCodSitVeh_f() {
        codSitVeh_f = codSitVeh_f.trim();
        
        return codSitVeh_f;
    }

    public void setCodSitVeh_f(String codSitVeh_f) {
        codSitVeh_f = codSitVeh_f.trim();
        
        this.codSitVeh_f = codSitVeh_f;
    }

    public String getCodSitVen_f() {
        codSitVen_f = codSitVen_f.trim();
        
        return codSitVen_f;
    }

    public void setCodSitVen_f(String codSitVen_f) {
        codSitVen_f = codSitVen_f.trim();
        
        this.codSitVen_f = codSitVen_f;
    }
}
