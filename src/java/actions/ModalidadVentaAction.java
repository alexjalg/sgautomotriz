/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package actions;

import com.opensymphony.xwork2.ModelDriven;
import conexion.helper;
import entities.ModalidadVenta;
import java.sql.ResultSet;
import java.util.ArrayList;

public class ModalidadVentaAction extends MasterAction implements ModelDriven<ModalidadVenta> {

    private ModalidadVenta modelo = new ModalidadVenta();
    private ArrayList<ModalidadVenta> listModVen = new ArrayList<ModalidadVenta>();

    @Override
    public ModalidadVenta getModel() {
        tituloOpc = "Modalidad de Venta";
        idClaseAccion = 38;
        
        return modelo;
    }

    public String vrfSeleccion() {
        idAccion = 1;
        verifAccionTipoUsuario();

        if (indErrAcc.equals("")) {
            if (modelo.getIdModVen() == 0) {
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

            urlPaginacion = "modalidadVenta/ModalidadVenta";
            varReturn = varReturn.trim();
            varReturnProcess(0);
            if (!listVarReturn.isEmpty()) {
                curPagVis = Integer.parseInt(listVarReturn.get(0).toString().trim());
            }
            cantModalidadVentaIndex();
            verifPag();
            listModalidadVentaIndex();
        }
        
        return SUCCESS;
    }

    private void cantModalidadVentaIndex() {
        helper conex = null;
        ResultSet tabla = null;

        try {
            conex = new helper();
            indError += conex.getErrorSQL();

            if (!indError.equals("")) {
                errores.add(indError);
            } else {
                tabla = conex.executeDataSet("CALL usp_cantModalidadVentaIndex()", new Object[]{});

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

    private void listModalidadVentaIndex() {
        helper conex = null;
        ResultSet tabla = null;

        try {
            conex = new helper();
            indError += conex.getErrorSQL();

            if (!indError.equals("")) {
                errores.add(indError);
            } else {
                tabla = conex.executeDataSet("CALL usp_listModalidadVentaIndex(?,?)",
                        new Object[]{getCurPag() * regPag, regPag});

                indError += conex.getErrorSQL();

                if (!indError.equals("")) {
                    errores.add(indError);
                } else {
                    ModalidadVenta obj;
                    while (tabla.next()) {
                        obj = new ModalidadVenta();
                        obj.setIdModVen(tabla.getInt("idModVen"));
                        obj.setDesModVen(tabla.getString("desModVen"));
                        listModVen.add(obj);
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

    public String adicionar() {
        idAccion = 3;
        verifAccionTipoUsuario();
        
        if (indErrAcc.equals("")) {
            nivBandeja = 1;

            if (!opcion.trim().equals("A")) {
                indErrParm = "error";
            } else {
                varReturnProcess(1);
                
                accion = "Adicionar";
                
                formURL = baseURL + "modalidadVenta/grabarModalidadVenta";
            }
        }
        return "adicionar";
    }

    public String modificar() {
        idAccion = 4;
        verifAccionTipoUsuario();
        
        if (indErrAcc.equals("")) {
            nivBandeja = 1;

            if (!opcion.trim().equals("M")) {
                indErrParm = "error";
            } else {
                varReturnProcess(1);

                accion = "Modificar";
                
                if (modelo.getIdModVen() == 0) {
                    indErrParm = "error";
                } else {
                    getDatosModalidadVenta();
                    formURL = baseURL + "modalidadVenta/actualizarModalidadVenta";
                }
            }
        }

        return "adicionar";
    }

    public String grabar() {
        idAccion = 5;
        verifAccionTipoUsuario();
        if (indErrAcc.equals("")) {
            modelo.setDesModVen(modelo.getDesModVen().trim());

            if (modelo.getDesModVen().equals("")) {
                indError = "Debe ingresar la descripción";
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
                        indError = conex.executeNonQuery("CALL usp_insModalidadVenta(?)",
                                new Object[]{modelo.getDesModVen()});

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
            modelo.setDesModVen(modelo.getDesModVen().trim());

            if (modelo.getDesModVen().equals("")) {
                indError = "Debe ingresar la descripción";
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
                        indError = conex.executeNonQuery("CALL usp_updModalidadVenta(?,?)",
                                new Object[]{modelo.getIdModVen(), modelo.getDesModVen()});

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
                        tabla = conex.executeDataSet("CALL usp_verifDependModVen(?)",
                                new Object[]{modelo.getIdModVen()});
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
                                indError = conex.executeNonQuery("CALL usp_dltModalidadVenta(?)",
                                        new Object[]{modelo.getIdModVen()});

                                indError = indError.trim();
                                if (indError.trim().equals("")) {
                                    errores.add(indError);
                                }
                            } else /* si tiene dependencias */ {
                                indError = "error";
                                errores.add("Existen registros dependientes de la modalidad de venta");
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

    private void getDatosModalidadVenta() {
        helper conex = null;
        ResultSet tabla = null;

        try {
            conex = new helper();
            indError = conex.getErrorSQL();

            if (!indError.equals("")) {
                errores.add(indError);
            } else {
                tabla = conex.executeDataSet("CALL usp_getDatosModalidadVenta(?)",
                        new Object[]{modelo.getIdModVen()});

                indError = conex.getErrorSQL();

                if (!indError.equals("")) {
                    errores.add(indError);
                } else {
                    while (tabla.next()) {
                        modelo.setDesModVen(tabla.getString("desModVen"));
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

    /**
     * @return the modelo
     */
    public ModalidadVenta getModelo() {
        return modelo;
    }

    /**
     * @param modelo the modelo to set
     */
    public void setModelo(ModalidadVenta modelo) {
        this.modelo = modelo;
    }

    /**
     * @return the listModVen
     */
    public ArrayList<ModalidadVenta> getListModVen() {
        return listModVen;
    }

    /**
     * @param listModVen the listModVen to set
     */
    public void setListModVen(ArrayList<ModalidadVenta> listModVen) {
        this.listModVen = listModVen;
    }
}
