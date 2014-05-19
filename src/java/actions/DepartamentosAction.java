/*
 * Action: Departamentos
 * Creado por: Angelo Ccoicca
 * Fecha de creación: 10-03-2014
 * Modificado por                   Fecha de Modificación
 * - 
 * -
 */

package actions;

import com.opensymphony.xwork2.ModelDriven;
import conexion.helper;
import entities.Departamentos;
import java.sql.ResultSet;
import java.util.ArrayList;

public class DepartamentosAction extends MasterAction implements ModelDriven<Departamentos>
{
    private Departamentos modelo = new Departamentos();
    private ArrayList<Departamentos> listDptos = new ArrayList<Departamentos>();

    @Override
    public Departamentos getModel()
    {
        tituloOpc = "Departamentos";// cambiar por campo titulo cuando este lista la tabla de opciones
        return modelo;
    }
    
    private void cantDptosIndex()
    {
        helper conex = null;
        ResultSet tabla = null;
        
        try
        {
            conex = new helper();
            indError = conex.getErrorSQL().trim();

            if(!indError.trim().equals(""))
            {
                errores.add(indError);
            }
            else
            {
                tabla = conex.executeDataSet("CALL usp_cantDptosIndex(?)", 
                        new Object[]{ modelo.getDesDep_f().trim() });

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
    
    private void listDptosIndex()
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
                tabla = conex.executeDataSet("CALL usp_listDptosIndex(?,?,?)", 
                        new Object[]{ modelo.getDesDep_f().trim(),(getCurPag())*getRegPag(),getRegPag() });

                indError = conex.getErrorSQL();

                if(!indError.equals(""))
                {
                    errores.add(indError);
                }
                else
                {
                    Departamentos obj;
                    while(tabla.next())
                    {
                        obj = new Departamentos();
                        obj.setIdDep(tabla.getInt("idDep"));
                        obj.setDesDep(tabla.getString("desDep"));
                        obj.setCodTel(tabla.getString("codTel"));
                        listDptos.add(obj);
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
        
        urlPaginacion = "departamentos/Departamento";
        
        modelo.setDesDep_f(modelo.getDesDep_f().trim());
        varReturn = varReturn.trim();
        
        varReturnProcess(0);
        if(!listVarReturn.isEmpty())
        {
            curPagVis = Integer.parseInt(listVarReturn.get(0).toString().trim());
            modelo.setDesDep_f(listVarReturn.get(1).toString().trim());
        }
        
        cantDptosIndex();
        verifPag();
        listDptosIndex();
        
        return SUCCESS;
    }
    
    public String adicionar()
    {
        nivBandeja = 1;
        
        if(!opcion.trim().equals("A") && !opcion.trim().equals("M"))
        {
            indErrParm = "error";
        }
        else
        {
            varReturnProcess(1);
            
            if(opcion.equals("A"))
            {
                formURL = baseURL+"departamentos/grabarDepartamento";
            }

            if(opcion.equals("M"))
            {
                if(modelo.getIdDep()==0)
                    indErrParm = "error";
                else
                {
                    getDatosDepartamento();
                    formURL = baseURL+"departamentos/actualizarDepartamento";
                }
            }
        }
        
        return "adicionar";
    }
    
    private void getDatosDepartamento()
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
                        modelo.setCodTel(tabla.getString("codTel"));
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
        modelo.setDesDep(modelo.getDesDep().trim());
        modelo.setCodTel(modelo.getCodTel().trim());
        
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
                    indError = conex.executeNonQuery("CALL usp_insDepartamento(?,?)",
                            new Object[]{ modelo.getDesDep(),modelo.getCodTel() });

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
        modelo.setDesDep(modelo.getDesDep().trim());
        modelo.setCodTel(modelo.getCodTel().trim());
        
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
                    indError = conex.executeNonQuery("CALL usp_updDepartamento(?,?,?)",
                            new Object[]{ modelo.getIdDep(), modelo.getDesDep(), modelo.getCodTel() });

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
                    tabla = conex.executeDataSet("CALL usp_verifDependDpto(?)", 
                            new Object[]{ modelo.getIdDep() });
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
                            indError = conex.executeNonQuery("CALL usp_dltDepartamento(?)",
                                    new Object[]{ modelo.getIdDep() });

                            indError = indError.trim();
                            if(indError.trim().equals(""))
                            {
                                errores.add(indError);
                            }
                        }
                        else /* si tiene dependencias */
                        {
                            indError = "error";
                            errores.add("Existen registros dependientes del departamento");
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
        
        return "eliminar";
    }
    
    public String vrfSeleccion()
    {
        if(modelo.getIdDep()==0)
        {
            indError = "error";
            errores.add("No ha seleccionado ningun registro");
        }
        
        return "vrfSeleccion";
    }
    
    /**
     * @return the modelo
     */
    public Departamentos getModelo() {
        return modelo;
    }

    /**
     * @param modelo the modelo to set
     */
    public void setModelo(Departamentos modelo) {
        this.modelo = modelo;
    }

    /**
     * @return the listDptos
     */
    public ArrayList<Departamentos> getListDptos() {
        return listDptos;
    }
    
}
