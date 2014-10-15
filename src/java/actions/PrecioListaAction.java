package actions;

import com.opensymphony.xwork2.ModelDriven;
import conexion.helper;
import entities.PrecioLista;
import entities.Marcas;
import entities.Modelos;
import java.sql.ResultSet;
import java.util.ArrayList;

public class PrecioListaAction extends MasterAction implements ModelDriven<PrecioLista> {

    private ArrayList<PrecioLista> listPrecioLista = new ArrayList<PrecioLista>();
    private PrecioLista modelo = new PrecioLista();
    private ArrayList<Marcas> listMarcas = new ArrayList<Marcas>();
    private ArrayList<Modelos> listModelo = new ArrayList<Modelos>();

    @Override
    public PrecioLista getModel() {
        tituloOpc = "Precio de Lista";
        idClaseAccion = 35;
        return modelo;
    }

    public String vrfSeleccion() {
        idAccion = 1;
        verifAccionTipoUsuario();
        if (indErrAcc.equals("")) {
            if (modelo.getIdVerMod() == 0) {
                indError = "error";
                errores.add("No ha seleccionado ningun registro");
            }
        }
        return "vrfSeleccion";
    }

    @Override
    public String execute() {
        idAccion = 2;
        verifAccionTipoUsuario();
        if (indErrAcc.equals("")) {
            nivBandeja = 1;
            urlPaginacion = "precioLista/PrecioLista";
            varReturn = varReturn.trim();
            varReturnProcess(0);
            if (!listVarReturn.isEmpty()) {
                curPagVis = Integer.parseInt(listVarReturn.get(0).toString().trim());
                modelo.setIdMar(listVarReturn.get(1).toString().trim());
                modelo.setIdModMar(listVarReturn.get(2).toString().trim());
                modelo.setNumAnoLis(Integer.parseInt(listVarReturn.get(3).toString().trim()));
            }
            
            setListAnios();
            
            if (modelo.getNumAnoLis() == 0) {
                modelo.setNumAnoLis(Integer.parseInt(getCurYear()));
            }


            listarMarcas();
            listarModelosPorMarcas();

            cantPrecioListaIndex();
            verifPag();
            listPrecioListaIndex();
        }
        return "success";
    }
    
