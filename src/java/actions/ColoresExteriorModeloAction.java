/*
 * Action: Colores modelo
 * Creado por: Angelo Ccoicca
 * Fecha de creación: 20-05-2014
 * Modificado por                   Fecha de Modificación
 * - 
 * -
 */
package actions;

import com.opensymphony.xwork2.ModelDriven;
import conexion.helper;
import entities.ColoresExterior;
import entities.ColoresExteriorModelo;
import java.sql.ResultSet;
import java.util.ArrayList;

public class ColoresExteriorModeloAction extends MasterAction implements ModelDriven<ColoresExteriorModelo> {

    private ColoresExteriorModelo modelo = new ColoresExteriorModelo();
    private ArrayList<ColoresExteriorModelo> listColoresExtModelo = new ArrayList<ColoresExteriorModelo>();
    private ColoresExterior modeloColorExterior = new ColoresExterior();
    private ArrayList<ColoresExterior> listColoresExterior = new ArrayList<ColoresExterior>();

    @Override
    public ColoresExteriorModelo getModel() {
        tituloOpc = "Colores de exterior por modelo";
        idClaseAccion = 2;

        return modelo;
    }

    public String vrfSeleccion() {
        idAccion = 1;

        verifAccionTipoUsuario();

        if (indErrAcc.equals("")) {
            if (modelo.getIdColExt().equals("")) {
                indError = "error";
                errores.add("No ha seleccionado ningun registro");
            }
        }

        return "vrfSeleccion";
    }

