/*
 * Entidad: Modulos
 * Creado por: Angelo Ccoicca
 * Fecha de creación: 09-04-2014
 * Modificado por                   Fecha de Modificación
 * - 
 * -
 */

package entities;

import java.util.ArrayList;

public class Modulos 
{
    private String idModu="0";
    private String desModu="";
    private ArrayList<Opciones> listOpc = new ArrayList<Opciones>();
    private String numOrdVis="0";
    
    private String idTipUsu="0";
    private int indSinOpc=0;
    
    private int cantOpcDep=0;

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
     * @return the listOpc
     */
    public ArrayList<Opciones> getListOpc() {
        return listOpc;
    }

    /**
     * @param listOpc the listOpc to set
     */
    public void setListOpc(ArrayList<Opciones> listOpc) {
        this.listOpc = listOpc;
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

    /**
     * @return the indSinOpc
     */
    public int getIndSinOpc() {
        return indSinOpc;
    }

    /**
     * @param indSinOpc the indSinOpc to set
     */
    public void setIndSinOpc(int indSinOpc) {
        this.indSinOpc = indSinOpc;
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
}
