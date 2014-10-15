package actions;

import com.opensymphony.xwork2.ModelDriven;
import conexion.helper;
import entities.CondicionCampania;
import entities.Marcas;
import entities.Modelos;
import entities.Versiones;
import java.sql.ResultSet;
import java.util.ArrayList;

public class CondicionCampaniaAction extends MasterAction implements ModelDriven<CondicionCampania> {

    private CondicionCampania modelo = new CondicionCampania();
    private ArrayList<CondicionCampania> listCondicionCampania = new ArrayList<CondicionCampania>();
    private ArrayList<Marcas> listMarcas = new ArrayList<Marcas>();
    private ArrayList<Modelos> listModelo = new ArrayList<Modelos>();
    private ArrayList<Versiones> listVersion = new ArrayList<Versiones>();
    private ArrayList<String> listIdVerMod = new ArrayList<String>();

    @Override
    public CondicionCampania getModel() {
        tituloOpc = "Condiciones de la Campaña";
        idClaseAccion = 41;
        return modelo;
    }

    @Override
    public String execute() {
        idAccion = 1;
        verifAccionTipoUsuario();

        if (indErrAcc.equals("")) {
            nivBandeja = 2;

            varReturnProcess(0);
            if (!listVarReturn.isEmpty()) {
                curPagVis = Integer.parseInt(listVarReturn.get(0).toString().trim());
                modelo.setIdOriCam(Integer.parseInt(listVarReturn.get(1).toString().trim()));
                modelo.setIdCam(Integer.parseInt(listVarReturn.get(2).toString().trim()));
            }

            if (modelo.getIdOriCam() == 0 || modelo.getIdCam() == 0) {
                indErrParm = "error";
            } else {
                urlPaginacion = "condicionCampania/CondicionCampania";
                getDatosCampania();
                cantCondicionCampaniaIndex();
                verifPag();
                listCondicionCampaniaIndex();
            }

        }

        return SUCCESS;
    }

    private void cantCondicionCampaniaIndex() {
        helper conex = null;
        ResultSet tabla = null;

        try {
            conex = new helper();
            indError += conex.getErrorSQL().trim();

            if (!indError.trim().equals("")) {
                errores.add(indError);
            } else {
                tabla = conex.executeDataSet("CALL usp_cantCondicionCampaniaIndex(?,?)",
                        new Object[]{modelo.getIdOriCam(), modelo.getIdCam()});

                indError += conex.getErrorSQL();

                if (!indError.equals("")) {
                    errores.add(indError);
                } else {
                    while (tabla.next()) {
                        cantReg = tabla.getInt(1);
                    }
                }
            }
        } catch (Exception e) {
            indError += "error";
            errores.add(e.getMessage());
        } finally {
            try {
                tabla.close();
                conex.returnConnect();
            } catch (Exception e) {
            }
        }
    }

    private void listCondicionCampaniaIndex() {
        helper conex = null;
        ResultSet tabla = null;

        try {
            conex = new helper();
            indError += conex.getErrorSQL();

            if (!indError.equals("")) {
                errores.add(indError);
            } else {
                tabla = conex.executeDataSet("CALL usp_listCondicionCampaniaIndex(?,?,?,?)",
                        new Object[]{modelo.getIdOriCam(), modelo.getIdCam(),
                    (getCurPag()) * getRegPag(), getRegPag()});

                indError += conex.getErrorSQL();

                if (!indError.equals("")) {
                    errores.add(indError);
                } else {
                    CondicionCampania obj;
                    while (tabla.next()) {
                        obj = new CondicionCampania();
                        obj.setIdMar(tabla.getString("idMar"));
                        obj.setDesMarExt(tabla.getString("desMar"));
                        obj.setIdModMar(tabla.getString("idModMar"));
                        obj.setDesModExt(tabla.getString("desModMar"));
                        obj.setIdVerMod(tabla.getInt("idVerMod"));
                        obj.setDesVerExt(tabla.getString("desVerMod"));
                        obj.setEdoConCam(tabla.getString("edoConCam"));
                        listCondicionCampania.add(obj);
                    }
                }
            }
        } catch (Exception e) {
            indError = "error";
            errores.add(e.getMessage());
        } finally {
            try {
                tabla.close();
                conex.returnConnect();
            } catch (Exception e) {
            }
        }
    }

