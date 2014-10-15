/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

/**
 *
 * @author Administrador
 */
public class CargaDatosClientes {

    private String idCli = "";    
    private String idTipDoc = "";
    private String otrTipCli = "";
    private String desCli = "";
    private String desApeCon = "";
    private String desNomCon = "";
    private String desDirCon = "";
    private String idDep = "";
    private String idPrvDep = "";
    private String idDisPrv = "";
    private String numTelFij = "";
    private String numTelMov = "";
    private String numTelOfi = "";
    private String numTelRP = "";
    private String otrEmaPer = "";
    private String otrEmaTra = "";
    private String fecNacCon = "";
    private String otrGenCon = "";
    private String otrEstCiv = "";
    private String desNomCenLab = "";
    private String desDirCenLab = "";
    private String desAreTra = "";
    private String desCarCon = "";
    private String desPagWeb = "";
    private int idGrpCli = 0;
    private int idForPag = 0;
    private String codSitCli = "";
    private String fecReg = "";
    private String idUsu = "";    
    private String fileload;

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
     * @return the idTipDoc
     */
    public String getIdTipDoc() {
        return idTipDoc;
    }

    /**
     * @param idTipDoc the idTipDoc to set
     */
    public void setIdTipDoc(String idTipDoc) {
        this.idTipDoc = idTipDoc;
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
     * @return the desApeCon
     */
    public String getDesApeCon() {
        return desApeCon;
    }

    /**
     * @param desApeCon the desApeCon to set
     */
    public void setDesApeCon(String desApeCon) {
        this.desApeCon = desApeCon;
    }

    /**
     * @return the desNomCon
     */
    public String getDesNomCon() {
        return desNomCon;
    }

    /**
     * @param desNomCon the desNomCon to set
     */
    public void setDesNomCon(String desNomCon) {
        this.desNomCon = desNomCon;
    }

    /**
     * @return the desDirCon
     */
    public String getDesDirCon() {
        return desDirCon;
    }

    /**
     * @param desDirCon the desDirCon to set
     */
    public void setDesDirCon(String desDirCon) {
        this.desDirCon = desDirCon;
    }

    /**
     * @return the idDep
     */
    public String getIdDep() {
        return idDep;
    }

    /**
     * @param idDep the idDep to set
     */
    public void setIdDep(String idDep) {
        this.idDep = idDep;
    }

    /**
     * @return the idPrvDep
     */
    public String getIdPrvDep() {
        return idPrvDep;
    }

    /**
     * @param idPrvDep the idPrvDep to set
     */
    public void setIdPrvDep(String idPrvDep) {
        this.idPrvDep = idPrvDep;
    }

    /**
     * @return the idDisPrv
     */
    public String getIdDisPrv() {
        return idDisPrv;
    }

    /**
     * @param idDisPrv the idDisPrv to set
     */
    public void setIdDisPrv(String idDisPrv) {
        this.idDisPrv = idDisPrv;
    }

    /**
     * @return the numTelFij
     */
    public String getNumTelFij() {
        return numTelFij;
    }

    /**
     * @param numTelFij the numTelFij to set
     */
    public void setNumTelFij(String numTelFij) {
        this.numTelFij = numTelFij;
    }

    /**
     * @return the numTelMov
     */
    public String getNumTelMov() {
        return numTelMov;
    }

    /**
     * @param numTelMov the numTelMov to set
     */
    public void setNumTelMov(String numTelMov) {
        this.numTelMov = numTelMov;
    }

    /**
     * @return the numTelOfi
     */
    public String getNumTelOfi() {
        return numTelOfi;
    }

    /**
     * @param numTelOfi the numTelOfi to set
     */
    public void setNumTelOfi(String numTelOfi) {
        this.numTelOfi = numTelOfi;
    }

    /**
     * @return the numTelRP
     */
    public String getNumTelRP() {
        return numTelRP;
    }

    /**
     * @param numTelRP the numTelRP to set
     */
    public void setNumTelRP(String numTelRP) {
        this.numTelRP = numTelRP;
    }

    /**
     * @return the otrEmaPer
     */
    public String getOtrEmaPer() {
        return otrEmaPer;
    }

    /**
     * @param otrEmaPer the otrEmaPer to set
     */
    public void setOtrEmaPer(String otrEmaPer) {
        this.otrEmaPer = otrEmaPer;
    }

    /**
     * @return the otrEmaTra
     */
    public String getOtrEmaTra() {
        return otrEmaTra;
    }

    /**
     * @param otrEmaTra the otrEmaTra to set
     */
    public void setOtrEmaTra(String otrEmaTra) {
        this.otrEmaTra = otrEmaTra;
    }

    /**
     * @return the fecNacCon
     */
    public String getFecNacCon() {
        return fecNacCon;
    }

    /**
     * @param fecNacCon the fecNacCon to set
     */
    public void setFecNacCon(String fecNacCon) {
        this.fecNacCon = fecNacCon;
    }

    /**
     * @return the otrGenCon
     */
    public String getOtrGenCon() {
        return otrGenCon;
    }

    /**
     * @param otrGenCon the otrGenCon to set
     */
    public void setOtrGenCon(String otrGenCon) {
        this.otrGenCon = otrGenCon;
    }

    /**
     * @return the otrEstCiv
     */
    public String getOtrEstCiv() {
        return otrEstCiv;
    }

    /**
     * @param otrEstCiv the otrEstCiv to set
     */
    public void setOtrEstCiv(String otrEstCiv) {
        this.otrEstCiv = otrEstCiv;
    }

    /**
     * @return the desNomCenLab
     */
    public String getDesNomCenLab() {
        return desNomCenLab;
    }

    /**
     * @param desNomCenLab the desNomCenLab to set
     */
    public void setDesNomCenLab(String desNomCenLab) {
        this.desNomCenLab = desNomCenLab;
    }

    /**
     * @return the desDirCenLab
     */
    public String getDesDirCenLab() {
        return desDirCenLab;
    }

    /**
     * @param desDirCenLab the desDirCenLab to set
     */
    public void setDesDirCenLab(String desDirCenLab) {
        this.desDirCenLab = desDirCenLab;
    }

    /**
     * @return the desAreTra
     */
    public String getDesAreTra() {
        return desAreTra;
    }

    /**
     * @param desAreTra the desAreTra to set
     */
    public void setDesAreTra(String desAreTra) {
        this.desAreTra = desAreTra;
    }

    /**
     * @return the desCarCon
     */
    public String getDesCarCon() {
        return desCarCon;
    }

    /**
     * @param desCarCon the desCarCon to set
     */
    public void setDesCarCon(String desCarCon) {
        this.desCarCon = desCarCon;
    }

    /**
     * @return the desPagWeb
     */
    public String getDesPagWeb() {
        return desPagWeb;
    }

    /**
     * @param desPagWeb the desPagWeb to set
     */
    public void setDesPagWeb(String desPagWeb) {
        this.desPagWeb = desPagWeb;
    }

    /**
     * @return the idGrpCli
     */
    public int getIdGrpCli() {
        return idGrpCli;
    }

    /**
     * @param idGrpCli the idGrpCli to set
     */
    public void setIdGrpCli(int idGrpCli) {
        this.idGrpCli = idGrpCli;
    }

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
     * @return the codSitCli
     */
    public String getCodSitCli() {
        return codSitCli;
    }

    /**
     * @param codSitCli the codSitCli to set
     */
    public void setCodSitCli(String codSitCli) {
        this.codSitCli = codSitCli;
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
     * @return the fileload
     */
    public String getFileload() {
        return fileload;
    }

    /**
     * @param fileload the fileload to set
     */
    public void setFileload(String fileload) {
        this.fileload = fileload;
    }

}
