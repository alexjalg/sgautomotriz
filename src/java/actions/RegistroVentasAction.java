/*
 * Action: Registro de ventas
 * Creado por: Angelo Ccoicca
 * Fecha de creación: 25-09-2014
 * Modificado por                   Fecha de Modificación
 * - 
 * -
 */
package actions;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ModelDriven;
import conexion.helper;
import entities.Clientes;
import entities.FormaPago;
import entities.RegistroVentas;
import entities.TipoDocumentoVenta;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Map;

public class RegistroVentasAction extends MasterAction implements ModelDriven<RegistroVentas> {
    
    private Map<String, Object> sesion_sga = ActionContext.getContext().getSession();
    private RegistroVentas modelo = new RegistroVentas();
    private ArrayList<RegistroVentas> listRegistroVentas = new ArrayList<RegistroVentas>();
    private ArrayList<TipoDocumentoVenta> listTiposDocumentoVenta = new ArrayList<TipoDocumentoVenta>();
    private ArrayList<FormaPago> listFormasPago = new ArrayList<FormaPago>();
    private Clientes cliente = new Clientes();
    private ArrayList<Clientes> listClientes = new ArrayList<Clientes>();
    
    public RegistroVentas getModel() {
        tituloOpc = "Registros de venta";
        idClaseAccion = 37;
        
        return modelo;
    }
    
    public String vrfSeleccion() {
        idAccion = 1;
        verifAccionTipoUsuario();

        if (indErrAcc.equals("")) {
            if (modelo.getIdNumIntRV().trim().equals("") || modelo.getIdNumIntRV().equals("0")) {
                indError = "error";
                errores.add("No ha seleccionado ningun registro");
            }
        }

        return "vrfSeleccion";
    }
    
