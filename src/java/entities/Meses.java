/*
 * Entidad: Meses
 * Creado por: Angelo Ccoicca
 * Fecha de creación: 16-09-2014
 * Modificado por                   Fecha de Modificación
 * - 
 * -
 */

package entities;

public class Meses {
    private int idMes=0;
    private String desMes="";
    private String desCorta="";
    
    public Meses() {
    }
    
    public Meses(int idMes,String desMes,String desCorta) {
        this.idMes=idMes;
        this.desMes=desMes;
        this.desCorta=desCorta;
    }

    public int getIdMes() {
        return idMes;
    }

    public void setIdMes(int idMes) {
        this.idMes = idMes;
    }

    public String getDesMes() {
        return desMes;
    }

    public void setDesMes(String desMes) {
        this.desMes = desMes;
    }

    public String getDesCorta() {
        return desCorta;
    }

    public void setDesCorta(String desCorta) {
        this.desCorta = desCorta;
    }
}
