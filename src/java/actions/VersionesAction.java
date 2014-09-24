package actions;

import static com.opensymphony.xwork2.Action.SUCCESS;
import com.opensymphony.xwork2.ModelDriven;
import conexion.helper;
import entities.CategoriaVehiculo;
import entities.ClaseVehiculo;
import entities.TipoCarroceria;
import entities.TipoCombustible;
import entities.TipoTraccion;
import entities.TipoTrasmision;
import entities.Versiones;
import java.sql.ResultSet;
import java.util.ArrayList;

public class VersionesAction extends MasterAction implements ModelDriven<Versiones> {

    private Versiones modelo = new Versiones();
    private ArrayList<Versiones> listVersiones = new ArrayList<Versiones>();
    private ArrayList<TipoCarroceria> listTipCarroceria = new ArrayList<TipoCarroceria>();
    private ArrayList<CategoriaVehiculo> listCatVehiculo = new ArrayList<CategoriaVehiculo>();
    private ArrayList<ClaseVehiculo> listClaVehiculo = new ArrayList<ClaseVehiculo>();
    private ArrayList<TipoCombustible> listTipCombustible = new ArrayList<TipoCombustible>();
    private ArrayList<TipoTrasmision> listTipTransmision = new ArrayList<TipoTrasmision>();
    private ArrayList<TipoTraccion> listTipTraccion = new ArrayList<TipoTraccion>();

    @Override
    public Versiones getModel() {
        tituloOpc = "Versiones";
        idClaseAccion = 29;
        return modelo;
    }

    public String vrfSeleccion() {
        idAccion = 1;
        verifAccionTipoUsuario();

        if (indErrAcc.equals("")) {
            if (getModelo().getIdVerMod().trim().equals("")) {
                indError = "error";
                errores.add("No ha seleccionado ningun registro");
            }
        }

        return "vrfSeleccion";
    }

    @Override
    public String execute() {
        idAccion = 2;
        verifAccionTipoUsuario();

        if (indErrAcc.equals("")) {
            nivBandeja = 3;
            varReturnProcess(0);

            if (!listVarReturn.isEmpty()) {
                curPagVis = Integer.parseInt(listVarReturn.get(0).toString().trim());
            }

            urlPaginacion = "versiones/Version";
            
            getDatosMarcaModelo();
            cantVersionesIndex();
            verifPag();
            listVersionesIndex();
        }

        return SUCCESS;
    }

    private void cantVersionesIndex() {
        helper conex = null;
        ResultSet tabla = null;
        try {
            conex = new helper();
            indError += conex.getErrorSQL();

            if (!indError.equals("")) {
                errores.add(indError);
            } else {
                tabla = conex.executeDataSet("CALL usp_cantVersionesIndex(?,?,?)",
                        new Object[]{modelo.getIdMar(), modelo.getIdModMar(),modelo.getDesVerMod_f()});

                indError += conex.getErrorSQL();
                if (!indError.equals("")) {
                    errores.add(indError);
                } else {
                    while (tabla.next()) {
                        cantReg = tabla.getInt(1);
                    }
                }
            }
        } catch (Exception e) {
            indError += "error";
            errores.add(e.getMessage());
        } finally {
            try {
                tabla.close();
                conex.returnConnect();
            } catch (Exception e) {
            }
        }
    }

    private void listVersionesIndex() {
        helper conex = null;
        ResultSet tabla = null;
        try {
            conex = new helper();
            indError += conex.getErrorSQL();
            if (!indError.equals("")) {
                errores.add(indError);
            } else {
                tabla = conex.executeDataSet("CALL usp_listVersionIndex(?,?,?,?,?)",
                        new Object[]{modelo.getIdMar(), modelo.getIdModMar(), modelo.getDesVerMod_f(),
                            getCurPag() * regPag, regPag});
                indError += conex.getErrorSQL();
                if (!indError.equals("")) {
                    errores.add(indError);
                } else {
                    Versiones obj;
                    while (tabla.next()) {
                        obj = new Versiones();
                        obj.setIdMar(tabla.getString("idMar").trim());
                        obj.setIdModMar(tabla.getString("idModMar").trim());
                        obj.setIdVerMod(tabla.getString("idVerMod"));
                        obj.setDesVerMod(tabla.getString("desVerMod").trim());
                        obj.setDesKat(tabla.getString("desKat").trim());
                        obj.setDesPreCha(tabla.getString("desPreCha").trim());
                        obj.setDesPreMot(tabla.getString("desPreMot").trim());
                        listVersiones.add(obj);
                    }
                }
            }
        } catch (Exception e) {
            indError += "error";
            errores.add(e.getMessage());
        } finally {
            try {
                tabla.close();
                conex.returnConnect();
            } catch (Exception e) {
            }
        }
    }
    
