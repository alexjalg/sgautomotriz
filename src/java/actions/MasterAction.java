package actions;

import com.ibm.db2.jcc.DBTimestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.interceptor.ServletRequestAware;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import conexion.helper;
import entities.Anios;
import entities.Meses;
import java.sql.ResultSet;
import java.util.Calendar;

@SuppressWarnings("serial")
public class MasterAction extends ActionSupport implements ServletRequestAware {
    //Variable que nos va a permitir obtener la ruta absoluta del proyecto 
    //y asi nosotros trabajar como si fueran rutas relativas
    protected HttpServletRequest servletRequest;
    
    //URL para los recursos, redireccionamientos y demas
    protected String baseURL = "http://192.168.0.22:8080/sgautomotriz/";  //Desarrollo
    //protected String baseURL = "http://192.168.10.15:8080/sgautomotriz/";  //Mopal
    protected String urlRedirect = "";   //url a la cual se quiere redireccionar
    protected String inicioURL = baseURL+"Inicio";  //URL a la cual redireccionar si en la peticiona de pagina falta un parametro obligatorio
    protected String indErrParm = "";
    protected int idClaseAccion=0;
    protected int idAccion=0;
    protected int indVerifAcc=0;
    protected String indErrAcc = "";  //Indica si no se tiene permiso a la url(accion) llamada
    protected String indErrSes = "";  //Indica si error de sesion caducada
    private String titleDialog = "Sistema de Gestión Automotriz";
    
    private Map<String, Object> sesion_sga = ActionContext.getContext().getSession();
    
    //variable que se usara para indicar si existe algun error en el proceso
    protected String indError = "";
    //Arreglo donde se guardaran los errores capturados
    protected ArrayList<String> errores = new ArrayList<String>();
    
    protected int cantReg = 0;  //Cantidad de registros en la pagina actual
    protected int curPag = 1;   //Pagina actual (para proceso)
    protected int curPagVis = 1;   //Pagina actual (para visualizar)
    protected int ultPag = 1;     //Ultima pagina
    protected int regPag = 15;    //Registros que se visualizaran por pagina
    
    //Variable para titulo de opcion (se visualizara en cabecera de la grilla o formulario)
    protected String tituloOpc="";
    //Variable que idica que concepto sequiere eliminar
    protected String conceptoEliminar="";
    
    protected String accion="";     //Guardara y mostrara en la cabecera la accion que se hara cuando se envie un formulario
    protected String opcion = "";   //Indicara que se va hacer cuando se envie un formulario, por ejemplo, confirmar antes de grabar. NO usar para indicar errores.
    protected String backURL = "";  //URL a la cual regresar
    protected String formURL = "";  //URL a la que se enviara el formulario principal
    
    //Arreglos para armar grillas complejas (con campos calculados en java, cantidad de columnas variables, etc)
    protected ArrayList<String> arColumnas;
    protected ArrayList<ArrayList> arFilas = new ArrayList<ArrayList>();
    
    //variables que se usaran para definir el nombre de un excel generado
    protected String nomExcel = "";
    protected String sufijoExcel = "";
    
    // Rutas de PDF's y excel
    private String rutaPDF = "";
    private String rutaExcel = "";
    
    protected String paginacion = "";
    protected String paginacionPopUp = "";
    protected String urlPaginacion = "";
    protected String jsPaginacion = "";
    protected String jsPaginacionPopUp = "";
    protected String datosOblig = "";
    protected String divPopUp = "";
    
    /*variable para recuperar filtros y otros al cambiar de paginas*/
    private String strParamsReturn="";
    
    /*nivel de la opción*/
    protected int nivBandeja=1;
    
    //variable que almacenara los filtros y pagina actual, al pasar de nivel de bandeja
    protected String varReturn = "";
    protected ArrayList<String> listVarReturn = new ArrayList<String>();
    
    /*parametros que se envian al seleccionar una opcion del menú para recuperar los permisos del tipo 
     de usuario en la opción */
    protected String mtu="0";
    protected String mmo="0";
    protected String mop="0";
    protected String mni="0";
    protected String mod="0";
    protected String perm="";
    
