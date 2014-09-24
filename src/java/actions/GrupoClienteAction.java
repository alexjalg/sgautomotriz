package actions;

import static com.opensymphony.xwork2.Action.SUCCESS;
import com.opensymphony.xwork2.ModelDriven;
import conexion.helper;
import entities.GrupoCliente;
import java.sql.ResultSet;
import java.util.ArrayList;

public class GrupoClienteAction extends MasterAction implements ModelDriven<GrupoCliente> {

    private GrupoCliente modelo = new GrupoCliente();
    private ArrayList<GrupoCliente> listGrupoCliente = new ArrayList<GrupoCliente>();

    @Override
    public GrupoCliente getModel() {
        tituloOpc = "Grupos de cliente";
        idClaseAccion = 26;
        return getModelo();
    }

    public String vrfSeleccion() {
        idAccion = 1;
        verifAccionTipoUsuario();
        if (indErrAcc.equals("")) {
            if (getModel().getIdGrpCli() == 0) {
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
            urlPaginacion = "grupoCliente/GrupoCliente";
            varReturnProcess(0);
            if (!listVarReturn.isEmpty()) {
                curPagVis = Integer.parseInt(listVarReturn.get(0).toString().trim());
            }
            cantGrupoClienteIndex();
            verifPag();
            listGrupoClienteIndex();
        }
        return SUCCESS;
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
                    formURL = baseURL + "grupoCliente/grabarGrupoCliente";
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
                    if (getModelo().getIdGrpCli() == 0) {
                        indErrParm = "error";
                    } else {
                        getDatosTipoCliente();
                        formURL = baseURL + "grupoCliente/actualizarGrupoCliente";
                    }
                }
            }
        }
        return "modificar";
    }

    public String grabar() {
        idAccion = 5;
        verifAccionTipoUsuario();
        if (indErrAcc.equals("")) {
            getModelo().setDesGrpCli(getModelo().getDesGrpCli().trim());

            if (indError.equals("")) {
                helper conex = null;
                try {
                    conex = new helper();
                    indError = conex.getErrorSQL();
                    if (!indError.equals("")) {
                        errores.add(indError);
                    } else {
                        indError = conex.executeNonQuery("CALL usp_insGrupoCliente(?)",
                                new Object[]{getModelo().getDesGrpCli()});
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
            getModelo().setDesGrpCli(getModelo().getDesGrpCli().trim());

            if (indError.equals("")) {
                helper conex = null;
                try {
                    conex = new helper();
                    indError = conex.getErrorSQL();
                    if (!indError.equals("")) {
                        errores.add(indError);
                    } else {
                        indError = conex.executeNonQuery("CALL usp_updGrupoCliente(?,?)",
                                new Object[]{getModelo().getIdGrpCli(), getModelo().getDesGrpCli()});
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
                indError = conex.getErrorSQL();
                
                if (!indError.equals("")) {
                    errores.add(indError);
                } else {
                    ResultSet tabla = null;
                    try {
                        tabla = conex.executeDataSet("CALL usp_verifDependGrupoCliente(?)", 
                                    new Object[]{ modelo.getIdGrpCli() });
                        indError = conex.getErrorSQL();
                        
                        if(!indError.equals("")) {
                            errores.add(indError);
                        } else {
                            int cant = 0;
                            while(tabla.next()) {
                                cant = tabla.getInt(1);
                            }
                            
                            if (cant == 0) {
                                indError = conex.executeNonQuery("CALL usp_dltGrupoCarroceria(?)",
                                        new Object[]{getModelo().getIdGrpCli()});
                                indError = indError.trim();
                                if (indError.trim().equals("")) {
                                    errores.add(indError);
                                }
                            } else /* si tiene dependencias */ {
                                indError = "error";
                                errores.add("Existen registros dependientes del grupo de cliente");
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

    private void getDatosTipoCliente() {
        helper conex = null;
        ResultSet tabla = null;

        try {
            conex = new helper();
            indError = conex.getErrorSQL();

            if (!indError.equals("")) {
                errores.add(indError);
            } else {
                tabla = conex.executeDataSet("CALL usp_getDatosGrupoCliente(?)",
                        new Object[]{getModelo().getIdGrpCli()});

                indError = conex.getErrorSQL();

                if (!indError.equals("")) {
                    errores.add(indError);
                } else {
                    while (tabla.next()) {
                        getModelo().setDesGrpCli(tabla.getString("desGrpCli"));
                        
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

    private void cantGrupoClienteIndex() {
        helper conex = null;
        ResultSet tabla = null;
        try {
            conex = new helper();
            indError += conex.getErrorSQL();

            if (!indError.equals("")) {
                errores.add(indError);
            } else {
                tabla = conex.executeDataSet("CALL usp_cantGrupoClienteIndex()", new Object[]{});
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

    private void listGrupoClienteIndex() {
        helper conex = null;
        ResultSet tabla = null;
        try {
            conex = new helper();
            indError += conex.getErrorSQL();
            if (!indError.equals("")) {
                errores.add(indError);
            } else {
                tabla = conex.executeDataSet("CALL usp_listGrupoClienteIndex(?,?)",
                        new Object[]{getCurPag() * regPag, regPag});
                indError += conex.getErrorSQL();
                if (!indError.equals("")) {
                    errores.add(indError);
                } else {
                    GrupoCliente obj;
                    while (tabla.next()) {
                        obj = new GrupoCliente();
                        obj.setIdGrpCli(tabla.getInt("idGrpCli"));
                        obj.setDesGrpCli(tabla.getString("desGrpCli"));
                        listGrupoCliente.add(obj);
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

 

    public void setModelo(GrupoCliente model) {
        this.modelo = model;
    }

    /**
     * @return the listGrupoCliente
     */
    public ArrayList<GrupoCliente> getListGrupoCliente() {
        return listGrupoCliente;
    }

    /**
     * @return the modelo
     */
    public GrupoCliente getModelo() {
        return modelo;
    }

}
