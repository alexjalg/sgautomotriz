/*
 * Entidad: Registro de ventas
 * Creado por: Angelo Ccoicca
 * Fecha de creación: 25-09-2014
 * Modificado por                   Fecha de Modificación
 * - 
 * -
 */
package entities;

public class RegistroVentas {
    private String idNumIntRV="";
    private String idNumIntRV_f="";
    private String idTipDocVen="";
    private String desTipDocVen="";
    private String idTipDocVen_f="";
    private String desNumDocVen="";
    private String desNumDocVen_f="";
    private String fecEmiDocVen="";
    private String fecEmiDocVen_f="";
    private int codMonDocVen=0;
    private String desMonDocVen="";
    private String impTipCamVen="";
    private String impMOAntVen="";
    private String impMOAntImp="";
    private String impMOAntTot="";
    private String impMonLocVen="";
    private String impMonLocDes="";
    private String impMonLocDesCam="";
    private String impMonLocImp="";
    private String impMonLocTot="";
    private String impMonLocTotPag="";
    private String impMonExtVen="";
    private String impMonExtDes="";
    private String impMonExtDesCam="";
    private String impMonExtImp="";
    private String impMonExtTot="";
    private String impMonExtTotPag="";
    private int idForPag=0;
    private String desForPag="";
    private String desObsDoc="";
    private String idCli="";
    private String idCli_f="";
    private String desCli="";
    private String desCli_f="";
    private String desCli_h="";
    private String idVeh="";
    private String desVeh="";
    private String desVeh_h="";
    private int idCon=0;
    private int idLocCon=0;
    private String codOpeRegVen="";
    private String otrOpeAfeImp="";
    private String desDocRef1="";
    private String desDocRef2="";
    private String indDoc="";
    private String edoDoc="";
    private String fecReg="";
    private String fecReg_f="";
    private String idUsu="";

    public String getIdNumIntRV() {
        idNumIntRV = idNumIntRV.trim();
        
        if(idNumIntRV.equals("")) {
            idNumIntRV = "0";
        }
        
        return idNumIntRV;
    }

    public void setIdNumIntRV(String idNumIntRV) {
        idNumIntRV = idNumIntRV.trim();
        
        if(idNumIntRV.equals("")) {
            idNumIntRV = "0";
        }
        
        this.idNumIntRV = idNumIntRV;
    }

    public String getIdTipDocVen() {
        idTipDocVen = idTipDocVen.trim();
        
        return idTipDocVen;
    }

    public void setIdTipDocVen(String idTipDocVen) {
        idTipDocVen = idTipDocVen.trim();
        
        this.idTipDocVen = idTipDocVen;
    }

    public String getDesNumDocVen() {
        desNumDocVen = desNumDocVen.trim();
        
        return desNumDocVen;
    }

    public void setDesNumDocVen(String desNumDocVen) {
        desNumDocVen = desNumDocVen.trim();
        
        this.desNumDocVen = desNumDocVen;
    }

    public String getFecEmiDocVen() {
        fecEmiDocVen = fecEmiDocVen.trim();
        
        return fecEmiDocVen;
    }

    public void setFecEmiDocVen(String fecEmiDocVen) {
        fecEmiDocVen = fecEmiDocVen.trim();
        
        this.fecEmiDocVen = fecEmiDocVen;
    }

    public String getFecEmiDocVen_f() {
        fecEmiDocVen_f = fecEmiDocVen_f.trim();
        
        return fecEmiDocVen_f;
    }

    public void setFecEmiDocVen_f(String fecEmiDocVen_f) {
        fecEmiDocVen_f = fecEmiDocVen_f.trim();
        
        this.fecEmiDocVen_f = fecEmiDocVen_f;
    }

    public int getCodMonDocVen() {
        return codMonDocVen;
    }

    public void setCodMonDocVen(int codMonDocVen) {
        this.codMonDocVen = codMonDocVen;
    }

