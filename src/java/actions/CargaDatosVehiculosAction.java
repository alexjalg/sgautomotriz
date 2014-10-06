package actions;

import static com.opensymphony.xwork2.Action.SUCCESS;
import com.opensymphony.xwork2.ModelDriven;
import conexion.helper;
import entities.CargaDatosVehiculos;
import entities.TipoCargaDatVehiculos;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import org.apache.commons.io.FileUtils;

public class CargaDatosVehiculosAction extends MasterAction implements ModelDriven<CargaDatosVehiculos> {

    private CargaDatosVehiculos modelo = new CargaDatosVehiculos();
    private ArrayList<CargaDatosVehiculos> listCargaArchivo = new ArrayList<CargaDatosVehiculos>();
    private ArrayList<TipoCargaDatVehiculos> listTipoCarga = new ArrayList<TipoCargaDatVehiculos>();

    private File fileUpload;
    private String fileUploadContentType;
    private String fileUploadFileName;
    private String rutaUpFile = "";
    private String rutaFile = "";

    @Override
    public CargaDatosVehiculos getModel() {
        tituloOpc = "Carga de Archivos";
        idClaseAccion = 30;
        return getModelo();
    }

    @Override
    public String execute() {
        idAccion = 1;
        populate();
        verifAccionTipoUsuario();
        if (indErrAcc.equals("")) {
            nivBandeja = 1;
            formURL = "grabarCargaDatosVehiculos";
            varReturnProcess(0);
            if (!listVarReturn.isEmpty()) {
                curPagVis = Integer.parseInt(listVarReturn.get(0).toString().trim());
            }
        }
        return SUCCESS;
    }

    public void populate() {
        listTipoCarga.add(new TipoCargaDatVehiculos("P", "Pedido"));
        listTipoCarga.add(new TipoCargaDatVehiculos("F", "Factura"));
    }

    public String grabar() {
        idAccion = 2;
        verifAccionTipoUsuario();
        if (indError.equals("")) {
            helper conex = null;
            try {
                conex = new helper();
                indError = conex.getErrorSQL();
                if (!indError.equals("")) {
                    errores.add(indError);
                } else {                    
                    File fileToCreate = new File(getRutaUpFile(), this.fileUploadFileName);
                    FileUtils.copyFile(this.getFileUpload(), fileToCreate);
                    BufferedReader entrada = new BufferedReader(new FileReader(fileToCreate));
                    String s;
                    while ((s = entrada.readLine()) != null) {
                        if (s.length() >= 351) {
                            modelo.setIdVeh(s.substring(0, 6));
                            modelo.setDesNumCha(s.substring(26, 46));
                            modelo.setDesNumMot(s.substring(46, 58));
                            modelo.setIdColExt(s.substring(58, 61));
                            modelo.setDesColExt(s.substring(61, 86));
                            modelo.setNumAnoFab(Integer.parseInt(s.substring(86, 90).toString()));
                            modelo.setNumAnoMod(Integer.parseInt(s.substring(90, 94).toString()));
                            modelo.setIdVerMod(s.substring(132, 136));                            
                            modelo.setDesVerMod(s.substring(151, 166));                            
                            modelo.setDesPolImp(s.substring(271, 277));
                            modelo.setIdTipDocVen(s.substring(277, 279));
                            modelo.setDesNumFacPrv(s.substring(279, 289));
                            modelo.setFecFacPrv(s.substring(289, 297));
                            modelo.setImpTipCamFP(Integer.parseInt(s.substring(297, 303).toString()));
                            modelo.setImpMonExt(s.substring(341, 351));

                            indError = conex.executeNonQuery("CALL usp_insCargaDatosVehiculos(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)",
                                    new Object[]{modelo.getIdVeh(),
                                        modelo.getDesNumCha().trim(),
                                        modelo.getDesNumMot().trim(),
                                        modelo.getIdColExt(),
                                        modelo.getDesColExt().trim(),
                                        modelo.getNumAnoFab(),
                                        modelo.getNumAnoMod(),
                                        modelo.getIdVerMod(),
                                        modelo.getDesVerMod().trim(),
                                        modelo.getDesPolImp().trim(),
                                        modelo.getIdTipDocVen(),
                                        modelo.getDesNumFacPrv().trim(),
                                        modelo.getFecFacPrv(),
                                        modelo.getImpTipCamFP(),
                                        modelo.getImpMonExt()
                                    });
                            if (!indError.equals("")) {
                                errores.add(indError);
                            }
                        } else {
                            indError = "Error";
                            errores.add("El archivo no cumple con el tamaño de linea requerida.");
                            break;
                        }
                    }
                }
            } catch (Exception e) {
                indError = "error";
                errores.add(e.getMessage());
            } finally {
                conex.returnConnect();
            }
        }        
        populate();
        formURL = "CargaDatosVehiculos";
        return "grabar";
    }
    
    public String verificar(){
        idAccion = 3;
        verifAccionTipoUsuario();
        if(modelo.getFileload().equals("")){
            indError = "Error";
            errores.add("Debe de seleccionar un archivo.");
        }        
        return "verificar";
    }
    
    public String getRutaUpFile() {
        try {
            //OBTIENE LA RUTA DEL VIEW ACTUAL
            String filePath = servletRequest.getSession().getServletContext().getRealPath("/");
            //RUTEA A LA CARPETA TMP, DONDE SE GUARDAN LOS ARCHIVOS.
            this.setRutaUpFile(filePath + "filesup\\tmp\\");
        } catch (Exception ex) {
        }
        return rutaUpFile;
    }

    public CargaDatosVehiculos getModelo() {
        return modelo;
    }

    public void setModelo(CargaDatosVehiculos modelo) {
        this.modelo = modelo;
    }

    public ArrayList<CargaDatosVehiculos> getListCargaArchivo() {
        return listCargaArchivo;
    }

    public void setListCargaArchivo(ArrayList<CargaDatosVehiculos> listCargaArchivo) {
        this.listCargaArchivo = listCargaArchivo;
    }

    public ArrayList<TipoCargaDatVehiculos> getListTipoCarga() {
        return listTipoCarga;
    }

    public void setListTipoCarga(ArrayList<TipoCargaDatVehiculos> listTipoCarga) {
        this.listTipoCarga = listTipoCarga;
    }

    /**
     * @return the fileUpload
     */
    public File getFileUpload() {
        return fileUpload;
    }

    /**
     * @param fileUpload the fileUpload to set
     */
    public void setFileUpload(File fileUpload) {
        this.fileUpload = fileUpload;
    }

    /**
     * @return the fileUploadContentType
     */
    public String getFileUploadContentType() {
        return fileUploadContentType;
    }

    /**
     * @param fileUploadContentType the fileUploadContentType to set
     */
    public void setFileUploadContentType(String fileUploadContentType) {
        this.fileUploadContentType = fileUploadContentType;
    }

    /**
     * @return the fileUploadFileName
     */
    public String getFileUploadFileName() {
        return fileUploadFileName;
    }

    /**
     * @param fileUploadFileName the fileUploadFileName to set
     */
    public void setFileUploadFileName(String fileUploadFileName) {
        this.fileUploadFileName = fileUploadFileName;
    }

    /**
     * @param rutaUpFile the rutaUpFile to set
     */
    public void setRutaUpFile(String rutaUpFile) {
        this.rutaUpFile = rutaUpFile;
    }

    /**
     * @return the rutaFile
     */
    public String getRutaFile() {
        return rutaFile;
    }

    /**
     * @param rutaFile the rutaFile to set
     */
    public void setRutaFile(String rutaFile) {
        this.rutaFile = rutaFile;
    }

}