    /*private void listgetVersionesIndex() {
        helper conex = null;
        ResultSet tabla = null;
        try {
            conex = new helper();
            indError += conex.getErrorSQL();
            if (!indError.equals("")) {
                errores.add(indError);
            } else {
                tabla = conex.executeDataSet("CALL usp_listgetVersionIndex(?,?)",
                        new Object[]{modelo.getIdMar(), modelo.getIdModMar()});
                indError += conex.getErrorSQL();
                if (!indError.equals("")) {
                    errores.add(indError);
                } else {
                    while (tabla.next()) {
                        modelo.setDesMar(tabla.getString("desMar").trim());                        
                        modelo.setDesModMar(tabla.getString("desModMar").trim());                        
                    }
                }
            }
        } catch (Exception e) {
            indError += "error";
            errores.add(e.getMessage());
        } finally {
            try {
                tabla.close();
                conex.returnConnect();
            } catch (Exception e) {
            }
        }
    }*/

    public String adicionar() {
        idAccion = 3;
        verifAccionTipoUsuario();

        if (indErrAcc.equals("")) {
            nivBandeja = 3;
            if (!opcion.trim().equals("A") || modelo.getIdMar().equals("") || modelo.getIdModMar().equals("")) {
                indErrParm = "error";
            } else {
                varReturnProcess(1);
                if (opcion.equals("A")) {
                    modelo.setIdVerMod("");
                    getDatosMarcaModelo();
                    populateForm();

                    formURL = baseURL + "versiones/grabarVersion";
                }
            }
        }
        return "adicionar";
    }

    public String modificar() {
        idAccion = 4;
        verifAccionTipoUsuario();

        if (indErrAcc.equals("")) {
            nivBandeja = 3;
            if (!opcion.trim().equals("M") || modelo.getIdMar().equals("")
                    || modelo.getIdModMar().equals("") || modelo.getIdVerMod().equals("") || modelo.getIdVerMod().equals("0")) {
                indErrParm = "error";
            } else {
                varReturnProcess(1);
                if (opcion.equals("M")) {
                    if (modelo.getIdVerMod().trim().equals("")) {
                        indErrParm = "error";
                    } else {
                        getDatosMarcaModelo();
                        getDatosVersiones();

                        populateForm();

                        formURL = baseURL + "versiones/actualizarVersion";
                    }
                }
            }
        }
        return "modificar";
    }
    
    private void getDatosMarcaModelo() {
        helper conex = null;
        ResultSet tabla = null;
        
        try {
            conex = new helper();
            indError = conex.getErrorSQL();
            
            if(!indError.equals("")) {
                errores.add(indError);
            } else {
                tabla = conex.executeDataSet("CALL usp_getDatosMarca(?)", 
                        new Object[]{ modelo.getIdMar() });
                indError = conex.getErrorSQL();
                
                if(!indError.equals("")) {
                    errores.add(indError);
                } else {
                    while(tabla.next()) {
                        modelo.setDesMar(tabla.getString("desMar"));
                    }
                    
                    tabla = null;
                    tabla = conex.executeDataSet("CALL usp_getDatosModelo(?,?)", 
                            new Object[]{ modelo.getIdMar(),modelo.getIdModMar() });
                    indError = conex.getErrorSQL();
                    
                    if(!indError.equals("")) {
                        errores.add(indError);
                    } else {
                        while(tabla.next()) {
                            modelo.setDesModMar(tabla.getString("desModMar"));
                        }
                    }
                }
            }
        } catch (Exception e) {
            indError += "error";
            errores.add(e.getMessage());
        } finally {
            try {
                tabla.close();
                conex.returnConnect();
            } catch (Exception e) {
            }
        }
    }

