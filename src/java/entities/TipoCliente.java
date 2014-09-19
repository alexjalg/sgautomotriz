package entities;

public class TipoCliente 
{
    private String idTipCli;
    private String desTipCli;

    public TipoCliente(String idTipCli,String desTipCli)
    {
        this.idTipCli=idTipCli;
        this.desTipCli=desTipCli;
    }
    /**
     * @return the idTipCli
     */
    public String getIdTipCli() {
        return idTipCli;
    }

    /**
     * @param idTipCli the idTipCli to set
     */
    public void setIdTipCli(String idTipCli) {
        this.idTipCli = idTipCli;
    }

    /**
     * @return the desTipCli
     */
    public String getDesTipCli() {
        return desTipCli;
    }

    /**
     * @param desTipCli the desTipCli to set
     */
    public void setDesTipCli(String desTipCli) {
        this.desTipCli = desTipCli;
    }
}
