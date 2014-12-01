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

<div class="d-content-form">
    <table border="0" cellpadding="0" cellspacing="0">
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
                                        <td>
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
                                        <td style="width: 90px;">Nº Chasis:</td>
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
                                </table>
                            </td>
                        </tr>
                    </table>
                </div>
            </td>
        </tr>
    </table>

    <div class="d-content-grilla" style="min-width: 660px; margin-top: 30px; border-bottom: none;">
        <form id="frm_campaniaVeh" method="POST" action="<s:property value="baseURL" /><s:url namespace="vehiculos" includeContext="false" action="updEstadoCampaniaVehiculo" />">
            <s:hidden name="idVeh" />
            <s:hidden name="campania.idCamStr" id="idCamStr" />
            <s:hidden name="campania.indCamVeh" id="indCamVeh" />
        </form>
        <div class="d-grilla" style="overflow: hidden;">
            <div class="d-content-grilla-head" style="">
                <table border="0" cellpadding="0" cellspacing="0" style="width: 100%">
                    <tr class="tr-head">
                        <td style="">
                            Campaña
                        </td>
                        <td style="width: 140px; text-align: center;">
                            Importe
                        </td>
                        <td style="width: 70px; text-align: center;">
                            Activo
                        </td>
                    </tr>
                    <tr class="tr-head">
                        <td style=""></td>
                        <td style=""></td>
                        <td style=""></td>
                    </tr>
                </table>
            </div>
            <div class="d-content-grilla-body" style="background-color: #FFF;">
                <table border="0" cellpadding="0" cellspacing="0" style="width: 100%">
                    <s:iterator value="listCampanias">
                    <tr>
                        <td style=""  >
                         <s:property value="desCam" />
                        </td>
                        <td style="width: 140px; text-align: center">
                            <s:property value="impRelCam" />
                        </td>
                        <td style="width: 70px; text-align: center;">
                            <input type="checkbox" id="chk_edousu" <s:if test='%{indCamVeh=="A"}'> checked="checked" class="chk_indcamveh check_grid_on" </s:if><s:else> class="chk_indcamveh" </s:else> />
                            <input type="hidden" value="<s:property value="idCamStr" />" />
                        </td>
                    </tr>
                    </s:iterator>
                </table>
            </div>
        </div>
    </div>
    
    <s:if test='%{codSitVen!="A"}'>
    <form id="frm_vehiculo" method="POST" action="javascript:void(0)">
    <s:hidden name="idVeh" />
    <s:hidden name="opcion" id="opcion_h1" />
    <table border="0" cellpadding="0" cellspacing="0" style="margin-top: 20px;">
        <tr>
            <td>
                <div class="d-block-form">
                    <div class="text-block">Situación</div>
                    <table border="0" cellpadding="0" cellspacing="0">
                        <tr>
                            <td>
                                <table border="0" cellpadding="0" cellspacing="0" class="table-form">
                                    <tr>
                                        <td>
                                            Situación del vehículo:
                                        </td>
                                        <td>
                                            <s:if test='%{codSitVeh=="L" || codSitVeh=="B" || codSitVeh=="X" || codSitVeh=="E"}'>
                                                <s:select name="codSitVeh" id="codSitVeh" list="listSituacionVehiculo" listKey="codSitVeh" listValue="desSitVeh" 
                                                          cssClass="element-form" cssStyle="width: 140px;" />
                                            </s:if>
                                            <s:else>
                                                <span style="font-weight: bold;">
                                                    <s:property value="desSitVeh" />
                                                </span>
                                            </s:else>
                                        </td>
                                    </tr>
                                </table>
                            </td>
                            <td style="vertical-align: top; padding-left: 50px;">
                                <table border="0" cellpadding="0" cellspacing="0" class="table-form">
                                    <tr>
                                        <td>
                                            Situación de venta:
                                        </td>
                                        <td>
                                            <span style="font-weight: bold;" id="sp_desSitVen">
                                                <s:property value="desSitVen" />
                                            </span>
                                            <s:hidden name="codSitVen" id="codSitVen" />
                                        </td>
                                    </tr>
                                </table>
                            </td>
                            <td style="vertical-align: top; padding-left: 50px;">
                                <table border="0" cellpadding="0" cellspacing="0" class="table-form">
                                    <tr>
                                        <td>
                                            ¿Forma parte del activo de la empresa?:
                                        </td>
                                        <td>
                                            <s:if test='%{codSitVen=="D"}'>
                                                <button id="btn_activar" style="margin-left: 10px;">
                                                    Activar
                                                </button>
                                            </s:if>
                                        </td>
                                    </tr>
                                </table>
                            </td>
                        </tr>
                    </table>
                </div>
            </td>
        </tr>
    </table>
    </form>
    </s:if>
                                        
    <s:if test='%{codSitVen!="A"}'>
    <table border="0" cellpadding="0" cellspacing="0">
        <tr>
            <td style="padding-top: 15px;">
                <button id="btn_grabar">
                    Grabar
                </button>
                <span class="required">(*) campos obligatorios</span>
            </td>
        </tr>
    </table>
    </s:if>    
