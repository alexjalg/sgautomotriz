/*
 * Entidad: Departamentos
 * Creado por: Angelo Ccoicca
 * Fecha de creación: 10-03-2014
 * Modificado por                   Fecha de Modificación
 * - 
 * -
 */
package entities;

public class Departamentos 
{
    private int idDep=0;
    private String desDep="";
    private String codTel="";
    private String desDep_f="";

    /**
     * @return the idDep
     */
    public int getIdDep() {
        return idDep;
    }

    /**
     * @param idDep the idDep to set
     */
    public void setIdDep(int idDep) {
        this.idDep = idDep;
    }

    /**
     * @return the desDep
     */
    public String getDesDep() {
        return desDep;
    }

    /**
     * @param desDep the desDep to set
     */
    public void setDesDep(String desDep) {
        this.desDep = desDep;
    }

    /**
     * @return the codTel
     */
    public String getCodTel() {
        return codTel;
    }

    /**
     * @param codTel the codTel to set
     */
    public void setCodTel(String codTel) {
        this.codTel = codTel;
    }

    /**
     * @return the desDep_f
     */
    public String getDesDep_f() {
        return desDep_f;
    }

    /**
     * @param desDep_f the desDep_f to set
     */
    public void setDesDep_f(String desDep_f) {
        this.desDep_f = desDep_f;
    }
}
