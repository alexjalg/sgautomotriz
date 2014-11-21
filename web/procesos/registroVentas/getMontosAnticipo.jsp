<%@taglib uri="/struts-tags" prefix="s" %>
<s:if test='%{codMonDocVen==1}'>
{
    'impMonLocVen':'<s:property value="impMonLocVen" />',
    'impMonLocImp':'<s:property value="impMonLocImp" />',
    'impMonLocTot':'<s:property value="impMonLocTot" />',
    'impMonExtVen':'<s:property value="impMonExtVen" />',
    'impMonExtImp':'<s:property value="impMonExtImp" />',
    'impMonExtTot':'<s:property value="impMonExtTot" />'
}    
</s:if>
<s:elseif test='%{codMonDocVen==2}'>
{
    'impMonLocVen':'<s:property value="impMonLocVen" />',
    'impMonLocImp':'<s:property value="impMonLocImp" />',
    'impMonLocTot':'<s:property value="impMonLocTot" />',
    'impMonExtVen':'<s:property value="impMonExtVen" />',
    'impMonExtImp':'<s:property value="impMonExtImp" />',
    'impMonExtTot':'<s:property value="impMonExtTot" />'
}
</s:elseif>