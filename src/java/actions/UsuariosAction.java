/*
 * Action: Usuarios
 * Creado por: Angelo Ccoicca
 * Fecha de creación: 07-03-2014
 * Modificado por                   Fecha de Modificación
 * - 
 * -
 */

package actions;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ModelDriven;
import conexion.helper;
import entities.Generos;
import entities.Usuarios;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Map;
import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import utilities.Codificador;

public class UsuariosAction extends MasterAction implements ModelDriven<Usuarios>
{
    private Map<String, Object> sesion_sga = ActionContext.getContext().getSession();
    //Objeto usuario pricipal
    private Usuarios usuario = new Usuarios();
    //Arreglo de objetos usuario, se utilizará para listar en grillas y/o combobox
    private ArrayList<Usuarios> listUsuarios = new ArrayList<Usuarios>();
    
    private ArrayList<Generos> listGeneros = new ArrayList<Generos>();
    
    public Usuarios getModel()
    {
        tituloOpc = "Usuarios";// cambiar por campo titulo cuando este lista la tabla de opciones
        return usuario;
    }
    
    public String imprimir()
    {
        PrintService[] ps = PrintServiceLookup.lookupPrintServices(null, null);
        for(int i=0; i<ps.length; i++)
        {
            errores.add(ps[i].getName());
        }
        
        return "imprimir";
    }
    
