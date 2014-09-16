/*
 * Entidad: Tipo de local
 * Creado por: Angelo Ccoicca
 * Fecha de creación: 11-09-2014
 * Modificado por                   Fecha de Modificación
 * - 
 * -
 */

package entities;

public class TipoLocal {
    private int idTipLoc=0;
    private String desTipLoc="";

    public int getIdTipLoc() {
        return idTipLoc;
    }

    public void setIdTipLoc(int idTipLoc) {
        this.idTipLoc = idTipLoc;
    }

    public String getDesTipLoc() {
        return desTipLoc;
    }

    public void setDesTipLoc(String desTipLoc) {
        this.desTipLoc = desTipLoc;
    }
}
