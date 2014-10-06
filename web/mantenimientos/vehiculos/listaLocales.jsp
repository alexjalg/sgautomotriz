<%@taglib uri="/struts-tags" prefix="s" %>
<s:select theme="simple" list="listLocales" id="idLocCon" name="idLocCon" listKey="idLocCon" listValue="desLocCon" cssClass="element-form" headerKey="0" headerValue="Seleccione" />

<script type="text/javascript">
    
//     $("#idProv").change(function(){
//        var _iddep = $('#idDpto').val();
//        var _idprv = $("#idProv").val();
//        $('#tdDist').css('text-align','center');
//        $('#tdDist').html('<img src="<s:property value="baseURL" />images/ajax-loader.gif" />')
//        $.post("populateDistCliente.action", { idDep:_iddep,idPrvDep:_idprv }, 
//            function(resultado){
//                $('#tdDist').css('text-align','left');
//                $('#tdDist').html(resultado);
//            }
//        )
//    });
    
</script>