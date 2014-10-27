/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package actions;

import com.opensymphony.xwork2.ModelDriven;
import conexion.helper;
import entities.FormaPago;
import java.sql.ResultSet;
import java.util.ArrayList;

public class FormaPagoAction extends MasterAction implements ModelDriven<FormaPago> {

    private FormaPago modelo = new FormaPago();
    private ArrayList<FormaPago> listForPag = new ArrayList<FormaPago>();

    @Override
    public FormaPago getModel() {
        tituloOpc = "Formas de pago";
        idClaseAccion = 19;
        
        return modelo;
    }

    public String vrfSeleccion() {
        idAccion = 1;
        verifAccionTipoUsuario();
        if (indErrAcc.equals("")) {
            if (modelo.getIdForPag() == 0) {
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
            urlPaginacion = "formaPago/FormaPago";
            varReturn = varReturn.trim();
            varReturnProcess(0);
            if (!listVarReturn.isEmpty()) {
                curPagVis = Integer.parseInt(listVarReturn.get(0).toString().trim());
            }
            cantFormaPagoIndex();
            verifPag();
            listFormaPagoIndex();

        }
        
        return SUCCESS;
    }

    private void cantFormaPagoIndex() {
        helper conex = null;
        ResultSet tabla = null;
        try {
            conex = new helper();
            indError = conex.getErrorSQL().trim();

            if (!indError.trim().equals("")) {
                errores.add(indError);
            } else {
                tabla = conex.executeDataSet("CALL usp_cantFormaPagoIndex()", new Object[]{});
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

    private void listFormaPagoIndex() {
        helper conex = null;
        ResultSet tabla = null;

        try {
            conex = new helper();
            indError = conex.getErrorSQL();

            if (!indError.equals("")) {
                errores.add(indError);
            } else {
                tabla = conex.executeDataSet("CALL usp_listFormaPagoIndex(?,?)",
                        new Object[]{(getCurPag()) * getRegPag(), getRegPag()});

                indError = conex.getErrorSQL();

                if (!indError.equals("")) {
                    errores.add(indError);
                } else {
                    FormaPago obj;
                    while (tabla.next()) {
                        obj = new FormaPago();
                        obj.setIdForPag(tabla.getInt("idForPag"));
                        obj.setDesForPag(tabla.getString("desForPag"));
                        obj.setNumDiaPag(tabla.getInt("numDiaPag"));
                        listForPag.add(obj);
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

    private void getDatosFormaPago() {
        helper conex = null;
        ResultSet tabla = null;

        try {
            conex = new helper();
            indError = conex.getErrorSQL();

            if (!indError.equals("")) {
                errores.add(indError);
            } else {
                tabla = conex.executeDataSet("CALL usp_getDatosFormaPago(?)",
                        new Object[]{modelo.getIdForPag()});

                indError = conex.getErrorSQL();

                if (!indError.equals("")) {
                    errores.add(indError);
                } else {
                    while (tabla.next()) {
                        modelo.setDesForPag(tabla.getString("desForPag"));
                        modelo.setNumDiaPag(tabla.getInt("numDiaPag"));
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

            if (!opcion.trim().equals("A")) {
                indErrParm = "error";
            } else {
                varReturnProcess(1);
                
                accion = "Adicionar";

                formURL = baseURL + "formaPago/grabarFormaPago";
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
                
                if (modelo.getIdForPag() == 0) {
                    indErrParm = "error";
                } else {
                    getDatosFormaPago();
                    formURL = baseURL + "formaPago/actualizarFormaPago";
                }
            }
        }

        return "adicionar";
    }

    public String grabar() {
        idAccion = 5;
        verifAccionTipoUsuario();
        if (indErrAcc.equals("")) {
            modelo.setDesForPag(modelo.getDesForPag().trim());
            modelo.setNumDiaPag(modelo.getNumDiaPag());

            if (modelo.getDesForPag().equals("")) {
                indError = "Debe ingresar la forma de pago";
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
                        indError = conex.executeNonQuery("CALL usp_insFormaPago(?,?)",
                                new Object[]{modelo.getDesForPag(), modelo.getNumDiaPag()});

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
            modelo.setDesForPag(modelo.getDesForPag().trim());
            modelo.setNumDiaPag(modelo.getNumDiaPag());

            if (modelo.getDesForPag().equals("")) {
                indError = "Debe ingresar la forma de pago";
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
                        indError = conex.executeNonQuery("CALL usp_updFormaPago(?,?,?)",
                                new Object[]{modelo.getIdForPag(), modelo.getDesForPag(), modelo.getNumDiaPag()});

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
                        tabla = conex.executeDataSet("CALL usp_verifDependForPag(?)",
                                new Object[]{modelo.getIdForPag()});
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
                                indError = conex.executeNonQuery("CALL usp_dltFormaPago(?)",
                                        new Object[]{modelo.getIdForPag()});

                                indError = indError.trim();
                                if (indError.trim().equals("")) {
                                    errores.add(indError);
                                }
                            } else /* si tiene dependencias */ {
                                indError = "error";
                                errores.add("Existen registros dependientes de la forma de pago");
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
    public FormaPago getModelo() {
        return modelo;
    }

    /**
     * @param modelo the modelo to set
     */
    public void setModelo(FormaPago modelo) {
        this.modelo = modelo;
    }

    /**
     * @return the listForPag
     */
    public ArrayList<FormaPago> getListForPag() {
        return listForPag;
    }

    /**
     * @param listForPag the listForPag to set
     */
    public void setListForPag(ArrayList<FormaPago> listForPag) {
        this.listForPag = listForPag;
    }
}
