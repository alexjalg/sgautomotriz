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
import entities.Concesionarios;
import entities.Generos;
import entities.Locales;
import entities.TipoUsuario;
import entities.Usuarios;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import utilities.Codificador;

public class UsuariosAction extends MasterAction implements ModelDriven<Usuarios> {

    private Map<String, Object> sesion_sga = ActionContext.getContext().getSession();
    //Objeto usuario pricipal
    private Usuarios modelo = new Usuarios();
    //Arreglo de objetos usuario, se utilizará para listar en grillas y/o combobox
    private ArrayList<Usuarios> listUsuarios = new ArrayList<Usuarios>();
    private ArrayList<Concesionarios> listConcesionarios = new ArrayList<Concesionarios>();
    private ArrayList<Locales> listLocales = new ArrayList<Locales>();
    private ArrayList<TipoUsuario> listTipoUsuario = new ArrayList<TipoUsuario>();

    public Usuarios getModel() {
        tituloOpc = "Usuarios";
        idClaseAccion = 15;
        
        return modelo;
    }
    
    public String vrfSeleccion() {
        idAccion = 1;
        verifAccionTipoUsuario();

        if (indErrAcc.equals("")) {
            modelo.setIdUsu(modelo.getIdUsu());
            if (modelo.getIdUsu().equals("")) {
                indError = "error";
                errores.add("No ha seleccionado ningun registro");
            }
        }

        return "vrfSeleccion";
    }

    public String imprimir() {
        PrintService[] ps = PrintServiceLookup.lookupPrintServices(null, null);
        for (int i = 0; i < ps.length; i++) {
            errores.add(ps[i].getName());
        }

        return "imprimir";
    }

    private void cantUsuariosIndex() {
        modelo.setIdUsu_f(modelo.getIdUsu_f().trim());
        modelo.setDesUsu_f(modelo.getDesUsu_f().trim());

        helper conex = null;
        ResultSet tabla = null;

        try {
            conex = new helper();
            indError = conex.getErrorSQL().trim();

            if (!indError.equals("")) {
                errores.add(indError);
            } else {
                tabla = conex.executeDataSet("CALL usp_cantUsuariosIndex(?,?,?,?)",
                        new Object[]{modelo.getIdUsu_f(), modelo.getDesUsu_f(),
                            modelo.getIdTipUsu_f(),modelo.getEdoUsu_f()});
                indError = conex.getErrorSQL();
                if (!indError.equals("")) {
                    errores.add(indError);
                } else {
                    while (tabla.next()) {
                        cantReg = tabla.getInt(1);
                    }
                }
            }
        } catch (Exception e) {
            indError = "error";
            errores.add(e.getMessage());
        } finally {
            try {
                tabla.close();
                conex.returnConnect();
            } catch (Exception e) {
                indError = "error";
                errores.add(e.getMessage());
            }
        }
    }

    private void listUsuariosIndex() {
        modelo.setIdUsu_f(modelo.getIdUsu_f().trim());
        modelo.setDesUsu_f(modelo.getDesUsu_f().trim());

        helper conex = null;
        ResultSet tabla = null;

        try {
            conex = new helper();
            indError = conex.getErrorSQL().trim();
            if (!indError.equals("")) {
                errores.add(indError);
            } else {
                tabla = conex.executeDataSet("CALL usp_listUsuariosIndex(?,?,?,?,?,?)",
                        new Object[]{modelo.getIdUsu_f(), modelo.getDesUsu_f(),
                            modelo.getIdTipUsu_f(),modelo.getEdoUsu_f(), 
                            getCurPag() * getRegPag(), getRegPag()});
                indError = conex.getErrorSQL().trim();
                if (!indError.equals("")) {
                    errores.add(indError);
                } else {
                    Usuarios obj;
                    while (tabla.next()) {
                        obj = new Usuarios();
                        obj.setIdUsu(tabla.getString("idUsu"));
                        obj.setDesUsu(tabla.getString("desUsu"));
                        obj.setDesTipUsu(tabla.getString("desTipUsu"));
                        obj.setEdoUsu(tabla.getString("edoUsu"));
                        listUsuarios.add(obj);
                    }
                }
            }
        } catch (Exception e) {
            indError = "error";
            errores.add(e.getMessage());
        } finally {
            try {
                tabla.close();
                conex.returnConnect();
            } catch (Exception e) {
                indError = "error";
                errores.add(e.getMessage());
            }
        }
    }

