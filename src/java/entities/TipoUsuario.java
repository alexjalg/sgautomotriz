/*
 * Entidad: Tipo de usuario
 * Creado por: Angelo Ccoicca
 * Fecha de creación: 10-04-2014
 * Modificado por                   Fecha de Modificación
 * - 
 * -
 */

package entities;

public class TipoUsuario 
{
    private String idTipUsu="0";
    private String desTipUsu="";

    /**
     * @return the idTipUsu
     */
    public String getIdTipUsu() {
        return idTipUsu;
    }

    /**
     * @param idTipUsu the idTipUsu to set
     */
    public void setIdTipUsu(String idTipUsu) {
        this.idTipUsu = idTipUsu;
    }

    /**
     * @return the desTipUsu
     */
    public String getDesTipUsu() {
        return desTipUsu;
    }

    /**
     * @param desTipUsu the desTipUsu to set
     */
    public void setDesTipUsu(String desTipUsu) {
        this.desTipUsu = desTipUsu;
    }
}
