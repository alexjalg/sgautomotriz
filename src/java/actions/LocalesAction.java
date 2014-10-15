/*
 * Action: Locales
 * Creado por: Angelo Ccoicca
 * Fecha de creación: 15-05-2014
 * Modificado por                   Fecha de Modificación
 * - 
 * -
 */
package actions;

import com.opensymphony.xwork2.ModelDriven;
import conexion.helper;
import entities.Departamentos;
import entities.Distritos;
import entities.Locales;
import entities.Provincias;
import entities.TipoLocal;
import java.sql.ResultSet;
import java.util.ArrayList;

public class LocalesAction extends MasterAction implements ModelDriven<Locales> {

    private Locales modelo = new Locales();
    private ArrayList<Locales> listLocales = new ArrayList<Locales>();
    private ArrayList<Departamentos> listDepartamentos = new ArrayList<Departamentos>();
    private ArrayList<Provincias> listProvincias = new ArrayList<Provincias>();
    private ArrayList<Distritos> listDistritos = new ArrayList<Distritos>();
    private ArrayList<TipoLocal> listTipoLocales = new ArrayList<TipoLocal>();

    @Override
    public Locales getModel() {
        tituloOpc = "Locales";
        idClaseAccion = 6;

        return modelo;
    }

    public String vrfSeleccion() {
        idAccion = 1;

        verifAccionTipoUsuario();

        if (indErrAcc.equals("")) {
            if (modelo.getIdLocCon().trim().equals("") || modelo.getIdLocCon().trim().equals("0")) {
                indError = "error";
                errores.add("No ha seleccionado ningun registro");
            }
        }

        return "vrfSeleccion";
    }

    private void cantLocalesConcesIndex() {
        helper conex = null;
        ResultSet tabla = null;

        try {
            conex = new helper();
            indError = conex.getErrorSQL();

            if (!indError.equals("")) {
                errores.add(indError);
            } else {
                tabla = conex.executeDataSet("CALL usp_cantLocalesConcesIndex(?)",
                        new Object[]{modelo.getIdCon()});

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
            }
        }
    }