    @Override
    public String execute() {
        idAccion = 2;
        verifAccionTipoUsuario();

        if (indErrAcc.equals("")) {
            urlPaginacion = "usuarios/Usuario";

            varReturnProcess(0);
            if (!listVarReturn.isEmpty()) {
                curPagVis = Integer.parseInt(listVarReturn.get(0).toString().trim());
                modelo.setIdUsu_f(listVarReturn.get(1).toString().trim());
                modelo.setDesUsu_f(listVarReturn.get(2).toString().trim());
                modelo.setIdTipUsu_f(Integer.parseInt(listVarReturn.get(3).toString().trim()));
            }
            
            helper conex = null;
            ResultSet tabla = null;
            
            try 
            {
                conex = new helper();
                indError = conex.getErrorSQL();
                
                if (!indError.equals("")) {
                    errores.add(indError);
                } else {
                    tabla = conex.executeDataSet("CALL usp_listTipoUsuario()", new Object[]{});
                    
                    indError = conex.getErrorSQL();
                    
                    if(!indError.equals("")) {
                        errores.add(indError);
                    }
                    else {
                        TipoUsuario obj;
                        while(tabla.next()) {
                            obj = new TipoUsuario();
                            obj.setIdTipUsu(tabla.getString("idTipUsu"));
                            obj.setDesTipUsu(tabla.getString("desTipUsu"));
                            listTipoUsuario.add(obj);
                        }
                    }
                }
            } catch (Exception e) {
                indError = "error";
                errores.add(e.getMessage());
            } finally {
                try{
                   tabla.close();
                   conex.returnConnect();
                } catch (Exception e) {
                }
            }

            cantUsuariosIndex();
            verifPag();
            listUsuariosIndex();
        }

        return SUCCESS;
    }

    private void populateForm() {
        helper conex = null;
        ResultSet tabla = null;

        try {
            conex = new helper();
            indError = conex.getErrorSQL();

            if (!indError.equals("")) {
                errores.add(indError);
            } else {
                tabla = null;
                tabla = conex.executeDataSet("CALL usp_listTipoUsuario()", new Object[]{});

                indError = conex.getErrorSQL();

                if (!indError.equals("")) {
                    errores.add(indError);
                } else {
                    TipoUsuario obj;
                    while (tabla.next()) {
                        obj = new TipoUsuario();
                        obj.setIdTipUsu(tabla.getString("idTipUsu"));
                        obj.setDesTipUsu(tabla.getString("desTipUsu"));
                        listTipoUsuario.add(obj);
                    }
                }
                
                tabla = null;
                tabla = conex.executeDataSet("CALL usp_listConcesionarios()", new Object[]{});

                indError = conex.getErrorSQL();

                if (!indError.equals("")) {
                    errores.add(indError);
                } else {
                    Concesionarios obj;
                    while (tabla.next()) {
                        obj = new Concesionarios();
                        obj.setIdCon(tabla.getString("idCon"));
                        obj.setDesCon(tabla.getString("desCon"));
                        listConcesionarios.add(obj);
                    }
                }

                tabla = null;
                tabla = conex.executeDataSet("CALL usp_listLocalesConces(?)",
                        new Object[]{modelo.getIdCon()});

                indError = conex.getErrorSQL();

                if (!indError.equals("")) {
                    errores.add(indError);
                } else {
                    Locales obj;
                    while (tabla.next()) {
                        obj = new Locales();
                        obj.setIdLocCon(tabla.getString("idLocCon"));
                        obj.setDesLocCon(tabla.getString("desLocCon"));
                        listLocales.add(obj);
                    }
                }
            }
        } catch (Exception e) {
            indError = "error";
            errores.add(e.getMessage().trim());
        } finally {
            try {
                tabla.close();
                conex.returnConnect();
            } catch (Exception e) {
            }
        }
    }

