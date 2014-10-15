/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package actions;

import com.opensymphony.xwork2.ModelDriven;
import conexion.helper;
import entities.Campania;
import entities.TipoOrigenCampania;
import java.sql.ResultSet;
import java.util.ArrayList;

public class CampaniaAction extends MasterAction implements ModelDriven<Campania> {

    private Campania modelo = new Campania();
    private ArrayList<Campania> listCam = new ArrayList<Campania>();
    private ArrayList<TipoOrigenCampania> listTipOri = new ArrayList<TipoOrigenCampania>();

    @Override
    public Campania getModel() {
        tituloOpc = "Campañas";
        idClaseAccion = 25;
        return modelo;
    }

    public String vrfSeleccion() {
        idAccion = 1;
        verifAccionTipoUsuario();
        if (indErrAcc.equals("")) {
            if (modelo.getIdCam() == 0) {
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
            nivBandeja = 1;

            urlPaginacion = "campania/Campania";
            varReturn = varReturn.trim();
            varReturnProcess(0);
            if (!listVarReturn.isEmpty()) {
                curPagVis = Integer.parseInt(listVarReturn.get(0).toString().trim());
                modelo.setIdOriCam(Integer.parseInt(listVarReturn.get(1).toString().trim()));
            }
            populateOrigen();
            cantCampaniaIndex();
            verifPag();
            listCampaniaIndex();
        }
        return "success";
    }

    private void populateOrigen() {

        getListTipOri().add(new TipoOrigenCampania(1, "Toyota"));
        getListTipOri().add(new TipoOrigenCampania(2, "MAF"));
        getListTipOri().add(new TipoOrigenCampania(3, "Concesionario"));
        getListTipOri().add(new TipoOrigenCampania(4, "Acuerdos"));

        if (modelo.getIdOriCam() == 0) {
            modelo.setIdOriCam(1);
        }
    }

    private void cantCampaniaIndex() {
        helper conex = null;
        ResultSet tabla = null;
        try {
            conex = new helper();
            indError = conex.getErrorSQL().trim();

            if (!indError.trim().equals("")) {
                errores.add(indError);
            } else {
                tabla = conex.executeDataSet("CALL usp_cantCampaniaIndex(?)", new Object[]{modelo.getIdOriCam()});
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

    private void listCampaniaIndex() {
        helper conex = null;
        ResultSet tabla = null;

        try {
            conex = new helper();
            indError = conex.getErrorSQL();

            if (!indError.equals("")) {
                errores.add(indError);
            } else {
                tabla = conex.executeDataSet("CALL usp_listCampaniaIndex(?,?,?)",
                        new Object[]{modelo.getIdOriCam(), (getCurPag()) * getRegPag(), getRegPag()});

                indError = conex.getErrorSQL();

                if (!indError.equals("")) {
                    errores.add(indError);
                } else {
                    Campania obj;
                    while (tabla.next()) {
                        obj = new Campania();
                        obj.setIdCam(tabla.getInt("idCam"));
                        obj.setDesCam(tabla.getString("desCam"));
                        obj.setDesCamImp(tabla.getString("desCamImp"));
                        obj.setImpRelCam(tabla.getString("impRelCam"));
                        obj.setCodMonCam(tabla.getInt("codMonCam"));
                        listCam.add(obj);
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

    private void getDatosCampania() {
        helper conex = null;
        ResultSet tabla = null;

        try {
            conex = new helper();
            indError = conex.getErrorSQL();

            if (!indError.equals("")) {
                errores.add(indError);
            } else {
                tabla = conex.executeDataSet("CALL usp_getDatosCampania(?,?)",
                        new Object[]{modelo.getIdOriCam(), modelo.getIdCam()});

                indError = conex.getErrorSQL();

                if (!indError.equals("")) {
                    errores.add(indError);
                } else {
                    while (tabla.next()) {
                        modelo.setDesCam(tabla.getString("desCam"));
                        modelo.setDesCamImp(tabla.getString("desCamImp"));
                        modelo.setImpRelCam(tabla.getString("impRelCam"));
                        modelo.setCodMonCam(tabla.getInt("codMonCam"));
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

    public String adicionar() {
        idAccion = 3;
        verifAccionTipoUsuario();
        if (indErrAcc.equals("")) {
            nivBandeja = 1;

            if (!opcion.trim().equals("A") && !opcion.trim().equals("M")) {
                indErrParm = "error";
            } else {
                varReturnProcess(1);
                getDescripcionOrigen();
                if (opcion.equals("A")) {
                    modelo.setImpRelCam(".00");
                    formURL = baseURL + "campania/grabarCampania";
                }
            }
        }
        return "adicionar";
    }

    public String modificar() {
        idAccion = 4;
        verifAccionTipoUsuario();
        if (indErrAcc.equals("")) {
            nivBandeja = 1;

            if (!opcion.trim().equals("A") && !opcion.trim().equals("M")) {
                indErrParm = "error";
            } else {
                varReturnProcess(1);

                if (opcion.equals("M")) {
                    if (modelo.getIdCam() == 0) {
                        indErrParm = "error";
                    } else {
                        getDescripcionOrigen();
                        getDatosCampania();
                        formURL = baseURL + "campania/actualizarCampania";
                    }
                }
            }
        }

        return "adicionar";
    }

    private void getDescripcionOrigen() {
        if (modelo.getIdOriCam() == 1) {
            modelo.setDesOriCam("Toyota");
        } else if (modelo.getIdOriCam() == 2) {
            modelo.setDesOriCam("MAF");
        } else if (modelo.getIdOriCam() == 3) {
            modelo.setDesOriCam("Consecionario");
        }
    }

    public String grabar() {
        idAccion = 5;
        verifAccionTipoUsuario();
        if (indErrAcc.equals("")) {
            modelo.setDesCam(modelo.getDesCam().trim());
            modelo.setDesCamImp(modelo.getDesCamImp().trim());
            modelo.setImpRelCam(modelo.getImpRelCam().trim());

            if (modelo.getDesCam().equals("")) {
                indError += "error";
                errores.add("Debe ingresar la descripción");
            }
            if (modelo.getDesCamImp().equals("")) {
                indError += "error";
                errores.add("Debe ingresar la descripción para impresión");
            }
            if (modelo.getCodMonCam() == 0) {
                indError += "error";
                errores.add("Debe seleccionar el tipo de moneda");
            }
            if (modelo.getImpRelCam().equals("")) {
                indError += "error";
                errores.add("Debe ingresar el importe");
            } else {
                if (Double.parseDouble(modelo.getImpRelCam().toString()) == 0) {
                    indError += "error";
                    errores.add("Importe debe ser mayor a cero");
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
                        indError = conex.executeNonQuery("CALL usp_insCampania(?,?,?,?,?)",
                                new Object[]{modelo.getIdOriCam(), modelo.getDesCam(), modelo.getDesCamImp(), modelo.getImpRelCam(), modelo.getCodMonCam()});

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
            modelo.setDesCam(modelo.getDesCam().trim());
            modelo.setDesCamImp(modelo.getDesCamImp().trim());
            modelo.setImpRelCam(modelo.getImpRelCam().trim());

            if (modelo.getDesCam().equals("")) {
                indError += "error";
                errores.add("Debe ingresar la descripción");
            }
            if (modelo.getDesCamImp().equals("")) {
                indError += "error";
                errores.add("Debe ingresar la descripción para impresión");
            }
            if (modelo.getCodMonCam() == 0) {
                indError += "error";
                errores.add("Debe seleccionar el tipo de moneda");
            }

            if (modelo.getImpRelCam().equals("")) {
                indError += "error";
                errores.add("Debe ingresar el importe");
            } else {
                if (Double.parseDouble(modelo.getImpRelCam().toString()) == 0) {
                    indError += "error";
                    errores.add("Importe debe ser mayor a cero");
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
                        indError = conex.executeNonQuery("CALL usp_updCampania(?,?,?,?,?,?)",
                                new Object[]{modelo.getIdOriCam(), modelo.getIdCam(), modelo.getDesCam(), modelo.getDesCamImp(), modelo.getImpRelCam(), modelo.getCodMonCam()});

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
                helper conex = new helper();

                indError = conex.getErrorSQL().trim();
                if (!indError.equals("")) {
                    errores.add(indError);
                } else {
                    ResultSet tabla = null;
                    try {
                        tabla = conex.executeDataSet("CALL usp_verifDependCam(?,?)",
                                new Object[]{modelo.getIdOriCam(), modelo.getIdCam()});
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
                                indError = conex.executeNonQuery("CALL usp_dltCampania(?)",
                                        new Object[]{modelo.getIdCam()});

                                indError = indError.trim();
                                if (indError.trim().equals("")) {
                                    errores.add(indError);
                                }
                            } else /* si tiene dependencias */ {
                                indError = "error";
                                errores.add("Existen registros dependientes de la campaña");
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
        }
        return "eliminar";
    }

    /**
     * @return the modelo
     */
    public Campania getModelo() {
        return modelo;
    }

    /**
     * @param modelo the modelo to set
     */
    public void setModelo(Campania modelo) {
        this.modelo = modelo;
    }

    /**
     * @return the listCam
     */
    public ArrayList<Campania> getListCam() {
        return listCam;
    }

    /**
     * @param listCam the listCam to set
     */
    public void setListCam(ArrayList<Campania> listCam) {
        this.listCam = listCam;
    }

    /**
     * @return the listTipOri
     */
    public ArrayList<TipoOrigenCampania> getListTipOri() {
        return listTipOri;
    }

    /**
     * @param listTipOri the listTipOri to set
     */
    public void setListTipOri(ArrayList<TipoOrigenCampania> listTipOri) {
        this.listTipOri = listTipOri;
    }
}