    protected String curDate="";
    protected String curYear="";
    protected String curMonth="";
    protected String curDay="";
    
    private ArrayList<Meses> listMeses = new ArrayList<Meses>();
    private ArrayList<Anios> listAnios = new ArrayList<Anios>();

    public void setServletRequest(HttpServletRequest request) {
        this.servletRequest = request;
    }

    public String getBaseURL() {
        return baseURL;
    }

    public String getUrlRedirect() {
        return urlRedirect;
    }

    public void setUrlRedirect(String urlRedirect) {
        this.urlRedirect = urlRedirect;
    }

    /*  Manejo de errores  */
    public String getIndError() {
        return indError;
    }

    public ArrayList<String> getErrores() {
        return errores;
    }

    /*  Paginaci�n de las bandejas  */
    public int getCantReg() {
        return cantReg;
    }

    public int getCurPag() {
        curPag = getCurPagVis()-1;
        
        if(curPag==-1)
        {
            curPag=0;
        }
        
        return curPag;
    }

    public void setCurPag(int curPag) {
        this.curPag = curPag;
    }
    
    /**
     * @return the curPagVis
     */
    public int getCurPagVis() {
        if(curPagVis==0)
        {
            if(cantReg==0)
                curPagVis=0;
            else
                curPagVis=1;
        }
        
        return curPagVis;
    }

    /**
     * @param curPagVis the curPagVis to set
     */
    public void setCurPagVis(int curPagVis) {
        this.curPagVis = curPagVis;
    }

    public int getUltPag() {
        Double a = cantReg/(getRegPag()*1.0);
        ultPag = (int) Math.ceil(a);
        
        return ultPag;
    }
    
    public void verifPag()
    {
        if(curPagVis>getUltPag())
        {
            if(getUltPag()==0)
                curPagVis=0;
            else
                curPagVis=1;
        }
    }

    /*  Acciones a hacer al enviar formulario  */
    public String getAccion() {
        return accion;
    }

    public void setAccion(String accion) {
        this.accion = accion;
    }

    public String getOpcion() {
        return opcion;
    }

    public void setOpcion(String opcion) {
        this.opcion = opcion;
    }

    /*  Para ayudar a armar grillas complejas  */
    public ArrayList<ArrayList> getArFilas() {
        return arFilas;
    }

    public void setArFilas(ArrayList<ArrayList> arFilas) {
        this.arFilas = arFilas;
    }

    public ArrayList<String> getArColumnas() {
        return arColumnas;
    }

    public void setArColumnas(ArrayList<String> arColumnas) {
        this.arColumnas = arColumnas;
    }

    /*  Funciones para nombrar a un excel  */
    public String getNomExcel() {
        return nomExcel;
    }

    public void setNomExcel(String nomExcel) {
        this.nomExcel = nomExcel;
    }

    public String getSufijoExcel() {
        return sufijoExcel;
    }

    /*  Funciones para validar el ingreso de datos  */
    protected boolean isInteger(String cadena) {
        try {
            Integer.parseInt(cadena);
            return true;
        } catch (NumberFormatException nfe) {
            return false;
        }
    }

    protected boolean isLong(String cadena) {
        try {
            Long.parseLong(cadena);
            return true;
        } catch (NumberFormatException nfe) {
            return false;
        }
    }

    protected boolean isDouble(String cadena) {
        try {
            Double.parseDouble(cadena);
            return true;
        } catch (NumberFormatException nfe) {
            return false;
        }
    }

    protected boolean isFloat(String cadena) {
        try {
            Float.parseFloat(cadena);
            return true;
        } catch (NumberFormatException nfe) {
            return false;
        }
    }

    protected boolean isEmail(String correo) {
        Pattern pat = null;
        Matcher mat = null;
        pat = Pattern.compile("^\\w+([\\.-]?\\w+)*@\\w+([\\.-]?\\w+)*(\\.\\w{2,3})+$");

        mat = pat.matcher(correo);
        if (mat.find()) {
            return true;
        } else {
            return false;
        }
    }

