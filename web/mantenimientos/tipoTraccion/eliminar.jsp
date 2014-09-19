<%@taglib uri="/struts-tags" prefix="s" %>
<s:if test='%{opcion=="C"}'>
    <div class="ui-state-highlight ui-corner-all" style="margin-top: 5px; padding: 0 .7em;">
        <div>
            <span class="ui-icon ui-icon-info" style="float: left; margin-right: .3em;"></span>
            <div class="both"></div>
        </div>
        <div style="padding: 0px 10px 15px 10px; text-align: center;">
            ¿Desea eliminar el tipo de tracción?
        </div>
    </div>
</s:if>
<s:elseif test='%{opcion=="E"}'>
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
</s:elseif>