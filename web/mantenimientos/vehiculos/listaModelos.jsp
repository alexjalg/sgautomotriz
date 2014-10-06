<%@taglib uri="/struts-tags" prefix="s" %>
<s:select id="idModMar"  cssClass="element-form" name="idModMar" list="listModelos" listKey="idModMar" listValue="desModMar" headerKey="0" theme="simple"
    headerValue="Seleccione" onkeypress="return event.keyCode!=13" cssStyle="width:150px; max-width:150px; margin-right:10px;" />

<script type="text/javascript">
    
     $("#idModMar").change(function(){
//        var _iddep = $('#idDpto').val();
//        var _idprv = $("#idProv").val();
//        $('#tdDist').css('text-align','center');
//        $('#tdDist').html('<img src="<s:property value="baseURL" />images/ajax-loader.gif" />')
        $.post("listaVersionesVehiculo.action", 
        {   idMar: $('#idMar').val() ,
            idModMar: $('#idModMar').val()
        }, 
            function(resultado){
             
              $('.tdVersiones').html(resultado);   
            }
        )
        /* colores exteriores */    
        $.post("listColoresExtVehiculo.action", 
        {   idMar: $('#idMar').val() ,
            idModMar: $('#idModMar').val()
        }, 
            function(resultado){
              $('.tdColExt').html(resultado);   
            }
        )
            
    });
    
</script>