    private void getDatosUsuario() {
        helper conex = null;
        ResultSet tabla = null;

        try {
            conex = new helper();
            indError = conex.getErrorSQL().trim();

            if (!indError.equals("")) {
                errores.add(indError);
            } else {
                tabla = conex.executeDataSet("CALL usp_getDatosUsuario(?)",
                        new Object[]{modelo.getIdUsu()});
                indError = conex.getErrorSQL().trim();
                if (!indError.equals("")) {
                    errores.add(indError);
                } else {
                    while (tabla.next()) {
                        modelo.setIdUsu(tabla.getString("idUsu"));
                        modelo.setDesApeUsu(tabla.getString("desApeUsu"));
                        modelo.setDesNomUsu(tabla.getString("desNomUsu"));
                        modelo.setIdTipUsu(tabla.getInt("idTipUsu"));
                        modelo.setIdCon(tabla.getInt("idCon"));
                        modelo.setIdLocCon(tabla.getInt("idLocCon"));
                        modelo.setNumTelFij(tabla.getString("numTelFij"));
                        modelo.setNumAnexo(tabla.getString("numAnexo"));
                        modelo.setNumTelMov1(tabla.getString("numTelMov1"));
                        modelo.setNumTelMov2(tabla.getString("numTelMov2"));
                        modelo.setNumTelRP1(tabla.getString("numTelRP1"));
                        modelo.setNumTelRP2(tabla.getString("numTelRP2"));
                        modelo.setNumTelRP3(tabla.getString("numTelRP3"));
                        modelo.setOtrEmaTra(tabla.getString("otrEmaTra"));
                    }
                }
            }
        } catch (Exception e) {
            indError = "error";
            errores.add(e.getMessage());
        } finally {
            try {
                tabla.close();
                conex.returnConnect();
            } catch (Exception e) {
                indError = "error";
                errores.add(e.getMessage());
            }
        }
    }

    public String adicionar() {
        idAccion = 3;
        verifAccionTipoUsuario();

        if (indErrAcc.equals("")) {
            if (!opcion.trim().equals("A") && !opcion.trim().equals("M")) {
                indErrParm = "error";
            } else {
                varReturnProcess(1);

                if (opcion.equals("A")) {
                    formURL = baseURL + "usuarios/grabarUsuario";
                }
            }
        }

        populateForm();

        return "adicionar";
    }
    
    public String modificar() {
        idAccion = 4;
        verifAccionTipoUsuario();

        if (indErrAcc.equals("")) {
            if (!opcion.trim().equals("A") && !opcion.trim().equals("M")) {
                indErrParm = "error";
            } else {
                varReturnProcess(1);

                if (opcion.equals("M")) {
                    if (modelo.getIdUsu().trim().equals("")) {
                        indErrParm = "error";
                    } else {
                        getDatosUsuario();
                        formURL = baseURL + "usuarios/actualizarUsuario";
                    }
                }
            }
        }

        populateForm();

        return "adicionar";
    }
        
