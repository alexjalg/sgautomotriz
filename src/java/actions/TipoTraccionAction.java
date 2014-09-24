package actions;

import static com.opensymphony.xwork2.Action.SUCCESS;
import com.opensymphony.xwork2.ModelDriven;
import conexion.helper;
import entities.TipoTraccion;
import java.sql.ResultSet;
import java.util.ArrayList;

public class TipoTraccionAction extends MasterAction implements ModelDriven<TipoTraccion> {

    private TipoTraccion modelo = new TipoTraccion();
    private ArrayList<TipoTraccion> listTipoTraccion = new ArrayList<TipoTraccion>();

    @Override
    public TipoTraccion getModel() {
        tituloOpc = "Tipo de tracción";
        idClaseAccion = 28;
        return modelo;
    }

    public String vrfSeleccion() {
        idAccion = 1;
        verifAccionTipoUsuario();
        if (indErrAcc.equals("")) {
            if (modelo.getIdTipTrac() == 0) {
                indError = "error";
                errores.add("No ha seleccionado ningun registro");
            }
        }
        return "vrfSeleccion";
    }
    
    private void cantTipoTraccionIndex() {
        helper conex = null;
        ResultSet tabla = null;
        try {
            conex = new helper();
            indError += conex.getErrorSQL();

            if (!indError.equals("")) {
                errores.add(indError);
            } else {
                tabla = conex.executeDataSet("CALL usp_cantTipoTraccionIndex()", new Object[]{});
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

    private void listTipoTraccionIndex() {
        helper conex = null;
        ResultSet tabla = null;
        try {
            conex = new helper();
            indError += conex.getErrorSQL();
            if (!indError.equals("")) {
                errores.add(indError);
            } else {
                tabla = conex.executeDataSet("CALL usp_listTipoTraccionIndex(?,?)",
                        new Object[]{getCurPag() * regPag, regPag});
                indError += conex.getErrorSQL();
                if (!indError.equals("")) {
                    errores.add(indError);
                } else {
                    TipoTraccion obj;
                    while (tabla.next()) {
                        obj = new TipoTraccion();
                        obj.setIdTipTrac(tabla.getInt("idTipTrac"));
                        obj.setDesTipTrac(tabla.getString("desTipTrac"));
                        listTipoTraccion.add(obj);
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

    @Override
    public String execute() {
        idAccion = 2;
        verifAccionTipoUsuario();
        if (indErrAcc.equals("")) {
            nivBandeja = 1;
            urlPaginacion = "tipoTraccion/TipoTraccion";
            varReturnProcess(0);
            if (!listVarReturn.isEmpty()) {
                curPagVis = Integer.parseInt(listVarReturn.get(0).toString().trim());
            }
            cantTipoTraccionIndex();
            verifPag();
            listTipoTraccionIndex();
        }
        return SUCCESS;
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
                    formURL = baseURL + "tipoTraccion/grabarTipoTraccion";
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
                    if (modelo.getIdTipTrac() == 0) {
                        indErrParm = "error";
                    } else {
                        getDatosTipoTraccion();
                        formURL = baseURL + "tipoTraccion/actualizarTipoTraccion";
                    }
                }
            }
        }
        return "modificar";
    }
    
    private void getDatosTipoTraccion() {
        helper conex = null;
        ResultSet tabla = null;

        try {
            conex = new helper();
            indError = conex.getErrorSQL();

            if (!indError.equals("")) {
                errores.add(indError);
            } else {
                tabla = conex.executeDataSet("CALL usp_getDatosTipoTraccion(?)",
                        new Object[]{ modelo.getIdTipTrac() });

                indError = conex.getErrorSQL();

                if (!indError.equals("")) {
                    errores.add(indError);
                } else {
                    while (tabla.next()) {
                        modelo.setDesTipTrac(tabla.getString("desTipTrac"));                        
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

    public String grabar() {
        idAccion = 5;
        verifAccionTipoUsuario();
        if (indErrAcc.equals("")) {
            modelo.setDesTipTrac(modelo.getDesTipTrac().trim());
            
            if(modelo.getDesTipTrac().equals("")) {
                indError += "error";
                errores.add("Debe ingresar el tipo de carrocería");
            }

            if (indError.equals("")) {
                helper conex = null;
                try {
                    conex = new helper();
                    indError = conex.getErrorSQL();
                    if (!indError.equals("")) {
                        errores.add(indError);
                    } else {
                        indError = conex.executeNonQuery("CALL usp_insTipoTraccion(?)",
                                new Object[]{ modelo.getDesTipTrac() });
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
            modelo.setDesTipTrac(modelo.getDesTipTrac().trim());
            
            if(modelo.getDesTipTrac().equals("")) {
                indError += "error";
                errores.add("Debe ingresar el tipo de carrocería");
            }

            if (indError.equals("")) {
                helper conex = null;
                try {
                    conex = new helper();
                    indError = conex.getErrorSQL();
                    if (!indError.equals("")) {
                        errores.add(indError);
                    } else {
                        indError = conex.executeNonQuery("CALL usp_updTipoTraccion(?,?)",
                                new Object[]{ modelo.getIdTipTrac(), modelo.getDesTipTrac() });
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
                        tabla = conex.executeDataSet("CALL usp_verifDependTipoTraccion(?)", 
                                    new Object[]{ modelo.getIdTipTrac() });
                        indError = conex.getErrorSQL();
                        
                        if(!indError.equals("")) {
                            errores.add(indError);
                        } else {
                            int cant = 0;
                            while(tabla.next()) {
                                cant = tabla.getInt(1);
                            }
                            
                            if (cant == 0) {
                                indError = conex.executeNonQuery("CALL usp_dltTipoTraccion(?)",
                                        new Object[]{ modelo.getIdTipTrac() });
                                indError = indError.trim();
                                if (indError.trim().equals("")) {
                                    errores.add(indError);
                                }
                            } else /* si tiene dependencias */ {
                                indError = "error";
                                errores.add("Existen registros dependientes del tipo de tracción");
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

    public ArrayList<TipoTraccion> getListTipoTraccion() {
        return listTipoTraccion;
    }

    public void setModelo(TipoTraccion model) {
        this.modelo = getModelo();
    }

    /**
     * @return the modelo
     */
    public TipoTraccion getModelo() {
        return modelo;
    }

}