    private void listLocalesConcesIndex() {
        helper conex = null;
        ResultSet tabla = null;

        try {
            conex = new helper();
            indError = conex.getErrorSQL();

            if (!indError.equals("")) {
                errores.add(indError);
            } else {
                tabla = conex.executeDataSet("CALL usp_listLocalesConcesIndex(?,?,?)",
                        new Object[]{modelo.getIdCon(), getCurPag() * regPag, regPag});

                indError = conex.getErrorSQL();

                if (!indError.equals("")) {
                    errores.add(indError);
                } else {
                    Locales obj;
                    while (tabla.next()) {
                        obj = new Locales();
                        obj.setIdLocCon(tabla.getString("idLocCon"));
                        obj.setDesLocCon(tabla.getString("desLocCon"));
                        obj.setDesNomCom(tabla.getString("desNomCom"));
                        obj.setNumRuc(tabla.getString("numRuc"));
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
    }

    @Override
    public String execute() {
        idAccion = 2;

        verifAccionTipoUsuario();

        if (indErrAcc.equals("") && indVerifAcc == 0) {
            nivBandeja = 2;
            urlPaginacion = "locales/Local";

            varReturnProcess(0);
            if (!listVarReturn.isEmpty()) {
                curPagVis = Integer.parseInt(listVarReturn.get(0).toString().trim());
                modelo.setIdCon(listVarReturn.get(1).toString());
            }

            if (modelo.getIdCon().equals("") || modelo.getIdCon().equals("0")) {
                indErrParm = "error";
            } else {
                getDatosConcesionario();

                cantLocalesConcesIndex();
                verifPag();
                listLocalesConcesIndex();
            }
        }

        return "success";
    }

    public String adicionar() {
        idAccion = 3;

        verifAccionTipoUsuario();

        if (indErrAcc.equals("")) {
            nivBandeja = 2;

            if ((!opcion.trim().equals("A") && !opcion.trim().equals("M"))) {
                indErrParm = "error";
            } else {
                varReturnProcess(1);

                if (modelo.getIdCon().equals("") || modelo.getIdCon().equals("0")) {
                    indErrParm = "error";
                } else {
                    getDatosConcesionario();

                    if (opcion.equals("A")) {
                        formURL = baseURL + "locales/grabarLocal";
                    }

                    if (opcion.equals("M")) {

                        getDatosLocalConcesionario();
                        formURL = baseURL + "locales/actualizarLocal";
                    }

                    populateForm();
                }
            }
        }

        return "adicionar";
    }

    public String modificar() {
        idAccion = 4;

        verifAccionTipoUsuario();

        if (indErrAcc.equals("")) {
            nivBandeja = 2;

            if ((!opcion.trim().equals("A") && !opcion.trim().equals("M"))) {
                indErrParm = "error";
            } else {
                varReturnProcess(1);

                if (modelo.getIdCon().equals("") || modelo.getIdCon().equals("0")) {
                    indErrParm = "error";
                } else {
                    getDatosConcesionario();

                    if (opcion.equals("A")) {
                        formURL = baseURL + "locales/grabarLocal";
                    }

                    if (opcion.equals("M")) {

                        getDatosLocalConcesionario();
                        formURL = baseURL + "locales/actualizarLocal";
                    }

                    populateForm();
                }
            }
        }

        return "adicionar";
    }

    public void populateForm() {
        helper conex = null;
        ResultSet tabla = null;

        try {
            conex = new helper();

            indError += conex.getErrorSQL();

            if (!indError.equals("")) {
                errores.add(indError);
            } else {
                tabla = conex.executeDataSet("CALL usp_listTipoLocal()",new Object[]{});
                
                indError += conex.getErrorSQL();

                if (!indError.equals("")) {
                    errores.add(indError);
                } else {
                    TipoLocal obj;
                    while (tabla.next()) {
                        obj = new TipoLocal();
                        obj.setIdTipLoc(tabla.getInt("idTipLoc"));
                        obj.setDesTipLoc(tabla.getString("desTipLoc"));
                        listTipoLocales.add(obj);
                    }
                }
                
                tabla = null;
                tabla = conex.executeDataSet("CALL usp_listDepartamentos()", new Object[]{});
                
                indError += conex.getErrorSQL();

                if (!indError.equals("")) {
                    errores.add(indError);
                } else {
                    Departamentos obj;
                    while (tabla.next()) {
                        obj = new Departamentos();
                        obj.setIdDep(tabla.getInt("idDep"));
                        obj.setDesDep(tabla.getString("desDep"));
                        listDepartamentos.add(obj);
                    }
                }

                tabla = null;
                tabla = conex.executeDataSet("CALL usp_listProvincias(?)",
                        new Object[]{modelo.getIdDep()});

                indError += conex.getErrorSQL();

                if (!indError.equals("")) {
                    errores.add(indError);
                } else {
                    Provincias obj;
                    while (tabla.next()) {
                        obj = new Provincias();
                        obj.setIdPrvDep(tabla.getInt("idPrvDep"));
                        obj.setDesProv(tabla.getString("desProv"));
                        listProvincias.add(obj);
                    }
                }

                tabla = null;
                tabla = conex.executeDataSet("CALL usp_listDistritos(?,?)",
                        new Object[]{modelo.getIdDep(), modelo.getIdPrvDep()});

                indError += conex.getErrorSQL();

                if (!indError.equals("")) {
                    errores.add(indError);
                } else {
                    Distritos obj;
                    while (tabla.next()) {
                        obj = new Distritos();
                        obj.setIdDisPrv(tabla.getInt("idDisPrv"));
                        obj.setDesDis(tabla.getString("desDis"));
                        listDistritos.add(obj);
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

    public String getProvincias() {
        helper conex = null;
        ResultSet tabla = null;

        try {
            conex = new helper();
            indError += conex.getErrorSQL();

            if (!indError.equals("")) {
                errores.add(indError);
            } else {
                tabla = conex.executeDataSet("CALL usp_listProvincias(?)",
                        new Object[]{modelo.getIdDep()});
                indError += conex.getErrorSQL();

                if (!indError.equals("")) {
                    errores.add(indError);
                } else {
                    Provincias obj;
                    while (tabla.next()) {
                        obj = new Provincias();
                        obj.setIdPrvDep(tabla.getInt("idPrvDep"));
                        obj.setDesProv(tabla.getString("desProv"));
                        listProvincias.add(obj);
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

        return "getProvincias";
    }

    public String getDistritos() {
        helper conex = null;
        ResultSet tabla = null;

        try {
            conex = new helper();
            indError += conex.getErrorSQL();

            if (!indError.equals("")) {
                errores.add(indError);
            } else {
                tabla = conex.executeDataSet("CALL usp_listDistritos(?,?)",
                        new Object[]{modelo.getIdDep(), modelo.getIdPrvDep()});
                indError += conex.getErrorSQL();

                if (!indError.equals("")) {
                    errores.add(indError);
                } else {
                    Distritos obj;
                    while (tabla.next()) {
                        obj = new Distritos();
                        obj.setIdDisPrv(tabla.getInt("idDisPrv"));
                        obj.setDesDis(tabla.getString("desDis"));
                        listDistritos.add(obj);
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

        return "getDistritos";
    }
    
    public void getDatosConcesionario() {
        helper conex = null;
        ResultSet tabla = null;

        try {
            conex = new helper();
            indError += conex.getErrorSQL();

            if (!indError.equals("")) {
                errores.add(indError);
            } else {
                tabla = conex.executeDataSet("CALL usp_getDatosConcesionario(?)",
                        new Object[]{modelo.getIdCon()});

                indError = conex.getErrorSQL();

                if (!indError.equals("")) {
                    errores.add(indError);
                } else {
                    while (tabla.next()) {
                        modelo.setDesCon(tabla.getString("desCon"));
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

    public void getDatosLocalConcesionario() {
        helper conex = null;
        ResultSet tabla = null;

        try {
            conex = new helper();
            indError = conex.getErrorSQL();

            if (!indError.equals("")) {
                errores.add(indError);
            } else {
                tabla = conex.executeDataSet("CALL usp_getDatosLocalConces(?,?)",
                        new Object[]{modelo.getIdCon(), modelo.getIdLocCon()});
                indError = conex.getErrorSQL();

                if (!indError.equals("")) {
                    errores.add(indError);
                } else {
                    while (tabla.next()) {
                        modelo.setIdLocCon(tabla.getString("idLocCon"));
                        modelo.setDesLocCon(tabla.getString("desLocCon"));
                        modelo.setDesNomCom(tabla.getString("desNomCom"));
                        modelo.setDesCorta(tabla.getString("desCorta"));
                        modelo.setIdTipLoc(tabla.getInt("idTipLoc"));
                        modelo.setOtrDirLoc(tabla.getString("otrDirLoc"));
                        modelo.setIdDep(tabla.getInt("idDep"));
                        modelo.setIdPrvDep(tabla.getInt("idPrvDep"));
                        modelo.setIdDisPrv(tabla.getInt("idDisPrv"));
                        modelo.setNumRuc(tabla.getString("numRuc"));
                        modelo.setOtrDatEqu1(tabla.getString("otrDatEqu1"));
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

    public String grabar() {
        idAccion = 5;
        verifAccionTipoUsuario();

        if (indErrAcc.equals("")) {
            if (modelo.getDesLocCon().equals("")) {
                indError += "error";
                errores.add("Ingrese el nombre del local");
            }

            if (modelo.getDesNomCom().equals("")) {
                indError += "error";
                errores.add("Ingrese el nombre comercial del local");
            }

            if (modelo.getDesCorta().equals("")) {
                indError += "error";
                errores.add("Ingrese el nombre corto del local");
            }

            if (modelo.getIdTipLoc() == 0) {
                indError += "error";
                errores.add("Seleccione el tipo de local");
            }

            if (modelo.getOtrDirLoc().equals("")) {
                indError += "error";
                errores.add("Ingrese la dirección del local");
            }

            if (modelo.getIdDep() == 0 || modelo.getIdPrvDep() == 0 || modelo.getIdDisPrv() == 0) {
                indError += "error";
                errores.add("Seleccione un departamento, provincia y distrito");
            }

            if (modelo.getNumRuc().equals("")) {
                indError += "error";
                errores.add("Ingrese el número de RUC");
            } else {
                if (!isLong(modelo.getNumRuc())) {
                    indError += "error";
                    errores.add("El número de RUC no es válido");
                } else {
                    Long rucAux = Long.parseLong(modelo.getNumRuc());

                    if (rucAux == 0 || rucAux > 99999999999L || rucAux < 10000000000L) {
                        indError += "error";
                        errores.add("El número de RUC no es válido");
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
                        indError = conex.executeNonQuery("CALL usp_insLocalConces(?,?,?,?,?,?,?,?,?,?,?)",
                                new Object[]{modelo.getIdCon(), modelo.getDesLocCon(), modelo.getDesNomCom(),
                            modelo.getDesCorta(), modelo.getIdTipLoc(), modelo.getOtrDirLoc(),
                            modelo.getIdDep(), modelo.getIdPrvDep(), modelo.getIdDisPrv(),
                            modelo.getNumRuc(), modelo.getOtrDatEqu1()});

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
            if (modelo.getDesLocCon().equals("")) {
                indError += "error";
                errores.add("Ingrese el nombre del local");
            }

            if (modelo.getDesNomCom().equals("")) {
                indError += "error";
                errores.add("Ingrese el nombre comercial del local");
            }

            if (modelo.getDesCorta().equals("")) {
                indError += "error";
                errores.add("Ingrese el nombre corto del local");
            }

            if (modelo.getIdTipLoc() == 0) {
                indError += "error";
                errores.add("Seleccione el tipo de local");
            }

            if (modelo.getOtrDirLoc().equals("")) {
                indError += "error";
                errores.add("Ingrese la dirección del local");
            }

            if (modelo.getIdDep() == 0 || modelo.getIdPrvDep() == 0 || modelo.getIdDisPrv() == 0) {
                indError += "error";
                errores.add("Seleccione un departamento, provincia y distrito");
            }

            if (modelo.getNumRuc().equals("")) {
                indError += "error";
                errores.add("Ingrese el número de RUC");
            } else {
                if (!isLong(modelo.getNumRuc())) {
                    indError += "error";
                    errores.add("El número de RUC no es válido");
                } else {
                    Long rucAux = Long.parseLong(modelo.getNumRuc());

                    if (rucAux == 0 || rucAux > 99999999999L || rucAux < 10000000000L) {
                        indError += "error";
                        errores.add("El número de RUC no es válido");
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
                        indError = conex.executeNonQuery("CALL usp_updLocalConces(?,?,?,?,?,?,?,?,?,?,?,?)",
                                new Object[]{ Integer.parseInt(modelo.getIdCon()), Integer.parseInt(modelo.getIdLocCon()),
                            modelo.getDesLocCon(),modelo.getDesNomCom(),modelo.getDesCorta(),modelo.getIdTipLoc(),
                            modelo.getOtrDirLoc(), modelo.getIdDep(), modelo.getIdPrvDep(),modelo.getIdDisPrv(),
                            modelo.getNumRuc(),modelo.getOtrDatEqu1() });

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
                        tabla = conex.executeDataSet("CALL usp_verifDependLocalConces(?,?)",
                                new Object[]{Integer.parseInt(modelo.getIdCon()), Integer.parseInt(modelo.getIdLocCon())});
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
                                indError = conex.executeNonQuery("CALL usp_dltLocalConces(?,?)",
                                        new Object[]{Integer.parseInt(modelo.getIdCon()), Integer.parseInt(modelo.getIdLocCon())});

                                indError = indError.trim();
                                if (indError.trim().equals("")) {
                                    errores.add(indError);
                                }
                            } else /* si tiene dependencias */ {
                                indError = "error";
                                errores.add("Existen registros dependientes del concesionario");
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

    /**
     * @return the modelo
     */
    public Locales getModelo() {
        return modelo;
    }

    /**
     * @param modelo the modelo to set
     */
    public void setModelo(Locales modelo) {
        this.modelo = modelo;
    }

    /**
     * @return the listLocales
     */
    public ArrayList<Locales> getListLocales() {
        return listLocales;
    }

    /**
     * @return the listDepartamentos
     */
    public ArrayList<Departamentos> getListDepartamentos() {
        return listDepartamentos;
    }

    /**
     * @return the listProvincias
     */
    public ArrayList<Provincias> getListProvincias() {
        return listProvincias;
    }

    /**
     * @return the listDistritos
     */
    public ArrayList<Distritos> getListDistritos() {
        return listDistritos;
    }

    /**
     * @return the listTipoLocales
     */
    public ArrayList<TipoLocal> getListTipoLocales() {
        return listTipoLocales;
    }
}
