<%@taglib uri="/struts-tags" prefix="s" %>
<s:if test='%{indError!=""}'>
    <!--Si en la respuesta del ajax se encuntra la cadena 'error' se visualiza el div conteniendo los errores,
    ademas no se ocultara el div overlay de carga -->
    <div class="ui-state-error ui-corner-all" style="padding: 0 .7em;">
        <span class="ui-icon ui-icon-alert" style="float: left; margin-right: .3em;"></span>
        <ul class="error">
            <s:iterator value="errores">
                <li><s:property /></li> 
                </s:iterator>
        </ul>
    </div>
</s:if>
<s:else>
    <html>
        <head>
            <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">            
            <meta http-equiv="Refresh" content="0;url=<s:property value="formURL" />">                
            <title></title>
        </head>
        <body style="margin: 0px; padding: 0px; color: #404040; font-family: Verdana, Arial, sans-serif; font-size: 11px;">
            Redireccionando...
        </body>
    </html>
</s:else>
   