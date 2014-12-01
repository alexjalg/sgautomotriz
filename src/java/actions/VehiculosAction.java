package actions;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ModelDriven;
import conexion.helper;

import entities.Vehiculos;
import java.sql.ResultSet;
import java.util.ArrayList;
import entities.Marcas;
import entities.Modelos;
import entities.Versiones;
import entities.ColorInteior;
import entities.ColoresExterior;
import entities.AmbienteUbicacion;
import entities.UbicacionAmbiente;
import entities.Campania;
import entities.Concesionarios;
import entities.Locales;
import java.util.Map;
import entities.TipoDocumentoVenta;
import entities.Clientes;
import entities.ModalidadVenta;
import entities.SituacionVehiculo;
import entities.SituacionVentaVehiculo;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author Angel
 */
public class VehiculosAction extends MasterAction implements ModelDriven<Vehiculos> {

    private Map<String, Object> sesion_sga = ActionContext.getContext().getSession();
    private Vehiculos vehiculo = new Vehiculos();
    private Campania campania = new Campania();
    private ArrayList<SituacionVehiculo> listSituacionVehiculo = new ArrayList<SituacionVehiculo>();
    private ArrayList<SituacionVentaVehiculo> listSituacionVentaVehiculo = new ArrayList<SituacionVentaVehiculo>();
    private ArrayList<Vehiculos> listVehiculos = new ArrayList<Vehiculos>();
    private ArrayList<Marcas> listMarcas = new ArrayList<Marcas>();
    private ArrayList<Modelos> listModelos = new ArrayList<Modelos>();
    private ArrayList<Versiones> listVersiones = new ArrayList<Versiones>();
    private ArrayList<ColorInteior> listColorInt = new ArrayList<ColorInteior>();
    private ArrayList<ColoresExterior> listColoExt = new ArrayList<ColoresExterior>();
    private ArrayList<AmbienteUbicacion> listAmbientes = new ArrayList<AmbienteUbicacion>();
    private ArrayList<UbicacionAmbiente> listUbicaciones = new ArrayList<UbicacionAmbiente>();
    private ArrayList<Campania> listCampanias = new ArrayList<Campania>();
    private ArrayList<Concesionarios> listConcesionarios = new ArrayList<Concesionarios>();
    private ArrayList<Locales> listLocales = new ArrayList<Locales>();
    private ArrayList<Clientes> listClientes = new ArrayList<Clientes>();
    private ArrayList<TipoDocumentoVenta> listTipoDocVenta = new ArrayList<TipoDocumentoVenta>();
    private ArrayList<ModalidadVenta> listModalidadVenta = new ArrayList<ModalidadVenta>();

    @Override
    public Vehiculos getModel() {

        tituloOpc = "Recepción de Vehículos";
        idClaseAccion = 31;
        return vehiculo;
    }

