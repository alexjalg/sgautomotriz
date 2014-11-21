/*
 * Entidad: Registro de ventas
 * Creado por: Angelo Ccoicca
 * Fecha de creación: 25-09-2014
 * Modificado por                   Fecha de Modificación
 * - 
 * -
 */
package entities;

public class AnticiposRegistroVentas {
    private String idCli="";
    private String idNumIntRV="";
    private String impDisMonLocVen="";
    private String impDisMonLocImp="";
    private String impDisMonLocTot="";
    private String impDisMonExtVen="";
    private String impDisMonExtImp="";
    private String impDisMonExtTot="";
    private String indAnt="";
    private String edoAnt="";

    public String getIdCli() {
        return idCli;
    }

    public void setIdCli(String idCli) {
        this.idCli = idCli;
    }

    public String getIdNumIntRV() {
        return idNumIntRV;
    }

    public void setIdNumIntRV(String idNumIntRV) {
        this.idNumIntRV = idNumIntRV;
    }

    public String getImpDisMonLocVen() {
        impDisMonLocVen = impDisMonLocVen.trim();
        
        if(impDisMonLocVen.equals("")) {
            impDisMonLocVen = "0.00";
        }
        
        return impDisMonLocVen;
    }

    public void setImpDisMonLocVen(String impDisMonLocVen) {
        impDisMonLocVen = impDisMonLocVen.trim();
        
        if(impDisMonLocVen.equals("")) {
            impDisMonLocVen = "0.00";
        }
        
        this.impDisMonLocVen = impDisMonLocVen;
    }

    public String getImpDisMonLocImp() {
        impDisMonLocImp = impDisMonLocImp.trim();
        
        if(impDisMonLocImp.equals("")) {
            impDisMonLocImp = "0.00";
        }
        
        return impDisMonLocImp;
    }

    public void setImpDisMonLocImp(String impDisMonLocImp) {
        impDisMonLocImp = impDisMonLocImp.trim();
        
        if(impDisMonLocImp.equals("")) {
            impDisMonLocImp = "0.00";
        }
        
        this.impDisMonLocImp = impDisMonLocImp;
    }

    public String getImpDisMonLocTot() {
        impDisMonLocTot = impDisMonLocTot.trim();
        
        if(impDisMonLocTot.equals("")) {
            impDisMonLocTot = "0.00";
        }
        
        return impDisMonLocTot;
    }

    public void setImpDisMonLocTot(String impDisMonLocTot) {
        impDisMonLocTot = impDisMonLocTot.trim();
        
        if(impDisMonLocTot.equals("")) {
            impDisMonLocTot = "0.00";
        }
        
        this.impDisMonLocTot = impDisMonLocTot;
    }

    public String getImpDisMonExtVen() {
        impDisMonExtVen = impDisMonExtVen.trim();
        
        if(impDisMonExtVen.equals("")) {
            impDisMonExtVen = "0.00";
        }
        
        return impDisMonExtVen;
    }

    public void setImpDisMonExtVen(String impDisMonExtVen) {
        impDisMonExtVen = impDisMonExtVen.trim();
        
        if(impDisMonExtVen.equals("")) {
            impDisMonExtVen = "0.00";
        }
        
        this.impDisMonExtVen = impDisMonExtVen;
    }

    public String getImpDisMonExtImp() {
        impDisMonExtImp = impDisMonExtImp.trim();
        
        if(impDisMonExtImp.equals("")) {
            impDisMonExtImp = "0.00";
        }
        
        return impDisMonExtImp;
    }

    public void setImpDisMonExtImp(String impDisMonExtImp) {
        impDisMonExtImp = impDisMonExtImp.trim();
        
        if(impDisMonExtImp.equals("")) {
            impDisMonExtImp = "0.00";
        }
        
        this.impDisMonExtImp = impDisMonExtImp;
    }

    public String getImpDisMonExtTot() {
        impDisMonExtTot = impDisMonExtTot.trim();
        
        if(impDisMonExtTot.equals("")) {
            impDisMonExtTot = "0.00";
        }
        
        return impDisMonExtTot;
    }

    public void setImpDisMonExtTot(String impDisMonExtTot) {
        impDisMonExtTot = impDisMonExtTot.trim();
        
        if(impDisMonExtTot.equals("")) {
            impDisMonExtTot = "0.00";
        }
        
        this.impDisMonExtTot = impDisMonExtTot;
    }

    public String getIndAnt() {
        indAnt = indAnt.trim();
        
        return indAnt;
    }

    public void setIndAnt(String indAnt) {
        indAnt = indAnt.trim();
        
        this.indAnt = indAnt;
    }

    public String getEdoAnt() {
        edoAnt = edoAnt.trim();
        
        return edoAnt;
    }

    public void setEdoAnt(String edoAnt) {
        edoAnt = edoAnt.trim();
        
        this.edoAnt = edoAnt;
    }
}