    private void cantColoresExtModeloIndex() {
        helper conex = null;
        ResultSet tabla = null;

        try {
            conex = new helper();
            indError += conex.getErrorSQL().trim();

            if (!indError.trim().equals("")) {
                errores.add(indError);
            } else {
                tabla = conex.executeDataSet("CALL usp_cantColoresExtModeloIndex(?,?)",
                        new Object[]{modelo.getIdMar(), modelo.getIdModMar()});

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

    private void listColoresExtModeloIndex() {
        helper conex = null;
        ResultSet tabla = null;

        try {
            conex = new helper();
            indError += conex.getErrorSQL();

            if (!indError.equals("")) {
                errores.add(indError);
            } else {
                tabla = conex.executeDataSet("CALL usp_listColoresExtModeloIndex(?,?,?,?)",
                        new Object[]{modelo.getIdMar(), modelo.getIdModMar(),
                    (getCurPag()) * getRegPag(), getRegPag()});

                indError += conex.getErrorSQL();

                if (!indError.equals("")) {
                    errores.add(indError);
                } else {
                    ColoresExteriorModelo obj;
                    while (tabla.next()) {
                        obj = new ColoresExteriorModelo();
                        obj.setIdColExt(tabla.getString("idColExt"));
                        obj.setDesColExt(tabla.getString("desColExt"));
                        listColoresExtModelo.add(obj);
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
            nivBandeja = 3;

            varReturnProcess(0);
            if (!listVarReturn.isEmpty()) {
                curPagVis = Integer.parseInt(listVarReturn.get(0).toString().trim());
                modelo.setIdMar(listVarReturn.get(1).toString().trim());
                modelo.setIdModMar(listVarReturn.get(2).toString().trim());
            }

            if (modelo.getIdMar().trim().equals("") || modelo.getIdModMar().trim().equals("")) //if(false)
            {
                indErrParm = "error";
            } else {
                urlPaginacion = "coloresExtModelo/ColorExtModelo";

                getDatosMarcaModelo();

                cantColoresExtModeloIndex();
                verifPag();
                listColoresExtModeloIndex();
            }
        }

        return SUCCESS;
    }

    private void getDatosMarcaModelo() {
        helper conex = null;
        ResultSet tabla = null;

        try {
            conex = new helper();
            indError += conex.getErrorSQL();

            if (!indError.equals("")) {
                errores.add(indError);
            } else {
                tabla = conex.executeDataSet("CALL usp_getDatosMarca(?)",
                        new Object[]{modelo.getIdMar()});
                indError += conex.getErrorSQL();

                if (!indError.equals("")) {
                    errores.add(indError);
                } else {
                    while (tabla.next()) {
                        modelo.setDesMar(tabla.getString("desMar"));
                    }

                    tabla = null;
                    tabla = conex.executeDataSet("CALL usp_getDatosModelo(?,?)",
                            new Object[]{modelo.getIdMar(), modelo.getIdModMar()});
                    indError += conex.getErrorSQL();

                    if (!indError.equals("")) {
                        errores.add(indError);
                    } else {
                        while (tabla.next()) {
                            modelo.setDesModMar(tabla.getString("desModMar"));
                        }
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

    public String adicionar() {
        idAccion = 3;

        verifAccionTipoUsuario();

        if (indErrAcc.equals("")) {
            nivBandeja = 3;

            if (!opcion.trim().equals("A") || modelo.getIdMar().trim().equals("")
                    || modelo.getIdModMar().trim().equals("")) {
                indErrParm = "error";
            } else {
                varReturnProcess(1);
                
                accion = "Adicionar";
                
                getDatosMarcaModelo();
                formURL = baseURL + "coloresExtModelo/grabarColorExtModelo";
            }
        }

        return "adicionar";
    }

    private void cantColoresExtIndex() {
        helper conex = null;
        ResultSet tabla = null;

        try {
            conex = new helper();
            indError += conex.getErrorSQL();

            if (!indError.equals("")) {
                errores.add(indError);
            } else {
                tabla = conex.executeDataSet("CALL usp_cantColoresExtIndex(?)",
                        new Object[]{modeloColorExterior.getDesColExt_f()});

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

    private void listColoresExtIndex() {
        helper conex = null;
        ResultSet tabla = null;

        try {
            conex = new helper();
            indError += conex.getErrorSQL();

            if (!indError.equals("")) {
                errores.add(indError);
            } else {
                tabla = conex.executeDataSet("CALL usp_listColoresExtIndex(?,?,?)",
                        new Object[]{modeloColorExterior.getDesColExt_f(), getCurPag() * regPag, regPag});

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

    public String listColoresExterior() {
        idAccion = 4;

        verifAccionTipoUsuario();

        if (indErrAcc.equals("")) {
            regPag = 13;
            urlPaginacion = "coloresExtModelo/listColoresExteriorColorExtModelo";
            divPopUp = "DIVcolores";

            cantColoresExtIndex();
            verifPag();
            listColoresExtIndex();
        }

        return "listColoresExterior";
    }

    public String grabar() {
        idAccion = 5;

        verifAccionTipoUsuario();

        if (indErrAcc.equals("")) {
            if (modelo.getIdColExt().equals("")) {
                indError += "error";
                errores.add("Seleccione un color de exterior");
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
                        tabla = conex.executeDataSet("CALL usp_verifExistColorExtModelo(?,?,?)",
                                new Object[]{modelo.getIdMar(), modelo.getIdModMar(), modelo.getIdColExt()});

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
                                errores.add("El color de exterior seleccionado ya fue registrado para este modelo");
                            } else {
                                indError += conex.executeNonQuery("CALL usp_insColorExtModelo(?,?,?)",
                                        new Object[]{modelo.getIdMar(), modelo.getIdModMar(), modelo.getIdColExt()});

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
                    try {
                        tabla.close();
                        conex.returnConnect();
                    } catch (Exception e) {
                    }
                }
            }
        }

        return "grabar";
    }

    public String eliminar() {
        idAccion = 6;

        verifAccionTipoUsuario();

        if (indErrAcc.equals("")) {
            if (opcion.trim().equals("E")) {
                helper conex = null;

                try {
                    conex = new helper();
                    indError += conex.getErrorSQL().trim();

                    if (!indError.equals("")) {
                        errores.add(indError);
                    } else {
                        indError += conex.executeNonQuery("CALL usp_dltColorExtModelo(?,?,?)",
                                new Object[]{modelo.getIdMar(), modelo.getIdModMar(), modelo.getIdColExt()});

                        indError = indError.trim();
                        if (indError.trim().equals("")) {
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

        return "eliminar";
    }

    /**
     * @return the modelo
     */
    public ColoresExteriorModelo getModelo() {
        return modelo;
    }

    /**
     * @param modelo the modelo to set
     */
    public void setModelo(ColoresExteriorModelo modelo) {
        this.modelo = modelo;
    }

    /**
     * @return the listColoresMod
     */
    public ArrayList<ColoresExteriorModelo> getListColoresExtModelo() {
        return listColoresExtModelo;
    }

    /**
     * @return the listColores
     */
    public ArrayList<ColoresExterior> getListColoresExterior() {
        return listColoresExterior;
    }

    /**
     * @return the color
     */
    public ColoresExterior getModeloColorExterior() {
        return modeloColorExterior;
    }

    /**
     * @param color the color to set
     */
    public void setModeloColorExterior(ColoresExterior modeloColorExterior) {
        this.modeloColorExterior = modeloColorExterior;
    }
}
