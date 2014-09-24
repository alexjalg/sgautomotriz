/*
 * Action: Concesionarios
 * Creado por: Angelo Ccoicca
 * Fecha de creación: 09-04-2014
 * Modificado por                   Fecha de Modificación
 * - 
 * -
 */

package actions;

import static com.opensymphony.xwork2.Action.SUCCESS;
import com.opensymphony.xwork2.ModelDriven;
import conexion.helper;
import entities.Cortesias;
import entities.Marcas;
import java.sql.ResultSet;
import java.util.ArrayList;

public class CortesiasAction extends MasterAction implements ModelDriven<Cortesias> {
    private Cortesias modelo = new Cortesias();
    private ArrayList<Cortesias> listCortesias = new ArrayList<Cortesias>();
    private ArrayList<Marcas> listMarcas = new ArrayList<Marcas>();

    @Override
    public Cortesias getModel() {
        tituloOpc = "Cortesias";
        idClaseAccion = 32;
        
        return modelo;
    }
    
    public String vrfSeleccion() {
        idAccion = 1;
        verifAccionTipoUsuario();
        
        if (indErrAcc.equals("")) {
            if (getModelo().getIdCorMar()==0) {
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
            nivBandeja = 2;   
            varReturnProcess(0);

            if (!listVarReturn.isEmpty()) {
                curPagVis = Integer.parseInt(listVarReturn.get(0).toString().trim());
                getModelo().setIdCon(Integer.parseInt(listVarReturn.get(1).toString().trim()));
                getModelo().setIdMar(listVarReturn.get(2).toString().trim());
            }
            
            if (modelo.getIdCon()==0) {
                indErrParm = "error";
            } else {
                getDatosConcesionarioMarca();
                listMarcas();
                
                regPag = 13;
                urlPaginacion = "cortesias/Cortesia";
                cantCortesiasIndex();
                verifPag();
                listCortesiasIndex();
            }
        }
        
        return SUCCESS;
    }
    
    private void cantCortesiasIndex() {
        helper conex = null;
        ResultSet tabla = null;
        try {
            conex = new helper();
            indError += conex.getErrorSQL();

            if (!indError.equals("")) {
                errores.add(indError);
            } else {
                tabla = conex.executeDataSet("CALL usp_cantCortesiasMarcaConcesIndex(?,?)", 
                        new Object[]{ getModelo().getIdCon(),getModelo().getIdMar() });

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

    private void listCortesiasIndex() {
        helper conex = null;
        ResultSet tabla = null;
        try {
            conex = new helper();
            indError += conex.getErrorSQL();
            if (!indError.equals("")) {
                errores.add(indError);
            } else {
                tabla = conex.executeDataSet("CALL usp_listCortesiasMarcaConcesIndex(?,?,?,?)",
                        new Object[]{ getModelo().getIdCon(),getModelo().getIdMar(),
                            getCurPag()*regPag, regPag });
                indError += conex.getErrorSQL();
                if (!indError.equals("")) {
                    errores.add(indError);
                } else {
                    Cortesias obj;
                    while (tabla.next()) {
                        obj = new Cortesias();
                        obj.setIdCorMar(tabla.getInt("idCorMar"));
                        obj.setDesCorMar(tabla.getString("desCorMar").trim());
                        obj.setEdoCorMar(tabla.getString("edoCorMar").trim());
                        listCortesias.add(obj);
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
    
    private void getDatosConcesionarioMarca() {
        helper conex = null;
        ResultSet tabla = null;
        
        try {
            conex = new helper();
            indError += conex.getErrorSQL();
            
            if(!indError.equals("")) {
                errores.add(indError);
            } else {
                tabla = conex.executeDataSet("CALL usp_getDatosConcesionario(?)", 
                        new Object[]{ getModelo().getIdCon() });
                indError += conex.getErrorSQL();
                
                if(!indError.equals("")) {
                    errores.add(indError);
                } else {
                    while(tabla.next()) {
                        getModelo().setDesCon(tabla.getString("desCon"));
                    }
                }
                
                tabla = null;
                tabla = conex.executeDataSet("CALL usp_getDatosMarca(?)", 
                        new Object[]{ getModelo().getIdMar() });
                indError += conex.getErrorSQL();
                
                if(!indError.equals("")) {
                    errores.add(indError);
                } else {
                    while(tabla.next()) {
                        getModelo().setDesMar(tabla.getString("desMar"));
                    }
                }
            }
        } catch (Exception e) {
            indError += "";
            errores.add(e.getMessage());
        } finally {
            try {
                tabla.close();
                conex.returnConnect();
            } catch (Exception e) {
            }
        }
    }
    
    private void listMarcas() {
        helper conex = null;
        ResultSet tabla = null;

        try {
            conex = new helper();
            indError += conex.getErrorSQL();

            if(!indError.equals("")) {
                errores.add(indError);
            } else {
                tabla = conex.executeDataSet("CALL usp_listMarcas()", new Object[]{});
                indError = conex.getErrorSQL();

                if(!indError.equals("")) {
                    errores.add(indError);
                } else {
                    Marcas obj;
                    while(tabla.next()) {
                        obj = new Marcas();
                        obj.setIdMar(tabla.getString("idMar"));
                        if(getModelo().getIdMar().trim().equals("")) {
                            getModelo().setIdMar(obj.getIdMar());
                        }
                        obj.setDesMar(tabla.getString("desMar"));
                        listMarcas.add(obj);
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
    
    public String adicionar() {
        idAccion = 3;
        verifAccionTipoUsuario();
        
        if (indErrAcc.equals("")) {
            nivBandeja = 2;
            if (!opcion.trim().equals("A") || getModelo().getIdCon()==0 || getModelo().getIdMar().equals("")) {
                indErrParm = "error";
            } else {
                varReturnProcess(1);
                if (opcion.equals("A")) {
                    getModelo().setIdCorMar(0);
                    getDatosConcesionarioMarca();
                    
                    formURL = baseURL + "cortesias/grabarCortesia";
                }
            }
        }
        
        return "adicionar";
    }
    
    private void getDatosCortesia() {
        helper conex = null;
        ResultSet tabla = null;

        try {
            conex = new helper();
            indError += conex.getErrorSQL();

            if(!indError.equals("")) {
                errores.add(indError);
            } else {
                tabla = conex.executeDataSet("CALL usp_getDatosCortesia(?,?,?)", 
                        new Object[]{ getModelo().getIdCon(),getModelo().getIdMar(),getModelo().getIdCorMar() });
                indError = conex.getErrorSQL();

                if(!indError.equals("")) {
                    errores.add(indError);
                } else {
                    while(tabla.next()) {
                        getModelo().setDesCorMar(tabla.getString("desCorMar"));
                        getModelo().setEdoCorMar(tabla.getString("edoCorMar"));
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
    
    public String modificar() {
        idAccion = 4;
        verifAccionTipoUsuario();
        
        if (indErrAcc.equals("")) {
            nivBandeja = 2;
            if (!opcion.trim().equals("M") || getModelo().getIdCon()==0 || getModelo().getIdMar().equals("") || 
                    getModelo().getIdCorMar()==0) {
                indErrParm = "error";
            } else {
                varReturnProcess(1);
                if (opcion.equals("M")) {
                    getDatosCortesia();
                    getDatosConcesionarioMarca();
                    
                    formURL = baseURL + "cortesias/actualizarCortesia";
                }
            }
        }
        
        return "modificar";
    }
    
    public String grabar() {
        idAccion = 5;
        verifAccionTipoUsuario();

        if (indErrAcc.equals("")) {
            if (modelo.getDesCorMar().equals("")) {
                indError += "error";
                errores.add("Ingrese la descripción de la cortesía");
            }

            if (indError.equals("")) {
                helper conex = null;
                ResultSet tabla = null;

                try {
                    conex = new helper();
                    indError += conex.getErrorSQL();

                    if (!indError.equals("")) {
                        errores.add(indError);
                    } else {
                        if(getModelo().getDesCorMar().length()>100) 
                            getModelo().setDesCorMar(getModelo().getDesCorMar().substring(0,100));
                        
                        indError += conex.executeNonQuery("CALL usp_insCortesiaConcesMarca(?,?,?)",
                                new Object[]{ getModelo().getIdCon(),getModelo().getIdMar(),
                                    getModelo().getDesCorMar() });

                        if (!indError.equals("")) {
                            errores.add(indError);
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
        
        return "grabar";
    }
    
    public String actualizar() {
        idAccion = 6;
        verifAccionTipoUsuario();

        if (indErrAcc.equals("")) {
            if (modelo.getDesCorMar().equals("")) {
                indError += "error";
                errores.add("Ingrese la descripción de la cortesía");
            }

            if (indError.equals("")) {
                helper conex = null;
                ResultSet tabla = null;

                try {
                    conex = new helper();
                    indError += conex.getErrorSQL();

                    if (!indError.equals("")) {
                        errores.add(indError);
                    } else {
                        if(getModelo().getDesCorMar().length()>100) 
                            getModelo().setDesCorMar(getModelo().getDesCorMar().substring(0,100));
                        
                        indError += conex.executeNonQuery("CALL usp_updCortesiaConcesMarca(?,?,?,?)",
                                new Object[]{ getModelo().getIdCon(),getModelo().getIdMar(),getModelo().getIdCorMar(),
                                    getModelo().getDesCorMar() });

                        if (!indError.equals("")) {
                            errores.add(indError);
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
        
        return "actualizar";
    }
    
    public String actualizarEstado() {
        idAccion = 7;
        verifAccionTipoUsuario();

        if (indErrAcc.equals("")) {
            if (indError.equals("")) {
                helper conex = null;
                ResultSet tabla = null;

                try {
                    conex = new helper();
                    indError += conex.getErrorSQL();

                    if (!indError.equals("")) {
                        errores.add(indError);
                    } else {
                        indError += conex.executeNonQuery("CALL usp_updEstadoCortesiaConcesMarca(?,?,?,?)",
                                new Object[]{ getModelo().getIdCon(),getModelo().getIdMar(),getModelo().getIdCorMar(),
                                    getModelo().getEdoCorMar() });

                        if (!indError.equals("")) {
                            errores.add(indError);
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
        
        return "actualizarEstado";
    }
    
    public String eliminar() {
        idAccion = 8;
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
                        tabla = conex.executeDataSet("CALL usp_verifDependCortesia(?,?,?)",
                                new Object[]{ getModelo().getIdCon(),getModelo().getIdMar(),getModelo().getIdCorMar() });
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
                                indError = conex.executeNonQuery("CALL usp_dltCortesiaConcesMarca(?,?,?)",
                                        new Object[]{ getModelo().getIdCon(),getModelo().getIdMar(),getModelo().getIdCorMar() });

                                indError = indError.trim();
                                if (indError.trim().equals("")) {
                                    errores.add(indError);
                                }
                            } else /* si tiene dependencias */ {
                                indError = "error";
                                errores.add("Existen registros dependientes de la cortesía");
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
    public Cortesias getModelo() {
        return modelo;
    }

    /**
     * @param modelo the modelo to set
     */
    public void setModelo(Cortesias modelo) {
        this.modelo = modelo;
    }

    /**
     * @return the listCortesias
     */
    public ArrayList<Cortesias> getListCortesias() {
        return listCortesias;
    }

    /**
     * @return the listMarcas
     */
    public ArrayList<Marcas> getListMarcas() {
        return listMarcas;
    }
}
