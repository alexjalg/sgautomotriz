package actions;

import com.opensymphony.xwork2.ModelDriven;
import conexion.helper;
import entities.TipoCarroceria;
import java.sql.ResultSet;
import java.util.ArrayList;

public class TipoCarroceriaAction extends MasterAction implements ModelDriven<TipoCarroceria> {

    private TipoCarroceria modelo = new TipoCarroceria();
    private ArrayList<TipoCarroceria> listTipoCarroceria = new ArrayList<TipoCarroceria>();

    @Override
    public TipoCarroceria getModel() {
        tituloOpc = "Tipo de carroceria";
        idClaseAccion = 27;
        return getModelo();
    }

    public String vrfSeleccion() {
        idAccion = 1;
        verifAccionTipoUsuario();
        if (indErrAcc.equals("")) {
            if (getModel().getIdTipCar() == 0) {
                indError = "error";
                errores.add("No ha seleccionado ningun registro");
            }
        }
        return "vrfSeleccion";
    }
    
    private void cantTipoCarroceriaIndex() {
        helper conex = null;
        ResultSet tabla = null;
        try {
            conex = new helper();
            indError += conex.getErrorSQL();

            if (!indError.equals("")) {
                errores.add(indError);
            } else {
                tabla = conex.executeDataSet("CALL usp_cantTipoCarroceriaIndex()", new Object[]{});
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

    private void listTipoCarroceriaIndex() {
        helper conex = null;
        ResultSet tabla = null;
        try {
            conex = new helper();
            indError += conex.getErrorSQL();
            if (!indError.equals("")) {
                errores.add(indError);
            } else {
                tabla = conex.executeDataSet("CALL usp_listTipoCarroceriaIndex(?,?)",
                        new Object[]{getCurPag() * regPag, regPag});
                indError += conex.getErrorSQL();
                if (!indError.equals("")) {
                    errores.add(indError);
                } else {
                    TipoCarroceria obj;
                    while (tabla.next()) {
                        obj = new TipoCarroceria();
                        obj.setIdTipCar(tabla.getInt("idTipCar"));
                        obj.setDesTipCar(tabla.getString("desTipCar"));
                        obj.setCodAbrTip(tabla.getString("codAbrTip"));
                        listTipoCarroceria.add(obj);
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
            urlPaginacion = "tipoCarroceria/TipoCarroceria";
            varReturnProcess(0);
            if (!listVarReturn.isEmpty()) {
                curPagVis = Integer.parseInt(listVarReturn.get(0).toString().trim());
            }
            cantTipoCarroceriaIndex();
            verifPag();
            listTipoCarroceriaIndex();
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
                    formURL = baseURL + "tipoCarroceria/grabarTipoCarroceria";
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
                    if (modelo.getIdTipCar() == 0) {
                        indErrParm = "error";
                    } else {
                        getDatosTipoCarroceria();
                        formURL = baseURL + "tipoCarroceria/actualizarTipoCarroceria";
                    }
                }
            }
        }

        return "modificar";
    }
    
    private void getDatosTipoCarroceria() {
        helper conex = null;
        ResultSet tabla = null;

        try {
            conex = new helper();
            indError = conex.getErrorSQL();

            if (!indError.equals("")) {
                errores.add(indError);
            } else {
                tabla = conex.executeDataSet("CALL usp_getDatosTipoCarroceria(?)",
                        new Object[]{modelo.getIdTipCar()});

                indError = conex.getErrorSQL();

                if (!indError.equals("")) {
                    errores.add(indError);
                } else {
                    while (tabla.next()) {
                        modelo.setDesTipCar(tabla.getString("desTipCar"));
                        modelo.setCodAbrTip(tabla.getString("codAbrTip"));
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
            modelo.setDesTipCar(modelo.getDesTipCar().trim());
            modelo.setCodAbrTip(modelo.getCodAbrTip().trim());
            
            if(modelo.getDesTipCar().equals("")) {
                indError += "error";
                errores.add("Debe ingresar la descripción del tipo de carrocería");
            }
            
            if(modelo.getCodAbrTip().equals("")) {
                indError += "error";
                errores.add("Debe ingresar el código abreviado");
            }

            if (indError.equals("")) {
                helper conex = null;
                try {
                    conex = new helper();
                    indError = conex.getErrorSQL();
                    if (!indError.equals("")) {
                        errores.add(indError);
                    } else {
                        indError = conex.executeNonQuery("CALL usp_insTipoCarroceria(?,?)",
                                new Object[]{modelo.getDesTipCar(), modelo.getCodAbrTip()});
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
            modelo.setDesTipCar(modelo.getDesTipCar().trim());
            modelo.setCodAbrTip(modelo.getCodAbrTip().trim());
            
            if(modelo.getDesTipCar().equals("")) {
                indError += "error";
                errores.add("Debe ingresar la descripción del tipo de carrocería");
            }
            
            if(modelo.getCodAbrTip().equals("")) {
                indError += "error";
                errores.add("Debe ingresar el código abreviado");
            }

            if (indError.equals("")) {
                helper conex = null;
                try {
                    conex = new helper();
                    indError = conex.getErrorSQL();
                    if (!indError.equals("")) {
                        errores.add(indError);
                    } else {
                        indError = conex.executeNonQuery("CALL usp_updTipoCarroceria(?,?,?)",
                                new Object[]{modelo.getIdTipCar(), modelo.getDesTipCar(), modelo.getCodAbrTip()});
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
                        tabla = conex.executeDataSet("CALL usp_verifDependTipoCarroceria(?)", 
                                    new Object[]{ modelo.getIdTipCar() });
                        indError = conex.getErrorSQL();
                        
                        if(!indError.equals("")) {
                            errores.add(indError);
                        } else {
                            int cant = 0;
                            while(tabla.next()) {
                                cant = tabla.getInt(1);
                            }
                            
                            if (cant == 0) {
                                indError = conex.executeNonQuery("CALL usp_dltTipoCarroceria(?)",
                                        new Object[]{modelo.getIdTipCar()});
                                indError = indError.trim();
                                if (indError.trim().equals("")) {
                                    errores.add(indError);
                                }
                            } else /* si tiene dependencias */ {
                                indError = "error";
                                errores.add("Existen registros dependientes del tipo de carrocería");
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

    public ArrayList<TipoCarroceria> getListTipoCarroceria() {
        return listTipoCarroceria;
    }

    /**
     * @return the modelo
     */
    public TipoCarroceria getModelo() {
        return modelo;
    }

    /**
     * @param modelo the modelo to set
     */
    public void setModelo(TipoCarroceria modelo) {
        this.modelo = modelo;
    }

}
