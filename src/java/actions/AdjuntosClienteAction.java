/*
 * Action: Colores de exterior
 * Creado por: Angelo Ccoicca
 * Fecha de creación: 19-05-2014
 * Modificado por                   Fecha de Modificación
 * - 
 * -
 */

package actions;

import com.opensymphony.xwork2.ModelDriven;
import conexion.helper;
import entities.AdjuntosCliente;
import java.sql.ResultSet;
import java.util.ArrayList;

public class AdjuntosClienteAction extends MasterAction implements ModelDriven<AdjuntosCliente> {
    private AdjuntosCliente modelo = new AdjuntosCliente();
    private ArrayList<AdjuntosCliente> listAdjuntosCliente = new ArrayList<AdjuntosCliente>();

    @Override
    public AdjuntosCliente getModel() {
        tituloOpc = "Adjuntos al cliente";
        idClaseAccion = 36;
        
        return modelo;
    }
    
    public String vrfSeleccion() {
        idAccion = 1;
        verifAccionTipoUsuario();

        if (indErrAcc.equals("")) {
            if (modelo.getIdAdjCli().trim().equals("")) {
                indError = "error";
                errores.add("No ha seleccionado ningun registro");
            }
        }

        return "vrfSeleccion";
    }
    
    public void cantAdjuntosClienteIndex() {
        helper conex = null;
        ResultSet tabla = null;
        
        try {
            conex = new helper();
            indError = conex.getErrorSQL();
            
            if(!indError.equals("")) {
                errores.add(indError);
            } else {
                tabla = conex.executeDataSet("CALL usp_cantAdjuntosClienteIndex(?)", 
                        new Object[]{ modelo.getIdCli() });
                indError = conex.getErrorSQL();
                
                if(!indError.equals("")) {
                    errores.add(indError);
                } else {
                    while(tabla.next()) {
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
    
    public void listAdjuntosClienteIndex() {
        helper conex = null;
        ResultSet tabla = null;
        
        try {
            conex = new helper();
            indError = conex.getErrorSQL();
            
            if(!indError.equals("")) {
                errores.add(indError);
            } else {
                tabla = conex.executeDataSet("CALL usp_listAdjuntosClienteIndex(?,?,?)", 
                        new Object[]{ modelo.getIdCli(), getCurPag()*regPag, regPag });
                indError = conex.getErrorSQL();
                
                if(!indError.equals("")) {
                    errores.add(indError);
                } else {
                    AdjuntosCliente obj;
                    while(tabla.next()) {
                        obj = new AdjuntosCliente();
                        obj.setIdAdjCli(tabla.getString("idAdjCli"));
                        obj.setDesTipAdj(tabla.getString("desTipAdj"));
                        obj.setDesAdjCli(tabla.getString("desAdjCli"));
                        listAdjuntosCliente.add(obj);
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
            nivBandeja = 2;
            urlPaginacion = "adjuntosCliente/AdjuntoCliente";
            
            varReturnProcess(0);
            if (!listVarReturn.isEmpty()) {
                curPagVis = Integer.parseInt(listVarReturn.get(0).toString().trim());
                modelo.setIdCli(listVarReturn.get(1).toString());
            }
            
            if (modelo.getIdCli().equals("")) {
                indErrParm = "error";
            } else {
                getDatosCliente();

                cantAdjuntosClienteIndex();
                verifPag();
                listAdjuntosClienteIndex();
            }
        }
        
        return SUCCESS;
    }
    
    public String adicionar() {
        idAccion = 3;
        verifAccionTipoUsuario();

        if (indErrAcc.equals("")) {
            nivBandeja = 2;

            if (!opcion.trim().equals("A")) {
                indErrParm = "error";
            } else {
                varReturnProcess(1);
                
                accion = "Adicionar";

                getDatosCliente();
                modelo.setIdAdjCli("");
                formURL = baseURL + "adjuntosCliente/grabarAdjuntoCliente";
            }
        }

        return "adicionar";
    }

    public String modificar() {
        idAccion = 4;
        verifAccionTipoUsuario();

        if (indErrAcc.equals("")) {
            nivBandeja = 2;

            if (!opcion.trim().equals("M")) {
                indErrParm = "error";
            } else {
                varReturnProcess(1);

                accion = "Modificar";
                
                getDatosCliente();
                getDatosAdjuntoCliente();
                formURL = baseURL + "adjuntosCliente/actualizarAdjuntoCliente";
            }
        }

        return "adicionar";
    }

    public void getDatosAdjuntoCliente() {
        helper conex = null;
        ResultSet tabla = null;

        try {
            conex = new helper();
            indError += conex.getErrorSQL();

            if (!indError.equals("")) {
                errores.add(indError);
            } else {
                tabla = conex.executeDataSet("CALL usp_getDatosAdjuntoCliente(?,?)",
                        new Object[]{ modelo.getIdCli(),modelo.getIdAdjCli() });
                indError += conex.getErrorSQL();

                if (!indError.equals("")) {
                    errores.add(indError);
                } else {
                    while (tabla.next()) {
                        modelo.setIdAdjCli(tabla.getString("idAdjCli"));
                        modelo.setCodTipAdj(tabla.getString("codTipAdj"));
                        modelo.setDesApeAdj(tabla.getString("desApeAdj"));
                        modelo.setDesNomAdj(tabla.getString("desNomAdj"));
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
    
    private void getDatosCliente() {
        helper conex = null;
        ResultSet tabla = null;
        
        try {
            conex = new helper();
            indError += conex.getErrorSQL();
            
            if(!indError.equals("")) {
                errores.add(indError);
            } else {
                tabla = conex.executeDataSet("CALL usp_getDatosCliente(?)", 
                        new Object[]{ modelo.getIdCli() });
                indError = conex.getErrorSQL();
                
                if(!indError.equals("")) {
                    errores.add(indError);
                } else {
                    while(tabla.next()) {
                        modelo.setDesCli(tabla.getString("desCli"));
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
            if (modelo.getIdAdjCli().equals("")) {
                indError += "error";
                errores.add("Ingrese el número de documento");
            }

            if (modelo.getCodTipAdj().equals("")) {
                indError += "error";
                errores.add("Seleccione el tipo de adjunto");
            }
            
            if (modelo.getDesApeAdj().equals("")) {
                indError += "error";
                errores.add("Ingrese los apellidos del adjunto");
            }
            
            if (modelo.getDesNomAdj().equals("")) {
                indError += "error";
                errores.add("Ingrese los nombres del adjunto");
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
                        indError = conex.executeNonQuery("CALL usp_insAdjuntoCliente(?,?,?,?,?)",
                                new Object[]{ modelo.getIdCli(),modelo.getIdAdjCli(),modelo.getCodTipAdj(),
                                    modelo.getDesApeAdj(),modelo.getDesNomAdj() });

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

        return "grabar";
    }

    public String actualizar() {
        idAccion = 6;
        verifAccionTipoUsuario();

        if (indErrAcc.equals("")) {
            if (modelo.getIdAdjCli().equals("")) {
                indError += "error";
                errores.add("Ingrese el número de documento");
            }

            if (modelo.getCodTipAdj().equals("")) {
                indError += "error";
                errores.add("Seleccione el tipo de adjunto");
            }
            
            if (modelo.getDesApeAdj().equals("")) {
                indError += "error";
                errores.add("Ingrese los apellidos del adjunto");
            }
            
            if (modelo.getDesNomAdj().equals("")) {
                indError += "error";
                errores.add("Ingrese los nombres del adjunto");
            }

            if (indError.equals("")) {
                helper conex = null;

                try {
                    conex = new helper();
                    indError += conex.getErrorSQL();

                    if (!indError.equals("")) {
                        errores.add(indError);
                    } else {
                        indError += conex.executeNonQuery("CALL usp_updAdjuntoCliente(?,?,?,?,?)",
                                new Object[]{ modelo.getIdCli(),modelo.getIdAdjCli(),modelo.getCodTipAdj(),
                                    modelo.getDesApeAdj(),modelo.getDesNomAdj() });

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
                        indError += conex.executeNonQuery("CALL usp_dltAdjuntoCliente(?,?)",
                                new Object[]{ modelo.getIdCli(),modelo.getIdAdjCli() });

                        indError = indError.trim();
                        if (indError.trim().equals("")) {
                            errores.add(indError);
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
    
    public AdjuntosCliente getModelo() {
        return modelo;
    }

    public void setModelo(AdjuntosCliente modelo) {
        this.modelo = modelo;
    }

    public ArrayList<AdjuntosCliente> getListAdjuntosCliente() {
        return listAdjuntosCliente;
    }

    public void setListAdjuntosCliente(ArrayList<AdjuntosCliente> listAdjuntosCliente) {
        this.listAdjuntosCliente = listAdjuntosCliente;
    }
}
