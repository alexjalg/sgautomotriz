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
    <s:hidden name="varReturn" id="varReturn_f" />
</form>
        
<div class="d-content-form">
    <s:form id="frm_usuario" action='javascript:void(0)' theme="simple">
        <table border="0" cellpadding="0" cellspacing="0">
            <tr>
                <td style="vertical-align: top;">
                    <table border="0" cellpadding="0" cellspacing="0" class="table-form">
                        <tr>
                            <td style="width: 100px;">
                                DNI<s:if test='%{opcion=="A"}'><span class="required">*</span></s:if>
                            </td>
                            <td style="">
                                <s:if test='%{opcion=="A"}'>
                                    <s:textfield name="idUsu" cssClass="element-form" cssStyle="width: 90px;" maxLength="10"
                                                 onkeypress="return isNumberIntegerKey(event)" />
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
                                <s:textfield name="desApeUsu" cssClass="element-form" cssStyle="width:300px;" maxLength="40" 
                                             onkeypress="return isCharacterKey(event)" />
                            </td>
                        </tr>
                        <tr>
                            <td>Nombres<span class="required">*</span></td>
                            <td>
                                <s:textfield name="desNomUsu" cssClass="element-form" cssStyle="width:300px;" maxLength="30"
                                             onkeypress="return isCharacterKey(event)"/>
                            </td>
                        </tr>
                        <tr>
                            <td>Contraseña<s:if test='%{opcion=="A"}'><span class="required">*</span></s:if></td>
                            <td>
                                <s:password name="otrClaUsu" cssClass="element-form" cssStyle="width: 140px;" maxLength="15" />
                            </td>
                        </tr>
                        <tr>
                            <td>Tipo de usuario<span class="required">*</span></td>
                            <td>
                                <s:select name="idTipUsu" id="idTipUsu" list="listTipoUsuario" listKey="idTipUsu" listValue="desTipUsu" 
                                          headerKey="0" headerValue="-Seleccione-" cssClass="element-form" 
                                          cssStyle="min-width: 170px; max-width: 170px;" onkeypress="return event.keyCode!=13" /> 
                            </td>
                        </tr>
                        <tr>
                            <td>Concesionario<span class="required">*</span></td>
                            <td>
                                <s:select name="idCon" id="idCon" list="listConcesionarios" listKey="idCon" listValue="desCon" 
                                          headerKey="0" headerValue="-Seleccione-" cssClass="element-form" 
                                          cssStyle="min-width: 170px; max-width: 170px;" onkeypress="return event.keyCode!=13" /> 
                            </td>
                        </tr>
                        <tr>
                            <td>Local<span class="required">*</span></td>
                            <td class="td-locales">
                                <s:select name="idLocCon" id="idLocCon" list="listLocales" listKey="idLocCon" listValue="desLocCon" 
                                          headerKey="0" headerValue="-Seleccione-" cssClass="element-form" 
                                          cssStyle="min-width: 170px; max-width: 170px;" onkeypress="return event.keyCode!=13" /> 
                            </td>
                        </tr>
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
                </td>
                <td style="vertical-align: top; padding-left: 50px;">
                    <table border="0" cellpadding="0" cellspacing="0" class="table-form">
                        <tr>
                            <td colspan="2" style="height: 28px; padding-right: 0px;">
                                <div class="d-block-form">
                                    <div class="text-block">Datos de contacto</div>
                                    <div class="line-text-block"></div>
                                </div>
                            </td>
                        </tr>
                        <tr>
                            <td style="width: 100px;">Teléfono fijo</td>
                            <td>
                                <s:if test='%{numTelFij=="0"}'>
                                    <s:textfield name="numTelFij" cssClass="element-form" cssStyle="width:80px;" maxLength="10"
                                             onkeypress="return isNumberIntegerKey(event)" value="" />
                                </s:if>
                                <s:else>
                                    <s:textfield name="numTelFij" cssClass="element-form" cssStyle="width:80px;" maxLength="10"
                                             onkeypress="return isNumberIntegerKey(event)" />
                                </s:else>
                            </td>
                        </tr>
                        <tr>
                            <td style="width: 100px;">Anexo</td>
                            <td>
                                <s:if test='%{numAnexo=="0"}'>
                                    <s:textfield name="numAnexo" cssClass="element-form" cssStyle="width:40px;" maxLength="5"
                                             onkeypress="return isNumberIntegerKey(event)" value="" />
                                </s:if>
                                <s:else>
                                    <s:textfield name="numAnexo" cssClass="element-form" cssStyle="width:40px;" maxLength="5"
                                             onkeypress="return isNumberIntegerKey(event)" />
                                </s:else>
                            </td>
                        </tr>
                        <tr>
                            <td style="">Teléfonos movil</td>
                            <td>
                                <s:if test='%{numTelMov1=="0"}'>
                                    <s:textfield name="numTelMov1" cssClass="element-form" cssStyle="width: 80px; margin-right: 5px;" maxLength="10"
                                                 onkeypress="return isNumberIntegerKey(event)" value="" />
                                </s:if>
                                <s:else>
                                    <s:textfield name="numTelMov1" cssClass="element-form" cssStyle="width: 80px; margin-right: 5px;" maxLength="10"
                                                 onkeypress="return isNumberIntegerKey(event)" />
                                </s:else>
                                /
                                <s:if test='%{numTelMov2=="0"}'>
                                    <s:textfield name="numTelMov2" cssClass="element-form" cssStyle="width: 80px; margin-left: 5px;" maxLength="10"
                                                 onkeypress="return isNumberIntegerKey(event)" value="" />
                                </s:if>
                                <s:else>
                                    <s:textfield name="numTelMov2" cssClass="element-form" cssStyle="width: 80px; margin-left: 5px;" maxLength="10"
                                             onkeypress="return isNumberIntegerKey(event)" />
                                </s:else>
                            </td>
                        </tr>
                        <tr>
                            <td style="">Redes privadas</td>
                            <td>
                                <s:textfield name="numTelRP1" id="numTelRP1" cssClass="element-form" cssStyle="width: 80px; margin-right: 5px;" maxLength="10" />
                                /
                                <s:textfield name="numTelRP2" id="numTelRP2" cssClass="element-form" cssStyle="width: 80px; margin-left: 5px; margin-right: 5px;" maxLength="10" />
                                /
                                <s:textfield name="numTelRP3" id="numTelRP3" cssClass="element-form" cssStyle="width: 80px; margin-left: 5px;" maxLength="10" />
                            </td>
                        </tr>
                    </table>
                </td>
            </tr>
        </table>
    </s:form>
