package entities;

public class EstadoCivil 
{
    private String idEstCiv;
    private String desEstCiv;

    public EstadoCivil(String idEstCiv,String desEstCiv)
    {
        this.idEstCiv=idEstCiv;
        this.desEstCiv=desEstCiv;
    }
    /**
     * @return the idEstCiv
     */
    public String getIdEstCiv() {
        return idEstCiv;
    }

    /**
     * @param idEstCiv the idEstCiv to set
     */
    public void setIdEstCiv(String idEstCiv) {
        this.idEstCiv = idEstCiv;
    }

    /**
     * @return the desEstCiv
     */
    public String getDesEstCiv() {
        return desEstCiv;
    }

    /**
     * @param desEstCiv the desEstCiv to set
     */
    public void setDesEstCiv(String desEstCiv) {
        this.desEstCiv = desEstCiv;
    }
}