    private void cantUsuariosIndex()
    {
        usuario.setIdUsu_f(usuario.getIdUsu_f().trim());
        usuario.setDesUsu_f(usuario.getDesUsu_f().trim());
        
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
                tabla = conex.executeDataSet("CALL usp_cantUsuariosIndex(?,?,?)", 
                        new Object[]{ usuario.getIdUsu_f(),usuario.getDesUsu_f(),
                            usuario.getEdoUsu_f() });
                indError = conex.getErrorSQL();
                if(!indError.equals(""))
                {
                    errores.add(indError);
                }
                else
                {
                    while (tabla.next()) 
                    {
                        cantReg = tabla.getInt(1);
                    }
                    
                    if(cantReg==0)
                        curPag = 0;
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
                {
                    indError = "error";
                    errores.add(e.getMessage());
                }
            }
        }
    }
    
    private void listUsuariosIndex()
    {
        usuario.setIdUsu_f(usuario.getIdUsu_f().trim());
        usuario.setDesUsu_f(usuario.getDesUsu_f().trim());
        
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
                tabla = conex.executeDataSet("CALL usp_listUsuariosIndex(?,?,?,?,?)", 
                        new Object[]{ usuario.getIdUsu_f(),usuario.getDesUsu_f(),
                            usuario.getEdoUsu_f(),getCurPag()*getRegPag(), getRegPag()});
                indError = conex.getErrorSQL().trim();
                if(!indError.equals(""))
                {
                    errores.add(indError);
                }
                else
                {
                    Usuarios obj;
                    while(tabla.next())
                    {
                        obj = new Usuarios();
                        obj.setIdUsu(tabla.getString("idUsu"));
                        obj.setDesUsu(tabla.getString("desUsu"));
                        obj.setEdoUsu(tabla.getString("edoUsu"));
                        listUsuarios.add(obj);
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
                {
                    indError = "error";
                    errores.add(e.getMessage());
                }
            }
        }
    }
    
    @Override
    public String execute()
    {
        urlPaginacion = "usuarios/Usuario";
        cantUsuariosIndex();
        listUsuariosIndex();
        
        return SUCCESS;
    }
    
    private void populateForm()
    {
        listGeneros.add(new Generos("M", "Masculino"));
        listGeneros.add(new Generos("F", "Femenino"));
    }
    
    private void getDatosUsuario()
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
                tabla = conex.executeDataSet("CALL usp_getDatosUsuario(?)", 
                        new Object[]{ usuario.getIdUsu() });
                indError = conex.getErrorSQL().trim();
                if(!indError.equals(""))
                {
                    errores.add(indError);
                }
                else
                {
                    while(tabla.next())
                    {
                        usuario.setIdUsu(tabla.getString("idUsu"));
                        usuario.setDesApeUsu(tabla.getString("desApeUsu"));
                        usuario.setDesNomUsu(tabla.getString("desNomUsu"));
                        usuario.setOtrGenUsu(tabla.getString("otrGenUsu"));
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
                {
                    indError = "error";
                    errores.add(e.getMessage());
                }
            }
        }
    }
    
    public String adicionar()
    {
        if(!opcion.trim().equals("A") && !opcion.trim().equals("M"))
        {
            indErrParm = "error";
        }
        else
        {
            if(opcion.equals("A"))
            {
                formURL = baseURL+"usuarios/grabarUsuario";
            }

            if(opcion.equals("M"))
            {
                if(usuario.getIdUsu().trim().equals(""))
                    indErrParm = "error";
                else
                {
                    getDatosUsuario();
                    formURL = baseURL+"usuarios/actualizarUsuario";
                }
            }
        }
        
        populateForm();
        
        return "adicionar";
    }
    
    public String grabar()
    {
        usuario.setIdUsu(usuario.getIdUsu().trim());
        usuario.setDesNomUsu(usuario.getDesNomUsu().trim());
        usuario.setDesApeUsu(usuario.getDesApeUsu().trim());
        usuario.setOtrPwdUsu(usuario.getOtrPwdUsu().trim());
        
        if(usuario.getIdUsu().equals(""))
        {
            indError = "error";
            errores.add("Ingrese el número de DNI");
        }
        else
        {
            if(usuario.getIdUsu().length()!=8)
            {
                indError = "error";
                errores.add("El DNI ingresado no es válido");
            }
        }
        
        if(usuario.getDesApeUsu().equals(""))
        {
            indError = "error";
            errores.add("Ingrese los apellidos");
        }
        
        if(usuario.getDesNomUsu().equals(""))
        {
            indError = "error";
            errores.add("Ingrese los nombres ");
        }
        
        if(usuario.getOtrPwdUsu().equals(""))
        {
            indError = "error";
            errores.add("Debe ingresar la contraseña");
        }
        
        if(indError.equals(""))
        {
            helper conex = new helper();
            
            indError = conex.getErrorSQL().trim();
            if(!indError.equals(""))
            {
                errores.add(indError);
            }
            else
            {
                String pwd = "";
                try 
                {
                    byte[] md5_bytes = Codificador.getEncoded(usuario.getOtrPwdUsu(), "md5").getBytes();
                    for (byte b : md5_bytes)
                    {
                        pwd += Integer.toHexString(Integer.parseInt(Byte.toString((byte) ((b & 0x0F0) >> 4)))).toString();
                    }

                    indError = conex.executeNonQuery("CALL usp_insUsuario(?,?,?,?,?)", 
                            new Object[]{ usuario.getIdUsu(),usuario.getDesApeUsu(),usuario.getDesNomUsu(),
                                pwd,usuario.getOtrGenUsu()});
                    indError = indError.trim();
                    if(!indError.trim().equals(""))
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
        }
        
        return "grabar";
    }
    
    public String actualizar()
    {
        usuario.setIdUsu(usuario.getIdUsu().trim());
        usuario.setDesNomUsu(usuario.getDesNomUsu().trim());
        usuario.setDesApeUsu(usuario.getDesApeUsu().trim());
        usuario.setOtrPwdUsu(usuario.getOtrPwdUsu().trim());
        
        if(usuario.getDesApeUsu().equals(""))
        {
            indError = "error";
            errores.add("Ingrese los apellidos");
        }
        
        if(usuario.getDesNomUsu().equals(""))
        {
            indError = "error";
            errores.add("Ingrese los nombres ");
        }
        
        if(indError.equals(""))
        {
            helper conex = new helper();
            
            indError = conex.getErrorSQL().trim();
            if(!indError.equals(""))
            {
                errores.add(indError);
            }
            else
            {
                String pwd = "";
                try 
                {
                    if(!usuario.getOtrPwdUsu().equals(""))
                    {
                        byte[] md5_bytes = Codificador.getEncoded(usuario.getOtrPwdUsu(), "md5").getBytes();
                        for (byte b : md5_bytes)
                        {
                            pwd += Integer.toHexString(Integer.parseInt(Byte.toString((byte) ((b & 0x0F0) >> 4)))).toString();
                        }
                    }

                    indError = conex.executeNonQuery("CALL usp_updUsuario(?,?,?,?,?)", 
                            new Object[]{ usuario.getIdUsu(),usuario.getDesApeUsu(),usuario.getDesNomUsu(),
                                pwd,usuario.getOtrGenUsu()});

                    indError = indError.trim();
                    if(!indError.trim().equals(""))
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
        }
        
        return "actualizar";
    }
    
    public String actEstado()
    {
        usuario.setIdUsu(usuario.getIdUsu().trim());
        
        if(indError.equals(""))
        {
            helper conex = new helper();
            
            indError = conex.getErrorSQL().trim();
            if(!indError.equals(""))
            {
                errores.add(indError);
            }
            else
            {
                try 
                {
                    indError = conex.executeNonQuery("CALL usp_updEdoUsuario(?,?)", 
                            new Object[]{ usuario.getIdUsu(),usuario.getEdoUsu() });
                    
                    indError = indError.trim();
                    if(!indError.trim().equals(""))
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
        }
        
        return "actEstado";
    }
    
    public String eliminar()
    {
        usuario.setIdUsu(usuario.getIdUsu().trim());
        
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
                try 
                {
                    indError = conex.executeNonQuery("CALL usp_dltUsuario(?)", 
                            new Object[]{ usuario.getIdUsu() });

                    indError = indError.trim();
                    if(indError.trim().equals(""))
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
        }
        
        return "eliminar";
    }
    
    public String vrfSeleccion()
    {
        usuario.setIdUsu(usuario.getIdUsu());
        if(usuario.getIdUsu().equals(""))
        {
            indError = "error";
            errores.add("No ha seleccionado ningun registro");
        }
        
        return "vrfSeleccion";
    }
    
    /**
     * @return the usuario
     */
    public Usuarios getUsuario() {
        return usuario;
    }

    /**
     * @param usuario the usuario to set
     */
    public void setUsuario(Usuarios usuario) {
        this.usuario = usuario;
    }

    /**
     * @return the listUsuarios
     */
    public ArrayList<Usuarios> getListUsuarios() {
        return listUsuarios;
    }

    /**
     * @return the listGeneros
     */
    public ArrayList<Generos> getListGeneros() {
        return listGeneros;
    }
}