    public String getImpTipCamVen() {
        impTipCamVen = impTipCamVen.trim();
        
        if(impTipCamVen.equals("")) {
            impTipCamVen = "0.00";
        }
        
        return impTipCamVen;
    }

    public void setImpTipCamVen(String impTipCamVen) {
        impTipCamVen = impTipCamVen.trim();
        
        if(impTipCamVen.equals("")) {
            impTipCamVen = "0.00";
        }
        
        this.impTipCamVen = impTipCamVen;
    }

    public String getImpMOAntVen() {
        impMOAntVen = impMOAntVen.trim();
        
        if(impMOAntVen.equals("")) {
            impMOAntVen = "0.00";
        }
        
        return impMOAntVen;
    }

    public void setImpMOAntVen(String impMOAntVen) {
        impMOAntVen = impMOAntVen.trim();
        
        if(impMOAntVen.equals("")) {
            impMOAntVen = "0.00";
        }
        
        this.impMOAntVen = impMOAntVen;
    }

    public String getImpMOAntImp() {
        impMOAntImp = impMOAntImp.trim();
        
        if(impMOAntImp.equals("")) {
            impMOAntImp = "0.00";
        }
        
        return impMOAntImp;
    }

    public void setImpMOAntImp(String impMOAntImp) {
        impMOAntImp = impMOAntImp.trim();
        
        if(impMOAntImp.equals("")) {
            impMOAntImp = "0.00";
        }
        
        this.impMOAntImp = impMOAntImp;
    }

    public String getImpMOAntTot() {
        impMOAntTot = impMOAntTot.trim();
        
        if(impMOAntTot.equals("")) {
            impMOAntTot = "0.00";
        }
        
        return impMOAntTot;
    }

    public void setImpMOAntTot(String impMOAntTot) {
        impMOAntTot = impMOAntTot.trim();
        
        if(impMOAntTot.equals("")) {
            impMOAntTot = "0.00";
        }
        
        this.impMOAntTot = impMOAntTot;
    }

    public String getImpMonLocVen() {
        impMonLocVen = impMonLocVen.trim();
        
        if(impMonLocVen.equals("")) {
            impMonLocVen = "0.00";
        }
        
        return impMonLocVen;
    }

    public void setImpMonLocVen(String impMonLocVen) {
        impMonLocVen = impMonLocVen.trim();
        
        if(impMonLocVen.equals("")) {
            impMonLocVen = "0.00";
        }
        
        this.impMonLocVen = impMonLocVen;
    }

    public String getImpMonLocDes() {
        impMonLocDes = impMonLocDes.trim();
        
        if(impMonLocDes.equals("")) {
            impMonLocDes = "0.00";
        }
        
        return impMonLocDes;
    }

    public void setImpMonLocDes(String impMonLocDes) {
        impMonLocDes = impMonLocDes.trim();
        
        if(impMonLocDes.equals("")) {
            impMonLocDes = "0.00";
        }
        
        this.impMonLocDes = impMonLocDes;
    }

    public String getImpMonLocImp() {
        impMonLocImp = impMonLocImp.trim();
        
        if(impMonLocImp.equals("")) {
            impMonLocImp = "0.00";
        }
        
        return impMonLocImp;
    }

    public void setImpMonLocImp(String impMonLocImp) {
        impMonLocImp = impMonLocImp.trim();
        
        if(impMonLocImp.equals("")) {
            impMonLocImp = "0.00";
        }
        
        this.impMonLocImp = impMonLocImp;
    }

    public String getImpMonLocTot() {
        impMonLocTot = impMonLocTot.trim();
        
        if(impMonLocTot.equals("")) {
            impMonLocTot = "0.00";
        }
        
        return impMonLocTot;
    }

    public void setImpMonLocTot(String impMonLocTot) {
        impMonLocTot = impMonLocTot.trim();
        
        if(impMonLocTot.equals("")) {
            impMonLocTot = "0.00";
        }
        
        this.impMonLocTot = impMonLocTot;
    }

