/*
 * Action: Colores
 * Creado por: Angelo Ccoicca
 * Fecha de creación: 19-05-2014
 * Modificado por                   Fecha de Modificación
 * - 
 * -
 */
package actions;

import com.opensymphony.xwork2.ModelDriven;
import conexion.helper;
import entities.Colores;
import java.sql.ResultSet;
import java.util.ArrayList;

public class ColoresAction extends MasterAction implements ModelDriven<Colores>
{
    private Colores modelo = new Colores();
    private ArrayList<Colores> listColores = new ArrayList<Colores>();
    
    @Override
    public Colores getModel()
    {
        tituloOpc = "Colores";
        idClaseAccion = 1;
        
        return modelo;
    }
    
    public String vrfSeleccion()
    {
        idAccion = 1;
        verifAccionTipoUsuario();
        
        if(indErrAcc.equals(""))
        {
            if(modelo.getIdCol().trim().equals(""))
            {
                indError = "error";
                errores.add("No ha seleccionado ningun registro");
            }
        }
        
        return "vrfSeleccion";
    }
    
    private void cantColoresIndex()
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
                tabla = conex.executeDataSet("CALL usp_cantColoresIndex(?)", 
                        new Object[]{ modelo.getDesCol_f() });

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
    
    private void listColoresIndex()
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
                tabla = conex.executeDataSet("CALL usp_listColoresIndex(?,?,?)", 
                        new Object[]{ modelo.getDesCol_f(),getCurPag()*regPag,regPag });

                indError += conex.getErrorSQL();

                if(!indError.equals(""))
                {
                    errores.add(indError);
                }
                else
                {
                    Colores obj;
                    while(tabla.next())
                    {
                        obj = new Colores();
                        obj.setIdCol(tabla.getString("idCol"));
                        obj.setDesCol(tabla.getString("desCol"));
                        listColores.add(obj);
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
    public String execute()
    {
        idAccion = 2;
        
        verifAccionTipoUsuario();
        
        if(indErrAcc.equals(""))
        {
            urlPaginacion = "colores/Color";

            varReturnProcess(0);
            if(!listVarReturn.isEmpty())
            {
                curPagVis = Integer.parseInt(listVarReturn.get(0).toString().trim());
            }

            cantColoresIndex();
            verifPag();
            listColoresIndex();
        }
        
        return "success";
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
                    modelo.setIdCol("");
                    formURL = baseURL+"colores/grabarColor";
                }
            }
        }
        
        return "adicionar";
    }
    
    public String modificar()
    {
        idAccion = 4;
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
                    getDatosColor();
                    formURL = baseURL+"colores/actualizarColor";

                }
            }
        }
        
        return "modificar";
    }
    
    public void getDatosColor()
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
                tabla = conex.executeDataSet("CALL usp_getDatosColor(?)", 
                        new Object[]{ modelo.getIdCol() });
                indError += conex.getErrorSQL();

                if(!indError.equals(""))
                {
                    errores.add(indError);
                }
                else
                {
                    while(tabla.next())
                    {
                        modelo.setIdCol(tabla.getString("idCol"));
                        modelo.setDesCol(tabla.getString("desCol"));
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
    
    public String grabar()
    {
        idAccion = 5;
        verifAccionTipoUsuario();
        
        if(indErrAcc.equals(""))
        {
            modelo.setIdCol(modelo.getIdCol().trim());
            modelo.setDesCol(modelo.getDesCol().trim());

            if(modelo.getIdCol().equals(""))
            {
                indError += "error";
                errores.add("Ingrese el código del color");
            }

            if(modelo.getDesCol().equals(""))
            {
                indError += "error";
                errores.add("Ingrese el nombre del color");
            }

            if(indError.equals(""))
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
                        tabla = conex.executeDataSet("CALL usp_verifExistColor(?)", 
                                new Object[]{ modelo.getIdCol() });

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
                                errores.add("Ya existe un color con el código ingresado");
                            }
                            else
                            {
                                indError = conex.executeNonQuery("CALL usp_insColor(?,?)", 
                                        new Object[]{ modelo.getIdCol(),modelo.getDesCol() });

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
    
    public String actualizar()
    {
        idAccion = 6;
        verifAccionTipoUsuario();
        
        if(indErrAcc.equals(""))
        {
            modelo.setIdCol(modelo.getIdCol().trim());
            modelo.setDesCol(modelo.getDesCol().trim());

            if(modelo.getDesCol().equals(""))
            {
                indError += "error";
                errores.add("Ingrese el nombre del color");
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
                        indError += conex.executeNonQuery("CALL usp_updColor(?,?)",
                                new Object[]{ modelo.getIdCol(),modelo.getDesCol() });

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
        }
        
        return "actualizar";
    }
    
    public String eliminar()
    {
        idAccion = 7;
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
                        tabla = conex.executeDataSet("CALL usp_verifDependColor(?)", 
                                new Object[]{ modelo.getIdCol() });
                        indError += conex.getErrorSQL();

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
                                indError += conex.executeNonQuery("CALL usp_dltColor(?)",
                                        new Object[]{ modelo.getIdCol() });

                                indError = indError.trim();
                                if(indError.trim().equals(""))
                                {
                                    errores.add(indError);
                                }
                            }
                            else /* si tiene dependencias */
                            {
                                indError += "error";
                                errores.add("Existen registros dependientes del color");
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
    
    public Colores getModelo() {
        return modelo;
    }
    
    public void setModelo(Colores modelo) {
        this.modelo = modelo;
    }
    
    public ArrayList<Colores> getListColores() {
        return listColores;
    }
}
