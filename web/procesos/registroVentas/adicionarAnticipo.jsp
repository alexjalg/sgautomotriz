<%@taglib uri="/struts-tags" prefix="s" %>
<!-- cabecera de pagina -->
<div class="d-header">
    <div class="d-title-header">
        <s:property value="tituloOpc" />
        <div class="d-subtitle-header">
            <s:property value="accion" />
        </div>
    </div>
    <div class="d-subheader">
        <div class="d-back">
            <a href="javascript:void(0)" class="back">Volver</a>
            <form method="POST" id="frm_back" action="<s:property value="backURL" />">
                <s:hidden name="varReturn" />
            </form>
        </div>
    </div>
</div>
        
<div class="d-content-form">
    <s:form id="frm_registroVenta" action='javascript:void(0)' theme="simple">
        <table border="0" cellpadding="0" cellspacing="0">
            <tr>
                <td>
                    <table border="0" cellpadding="0" cellspacing="0" class="table-form">
                        <tr>
                            <td style="width: 118px;">
                                Cliente<span class="required">*</span>
                            </td>
                            <td>
                                <s:textfield name="idCli" id="idCli" cssClass="element-form" cssStyle="width: 110px;"
                                             maxLength="15" onkeypress="return isNumberIntegerKeyWithIntro(event)" />
                                <input type="hidden" id="indIdCli" value="" />
                                <button id="btn_clientes" style="margin-left: 3px; margin-right: 3px;">
                                    ...
                                </button>
                                <s:textfield name="desCli" id="desCli" cssClass="element-form" cssStyle="width: 500px;" disabled="true" />
                                <s:hidden name="desCli_h" id="desCli_hf" />
                            </td>
                        </tr>
                    </table>
                </td>
            </tr>
            <tr>
                <td>
                    <table border="0" cellpadding="0" cellspacing="0">
                        <tr>
                            <td style="width: 300px;">
                                <table border="0" cellpadding="0" cellspacing="0" class="table-form">
                                    <tr>
                                        <td style="width: 118px;">
                                           Tipo de documento<span class="required">*</span>
                                        </td>
                                        <td id="td_tipodocventa">
                                            <s:select name="idTipDocVen" id="idTipDocVen" list="listTiposDocumentoVenta" listKey="idTipDocVen" listValue="desTipDocVen" 
                                                      headerKey="9" headerValue="-Seleccione-" cssClass="element-form" cssStyle="width: 170px;" />
                                        </td>
                                    </tr>
                                </table>
                            </td>
                            <td style="padding-left: 20px;">
                                <table border="0" cellpadding="0" cellspacing="0" class="table-form">
                                    <tr>
                                        <td style="width: 138px;">
                                            Número de documento<span class="required">*</span>
                                        </td>
                                        <td>
                                            <s:textfield name="desNumDocVen" cssClass="element-form" cssStyle="width: 210px;" maxLength="20"
                                                         onkeypress="return isNumberIntegerKey(event)" />
                                        </td>
                                    </tr>
                                </table>
                            </td>
                            <td style="padding-left: 20px;">
                                <table border="0" cellpadding="0" cellspacing="0" class="table-form">
                                    <tr>
                                        <td style="width: 110px;">
                                           Fecha de emisión<span class="required">*</span>
                                        </td>
                                        <td>
                                            <s:textfield name="fecEmiDocVen" id="fecEmiDocVen" cssClass="element-form" cssStyle="width: 80px;" />
                                        </td>
                                    </tr>
                                </table>
                            </td>
                        </tr>
                    </table>
                </td>
            </tr>
            <tr>
                <td>
                    <table border="0" cellpadding="0" cellspacing="0">
                        <tr>
                            <td style="width: 300px;">
                                <table border="0" cellpadding="0" cellspacing="0" class="table-form">
                                    <tr>
                                        <td style="width: 118px;">
                                            Moneda<span class="required">*</span>
                                        </td>
                                        <td>
                                            <s:select name="codMonDocVen" id="codMonDocVen" list="#{'0':'-Seleccione-','1':'Soles','2':'Dólares'}"
                                                      cssClass="element-form" cssStyle="width: 105px;" />
                                        </td>
                                    </tr>
                                </table>
                            </td>
                            <td style="padding-left: 20px;">
                                <table border="0" cellpadding="0" cellspacing="0" class="table-form">
                                    <tr>
                                        <td style="width: 138px;">
                                            Tipo de cambio
                                        </td>
                                        <td>
                                            <s:hidden name="impTipCamVen" />
                                            <span style="font-weight: bold"><s:property value="impTipCamVen" /></span>
                                        </td>
                                    </tr>
                                </table>
                            </td>
                        </tr>
                    </table>
                </td>
            </tr>
        </table>
        <table border="0" cellpadding="0" cellspacing="0" style="margin-top: 10px;">
            <tr>
                <td style="vertical-align: top;">
                    <div class="d-block-form" style="margin-right: 50px;">
                        <div class="text-block">Importes en soles</div>
                        <table border="0" cellpadding="0" cellspacing="0" class="table-form">
                            <tr>
                                <td style="width: 118px;">
                                    Importe venta
                                </td>
                                <td>
                                    <s:textfield name="impMonLocVen" id="impMonLocVen" cssClass="element-form" cssStyle="width: 90px; text-align: right;"
                                                 onkeypress="return isNumberKey(event)" disabled="true" />
                                </td>
                            </tr>
                            <tr>
                                <td style="width: 118px;">
                                    Importe descuento
                                </td>
                                <td>
                                    <s:textfield name="impMonLocDes" id="impMonLocDes" cssClass="element-form" cssStyle="width: 90px; text-align: right;"
                                                 onkeypress="return isNumberKey(event)" disabled="true" />
                                </td>
                            </tr>
                            <tr>
                                <td style="width: 118px;">
                                    Importe impuestos
                                </td>
                                <td>
                                    <s:textfield name="impMonLocImp" id="impMonLocImp" cssClass="element-form" cssStyle="width: 90px; text-align: right;"
                                                 onkeypress="return isNumberKey(event)" disabled="true" />
                                </td>
                            </tr>
                            <tr>
                                <td style="width: 118px;">
                                    Importe total
                                </td>
                                <td>
                                    <s:textfield name="impMonLocTot" id="impMonLocTot" cssClass="element-form" cssStyle="width: 90px; text-align: right;"
                                                 onkeypress="return isNumberKey(event)" disabled="true" />
                                </td>
                            </tr>
                        </table>
                    </div>
                </td>
                <td style="vertical-align: top;">
                    <div class="d-block-form" style="">
                        <div class="text-block">Importes en dólares</div>
                        <table border="0" cellpadding="0" cellspacing="0" class="table-form">
                            <tr>
                                <td style="width: 118px;">
                                    Importe venta
                                </td>
                                <td>
                                    <s:textfield name="impMonExtVen" id="impMonExtVen" cssClass="element-form" cssStyle="width: 90px; text-align: right;"
                                                 onkeypress="return isNumberKey(event)" disabled="true" />
                                </td>
                            </tr>
                            <tr>
                                <td style="width: 118px;">
                                    Importe descuento
                                </td>
                                <td>
                                    <s:textfield name="impMonExtDes" id="impMonExtDes" cssClass="element-form" cssStyle="width: 90px; text-align: right;"
                                                 onkeypress="return isNumberKey(event)" disabled="true" />
                                </td>
                            </tr>
                            <tr>
                                <td style="width: 118px;">
                                    Importe impuestos
                                </td>
                                <td>
                                    <s:textfield name="impMonExtImp" id="impMonExtImp" cssClass="element-form" cssStyle="width: 90px; text-align: right;"
                                                 onkeypress="return isNumberKey(event)" disabled="true" />
                                </td>
                            </tr>
                            <tr>
                                <td style="width: 118px;">
                                    Importe total
                                </td>
                                <td>
                                    <s:textfield name="impMonExtTot" id="impMonExtTot" cssClass="element-form" cssStyle="width: 90px; text-align: right;"
                                                 onkeypress="return isNumberKey(event)" disabled="true" />
                                </td>
                            </tr>
                        </table>
                    </div>
                </td>
            </tr>
        </table>
        <table border="0" cellpadding="0" cellspacing="0" style="margin-top: 15px;">
            <tr>
                <td style="vertical-align: top; width: 300px;">
                    <table border="0" cellpadding="0" cellspacing="0" class="table-form">
                        <tr>
                            <td style="width: 118px;">
                                Forma de pago <span class="required">*</span>
                            </td>
                            <td>
                                <s:select name="idForPag" list="listFormasPago" listKey="idForPag" listValue="desForPag"
                                          headerKey="" headerValue="-Seleccione-" cssClass="element-form" cssStyle="width: 130px;" /> 
                            </td>
                        </tr>
                    </table>
                </td>
                <td>
                    <table border="0" cellpadding="0" cellspacing="0" class="table-form">
                        <tr>
                            <td style="vertical-align: top; padding-top: 6px;">
                                Observación
                            </td>
                            <td>
                                <s:textarea name="desObsDoc" cssClass="element-form" cssStyle="width: 400px; height: 70px;" />
                            </td>
                        </tr>
                    </table>
                </td>
            </tr>
        </table>
        <table border="0" cellpadding="0" cellspacing="0" class="table-form">
            <tr>
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
<div id="DIVclientes" title="<s:property value="titleDialog" />" class="alerta"></div>
                    
                    
<script type="text/javascript">
    $(document).ready(function(){
        $('#fecEmiDocVen').datepicker();
        
        $('#DIVgrabar').dialog({
            autoOpen: false,
            width: 400,
            height: 240,
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
            $('#frm_back').submit();
        });
        
        $('#DIVclientes').dialog({
            autoOpen: false,
            width: 800,
            height: 500,
            modal: true,
            closeOnEscape: false,
            open: function(event, ui) { $(".ui-dialog-titlebar-close").hide(); },
            draggable: false,
            resizable: false
        });
        
        $('#btn_clientes').click(function(){
            post(
                '<s:property value="baseURL" /><s:url namespace="registroVentas" includeContext="false" action="listClientesRegistroVenta" />',
                {},
                function(resultado){
                    var _error = validaRespuestaAjax(resultado);
                    
                    if(_error != -1)
                    {
                        $('#DIVerroresGen').html(resultado);
                        $('#DIVerroresGen').dialog('open');
                    }
                    else
                    {
                        $('#DIVclientes').dialog({
                            buttons:{
                                "Aceptar":function(){
                                    $('#idCli').val($('.select_rec:checked').val());
                                    $('#desCli').val($.trim($('.select_rec:checked').parent().next().next().text()));
                                    $('#desCli_hf').val($.trim($('.select_rec:checked').parent().next().next().text()));
                                    $('#DIVclientes').dialog("close");
                                    if($('#idCli').val()!='') {
                                        alCambiarCliente();
                                    }
                                    hideOverlay(function(){});
                                },
                                "Cancelar":function(){
                                    $('#DIVclientes').dialog("close");
                                    hideOverlay(function(){});
                                }
                            }
                        });
                        $('#DIVclientes').html(resultado);
                        $('#DIVclientes').dialog('open');
                    }
                    $('#indIdCli').val('');
                },
                2       
            );
        });
        
        function alCambiarCliente() {
            $.post(
                '<s:property value="baseURL" /><s:url namespace="registroVentas" includeContext="false" action="getTiposDocumentoVentaClienteAnticipoRegistroVenta" />',
                {
                    idCli:$('#idCli').val()
                },
                function(resultado){
                    resultado = $.trim(resultado);
                    var _error = resultado.indexOf("error");
                    if(_error != -1)
                    {
                        $('#DIVerroresGen').html(resultado);
                        $('#DIVerroresGen').dialog('open');
                    }
                    else
                    {
                        $('#td_tipodocventa').html(resultado);
                    }
                }
            );
        }
        
        $('#idCli').change(function(){
            $('#indIdCli').val('C');
        });
        
        $('#idCli').keypress(function(evt){
            var charCode = (evt.which) ? evt.which : window.event.keyCode;
    
            if(charCode==13) {
                clienteIntroOrBlur();
                alCambiarCliente();
                $('#indIdCli').val('');
            }
        });
        
        $('#idCli').blur(function(){
            if($('#indIdCli').val()!='') {
                clienteIntroOrBlur();
                alCambiarCliente();
                $('#indIdCli').val('');
            }
        });
        
        function clienteIntroOrBlur(){
            $.post(
                '<s:property value="baseURL" /><s:url namespace="registroVentas" includeContext="false" action="getDatosClienteRegistroVenta" />',
                {
                    idCli:$('#idCli').val()
                },
                function(resultado) {
                    resultado = resultado.substring(resultado.indexOf('{'),resultado.indexOf('}')+1);
                    var objc=eval("("+resultado+")");
                    $('#desCli').val(objc.desCli);
                    $('#desCli_hf').val(objc.desCli);
                }
            );
        }
        
        $('#codMonDocVen').change(function(){
            if($(this).val() == 1) {
                $('#impMonLocTot').removeAttr('disabled');
                $('#impMonExtTot').attr('disabled',true);
            } else if($(this).val() == 2) {
                $('#impMonExtTot').removeAttr('disabled');
                $('#impMonLocTot').attr('disabled',true);
            } else {
                $('#impMonLocTot').attr('disabled',true);
                $('#impMonExtTot').attr('disabled',true);
            }
        });
        
        $('#impMonLocTot').keypress(function(evt){
            var charCode = (evt.which) ? evt.which : window.event.keyCode;
    
            if(charCode==13) {
                montoSolesIntroOrBlur();
            }
        });
        
        $('#impMonLocTot').blur(function(){
            montoSolesIntroOrBlur();
        });
        
        function montoSolesIntroOrBlur(){
            $.post(
                '<s:property value="baseURL" /><s:url namespace="registroVentas" includeContext="false" action="getMontosAnticipoRegistroVenta" />',
                {
                    codMonDocVen:$('#codMonDocVen').val(),
                    impMonLocTot:$('#impMonLocTot').val()
                },
                function(resultado) {
                    resultado = resultado.substring(resultado.indexOf('{'),resultado.indexOf('}')+1);
                    var objc=eval("("+resultado+")");
                    $('#impMonLocVen').val(objc.impMonLocVen);
                    $('#impMonLocImp').val(objc.impMonLocImp);
                    $('#impMonLocTot').val(objc.impMonLocTot);
                    $('#impMonExtVen').val(objc.impMonExtVen);
                    $('#impMonExtImp').val(objc.impMonExtImp);
                    $('#impMonExtTot').val(objc.impMonExtTot);
                }
            );
        }
        
        $('#impMonExtTot').keypress(function(evt){
            var charCode = (evt.which) ? evt.which : window.event.keyCode;
    
            if(charCode==13) {
                montoDolaresIntroOrBlur();
            }
        });
        
        $('#impMonExtTot').blur(function(){
            montoDolaresIntroOrBlur();
        });
        
        function montoDolaresIntroOrBlur(){
            $.post(
                '<s:property value="baseURL" /><s:url namespace="registroVentas" includeContext="false" action="getMontosAnticipoRegistroVenta" />',
                {
                    codMonDocVen:$('#codMonDocVen').val(),
                    impMonExtTot:$('#impMonExtTot').val()
                },
                function(resultado) {
                    resultado = resultado.substring(resultado.indexOf('{'),resultado.indexOf('}')+1);
                    var objc=eval("("+resultado+")");
                    $('#impMonExtVen').val(objc.impMonExtVen);
                    $('#impMonExtImp').val(objc.impMonExtImp);
                    $('#impMonExtTot').val(objc.impMonExtTot);
                    $('#impMonLocVen').val(objc.impMonLocVen);
                    $('#impMonLocImp').val(objc.impMonLocImp);
                    $('#impMonLocTot').val(objc.impMonLocTot);
                }
            );
        }
        
        $('#btn_grabar').click(function(){
            $('#btn_grabar').attr('disabled',true);
            post(
                '<s:property value="formURL" />',
                $('#frm_registroVenta').serialize(),
                function(resultado){
                    resultado = $.trim(resultado);
                    var _error = resultado.indexOf("error");
                    if(_error != -1) {
                        $('#DIVgrabar').html(resultado);
                        $('#DIVgrabar').dialog('open');
                        $('#btn_grabar').removeAttr('disabled');
                    } else {
                        $('#frm_back').submit();
                    }
                },
                1
            );
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