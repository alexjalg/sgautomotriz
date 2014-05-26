/*
 * Action: Permisos de tipos de usuario
 * Creado por: Angelo Ccoicca
 * Fecha de creación: 10-04-2014
 * Modificado por                   Fecha de Modificación
 * - 
 * -
 */
package actions;

import com.opensymphony.xwork2.ModelDriven;
import conexion.helper;
import entities.Modulos;
import entities.Opciones;
import entities.Permisos;
import entities.TipoUsuario;
import java.sql.ResultSet;
import java.util.ArrayList;

public class PermisosAction extends MasterAction implements ModelDriven<Permisos>
{
    private Permisos modelo = new Permisos();
    private Modulos modeloModulo = new Modulos();
    private Opciones modeloOpcion = new Opciones();
    
    private ArrayList<TipoUsuario> listTipoUsuario = new ArrayList<TipoUsuario>();
    private ArrayList<Modulos> listModulos = new ArrayList<Modulos>();
    private ArrayList<Opciones> listOpciones = new ArrayList<Opciones>();
    
    public Permisos getModel()
    {
        return modelo;
    }
    
    /* MODULOS POR TIPO DE USUSARIO */
    
    private void cantModuTipUsuIndex()
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
                tabla = conex.executeDataSet("CALL usp_cantModuTipUsuIndex(?)",
                        new Object[]{ Integer.parseInt(modelo.getIdTipUsu()) });

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
    
    private void listModuTipUsuIndex()
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
                tabla = conex.executeDataSet("CALL usp_listModuTipUsuIndex(?,?,?)", 
                        new Object[]{ Integer.parseInt(modelo.getIdTipUsu()),
                            getCurPag()*getRegPag(),getRegPag() });

                indError += conex.getErrorSQL();

