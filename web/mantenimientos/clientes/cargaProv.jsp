<%@taglib uri="/struts-tags" prefix="s" %>
<s:select id="idProv"  cssClass="element-form" name="idPrvDep" list="listProv" listKey="idPrvDep" listValue="desProv" headerKey="0" theme="simple"
    headerValue="Seleccione" onkeypress="return event.keyCode!=13" cssStyle="width:150px; max-width:150px; margin-right:10px;" />

<script type="text/javascript">
    
     $("#idProv").change(function(){
        var _iddep = $('#idDpto').val();
        var _idprv = $("#idProv").val();
        $('#tdDist').css('text-align','center');
        $('#tdDist').html('<img src="<s:property value="baseURL" />images/ajax-loader.gif" />')
        $.post("populateDistCliente.action", { idDep:_iddep,idPrvDep:_idprv }, 
            function(resultado){
                $('#tdDist').css('text-align','left');
                $('#tdDist').html(resultado);
            }
        )
    });
    
</script>