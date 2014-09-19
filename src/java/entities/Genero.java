package entities;

public class Genero 
{
    private String idGen;
    private String desGen;
    
    public Genero(String idGen,String desGen)
    {
        this.idGen=idGen;
        this.desGen=desGen;
    }

    /**
     * @return the idGen
     */
    public String getIdGen() {
        return idGen;
    }

    /**
     * @param idGen the idGen to set
     */
    public void setIdGen(String idGen) {
        this.idGen = idGen;
    }

    /**
     * @return the desGen
     */
    public String getDesGen() {
        return desGen;
    }

    /**
     * @param desGen the desGen to set
     */
    public void setDesGen(String desGen) {
        this.desGen = desGen;
    }
}
