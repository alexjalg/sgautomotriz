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
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author Angel
 */
public class VehiculosAction extends MasterAction implements ModelDriven<Vehiculos> {

    private Map<String, Object> sesion_sga = ActionContext.getContext().getSession();
    private Vehiculos vehiculo = new Vehiculos();
    private ArrayList<Vehiculos> listvehiculos = new ArrayList<Vehiculos>();
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
                tabla = conex.executeDataSet("CALL usp_cantVehiculoIndex(?,?,?,?,?,?,?,?,?,?)",
                        new Object[]{ vehiculo.getIdVeh_f(),vehiculo.getDesNumCha_f(),vehiculo.getDesNumMot_f(),
                            vehiculo.getFecFacPrv_f(),vehiculo.getDesCli_f(),vehiculo.getFecEmiDocVen_f(),
                            vehiculo.getIdTipDocVen_f(),vehiculo.getDesNumDocVen_f(),vehiculo.getDesNumPla_f(),
                            vehiculo.getFecEntCli_f() });
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
                tabla = conex.executeDataSet("CALL usp_listVehiculoIndex(?,?,?,?,?,?,?,?,?,?,?,?)",
                        new Object[]{ vehiculo.getIdVeh_f(),vehiculo.getDesNumCha_f(),vehiculo.getDesNumMot_f(),
                            vehiculo.getFecFacPrv_f(),vehiculo.getDesCli_f(),vehiculo.getFecEmiDocVen_f(),
                            vehiculo.getIdTipDocVen_f(),vehiculo.getDesNumDocVen_f(),vehiculo.getDesNumPla_f(),
                            vehiculo.getFecEntCli_f(),getCurPag()*getRegPag(),getRegPag() });
                
