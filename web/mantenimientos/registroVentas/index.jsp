<%@taglib uri="/struts-tags" prefix="s" %>

<!-- cabecera de pagina -->
<div class="d-header">
    <div class="d-title-header">
        <s:property value="tituloOpc" />
        <div class="d-subtitle-header"></div>
    </div>
</div>
        
<!-- modelo de grilla -->
<div class="d-buttons-grid">
<s:if test='%{perm=="M" || perm=="V"}'>
    <s:if test='%{perm=="M"}'>
    <button id="btn-add">
        Adicionar
    </button>
    <!--<button id="btn-edit">
        Modificar
    </button>-->
    <button id="btn-details">
        Detalle
    </button>
    <button id="btn-delete">
        Eliminar
    </button>
    </s:if>
</s:if>
</div>
<div class="d-content-grilla" style="min-width: 660px;">
    <form id="frm_princ" method="POST" action="<s:property value="baseURL" /><s:property value="urlPaginacion" />">
    <s:property value="datosOblig" escape="false" />
    
    <div class="d-grilla" style="overflow: hidden;">
        <div class="d-content-grilla-head" style="">
            <table border="0" cellpadding="0" cellspacing="0" style="">
                <tr class="tr-head">
                    <td style="width: 24px;"></td>
                    <td style="width: 110px; text-align: center;<s:if test='%{idNumIntRV_f!="" && idNumIntRV_f!="0"}'> background-color: #B5CCED; </s:if>" title="Número Interno">
                        Nro. Interno
                    </td>
                    <td style="width: 150px; text-align: center;<s:if test='%{idTipDocVen_f!=""}'> background-color: #B5CCED; </s:if>" title="Tipo de documento">
                       Tipo documento
                    </td>
                    <td style="width: 200px; text-align: center;<s:if test='%{desNumDocVen_f!=""}'> background-color: #B5CCED; </s:if>" title="Número de documento">
                       Nro. documento
                    </td>
                    <td style="width: 110px; text-align: center;<s:if test='%{fecEmiDocVen_f!="" && fecEmiDocVen!="0000-00-00"}'> background-color: #B5CCED; </s:if>" title="Fecha de emisión">
                       Fec. emisíon
                    </td>
                    <td style="width: 120px; text-align: center;<s:if test='%{idCli_f!=""}'> background-color: #B5CCED; </s:if>" title="Código de cliente">
                       Cod. cliente
                    </td>
                    <td style="<s:if test='%{desCli_f!=""}'> background-color: #B5CCED; </s:if>">
                       Nombres y apellidos de cliente
                    </td>
                </tr>
                <tr class="tr-head">
                    <td style="width: 24px;"></td>
                    <td style="text-align: center;<s:if test='%{idNumIntRV_f!="" && idNumIntRV_f!="0"}'> background-color: #B5CCED; </s:if>">
                        <s:if test='%{idNumIntRV_f=="0"}'>
                            <s:textfield name="idNumIntRV_f" value="" cssClass="element-form-grid" cssStyle="width: 90px;" />
                        </s:if>
                        <s:else>
                            <s:textfield name="idNumIntRV_f" cssClass="element-form-grid" cssStyle="width: 90px;" />
                        </s:else>
                    </td>
                    <td style="text-align: center;<s:if test='%{idTipDocVen_f!=""}'> background-color: #B5CCED; </s:if>">
                        <s:select name="idTipDocVen_f" list="listTiposDocumentoVenta" listKey="idTipDocVen" listValue="desTipDocVen"
                                  headerKey="" headerValue="-Seleccione-" cssClass="element-form-grid" cssStyle="width: 120px;" />
                    </td>
                    <td style="text-align: center;<s:if test='%{desNumDocVen_f!=""}'> background-color: #B5CCED; </s:if>">
                        <s:textfield name="desNumDocVen_f" cssClass="element-form-grid" cssStyle="width: 180px;" />
                    </td>
                    <td style="text-align: center;<s:if test='%{fecEmiDocVen_f!="" && fecEmiDocVen!="0000-00-00"}'> background-color: #B5CCED; </s:if>">
                        <s:textfield name="fecEmiDocVen_f" id="fecEmiDocVen_f" cssClass="element-form-grid" cssStyle="width: 74px;" />
                    </td>
                    <td style="text-align: center;<s:if test='%{idCli_f!=""}'> background-color: #B5CCED; </s:if>">
                        <s:textfield name="idCli_f" cssClass="element-form-grid" cssStyle="width: 100px;" />
                    </td>
                    <td style="<s:if test='%{desCli_f!=""}'> background-color: #B5CCED; </s:if>">
                        <s:textfield name="desCli_f" cssClass="element-form-grid" cssStyle="width: 400px;" />
                    </td>
                </tr>
            </table>
        </div>
        <div class="d-content-grilla-body">
            <table border="0" cellpadding="0" cellspacing="0" style="">
                <s:iterator value="listRegistroVentas">
                <tr>
                    <td style="width: 24px;">
                        <input type="radio" name="idNumIntRV" id="rbt_idNumIntRV" value="<s:property value="idNumIntRV" />" class="select_rec" />
                    </td>
                    <td style="width: 110px; text-align: center;">
                        <s:property value="idNumIntRV" />
                    </td>
                    <td style="width: 150px; text-align: center;">
                        <s:property value="desTipDocVen" />
                    </td>
                    <td style="width: 200px; text-align: center;">
                        <s:property value="desNumDocVen" />
                    </td>
                    <td style="width: 110px; text-align: center;">
                       <s:property value="fecEmiDocVen" />
                    </td>
                    <td style="width: 120px; text-align: center;">
                       <s:property value="idCli" />
                    </td>
                    <td style="">
                       <s:property value="desCli" />
                    </td>
                </tr>
                </s:iterator>
            </table>
        </div>
        
        <div class="d-content-grilla-footer">
            <div class="d-paging">
                <s:property value="paginacion" escape="false" />
            </div>
        </div>
    </div>
    </form>
