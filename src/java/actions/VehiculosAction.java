/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package actions;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ModelDriven;
import conexion.helper;
import entities.Usuarios;

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
import entities.Clientes;
import entities.Distritos;

/**
 *
 * @author Angel
 */
public class VehiculosAction extends MasterAction implements ModelDriven<Vehiculos>{
    
    
    private Map<String, Object> sesion_sga = ActionContext.getContext().getSession();
    private Vehiculos vehiculo=new Vehiculos();
    private ArrayList<Vehiculos> listvehiculos=new ArrayList<Vehiculos>();
    private ArrayList<Marcas> listMarcas=new ArrayList<Marcas>();
    private ArrayList<Modelos> listModelos=new ArrayList<Modelos>();
    private ArrayList<Versiones> listVersiones=new ArrayList<Versiones>();
    private ArrayList<ColorInteior> listColorInt=new ArrayList<ColorInteior>();
    private ArrayList<ColoresExterior> listColoExt=new ArrayList<ColoresExterior>();
    private ArrayList<AmbienteUbicacion> listAmbientes=new ArrayList<AmbienteUbicacion>();
    private ArrayList<UbicacionAmbiente> listUbicaciones=new ArrayList<UbicacionAmbiente>();
    private ArrayList<Campania> listCampanias=new ArrayList<Campania>();
    private ArrayList<Concesionarios> listConcesionarios=new ArrayList<Concesionarios>();
    private ArrayList<Locales> listLocales=new ArrayList<Locales>();
    private ArrayList<Clientes> listClientes=new ArrayList<Clientes>();
   