    public String getLocales() {
        helper conex = null;
        ResultSet tabla = null;

        try {
            conex = new helper();
            indError = conex.getErrorSQL();

            if (!indError.equals("")) {
                errores.add(indError);
            } else {
                tabla = conex.executeDataSet("CALL usp_listLocalesConces(?)",
                        new Object[]{modelo.getIdCon()});

                indError = conex.getErrorSQL();

                if (!indError.equals("")) {
                    errores.add(indError);
                } else {
                    Locales obj;
                    while (tabla.next()) {
                        obj = new Locales();
                        obj.setIdLocCon(tabla.getString("idLocCon"));
                        obj.setDesLocCon(tabla.getString("desLocCon"));
                        listLocales.add(obj);
                    }
                }
            }
        } catch (Exception e) {
            indError = "error";
            errores.add(e.getMessage());
        } finally {
            try {
                tabla.close();
                conex.returnConnect();
            } catch (Exception e) {
            }
        }

        return "getLocales";
    }

    public String grabar() {
        idAccion = 5;
        verifAccionTipoUsuario();

        if (indErrAcc.equals("")) {
            if (modelo.getIdUsu().equals("")) {
                indError += "error";
                errores.add("Ingrese el número de DNI");
            } else {
                if (modelo.getIdUsu().length() < 8) {
                    indError += "error";
                    errores.add("El DNI ingresado no es válido");
                }
            }

            if (modelo.getDesApeUsu().equals("")) {
                indError += "error";
                errores.add("Ingrese los apellidos");
            }

            if (modelo.getDesNomUsu().equals("")) {
                indError += "error";
                errores.add("Ingrese los nombres ");
            }

            if (modelo.getOtrClaUsu().equals("")) {
                indError += "error";
                errores.add("Debe ingresar la contraseña");
            }
            
            if (modelo.getIdTipUsu() == 0) {
                indError += "error";
                errores.add("Seleccione el tipo de usuario");
            }

            if (modelo.getIdCon() == 0) {
                indError += "error";
                errores.add("Seleccione un concesionario");
            }

            if (modelo.getIdLocCon() == 0 && modelo.getIdCon() != 0) {
                indError += "error";
                errores.add("Seleccione un local");
            }
            
            if (modelo.getNumTelFij().equals("0") && modelo.getNumTelMov1().equals("0")
                    && modelo.getNumTelMov2().equals("0") && modelo.getNumTelRP1().equals("")
                    && modelo.getNumTelRP2().equals("") && modelo.getNumTelRP3().equals("")){
                indError += "error";
                errores.add("Debe ingresar al menos un número telefónico");
            }
            
            if (!modelo.getNumTelFij().equals("0")) {
                if(modelo.getNumTelFij().length()<6 || modelo.getNumTelFij().length()>10) {
                    indError += "error";
                    errores.add("Número de teléfono fijo no válido");
                } else {
                    if(!isInteger(modelo.getNumTelFij())) {
                        indError += "error";
                        errores.add("Número de teléfono fijo no válido");
                    } else {
                        if(Integer.parseInt(modelo.getNumTelFij())==0) {
                            indError += "error";
                            errores.add("Número de teléfono fijo no válido");
                        }
                    }
                }
            }
            
            if (!modelo.getNumAnexo().equals("0") && modelo.getNumAnexo().length()>5) {
                if(modelo.getNumAnexo().length()>5) {
                    indError += "error";
                    errores.add("Número de anexo no válido");
                } else {
                    if(!isInteger(modelo.getNumAnexo())) {
                        indError += "error";
                        errores.add("Número de anexo no válido");
                    } else {
                        if(Integer.parseInt(modelo.getNumAnexo())==0) {
                            indError += "error";
                            errores.add("Número de anexo no válido");
                        }
                    }
                }
            }
            
            if (!modelo.getNumTelMov1().equals("0")) {
                if(modelo.getNumTelMov1().length()<9 || modelo.getNumTelMov1().length()>10) {
                    indError += "error";
                    errores.add("Número de movil 1 no válido");
                } else {
                    if(!isInteger(modelo.getNumTelMov1())) {
                        indError += "error";
                        errores.add("Número de movil 1 no válido");
                    } else {
                        if(Integer.parseInt(modelo.getNumTelMov1())==0) {
                            indError += "error";
                            errores.add("Número de movil 1 no válido");
                        }
                    }
                }
            }
            
            if (!modelo.getNumTelMov2().equals("0")) {
                if(modelo.getNumTelMov2().length()<9 || modelo.getNumTelMov2().length()>10) {
                    indError += "error";
                    errores.add("Número de movil 2 no válido");
                } else {
                    if(!isInteger(modelo.getNumTelMov2())) {
                        indError += "error";
                        errores.add("Número de movil 2 no válido");
                    } else {
                        if(Integer.parseInt(modelo.getNumTelMov2())==0) {
                            indError += "error";
                            errores.add("Número de movil 2 no válido");
                        }
                    }
                }
            }
            
            if (!modelo.getNumTelRP1().equals("")) {
                if(modelo.getNumTelRP1().length()>10) {
                    indError += "error";
                    errores.add("Número RPM no válido");
                }
            }
            
            if (!modelo.getNumTelRP2().equals("")) {
                if(modelo.getNumTelRP2().length()>10) {
                    indError += "error";
                    errores.add("Número RPC no válido");
                }
            }
            
            if (!modelo.getNumTelRP3().equals("")) {
                if(modelo.getNumTelRP3().length()>10) {
                    indError += "error";
                    errores.add("Número Nextel no válido");
                }
            }
            
            if(!modelo.getOtrEmaTra().equals("")) {
                if(!isEmail(modelo.getOtrEmaTra())) {
                    indError += "error";
                    errores.add("Email no válido");
                }
            }

            if (indError.equals("")) {
                helper conex = null;

                try {
                    conex = new helper();
                    indError = conex.getErrorSQL().trim();

                    if (!indError.equals("")) {
                        errores.add(indError);
                    } else {
                        String pwd = "";

                        byte[] md5_bytes = Codificador.getEncoded(modelo.getOtrClaUsu(), "md5").getBytes();
                        for (byte b : md5_bytes) {
                            pwd += Integer.toHexString(Integer.parseInt(Byte.toString((byte) ((b & 0x0F0) >> 4)))).toString();
                        }

                        indError = conex.executeNonQuery("CALL usp_insUsuario(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)",
                                new Object[]{ modelo.getIdUsu(),modelo.getDesApeUsu(),modelo.getDesNomUsu(),pwd, 
                                    modelo.getIdTipUsu(),modelo.getIdCon(), modelo.getIdLocCon(),modelo.getNumTelFij(),
                                    modelo.getNumAnexo(),modelo.getNumTelMov1(),modelo.getNumTelMov2(),modelo.getNumTelRP1(),
                                    modelo.getNumTelRP2(),modelo.getNumTelRP3(),modelo.getOtrEmaTra(),sesion_sga.get("ses_idusu") });

                        if (!indError.trim().equals("")) {
                            errores.add(indError);
                        }
                    }
                } catch (Exception e) {
                    indError = "error";
                    errores.add(e.getMessage());
                } finally {
                    conex.returnConnect();
                }
            }
        }

        return "grabar";
    }

