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

<form method="POST" id="frm_veh_back" action="<s:property value="backURL" />">
    <s:hidden name="varReturn" />
</form>
<style>
    .cls_quit{
        cursor: pointer;
        color: blue;
    }
</style>

<s:form id="frmVehiculo" action='javascript:void(0)' method="post" theme="simple">
    <s:hidden name="idMar" id="idMar" />
    <s:hidden name="idModMar" id="idModMar" />
    <s:hidden name="idVerMod" id="idVerMod" />
    <s:hidden name="numAnoFab" id="numAnoFab" />
    <s:hidden name="codSitVen" />
<div class="d-content-form">
    <table border="0" cellpadding="0" cellspacing="0" style="margin-bottom: 20px;">
        <tr>
            <td>
                <div class="d-block-form">
                    <div class="text-block">Datos del vehículo</div>
                    <table border="0" cellpadding="0" cellspacing="0">
                        <tr>
                            <td>
                                <table border="0" cellpadding="0" cellspacing="0" class="table-form">
                                    <tr>
                                        <td style="width: 108px;">Serie:</td>
                                        <td>
                                            <span style="font-weight: bold;">
                                                <s:hidden name="idVeh" id="idVeh" />
                                                <s:property value="idVeh" />
                                            </span>
                                        </td> 
                                    </tr>
                                    <tr>
                                        <td>Marca:</td>
                                        <td>
                                            <span style="font-weight: bold;">
                                                <s:property value="desMar" />
                                            </span>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td>Modelo:</td>
                                        <td> 
                                            <span style="font-weight: bold;">
                                                <s:property value="desModMar" />
                                            </span>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td>Versión:</td>
                                        <td>
                                            <span style="font-weight: bold;">
                                                <s:property value="desVerMod" />
                                            </span>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td>Color exterior:</td>
                                        <td>
                                            <span style="font-weight: bold;">
                                                <s:property value="desColExt" />
                                            </span>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td>Color interior:</td>
                                        <td style="width: 250px;">
                                            <span style="font-weight: bold;">
                                                <s:property value="desColInt" />
                                            </span>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td>Año de fabricación:</td>
                                        <td>
                                            <span style="font-weight: bold;">
                                                <s:property value="numAnoFab" />
                                            </span>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td>Año Modelo:</td>
                                        <td>
                                            <span style="font-weight: bold;">
                                                <s:property value="numAnoMod" />
                                            </span>
                                        </td>
                                    </tr>
                                </table>
                            </td>
                            <td style="vertical-align: top; padding-left: 50px;">
                                <table border="0" cellpadding="0" cellspacing="0" class="table-form">  
                                    <tr>
                                        <td style="width: 110px;">Nº Chasis:</td>
                                        <td>
                                            <span style="font-weight: bold;">
                                                <s:property value="desNumCha" />
                                            </span>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td>Nº Motor:</td>
                                        <td>
                                            <span style="font-weight: bold;">
                                                <s:property value="desNumMot" />
                                            </span>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td>Precio de venta:</td>
                                        <td>
                                            <span style="font-weight: bold;">
                                                <s:property value="impPreVenAsi" />
                                            </span>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td>Kilometraje:</td>
                                        <td>
                                            <span style="font-weight: bold;">
                                                <s:property value="numKimAct" />
                                            </span>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td>Ambiente:</td>
                                        <td>
                                            <span style="font-weight: bold;">
                                                <s:property value="desAmbUbi" />
                                            </span>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td>Ubicación:</td>
                                        <td>
                                            <span style="font-weight: bold;">
                                                <s:property value="desUbiAmb" />
                                            </span>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td>Situación:</td>
                                        <td>
                                            <span style="font-weight: bold;">
                                                <s:if test='%{(codSitVeh=="L" || codSitVeh=="S" || codSitVeh=="K") && (idUsuSitVeh=="" || idUsuSitVeh==#session.ses_idusu)}'>
                                                    <s:select name="codSitVeh" id="codSitVeh" list="listSituacionVehiculo" listKey="codSitVeh" listValue="desSitVeh" 
                                                              headerKey="" headerValue="-Seleccione-" cssClass="element-form" cssStyle="width: 130px;" />
                                                </s:if>
                                                <s:else>
                                                    <s:property value="desSitVeh" />
                                                    <s:hidden name="codSitVeh" />
                                                </s:else>
                                            </span>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td>Fecha de situación:</td>
                                        <td>
                                            <span style="font-weight: bold;">
                                                <s:property value="fecSitVeh" />
                                            </span>
                                        </td>
                                    </tr>
                                </table>
                            </td>
                        </tr>
                    </table>
                </div>
            </td>
        </tr>
        <tr>
            <td colspan="2" style="vertical-align: top; padding-top: 20px;">
                <table border="0" cellpadding="0" cellspacing="0" >
                    <tr>
                        <td style="">
                            <div class="d-block-form">
                                <div class="text-block">Datos de venta y entrega</div>
                                <table border="0" cellpadding="0" cellspacing="0" style="width: 100%">
                                    <tr>
                                        <td>
                                            <table border="0" cellpadding="0" cellspacing="0" class="table-form">
                                                <tr>
                                                    <td style="width: 131px;">Situación:</td>
                                                    <td>
                                                        <span style="font-weight: bold;">
                                                            <s:property value="desSitVen" />
                                                        </span>
                                                    </td>
                                                </tr>
                                                <tr>
                                                    <td>Fec. situación de venta: </td>
                                                    <td>
                                                        <span style="font-weight: bold;">
                                                            <s:property value="fecSitVen" />
                                                        </span>
                                                    </td>
                                                </tr>
                                                <tr>
                                                    <td>Modalidad:</td>
                                                    <td style="">
                                                        <s:select name="idModVen" id="idModVen" list="listModalidadVenta" listKey="idModVen" listValue="desModVen" 
                                                              cssClass="element-form" cssStyle="max-width:140px;"  />
                                                        
                                                        <span style="font-weight: bold;" id="sp_modVenta">
                                                            <s:property value="desModVen" />
                                                        </span>
                                                        <s:hidden name="idModVen" id="idModVen_h1" />
                                                    </td>
                                                </tr>
                                                <tr>
                                                    <td style="height: 30px;">Cliente:</td>
                                                    <td style="">
                                                        <s:if test='%{idCli=="9999999999"}'>
                                                            <s:textfield cssClass="element-form" size="10" name="idCli" value="" id="idCli" disabled="true" />
                                                        </s:if>
                                                        <s:else>
                                                            <s:textfield cssClass="element-form" size="10" name="idCli" id="idCli" disabled="true" />
                                                        </s:else>

                                                        <s:textfield name="desCli" id="desCli" cssClass="element-form" cssStyle="width: 250px" disabled="true"  />
                                                        <button type="button" class="boton" id="btn-findcli">...</button>
                                                        <s:hidden name="idCli" id="idCli_h1" />
                                                        
                                                        <span style="font-weight: bold;" id="sp_cliente">
                                                            <s:property value="idCli" /> - <s:property value="desCli" />
                                                        </span>
                                                        <s:hidden name="idCli" id="idCli_h2" />
                                                    </td>
                                                </tr>
                                                <tr>
                                                    <td title="">Tipo doc. venta:</td>
                                                    <td>
                                                        <span style="font-weight: bold;">
                                                            <s:property value="desTipDocVen" />
                                                        </span>
                                                    </td>
                                                </tr>
                                                <tr>
                                                    <td>Nº doc. venta:</td>
                                                    <td>
                                                        <span style="font-weight: bold;">
                                                            <s:property value="desNumDocVen" />
                                                        </span>
                                                    </td>
                                                </tr>
                                                <tr>
                                                    <td>Fec. emisión:</td>
                                                    <td>
                                                        <span style="font-weight: bold;">
                                                            <s:property value="fecEmiDocVen" />
                                                        </span>
                                                    </td>
                                                </tr>
                                            </table>
                                        </td>
                                        <td style="vertical-align: top; padding-left: 50px;width: 545px" >
                                            <s:if test='%{codSitVen=="V"}' >
                                            <table border="0" cellpadding="0" cellspacing="0" class="table-form">
                                                <tr>
                                                    <td style="width: 84px;">Fecha entrega:</td>
                                                    <td>
                                                        <span style="font-weight: bold;">
                                                            <s:textfield cssClass="element-form" size="11" maxLength="10" name="fecEntCli" id="fecEntCli" />
                                                        </span>
                                                    </td>
                                                </tr>
                                                <tr>
                                                    <td>Nº de placa:</td>
                                                    <td>
                                                        <span style="font-weight: bold;">
                                                            <s:textfield cssClass="element-form" size="11" name="desNumPla" id="desNumPla" />
                                                        </span>
                                                    </td>
                                                </tr>
                                                <tr>
                                                    <td style="vertical-align: top; padding-top: 4px;">Observación:</td>
                                                    <td>    
                                                        <s:textarea cssStyle="width:300px; height:80px; resize:none;" name="desObs" id="desObs" 
                                                                    cssClass="element-form" readonly="false" disabled="false"   />
                                                    </td>
                                                </tr>
                                            </table>
                                            </s:if>
                                            <s:else>
                                            <table border="0" cellpadding="0" cellspacing="0" class="table-form">
                                                <tr>
                                                    <td style="width: 84px;">Fecha entrega:</td>
                                                    <td>
                                                        <span style="font-weight: bold;">
                                                            <s:property value="fecEntCli" />
                                                        </span>
                                                        <s:hidden name="fecEntCli" />
                                                    </td>
                                                </tr>
                                                <tr>
                                                    <td>Nº de placa:</td>
                                                    <td>
                                                        <span style="font-weight: bold;">
                                                            <s:property value="desNumPla" />
                                                        </span>
                                                        <s:hidden name="desNumPla" />
                                                    </td>
                                                </tr>
                                                <tr>
                                                    <td style="vertical-align: top; padding-top: 6px;">Observación:</td>
                                                    <td>
                                                        <span style="font-weight: bold; display: inline-block; width: 300px; height: 100px; margin-top: 6px; margin-left: 0px;">
                                                            <s:property value="desObs" />
                                                        </span>
                                                        <s:hidden name="desObs" />
                                                    </td>
                                                </tr>
                                            </table>    
                                            </s:else>
                                        </td>
                                    </tr>
                                </table>
                            </div>
                        </td>
                    </tr>
                </table>
            </td>
        </tr>
    </table>
    
    <button type="button" class="boton" id="btn-findcamp">
        Agregar Campaña
    </button>
    <div class="d-content-grilla" style="min-width: 660px; margin-top: 5px; border-bottom: none;">
        <div class="d-grilla" style="overflow: hidden;">
            <div class="d-content-grilla-head" style="">
                <table border="0" cellpadding="0" cellspacing="0" style="width: 100%">
                    <tr class="tr-head">
                        <td style="width: 110px; text-align: center;">
                            Origen
                        </td>
                        <td style="">
                            Campaña
                        </td>
                        <td style="text-align: center; width: 50px;">Eliminar</td>
                    </tr>
                    <tr class="tr-head">
                        <td style=""></td>
                        <td style=""></td>
                        <td style=""></td>
                    </tr>
                </table>
            </div>
            <div class="d-content-grilla-body" style="background-color: #FFF;">
                <table border="0" cellpadding="0" cellspacing="0" id="tabla-campanias" style="width: 100%">
                    <s:iterator value="listCampanias">
                    <tr>
                        <td style="width: 110px; text-align: center;">
                            <input type="hidden" name="campania.listIdCamStr" cssClass="h_idCamStr" value="<s:property value="idCamStr" />" />
                            <s:property value="desOriCam" />
                        </td>
                        <td style="">
                            <s:property value="desCam" />
                        </td>
                        <td style="text-align: center; width: 50px;">
                            <s:if test='%{indCamVeh!="A" && codSitVeh=="K"}'>
                                <img src="<s:property value="baseURL" />/images/icons/trash-icon.png" alt="eliminar" style="cursor: pointer;" class="img-delete" />
                            </s:if>
                        </td>
                    </tr>
                    </s:iterator>
                </table>
            </div>
        </div>
    </div>                                                    
    
    <table>
        <tr>
            <td style="padding-top: 10px;">
                <button id="btn_grabar">
                    Grabar
                </button>
                <span class="required">(*) campos obligatorios</span>
            </td>
        </tr>
    </table>