    private void populateForm() {
        helper conex = null;
        ResultSet tabla = null;

        try {
            conex = new helper();
            indError += conex.getErrorSQL();

            if (!indError.equals("")) {
                errores.add(indError);
            } else {
                tabla = null;
                tabla = conex.executeDataSet("CALL usp_listCategoriaVehiculo()", new Object[]{});

                if (!conex.getErrorSQL().equals("")) {
                    indError += "error";
                    errores.add(conex.getErrorSQL());
                } else {
                    CategoriaVehiculo obj;
                    while (tabla.next()) {
                        obj = new CategoriaVehiculo();
                        obj.setIdCatVeh(tabla.getInt("idCatVeh"));
                        obj.setDesCatVeh(tabla.getString("desCatVeh"));
                        listCatVehiculo.add(obj);
                    }
                }

                tabla = null;
                tabla = conex.executeDataSet("CALL usp_listClaseVehiculo()", new Object[]{});

                if (!conex.getErrorSQL().equals("")) {
                    indError += "error";
                    errores.add(conex.getErrorSQL().trim());
                } else {
                    ClaseVehiculo obj;
                    while (tabla.next()) {
                        obj = new ClaseVehiculo();
                        obj.setIdClaVeh(tabla.getInt("idClaVeh"));
                        obj.setDesClaVeh(tabla.getString("desClaVeh"));
                        listClaVehiculo.add(obj);
                    }
                }

                tabla = null;
                tabla = conex.executeDataSet("CALL usp_listTipoCarroceria()", new Object[]{});

                if (!conex.getErrorSQL().equals("")) {
                    indError = "error";
                    errores.add(conex.getErrorSQL().trim());
                } else {
                    TipoCarroceria obj;
                    while (tabla.next()) {
                        obj = new TipoCarroceria();
                        obj.setIdTipCar(tabla.getInt("idTipCar"));
                        obj.setDesTipCar(tabla.getString("desTipCar"));
                        listTipCarroceria.add(obj);
                    }
                }

                tabla = null;
                tabla = conex.executeDataSet("CALL usp_listTipoCombustible()", new Object[]{});

                if (!conex.getErrorSQL().equals("")) {
                    indError = "error";
                    errores.add(conex.getErrorSQL().trim());
                } else {
                    TipoCombustible obj;
                    while (tabla.next()) {
                        obj = new TipoCombustible();
                        obj.setIdTipCom(tabla.getInt("idTipCom"));
                        obj.setDesTipCom(tabla.getString("desTipCom"));
                        listTipCombustible.add(obj);
                    }
                }

                tabla = null;
                tabla = conex.executeDataSet("CALL usp_listTipoTrasmision()", new Object[]{});

                if (!conex.getErrorSQL().equals("")) {
                    indError = "error";
                    errores.add(conex.getErrorSQL().trim());
                } else {
                    TipoTrasmision obj;
                    while (tabla.next()) {
                        obj = new TipoTrasmision();
                        obj.setIdTipTras(tabla.getInt("idTipTras"));
                        obj.setDesTipTras(tabla.getString("desTipTras"));
                        listTipTransmision.add(obj);
                    }
                }

                tabla = null;
                tabla = conex.executeDataSet("CALL usp_listTipoTraccion()", new Object[]{});

                if (!conex.getErrorSQL().equals("")) {
                    indError = "error";
                    errores.add(conex.getErrorSQL().trim());
                } else {
                    TipoTraccion obj;
                    while (tabla.next()) {
                        obj = new TipoTraccion();
                        obj.setIdTipTrac(tabla.getInt("idTipTrac"));
                        obj.setDesTipTrac(tabla.getString("desTipTrac"));
                        listTipTraccion.add(obj);
                    }
                }
            }
        } catch (Exception e) {
            String error = e.getMessage().trim();
            if (!error.equals("")) {
                indError = "error";
                errores.add(error);
            }
        } finally {
            try {
                tabla.close();
                conex.returnConnect();
            } catch (Exception e) {

            }
        }
    }

