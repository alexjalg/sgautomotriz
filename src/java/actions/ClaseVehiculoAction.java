/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package actions;

import com.opensymphony.xwork2.ModelDriven;
import conexion.helper;
import entities.ClaseVehiculo;
import java.sql.ResultSet;
import java.util.ArrayList;

public class ClaseVehiculoAction extends MasterAction implements ModelDriven<ClaseVehiculo> {

    private ClaseVehiculo modelo = new ClaseVehiculo();
    private ArrayList<ClaseVehiculo> listClaVeh = new ArrayList<ClaseVehiculo>();

    @Override
    public ClaseVehiculo getModel() {
        tituloOpc = "Clases de vehículo";
        idClaseAccion = 16;
        return modelo;
    }

    public String vrfSeleccion() {
        idAccion = 1;

        verifAccionTipoUsuario();

        if (indErrAcc.equals("")) {
            if (modelo.getIdClaVeh() == 0) {
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

            urlPaginacion = "claseVehiculo/ClaseVehiculo";
            varReturn = varReturn.trim();

            varReturnProcess(0);
            if (!listVarReturn.isEmpty()) {
                curPagVis = Integer.parseInt(listVarReturn.get(0).toString().trim());
            }

            cantClaseVehiculoIndex();
            verifPag();
            listClaseVehiculoIndex();
        }
        return "success";
    }

    private void cantClaseVehiculoIndex() {
        helper conex = null;
        ResultSet tabla = null;

        try {
            conex = new helper();
            indError = conex.getErrorSQL().trim();

            if (!indError.trim().equals("")) {
                errores.add(indError);
            } else {
                tabla = conex.executeDataSet("CALL usp_cantClaseVehiculoIndex()", new Object[]{});
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

    private void listClaseVehiculoIndex() {
        helper conex = null;
        ResultSet tabla = null;

        try {
            conex = new helper();
            indError = conex.getErrorSQL();

            if (!indError.equals("")) {
                errores.add(indError);
            } else {
                tabla = conex.executeDataSet("CALL usp_listClaseVehiculoIndex(?,?)",
                        new Object[]{(getCurPag()) * getRegPag(), getRegPag()});

                indError = conex.getErrorSQL();

                if (!indError.equals("")) {
                    errores.add(indError);
                } else {
                    ClaseVehiculo obj;
                    while (tabla.next()) {
                        obj = new ClaseVehiculo();
                        obj.setIdClaVeh(tabla.getInt("idClaVeh"));
                        obj.setDesClaVeh(tabla.getString("desClaVeh"));
                        listClaVeh.add(obj);
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

    private void getDatosClaseVehiculo() {
        helper conex = null;
        ResultSet tabla = null;

        try {
            conex = new helper();
            indError = conex.getErrorSQL();

            if (!indError.equals("")) {
                errores.add(indError);
            } else {
                tabla = conex.executeDataSet("CALL usp_getDatosClaseVehiculo(?)",
                        new Object[]{modelo.getIdClaVeh()});

                indError = conex.getErrorSQL();

                if (!indError.equals("")) {
                    errores.add(indError);
                } else {
                    while (tabla.next()) {
                        modelo.setDesClaVeh(tabla.getString("desClaVeh"));
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
                    formURL = baseURL + "claseVehiculo/grabarClaseVehiculo";
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
                    if (modelo.getIdClaVeh() == 0) {
                        indErrParm = "error";
                    } else {
                        getDatosClaseVehiculo();
                        formURL = baseURL + "claseVehiculo/actualizarClaseVehiculo";
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
            modelo.setDesClaVeh(modelo.getDesClaVeh().trim());
            if (modelo.getDesClaVeh().equals("")) {
                indError = "Debe ingresar la clase del vehículo";
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
                        indError = conex.executeNonQuery("CALL usp_insClaseVehiculo(?)",
                                new Object[]{modelo.getDesClaVeh()});

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
            modelo.setDesClaVeh(modelo.getDesClaVeh().trim());
            if (modelo.getDesClaVeh().equals("")) {
                indError = "Debe ingresar la clase del vehículo";
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
                        indError = conex.executeNonQuery("CALL usp_updClaseVehiculo(?,?)",
                                new Object[]{modelo.getIdClaVeh(), modelo.getDesClaVeh()});

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
                        tabla = conex.executeDataSet("CALL usp_verifDependClaVeh(?)",
                                new Object[]{modelo.getIdClaVeh()});
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
                                indError = conex.executeNonQuery("CALL usp_dltClaseVehiculo(?)",
                                        new Object[]{modelo.getIdClaVeh()});

                                indError = indError.trim();
                                if (indError.trim().equals("")) {
                                    errores.add(indError);
                                }
                            } else /* si tiene dependencias */ {
                                indError = "error";
                                errores.add("Existen registros dependientes de la clase del vehículo");
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
    public ClaseVehiculo getModelo() {
        return modelo;
    }

    /**
     * @param modelo the modelo to set
     */
    public void setModelo(ClaseVehiculo modelo) {
        this.modelo = modelo;
    }

    /**
     * @return the listClaVeh
     */
    public ArrayList<ClaseVehiculo> getListClaVeh() {
        return listClaVeh;
    }

    /**
     * @param listClaVeh the listClaVeh to set
     */
    public void setListClaVeh(ArrayList<ClaseVehiculo> listClaVeh) {
        this.listClaVeh = listClaVeh;
    }
}
