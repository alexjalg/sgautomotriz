/*
 * Action: Inicio
 * Descripción: Action para ayudar el redireccionamiento al acceder a la aplicación
 * Creado por: Angelo Ccoicca
 * Fecha de creación: 07-03-2014
 * Modificado por                   Fecha de Modificación
 * - 
 * -
 */
package actions;

import java.util.Map;

import com.opensymphony.xwork2.ActionContext;

@SuppressWarnings("serial")
public class InicioAction extends MasterAction 
{
    public Map<String, Object> sesion_sga = ActionContext.getContext().getSession();

    public String execute()
    {	
        return SUCCESS;
    }

    public String redirect()
    {
        if(sesion_sga.isEmpty())
        {
            urlRedirect = baseURL+"Login";
        }
        else
        {
            if(sesion_sga.get("ses_estado").toString().equals("A"))
                    urlRedirect = baseURL+"Inicio";
            else
                    urlRedirect = baseURL+"Login";
        }


        return "redirect";
    }
}