    public String grabar() {
        idAccion = 5;
        verifAccionTipoUsuario();

        if (indErrAcc.equals("")) {
            if (modelo.getIdVerMod().trim().equals("") || modelo.getIdVerMod().trim().equals("0")) {
                indError += "error";
                errores.add("Ingrese el código de la versión.");
            }
            if (getModelo().getDesVerMod().trim().equals("")) {
                indError += "error";
                errores.add("Ingrese la descripción de la versión.");
            }
            if (getModelo().getDesKat().trim().equalsIgnoreCase("")) {
                indError += "error";
                errores.add("Ingrese la descripción del Katashiki.");
            }
            if (getModelo().getDesPreCha().trim().equalsIgnoreCase("")) {
                indError += "error";
                errores.add("Ingrese el prefijo de Chasis.");
            }
            if (getModelo().getDesPreMot().trim().equalsIgnoreCase("")) {
                indError += "error";
                errores.add("Ingrese el prefijo de Motor.");
            }
            if (getModelo().getIdCatVeh() == 0) {
                indError += "error";
                errores.add("Seleccione la categoría del vehículo.");
            }
            if (getModelo().getIdClaVeh() == 0) {
                indError += "error";
                errores.add("Seleccione la clase del vehículo.");
            }
            if (getModelo().getIdTipCar() == 0) {
                indError += "error";
                errores.add("Seleccione el tipo de carrocería.");
            }
            if (getModelo().getIdTipCom() == 0) {
                indError += "error";
                errores.add("Seleccione el tipo de combustible.");
            }
            if (getModelo().getIdTipTras() == 0) {
                indError += "error";
                errores.add("Seleccione el tipo de transmisión.");
            }
            if (getModelo().getIdTipTrac() == 0) {
                indError += "error";
                errores.add("Seleccione el tipo de tracción.");
            }
            if (getModelo().getDesPotMot().trim().equals("")) {
                indError += "error";
                errores.add("Ingrese la potencia del motor.");
            }
            if (getModelo().getNumCilVeh().trim().equals("")) {
                indError += "error";
                errores.add("Ingrese la cilindrada del vehículo.");
            } else {
                if (!isInteger(getModelo().getNumCilVeh())) {
                    indError += "error";
                    errores.add("Cilindrada no válida.");
                } else {
                    if (Integer.parseInt(getModelo().getNumCilVeh()) == 0) {
                        indError += "error";
                        errores.add("Cilindrada no válida.");
                    }
                }
            }
            if (getModelo().getNumCil().trim().equals("")) {
                indError += "error";
                errores.add("Ingrese el número de cilindros.");
            } else {
                if (!isInteger(getModelo().getNumCil())) {
                    indError += "error";
                    errores.add("Número de cilindros no válido.");
                } else {
                    if (Integer.parseInt(getModelo().getNumCil()) == 0) {
                        indError += "error";
                        errores.add("Número de cilindros no válido.");
                    }
                }
            }
            if (getModelo().getNumAsiVeh().trim().equals("")) {
                indError += "error";
                errores.add("Ingrese el número de asientos.");
            } else {
                if (!isInteger(getModelo().getNumAsiVeh())) {
                    indError += "error";
                    errores.add("Número de asientos no válido.");
                } else {
                    if (Integer.parseInt(getModelo().getNumAsiVeh()) == 0) {
                        indError += "error";
                        errores.add("Número de asientos no válido.");
                    }
                }
            }
            if (getModelo().getNumPasVeh().trim().equals("")) {
                indError += "error";
                errores.add("Ingrese el número de pasajeros.");
            } else {
                if (!isInteger(getModelo().getNumPasVeh())) {
                    indError += "error";
                    errores.add("Número de pasajeros no válido.");
                } else {
                    if (Integer.parseInt(getModelo().getNumPasVeh()) == 0) {
                        indError += "error";
                        errores.add("Número de pasajeros no válido.");
                    }
                }
            }
            if (getModelo().getNumPueVeh().trim().equals("")) {
                indError += "error";
                errores.add("Ingrese el número de puertas.");
            } else {
                if (!isInteger(getModelo().getNumPueVeh())) {
                    indError += "error";
                    errores.add("Número de puertas no válido.");
                } else {
                    if (Integer.parseInt(getModelo().getNumPueVeh()) == 0) {
                        indError += "error";
                        errores.add("Número de puertas no válido.");
                    }
                }
            }
            if (getModelo().getNumEjeVeh().trim().equals("")) {
                indError += "error";
                errores.add("Ingrese el número de ejes.");
            } else {
                if (!isInteger(getModelo().getNumEjeVeh())) {
                    indError += "error";
                    errores.add("Número de ejes no válido.");
                } else {
                    if (Integer.parseInt(getModelo().getNumEjeVeh()) == 0) {
                        indError += "error";
                        errores.add("Número de ejes no válido.");
                    }
                }
            }
            if (getModelo().getNumRueVeh().trim().equals("")) {
                indError += "error";
                errores.add("Ingrese el número de ruedas.");
            } else {
                if (!isInteger(getModelo().getNumRueVeh())) {
                    indError += "error";
                    errores.add("Número de ruedas no válido.");
                } else {
                    if (Integer.parseInt(getModelo().getNumRueVeh()) == 0) {
                        indError += "error";
                        errores.add("Número de ruedas no válido.");
                    }
                }
            }
            if (getModelo().getNumDisEje().trim().equals("")) {
                indError += "error";
                errores.add("Ingrese la distancia entre ejes.");
            } else {
                if (!isInteger(getModelo().getNumDisEje())) {
                    indError += "error";
                    errores.add("Número de distancia entre ejes no válido.");
                } else {
                    if (Integer.parseInt(getModelo().getNumDisEje()) == 0) {
                        indError += "error";
                        errores.add("Número de distancia entre ejes no válido.");
                    }
                }
            }
            if (getModelo().getNumMedLar().trim().equals("")) {
                indError += "error";
                errores.add("Ingrese la medida de largo.");
            } else {
                if (!isInteger(getModelo().getNumMedLar())) {
                    indError += "error";
                    errores.add("Número de medida de largo no válido.");
                } else {
                    if (Integer.parseInt(getModelo().getNumMedLar()) == 0) {
                        indError += "error";
                        errores.add("Número de medida de largo no válido.");
                    }
                }
            }
            if (getModelo().getNumMedAnc().trim().equals("")) {
                indError += "error";
                errores.add("Ingrese la medida de ancho.");
            } else {
                if (!isInteger(getModelo().getNumMedAnc())) {
                    indError += "error";
                    errores.add("Número de medida de ancho no válido.");
                } else {
                    if (Integer.parseInt(getModelo().getNumMedAnc()) == 0) {
                        indError += "error";
                        errores.add("Número de medida de ancho no válido.");
                    }
                }
            }
            if (getModelo().getNumMedAlt().trim().equals("")) {
                indError += "error";
                errores.add("Ingrese la medida de alto.");
            } else {
                if (!isInteger(getModelo().getNumMedAlt())) {
                    indError += "error";
                    errores.add("Número de medida de alto no válido.");
                } else {
                    if (Integer.parseInt(getModelo().getNumMedAlt()) == 0) {
                        indError += "error";
                        errores.add("Número de medida de alto no válido.");
                    }
                }
            }
            if (getModelo().getNumPesNet().trim().equals("")) {
                indError += "error";
                errores.add("Ingrese el peso neto.");
            } else {
                if (!isInteger(getModelo().getNumPesNet())) {
                    indError += "error";
                    errores.add("Número del peso neto no válido.");
                } else {
                    if (Integer.parseInt(getModelo().getNumPesNet()) == 0) {
                        indError += "error";
                        errores.add("Número del peso neto no válido.");
                    }
                }
            }
            if (getModelo().getNumPesBru().trim().equals("")) {
                indError += "error";
                errores.add("Ingrese el peso bruto.");
            } else {
                if (!isInteger(getModelo().getNumPesBru())) {
                    indError += "error";
                    errores.add("Número del peso bruto no válido.");
                } else {
                    if (Integer.parseInt(getModelo().getNumPesBru()) == 0) {
                        indError += "error";
                        errores.add("Número del peso bruto no válido.");
                    }
                }
            }
            if (getModelo().getNumCarUti().trim().equals("")) {
                indError += "error";
                errores.add("Ingrese la carga util.");
            } else {
                if (!isInteger(getModelo().getNumCarUti())) {
                    indError += "error";
                    errores.add("Número de carga util no válido.");
                } else {
                    if (Integer.parseInt(getModelo().getNumCarUti()) == 0) {
                        indError += "error";
                        errores.add("Número de carga util no válido.");
                    }
                }
            }
            if (indError.equals("")) {
                helper conex = null;
                try {
                    conex = new helper();
                    indError = conex.getErrorSQL();

                    if (!indError.equals("")) {
                        errores.add(indError);
                    } else {
                        indError = conex.executeNonQuery("CALL usp_insVersiones(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)",
                                new Object[]{getModelo().getIdMar().trim(), getModelo().getIdModMar().trim(), getModelo().getIdVerMod(),
                                    getModelo().getDesVerMod().trim(), getModelo().getDesKat().trim(), getModelo().getDesPreCha().trim(),
                                    getModelo().getDesPreMot().trim(), getModelo().getIdCatVeh(), getModelo().getIdClaVeh(), getModelo().getIdTipCar(),
                                    getModelo().getIdTipCom(), getModelo().getIdTipTras(), getModelo().getIdTipTrac(), getModelo().getDesPotMot().trim(),
                                    getModelo().getNumCilVeh(), getModelo().getNumCil(), getModelo().getNumAsiVeh(), getModelo().getNumPasVeh(),
                                    getModelo().getNumPueVeh(), getModelo().getNumEjeVeh(), getModelo().getNumRueVeh(), getModelo().getNumDisEje(),
                                    getModelo().getNumMedLar(), getModelo().getNumMedAnc(), getModelo().getNumMedAlt(), getModelo().getNumPesNet(),
                                    getModelo().getNumPesBru(), getModelo().getNumCarUti()});
                        if (!indError.equals("")) {
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
            if (modelo.getIdVerMod().trim().equals("") || modelo.getIdVerMod().trim().equals("0")) {
                indError += "error";
                errores.add("Ingrese el código de la versión.");
            }
            if (getModelo().getDesVerMod().trim().equals("")) {
                indError += "error";
                errores.add("Ingrese la descripción de la versión.");
            }
            if (getModelo().getDesKat().trim().equalsIgnoreCase("")) {
                indError += "error";
                errores.add("Ingrese la descripción del Katashiki.");
            }
            if (getModelo().getDesPreCha().trim().equalsIgnoreCase("")) {
                indError += "error";
                errores.add("Ingrese el prefijo de Chasis.");
            }
            if (getModelo().getDesPreMot().trim().equalsIgnoreCase("")) {
                indError += "error";
                errores.add("Ingrese el prefijo de Motor.");
            }
            if (getModelo().getIdCatVeh() == 0) {
                indError += "error";
                errores.add("Seleccione la categoría del vehículo.");
            }
            if (getModelo().getIdClaVeh() == 0) {
                indError += "error";
                errores.add("Seleccione la clase del vehículo.");
            }
            if (getModelo().getIdTipCar() == 0) {
                indError += "error";
                errores.add("Seleccione el tipo de carrocería.");
            }
            if (getModelo().getIdTipCom() == 0) {
                indError += "error";
                errores.add("Seleccione el tipo de combustible.");
            }
            if (getModelo().getIdTipTras() == 0) {
                indError += "error";
                errores.add("Seleccione el tipo de transmisión.");
            }
            if (getModelo().getIdTipTrac() == 0) {
                indError += "error";
                errores.add("Seleccione el tipo de tracción.");
            }
            if (getModelo().getDesPotMot().trim().equals("")) {
                indError += "error";
                errores.add("Ingrese la potencia del motor.");
            }
            if (getModelo().getNumCilVeh().trim().equals("")) {
                indError += "error";
                errores.add("Ingrese la cilindrada del vehículo.");
            } else {
                if (!isInteger(getModelo().getNumCilVeh())) {
                    indError += "error";
                    errores.add("Cilindrada no válida.");
                } else {
                    if (Integer.parseInt(getModelo().getNumCilVeh()) == 0) {
                        indError += "error";
                        errores.add("Cilindrada no válida.");
                    }
                }
            }
            if (getModelo().getNumCil().trim().equals("")) {
                indError += "error";
                errores.add("Ingrese el número de cilindros.");
            } else {
                if (!isInteger(getModelo().getNumCil())) {
                    indError += "error";
                    errores.add("Número de cilindros no válido.");
                } else {
                    if (Integer.parseInt(getModelo().getNumCil()) == 0) {
                        indError += "error";
                        errores.add("Número de cilindros no válido.");
                    }
                }
            }
            if (getModelo().getNumAsiVeh().trim().equals("")) {
                indError += "error";
                errores.add("Ingrese el número de asientos.");
            } else {
                if (!isInteger(getModelo().getNumAsiVeh())) {
                    indError += "error";
                    errores.add("Número de asientos no válido.");
                } else {
                    if (Integer.parseInt(getModelo().getNumAsiVeh()) == 0) {
                        indError += "error";
                        errores.add("Número de asientos no válido.");
                    }
                }
            }
            if (getModelo().getNumPasVeh().trim().equals("")) {
                indError += "error";
                errores.add("Ingrese el número de pasajeros.");
            } else {
                if (!isInteger(getModelo().getNumPasVeh())) {
                    indError += "error";
                    errores.add("Número de pasajeros no válido.");
                } else {
                    if (Integer.parseInt(getModelo().getNumPasVeh()) == 0) {
                        indError += "error";
                        errores.add("Número de pasajeros no válido.");
                    }
                }
            }
            if (getModelo().getNumPueVeh().trim().equals("")) {
                indError += "error";
                errores.add("Ingrese el número de puertas.");
            } else {
                if (!isInteger(getModelo().getNumPueVeh())) {
                    indError += "error";
                    errores.add("Número de puertas no válido.");
                } else {
                    if (Integer.parseInt(getModelo().getNumPueVeh()) == 0) {
                        indError += "error";
                        errores.add("Número de puertas no válido.");
                    }
                }
            }
            if (getModelo().getNumEjeVeh().trim().equals("")) {
                indError += "error";
                errores.add("Ingrese el número de ejes.");
            } else {
                if (!isInteger(getModelo().getNumEjeVeh())) {
                    indError += "error";
                    errores.add("Número de ejes no válido.");
                } else {
                    if (Integer.parseInt(getModelo().getNumEjeVeh()) == 0) {
                        indError += "error";
                        errores.add("Número de ejes no válido.");
                    }
                }
            }
            if (getModelo().getNumRueVeh().trim().equals("")) {
                indError += "error";
                errores.add("Ingrese el número de ruedas.");
            } else {
                if (!isInteger(getModelo().getNumRueVeh())) {
                    indError += "error";
                    errores.add("Número de ruedas no válido.");
                } else {
                    if (Integer.parseInt(getModelo().getNumRueVeh()) == 0) {
                        indError += "error";
                        errores.add("Número de ruedas no válido.");
                    }
                }
            }
            if (getModelo().getNumDisEje().trim().equals("")) {
                indError += "error";
                errores.add("Ingrese la distancia entre ejes.");
            } else {
                if (!isInteger(getModelo().getNumDisEje())) {
                    indError += "error";
                    errores.add("Número de distancia entre ejes no válido.");
                } else {
                    if (Integer.parseInt(getModelo().getNumDisEje()) == 0) {
                        indError += "error";
                        errores.add("Número de distancia entre ejes no válido.");
                    }
                }
            }
            if (getModelo().getNumMedLar().trim().equals("")) {
                indError += "error";
                errores.add("Ingrese la medida de largo.");
            } else {
                if (!isInteger(getModelo().getNumMedLar())) {
                    indError += "error";
                    errores.add("Número de medida de largo no válido.");
                } else {
                    if (Integer.parseInt(getModelo().getNumMedLar()) == 0) {
                        indError += "error";
                        errores.add("Número de medida de largo no válido.");
                    }
                }
            }
            if (getModelo().getNumMedAnc().trim().equals("")) {
                indError += "error";
                errores.add("Ingrese la medida de ancho.");
            } else {
                if (!isInteger(getModelo().getNumMedAnc())) {
                    indError += "error";
                    errores.add("Número de medida de ancho no válido.");
                } else {
                    if (Integer.parseInt(getModelo().getNumMedAnc()) == 0) {
                        indError += "error";
                        errores.add("Número de medida de ancho no válido.");
                    }
                }
            }
            if (getModelo().getNumMedAlt().trim().equals("")) {
                indError += "error";
                errores.add("Ingrese la medida de alto.");
            } else {
                if (!isInteger(getModelo().getNumMedAlt())) {
                    indError += "error";
                    errores.add("Número de medida de alto no válido.");
                } else {
                    if (Integer.parseInt(getModelo().getNumMedAlt()) == 0) {
                        indError += "error";
                        errores.add("Número de medida de alto no válido.");
                    }
                }
            }
            if (getModelo().getNumPesNet().trim().equals("")) {
                indError += "error";
                errores.add("Ingrese el peso neto.");
            } else {
                if (!isInteger(getModelo().getNumPesNet())) {
                    indError += "error";
                    errores.add("Número del peso neto no válido.");
                } else {
                    if (Integer.parseInt(getModelo().getNumPesNet()) == 0) {
                        indError += "error";
                        errores.add("Número del peso neto no válido.");
                    }
                }
            }
            if (getModelo().getNumPesBru().trim().equals("")) {
                indError += "error";
                errores.add("Ingrese el peso bruto.");
            } else {
                if (!isInteger(getModelo().getNumPesBru())) {
                    indError += "error";
                    errores.add("Número del peso bruto no válido.");
                } else {
                    if (Integer.parseInt(getModelo().getNumPesBru()) == 0) {
                        indError += "error";
                        errores.add("Número del peso bruto no válido.");
                    }
                }
            }
            if (getModelo().getNumCarUti().trim().equals("")) {
                indError += "error";
                errores.add("Ingrese la carga util.");
            } else {
                if (!isInteger(getModelo().getNumCarUti())) {
                    indError += "error";
                    errores.add("Número de carga util no válido.");
                } else {
                    if (Integer.parseInt(getModelo().getNumCarUti()) == 0) {
                        indError += "error";
                        errores.add("Número de carga util no válido.");
                    }
                }
            }
            if (indError.equals("")) {
                helper conex = null;
                try {
                    conex = new helper();
                    indError = conex.getErrorSQL();
                    if (!indError.equals("")) {
                        errores.add(indError);
                    } else {
                        indError = conex.executeNonQuery("CALL usp_updVersiones(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)",
                                new Object[]{getModelo().getIdMar().trim(), getModelo().getIdModMar().trim(), getModelo().getIdVerMod(),
                                    getModelo().getDesVerMod().trim(), getModelo().getDesKat().trim(), getModelo().getDesPreCha().trim(),
                                    getModelo().getDesPreMot().trim(), getModelo().getIdCatVeh(), getModelo().getIdClaVeh(), getModelo().getIdTipCar(),
                                    getModelo().getIdTipCom(), getModelo().getIdTipTras(), getModelo().getIdTipTrac(), getModelo().getDesPotMot().trim(),
                                    getModelo().getNumCilVeh(), getModelo().getNumCil(), getModelo().getNumAsiVeh(), getModelo().getNumPasVeh(),
                                    getModelo().getNumPueVeh(), getModelo().getNumEjeVeh(), getModelo().getNumRueVeh(), getModelo().getNumDisEje(),
                                    getModelo().getNumMedLar(), getModelo().getNumMedAnc(), getModelo().getNumMedAlt(), getModelo().getNumPesNet(),
                                    getModelo().getNumPesBru(), getModelo().getNumCarUti()});
                        if (!indError.equals("")) {
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

    public String eliminar() {
        idAccion = 7;
        verifAccionTipoUsuario();

        if (indErrAcc.equals("")) {
            if (opcion.trim().equals("E")) {
                helper conex = null;
                ResultSet tabla = null;

                try {
                    conex = new helper();

                    indError = conex.getErrorSQL().trim();

                    if (!indError.equals("")) {
                        errores.add(indError);
                    } else {
                        tabla = conex.executeDataSet("CALL usp_verifDependVersiones(?,?,?)",
                                new Object[]{getModelo().getIdMar(), getModelo().getIdModMar(),
                                    Integer.parseInt(getModelo().getIdVerMod())});
                        indError = conex.getErrorSQL();

                        if (!indError.equals("")) {
                            errores.add(indError);
                        } else {
                            int cant = 0;
                            while (tabla.next()) {
                                cant = tabla.getInt(1);
                            }

                            /* Si no tiene dependencias */
                            if (cant == 0) {
                                indError = conex.executeNonQuery("CALL usp_dltVersiones(?,?,?)",
                                        new Object[]{getModelo().getIdMar().trim(), getModelo().getIdModMar().trim(),
                                            getModelo().getIdVerMod()});
                                indError = indError.trim();
                                if (indError.trim().equals("")) {
                                    errores.add(indError);
                                }
                            } else /* si tiene dependencias */ {
                                indError = "error";
                                errores.add("Existen registros dependientes de la versión");
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
            }
        }
        return "eliminar";
    }

    private void getDatosVersiones() {
        helper conex = null;
        ResultSet tabla = null;

        try {
            conex = new helper();
            indError = conex.getErrorSQL();

            if (!indError.equals("")) {
                errores.add(indError);
            } else {
                tabla = conex.executeDataSet("CALL usp_getDatosVersiones(?,?,?)",
                        new Object[]{getModelo().getIdMar(), getModelo().getIdModMar(),
                            getModelo().getIdVerMod()});

                indError = conex.getErrorSQL();

                if (!indError.equals("")) {
                    errores.add(indError);
                } else {
                    while (tabla.next()) {
                        getModelo().setIdMar(tabla.getString("idMar").trim());
                        getModelo().setIdModMar(tabla.getString("idModMar").trim());
                        getModelo().setIdVerMod(tabla.getString("idVerMod"));
                        getModelo().setDesVerMod(tabla.getString("desVerMod").trim());
                        getModelo().setDesKat(tabla.getString("desKat").trim());
                        getModelo().setDesPreCha(tabla.getString("desPreCha").trim());
                        getModelo().setDesPreMot(tabla.getString("desPreMot").trim());
                        getModelo().setIdCatVeh(tabla.getInt("idCatVeh"));
                        getModelo().setIdClaVeh(tabla.getInt("idClaVeh"));
                        getModelo().setIdTipCar(tabla.getInt("idTipCar"));
                        getModelo().setIdTipCom(tabla.getInt("idTipCom"));
                        getModelo().setIdTipTras(tabla.getInt("idTipTras"));
                        getModelo().setIdTipTrac(tabla.getInt("idTipTrac"));
                        getModelo().setDesPotMot(tabla.getString("desPotMot"));
                        getModelo().setNumCilVeh(tabla.getString("numCilVeh"));
                        getModelo().setNumCil(tabla.getString("numCil"));
                        getModelo().setNumAsiVeh(tabla.getString("numAsiVeh"));
                        getModelo().setNumPasVeh(tabla.getString("numPasVeh"));
                        getModelo().setNumPueVeh(tabla.getString("numPueVeh"));
                        getModelo().setNumEjeVeh(tabla.getString("numEjeVeh"));
                        getModelo().setNumRueVeh(tabla.getString("numRueVeh"));
                        getModelo().setNumDisEje(tabla.getString("numDisEje"));
                        getModelo().setNumMedLar(tabla.getString("numMedLar"));
                        getModelo().setNumMedAnc(tabla.getString("numMedAnc"));
                        getModelo().setNumMedAlt(tabla.getString("numMedAlt"));
                        getModelo().setNumPesNet(tabla.getString("numPesNet"));
                        getModelo().setNumPesBru(tabla.getString("numPesBru"));
                        getModelo().setNumCarUti(tabla.getString("numCarUti"));
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
    }

    public void setModelo(Versiones modelo) {
        this.modelo = modelo;
    }

    public ArrayList<Versiones> getListVersiones() {
        return listVersiones;
    }

    public ArrayList<TipoCarroceria> getListTipCarroceria() {
        return listTipCarroceria;
    }

    public ArrayList<CategoriaVehiculo> getListCatVehiculo() {
        return listCatVehiculo;
    }

    public ArrayList<ClaseVehiculo> getListClaVehiculo() {
        return listClaVehiculo;
    }

    public ArrayList<TipoCombustible> getListTipCombustible() {
        return listTipCombustible;
    }

    public ArrayList<TipoTrasmision> getListTipTransmision() {
        return listTipTransmision;
    }

    public ArrayList<TipoTraccion> getListTipTraccion() {
        return listTipTraccion;
    }

    /**
     * @return the modelo
     */
    public Versiones getModelo() {
        return modelo;
    }

}
