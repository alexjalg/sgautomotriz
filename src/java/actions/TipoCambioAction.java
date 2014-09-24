/*
 * Action: Tipo de Cambio
 * Creado por: Angelo Ccoicca
 * Fecha de creación: 22-05-2014
 * Modificado por                   Fecha de Modificación
 * - 
 * -
 */
package actions;

import com.opensymphony.xwork2.ModelDriven;
import conexion.helper;
import entities.TipoCambio;
import java.sql.ResultSet;
import java.util.ArrayList;

public class TipoCambioAction extends MasterAction implements ModelDriven<TipoCambio> {

    private TipoCambio modelo = new TipoCambio();
    private ArrayList<TipoCambio> listTipoCambio = new ArrayList<TipoCambio>();

    @Override
    public TipoCambio getModel() {
        tituloOpc = "Tipo de cambio";
        idClaseAccion = 13;

        return modelo;
    }
    
    public String vrfSeleccion() {
        idAccion = 1;
        verifAccionTipoUsuario();

        if (indErrAcc.equals("")) {
            if (modelo.getFecTipCam().trim().equals("")) {
                indError = "error";
                errores.add("No ha seleccionado ningun registro");
            }
        }

        return "vrfSeleccion";
    }

    private void cantTipoCambioIndex() {
        helper conex = null;
        ResultSet tabla = null;

        try {
            conex = new helper();
            indError += conex.getErrorSQL();

            if (!indError.equals("")) {
                errores.add(indError);
            } else {
                tabla = conex.executeDataSet("CALL usp_cantTipoCambioIndex()", new Object[]{});

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

    private void listTipoCambioIndex() {
        helper conex = null;
        ResultSet tabla = null;

        try {
            conex = new helper();
            indError += conex.getErrorSQL();

            if (!indError.equals("")) {
                errores.add(indError);
            } else {
                tabla = conex.executeDataSet("CALL usp_listTipoCambioIndex(?,?)",
                        new Object[]{getCurPag() * regPag, regPag});

                indError += conex.getErrorSQL();

                if (!indError.equals("")) {
                    errores.add(indError);
                } else {
                    TipoCambio obj;
                    while (tabla.next()) {
                        obj = new TipoCambio();
                        obj.setFecTipCam(tabla.getString("fecTipCam").trim());
                        int indComp = compareDate(obj.getFecTipCam(), getCurDate());
                        if (indComp >= 0) {
                            obj.setIndMod("M");
                        }
                        obj.setFecTipCam(getConvertFecha(obj.getFecTipCam().trim(), 1));
                        obj.setImpTCIntCom(tabla.getString("impTCIntCom"));
                        obj.setImpTCIntVen(tabla.getString("impTCIntVen"));
                        obj.setImpTCLegCom(tabla.getString("impTCLegCom"));
                        obj.setImpTCLegVen(tabla.getString("impTCLegVen"));
                        listTipoCambio.add(obj);
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
            urlPaginacion = "tipoCambio/TipoCambio";

            varReturnProcess(0);
            if (!listVarReturn.isEmpty()) {
                curPagVis = Integer.parseInt(listVarReturn.get(0).toString().trim());
            }

            cantTipoCambioIndex();
            verifPag();
            listTipoCambioIndex();
        }

        return SUCCESS;
    }

    public String adicionar() {
        idAccion = 3;
        verifAccionTipoUsuario();

        if (indErrAcc.equals("")) {
            nivBandeja = 1;

            if ((!opcion.trim().equals("A") && !opcion.trim().equals("M"))) {
                indErrParm = "error";
            } else {
                varReturnProcess(1);

                if (opcion.equals("A")) {
                    modelo.setFecTipCam(getConvertFecha(getCurDate(), 1));
                    formURL = baseURL + "tipoCambio/grabarTipoCambio";
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

            if ((!opcion.trim().equals("A") && !opcion.trim().equals("M"))) {
                indErrParm = "error";
            } else {
                varReturnProcess(1);
                
                if (opcion.equals("M")) {
                    getDatosTipoCambio();
                    formURL = baseURL + "tipoCambio/actualizarTipoCambio";
                }
            }
        }

        return "adicionar";
    }

    public void getDatosTipoCambio() {
        helper conex = null;
        ResultSet tabla = null;

        try {
            conex = new helper();
            indError += conex.getErrorSQL();

            if (!indError.equals("")) {
                errores.add(indError);
            } else {
                modelo.setFecTipCam(getConvertFecha(modelo.getFecTipCam().trim(), 2));
                tabla = conex.executeDataSet("CALL usp_getDatosTipoCambio(?)",
                        new Object[]{modelo.getFecTipCam()});
                indError += conex.getErrorSQL();

                if (!indError.equals("")) {
                    errores.add(indError);
                } else {
                    while (tabla.next()) {
                        modelo.setFecTipCam(getConvertFecha(modelo.getFecTipCam(), 1));
                        modelo.setImpTCIntCom(tabla.getString("impTCIntCom"));
                        modelo.setImpTCIntVen(tabla.getString("impTCIntVen"));
                        modelo.setImpTCLegCom(tabla.getString("impTCLegCom"));
                        modelo.setImpTCLegVen(tabla.getString("impTCLegVen"));
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
        idAccion = 5;
        verifAccionTipoUsuario();

        if (indErrAcc.equals("")) {
            modelo.setFecTipCam(modelo.getFecTipCam().trim());
            modelo.setFecTipCam(getConvertFecha(modelo.getFecTipCam(), 2));
            modelo.setImpTCIntCom(modelo.getImpTCIntCom().trim());
            modelo.setImpTCIntVen(modelo.getImpTCIntVen().trim());
            modelo.setImpTCLegCom(modelo.getImpTCLegCom().trim());
            modelo.setImpTCLegVen(modelo.getImpTCLegVen().trim());

            if (modelo.getImpTCIntCom().equals("")) {
                indError += "error";
                errores.add("Ingrese el importe de tipo de cambio interno compra");
            } else {
                if (modelo.getImpTCIntCom().length() > 7) {
                    indError += "error";
                    errores.add("Importe de tipo de cambio interno compra no válido");
                } else {
                    if (!isDouble(modelo.getImpTCIntCom())) {
                        indError += "error";
                        errores.add("Importe de tipo de cambio interno compra no válido");
                    } else {
                        if (Double.parseDouble(modelo.getImpTCIntCom()) <= 0) {
                            indError += "error";
                            errores.add("Importe de tipo de cambio interno compra no válido");
                        }
                    }
                }
            }
            
            if (modelo.getImpTCIntVen().equals("")) {
                indError += "error";
                errores.add("Ingrese el importe de tipo de cambio interno venta");
            } else {
                if (modelo.getImpTCIntVen().length() > 7) {
                    indError += "error";
                    errores.add("Importe de tipo de cambio interno venta no válido");
                } else {
                    if (!isDouble(modelo.getImpTCIntVen())) {
                        indError += "error";
                        errores.add("Importe de tipo de cambio interno venta no válido");
                    } else {
                        if (Double.parseDouble(modelo.getImpTCIntVen()) <= 0) {
                            indError += "error";
                            errores.add("Importe de tipo de cambio interno venta no válido");
                        }
                    }
                }
            }

            if (modelo.getImpTCLegCom().equals("")) {
                indError += "error";
                errores.add("Ingrese el importe de tipo de cambio legal compra");
            } else {
                if (modelo.getImpTCLegCom().length() > 7) {
                    indError += "error";
                    errores.add("Importe de tipo de cambio legal no válido");
                } else {
                    if (!isDouble(modelo.getImpTCLegCom())) {
                        indError += "error";
                        errores.add("Importe de tipo de cambio legal no válido");
                    } else {
                        if (Double.parseDouble(modelo.getImpTCLegCom()) <= 0) {
                            indError += "error";
                            errores.add("Importe de tipo de cambio legal no válido");
                        }
                    }
                }
            }
            
            if (modelo.getImpTCLegVen().equals("")) {
                indError += "error";
                errores.add("Ingrese el importe de tipo de cambio legal compra");
            } else {
                if (modelo.getImpTCLegVen().length() > 7) {
                    indError += "error";
                    errores.add("Importe de tipo de cambio legal no válido");
                } else {
                    if (!isDouble(modelo.getImpTCLegVen())) {
                        indError += "error";
                        errores.add("Importe de tipo de cambio legal no válido");
                    } else {
                        if (Double.parseDouble(modelo.getImpTCLegVen()) <= 0) {
                            indError += "error";
                            errores.add("Importe de tipo de cambio legal no válido");
                        }
                    }
                }
            }

            if (indError.equals("")) {
                helper conex = null;
                ResultSet tabla = null;

                try {
                    conex = new helper();
                    indError = conex.getErrorSQL();

                    if (!indError.equals("")) {
                        errores.add(indError);
                    } else {
                        tabla = conex.executeDataSet("CALL usp_verifExistTipoCambio(?)",
                                new Object[]{modelo.getFecTipCam()});

                        indError += conex.getErrorSQL();

                        if (!indError.equals("")) {
                            errores.add(indError);
                        } else {
                            int cont = 0;
                            while (tabla.next()) {
                                cont = tabla.getInt(1);
                            }

                            if (cont > 0) {
                                indError += "error";
                                errores.add("Ya existe un tipo de cambio registrado para la fecha");
                            } else {
                                indError = conex.executeNonQuery("CALL usp_insTipoCambio(?,?,?,?,?)",
                                        new Object[]{ modelo.getFecTipCam(),modelo.getImpTCIntCom(),modelo.getImpTCIntVen(),
                                            modelo.getImpTCLegCom(),modelo.getImpTCLegVen() });

                                if (!indError.equals("")) {
                                    errores.add(indError);
                                }
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

    public String actualizar() {
        idAccion = 6;
        verifAccionTipoUsuario();

        if (indErrAcc.equals("")) {
            modelo.setFecTipCam(modelo.getFecTipCam().trim());
            modelo.setFecTipCam(getConvertFecha(modelo.getFecTipCam(), 2));
            modelo.setImpTCIntCom(modelo.getImpTCIntCom().trim());
            modelo.setImpTCIntVen(modelo.getImpTCIntVen().trim());
            modelo.setImpTCLegCom(modelo.getImpTCLegCom().trim());
            modelo.setImpTCLegVen(modelo.getImpTCLegVen().trim());

            if (modelo.getImpTCIntCom().equals("")) {
                indError += "error";
                errores.add("Ingrese el importe de tipo de cambio interno compra");
            } else {
                if (modelo.getImpTCIntCom().length() > 7) {
                    indError += "error";
                    errores.add("Importe de tipo de cambio interno compra no válido");
                } else {
                    if (!isDouble(modelo.getImpTCIntCom())) {
                        indError += "error";
                        errores.add("Importe de tipo de cambio interno compra no válido");
                    } else {
                        if (Double.parseDouble(modelo.getImpTCIntCom()) <= 0) {
                            indError += "error";
                            errores.add("Importe de tipo de cambio interno compra no válido");
                        }
                    }
                }
            }
            
            if (modelo.getImpTCIntVen().equals("")) {
                indError += "error";
                errores.add("Ingrese el importe de tipo de cambio interno venta");
            } else {
                if (modelo.getImpTCIntVen().length() > 7) {
                    indError += "error";
                    errores.add("Importe de tipo de cambio interno venta no válido");
                } else {
                    if (!isDouble(modelo.getImpTCIntVen())) {
                        indError += "error";
                        errores.add("Importe de tipo de cambio interno venta no válido");
                    } else {
                        if (Double.parseDouble(modelo.getImpTCIntVen()) <= 0) {
                            indError += "error";
                            errores.add("Importe de tipo de cambio interno venta no válido");
                        }
                    }
                }
            }

            if (modelo.getImpTCLegCom().equals("")) {
                indError += "error";
                errores.add("Ingrese el importe de tipo de cambio legal compra");
            } else {
                if (modelo.getImpTCLegCom().length() > 7) {
                    indError += "error";
                    errores.add("Importe de tipo de cambio legal no válido");
                } else {
                    if (!isDouble(modelo.getImpTCLegCom())) {
                        indError += "error";
                        errores.add("Importe de tipo de cambio legal no válido");
                    } else {
                        if (Double.parseDouble(modelo.getImpTCLegCom()) <= 0) {
                            indError += "error";
                            errores.add("Importe de tipo de cambio legal no válido");
                        }
                    }
                }
            }
            
            if (modelo.getImpTCLegVen().equals("")) {
                indError += "error";
                errores.add("Ingrese el importe de tipo de cambio legal compra");
            } else {
                if (modelo.getImpTCLegVen().length() > 7) {
                    indError += "error";
                    errores.add("Importe de tipo de cambio legal no válido");
                } else {
                    if (!isDouble(modelo.getImpTCLegVen())) {
                        indError += "error";
                        errores.add("Importe de tipo de cambio legal no válido");
                    } else {
                        if (Double.parseDouble(modelo.getImpTCLegVen()) <= 0) {
                            indError += "error";
                            errores.add("Importe de tipo de cambio legal no válido");
                        }
                    }
                }
            }

            if (indError.equals("")) {
                helper conex = null;

                try {
                    conex = new helper();
                    indError += conex.getErrorSQL();

                    if (!indError.equals("")) {
                        errores.add(indError);
                    } else {
                        indError += conex.executeNonQuery("CALL usp_updTipoCambio(?,?,?,?,?)",
                                new Object[]{ modelo.getFecTipCam(),modelo.getImpTCIntCom(),modelo.getImpTCIntVen(),
                                    modelo.getImpTCLegCom(),modelo.getImpTCLegVen() });

                        if (!indError.equals("")) {
                            errores.add(indError);
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

        return "actualizar";
    }

    public String eliminar() {
        idAccion = 7;
        verifAccionTipoUsuario();

        if (indErrAcc.equals("")) {
            if (opcion.trim().equals("E")) {
                helper conex = null;
                ResultSet tabla = null;

                try {
                    conex = new helper();
                    indError += conex.getErrorSQL().trim();

                    if (!indError.equals("")) {
                        errores.add(indError);
                    } else {
                        modelo.setFecTipCam(getConvertFecha(modelo.getFecTipCam().trim(), 2));
                        indError += conex.executeNonQuery("CALL usp_dltTipoCambio(?)",
                                new Object[]{ modelo.getFecTipCam() });

                        if (indError.trim().equals("")) {
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

        return "eliminar";
    }

    /**
     * @return the modelo
     */
    public TipoCambio getModelo() {
        return modelo;
    }

    /**
     * @param modelo the modelo to set
     */
    public void setModelo(TipoCambio modelo) {
        this.modelo = modelo;
    }

    /**
     * @return the listTipoCambio
     */
    public ArrayList<TipoCambio> getListTipoCambio() {
        return listTipoCambio;
    }
}