</div>
<div id="DIVgrabar" title="<s:property value="titleDialog" />" class="alerta"></div>
<div id="DIVactivar" title="<s:property value="titleDialog" />" class="alerta"></div>                    

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

        $('#DIVactivar').dialog({
            autoOpen: false,
            width: 400,
            height: 180,
            modal: true,
            closeOnEscape: false,
            open: function(event, ui) {
                $(".ui-dialog-titlebar-close").hide();
            },
            draggable: false,
            resizable: false
        });

        $('a.back').click(function() {
            $('#frm_veh_back').submit();
        });

        $('#btn_grabar').click(function() {
            $('#btn_grabar').attr('disabled',true);
        
            post(
                '<s:property value="formURL" />',
                $('#frm_vehiculo').serialize(),
                function(resultado) {
                    var _error = validaRespuestaAjax(resultado);

                    if (_error != -1)
                    {
                        $('#DIVgrabar').html(resultado);
                        $('#DIVgrabar').dialog('open');
                        $('#btn_grabar').removeAttr('disabled');
                    }
                    else
                    {
                        $('#frm_veh_back').submit();
                    }
                },
                1
            );
        });
        
        $('.chk_indcamveh').click(function(){
            var _chk = $(this);
            var _edo = 'A';
            var _idcamstr = $(this).next().val();
            
            if($(this).hasClass('check_grid_on'))
                _edo = 'D';
            
            if($(this).hasClass('check_grid_on'))
                $(this).removeClass('check_grid_on');
            else
                $(this).addClass('check_grid_on');
            
            $('#idCamStr').val(_idcamstr);
            $('#indCamVeh').val(_edo);
            
            post(
                '<s:property value="baseURL" /><s:url namespace="vehiculos" includeContext="false" action="updEstadoCampaniaVehiculo" />',
                $('#frm_campaniaVeh').serialize(),
                function(resultado){
                    var _error = validaRespuestaAjax(resultado);
                    
                    if(_error != -1)
                    {
                        $('#DIVgrabar').html(resultado);
                        $('#DIVeliminar').dialog({
                            buttons:{
                                "Aceptar":function(){
                                    $('#DIVgrabar').dialog("close");
                                    $('.overlay').animate({'opacity':'0'},250,'swing',function(){
                                       $('.overlay').css({'z-index':'-1'}); 
                                    });
                                }
                            }
                        });
                        
                        displayOverlay(function(){
                            $('#DIVgrabar').dialog('open');
                            
                            if($(_chk).hasClass('check_grid_on'))
                            {
                                $(_chk).removeClass('check_grid_on');
                                $(_chk).prop('checked',false);
                            }
                            else
                            {
                                $(_chk).addClass('check_grid_on');
                                $(_chk).prop('checked',true);
                            }
                        });
                    }
                },
                1
            );
        });
        
        $('#codSitVeh').change(function(){
            if($(this).val()=='E') {
                $('#sp_desSitVen').html('Endosado');
                $('#codSitVen').val('E');
                $('#btn_activar').css('visibility','hidden');
            } else {
                $('#sp_desSitVen').html('Disponible');
                $('#codSitVen').val('D');
                $('#btn_activar').css('visibility','visible');
            }
        });
        
        $('#btn_activar').click(function(){
            $('#btn_activar').attr('disabled',true);
            $('#opcion_h1').val('C');
            post(
                '<s:property value="baseURL" /><s:url namespace="vehiculos" includeContext="false" action="activarVehiculo" />',
                $('#frm_vehiculo').serialize(),
                function(resultado1)
                {
                    var _error1 = validaRespuestaAjax(resultado1);

                    if(_error1 != -1)
                    {
                        $('#DIVerroresGen').html(resultado1);
                        $('#DIVerroresGen').dialog('open');
                        $('#btn_activar').removeAttr('disabled');
                    }
                    else
                    {
                        $('#DIVactivar').dialog({
                            buttons:{
                                "Confirmar":function(){
                                    $('#DIVeliminar').dialog('close');
                                    $('#opcion_h1').val('A');
                                    post(
                                        '<s:property value="baseURL" /><s:url namespace="vehiculos" includeContext="false" action="activarVehiculo" />',
                                        $('#frm_vehiculo').serialize(),
                                        function(resultado2)
                                        {
                                            var _error2 = validaRespuestaAjax(resultado2);

                                            if(_error2 != -1)
                                            {
                                                $('#DIVerroresGen').html(resultado2);
                                                $('#DIVerroresGen').dialog('open');
                                                $('#btn_activar').removeAttr('disabled');
                                            }
                                            else
                                            {
                                                $('#DIVactivar').dialog('close');
                                                $('#frm_veh_back').submit();
                                            }
                                        },
                                        4
                                    );
                                },
                                "Cancelar":function(){
                                    $('#DIVactivar').dialog("close");
                                    hideOverlay(function(){});
                                    $('#btn_activar').removeAttr('disabled');
                                }
                            }
                        });
                        $('#DIVactivar').html(resultado1);
                        $('#DIVactivar').dialog('open');
                    }
                },
                2
            );      
        });
    });
    
    
</script>