    public String actualizar() {
        idAccion = 6;
        verifAccionTipoUsuario();

        if (indErrAcc.equals("")) {
            if (modelo.getDesApeUsu().equals("")) {
                indError += "error";
                errores.add("Ingrese los apellidos");
            }

            if (modelo.getDesNomUsu().equals("")) {
                indError += "error";
                errores.add("Ingrese los nombres ");
            }
            
            if (modelo.getIdTipUsu() == 0) {
                indError += "error";
                errores.add("Seleccione el tipo de usuario");
            }

            if (modelo.getIdCon() == 0) {
                indError += "error";
                errores.add("Seleccione un concesionario");
            }

            if (modelo.getIdLocCon() == 0 && modelo.getIdCon() != 0) {
                indError += "error";
                errores.add("Seleccione un local");
            }
            
            if (modelo.getNumTelFij().equals("") && modelo.getNumTelMov1().equals("")
                    && modelo.getNumTelMov2().equals("") && modelo.getNumTelRP1().equals("")
                    && modelo.getNumTelRP2().equals("") && modelo.getNumTelRP3().equals("")){
                indError += "error";
                errores.add("Debe ingresar al menos un número telefónico");
            }
            
            if (!modelo.getNumTelFij().equals("0")) {
                if(modelo.getNumTelFij().length()<6 || modelo.getNumTelFij().length()>10) {
                    indError += "error";
                    errores.add("Número de teléfono fijo no válido");
                } else {
                    if(!isInteger(modelo.getNumTelFij())) {
                        indError += "error";
                        errores.add("Número de teléfono fijo no válido");
                    } else {
                        if(Integer.parseInt(modelo.getNumTelFij())==0) {
                            indError += "error";
                            errores.add("Número de teléfono fijo no válido");
                        }
                    }
                }
            }
            
            if (!modelo.getNumAnexo().equals("0") && modelo.getNumAnexo().length()>5) {
                if(modelo.getNumAnexo().length()>5) {
                    indError += "error";
                    errores.add("Número de anexo no válido");
                } else {
                    if(!isInteger(modelo.getNumAnexo())) {
                        indError += "error";
                        errores.add("Número de anexo no válido");
                    } else {
                        if(Integer.parseInt(modelo.getNumAnexo())==0) {
                            indError += "error";
                            errores.add("Número de anexo no válido");
                        }
                    }
                }
            }
            
            if (!modelo.getNumTelMov1().equals("0")) {
                if(modelo.getNumTelMov1().length()<9 || modelo.getNumTelMov1().length()>10) {
                    indError += "error";
                    errores.add("Número de movil 1 no válido");
                } else {
                    if(!isInteger(modelo.getNumTelMov1())) {
                        indError += "error";
                        errores.add("Número de movil 1 no válido");
                    } else {
                        if(Integer.parseInt(modelo.getNumTelMov1())==0) {
                            indError += "error";
                            errores.add("Número de movil 1 no válido");
                        }
                    }
                }
            }
            
            if (!modelo.getNumTelMov2().equals("0")) {
                if(modelo.getNumTelMov2().length()<9 || modelo.getNumTelMov2().length()>10) {
                    indError += "error";
                    errores.add("Número de movil 2 no válido");
                } else {
                    if(!isInteger(modelo.getNumTelMov2())) {
                        indError += "error";
                        errores.add("Número de movil 2 no válido");
                    } else {
                        if(Integer.parseInt(modelo.getNumTelMov2())==0) {
                            indError += "error";
                            errores.add("Número de movil 2 no válido");
                        }
                    }
                }
            }
            
            if (!modelo.getNumTelRP1().equals("")) {
                if(modelo.getNumTelRP1().length()>10) {
                    indError += "error";
                    errores.add("Número RPM no válido");
                }
            }
            
            if (!modelo.getNumTelRP2().equals("")) {
                if(modelo.getNumTelRP2().length()>10) {
                    indError += "error";
                    errores.add("Número RPC no válido");
                }
            }
            
            if (!modelo.getNumTelRP3().equals("")) {
                if(modelo.getNumTelRP3().length()>10) {
                    indError += "error";
                    errores.add("Número Nextel no válido");
                }
            }
            
            if(!modelo.getOtrEmaTra().equals("")) {
                if(!isEmail(modelo.getOtrEmaTra())) {
                    indError += "error";
                    errores.add("Email no válido");
                }
            }

            if (indError.equals("")) {
                helper conex = null;

                try {
                    conex = new helper();
                    indError = conex.getErrorSQL().trim();

                    if (!indError.equals("")) {
                        errores.add(indError);
                    } else {
                        String pwd = "";

                        if (!modelo.getOtrClaUsu().equals("")) {
                            byte[] md5_bytes = Codificador.getEncoded(modelo.getOtrClaUsu(), "md5").getBytes();
                            for (byte b : md5_bytes) {
                                pwd += Integer.toHexString(Integer.parseInt(Byte.toString((byte) ((b & 0x0F0) >> 4)))).toString();
                            }
                        }

                        indError = conex.executeNonQuery("CALL usp_updUsuario(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)",
                                new Object[]{ modelo.getIdUsu(),modelo.getDesApeUsu(),modelo.getDesNomUsu(),pwd,modelo.getIdTipUsu(),
                                    modelo.getIdCon(), modelo.getIdLocCon(),modelo.getNumTelFij(),modelo.getNumAnexo(),
                                    modelo.getNumTelMov1(),modelo.getNumTelMov2(),modelo.getNumTelRP1(),modelo.getNumTelRP2(),
                                    modelo.getNumTelRP3(),modelo.getOtrEmaTra() });

                        if (!indError.trim().equals("")) {
                            errores.add(indError);
                        }
                    }
                } catch (Exception e) {
                    indError = "error";
                    errores.add(e.getMessage());
                } finally {
                    conex.returnConnect();
                }
            }
        }

        return "actualizar";
    }

