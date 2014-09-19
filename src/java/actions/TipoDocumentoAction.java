/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package actions;

import com.opensymphony.xwork2.ModelDriven;
import conexion.helper;
import entities.TipoDocumento;
import java.sql.ResultSet;
import java.util.ArrayList;

public class TipoDocumentoAction extends MasterAction implements ModelDriven<TipoDocumento> {

    private TipoDocumento modelo = new TipoDocumento();
    private ArrayList<TipoDocumento> listTipDoc = new ArrayList<TipoDocumento>();

    @Override
    public TipoDocumento getModel() {
        tituloOpc = "Tipos de Documento";
        idClaseAccion = 22;
        return modelo;
    }

    public String vrfSeleccion() {
        idAccion = 1;
        verifAccionTipoUsuario();
        if (indErrAcc.equals("")) {
            if (modelo.getIdTipDoc() == 0) {
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
            urlPaginacion = "tipoDocumento/TipoDocumento";
            varReturn = varReturn.trim();
            varReturnProcess(0);
            if (!listVarReturn.isEmpty()) {
                curPagVis = Integer.parseInt(listVarReturn.get(0).toString().trim());
            }
            cantTipoDocumentoIndex();
            verifPag();
            listTipoDocumentoIndex();

        }
        return "success";
    }

    private void cantTipoDocumentoIndex() {
        helper conex = null;
        ResultSet tabla = null;
        try {
            conex = new helper();
            indError = conex.getErrorSQL().trim();

            if (!indError.trim().equals("")) {
                errores.add(indError);
            } else {
                tabla = conex.executeDataSet("CALL usp_cantTipoDocumentoIndex()", new Object[]{});
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

    private void listTipoDocumentoIndex() {
        helper conex = null;
        ResultSet tabla = null;

        try {
            conex = new helper();
            indError = conex.getErrorSQL();

            if (!indError.equals("")) {
                errores.add(indError);
            } else {
                tabla = conex.executeDataSet("CALL usp_listTipoDocumentoIndex(?,?)",
                        new Object[]{(getCurPag()) * getRegPag(), getRegPag()});

                indError = conex.getErrorSQL();

                if (!indError.equals("")) {
                    errores.add(indError);
                } else {
                    TipoDocumento obj;
                    while (tabla.next()) {
                        obj = new TipoDocumento();
                        obj.setIdTipDoc(tabla.getInt("idTipDoc"));
                        obj.setDesTipDoc(tabla.getString("desTipDoc"));
                        obj.setOtrTipCli(tabla.getString("otrTipCli"));
                        obj.setNumLonVal(tabla.getInt("numLonVal"));
                        listTipDoc.add(obj);
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

    private void getDatosTipoDocumento() {
        helper conex = null;
        ResultSet tabla = null;

        try {
            conex = new helper();
            indError = conex.getErrorSQL();

            if (!indError.equals("")) {
                errores.add(indError);
            } else {
                tabla = conex.executeDataSet("CALL usp_getDatosTipoDocumento(?)",
                        new Object[]{modelo.getIdTipDoc()});

                indError = conex.getErrorSQL();

                if (!indError.equals("")) {
                    errores.add(indError);
                } else {
                    while (tabla.next()) {
                        modelo.setDesTipDoc(tabla.getString("desTipDoc"));
                        modelo.setOtrTipCli(tabla.getString("otrTipCli"));
                        modelo.setNumLonVal(tabla.getInt("numLonVal"));
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
                    formURL = baseURL + "tipoDocumento/grabarTipoDocumento";
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
                    if (modelo.getIdTipDoc() == 0) {
                        indErrParm = "error";
                    } else {
                        getDatosTipoDocumento();
                        formURL = baseURL + "tipoDocumento/actualizarTipoDocumento";
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
            modelo.setDesTipDoc(modelo.getDesTipDoc().trim());
            modelo.setOtrTipCli(modelo.getOtrTipCli().trim());
            modelo.setNumLonVal(modelo.getNumLonVal());

            if (modelo.getDesTipDoc().equals("")) {
                indError = "E";
                errores.add("Debe ingresar el documento");
            }

            if (modelo.getOtrTipCli().equals("")) {
                indError = "E";
                errores.add("Debe seleccionar el tipo de cliente");
            } /*else {
                if (modelo.getOtrTipCli().equals("N")) {
                    if (modelo.getOtrTipCli().equals("J")) {
                        indError = "E";
                        errores.add("N o J son los valores válidos para el tipo de cliente");
                    }
                }
            }*/
            if (modelo.getNumLonVal() > 0) {
            } else {
                indError = "E";
                errores.add("La longitud de validación debe de ser mayor a cero");
            }

            if (indError.equals("")) {
                helper conex = null;

                try {
                    conex = new helper();
                    indError = conex.getErrorSQL();

                    if (!indError.equals("")) {
                        errores.add(indError);
                    } else {
                        indError = conex.executeNonQuery("CALL usp_insTipoDocumento(?,?,?)",
                                new Object[]{modelo.getDesTipDoc(), modelo.getOtrTipCli(), modelo.getNumLonVal()});

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
            modelo.setDesTipDoc(modelo.getDesTipDoc().trim());
            modelo.setOtrTipCli(modelo.getOtrTipCli().trim());
            modelo.setNumLonVal(modelo.getNumLonVal());

            if (modelo.getDesTipDoc().equals("")) {
                indError = "E";
                errores.add("Debe ingresar el documento");
            }
            if (modelo.getOtrTipCli().equals("")) {
                indError = "E";
                errores.add("Debe seleccionar el tipo de cliente");
            } /*else {
                if (modelo.getOtrTipCli() != "N") {
                    if (modelo.getOtrTipCli() != "J") {
                        indError = "E";
                        errores.add("N ó J son los valores válidos para el tipo de cliente");
                    }
                }
            }*/
            if (modelo.getNumLonVal() > 0) {
            } else {
                indError = "E";
                errores.add("La longitud de validación debe de ser mayor a cero");
            }

            if (indError.equals("")) {
                helper conex = null;

                try {
                    conex = new helper();
                    indError = conex.getErrorSQL();

                    if (!indError.equals("")) {
                        errores.add(indError);
                    } else {
                        indError = conex.executeNonQuery("CALL usp_updTipoDocumento(?,?,?,?)",
                                new Object[]{modelo.getIdTipDoc(), modelo.getDesTipDoc(), modelo.getOtrTipCli(), modelo.getNumLonVal()});

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
                        tabla = conex.executeDataSet("CALL usp_verifTipoDocumento(?)",
                                new Object[]{modelo.getIdTipDoc()});
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
                                indError = conex.executeNonQuery("CALL usp_dltTipoDocumento(?)",
                                        new Object[]{modelo.getIdTipDoc()});

                                indError = indError.trim();
                                if (indError.trim().equals("")) {
                                    errores.add(indError);
                                }
                            } else /* si tiene dependencias */ {
                                indError = "error";
                                errores.add("Existen registros dependientes del tipo de documento");
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
    public TipoDocumento getModelo() {
        return modelo;
    }

    /**
     * @param modelo the modelo to set
     */
    public void setModelo(TipoDocumento modelo) {
        this.modelo = modelo;
    }

    /**
     * @return the listTipDoc
     */
    public ArrayList<TipoDocumento> getListTipDoc() {
        return listTipDoc;
    }

    /**
     * @param listTipDoc the listTipDoc to set
     */
    public void setListTipDoc(ArrayList<TipoDocumento> listTipDoc) {
        this.listTipDoc = listTipDoc;
    }
}
