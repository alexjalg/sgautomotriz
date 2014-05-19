<%@taglib uri="/struts-tags" prefix="s" %>
<s:select name="idLocCon" id="idLocCon" list="listLocales" listKey="idLocCon" listValue="desLocCon" 
          headerKey="0" headerValue="-Seleccione-" cssClass="element-form" 
          cssStyle="min-width: 170px; max-width: 170px;" onkeypress="return event.keyCode!=13" theme="simple" /> 