                if(!indError.equals(""))
                {
                    errores.add(indError);
                }
                else
                {
                    Modulos obj;
                    while(tabla.next())
                    {
                        obj = new Modulos();
                        //obj.setIdTipUsu(tabla.getString("idTipUsu"));
                        obj.setIdModu(tabla.getString("idModu"));
                        obj.setDesModu(tabla.getString("desModu"));
                        obj.setNumOrdVis(tabla.getString("numOrdVis"));
                        listModulos.add(obj);
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
    
    public String modulosTipUsu()
    {
        nivBandeja = 2;
        tituloOpc = "Permisos - Módulos";
        
        urlPaginacion = "permisos/modulosTipUsuPermisos";
        
        varReturnProcess(0);
        if(!listVarReturn.isEmpty())
        {
            curPagVis = Integer.parseInt(listVarReturn.get(0).toString().trim());
            modelo.setIdTipUsu(listVarReturn.get(1).toString().trim());
        }
        
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
                tabla = conex.executeDataSet("CALL usp_getDatosTipoUsuario(?)", 
                        new Object[]{ modelo.getIdTipUsu() });
                indError += conex.getErrorSQL();

                if(!indError.equals(""))
                {
                    errores.add(indError);
                }
                else
                {
                    while(tabla.next())
                    {
                        modelo.setDesTipUsu(tabla.getString("desTipUsu"));
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
        
        cantModuTipUsuIndex();
        verifPag();
        listModuTipUsuIndex();
        
        return "modulosTipUsu";
    }
    
    public String adicionarModulo()
    {
        nivBandeja = 2;
        tituloOpc = "Permisos - Módulos";
        
        varReturnProcess(1);
        if(!listVarReturn.isEmpty())
        {
            curPagVis = Integer.parseInt(listVarReturn.get(0).toString().trim());
            modelo.setIdTipUsu(listVarReturn.get(1).toString().trim());
        }
        
        if((!opcion.trim().equals("A") && !opcion.trim().equals("M")) || modelo.getIdTipUsu().equals("") 
                || modelo.getIdTipUsu().equals("0"))
        {
            indErrParm = "error";
        }
        else
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
                    tabla = conex.executeDataSet("CALL usp_getDatosTipoUsuario(?)", 
                            new Object[]{ modelo.getIdTipUsu() });
                    indError += conex.getErrorSQL();

                    if(!indError.equals(""))
                    {
                        errores.add(indError);
                    }
                    else
                    {
                        while(tabla.next())
                        {
                            modelo.setDesTipUsu(tabla.getString("desTipUsu"));
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
            
            if(opcion.equals("A"))
            {
                formURL = baseURL+"permisos/grabarModuloPermisos";
            }

            if(opcion.equals("M"))
            {
                
                getDatosModuloTipUsu();
                formURL = baseURL+"permisos/actualizarModuloPermisos";
                
            }
        }
        
        return "adicionarModulo";
    }
    
    public void getDatosModuloTipUsu()
    {
        helper conex = null;
        ResultSet tabla = null;
        
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
                tabla = conex.executeDataSet("CALL usp_getDatosModuTipUsu(?,?)", 
                        new Object[]{ Integer.parseInt(modelo.getIdTipUsu()),
                            Integer.parseInt(modelo.getIdModu()) });
                indError = conex.getErrorSQL();

                if(!indError.equals(""))
                {
                    errores.add(indError);
                }
                else
                {
                    while(tabla.next())
                    {
                        modeloModulo.setDesModu(tabla.getString("desModu").trim());
                        modeloModulo.setNumOrdVis(tabla.getString("numOrdVis").trim());
                    }
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
    
    private void cantModulosIndex()
    {
        helper conex = null;
        ResultSet tabla = null;
        
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
                tabla = conex.executeDataSet("CALL usp_cantModulosIndex()", new Object[]{});

                indError = conex.getErrorSQL();

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
    
    private void listModulosIndex()
    {
        helper conex = null;
        ResultSet tabla = null;
        
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
                tabla = conex.executeDataSet("CALL usp_listModulosIndex(?,?)", 
                        new Object[]{ getCurPag()*regPag,regPag });

                indError = conex.getErrorSQL();

                if(!indError.equals(""))
                {
                    errores.add(indError);
                }
                else
                {
                    Modulos obj;
                    while(tabla.next())
                    {
                        obj = new Modulos();
                        obj.setIdModu(tabla.getString("idModu"));
                        obj.setDesModu(tabla.getString("desModu"));
                        listModulos.add(obj);
                    }
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
    
    public String listModulos()
    {
        regPag = 13;
        urlPaginacion = "permisos/listModulosPermisos";
        divPopUp = "DIVopciones";
        
        cantModulosIndex();
        verifPag();
        listModulosIndex();
        
        return "listModulos";
    }
    
    public String grabarModulo()
    {
        modelo.setIdTipUsu(modelo.getIdTipUsu().trim());
        modelo.setIdModu(modelo.getIdModu().trim());
        modeloModulo.setNumOrdVis(modeloModulo.getNumOrdVis().trim());
        
        if(modelo.getIdModu().equals("") || modelo.getIdModu().equals("0"))
        {
            indError += "error";
            errores.add("Seleccione un módulo");
        }
        
        if(!isInteger(modeloModulo.getNumOrdVis()))
        {
            indError += "error";
            errores.add("Número de orden no válido");
        }
        else
        {
            if(Integer.parseInt(modeloModulo.getNumOrdVis())<1)
            {
                indError += "error";
                errores.add("Número de orden no válido");
            }
        }
        
        if(indError.equals(""))
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
                    tabla = conex.executeDataSet("CALL usp_verifExistsModuTipUsu(?,?)", 
                            new Object[]{ Integer.parseInt(modelo.getIdTipUsu()),
                                Integer.parseInt(modelo.getIdModu()) });
                    indError += conex.getErrorSQL();

                    if(!indError.equals(""))
                    {
                        errores.add(indError);
                    }
                    else
                    {
                        int cont = 0;
                        while(tabla.next())
                        {
                            cont = tabla.getInt(1);
                        }   

                        if(cont>0)
                        {
                            indError += "error";
                            errores.add("El módulo ya ha sido registrado para el tipo de usuario");
                        }
                        else
                        {
                            indError += conex.executeNonQuery("CALL usp_insModuTipUsu(?,?,?)", 
                                    new Object[]{ Integer.parseInt(modelo.getIdTipUsu()),
                                        Integer.parseInt(modelo.getIdModu()),
                                        Integer.parseInt(modeloModulo.getNumOrdVis()) });

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
                try 
                {
                    tabla.close();
                    conex.returnConnect();
                }
                catch (Exception e) 
                {}
            }
        }
        
        return "grabarModulo";
    }
    
    public String actualizarModulo()
    {
        modelo.setIdTipUsu(modelo.getIdTipUsu().trim());
        modelo.setIdModu(modelo.getIdModu().trim());
        modeloModulo.setNumOrdVis(modeloModulo.getNumOrdVis().trim());
        
        if(!isInteger(modeloModulo.getNumOrdVis()))
        {
            indError += "error";
            errores.add("Número de orden no válido");
        }
        else
        {
            if(Integer.parseInt(modeloModulo.getNumOrdVis())<1)
            {
                indError += "error";
                errores.add("Número de orden no válido");
            }
        }
        
        if(indError.equals(""))
        {
            helper conex = null;
                    
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
                    indError += conex.executeNonQuery("CALL usp_updModuTipUsu(?,?,?)", 
                            new Object[]{ Integer.parseInt(modelo.getIdTipUsu()),
                                Integer.parseInt(modelo.getIdModu()),
                                Integer.parseInt(modeloModulo.getNumOrdVis()) });

                    if(!indError.equals(""))
                    {
                        errores.add(indError);
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
        
        return "actualizarModulo";
    }
    
    public String eliminarModulo()
    {
        if(opcion.trim().equals("E"))
        {
            helper conex = null;
            ResultSet tabla = null;
            
            try
            {
                conex = new helper();
                indError = conex.getErrorSQL().trim();
                
                if(!indError.equals(""))
                {
                    errores.add(indError);
                }
                else
                {
                    tabla = conex.executeDataSet("CALL usp_verifDependModuTipUsu(?,?)", 
                            new Object[]{ Integer.parseInt(modelo.getIdTipUsu()),
                                Integer.parseInt(modelo.getIdModu()) });
                    indError = conex.getErrorSQL();

                    if(!indError.equals(""))
                    {
                        errores.add(indError);
                    }
                    else
                    {
                        int cant = 0;
                        while(tabla.next())
                        {
                            cant = tabla.getInt(1);
                        }

                        /* Si no tiene dependencias */
                        if(cant == 0)
                        {
                            indError = conex.executeNonQuery("CALL usp_dltModuTipUsu(?,?)",
                                    new Object[]{ Integer.parseInt(modelo.getIdTipUsu()),
                                        Integer.parseInt(modelo.getIdModu()) });

                            indError = indError.trim();
                            if(!indError.trim().equals(""))
                            {
                                errores.add(indError);
                            }
                        }
                        else /* si tiene dependencias */
                        {
                            indError = "error";
                            errores.add("Existen registros dependientes del módulo");
                        }
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
        
        return "eliminarModulo";
    }
    
    public String vrfSelecModulo()
    {
        if(modelo.getIdModu().trim().equals("") || modelo.getIdModu().trim().equals("0"))
        {
            indError = "error";
            errores.add("No ha seleccionado ningún módulo");
        }
        
        return "vrfSelecModulo";
    }
    
    
    /*OPCIONES POR MODULO Y TIPO DE USUARIO*/
    private void cantOpcionesTipUsuIndex()
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
                tabla = conex.executeDataSet("CALL usp_cantOpcTipUsuIndex(?,?,?,?)",
                        new Object[]{ Integer.parseInt(modelo.getIdTipUsu()),
                            Integer.parseInt(modelo.getIdModu()),1,0 });

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
    
    private void listOpcionesTipUsuIndex()
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
                tabla = conex.executeDataSet("CALL usp_listOpcTipUsuIndex(?,?,?,?,?,?)", 
                        new Object[]{ Integer.parseInt(modelo.getIdTipUsu()),Integer.parseInt(modelo.getIdModu()),
                            1,0,getCurPag()*getRegPag(),getRegPag() });

                indError += conex.getErrorSQL();

                if(!indError.equals(""))
                {
                    errores.add(indError);
                }
                else
                {
                    Opciones obj;
                    while(tabla.next())
                    {
                        obj = new Opciones();
                        obj.setIdOpc(tabla.getString("idOpc"));
                        obj.setDesOpc(tabla.getString("desOpc"));
                        obj.setNumOrdVis(tabla.getString("numOrdVis"));
                        obj.setCodPerOpc(tabla.getString("codPerOpc"));
                        listOpciones.add(obj);
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
    
    public String opcionesTipUsu()
    {
        nivBandeja = 3;
        tituloOpc = "Permisos - Opciones";
        
        urlPaginacion = "permisos/opcionesTipUsuPermisos";
        
        varReturnProcess(0);
        if(!listVarReturn.isEmpty())
        {
            curPagVis = Integer.parseInt(listVarReturn.get(0).toString().trim());
            modelo.setIdTipUsu(listVarReturn.get(1).toString().trim());
            modelo.setIdModu(listVarReturn.get(2).toString().trim());
        }
        
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
                tabla = conex.executeDataSet("CALL usp_getDatosTipoUsuario(?)", 
                        new Object[]{ modelo.getIdTipUsu() });
                indError += conex.getErrorSQL();

                if(!indError.equals(""))
                {
                    errores.add(indError);
                }
                else
                {
                    while(tabla.next())
                    {
                        modelo.setDesTipUsu(tabla.getString("desTipUsu"));
                    }

                    tabla = null;
                    tabla = conex.executeDataSet("CALL usp_getDatosModulo(?)", 
                            new Object[]{ modelo.getIdModu() });
                    indError += conex.getErrorSQL();

                    if(!indError.equals(""))
                    {
                        errores.add(indError);
                    }
                    else
                    {
                        while(tabla.next())
                        {
                            modelo.setDesModu(tabla.getString("desModu"));
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
            try 
            {
                tabla.close();
                conex.returnConnect();
            }
            catch (Exception e) 
            {}
        }
        
        cantOpcionesTipUsuIndex();
        verifPag();
        listOpcionesTipUsuIndex();
        
        return "opcionesTipUsu";
    }
    
    public String adicionarOpcion()
    {
        nivBandeja = 3;
        tituloOpc = "Permisos - Opciones";
        
        varReturn = varReturn.trim();
        varReturnProcess(1);
        if(!listVarReturn.isEmpty())
        {
            curPagVis = Integer.parseInt(listVarReturn.get(0).toString().trim());
            modelo.setIdTipUsu(listVarReturn.get(1).toString().trim());
            modelo.setIdModu(listVarReturn.get(2).toString().trim());
        }
        
        if((!opcion.trim().equals("A") && !opcion.trim().equals("M")) || modelo.getIdTipUsu().equals("") 
                || modelo.getIdTipUsu().equals("0") || modelo.getIdModu().equals("") || modelo.getIdModu().equals("0"))
        {
            indErrParm = "error";
        }
        else
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
                    tabla = conex.executeDataSet("CALL usp_getDatosTipoUsuario(?)", 
                            new Object[]{ modelo.getIdTipUsu() });
                    indError += conex.getErrorSQL();

                    if(!indError.equals(""))
                    {
                        errores.add(indError);
                    }
                    else
                    {
                        while(tabla.next())
                        {
                            modelo.setDesTipUsu(tabla.getString("desTipUsu"));
                        }

                        tabla = null;
                        tabla = conex.executeDataSet("CALL usp_getDatosModulo(?)", 
                                new Object[]{ modelo.getIdModu() });
                        indError += conex.getErrorSQL();

                        if(!indError.equals(""))
                        {
                            errores.add(indError);
                        }
                        else
                        {
                            while(tabla.next())
                            {
                                modelo.setDesModu(tabla.getString("desModu"));
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
                try 
                {
                    tabla.close();
                    conex.returnConnect();
                }
                catch (Exception e) 
                {}
            }
            
            if(opcion.equals("A"))
            {
                formURL = baseURL+"permisos/grabarOpcionPermisos";
            }

            if(opcion.equals("M"))
            {
                
                getDatosOpcionTipUsu();
                formURL = baseURL+"permisos/actualizarOpcionPermisos";
                
            }
        }
        
        return "adicionarOpcion";
    }
    
    public void getDatosOpcionTipUsu()
    {
        helper conex = null;
        ResultSet tabla = null;
        
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
                tabla = conex.executeDataSet("CALL usp_getDatosOpcTipUsu(?,?,?,?,?)", 
                        new Object[]{ Integer.parseInt(modelo.getIdTipUsu()),
                            Integer.parseInt(modelo.getIdModu()),
                            Integer.parseInt(modelo.getIdOpc1()),1,0 });
                indError = conex.getErrorSQL();

                if(!indError.equals(""))
                {
                    errores.add(indError);
                }
                else
                {
                    while(tabla.next())
                    {
                        modeloOpcion.setDesOpc(tabla.getString("desOpc").trim());
                        modeloOpcion.setNumOrdVis(tabla.getString("numOrdVis").trim());
                        modeloOpcion.setCodPerOpc(tabla.getString("codPerOpc").trim());
                    }
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
    
    private void cantOpcionesIndex()
    {
        helper conex = null;
        ResultSet tabla = null;
        
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
                tabla = conex.executeDataSet("CALL usp_cantOpcionesIndex(?)", 
                        new Object[]{ modeloOpcion.getDesOpc_f() });

                indError = conex.getErrorSQL();

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
    
    private void listOpcionesIndex()
    {
        helper conex = null;
        ResultSet tabla = null;
        
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
                tabla = conex.executeDataSet("CALL usp_listOpcionesIndex(?,?,?)", 
                        new Object[]{ modeloOpcion.getDesOpc_f(),getCurPag()*regPag,regPag });

                indError = conex.getErrorSQL();

                if(!indError.equals(""))
                {
                    errores.add(indError);
                }
                else
                {
                    Opciones obj;
                    while(tabla.next())
                    {
                        obj = new Opciones();
                        obj.setIdOpc(tabla.getString("idOpc"));
                        obj.setDesOpc(tabla.getString("desOpc"));
                        listOpciones.add(obj);
                    }
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
    
    public String listOpciones()
    {
        regPag = 13;
        urlPaginacion = "permisos/listOpcionesPermisos";
        divPopUp = "DIVopciones";
        
        cantOpcionesIndex();
        verifPag();
        listOpcionesIndex();
        
        return "listOpciones";
    }
    
    public String grabarOpcion()
    {
        modelo.setIdTipUsu(modelo.getIdTipUsu().trim());
        modelo.setIdModu(modelo.getIdModu().trim());
        modelo.setIdOpc1(modelo.getIdOpc1().trim());
        modeloOpcion.setNumOrdVis(modeloOpcion.getNumOrdVis().trim());
        modeloOpcion.setCodPerOpc(modeloOpcion.getCodPerOpc().trim());
        
        if(modelo.getIdOpc1().equals("") || modelo.getIdOpc1().equals("0"))
        {
            indError += "error";
            errores.add("Seleccione una opción");
        }
        
        if(!isInteger(modeloOpcion.getNumOrdVis()))
        {
            indError += "error";
            errores.add("Número de orden no válido");
        }
        else
        {
            if(Integer.parseInt(modeloOpcion.getNumOrdVis())<1)
            {
                indError += "error";
                errores.add("Número de orden no válido");
            }
        }
        
        if(modeloOpcion.getCodPerOpc().equals(""))
        {
            indError += "error";
            errores.add("Seleccione el tipo de permiso");
        }
        
        if(indError.equals(""))
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
                    tabla = conex.executeDataSet("CALL usp_verifExistsOpcTipUsu(?,?,?,?,?)", 
                            new Object[]{ Integer.parseInt(modelo.getIdTipUsu()),
                                Integer.parseInt(modelo.getIdModu()),
                                Integer.parseInt(modelo.getIdOpc1()),1,0 });
                    indError += conex.getErrorSQL();

                    if(!indError.equals(""))
                    {
                        errores.add(indError);
                    }
                    else
                    {
                        int cont = 0;
                        while(tabla.next())
                        {
                            cont = tabla.getInt(1);
                        }   

                        if(cont>0)
                        {
                            indError += "error";
                            errores.add("La opción ya ha sido registrado para el tipo de usuario");
                        }
                        else
                        {
                            indError += conex.executeNonQuery("CALL usp_insOpcTipUsu(?,?,?,?,?,?,?)", 
                                    new Object[]{ Integer.parseInt(modelo.getIdTipUsu()),
                                        Integer.parseInt(modelo.getIdModu()),
                                        Integer.parseInt(modelo.getIdOpc1()),1,0,
                                        Integer.parseInt(modeloOpcion.getNumOrdVis()),
                                        modeloOpcion.getCodPerOpc() });

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
                try 
                {
                    tabla.close();
                    conex.returnConnect();
                }
                catch (Exception e) 
                {}
            }
        }
        
        return "grabarOpcion";
    }
    
    public String actualizarOpcion()
    {
        modelo.setIdTipUsu(modelo.getIdTipUsu().trim());
        modelo.setIdModu(modelo.getIdModu().trim());
        modelo.setIdOpc1(modelo.getIdOpc1().trim());
        modeloOpcion.setNumOrdVis(modeloOpcion.getNumOrdVis().trim());
        modeloOpcion.setCodPerOpc(modeloOpcion.getCodPerOpc().trim());
        
        if(!isInteger(modeloOpcion.getNumOrdVis()))
        {
            indError += "error";
            errores.add("Número de orden no válido");
        }
        else
        {
            if(Integer.parseInt(modeloOpcion.getNumOrdVis())<1)
            {
                indError += "error";
                errores.add("Número de orden no válido");
            }
        }
        
        if(modeloOpcion.getCodPerOpc().equals(""))
        {
            indError += "error";
            errores.add("Seleccione el tipo de permiso");
        }
        
        if(indError.equals(""))
        {
            helper conex = null;
            
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
                    indError += conex.executeNonQuery("CALL usp_updOpcTipUsu(?,?,?,?,?,?,?)", 
                            new Object[]{ Integer.parseInt(modelo.getIdTipUsu()),
                                        Integer.parseInt(modelo.getIdModu()),
                                        Integer.parseInt(modelo.getIdOpc1()),1,0,
                                        Integer.parseInt(modeloOpcion.getNumOrdVis()),
                                        modeloOpcion.getCodPerOpc() });

                    if(!indError.equals(""))
                    {
                        errores.add(indError);
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
        
        return "actualizarOpcion";
    }
    
    public String eliminarOpcion()
    {
        if(opcion.trim().equals("E"))
        {
            helper conex = null;
            ResultSet tabla = null;
            
            try
            {
                conex = new helper();
                indError = conex.getErrorSQL().trim();

                if(!indError.equals(""))
                {
                    errores.add(indError);
                }
                else
                {
                    tabla = conex.executeDataSet("CALL usp_verifDependOpcTipUsu(?,?,?)", 
                            new Object[]{ Integer.parseInt(modelo.getIdTipUsu()),
                                Integer.parseInt(modelo.getIdModu()),
                                Integer.parseInt(modelo.getIdOpc1()) });
                    indError = conex.getErrorSQL();

                    if(!indError.equals(""))
                    {
                        errores.add(indError);
                    }
                    else
                    {
                        int cant = 0;
                        while(tabla.next())
                        {
                            cant = tabla.getInt(1);
                        }

                        /* Si no tiene dependencias */
                        if(cant == 0)
                        {
                            indError = conex.executeNonQuery("CALL usp_dltOpcTipUsu(?,?,?,?,?)",
                                    new Object[]{ Integer.parseInt(modelo.getIdTipUsu()),
                                        Integer.parseInt(modelo.getIdModu()),
                                        Integer.parseInt(modelo.getIdOpc1()),1,0 });

                            indError = indError.trim();
                            if(!indError.trim().equals(""))
                            {
                                errores.add(indError);
                            }
                        }
                        else /* si tiene dependencias */
                        {
                            indError = "error";
                            errores.add("Existen registros dependientes del módulo");
                        }
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
        
        return "eliminarOpcion";
    }
    
    public String vrfSelecOpcion()
    {
        if(modelo.getIdOpc1().trim().equals("") || modelo.getIdOpc1().trim().equals("0"))
        {
            indError = "error";
            errores.add("No ha seleccionado ninguna opción");
        }
        
        return "vrfSelecOpcion";
    }
    
    /*SUB-OPCIONES POR MODULO Y TIPO DE USUARIO*/
    private void cantSubOpcionesTipUsuIndex()
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
                tabla = conex.executeDataSet("CALL usp_cantOpcTipUsuIndex(?,?,?,?)",
                        new Object[]{ Integer.parseInt(modelo.getIdTipUsu()),
                            Integer.parseInt(modelo.getIdModu()),2,modelo.getIdOpc1() });

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
    
    private void listSubOpcionesTipUsuIndex()
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
                tabla = conex.executeDataSet("CALL usp_listOpcTipUsuIndex(?,?,?,?,?,?)", 
                        new Object[]{ Integer.parseInt(modelo.getIdTipUsu()),Integer.parseInt(modelo.getIdModu()),
                            2,modelo.getIdOpc1(),getCurPag()*getRegPag(),getRegPag() });

                indError += conex.getErrorSQL();

                if(!indError.equals(""))
                {
                    errores.add(indError);
                }
                else
                {
                    Opciones obj;
                    while(tabla.next())
                    {
                        obj = new Opciones();
                        obj.setIdOpc(tabla.getString("idOpc"));
                        obj.setDesOpc(tabla.getString("desOpc"));
                        obj.setNumOrdVis(tabla.getString("numOrdVis"));
                        obj.setCodPerOpc(tabla.getString("codPerOpc"));
                        listOpciones.add(obj);
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
    
    public String subopcionesTipUsu()
    {
        nivBandeja = 4;
        tituloOpc = "Permisos - Sub-Opciones";
        
        urlPaginacion = "permisos/subopcionesTipUsuPermisos";
        
        varReturnProcess(0);
        if(!listVarReturn.isEmpty())
        {
            curPagVis = Integer.parseInt(listVarReturn.get(0).toString().trim());
            modelo.setIdTipUsu(listVarReturn.get(1).toString().trim());
            modelo.setIdModu(listVarReturn.get(2).toString().trim());
            modelo.setIdOpc1(listVarReturn.get(3).toString().trim());
        }
        
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
                tabla = conex.executeDataSet("CALL usp_getDatosTipoUsuario(?)", 
                        new Object[]{ modelo.getIdTipUsu() });
                indError += conex.getErrorSQL();

                if(!indError.equals(""))
                {
                    errores.add(indError);
                }
                else
                {
                    while(tabla.next())
                    {
                        modelo.setDesTipUsu(tabla.getString("desTipUsu"));
                    }

                    tabla = null;
                    tabla = conex.executeDataSet("CALL usp_getDatosModulo(?)", 
                            new Object[]{ modelo.getIdModu() });
                    indError += conex.getErrorSQL();

                    if(!indError.equals(""))
                    {
                        errores.add(indError);
                    }
                    else
                    {
                        while(tabla.next())
                        {
                            modelo.setDesModu(tabla.getString("desModu"));
                        }

                        tabla = null;
                        tabla = conex.executeDataSet("CALL usp_getDatosOpcion(?)", 
                                new Object[]{ modelo.getIdOpc1() });
                        indError += conex.getErrorSQL();

                        if(!indError.equals(""))
                        {
                            errores.add(indError);
                        }
                        else
                        {
                            while(tabla.next())
                            {
                                modelo.setDesOpc1(tabla.getString("desOpc"));
                            }
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
            try 
            {
                tabla.close();
                conex.returnConnect();
            }
            catch (Exception e) 
            {}
        }
        
        cantSubOpcionesTipUsuIndex();
        verifPag();
        listSubOpcionesTipUsuIndex();
        
        return "subopcionesTipUsu";
    }
    
    public String adicionarSubOpcion()
    {
        nivBandeja = 4;
        tituloOpc = "Permisos - Sub-Opciones";
        
        varReturnProcess(1);
        if(!listVarReturn.isEmpty())
        {
            curPagVis = Integer.parseInt(listVarReturn.get(0).toString().trim());
            modelo.setIdTipUsu(listVarReturn.get(1).toString().trim());
            modelo.setIdModu(listVarReturn.get(2).toString().trim());
            modelo.setIdOpc1(listVarReturn.get(3).toString().trim());
        }
        
        if((!opcion.trim().equals("A") && !opcion.trim().equals("M")) || modelo.getIdTipUsu().equals("") 
                || modelo.getIdTipUsu().equals("0") || modelo.getIdModu().equals("") || modelo.getIdModu().equals("0")
                || modelo.getIdOpc1().equals("") || modelo.getIdOpc1().equals("0"))
        {
            indErrParm = "error";
        }
        else
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
                    tabla = conex.executeDataSet("CALL usp_getDatosTipoUsuario(?)", 
                            new Object[]{ modelo.getIdTipUsu() });
                    indError += conex.getErrorSQL();

                    if(!indError.equals(""))
                    {
                        errores.add(indError);
                    }
                    else
                    {
                        while(tabla.next())
                        {
                            modelo.setDesTipUsu(tabla.getString("desTipUsu"));
                        }

                        tabla = null;
                        tabla = conex.executeDataSet("CALL usp_getDatosModulo(?)", 
                                new Object[]{ modelo.getIdModu() });
                        indError += conex.getErrorSQL();

                        if(!indError.equals(""))
                        {
                            errores.add(indError);
                        }
                        else
                        {
                            while(tabla.next())
                            {
                                modelo.setDesModu(tabla.getString("desModu"));
                            }

                            tabla = null;
                            tabla = conex.executeDataSet("CALL usp_getDatosOpcion(?)", 
                                    new Object[]{ modelo.getIdOpc1() });
                            indError += conex.getErrorSQL();

                            if(!indError.equals(""))
                            {
                                errores.add(indError);
                            }
                            else
                            {
                                while(tabla.next())
                                {
                                    modelo.setDesOpc1(tabla.getString("desOpc"));
                                }
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
                try 
                {
                    tabla.close();
                    conex.returnConnect();
                }
                catch (Exception e) 
                {}
            }
            
            if(opcion.equals("A"))
            {
                formURL = baseURL+"permisos/grabarSubOpcionPermisos";
            }

            if(opcion.equals("M"))
            {
                
                getDatosSubOpcionTipUsu();
                formURL = baseURL+"permisos/actualizarSubOpcionPermisos";
                
            }
        }
        
        return "adicionarSubOpcion";
    }
    
    private void getDatosSubOpcionTipUsu()
    {
        helper conex = null;
        ResultSet tabla = null;

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
                tabla = conex.executeDataSet("CALL usp_getDatosOpcTipUsu(?,?,?,?,?)", 
                        new Object[]{ Integer.parseInt(modelo.getIdTipUsu()),
                            Integer.parseInt(modelo.getIdModu()),
                            Integer.parseInt(modelo.getIdOpc2()),2,
                            Integer.parseInt(modelo.getIdOpc1()) });
                indError = conex.getErrorSQL();

                if(!indError.equals(""))
                {
                    errores.add(indError);
                }
                else
                {
                    while(tabla.next())
                    {
                        modeloOpcion.setDesOpc(tabla.getString("desOpc").trim());
                        modeloOpcion.setNumOrdVis(tabla.getString("numOrdVis").trim());
                        modeloOpcion.setCodPerOpc(tabla.getString("codPerOpc").trim());
                    }
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
    
    public String grabarSubOpcion()
    {
        modelo.setIdTipUsu(modelo.getIdTipUsu().trim());
        modelo.setIdModu(modelo.getIdModu().trim());
        modelo.setIdOpc1(modelo.getIdOpc1().trim());
        modelo.setIdOpc2(modelo.getIdOpc2().trim());
        modeloOpcion.setNumOrdVis(modeloOpcion.getNumOrdVis().trim());
        modeloOpcion.setCodPerOpc(modeloOpcion.getCodPerOpc().trim());
        
        if(modelo.getIdOpc2().equals("") || modelo.getIdOpc2().equals("0"))
        {
            indError += "error";
            errores.add("Seleccione una sub-opción");
        }
        
        if(!isInteger(modeloOpcion.getNumOrdVis()))
        {
            indError += "error";
            errores.add("Número de orden no válido");
        }
        else
        {
            if(Integer.parseInt(modeloOpcion.getNumOrdVis())<1)
            {
                indError += "error";
                errores.add("Número de orden no válido");
            }
        }
        
        if(modeloOpcion.getCodPerOpc().equals(""))
        {
            indError += "error";
            errores.add("Seleccione el tipo de permiso");
        }
        
        if(indError.equals(""))
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
                    tabla = conex.executeDataSet("CALL usp_verifExistsOpcTipUsu(?,?,?,?,?)", 
                            new Object[]{ Integer.parseInt(modelo.getIdTipUsu()),
                                Integer.parseInt(modelo.getIdModu()),
                                Integer.parseInt(modelo.getIdOpc2()),2,
                                Integer.parseInt(modelo.getIdOpc1()) });
                    indError += conex.getErrorSQL();

                    if(!indError.equals(""))
                    {
                        errores.add(indError);
                    }
                    else
                    {
                        int cont = 0;
                        while(tabla.next())
                        {
                            cont = tabla.getInt(1);
                        }   

                        if(cont>0)
                        {
                            indError += "error";
                            errores.add("La opción ya ha sido registrada para el tipo de usuario");
                        }
                        else
                        {
                            indError += conex.executeNonQuery("CALL usp_insOpcTipUsu(?,?,?,?,?,?,?)", 
                                    new Object[]{ Integer.parseInt(modelo.getIdTipUsu()),
                                        Integer.parseInt(modelo.getIdModu()),
                                        Integer.parseInt(modelo.getIdOpc2()),2,
                                        Integer.parseInt(modelo.getIdOpc1()),
                                        Integer.parseInt(modeloOpcion.getNumOrdVis()),
                                        modeloOpcion.getCodPerOpc() });

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
        
        return "grabarSubOpcion";
    }
    
    public String actualizarSubOpcion()
    {
        modelo.setIdTipUsu(modelo.getIdTipUsu().trim());
        modelo.setIdModu(modelo.getIdModu().trim());
        modelo.setIdOpc1(modelo.getIdOpc1().trim());
        modelo.setIdOpc2(modelo.getIdOpc2().trim());
        modeloOpcion.setNumOrdVis(modeloOpcion.getNumOrdVis().trim());
        modeloOpcion.setCodPerOpc(modeloOpcion.getCodPerOpc().trim());
        
        if(!isInteger(modeloOpcion.getNumOrdVis()))
        {
            indError += "error";
            errores.add("Número de orden no válido");
        }
        else
        {
            if(Integer.parseInt(modeloOpcion.getNumOrdVis())<1)
            {
                indError += "error";
                errores.add("Número de orden no válido");
            }
        }
        
        if(modeloOpcion.getCodPerOpc().equals(""))
        {
            indError += "error";
            errores.add("Seleccione el tipo de permiso");
        }
        
        if(indError.equals(""))
        {
            helper conex = null;
            
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
                    indError += conex.executeNonQuery("CALL usp_updOpcTipUsu(?,?,?,?,?,?,?)", 
                            new Object[]{ Integer.parseInt(modelo.getIdTipUsu()),
                                        Integer.parseInt(modelo.getIdModu()),
                                        Integer.parseInt(modelo.getIdOpc2()),2,
                                        Integer.parseInt(modelo.getIdOpc1()),
                                        Integer.parseInt(modeloOpcion.getNumOrdVis()),
                                        modeloOpcion.getCodPerOpc() });

                    if(!indError.equals(""))
                    {
                        errores.add(indError);
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
        
        return "actualizarSubOpcion";
    }
    
    public String eliminarSubOpcion()
    {
        if(opcion.trim().equals("E"))
        {
            helper conex = null;
            
            try
            {
                conex = new helper();
                indError = conex.getErrorSQL().trim();
                
                if(!indError.equals(""))
                {
                    errores.add(indError);
                }
                else
                {
                    indError = conex.executeNonQuery("CALL usp_dltOpcTipUsu(?,?,?,?,?)",
                            new Object[]{ Integer.parseInt(modelo.getIdTipUsu()),
                                Integer.parseInt(modelo.getIdModu()),
                                Integer.parseInt(modelo.getIdOpc2()),2,
                                Integer.parseInt(modelo.getIdOpc1()) });

                    indError = indError.trim();
                    if(!indError.trim().equals(""))
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
                conex.returnConnect();
            }
        }
        
        return "eliminarSubOpcion";
    }
    
    public String vrfSelecSubOpcion()
    {
        if(modelo.getIdOpc2().trim().equals("") || modelo.getIdOpc2().trim().equals("0"))
        {
            indError = "error";
            errores.add("No ha seleccionado ninguna sub-opción");
        }
        
        return "vrfSelecSubOpcion";
    }
    
    /**
     * @return the modelo
     */
    public Permisos getModelo() {
        return modelo;
    }

    /**
     * @param modelo the modelo to set
     */
    public void setModelo(Permisos modelo) {
        this.modelo = modelo;
    }

    /**
     * @return the listTipoUsuario
     */
    public ArrayList<TipoUsuario> getListTipoUsuario() {
        return listTipoUsuario;
    }

    /**
     * @return the listModulos
     */
    public ArrayList<Modulos> getListModulos() {
        return listModulos;
    }

    /**
     * @return the listOpciones
     */
    public ArrayList<Opciones> getListOpciones() {
        return listOpciones;
    }

    /**
     * @return the modeloModulo
     */
    public Modulos getModeloModulo() {
        return modeloModulo;
    }

    /**
     * @param modeloModulo the modeloModulo to set
     */
    public void setModeloModulo(Modulos modeloModulo) {
        this.modeloModulo = modeloModulo;
    }

    /**
     * @return the modeloOpcion
     */
    public Opciones getModeloOpcion() {
        return modeloOpcion;
    }

    /**
     * @param modeloOpcion the modeloOpcion to set
     */
    public void setModeloOpcion(Opciones modeloOpcion) {
        this.modeloOpcion = modeloOpcion;
    }
}