    @Override 
    public Vehiculos getModel(){
        
        tituloOpc = "Recepción de Vehículos";
        idClaseAccion = 31;
        return vehiculo;
    }
    
    
    @Override
    public String execute(){
        idAccion = 2;
        verifAccionTipoUsuario();
        if (indErrAcc.equals("")) {
            urlPaginacion = "vehiculos/Vehiculo";

            varReturnProcess(0);
            if (!listVarReturn.isEmpty()) {
                curPagVis = Integer.parseInt(listVarReturn.get(0).toString().trim());
                vehiculo.setIdVeh_f(listVarReturn.get(1).toString().trim());
                vehiculo.setDesNumCha_f(listVarReturn.get(2).toString().trim());
                vehiculo.setDesNumMot_f( listVarReturn.get(3).toString().trim());
            }
          
            cantVehiculosIndex(); 
            verifPag();
            listVehiculoIndex();

        }     
        
        return  SUCCESS;
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
    
    public void cantVehiculosIndex(){
        helper conex = null;
        ResultSet tabla = null;

        try {
            conex = new helper();
            indError = conex.getErrorSQL().trim();

            if (!indError.equals("")) {
                errores.add(indError);
            } else {
                tabla = conex.executeDataSet("CALL usp_cantVehiculoIndex(?,?,?,?,?,?,?,?,?,?)",
                        new Object[]{   vehiculo.getIdVeh_f(),
                                        vehiculo.getDesNumCha_f(),
                                        vehiculo.getDesNumMot_f(),
                                        vehiculo.getFecFacPrv_f(),
                                        vehiculo.getDesCli_f(),
                                        vehiculo.getFecVenVeh_f(),
                                        vehiculo.getIdTipDocVen_f(),
                                        vehiculo.getDesNumDocVen_f(),
                                        vehiculo.getDesNumPla_f(),
                                        vehiculo.getFecEntCli_f(), 
                                    });
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
    
    
    
    public void listVehiculoIndex(){
        helper conex = null;
        ResultSet tabla = null;

        try {
            conex = new helper();
            indError = conex.getErrorSQL().trim();
            if (!indError.equals("")) {
                errores.add(indError);
            } else {
                tabla = conex.executeDataSet("CALL usp_listVehiculoIndex(?,?,?,?,?,?,?,?,?,?,?,?)",
                        new Object[]{   vehiculo.getIdVeh_f(),
                                        vehiculo.getDesNumCha_f(),
                                        vehiculo.getDesNumMot_f(),
                                        vehiculo.getFecFacPrv_f(),
                                        vehiculo.getDesCli_f(),
                                        vehiculo.getFecVenVeh_f(),
                                        vehiculo.getIdTipDocVen_f(),
                                        vehiculo.getDesNumDocVen_f(),
                                        vehiculo.getDesNumPla_f(),
                                        vehiculo.getFecEntCli_f(), 
                                     getCurPag() * getRegPag(),
                                     getRegPag()});
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
                        /*obj.setFecVenVeh(tabla.getString("fecVenVeh"));*/
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
    
    public String adicionar() {
        idAccion = 3;
        verifAccionTipoUsuario();
        
        if (indErrAcc.equals("")) {
            vehiculo.setIdVeh("");
            if (!opcion.trim().equals("A") && !opcion.trim().equals("M")) {
                indErrParm = "error";
            } else {
                varReturnProcess(1);

                if (opcion.equals("A")) {
                    obtDesConLocal();
                    formURL = baseURL + "vehiculos/grabarVehiculo";
                    listaMarcas();
                    listaAmbientes();
                    listaCampanias();
                    listaConces();
                    listColoresInt();
                    
                }
            }
        }

        return "adicionar";
    }
    
    
    
    public void listaMarcas(){
        
        helper conex = null;
        ResultSet tabla = null;
          
        try {
            conex = new helper();
            indError = conex.getErrorSQL().trim();
            if (!indError.equals("")) {
                errores.add(indError);
            } else {
                tabla = conex.executeDataSet("CALL usp_listMarcas()",
                        new Object[]{});
                indError = conex.getErrorSQL().trim();
                if (!indError.equals("")) {
                    errores.add(indError);
                } else {
                    Marcas obj;
                    while (tabla.next()) {
                        obj = new Marcas();
                        obj.setIdMar(tabla.getString("idMar"));
                        obj.setDesMar(tabla.getString("desMar"));
                        listMarcas.add(obj);
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
    
    public String listaModelos(){

        helper conex = null;
        ResultSet tabla = null;
          
        try {
            conex = new helper();
            indError = conex.getErrorSQL().trim();
            if (!indError.equals("")) {
                errores.add(indError);
            } else {
                tabla = conex.executeDataSet("CALL usp_listModeloxMarca2(?)",
                        new Object[]{ vehiculo.getIdMar() });
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
    
    
    public String listaVersiones(){
        
        helper conex = null;
        ResultSet tabla = null;
          
        try {
            conex = new helper();
            indError = conex.getErrorSQL().trim();
            if (!indError.equals("")) {
                errores.add(indError);
            } else {
                tabla = conex.executeDataSet("CALL usp_listVersionesxModeloxMarca(?,?)",
                        new Object[]{ vehiculo.getIdMar(),vehiculo.getIdModMar() });
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
    
    public void listaAmbientes(){
        
        helper conex = null;
        ResultSet tabla = null;
          
        try {
            conex = new helper();
            indError = conex.getErrorSQL().trim();
            if (!indError.equals("")) {
                errores.add(indError);
            } else {
                tabla = conex.executeDataSet("CALL usp_listAmbientes()",
                        new Object[]{});
                indError = conex.getErrorSQL().trim();
                if (!indError.equals("")) {
                    errores.add(indError);
                } else {
                    AmbienteUbicacion obj;
                    while (tabla.next()) {
                        obj = new AmbienteUbicacion();
                        obj.setIdAmbUbi(tabla.getInt("idAmbUbi"));
                        obj.setDesAmbUbi(tabla.getString("desAmbUbi"));
                        listAmbientes.add(obj);
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
    
    public String listaUbicaciones(){
        
        helper conex = null;
        ResultSet tabla = null;
          
        try {
            conex = new helper();
            indError = conex.getErrorSQL().trim();
            if (!indError.equals("")) {
                errores.add(indError);
            } else {
                tabla = conex.executeDataSet("CALL usp_listAmbietexUbic(?)",
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
    
    
    public void listaCampanias(){
        
        helper conex = null;
        ResultSet tabla = null;
          
        try {
            conex = new helper();
            indError = conex.getErrorSQL().trim();
            if (!indError.equals("")) {
                errores.add(indError);
            } else {
                tabla = conex.executeDataSet("CALL usp_listCampanias()",
                        new Object[]{});
                indError = conex.getErrorSQL().trim();
                if (!indError.equals("")) {
                    errores.add(indError);
                } else {
                    Campania obj;
                    while (tabla.next()) {
                        obj = new Campania();
                        obj.setIdCam(tabla.getInt("idCam"));
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
                indError = "error";
                errores.add(e.getMessage());
            }
        }
        
        
    }
    
    
    public void listaConces(){
        
        helper conex = null;
        ResultSet tabla = null;
          
        try {
            conex = new helper();
            indError = conex.getErrorSQL().trim();
            if (!indError.equals("")) {
                errores.add(indError);
            } else {
                tabla = conex.executeDataSet("CALL usp_listConcesionarios()",
                        new Object[]{});
                indError = conex.getErrorSQL().trim();
                if (!indError.equals("")) {
                    errores.add(indError);
                } else {
                    Concesionarios obj;
                    while (tabla.next()) {
                        obj = new Concesionarios();
                        obj.setIdCon(tabla.getString("idCon"));
                        obj.setDesCon(tabla.getString("desCon"));
                        listConcesionarios.add(obj);
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
    
    public String listaLocales(){
        
        helper conex = null;
        ResultSet tabla = null;
          
        try {
            conex = new helper();
            indError = conex.getErrorSQL().trim();
            if (!indError.equals("")) {
                errores.add(indError);
            } else {
                tabla = conex.executeDataSet("CALL usp_listLocalesxConces(?)",
                        new Object[]{ vehiculo.getIdCon() });
                indError = conex.getErrorSQL().trim();
                if (!indError.equals("")) {
                    errores.add(indError);
                } else {
                    Locales obj;
                    while (tabla.next()) {
                        obj = new Locales();
                        obj.setIdLocCon(tabla.getString("idLocCon"));
                        obj.setDesLocCon(tabla.getString("desLocCon"));
                        listLocales.add(obj);
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
        
        return "locales";
    }
            
    public String listColoresExt(){
        
        helper conex = null;
        ResultSet tabla = null;
          
        try {
            conex = new helper();
            indError = conex.getErrorSQL().trim();
            if (!indError.equals("")) {
                errores.add(indError);
            } else {
                tabla = conex.executeDataSet("CALL usp_listColoresExtxModel(?,?)",
                        new Object[]{ vehiculo.getIdMar(),vehiculo.getIdModMar() });
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
    
    public void listColoresInt(){
        
        helper conex = null;
        ResultSet tabla = null;
          
        try {
            conex = new helper();
            indError = conex.getErrorSQL().trim();
            if (!indError.equals("")) {
                errores.add(indError);
            } else {
                tabla = conex.executeDataSet("CALL usp_listColoresInt()",
                        new Object[]{});
                indError = conex.getErrorSQL().trim();
                if (!indError.equals("")) {
                    errores.add(indError);
                } else {
                    ColorInteior obj;
                    while (tabla.next()) {
                        obj = new ColorInteior();
                        obj.setIdColInt(tabla.getString("idColInt"));
                        obj.setDesColInt(tabla.getString("desColInt"));
                        listColorInt.add(obj);
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
    
    
    
    public String grabar(){
        
        idAccion = 4;
        verifAccionTipoUsuario();
        
        if(indErrAcc.equals(""))
        {
            
            vehiculo.setIdVeh(vehiculo.getIdVeh().trim());
            vehiculo.setDesNumCha(vehiculo.getDesNumCha().trim());
            
            if(vehiculo.getIdVeh().equals(""))
            {
                indError += "error";
                errores.add("Ingrese el código del vehiculo");
            }

            if(vehiculo.getDesNumCha().equals(""))
            {
                indError += "error";
                errores.add("Ingrese el número de chasis");
            }
          
            validaform();
            if(indError.equals(""))
            {
                helper conex = null;
                ResultSet tabla = null;
                ResultSet tbltcv=null;

                try
                {
                    conex = new helper();
                    indError = conex.getErrorSQL();

                    if(!indError.equals(""))
                    {
                        errores.add(indError);
                    }
                    else
                    {
                        tabla = conex.executeDataSet("CALL usp_verifIdVehiculo(?)", 
                                new Object[]{ vehiculo.getIdVeh() });

                        indError += conex.getErrorSQL();

                        if(!indError.equals(""))
                        {
                            errores.add(indError);
                        }
                        else
                        {
                            int cont=0;
                            while(tabla.next())
                            {
                                cont = tabla.getInt(1);
                            }

                            if(cont>0)
                            {
                                indError += "error";
                                errores.add("Ya existe un vehículo con el código ingresado");
                            }
                            else
                            {
                                
                                /*vehiculo.setImpTipCamVen("0");*/
//                                tbltcv=conex.executeDataSet("call usp_getImpTipCambIntVenta()", new Object[] {} );
//                                
//                                while (tbltcv.next()) {                                    
//                                    vehiculo.setImpTipCamVen( tbltcv.getString(1) );
//                                }
                                
                                if(vehiculo.getFecFacPrv().trim().equals("")){
                                    vehiculo.setFecFacPrv("0000-00-00");
                                }
                                
                                if(vehiculo.getFecSitVeh().trim().equals("")){
                                    vehiculo.setFecSitVeh("0000-00-00");
                                }
                                
                                 if(vehiculo.getFecSitVen().trim().equals("")){
                                    vehiculo.setFecSitVen("0000-00-00");
                                }
                                
//                                if(vehiculo.getFecVenVeh().trim().equals("")){
//                                    vehiculo.setFecVenVeh("0000-00-00");
//                                }
                                
                                if(vehiculo.getFecEntCli().trim().equals("")){
                                    vehiculo.setFecEntCli("0000-00-00");
                                }
                                
                                if(vehiculo.getIdCli().equals("")){
                                    vehiculo.setIdCli("9999999999");
                                }
                                
                                indError = conex.executeNonQuery("CALL usp_insVehiculo(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)", 
                                        new Object[]{   vehiculo.getIdVeh() ,
                                                        vehiculo.getIdMar() ,
                                                        vehiculo.getIdModMar() ,
                                                        vehiculo.getIdVerMod() ,
                                                        vehiculo.getIdColExt() ,
                                                        vehiculo.getIdColInt() ,
                                                        vehiculo.getNumAnoFab() ,
                                                        vehiculo.getNumAnoMod() ,
                                                        vehiculo.getDesNumCha() ,
                                                        vehiculo.getDesNumMot() ,
                                                        vehiculo.getDesPolImp(),  
                                                        vehiculo.getFecFacPrv() ,
                                                        vehiculo.getDesNumFacPrv() ,
                                                        vehiculo.getCodMonFacPrv() ,
                                                        vehiculo.getImpTipCamFP() ,
                                                        vehiculo.getImpMonLoc() ,
                                                        vehiculo.getImpMonExt() ,
                                                        vehiculo.getImpPreVenAsi(),
                                                        vehiculo.getIdAmbUbi() ,
                                                        vehiculo.getIdUbiAmb() ,
                                                        vehiculo.getNumKimAct() ,
                                                        
                                                       /* vehiculo.getCodMonDocVen() ,*/
                                                       /* vehiculo.getImpTipCamVen() ,*/
                                                       /* vehiculo.getImpMonLocVen() ,*/
                                                       /* vehiculo.getImpMonExtVen() ,
                                                        vehiculo.getIdCam() ,*/
                                                        vehiculo.getDesNumPla() ,
                                                        vehiculo.getFecEntCli() ,
                                                        vehiculo.getDesObs() ,
                                                       sesion_sga.get("ses_idcon"),
                                                       sesion_sga.get("ses_idloccon"),
                                                        sesion_sga.get("ses_idusu").toString() ,
                                        });

                                if(!indError.equals(""))
                                {
                                    errores.add(indError);
                                }
                            }
                        }
                    }
                }
                catch (Exception e) 
                {
                    indError += "error";
                    errores.add(e.getMessage());
                }
                finally
                {
                    conex.returnConnect();
                }
            }

        }
        
        return "grabar";
    }     
     
    
    
   public void cantClientes(){
       
        helper conex = null;
        ResultSet tabla = null;

        try {
            conex = new helper();
            indError += conex.getErrorSQL().trim();

            if (!indError.trim().equals("")) {
                errores.add(indError);
            } else {
                tabla = conex.executeDataSet("CALL usp_cantClientesIndex(?,?)",
                        new Object[]{ "",vehiculo.getDesCli_f()  });

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
    
    
    
    public void listClientesVeh(){
        
        helper conex = null;
        ResultSet tabla = null;
          
        try {
            conex = new helper();
            indError = conex.getErrorSQL().trim();
            if (!indError.equals("")) {
                errores.add(indError);
            } else {
                tabla = conex.executeDataSet("CALL usp_listClientesIndex(?,?,?,?)",
                        new Object[]{ "",vehiculo.getDesCli_f(), (getCurPag()) * getRegPag(), getRegPag() });
                indError = conex.getErrorSQL().trim();
                if (!indError.equals("")) {
                    errores.add(indError);
                } else {
                    Clientes  obj;
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
                indError = "error";
                errores.add(e.getMessage());
            }
        }

    }
    
    
        
    public String listVehClientes() {
        idAccion = 5;

        verifAccionTipoUsuario();

        if (indErrAcc.equals("")) {
            regPag = 13;
            urlPaginacion = "vehiculos/listVehClientesVehiculo";
            divPopUp = "DIVclientes";

            cantClientes();
            verifPag();
            listClientesVeh();
        }

        return "listclientes";
    }
        
    
    public String modificar()
    {
        idAccion = 6;
        verifAccionTipoUsuario();
        
        if(indErrAcc.equals(""))
        {
            nivBandeja = 1;

            if((!opcion.trim().equals("A") && !opcion.trim().equals("M")))
            {
                indErrParm = "error";
            }
            else
            {
                varReturnProcess(1);
                
                if(opcion.equals("M"))
                {
                     
                    listaMarcas();
                    
                    
                    listaAmbientes();
                    listaCampanias();
                    listaConces();
                    listColoresInt();
                    
                    obtDatosVehiculo();
                    listaModelos();
                    listaVersiones();
                    listColoresExt();
                    listaUbicaciones();
                    listaLocales();
                    
                    formURL = baseURL+"vehiculos/actualizarVehiculo";

                }
            }
        }
        
        return "modificar";
    }
   
    
    public void obtDatosVehiculo(){
        
          helper conex = null;
        ResultSet tabla = null;
        
        try 
        {
            conex = new helper();
            indError += conex.getErrorSQL();

            if(!indError.equals(""))
            {
                errores.add(indError);
            }
            else
            {
                tabla = conex.executeDataSet("CALL usp_getDatosVehiculo(?)", 
                        new Object[]{ vehiculo.getIdVeh()});
                indError += conex.getErrorSQL();

                if(!indError.equals(""))
                {
                    errores.add(indError);
                }
                else
                {
                    while(tabla.next())
                    {
                            vehiculo.setIdVeh(tabla.getString("idVeh")) ;
                            vehiculo.setIdMar(tabla.getString("idMar")) ;
                            vehiculo.setIdModMar(tabla.getString("idModMar")) ;
                            vehiculo.setIdVerMod(tabla.getString("idVerMod")) ;
                            vehiculo.setIdColExt(tabla.getString("idColExt")) ;
                            vehiculo.setIdColInt(tabla.getString("idColInt")) ;
                            vehiculo.setNumAnoFab(tabla.getString("numAnoFab")) ;
                            vehiculo.setNumAnoMod(tabla.getString("numAnoMod")) ;
                            vehiculo.setDesNumCha(tabla.getString("desNumCha")) ;
                            vehiculo.setDesNumMot(tabla.getString("desNumMot")) ;
                            vehiculo.setDesPolImp(tabla.getString("desPolImp"));  
                            vehiculo.setFecFacPrv(tabla.getString("fecFacPrv")) ;
                            vehiculo.setDesNumFacPrv(tabla.getString("desNumFacPrv")) ;
                            vehiculo.setCodMonFacPrv(tabla.getString("codMonFacPrv")) ;
                            vehiculo.setImpTipCamFP(tabla.getString("impTipCamFP")) ;
                            vehiculo.setImpMonLoc(tabla.getString("impMonLoc")) ;
                            vehiculo.setImpMonExt(tabla.getString("impMonExt")) ;
                            vehiculo.setImpPreVenAsi(tabla.getString("impPreVenAsi"));
                            vehiculo.setIdAmbUbi(tabla.getInt("idAmbUbi")) ;
                            vehiculo.setIdUbiAmb(tabla.getInt("idUbiAmb")) ;
                            vehiculo.setNumKimAct(tabla.getString("numKimAct")) ;
                            vehiculo.setCodSitVeh(tabla.getString("codSitVeh")) ;
                            vehiculo.setFecSitVeh(tabla.getString("fecSitVeh")) ;
                            vehiculo.setIdUsuSitVeh(tabla.getString("idUsuSitVeh")) ;
                            vehiculo.setCodSitVen(tabla.getString("codSitVen")) ;
                            vehiculo.setFecSitVen(tabla.getString("fecSitVen")) ;
                            vehiculo.setIdCli(tabla.getString("idCli")) ;
                            vehiculo.setIdNumIntRV(tabla.getString("idNumIntRV"));
                            /*vehiculo.setFecVenVeh(tabla.getString("fecVenVeh"));*/
                            vehiculo.setIdTipDocVen(tabla.getString("idTipDocVen")) ;
                            vehiculo.setDesNumDocVen(tabla.getString("desNumDocVen")) ;
                           /* vehiculo.setCodMonDocVen(tabla.getInt("codMonDocVen")) ;*/
                           /* vehiculo.setImpTipCamVen(tabla.getString("impTipCamVen")) ;
                            vehiculo.setImpMonLocVen(tabla.getString("impMonLocVen")) ;
                            vehiculo.setImpMonExtVen(tabla.getString("impMonExtVen")) ;
                            vehiculo.setIdCam(tabla.getString("idCam")) ;*/
                            vehiculo.setFecEmiDocVen(tabla.getString("fecEmiDocVen"));
                            vehiculo.setDesNumPla(tabla.getString("desNumPla")) ;
                            vehiculo.setFecEntCli(tabla.getString("fecEntCli")) ;
                            vehiculo.setDesObs(tabla.getString("desObs")) ;
                            vehiculo.setIdCon(tabla.getString("idCon")) ;
                            vehiculo.setIdLocCon(tabla.getString("idLocCon")) ;
                            vehiculo.setIndVeh(tabla.getString("indVeh"));
                            vehiculo.setEdoVeh(tabla.getString("edoVeh"));
                            vehiculo.setDesCli(tabla.getString("desCli"));
                            vehiculo.setDesModVen(tabla.getString("desModVen"));
                            vehiculo.setDesUsu(tabla.getString("desUsu"));
                            vehiculo.setIdUsu(tabla.getString("idUsu"));
                            vehiculo.setDesCon(tabla.getString("desCon"));
                            vehiculo.setDesLocCon(tabla.getString("desLocCon"));
                            vehiculo.setFecReg(tabla.getString("fecReg"));
                    }
                }
            }
        }
        catch (Exception e) 
        {
            indError += "error";
            errores.add(e.getMessage());
        }
        finally
        {
            try 
            {
                tabla.close();
                conex.returnConnect();
            }
            catch (Exception e) 
            {}
        }   
        
    }
    
    
    public String obtndescli(){
      
        idAccion=8;
        verifAccionTipoUsuario();
        if(indErrAcc.equals(""))
        {
                helper objmy = null;
                ResultSet tabla = null;
                try 
                {
                    objmy = new helper();

                    tabla=objmy.executeDataSet("CALL usp_getDesCli(?)",
                            new Object[]{ vehiculo.getIdCli_f() });
                    vehiculo.setDesCli("");
                    while(tabla.next())
                    {
                         vehiculo.setDesCli(tabla.getString("desCli"));
                    }
                } 
                catch (Exception e) 
                {
                    indError="error";
                    errores.add(e.getMessage());
                }
                finally
                {
                    try
                    {
                        tabla.close();
                        objmy.returnConnect();
                    }
                    catch(Exception ex){}
                }
        }
      
      return "obtdescli";
  }
    
    
 public String actualizar(){
     
      idAccion = 8;
      verifAccionTipoUsuario();
      if(indErrAcc.equals(""))
      {
        helper objmy=null;
       // ResultSet tabla=null;
       
        String res="";
       
       try {
           
           objmy=new helper();
           indError=objmy.getErrorSQL();
           if(!indError.equals("")){
              errores.add(indError);
           }else{
               
               validaform();
               if(indError.equals("")){ 
                  indError=objmy.executeNonQuery("call usp_updVehiculo(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)"
                   , new Object[] {      
                                        vehiculo.getIdVeh() ,
                                        vehiculo.getIdMar() ,
                                        vehiculo.getIdModMar() ,
                                        vehiculo.getIdVerMod() ,
                                        vehiculo.getIdColExt() ,
                                        vehiculo.getIdColInt() ,
                                        vehiculo.getNumAnoFab() ,
                                        vehiculo.getNumAnoMod() ,
                                        vehiculo.getDesNumCha() ,
                                        vehiculo.getDesNumMot() ,
                                        vehiculo.getDesPolImp(),  
                                        vehiculo.getFecFacPrv() ,
                                        vehiculo.getDesNumFacPrv() ,
                                        vehiculo.getCodMonFacPrv() ,
                                        vehiculo.getImpTipCamFP() ,
                                        vehiculo.getImpMonLoc() ,
                                        vehiculo.getImpMonExt() ,
                                        vehiculo.getImpPreVenAsi(),
                                        vehiculo.getIdAmbUbi() ,
                                        vehiculo.getIdUbiAmb() ,
                                        vehiculo.getNumKimAct() 
                                        
                                      /*  vehiculo.getCodSitVeh() ,
                                        vehiculo.getFecSitVeh() ,
                                        sesion_sga.get("ses_idusu"),
                                        vehiculo.getCodSitVen() ,
                                        vehiculo.getFecSitVen() ,
                                        vehiculo.getIdCli() ,
                                        vehiculo.getIdTipDocVen() ,
                                        vehiculo.getDesNumDocVen() ,*/
                                       /* vehiculo.getDesNumPla() ,
                                        vehiculo.getFecEntCli() ,
                                        vehiculo.getDesObs() ,
                                        sesion_sga.get("ses_idcon"),
                                        sesion_sga.get("ses_idloccon")    */
                                
                                 });
                    
                    if(!indError.equals(""))
                    {
                      errores.add(indError);
                    }
                }   
           }           
 
           
       } catch (Exception e) {
           
           indError += "error";
           errores.add(e.getMessage());
           
       }finally{
           
           objmy.returnConnect();
       }
      } 
       return "actualizar";
     
      
 }   
 
 
 public String eliminar()
    {
        idAccion = 9;
        verifAccionTipoUsuario();
        
        if(indErrAcc.equals(""))
        {
            if(opcion.trim().equals("E"))
            {
                helper conex = null;
                ResultSet tabla = null;

                try
                {
                    conex = new helper();
                    indError += conex.getErrorSQL().trim();

                    if(!indError.equals(""))
                    {
                       errores.add(indError);
                    }
                    else
                    {

                        indError += conex.executeNonQuery("CALL usp_dltvehiculo(?)",
                                new Object[]{ vehiculo.getIdVeh().trim() });

                        indError = indError.trim();
                        if(indError.trim().equals(""))
                        {
                            errores.add(indError);
                        }

                    }
                }
                catch (Exception e) 
                {
                    indError = "error";
                    errores.add(e.getMessage());
                }
                finally
                {
                    try 
                    {
                        tabla.close();
                        conex.returnConnect();
                    }
                    catch (Exception e) 
                    {}
                }
            }
        }
        
        return "eliminar";
    }  
 
    
 
 public void validaform(){
      
     if ( vehiculo.getIdVeh().trim().equals("") ){
           indError="Ingrese codigo para el vehículo";
           errores.add(indError);
     }
     
     if(vehiculo.getIdMar().equals("") || vehiculo.getIdMar().equals("0")  ){
           indError="Seleccione una marca";
           errores.add(indError);
     }
     
     if(vehiculo.getIdModMar().equals("") || vehiculo.getIdModMar().equals("0")  ){
           indError="Seleccione un modelo";
           errores.add(indError);
     }
     
     if(vehiculo.getIdVerMod().equals("") || vehiculo.getIdVerMod().equals("0")  ){
           indError="Seleccione una versión";
           errores.add(indError);
     }
     
     if(vehiculo.getIdColExt().equals("") || vehiculo.getIdColExt().equals("0")  ){
          indError="Seleccione un color exterior";
           errores.add(indError);
     }
     
     if(vehiculo.getIdColInt().equals("") || vehiculo.getIdColInt().equals("0")  ){
          indError="Seleccione un color interior";
           errores.add(indError);
     }
     
      if(vehiculo.getDesNumCha().equals("") || vehiculo.getDesNumCha().equals("0") ){
          indError="Ingrese un chasis correcto";
           errores.add(indError);
     }
      
     if(vehiculo.getNumAnoFab().equals("") || vehiculo.getNumAnoFab().equals("0") ){
          if(isInteger(vehiculo.getNumAnoFab().trim())){
              
          }
          indError="Ingrese el año de fabricación";
           errores.add(indError);
     }else{
         if(!isInteger(vehiculo.getNumAnoFab().trim())){
              indError="Ingrese un año de fabricación correcto";
              errores.add(indError);
          }
          
     }
     
   if(vehiculo.getNumAnoMod().equals("") || vehiculo.getNumAnoMod().equals("0") ){
          indError="Ingrese el año del modelo";
          errores.add(indError);
     }else{
         if(!isInteger(vehiculo.getNumAnoMod())){
              indError="Ingrese un año de modelo correcto";
              errores.add(indError);
         }
     } 
   
   if(vehiculo.getDesNumMot().trim().equals("")){
          indError="Ingrese el número de motor";
          errores.add(indError);
   }
     
     if(indError.trim().equals("")){
         vehiculo.setIdCon(sesion_sga.get("ses_idcon").toString() );
         vehiculo.setIdLocCon(sesion_sga.get("ses_idloccon").toString() );
     }
     
     if(indError.trim().equals("")){
         vehiculo.setIdCon(sesion_sga.get("ses_idcon").toString() );
         vehiculo.setIdLocCon(sesion_sga.get("ses_idloccon").toString() );
     }
     
     
 }
 
 
 public void obtDesConLocal(){
     
        helper conex = null;
        ResultSet tabla = null;

        try {
            conex = new helper();
            indError += conex.getErrorSQL().trim();

            if (!indError.trim().equals("")) {
                errores.add(indError);
            } else {
                tabla = conex.executeDataSet("CALL usp_obtDesConcesLocal(?,?)",
                        new Object[]{  sesion_sga.get("ses_idcon"),sesion_sga.get("ses_idloccon")  });
            
                indError += conex.getErrorSQL();

                if (!indError.equals("")) {
                    errores.add(indError);
                } else {
                    while (tabla.next()) {
                         vehiculo.setDesCon(tabla.getString("desCon"));
                         vehiculo.setDesLocCon(tabla.getString("desLocCon"));
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
 
 public String obtPrecVentAsi(){
     
        helper conex = null;
        ResultSet tabla = null;

        try {
            conex = new helper();
            indError += conex.getErrorSQL().trim();

            if (!indError.trim().equals("")) {
                errores.add(indError);
            } else {
                tabla = conex.executeDataSet("CALL usp_getDatosPrecioLista(?,?,?,?)",
                        new Object[]{  vehiculo.getIdMar(),vehiculo.getIdModMar(),vehiculo.getIdVerMod(),vehiculo.getNumAnoFab() });
            
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
    
    
}
