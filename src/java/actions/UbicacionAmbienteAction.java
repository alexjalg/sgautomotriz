/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package actions;

import com.opensymphony.xwork2.ModelDriven;
import conexion.helper;
import entities.UbicacionAmbiente;
import java.sql.ResultSet;
import java.util.ArrayList;

public class UbicacionAmbienteAction extends MasterAction implements ModelDriven<UbicacionAmbiente> {
    
    private UbicacionAmbiente modelo = new UbicacionAmbiente();
    private ArrayList<UbicacionAmbiente> listUbiAmb = new ArrayList<UbicacionAmbiente>();
    
    @Override
    public UbicacionAmbiente getModel() {
        tituloOpc = "Ubicaciones por Ambientes";
        idClaseAccion = 34;
        return modelo;
    }
    
    public String vrfSeleccion() {
        idAccion = 1;
        
        verifAccionTipoUsuario();
        
        if (indErrAcc.equals("")) {
            if (modelo.getIdUbiAmb() == 99) {
                indError = "error";
                errores.add("No ha seleccionado ningun registro");
            }
        }
        
        return "vrfSeleccion";
    }
    
    private void cantUbicacionAmbienteIndex() {
        helper conex = null;
        ResultSet tabla = null;
        
        try {
            conex = new helper();
            indError = conex.getErrorSQL().trim();
            
            if (!indError.trim().equals("")) {
                errores.add(indError);
            } else {
                tabla = conex.executeDataSet("CALL usp_cantUbicacionAmbienteIndex(?)",
                        new Object[]{modelo.getIdAmbUbi()});
                
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
    
    private void listUbicacionAmbienteIndex() {
        helper conex = null;
        ResultSet tabla = null;
        
        try {
            conex = new helper();
            indError = conex.getErrorSQL();
            
            if (!indError.equals("")) {
                errores.add(indError);
            } else {
                tabla = conex.executeDataSet("CALL usp_listUbicacionAmbienteIndex(?,?,?)",
                        new Object[]{modelo.getIdAmbUbi(), (getCurPag()) * getRegPag(), getRegPag()});
                
                indError = conex.getErrorSQL();
                
                if (!indError.equals("")) {
                    errores.add(indError);
                } else {
                    UbicacionAmbiente obj;
                    while (tabla.next()) {
                        obj = new UbicacionAmbiente();
                        obj.setIdUbiAmb(tabla.getInt("idUbiAmb"));
                        obj.setDesUbiAmb(tabla.getString("desUbiAmb"));
                        listUbiAmb.add(obj);
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
    
    private void getDatosAmbientesUbicacion() {
        helper conex = null;
        ResultSet tabla = null;
        
        try {
            conex = new helper();
            indError = conex.getErrorSQL();
            
            if (!indError.equals("")) {
                errores.add(indError);
            } else {
                tabla = conex.executeDataSet("CALL usp_getDatosAmbienteUbicacion(?)",
                        new Object[]{modelo.getIdAmbUbi()});
                
                indError = conex.getErrorSQL();
                
                if (!indError.equals("")) {
                    errores.add(indError);
                } else {
                    while (tabla.next()) {
                        modelo.setDesAmbUbi(tabla.getString("desAmbUbi"));
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
    
    private void getDatosUbicacionPorAmbiente() {
        helper conex = null;
        ResultSet tabla = null;
        
        try {
            conex = new helper();
            indError = conex.getErrorSQL();
            
            if (!indError.equals("")) {
                errores.add(indError);
            } else {
                tabla = conex.executeDataSet("CALL usp_getDatosUbicacionAmbiente(?,?)",
                        new Object[]{modelo.getIdAmbUbi(), modelo.getIdUbiAmb()});
                
                indError = conex.getErrorSQL();
                
                if (!indError.equals("")) {
                    errores.add(indError);
                } else {
                    while (tabla.next()) {
                        modelo.setDesUbiAmb(tabla.getString("desUbiAmb"));
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
                    modelo.setIdAmbUbi(Integer.parseInt(listVarReturn.get(1).toString().trim()));
                }
                
                if (modelo.getIdAmbUbi() == 99) {
                    indErrParm = "error";
                } else {
                    urlPaginacion = "ubicacionAmbiente/UbicacionAmbiente";
                    
                    getDatosAmbientesUbicacion();
                    cantUbicacionAmbienteIndex();
                    verifPag();
                    listUbicacionAmbienteIndex();
                }
            
        }
        
        return SUCCESS;
    }
    
    public String adicionar() {
        idAccion = 3;
        verifAccionTipoUsuario();
        
        if (indErrAcc.equals("")) {
            nivBandeja = 2;
            
            if ((!opcion.trim().equals("A") && !opcion.trim().equals("M")) || modelo.getIdAmbUbi() == 99) {
                indErrParm = "error";
            } else {
                varReturnProcess(1);
                
                getDatosAmbientesUbicacion();
                
                if (opcion.equals("A")) {
                    formURL = baseURL + "ubicacionAmbiente/grabarUbicacionAmbiente";
                }
            }
        }
        
        return "adicionar";
    }
    
    public String modificar() {
        idAccion = 4;
        verifAccionTipoUsuario();
        
        if (indErrAcc.equals("")) {
            nivBandeja = 2;
            
            if ((!opcion.trim().equals("A") && !opcion.trim().equals("M")) || modelo.getIdAmbUbi() == 99) {
                indErrParm = "error";
            } else {
                getDatosAmbientesUbicacion();
                varReturnProcess(1);
                
                if (opcion.equals("M")) {
                    if (modelo.getIdAmbUbi() == 0) {
                        indErrParm = "error";
                    } else {
                        getDatosUbicacionPorAmbiente();
                        formURL = baseURL + "ubicacionAmbiente/actualizarUbicacionAmbiente";
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
            modelo.setDesUbiAmb(modelo.getDesUbiAmb().trim());
            
            if (modelo.getDesUbiAmb().equals("")) {
                indError += "error";
                errores.add("Debe ingresar una ubicación");
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
                        indError = conex.executeNonQuery("CALL usp_insUbicacionAmbiente(?,?)",
                                new Object[]{modelo.getIdAmbUbi(), modelo.getDesUbiAmb()});
                        
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
            modelo.setIdUbiAmb(modelo.getIdUbiAmb());
            modelo.setDesUbiAmb(modelo.getDesUbiAmb().trim());
            
            if (modelo.getDesUbiAmb().equals("")) {
                indError += "error";
                errores.add("Ingrese la ubicación");
            }

            if (indError.equals("")) {
                helper conex = null;
                
                try {
                    conex = new helper();
                    indError = conex.getErrorSQL();
                    
                    if (!indError.equals("")) {
                        errores.add(indError);
                    } else {
                        indError = conex.executeNonQuery("CALL usp_updUbicacionAmbiente(?,?,?)",
                                new Object[]{modelo.getIdAmbUbi(), modelo.getIdUbiAmb(), modelo.getDesUbiAmb()});
                                
                        
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
                        tabla = conex.executeDataSet("CALL usp_verifDependUbicacionAmbiente(?,?)",
                                new Object[]{modelo.getIdAmbUbi(), modelo.getIdUbiAmb()});
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
                                indError = conex.executeNonQuery("CALL usp_dltUbicacionAmbiente(?,?)",
                                        new Object[]{modelo.getIdAmbUbi(), modelo.getIdUbiAmb()});
                                
                                indError = indError.trim();
                                if (indError.trim().equals("")) {
                                    errores.add(indError);
                                }
                            } else /* si tiene dependencias */ {
                                indError = "error";
                                errores.add("Existen registros dependientes de la ubicación");
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
    public UbicacionAmbiente getModelo() {
        return modelo;
    }

    /**
     * @param modelo the modelo to set
     */
    public void setModelo(UbicacionAmbiente modelo) {
        this.modelo = modelo;
    }

    /**
     * @return the listUbiAmb
     */
    public ArrayList<UbicacionAmbiente> getListUbiAmb() {
        return listUbiAmb;
    }

    /**
     * @param listUbiAmb the listUbiAmb to set
     */
    public void setListUbiAmb(ArrayList<UbicacionAmbiente> listUbiAmb) {
        this.listUbiAmb = listUbiAmb;
    }
}
