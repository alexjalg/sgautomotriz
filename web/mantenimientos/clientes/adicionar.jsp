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
                            <td>
                                <table border="0" cellpadding="0" cellspacing="0" class="table-form">
                                    <tr>
                                        <td style="width: 104px; height: 28px;">Tipo Cliente</td>
                                        <td>
                                            <div id="d_rbt_tipcli">
                                            <s:if test='%{idCli!=""}'>
                                                <span style="font-weight: bold;"><s:property value="desTipCli" /></span>
                                                <s:hidden name="otrTipCli" />
                                            </s:if>
                                            <s:else>
                                                <s:radio name="otrTipCli" id="otrTipCli" list="listTipCli" listKey="idTipCli" listValue="desTipCli" cssClass="r_tipcli" />
                                            </s:else>
                                            </div>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td>
                                            Tipo Documento<s:if test='%{opcion=="A"}'><span class="required">*</span></s:if>
                                        </td>
                                        <td id="tdTipDoc">
                                            <s:if test='%{idCli!=""}'>
                                                <s:select cssClass="element-form" id="idTipDoc" name="idTipDoc" disabled="true" list="listTipoDoc" listKey="idTipDoc" listValue="desTipDoc"
                                                          onkeypress="return event.keyCode!=13" cssStyle="width:220px;" />
                                            </s:if>
                                            <s:else>
                                                <s:select cssClass="element-form" id="idTipDoc" name="idTipDoc" list="listTipoDoc" listKey="idTipDoc" listValue="desTipDoc"
                                                          onkeypress="return event.keyCode!=13" cssStyle="width:220px;" />
                                            </s:else>
                                        </td>
                                    </tr>
                                </table>
                            </td>
                            <td style="padding-left: 30px;">
                                <table border="0" cellpadding="0" cellspacing="0" class="table-form">
                                    <tr>
                                        <td style="width: 114px;">
                                            Código<s:if test='%{opcion=="A"}'><span class="required">*</span></s:if>
                                        </td>
                                        <td>
                                            <s:if test='%{opcion=="A"}'>
                                                <s:textfield name="idCli" cssClass="element-form" cssStyle="width:150px;" maxlength="15" />
                                            </s:if>
                                            <s:elseif test='%{opcion=="M"}'>
                                                <s:textfield name="idCli" cssClass="element-form" cssStyle="width:150px;" maxLength="15" disabled="true" />
                                                <s:hidden name="idCli" />
                                            </s:elseif>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td>Nombre de cliente<span class="required">*</span></td>
                                        <td>
                                            <s:textfield id="desCli" name="desCli" cssClass="element-form" cssStyle="width: 510px; text-transform: uppercase;" maxLength="70" readonly="true" />
                                        </td>  
                                    </tr>
                                </table>
                            </td>
                        </tr>
                    </table>
                </td>
            </tr>
            <tr>
                <td style="padding-top: 15px;">
                    <table border="0" cellpadding="0" cellspacing="0">
                        <tr>
                            <td colspan="3" style="height: 28px; padding-right: 0px;">
                                <div class="d-block-form">
                                    <div class="text-block">Datos del contacto</div>
                                    <div class="line-text-block"></div>
                                </div>
                            </td>
                        </tr>
                        <tr>
                            <td style="vertical-align: top;">
                                <table border="0" cellpadding="0" cellspacing="0" class="table-form">
                                    <tr>
                                        <td style="">
                                            Apellidos<span class="required">*</span></td>
                                        <td>
                                            <s:textfield name="desApeCon" id="apeCli" cssClass="element-form" style="width:260px; text-transform: uppercase;" 
                                                         onkeypress="return isCharacterKey(event)" maxlength="40" />
                                        </td>
                                    </tr>
                                    <tr>
                                        <td>
                                            Nombres<span class="required">*</span>
                                        </td>
                                        <td>
                                            <s:textfield name="desNomCon" id="nomCli" cssClass="element-form" type="text" style="width:260px; text-transform: uppercase;" 
                                                         onkeypress="return isCharacterKey(event)" maxlength="30" />
                                        </td>
                                    </tr>
                                    <tr>
                                        <td style="height: 24px;">
                                            Género<span class="required">*</span>
                                        </td>
                                        <td>
                                            <div id="d_rbt_gencon">
                                                <s:radio name="otrGenCon" list="listGenero" listKey="idGen" listValue="desGen" onkeypress="return even.keyCode!=13" />
                                            </div>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td>Estado civil</td>
                                        <td>
                                            <s:select name="otrEstCiv" list="listEstCiv" listKey="idEstCiv" listValue="desEstCiv" 
                                                      headerKey="" headerValue="-Seleccione-" cssClass="element-form" />
                                        </td>
                                    </tr>
                                    <tr>
                                        <td>Fec. nacimiento</td>
                                        <td>
                                            <s:textfield name="fecNacCon" id="fecNacCon" cssClass="element-form" cssStyle="width:90px" 
                                                         onkeypress="return isCharacterKey(event)" maxlength="10" />
                                        </td>
                                    </tr>
                                </table>
                            </td>
                            <td style="vertical-align: top; padding-left: 20px;">
                                <table border="0" cellpadding="0" cellspacing="0" class="table-form">
                                    <tr>
                                        <td>Departamento</td>
                                        <td>
                                            <s:select name="idDep" id="idDpto" list="listDpto" listKey="idDep" listValue="desDep" 
                                                      headerKey="0" headerValue="-Seleccione-" cssClass="element-form" cssStyle="width:180px;" />
                                        </td>
                                    </tr>
                                    <tr>
                                        <td style="height: 28px;">Provincia</td>
                                        <td id="tdProv">
                                            <s:select name="idPrvDep" id="idProv" list="listProv" listKey="idPrvDep" listValue="desProv" 
                                                      headerKey="0" headerValue="-Seleccione-" cssClass="element-form" cssStyle="width: 180px;" />
                                        </td>
                                    </tr>
                                    <tr>
                                        <td style="height: 28px;">Distrito</td>
                                        <td id="tdDist">
                                            <s:select name="idDisPrv" id="idDist" list="listDist" listKey="idDisPrv" listValue="desDis" 
                                                      headerKey="0" headerValue="-Seleccione-" cssClass="element-form" cssStyle="width: 180px;" />
                                        </td>
                                    </tr>
                                    <tr>
                                        <td>Telf. Fijo</td>
                                        <td>
                                            <span id="s_codTel1" style="display:block; width:40px; padding-top:4px; font-size: 12px; float: left;">00 - </span>
                                            <s:if test="%{cliente.numTelFij==0}">
                                                <s:textfield name="numTelFij" id="numTelFij" cssClass="element-form" maxLength="7" value="" cssStyle="width: 90px;" onkeypress="return isNumberKey(event)" />
                                            </s:if>
                                            <s:else>
                                                <s:textfield name="numTelFij" id="numTelFij" cssClass="element-form" maxLength="7" cssStyle="width: 90px;" onkeypress="return isNumberKey(event)" />
                                            </s:else>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td style="">Telf. Movil</td>
                                        <td>
                                            <s:if test="%{cliente.numTelMov==0}">
                                                <s:textfield name="numTelMov" id="numTelMov" cssClass="element-form" maxLength="9" value="" cssStyle="width: 110px;" onkeypress="return isNumberKey(event)" />
                                            </s:if>
                                            <s:else>
                                                <s:textfield name="numTelMov" id="numTelMov" cssClass="element-form" maxLength="9" cssStyle="width: 110px;" onkeypress="return isNumberKey(event)" />
                                            </s:else>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td style="">Telf. Oficina</td>
                                        <td>
                                            <span id="s_codTel2" style="display:block; width:40px; padding-top:4px; font-size: 12px; float: left;">00 - </span>
                                            <s:if test="%{cliente.numTelOfi==0}">
                                                <s:textfield name="numTelOfi" id="numTelOfi" cssClass="element-form" maxLength="7" value="" cssStyle="width: 90px;" onkeypress="return isNumberKey(event)" />
                                            </s:if>
                                            <s:else>
                                                <s:textfield name="numTelOfi" id="numTelOfi" cssClass="element-form" maxLength="7" cssStyle="width: 90px;" onkeypress="return isNumberKey(event)" />
                                            </s:else>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td style="">RPM/RPC/Nextel</td>
                                        <td>
                                            <s:textfield name="numTelRP" id="numTelRP" cssClass="element-form" maxLength="10" cssStyle="width: 110px;" onkeypress="return isNumberRPKey(event)" />
                                        </td>
                                    </tr>
                                </table>
                            </td>
                            <td style="vertical-align: top; padding-left: 20px;">
                                <table border="0" cellpadding="0" cellspacing="0" class="table-form">
                                    <tr>
                                        <td style="vertical-align: top; padding-top: 6px;">
                                            Dirección<span class="required">*</span>
                                        </td>
                                        <td>
                                            <s:textarea name="desDirCon" cssClass="element-form" style="width:210px; height:66px;" maxLength="70" />
                                        </td>
                                    </tr>
                                    <tr>
                                        <td>E-mail personal</td>
                                        <td>
                                            <s:textfield name="otrEmaPer" id="otrEmaPer" cssClass="element-form" cssStyle="width:200px;" maxLength="40" />
                                        </td>
                                    </tr>
                                    <tr>
                                        <td>E-mail trabajo</td>
                                        <td>
                                            <s:textfield name="otrEmaTra" id="otrEmaTra" cssClass="element-form" cssStyle="width:200px;" maxLength="40" />
                                        </td>
                                    </tr>
                                    <tr>
                                        <td>Grupo de cliente</td>
                                        <td> 
                                            <s:select name="idGrpCli" id="idGrupCli" list="listGrpCli" listKey="idGrpCli" listValue="desGrpCli"
                                                       cssClass="element-form" cssStyle="width: 170px;" />
                                        </td>
                                    </tr>
                                    <tr>
                                        <td>Forma de pago</td>
                                        <td>
                                            <s:select name="idForPag" id="idForPag" list="listFormaPag" listKey="idForPag" listValue="desForPag"
                                                       cssClass="element-form" cssStyle="width: 140px"  />
                                        </td>
                                    </tr>
                                    <tr>
                                        <td title="Situación del cliente">Sit. Cliente</td>
                                        <td>
                                            <s:select name="codSitCli" id="codSitCli" list="listaSitCli" listKey="idSitCli" listValue="desSitCli"
                                                      cssClass="element-form" />
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
                            <td colspan="2" style="height: 28px; padding-right: 0px;">
                                <div class="d-block-form">
                                    <div class="text-block">Centro Laboral</div>
                                    <div class="line-text-block"></div>
                                </div>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <table border="0" cellpadding="0" cellspacing="0" class="table-form">
                                    <tr>
                                        <td>Empresa</td>
                                        <td>
                                            <s:if test='%{otrTipCli=="J"}'>
                                                <s:textfield id="desNomCenLab" cssClass="element-form" name="desNomCenLab" disabled="true" cssStyle="width: 500px; text-transform: uppercase;" maxLength="60" />
                                            </s:if>
                                            <s:else>
                                                <s:textfield id="desNomCenLab" cssClass="element-form" name="desNomCenLab" cssStyle="width: 500px; text-transform: uppercase;" maxLength="60" />
                                        </s:else>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td>Dirección</td>
                                        <td>
                                            <s:textfield id="desDirCenLab" cssClass="element-form" name="desDirCenLab" cssStyle="width: 500px;" maxLength="60" />
                                        </td>
                                    </tr>
                                        <td>Pagina Web</td>
                                        <td>
                                            <s:textfield name="desPagWeb" id="desPagWeb" cssStyle="width: 300px;" cssClass="element-form" maxLength="60" disabled="" />
                                        </td>
                                    </tr>
                                </table>
                            </td>
                            <td style="vertical-align: top; padding-left: 30px;">
                                <table border="0" cellpadding="0" cellspacing="0" class="table-form">
                                    <tr>
                                        <td>Cargo</td>
                                        <td>
                                            <s:textfield name="desCarCon" id="desCarCon" cssClass="element-form" cssStyle="width: 340px;" maxLength="40" />
                                        </td>

                                    </tr>
                                    <tr>
                                        <td>Área</td>
                                        <td>
                                            <s:textfield name="desAreTra" id="desOcuCon" cssClass="element-form" cssStyle="width: 340px;" maxLength="40" />
                                        </td>
                                    </tr>
                                </table>
                            </td>
                        </tr>
                    </table>
                </td>
            </tr>
            <tr>
                <td style="padding-top: 15px;">
                    <button id="btn_grabar">
                        Grabar
                    </button>
                    <span class="required">(*) campos obligatorios</span>
                </td>
            </tr>
        </table>   
