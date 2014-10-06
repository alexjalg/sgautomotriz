
package actions;

import com.mysql.jdbc.PreparedStatement;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ModelDriven;
import conexion.helper;
import entities.Clientes;
import entities.EstadoCivil;
import entities.Genero;
import entities.TipoCliente;
import java.sql.ResultSet;
import java.util.ArrayList;
import entities.TipoDocumento;
import entities.Departamentos;
import entities.Provincias;
import entities.Distritos;
import entities.FormaPago;
import entities.GrupoCliente;
import entities.SituacionCliente;
import java.util.Map;


/**
 *
 * @author Angel
 */
public class ClientesAction extends MasterAction implements ModelDriven<Clientes>{
    
    private Map<String, Object> sesion_sga = ActionContext.getContext().getSession();
   private Clientes cliente=new Clientes();
   private ArrayList<Clientes> listClientes=new ArrayList<Clientes>();
   private ArrayList<TipoDocumento> listTipoDoc=new ArrayList<TipoDocumento>();
   private ArrayList<TipoCliente> listTipCli = new ArrayList<TipoCliente>();
   
   private ArrayList<Departamentos> listDpto = new ArrayList<Departamentos>();
   private ArrayList<Provincias> listProv = new ArrayList<Provincias>();
   private ArrayList<Distritos> listDist = new ArrayList<Distritos>();
   
   private ArrayList<EstadoCivil> listEstCiv = new ArrayList<EstadoCivil>();
   private ArrayList<Genero> listGenero = new ArrayList<Genero>();
   
    private ArrayList<GrupoCliente> listGrpCli=new ArrayList<GrupoCliente>();
    private ArrayList<SituacionCliente> listaSitCli=new ArrayList<SituacionCliente>();
    private ArrayList<FormaPago> listFormaPag=new ArrayList<FormaPago>();
    
  @Override 
   public Clientes getModel(){
       
        tituloOpc = "Clientes";
        idClaseAccion = 20;
       return cliente;
   }
  
   public String vrfSeleccion()
    {
        idAccion = 1;
        verifAccionTipoUsuario();
        
        if(indErrAcc.equals(""))
        {
            if(cliente.getIdCli().trim().equals(""))
            {
                indError = "error";
                errores.add("No ha seleccionado ningun registro");
            }
        }
        
        return "vrfSeleccion";
    }
   