    //Comprueba que la fecha enviada como primer parametro sea válida, si el segundo parametro es 1,
    //el formato con el que se validará será yyyy-MM-dd; si es 2 el formato a validar será dd-MM-yyyy.
    protected boolean isDate(String fecha, int ind) 
    {
        try
        {
            fecha = fecha.trim();
            String formato="yyyy-MM-dd";
            String dia="", mes="", anio="";
           
            if(fecha.length() == 10)
            {
                if(ind == 1)
                {
                    formato = "yyyy-MM-dd";
                    anio=fecha.substring(0,4);
                    mes=fecha.substring(5,7);
                    dia=fecha.substring(8);
               
                    if(!isInteger(anio) || !isInteger(mes) || !isInteger(dia))
                        return false;
                }
                else if(ind == 2)
                {
                    formato="dd-MM-yyyy";
                    dia=fecha.substring(0,2);
                    mes=fecha.substring(3,5);
                    anio=fecha.substring(6);
                    
                    if(!isInteger(anio) || !isInteger(mes) || !isInteger(dia))
                        return false;
                }
           
                SimpleDateFormat sdf = new SimpleDateFormat(formato);
                sdf.setLenient(false);
                Date fec = sdf.parse(fecha);
            }
            else
            {
                return false;
            }
        }
        catch (Exception e)
        {
            return false;
        }

        return true;
    }
    
    //Convierte el formato de fecha enviado como primer parámetro.
    //Si el segundo parametro es 1, el formato de fecha que se tiene que enviar como primer parametro es
    //yyyy-MM-dd y la funcion la devolverá en el formato dd-MM-yyyy. Si el segundo parametro es 2,  
    //los formatos que se envia y recupera seria lo inverso.
    protected String getConvertFecha(String fecha,int tipo)
    {
        fecha = fecha.trim();
        String res="";
        String dia="", mes="", anio="";
        try 
        {   
            if(tipo==1)
            {
                anio=fecha.substring(0,4);
                mes=fecha.substring(5,7);
                dia=fecha.substring(8);
               
                res=dia+"-"+mes+"-"+anio;
            }
            else
            {
                dia=fecha.substring(0,2);
                mes=fecha.substring(3,5);
                anio=fecha.substring(6);
                
                res=anio+"-"+mes+"-"+dia;
            }
        }
        catch (Exception e) 
        {}
       
        return res;
    }
    
    //Formato de fecha aceptado yyyy-MM-dd
    //Si la respuesta es menor a cero entonces la primera fecha enviada es menor a la segunda
    //Si la respuesta es cero entonces las dos fechas enviadas son iguales
    //Si la respuesta es mayor a cero entonces la primera fecha enviada es mayor a la segunda
    protected int compareDate(String fecha1,String fecha2)
    {
        int res=0;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        Date fecha_f1 = null;       
        try
        {
            fecha_f1 = sdf.parse(fecha1);
        }
        catch(Exception e)
        {}        
                
        Date fecha_f2 = null;
        try
        {
            fecha_f2 = sdf.parse(fecha2);
        }
        catch(Exception e)
        {}
                
        res=fecha_f1.compareTo(fecha_f2);
        
        return res;
    }

