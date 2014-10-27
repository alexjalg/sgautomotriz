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
        </div>
    </div>
</div>

<form method="POST" id="frm_cli_back" action="<s:property value="backURL" />">
    <s:hidden name="varReturn" />
</form>

<div class="d-content-form">
    <s:form id="frm_cli" action='javascript:void(0)' theme="simple">
        <table border="0" cellpadding="0" cellspacing="0">
            <tr>
                <td>
                    <table border="0" cellpadding="0" cellspacing="0">
                        <tr>
                            <td colspan="2" style="height: 28px; padding-right: 0px;">
                                <div class="d-block-form">
                                    <div class="text-block">Datos del vehículo</div>
                                    <div class="line-text-block"></div>
                                </div>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <table border="0" cellpadding="0" cellspacing="0" class="table-form">
                                    <tr>
                                        <td style="">
                                            Serie<span class="required">*</span>
                                        </td>
                                        <td>
                                            <s:if test='%{opcion=="A"}'>
                                                <s:textfield id="idVeh" name="idVeh" cssClass="element-form"  cssStyle="width:120px;text-transform: uppercase;" maxLength="10" />
                                            </s:if>
                                            <s:else>
                                                <s:textfield id="idVeh" name="idVeh" cssClass="element-form"  cssStyle="width:120px;text-transform: uppercase;" maxLength="10" disabled="true" />
                                                <s:hidden name="idVeh" />
                                            </s:else>
                                        </td>   
                                    </tr>
                                    <tr>
                                        <td>
                                            Marca<span class="required">*</span>
                                        </td>
                                        <td>
                                            <s:select id="idMar" name="idMar" list="listMarcas" listKey="idMar" listValue="desMar" 
                                                      headerKey="" headerValue="-Seleccione-" cssClass="element-form" cssStyle="width: 130px;" />
                                        </td>
                                    </tr>
                                    <tr>
                                        <td>
                                            Modelo<span class="required">*</span>
                                        </td>
                                        <td class="tdModelos"> 
                                            <s:select name="idModMar" id="idModMar" list="listModelos" listKey="idModMar" listValue="desModMar" 
                                                      headerKey="" headerValue="-Seleccione-" cssClass="element-form" cssStyle="width:150px;" /> 
                                        </td>
                                    </tr>
                                    <tr>
                                        <td>
                                            Versión<span class="required">*</span>
                                        </td>
                                        <td class="tdVersiones" >
                                            <s:select name="idVerMod" id="idVerMod" list="listVersiones" listKey="idVerMod" listValue="desVerMod" 
                                                      headerKey="0" headerValue="-Seleccione-" cssClass="element-form" cssStyle="width:150px;" /> 
                                        </td>
                                    </tr>
                                    <tr>
                                        <td>
                                            Color exterior<span class="required">*</span>
                                        </td>
                                        <td class="tdColExt">
                                            <s:select name="idColExt" id="idColExt" list="listColoExt" listKey="idColExt" listValue="desColExt" 
                                                      headerKey="0" headerValue="-Seleccione-"  cssClass="element-form" cssStyle="width: 220px;" />
                                        </td>
                                    </tr>
                                    <tr>
                                        <td>
                                            Color interior<span class="required">*</span>
                                        </td>
                                        <td>
                                            <s:select name="idColInt" id="idColInt" list="listColorInt" listKey="idColInt" listValue="desColInt" 
                                                      headerKey="0" headerValue="-Seleccione-" cssClass="element-form" cssStyle="width: 220px;" />
                                        </td>
                                    </tr>
                                    <tr>
                                        <td title="Año de fabricación">
                                            Año fab.<span class="required">*</span>
                                        </td>
                                        <td>
                                            <s:if test='%{numAnoFab=="0"}'>
                                            <s:textfield name="numAnoFab" id="numAnoFab" cssClass="element-form" cssStyle="width:60px" maxLength="4"
                                                         onkeypress="return isNumberIntegerKey(event)" value="" />
                                            </s:if>
                                            <s:else>
                                            <s:textfield name="numAnoFab" id="numAnoFab" cssClass="element-form" cssStyle="width:60px" maxLength="4"
                                                         onkeypress="return isNumberIntegerKey(event)" />    
                                            </s:else>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td>
                                            Año Modelo<span class="required">*</span>
                                        </td>
                                        <td>
                                            <s:if test='%{numAnoMod=="0"}'>
                                            <s:textfield name="numAnoMod" id="numAnoMod" cssClass="element-form" cssStyle="width:60px" maxLength="4" 
                                                         onkeypress="return isNumberIntegerKey(event)" value="" />
                                            </s:if>
                                            <s:else>
                                            <s:textfield name="numAnoMod" id="numAnoMod" cssClass="element-form" cssStyle="width:60px" maxLength="4" 
                                                         onkeypress="return isNumberIntegerKey(event)" />
                                            </s:else>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td></td>
                                        <td style="padding-top: 15px;">
                                            <button id="btn_grabar">
                                                Grabar
                                            </button>
                                            <span class="required">(*) campos obligatorios</span>
                                        </td>
                                    </tr>
                                </table>
                            </td>
                            <td style="vertical-align: top; padding-left: 40px;">
                                <table border="0" cellpadding="0" cellspacing="0" class="table-form">  
                                    <tr>
                                        <td>
                                            Nº Chasis<span class="required">*</span>
                                        </td>
                                        <td>
                                            <s:textfield name="desNumCha" id="desNumCha" cssClass="element-form" cssStyle="width: 150px; text-transform: uppercase;" maxlength="20" /> 
                                        </td>
                                    </tr>
                                    <tr>
                                        <td>
                                            Nº Motor<span class="required">*</span>
                                        </td>
                                        <td>
                                            <s:textfield name="desNumMot" id="desNumMot" cssClass="element-form" cssStyle="width:110px" maxLength="15" />
                                        </td>
                                    </tr>
                                    <tr>
                                        <td style="height: 28px;">Precio de venta</td>
                                        <td>
                                            <span id="sp_impprevenasi" style="font-weight: bold;">
                                                <s:property value="impPreVenAsi" />
                                            </span>
                                            <s:hidden name="impPreVenAsi" id="impPrevenAsi" />
                                        </td>
                                    </tr>
                                    <tr>
                                        <td>Kilometraje</td>
                                        <td>
                                            <s:if test='%{numKimAct=="0"}'>
                                            <s:textfield name="numKimAct" id="numKimAct" cssClass="element-form" cssStyle="width:70px" maxLength="6" 
                                                         onkeypress="return isNumberKey(event)" value="" />
                                            </s:if>
                                            <s:else>
                                            <s:textfield name="numKimAct" id="numKimAct" cssClass="element-form" cssStyle="width:70px" maxLength="6" 
                                                     onkeypress="return isNumberKey(event)" />
                                            </s:else>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td>Ambiente</td>
                                        <td>
                                            <s:select name="idAmbUbi" id="idAmbUbi" list="listAmbientes" listKey="idAmbUbi" listValue="desAmbUbi" 
                                                      headerKey="99" headerValue="-Seleccione-"  cssClass="element-form" cssStyle="width: 200px;" /> </td>
                                    </tr>
                                    <tr>
                                        <td>Ubicación</td>
                                        <td class="clsUbic">
                                            <s:select name="idUbiAmb" id="idUbiAmb" list="listUbicaciones" listKey="idUbiAmb" listValue="desUbiAmb" 
                                                      headerKey="99" headerValue="-Seleccione-" cssClass="element-form" cssStyle="width: 200px;" />
                                        </td>
                                    </tr>
                                </table>
                            </td>
                        </tr>
                    </table>
                </td>
                <td style="padding-left: 60px; vertical-align: top;">
                    <table border="0" cellpadding="0" cellspacing="0" class="table-form" >
                        <tr>
                            <td colspan="2" style="height: 28px; padding-right: 0px;">
                                <div class="d-block-form">
                                    <div class="text-block">Datos del proveedor</div>
                                    <div class="line-text-block"></div>
                                </div>
                            </td>
                        </tr>
                        <tr>
                            <td>Poliza de importación</td>
                            <td>
                                <s:textfield name="desPolImp" id="desPolImp" cssClass="element-form" cssStyle="width:180px" maxLength="25" />
                            </td>
                        </tr>
                        <tr>
                            <td>Nº Factura</td>
                            <td>
                                <s:textfield id="desNumFacPrv" name="desNumFacPrv" cssClass="element-form"  cssStyle="width:120px; text-transform: uppercase;" maxLength="20" />
                            </td>
                        </tr>
                        <tr>
                            <td>Fecha Factura</td>
                            <td>
                                <s:textfield name="fecFacPrv" id="fecFacPrv" cssClass="element-form" cssStyle="width: 90px;" maxLength="10" />
                            </td>
                        </tr>
                        <tr>
                            <td>Moneda</td>
                            <td>
                                <s:select name="codMonFacPrv" id="codMonFacPrv" cssClass="element-form" list="#{'1':'Soles','2':'Dolares'}" />
                            </td>
                        </tr>
                        <tr>
                            <td>Tipo de cambio</td>
                            <td>
                                <s:if test='%{impTipCamFP=="0.0000"}'>
                                <s:textfield name="impTipCamFP" id="impTipCamFP" cssClass="element-form" cssStyle="width: 90px;" maxlength="5" 
                                             onkeypress="return isNumberKey(event)" value="" />
                                </s:if>
                                <s:else>
                                <s:textfield name="impTipCamFP" id="impTipCamFP" cssClass="element-form" cssStyle="width: 90px;" maxlength="5" 
                                             onkeypress="return isNumberKey(event)" />
                                </s:else>
                            </td>
                        </tr>
                        <tr>
                            <td title="Importe moneda local">
                                Imp. moneda local
                            </td>
                            <td>
                                <s:if test='%{impMonLoc=="0.00"}'>
                                <s:textfield name="impMonLoc" id="impMonLoc" cssClass="element-form" cssStyle="width: 100px;" maxLength="10" 
                                             onkeypress="return isNumberKey(event)" value="" />
                                </s:if>
                                <s:else>
                                <s:textfield name="impMonLoc" id="impMonLoc" cssClass="element-form" cssStyle="width: 100px;" maxLength="10" 
                                             onkeypress="return isNumberKey(event)" />
                                </s:else>
                            </td>
                        </tr>
                        <tr>
                            <td title="Importe moneda extanjera">
                                Imp. moneda extranjera
                            </td>
                            <td>
                                <s:if test='%{impMonExt=="0.00"}'>
                                <s:textfield name="impMonExt" id="impMonExt" cssClass="element-form" cssStyle="width: 100px;" maxLength="10" 
                                             onkeypress="return isNumberKey(event)" value="" />
                                </s:if>
                                <s:else>
                                <s:textfield name="impMonExt" id="impMonExt" cssClass="element-form" cssStyle="width: 100px;" maxLength="10" 
                                             onkeypress="return isNumberKey(event)" />
                                </s:else>
                            </td>
                        </tr>
                    </table>
                </td>
            </tr>
        </table>
    </s:form>
    <ul>
    <s:iterator value="errores">
        <li><s:property /></li>
    </s:iterator>
    </ul>
