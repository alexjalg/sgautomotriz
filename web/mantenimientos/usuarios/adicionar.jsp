<%@taglib uri="/struts-tags" prefix="s" %>
<!-- cabecera de pagina -->
<div class="d-header">
    <div class="d-title-header">
        <s:property value="tituloOpc" />
        <div class="d-subtitle-header"></div>
    </div>
    <div class="d-subheader">
        <div class="d-back">
            <a href="javascript:void(0)" class="back">Volver</a>
        </div>
    </div>
</div>
            
<form method="POST" id="frm_usuario_back" action="<s:property value="backURL" />">
    <s:hidden name="opcion" id="opcion_h1" value="" />
    
    <s:hidden name="curPag" />
    <s:hidden name="idUsu_f" />
    <s:hidden name="desUsu_f" />
    <s:hidden name="edoUsu_f" />
</form>
        
<div class="d-content-form">
    <s:form id="frm_usuario" action='javascript:void(0)' theme="simple">
        <table border="0" cellpadding="0" cellspacing="0" class="table-form">
            <tr>
                <td style="width: 80px;">
                    DNI<s:if test='%{opcion=="A"}'><span class="required">*</span></s:if>
                </td>
                <td style="">
                    <s:if test='%{opcion=="A"}'>
                        <s:textfield name="idUsu" cssClass="element-form" cssStyle="width:100px;" />
                    </s:if>
                    <s:elseif test='%{opcion=="M"}'>
                        <s:textfield name="idUsu" disabled="true" cssClass="element-form" cssStyle="width:100px;" />
                        <s:hidden name="idUsu" />
                    </s:elseif>
                </td>
            </tr>
            <tr>
                <td>Apellidos<span class="required">*</span></td>
                <td>
                    <s:textfield name="desApeUsu" cssClass="element-form" cssStyle="width:300px;" />
                </td>
            </tr>
            <tr>
                <td>Nombres<span class="required">*</span></td>
                <td>
                    <s:textfield name="desNomUsu" cssClass="element-form" cssStyle="width:300px;" />
                </td>
            </tr>
            <tr>
                <td>Contraseña<s:if test='%{opcion=="A"}'><span class="required">*</span></s:if></td>
                <td>
                    <s:password name="otrPwdUsu" cssClass="element-form" cssStyle="width:150px;" />
                </td>
            </tr>
            <tr>
                <td>Género</td>
                <td>
                    <div class="radio">  
                        <s:radio name="otrGenUsu" list="listGeneros" listKey="idGen" listValue="desGen" cssClass="lbl-radio" 
                                 onkeypress="return event.keyCode!=13" /> 
                    </div> 	
                </td>
            </tr>
            <!--<tr>
                <td>etiqueta 08</td>
                <td>
                    <div class="checkbox">
                        <input id="check1" type="checkbox" name="check" value="check1">
                        <label class="lbl-checkbox" for="check1">Checkbox No. 1</label>
                        <input id="check2" type="checkbox" name="check" value="check2">
                        <label class="lbl-checkbox" for="check2">Checkbox No. 2</label>

                        <input id="check3" type="checkbox" name="check" value="check3">
                        <label class="lbl-checkbox" for="check3">Checkbox No. 1</label>
                    </div>
                </td>
            </tr>-->
            <tr>
                <td></td>
                <td style="padding-top: 10px;">
                    <button id="btn_grabar">
                        Grabar
                    </button>
                    <span class="required">(*) campos obligatorios</span>
                </td>
            </tr>
        </table>
    </s:form>
</div>
<div id="DIVgrabar" title="<s:property value="titleDialog" />" class="alerta"></div>
                    
                    
<script type="text/javascript">
    $(document).ready(function(){
        $('#btn_grabar').button();
    
        $('#DIVgrabar').dialog({
            autoOpen: false,
            width: 400,
            height: 200,
            modal: true,
            closeOnEscape: false,
            buttons:
            {
                "Aceptar":function(){
                    $('#DIVgrabar').dialog("close");
                    $('.overlay').animate({'opacity':'0'},250,'swing',function(){
                        $('.overlay').css({'z-index':'-1'});
                    });
                }
            },
            open: function(event, ui) { $(".ui-dialog-titlebar-close").hide(); },
            draggable: false,
            resizable: false
        });
        
        $('a.back').click(function(){
            $('#frm_usuario_back').submit();
        });
        
        $('#btn_grabar').click(function(){
            post(
                '<s:property value="formURL" />',
                $('#frm_usuario').serialize(),
                function(resultado){
                    resultado = $.trim(resultado);
                    var _error = resultado.indexOf("error");
                    if(_error != -1)
                    {
                        $('#DIVgrabar').html(resultado);
                        $('#DIVgrabar').dialog('open');
                    }
                    else
                    {
                        $('#frm_usuario_back').submit();
                    }
                },
                1
            );
        });
        
        $('#btn_imprimir').click(function(){
            abrir_ventana('<s:property value="baseURL" /><s:url action="imprimirUsuario" namespace="usuarios" includeContext="false" />');
        });
    });
    
    function abrir_ventana(url)
    {	
        //var opc = "location=0,directories=0,titlebar=0,toolbar=0,location=1,status=0,menubar=0,scrollbars=1,width=" + screen.availWidth + ",height=" + screen.availHeight;
        var opc = "location=0,directories=0,titlebar=0,toolbar=0,location=1,status=0,menubar=0,scrollbars=1,width=300,height=150";
        mywindow = window.open(url, "", opc);
        mywindow.moveTo(100, 100);
        setTimeout('mywindow.close()',7000);
    }
</script>