/*
 * Entidad: Situacion del vehiculo
 * Creado por: Angelo Ccoicca
 * Fecha de creación: 24-11-2014
 * Modificado por                   Fecha de Modificación
 * - 
 * -
 */
package entities;

public class SituacionVehiculo {
    private String codSitVeh = "";
    private String desSitVeh = "";

    public SituacionVehiculo() {
    }
    
    public SituacionVehiculo(String codSitVeh,String desSitVeh) {
        this.codSitVeh=codSitVeh;
        this.desSitVeh=desSitVeh;
    }
    
    public String getCodSitVeh() {
        codSitVeh = codSitVeh.trim();
        
        return codSitVeh;
    }

    public void setCodSitVeh(String codSitVeh) {
        codSitVeh = codSitVeh.trim();
        
        this.codSitVeh = codSitVeh;
    }

    public String getDesSitVeh() {
        desSitVeh = desSitVeh.trim();
        
        return desSitVeh;
    }

    public void setDesSitVeh(String desSitVeh) {
        desSitVeh = desSitVeh.trim();
        
        this.desSitVeh = desSitVeh;
    }
}
