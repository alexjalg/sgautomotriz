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
                                            N�mero de documento<span class="required">*</span>
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
                                           Fecha de emisi�n<span class="required">*</span>
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
                                            <s:select name="codMonDocVen" id="codMonDocVen" list="#{'0':'-Seleccione-','1':'Soles','2':'D�lares'}"
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
            <tr>
                <td>
                    <table border="0" cellpadding="0" cellspacing="0" class="table-form">
                        <tr>
                            <td style="width: 118px;">
                                Veh�culo<span class="required">*</span>
                            </td>
                            <td>
                                <s:textfield name="idVeh" id="idVeh" cssClass="element-form" cssStyle="width: 90px;"
                                             maxLength="10" onkeypress="return idCharacterNumberKeyWithIntro(event)" />
                                <input type="hidden" id="indIdVeh" value="" />
                                <s:textfield name="desVeh" id="desVeh" cssClass="element-form" cssStyle="width: 350px;" disabled="true" />
                                <s:hidden name="desVeh_h" id="desVeh_hf" />
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
                        <div class="text-block">Importes de anticipo</div>
                        <table border="0" cellpadding="0" cellspacing="0" class="table-form">
                            <tr>
                                <td style="width: 118px;">
                                    Importe venta
                                </td>
                                <td>
                                    <s:textfield name="impMOAntVen" id="impMOAntVen" cssClass="element-form" cssStyle="width: 90px; text-align: right;"
                                                 onkeypress="return isNumberKey(event)" disabled="true" />
                                    <s:hidden name="impMOAntVen" id="impMOAntVen_h" />
                                </td>
                            </tr>
                            <tr>
                                <td style="width: 118px;">
                                    Importe impuestos
                                </td>
                                <td>
                                    <s:textfield name="impMOAntImp" id="impMOAntImp" cssClass="element-form" cssStyle="width: 90px; text-align: right;"
                                                 onkeypress="return isNumberKey(event)" disabled="true" />
                                    <s:hidden name="impMOAntImp" id="impMOAntImp_h" />
                                </td>
                            </tr>
                            <tr>
                                <td style="width: 118px;">
                                    Importe total
                                </td>
                                <td>
                                    <s:textfield name="impMOAntTot" id="impMOAntTot" cssClass="element-form" cssStyle="width: 90px; text-align: right;"
                                                 onkeypress="return isNumberKey(event)" disabled="true" />
                                    <s:hidden name="impMOAntTot" id="impMOAntTot_h" />
                                </td>
                            </tr>
                        </table>
                    </div>
                </td>
                <td style="vertical-align: top;">
                    <div class="d-block-form" style="margin-right: 50px;">
                        <div class="text-block">Importes en soles</div>
                        <table border="0" cellpadding="0" cellspacing="0" class="table-form">
                            <tr>
                                <td style="width: 124px;">
                                    Venta
                                </td>
                                <td>
                                    <s:textfield name="impMonLocVen" id="impMonLocVen" cssClass="element-form" cssStyle="width: 90px; text-align: right;"
                                                 onkeypress="return isNumberKey(event)" disabled="true" />
                                    <s:hidden name="impMonLocVen" id="impMonLocVen_h" />
                                </td>
                            </tr>
                            <tr>
                                <td style="width: 124px;">
                                    Descuento campa�as
                                </td>
                                <td>
                                    <s:textfield name="impMonLocDesCam" id="impMonLocDesCam" cssClass="element-form" cssStyle="width: 90px; text-align: right;"
                                                 onkeypress="return isNumberKey(event)" disabled="true" />
                                    <s:hidden name="impMonLocDesCam" id="impMonLocDesCam_h" />
                                </td>
                            </tr>
                            <tr>
                                <td style="width: 124px;">
                                    Descuento
                                </td>
                                <td>
                                    <s:textfield name="impMonLocDes" id="impMonLocDes" cssClass="element-form" cssStyle="width: 90px; text-align: right;"
                                                 onkeypress="return isNumberKey(event)" disabled="true" />
                                    <s:hidden name="impMonLocDes" id="impMonLocDes_h" />
                                </td>
                            </tr>
                            <tr>
                                <td style="width: 124px;">
                                    Impuestos
                                </td>
                                <td>
                                    <s:textfield name="impMonLocImp" id="impMonLocImp" cssClass="element-form" cssStyle="width: 90px; text-align: right;"
                                                 onkeypress="return isNumberKey(event)" disabled="true" />
                                    <s:hidden name="impMonLocImp" id="impMonLocImp_h" />
                                </td>
                            </tr>
                            <tr>
                                <td style="width: 124px;">
                                    Total
                                </td>
                                <td>
                                    <s:textfield name="impMonLocTot" id="impMonLocTot" cssClass="element-form" cssStyle="width: 90px; text-align: right;"
                                                 onkeypress="return isNumberKey(event)" disabled="true" />
                                    <s:hidden name="impMonLocTot" id="impMonLocTot_h" />
                                </td>
                            </tr>
                            <tr>
                                <td style="width: 124px; border-top: 2px solid #476285;">
                                    Total a pagar
                                </td>
                                <td style="border-top: 2px solid #476285;">
                                    <s:textfield name="impMonLocTotPag" id="impMonLocTotPag" cssClass="element-form" cssStyle="width: 90px; text-align: right;"
                                                 onkeypress="return isNumberKey(event)" disabled="true" />
                                </td>
                            </tr>
                        </table>
                    </div>
                </td>
                <td style="vertical-align: top;">
                    <div class="d-block-form" style="margin-right: 50px;">
                        <div class="text-block">Importes en d�lares</div>
                        <table border="0" cellpadding="0" cellspacing="0" class="table-form">
                            <tr>
                                <td style="width: 124px;">
                                    Venta
                                </td>
                                <td>
                                    <s:textfield name="impMonExtVen" id="impMonExtVen" cssClass="element-form" cssStyle="width: 90px; text-align: right;"
                                                 onkeypress="return isNumberKey(event)" disabled="true" />
                                    <s:hidden name="impMonExtVen" id="impMonExtVen_h" />
                                </td>
                            </tr>
                            <tr>
                                <td style="width: 118px;">
                                    Descuento campa�as
                                </td>
                                <td>
                                    <s:textfield name="impMonExtDesCam" id="impMonExtDesCam" cssClass="element-form" cssStyle="width: 90px; text-align: right;"
                                                 onkeypress="return isNumberKey(event)" disabled="true" />
                                    <s:hidden name="impMonExtDesCam" id="impMonExtDesCam_h" />
                                </td>
                            </tr>
                            <tr>
                                <td style="width: 118px;">
                                    Descuento
                                </td>
                                <td>
                                    <s:textfield name="impMonExtDes" id="impMonExtDes" cssClass="element-form" cssStyle="width: 90px; text-align: right;"
                                                 onkeypress="return isNumberKey(event)" disabled="true" />
                                    <s:hidden name="impMonExtDes" id="impMonExtDes_h" />
                                </td>
                            </tr>
                            <tr>
                                <td style="width: 118px;">
                                    Impuestos
                                </td>
                                <td>
                                    <s:textfield name="impMonExtImp" id="impMonExtImp" cssClass="element-form" cssStyle="width: 90px; text-align: right;"
                                                 onkeypress="return isNumberKey(event)" disabled="true" />
                                    <s:hidden name="impMonExtImp" id="impMonExtImp_h" />
                                </td>
                            </tr>
                            <tr>
                                <td style="width: 118px;">
                                    Total
                                </td>
                                <td>
                                    <s:textfield name="impMonExtTot" id="impMonExtTot" cssClass="element-form" cssStyle="width: 90px; text-align: right;"
                                                 onkeypress="return isNumberKey(event)" disabled="true" />
                                    <s:hidden name="impMonExtTot" id="impMonExtTot_h" />
                                </td>
                            </tr>
                            <tr>
                                <td style="width: 118px; border-top: 2px solid #476285;">
                                    Total a pagar
                                </td>
                                <td style="border-top: 2px solid #476285;">
                                    <s:textfield name="impMonExtTotPag" id="impMonExtTotPag" cssClass="element-form" cssStyle="width: 90px; text-align: right;"
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
                                Observaci�n
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
            height: 270,
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
                                    
                                    if($('#codMonDocVen').val()!=0 && $('#desCli_hf').val()!='') {
                                        alCambiarClienteOTipoMoneda();
                                        
                                        if($('#desVeh_hf').val()!="") {
                                            vehiculoIntroOrBlur();
                                        }
                                    } else {
                                        $('#impMOAntVen').val("0.00");
                                        $('#impMOAntImp').val("0.00");
                                        $('#impMOAntTot').val("0.00");
                                    }
                                    
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
                '<s:property value="baseURL" /><s:url namespace="registroVentas" includeContext="false" action="getTiposDocumentoVentaClienteRegistroVenta" />',
                {
                    idCli:$('#idCli').val()
                },
                function(resultado){
                    resultado = $.trim(resultado);
                    var _error = resultado.indexOf("error");
                    if(_error != -1) {
                        $('#DIVerroresGen').html(resultado);
                        $('#DIVerroresGen').dialog('open');
                    } else {
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
                    
                    if($('#codMonDocVen').val()!=0 && $('#desCli_hf').val()!='') {
                        alCambiarClienteOTipoMoneda();
                    } else {
                        $('#impMOAntVen').val("0.00");
                        $('#impMOAntVen_h').val("0.00");
                        $('#impMOAntImp').val("0.00");
                        $('#impMOAntImp_h').val("0.00");
                        $('#impMOAntTot').val("0.00");
                        $('#impMOAntTot_h').val("0.00");
                    }
                    
                    if($('#codMonDocVen').val()!=0 && $('#desCli_hf').val()!='' && $('#desVeh_hf').val()!="") {
                        vehiculoIntroOrBlur();
                    }
                }
            );
        }
        
        $('#codMonDocVen').change(function(){
            if($(this).val()!=0 && $('#desCli_hf').val()!='') {
                alCambiarClienteOTipoMoneda();
                if($('#desVeh_hf').val()!="") {
                    vehiculoIntroOrBlur();
                }
            } else {
                $('#impMOAntVen').val("0.00");
                $('#impMOAntVen_h').val("0.00");
                $('#impMOAntImp').val("0.00");
                $('#impMOAntImp_h').val("0.00");
                $('#impMOAntTot').val("0.00");
                $('#impMOAntTot_h').val("0.00");
            }
        });
        
        function alCambiarClienteOTipoMoneda(){
            $.post(
                '<s:property value="baseURL" /><s:url namespace="registroVentas" includeContext="false" action="getTotalAnticiposRegistroVenta" />',
                {
                    idCli:$('#idCli').val(),
                    codMonDocVen:$('#codMonDocVen').val()
                },
                function(resultado) {
                    resultado = resultado.substring(resultado.indexOf('{'),resultado.indexOf('}')+1);
                    var objc=eval("("+resultado+")");
                    $('#impMOAntVen').val(objc.impMOAntVen);
                    $('#impMOAntVen_h').val(objc.impMOAntVen);
                    $('#impMOAntImp').val(objc.impMOAntImp);
                    $('#impMOAntImp_h').val(objc.impMOAntImp);
                    $('#impMOAntTot').val(objc.impMOAntTot);
                    $('#impMOAntTot_h').val(objc.impMOAntTot);
                }
            );
        }
        
        $('#idVeh').change(function(){
            $('#indIdVeh').val('C');
        });
        
        $('#idVeh').keypress(function(evt){
            var charCode = (evt.which) ? evt.which : window.event.keyCode;
    
            if(charCode==13) {
                vehiculoIntroOrBlur();
                $('#indIdVeh').val('');
            }
        });
        
        $('#idVeh').blur(function(){
            if($('#indIdVeh').val()!='') {
                vehiculoIntroOrBlur();
                $('#indIdVeh').val('');
            }
        });
        
        function vehiculoIntroOrBlur(){
            $.post(
                '<s:property value="baseURL" /><s:url namespace="registroVentas" includeContext="false" action="getDatosVehiculoRegistroVenta" />',
                {
                    idVeh:$('#idVeh').val(),
                    idCli:$('#idCli').val(),
                    codMonDocVen:$('#codMonDocVen').val()
                },
                function(resultado) {
                    resultado = resultado.substring(resultado.indexOf('{'),resultado.indexOf('}')+1);
                    var objc=eval("("+resultado+")");
                    $('#desVeh').val(objc.desVeh);
                    $('#desVeh_hf').val(objc.desVeh);
                    $('#impMonLocVen').val(objc.impMonLocVen);
                    $('#impMonLocVen_h').val(objc.impMonLocVen);
                    $('#impMonLocDesCam').val(objc.impMonLocDesCam);
                    $('#impMonLocDesCam_h').val(objc.impMonLocDesCam);
                    $('#impMonLocDes').val(objc.impMonLocDes);
                    $('#impMonLocDes_h').val(objc.impMonLocDes);
                    $('#impMonLocImp').val(objc.impMonLocImp);
                    $('#impMonLocImp_h').val(objc.impMonLocImp);
                    $('#impMonLocTot').val(objc.impMonLocTot);
                    $('#impMonLocTot_h').val(objc.impMonLocTot);
                    $('#impMonLocTotPag').val(objc.impMonLocTotPag);
                    $('#impMonExtVen').val(objc.impMonExtVen);
                    $('#impMonExtVen_h').val(objc.impMonExtVen);
                    $('#impMonExtDesCam').val(objc.impMonExtDesCam);
                    $('#impMonExtDesCam_h').val(objc.impMonExtDesCam);
                    $('#impMonExtDes').val(objc.impMonExtDes);
                    $('#impMonExtDes_h').val(objc.impMonExtDes);
                    $('#impMonExtImp').val(objc.impMonExtImp);
                    $('#impMonExtImp_h').val(objc.impMonExtImp);
                    $('#impMonExtTot').val(objc.impMonExtTot);
                    $('#impMonExtTot_h').val(objc.impMonExtTot);
                    $('#impMonExtTotPag').val(objc.impMonExtTotPag);
                }
            );
        }
        
        $('#btn_grabar').click(function(){
            post(
                '<s:property value="formURL" />',
                $('#frm_registroVenta').serialize(),
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