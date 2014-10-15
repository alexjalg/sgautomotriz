/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package actions;

import com.opensymphony.xwork2.ModelDriven;
import conexion.helper;
import entities.AmbienteUbicacion;
import entities.TipoAmbienteUbicacion;
import java.sql.ResultSet;
import java.util.ArrayList;

public class AmbienteUbicacionAction extends MasterAction implements ModelDriven<AmbienteUbicacion> {

    private AmbienteUbicacion modelo = new AmbienteUbicacion();
    private ArrayList<AmbienteUbicacion> listAmbUbi = new ArrayList<AmbienteUbicacion>();
    private ArrayList<TipoAmbienteUbicacion> listTipAmbUbi = new ArrayList<TipoAmbienteUbicacion>();

    @Override
    public AmbienteUbicacion getModel() {
        tituloOpc = "Ambientes de Ubicación";
        idClaseAccion = 33;
        return modelo;
    }

    public String vrfSeleccion() {
        idAccion = 1;
        verifAccionTipoUsuario();
        if (indErrAcc.equals("")) {
            if (modelo.getIdAmbUbi() == 99) {
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

            urlPaginacion = "ambienteUbicacion/AmbienteUbicacion";
            varReturn = varReturn.trim();
            varReturnProcess(0);
            if (!listVarReturn.isEmpty()) {
                curPagVis = Integer.parseInt(listVarReturn.get(0).toString().trim());
            }
            cantAmbientesUbicacionaIndex();
            verifPag();
            listAmbientesUbicacionIndex();
        }
        return "success";
    }

    private void cantAmbientesUbicacionaIndex() {
        helper conex = null;
        ResultSet tabla = null;
        try {
            conex = new helper();
            indError = conex.getErrorSQL().trim();

            if (!indError.trim().equals("")) {
                errores.add(indError);
            } else {
                tabla = conex.executeDataSet("CALL usp_cantAmbienteUbicacionIndex()", new Object[]{});
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

    private void listAmbientesUbicacionIndex() {
        helper conex = null;
        ResultSet tabla = null;

        try {
            conex = new helper();
            indError = conex.getErrorSQL();

            if (!indError.equals("")) {
                errores.add(indError);
            } else {
                tabla = conex.executeDataSet("CALL usp_listAmbienteUbicacionIndex(?,?)",
                        new Object[]{(getCurPag()) * getRegPag(), getRegPag()});

                indError = conex.getErrorSQL();

                if (!indError.equals("")) {
                    errores.add(indError);
                } else {
                    AmbienteUbicacion obj;
                    while (tabla.next()) {
                        obj = new AmbienteUbicacion();
                        obj.setIdAmbUbi(tabla.getInt("idAmbUbi"));
                        obj.setDesAmbUbi(tabla.getString("desAmbUbi"));
                        obj.setCodTipAmbUbi(tabla.getString("codTipAmbUbi"));
                        obj.setDesCorAmbUbi(tabla.getString("desCorAmbUbi"));
                        listAmbUbi.add(obj);
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

    private void getDatosAmbienteUbicacion() {
        helper conex = null;
        ResultSet tabla = null;

        try {
            conex = new helper();
            indError = conex.getErrorSQL();

            if (!indError.equals("")) {
                errores.add(indError);
            } else {
                tabla = conex.executeDataSet("CALL usp_getDatosAmbienteUbicacion(?)",
                        new Object[]{modelo.getIdAmbUbi()});

                indError = conex.getErrorSQL();

                if (!indError.equals("")) {
                    errores.add(indError);
                } else {
                    while (tabla.next()) {
                        modelo.setDesAmbUbi(tabla.getString("desAmbUbi"));
                        modelo.setCodTipAmbUbi(tabla.getString("codTipAmbUbi"));
                        modelo.setDesCorAmbUbi(tabla.getString("desCorAmbUbi"));
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
                if (opcion.equals("A")) {
                    getListTipAmbUbi().add(new TipoAmbienteUbicacion("R", "Almacen Repuestos"));
                    getListTipAmbUbi().add(new TipoAmbienteUbicacion("T", "Taller Servicios"));
                    getListTipAmbUbi().add(new TipoAmbienteUbicacion("V", "Almacen Vehículos"));
                    getListTipAmbUbi().add(new TipoAmbienteUbicacion("I", "Almacen Virtual"));
                    formURL = baseURL + "ambienteUbicacion/grabarAmbienteUbicacion";
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
            if (!opcion.trim().equals("M") || modelo.getIdAmbUbi() == 99) {
                indErrParm = "error";
            } else {
                varReturnProcess(1);

                if (opcion.equals("M")) {
                    getListTipAmbUbi().add(new TipoAmbienteUbicacion("R", "Almacen Repuestos"));
                    getListTipAmbUbi().add(new TipoAmbienteUbicacion("T", "Taller Servicios"));
                    getListTipAmbUbi().add(new TipoAmbienteUbicacion("V", "Almacen Vehículos"));
                    getListTipAmbUbi().add(new TipoAmbienteUbicacion("I", "Almacen Virtual"));
                    getDatosAmbienteUbicacion();
                    formURL = baseURL + "ambienteUbicacion/actualizarAmbienteUbicacion";
                }
            }
        }

        return "adicionar";
    }

    public String grabar() {
        idAccion = 5;
        verifAccionTipoUsuario();
        if (indErrAcc.equals("")) {
            modelo.setDesAmbUbi(modelo.getDesAmbUbi().trim());
            modelo.setDesCorAmbUbi(modelo.getDesCorAmbUbi().trim());

            if (modelo.getDesAmbUbi().equals("")) {
                indError = "Debe ingresar la descripción";
                errores.add(indError);
            }
            if (modelo.getDesCorAmbUbi().equals("")) {
                indError = "Debe ingresar la descripción corta";
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
                        indError = conex.executeNonQuery("CALL usp_insAmbienteUbicacion(?,?,?)",
                                new Object[]{modelo.getDesAmbUbi(), modelo.getCodTipAmbUbi(), modelo.getDesCorAmbUbi()});

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
            modelo.setDesAmbUbi(modelo.getDesAmbUbi().trim());
            modelo.setDesCorAmbUbi(modelo.getDesCorAmbUbi().trim());

            if (modelo.getDesAmbUbi().equals("")) {
                indError = "Debe ingresar la descripción";
                errores.add(indError);
            }
            if (modelo.getDesCorAmbUbi().equals("")) {
                indError = "Debe ingresar la descripción corta";
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
                        indError = conex.executeNonQuery("CALL usp_updAmbienteUbicacion(?,?,?,?)",
                                new Object[]{modelo.getIdAmbUbi(), modelo.getDesAmbUbi(), modelo.getCodTipAmbUbi(), modelo.getDesCorAmbUbi()});

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
                        tabla = conex.executeDataSet("CALL usp_verifDependAmbienteUbicacion(?)",
                                new Object[]{modelo.getIdAmbUbi()});
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
                                indError = conex.executeNonQuery("CALL usp_dltAmbienteUbicacion(?)",
                                        new Object[]{modelo.getIdAmbUbi()});

                                indError = indError.trim();
                                if (indError.trim().equals("")) {
                                    errores.add(indError);
                                }
                            } else /* si tiene dependencias */ {
                                indError = "error";
                                errores.add("Existen registros dependientes del Ambiente");
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
    public AmbienteUbicacion getModelo() {
        return modelo;
    }

    /**
     * @param modelo the modelo to set
     */
    public void setModelo(AmbienteUbicacion modelo) {
        this.modelo = modelo;
    }

    /**
     * @return the listAmbUbi
     */
    public ArrayList<AmbienteUbicacion> getListAmbUbi() {
        return listAmbUbi;
    }

    /**
     * @param listAmbUbi the listAmbUbi to set
     */
    public void setListAmbUbi(ArrayList<AmbienteUbicacion> listAmbUbi) {
        this.listAmbUbi = listAmbUbi;
    }

    /**
     * @return the listTipAmbUbi
     */
    public ArrayList<TipoAmbienteUbicacion> getListTipAmbUbi() {
        return listTipAmbUbi;
    }

    /**
     * @param listTipAmbUbi the listTipAmbUbi to set
     */
    public void setListTipAmbUbi(ArrayList<TipoAmbienteUbicacion> listTipAmbUbi) {
        this.listTipAmbUbi = listTipAmbUbi;
    }
}