    public String getImpMonExtVen() {
        impMonExtVen = impMonExtVen.trim();
        
        if(impMonExtVen.equals("")) {
            impMonExtVen = "0.00";
        }
        
        return impMonExtVen;
    }

    public void setImpMonExtVen(String impMonExtVen) {
        impMonExtVen = impMonExtVen.trim();
        
        if(impMonExtVen.equals("")) {
            impMonExtVen = "0.00";
        }
        
        this.impMonExtVen = impMonExtVen;
    }

    public String getImpMonExtDes() {
        impMonExtDes = impMonExtDes.trim();
        
        if(impMonExtDes.equals("")) {
            impMonExtDes = "0.00";
        }
        
        return impMonExtDes;
    }

    public void setImpMonExtDes(String impMonExtDes) {
        impMonExtDes = impMonExtDes.trim();
        
        if(impMonExtDes.equals("")) {
            impMonExtDes = "0.00";
        }
        
        this.impMonExtDes = impMonExtDes;
    }

    public String getImpMonExtImp() {
        impMonExtImp = impMonExtImp.trim();
        
        if(impMonExtImp.equals("")) {
            impMonExtImp = "0.00";
        }
        
        return impMonExtImp;
    }

    public void setImpMonExtImp(String impMonExtImp) {
        impMonExtImp = impMonExtImp.trim();
        
        if(impMonExtImp.equals("")) {
            impMonExtImp = "0.00";
        }
        
        this.impMonExtImp = impMonExtImp;
    }

    public String getImpMonExtTot() {
        impMonExtTot = impMonExtTot.trim();
        
        if(impMonExtTot.equals("")) {
            impMonExtTot = "0.00";
        }
        
        return impMonExtTot;
    }

    public void setImpMonExtTot(String impMonExtTot) {
        impMonExtTot = impMonExtTot.trim();
        
        if(impMonExtTot.equals("")) {
            impMonExtTot = "0.00";
        }
        
        this.impMonExtTot = impMonExtTot;
    }

    public int getIdForPag() {
        return idForPag;
    }

    public void setIdForPag(int idForPag) {
        this.idForPag = idForPag;
    }

    public String getDesObsDoc() {
        desObsDoc = desObsDoc.trim();
        
        return desObsDoc;
    }

    public void setDesObsDoc(String desObsDoc) {
        desObsDoc = desObsDoc.trim();
        
        this.desObsDoc = desObsDoc;
    }

    public String getIdCli() {
        idCli = idCli.trim();
        
        return idCli;
    }

    public void setIdCli(String idCli) {
        idCli = idCli.trim();
        
        this.idCli = idCli;
    }

    public String getDesCli() {
        desCli = desCli.trim();
        
        return desCli;
    }

    public void setDesCli(String desCli) {
        desCli = desCli.trim();
        
        this.desCli = desCli;
    }

    public String getDesCli_f() {
        desCli_f = desCli_f.trim();
        
        return desCli_f;
    }

    public void setDesCli_f(String desCli_f) {
        desCli_f = desCli_f.trim();
        
        this.desCli_f = desCli_f;
    }

    public String getIdVeh() {
        idVeh = idVeh.trim();
        
        return idVeh;
    }

    public void setIdVeh(String idVeh) {
        idVeh = idVeh.trim();
        
        this.idVeh = idVeh;
    }

    public int getIdCon() {
        return idCon;
    }

    public void setIdCon(int idCon) {
        this.idCon = idCon;
    }

    public int getIdLocCon() {
        return idLocCon;
    }

    public void setIdLocCon(int idLocCon) {
        this.idLocCon = idLocCon;
    }

    public String getCodOpeRegVen() {
        codOpeRegVen = codOpeRegVen.trim();
        
        return codOpeRegVen;
    }