                indError = conex.getErrorSQL().trim();
                if (!indError.equals("")) {
                    errores.add(indError);
                } else {
                    Vehiculos obj;
                    while (tabla.next()) {
                        obj = new Vehiculos();

                        obj.setIdVeh(tabla.getString("idVeh"));
                        obj.setDesNumCha(tabla.getString("desNumCha"));
                        obj.setDesNumMot(tabla.getString("desNumMot"));
                        obj.setFecFacPrv(tabla.getString("fecFacPrv"));
                        obj.setDesCli(tabla.getString("desCli"));
                        obj.setFecEmiDocVen(tabla.getString("fecEmiDocVen"));
                        obj.setDesTipDocVen(tabla.getString("desTipDocVen"));
                        obj.setDesNumDocVen(tabla.getString("desNumDocVen"));
                        obj.setDesNumPla(tabla.getString("desNumPla"));
                        obj.setFecEntCli(tabla.getString("fecEntCli"));
                        listvehiculos.add(obj);
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
    
    public String modificar() {
        idAccion = 4;
        verifAccionTipoUsuario();

        if (indErrAcc.equals("")) {
            nivBandeja = 1;

            if (!opcion.trim().equals("M") || vehiculo.getIdVeh().equals("")) {
                indErrParm = "error";
            } else {
                varReturnProcess(1);

                accion = "Adicionar";
                
                getDatosVehiculo();
                populateForm();

                formURL = baseURL + "vehiculos/actualizarVehiculo";
            }
        }

        return "modificar";
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
                        vehiculo.setDesMar(tabla.getString("desMar"));
                        vehiculo.setDesModMar(tabla.getString("desModMar"));
                        vehiculo.setDesVerMod(tabla.getString("desVerMod"));
                        vehiculo.setDesColExt(tabla.getString("desColExt"));
                        vehiculo.setDesColInt(tabla.getString("desColInt"));
                        vehiculo.setNumAnoFab(tabla.getString("numAnoFab"));
                        vehiculo.setNumAnoMod(tabla.getString("numAnoMod"));
                        vehiculo.setDesNumCha(tabla.getString("desNumCha"));
                        vehiculo.setDesNumMot(tabla.getString("desNumMot"));
                        vehiculo.setImpPreVenAsi(tabla.getString("impPreVenAsi"));
                        vehiculo.setNumKimAct(tabla.getString("numKimAct"));
                        vehiculo.setDesAmbUbi(tabla.getString("desAmbUbi"));
                        vehiculo.setDesUbiAmb(tabla.getString("desUbiAmb"));
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
    
    public String listadoPropositosVarios() {
        idAccion = 9;
        verifAccionTipoUsuario();
        if (indErrAcc.equals("")) {
            urlPaginacion = "vehiculos/listadoPropositosVariosVehiculo";

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

        return "listadoPropositosVarios";
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
    public ArrayList<Vehiculos> getListvehiculos() {
        return listvehiculos;
    }

    /**
     * @param listvehiculos the listvehiculos to set
     */
    public void setListvehiculos(ArrayList<Vehiculos> listvehiculos) {
        this.listvehiculos = listvehiculos;
    }

    /**
     * @return the listMarcas
     */
    public ArrayList<Marcas> getListMarcas() {
        return listMarcas;
    }

    /**
     * @param listMarcas the listMarcas to set
     */
    public void setListMarcas(ArrayList<Marcas> listMarcas) {
        this.listMarcas = listMarcas;
    }

    /**
     * @return the listVersiones
     */
    public ArrayList<Versiones> getListVersiones() {
        return listVersiones;
    }

    /**
     * @param listVersiones the listVersiones to set
     */
    public void setListVersiones(ArrayList<Versiones> listVersiones) {
        this.listVersiones = listVersiones;
    }

    /**
     * @return the listColorInt
     */
    public ArrayList<ColorInteior> getListColorInt() {
        return listColorInt;
    }

    /**
     * @param listColorInt the listColorInt to set
     */
    public void setListColorInt(ArrayList<ColorInteior> listColorInt) {
        this.listColorInt = listColorInt;
    }

    /**
     * @return the listColoExt
     */
    public ArrayList<ColoresExterior> getListColoExt() {
        return listColoExt;
    }

    /**
     * @param listColoExt the listColoExt to set
     */
    public void setListColoExt(ArrayList<ColoresExterior> listColoExt) {
        this.listColoExt = listColoExt;
    }

    /**
     * @return the listAmbientes
     */
    public ArrayList<AmbienteUbicacion> getListAmbientes() {
        return listAmbientes;
    }

    /**
     * @param listAmbientes the listAmbientes to set
     */
    public void setListAmbientes(ArrayList<AmbienteUbicacion> listAmbientes) {
        this.listAmbientes = listAmbientes;
    }

    /**
     * @return the listUbicaciones
     */
    public ArrayList<UbicacionAmbiente> getListUbicaciones() {
        return listUbicaciones;
    }

    /**
     * @param listUbicaciones the listUbicaciones to set
     */
    public void setListUbicaciones(ArrayList<UbicacionAmbiente> listUbicaciones) {
        this.listUbicaciones = listUbicaciones;
    }

    /**
     * @return the listModelos
     */
    public ArrayList<Modelos> getListModelos() {
        return listModelos;
    }

    /**
     * @param listModelos the listModelos to set
     */
    public void setListModelos(ArrayList<Modelos> listModelos) {
        this.listModelos = listModelos;
    }

    /**
     * @return the listCampanias
     */
    public ArrayList<Campania> getListCampanias() {
        return listCampanias;
    }

    /**
     * @param listCampanias the listCampanias to set
     */
    public void setListCampanias(ArrayList<Campania> listCampanias) {
        this.listCampanias = listCampanias;
    }

    /**
     * @return the listConcesionarios
     */
    public ArrayList<Concesionarios> getListConcesionarios() {
        return listConcesionarios;
    }

    /**
     * @param listConcesionarios the listConcesionarios to set
     */
    public void setListConcesionarios(ArrayList<Concesionarios> listConcesionarios) {
        this.listConcesionarios = listConcesionarios;
    }

    /**
     * @return the listLocales
     */
    public ArrayList<Locales> getListLocales() {
        return listLocales;
    }

    /**
     * @param listLocales the listLocales to set
     */
    public void setListLocales(ArrayList<Locales> listLocales) {
        this.listLocales = listLocales;
    }

    /**
     * @return the listClientes
     */
    public ArrayList<Clientes> getListClientes() {
        return listClientes;
    }

    /**
     * @param listClientes the listClientes to set
     */
    public void setListClientes(ArrayList<Clientes> listClientes) {
        this.listClientes = listClientes;
    }

    /**
     * @return the listTipDocVent
     */
    public ArrayList<TipoDocumentoVenta> getListTipoDocVenta() {
        return listTipoDocVenta;
    }

    /**
     * @param listTipDocVent the listTipDocVent to set
     */
    public void setListTipoDocVenta(ArrayList<TipoDocumentoVenta> listTipoDocVenta) {
        this.listTipoDocVenta = listTipoDocVenta;
    }
}
