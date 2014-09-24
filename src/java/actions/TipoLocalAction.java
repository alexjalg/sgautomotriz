/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package actions;

import com.opensymphony.xwork2.ModelDriven;
import conexion.helper;
import entities.TipoLocal;
import java.sql.ResultSet;
import java.util.ArrayList;

public class TipoLocalAction  extends  MasterAction implements ModelDriven<TipoLocal>{
    
    private TipoLocal modelo = new TipoLocal();
    private ArrayList<TipoLocal> listTipLoc = new ArrayList<TipoLocal>();
    
    @Override
    public TipoLocal getModel() {
        tituloOpc = "Tipos de local";
        idClaseAccion = 23;
        return modelo;
    }

    public String vrfSeleccion() {
        idAccion = 1;
        verifAccionTipoUsuario();
        if (indErrAcc.equals("")) {
            if (modelo.getIdTipLoc()== 0) {
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
            urlPaginacion = "tipoLocal/TipoLocal";
            varReturn = varReturn.trim();
            varReturnProcess(0);
            if (!listVarReturn.isEmpty()) {
                curPagVis = Integer.parseInt(listVarReturn.get(0).toString().trim());
            }
            cantTipoLocalIndex();
            verifPag();
            listTipoLocalIndex();

        }
        return "success";
    }

    private void cantTipoLocalIndex() {
        helper conex = null;
        ResultSet tabla = null;
        try {
            conex = new helper();
            indError = conex.getErrorSQL().trim();

            if (!indError.trim().equals("")) {
                errores.add(indError);
            } else {
                tabla = conex.executeDataSet("CALL usp_cantTipoLocalIndex()", new Object[]{});
                indError = conex.getErrorSQL();

                if (!indError.equals("")) {
                    errores.add(indError);
                } else {
                    while (tabla.next()) {
                        cantReg = tabla.getInt(1);
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

    private void listTipoLocalIndex() {
        helper conex = null;
        ResultSet tabla = null;

        try {
            conex = new helper();
            indError = conex.getErrorSQL();

            if (!indError.equals("")) {
                errores.add(indError);
            } else {
                tabla = conex.executeDataSet("CALL usp_listTipoLocalIndex(?,?)",
                        new Object[]{(getCurPag()) * getRegPag(), getRegPag()});

                indError = conex.getErrorSQL();

                if (!indError.equals("")) {
                    errores.add(indError);
                } else {
                    TipoLocal obj;
                    while (tabla.next()) {
                        obj = new TipoLocal();
                        obj.setIdTipLoc(tabla.getInt("idTipLoc"));
                        obj.setDesTipLoc(tabla.getString("desTipLoc"));
                        listTipLoc.add(obj);
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

    private void getDatosTipoTrasmision() {
        helper conex = null;
        ResultSet tabla = null;

        try {
            conex = new helper();
            indError = conex.getErrorSQL();

            if (!indError.equals("")) {
                errores.add(indError);
            } else {
                tabla = conex.executeDataSet("CALL usp_getDatosTipoLocal(?)",
                        new Object[]{modelo.getIdTipLoc()});

                indError = conex.getErrorSQL();

                if (!indError.equals("")) {
                    errores.add(indError);
                } else {
                    while (tabla.next()) {
                        modelo.setDesTipLoc(tabla.getString("desTipLoc"));
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

    public String adicionar() {
        idAccion = 3;
        verifAccionTipoUsuario();
        if (indErrAcc.equals("")) {
            nivBandeja = 1;

            if (!opcion.trim().equals("A") && !opcion.trim().equals("M")) {
                indErrParm = "error";
            } else {
                varReturnProcess(1);

                if (opcion.equals("A")) {
                    formURL = baseURL + "tipoLocal/grabarTipoLocal";
                }
            }
        }
        return "adicionar";
    }

    public String modificar() {
        idAccion = 4;
        verifAccionTipoUsuario();
        if (indErrAcc.equals("")) {
            nivBandeja = 1;

            if (!opcion.trim().equals("A") && !opcion.trim().equals("M")) {
                indErrParm = "error";
            } else {
                varReturnProcess(1);

                if (opcion.equals("M")) {
                    if (modelo.getIdTipLoc()== 0) {
                        indErrParm = "error";
                    } else {
                        getDatosTipoTrasmision();
                        formURL = baseURL + "tipoLocal/actualizarTipoLocal";
                    }
                }
            }
        }

        return "adicionar";
    }

    public String grabar() {
        idAccion = 5;
        verifAccionTipoUsuario();
        if (indErrAcc.equals("")) {
            modelo.setDesTipLoc(modelo.getDesTipLoc().trim());

            if (modelo.getDesTipLoc().equals("")) {
                indError = "Debe ingresar el local";
                errores.add(indError);
            }

            if (indError.equals("")) {
                helper conex = null;

                try {
                    conex = new helper();
                    indError = conex.getErrorSQL();

                    if (!indError.equals("")) {
                        errores.add(indError);
                    } else {
                        indError = conex.executeNonQuery("CALL usp_insTipoLocal(?)",
                                new Object[]{modelo.getDesTipLoc()});

                        if (!indError.equals("")) {
                            errores.add(indError);
                        }
                    }
                } catch (Exception e) {
                    indError = "error";
                    errores.add(e.getMessage());
                } finally {
                    conex.returnConnect();
                }
            }
        }
        return "grabar";
    }

    public String actualizar() {
        idAccion = 6;

        verifAccionTipoUsuario();

        if (indErrAcc.equals("")) {
            modelo.setDesTipLoc(modelo.getDesTipLoc().trim());
            
            if (modelo.getDesTipLoc().equals("")) {
                indError = "Debe ingresar el Local";
                errores.add(indError);
            }

            if (indError.equals("")) {
                helper conex = null;

                try {
                    conex = new helper();
                    indError = conex.getErrorSQL();

                    if (!indError.equals("")) {
                        errores.add(indError);
                    } else {
                        indError = conex.executeNonQuery("CALL usp_updTipoLocal(?,?)",
                                new Object[]{modelo.getIdTipLoc(), modelo.getDesTipLoc()});

                        if (!indError.equals("")) {
                            errores.add(indError);
                        }
                    }
                } catch (Exception e) {
                    indError = "error";
                    errores.add(e.getMessage());
                } finally {
                    conex.returnConnect();
                }
            }
        }

        return "actualizar";
    }

    public String eliminar() {
        idAccion = 7;
        verifAccionTipoUsuario();
        if (indErrAcc.equals("")) {
            if (opcion.trim().equals("E")) {
                helper conex = new helper();

                indError = conex.getErrorSQL().trim();
                if (!indError.equals("")) {
                    errores.add(indError);
                } else {
                    ResultSet tabla = null;
                    try {
                        tabla = conex.executeDataSet("CALL usp_verifDependTipLoc(?)",
                                new Object[]{modelo.getIdTipLoc()});
                        indError = conex.getErrorSQL();

                        if (!indError.equals("")) {
                            errores.add(indError);
                        } else {
                            int cant = 0;
                            while (tabla.next()) {
                                cant = tabla.getInt(1);
                            }
                            /* Si no tiene dependencias */
                            if (cant == 0) {
                                indError = conex.executeNonQuery("CALL usp_dltTipoLocal(?)",
                                        new Object[]{modelo.getIdTipLoc()});

                                indError = indError.trim();
                                if (indError.trim().equals("")) {
                                    errores.add(indError);
                                }
                            } else /* si tiene dependencias */ {
                                indError = "error";
                                errores.add("Existen registros dependientes del tipo de local");
                            }
                        }
                    } catch (Exception e) {
                        indError = "error";
                        errores.add(e.getMessage());
                    } finally {
                        conex.returnConnect();
                    }
                }
            }
        }
        return "eliminar";
    }    

    /**
     * @return the modelo
     */
    public TipoLocal getModelo() {
        return modelo;
    }

    /**
     * @param modelo the modelo to set
     */
    public void setModelo(TipoLocal modelo) {
        this.modelo = modelo;
    }

    /**
     * @return the listTipLoc
     */
    public ArrayList<TipoLocal> getListTipLoc() {
        return listTipLoc;
    }

    /**
     * @param listTipLoc the listTipLoc to set
     */
    public void setListTipLoc(ArrayList<TipoLocal> listTipLoc) {
        this.listTipLoc = listTipLoc;
    }
    
    
    
    
}
