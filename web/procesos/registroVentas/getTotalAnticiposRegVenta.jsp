<%@taglib uri="/struts-tags" prefix="s" %>
<s:if test='%{codMonDocVen==1}'>
{
    'impMOAntVen':'<s:property value="anticipoRegistroVentas.impDisMonLocVen" />',
    'impMOAntImp':'<s:property value="anticipoRegistroVentas.impDisMonLocImp" />',
    'impMOAntTot':'<s:property value="anticipoRegistroVentas.impDisMonLocTot" />'
}
</s:if>
<s:elseif test='%{codMonDocVen==2}'>
{
    'impMOAntVen':'<s:property value="anticipoRegistroVentas.impDisMonExtVen" />',
    'impMOAntImp':'<s:property value="anticipoRegistroVentas.impDisMonExtImp" />',
    'impMOAntTot':'<s:property value="anticipoRegistroVentas.impDisMonExtTot" />'
}    
</s:elseif>
 