/*
 * Action: Registro de ventas
 * Creado por: Angelo Ccoicca
 * Fecha de creación: 25-09-2014
 * Modificado por                   Fecha de Modificación
 * - 
 * -
 */

package actions;

import com.opensymphony.xwork2.ActionContext;
import conexion.helper;
import java.sql.ResultSet;
import java.util.Map;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.util.JRLoader;

public class GeneraPDF extends MasterAction {
    private Map<String, Object> sesion_cot = ActionContext.getContext().getSession();
    
    public String registroVenta() {
        
        
        return "registroVenta";
    }
    
    public void generaPDFregistroVenta(String idNumIntRV,String idUsu) {
        helper conex = null;
        helper conex1 = null;
        ResultSet tabla = null;
        
        try {
            conex = new helper();
            conex1 = new helper();
            indError = conex.getErrorSQL();
            
            if(!indError.equals("")) {
                errores.add(indError);
            } else {
                
            }
        } catch (Exception e) {
            errores.add(e.getMessage());
        } finally {
            try {
                tabla.close();
                conex.returnConnect();
                conex1.returnConnect();
            } catch (Exception e) {
            }
        }
        
        if(!errores.isEmpty()) {
            indErrorTot = "error";
        }
    }
}