</div>
<div id="DIVeliminar" title="<s:property value="titleDialog" />" class="alerta"></div>
<s:iterator value="errores">
    <s:property />
</s:iterator>
        
<script type="text/javascript">
    $(document).ready(function() {
        /*Paginacion de grilla*/
        <s:property value="jsPaginacion" escape="false" />
                
        $('#fecEmiDocVen_f').datepicker({ 
            monthNames: ["Enero","Febrero","Marzo","Abril","Mayo","Junio","Julio","Agosto","Septiembre","Octubre","Noviembre","Diciembre"], 
            monthNamesShort: ["Ene","Feb","Mar","Abr","May","Jun","Jul","Ago","Sep","Oct","Nov","Dic"],
            dayNamesMin: ["Do", "Lu", "Ma", "Mi", "Ju", "Vi", "Sa"],
            firstDay: 1,
            dateFormat: "dd-mm-yy"
        });
        
        $('#fecReg_f').datepicker({ 
            monthNames: ["Enero","Febrero","Marzo","Abril","Mayo","Junio","Julio","Agosto","Septiembre","Octubre","Noviembre","Diciembre"], 
            monthNamesShort: ["Ene","Feb","Mar","Abr","May","Jun","Jul","Ago","Sep","Oct","Nov","Dic"],
            dayNamesMin: ["Do", "Lu", "Ma", "Mi", "Ju", "Vi", "Sa"],
            firstDay: 1,
            dateFormat: "dd-mm-yy"
        });
    
        $('#DIVeliminar').dialog({
            autoOpen: false,
            width: 400,
            height: 200,
            modal: true,
            closeOnEscape: false,
            open: function(event, ui) { $(".ui-dialog-titlebar-close").hide(); },
            draggable: false,
            resizable: false
        });

        $('#btn-add').click(function(){
            $('#opcion_h1').val('A');
            var href = $(location).attr('href');
            
            var _varret = $('#nivBandeja_f').val()+'%'+href+'%'+$('#mtu_h1').val()+'%'+$('#mmo_h1').val()+'%'+$('#mop_h1').val()+'%'+$('#mni_h1').val()+'%'+$('#mod_h1').val()+'%'+$('#curPag_f').val()+'%'+$('#idNumIntRV_f').val()+'%'+$('#idTipDocVen_f').val()+'%'+$('#desNumDocVen_f').val()+'%'+$('#fecEmiDocVen_f').val()+'%'+$('#idCli_f').val()+'%'+$('#desCli_f').val()+'|';
            $('#varReturn_f').val($('#varReturn_f').val()+_varret);
            
            $('#frm_princ').attr('action','<s:property value="baseURL" /><s:url namespace="registroVentas" includeContext="false" action="adicionarRegistroVenta" />');
            $('#frm_princ').submit();
        });
        
        $('#btn-edit').click(function() {
            post(
                '<s:property value="baseURL" /><s:url namespace="registroVentas" includeContext="false" action="vrfSeleccionRegistroVenta" />',
                $('#frm_princ').serialize(),
                function(resultado){
                    resultado = $.trim(resultado);
                    var _error = resultado.indexOf("error");
                    if(_error != -1)
                    {
                        $('#DIVverif').html(resultado);
                        $('#DIVverif').dialog('open');
                    }
                    else
                    {
                        $('#opcion_h1').val('M');
                        var href = $(location).attr('href');
                        
                        var _varret = $('#nivBandeja_f').val()+'%'+href+'%'+$('#mtu_h1').val()+'%'+$('#mmo_h1').val()+'%'+$('#mop_h1').val()+'%'+$('#mni_h1').val()+'%'+$('#mod_h1').val()+'%'+$('#curPag_f').val()+'%'+$('#idNumIntRV_f').val()+'%'+$('#idTipDocVen_f').val()+'%'+$('#desNumDocVen_f').val()+'%'+$('#fecEmiDocVen_f').val()+'%'+$('#idCli_f').val()+'%'+$('#desCli_f').val()+'|';
                        $('#varReturn_f').val($('#varReturn_f').val()+_varret);
                        
                        $('#frm_princ').attr('action','<s:property value="baseURL" /><s:url namespace="registroVentas" includeContext="false" action="modificarRegistroVenta" />');
                        $('#frm_princ').submit();
                    }
                },
                1
            );
        });
        
        $('#btn-details').click(function() {
            post(
                '<s:property value="baseURL" /><s:url namespace="registroVentas" includeContext="false" action="vrfSeleccionRegistroVenta" />',
                $('#frm_princ').serialize(),
                function(resultado){
                    resultado = $.trim(resultado);
                    var _error = resultado.indexOf("error");
                    if(_error != -1)
                    {
                        $('#DIVverif').html(resultado);
                        $('#DIVverif').dialog('open');
                    }
                    else
                    {
                        $('#opcion_h1').val('D');
                        var href = $(location).attr('href');
                        
                        var _varret = $('#nivBandeja_f').val()+'%'+href+'%'+$('#mtu_h1').val()+'%'+$('#mmo_h1').val()+'%'+$('#mop_h1').val()+'%'+$('#mni_h1').val()+'%'+$('#mod_h1').val()+'%'+$('#curPag_f').val()+'%'+$('#idNumIntRV_f').val()+'%'+$('#idTipDocVen_f').val()+'%'+$('#desNumDocVen_f').val()+'%'+$('#fecEmiDocVen_f').val()+'%'+$('#idCli_f').val()+'%'+$('#desCli_f').val()+'|';
                        $('#varReturn_f').val($('#varReturn_f').val()+_varret);
                        
                        $('#frm_princ').attr('action','<s:property value="baseURL" /><s:url namespace="registroVentas" includeContext="false" action="detalleRegistroVenta" />');
                        $('#frm_princ').submit();
                    }
                },
                1
            );
        });
        
        $('#btn-delete').click(function(){
            post(
                '<s:property value="baseURL" /><s:url namespace="registroVentas" includeContext="false" action="vrfSeleccionRegistroVenta" />',
                $('#frm_princ').serialize(),
                function(resultado){
                    resultado = $.trim(resultado);
                    var _error = resultado.indexOf("error");
                    if(_error != -1)
                    {
                        $('#DIVverif').html(resultado);
                        $('#DIVverif').dialog('open');
                    }
                    else
                    {
                        $('#opcion_h1').val('C');
                        post(
                            '<s:property value="baseURL" /><s:url namespace="registroVentas" includeContext="false" action="eliminarRegistroVenta" />',
                            $('#frm_princ').serialize(),
                            function(resultado1)
                            {
                                resultado1 = $.trim(resultado1);
                                var _error1 = resultado1.indexOf("error");
                                if(_error1 != -1)
                                {
                                    $('#DIVeliminar').html(resultado1);
                                    $('#DIVeliminar').dialog('open');
                                }
                                else
                                {
                                    $('#DIVeliminar').dialog({
                                        buttons:{
                                            "Confirmar":function(){
                                                $('#DIVeliminar').dialog('close');
                                                $('#opcion_h1').val('E');
                                                post(
                                                    '<s:property value="baseURL" /><s:url namespace="registroVentas" includeContext="false" action="eliminarRegistroVenta" />',
                                                    $('#frm_princ').serialize(),
                                                    function(resultado2)
                                                    {
                                                        resultado2 = $.trim(resultado2);
                                                        var _error2 = resultado2.indexOf("error");
                                                        if(_error2 != -1)
                                                        {
                                                            $('#DIVeliminar').html(resultado2);
                                                            $('#DIVeliminar').dialog({
                                                                buttons:{
                                                                    "Aceptar":function(){
                                                                        $('#DIVeliminar').dialog('close');
                                                                        hideOverlay(function(){});
                                                                    }
                                                                }
                                                            });
                                                            $('#DIVeliminar').dialog('open');
                                                        }
                                                        else
                                                        {
                                                            $('#DIVeliminar').dialog('close');
                                                            $('#frm_princ').attr('action','<s:property value="baseURL" /><s:url namespace="registroVentas" includeContext="false" action="RegistroVenta" />');
                                                            $('#frm_princ').submit();
                                                        }
                                                    },
                                                    4
                                                );
                                            },
                                            "Cancelar":function(){
                                                $('#DIVeliminar').dialog("close");
                                                hideOverlay(function(){});
                                            }
                                        }
                                    });
                                    $('#DIVeliminar').html(resultado1);
                                    $('#DIVeliminar').dialog('open');
                                }
                            },
                            3
                        );
                    }
                },
                2
            );
        });
        
        $('.d-back a').click(function(){
            $('#frm_back').submit();
        });
    });
    
    //Redimensionar el alto del contenedor de grilla si fuese necesario
    resizeContGrilla(<s:property value="regPag" />);    

    function hideOptGrilla()
    {
        $('.boton-bandeja-click').removeClass('boton-bandeja-click');
        $('#d-filtros-float').css('display', 'none');
    }
</script>