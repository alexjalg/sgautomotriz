<%@taglib uri="/struts-tags" prefix="s" %>
<ul>
    <s:iterator value="errores">
        <li><s:property /></li>
    </s:iterator>
</ul>
{
    'desVeh':'<s:property value="desVeh" />',
    'impMonExtVen':'<s:property value="impMonExtVen" />',
    'impMonExtDesCam':'<s:property value="impMonExtDesCam" />',
    'impMonExtDes':'<s:property value="impMonExtDes" />',
    'impMonExtImp':'<s:property value="impMonExtImp" />',
    'impMonExtTot':'<s:property value="impMonExtTot" />',
    'impMonLocVen':'<s:property value="impMonLocVen" />',
    'impMonLocDesCam':'<s:property value="impMonLocDesCam" />',
    'impMonLocDes':'<s:property value="impMonLocDes" />',
    'impMonLocImp':'<s:property value="impMonLocImp" />',
    'impMonLocTot':'<s:property value="impMonLocTot" />',
    'impMonExtTotPag':'<s:property value="impMonExtTotPag" />',
    'impMonLocTotPag':'<s:property value="impMonLocTotPag" />'
}