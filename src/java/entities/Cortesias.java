/*
 * Entidad: Cortesias
 * Creado por: Angelo Ccoicca
 * Fecha de creación: 19-09-2014
 * Modificado por                   Fecha de Modificación
 * - 
 * -
 */

package entities;

public class Cortesias {
    private int idCon = 0;
    private String desCon="";
    private String idMar = "";
    private String desMar="";
    private String idModMar="";
    private String desModMar="";
    private int idCorMar = 0;
    private String desCorMar="";
    private String edoCorMar="";

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
     * @return the idCorMar
     */
    public int getIdCorMar() {
        return idCorMar;
    }

    /**
     * @param idCorMar the idCorMar to set
     */
    public void setIdCorMar(int idCorMar) {
        this.idCorMar = idCorMar;
    }

    /**
     * @return the desCorMar
     */
    public String getDesCorMar() {
        desCorMar = desCorMar.trim();
        
        return desCorMar;
    }

    /**
     * @param desCorMar the desCorMar to set
     */
    public void setDesCorMar(String desCorMar) {
        desCorMar = desCorMar.trim();
        
        this.desCorMar = desCorMar;
    }

    /**
     * @return the edoCorMar
     */
    public String getEdoCorMar() {
        edoCorMar = edoCorMar.trim();
        
        return edoCorMar;
    }

    /**
     * @param edoCorMar the edoCorMar to set
     */
    public void setEdoCorMar(String edoCorMar) {
        edoCorMar = edoCorMar.trim();
        
        this.edoCorMar = edoCorMar;
    }

    public String getDesCon() {
        desCon = desCon.trim();
        
        return desCon;
    }

    public void setDesCon(String desCon) {
        desCon = desCon.trim();
        
        this.desCon = desCon;
    }

    public String getDesMar() {
        desMar = desMar.trim();
        
        return desMar;
    }

    public void setDesMar(String desMar) {
        desMar = desMar.trim();
        
        this.desMar = desMar;
    }

    public String getIdModMar() {
        return idModMar;
    }

    public void setIdModMar(String idModMar) {
        this.idModMar = idModMar;
    }

    public String getDesModMar() {
        return desModMar;
    }

    public void setDesModMar(String desModMar) {
        this.desModMar = desModMar;
    }
}
