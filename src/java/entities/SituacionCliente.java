/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

/**
 *
 * @author Angel
 */
public class SituacionCliente {
    
    private String idSitCli="";
    private String desSitCli="";
    
    
    public SituacionCliente( String idsitcli, String dessitcli  ){
        
        this.idSitCli=idsitcli;
        this.desSitCli=dessitcli;
        
    }

    /**
     * @return the idSitCli
     */
    public String getIdSitCli() {
        return idSitCli;
    }

    /**
     * @param idSitCli the idSitCli to set
     */
    public void setIdSitCli(String idSitCli) {
        this.idSitCli = idSitCli;
    }

    /**
     * @return the desSitCli
     */
    public String getDesSitCli() {
        return desSitCli;
    }

    /**
     * @param desSitCli the desSitCli to set
     */
    public void setDesSitCli(String desSitCli) {
        this.desSitCli = desSitCli;
    }
    
}
