<%@taglib uri="/struts-tags" prefix="s" %>
<s:if test='%{indErrorTot!="" || indError!=""}'>
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