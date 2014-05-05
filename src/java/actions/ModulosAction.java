/*
 * Action: Modulos
 * Creado por: Angelo Ccoicca
 * Fecha de creación: 09-04-2014
 * Modificado por                   Fecha de Modificación
 * - 
 * -
 */
package actions;

import com.opensymphony.xwork2.ModelDriven;
import conexion.helper;
import entities.Modulos;
import java.sql.ResultSet;
import java.util.ArrayList;

public class ModulosAction extends MasterAction implements ModelDriven<Modulos>
{
    private Modulos modelo = new Modulos();
    private ArrayList<Modulos> listModulos = new ArrayList<Modulos>();
    
    @Override
    public Modulos getModel()
    {
        tituloOpc = "Modulos";
        
        return modelo;
    }
    
    private void cantModulosIndex()
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
    
    private void listModulosIndex()
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
                tabla = conex.executeDataSet("CALL usp_listModulosIndex(?,?)", 
                        new Object[]{ getCurPag()*getRegPag(),getRegPag() });
                
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
    
    @Override
    public String execute()
    {
        urlPaginacion = "modulos/Modulo";
        
        cantModulosIndex();
        verifPag();
        listModulosIndex();
        
        return SUCCESS;
    }
    
    public String adicionar()
    {
        if((!opcion.trim().equals("A") && !opcion.trim().equals("M")))
        {
            indErrParm = "error";
        }
        else
        {
            if(opcion.equals("A"))
            {
                formURL = baseURL+"modulos/grabarModulo";
            }

            if(opcion.equals("M"))
            {
                
                getDatosModulo();
                formURL = baseURL+"modulos/actualizarModulo";
                
            }
        }
        
        return "adicionar";
    }
    
    public void getDatosModulo()
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
                tabla = conex.executeDataSet("CALL usp_getDatosModulo(?)", 
                        new Object[]{ modelo.getIdModu() });
                indError = conex.getErrorSQL();
                
                if(!indError.equals(""))
                {
                    errores.add(indError);
                }
                else
                {
                    while(tabla.next())
                    {
                        modelo.setIdModu(tabla.getString("idModu"));
                        modelo.setDesModu(tabla.getString("desModu"));
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
    
    public String grabar()
    {
        modelo.setDesModu(modelo.getDesModu().trim());
        
        helper conex = new helper();
        indError = conex.getErrorSQL();
        
        if(!indError.equals(""))
        {
            errores.add(indError);
        }
        else
        {
            try 
            {
                indError = conex.executeNonQuery("CALL usp_insModulo(?)", 
                        new Object[]{ modelo.getDesModu() });
                
                if(!indError.equals(""))
                {
                    errores.add(indError);
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
        modelo.setDesModu(modelo.getDesModu().trim());
        
        helper conex = new helper();
        
        indError = conex.getErrorSQL();
        
        if(!indError.equals(""))
        {
            errores.add(indError);
        }
        else
        {
            try 
            {
                indError = conex.executeNonQuery("CALL usp_updModulo(?,?)",
                        new Object[]{ Integer.parseInt(modelo.getIdModu()),modelo.getDesModu() });
                
                if(!indError.equals(""))
                {
                    errores.add(indError);
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
            helper conex = new helper();

            indError = conex.getErrorSQL().trim();
            if(!indError.equals(""))
            {
                errores.add(indError);
            }
            else
            {
                ResultSet tabla = null;
                try 
                {
                    tabla = conex.executeDataSet("CALL usp_verifDependModulo(?)", 
                            new Object[]{ Integer.parseInt(modelo.getIdModu()) });
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
                            indError = conex.executeNonQuery("CALL usp_dltModulo(?)",
                                    new Object[]{ Integer.parseInt(modelo.getIdModu()) });

                            indError = indError.trim();
                            if(indError.trim().equals(""))
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
    
    public String vrfSeleccion()
    {
        if(modelo.getIdModu().trim().equals("") || modelo.getIdModu().trim().equals("0"))
        {
            indError = "error";
            errores.add("No ha seleccionado ningun registro");
        }
        
        return "vrfSeleccion";
    }

    /**
     * @return the modelo
     */
    public Modulos getModelo() {
        return modelo;
    }

    /**
     * @param modelo the modelo to set
     */
    public void setModelo(Modulos modelo) {
        this.modelo = modelo;
    }

    /**
     * @return the listModulos
     */
    public ArrayList<Modulos> getListModulos() {
        return listModulos;
    }
    
}