    public String actEstado() {
        idAccion = 7;
        verifAccionTipoUsuario();

        if (indErrAcc.equals("")) {
            modelo.setIdUsu(modelo.getIdUsu().trim());

            if (indError.equals("")) {
                helper conex = null;

                try {
                    conex = new helper();
                    indError = conex.getErrorSQL().trim();

                    if (!indError.equals("")) {
                        errores.add(indError);
                    } else {
                        indError = conex.executeNonQuery("CALL usp_updEdoUsuario(?,?)",
                                new Object[]{modelo.getIdUsu(), modelo.getEdoUsu()});

                        if (!indError.trim().equals("")) {
                            errores.add(indError);
                        }
                    }
                } catch (Exception e) {
                    indError = "error";
                    errores.add(e.getMessage());
                } finally {
                    conex.returnConnect();
                }
            }
        }

        return "actEstado";
    }

    public String eliminar() {
        idAccion = 8;
        verifAccionTipoUsuario();

        if (indErrAcc.equals("")) {
            modelo.setIdUsu(modelo.getIdUsu().trim());

            if (opcion.trim().equals("E")) {
                helper conex = null;

                try {
                    conex = new helper();
                    indError = conex.getErrorSQL().trim();

                    if (!indError.equals("")) {
                        errores.add(indError);
                    } else {
                        indError = conex.executeNonQuery("CALL usp_dltUsuario(?)",
                                new Object[]{modelo.getIdUsu()});

                        if (indError.trim().equals("")) {
                            errores.add(indError);
                        }
                    }
                } catch (Exception e) {
                    indError = "error";
                    errores.add(e.getMessage());
                } finally {
                    conex.returnConnect();
                }
            }
        }

        return "eliminar";
    }

