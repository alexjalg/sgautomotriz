<%@taglib uri="/struts-tags" prefix="s" %>
<s:select name="idModMar" id="cbo_Modelo" list="listModelo" listKey="idModMar" listValue="desModMar"
          cssClass="element-form" cssStyle="width:200px;" headerKey="0" headerValue="-Seleccione-"/>

<script type="text/javascript">
    $('#cbo_Modelo').change(function() {
        $('#idModMar_h1').val($(this).val());
        $('#frm_princ').submit();
    });
</script>