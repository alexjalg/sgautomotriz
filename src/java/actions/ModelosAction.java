/*
 * Action: Modelos
 * Creado por: Angelo Ccoicca
 * Fecha de creación: 19-05-2014
 * Modificado por                   Fecha de Modificación
 * - 
 * -
 */
package actions;

import static com.opensymphony.xwork2.Action.SUCCESS;
import com.opensymphony.xwork2.ModelDriven;
import conexion.helper;
import entities.Modelos;
import java.sql.ResultSet;
import java.util.ArrayList;

public class ModelosAction extends MasterAction implements ModelDriven<Modelos> {

    private Modelos modelo = new Modelos();
    private ArrayList<Modelos> listModelos = new ArrayList<Modelos>();

    @Override
    public Modelos getModel() {
        tituloOpc = "Modelos";
        idClaseAccion = 8;

        return modelo;
    }
    
    public String vrfSeleccion() {
        idAccion = 1;
        verifAccionTipoUsuario();

        if (indErrAcc.equals("")) {
            if (modelo.getIdModMar().trim().equals("")) {
                indError = "error";
                errores.add("No ha seleccionado ningun registro");
            }
        }

        return "vrfSeleccion";
    }

    private void cantModelosIndex() {
        helper conex = null;
        ResultSet tabla = null;

        try {
            conex = new helper();
            indError += conex.getErrorSQL();

            if (!indError.equals("")) {
                errores.add(indError);
            } else {
                tabla = conex.executeDataSet("CALL usp_cantModelosIndex(?)",
                        new Object[]{modelo.getIdMar()});

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

    private void listModelosIndex() {
        helper conex = null;
        ResultSet tabla = null;

        try {
            conex = new helper();
            indError = conex.getErrorSQL();

            if (!indError.equals("")) {
                errores.add(indError);
            } else {
                tabla = conex.executeDataSet("CALL usp_listModelosIndex(?,?,?)",
                        new Object[]{modelo.getIdMar(), getCurPag() * regPag, regPag});

                indError = conex.getErrorSQL();

                if (!indError.equals("")) {
                    errores.add(indError);
                } else {
                    Modelos obj;
                    while (tabla.next()) {
                        obj = new Modelos();
                        obj.setIdModMar(tabla.getString("idModMar"));
                        obj.setDesModMar(tabla.getString("desModMar"));
                        listModelos.add(obj);
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

    @Override
    public String execute() {
        idAccion = 2;
        verifAccionTipoUsuario();

        if (indErrAcc.equals("")) {
            nivBandeja = 2;

            varReturnProcess(0);
            if (!listVarReturn.isEmpty()) {
                curPagVis = Integer.parseInt(listVarReturn.get(0).toString().trim());
                modelo.setIdMar(listVarReturn.get(1).toString().trim());
            }

            if (modelo.getIdMar().equals("")) {
                indErrParm = "error";
            } else {
                urlPaginacion = "modelos/Modelo";

                getDatosMarca();

                cantModelosIndex();
                verifPag();
                listModelosIndex();
            }
        }

        return SUCCESS;
    }

    public String adicionar() {
        idAccion = 3;
        verifAccionTipoUsuario();

        if (indErrAcc.equals("")) {
            nivBandeja = 2;

            if (!opcion.trim().equals("A") || modelo.getIdMar().equals("")) {
                indErrParm = "error";
            } else {
                modelo.setIdModMar("");
                getDatosMarca();

                varReturnProcess(1);
                
                accion = "Adicionar";
                
                formURL = baseURL + "modelos/grabarModelo";
            }
        }

        return "adicionar";
    }
    
    public String modificar() {
        idAccion = 4;
        verifAccionTipoUsuario();

        if (indErrAcc.equals("")) {
            nivBandeja = 2;

            if (!opcion.trim().equals("M") || modelo.getIdMar().equals("")) {
                indErrParm = "error";
            } else {
                getDatosMarca();

                varReturnProcess(1);
                
                accion = "Modificar";
                
                getDatosModelo();
                formURL = baseURL + "modelos/actualizarModelo";
            }
        }

        return "adicionar";
    }

    private void getDatosModelo() {
        helper conex = null;
        ResultSet tabla = null;

        try {
            conex = new helper();
            indError = conex.getErrorSQL();

            if (!indError.equals("")) {
                errores.add(indError);
            } else {
                tabla = conex.executeDataSet("CALL usp_getDatosModelo(?,?)",
                        new Object[]{modelo.getIdMar(), modelo.getIdModMar()});
                indError = conex.getErrorSQL();

                if (!indError.equals("")) {
                    errores.add(indError);
                } else {
                    while (tabla.next()) {
                        modelo.setIdModMar(tabla.getString("idModMar"));
                        modelo.setDesModMar(tabla.getString("desModMar"));
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

    private void getDatosMarca() {
        helper conex = null;
        ResultSet tabla = null;

        try {
            conex = new helper();
            indError = conex.getErrorSQL();

            if (!indError.equals("")) {
                errores.add(indError);
            } else {
                tabla = conex.executeDataSet("CALL usp_getDatosMarca(?)",
                        new Object[]{modelo.getIdMar()});
                indError = conex.getErrorSQL();

                if (!indError.equals("")) {
                    errores.add(indError);
                } else {
                    while (tabla.next()) {
                        modelo.setDesMar(tabla.getString("desMar"));
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

    public String grabar() {
        idAccion = 5;
        verifAccionTipoUsuario();

        if (indErrAcc.equals("")) {
            modelo.setIdModMar(modelo.getIdModMar().trim());
            modelo.setDesModMar(modelo.getDesModMar().trim());

            if (modelo.getIdModMar().equals("")) {
                indError += "error";
                errores.add("Ingrese el código del modelo");
            }

            if (modelo.getDesModMar().equals("")) {
                indError += "error";
                errores.add("Ingrese el nombre del modelo");
            }

            if (indError.equals("")) {
                helper conex = null;
                ResultSet tabla = null;

                try {
                    conex = new helper();
                    indError += conex.getErrorSQL();

                    if (!indError.equals("")) {
                        errores.add(indError);
                    } else {
                        tabla = conex.executeDataSet("CALL usp_verifExistModelo(?,?)",
                                new Object[]{modelo.getIdMar(), modelo.getIdModMar()});

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
                                errores.add("Ya existe un modelo con el código ingresado");
                            } else {
                                indError += conex.executeNonQuery("CALL usp_insModelo(?,?,?)",
                                        new Object[]{modelo.getIdMar(), modelo.getIdModMar(), modelo.getDesModMar()});

                                if (!indError.equals("")) {
                                    errores.add(indError);
                                }
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
            modelo.setIdModMar(modelo.getIdModMar().trim());
            modelo.setDesModMar(modelo.getDesModMar().trim());

            if (modelo.getDesModMar().equals("")) {
                indError += "error";
                errores.add("Ingrese el nombre del modelo");
            }

            if (indError.equals("")) {
                helper conex = null;

                try {
                    conex = new helper();
                    indError = conex.getErrorSQL();

                    if (!indError.equals("")) {
                        errores.add(indError);
                    } else {
                        indError = conex.executeNonQuery("CALL usp_updModelo(?,?,?)",
                                new Object[]{modelo.getIdMar(), modelo.getIdModMar(), modelo.getDesModMar()});

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
                helper conex = null;
                ResultSet tabla = null;

                try {
                    conex = new helper();
                    indError = conex.getErrorSQL().trim();

                    if (!indError.equals("")) {
                        errores.add(indError);
                    } else {
                        tabla = conex.executeDataSet("CALL usp_verifDependModelo(?,?)",
                                new Object[]{modelo.getIdMar(), modelo.getIdModMar()});
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
                                indError = conex.executeNonQuery("CALL usp_dltModelo(?,?)",
                                        new Object[]{modelo.getIdMar(), modelo.getIdModMar()});

                                indError = indError.trim();
                                if (indError.trim().equals("")) {
                                    errores.add(indError);
                                }
                            } else /* si tiene dependencias */ {
                                indError = "error";
                                errores.add("Existen registros dependientes del modelo");
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

    /**
     * @return the modelo
     */
    public Modelos getModelo() {
        return modelo;
    }

    /**
     * @param modelo the modelo to set
     */
    public void setModelo(Modelos modelo) {
        this.modelo = modelo;
    }

    /**
     * @return the listModelos
     */
    public ArrayList<Modelos> getListModelos() {
        return listModelos;
    }
}
