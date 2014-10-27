/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package actions;

import com.opensymphony.xwork2.ModelDriven;
import conexion.helper;
import entities.CategoriaVehiculo;
import java.sql.ResultSet;
import java.util.ArrayList;

public class CategoriaVehiculoAction extends MasterAction implements ModelDriven<CategoriaVehiculo> {

    private CategoriaVehiculo modelo = new CategoriaVehiculo();
    private ArrayList<CategoriaVehiculo> listCatVeh = new ArrayList<CategoriaVehiculo>();

    @Override
    public CategoriaVehiculo getModel() {
        tituloOpc = "Categorias de vehículo";
        idClaseAccion = 21;
        
        return modelo;
    }

    public String vrfSeleccion() {
        idAccion = 1;

        verifAccionTipoUsuario();

        if (indErrAcc.equals("")) {
            if (modelo.getIdCatVeh() == 0) {
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

            urlPaginacion = "categoriaVehiculo/CategoriaVehiculo";
            varReturn = varReturn.trim();

            varReturnProcess(0);
            if (!listVarReturn.isEmpty()) {
                curPagVis = Integer.parseInt(listVarReturn.get(0).toString().trim());
            }

            cantCategoriaVehiculoIndex();
            verifPag();
            listCategoriaVehiculoIndex();
        }
        return "success";
    }

    private void cantCategoriaVehiculoIndex() {
        helper conex = null;
        ResultSet tabla = null;

        try {
            conex = new helper();
            indError = conex.getErrorSQL().trim();

            if (!indError.trim().equals("")) {
                errores.add(indError);
            } else {
                tabla = conex.executeDataSet("CALL usp_cantCategoriaVehiculoIndex()", new Object[]{});
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

    private void listCategoriaVehiculoIndex() {
        helper conex = null;
        ResultSet tabla = null;

        try {
            conex = new helper();
            indError = conex.getErrorSQL();

            if (!indError.equals("")) {
                errores.add(indError);
            } else {
                tabla = conex.executeDataSet("CALL usp_listCategoriaVehiculoIndex(?,?)",
                        new Object[]{(getCurPag()) * getRegPag(), getRegPag()});

                indError = conex.getErrorSQL();

                if (!indError.equals("")) {
                    errores.add(indError);
                } else {
                    CategoriaVehiculo obj;
                    while (tabla.next()) {
                        obj = new CategoriaVehiculo();
                        obj.setIdCatVeh(tabla.getInt("idCatVeh"));
                        obj.setDesCatVeh(tabla.getString("desCatVeh"));
                        listCatVeh.add(obj);
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

    private void getDatosCategoriaVehiculo() {
        helper conex = null;
        ResultSet tabla = null;

        try {
            conex = new helper();
            indError = conex.getErrorSQL();

            if (!indError.equals("")) {
                errores.add(indError);
            } else {
                tabla = conex.executeDataSet("CALL usp_getDatosCategoriaVehiculo(?)",
                        new Object[]{modelo.getIdCatVeh()});

                indError = conex.getErrorSQL();

                if (!indError.equals("")) {
                    errores.add(indError);
                } else {
                    while (tabla.next()) {
                        modelo.setDesCatVeh(tabla.getString("desCatVeh"));
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
                
                formURL = baseURL + "categoriaVehiculo/grabarCategoriaVehiculo";
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
                
                if (modelo.getIdCatVeh() == 0) {
                    indErrParm = "error";
                } else {
                    getDatosCategoriaVehiculo();
                    formURL = baseURL + "categoriaVehiculo/actualizarCategoriaVehiculo";
                }
            }
        }

        return "adicionar";
    }

    public String grabar() {
        idAccion = 5;

        verifAccionTipoUsuario();

        if (indErrAcc.equals("")) {
            modelo.setDesCatVeh(modelo.getDesCatVeh().trim());
            if (modelo.getDesCatVeh().equals("")) {
                indError = "Debe ingresar una categoria del vehículo";
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
                        indError = conex.executeNonQuery("CALL usp_insCategoriaVehiculo(?)",
                                new Object[]{modelo.getDesCatVeh()});

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
            modelo.setDesCatVeh(modelo.getDesCatVeh().trim());
            if (modelo.getDesCatVeh().equals("")) {
                indError = "Debe ingresar la categoria del vehículo";
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
                        indError = conex.executeNonQuery("CALL usp_updCategoriaVehiculo(?,?)",
                                new Object[]{modelo.getIdCatVeh(), modelo.getDesCatVeh()});

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
                        tabla = conex.executeDataSet("CALL usp_verifDependCatVeh(?)",
                                new Object[]{modelo.getIdCatVeh()});
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
                                indError = conex.executeNonQuery("CALL usp_dltCategoriaVehiculo(?)",
                                        new Object[]{modelo.getIdCatVeh()});

                                indError = indError.trim();
                                if (indError.trim().equals("")) {
                                    errores.add(indError);
                                }
                            } else /* si tiene dependencias */ {
                                indError = "error";
                                errores.add("Existen registros dependientes de la categoria del vehículo");
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
    public CategoriaVehiculo getModelo() {
        return modelo;
    }

    /**
     * @param modelo the modelo to set
     */
    public void setModelo(CategoriaVehiculo modelo) {
        this.modelo = modelo;
    }

    /**
     * @return the listCatVeh
     */
    public ArrayList<CategoriaVehiculo> getListCatVeh() {
        return listCatVeh;
    }

    /**
     * @param listCatVeh the listCatVeh to set
     */
    public void setListCatVeh(ArrayList<CategoriaVehiculo> listCatVeh) {
        this.listCatVeh = listCatVeh;
    }
}
