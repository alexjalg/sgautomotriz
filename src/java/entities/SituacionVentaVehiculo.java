/*
 * Entidad: Situacion de venta del vehiculo
 * Creado por: Angelo Ccoicca
 * Fecha de creación: 26-11-2014
 * Modificado por                   Fecha de Modificación
 * - 
 * -
 */
package entities;

public class SituacionVentaVehiculo {
    private String codSitVen = "";
    private String desSitVen = "";

    public SituacionVentaVehiculo() {
    }
    
    public SituacionVentaVehiculo(String codSitVen, String desSitVen) {
        this.codSitVen = codSitVen.trim();
        this.desSitVen = desSitVen.trim();
    }
    
    public String getCodSitVen() {
        codSitVen = codSitVen.trim();
        
        return codSitVen;
    }

    public void setCodSitVen(String codSitVen) {
        codSitVen = codSitVen.trim();
        
        this.codSitVen = codSitVen;
    }

    public String getDesSitVen() {
        desSitVen = desSitVen.trim();
        
        return desSitVen;
    }

    public void setDesSitVen(String desSitVen) {
        desSitVen = desSitVen.trim();
        
        this.desSitVen = desSitVen;
    }
}