   private void cantClientesIndex()
    {
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
                tabla = conex.executeDataSet("CALL usp_cantClientesIndex(?,?)", 
                        new Object[]{ cliente.getIdCli_f(),cliente.getDesCli_f()  });

                indError += conex.getErrorSQL();

                if(!indError.equals(""))
                {
                    errores.add(indError);
                }
                else
                {
                    while(tabla.next())
                    {
                        cantReg = tabla.getInt(1);
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
   
    private void listClientesIndex()
    {
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
                tabla = conex.executeDataSet("CALL usp_listClientesIndex(?,?,?,?)", 
                        new Object[]{ cliente.getIdCli_f(),cliente.getDesCli_f(),
                            (getCurPag()) * getRegPag(), getRegPag() });

                indError += conex.getErrorSQL();

                if(!indError.equals(""))
                {
                    errores.add(indError);
                }
                else
                {
                    Clientes obj;
                    while(tabla.next())
                    {
                        obj = new Clientes();
                        obj.setIdCli(tabla.getString("idCli"));
                        obj.setDesCli(tabla.getString("desCli"));
                        obj.setNumTelFij(tabla.getString("numTelFij"));
                        obj.setNumTelMov(tabla.getString("numTelMov"));
                        getListClientes().add(obj);
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
  
   
 @Override
   public String execute(){
       
     
     idAccion = 2;
        
     verifAccionTipoUsuario();
     
      if(indErrAcc.equals(""))
        {
            urlPaginacion = "clientes/Cliente";

            varReturnProcess(0);
            if(!listVarReturn.isEmpty())
            {
                curPagVis = Integer.parseInt(listVarReturn.get(0).toString().trim());
                cliente.setIdCli_f(listVarReturn.get(1).toString().trim());
                cliente.setDesCli_f(listVarReturn.get(2).toString().trim());
            }

            cantClientesIndex();
            verifPag();
            listClientesIndex();
        }
       
       
       return SUCCESS;
   }
 
 
  public String adicionar()
    {
        idAccion = 3;
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

                if(opcion.equals("A"))
                {
                    cliente.setIdCli("");
                    formURL = baseURL+"clientes/grabarCliente";
                }
            }
        }
        populate();
        return "adicionar";
    }
  
  public String modificar()
    {
        idAccion = 7;
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
                    populate();
                    getDatosCliente();
                    populateProv();
                    populateDist();
                    formURL = baseURL+"clientes/actualizarCliente";

                }
            }
        }
        
        return "modificar";
    }
   
  
  public void populate(){
      
     listTipCli.add(new TipoCliente("N", "Natural"));
     listTipCli.add(new TipoCliente("J", "Juridica"));  
     
     listGenero.add(new Genero("M", "Masculino"));
     listGenero.add(new Genero("F", "Femenino"));
     
     listEstCiv.add(new EstadoCivil("S","Soltero"));
     listEstCiv.add(new EstadoCivil("C","Casado"));
     listEstCiv.add(new EstadoCivil("V","Viudo"));
     listEstCiv.add(new EstadoCivil("D","Divorciado"));
     
     listaSitCli.add(new SituacionCliente("N", "Normal"));
     listaSitCli.add(new SituacionCliente("S", "Suspendido"));
     listaSitCli.add(new SituacionCliente("C", "Cobranza Coactiva"));
     
     cliente.setOtrTipCli("N");
     cliente.setOtrGenCon("M");
    
     populateTipoDoc();
     populateDep();
     populateDatosCli();
      
  }
  
  
   public String populateTipoDoc()
    {
        helper objmy = null;
        ResultSet tabla = null;
        try 
        {
            objmy = new helper();
            
            tabla = objmy.executeDataSet("CALL usp_listTipoDocumento()",
                    new Object[]{});
            TipoDocumento obj;
            while(tabla.next())
            {
                obj=new TipoDocumento();
                obj.setIdTipDoc(tabla.getInt("idTipDoc"));
                obj.setDesTipDoc(tabla.getString("desTipDoc"));
                listTipoDoc.add(obj);
                
            }
        } 
        catch (Exception e) 
        {}
        finally
        {
            try
            {
                tabla.close();
                objmy.returnConnect();
            }
            catch(Exception ex){}
        }
        return "tipoDoc";
    }
   
   
   private void populateDep(){
       
       helper objmy = null;
       ResultSet tabla = null;
       
       
       try {
          objmy=new helper();
          tabla = null;
            tabla=objmy.executeDataSet("CALL usp_listDptos()",new Object[]{});
            Departamentos objDpto;
            while(tabla.next())
            {
                objDpto=new Departamentos();
                objDpto.setIdDep(tabla.getInt("idDep"));
                objDpto.setDesDep(tabla.getString("desDep"));
                objDpto.setCodTel(tabla.getString("codTel"));
                getListDpto().add(objDpto);
            }
           
           
       } catch (Exception e) {
       }
       
   }
   
   
    public String populateProv()
    {
        idAccion=4;
        verifAccionTipoUsuario();
        if(indErrAcc.equals(""))
        {
            
                helper objmy = null;
                ResultSet tabla = null;
                try 
                {
                    objmy = new helper();

                    tabla=objmy.executeDataSet("CALL usp_listProvXDep(?)",
                            new Object[]{cliente.getIdDep()});
                    Provincias objProv;
                    while(tabla.next())
                    {
                        objProv=new Provincias();
                        objProv.setIdPrvDep(tabla.getInt("idPrvDep"));
                        objProv.setDesProv(tabla.getString("desProv"));
                        listProv.add(objProv);
                    }
                } 
                catch (Exception e) 
                {}
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

        return "prov";
    }
    
     public String populateDist()
    {
        idAccion=5;
        verifAccionTipoUsuario();
        if(indErrAcc.equals(""))
        {
                helper objmy = null;
                ResultSet tabla = null;
                try 
                {
                    objmy = new helper();

                    tabla=objmy.executeDataSet("CALL usp_listDistXProvYDep(?,?)",
                            new Object[]{cliente.getIdDep(),cliente.getIdPrvDep()});
                    Distritos objDist;
                    while(tabla.next())
                    {
                        objDist=new Distritos();
                        objDist.setIdDisPrv(tabla.getInt("idDisPrv"));
                        objDist.setDesDis(tabla.getString("desDis"));
                        listDist.add(objDist);
                    }
                } 
                catch (Exception e) 
                {}
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
        
        
        return "dist";
    }
     
     
   private void populateDatosCli(){
       
       
       helper objmy=null;
       ResultSet tabla=null;
       
       try {
           objmy=new helper();
           tabla=objmy.executeDataSet("call usp_listGrupoCliente()", new Object[]{});

           GrupoCliente objg;
           
           while (tabla.next()) {               
               objg=new  GrupoCliente();
               
               objg.setIdGrpCli(tabla.getInt("idGrpCli"));
               objg.setDesGrpCli(tabla.getString("desGrpCli"));
               listGrpCli.add(objg);
           }
           
           tabla=null;
           tabla=objmy.executeDataSet("call usp_listFormaPago()", new Object[]{});
           FormaPago objfp;
           while (tabla.next()) {      
                    objfp=new FormaPago();
                    objfp.setIdForPag(tabla.getInt("idForPag"));
                    objfp.setDesForPag(tabla.getString("desForPag"));
                    listFormaPag.add(objfp);
           }

           
       } catch (Exception e) {
       }finally{
           objmy.returnConnect();
       }
       
   }
   
   public void getDatosCliente(){
         
    
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
                tabla = conex.executeDataSet("CALL usp_getDatosCliente(?)", 
                        new Object[]{ cliente.getIdCli() });
                indError += conex.getErrorSQL();

                if(!indError.equals(""))
                {
                    errores.add(indError);
                }
                else
                {
                    while(tabla.next())
                    {
                        cliente.setIdCli(tabla.getString("idCli" )) ;
                        cliente.setIdTipDoc(tabla.getInt("idTipDoc")) ;
                        cliente.setOtrTipCli(tabla.getString("otrTipCli")) ;
                        cliente.setDesCli(tabla.getString("desCli")) ;
                        cliente.setDesApeCon(tabla.getString("desApeCon")) ;
                        cliente.setDesNomCon(tabla.getString("desNomCon")) ;
                        cliente.setDesDirCon(tabla.getString("desDirCon")) ;
                        cliente.setIdDep(tabla.getInt("idDep")) ;
                        cliente.setIdPrvDep(tabla.getInt("idPrvDep")) ;
                        cliente.setIdDisPrv(tabla.getInt("idDisPrv")) ;
                        cliente.setNumTelFij(tabla.getString("numTelFij")) ;
                        cliente.setNumTelMov(tabla.getString("numTelMov")) ;
                        cliente.setNumTelOfi(tabla.getString("numTelOfi")) ;
                        cliente.setNumTelRP(tabla.getString("numTelRP")) ;
                        cliente.setOtrEmaPer(tabla.getString("otrEmaPer")) ;
                        cliente.setOtrEmaTra(tabla.getString("otrEmaTra")) ;
                        cliente.setFecNacCon(tabla.getString("fecNacCon")) ;
                        cliente.setOtrGenCon(tabla.getString("otrGenCon")) ;
                        cliente.setOtrEstCiv(tabla.getString("otrEstCiv")) ;
                        cliente.setDesNomCenLab(tabla.getString("desNomCenLab")) ;
                        cliente.setDesDirCenLab(tabla.getString("desDirCenLab"));
                        cliente.setDesAreTra(tabla.getString("desAreTra")) ;
                        cliente.setDesCarCon(tabla.getString("desCarCon")) ;
                        cliente.setDesPagWeb(tabla.getString("desPagWeb")) ;
                        cliente.setIdGrpCli(tabla.getInt("idGrpCli"));
                        cliente.setIdForPag(tabla.getInt("idForPag"));
                        cliente.setCodSitCli(tabla.getString("codSitCli"));
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
   
   public String grabar(){
       
      idAccion = 6;
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
                  
                   indError=objmy.executeNonQuery("call usp_insCliente(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)"
                   , new Object[] {     cliente.getIdCli() ,
                                        cliente.getIdTipDoc() ,
                                        cliente.getOtrTipCli() ,
                                        cliente.getDesCli() ,
                                        cliente.getDesApeCon() ,
                                        cliente.getDesNomCon() ,
                                        cliente.getDesDirCon() ,
                                        cliente.getIdDep() ,
                                        cliente.getIdPrvDep() ,
                                        cliente.getIdDisPrv() ,
                                        cliente.getNumTelFij() ,
                                        cliente.getNumTelMov() ,
                                        cliente.getNumTelOfi() ,
                                        cliente.getNumTelRP() ,
                                        cliente.getOtrEmaPer() ,
                                        cliente.getOtrEmaTra() ,
                                        cliente.getFecNacCon() ,
                                        cliente.getOtrGenCon() ,
                                        cliente.getOtrEstCiv() ,
                                        cliente.getDesNomCenLab(),
                                        cliente.getDesDirCenLab(),
                                        cliente.getDesAreTra() ,
                                        cliente.getDesCarCon() ,
                                        cliente.getDesPagWeb() ,
                                        cliente.getIdGrpCli() ,
                                        cliente.getIdForPag() ,
                                        cliente.getCodSitCli() ,
                                         sesion_sga.get("ses_idusu").toString()
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
       return "grabar";
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
                  indError=objmy.executeNonQuery("call usp_updCliente(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)"
                   , new Object[] {     cliente.getIdCli() ,
                                        cliente.getDesCli() ,
                                        cliente.getDesApeCon() ,
                                        cliente.getDesNomCon() ,
                                        cliente.getDesDirCon() ,
                                        cliente.getIdDep() ,
                                        cliente.getIdPrvDep() ,
                                        cliente.getIdDisPrv() ,
                                        cliente.getNumTelFij() ,
                                        cliente.getNumTelMov() ,
                                        cliente.getNumTelOfi() ,
                                        cliente.getNumTelRP() ,
                                        cliente.getOtrEmaPer() ,
                                        cliente.getOtrEmaTra() ,
                                        cliente.getFecNacCon() ,
                                        cliente.getOtrGenCon() ,
                                        cliente.getOtrEstCiv() ,
                                        cliente.getDesNomCenLab(),
                                        cliente.getDesDirCenLab(),
                                        cliente.getDesAreTra() ,
                                        cliente.getDesCarCon() ,
                                        cliente.getDesPagWeb() ,
                                        cliente.getIdGrpCli() ,
                                        cliente.getIdForPag() ,
                                        cliente.getCodSitCli() 
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
//                        tabla = conex.executeDataSet("CALL usp_verifDependColor(?)", 
//                                new Object[]{ cliente.getIdCol() });
//                        indError += conex.getErrorSQL();
//
//                        if(!indError.equals(""))
//                        {
//                            errores.add(indError);
//                        }
//                        else
//                        {
//                            int cant = 0;
//                            while(tabla.next())
//                            {
//                                cant = tabla.getInt(1);
//                            }
//
//                            /* Si no tiene dependencias */
//                            if(cant == 0)
//                            {
                                indError += conex.executeNonQuery("CALL usp_dltCliente(?)",
                                        new Object[]{ cliente.getIdCli().trim() });

                                indError = indError.trim();
                                if(indError.trim().equals(""))
                                {
                                    errores.add(indError);
                                }
//                            }
//                            else /* si tiene dependencias */
//                            {
//                                indError += "error";
//                                errores.add("Existen registros dependientes del color");
//                            }
//                        }
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
   
  
  private void validaform(){
      
      
      if( !isLong(cliente.getIdCli()) || cliente.getIdCli().trim().equals("") ){
          indError="Ingrese un num. de doc. correcto";
          errores.add(indError);
      }
      
      if(cliente.getDesNomCon().trim().equals("")){
          indError=" Ingrese nombres ";
          errores.add(indError);
      }
      
      if(cliente.getDesApeCon().trim().equals("") ){
          indError="Ingrese apellidos";
          errores.add(indError);
      }
      
      
      if(cliente.getDesDirCon().trim().equals("")){
          indError="Ingrese una dirección";
          errores.add(indError);
      } 
         
      if(cliente.getNumTelFij().equals("") && cliente.getNumTelMov().equals("") && cliente.getNumTelOfi().equals("") && cliente.getNumTelRP().equals("") ){
          
          indError="Ingrese por lo menos un num. telefónico";
          errores.add(indError);
      }else{
      
            if(!cliente.getNumTelFij().trim().equals("")){
                if(!isLong( cliente.getNumTelFij())){
                    indError="Ingrese un num telf fijo correcto";
                    errores.add(indError);
                }
            }else{
                cliente.setNumTelFij("0");
            } 

            if(!cliente.getNumTelMov().trim().equals("")) {
               if(!isLong( cliente.getNumTelMov())){
                    indError="Ingrese un num telf movil correcto";
                    errores.add(indError);
                }
            }else{
                cliente.setNumTelMov("0");
            }

            if(!cliente.getNumTelOfi().trim().equals("")){
                if(!isLong( cliente.getNumTelOfi() )){
                    indError="Ingrese un num telf de oficina correcto";
                    errores.add(indError);
                } 
            }else{
                cliente.setNumTelOfi("0");
            }
     }
      
      
      
  }

    /**
     * @return the cliente
     */
    public Clientes getCliente() {
        return cliente;
    }

    /**
     * @param cliente the cliente to set
     */
    public void setCliente(Clientes cliente) {
        this.cliente = cliente;
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
     * @return the listTipDoc
     */
   
    /**
     * @return the listTipCli
     */
    public ArrayList<TipoCliente> getListTipCli() {
        return listTipCli;
    }

    /**
     * @param listTipCli the listTipCli to set
     */
    public void setListTipCli(ArrayList<TipoCliente> listTipCli) {
        this.listTipCli = listTipCli;
    }

    /**
     * @return the listTipoDoc
     */
    public ArrayList<TipoDocumento> getListTipoDoc() {
        return listTipoDoc;
    }

    /**
     * @param listTipoDoc the listTipoDoc to set
     */
    public void setListTipoDoc(ArrayList<TipoDocumento> listTipoDoc) {
        this.listTipoDoc = listTipoDoc;
    }

    /**
     * @return the listGenero
     */
    public ArrayList<Genero> getListGenero() {
        return listGenero;
    }

    /**
     * @param listGenero the listGenero to set
     */
    public void setListGenero(ArrayList<Genero> listGenero) {
        this.listGenero = listGenero;
    }

    /**
     * @return the listEstCiv
     */
    public ArrayList<EstadoCivil> getListEstCiv() {
        return listEstCiv;
    }

    /**
     * @param listEstCiv the listEstCiv to set
     */
    public void setListEstCiv(ArrayList<EstadoCivil> listEstCiv) {
        this.listEstCiv = listEstCiv;
    }

    /**
     * @return the listDpto
     */
    public ArrayList<Departamentos> getListDpto() {
        return listDpto;
    }

    /**
     * @param listDpto the listDpto to set
     */
    public void setListDpto(ArrayList<Departamentos> listDpto) {
        this.listDpto = listDpto;
    }

    /**
     * @return the listProv
     */
    public ArrayList<Provincias> getListProv() {
        return listProv;
    }

    /**
     * @param listProv the listProv to set
     */
    public void setListProv(ArrayList<Provincias> listProv) {
        this.listProv = listProv;
    }

    /**
     * @return the listDist
     */
    public ArrayList<Distritos> getListDist() {
        return listDist;
    }

    /**
     * @param listDist the listDist to set
     */
    public void setListDist(ArrayList<Distritos> listDist) {
        this.listDist = listDist;
    }

    /**
     * @return the listGrpCli
     */
    public ArrayList<GrupoCliente> getListGrpCli() {
        return listGrpCli;
    }

    /**
     * @param listGrpCli the listGrpCli to set
     */
    public void setListGrpCli(ArrayList<GrupoCliente> listGrpCli) {
        this.listGrpCli = listGrpCli;
    }

    /**
     * @return the listaSitCli
     */
    public ArrayList<SituacionCliente> getListaSitCli() {
        return listaSitCli;
    }

    /**
     * @param listaSitCli the listaSitCli to set
     */
    public void setListaSitCli(ArrayList<SituacionCliente> listaSitCli) {
        this.listaSitCli = listaSitCli;
    }

    /**
     * @return the listFormaPag
     */
    public ArrayList<FormaPago> getListFormaPag() {
        return listFormaPag;
    }

    /**
     * @param listFormaPag the listFormaPag to set
     */
    public void setListFormaPag(ArrayList<FormaPago> listFormaPag) {
        this.listFormaPag = listFormaPag;
    }
   
    
    
}
