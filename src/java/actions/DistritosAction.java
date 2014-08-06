/*
 * Action: Distritos
 * Creado por: Angelo Ccoicca
 * Fecha de creación: 13-03-2014
 * Modificado por                   Fecha de Modificación           Ind
 * - 
 * -
 */
package actions;

import com.opensymphony.xwork2.ModelDriven;
import conexion.helper;
import entities.Distritos;
import java.sql.ResultSet;
import java.util.ArrayList;

public class DistritosAction extends MasterAction implements ModelDriven<Distritos>
{
    private Distritos modelo = new Distritos();
    private ArrayList<Distritos> listDistritos = new ArrayList<Distritos>();

    @Override
    public Distritos getModel()
    {
        tituloOpc = "Distritos";
        idClaseAccion = 5;
        
        return modelo;
    }
    
    public String vrfSeleccion()
    {
        idAccion = 1;
        
        verifAccionTipoUsuario();
        
        if(indErrAcc.equals(""))
        {
            if(modelo.getIdDisPrv()==0)
            {
                indError = "error";
                errores.add("No ha seleccionado ningun registro");
            }
        }
        
        return "vrfSeleccion";
    }
    
    private void cantDistritosIndex()
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
                tabla = conex.executeDataSet("CALL usp_cantDistIndex(?,?,?)", 
                        new Object[]{ modelo.getIdDep(),modelo.getIdPrvDep(),
                            modelo.getDesDis_f().trim() });

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
    
    private void listDistritosIndex()
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
                tabla = conex.executeDataSet("CALL usp_listDistIndex(?,?,?,?,?)", 
                        new Object[]{ modelo.getIdDep(),modelo.getIdPrvDep(),modelo.getDesDis_f().trim(),
                            (getCurPag())*getRegPag(),getRegPag() });

                indError = conex.getErrorSQL();

                if(!indError.equals(""))
                {
                    errores.add(indError);
                }
                else
                {
                    Distritos obj;
                    while(tabla.next())
                    {
                        obj = new Distritos();
                        obj.setIdDisPrv(tabla.getInt("idDisPrv"));
                        obj.setDesDis(tabla.getString("desDis"));
                        listDistritos.add(obj);
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
            nivBandeja = 3;

            varReturnProcess(0);
            if(!listVarReturn.isEmpty())
            {
                curPagVis = Integer.parseInt(listVarReturn.get(0).toString().trim());
                modelo.setIdDep(Integer.parseInt(listVarReturn.get(1).toString().trim()));
                modelo.setIdPrvDep(Integer.parseInt(listVarReturn.get(2).toString().trim()));
                modelo.setDesDis_f(listVarReturn.get(3).toString().trim());
            }

            if(modelo.getIdDep()==0 || modelo.getIdPrvDep()==0)
            {
                indErrParm = "error";
            }
            else
            {
                urlPaginacion = "distritos/Distrito";

                getDatosDepartamentoYProvincia();

                modelo.setDesDis_f(modelo.getDesDis_f().trim());

                cantDistritosIndex();
                verifPag();
                listDistritosIndex();
            }
        }
        
        return SUCCESS;
    }
    
    public String adicionar()
    {
        idAccion = 3;
        
        verifAccionTipoUsuario();
        
        if(indErrAcc.equals(""))
        {
            nivBandeja = 3;

            varReturnProcess(1);

            if((!opcion.trim().equals("A") && !opcion.trim().equals("M")) || (modelo.getIdDep()==0 || modelo.getIdPrvDep()==0))
            {
                indErrParm = "error";
            }
            else
            {
                getDatosDepartamentoYProvincia();
                
                if(opcion.equals("A"))
                {
                    formURL = baseURL+"distritos/grabarDistrito";
                }

                if(opcion.equals("M"))
                {
                    if(modelo.getIdDep()==0)
                        indErrParm = "error";
                    else
                    {
                        getDatosDistrito();
                        formURL = baseURL+"distritos/actualizarDistrito";
                    }
                }
            }
        }
        
        return "adicionar";
    }
    
    private void getDatosDepartamentoYProvincia()
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
                        modelo.setDesDep(tabla.getString("desDep"));
                        modelo.setDesProv(tabla.getString("desProv"));
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
    
    private void getDatosDistrito()
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
                tabla = conex.executeDataSet("CALL usp_getDatosDistrito(?,?,?)", 
                    new Object[]{ modelo.getIdDep(),modelo.getIdPrvDep(),modelo.getIdDisPrv() });

                indError = conex.getErrorSQL();

                if(!indError.equals(""))
                {
                    errores.add(indError);
                }
                else
                {
                    while(tabla.next())
                    {
                        modelo.setDesDis(tabla.getString("desDis"));
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
            modelo.setDesProv(modelo.getDesProv().trim());

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
                        indError = conex.executeNonQuery("CALL usp_insDistrito(?,?,?)",
                                new Object[]{ modelo.getIdDep(),modelo.getIdPrvDep(),modelo.getDesDis() });

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
        
        return "grabar";
    }
    
    public String actualizar()
    {
        idAccion = 5;
        
        verifAccionTipoUsuario();
        
        if(indErrAcc.equals(""))
        {
            modelo.setDesProv(modelo.getDesProv().trim());

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
                        indError = conex.executeNonQuery("CALL usp_updDistrito(?,?,?,?)",
                                new Object[]{ modelo.getIdDep(), modelo.getIdPrvDep(), modelo.getIdDisPrv(),
                                    modelo.getDesDis() });

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
                        indError = conex.executeNonQuery("CALL usp_dltDistrito(?,?,?)",
                                new Object[]{ modelo.getIdDep(),modelo.getIdPrvDep(),modelo.getIdDisPrv() });

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
                    conex.returnConnect();
                }
            }
        }
        
        return "eliminar";
    }
    
    /**
     * @return the modelo
     */
    public Distritos getModelo() {
        return modelo;
    }

    /**
     * @param modelo the modelo to set
     */
    public void setModelo(Distritos modelo) {
        this.modelo = modelo;
    }

    /**
     * @return the listProvincias
     */
    public ArrayList<Distritos> getListDistritos() {
        return listDistritos;
    }
    
}
