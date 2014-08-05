/*
 * Action: Concesionarios
 * Creado por: Angelo Ccoicca
 * Fecha de creación: 09-04-2014
 * Modificado por                   Fecha de Modificación
 * - 
 * -
 */
package actions;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ModelDriven;
import conexion.helper;
import entities.Concesionarios;
import java.sql.ResultSet;
import java.util.ArrayList;

public class ConcesionariosAction extends MasterAction implements ModelDriven<Concesionarios>
{
    private Concesionarios modelo = new Concesionarios();
    private ArrayList<Concesionarios> listConcesionarios = new ArrayList<Concesionarios>();

    @Override
    public Concesionarios getModel()
    {
        tituloOpc = "Concesionarios";
        
        return modelo;
    }
    
    private void cantConcesionariosIndex()
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
                tabla = conex.executeDataSet("CALL usp_cantConcesionariosIndex()", new Object[]{});

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
    
    private void listConcesionariosIndex()
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
                tabla = conex.executeDataSet("CALL usp_listConcesionariosIndex(?,?)", 
                        new Object[]{ getCurPag()*regPag,regPag });

                indError = conex.getErrorSQL();

                if(!indError.equals(""))
                {
                    errores.add(indError);
                }
                else
                {
                    Concesionarios obj;
                    while(tabla.next())
                    {
                        obj = new Concesionarios();
                        obj.setIdCon(tabla.getString("idCon"));
                        obj.setDesCon(tabla.getString("desCon"));
                        listConcesionarios.add(obj);
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
    
    @Override
    public String execute()
    {
        nivBandeja = 1;
        urlPaginacion = "concesionarios/Concesionario";
        
        varReturnProcess(0);
        if(!listVarReturn.isEmpty())
        {
            curPagVis = Integer.parseInt(listVarReturn.get(0).toString().trim());
        }
        
        cantConcesionariosIndex();
        verifPag();
        listConcesionariosIndex();
        
        return SUCCESS;
    }
    
    public String adicionar()
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
                formURL = baseURL+"concesionarios/grabarConcesionario";
            }

            if(opcion.equals("M"))
            {
                
                getDatosConcesionario();
                formURL = baseURL+"concesionarios/actualizarConcesionario";
                
            }
        }
        
        return "adicionar";
    }
    
    public void getDatosConcesionario()
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
                tabla = conex.executeDataSet("CALL usp_getDatosConcesionario(?)", 
                        new Object[]{ modelo.getIdCon() });
                indError = conex.getErrorSQL();

                if(!indError.equals(""))
                {
                    errores.add(indError);
                }
                else
                {
                    while(tabla.next())
                    {
                        modelo.setIdCon(tabla.getString("idCon"));
                        modelo.setDesCon(tabla.getString("desCon"));
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
    
    public String grabar()
    {
        modelo.setDesCon(modelo.getDesCon().trim());
        
        if(modelo.getDesCon().equals(""))
        {
            indError += "error";
            errores.add("Ingrese el nombre del concesionario");
        }
        
        if(indError.equals(""))
        {
            helper conex = null;
            
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
                    indError = conex.executeNonQuery("CALL usp_insConcesionario(?)", 
                            new Object[]{ modelo.getDesCon() });

                    if(!indError.equals(""))
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
        
        return "grabar";
    }
    
    public String actualizar()
    {
        modelo.setDesCon(modelo.getDesCon().trim());
        
        if(modelo.getDesCon().equals(""))
        {
            indError="error";
            errores.add("Ingrese el nombre del concesionario");
        }
        
        if(indError.equals(""))
        {
            helper conex = null;
            
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
                    indError = conex.executeNonQuery("CALL usp_updConcesionario(?,?)",
                            new Object[]{ Integer.parseInt(modelo.getIdCon()),modelo.getDesCon() });

                    if(!indError.equals(""))
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
        
        return "actualizar";
    }
    
    public String eliminar()
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
                    tabla = conex.executeDataSet("CALL usp_verifDependConcesionario(?)", 
                            new Object[]{ Integer.parseInt(modelo.getIdCon()) });
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
                            indError = conex.executeNonQuery("CALL usp_dltConcesionario(?)",
                                    new Object[]{ Integer.parseInt(modelo.getIdCon()) });

                            indError = indError.trim();
                            if(indError.trim().equals(""))
                            {
                                errores.add(indError);
                            }
                        }
                        else /* si tiene dependencias */
                        {
                            indError = "error";
                            errores.add("Existen registros dependientes del concesionario");
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
        
        return "eliminar";
    }
    
    public String vrfSeleccion()
    {
        if(modelo.getIdCon().trim().equals("") || modelo.getIdCon().trim().equals("0"))
        {
            indError = "error";
            errores.add("No ha seleccionado ningun registro");
        }
        
        return "vrfSeleccion";
    }
    
    /**
     * @return the modelo
     */
    public Concesionarios getModelo() {
        return modelo;
    }

    /**
     * @param modelo the modelo to set
     */
    public void setModelo(Concesionarios modelo) {
        this.modelo = modelo;
    }

    /**
     * @return the listConcesionarios
     */
    public ArrayList<Concesionarios> getListConcesionarios() {
        return listConcesionarios;
    }

    /**
     * @return the accion
     */
    public String getAccion() {
        accion = this.getClass().getName().substring(this.getClass().getName().lastIndexOf(".")+1);
        return accion;
    }

    /**
     * @param accion the accion to set
     */
    public void setAccion(String accion) {
        this.accion = accion;
    }
    
}