    public void setCodOpeRegVen(String codOpeRegVen) {
        codOpeRegVen = codOpeRegVen.trim();
        
        this.codOpeRegVen = codOpeRegVen;
    }

    public String getIndDoc() {
        indDoc = indDoc.trim();
        
        return indDoc;
    }

    public void setIndDoc(String indDoc) {
        indDoc = indDoc.trim();
        
        this.indDoc = indDoc;
    }

    public String getEdoDoc() {
        edoDoc = edoDoc.trim();
        
        return edoDoc;
    }

    public void setEdoDoc(String edoDoc) {
        edoDoc = edoDoc.trim();
        
        this.edoDoc = edoDoc;
    }

    public String getFecReg() {
        fecReg = fecReg.trim();
        
        return fecReg;
    }

    public void setFecReg(String fecReg) {
        fecReg = fecReg.trim();
        
        this.fecReg = fecReg;
    }

    public String getIdUsu() {
        idUsu = idUsu.trim();
        
        return idUsu;
    }

    public void setIdUsu(String idUsu) {
        idUsu = idUsu.trim();
        
        this.idUsu = idUsu;
    }

    public String getIdNumIntRV_f() {
        idNumIntRV_f = idNumIntRV_f.trim();
        if(idNumIntRV_f.equals("")) {
            idNumIntRV_f = "0";
        }
        
        return idNumIntRV_f;
    }

    public void setIdNumIntRV_f(String idNumIntRV_f) {
        idNumIntRV_f = idNumIntRV_f.trim();
        if(idNumIntRV_f.equals("")) {
            idNumIntRV_f = "0";
        }
        
        this.idNumIntRV_f = idNumIntRV_f;
    }

    public String getIdTipDocVen_f() {
        idTipDocVen_f = idTipDocVen_f.trim();
        
        return idTipDocVen_f;
    }

    public void setIdTipDocVen_f(String idTipDocVen_f) {
        idTipDocVen_f = idTipDocVen_f.trim();
        
        this.idTipDocVen_f = idTipDocVen_f;
    }

    public String getDesNumDocVen_f() {
        desNumDocVen_f = desNumDocVen_f.trim();
        
        return desNumDocVen_f;
    }

    public void setDesNumDocVen_f(String desNumDocVen_f) {
        desNumDocVen_f = desNumDocVen_f.trim();
        
        this.desNumDocVen_f = desNumDocVen_f;
    }

    public String getIdCli_f() {
        idCli_f = idCli_f.trim();
        
        return idCli_f;
    }

    public void setIdCli_f(String idCli_f) {
        idCli_f = idCli_f.trim();
        
        this.idCli_f = idCli_f;
    }

    public String getFecReg_f() {
        fecReg_f = fecReg_f.trim();
        
        return fecReg_f;
    }

    public void setFecReg_f(String fecReg_f) {
        fecReg_f = fecReg_f.trim();
        
        this.fecReg_f = fecReg_f;
    }

    public String getDesTipDocVen() {
        desTipDocVen = desTipDocVen.trim();
        
        return desTipDocVen;
    }

    public void setDesTipDocVen(String desTipDocVen) {
        desTipDocVen = desTipDocVen.trim();
        
        this.desTipDocVen = desTipDocVen;
    }

    public String getDesVeh() {
        desVeh = desVeh.trim();
        
        return desVeh;
    }

    public void setDesVeh(String desVeh) {
        desVeh = desVeh.trim();
        
        this.desVeh = desVeh;
    }

    public String getDesCli_h() {
        desCli_h = desCli_h.trim();
        
        return desCli_h;
    }

    public void setDesCli_h(String desCli_h) {
        desCli_h = desCli_h.trim();
        
        this.desCli_h = desCli_h;
    }

    public String getDesVeh_h() {
        desVeh_h = desVeh_h.trim();
        
        return desVeh_h;
    }

