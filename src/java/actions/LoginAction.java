/*
 * Action: Login
 * Creado por: Angelo Ccoicca
 * Fecha de creación: 07-03-2014
 * Modificado por                   Fecha de Modificación
 * - 
 * -
 */

package actions;

import java.sql.ResultSet;
import java.util.Map;

import utilities.Codificador;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ModelDriven;

import conexion.helper;
import entities.Modulos;
import entities.Opciones;
import entities.Usuarios;
import java.util.ArrayList;

public class LoginAction extends MasterAction implements ModelDriven<Usuarios> {

    private Map<String, Object> sesion_sga = ActionContext.getContext().getSession();
    private Usuarios usuario = new Usuarios();
    private ArrayList<Modulos> listModuMaster = new ArrayList<Modulos>();
    private ArrayList<Opciones> listOpcMaster = new ArrayList<Opciones>();
    private ArrayList<Opciones> listSubOpcMaster = new ArrayList<Opciones>();

    public Usuarios getModel() 
    {
        return usuario;
    }

    @Override
    public String execute() 
    {
        return SUCCESS;
    }

    public String verifica() 
    {
        if (getUsuario().getIdUsu().trim().equals("") || getUsuario().getOtrPwdUsu().trim().equals("")) 
        {
            indError = "";
        }

        if (errores.isEmpty())
        {
            String pwd = "", edo = "", clave = "";
            int ind=0;
            helper conex = null;
            ResultSet tabla = null;
            int cant = 0;

            try
            {
                usuario.setIdUsu(usuario.getIdUsu().trim());
                
                byte[] md5_bytes = Codificador.getEncoded(usuario.getOtrPwdUsu(), "md5").getBytes();
                for (byte b : md5_bytes)
                {
                    clave += Integer.toHexString(Integer.parseInt(Byte.toString((byte) ((b & 0x0F0) >> 4)))).toString();
                }

                conex = new helper();

                if (!conex.getErrorSQL().trim().equals(""))
                {
                    indError = conex.getErrorSQL().trim();
                    errores.add(conex.getErrorSQL());
                }
                else
                {
                    tabla = conex.executeDataSet("CALL usp_verifLogin(?,?)",
                            new Object[]{ usuario.getIdUsu().trim(),clave });

                    if (!conex.getErrorSQL().trim().equals(""))
                    {
                        indError = conex.getErrorSQL();
                        errores.add(conex.getErrorSQL());
                    }
                    else
                    {
                        while (tabla.next())
                        {
                            ind = tabla.getInt("ind");
                        }
                        
                        if(ind==1)
                        {
                            sesion_sga.put("ses_estado", "A");
                            
                            /*Date fechaAct = new Date();
                            SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
                            String fechaStr = sdf1.format(fechaAct);*/
                            
                            tabla = null;
                            tabla = conex.executeDataSet("CALL usp_getDatosUsuLogin(?)",
                                    new Object[]{ getUsuario().getIdUsu().trim() });
                            
                            while(tabla.next())
                            {
                                sesion_sga.put("ses_idusu",tabla.getString("idUsu"));
                                sesion_sga.put("ses_desusu",tabla.getString("desUsu"));
                                sesion_sga.put("ses_idtipusu", tabla.getInt("idTipUsu"));
                                

                                getModuOpcPerfil();
                                sesion_sga.put("ses_listmodumaster", listModuMaster);
                            }
                        }
                        else 
                        {
                            errores.add("No cuenta con permisos de acceso");
                            indError = "error";
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
                catch (Exception ex) 
                {}
            }
        }
        return "verifica";
    }
    
    public void getModuOpcPerfil()
    {
    	helper conex = null;
    	ResultSet tablaModu=null;
    	ResultSet tablaOpc=null;
    	ResultSet tablaSubOpc=null;
        
        try
        {
            conex = new helper();
            
            tablaModu = conex.executeDataSet("CALL usp_listModulosTipoUsu(?)", 
                    new Object[]{ Integer.parseInt(sesion_sga.get("ses_idtipusu").toString()) });
            indError += conex.getErrorSQL();
            
            if(!indError.equals(""))
            {
                errores.add(indError);
            }
            else
            {
                Modulos objModu;
                while(tablaModu.next())
                {
                    objModu = new Modulos();
                    objModu.setIdModu(tablaModu.getString("idModu"));
                    objModu.setDesModu(tablaModu.getString("desModu"));
                    objModu.setCantOpcDep(tablaModu.getInt("cantOpcDep"));

                    tablaOpc = null;
                    tablaOpc = conex.executeDataSet("CALL usp_listOpcionesModTipoUsu(?,?,?,?)", 
                            new Object[]{ Integer.parseInt(sesion_sga.get("ses_idtipusu").toString()),
                                Integer.parseInt(objModu.getIdModu()),1,0 });
                    indError += conex.getErrorSQL();
                    
                    if(!indError.equals(""))
                    {
                        errores.add(indError);
                    }
                    else
                    {
                        Opciones objOpc;
                        listOpcMaster = new ArrayList<Opciones>();
                        while(tablaOpc.next())
                        {
                            objOpc = new Opciones();
                            objOpc.setIdTipUsu(tablaOpc.getString("idTipUsu"));
                            objOpc.setIdModu(tablaOpc.getString("idModu"));
                            objOpc.setIdOpc(tablaOpc.getString("idOpc"));
                            objOpc.setNumNivOpc(tablaOpc.getString("numNiv"));
                            objOpc.setIdOpcDep(tablaOpc.getString("idOpcDep"));
                            objOpc.setDesOpc(tablaOpc.getString("desOpc"));
                            objOpc.setDesOpc(objOpc.getDesOpc().replace(" ", "&nbsp;"));
                            objOpc.setDesUrlOpc(tablaOpc.getString("desUrlOpc"));
                            objOpc.setCodPerOpc(tablaOpc.getString("codPerOpc"));
                            objOpc.setCantOpcDep(tablaOpc.getInt("cantOpcDep"));

                            tablaSubOpc = null;
                            tablaSubOpc = conex.executeDataSet("CALL usp_listOpcionesModTipoUsu(?,?,?,?)",
                                    new Object[]{ Integer.parseInt(sesion_sga.get("ses_idtipusu").toString()),
                                        Integer.parseInt(objOpc.getIdModu()),2,
                                        Integer.parseInt(objOpc.getIdOpc()) });
                            indError += conex.getErrorSQL();
                            
                            if(!indError.equals(""))
                            {
                                errores.add(indError);
                            }
                            else
                            {
                                Opciones objOpc2;
                                listSubOpcMaster = new ArrayList<Opciones>();
                                while(tablaSubOpc.next())
                                {
                                    objOpc2 = new Opciones();
                                    objOpc2.setIdTipUsu(tablaSubOpc.getString("idTipUsu"));
                                    objOpc2.setIdModu(tablaSubOpc.getString("idModu"));
                                    objOpc2.setIdOpc(tablaSubOpc.getString("idOpc"));
                                    objOpc2.setNumNivOpc(tablaSubOpc.getString("numNiv"));
                                    objOpc2.setIdOpcDep(tablaSubOpc.getString("idOpcDep"));
                                    objOpc2.setDesOpc(tablaSubOpc.getString("desOpc"));
                                    objOpc2.setDesOpc(objOpc2.getDesOpc().replace(" ", "&nbsp;"));
                                    objOpc2.setDesUrlOpc(tablaSubOpc.getString("desUrlOpc"));
                                    objOpc2.setCodPerOpc(tablaSubOpc.getString("codPerOpc"));
                                    objOpc2.setCantOpcDep(tablaSubOpc.getInt("cantOpcDep"));

                                    listSubOpcMaster.add(objOpc2);
                                }

                                objOpc.setListSubOpc(listSubOpcMaster);
                            }
                            
                            listOpcMaster.add(objOpc);
                        }
                        
                        objModu.setListOpc(listOpcMaster);
                    }
                    
                    listModuMaster.add(objModu);
                }
            }
        }
        catch(Exception e)
        {
            indError += "error";
            errores.add(e.getMessage());
        }
        finally
        {
            try
            {
                tablaModu.close();
                tablaOpc.close();
                tablaSubOpc.close();
                conex.returnConnect();
            }
            catch(Exception ex)
            {}
        }
    }
    
    public String salir()
    {
        sesion_sga.clear();
        
        return "salir";
    }

    public Usuarios getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuarios usuario) {
        this.usuario = usuario;
    }

    /**
     * @return the listModuMaster
     */
    public ArrayList<Modulos> getListModuMaster() {
        return listModuMaster;
    }

    /**
     * @return the listOpcMaster
     */
    public ArrayList<Opciones> getListOpcMaster() {
        return listOpcMaster;
    }

    /**
     * @return the listOpcMaster2
     */
    public ArrayList<Opciones> getListSubOpcMaster() {
        return listSubOpcMaster;
    }
}