</div>
                                            
</s:form>

<div id="DIVgrabar" title="<s:property value="titleDialog" />" class="alerta"></div>
<div id="DIVcampanias" title="<s:property value="titleDialog" />" class="alerta"></div>
<div id="DIVclientes" title="<s:property value="titleDialog" />" class="alerta"></div> 

                                                        
<script type="text/javascript">
    
    $(document).ready(function() {
         $('#fecEntCli').datepicker();
         
         $('#DIVclientes').dialog({
            autoOpen: false,
            width: 580,
            height: 500,
            modal: true,
            closeOnEscape: false,
            open: function(event, ui) { $(".ui-dialog-titlebar-close").hide(); },
            draggable: false,
            resizable: false
        });
        
        $('#DIVcampanias').dialog({
            autoOpen: false,
            width: 580,
            height: 500,
            modal: true,
            closeOnEscape: false,
            open: function(event, ui) { $(".ui-dialog-titlebar-close").hide(); },
            draggable: false,
            resizable: false
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
        
        $('a.back').click(function() {
            $('#frm_veh_back').submit();
        });
        
        $('#btn-findcli').click(function(){
            post(
                '<s:property value="baseURL" /><s:url namespace="vehiculos" includeContext="false" action="listClientesVehiculo" />',
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
                                    $('#desCli').val($.trim($('.select_rec:checked').parent().next().next().html()));
                                    $('#DIVclientes').dialog("close");
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
                },
                2       
            );     
        });
        
        /* ***************** */
        $('#btn-findcamp').click(function(){
            post(
                '<s:property value="baseURL" /><s:url namespace="vehiculos" includeContext="false" action="listCampaniasVersionVehiculo" />',
                {
                    idMar:$('#idMar').val(),
                    idModMar:$('#idModMar').val(),
                    idVerMod:$('#idVerMod').val(),
                    numAnoFab:$('#numAnoFab').val(),
                    idVeh:$('#idVeh').val()
                },
                function(resultado){
                    var _error = validaRespuestaAjax(resultado);
                    
                    if(_error != -1)
                    {
                        $('#DIVerroresGen').html(resultado);
                        $('#DIVerroresGen').dialog('open');
                    }
                    else
                    {
                        $('#DIVcampanias').dialog({
                            buttons:{
                                "Aceptar":function(){
                                    var p_idcamstr = $('.select_rec:checked').val();
                                    var p_desoricam= $.trim($('.select_rec:checked').parent().next().html());
                                    var p_descamp= $.trim($('.select_rec:checked').parent().next().next().html());
                                    
                                    if( $('.select_rec:checked').length >0 ){
                                        addcamp(p_idcamstr,p_desoricam,p_descamp);
                                    }
                                    
                                    $('#DIVcampanias').dialog("close");
                                    hideOverlay(function(){});
                                },
                                "Cancelar":function(){
                                    $('#DIVcampanias').dialog("close");
                                    hideOverlay(function(){});
                                }
                            }
                        });
                         
                        $('#DIVcampanias').html(resultado);
                        $('#DIVcampanias').dialog('open');
                    }
                },
                2       
            );     
        });
       
        
        /* ****************** */
        $('#btn_grabar').click(function(){   
            post(
                '<s:property value="formURL" />',
                $('#frmVehiculo').serialize(),
                function(resultado){
                    var _error = validaRespuestaAjax(resultado);
                    
                    if(_error != -1)
                    {
                        $('#DIVgrabar').html(resultado);
                        $('#DIVgrabar').dialog('open');
                    }
                    else
                    {
                        $('#frm_veh_back').submit();
                    }
                },
                1
            );
        });
        
        <s:if test='%{codSitVeh=="K" && (idUsuSitVeh=="" || idUsuSitVeh==#session.ses_idusu)}'>
        $('#sp_cliente,#sp_modVenta').css('display','nonde');
        $('#btn-findcli,#btn-findcamp,#idModVen,#idCli,#desCli').css('display','inline-block');
        $('#idModVen,#idCli_h1').removeAttr('disabled');
        $('#idCli_h2,#idModVen_h1').attr('disabled',true);
        </s:if>
        <s:else>
        $('#btn-findcli,#btn-findcamp,#idModVen,#idCli,#desCli').css('display','none');
        $('#sp_cliente,#sp_modVenta').css('display','inline-block');
        $('#idModVen,#idCli_h1').attr('disabled',true);
        $('#idCli_h2,#idModVen_h1').removeAttr('disabled');
        </s:else>
        
        $('#codSitVeh').change(function(){
            if($('#codSitVeh').val()=='K' ){
                $('#sp_cliente,#sp_modVenta').css('display','none');
                $('#btn-findcli,#btn-findcamp,#idModVen,#idCli,#desCli').css('display','inline-block');
                $('#idModVen,#idCli_h1').removeAttr('disabled');
                $('#idCli_h2,#idModVen_h1').attr('disabled',true);
            }else{
                $('#tabla-campanias tr').each(function(){
                    $(this).remove();         
                });
                $('#btn-findcli,#btn-findcamp,#idModVen,#idCli,#desCli').css('display','none');
                $('#sp_cliente,#sp_modVenta').css('display','inline-block');
                $('#idCli_h2,#idModVen_h1').removeAttr('disabled');
                $('#idModVen,#idCli_h1').attr('disabled',true);
            } 
        });
    });
        
        
    function addcamp (p_idcamstr,p_desoricam,p_descam) {
        p_idcamstr = $.trim(p_idcamstr);
        p_desoricam = $.trim(p_desoricam);
        p_descam = $.trim(p_descam);
        
        var sw = 0;
        
        $('#tabla-campanias tr').each(function(){
            var h_idcamstr = $.trim($(this).find('.h_idCamStr').val());
            if(p_idcamstr==h_idcamstr)
                sw++;          
        });
        
        if(sw==0) {
            var tradd='<tr>';
            tradd = tradd+'<td style="width: 110px; text-align: center;">';
            tradd = tradd+'<input type="hidden" name="campania.listIdCamStr" class="h_idCamStr" value="'+p_idcamstr+'" />';
            tradd = tradd+p_desoricam;
            tradd = tradd+'</td>';
            tradd = tradd+'<td>';
            tradd = tradd+p_descam;
            tradd = tradd+'</td>';
            tradd = tradd+'<td style="text-align: center; width: 50px;">';
            tradd = tradd+'<img src="<s:property value="baseURL" />/images/icons/trash-icon.png" alt="eliminar" style="cursor: pointer;" class="img-delete" />';
            tradd = tradd+'</td>';
            tradd = tradd+'</tr>';
            $('#tabla-campanias').append(tradd);
        }
    }

  
  function quitelemnt(obj){
        $(obj).parent().detach();
  }
    
</script>