    public void setDesVeh_h(String desVeh_h) {
        desVeh_h = desVeh_h.trim();
        
        this.desVeh_h = desVeh_h;
    }

    public String getDesMonDocVen() {
        desMonDocVen = desMonDocVen.trim();
        
        return desMonDocVen;
    }

    public void setDesMonDocVen(String desMonDocVen) {
        desMonDocVen = desMonDocVen.trim();
        
        this.desMonDocVen = desMonDocVen;
    }

    public String getDesForPag() {
        desForPag = desForPag.trim();
        
        return desForPag;
    }

    public void setDesForPag(String desForPag) {
        desForPag = desForPag.trim();
        
        this.desForPag = desForPag;
    }

    public String getOtrOpeAfeImp() {
        otrOpeAfeImp = otrOpeAfeImp.trim();
        
        return otrOpeAfeImp;
    }

    public void setOtrOpeAfeImp(String otrOpeAfeImp) {
        otrOpeAfeImp = otrOpeAfeImp.trim();
        
        this.otrOpeAfeImp = otrOpeAfeImp;
    }

    public String getDesDocRef1() {
        desDocRef1 = desDocRef1.trim();
        
        return desDocRef1;
    }

    public void setDesDocRef1(String desDocRef1) {
        desDocRef1 = desDocRef1.trim();
        
        this.desDocRef1 = desDocRef1;
    }

    public String getDesDocRef2() {
        desDocRef2 = desDocRef2.trim();
        
        return desDocRef2;
    }

    public void setDesDocRef2(String desDocRef2) {
        desDocRef2 = desDocRef2.trim();
        
        this.desDocRef2 = desDocRef2;
    }

    public String getImpMonLocTotPag() {
        impMonLocTotPag = impMonLocTotPag.trim();
        
        if(impMonLocTotPag.equals("")) {
            impMonLocTotPag = "0.00";
        }
        
        return impMonLocTotPag;
    }

    public void setImpMonLocTotPag(String impMonLocTotPag) {
        impMonLocTotPag = impMonLocTotPag.trim();
        
        if(impMonLocTotPag.equals("")) {
            impMonLocTotPag = "0.00";
        }
        
        this.impMonLocTotPag = impMonLocTotPag;
    }

    public String getImpMonExtTotPag() {
        impMonExtTotPag = impMonExtTotPag.trim();
        
        if(impMonExtTotPag.equals("")) {
            impMonExtTotPag = "0.00";
        }
        
        return impMonExtTotPag;
    }

    public void setImpMonExtTotPag(String impMonExtTotPag) {
        impMonExtTotPag = impMonExtTotPag.trim();
        
        if(impMonExtTotPag.equals("")) {
            impMonExtTotPag = "0.00";
        }
        
        this.impMonExtTotPag = impMonExtTotPag;
    }

    public String getImpMonLocDesCam() {
        impMonLocDesCam = impMonLocDesCam.trim();
        
        if(impMonLocDesCam.equals("")) {
            impMonLocDesCam = "0.00";
        }
        
        return impMonLocDesCam;
    }

    public void setImpMonLocDesCam(String impMonLocDesCam) {
        impMonLocDesCam = impMonLocDesCam.trim();
        
        if(impMonLocDesCam.equals("")) {
            impMonLocDesCam = "0.00";
        }
        
        this.impMonLocDesCam = impMonLocDesCam;
    }

    public String getImpMonExtDesCam() {
        impMonExtDesCam = impMonExtDesCam.trim();
        
        if(impMonExtDesCam.equals("")) {
            impMonExtDesCam = "0.00";
        }
        
        return impMonExtDesCam;
    }

    public void setImpMonExtDesCam(String impMonExtDesCam) {
        impMonExtDesCam = impMonExtDesCam.trim();
        
        if(impMonExtDesCam.equals("")) {
            impMonExtDesCam = "0.00";
        }
        
        this.impMonExtDesCam = impMonExtDesCam;
    }
}
