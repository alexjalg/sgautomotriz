package actions;

import static com.opensymphony.xwork2.Action.SUCCESS;
import com.opensymphony.xwork2.ModelDriven;
import conexion.helper;
import entities.CargaDatosClientes;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import org.apache.commons.io.FileUtils;

public class CargaDatosClientesAction extends MasterAction implements ModelDriven<CargaDatosClientes> {

    private CargaDatosClientes modelo = new CargaDatosClientes();
    private ArrayList<CargaDatosClientes> listCargaArchivo = new ArrayList<CargaDatosClientes>();

    private File fileUpload;
    private String fileUploadContentType;
    private String fileUploadFileName;
    private String rutaUpFile = "";
    private String rutaFile = ""; 

    @Override
    public CargaDatosClientes getModel() {
        tituloOpc = "Cargar Archivos de Clientes";
        idClaseAccion = 40;
        return getModelo();
    }

    @Override
    public String execute() {
        idAccion = 1;
        verifAccionTipoUsuario();
        if (indErrAcc.equals("")) {
            nivBandeja = 1;
            formURL = "grabarCargaDatosClientes";
            varReturnProcess(0);
            if (!listVarReturn.isEmpty()) {
                curPagVis = Integer.parseInt(listVarReturn.get(0).toString().trim());
            }
        }
        return SUCCESS;
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
                    int cont = 0;
                    while ((s = entrada.readLine()) != null) {
                        cont += 1;
                        String[] textElements = s.split("\",");
                        if (cont > 1) {
                            getModelo().setIdCli(textElements[1].replaceAll("\"", ""));
                            getModelo().setIdTipDoc(textElements[0].replaceAll("\"", ""));
                            getModelo().setDesCli(textElements[2].replaceAll("\"", ""));
                            getModelo().setDesApeCon(textElements[4].replaceAll("\"", ""));
                            getModelo().setDesNomCon(textElements[4].replaceAll("\"", ""));
                            getModelo().setDesDirCon(textElements[5].replaceAll("\"", ""));
                            getModelo().setIdDisPrv(textElements[8].replaceAll("\"", ""));
                            try {
                                Integer.parseInt(textElements[9].replaceAll("\"", "").toString());
                                getModelo().setNumTelFij(textElements[9].replaceAll("\"", ""));
                            } catch (Exception e) {
                                getModelo().setNumTelFij("0");
                            }
                            try {
                                Integer.parseInt(textElements[10].replaceAll("\"", "").toString());
                                getModelo().setNumTelMov(textElements[10].replaceAll("\"", ""));
                            } catch (Exception e) {
                                getModelo().setNumTelMov("0");
                            }
                            try {
                                Integer.parseInt(textElements[11].replaceAll("\"", "").toString());
                                getModelo().setNumTelOfi(textElements[11].replaceAll("\"", ""));
                            } catch (Exception e) {
                                getModelo().setNumTelOfi("0");
                            }
                            getModelo().setNumTelRP(textElements[12].replaceAll("\"", ""));
                            getModelo().setOtrEmaPer(textElements[13].replaceAll("\"", ""));
                            getModelo().setOtrEmaTra(textElements[13].replaceAll("\"", ""));
                            getModelo().setOtrGenCon(textElements[15].replaceAll("\"", ""));
                            getModelo().setOtrEstCiv(textElements[17].replaceAll("\"", ""));
                            getModelo().setDesNomCenLab(textElements[20].replaceAll("\"", ""));
                            getModelo().setDesDirCenLab(textElements[21].replaceAll("\"", ""));
                            getModelo().setDesCarCon(textElements[22].replaceAll("\"", ""));
                            indError = conex.executeNonQuery("CALL usp_insCargaDatosClientes(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)",
                                    new Object[]{getModelo().getIdCli().replaceAll("'", ""),
                                        (getModelo().getIdTipDoc().length() > 30) ? getModelo().getIdTipDoc().substring(0, 30) : getModelo().getIdTipDoc(),
                                        (getModelo().getDesCli().length() > 60) ? getModelo().getDesCli().substring(0, 60) : getModelo().getDesCli(),
                                        (getModelo().getDesApeCon().length() > 40) ? getModelo().getDesApeCon().substring(0, 40) : getModelo().getDesApeCon(),
                                        (getModelo().getDesNomCon().length() > 30) ? getModelo().getDesNomCon().substring(0, 30) : getModelo().getDesNomCon(),
                                        (getModelo().getDesDirCon().length() > 70) ? getModelo().getDesDirCon().substring(0, 70) : getModelo().getDesDirCon(),
                                        getModelo().getIdDisPrv(),
                                        getModelo().getNumTelFij(),
                                        getModelo().getNumTelMov(),
                                        getModelo().getNumTelOfi(),
                                        getModelo().getNumTelRP(),
                                        (getModelo().getOtrEmaPer().length() > 40) ? getModelo().getOtrEmaPer().substring(0, 40) : getModelo().getOtrEmaPer(),
                                        (getModelo().getOtrEmaTra().length() > 40) ? getModelo().getOtrEmaTra().substring(0, 40) : getModelo().getOtrEmaTra(),
                                        getModelo().getOtrGenCon(),
                                        getModelo().getOtrEstCiv(),
                                        (getModelo().getDesNomCenLab().length() > 60) ? getModelo().getDesNomCenLab().substring(0, 60) : getModelo().getDesNomCenLab(),
                                        (getModelo().getDesDirCenLab().length() > 60) ? getModelo().getDesDirCenLab().substring(0, 60) : getModelo().getDesDirCenLab(),
                                        (getModelo().getDesCarCon().length() > 40) ? getModelo().getDesCarCon().substring(0, 40) : getModelo().getDesCarCon()
                                    });
                            if (!indError.equals("")) {
                                indError = "error";
                                errores.add(indError);
                            }
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
        formURL = baseURL+"cargaDatosClientes/CargaDatosClientes";
        return "grabar";
    }

    public String verificar() {
        idAccion = 3;
        verifAccionTipoUsuario();
        if (modelo.getFileload().equals("")) {
            indError = "error";
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

    /**
     * @return the modelo
     */
    public CargaDatosClientes getModelo() {
        return modelo;
    }

    /**
     * @param modelo the modelo to set
     */
    public void setModelo(CargaDatosClientes modelo) {
        this.modelo = modelo;
    }

    /**
     * @return the listCargaArchivo
     */
    public ArrayList<CargaDatosClientes> getListCargaArchivo() {
        return listCargaArchivo;
    }

}