    private void getDescripcionOrigen() {
        if (modelo.getIdOriCam() == 1) {
            modelo.setDesOriCam("Toyota");
        } else if (modelo.getIdOriCam() == 2) {
            modelo.setDesOriCam("MAF");
        } else if (modelo.getIdOriCam() == 3) {
            modelo.setDesOriCam("Consecionario");
        } else if (modelo.getIdOriCam() == 4){
            modelo.setDesOriCam("Acuerdos");
        }
    }

    private void getDatosCampania() {
        getDescripcionOrigen();
        helper conex = null;
        ResultSet tabla = null;

        try {
            conex = new helper();
            indError = conex.getErrorSQL();

            if (!indError.equals("")) {
                errores.add(indError);
            } else {
                tabla = conex.executeDataSet("CALL usp_getDatosCampania(?,?)",
                        new Object[]{modelo.getIdOriCam(), modelo.getIdCam()});

                indError = conex.getErrorSQL();

                if (!indError.equals("")) {
                    errores.add(indError);
                } else {
                    while (tabla.next()) {
                        modelo.setDesCam(tabla.getString("desCam"));
                    }
                }
            }
        } catch (Exception e) {
            indError = "error";
            errores.add(e.getMessage());
        } finally {
            try {
                tabla.close();
                conex.returnConnect();
            } catch (Exception e) {
            }
        }
    }
    public String listVersiones() {
        idAccion = 2;

        verifAccionTipoUsuario();

        if (indErrAcc.equals("")) {
            regPag = 13;
            urlPaginacion = "condicionCampania/listVersionesCondicionCampania";
            divPopUp = "DIVversiones";
            
            listarMarcas();
            listarModelosPorMarcas();
            
            cantVersionesIndex();
            verifPag();
            listVersionesIndex();
        }
        return "listVersiones";
    }

    public void listarMarcas() {
        helper conex = new helper();
        ResultSet tabla = null;
        try {

            tabla = conex.executeDataSet("call usp_listMarcas()", new Object[]{});
            Marcas obj;
            while (tabla.next()) {
                obj = new Marcas();
                obj.setIdMar(tabla.getString("idMar"));
                if (modelo.getIdMar().trim().equals("")) {
                    modelo.setIdMar(tabla.getString("idMar"));
                }
                obj.setDesMar(tabla.getString("desMar"));
                listMarcas.add(obj);
            }
        } catch (Exception e) {
        } finally {
            try {
                tabla.close();
                conex.returnConnect();
            } catch (Exception e) {
            }
        }
    }

    public void listarModelosPorMarcas() {
        helper conex = new helper();
        ResultSet tabla = null;

        try {
            tabla = conex.executeDataSet("CALL usp_listModeloxMarca(?)", new Object[]{modelo.getIdMar()});
            Modelos obj;
            while (tabla.next()) {
                obj = new Modelos();
                obj.setIdModMar(tabla.getString("idModMar"));
                if (modelo.getIdModMar().trim().equals("")) {
                    modelo.setIdModMar(tabla.getString("idModMar"));
                }
                obj.setDesModMar(tabla.getString("desModMarAux"));
                listModelo.add(obj);
            }

        } catch (Exception e) {
            e.getStackTrace();
        } finally {
            try {
                tabla.close();
                conex.returnConnect();
            } catch (Exception e) {
                e.getStackTrace();
            }
        }
    }

    private void cantVersionesIndex() {
        helper conex = null;
        ResultSet tabla = null;
        try {
            conex = new helper();
            indError += conex.getErrorSQL();

            if (!indError.equals("")) {
                errores.add(indError);
            } else {
                tabla = conex.executeDataSet("CALL usp_cantVersionesIndex(?,?,?)",
                        new Object[]{modelo.getIdMar(), modelo.getIdModMar(), ""});

                indError += conex.getErrorSQL();
                if (!indError.equals("")) {
                    errores.add(indError);
                } else {
                    while (tabla.next()) {
                        cantReg = tabla.getInt(1);
                    }
                }
            }
        } catch (Exception e) {
            indError += "error";
            errores.add(e.getMessage());
        } finally {
            try {
                tabla.close();
                conex.returnConnect();
            } catch (Exception e) {
            }
        }
    }