    private void cantPrecioListaIndex() {
        helper conex = null;
        ResultSet tabla = null;
        try {
            conex = new helper();
            indError += conex.getErrorSQL();

            if (!indError.equals("")) {
                errores.add(indError);
            } else {
                tabla = conex.executeDataSet("CALL usp_cantPrecioListaIndex(?,?,?)",
                        new Object[]{modelo.getIdMar(), modelo.getIdModMar(), modelo.getNumAnoLis()});

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

    private void listPrecioListaIndex() {
        helper conex = null;
        ResultSet tabla = null;
        try {
            conex = new helper();
            indError += conex.getErrorSQL();
            if (!indError.equals("")) {
                errores.add(indError);
            } else {
                tabla = conex.executeDataSet("CALL usp_listPrecioListaIndex(?,?,?,?,?)",
                        new Object[]{modelo.getIdMar(), modelo.getIdModMar(), modelo.getNumAnoLis(),
                    getCurPag() * regPag, regPag});
                indError += conex.getErrorSQL();
                if (!indError.equals("")) {
                    errores.add(indError);
                } else {
                    PrecioLista obj;
                    while (tabla.next()) {
                        obj = new PrecioLista();
                        obj.setIdVerMod(tabla.getInt("idVerMod"));
                        obj.setDesVerMod(tabla.getString("desVerMod"));
                        obj.setImpPreLis(tabla.getString("impPreLis"));
                        obj.setImpPreMin(tabla.getString("impPreMin"));
                        obj.setImpPreFlo(tabla.getString("impPreFlo"));
                        listPrecioLista.add(obj);
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

    public String populateModelo() {
        helper conex = new helper();
        ResultSet tabla = null;

        try {
            tabla = conex.executeDataSet("CALL usp_listModeloxMarca(?)", new Object[]{modelo.getIdMar()});
            Modelos obj;
            while (tabla.next()) {
                obj = new Modelos();
                obj.setIdModMar(tabla.getString("idModMar"));
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

        return "listmodelo";
    }

    public String modificar() {
        idAccion = 3;
        verifAccionTipoUsuario();
        
        if (indErrAcc.equals("")) {
            nivBandeja = 1;

            if (!opcion.trim().equals("A") && !opcion.trim().equals("M")  || modelo.getIdMar().trim().equals("") || modelo.getIdModMar().trim().equals("") || modelo.getNumAnoLis() == 0) {
                indErrParm = "error";
            } else {
                varReturnProcess(1);

                if (opcion.equals("M")) {
                    if (modelo.getIdVerMod() == 0) {
                        indErrParm = "error";
                    } else {
                        getDatosMarcaModelVersion();
                        
                        getDatosPrecioLista();
                        formURL = baseURL + "precioLista/actualizarPrecioLista";
                    }
                }
            }
        }

        return "adicionar";
    }
    
    public void getDatosMarcaModelVersion() {
        helper conex = null;
        ResultSet tabla = null;
        try {
            conex = new helper();
            indError += conex.getErrorSQL();
            
            if(!indError.equals("")) {
                errores.add(indError);
            } else {
                tabla = conex.executeDataSet("call usp_getDatosMarca(?)", 
                    new Object[]{ modelo.getIdMar() });
                indError += conex.getErrorSQL();
                
                if(!indError.equals("")) {
                    errores.add(indError);
                } else {
                    while (tabla.next()) {
                        modelo.setDesMar(tabla.getString("desMar"));
                    }
                    
                    tabla = null;
                    tabla = conex.executeDataSet("CALL usp_getDatosModelo(?,?)", 
                            new Object[]{ modelo.getIdMar(),modelo.getIdModMar() });
                    indError += conex.getErrorSQL();
                    
                    if(!indError.equals("")) {
                        errores.add(indError);
                    } else {
                        while(tabla.next()) {
                            modelo.setDesModMar(tabla.getString("desModMar"));
                        }
                        
                        tabla = null;
                        tabla = conex.executeDataSet("CALL usp_getDatosVersiones(?,?,?)", 
                                new Object[]{ modelo.getIdMar(),modelo.getIdModMar(),modelo.getIdVerMod() });
                        indError += conex.getErrorSQL();
                        
                        if(!indError.equals("")) {
                            errores.add(indError);
                        } else {
                            while(tabla.next()) {
                                modelo.setDesVerMod(tabla.getString("desVerMod"));
                            }
                        }
                        
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

    public String actualizar() {
        idAccion = 4;
        verifAccionTipoUsuario();

        if (indErrAcc.equals("")) {
            modelo.setImpPreLis(modelo.getImpPreLis());
            modelo.setImpPreMin(modelo.getImpPreMin());
            modelo.setImpPreFlo(modelo.getImpPreFlo());
            if (modelo.getImpPreLis().trim().equals("")) {
                indError += "error";
                errores.add("No ha Ingresado Imp Prec. Lista");
            }
            if (modelo.getImpPreMin().trim().equals("")) {
                indError += "error";
                errores.add("No ha Ingresado Imp Prec. Minimo");
            }
            if (modelo.getImpPreFlo().trim().equals("")) {
                indError += "error";
                errores.add("No ha Ingresado Imp Prec. Flota");
            }

            if (indError.equals("")) {
                helper conex = null;
                ResultSet tabla = null;

                try {
                    conex = new helper();
                    indError += conex.getErrorSQL();

                    if (!indError.equals("")) {
                        errores.add(indError);
                    } else {
                        indError += conex.executeNonQuery("CALL usp_insPrecioLista(?,?,?,?,?,?,?)",
                                new Object[]{modelo.getIdMar(), modelo.getIdModMar(), modelo.getIdVerMod(),
                            modelo.getNumAnoLis(), modelo.getImpPreLis(), modelo.getImpPreMin(), modelo.getImpPreFlo()});

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
        return "actualizar";
    }

    private void getDatosPrecioLista() {
        helper conex = null;
        ResultSet tabla = null;

        try {
            conex = new helper();
            indError = conex.getErrorSQL();

            if (!indError.equals("")) {
                errores.add(indError);
            } else {
                tabla = conex.executeDataSet("CALL usp_getDatosPrecioLista(?,?,?,?)",
                        new Object[]{modelo.getIdMar(), modelo.getIdModMar(), modelo.getIdVerMod(), modelo.getNumAnoLis()});

                indError = conex.getErrorSQL();

                if (!indError.equals("")) {
                    errores.add(indError);
                } else {
                    while (tabla.next()) {
                        modelo.setImpPreLis(tabla.getString("impPreLis"));
                        modelo.setImpPreMin(tabla.getString("impPreMin"));
                        modelo.setImpPreFlo(tabla.getString("impPreFlo"));
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

    /**
     * @return the modelo
     */
    public PrecioLista getModelo() {
        return modelo;
    }

    /**
     * @param modelo the modelo to set
     */
    public void setModelo(PrecioLista modelo) {
        this.modelo = modelo;
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
     * @return the listPrecioLista
     */
    public ArrayList<PrecioLista> getListPrecioLista() {
        return listPrecioLista;
    }

    /**
     * @param listPrecioLista the listPrecioLista to set
     */
    public void setListPrecioLista(ArrayList<PrecioLista> listPrecioLista) {
        this.listPrecioLista = listPrecioLista;
    }
}
