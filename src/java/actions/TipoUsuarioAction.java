/*
 * Action: Tipo de Usuario
 * Creado por: Angelo Ccoicca
 * Fecha de creación: 10-04-2014
 * Modificado por                   Fecha de Modificación
 * - 
 * -
 */
package actions;

import com.opensymphony.xwork2.ModelDriven;
import conexion.helper;
import entities.Modulos;
import entities.Opciones;
import entities.TipoUsuario;
import java.sql.ResultSet;
import java.util.ArrayList;

public class TipoUsuarioAction extends MasterAction implements ModelDriven<TipoUsuario> {

    private TipoUsuario modelo = new TipoUsuario();
    private ArrayList<TipoUsuario> listTipoUsuario = new ArrayList<TipoUsuario>();

    @Override
    public TipoUsuario getModel() {
        tituloOpc = "Tipos de Usuario";
        idClaseAccion = 14;

        return modelo;
    }
    
    public String vrfSeleccion() {
        idAccion = 1;
        verifAccionTipoUsuario();

        if (indErrAcc.equals("")) {
            if (modelo.getIdTipUsu().trim().equals("") || modelo.getIdTipUsu().trim().equals("0")) {
                indError = "error";
                errores.add("No ha seleccionado ningun registro");
            }
        }

        return "vrfSeleccion";
    }

    private void cantTipoUsuarioIndex() {
        helper conex = null;
        ResultSet tabla = null;

        try {
            conex = new helper();
            indError = conex.getErrorSQL();

            if (!indError.equals("")) {
                errores.add(indError);
            } else {
                tabla = conex.executeDataSet("CALL usp_cantTipoUsuarioIndex()", new Object[]{});

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

    private void listTipoUsuarioIndex() {
        helper conex = null;
        ResultSet tabla = null;

        try {
            conex = new helper();
            indError = conex.getErrorSQL();

            if (!indError.equals("")) {
                errores.add(indError);
            } else {
                tabla = conex.executeDataSet("CALL usp_listTipoUsuarioIndex(?,?)",
                        new Object[]{getCurPag() * getRegPag(), getRegPag()});

                indError = conex.getErrorSQL();

                if (!indError.equals("")) {
                    errores.add(indError);
                } else {
                    TipoUsuario obj;
                    while (tabla.next()) {
                        obj = new TipoUsuario();
                        obj.setIdTipUsu(tabla.getString("idTipUsu"));
                        obj.setDesTipUsu(tabla.getString("desTipUsu"));
                        listTipoUsuario.add(obj);
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
            nivBandeja = 1;
            urlPaginacion = "tipoUsuario/TipoUsuario";

            varReturnProcess(0);
            if (!listVarReturn.isEmpty()) {
                curPagVis = Integer.parseInt(listVarReturn.get(0).toString().trim());
            }

            cantTipoUsuarioIndex();
            verifPag();
            listTipoUsuarioIndex();
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
                    formURL = baseURL + "tipoUsuario/grabarTipoUsuario";
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
                    getDatosTipoUsuario();
                    formURL = baseURL + "tipoUsuario/actualizarTipoUsuario";
                }
            }
        }

        return "adicionar";
    }

    public void getDatosTipoUsuario() {
        helper conex = null;
        ResultSet tabla = null;

        try {
            conex = new helper();
            indError = conex.getErrorSQL();

            if (!indError.equals("")) {
                errores.add(indError);
            } else {
                tabla = conex.executeDataSet("CALL usp_getDatosTipoUsuario(?)",
                        new Object[]{modelo.getIdTipUsu()});
                indError = conex.getErrorSQL();

                if (!indError.equals("")) {
                    errores.add(indError);
                } else {
                    while (tabla.next()) {
                        modelo.setIdTipUsu(tabla.getString("idTipUsu"));
                        modelo.setDesTipUsu(tabla.getString("desTipUsu"));
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
            modelo.setDesTipUsu(modelo.getDesTipUsu().trim());

            if (indError.equals("")) {
                helper conex = null;

                try {
                    conex = new helper();
                    indError = conex.getErrorSQL();

                    if (!indError.equals("")) {
                        errores.add(indError);
                    } else {
                        indError = conex.executeNonQuery("CALL usp_insTipoUsuario(?)",
                                new Object[]{modelo.getDesTipUsu()});

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
            modelo.setDesTipUsu(modelo.getDesTipUsu().trim());

            if (indError.equals("")) {
                helper conex = null;

                try {
                    conex = new helper();
                    indError = conex.getErrorSQL();

                    if (!indError.equals("")) {
                        errores.add(indError);
                    } else {
                        indError = conex.executeNonQuery("CALL usp_updTipoUsuario(?,?)",
                                new Object[]{Integer.parseInt(modelo.getIdTipUsu()), modelo.getDesTipUsu()});

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
                        tabla = conex.executeDataSet("CALL usp_verifDependTipoUsuario(?)",
                                new Object[]{Integer.parseInt(modelo.getIdTipUsu())});
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
                                indError = conex.executeNonQuery("CALL usp_dltTipoUsuario(?)",
                                        new Object[]{Integer.parseInt(modelo.getIdTipUsu())});

                                indError = indError.trim();
                                if (indError.trim().equals("")) {
                                    errores.add(indError);
                                }
                            } else /* si tiene dependencias */ {
                                indError = "error";
                                errores.add("Existen registros dependientes del tipo de usuario");
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
    public TipoUsuario getModelo() {
        return modelo;
    }

    /**
     * @param modelo the modelo to set
     */
    public void setModelo(TipoUsuario modelo) {
        this.modelo = modelo;
    }

    /**
     * @return the listTipoUsuario
     */
    public ArrayList<TipoUsuario> getListTipoUsuario() {
        return listTipoUsuario;
    }
}