    private void listVersionesIndex() {
        helper conex = null;
        ResultSet tabla = null;
        try {
            conex = new helper();
            indError += conex.getErrorSQL();
            if (!indError.equals("")) {
                errores.add(indError);
            } else {
                tabla = conex.executeDataSet("CALL usp_listVersionIndex(?,?,?,?,?)",
                        new Object[]{modelo.getIdMar(), modelo.getIdModMar(), "",
                    getCurPag() * regPag, regPag});
                indError += conex.getErrorSQL();
                if (!indError.equals("")) {
                    errores.add(indError);
                } else {
                    Versiones obj;
                    while (tabla.next()) {
                        obj = new Versiones();
                        obj.setIdVerMod(tabla.getString("idVerMod"));
                        obj.setDesVerMod(tabla.getString("desVerMod").trim());
                        listVersion.add(obj);
                    }
                }
            }
        } catch (Exception e) {
            indError += "error";
            errores.add(e.getMessage());
        } finally {
            try {
                tabla.close();
                conex.returnConnect();
            } catch (Exception e) {
            }
        }
    }   
    
    public String grabar() {
        idAccion = 3;

        verifAccionTipoUsuario();

        if (indErrAcc.equals("")) {
            if(listIdVerMod.size()>0) {
                helper conex = null;

                try {
                    conex = new helper();

                    indError += conex.getErrorSQL();

                    if(!indError.equals("")) {
                        errores.add(indError);
                    } else {
                        for(int i=0; i<listIdVerMod.size(); i++) {
                            indError += conex.executeNonQuery("CALL usp_insCondicionCampania(?,?,?,?,?)", 
                                    new Object[]{ modelo.getIdOriCam(),modelo.getIdCam(),modelo.getIdMar(),
                                        modelo.getIdModMar(),listIdVerMod.get(i) });
                            if(!indError.equals("")) {
                                errores.add(indError);
                            }
                        }
                    }
                } catch (Exception e) {
                    indError += "error";
                    errores.add(e.getMessage());
                } finally {
                    conex.returnConnect();
                }
            }
        }
        
        return "grabar";
    }
    
    
    public String actualizarEstado() {
        idAccion = 4;
        verifAccionTipoUsuario();

        if (indErrAcc.equals("")) {
            if (indError.equals("")) {
                helper conex = null;
                ResultSet tabla = null;

                try {
                    conex = new helper();
                    indError += conex.getErrorSQL();

                    if (!indError.equals("")) {
                        errores.add(indError);
                    } else {
                        indError += conex.executeNonQuery("CALL usp_updEstadoCondicionCampania(?,?,?,?,?,?)",
                                new Object[]{ modelo.getIdOriCam(),modelo.getIdCam(),modelo.getIdMar(),modelo.getIdModMar(),
                                    modelo.getIdVerMod(),modelo.getEdoConCam() });

                        if (!indError.equals("")) {
                            errores.add(indError);
                        }
                    }
                } catch (Exception e) {
                    indError = "error";
                    errores.add(e.getMessage());
                } finally {
                    try {
                        tabla.close();
                        conex.returnConnect();
                    } catch (Exception e) {
                    }
                }
            }
        }
        
        return "actualizarEstado";
    }
    
    /**
     * @return the modelo
     */
    public CondicionCampania getModelo() {
        return modelo;
    }

    /**
     * @param modelo the modelo to set
     */
    public void setModelo(CondicionCampania modelo) {
        this.modelo = modelo;
    }

    /**
     * @return the listCondicionCampania
     */
    public ArrayList<CondicionCampania> getListCondicionCampania() {
        return listCondicionCampania;
    }

    /**
     * @param listCondicionCampania the listCondicionCampania to set
     */
    public void setListCondicionCampania(ArrayList<CondicionCampania> listCondicionCampania) {
        this.listCondicionCampania = listCondicionCampania;
    }

    /**
     * @return the listMarcas
     */
    public ArrayList<Marcas> getListMarcas() {
        return listMarcas;
    }

    /**
     * @param listMarcas the listMarcas to set
     */
    public void setListMarcas(ArrayList<Marcas> listMarcas) {
        this.listMarcas = listMarcas;
    }

    /**
     * @return the listModelo
     */
    public ArrayList<Modelos> getListModelo() {
        return listModelo;
    }

    /**
     * @param listModelo the listModelo to set
     */
    public void setListModelo(ArrayList<Modelos> listModelo) {
        this.listModelo = listModelo;
    }

    /**
     * @return the listVersion
     */
    public ArrayList<Versiones> getListVersion() {
        return listVersion;
    }

    /**
     * @param listVersion the listVersion to set
     */
    public void setListVersion(ArrayList<Versiones> listVersion) {
        this.listVersion = listVersion;
    }

    public ArrayList<String> getListIdVerMod() {
        return listIdVerMod;
    }

    public void setListIdVerMod(ArrayList<String> listIdVerMod) {
        this.listIdVerMod = listIdVerMod;
    }
}
