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
                            <td style="width: 118px;">Cliente</td>
                            <td>
                                <span style="font-weight: bold;">
                                    <s:property value="idCli" /> - <s:property value="desCli" />
                                </span>
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
                                        <td style="width: 118px;">Tipo de documento</td>
                                        <td>
                                            <span style="font-weight: bold;">
                                                <s:property value="desTipDocVen" />
                                            </span>
                                        </td>
                                    </tr>
                                </table>
                            </td>
                            <td style="width: 300px; padding-left: 20px;">
                                <table border="0" cellpadding="0" cellspacing="0" class="table-form">
                                    <tr>
                                        <td style="width: 138px;">Número de documento</td>
                                        <td>
                                            <span style="font-weight: bold;">
                                                <s:property value="desNumDocVen" />
                                            </span>
                                        </td>
                                    </tr>
                                </table>
                            </td>
                            <td style="padding-left: 20px;">
                                <table border="0" cellpadding="0" cellspacing="0" class="table-form">
                                    <tr>
                                        <td style="width: 110px;">Fecha de emisión</td>
                                        <td>
                                            <span style="font-weight: bold;">
                                                <s:property value="fecEmiDocVen" />
                                            </span>
                                        </td>
                                    </tr>
                                </table>
                            </td>
                        </tr>
                    </table>
                    <table border="0" cellpadding="0" cellspacing="0">
                        <tr>
                            <td style="width: 300px;">
                                <table border="0" cellpadding="0" cellspacing="0" class="table-form">
                                    <tr>
                                        <td style="width: 118px;">Moneda</td>
                                        <td>
                                            <span style="font-weight: bold;">
                                                <s:property value="desMonDocVen" />
                                            </span>
                                        </td>
                                    </tr>
                                </table>
                            </td>
                            <td style="width: 300px; padding-left: 20px;">
                                <table border="0" cellpadding="0" cellspacing="0" class="table-form">
                                    <tr>
                                        <td style="width: 138px;">Tipo de cambio</td>
                                        <td>
                                            <span style="font-weight: bold">
                                                <s:property value="impTipCamVen" />
                                            </span>
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
                                Vehículo
                            </td>
                            <td>
                                <span style="font-weight: bold;">
                                    <s:property value="idVeh" /> - <s:property value="desVeh" />
                                </span>
                            </td>
                        </tr>
                    </table>
                </td>
            </tr>
        </table>
        <table border="0" cellpadding="0" cellspacing="0" style="margin-top: 10px;">
            <tr>
                <td style="vertical-align: top; width:300px;">
                    <table border="0" cellpadding="0" cellspacing="0" class="table-form">
                        <tr>
                            <td colspan="2" style="height: 26px; padding-right: 65px;">
                                <div class="d-block-form">
                                    <div class="text-block">Importes de anticipo</div>
                                    <div class="line-text-block"></div>
                                </div>
                            </td>
                        </tr>
                        <tr>
                            <td style="width: 118px;">Importe venta</td>
                            <td>
                                <span style="font-weight: bold;">
                                    <s:property value="impMOAntVen" />
                                </span>
                            </td>
                        </tr>
                        <tr>
                            <td style="width: 118px;">Importe impuestos</td>
                            <td>
                                <span style="font-weight: bold;">
                                    <s:property value="impMOAntImp" />
                                </span>
                            </td>
                        </tr>
                        <tr>
                            <td style="width: 118px;">Importe total</td>
                            <td>
                                <span style="font-weight: bold;">
                                    <s:property value="impMOAntTot" />
                                </span>
                            </td>
                        </tr>
                    </table>
                </td>
                <td style="vertical-align: top; width:300px;">
                    <table border="0" cellpadding="0" cellspacing="0" class="table-form">
                        <tr>
                            <td colspan="2" style="height: 26px; padding-right: 65px;">
                                <div class="d-block-form">
                                    <div class="text-block">Importes en soles</div>
                                    <div class="line-text-block"></div>
                                </div>
                            </td>
                        </tr>
                        <tr>
                            <td style="width: 118px;">Importe venta</td>
                            <td>
                                <span style="font-weight: bold;">
                                    <s:property value="impMonLocVen" />
                                </span>
                            </td>
                        </tr>
                        <tr>
                            <td style="width: 118px;">Importe descuento</td>
                            <td>
                                <span style="font-weight: bold;">
                                    <s:property value="impMonLocDes" />
                                </span>
                            </td>
                        </tr>
                        <tr>
                            <td style="width: 118px;">Importe impuestos</td>
                            <td>
                                <span style="font-weight: bold;">
                                    <s:property value="impMonLocImp" />
                                </span>
                            </td>
                        </tr>
                        <tr>
                            <td style="width: 118px;">Importe total</td>
                            <td>
                                <span style="font-weight: bold;">
                                    <s:property value="impMonLocTot" />
                                </span>
                            </td>
                        </tr>
                    </table>
                </td>
                <td style="vertical-align: top; width:300px;">
                    <table border="0" cellpadding="0" cellspacing="0" class="table-form">
                        <tr>
                            <td colspan="2" style="height: 26px; padding-right: 65px;">
                                <div class="d-block-form">
                                    <div class="text-block">Importes en dólares</div>
                                    <div class="line-text-block"></div>
                                </div>
                            </td>
                        </tr>
                        <tr>
                            <td style="width: 118px;">Importe venta</td>
                            <td>
                                <span style="font-weight: bold;">
                                    <s:property value="impMonExtVen" />
                                </span>
                            </td>
                        </tr>
                        <tr>
                            <td style="width: 118px;">Importe descuento</td>
                            <td>
                                <span style="font-weight: bold;">
                                    <s:property value="impMonExtDes" />
                                </span>
                            </td>
                        </tr>
                        <tr>
                            <td style="width: 118px;">Importe impuestos</td>
                            <td>
                                <span style="font-weight: bold;">
                                    <s:property value="impMonExtImp" />
                                </span>
                            </td>
                        </tr>
                        <tr>
                            <td style="width: 118px;">Importe total</td>
                            <td>
                                <span style="font-weight: bold;">
                                    <s:property value="impMonExtTot" />
                                </span>
                            </td>
                        </tr>
                    </table>
                </td>
            </tr>
        </table>
        <table border="0" cellpadding="0" cellspacing="0" style="margin-top: 15px;">
            <tr>
                <td style="vertical-align: top; width: 300px;">
                    <table border="0" cellpadding="0" cellspacing="0" class="table-form">
                        <tr>
                            <td style="width: 118px;">Forma de pago</td>
                            <td>
                                <span style="font-weight: bold;">
                                    <s:property value="desForPag" />
                                </span>
                            </td>
                        </tr>
                    </table>
                </td>
                <td>
                    <table border="0" cellpadding="0" cellspacing="0" class="table-form">
                        <tr>
                            <td style="vertical-align: top; padding-top: 6px;">Observación</td>
                            <td>
                                <span style="font-weight: bold;">
                                    <span style="font-weight: bold; display: inline-block; width: 400px; height: 70px; margin-top: 6px; margin-left: 10px;">
                                        <s:property value="desObsDoc" />
                                    </span>
                                </span>
                            </td>
                        </tr>
                    </table>
                </td>
            </tr>
        </table>
    </s:form>
</div>
<div id="DIVgrabar" title="<s:property value="titleDialog" />" class="alerta"></div>
<div id="DIVclientes" title="<s:property value="titleDialog" />" class="alerta"></div>
                    
                    
<script type="text/javascript">
    $(document).ready(function(){
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
                '<s:property value="baseURL" /><s:url namespace="registroVentas" includeContext="false" action="getTiposDocumentoVentaClienteRegistroVenta" />',
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
                    idVeh:$('#idVeh').val()
                },
                function(resultado) {
                    resultado = resultado.substring(resultado.indexOf('{'),resultado.indexOf('}')+1);
                    var objc=eval("("+resultado+")");
                    $('#desVeh').val(objc.desVeh);
                    $('#desVeh_hf').val(objc.desVeh);
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