/*
 * Entidad: Adjuntos al cliente
 * Creado por: Angelo Ccoicca
 * Fecha de creación: 24-09-2014
 * Modificado por                   Fecha de Modificación
 * - 
 * -
 */

package entities;

public class AdjuntosCliente {
    private String idCli="";
    private String desCli="";
    private String idAdjCli="";
    private String codTipAdj="";
    private String desTipAdj="";
    private String desApeAdj="";
    private String desNomAdj="";
    private String desAdjCli="";

    public String getIdCli() {
        idCli=idCli.trim();
        
        return idCli;
    }

    public void setIdCli(String idCli) {
        idCli=idCli.trim();
        
        this.idCli = idCli;
    }

    public String getIdAdjCli() {
        idAdjCli=idAdjCli.trim();
        
        return idAdjCli;
    }

    public void setIdAdjCli(String idAdjCli) {
        idAdjCli=idAdjCli.trim();
        
        this.idAdjCli = idAdjCli;
    }

    public String getCodTipAdj() {
        codTipAdj=codTipAdj.trim();
        
        return codTipAdj;
    }

    public void setCodTipAdj(String codTipAdj) {
        codTipAdj=codTipAdj.trim();
        
        this.codTipAdj = codTipAdj;
    }

    public String getDesApeAdj() {
        desApeAdj=desApeAdj.trim();
        
        return desApeAdj;
    }

    public void setDesApeAdj(String desApeAdj) {
        desApeAdj=desApeAdj.trim();
        
        this.desApeAdj = desApeAdj;
    }

    public String getDesNomAdj() {
        desNomAdj=desNomAdj.trim();
        
        return desNomAdj;
    }

    public void setDesNomAdj(String desNomAdj) {
        desNomAdj=desNomAdj.trim();
        
        this.desNomAdj = desNomAdj;
    }

    public String getDesCli() {
        desCli=desCli.trim();
        
        return desCli;
    }

    public void setDesCli(String desCli) {
        desCli=desCli.trim();
        
        this.desCli = desCli;
    }

    public String getDesAdjCli() {
        desAdjCli=desAdjCli.trim();
        
        return desAdjCli;
    }

    public void setDesAdjCli(String desAdjCli) {
        desAdjCli=desAdjCli.trim();
        
        this.desAdjCli = desAdjCli;
    }

    public String getDesTipAdj() {
        desTipAdj=desTipAdj.trim();
        
        return desTipAdj;
    }

    public void setDesTipAdj(String desTipAdj) {
        desTipAdj=desTipAdj.trim();
        
        this.desTipAdj = desTipAdj;
    }
}
