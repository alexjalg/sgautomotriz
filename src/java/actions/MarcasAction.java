/*
 * Action: Marcas
 * Creado por: Angelo Ccoicca
 * Fecha de creación: 16-05-2014
 * Modificado por                   Fecha de Modificación
 * - 
 * -
 */
package actions;

import com.opensymphony.xwork2.ModelDriven;
import conexion.helper;
import entities.Marcas;
import java.sql.ResultSet;
import java.util.ArrayList;

public class MarcasAction extends MasterAction implements ModelDriven<Marcas>
{
    private Marcas modelo = new Marcas();
    private ArrayList<Marcas> listMarcas = new ArrayList<Marcas>();

    @Override
    public Marcas getModel()
    {
        tituloOpc = "Marcas";
        idClaseAccion = 7;
        
        return modelo;
    }
    
    public String vrfSeleccion()
    {
        idAccion = 1;
        verifAccionTipoUsuario();
        
        if(indErrAcc.equals(""))
        {
            if(modelo.getIdMar().trim().equals(""))
            {
                indError = "error";
                errores.add("No ha seleccionado ningun registro");
            }
        }
        
        return "vrfSeleccion";
    }
    
    private void cantMarcasIndex()
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
                tabla = conex.executeDataSet("CALL usp_cantMarcasIndex()", new Object[]{});
                
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
    
    private void listMarcasIndex()
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
                tabla = conex.executeDataSet("CALL usp_listMarcasIndex(?,?)", 
                        new Object[]{ getCurPag()*regPag,regPag });

                indError = conex.getErrorSQL();

                if(!indError.equals(""))
                {
                    errores.add(indError);
                }
                else
                {
                    Marcas obj;
                    while(tabla.next())
                    {
                        obj = new Marcas();
                        obj.setIdMar(tabla.getString("idMar"));
                        obj.setDesMar(tabla.getString("desMar"));
                        listMarcas.add(obj);
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
        idAccion = 2;
        verifAccionTipoUsuario();
        
        if(indErrAcc.equals(""))
        {
            nivBandeja = 1;
            urlPaginacion = "marcas/Marca";

            varReturnProcess(0);
            if(!listVarReturn.isEmpty())
            {
                curPagVis = Integer.parseInt(listVarReturn.get(0).toString().trim());
            }

            cantMarcasIndex();
            verifPag();
            listMarcasIndex();
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
                    formURL = baseURL+"marcas/grabarMarca";
                }

                if(opcion.equals("M"))
                {

                    getDatosMarca();
                    formURL = baseURL+"marcas/actualizarMarca";

                }
            }
        }
        
        return "adicionar";
    }
    
    private void getDatosMarca()
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
                tabla = conex.executeDataSet("CALL usp_getDatosMarca(?)", 
                        new Object[]{ modelo.getIdMar() });
                indError = conex.getErrorSQL();

                if(!indError.equals(""))
                {
                    errores.add(indError);
                }
                else
                {
                    while(tabla.next())
                    {
                        modelo.setIdMar(tabla.getString("idMar"));
                        modelo.setDesMar(tabla.getString("desMar"));
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
        idAccion = 4;
        verifAccionTipoUsuario();
        
        if(indErrAcc.equals(""))
        {
            modelo.setIdMar(modelo.getIdMar().trim());
            modelo.setDesMar(modelo.getDesMar().trim());

            if(modelo.getIdMar().equals(""))
            {
                indError += "error";
                errores.add("Ingrese el código de la marca");
            }

            if(modelo.getDesMar().equals(""))
            {
                indError += "error";
                errores.add("Ingrese el nombre de la marca");
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
                        tabla = conex.executeDataSet("CALL usp_verifExistMarca(?)", 
                                new Object[]{ modelo.getIdMar() });

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
                                errores.add("Ya existe una marca con el código ingresado");
                            }
                            else
                            {
                                indError += conex.executeNonQuery("CALL usp_insMarca(?,?)",
                                            new Object[]{ modelo.getIdMar(),modelo.getDesMar() });

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
        
        return "grabar";
    }
    
    public String actualizar()
    {
        idAccion = 5;
        verifAccionTipoUsuario();
        
        if(indErrAcc.equals(""))
        {
            modelo.setIdMar(modelo.getIdMar().trim());
            modelo.setDesMar(modelo.getDesMar().trim());

            if(modelo.getDesMar().equals(""))
            {
                indError += "error";
                errores.add("Ingrese el nombre de la marca");
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
                        indError = conex.executeNonQuery("CALL usp_updMarca(?,?)", 
                                new Object[]{ modelo.getIdMar(),modelo.getDesMar() });

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
        }
        
        return "actualizar";
    }
    
    public String eliminar()
    {
        idAccion = 6;
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
                    indError = conex.getErrorSQL().trim();

                    if(!indError.equals(""))
                    {
                        errores.add(indError);
                    }
                    else
                    {
                        tabla = conex.executeDataSet("CALL usp_verifDependMarca(?)", 
                                new Object[]{ modelo.getIdMar() });
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
                                indError = conex.executeNonQuery("CALL usp_dltMarca(?)",
                                        new Object[]{ modelo.getIdMar() });

                                indError = indError.trim();
                                if(indError.trim().equals(""))
                                {
                                    errores.add(indError);
                                }
                            }
                            else /* si tiene dependencias */
                            {
                                indError = "error";
                                errores.add("Existen registros dependientes de la marca");
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
        }
        
        return "eliminar";
    }
    
    /**
     * @return the modelo
     */
    public Marcas getModelo() {
        return modelo;
    }

    /**
     * @param modelo the modelo to set
     */
    public void setModelo(Marcas modelo) {
        this.modelo = modelo;
    }

    /**
     * @return the listMarcas
     */
    public ArrayList<Marcas> getListMarcas() {
        return listMarcas;
    }
}