    public String vrfSeleccion() {
        idAccion = 1;
        verifAccionTipoUsuario();

        if (indErrAcc.equals("")) {

            vehiculo.setIdVeh(vehiculo.getIdVeh().trim());
            if (vehiculo.getIdVeh().equals("")) {
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
            urlPaginacion = "vehiculos/Vehiculo";

            varReturnProcess(0);
            if (!listVarReturn.isEmpty()) {
                curPagVis = Integer.parseInt(listVarReturn.get(0).toString().trim());
                vehiculo.setIdVeh_f(listVarReturn.get(1).toString().trim());
                vehiculo.setDesNumCha_f(listVarReturn.get(2).toString().trim());
                vehiculo.setDesNumMot_f(listVarReturn.get(3).toString().trim());
                vehiculo.setFecFacPrv_f(listVarReturn.get(4).toString().trim());
                vehiculo.setDesCli_f(listVarReturn.get(5).toString().trim());
                vehiculo.setFecEmiDocVen_f(listVarReturn.get(6).toString().trim());
                vehiculo.setIdTipDocVen_f(listVarReturn.get(7).toString().trim());
                vehiculo.setDesNumDocVen_f(listVarReturn.get(8).toString().trim());
                vehiculo.setDesNumPla_f(listVarReturn.get(9).toString().trim());
                vehiculo.setFecEntCli_f(listVarReturn.get(10).toString().trim());
            }

            vehiculo.setFecFacPrv_f(getConvertFecha(vehiculo.getFecFacPrv_f(), 2));
            vehiculo.setFecEmiDocVen_f(getConvertFecha(vehiculo.getFecEmiDocVen_f(), 2));
            vehiculo.setFecEntCli_f(getConvertFecha(vehiculo.getFecEntCli_f(), 2));

            cantVehiculosIndex();
            verifPag();
            listVehiculoIndex();

            vehiculo.setFecFacPrv_f(getConvertFecha(vehiculo.getFecFacPrv_f(), 1));
            vehiculo.setFecEmiDocVen_f(getConvertFecha(vehiculo.getFecEmiDocVen_f(), 1));
            vehiculo.setFecEntCli_f(getConvertFecha(vehiculo.getFecEntCli_f(), 1));
            
            listTipoDocumentoVenta();
        }

        return SUCCESS;
    }

    public void cantVehiculosIndex() {
        helper conex = null;
        ResultSet tabla = null;

        try {
            conex = new helper();
            indError = conex.getErrorSQL().trim();

            if (!indError.equals("")) {
                errores.add(indError);
            } else {
                tabla = conex.executeDataSet("CALL usp_cantVehiculoIndex(?,?,?,?,?,?,?,?,?,?,?,?,?)",
                        new Object[]{ vehiculo.getIdVeh_f(),vehiculo.getDesNumCha_f(),vehiculo.getDesNumMot_f(),
                            vehiculo.getFecFacPrv_f(),vehiculo.getDesCli_f(),vehiculo.getFecEmiDocVen_f(),
                            vehiculo.getIdTipDocVen_f(),vehiculo.getDesNumDocVen_f(),vehiculo.getDesNumPla_f(),
                            vehiculo.getFecEntCli_f(),vehiculo.getDesVeh_f(),vehiculo.getCodSitVeh_f(),
                            vehiculo.getCodSitVen_f() });
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
                indError = "error";
                errores.add(e.getMessage());
            }
        }
    }

    public void listVehiculoIndex() {
        helper conex = null;
        ResultSet tabla = null;

        try {
            conex = new helper();
            indError = conex.getErrorSQL().trim();
            if (!indError.equals("")) {
                errores.add(indError);
            } else {
                tabla = conex.executeDataSet("CALL usp_listVehiculoIndex(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)",
                        new Object[]{ vehiculo.getIdVeh_f(),vehiculo.getDesNumCha_f(),vehiculo.getDesNumMot_f(),
                            vehiculo.getFecFacPrv_f(),vehiculo.getDesCli_f(),vehiculo.getFecEmiDocVen_f(),
                            vehiculo.getIdTipDocVen_f(),vehiculo.getDesNumDocVen_f(),vehiculo.getDesNumPla_f(),
                            vehiculo.getFecEntCli_f(),vehiculo.getDesVeh_f(),vehiculo.getCodSitVeh_f(),
                            vehiculo.getCodSitVen_f(),getCurPag()*getRegPag(),getRegPag() });
                
                indError = conex.getErrorSQL().trim();
                if (!indError.equals("")) {
                    errores.add(indError);
                } else {
                    Vehiculos obj;
                    while (tabla.next()) {
                        obj = new Vehiculos();

                        obj.setIdVeh(tabla.getString("idVeh"));
                        obj.setDesVeh(tabla.getString("desVeh"));
                        obj.setDesNumCha(tabla.getString("desNumCha"));
                        obj.setDesNumMot(tabla.getString("desNumMot"));
                        obj.setFecFacPrv(tabla.getString("fecFacPrv"));
                        obj.setDesCli(tabla.getString("desCli"));
                        obj.setFecEmiDocVen(tabla.getString("fecEmiDocVen"));
                        obj.setDesTipDocVen(tabla.getString("desTipDocVen"));
                        obj.setDesNumDocVen(tabla.getString("desNumDocVen"));
                        obj.setDesNumPla(tabla.getString("desNumPla"));
                        obj.setFecEntCli(tabla.getString("fecEntCli"));
                        obj.setDesSitVeh(tabla.getString("desSitVeh"));
                        obj.setDesSitVen(tabla.getString("desSitVen"));
                        listVehiculos.add(obj);
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
                indError = "error";
                errores.add(e.getMessage());
            }
        }
    }
    
    private void listTipoDocumentoVenta() {
        helper conex = null;
        ResultSet tabla = null;
        
        try {
            conex = new helper();
            indError += conex.getErrorSQL();
            
            if(!indError.equals("")) {
                errores.add(indError);
            } else {
                tabla = conex.executeDataSet("CALL usp_listTipoDocumentoVenta()", new Object[]{});
                indError = conex.getErrorSQL();
                
                if(!indError.equals("")) {
                    errores.add(indError);
                } else {
                    TipoDocumentoVenta obj;
                    while(tabla.next()) {
                        obj = new TipoDocumentoVenta();
                        obj.setIdTipDocVen(tabla.getString("idTipDocVen"));
                        obj.setDesTipDocVen(tabla.getString("desTipDocVen"));
                        listTipoDocVenta.add(obj);
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
            if (!opcion.trim().equals("A")) {
                indErrParm = "error";
            } else {
                accion = "Adicionar";
                
                vehiculo.setIdVeh("");
                varReturnProcess(1);
                
                formURL = baseURL + "vehiculos/grabarVehiculo";
                populateForm();
            }
        }

        return "adicionar";
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
                tabla = conex.executeDataSet("CALL usp_listMarcas()",
                        new Object[]{});
                indError = conex.getErrorSQL().trim();
                
                if (!indError.equals("")) {
                    errores.add(indError);
                } else {
                    Marcas obj1;
                    while (tabla.next()) {
                        obj1 = new Marcas();
                        obj1.setIdMar(tabla.getString("idMar"));
                        obj1.setDesMar(tabla.getString("desMar"));
                        listMarcas.add(obj1);
                    }
                    
                    tabla = null;
                    if(!vehiculo.getIdModMar().equals("")) {
                        tabla = conex.executeDataSet("CALL usp_listModeloxMarca2(?)",
                                new Object[]{vehiculo.getIdMar()});
                        indError = conex.getErrorSQL().trim();
                        if (!indError.equals("")) {
                            errores.add(indError);
                        } else {
                            Modelos obj2;
                            while (tabla.next()) {
                                obj2 = new Modelos();
                                obj2.setIdModMar(tabla.getString("idModMar"));
                                obj2.setDesModMar(tabla.getString("desModMar"));
                                listModelos.add(obj2);
                            }
                        }
                    }
                    
                    tabla = null;
                    if(!vehiculo.getIdVerMod().equals("") && !vehiculo.getIdVerMod().equals("0")) {
                        tabla = conex.executeDataSet("CALL usp_listVersionesxModeloxMarca(?,?)",
                                new Object[]{vehiculo.getIdMar(), vehiculo.getIdModMar()});
                        indError = conex.getErrorSQL().trim();
                        if (!indError.equals("")) {
                            errores.add(indError);
                        } else {
                            Versiones obj3;
                            while (tabla.next()) {
                                obj3 = new Versiones();
                                obj3.setIdVerMod(tabla.getString("idVerMod"));
                                obj3.setDesVerMod(tabla.getString("desVerMod"));
                                listVersiones.add(obj3);
                            }
                        }
                    }
                    
                    tabla = null;
                    tabla = conex.executeDataSet("CALL usp_listColoresExtxModel(?,?)",
                            new Object[]{vehiculo.getIdMar(), vehiculo.getIdModMar()});
                    indError = conex.getErrorSQL().trim();
                    if (!indError.equals("")) {
                        errores.add(indError);
                    } else {
                        ColoresExterior obj4;
                        while (tabla.next()) {
                            obj4 = new ColoresExterior();
                            obj4.setIdColExt(tabla.getString("idColExt"));
                            obj4.setDesColExt(tabla.getString("desColExt"));
                            listColoExt.add(obj4);
                        }
                    }
                    
                    tabla = null;
                    tabla = conex.executeDataSet("CALL usp_listColoresInt()",
                            new Object[]{});
                    indError = conex.getErrorSQL().trim();
                    if (!indError.equals("")) {
                        errores.add(indError);
                    } else {
                        ColorInteior obj5;
                        while (tabla.next()) {
                            obj5 = new ColorInteior();
                            obj5.setIdColInt(tabla.getString("idColInt"));
                            obj5.setDesColInt(tabla.getString("desColInt"));
                            listColorInt.add(obj5);
                        }
                    }
                    
                    tabla = null;
                    tabla = conex.executeDataSet("CALL usp_listAmbientes()",
                            new Object[]{});
                    indError = conex.getErrorSQL().trim();
                    if (!indError.equals("")) {
                        errores.add(indError);
                    } else {
                        AmbienteUbicacion obj6;
                        while (tabla.next()) {
                            obj6 = new AmbienteUbicacion();
                            obj6.setIdAmbUbi(tabla.getInt("idAmbUbi"));
                            obj6.setDesAmbUbi(tabla.getString("desAmbUbi"));
                            listAmbientes.add(obj6);
                        }
                    }
                    
                    tabla = null;
                    if(vehiculo.getIdUbiAmb()!=99) {
                        tabla = conex.executeDataSet("CALL usp_listUbicacionesXAmbiente(?)",
                                new Object[]{ vehiculo.getIdAmbUbi() });
                        indError = conex.getErrorSQL().trim();

                        if (!indError.equals("")) {
                            errores.add(indError);
                        } else {
                            UbicacionAmbiente obj;
                            while (tabla.next()) {
                                obj = new UbicacionAmbiente();
                                obj.setIdUbiAmb(tabla.getInt("idUbiAmb"));
                                obj.setDesUbiAmb(tabla.getString("desUbiAmb"));
                                listUbicaciones.add(obj);
                            }        
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
    
    private void populateFormEdit2() {
        listSituacionVehiculo.add(new SituacionVehiculo("L", "Libre"));
        listSituacionVehiculo.add(new SituacionVehiculo("S", "Separado"));
        listSituacionVehiculo.add(new SituacionVehiculo("K", "Booking"));
    }
    
    private void populateFormEdit3() {
        listSituacionVehiculo.add(new SituacionVehiculo("L", "Libre"));
        listSituacionVehiculo.add(new SituacionVehiculo("B", "Bloqueado"));
        listSituacionVehiculo.add(new SituacionVehiculo("X", "Exhibición"));
        listSituacionVehiculo.add(new SituacionVehiculo("E", "Endosado"));
    }
    
    public String modificar() {
        idAccion = 4;
        verifAccionTipoUsuario();
        
        String resp = "modificar";
        
        if (indErrAcc.equals("")) {
            if(indModificar == 3)
                resp = "modificar3";
            
            if(indModificar == 2)
                resp = "modificar2";
        
            nivBandeja = 1;

            if (!opcion.trim().equals("M") || vehiculo.getIdVeh().equals("")) {
                indErrParm = "error";
            } else {
                varReturnProcess(1);

                accion = "Modificar";
                
                if(indModificar == 2) {
                    tituloOpc="Venta de Vehículos";
                    
                    listModalidadVenta();
                    getDatosFullVehiculo();
                    listCampaniasVehiculoIndex();
                    populateFormEdit2();
                    
                    formURL = baseURL + "vehiculos/actualizar2Vehiculo";
                } else if(indModificar == 3) {
                    tituloOpc = "Vehículos";
                    
                    getDatosFullVehiculo();
                    listCampaniasVehiculoIndex();
                    populateFormEdit3();
                    
                    formURL = baseURL + "vehiculos/actualizar3Vehiculo";
                } else {
                    getDatosVehiculo();
                    populateForm();
                    
                    formURL = baseURL + "vehiculos/actualizarVehiculo";
                }
            }
        }

        return resp;
    }
    
    private void getDatosVehiculo() {
        helper conex = null;
        ResultSet tabla = null;
        
        try {
            conex = new helper();
            indError = conex.getErrorSQL();
            
            if(!indError.equals("")) {
                errores.add(indError);
            } else {
                tabla = conex.executeDataSet("CALL usp_getDatosVehiculo(?)", 
                        new Object[]{ vehiculo.getIdVeh() });
                indError = conex.getErrorSQL();
                
                if(!indError.equals("")) {
                    errores.add(indError);
                } else {
                    while(tabla.next()) {
                        vehiculo.setIdVeh(tabla.getString("idVeh"));
                        vehiculo.setIdMar(tabla.getString("idMar"));
                        vehiculo.setIdModMar(tabla.getString("idModMar"));
                        vehiculo.setIdVerMod(tabla.getString("idVerMod"));
                        vehiculo.setIdColExt(tabla.getString("idColExt"));
                        vehiculo.setIdColInt(tabla.getString("idColInt"));
                        vehiculo.setNumAnoFab(tabla.getString("numAnoFab"));
                        vehiculo.setNumAnoMod(tabla.getString("numAnoMod"));
                        vehiculo.setDesNumCha(tabla.getString("desNumCha"));
                        vehiculo.setDesNumMot(tabla.getString("desNumMot"));
                        vehiculo.setImpPreVenAsi(tabla.getString("impPreVenAsi"));
                        vehiculo.setNumKimAct(tabla.getString("numKimAct"));
                        vehiculo.setIdAmbUbi(tabla.getInt("idAmbUbi"));
                        vehiculo.setIdUbiAmb(tabla.getInt("idUbiAmb"));
                        vehiculo.setDesPolImp(tabla.getString("desPolImp"));
                        vehiculo.setDesNumFacPrv(tabla.getString("desNumFacPrv"));
                        vehiculo.setFecFacPrv(tabla.getString("fecFacPrv"));
                        vehiculo.setCodMonFacPrv(tabla.getString("codMonFacPrv"));
                        vehiculo.setImpTipCamFP(tabla.getString("impTipCamFP"));
                        vehiculo.setImpMonLoc(tabla.getString("impMonLoc"));
                        vehiculo.setImpMonExt(tabla.getString("impMonExt"));
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

    public String listaModelos() {
        helper conex = null;
        ResultSet tabla = null;

        try {
            conex = new helper();
            indError = conex.getErrorSQL().trim();
            if (!indError.equals("")) {
                errores.add(indError);
            } else {
                tabla = conex.executeDataSet("CALL usp_listModeloxMarca2(?)",
                        new Object[]{vehiculo.getIdMar()});
                indError = conex.getErrorSQL().trim();
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
                indError = "error";
                errores.add(e.getMessage());
            }
        }
        
        return "modelos";
    }

    public String listaVersiones() {
        helper conex = null;
        ResultSet tabla = null;

        try {
            conex = new helper();
            indError = conex.getErrorSQL().trim();
            if (!indError.equals("")) {
                errores.add(indError);
            } else {
                tabla = conex.executeDataSet("CALL usp_listVersionesxModeloxMarca(?,?)",
                        new Object[]{vehiculo.getIdMar(), vehiculo.getIdModMar()});
                indError = conex.getErrorSQL().trim();
                if (!indError.equals("")) {
                    errores.add(indError);
                } else {
                    Versiones obj;
                    while (tabla.next()) {
                        obj = new Versiones();
                        obj.setIdVerMod(tabla.getString("idVerMod"));
                        obj.setDesVerMod(tabla.getString("desVerMod"));
                        listVersiones.add(obj);
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
                indError = "error";
                errores.add(e.getMessage());
            }
        }

        return "versiones";
    }

    public String listaUbicaciones() {
        helper conex = null;
        ResultSet tabla = null;

        try {
            conex = new helper();
            indError = conex.getErrorSQL().trim();
            if (!indError.equals("")) {
                errores.add(indError);
            } else {
                tabla = conex.executeDataSet("CALL usp_listUbicacionesXAmbiente(?)",
                        new Object[]{ vehiculo.getIdAmbUbi() });
                indError = conex.getErrorSQL().trim();

                if (!indError.equals("")) {
                    errores.add(indError);
                } else {
                    UbicacionAmbiente obj;
                    while (tabla.next()) {
                        obj = new UbicacionAmbiente();
                        obj.setIdUbiAmb(tabla.getInt("idUbiAmb"));
                        obj.setDesUbiAmb(tabla.getString("desUbiAmb"));
                        listUbicaciones.add(obj);
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
                indError = "error";
                errores.add(e.getMessage());
            }
        }

        return "ubicaciones";
    }

    public String listColoresExt() {
        helper conex = null;
        ResultSet tabla = null;

        try {
            conex = new helper();
            indError = conex.getErrorSQL().trim();
            if (!indError.equals("")) {
                errores.add(indError);
            } else {
                tabla = conex.executeDataSet("CALL usp_listColoresExtxModel(?,?)",
                        new Object[]{vehiculo.getIdMar(), vehiculo.getIdModMar()});
                indError = conex.getErrorSQL().trim();
                if (!indError.equals("")) {
                    errores.add(indError);
                } else {
                    ColoresExterior obj;
                    while (tabla.next()) {
                        obj = new ColoresExterior();
                        obj.setIdColExt(tabla.getString("idColExt"));
                        obj.setDesColExt(tabla.getString("desColExt"));
                        listColoExt.add(obj);
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
                indError = "error";
                errores.add(e.getMessage());
            }
        }
        
        return "coloresext";
    }

    public String grabar() {
        idAccion = 5;
        verifAccionTipoUsuario();

        if (indErrAcc.equals("")) {
            if (vehiculo.getIdVeh().equals("")) {
                indError += "error";
                errores.add("Ingrese el código del vehiculo.");
            } else {
                vehiculo.setIdVeh(vehiculo.getIdVeh().toUpperCase());
                
                Pattern pat = null;
                Matcher mat = null;
                if (vehiculo.getIdVeh().trim().length() >= 6) {
                    if (vehiculo.getIdVeh().trim().length() == 6) {
                        pat = Pattern.compile("^([0-9]){6}");
                    }
                    if (vehiculo.getIdVeh().trim().length() == 7) {
                        pat = Pattern.compile("^([0-9]){6}[S]{1}$");
                    }


                    mat = pat.matcher(vehiculo.getIdVeh().trim());
                    if (!mat.find()) {
                        indError += "error";
                        errores.add("Número de serie no válido.");
                    }
                } else {
                    indError += "error";
                    errores.add("Número de serie no válido.");
                }
            }
            
            validaForm();

            if (indError.equals("")) {
                helper conex = null;
                ResultSet tabla = null;

                try {
                    conex = new helper();
                    indError = conex.getErrorSQL();

                    if (!indError.equals("")) {
                        errores.add(indError);
                    } else {
                        tabla = conex.executeDataSet("CALL usp_verifExistVehiculo(?)",
                                new Object[]{vehiculo.getIdVeh()});

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
                                errores.add("Ya existe un vehículo con el serie ingresada");
                            } else {
                                if(!isDouble(vehiculo.getImpTipCamFP())) {
                                    indError += "error";
                                    errores.add("Tipo de cambio no válido");
                                } else {
                                    if (vehiculo.getCodMonFacPrv().equals("1")) {
                                        if (Double.parseDouble(vehiculo.getImpTipCamFP())>0) {
                                            Double impTC = Double.parseDouble(vehiculo.getImpTipCamFP());
                                            Double impML = Double.parseDouble(vehiculo.getImpMonLoc());
                                            vehiculo.setImpMonExt(String.valueOf(impML / impTC));
                                        } else {
                                            vehiculo.setImpMonExt("0.00");
                                        }
                                    } else if (vehiculo.getCodMonFacPrv().equals("2")) {
                                        if (Double.parseDouble(vehiculo.getImpTipCamFP())>0) {
                                            Double impTC = Double.parseDouble(vehiculo.getImpTipCamFP());
                                            Double impME = Double.parseDouble(vehiculo.getImpMonExt());
                                            vehiculo.setImpMonExt(String.valueOf(impME * impTC));
                                        } else {
                                            vehiculo.setImpMonLoc("0.00");
                                        }
                                    }
                                }
                                
                                vehiculo.setFecFacPrv(getConvertFecha(vehiculo.getFecFacPrv(), 2));

                                indError = conex.executeNonQuery("CALL usp_insVehiculo(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)",
                                        new Object[]{vehiculo.getIdVeh(), vehiculo.getIdMar(), vehiculo.getIdModMar(), vehiculo.getIdVerMod(),
                                    vehiculo.getIdColExt(), vehiculo.getIdColInt(), vehiculo.getNumAnoFab(), vehiculo.getNumAnoMod(),
                                    vehiculo.getDesNumCha(), vehiculo.getDesNumMot(), vehiculo.getImpPreVenAsi(), vehiculo.getNumKimAct(),
                                    vehiculo.getIdAmbUbi(), vehiculo.getIdUbiAmb(), vehiculo.getDesPolImp(), vehiculo.getDesNumFacPrv(),
                                    vehiculo.getFecFacPrv(), vehiculo.getCodMonFacPrv(), vehiculo.getImpTipCamFP(), vehiculo.getImpMonLoc(),
                                    vehiculo.getImpMonExt(), sesion_sga.get("ses_idcon"), sesion_sga.get("ses_idloccon"),
                                    sesion_sga.get("ses_idusu").toString()});

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
    
    private void validaForm() {
        if (vehiculo.getIdMar().equals("")) {
            indError += "error";
            errores.add("Seleccione una marca.");
        }

        if (vehiculo.getIdModMar().equals("")) {
            indError += "error";
            errores.add("Seleccione un modelo.");
        }

        if (vehiculo.getIdVerMod().equals("") || vehiculo.getIdVerMod().equals("0")) {
            indError += "error";
            errores.add("Seleccione una versión.");
        }

        if (vehiculo.getIdColExt().equals("")) {
            indError += "error";
            errores.add("Seleccione un color de exterior.");
        }

        if (vehiculo.getIdColInt().equals("")) {
            indError += "error";
            errores.add("Seleccione un color de interior.");
        }

        if (vehiculo.getNumAnoFab().equals("0")) {
            indError += "error";
            errores.add("Ingrese el año de fabricación.");
        }

        if (vehiculo.getNumAnoMod().equals("0")) {
            indError += "error";
            errores.add("Ingrese el año del modelo.");
        }

        if (vehiculo.getDesNumCha().equals("")) {
            indError += "error";
            errores.add("Ingrese el número de chasis.");
        }

        if (vehiculo.getDesNumMot().equals("")) {
            indError += "error";
            errores.add("Ingrese el número motor.");
        }

        if(vehiculo.getIdAmbUbi()!=99 && vehiculo.getIdUbiAmb()==99) {
            indError += "error";
            errores.add("Debe seleccionar una ubicación.");
        }
        
        if(!isDouble(vehiculo.getImpTipCamFP())) {
            indError += "error";
            errores.add("Importe de tipo de cambio no válido.");
        } else {
            if(Double.parseDouble(vehiculo.getImpTipCamFP())<0) {
                indError += "error";
                errores.add("Importe de tipo de cambio no válido.");
            }
        }
        
        if(!isDouble(vehiculo.getImpMonLoc())) {
            indError += "error";
            errores.add("Importe en moneda local no válido.");
        } else {
            if(Double.parseDouble(vehiculo.getImpMonLoc())<0) {
                indError += "error";
                errores.add("Importe en moneda local no válido.");
            }
        }
        
        if(!isDouble(vehiculo.getImpMonLoc())) {
            indError += "error";
            errores.add("Importe en moneda extranjera no válido.");
        } else {
            if(Double.parseDouble(vehiculo.getImpMonLoc())<0) {
                indError += "error";
                errores.add("Importe en moneda extranjera no válido.");
            }
        }
    }
    
    public String actualizar() {
        idAccion = 6;
        verifAccionTipoUsuario();
        if (indErrAcc.equals("")) {
            validaForm();
            
            if (indError.equals("")) {
                helper conex = null;
            
                try {
                    conex = new helper();
                    indError += conex.getErrorSQL();

                    if (!indError.equals("")) {
                        errores.add(indError);
                    } else {
                        if (vehiculo.getCodMonFacPrv().equals("1")) {
                            if (!vehiculo.getImpTipCamFP().equals("0.0000")) {
                                Double impTC = Double.parseDouble(vehiculo.getImpTipCamFP());
                                Double impML = Double.parseDouble(vehiculo.getImpMonLoc());
                                vehiculo.setImpMonExt(String.valueOf(impML / impTC));
                            } else {
                                vehiculo.setImpMonExt("0.00");
                            }
                        } else if (vehiculo.getCodMonFacPrv().equals("2")) {
                            if (!vehiculo.getImpTipCamFP().equals("0.0000")) {
                                Double impTC = Double.parseDouble(vehiculo.getImpTipCamFP());
                                Double impME = Double.parseDouble(vehiculo.getImpMonExt());
                                vehiculo.setImpMonExt(String.valueOf(impME * impTC));
                            } else {
                                vehiculo.setImpMonLoc("0.00");
                            }
                        }
                        
                        vehiculo.setFecFacPrv(getConvertFecha(vehiculo.getFecFacPrv(), 2));
                        
                        indError += conex.executeNonQuery("CALL usp_updVehiculo(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)", 
                                new Object[]{ vehiculo.getIdVeh(),vehiculo.getIdMar(),vehiculo.getIdModMar(),vehiculo.getIdVerMod(),
                                    vehiculo.getIdColExt(),vehiculo.getIdColInt(),vehiculo.getNumAnoFab(),vehiculo.getNumAnoMod(),
                                    vehiculo.getDesNumCha(),vehiculo.getDesNumMot(),vehiculo.getImpPreVenAsi(),vehiculo.getNumKimAct(),
                                    vehiculo.getIdAmbUbi(),vehiculo.getIdUbiAmb(),vehiculo.getDesPolImp(),vehiculo.getDesNumFacPrv(),
                                    vehiculo.getFecFacPrv(),vehiculo.getCodMonFacPrv(),vehiculo.getImpTipCamFP(),vehiculo.getImpMonLoc(),
                                    vehiculo.getImpMonExt() });

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
                        tabla = conex.executeDataSet("CALL usp_verifDependVehiculo(?)",
                                new Object[]{ vehiculo.getIdVeh() });
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
                                indError += conex.executeNonQuery("CALL usp_dltVehiculo(?)",
                                        new Object[]{ vehiculo.getIdVeh().trim() });

                                indError = indError.trim();
                                if (indError.trim().equals("")) {
                                    errores.add(indError);
                                }
                            } else /* si tiene dependencias */ {
                                indError = "error";
                                errores.add("Existen registros dependientes del vehículo");
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

    public String obtPrecVentAsi() {
        helper conex = null;
        ResultSet tabla = null;

        try {
            conex = new helper();
            indError += conex.getErrorSQL().trim();

            if (!indError.trim().equals("")) {
                errores.add(indError);
            } else {
                tabla = conex.executeDataSet("CALL usp_getDatosPrecioLista(?,?,?,?)",
                        new Object[]{vehiculo.getIdMar(), vehiculo.getIdModMar(), vehiculo.getIdVerMod(),
                    vehiculo.getNumAnoFab()});

                indError += conex.getErrorSQL();

                if (!indError.equals("")) {
                    errores.add(indError);
                } else {
                    while (tabla.next()) {
                        vehiculo.setImpPreVenAsi(tabla.getString("impPreLis"));
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

        return "precventasi";
    }

    public String detalle() {
        idAccion = 8;
        verifAccionTipoUsuario();
        if (indErrAcc.equals("")) {
           nivBandeja = 1;

            if (!opcion.trim().equals("D") || vehiculo.getIdVeh().equals("")) {
                indErrParm = "error";
            } else {
                varReturnProcess(1);

                accion = "Detalle";
                
                getDatosFullVehiculo();
                listCampaniasVehiculoIndex();
            } 
        }
        
        return "detalle";
    }
    
    private void getDatosFullVehiculo() {
        helper conex = null;
        ResultSet tabla = null;
        
        try {
            conex = new helper();
            indError = conex.getErrorSQL();
            
            if(!indError.equals("")) {
                errores.add(indError);
            } else {
                tabla = conex.executeDataSet("CALL usp_getDatosFullVehiculo(?)", 
                        new Object[]{ vehiculo.getIdVeh() });
                indError = conex.getErrorSQL();
                
                if(!indError.equals("")) {
                    errores.add(indError);
                } else {
                    while(tabla.next()) {
                        vehiculo.setIdVeh(tabla.getString("idVeh"));
                        vehiculo.setIdMar(tabla.getString("idMar"));
                        vehiculo.setDesMar(tabla.getString("desMar"));
                        vehiculo.setIdModMar(tabla.getString("idModMar"));
                        vehiculo.setDesModMar(tabla.getString("desModMar"));
                        vehiculo.setIdVerMod(tabla.getString("idVerMod"));
                        vehiculo.setDesVerMod(tabla.getString("desVerMod"));
                        vehiculo.setDesColExt(tabla.getString("desColExt"));
                        vehiculo.setDesColInt(tabla.getString("desColInt"));
                        vehiculo.setNumAnoFab(tabla.getString("numAnoFab"));
                        vehiculo.setNumAnoMod(tabla.getString("numAnoMod"));
                        vehiculo.setDesNumCha(tabla.getString("desNumCha"));
                        vehiculo.setDesNumMot(tabla.getString("desNumMot"));
                        vehiculo.setImpPreVenAsi(tabla.getString("impPreVenAsi"));
                        vehiculo.setNumKimAct(tabla.getString("numKimAct"));
                        vehiculo.setIdAmbUbi(tabla.getInt("idAmbUbi"));
                        vehiculo.setDesAmbUbi(tabla.getString("desAmbUbi"));
                        vehiculo.setIdUbiAmb(tabla.getInt("idUbiAmb"));
                        vehiculo.setDesUbiAmb(tabla.getString("desUbiAmb"));
                        vehiculo.setCodSitVeh(tabla.getString("codSitVeh"));
                        vehiculo.setDesSitVeh(tabla.getString("desSitVeh"));
                        vehiculo.setFecSitVeh(tabla.getString("fecSitVeh"));
                        vehiculo.setDesNumPla(tabla.getString("desNumPla"));
                        vehiculo.setDesPolImp(tabla.getString("desPolImp"));
                        vehiculo.setDesNumFacPrv(tabla.getString("desNumFacPrv"));
                        vehiculo.setFecFacPrv(tabla.getString("fecFacPrv"));
                        vehiculo.setDesMonFacPrv(tabla.getString("desMonFacPrv"));
                        vehiculo.setImpTipCamFP(tabla.getString("impTipCamFP"));
                        vehiculo.setImpMonLoc(tabla.getString("impMonLoc"));
                        vehiculo.setImpMonExt(tabla.getString("impMonExt"));
                        vehiculo.setCodSitVen(tabla.getString("codSitVen"));
                        vehiculo.setDesSitVen(tabla.getString("desSitVen"));
                        vehiculo.setFecSitVen(tabla.getString("fecSitVen"));
                        vehiculo.setIdModVen(tabla.getInt("idModVen"));
                        vehiculo.setDesModVen(tabla.getString("desModVen"));
                        vehiculo.setIdCli(tabla.getString("idCli"));
                        vehiculo.setDesCli(tabla.getString("desCli"));
                        vehiculo.setIdNumIntRV(tabla.getString("idNumIntRV"));
                        vehiculo.setDesTipDocVen(tabla.getString("desTipDocVen"));
                        vehiculo.setDesNumDocVen(tabla.getString("desNumDocVen"));
                        vehiculo.setFecEmiDocVen(tabla.getString("fecEmiDocVen"));
                        vehiculo.setFecEntCli(tabla.getString("fecEntCli"));
                        vehiculo.setDesObs(tabla.getString("desObs"));
                        vehiculo.setDesCon(tabla.getString("desCon"));
                        vehiculo.setDesLocCon(tabla.getString("desLocCon"));
                        vehiculo.setIndVeh(tabla.getString("indVeh"));
                        vehiculo.setEdoVeh(tabla.getString("edoVeh"));
                        vehiculo.setFecReg(tabla.getString("fecReg"));
                        vehiculo.setIdUsu(tabla.getString("idUsu"));
                        vehiculo.setDesUsu(tabla.getString("desUsu"));
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
    
    public String bandejaAdmin() {
        idAccion = 9;
        verifAccionTipoUsuario();
        if (indErrAcc.equals("")) {
            tituloOpc = "Vehículos";
            urlPaginacion = "vehiculos/bandejaAdminVehiculo";

            varReturnProcess(0);
            if (!listVarReturn.isEmpty()) {
                curPagVis = Integer.parseInt(listVarReturn.get(0).toString().trim());
                vehiculo.setIdVeh_f(listVarReturn.get(1).toString().trim());
                vehiculo.setDesNumCha_f(listVarReturn.get(2).toString().trim());
                vehiculo.setDesNumMot_f(listVarReturn.get(3).toString().trim());
                vehiculo.setDesVeh_f(listVarReturn.get(4).toString().trim());
                vehiculo.setDesCli_f(listVarReturn.get(5).toString().trim());
                vehiculo.setCodSitVeh_f(listVarReturn.get(6).toString().trim());
                vehiculo.setCodSitVen_f(listVarReturn.get(7).toString().trim());
                vehiculo.setDesNumPla_f(listVarReturn.get(8).toString().trim());
            }

            cantVehiculosIndex();
            verifPag();
            listVehiculoIndex();
            
            listSituacionVehiculo.add(new SituacionVehiculo("L", "Libre"));
            listSituacionVehiculo.add(new SituacionVehiculo("B", "Bloqueado"));
            listSituacionVehiculo.add(new SituacionVehiculo("X", "Exhibicion"));
            listSituacionVehiculo.add(new SituacionVehiculo("S", "Separado"));
            listSituacionVehiculo.add(new SituacionVehiculo("E", "Endosado"));
            listSituacionVehiculo.add(new SituacionVehiculo("K", "Booking"));
            listSituacionVehiculo.add(new SituacionVehiculo("V", "Vendido"));
            
            listSituacionVentaVehiculo.add(new SituacionVentaVehiculo("D", "Disponible"));
            listSituacionVentaVehiculo.add(new SituacionVentaVehiculo("E", "Endosado"));
            listSituacionVentaVehiculo.add(new SituacionVentaVehiculo("V", "Vendido"));
            listSituacionVentaVehiculo.add(new SituacionVentaVehiculo("A", "Activado"));
        }

        return "bandejaAdmin";
    }
    
    private void listCampaniasVehiculoIndex() {
        helper conex = null;
        ResultSet tabla = null;
        
        try {
            conex = new helper();
            
            if(!conex.getErrorSQL().equals("")) {
                errores.add(conex.getErrorSQL());
            } else {
                tabla = conex.executeDataSet("CALL usp_listCampaniasVehiculoIndex(?,?,?)", 
                        new Object[]{ vehiculo.getIdVeh(),0,15 });
                indError = conex.getErrorSQL();
                
                if(!indError.equals("")) {
                    errores.add(indError);
                } else {
                    Campania obj;
                    
                    while(tabla.next()) {
                        obj = new Campania();
                        obj.setIdOriCam(tabla.getInt("idOriCam"));
                        obj.setDesOriCam(tabla.getString("desOriCam"));
                        obj.setIdCam(tabla.getInt("idCam"));
                        obj.setIdCamStr(obj.getIdOriCam()+"-"+obj.getIdCam());
                        obj.setDesCam(tabla.getString("desCam"));
                        obj.setImpRelCam(tabla.getString("impRelCam"));
                        obj.setDesMonCam(tabla.getString("desMonCam"));
                        obj.setImpRelCam(obj.getDesMonCam()+obj.getImpRelCam());
                        obj.setIndCamVeh(tabla.getString("indCamVeh"));
                        listCampanias.add(obj);
                    }
                }
            }
        } catch (Exception e) {
            errores.add(e.getMessage());
        } finally {
            try {
                tabla.close();
                conex.returnConnect();
            } catch (Exception e) {
            }
        }
        
        if(!errores.isEmpty()) {
            indErrorTot = "error";
        }
    }
    
    public String updEstadoCampania() {
        idAccion = 11;
        verifAccionTipoUsuario();
        if (indErrAcc.equals("")) {
            helper conex = null;
            
            try {
                conex = new helper();
                indError = conex.getErrorSQL();
                
                if(!indError.equals("")) {
                    errores.add(indError);
                } else {
                    String[] arr = campania.getIdCamStr().split("-");
                    campania.setIdOriCam(Integer.parseInt(arr[0].toString()));
                    campania.setIdCam(Integer.parseInt(arr[1].toString()));

                    indError = conex.executeNonQuery("CALL usp_updEstadoCampaniaVehiculo(?,?,?,?)", 
                            new Object[]{ vehiculo.getIdVeh(),campania.getIdOriCam(),campania.getIdCam(),
                                campania.getIndCamVeh() });

                    if(!indError.equals("")) {
                        errores.add(indError);
                    }
                }
                
            } catch (Exception e) {
                errores.add(e.getMessage());
            } finally {
                conex.returnConnect();
            }
            
            if(!errores.isEmpty()) {
                indErrorTot = "error";
            }
        }
        
        return "updEstadoCampania";
    }
    
    public String activar() {
        idAccion = 14;
        verifAccionTipoUsuario();
        
        if (indErrAcc.equals("")) {
            if(opcion.equals("A")) {
                helper conex = null;
                ResultSet tabla = null;

                try {
                    conex = new helper();
                    indError = conex.getErrorSQL();

                    if(!indError.contains("")) {
                        errores.add(indError);
                    } else {
                        tabla = conex.executeDataSet("CALL usp_getDatosVehiculo(?)", 
                                new Object[]{ vehiculo.getIdVeh() });
                        indError = conex.getErrorSQL();

                        if(!indError.equals("")) {
                            errores.add(indError);
                        } else {
                            while(tabla.next()) {
                                vehiculo.setCodSitVeh(tabla.getString("codSitVeh"));
                            }

                            if(!vehiculo.getCodSitVeh().equals("L")) {
                                errores.add("La situación del vehículo no es libre");
                            }
                            
                            if(errores.isEmpty()) {
                                indError = conex.executeNonQuery("CALL usp_updActivarSituacionVehiculoYVenta(?,?,?,?)", 
                                        new Object[]{ vehiculo.getIdVeh(),"B",sesion_sga.get("ses_idusu"),
                                            "A" });
                                
                                if(!indError.equals("")) {
                                    errores.add(indError);
                                }
                            }
                        }
                    }
                } catch (Exception e) {
                    errores.add(e.getMessage());
                } finally {
                    try {
                        tabla.close();
                        conex.returnConnect();
                    } catch (Exception e) {
                    }
                }

                if(!errores.isEmpty()) {
                    indErrorTot = "error";
                }
            }
        }
        
        return "activar";
    }
    
    public String actualizar3() {
        idAccion = 15;
        verifAccionTipoUsuario();
        if (indErrAcc.equals("")) {
            if (indError.equals("")) {
                helper conex = null;
            
                try {
                    conex = new helper();
                    indError += conex.getErrorSQL();

                    if (!indError.equals("")) {
                        errores.add(indError);
                    } else {
                        String idUsuSit = "";
                        
                        if(vehiculo.getCodSitVeh().equals("L")) {
                            idUsuSit = "";
                        } else {
                            idUsuSit = sesion_sga.get("ses_idusu").toString();
                        }
                        
                        indError += conex.executeNonQuery("CALL usp_updActivarSituacionVehiculoYVenta(?,?,?,?)", 
                                new Object[]{ vehiculo.getIdVeh(),vehiculo.getCodSitVeh(),idUsuSit,
                                    vehiculo.getCodSitVen() });

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
    
    public String verificaSituacion(){
        helper objm=null;
        ResultSet tabla=null;

        try {
            objm=new helper();
            tabla=objm.executeDataSet("CALL usp_verfEdoVehiculo(?)", new Object[]{ vehiculo.getIdVeh() });

            String situacion = "";
            
            while (tabla.next()) {
                situacion = tabla.getString("codSitVeh");
            }

            if(!situacion.equals("L")){
                indError="La situación del vehículo no es libre";
                errores.add(indError);
            }
        } catch (Exception e) {
        }finally{
            try {
                tabla.close();
                objm.returnConnect();
            } catch (Exception e) {
            }
        }

        return "verificaSituacion";
    }
    
    public String verificaSituacionVenta(){
        helper objm=null;
        ResultSet tabla=null;

        try {
            objm=new helper();
            tabla=objm.executeDataSet("CALL usp_verfEdoVehiculo(?)", new Object[]{ vehiculo.getIdVeh() });

            String situacion = "";
            
            while (tabla.next()) {
                situacion = tabla.getString("codSitVen");
            }

            if(situacion.equals("A")){
                errores.add("El vehículo forma parte del activo de la empresa");
            }
        } catch (Exception e) {
        }finally{
            try {
                tabla.close();
                objm.returnConnect();
            } catch (Exception e) {
            }
        }
        
        if(!errores.isEmpty()) {
            indErrorTot = "error";
        }

        return "verificaSituacion";
    }
    
    public  String bandejaVentas(){
        idAccion = 10;
        
        verifAccionTipoUsuario();
        if (indErrAcc.equals("")) {
            tituloOpc="Venta de Vehículos";
            indModificar = 2;
            indDetalle = 2;
            urlPaginacion = "vehiculos/bandejaVentasVehiculo";

            varReturnProcess(0);
            if (!listVarReturn.isEmpty()) {
                curPagVis = Integer.parseInt(listVarReturn.get(0).toString().trim());
                vehiculo.setIdVeh_f(listVarReturn.get(1).toString().trim());
                vehiculo.setDesNumCha_f(listVarReturn.get(2).toString().trim());
                vehiculo.setDesNumMot_f(listVarReturn.get(3).toString().trim());
                vehiculo.setDesVeh_f(listVarReturn.get(4).toString().trim());
                vehiculo.setDesCli_f(listVarReturn.get(5).toString().trim());
                vehiculo.setCodSitVeh_f(listVarReturn.get(6).toString().trim());
                vehiculo.setCodSitVen_f(listVarReturn.get(7).toString().trim());
                vehiculo.setDesNumPla_f(listVarReturn.get(8).toString().trim());
            }
            
            cantVehiculosIndex();
            verifPag();
            listVehiculoIndex();
            
            listSituacionVehiculo.add(new SituacionVehiculo("L", "Libre"));
            listSituacionVehiculo.add(new SituacionVehiculo("B", "Bloqueado"));
            listSituacionVehiculo.add(new SituacionVehiculo("X", "Exhibicion"));
            listSituacionVehiculo.add(new SituacionVehiculo("S", "Separado"));
            listSituacionVehiculo.add(new SituacionVehiculo("E", "Endosado"));
            listSituacionVehiculo.add(new SituacionVehiculo("K", "Booking"));
            listSituacionVehiculo.add(new SituacionVehiculo("V", "Vendido"));
            
            listSituacionVentaVehiculo.add(new SituacionVentaVehiculo("D", "Disponible"));
            listSituacionVentaVehiculo.add(new SituacionVentaVehiculo("E", "Endosado"));
            listSituacionVentaVehiculo.add(new SituacionVentaVehiculo("V", "Vendido"));
            listSituacionVentaVehiculo.add(new SituacionVentaVehiculo("A", "Activado"));
        }

        return "bandejaVentas";
    }
    
    public void listModalidadVenta(){
        helper conex = null;
        ResultSet tabla = null;
        
        try {
            conex = new helper();
            indError = conex.getErrorSQL();
            
            if(!indError.equals("")) {
                errores.add(indError);
            } else {
                tabla = conex.executeDataSet("CALL usp_listaModalidadVenta()", new Object[]{});
                indError = conex.getErrorSQL();
                
                if(!indError.equals("")) {
                    errores.add(indError);
                } else {
                    ModalidadVenta obj;
                    while(tabla.next()) {
                        obj = new ModalidadVenta();
                        obj.setIdModVen(tabla.getInt("idModVen"));
                        obj.setDesModVen(tabla.getString("desModVen"));
                        listModalidadVenta.add(obj);
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
    
    public String listClientes(){
        idAccion = 12;
        verifAccionTipoUsuario();

        if (indErrAcc.equals("")) {
            regPag = 13;
            urlPaginacion = "vehiculos/listClientesVehiculo";
            divPopUp = "DIVclientes";

            cantClientesIndex();
            verifPag();
            listClientesIndex();
        }
      
        return "listClientes";
    }
    
    private void cantClientesIndex() {
        helper conex = null;
        ResultSet tabla = null;

        try {
            conex = new helper();
            indError = conex.getErrorSQL();

            if (!indError.equals("")) {
                errores.add(indError);
            } else {
                tabla = conex.executeDataSet("CALL usp_cantClientesIndex(?,?)",
                        new Object[]{ "",vehiculo.getDesCli_f()});

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
  
    private void listClientesIndex() {
        helper conex = null;
        ResultSet tabla = null;

        try {
            conex = new helper();
            indError = conex.getErrorSQL();

            if (!indError.equals("")) {
                errores.add(indError);
            } else {
                tabla = conex.executeDataSet("CALL usp_listClientesIndex(?,?,?,?)",
                        new Object[]{ "",vehiculo.getDesCli_f(),getCurPag()*regPag, regPag});

                indError = conex.getErrorSQL();

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
    
    public String listCampaniasVersion() {
        idAccion = 13;
        verifAccionTipoUsuario();

        if (indErrAcc.equals("")) {
            regPag = 13;
            urlPaginacion = "vehiculos/listCampaniasVersionVehiculo";
            divPopUp = "DIVcampanias";
            sufijoPopUp = "Camp";

            cantCampaniasVersionIndex();
            verifPag();
            listCampaniasVersionIndex();
        }
      
        return "listCampaniasVersion";
    }
    
    private void cantCampaniasVersionIndex(){
        helper conex = null;
        ResultSet tabla = null;

        try {
            conex = new helper();
            indError = conex.getErrorSQL();

            if (!indError.equals("")) {
                errores.add(indError);
            } else {
                tabla = conex.executeDataSet("CALL usp_cantCampaniasVersion(?,?,?,?,?)",
                        new Object[]{ vehiculo.getIdMar(),vehiculo.getIdModMar(),vehiculo.getIdVerMod(),
                            vehiculo.getNumAnoFab(),vehiculo.getIdVeh() });

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
  
    public void listCampaniasVersionIndex(){
        helper conex = null;
        ResultSet tabla = null;

        try {
            conex = new helper();
            indError = conex.getErrorSQL();

            if (!indError.equals("")) {
                errores.add(indError);
            } else {
                tabla = conex.executeDataSet("CALL usp_listCampaniasVersion(?,?,?,?,?,?,?)",
                        new Object[]{ vehiculo.getIdMar(),vehiculo.getIdModMar(),vehiculo.getIdVerMod(),
                            vehiculo.getNumAnoFab(),vehiculo.getIdVeh(),getCurPag()*regPag, regPag });

                indError = conex.getErrorSQL();

                if (!indError.equals("")) {
                    errores.add(indError);
                } else {
                    Campania obj;
                    while (tabla.next()) {
                        obj = new Campania();
                        obj.setIdOriCam(tabla.getInt("idOriCam"));
                        obj.setDesOriCam(tabla.getString("desOriCam"));
                        obj.setIdCam(tabla.getInt("idCam"));
                        obj.setIdCamStr(obj.getIdOriCam()+"-"+obj.getIdCam());
                        obj.setDesCam(tabla.getString("desCam"));
                        listCampanias.add(obj);
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
    
    private void validaFormVentaVeh() {
        if(vehiculo.getCodSitVeh().equals("K")) {
            if( vehiculo.getIdModVen()==0 ){
                indError="error";
                errores.add("Seleccione la modalidad de pago");
            }
          
            if(vehiculo.getIdCli().equals("")){
                indError="error";
                errores.add("Seleccione un cliente");
            }
        } else {
            vehiculo.setIdCli("9999999999");
            vehiculo.setIdModVen(0);
        }
      
        if(vehiculo.getCodSitVen().equals("V")) {               
            if(!vehiculo.getFecEntCli().equals("")) {
                if(isDate(vehiculo.getFecEntCli(), 2)) {
                    vehiculo.setFecEntCli(getConvertFecha( vehiculo.getFecEntCli() , 2));
                } else {
                    indError="error";
                    errores.add("Fecha de entrega no valida");
                }
            }
            
            if(vehiculo.getDesObs().length()>200) {
                vehiculo.setDesObs(vehiculo.getDesObs().substring(0,200));
            }                    
        }
    }
    
    public String actualizar2() {
        idAccion = 6;
        verifAccionTipoUsuario();
        if (indErrAcc.equals("")) {
            
            helper conex = null;

            try {
                conex = new helper();
                indError = conex.getErrorSQL();

                if(!indError.equals("")) {
                    errores.add(indError);
                } else {
                    validaFormVentaVeh();
                        
                    if(indError.equals("")){
                        /* Actualizar */
                        indError = conex.executeNonQuery("CALL usp_updVentaVehiculo(?,?,?,?,?,?,?,?)",
                                new Object[]{ vehiculo.getIdVeh(),vehiculo.getCodSitVeh(),vehiculo.getIdCli(),
                                            vehiculo.getIdModVen(),vehiculo.getDesNumPla(),vehiculo.getFecEntCli(),
                                            vehiculo.getDesObs(),sesion_sga.get("ses_idusu") });
                        if (!indError.equals("")) {
                            errores.add(indError);
                        } else {
                            grabarCampanias();
                        }
                    }
                }
            } catch (Exception e) {
                errores.add(e.getMessage());
            } finally {
                conex.returnConnect();
            }
        }
        
        return "actualizar2";
    }
    
    public void grabarCampanias() {
        helper cone=null;

        try {
            cone=new helper();

            if(vehiculo.getCodSitVeh().equals("K")){
                for (int i = 0; i < campania.getListIdCamStr().length; i++) {

                    String arrIdCam[]=campania.getListIdCamStr()[i].split("-");
                    
                    indError = cone.executeNonQuery("CALL usp_insCampaniaVehiculo(?,?,?)", 
                            new Object[]{ vehiculo.getIdVeh(),arrIdCam[0],arrIdCam[1]  });

                    if(!indError.equals("")){
                        errores.add(indError);
                    }
                }
            }
        } catch (Exception e) {
            errores.add(e.getMessage());
        }finally{
            cone.returnConnect();
        }
        
        if(!errores.isEmpty())
            indErrorTot = "error";
    }
  
    /**
     * @return the vehiculo
     */
    public Vehiculos getVehiculo() {
        return vehiculo;
    }

    /**
     * @param vehiculo the vehiculo to set
     */
    public void setVehiculo(Vehiculos vehiculo) {
        this.vehiculo = vehiculo;
    }

    /**
     * @return the listvehiculos
     */
    public ArrayList<Vehiculos> getListVehiculos() {
        return listVehiculos;
    }

    /**
     * @return the listMarcas
     */
    public ArrayList<Marcas> getListMarcas() {
        return listMarcas;
    }

    /**
     * @return the listVersiones
     */
    public ArrayList<Versiones> getListVersiones() {
        return listVersiones;
    }

    /**
     * @return the listColorInt
     */
    public ArrayList<ColorInteior> getListColorInt() {
        return listColorInt;
    }

    /**
     * @return the listColoExt
     */
    public ArrayList<ColoresExterior> getListColoExt() {
        return listColoExt;
    }

    /**
     * @return the listAmbientes
     */
    public ArrayList<AmbienteUbicacion> getListAmbientes() {
        return listAmbientes;
    }

    /**
     * @return the listUbicaciones
     */
    public ArrayList<UbicacionAmbiente> getListUbicaciones() {
        return listUbicaciones;
    }

    /**
     * @return the listModelos
     */
    public ArrayList<Modelos> getListModelos() {
        return listModelos;
    }

    /**
     * @return the listCampanias
     */
    public ArrayList<Campania> getListCampanias() {
        return listCampanias;
    }
    
    /**
     * @return the listConcesionarios
     */
    public ArrayList<Concesionarios> getListConcesionarios() {
        return listConcesionarios;
    }

    /**
     * @return the listLocales
     */
    public ArrayList<Locales> getListLocales() {
        return listLocales;
    }

    /**
     * @return the listClientes
     */
    public ArrayList<Clientes> getListClientes() {
        return listClientes;
    }


    /**
     * @return the listTipDocVent
     */
    public ArrayList<TipoDocumentoVenta> getListTipoDocVenta() {
        return listTipoDocVenta;
    }

    public Campania getCampania() {
        return campania;
    }

    public void setCampania(Campania campania) {
        this.campania = campania;
    }

    public ArrayList<SituacionVehiculo> getListSituacionVehiculo() {
        return listSituacionVehiculo;
    }

    public ArrayList<SituacionVentaVehiculo> getListSituacionVentaVehiculo() {
        return listSituacionVentaVehiculo;
    }

    public ArrayList<ModalidadVenta> getListModalidadVenta() {
        return listModalidadVenta;
    }
}
