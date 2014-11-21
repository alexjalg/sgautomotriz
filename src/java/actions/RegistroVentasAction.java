package actions;

/*
 * Action: Registro de ventas
 * Creado por: Angelo Ccoicca
 * Fecha de creación: 25-09-2014
 * Modificado por                   Fecha de Modificación
 * - 
 * -
 */

import actions.MasterAction;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ModelDriven;
import conexion.helper;
import entities.AnticiposRegistroVentas;
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
    private AnticiposRegistroVentas anticipoRegistroVentas = new AnticiposRegistroVentas();
    
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
                
                accion = "Adicionar";
                
                modelo.setIdTipDocVen("99");
                populateForm();
                
                modelo.setIdNumIntRV("");
                formURL = baseURL + "registroVentas/grabarRegistroVenta";
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
                
                accion = "Modificar";
                
                if(modelo.getIdTipDocVen().equals(""))
                    modelo.setIdTipDocVen("99");
                
                getDatosRegistroVenta();
                        
                listTiposDocumentoVenta();
                populateForm();
                
                formURL = baseURL + "registroVentas/actualizarRegistroVenta";
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
                
                accion = "Detalle";
                
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
        ResultSet tabla2 = null;
        ResultSet tabla3 = null;

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
                        Double precAsigDol = Double.parseDouble(tabla.getString("impPreVenAsi"));
                        Double precAsigSol = precAsigDol*Double.parseDouble(sesion_sga.get("ses_tipcam").toString());
                        
                        //Descuentos por campañas
                        Double impDesCamDol = 0.00;
                        Double impDesCamSol = 0.00;
                        
                        tabla3 = conex.executeDataSet("CALL usp_getTotalImpCampaniaVehiculo(?,?)", 
                                new Object[]{ modelo.getIdVeh(),Double.parseDouble(sesion_sga.get("ses_tipcam").toString()) });
                        
                        indError = conex.getErrorSQL();
                        
                        if(!indError.equals("")) {
                            errores.add(indError);
                        } else {
                            while(tabla3.next()) {
                                impDesCamDol += tabla3.getDouble("impRelCam");
                            }
                            
                            impDesCamSol = redondear(impDesCamDol*Double.parseDouble(sesion_sga.get("ses_tipcam").toString()), 2);
                        }
                        
                        impDesCamDol = redondear(calculaImporteVenta(impDesCamDol, Double.parseDouble(sesion_sga.get("ses_igv").toString())),2);
                        impDesCamSol = redondear(calculaImporteVenta(impDesCamSol, Double.parseDouble(sesion_sga.get("ses_igv").toString())),2);
                        
                        modelo.setImpMonExtDesCam(String.valueOf(impDesCamDol));
                        modelo.setImpMonLocDesCam(String.valueOf(impDesCamSol));
                        
                        //Si las ventas estan afectas a impuestos
                        if(sesion_sga.get("ses_afecimp").toString().equals("S")) {
                            Double monVenDol = redondear(calculaImporteVenta(precAsigDol,Double.parseDouble(sesion_sga.get("ses_igv").toString())),2);
                            modelo.setImpMonExtVen(String.valueOf(monVenDol));
                            monVenDol = monVenDol-impDesCamDol;
                            Double monImpDol = redondear(monVenDol*Double.parseDouble(sesion_sga.get("ses_igv").toString())/100.00,2);
                            precAsigDol = redondear(monVenDol+monImpDol,2); 
                            
                            modelo.setImpMonExtImp(String.valueOf(monImpDol));
                            modelo.setImpMonExtTot(String.valueOf(precAsigDol));
                            
                            Double monVenSol = redondear(calculaImporteVenta(precAsigSol,Double.parseDouble(sesion_sga.get("ses_igv").toString())),2);
                            modelo.setImpMonLocVen(String.valueOf(monVenSol));
                            monVenSol = monVenSol-impDesCamSol;
                            Double monImpSol = redondear(monVenSol*Double.parseDouble(sesion_sga.get("ses_igv").toString())/100.00,2);
                            precAsigSol = redondear(monVenSol+monImpSol,2);
                            
                            modelo.setImpMonLocImp(String.valueOf(monImpSol));
                            modelo.setImpMonLocTot(String.valueOf(precAsigSol));
                        } else {
                            modelo.setImpMonExtVen(String.valueOf(precAsigDol));
                            precAsigDol = redondear(precAsigDol-impDesCamDol,2);
                            modelo.setImpMonExtTot(String.valueOf(precAsigDol));
                            
                            modelo.setImpMonLocVen(String.valueOf(precAsigSol));
                            precAsigSol = redondear(precAsigSol-impDesCamSol,2);
                            modelo.setImpMonLocTot(String.valueOf(precAsigSol));
                        }
                        
                        if(!modelo.getIdCli().equals("") && modelo.getCodMonDocVen()!=0) {
                            tabla2 = conex.executeDataSet("CALL usp_getTotalAnticiposRegVentasCliente(?)", 
                                    new Object[]{ modelo.getIdCli() });
                            indError = conex.getErrorSQL();

                            if(!indError.equals("")) {
                                errores.add(conex.getErrorSQL());
                            } else {
                                while(tabla2.next()) {
                                    Double monTotPagDol = Double.parseDouble(modelo.getImpMonExtTot()) - Double.parseDouble(tabla2.getString("impDisMonExtTot"));
                                    monTotPagDol= redondear(monTotPagDol, 2);
                                    modelo.setImpMonExtTotPag(String.valueOf(monTotPagDol));

                                    Double monTotPagSol = Double.parseDouble(modelo.getImpMonLocTot()) - Double.parseDouble(tabla2.getString("impDisMonLocTot"));
                                    monTotPagSol= redondear(monTotPagSol, 2);
                                    modelo.setImpMonLocTotPag(String.valueOf(monTotPagSol));
                                }
                            } 
                        } else {
                            modelo.setImpMonExtTotPag(modelo.getImpMonExtTot());
                            modelo.setImpMonLocTotPag(modelo.getImpMonLocTot());
                        }
                    }
                }
            }
        } catch (Exception e) {
            indError = "error";
            errores.add(e.getMessage());
        } finally {
            try {
                tabla.close();
                tabla2.close();
                conex.returnConnect();
            } catch (Exception e) {
            }
        }
        
        return "getDatosVehiculo";
    }
    
    public String getTotalAnticipos() {
        helper conex = null;
        ResultSet tabla = null;
        
        try {
            conex = new helper();
            indError += conex.getErrorSQL();
            if(!indError.equals("")) {
                errores.add(indError);
            } else {
                tabla = conex.executeDataSet("CALL usp_getTotalAnticiposRegVentasCliente(?)", 
                        new Object[]{ modelo.getIdCli() });
                indError += conex.getErrorSQL();
                
                if(!indError.equals("")) {
                    errores.add(conex.getErrorSQL());
                } else {
                    while(tabla.next()) {
                        anticipoRegistroVentas.setImpDisMonLocVen(tabla.getString("impDisMonLocVen"));
                        anticipoRegistroVentas.setImpDisMonLocImp(tabla.getString("impDisMonLocImp"));
                        anticipoRegistroVentas.setImpDisMonLocTot(tabla.getString("impDisMonLocTot"));
                        anticipoRegistroVentas.setImpDisMonExtVen(tabla.getString("impDisMonExtVen"));
                        anticipoRegistroVentas.setImpDisMonExtImp(tabla.getString("impDisMonExtImp"));
                        anticipoRegistroVentas.setImpDisMonExtTot(tabla.getString("impDisMonExtTot"));
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
        
        return "getTotalAnticipos";
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
                errores.add("Ingrese o seleccione un cliente");
            } else {
                if(modelo.getDesCli_h().equals("")) {
                    errores.add("Cliente no válido");
                }
            }
            
            if(modelo.getIdTipDocVen().equals("9")) {
                errores.add("Seleccione el tipo de documento");
            }
            
            if(modelo.getDesNumDocVen().equals("")) {
                errores.add("Ingrese el número del documento");
            }
            
            if(modelo.getFecEmiDocVen().equals("")) {
                errores.add("Ingrese la fecha de emisión");
            } else {
                if(!isDate(modelo.getFecEmiDocVen(), 2)) {
                    errores.add("Fecha de emisión no válida");
                }
            }
            
            if(modelo.getCodMonDocVen()==0) {
                errores.add("Seleccione el tipo de moneda del documento");
            }
            
            if(modelo.getIdVeh().equals("")) {
                errores.add("Debe ingresar los datos de un vehículo");
            } else {
                helper conex1 = null;
                ResultSet tabla = null;
                
                try {
                    conex1 = new helper();
                    indError = conex1.getErrorSQL();
                    
                    if(!indError.equals("")) {
                        errores.add(indError);
                    } else {
                        tabla = conex1.executeDataSet("CALL usp_verifExistVehiculo(?)",
                                new Object[]{modelo.getIdVeh()});

                        indError = conex1.getErrorSQL();

                        if (!indError.equals("")) {
                            errores.add(indError);
                        } else {
                            int cont = 0;
                            while (tabla.next()) {
                                cont = tabla.getInt(1);
                            }

                            if(cont==0) {
                                errores.add("Serie de vehículo no válida");
                            }
                        }
                    }
                } catch (Exception e) {
                    errores.add(e.getMessage());
                } finally {
                    try {
                        tabla.close();
                        conex1.returnConnect();
                    } catch (Exception e) {
                    }
                }
            }
            
            if(modelo.getIdForPag()==0) {
                errores.add("Seleccione la forma de pago");
            }
            
            if(errores.isEmpty()) {
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
                        
                        indError = conex.executeNonQuery("CALL usp_insRegistroVenta(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)", 
                                new Object[]{ modelo.getIdTipDocVen(),modelo.getDesNumDocVen(),modelo.getFecEmiDocVen(),modelo.getCodMonDocVen(),
                                    modelo.getImpTipCamVen(),modelo.getImpMOAntVen(),modelo.getImpMOAntImp(),modelo.getImpMOAntTot(),
                                    modelo.getImpMonLocVen(),modelo.getImpMonLocDes(),modelo.getImpMonLocImp(),modelo.getImpMonLocTot(),
                                    modelo.getImpMonExtVen(),modelo.getImpMonExtDes(),modelo.getImpMonExtImp(),modelo.getImpMonExtTot(),
                                    modelo.getIdForPag(),modelo.getDesObsDoc(),modelo.getIdCli(),modelo.getIdVeh(),sesion_sga.get("ses_idcon"),
                                    sesion_sga.get("ses_idloccon"),sesion_sga.get("ses_idusu") });
                        
                        if(!indError.equals("")) {
                            errores.add(indError);
                        } else {
                            indError = conex.executeNonQuery("CALL usp_updAnticipoRegistroVenta(?)", 
                                    new Object[]{ modelo.getIdCli() });
                        }
                    }
                } catch (Exception e) {
                    indError = "error";
                    errores.add(e.getMessage());
                } finally {
                    conex.returnConnect();
                }
            }
            
            if(!errores.isEmpty()) {
                indErrorTot = "error";
            }
        } 
        
        return "grabar";
    }
    
    public String adicionarAnticipo() {
        idAccion = 8;

        verifAccionTipoUsuario();

        if (indErrAcc.equals("")) {
            nivBandeja = 1;

            if (!opcion.trim().equals("A")) {
                indErrParm = "error";
            } else {
                varReturnProcess(1);
                
                accion = "Adicionar anticipo";
                
                modelo.setIdTipDocVen("99");
                populateForm();
                
                modelo.setIdNumIntRV("");
                formURL = baseURL + "registroVentas/grabarAnticipoRegistroVenta";
            }
        }
        
        return "adicionarAnticipo";
    }
    
    public String getTiposDocumentoVentaClienteAnticipo() {
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
                        if(tabla.getString("idTipDocVen").equals("BV") || tabla.getString("idTipDocVen").equals("FA")) {
                            obj = new TipoDocumentoVenta();
                            obj.setIdTipDocVen(tabla.getString("idTipDocVen"));
                            obj.setDesTipDocVen(tabla.getString("desTipDocVen"));
                            listTiposDocumentoVenta.add(obj);
                        }
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
    
    public String getMontosAnticipo() {
        try {
            if(modelo.getCodMonDocVen()==1) {
                if(isDouble(modelo.getImpMonLocTot())) {
                    //Montos en soles
                    Double monTotSol = redondear(Double.parseDouble(modelo.getImpMonLocTot()), 2);
                    Double monVenSol = redondear(calculaImporteVenta(monTotSol, Double.parseDouble(sesion_sga.get("ses_igv").toString())),2);
                    Double monImpSol = redondear(monVenSol*Double.parseDouble(sesion_sga.get("ses_igv").toString()),2);
                    
                    modelo.setImpMonLocTot(String.valueOf(monTotSol));
                    modelo.setImpMonLocVen(String.valueOf(monVenSol));
                    modelo.setImpMonLocImp(String.valueOf(monImpSol));

                    //Montos en dolares
                    Double monTotDol = redondear(monTotSol/Double.parseDouble(sesion_sga.get("ses_tipcam").toString()),2);
                    Double monVenDol = redondear(calculaImporteVenta(monTotDol,Double.parseDouble(sesion_sga.get("ses_igv").toString())),2);
                    Double monImpDol = redondear(monVenDol*Double.parseDouble(sesion_sga.get("ses_igv").toString()),2);
                    
                    
                    modelo.setImpMonExtTot(String.valueOf(monTotDol));
                    modelo.setImpMonExtVen(String.valueOf(monVenDol));
                    modelo.setImpMonExtImp(String.valueOf(monImpDol));
                }
            } else if(modelo.getCodMonDocVen()==2) {
                if(isDouble(modelo.getImpMonExtTot())) {
                    //Montos en dolares
                    Double monTotDol = redondear(Double.parseDouble(modelo.getImpMonExtTot()), 2);
                    Double monVenDol = redondear(calculaImporteVenta(monTotDol, Double.parseDouble(sesion_sga.get("ses_igv").toString())),2);
                    Double monImpDol = redondear(monVenDol*Double.parseDouble(sesion_sga.get("ses_igv").toString()),2);
                    
                    modelo.setImpMonExtTot(String.valueOf(monTotDol));
                    modelo.setImpMonExtVen(String.valueOf(monVenDol));
                    modelo.setImpMonExtImp(String.valueOf(monImpDol));

                    //Montos en soles
                    Double monTotSol = redondear(monTotDol*Double.parseDouble(sesion_sga.get("ses_tipcam").toString()),2);
                    Double monVenSol = redondear(calculaImporteVenta(monTotSol, Double.parseDouble(sesion_sga.get("ses_igv").toString())),2);
                    Double monImpSol = redondear(monVenSol*Double.parseDouble(sesion_sga.get("ses_igv").toString()),2);
                    
                    
                    modelo.setImpMonLocTot(String.valueOf(monTotSol));
                    modelo.setImpMonLocVen(String.valueOf(monVenSol));
                    modelo.setImpMonLocImp(String.valueOf(monImpSol));
                }
            }
        } catch(Exception ex) {
            indError += "error";
            errores.add(ex.getMessage());
        }
        
        return "getMontosAnticipo";
    }
    
    public String grabarAnticipo() {
        idAccion = 9;

        verifAccionTipoUsuario();

        if (indErrAcc.equals("")) {
            if(modelo.getIdCli().equals("")) {
                errores.add("Ingrese o seleccione un cliente");
            } else {
                if(modelo.getDesCli_h().equals("")) {
                    errores.add("Cliente no válido");
                }
            }
            
            if(modelo.getIdTipDocVen().equals("9")) {
                errores.add("Seleccione el tipo de documento");
            }
            
            if(modelo.getDesNumDocVen().equals("")) {
                errores.add("Ingrese el número del documento");
            }
            
            if(modelo.getFecEmiDocVen().equals("")) {
                errores.add("Ingrese la fecha de emisión");
            } else {
                if(!isDate(modelo.getFecEmiDocVen(), 2)) {
                    errores.add("Fecha de emisión no válida");
                }
            }
            
            if(modelo.getCodMonDocVen()==0) {
                errores.add("Seleccione el tipo de moneda del documento");
            }
            
            if(modelo.getCodMonDocVen()!=0) {
                if(modelo.getCodMonDocVen()==1) {
                    if(modelo.getImpMonLocTot().equals("")) {
                        errores.add("Ingrese el importe de venta en soles");
                    } else if(!isDouble(modelo.getImpMonLocTot())) {
                        errores.add("Importe de venta en soles no válido");
                    } else if(Double.parseDouble(modelo.getImpMonLocTot())<=0) {
                        errores.add("Importe de venta en soles debe ser mayor a cero");
                    }
                }
                
                if(modelo.getCodMonDocVen()==2) {
                    if(modelo.getImpMonExtTot().equals("")) {
                        errores.add("Ingrese el importe de venta en dólares");
                    } else if(!isDouble(modelo.getImpMonExtTot())) {
                        errores.add("Importe de venta en dólares no válido");
                    } else if(Double.parseDouble(modelo.getImpMonExtTot())<=0) {
                        errores.add("Importe de venta en dólares debe ser mayor a cero");
                    }
                }
            }
            
            if(modelo.getIdForPag()==0) {
                errores.add("Seleccione la forma de pago");
            }
            
            if(errores.isEmpty()) {
                helper conex = null;
                
                try {
                    conex = new helper();
                    indError = conex.getErrorSQL();

                    if(!indError.equals("")) {
                        errores.add(indError);
                    } else {
                        getMontosAnticipo();
                        
                        modelo.setFecEmiDocVen(getConvertFecha(modelo.getFecEmiDocVen(), 2));
                        
                        if(modelo.getDesObsDoc().length()>100)
                            modelo.setDesObsDoc(modelo.getDesObsDoc().substring(0, 100));
                        
                        indError = conex.executeNonQuery("CALL usp_insRegistroVentaAnticipo(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)", 
                                new Object[]{ modelo.getIdTipDocVen(),modelo.getDesNumDocVen(),modelo.getFecEmiDocVen(),
                                    modelo.getCodMonDocVen(),modelo.getImpTipCamVen(),modelo.getImpMonLocVen(),modelo.getImpMonLocImp(),
                                    modelo.getImpMonLocTot(),modelo.getImpMonExtVen(),modelo.getImpMonExtImp(),modelo.getImpMonExtTot(),
                                    modelo.getIdForPag(),modelo.getDesObsDoc(),modelo.getIdCli(),sesion_sga.get("ses_idcon"),
                                    sesion_sga.get("ses_idloccon"),sesion_sga.get("ses_idusu") });
                        
                        if(!indError.equals("")) {
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
            
            if(!errores.isEmpty()) {
                indErrorTot = "error";
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

    public AnticiposRegistroVentas getAnticipoRegistroVentas() {
        return anticipoRegistroVentas;
    }

    public void setAnticipoRegistroVentas(AnticiposRegistroVentas anticipoRegistroVentas) {
        this.anticipoRegistroVentas = anticipoRegistroVentas;
    }
    
    
}
