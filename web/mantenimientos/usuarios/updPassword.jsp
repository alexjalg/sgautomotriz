<%@taglib uri="/struts-tags" prefix="s" %>
<s:if test='%{opcion=="F"}'>
<div class="d-content-form">
    <s:form action="javascript:void(0)" id="frmUpdPassword" theme="simple">
        <s:hidden name="opcion" id="opcion_h2" />
        <table border="0" cellpadding="0" callspacing="0" style="width:100%;" class="table-form">
            <s:if test='%{#session.ses_indclares==""}'>
            <tr>
                <td style="width: 140px;">Contraseña actual</td>
                <td>
                    <s:password name="otrClaUsu" id="otrClaUsu" cssClass="element-form"
                                cssStyle="width: 130px;" />
                </td>
            </tr>
            </s:if>
            <tr>
                <td style="">Nueva contraseña</td>
                <td>
                    <s:password name="otrNueClaUsu" id="otrNueClaUsu" cssClass="element-form"
                                cssStyle="width: 130px;" />
                </td>
            </tr>
            <tr>
                <td style="">Repita nueva contraseña</td>
                <td>
                    <s:password name="otrNueClaUsu2" id="otrNueClaUsu2" cssClass="element-form"
                                cssStyle="width: 130px;" />
                </td>
            </tr>
        </table>
    </s:form>
</div>
</s:if>
<s:if test='%{indError!=""}'>
    <div class="ui-state-error ui-corner-all" style="padding: 0 .7em;">
        <span class="ui-icon ui-icon-alert" style="float: left; margin-right: .3em;"></span>
        <ul class="error">
            <s:iterator value="errores">
                <li><s:property /></li> 
            </s:iterator>
        </ul>
    </div>
</s:if>