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
            
<form method="POST" id="frm_cli_back" action="<s:property value="backURL" />">
    <s:hidden name="varReturn" />
</form>
        
<div class="d-content-form">
    <s:form id="frm_cli" action='javascript:void(0)' theme="simple">
        <table border="0" cellpadding="0" cellspacing="2" class="table-form" >
            
            <tr>
                <td style="width: 500px" colspan="2">
                    
                    <table border="0" cellpadding="0" cellspacing="0" style="width: 500px"> 
                        <tr>
                
                        <td style="width: 180px"> Tipo Cliente </td>
                        <td style="width: 150px;">Tipo Documento<s:if test='%{opcion=="A"}'><span class="required">*</span></s:if></td>
                        <td style="width: 150px;">
                            Código<s:if test='%{opcion=="A"}'><span class="required">*</span></s:if>
                        </td>

                       </tr>
            
                        <tr>
                            <td>
                              <s:if test='%{idCli!=""}'>
                               <s:radio cssClass="r_tipcli" disabled="true" id="otrTipCli" name="otrTipCli" list="listTipCli" listKey="idTipCli" listValue="desTipCli" onkeypress="return event.keyCode!=13" />
                              </s:if>
                              <s:else>
                                <s:radio cssClass="r_tipcli" id="otrTipCli" name="otrTipCli" list="listTipCli" listKey="idTipCli" listValue="desTipCli" onkeypress="return event.keyCode!=13" />
                              </s:else>    
                            </td>

                            <td id="tdTipDoc" style="padding-right: 20px;">
                                <s:if test='%{idCli!=""}'>
                                    <s:select cssClass="element-form" id="idTipDoc" name="idTipDoc" disabled="true" list="listTipoDoc" listKey="idTipDoc" listValue="desTipDoc"
                                          onkeypress="return event.keyCode!=13" cssStyle="width:150px;" />
                                </s:if>
                                <s:else>
                                    <s:select cssClass="element-form" id="idTipDoc" name="idTipDoc" list="listTipoDoc" listKey="idTipDoc" listValue="desTipDoc"
                                          onkeypress="return event.keyCode!=13" cssStyle="width:150px;" />
                                </s:else>
                            </td>

                            <td style="">
                                <s:if test='%{opcion=="A"}'>
                                    <s:textfield name="idCli" cssClass="element-form" cssStyle="width:150px;" maxlength="15" />
                                </s:if>
                                <s:elseif test='%{opcion=="M"}'>
                                    <s:textfield name="idCli" disabled="true" cssClass="element-form" maxLength="15" cssStyle="width:150px;" />
                                    <s:hidden name="idCli" />
                                </s:elseif>
                            </td>
                        </tr>
                    </table>
                </td>
                
            </tr>

            <tr>
                <td>
                    <table border="0" cellpadding="0" cellspacing="0" >
                      <tr>
                        <td style="width: 50px;">Descripción<span class="required">*</span></td>
                        <td>
                        <s:textfield id="desCli" name="desCli" cssClass="element-form" readonly="true" cssStyle="width:500px;text-transform: uppercase;" maxLength="70" />
                        </td>    
                      </tr>
                      <tr>
                          <td style="padding-top:10px; font-size: 12px; color: #4282B7; font-weight: bold;" colspan="2">Datos del Contacto</td>
                      </tr>
                 </table>
                </td>
            </tr>
            
            <tr>
                <td>
                    <table border="0" cellpadding="0" cellspacing="0">
                        <tr>
                            <td valign="top">
                                <table border="0" cellpadding="0" cellspacing="0">
                                    <tr>
                                        <td style="">Apellidos <span class="required">*</span> </td><td><s:textfield cssClass="element-form" style="width:260px; text-transform: uppercase;" onkeypress="return isCharacterKey(event)" id="apeCli"  maxlength="40" name="desApeCon" /></td>
                                    </tr>
                                    <tr>
                                        <td>Nombres <span class="required">*</span></td><td><s:textfield cssClass="element-form" type="text" style="width:260px; text-transform: uppercase;" onkeypress="return isCharacterKey(event)" id="nomCli" maxlength="30" name="desNomCon" /></td>
                                    </tr>
                                    <tr>
                                        <td style="height: 24px;">
                                           Género <span class="required">*</span>
                                        </td>
                                        <td>
                                           <s:radio name="otrGenCon" list="listGenero" listKey="idGen" listValue="desGen" onkeypress="return even.keyCode!=13" />
                                        </td>
                                    </tr>
                                    <tr>
                                        <td> Est. Civil</td><td><s:select cssClass="element-form" name="otrEstCiv" list="listEstCiv" listKey="idEstCiv" listValue="desEstCiv" headerKey="" headerValue="Seleccione" onkeypress="return even.keyCode!=13" /></td>
                                    </tr>
                                     <tr>
                                         <td>Fec. Nacimiento</td><td><s:textfield cssClass="element-form"   cssStyle="width:90px" onkeypress="return isCharacterKey(event)" id="fecNacCon"  maxlength="10" name="fecNacCon" /></td>
                                    </tr>
                                </table>
                            </td>
                            
                            <td valign="top">
                            <table border="0" cellpadding="0" cellspacing="0" style="width: 100%">
                                    <tr>
                                        <td>Departamento</td>
                                        <td><s:select cssClass="element-form" id="idDpto" name="idDep" list="listDpto" listKey="idDep" listValue="desDep" headerKey="0" 
                                              headerValue="Seleccione" onkeypress="return event.keyCode!=13" cssStyle="width:150px;" /></td>
                                    </tr>
                                    <tr>
                                        <td style="padding-right: 5px; height: 24px;">Provincia</td>
                                        <td id="tdProv" style="width:150px; max-width:150px; max-width:150px; max-height: 24px; min-height: 24px;">

                                            <s:select cssClass="element-form" id="idProv" name="idPrvDep" list="listProv" listKey="idPrvDep" listValue="desProv" headerKey="0"
                                                      headerValue="Seleccione" onkeypress="return event.keyCode!=13" cssStyle="width:150px; max-width:150px;" />
                                        </td>
                                    </tr>
                                    <tr>
                                        <td style="padding-right: 5px; height: 24px;">Distrito</td>
                                        <td id="tdDist" style="width:150px; max-width:150px; min-width:150px; max-height: 24px; min-height: 24px;">
                                            <s:select cssClass="element-form" id="idDist" name="idDisPrv" list="listDist" listKey="idDisPrv" listValue="desDis" headerKey="0"
                                                      headerValue="Seleccione" onkeypress="return event.keyCode!=13" cssStyle="width:250px; max-width:150px;" />
                                        </td>
                                    </tr>
                                    <tr>
                                        <td style="width: 100px;">Telf. Fijo</td>
                                                        <td>
                                                            <span id="s_codTel1" style="display:block; width:40px; padding-top:4px; font-size: 12px; float: left;">00 - </span>
                                                        <s:if test="%{cliente.numTelFij==0}">
                                                            <s:textfield id="numTelFij" cssClass="element-form" name="numTelFij" maxLength="7" value="" cssStyle="width:99px;" onkeypress="return isNumberKey(event)" />
                                                        </s:if>
                                                        <s:else>
                                                            <s:textfield id="numTelFij" cssClass="element-form" name="numTelFij" maxLength="7" cssStyle="width:99px;" onkeypress="return isNumberKey(event)" />
                                                        </s:else>
                                                        </td>
                                    </tr>
                                    <tr>
                                        <td style="">Telf. Movil</td>
                                                        <td>
                                                        <s:if test="%{cliente.numTelMov==0}">
                                                            <s:textfield id="numTelMov" cssClass="element-form" name="numTelMov" maxLength="9" value="" cssStyle="width:139px;" onkeypress="return isNumberKey(event)" />
                                                        </s:if>
                                                        <s:else>
                                                            <s:textfield id="numTelMov" cssClass="element-form" name="numTelMov" maxLength="9" cssStyle="width:139px; " onkeypress="return isNumberKey(event)" />
                                                        </s:else>
                                                        </td>
                                    </tr>
                                    <tr>
                                         <td style="">Telf. Oficina</td>
                                                        <td>
                                                            <span id="s_codTel2" style="display:block; width:40px; padding-top:4px; font-size: 12px; float: left;">00 - </span>
                                                        <s:if test="%{cliente.numTelOfi==0}">
                                                            <s:textfield id="numTelOfi" cssClass="element-form" name="numTelOfi" maxLength="7" value="" cssStyle="width:99px;" onkeypress="return isNumberKey(event)" />
                                                        </s:if>
                                                        <s:else>
                                                            <s:textfield id="numTelOfi" cssClass="element-form" name="numTelOfi" maxLength="7" cssStyle="width:99px;" onkeypress="return isNumberKey(event)" />
                                                        </s:else>
                                                        </td>
                                    </tr>
                                    <tr>
                                        <td style="">RPM/RPC/Nextel</td>
                                                        <td>
                                                            <s:textfield id="numTelRP" cssClass="element-form" name="numTelRP" maxLength="10" cssStyle="width:139px;" onkeypress="return isNumberRPKey(event)" />
                                                        </td>
                                    </tr>
                                    
                                </table>
                            </td>
                            
                            <td valign="top" style="vertical-align: top;">                                
                                    <table border="0" cellpadding="0" cellspacing="0" style="width: 100%;padding-left: 12px">
                                                   <tr>
                                                        <td style="width:82px; padding-left: 3px; vertical-align: top;">
                                                            Direcci&oacute;n <span class="required">*</span>
                                                        </td>
                                                        <td>
                                                            <s:textarea name="desDirCon" maxLength="70" style="width:206px; height:51px;" 
                                                                        cssClass="element-form" onkeypress="return event.keyCode!=13" />
                                                        </td>
                                                    </tr>
                                                    <tr>
                                                        <td>e-mail personal</td>
                                                        <td>
                                                            <s:textfield id="otrEmaPer" cssClass="element-form" name="otrEmaPer" maxLength="40" cssStyle="width:200px;" onkeypress="return event.keyCode!=13" />
                                                        </td>
                                                    </tr>
                                                    <tr>
                                                        <td>e-mail trabajo</td>
                                                        <td>
                                                            <s:textfield id="otrEmaTra" cssClass="element-form" name="otrEmaTra" maxLength="40" cssStyle="width:200px;" onkeypress="return event.keyCode!=13" />
                                                        </td>
                                                    </tr>
                                                    <tr>
                                                        <td>Grp. Cliente</td>
                                                        <td> 
                                                            <s:select id="idGrupCli" cssClass="element-form" name="idGrpCli" list="listGrpCli" listKey="idGrpCli" listValue="desGrpCli" />
                                                        </td>
                                                    </tr>
                                                    <tr>
                                                        <td>Frm. Pago</td>
                                                        <td>
                                                            <s:select id="idForPag" cssClass="element-form" name="idForPag" list="listFormaPag" listKey="idForPag" listValue="desForPag" />
                                                        </td>
                                                    </tr>
                                                    <tr>
                                                        <td>Sit. Cliente</td>
                                                        <td>
                                                            <s:select id="codSitCli" cssClass="element-form" name="codSitCli" list="listaSitCli" listKey="idSitCli" listValue="desSitCli" />
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
                    <table border="0" cellpadding="0" cellspacing="0" style="width: 100%">
                        <tr>
                            <td>
                                <table>
                                    <tr>
                                        <td style="padding-right: 27px">Pagina Web</td>
                                        <td><s:textfield id="desPagWeb" cssClass="element-form" name="desPagWeb" disabled="" cssStyle="text-align:left; padding:0px; padding-left:0px; width:500px;"  maxLength="60" onkeypress="return even.keyCode!=13" />  </td>
                                    </tr>
                                </table>
                            </td>
                        </tr>
                    </table>
                </td>
            </tr>
            
            <tr>
                <td style="padding-top:10px; font-size: 12px; color: #4282B7; font-weight: bold;">Centro Laboral</td>
            </tr>
            
            <tr>
              <td>
                        <table border="0" cellpadding="0" cellspacing="0" style="width: 100%">
                            <tr>
                                <td>
                                    <table border="0" cellpadding="0" cellspacing="0" style="width: 100%">
                                        <tr>
                                            <td style="padding-right: 0px">Empresa</td>
                                            <td>
                                                <s:if test='%{otrTipCli=="J"}'>
                                                    <s:textfield id="desNomCenLab" cssClass="element-form" name="desNomCenLab" disabled="true" cssStyle="text-align:left; padding:0px; padding-left:0px; width:500px; text-transform: uppercase;"   maxLength="60" onkeypress="return even.keyCode!=13" />
                                                    <!s:hidden name="desNomCenLab" id="h_desNomCenTra" />
                                                </s:if>
                                                <s:else>
                                                    <s:textfield id="desNomCenLab" cssClass="element-form" name="desNomCenLab" cssStyle="text-align:left; padding:0px; width:500px; text-transform: uppercase;" maxLength="60" onkeypress="return even.keyCode!=13" />
                                                    <!s:hidden name="desNomCenLab" id="h_desNomCenTra" disabled="true" />
                                                </s:else>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td style="padding-right: 0px">Dirección</td>
                                            <td>
                                                <s:textfield id="desDirCenLab" cssClass="element-form" name="desDirCenLab" cssStyle="width:500px; padding:0px;" maxLength="60" />
                                            </td>
                                        </tr>
                                    </table>
                                </td>
                                <td>
                                    <table border="0" cellpadding="0" cellspacing="0" style="width: 100%">
                                        <tr>
                                            <td>Cargo</td>
                                            <td style="">
                                                <s:textfield cssClass="element-form" id="desCarCon" name="desCarCon" maxLength="40" cssStyle="width:200px;" onkeypress="return event.keyCode!=13" />
                                            </td>

                                        </tr>
                                        <tr>
                                            <td>Área</td>
                                            <td>
                                                <s:textfield cssClass="element-form" id="desOcuCon" name="desAreTra" maxLength="40" cssStyle="width:200px;" onkeypress="return event.keyCode!=13" />
                                            </td>
                                        </tr>
                                    </table>
                                </td>
                            </tr>
                        </table>    
            </tr>
           
            
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
                    
                    
<script type="text/javascript">
    $(document).ready(function(){
        
       //<----- DatePicker's  ------>
        $('#fecNacCon').datepicker({ 
            monthNames: ["Enero","Febrero","Marzo","Abril","Mayo","Junio","Julio","Agosto","Septiembre","Octubre","Noviembre","Diciembre"], 
            monthNamesShort: ["Ene","Feb","Mar","Abr","May","Jun","Jul","Ago","Sep","Oct","Nov","Dic"],
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
            $('#frm_cli_back').submit();
        });
        
        $('#btn_grabar').click(function(){
            post(
                '<s:property value="formURL" />',
                $('#frm_cli').serialize(),
                function(resultado){
                    var _error = validaRespuestaAjax(resultado);
                    
                    if(_error != -1)
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
    
    
    $('#idDpto').change(function(){
        
        var _iddep = $(this).val();
        
        $('#tdProv').css('text-align','center');
        $('#tdProv').html('<img src="<s:property value="baseURL" />images/ajax-loader.gif" />')
        $('#tdDist').css('text-align','center');
        $('#tdDist').html('<img src="<s:property value="baseURL" />images/ajax-loader.gif" />')
        $.post("populateProvCliente.action", { idDep:_iddep }, 
            function(resultado){
                $('#tdProv').css('text-align','left');
                $('#tdProv').html(resultado);
                $('#tdDist').css('text-align','left');
                $('#tdDist').html('<select name="idDisPrv" class="element-form"  style="width:150px; max-width:150px;"><option value="0">Seleccione</option></select>')
                
            }
        )
        
    });
    
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
    $('#nomCli').blur(function(){
       // alert($('.r_tipcli:checked').val())
        if($('.r_tipcli:checked').val()=='N')
        {
            /*if($.trim($('#desCli').val())=="")
            {*/
                $('#desCli').val($.trim($('#apeCli').val())+' '+$.trim($('#nomCli').val()));
               // $('#h_desCli').val($.trim($('#apeCli').val())+' '+$.trim($('#nomCli').val()));
            //}
        }
    });
    
    $('#apeCli').blur(function(){
        if($('.r_tipcli:checked').val()=='N')
        {
            /*if($.trim($('#desCli').val())=="")
            {*/
                $('#desCli').val($.trim($('#apeCli').val())+' '+$.trim($('#nomCli').val()));
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
        setTimeout('mywindow.close()',7000);
    }
</script>