</table>
</s:form>
</div>
<!--<ul>
    <s:iterator value="errores">
        <li><s:property /></li>
    </s:iterator>
</ul>-->
<div id="DIVgrabar" title="<s:property value="titleDialog" />" class="alerta"></div>


<script type="text/javascript">
    $(document).ready(function() {
        resizeContForm();
    
        $('#d_rbt_tipcli,#d_rbt_gencon').buttonset();
        //<----- DatePicker's  ------>
        $('#fecNacCon').datepicker({
            monthNames: ["Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio", "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre"],
            monthNamesShort: ["Ene", "Feb", "Mar", "Abr", "May", "Jun", "Jul", "Ago", "Sep", "Oct", "Nov", "Dic"],
            dayNamesMin: ["Do", "Lu", "Ma", "Mi", "Ju", "Vi", "Sa"],
            firstDay: 1,
            dateFormat: "yy-mm-dd"
        });

        $('#btn_grabar').button();

        $('#DIVgrabar').dialog({
            autoOpen: false,
            width: 400,
            height: 200,
            modal: true,
            closeOnEscape: false,
            buttons:
                    {
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
    });


    $('#idDpto').change(function() {
        var _iddep = $(this).val();

        $.post(
            "populateProvCliente.action", 
            {
                idDep: _iddep
            },
            function(resultado) {
                $('#tdProv').html(resultado);
            }
        );
            
        $.post(
            "populateDistCliente.action", 
            { 
                idDep: _iddep,
                idPrvDep: 0
            }, 
            function(resultado){
                $('#tdDist').html(resultado);
            }
        );
    });
    
    $("body").delegate('#idProv','change',function(){
        var _iddep = $('#idDpto').val();
        var _idprv = $("#idProv").val();
        
        $.post(
            "populateDistCliente.action", 
            { 
                idDep: _iddep,
                idPrvDep: _idprv 
            }, 
            function(resultado){
                $('#tdDist').css('text-align','left');
                $('#tdDist').html(resultado);
            }
        );
    });

    $('#nomCli').blur(function() {
        // alert($('.r_tipcli:checked').val())
        if ($('.r_tipcli:checked').val() == 'N')
        {
            /*if($.trim($('#desCli').val())=="")
             {*/
            $('#desCli').val($.trim($('#apeCli').val()) + ' ' + $.trim($('#nomCli').val()));
            // $('#h_desCli').val($.trim($('#apeCli').val())+' '+$.trim($('#nomCli').val()));
            //}
        }
    });

    $('#apeCli').blur(function() {
        if ($('.r_tipcli:checked').val() == 'N')
        {
            /*if($.trim($('#desCli').val())=="")
             {*/
            $('#desCli').val($.trim($('#apeCli').val()) + ' ' + $.trim($('#nomCli').val()));
            // $('#h_desCli').val($.trim($('#apeCli').val())+' '+$.trim($('#nomCli').val()));
            //}
        }
    });


    function abrir_ventana(url)
    {
        //var opc = "location=0,directories=0,titlebar=0,toolbar=0,location=1,status=0,menubar=0,scrollbars=1,width=" + screen.availWidth + ",height=" + screen.availHeight;
        var opc = "location=0,directories=0,titlebar=0,toolbar=0,location=1,status=0,menubar=0,scrollbars=1,width=300,height=150";
        mywindow = window.open(url, "", opc);
        mywindow.moveTo(100, 100);
        setTimeout('mywindow.close()', 7000);
    }
</script>