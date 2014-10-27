/*
 * Action: Opciones
 * Creado por: Angelo Ccoicca
 * Fecha de creación: 09-04-2014
 * Modificado por                   Fecha de Modificación
 * - 
 * -
 */
package actions;

import com.opensymphony.xwork2.ModelDriven;
import conexion.helper;
import entities.Opciones;
import java.sql.ResultSet;
import java.util.ArrayList;

public class OpcionesAction extends MasterAction implements ModelDriven<Opciones> {

    private Opciones modelo = new Opciones();
    private ArrayList<Opciones> listOpciones = new ArrayList<Opciones>();

    @Override
    public Opciones getModel() {
        tituloOpc = "Opciones";
        idClaseAccion = 10;

        return modelo;
    }
    
    public String vrfSeleccion() {
        idAccion = 1;
        verifAccionTipoUsuario();

        if (indErrAcc.equals("")) {
            if (modelo.getIdOpc().trim().equals("") || modelo.getIdOpc().trim().equals("0")) {
                indError = "error";
                errores.add("No ha seleccionado ningun registro");
            }
        }

        return "vrfSeleccion";
    }

    private void cantOpcionesIndex() {
        helper conex = null;
        ResultSet tabla = null;

        try {
            conex = new helper();
            indError = conex.getErrorSQL();

            if (!indError.equals("")) {
                errores.add(indError);
            } else {
                tabla = conex.executeDataSet("CALL usp_cantOpcionesIndex(?)",
                        new Object[]{modelo.getDesOpc_f()});

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

    private void listOpcionesIndex() {
        helper conex = null;
        ResultSet tabla = null;

        try {
            conex = new helper();
            indError = conex.getErrorSQL();

            if (!indError.equals("")) {
                errores.add(indError);
            } else {
                tabla = conex.executeDataSet("CALL usp_listOpcionesIndex(?,?,?)",
                        new Object[]{modelo.getDesOpc_f(), getCurPag() * getRegPag(), getRegPag()});

                indError = conex.getErrorSQL();

                if (!indError.equals("")) {
                    errores.add(indError);
                } else {
                    Opciones obj;
                    while (tabla.next()) {
                        obj = new Opciones();
                        obj.setIdOpc(tabla.getString("idOpc"));
                        obj.setDesOpc(tabla.getString("desOpc"));
                        obj.setDesUrlOpc(tabla.getString("desUrlOpc"));
                        listOpciones.add(obj);
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
            urlPaginacion = "opciones/Opcion";

            modelo.setDesOpc_f(modelo.getDesOpc_f().trim());

            varReturnProcess(0);
            if (!listVarReturn.isEmpty()) {
                curPagVis = Integer.parseInt(listVarReturn.get(0).toString().trim());
                modelo.setDesOpc_f(listVarReturn.get(1).toString().trim());
            }

            cantOpcionesIndex();
            verifPag();
            listOpcionesIndex();
        }

        return SUCCESS;
    }

    public String adicionar() {
        idAccion = 3;
        verifAccionTipoUsuario();

        if (indErrAcc.equals("")) {
            if(!opcion.trim().equals("A")) {
                indErrParm = "error";
            } else {
                varReturnProcess(1);
                
                accion = "Adicionar";
                
                formURL = baseURL + "opciones/grabarOpcion";
            }
        }

        return "adicionar";
    }
    
    public String modificar() {
        idAccion = 4;
        verifAccionTipoUsuario();

        if (indErrAcc.equals("")) {
            if (!opcion.trim().equals("M")) {
                indErrParm = "error";
            } else {
                varReturnProcess(1);

                accion = "Modificar";
                
                getDatosOpcion();
                formURL = baseURL + "opciones/actualizarOpcion";
            }
        }

        return "adicionar";
    }

    public void getDatosOpcion() {
        helper conex = null;
        ResultSet tabla = null;

        try {
            conex = new helper();
            indError = conex.getErrorSQL();

            if (!indError.equals("")) {
                errores.add(indError);
            } else {
                tabla = conex.executeDataSet("CALL usp_getDatosOpcion(?)",
                        new Object[]{modelo.getIdOpc()});
                indError = conex.getErrorSQL();

                if (!indError.equals("")) {
                    errores.add(indError);
                } else {
                    while (tabla.next()) {
                        modelo.setIdOpc(tabla.getString("idOpc"));
                        modelo.setDesOpc(tabla.getString("desOpc"));
                        modelo.setDesUrlOpc(tabla.getString("desUrlOpc"));
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
            modelo.setDesModu(modelo.getDesModu().trim());
            modelo.setDesUrlOpc(modelo.getDesUrlOpc().trim());

            if (indError.equals("")) {
                helper conex = null;

                try {
                    conex = new helper();
                    indError = conex.getErrorSQL();

                    if (!indError.equals("")) {
                        errores.add(indError);
                    } else {
                        indError = conex.executeNonQuery("CALL usp_insOpcion(?,?)",
                                new Object[]{modelo.getDesOpc(), modelo.getDesUrlOpc()});

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
            modelo.setDesModu(modelo.getDesModu().trim());
            modelo.setDesUrlOpc(modelo.getDesUrlOpc());

            if (indError.equals("")) {
                helper conex = null;

                try {
                    conex = new helper();
                    indError = conex.getErrorSQL();

                    if (!indError.equals("")) {
                        errores.add(indError);
                    } else {
                        indError = conex.executeNonQuery("CALL usp_updOpcion(?,?,?)",
                                new Object[]{Integer.parseInt(modelo.getIdOpc()), modelo.getDesOpc(),
                            modelo.getDesUrlOpc()});

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
                        tabla = conex.executeDataSet("CALL usp_verifDependOpcion(?)",
                                new Object[]{Integer.parseInt(modelo.getIdOpc())});
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
                                indError = conex.executeNonQuery("CALL usp_dltOpcion(?)",
                                        new Object[]{Integer.parseInt(modelo.getIdOpc())});

                                indError = indError.trim();
                                if (indError.trim().equals("")) {
                                    errores.add(indError);
                                }
                            } else /* si tiene dependencias */ {
                                indError = "error";
                                errores.add("Existen registros dependientes de la opción");
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
    public Opciones getModelo() {
        return modelo;
    }

    /**
     * @param modelo the modelo to set
     */
    public void setModelo(Opciones modelo) {
        this.modelo = modelo;
    }

    /**
     * @return the listOpciones
     */
    public ArrayList<Opciones> getListOpciones() {
        return listOpciones;
    }
}
