<%@taglib uri="/struts-tags" prefix="s" %>
<s:select id="idVerMod"  cssClass="element-form" name="idVerMod" list="listVersiones" listKey="idVerMod" listValue="desVerMod" headerKey="0" theme="simple"
    headerValue="Seleccione" onkeypress="return event.keyCode!=13" cssStyle="width:150px; max-width:150px; margin-right:10px;" />


<script type="text/javascript">
    
    $('#idVerMod').change(function(){
        
        $.post("obtPrecVentAsiVehiculo",
         {
             idMar:$('#idMar').val(),
             idModMar:$('#idModMar').val(),
             idVerMod:$('#idVerMod').val(),
             numAnoFab:$('#numAnoFab').val()
         }
         ,function(resultado){
             
              var _error = validaRespuestaAjax(resultado);
                    
                    if(_error != -1)
                    {
                        $('#DIVerroresGen').html(resultado);
                        $('#DIVerroresGen').dialog('open');
                    }
                    else
                    {
                        $('#impPreVenAsi').val($.trim(resultado));  
                    }    
             
         })  
        
    });
    
</script>
    