</div>
<div id="DIVgrabar" title="<s:property value="titleDialog" />" class="alerta"></div>
<div id="DIVclientes" title="<s:property value="titleDialog" />" class="alerta"></div>                    

<script type="text/javascript">
    $(document).ready(function() {
        //<----- DatePicker's  ------>
        $('#fecSitVeh,#fecFacPrv,#fecSitVen,#fecVenVeh,#fecEntCli').datepicker({
            monthNames: ["Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio", "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre"],
            monthNamesShort: ["Ene", "Feb", "Mar", "Abr", "May", "Jun", "Jul", "Ago", "Sep", "Oct", "Nov", "Dic"],
            dayNamesMin: ["Do", "Lu", "Ma", "Mi", "Ju", "Vi", "Sa"],
            firstDay: 1,
            dateFormat: "dd-mm-yy"
        });

        $('#DIVgrabar').dialog({
            autoOpen: false,
            width: 400,
            height: 200,
            modal: true,
            closeOnEscape: false,
            buttons:{
                "Aceptar": function() {
                    $('#DIVgrabar').dialog("close");
                    $('.overlay').animate({'opacity': '0'}, 250, 'swing', function() {
                        $('.overlay').css({'z-index': '-1'});
                    });
                }
            },
            open: function(event, ui) {
                $(".ui-dialog-titlebar-close").hide();
            },
            draggable: false,
            resizable: false
        });

        $('#DIVclientes').dialog({
            autoOpen: false,
            width: 580,
            height: 500,
            modal: true,
            closeOnEscape: false,
            open: function(event, ui) {
                $(".ui-dialog-titlebar-close").hide();
            },
            draggable: false,
            resizable: false
        });

        $('a.back').click(function() {
            $('#frm_cli_back').submit();
        });

        $('#btn_grabar').click(function() {
            post(
                '<s:property value="formURL" />',
                $('#frm_cli').serialize(),
                function(resultado) {
                    var _error = validaRespuestaAjax(resultado);

                    if (_error != -1)
                    {
                        $('#DIVgrabar').html(resultado);
                        $('#DIVgrabar').dialog('open');
                    }
                    else
                    {
                        $('#frm_cli_back').submit();
                    }
                },
                1
            );
        });
        
        $('#idMar').change(function() {
            $.post(
                'listaModelosVehiculo',
                {
                    idMar: $('#idMar').val()
                }, 
                function(resultado) {
                    $('.tdModelos').html(resultado);
                }
            );
        });

        $('body').delegate('#idModMar','change',function(){
            /*Versiones*/
            $.post(
                'listaVersionesVehiculo.action', 
                {   idMar: $('#idMar').val() ,
                    idModMar: $('#idModMar').val()
                },
                function(resultado){
                    $('.tdVersiones').html(resultado);   
                }
            );

            /* colores exteriores */    
            $.post(
                'listColoresExtVehiculo.action', 
                {   
                    idMar: $('#idMar').val() ,
                    idModMar: $('#idModMar').val()
                },
                function(resultado){
                    $('.tdColExt').html(resultado);   
                }
            );  
        });

        $('body').delegate('#idVerMod','change',function(){
            auxChangeVersionAnioFab();
        });

        $('#idAmbUbi').change(function() {
            $.post(
                'listaUbicacionesVehiculo', 
                {
                    idAmbUbi: $('#idAmbUbi').val()
                }, 
                function(resultado) {
                    $('.clsUbic').html(resultado);
                }
            );
        });

        $('#codMonFacPrv').change(function() {
            if ($(this).val() == '1') {
                $('#impMonLoc').removeAttr('disabled');
                $('#impMonExt').val('');
                $('#impMonExt').attr('disabled', 'disabled');
            } else {
                $('#impMonExt').removeAttr('disabled');
                $('#impMonLoc').val('');
                $('#impMonLoc').attr('disabled', 'disabled');
            }
        });

        $('#codMonFacPrv').change();
        
        $('#numAnoFab').keyup(function(evt) {
            var charCode = (evt.which) ? evt.which : window.event.keyCode
            if (charCode==13) {
                auxChangeVersionAnioFab();
            }
        });
        
        $('#numAnoFab').blur(function(){
            auxChangeVersionAnioFab();
        });
    });
    
    function auxChangeVersionAnioFab() {
        if($('#idMar').val()!='' && $('#idModMar').val()!='' && $('#idVerMod').val()!=0 && $('#numAnoFab').val()!='' && $('#numAnoFab').val()!='0') {
            $.post(
                'obtPrecVentAsiVehiculo',
                {
                    idMar: $('#idMar').val(),
                    idModMar: $('#idModMar').val(),
                    idVerMod: $('#idVerMod').val(),
                    numAnoFab: $('#numAnoFab').val()
                }, 
                function(resultado) {
                    var _error = validaRespuestaAjax(resultado);

                    if (_error != -1)
                    {
                        $('#DIVerroresGen').html(resultado);
                        $('#DIVerroresGen').dialog('open');
                    }
                    else
                    {
                        resultado = resultado.substring(resultado.indexOf('{'),resultado.indexOf('}')+1);
                        var objc=eval("("+resultado+")");
                        $('#sp_impprevenasi').html(objc.impPreVenAsi);
                        $('#impPreVenAsi').val(objc.impPreVenAsi);
                    }

                }
            );
        }
    }
</script>