    /**
     * @return the curDate
     */
    public String getCurDate() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        curDate = sdf.format(new Date());
        return curDate;
    }

    /**
     * @return the curYear
     */
    public String getCurYear() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String curDate = sdf.format(new Date());
        curYear = curDate.substring(0,4);
        return curYear;
    }

    /**
     * @return the curMonth
     */
    public String getCurMonth() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String curDate = sdf.format(new Date());
        curMonth = curDate.substring(5,7);
        return curMonth;
    }

    /**
     * @return the curDay
     */
    public String getCurDay() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String curDate = sdf.format(new Date());
        curDay = curDate.substring(8);
        return curDay;
    }

    /**
     * @return the tituloOpc
     */
    public String getTituloOpc() {
        return tituloOpc;
    }

    /**
     * @param tituloOpc the tituloOpc to set
     */
    public void setTituloOpc(String tituloOpc) {
        this.tituloOpc = tituloOpc;
    }

    /**
     * @return the regPag
     */
    public int getRegPag() {
        return regPag;
    }

    /**
     * @return the backURL
     */
    public String getBackURL() {
        return backURL;
    }

    /**
     * @param backURL the backURL to set
     */
    public void setBackURL(String backURL) {
        this.backURL = backURL;
    }

    /**
     * @return the formURL
     */
    public String getFormURL() {
        return formURL;
    }

    /**
     * @param formURL the formURL to set
     */
    public void setFormURL(String formURL) {
        this.formURL = formURL;
    }

    /**
     * @return the inicioURL
     */
    public String getInicioURL() {
        return inicioURL;
    }

    /**
     * @return the indErrParm
     */
    public String getIndErrParm() {
        return indErrParm;
    }

    /**
     * @return the titleDialog
     */
    public String getTitleDialog() {
        return titleDialog;
    }

    /**
     * @return the paginacion
     */
    public String getPaginacion() {
        paginacion += "";
        
        /*paginacion += "<table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" style=\"width: 100%\">";
        paginacion += "<tr>";
        paginacion += "<td style=\"width: 350px; padding-left: 10px;\">";*/
        
        if(curPagVis==1 || curPagVis==0)
            paginacion += "<button id=\"prev_pag\" style=\"cursor: default;\"><span class=\"ui-icon ui-icon-carat-1-w\"></span></button>";
        else
            paginacion += "<button id=\"prev_pag\" style=\"cursor: default;\"><span class=\"ui-icon ui-icon-carat-1-w\"></span></button>";
        
        paginacion += "<div class=\"text_pag\">Pag. "+curPagVis+" de "+getUltPag()+" ("+cantReg;
        
        if(cantReg==1)
        {
            paginacion += " registro)</div>";
        }
        else
        {
            paginacion += " registros)</div>";
        }
        
        paginacion += "<input type=\"hidden\" name=\"ultPag\" id=\"ultPag\" value=\""+getUltPag()+"\" />";
        
        if(curPagVis==0 || curPagVis==getUltPag())
            paginacion += "<button id=\"next_pag\" style=\"cursor: default;\"><span class=\"ui-icon ui-icon-carat-1-e\"></span></button>";
        else
            paginacion += "<button id=\"next_pag\" style=\"cursor: default;\"><span class=\"ui-icon ui-icon-carat-1-e\"></span></button>";
        
        paginacion += "<button type=\"submit\" id=\"btn_search\" style=\"margin-left: 20px;\"><span class=\"ui-icon ui-icon-search\" style=\"display: inline-block; height: 13px;\"></span> Buscar</button>";
        
        /*paginacion += "</td>";
        paginacion += "<td>";
        paginacion += "</td>";
        paginacion += "<td style=\"width: 350px; padding-right: 10px; text-align: right;\">";*/
        paginacion += "<div style=\"float: right; padding-top: 8px;\">";
        paginacion += "<div class=\"text_pag\">"+sesion_sga.get("ses_descon")+"&nbsp;&nbsp;-&nbsp;&nbsp;"+sesion_sga.get("ses_desloccon")+"</div>";
        paginacion += "</div>";
        /*paginacion += "</td>";
        paginacion += "</tr>";
        paginacion += "</table>";*/
        
        return paginacion;
    }
    
    /**
     * @return the paginacion
     */
    public String getPaginacionPopUp() {
        paginacionPopUp += "";
        
        if(curPagVis==1 || curPagVis==0)
            paginacionPopUp += "<button type=\"button\" id=\"prev_pag_pu\" style=\"cursor: default;\"><span class=\"ui-icon ui-icon-carat-1-w\"></span></button>";
        else
            paginacionPopUp += "<button type=\"button\" id=\"prev_pag_pu\" style=\"cursor: default;\"><span class=\"ui-icon ui-icon-carat-1-w\"></span></button>";
        
        paginacionPopUp += "<div class=\"text_pag\">Pag. "+curPagVis+" de "+getUltPag()+" ("+cantReg;
        
        if(cantReg==1)
        {
            paginacionPopUp += " registro)</div>";
        }
        else
        {
            paginacionPopUp += " registros)</div>";
        }
        
        paginacionPopUp += "<input type=\"hidden\" name=\"ultPag\" id=\"ultPag\" value=\""+getUltPag()+"\" />";
        
        if(curPagVis==0 || curPagVis==getUltPag())
            paginacionPopUp += "<button type=\"button\" id=\"next_pag_pu\" style=\"cursor: default;\"><span class=\"ui-icon ui-icon-carat-1-e\"></span></button>";
        else
            paginacionPopUp += "<button type=\"button\" id=\"next_pag_pu\" style=\"cursor: default;\"><span class=\"ui-icon ui-icon-carat-1-e\"></span></button>";
        
        paginacionPopUp += "<button type=\"button\" type=\"submit\" id=\"btn_search_pu\" style=\"margin-left: 20px;\"><span class=\"ui-icon ui-icon-search\" style=\"display: inline-block; height: 13px;\"></span> Buscar</button>";
        
        return paginacionPopUp;
    }

    /**
     * @return the urlPaginacion
     */
    public String getUrlPaginacion() {
        return urlPaginacion;
    }

    /**
     * @return the jsPaginacion
     */
    public String getJsPaginacion() {
        jsPaginacion += " $('#prev_pag').button(); ";
        jsPaginacion += " $('#next_pag').button(); ";
        jsPaginacion += " $('#btn_search').button(); ";
        
        if(curPagVis==1 || curPagVis==0)
            jsPaginacion += " $('#prev_pag').button('disable'); ";
            
        if(curPagVis==0 || curPagVis==getUltPag())
            jsPaginacion += " $('#next_pag').button('disable'); ";
        
        //jsPaginacion += " $('#first_pag').click(function(){ $('#curPag_f').val(1); $('#frm_usu_princ').attr('action','"+baseURL+urlPaginacion+"'); $('#frm_usu_princ').submit(); }); ";
        jsPaginacion += " $('#prev_pag').click(function(){ $('#curPag_f').val("+ (curPagVis-1) +"); $('#frm_princ').attr('action','"+baseURL+urlPaginacion+"'); $('#frm_princ').submit(); }); ";
        jsPaginacion += " $('#next_pag').click(function(){ $('#curPag_f').val("+ (curPagVis+1) +"); $('#frm_princ').attr('action','"+baseURL+urlPaginacion+"'); $('#frm_princ').submit(); }); ";
        jsPaginacion += " $('#btn_search').click(function(){ $('#curPag_f').val(1); }); ";
        //jsPaginacion += " $('#last_pag').click(function(){ $('#curPag_f').val("+ getUltPag() +"); $('#frm_usu_princ').attr('action','"+baseURL+urlPaginacion+"'); $('#frm_usu_princ').submit(); }); ";
        
        return jsPaginacion;
    }
    
    /**
     * @return the jsPaginacion
     */
    public String getJsPaginacionPopUp() {
        jsPaginacionPopUp += " $('#prev_pag_pu').button(); ";
        jsPaginacionPopUp += " $('#next_pag_pu').button(); ";
        jsPaginacionPopUp += " $('#btn_search_pu').button(); ";
        
        if(curPagVis==1 || curPagVis==0)
            jsPaginacionPopUp += " $('#prev_pag_pu').button('disable'); ";
            
        if(curPagVis==0 || curPagVis==getUltPag())
            jsPaginacionPopUp += " $('#next_pag_pu').button('disable'); ";
        
        jsPaginacionPopUp += " $('#prev_pag_pu').click(function(){ $('#curPag_f_pu').val("+ (curPagVis-1) +"); $('#frm_princ_pu').attr('action','"+baseURL+urlPaginacion+"'); "
                + " $.post( '"+baseURL+urlPaginacion+"', $('#frm_princ_pu').serialize(), function(resultado){ $('#"+divPopUp+"').html(resultado);  } ); }); ";
        jsPaginacionPopUp += " $('#next_pag_pu').click(function(){ $('#curPag_f_pu').val("+ (curPagVis+1) +"); $('#frm_princ_pu').attr('action','"+baseURL+urlPaginacion+"'); "
                + " $.post( '"+baseURL+urlPaginacion+"', $('#frm_princ_pu').serialize(), function(resultado){ $('#"+divPopUp+"').html(resultado);  } ); }); ";
        jsPaginacionPopUp += " $('#btn_search_pu').click(function(){ $('#curPag_f_pu').val(1); $('#frm_princ_pu').attr('action','"+baseURL+urlPaginacion+"'); "
                + " $.post( '"+baseURL+urlPaginacion+"', $('#frm_princ_pu').serialize(), function(resultado){ $('#"+divPopUp+"').html(resultado);  } ); }); ";
        
        return jsPaginacionPopUp;
    }
    
    public void varReturnProcess(int indic)
    {
        String aux1 = "";
        
        setVarReturn(getVarReturn().trim());
        
        if(!varReturn.trim().equals(""))
        {
            if(getVarReturn().substring(getVarReturn().length()-1).trim().equals("|"))
                setVarReturn(getVarReturn().substring(0, getVarReturn().length() - 1));
            
            String test1 = getVarReturn();
            String[] tokens1 = test1.split("\\|");
            
            for(int i=0; i<tokens1.length; i++)
            {
                String ind = tokens1[i].toString().trim().substring(0,1);
                
                if(Integer.parseInt(ind)<nivBandeja)
                {
                    aux1 += tokens1[i].toString().trim()+"|";
                    
                    if(Integer.parseInt(ind)==nivBandeja-1 && indic==0)
                    {
                        String test2 = tokens1[i].toString().trim();
                        String[] tokens2 = test2.split("%");
                        
                        backURL = tokens2[1].toString();
                        if(backURL.indexOf("?")>=0)
                            backURL = backURL.substring(0, backURL.indexOf("?"));
                    }
                }
                else if(Integer.parseInt(ind)==nivBandeja)
                {
                    String test2 = tokens1[i].toString().trim();
                    
                    test2 = test2.trim();
                    if(test2.substring(test2.length()-1).equals("%"))
                    {
                        test2 = test2+" ";
                    }
                    
                    String[] tokens2 = test2.split("%");
                    
                    if(indic > 0)
                    {
                        backURL = tokens2[1].toString();
                        if(backURL.indexOf("?")>=0)
                            backURL = backURL.substring(0, backURL.indexOf("?"));
                        
                        aux1 += tokens1[i].toString().trim()+"|";
                    }
                    
                    mtu = tokens2[2].toString();
                    mmo = tokens2[3].toString();
                    mop = tokens2[4].toString();
                    mni = tokens2[5].toString();
                    mod = tokens2[6].toString();
                    
                    //String[] tokens2 = test2.split("%");
                    
                    listVarReturn.clear();
                    
                    for(int j=7; j<=tokens2.length-1; j++)
                    {
                        listVarReturn.add(tokens2[j].toString().trim());
                    }
                }
            }
            
            setVarReturn(aux1);
        }
        
        getPermOpc();
    }
    
    /* Obtiene los permisos por opción */
    protected void getPermOpc()
    {
        helper conex = new helper();
        
        indError = conex.getErrorSQL();
        
        if(!indError.equals(""))
        {
            errores.add(indError);
        }
        else
        {
            ResultSet tabla = null;
            
            try 
            {
                tabla = conex.executeDataSet("CALL usp_getPermOpcion(?,?,?,?,?)",
                        new Object[]{ mtu,mmo,mop,mni,mod });
                indError += conex.getErrorSQL();
                
                if(!indError.equals(""))
                {
                    errores.add(indError);
                }
                else
                {
                    while(tabla.next())
                    {
                        perm = tabla.getString(1);
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
    }

    /**
     * @return the strParamsReturn
     */
    public String getStrParamsReturn() {
        return strParamsReturn;
    }

    /**
     * @param strParamsReturn the strParamsReturn to set
     */
    public void setStrParamsReturn(String strParamsReturn) {
        this.strParamsReturn = strParamsReturn;
    }

    /**
     * @return the nivOpcion
     */
    public int getNivBandeja() {
        return nivBandeja;
    }

    /**
     * @param nivOpcion the nivOpcion to set
     */
    public void setNivBandeja(int nivBandeja) {
        this.nivBandeja = nivBandeja;
    }

    /**
     * @return the varReturn
     */
    public String getVarReturn() {
        return varReturn;
    }

    /**
     * @param varReturn the varReturn to set
     */
    public void setVarReturn(String varReturn) {
        this.varReturn = varReturn;
    }

    /**
     * @return the listVarReturn
     */
    public ArrayList<String> getListVarReturn() {
        return listVarReturn;
    }

    /**
     * @param listVarReturn the listVarReturn to set
     */
    public void setListVarReturn(ArrayList<String> listVarReturn) {
        this.listVarReturn = listVarReturn;
    }

    /**
     * @return the rutaPDF
     */
    public String getRutaPDF() {
        try
    	{
            String filePath = servletRequest.getSession().getServletContext().getRealPath("/");
            this.rutaPDF = filePath + "\\pdf\\";
    	}
    	catch(Exception ex)
    	{}
        
        return rutaPDF;
    }

    /**
     * @return the rutaExcel
     */
    public String getRutaExcel() {
        try
    	{
            String filePath = servletRequest.getSession().getServletContext().getRealPath("/");
            this.rutaExcel = filePath + "\\excel\\";
    	}
    	catch(Exception ex)
    	{}
        
        return rutaExcel;
    }
    
    public String getTimePDFExcel() {
        
        Date fec = new Date();
        SimpleDateFormat sdf1 = new SimpleDateFormat("ddMMyyHHmmss");
        String fecAct = sdf1.format(fec);
        String nom = fecAct;
        
        return nom;
    }

    /**
     * @return the perm
     */
    public String getPerm() {
        return perm;
    }

    /**
     * @param perm the perm to set
     */
    public void setPerm(String perm) {
        this.perm = perm;
    }

    /**
     * @return the mtu
     */
    public String getMtu() {
        return mtu;
    }

    /**
     * @param mtu the mtu to set
     */
    public void setMtu(String mtu) {
        this.mtu = mtu;
    }

    /**
     * @return the mmo
     */
    public String getMmo() {
        return mmo;
    }

    /**
     * @param mmo the mmo to set
     */
    public void setMmo(String mmo) {
        this.mmo = mmo;
    }

    /**
     * @return the mop
     */
    public String getMop() {
        return mop;
    }

    /**
     * @param mop the mop to set
     */
    public void setMop(String mop) {
        this.mop = mop;
    }

    /**
     * @return the mni
     */
    public String getMni() {
        return mni;
    }

    /**
     * @param mni the mni to set
     */
    public void setMni(String mni) {
        this.mni = mni;
    }

    /**
     * @return the mod
     */
    public String getMod() {
        return mod;
    }

    /**
     * @param mod the mod to set
     */
    public void setMod(String mod) {
        this.mod = mod;
    }

    /**
     * @return the datosOblig
     */
    public String getDatosOblig() {
        datosOblig += "<input type=\"hidden\" name=\"opcion\" id=\"opcion_h1\" /> ";
        datosOblig += "<input type=\"hidden\" name=\"backURL\" id=\"backURL_h1\" /> ";
        datosOblig += "<input type=\"hidden\" name=\"mtu\" id=\"mtu_h1\" value=\""+mtu+"\" /> ";
        datosOblig += "<input type=\"hidden\" name=\"mmo\" id=\"mmo_h1\" value=\""+mmo+"\" /> ";
        datosOblig += "<input type=\"hidden\" name=\"mop\" id=\"mop_h1\" value=\""+mop+"\" /> ";
        datosOblig += "<input type=\"hidden\" name=\"mni\" id=\"mni_h1\" value=\""+mni+"\" /> ";
        datosOblig += "<input type=\"hidden\" name=\"mod\" id=\"mod_h1\" value=\""+mod+"\" /> ";
        datosOblig += "<input type=\"hidden\" name=\"curPagVis\" id=\"curPag_f\" value=\""+curPagVis+"\" />";
        datosOblig += "<input type=\"hidden\" name=\"varReturn\" id=\"varReturn_f\" value=\""+varReturn+"\" />";
        datosOblig += "<input type=\"hidden\" name=\"nivBandeja\" id=\"nivBandeja_f\" value=\""+nivBandeja+"\" />";
        
        return datosOblig;
    }

    /**
     * @return the divPopUp
     */
    public String getDivPopUp() {
        return divPopUp;
    }

    /**
     * @param divPopUp the divPopUp to set
     */
    public void setDivPopUp(String divPopUp) {
        this.divPopUp = divPopUp;
    }

    /**
     * @return the indErrAcc
     */
    public String getIndErrAcc() {
        return indErrAcc;
    }

    /**
     * @return the idClaseAccion
     */
    public int getIdClaseAccion() {
        return idClaseAccion;
    }

    /**
     * @return the idAccion
     */
    public int getIdAccion() {
        return idAccion;
    }
    
    public void verifAccionTipoUsuario()
    {
        helper conex = null;
        ResultSet tabla = null;
        
        try 
        {
            if(sesion_sga.get("ses_estado").equals("A"))
            {    
                conex = new helper();

                indError = conex.getErrorSQL();

                if(!indError.equals(""))
                {
                    errores.add(indError);
                }
                else
                {
                    tabla = conex.executeDataSet("CALL usp_verifPermisosAccionTipoUsuario(?,?,?)", 
                                new Object[]{ idClaseAccion,idAccion,sesion_sga.get("ses_idtipusu") });

                    indError = conex.getErrorSQL();

                    if(!indError.equals(""))
                    {
                        errores.add(indError);
                    }
                    else
                    {
                        while(tabla.next())
                        {
                            if(tabla.getInt(1)==0)
                            {
                                indErrAcc = "error";
                            }
                        }
                    }
                }
            }
            else
            {
                indErrAcc = "error";
            }
        }
        catch (Exception e) 
        {
            indError = e.getMessage();
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

    /**
     * @return the indVerifAcc
     */
    public int getIndVerifAcc() {
        return indVerifAcc;
    }

    /**
     * @param indVerifAcc the indVerifAcc to set
     */
    public void setIndVerifAcc(int indVerifAcc) {
        this.indVerifAcc = indVerifAcc;
    }

    /**
     * @return the conceptoEliminar
     */
    public String getConceptoEliminar() {
        return conceptoEliminar;
    }

    /**
     * @param conceptoEliminar the conceptoEliminar to set
     */
    public void setConceptoEliminar(String conceptoEliminar) {
        this.conceptoEliminar = conceptoEliminar;
    }

    /**
     * @return the listMeses
     */
    public ArrayList<Meses> getListMeses() {
        listMeses.add(new Meses(1, "Enero", "Ene"));
        listMeses.add(new Meses(2, "Febrero", "Feb"));
        listMeses.add(new Meses(3, "Marzo", "Mar"));
        listMeses.add(new Meses(4, "Abril", "Abr"));
        listMeses.add(new Meses(5, "Mayo", "May"));
        listMeses.add(new Meses(6, "Junio", "Jun"));
        listMeses.add(new Meses(7, "Julio", "Jul"));
        listMeses.add(new Meses(8, "Agosto", "Ago"));
        listMeses.add(new Meses(9, "Septiembre", "Sep"));
        listMeses.add(new Meses(10, "Octubre", "Oct"));
        listMeses.add(new Meses(11, "Noviembre", "Nov"));
        listMeses.add(new Meses(12, "Diciembre", "Dic"));
        
        return listMeses;
    }

    /**
     * @return the listAnios
     */
    public ArrayList<Anios> getListAnios() {
        return listAnios;
    }
    
    public void setListAnios() {
        Calendar cal = Calendar.getInstance();
        
        int anio = cal.getTime().getYear()+1900;
        
        for(int i=anio-5;i<=anio;i++) {
            listAnios.add(new Anios(i));
        }
    }
    
    public ArrayList<Anios> setListAniosCustom(int antes, int despues) {
        Calendar cal = Calendar.getInstance();
        
        int anio = cal.getTime().getYear()+1900;
        
        for(int i=anio-antes; i<=anio+despues; i++) {
            listAnios.add(new Anios(i));
        }
        
        return listAnios;
    }
}
