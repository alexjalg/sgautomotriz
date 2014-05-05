/*
 * Entidad: Opciones
 * Creado por: Angelo Ccoicca
 * Fecha de creación: 09-04-2014
 * Modificado por                   Fecha de Modificación
 * - 
 * -
 */

package entities;

import java.util.ArrayList;

public class Opciones 
{
    private String idModu="0";
    private String desModu="";
    private String idOpc="0";
    private String desOpc="";
    private String desOpc_f="";
    private String desUrlOpc="";
    private ArrayList<Opciones> listSubOpc = new ArrayList<Opciones>();
    private String idOpcDep="0";
    private String numNivOpc="0";
    private String codPerOpc="";
    private String numOrdVis="0";
    
    private String idTipUsu="0";
    private int indSinSubOpc=0;
    private int cantOpcDep=0;

    /**
     * @return the idModu
     */
    public String getIdOpc() {
        return idOpc;
    }

    /**
     * @param idModu the idModu to set
     */
    public void setIdOpc(String idOpc) {
        this.idOpc = idOpc;
    }

    /**
     * @return the desOpc
     */
    public String getDesOpc() {
        return desOpc;
    }

    /**
     * @param desOpc the desOpc to set
     */
    public void setDesOpc(String desOpc) {
        this.desOpc = desOpc;
    }

    /**
     * @return the urlOpcModu
     */
    public String getDesUrlOpc() {
        return desUrlOpc;
    }

    /**
     * @param urlOpcModu the urlOpcModu to set
     */
    public void setDesUrlOpc(String desUrlOpc) {
        this.desUrlOpc = desUrlOpc;
    }

    public ArrayList<Opciones> getListSubOpc() {
            return listSubOpc;
    }

    public void setListSubOpc(ArrayList<Opciones> listSubOpc) {
            this.listSubOpc = listSubOpc;
    }

    /**
     * @return the idModu
     */
    public String getIdModu() {
        return idModu;
    }

    /**
     * @param idModu the idModu to set
     */
    public void setIdModu(String idModu) {
        this.idModu = idModu;
    }

    /**
     * @return the desModu
     */
    public String getDesModu() {
        return desModu;
    }

    /**
     * @param desModu the desModu to set
     */
    public void setDesModu(String desModu) {
        this.desModu = desModu;
    }

    /**
     * @return the idOpcDep
     */
    public String getIdOpcDep() {
        return idOpcDep;
    }

    /**
     * @param idOpcDep the idOpcDep to set
     */
    public void setIdOpcDep(String idOpcDep) {
        this.idOpcDep = idOpcDep;
    }

    /**
     * @return the numNivOpc
     */
    public String getNumNivOpc() {
        return numNivOpc;
    }

    /**
     * @param numNivOpc the numNivOpc to set
     */
    public void setNumNivOpc(String numNivOpc) {
        this.numNivOpc = numNivOpc;
    }

    /**
     * @return the codPerOpc
     */
    public String getCodPerOpc() {
        return codPerOpc;
    }

    /**
     * @param codPerOpc the codPerOpc to set
     */
    public void setCodPerOpc(String codPerOpc) {
        this.codPerOpc = codPerOpc;
    }

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
     * @return the indSinSubOpc
     */
    public int getIndSinSubOpc() {
        return indSinSubOpc;
    }

    /**
     * @param indSinSubOpc the indSinSubOpc to set
     */
    public void setIndSinSubOpc(int indSinSubOpc) {
        this.indSinSubOpc = indSinSubOpc;
    }

    /**
     * @return the desOpc_f
     */
    public String getDesOpc_f() {
        return desOpc_f;
    }

    /**
     * @param desOpc_f the desOpc_f to set
     */
    public void setDesOpc_f(String desOpc_f) {
        this.desOpc_f = desOpc_f;
    }

    /**
     * @return the cantOpcDep
     */
    public int getCantOpcDep() {
        return cantOpcDep;
    }

    /**
     * @param cantOpcDep the cantOpcDep to set
     */
    public void setCantOpcDep(int cantOpcDep) {
        this.cantOpcDep = cantOpcDep;
    }

    /**
     * @return the numOrdVis
     */
    public String getNumOrdVis() {
        return numOrdVis;
    }

    /**
     * @param numOrdVis the numOrdVis to set
     */
    public void setNumOrdVis(String numOrdVis) {
        this.numOrdVis = numOrdVis;
    }
}
