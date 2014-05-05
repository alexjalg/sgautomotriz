/*
 * Entidad: Provincias
 * Creado por: Angelo Ccoicca
 * Fecha de creación: 12-03-2014
 * Modificado por                   Fecha de Modificación
 * - 
 * -
 */
package entities;

public class Provincias 
{
    private int idDep=0;
    private String desDep="";
    private int idPrvDep=0;
    private String desProv="";
    private String desProv_f="";

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
     * @return the idPrvDep
     */
    public int getIdPrvDep() {
        return idPrvDep;
    }

    /**
     * @param idPrvDep the idPrvDep to set
     */
    public void setIdPrvDep(int idPrvDep) {
        this.idPrvDep = idPrvDep;
    }

    /**
     * @return the desProv
     */
    public String getDesProv() {
        return desProv;
    }

    /**
     * @param desProv the desProv to set
     */
    public void setDesProv(String desProv) {
        this.desProv = desProv;
    }

    /**
     * @return the desProv_f
     */
    public String getDesProv_f() {
        return desProv_f;
    }

    /**
     * @param desProv_f the desProv_f to set
     */
    public void setDesProv_f(String desProv_f) {
        this.desProv_f = desProv_f;
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
}
