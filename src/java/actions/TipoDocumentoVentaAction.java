package actions;

import static com.opensymphony.xwork2.Action.SUCCESS;
import com.opensymphony.xwork2.ModelDriven;
import conexion.helper;
import entities.TipoDocumentoVenta;
import java.sql.ResultSet;
import java.util.ArrayList;

public class TipoDocumentoVentaAction extends MasterAction implements ModelDriven<TipoDocumentoVenta> {

    private TipoDocumentoVenta modelo = new TipoDocumentoVenta();
    private ArrayList<TipoDocumentoVenta> listTipoDocumentoVenta = new ArrayList<TipoDocumentoVenta>();

    @Override
    public TipoDocumentoVenta getModel() {
        tituloOpc = "Tipo de documento de venta";
        idClaseAccion = 30;
        return modelo;
    }

    public String vrfSeleccion() {
        idAccion = 1;
        verifAccionTipoUsuario();
        if (indErrAcc.equals("")) {
            if (modelo.getIdTipDocVen().trim().equalsIgnoreCase("")) {
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
            urlPaginacion = "tipoDocumentoVenta/TipoDocumentoVenta";
            varReturnProcess(0);
            if (!listVarReturn.isEmpty()) {
                curPagVis = Integer.parseInt(listVarReturn.get(0).toString().trim());
            }
            cantTipoDocumentoVentaIndex();
            verifPag();
            listTipoDocumentoVentaIndex();
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
                    formURL = baseURL + "tipoDocumentoVenta/grabarTipoDocumentoVenta";
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
                    if (getModel().getIdTipDocVen().trim().equalsIgnoreCase("")) {
                        indErrParm = "error";
                    } else {
                        getDatosTipoDocumentoVenta();
                        formURL = baseURL + "tipoDocumentoVenta/actualizarTipoDocumentoVenta";
                    }
                }
            }
        }
        return "modificar";
    }

    public String grabar() {
        idAccion = 5;
        verifAccionTipoUsuario();
        if (indErrAcc.equals("")) {
            modelo.setIdTipDocVen(modelo.getIdTipDocVen().trim());
            modelo.setDesTipDocVen(modelo.getDesTipDocVen().trim());
            if (modelo.getIdTipDocVen().equalsIgnoreCase("")) {
                indError += "error";
                errores.add("Ingrese el código del tipo de documento de venta.");
            } else {
                 if (!modelo.getIdTipDocVen().equalsIgnoreCase("BV") && !modelo.getIdTipDocVen().equalsIgnoreCase("FA")
                         && !modelo.getIdTipDocVen().equalsIgnoreCase("NC") && !modelo.getIdTipDocVen().equalsIgnoreCase("ND") ) {
                     indError += "error";
                     errores.add("El código del tipo de documento de venta no es valido, solo se permite BV,FA,NC,ND.");
                 }
            }

            if (modelo.getDesTipDocVen().equalsIgnoreCase("")) {
                indError += "error";
                errores.add("Ingrese la descripción del tipo de documento de venta.");
            }

            if (indError.equals("")) {
                helper conex = null;
                try {
                    conex = new helper();
                    indError = conex.getErrorSQL();
                    if (!indError.equals("")) {
                        errores.add(indError);
                    } else {
                        indError = conex.executeNonQuery("CALL usp_insTipoDocumentoVenta(?,?)",
                                new Object[]{modelo.getIdTipDocVen().trim(), modelo.getDesTipDocVen().trim()});
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
            getModel().setDesTipDocVen(getModel().getDesTipDocVen().trim());

            if (indError.equals("")) {
                helper conex = null;
                try {
                    conex = new helper();
                    indError = conex.getErrorSQL();
                    if (!indError.equals("")) {
                        errores.add(indError);
                    } else {
                        indError = conex.executeNonQuery("CALL usp_updTipoDocumentoVenta(?,?)",
                                new Object[]{getModel().getIdTipDocVen(), getModel().getDesTipDocVen()});
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
                ResultSet tabla = null;
                try {
                    conex = new helper();
                    indError = conex.getErrorSQL().trim();
                    if (!indError.equals("")) {
                        errores.add(indError);
                    } else {
                        tabla = conex.executeDataSet("CALL usp_verifDependTipoDocumentoVenta(?)",//maeVeh
                                new Object[]{modelo.getIdTipDocVen()});
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
                                indError = conex.executeNonQuery("CALL usp_dltTipoDocumentoVenta(?)",
                                        new Object[]{getModel().getIdTipDocVen()});
                                indError = indError.trim();
                                if (indError.trim().equals("")) {
                                    errores.add(indError);
                                }
                            } else /* si tiene dependencias */ {
                                indError = "error";
                                errores.add("Existen registros dependientes del tipo de documento de venta.");
                            }
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
        return "eliminar";
    }

    private void getDatosTipoDocumentoVenta() {
        helper conex = null;
        ResultSet tabla = null;

        try {
            conex = new helper();
            indError = conex.getErrorSQL();

            if (!indError.equals("")) {
                errores.add(indError);
            } else {
                tabla = conex.executeDataSet("CALL usp_getDatosTipoDocumentoVenta(?)",
                        new Object[]{modelo.getIdTipDocVen()});
                indError = conex.getErrorSQL();
                if (!indError.equals("")) {
                    errores.add(indError);
                } else {
                    while (tabla.next()) {
                        modelo.setDesTipDocVen(tabla.getString("desTipDocVen"));
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

    private void cantTipoDocumentoVentaIndex() {
        helper conex = null;
        ResultSet tabla = null;
        try {
            conex = new helper();
            indError += conex.getErrorSQL();

            if (!indError.equals("")) {
                errores.add(indError);
            } else {
                tabla = conex.executeDataSet("CALL usp_cantTipoDocumentoVentaIndex()", new Object[]{});
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

    private void listTipoDocumentoVentaIndex() {
        helper conex = null;
        ResultSet tabla = null;
        try {
            conex = new helper();
            indError += conex.getErrorSQL();
            if (!indError.equals("")) {
                errores.add(indError);
            } else {
                tabla = conex.executeDataSet("CALL usp_listTipoDocumentoVentaIndex(?,?)",
                        new Object[]{getCurPag() * regPag, regPag});
                indError += conex.getErrorSQL();
                if (!indError.equals("")) {
                    errores.add(indError);
                } else {
                    TipoDocumentoVenta obj;
                    while (tabla.next()) {
                        obj = new TipoDocumentoVenta();
                        obj.setIdTipDocVen(tabla.getString("idTipDocVen"));
                        obj.setDesTipDocVen(tabla.getString("desTipDocVen"));
                        listTipoDocumentoVenta.add(obj);
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

    public ArrayList<TipoDocumentoVenta> getListTipoDocumentoVenta() {
        return listTipoDocumentoVenta;
    }

    public void setModel(TipoDocumentoVenta modelo) {
        this.modelo = modelo;
    }

}
