/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package actions;

import com.opensymphony.xwork2.ModelDriven;
import conexion.helper;
import entities.ColorInteior;
import java.sql.ResultSet;
import java.util.ArrayList;

public class ColorInteriorAction extends MasterAction implements ModelDriven<ColorInteior> {

    private ColorInteior modelo = new ColorInteior();
    private ArrayList<ColorInteior> listColInt = new ArrayList<ColorInteior>();

    @Override
    public ColorInteior getModel() {
        tituloOpc = "Colores de Interior";
        idClaseAccion = 24;
        return modelo;
    }

    public String vrfSeleccion() {
        idAccion = 1;
        String valorB = "";
        verifAccionTipoUsuario();
        if (indErrAcc.equals("")) {
            if (modelo.getIdColInt().equals(valorB)) {
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

            urlPaginacion = "colorInterior/ColorInterior";
            varReturn = varReturn.trim();

            varReturnProcess(0);
            if (!listVarReturn.isEmpty()) {
                curPagVis = Integer.parseInt(listVarReturn.get(0).toString().trim());
            }

            cantColorInteriorIndex();
            verifPag();
            listColorInteriorIndex();
        }
        return "success";
    }

    private void cantColorInteriorIndex() {
        helper conex = null;
        ResultSet tabla = null;

        try {
            conex = new helper();
            indError = conex.getErrorSQL().trim();

            if (!indError.trim().equals("")) {
                errores.add(indError);
            } else {
                tabla = conex.executeDataSet("CALL usp_cantColorInteriorIndex()", new Object[]{});
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

    private void listColorInteriorIndex() {
        helper conex = null;
        ResultSet tabla = null;

        try {
            conex = new helper();
            indError = conex.getErrorSQL();

            if (!indError.equals("")) {
                errores.add(indError);
            } else {
                tabla = conex.executeDataSet("CALL usp_listColorInteriorIndex(?,?)",
                        new Object[]{(getCurPag()) * getRegPag(), getRegPag()});

                indError = conex.getErrorSQL();

                if (!indError.equals("")) {
                    errores.add(indError);
                } else {
                    ColorInteior obj;
                    while (tabla.next()) {
                        obj = new ColorInteior();
                        obj.setIdColInt(tabla.getString("idColInt"));
                        obj.setDesColInt(tabla.getString("desColInt"));
                        listColInt.add(obj);
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

    private void getDatosColorInterior() {
        helper conex = null;
        ResultSet tabla = null;

        try {
            conex = new helper();
            indError = conex.getErrorSQL();

            if (!indError.equals("")) {
                errores.add(indError);
            } else {
                modelo.setIdColInt_h(modelo.getIdColInt());
                tabla = conex.executeDataSet("CALL usp_getDatosColorInterior(?)",
                        new Object[]{modelo.getIdColInt()});

                indError = conex.getErrorSQL();

                if (!indError.equals("")) {
                    errores.add(indError);
                } else {
                    while (tabla.next()) {
                        modelo.setDesColInt(tabla.getString("desColInt"));
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
        modelo.setIdColInt("");
        verifAccionTipoUsuario();

        if (indErrAcc.equals("")) {
            nivBandeja = 1;

            if (!opcion.trim().equals("A") && !opcion.trim().equals("M")) {
                indErrParm = "error";
            } else {
                varReturnProcess(1);

                if (opcion.equals("A")) {
                    formURL = baseURL + "colorInterior/grabarColorInterior";
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
                    String valorB = "";
                    if (modelo.getIdColInt().equals(valorB)) {
                        indErrParm = "error";
                    } else {
                        getDatosColorInterior();

                        formURL = baseURL + "colorInterior/actualizarColorInterior";
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
            modelo.setIdColInt(modelo.getIdColInt().trim());
            modelo.setDesColInt(modelo.getDesColInt().trim());
            if (modelo.getIdColInt().equals("")) {
                indError = "Debe ingresar el codigo";
                errores.add(indError);
            }
            if (modelo.getDesColInt().equals("")) {
                indError = "Debe ingresar el color interior";
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
                        ResultSet tabla = null;

                        tabla = conex.executeDataSet("CALL usp_verifColorIntID(?)",
                                new Object[]{modelo.getIdColInt()});
                        indError = conex.getErrorSQL();
                        if (!indError.equals("")) {
                            errores.add(indError);
                        } else {
                            int cant = 0;
                            while (tabla.next()) {
                                cant = tabla.getInt(1);
                            }

                            if (cant == 0) {

                                indError = conex.executeNonQuery("CALL usp_insColorInterior(?,?)",
                                        new Object[]{modelo.getIdColInt(), modelo.getDesColInt()});

                                if (!indError.equals("")) {
                                    errores.add(indError);
                                }
                            } else {
                                indError = "error";
                                errores.add("Codigó ya existe.");
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

        return "grabar";
    }

    public String actualizar() {
        idAccion = 6;
        verifAccionTipoUsuario();
        if (indErrAcc.equals("")) {
            modelo.setIdColInt(modelo.getIdColInt_h().trim());
            modelo.setDesColInt(modelo.getDesColInt().trim());

            if (modelo.getIdColInt().equals("")) {
                indError = "Debe ingresar el codigo";
                errores.add(indError);
            }

            if (modelo.getDesColInt().equals("")) {
                indError = "Debe ingresar el color interior";
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
                        indError = conex.executeNonQuery("CALL usp_updColorInterior(?,?)",
                                new Object[]{modelo.getIdColInt_h(), modelo.getDesColInt()});

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
                        tabla = conex.executeDataSet("CALL usp_verifDependColInt(?)",
                                new Object[]{modelo.getIdColInt()});
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
                                indError = conex.executeNonQuery("CALL usp_dltColorInterior(?)",
                                        new Object[]{modelo.getIdColInt()});

                                indError = indError.trim();
                                if (indError.trim().equals("")) {
                                    errores.add(indError);
                                }
                            } else /* si tiene dependencias */ {
                                indError = "error";
                                errores.add("Existen registros dependientes del color de interior");
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
    public ColorInteior getModelo() {
        return modelo;
    }

    /**
     * @param modelo the modelo to set
     */
    public void setModelo(ColorInteior modelo) {
        this.modelo = modelo;
    }

    /**
     * @return the listColInt
     */
    public ArrayList<ColorInteior> getListColInt() {
        return listColInt;
    }

    /**
     * @param listColInt the listColInt to set
     */
    public void setListColInt(ArrayList<ColorInteior> listColInt) {
        this.listColInt = listColInt;
    }
}