</div>
<s:iterator value="errores">
    <s:property /><br /> 
</s:iterator>
<div id="DIVgrabar" title="<s:property value="titleDialog" />" class="alerta"></div>
                    
<script type="text/javascript">
    $(document).ready(function(){
        //$('#btn_grabar').button();
        
        if($.trim($('#numTelRP1').val())=='')
            $('#numTelRP1').val('RPM');
        $('#numTelRP1').addClass('focus');
        if($.trim($('#numTelRP2').val())=='')
            $('#numTelRP2').val('RPC');
        $('#numTelRP2').addClass('focus');
        if($.trim($('#numTelRP3').val())=='')
            $('#numTelRP3').val('Nextel');
        $('#numTelRP3').addClass('focus');
        
        $('#numTelRP1').bind('focus',function(){
            if($(this).val()=='RPM'){
                $(this).removeClass('focus');
                $(this).val('');
            }
        }).bind('blur',function(){
            if($(this).val()==""){
                $(this).addClass('focus');
                $(this).val('RPM');
            }
        });
        
        $('#numTelRP2').bind('focus',function(){
            if($(this).val()=='RPC'){
                $(this).removeClass('focus');
                $(this).val('');
            }
        }).bind('blur',function(){
            if($(this).val()==""){
                $(this).addClass('focus');
                $(this).val('RPC');
            }
        });
        
        $('#numTelRP3').bind('focus',function(){
            if($(this).val()=='Nextel'){
                $(this).removeClass('focus');
                $(this).val('');
            }
        }).bind('blur',function(){
            if($(this).val()==""){
                $(this).addClass('focus');
                $(this).val('Nextel');
            }
        });
    
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
        
        $('#idCon').change(function(){
            $.post(
                '<s:property value="baseURL" /><s:url includeContext="false" namespace="usuarios" action="getLocalesUsuario" />',
                {
                    idCon:$('#idCon').val()
                },
                function(resultado){
                    $('.td-locales').html(resultado);
                }
            );
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