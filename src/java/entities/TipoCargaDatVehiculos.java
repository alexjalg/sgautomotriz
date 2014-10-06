package entities;

public class TipoCargaDatVehiculos {
    
    private String idtipoCarga;
    private String tipoCarga;

    public TipoCargaDatVehiculos(String idtipoCarga,String tipoCarga)
    {
        this.idtipoCarga=idtipoCarga;
        this.tipoCarga=tipoCarga;
    }
    
    /**
     * @return the idtipoCarga
     */
    public String getIdtipoCarga() {
        return idtipoCarga;
    }

    /**
     * @param idtipoCarga the idtipoCarga to set
     */
    public void setIdtipoCarga(String idtipoCarga) {
        this.idtipoCarga = idtipoCarga;
    }

    /**
     * @return the tipoCarga
     */
    public String getTipoCarga() {
        return tipoCarga;
    }

    /**
     * @param tipoCarga the tipoCarga to set
     */
    public void setTipoCarga(String tipoCarga) {
        this.tipoCarga = tipoCarga;
    }
}
