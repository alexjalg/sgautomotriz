/*
 * Action: Colores
 * Creado por: Angelo Ccoicca
 * Fecha de creación: 19-05-2014
 * Modificado por                   Fecha de Modificación
 * - 
 * -
 */
package actions;

import com.opensymphony.xwork2.ModelDriven;
import conexion.helper;
import entities.ColoresExterior;
import java.sql.ResultSet;
import java.util.ArrayList;

public class ColoresExteriorAction extends MasterAction implements ModelDriven<ColoresExterior> {

    private ColoresExterior modelo = new ColoresExterior();
    private ArrayList<ColoresExterior> listColoresExterior = new ArrayList<ColoresExterior>();

    @Override
    public ColoresExterior getModel() {
        tituloOpc = "Colores de Exterior";
        idClaseAccion = 1;

        return modelo;
    }

    public String vrfSeleccion() {
        idAccion = 1;
        verifAccionTipoUsuario();

        if (indErrAcc.equals("")) {
            if (modelo.getIdColExt().trim().equals("")) {
                indError = "error";
                errores.add("No ha seleccionado ningun registro");
            }
        }

        return "vrfSeleccion";
    }

    private void cantColoresIndex() {
        helper conex = null;
        ResultSet tabla = null;

        try {
            conex = new helper();
            indError += conex.getErrorSQL();

            if (!indError.equals("")) {
                errores.add(indError);
            } else {
                tabla = conex.executeDataSet("CALL usp_cantColoresExtIndex(?)",
                        new Object[]{modelo.getDesColExt_f()});

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

    private void listColoresIndex() {
        helper conex = null;
        ResultSet tabla = null;

        try {
            conex = new helper();
            indError += conex.getErrorSQL();

            if (!indError.equals("")) {
                errores.add(indError);
            } else {
                tabla = conex.executeDataSet("CALL usp_listColoresExtIndex(?,?,?)",
                        new Object[]{modelo.getDesColExt_f(), getCurPag() * regPag, regPag});

                indError += conex.getErrorSQL();

                if (!indError.equals("")) {
                    errores.add(indError);
                } else {
                    ColoresExterior obj;
                    while (tabla.next()) {
                        obj = new ColoresExterior();
                        obj.setIdColExt(tabla.getString("idColExt"));
                        obj.setDesColExt(tabla.getString("desColExt"));
                        listColoresExterior.add(obj);
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
            urlPaginacion = "coloresExterior/ColorExterior";

            varReturnProcess(0);
            if (!listVarReturn.isEmpty()) {
                curPagVis = Integer.parseInt(listVarReturn.get(0).toString().trim());
                modelo.setDesColExt_f(listVarReturn.get(1).toString());
            }

            cantColoresIndex();
            verifPag();
            listColoresIndex();
        }

        return "success";
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
                    modelo.setIdColExt("");
                    formURL = baseURL + "coloresExterior/grabarColorExterior";
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
                    getDatosColor();
                    formURL = baseURL + "coloresExterior/actualizarColorExterior";

                }
            }
        }

        return "adicionar";
    }

    public void getDatosColor() {
        helper conex = null;
        ResultSet tabla = null;

        try {
            conex = new helper();
            indError += conex.getErrorSQL();

            if (!indError.equals("")) {
                errores.add(indError);
            } else {
                tabla = conex.executeDataSet("CALL usp_getDatosColorExt(?)",
                        new Object[]{modelo.getIdColExt()});
                indError += conex.getErrorSQL();

                if (!indError.equals("")) {
                    errores.add(indError);
                } else {
                    while (tabla.next()) {
                        modelo.setIdColExt(tabla.getString("idColExt"));
                        modelo.setDesColExt(tabla.getString("desColExt"));
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
            modelo.setIdColExt(modelo.getIdColExt().trim());
            modelo.setDesColExt(modelo.getDesColExt().trim());

            if (modelo.getIdColExt().equals("")) {
                indError += "error";
                errores.add("Ingrese el código del color de exterior");
            }

            if (modelo.getDesColExt().equals("")) {
                indError += "error";
                errores.add("Ingrese el nombre del color de exterior");
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
                        tabla = conex.executeDataSet("CALL usp_verifExistColorExt(?)",
                                new Object[]{modelo.getIdColExt()});

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
                                errores.add("Ya existe un color de exterior con el código ingresado");
                            } else {
                                indError = conex.executeNonQuery("CALL usp_insColorExt(?,?)",
                                        new Object[]{ modelo.getIdColExt(), modelo.getDesColExt() });

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
            modelo.setIdColExt(modelo.getIdColExt().trim());
            modelo.setDesColExt(modelo.getDesColExt().trim());

            if (modelo.getDesColExt().equals("")) {
                indError += "error";
                errores.add("Ingrese el nombre del color de exterior");
            }

            if (indError.equals("")) {
                helper conex = null;

                try {
                    conex = new helper();
                    indError += conex.getErrorSQL();

                    if (!indError.equals("")) {
                        errores.add(indError);
                    } else {
                        indError += conex.executeNonQuery("CALL usp_updColorExt(?,?)",
                                new Object[]{modelo.getIdColExt(), modelo.getDesColExt()});

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
                        tabla = conex.executeDataSet("CALL usp_verifDependColorExt(?)",
                                new Object[]{modelo.getIdColExt()});
                        indError += conex.getErrorSQL();

                        if (!indError.equals("")) {
                            errores.add(indError);
                        } else {
                            int cant = 0;
                            while (tabla.next()) {
                                cant = tabla.getInt(1);
                            }

                            /* Si no tiene dependencias */
                            if (cant == 0) {
                                indError += conex.executeNonQuery("CALL usp_dltColorExt(?)",
                                        new Object[]{modelo.getIdColExt()});

                                indError = indError.trim();
                                if (indError.trim().equals("")) {
                                    errores.add(indError);
                                }
                            } else /* si tiene dependencias */ {
                                indError += "error";
                                errores.add("Existen registros dependientes del color de exterior");
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
        }

        return "eliminar";
    }

    public ColoresExterior getModelo() {
        return modelo;
    }

    public void setModelo(ColoresExterior modelo) {
        this.modelo = modelo;
    }

    public ArrayList<ColoresExterior> getListColoresExterior() {
        return listColoresExterior;
    }
}
