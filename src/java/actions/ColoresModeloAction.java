/*
 * Action: Colores modelo
 * Creado por: Angelo Ccoicca
 * Fecha de creación: 20-05-2014
 * Modificado por                   Fecha de Modificación
 * - 
 * -
 */
package actions;

import com.opensymphony.xwork2.ModelDriven;
import conexion.helper;
import entities.Colores;
import entities.ColoresModelo;
import java.sql.ResultSet;
import java.util.ArrayList;

public class ColoresModeloAction extends MasterAction implements ModelDriven<ColoresModelo>
{
    private ColoresModelo modelo = new ColoresModelo();
    private ArrayList<ColoresModelo> listColoresMod = new ArrayList<ColoresModelo>();
    private Colores modeloColor = new Colores();
    private ArrayList<Colores> listColores = new ArrayList<Colores>();

    @Override
    public ColoresModelo getModel()
    {
        tituloOpc = "Colores por Modelo";
        
        return modelo;
    }
    
    private void cantColoresModIndex()
    {
        helper conex = null;
        ResultSet tabla = null;
                
        try 
        {
            conex = new helper();
            indError += conex.getErrorSQL().trim();

            if(!indError.trim().equals(""))
            {
                errores.add(indError);
            }
            else
            {
                tabla = conex.executeDataSet("CALL usp_cantColoresModIndex(?,?)", 
                        new Object[]{ modelo.getIdMar(),modelo.getIdModMar() });

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
    
    private void listColoresModIndex()
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
                tabla = conex.executeDataSet("CALL usp_listColoresModIndex(?,?,?,?)", 
                        new Object[]{ modelo.getIdMar(),modelo.getIdModMar(),
                            (getCurPag())*getRegPag(),getRegPag() });

                indError += conex.getErrorSQL();

                if(!indError.equals(""))
                {
                    errores.add(indError);
                }
                else
                {
                    ColoresModelo obj;
                    while(tabla.next())
                    {
                        obj = new ColoresModelo();
                        obj.setIdCol(tabla.getString("idCol"));
                        obj.setDesCol(tabla.getString("desCol"));
                        listColoresMod.add(obj);
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
        nivBandeja = 3;
        
        varReturnProcess(0);
        if(!listVarReturn.isEmpty())
        {
            curPagVis = Integer.parseInt(listVarReturn.get(0).toString().trim());
            modelo.setIdMar(listVarReturn.get(1).toString().trim());
            modelo.setIdModMar(listVarReturn.get(2).toString().trim());
        }
        
        if(modelo.getIdMar().trim().equals("") || modelo.getIdModMar().trim().equals(""))
        //if(false)
        {
            indErrParm = "error";
        }
        else
        {
            urlPaginacion = "coloresMod/ColorMod";
            
            getDatosMarcaModelo();
            
            cantColoresModIndex();
            verifPag();
            listColoresModIndex();
        }
        
        return SUCCESS;
    }
    
    private void getDatosMarcaModelo()
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
                tabla = conex.executeDataSet("CALL usp_getDatosMarca(?)", 
                        new Object[]{ modelo.getIdMar() });
                indError += conex.getErrorSQL();

                if(!indError.equals(""))
                {
                    errores.add(indError);
                }
                else
                {
                    while(tabla.next())
                    {
                        modelo.setDesMar(tabla.getString("desMar"));
                    }
                    
                    tabla = null;
                    tabla = conex.executeDataSet("CALL usp_getDatosModelo(?,?)", 
                            new Object[]{ modelo.getIdMar(),modelo.getIdModMar() });
                    indError += conex.getErrorSQL();

                    if(!indError.equals(""))
                    {
                        errores.add(indError);
                    }
                    else
                    {
                        while(tabla.next())
                        {
                            modelo.setDesModMar(tabla.getString("desModMar"));
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
    
    public String adicionar()
    {
        nivBandeja = 3;
        
        varReturnProcess(1);
        
        if((!opcion.trim().equals("A") && !opcion.trim().equals("M")) || modelo.getIdMar().trim().equals("") 
                || modelo.getIdModMar().trim().equals(""))
        {
            indErrParm = "error";
        }
        else
        {
            getDatosMarcaModelo();
            
            if(opcion.equals("A"))
            {
                formURL = baseURL+"coloresMod/grabarColorMod";
            }
        }
        
        return "adicionar";
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
                        new Object[]{ modeloColor.getDesCol_f() });

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
                        new Object[]{ modeloColor.getDesCol_f(),getCurPag()*regPag,regPag });

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
    
    public String listColores()
    {
        regPag = 13;
        urlPaginacion = "coloresMod/listColoresColorMod";
        divPopUp = "DIVcolores";
        
        cantColoresIndex();
        verifPag();
        listColoresIndex();
        
        return "listColores";
    }
    
    public String grabar()
    {
        if(modelo.getIdCol().equals(""))
        {
            indError += "error";
            errores.add("Seleccione un color");
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
                    tabla = conex.executeDataSet("CALL usp_verifExistColorMod(?,?,?)", 
                            new Object[]{ modelo.getIdMar(),modelo.getIdModMar(),modelo.getIdCol() });
                    
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
                            errores.add("El color seleccionado ya fue registrado para este modelo");
                        }
                        else
                        {
                            indError += conex.executeNonQuery("CALL usp_insColorMod(?,?,?)",
                                    new Object[]{ modelo.getIdMar(),modelo.getIdModMar(),modelo.getIdCol() });

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
        
        return "grabar";
    }
    
    public String eliminar()
    {
        if(opcion.trim().equals("E"))
        {
            helper conex = null;
            
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
                    indError += conex.executeNonQuery("CALL usp_dltColorMod(?,?,?)",
                            new Object[]{ modelo.getIdMar(),modelo.getIdModMar(),modelo.getIdCol() });

                    indError = indError.trim();
                    if(indError.trim().equals(""))
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
        
        return "eliminar";
    }
    
    public String vrfSeleccion()
    {
        if(modelo.getIdCol().equals(""))
        {
            indError = "error";
            errores.add("No ha seleccionado ningun registro");
        }
        
        return "vrfSeleccion";
    }
    
    /**
     * @return the modelo
     */
    public ColoresModelo getModelo() {
        return modelo;
    }

    /**
     * @param modelo the modelo to set
     */
    public void setModelo(ColoresModelo modelo) {
        this.modelo = modelo;
    }

    /**
     * @return the listColoresMod
     */
    public ArrayList<ColoresModelo> getListColoresMod() {
        return listColoresMod;
    }

    /**
     * @return the listColores
     */
    public ArrayList<Colores> getListColores() {
        return listColores;
    }

    /**
     * @return the color
     */
    public Colores getModeloColor() {
        return modeloColor;
    }

    /**
     * @param color the color to set
     */
    public void setModeloColor(Colores modeloColor) {
        this.modeloColor = modeloColor;
    }
    
}