    public String updPassword() {
        if (!opcion.equals("F")) {
            if(!sesion_sga.get("ses_indclares").equals("R")) {
                if (modelo.getOtrClaUsu().trim().equals("")) {
                    errores.add("Ingrese la contraseña actual");
                    indError += "error";
                }
            }

            if (modelo.getOtrNueClaUsu().trim().equals("")) {
                errores.add("Ingrese la nueva contraseña");
                indError += "error";
            }
            if (modelo.getOtrNueClaUsu2().trim().equals("")) {
                errores.add("Confirme la nueva contraseña");
                indError += "error";
            }

            if (indError.trim().equals("")) {
                helper conex = null;
                ResultSet tabla = null;

                try {
                    conex = new helper();

                    if(!sesion_sga.get("ses_indclares").equals("R")) {
                        String clave = "";
                        byte[] md5_bytes_cla = Codificador.getEncoded(modelo.getOtrClaUsu().trim(), "md5").getBytes();
                        for (byte b : md5_bytes_cla) {
                            clave += Integer.toHexString(Integer.parseInt(Byte.toString((byte) ((b & 0x0F0) >> 4)))).toString();
                        }

                        tabla = null;
                        tabla = conex.executeDataSet("CALL usp_verifUsuarioPassword(?,?)",
                                new Object[]{sesion_sga.get("ses_idusu"), clave});
                        while (tabla.next()) {
                            if (tabla.getInt(1) == 0) {
                                errores.add("La contraseña actual no es correcta");
                                indError += "error";
                            }
                        }
                    }

                    if (modelo.getOtrNueClaUsu().trim().length() < 8 || modelo.getOtrNueClaUsu2().trim().length() < 8) {
                        errores.add("La nueva contraseña debe tener al menos 8 caracteres");
                        indError += "error";
                    } else {
                        Pattern pat = null;
                        Matcher mat = null;
                        pat = Pattern.compile("^[a-z0-9]{8,}$");

                        mat = pat.matcher(modelo.getOtrNueClaUsu().trim());
                        if (!mat.find()) {
                            errores.add("La nueva contraseña ingresada no es válida");
                            indError += "error";
                        }
                    }

                    if (indError.trim().equals("")) {
                        if (!modelo.getOtrNueClaUsu().trim().equals(modelo.getOtrNueClaUsu2().trim())) {
                            errores.add("La nueva contraseña ingresada no es correcta");
                            indError += "error";
                        }

                        if (indError.trim().equals("")) {
                            String clavechg = "";
                            byte[] md5_bytes_chg = Codificador.getEncoded(modelo.getOtrNueClaUsu().trim(), "md5").getBytes();
                            for (byte b : md5_bytes_chg) {
                                clavechg += Integer.toHexString(Integer.parseInt(Byte.toString((byte) ((b & 0x0F0) >> 4)))).toString();
                            }

                            tabla = null;
                            tabla = conex.executeDataSet("CALL usp_verifUltPassUsu(?,?)",
                                    new Object[]{sesion_sga.get("ses_idusu"), clavechg});
                            int indicador = 0;
                            while (tabla.next()) {
                                indicador = tabla.getInt(1);
                            }

                            if (indicador == 0) {
                                indError += conex.executeNonQuery("CALL usp_updPasswordUsuario(?,?)",
                                        new Object[]{sesion_sga.get("ses_idusu"), clavechg});

                                if (!indError.trim().equals("")) {
                                    errores.add(indError);
                                }
                            } else {
                                //errores.add("No puede utilizar una contraseña que haya registrado antes");
                                errores.add("La contraseña no puede ser igual a las 3 últimas");
                                indError += "error";
                            }
                        }
                    }
                } catch (Exception ex) {
                    errores.add(ex.getMessage());
                    indError += "error";
                } finally {
                    try {
                        tabla.close();
                        conex.returnConnect();
                    } catch (Exception e) {
                    }
                }
            }
        }

        return "updPassword";
    }

    public String updVarSesionCaducCla() {
        sesion_sga.put("ses_indclares", "");
        sesion_sga.put("ses_indmencad", "");
        sesion_sga.put("ses_candiacad", 0);

        return "updVarSesionCaducCla";
    }

    /**
     * @return the usuario
     */
    public Usuarios getModelo() {
        return modelo;
    }

    /**
     * @param usuario the usuario to set
     */
    public void setModelo(Usuarios modelo) {
        this.modelo = modelo;
    }

    /**
     * @return the listUsuarios
     */
    public ArrayList<Usuarios> getListUsuarios() {
        return listUsuarios;
    }

    /**
     * @return the listConcesionarios
     */
    public ArrayList<Concesionarios> getListConcesionarios() {
        return listConcesionarios;
    }

    /**
     * @return the listLocales
     */
    public ArrayList<Locales> getListLocales() {
        return listLocales;
    }

    /**
     * @return the listTipoUsuario
     */
    public ArrayList<TipoUsuario> getListTipoUsuario() {
        return listTipoUsuario;
    }
}
