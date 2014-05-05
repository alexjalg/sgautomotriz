/*
 * Action: Provincias
 * Creado por: Angelo Ccoicca
 * Fecha de creación: 12-03-2014
 * Modificado por                   Fecha de Modificación
 * - 
 * -
 */

package actions;

import com.opensymphony.xwork2.ModelDriven;
import conexion.helper;
import entities.Provincias;
import java.sql.ResultSet;
import java.util.ArrayList;

public class ProvinciasAction extends MasterAction implements ModelDriven<Provincias>
{
    private Provincias modelo = new Provincias();
    private ArrayList<Provincias> listProvincias = new ArrayList<Provincias>();
    
    @Override
    public Provincias getModel()
    {
        tituloOpc = "Provincias";// cambiar por campo titulo cuando este lista la tabla de opciones
        return modelo;
    }
    
    private void cantProvinciasIndex()
    {
        helper conex = new helper();
        indError = conex.getErrorSQL().trim();
        
        if(!indError.trim().equals(""))
        {
            errores.add(indError);
        }
        else
        {
            ResultSet tabla = null;
            
            try 
            {
                tabla = conex.executeDataSet("CALL usp_cantProvIndex(?,?)", 
                        new Object[]{ modelo.getIdDep(),modelo.getDesProv_f().trim() });
                
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
    
    private void listProvinciasIndex()
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
                tabla = conex.executeDataSet("CALL usp_listProvIndex(?,?,?,?)", 
                        new Object[]{ modelo.getIdDep(),modelo.getDesProv_f().trim(),
                            (getCurPag())*getRegPag(),getRegPag() });
                
                indError = conex.getErrorSQL();
                
                if(!indError.equals(""))
                {
                    errores.add(indError);
                }
                else
                {
                    Provincias obj;
                    while(tabla.next())
                    {
                        obj = new Provincias();
                        obj.setIdPrvDep(tabla.getInt("idPrvDep"));
                        obj.setDesProv(tabla.getString("desProv"));
                        listProvincias.add(obj);
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
        nivBandeja = 2;
        
        varReturnProcess(0);
        if(!listVarReturn.isEmpty())
        {
            curPagVis = Integer.parseInt(listVarReturn.get(0).toString().trim());
            modelo.setIdDep(Integer.parseInt(listVarReturn.get(1).toString().trim()));
            modelo.setDesProv_f(listVarReturn.get(2).toString().trim());
        }
        
        if(modelo.getIdDep()==0)
        {
            indErrParm = "error";
        }
        else
        {
            urlPaginacion = "provincias/Provincia";
        
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
                    tabla = conex.executeDataSet("CALL usp_getDatosDepartamento(?)", 
                            new Object[]{ modelo.getIdDep() });
                    
                    indError = conex.getErrorSQL();
                    
                    if(!indError.equals(""))
                    {
                        errores.add(indError);
                    }
                    else
                    {
                        while(tabla.next())
                        {
                            modelo.setDesDep(tabla.getString("desDep"));
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
        
            modelo.setDesProv_f(modelo.getDesProv_f().trim());

            cantProvinciasIndex();
            verifPag();
            listProvinciasIndex();
        }
        
        return SUCCESS;
    }
    
    public String adicionar()
    {
        nivBandeja = 2;
        
        if((!opcion.trim().equals("A") && !opcion.trim().equals("M")) || modelo.getIdDep()==0)
        {
            indErrParm = "error";
        }
        else
        {
            varReturnProcess(1);
            
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
                    tabla = conex.executeDataSet("CALL usp_getDatosDepartamento(?)", 
                            new Object[]{ modelo.getIdDep() });
                    
                    indError = conex.getErrorSQL();
                    
                    if(!indError.equals(""))
                    {
                        errores.add(indError);
                    }
                    else
                    {
                        while(tabla.next())
                        {
                            modelo.setDesDep(tabla.getString("desDep"));
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
            
            if(opcion.equals("A"))
            {
                formURL = baseURL+"provincias/grabarProvincia";
            }

            if(opcion.equals("M"))
            {
                if(modelo.getIdDep()==0)
                    indErrParm = "error";
                else
                {
                    getDatosProvincia();
                    formURL = baseURL+"provincias/actualizarProvincia";
                }
            }
        }
        
        return "adicionar";
    }
    
    private void getDatosProvincia()
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
                tabla = conex.executeDataSet("CALL usp_getDatosProvincia(?,?)", 
                    new Object[]{ modelo.getIdDep(),modelo.getIdPrvDep() });
                
                indError = conex.getErrorSQL();
                
                if(!indError.equals(""))
                {
                    errores.add(indError);
                }
                else
                {
                    while(tabla.next())
                    {
                        modelo.setDesProv(tabla.getString("desProv"));
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
        modelo.setDesProv(modelo.getDesProv().trim());
        
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
                indError = conex.executeNonQuery("CALL usp_insProvincia(?,?)",
                        new Object[]{ modelo.getIdDep(),modelo.getDesProv() });
                
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
        modelo.setDesProv(modelo.getDesProv().trim());
        
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
                indError = conex.executeNonQuery("CALL usp_updProvincia(?,?,?)",
                        new Object[]{ modelo.getIdDep(), modelo.getIdPrvDep(), 
                            modelo.getDesProv() });
                
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
                    tabla = conex.executeDataSet("CALL usp_verifDependProv(?,?)", 
                            new Object[]{ modelo.getIdDep(),modelo.getIdPrvDep() });
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
                            indError = conex.executeNonQuery("CALL usp_dltProvincia(?,?)",
                                    new Object[]{ modelo.getIdDep(),modelo.getIdPrvDep() });

                            indError = indError.trim();
                            if(indError.trim().equals(""))
                            {
                                errores.add(indError);
                            }
                        }
                        else /* si tiene dependencias */
                        {
                            indError = "error";
                            errores.add("Existen registros dependientes de la provincia");
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
        if(modelo.getIdPrvDep()==0)
        {
            indError = "error";
            errores.add("No ha seleccionado ningun registro");
        }
        
        return "vrfSeleccion";
    }
    
    /**
     * @return the modelo
     */
    public Provincias getModelo() {
        return modelo;
    }

    /**
     * @param modelo the modelo to set
     */
    public void setModelo(Provincias modelo) {
        this.modelo = modelo;
    }

    /**
     * @return the listProvincias
     */
    public ArrayList<Provincias> getListProvincias() {
        return listProvincias;
    }
    
}