    private void cantRegistroVentasIndex() {
        helper conex = null;
        ResultSet tabla = null;

        try {
            conex = new helper();
            indError += conex.getErrorSQL();

            if (!indError.equals("")) {
                errores.add(indError);
            } else {
                tabla = conex.executeDataSet("CALL usp_cantRegistroVentasIndex(?,?,?,?,?,?)",
                        new Object[]{ modelo.getIdNumIntRV_f(),modelo.getIdTipDocVen_f(),modelo.getDesNumDocVen_f(),
                            modelo.getFecEmiDocVen_f(),modelo.getIdCli_f(),modelo.getDesCli_f() });

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
    
    private void listRegistroVentasIndex() {
        helper conex = null;
        ResultSet tabla = null;

        try {
            conex = new helper();
            indError += conex.getErrorSQL();

            if (!indError.equals("")) {
                errores.add(indError);
            } else {
                tabla = conex.executeDataSet("CALL usp_listRegistroVentasIndex(?,?,?,?,?,?,?,?)",
                        new Object[]{ modelo.getIdNumIntRV_f(),modelo.getIdTipDocVen_f(),modelo.getDesNumDocVen_f(),
                            modelo.getFecEmiDocVen_f(),modelo.getIdCli_f(),modelo.getDesCli_f(), 
                            getCurPag()*regPag,regPag });

                indError += conex.getErrorSQL();

                if (!indError.equals("")) {
                    errores.add(indError);
                } else {
                    RegistroVentas obj;
                    while (tabla.next()) {
                        obj = new RegistroVentas();
                        obj.setIdNumIntRV(tabla.getString("idNumIntRV"));
                        obj.setDesTipDocVen(tabla.getString("desTipDocVen"));
                        obj.setDesNumDocVen(tabla.getString("desNumDocVen"));
                        obj.setFecEmiDocVen(tabla.getString("fecEmiDocVen"));
                        obj.setIdCli(tabla.getString("idCli"));
                        obj.setDesCli(tabla.getString("desCli"));
                        listRegistroVentas.add(obj);
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
            urlPaginacion = "registroVentas/RegistroVenta";

            varReturnProcess(0);
            if (!listVarReturn.isEmpty()) {
                curPagVis = Integer.parseInt(listVarReturn.get(0).toString().trim());
                modelo.setIdNumIntRV_f(listVarReturn.get(1).toString().trim());
                modelo.setIdTipDocVen_f(listVarReturn.get(2).toString().trim());
                modelo.setDesNumDocVen_f(listVarReturn.get(3).toString().trim());
                modelo.setFecEmiDocVen_f(listVarReturn.get(4).toString().trim());
                modelo.setIdCli_f(listVarReturn.get(5).toString().trim());
                modelo.setDesCli_f(listVarReturn.get(6).toString().trim());
            }
            
            listTiposDocumentoVenta();
            
            modelo.setFecEmiDocVen_f(getConvertFecha(modelo.getFecEmiDocVen_f(), 2));
            
            cantRegistroVentasIndex();
            verifPag();
            listRegistroVentasIndex();
            
            modelo.setFecEmiDocVen_f(getConvertFecha(modelo.getFecEmiDocVen_f(), 1));
        }

        return SUCCESS;
    }
    
    private void listTiposDocumentoVenta() {
        helper conex = null;
        ResultSet tabla = null;
        
        try {
            conex = new helper();
            indError = conex.getErrorSQL();
            
            if(!indError.equals("")) {
                errores.add(indError);
            } else {
                tabla = conex.executeDataSet("CALL usp_listTipoDocumentoVenta()", new Object[]{});
                indError += conex.getErrorSQL();
                
                if(!indError.equals("")) {
                    errores.add(indError);
                } else {
                    TipoDocumentoVenta obj;
                    while(tabla.next()) {
                        obj = new TipoDocumentoVenta();
                        obj.setIdTipDocVen(tabla.getString("idTipDocVen"));
                        obj.setDesTipDocVen(tabla.getString("desTipDocVen"));
                        listTiposDocumentoVenta.add(obj);
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
                
                modelo.setIdTipDocVen("99");
                //listTiposDocumentoVenta();
                populateForm();

                if (opcion.equals("A")) {
                    modelo.setIdNumIntRV("");
                    formURL = baseURL + "registroVentas/grabarRegistroVenta";
                }
            }
        }

        return "adicionar";
    }
    
    private void getDatosRegistroVenta() {
        helper conex = null;
        ResultSet tabla = null;
        
        try {
            conex = new helper();
            indError += conex.getErrorSQL();
            
            if(!indError.equals("")) {
                errores.add(indError);
            } else {
                tabla = conex.executeDataSet("CALL usp_getDatosRegistroVenta(?)", 
                        new Object[]{ modelo.getIdNumIntRV() });
                
                indError += conex.getErrorSQL();
                
                if(!indError.equals("")) {
                    errores.add(indError);
                } else {
                    while(tabla.next()) {
                        modelo.setIdNumIntRV(tabla.getString("idNumIntRV"));
                        modelo.setIdTipDocVen(tabla.getString("idTipDocVen"));
                        modelo.setDesTipDocVen(tabla.getString("desTipDocVen"));
                        modelo.setDesNumDocVen(tabla.getString("desNumDocVen"));
                        modelo.setFecEmiDocVen(tabla.getString("fecEmiDocVen"));
                        modelo.setCodMonDocVen(tabla.getInt("codMonDocVen"));
                        modelo.setDesMonDocVen(tabla.getString("desMonDocVen"));
                        modelo.setImpTipCamVen(tabla.getString("impTipCamVen"));
                        modelo.setImpMOAntVen(tabla.getString("impMOAntVen"));
                        modelo.setImpMOAntImp(tabla.getString("impMOAntImp"));
                        modelo.setImpMOAntTot(tabla.getString("impMOAntTot"));
                        modelo.setImpMonLocVen(tabla.getString("impMonLocVen"));
                        modelo.setImpMonLocDes(tabla.getString("impMonLocDes"));
                        modelo.setImpMonLocImp(tabla.getString("impMonLocImp"));
                        modelo.setImpMonLocTot(tabla.getString("impMonLocTot"));
                        modelo.setImpMonExtVen(tabla.getString("impMonExtVen"));
                        modelo.setImpMonExtDes(tabla.getString("impMonExtDes"));
                        modelo.setImpMonExtImp(tabla.getString("impMonExtImp"));
                        modelo.setImpMonExtTot(tabla.getString("impMonExtTot"));
                        modelo.setIdForPag(tabla.getInt("idForPag"));
                        modelo.setDesForPag(tabla.getString("desForPag"));
                        modelo.setDesObsDoc(tabla.getString("desObsDoc"));
                        modelo.setIdCli(tabla.getString("idCli"));
                        modelo.setDesCli(tabla.getString("desCli"));
                        modelo.setDesCli_h(tabla.getString("desCli"));
                        modelo.setIdVeh(tabla.getString("idVeh"));
                        modelo.setDesVeh(tabla.getString("desVeh"));
                        modelo.setDesVeh_h(tabla.getString("desVeh"));
                    }
                }
            }
        } catch (Exception e) {
        } finally {
            try {
                tabla.close();
                conex.returnConnect();
            } catch (Exception e) {
            }
        }
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
                
                if(modelo.getIdTipDocVen().equals(""))
                    modelo.setIdTipDocVen("99");
                
                getDatosRegistroVenta();
                        
                listTiposDocumentoVenta();
                populateForm();

                if (opcion.equals("M")) {
                    formURL = baseURL + "registroVentas/actualizarRegistroVenta";
                }
            }
        }

        return "adicionar";
    }
    
    public String detalle() {
        idAccion = 5;
        verifAccionTipoUsuario();

        if (indErrAcc.equals("")) {
            nivBandeja = 1;
            tituloOpc = "Registro de venta Nro "+modelo.getIdNumIntRV();
            
            if (!opcion.trim().equals("D")) {
                indErrParm = "error";
            } else {
                varReturnProcess(1);
                
                if(modelo.getIdTipDocVen().equals(""))
                    modelo.setIdTipDocVen("99");
                
                getDatosRegistroVenta();
            }
        }

        return "detalle";
    }
    
    public String getTiposDocumentoVentaCliente() {
        helper conex = null;
        ResultSet tabla = null;

        try {
            conex = new helper();
            indError = conex.getErrorSQL();

            if(!indError.equals("")) {
                errores.add(indError);
            } else {
                tabla = conex.executeDataSet("CALL usp_listTipoDocVentaCliente(?)", 
                        new Object[]{ modelo.getIdCli() });
                indError += conex.getErrorSQL();

                if(!indError.equals("")) {
                    errores.add(indError);
                } else {
                    TipoDocumentoVenta obj;
                    while(tabla.next()) {
                        obj = new TipoDocumentoVenta();
                        obj.setIdTipDocVen(tabla.getString("idTipDocVen"));
                        obj.setDesTipDocVen(tabla.getString("desTipDocVen"));
                        listTiposDocumentoVenta.add(obj);
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
        
        return "getTiposDocumentoVentaCliente";
    }
    
    private void cantClientesIndex() {
        helper conex = null;
        ResultSet tabla = null;

        try {
            conex = new helper();
            indError += conex.getErrorSQL();

            if (!indError.equals("")) {
                errores.add(indError);
            } else {
                tabla = conex.executeDataSet("CALL usp_cantClientesIndex(?,?)",
                        new Object[]{ "", cliente.getDesCli_f() });

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
    
    private void listClientesIndex() {
        helper conex = null;
        ResultSet tabla = null;

        try {
            conex = new helper();
            indError += conex.getErrorSQL();

            if (!indError.equals("")) {
                errores.add(indError);
            } else {
                tabla = conex.executeDataSet("CALL usp_listClientesIndex(?,?,?,?)",
                        new Object[]{ "", cliente.getDesCli_f(), getCurPag()*regPag, regPag});

                indError += conex.getErrorSQL();

                if (!indError.equals("")) {
                    errores.add(indError);
                } else {
                    Clientes obj;
                    while (tabla.next()) {
                        obj = new Clientes();
                        obj.setIdCli(tabla.getString("idCli"));
                        obj.setDesCli(tabla.getString("desCli"));
                        listClientes.add(obj);
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
    
    public String listClientes() {
        idAccion = 6;

        verifAccionTipoUsuario();

        if (indErrAcc.equals("")) {
            regPag = 13;
            urlPaginacion = "registroVentas/listClientesRegistroVenta";
            divPopUp = "DIVclientes";

            cantClientesIndex();
            verifPag();
            listClientesIndex();
        }

        return "listClientes";
    }
    
    public String getDatosCliente() {
        helper conex = null;
        ResultSet tabla = null;

        try {
            conex = new helper();
            indError = conex.getErrorSQL();

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
            indError = "errro";
            errores.add(e.getMessage());
        } finally {
            try {
                tabla.close();
                conex.returnConnect();
            } catch (Exception e) {
            }
        }
        
        return "getDatosCliente";
    }
    
    public String getDatosVehiculo() {
        helper conex = null;
        ResultSet tabla = null;

        try {
            conex = new helper();
            indError = conex.getErrorSQL();

            if(!indError.equals("")) {
                errores.add(indError);
            } else {
                tabla = conex.executeDataSet("CALL usp_getDatosBasicosVehiculo(?)", 
                        new Object[]{ modelo.getIdVeh() });
                indError = conex.getErrorSQL();

                if(!indError.equals("")) {
                    errores.add(indError);
                } else {
                    while(tabla.next()) {
                        modelo.setDesVeh(tabla.getString("desVeh"));
                    }
                }
            }
        } catch (Exception e) {
            indError = "errro";
            errores.add(e.getMessage());
        } finally {
            try {
                tabla.close();
                conex.returnConnect();
            } catch (Exception e) {
            }
        }
        
        return "getDatosVehiculo";
    }
    
    private void populateForm() {
        helper conex = null;
        ResultSet tabla = null;
        
        try {
            conex = new helper();
            indError += conex.getErrorSQL();
            
            if(!indError.equals("")) {
                errores.add(indError);
            } else {
                tabla = conex.executeDataSet("CALL usp_getDatosUltimoTipoCambio()", new Object[]{});
                indError += conex.getErrorSQL();
                
                if(!indError.equals("")) {
                    errores.add(indError);
                } else {
                    while(tabla.next()) {
                        modelo.setImpTipCamVen(tabla.getString("impTCIntVen"));
                    }
                    
                    tabla = null;
                    tabla = conex.executeDataSet("CALL usp_listFormaPago()", new Object[]{});
                    indError = conex.getErrorSQL();
                    
                    if(!indError.equals("")) {
                        errores.add(indError);
                    } else {
                        FormaPago obj;
                        while(tabla.next()) {
                            obj = new FormaPago();
                            obj.setIdForPag(tabla.getInt("idForPag"));
                            obj.setDesForPag(tabla.getString("desForPag"));
                            listFormasPago.add(obj);
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
    
    public String grabar() {
        idAccion = 7;

        verifAccionTipoUsuario();

        if (indErrAcc.equals("")) {
            if(modelo.getIdCli().equals("")) {
                indError += "error";
                errores.add("Ingrese o seleccione un cliente");
            } else {
                if(modelo.getDesCli_h().equals("")) {
                    indError += "error";
                    errores.add("Cliente no válido");
                }
            }
            
            if(modelo.getIdTipDocVen().equals("9")) {
                indError += "error";
                errores.add("Seleccione el tipo de documento");
            }
            
            if(modelo.getDesNumDocVen().equals("")) {
                indError += "error";
                errores.add("Ingrese el número del documento");
            }
            
            if(modelo.getFecEmiDocVen().equals("")) {
                indError += "error";
                errores.add("Ingrese la fecha de emisión");
            } else {
                if(!isDate(modelo.getFecEmiDocVen(), 2)) {
                    indError += "error";
                    errores.add("Fecha de emisión no válida");
                }
            }
            
            if(modelo.getCodMonDocVen()==0) {
                indError += "error";
                errores.add("Seleccione el tipo de moneda del documento");
            }
            
            if(!modelo.getIdVeh().equals("")) {
                if(modelo.getDesVeh_h().equals("")) {
                    indError += "error";
                    errores.add("Serie de vehículo no válida");
                }
            }
            
            if(modelo.getIdForPag()==0) {
                indError += "error";
                errores.add("Seleccione la forma de pago");
            }
            
            if(indError.equals("")) {
                helper conex = null;
                
                try {
                    conex = new helper();
                    indError = conex.getErrorSQL();

                    if(!indError.equals("")) {
                        errores.add(indError);
                    } else {
                        modelo.setFecEmiDocVen(getConvertFecha(modelo.getFecEmiDocVen(), 2));
                        if(modelo.getDesObsDoc().length()>100)
                            modelo.setDesObsDoc(modelo.getDesObsDoc().substring(0, 100));
                        
                        indError = conex.executeNonQuery("CALL usp_insRegistroVenta(?,?,?,?,?,?,?,?,?,?,?,?)", 
                                new Object[]{ modelo.getIdTipDocVen(),modelo.getDesNumDocVen(),modelo.getFecEmiDocVen(),
                                    modelo.getCodMonDocVen(),modelo.getImpTipCamVen(),modelo.getIdForPag(),modelo.getDesObsDoc(),
                                    modelo.getIdCli(),modelo.getIdVeh(),sesion_sga.get("ses_idcon"),sesion_sga.get("ses_idloccon"),
                                    sesion_sga.get("ses_idusu") });
                        
                        if(indError.equals("")) {
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

    public RegistroVentas getModelo() {
        return modelo;
    }

    public void setModelo(RegistroVentas modelo) {
        this.modelo = modelo;
    }

    public ArrayList<RegistroVentas> getListRegistroVentas() {
        return listRegistroVentas;
    }

    public ArrayList<TipoDocumentoVenta> getListTiposDocumentoVenta() {
        return listTiposDocumentoVenta;
    }

    public ArrayList<FormaPago> getListFormasPago() {
        return listFormasPago;
    }

    public ArrayList<Clientes> getListClientes() {
        return listClientes;
    }

    public Clientes getCliente() {
        return cliente;
    }

    public void setCliente(Clientes cliente) {
        this.cliente = cliente;
    }
    
    
}
