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
        <button id="btn-edit">
            Modificar
        </button>
        </s:if>
        <button id="btn-detail">
            Detalle
        </button>
    </s:if>
</div>
<div class="d-content-grilla" style="min-width: 660px;">
    <form id="frm_princ" method="POST" action="<s:property value="baseURL" /><s:property value="urlPaginacion" />">
    <s:property value="datosOblig" escape="false" />
    <s:hidden name="indModificar" id="indModificar" value="3" />
    <s:hidden name="indDetalle" id="indDetalle" value="3" />
    
    <div class="d-grilla" style="overflow: hidden;">
        <div class="d-content-grilla-head" style="">
            <table border="0" cellpadding="0" cellspacing="0" style="width: 1720px">
                <tr class="tr-head">
                    <td style="width: 24px;"></td>
                    <td style="width:100px; text-align: center;<s:if test='%{idVeh_f!=""}'> background-color: #B5CCED; </s:if>"  >
                        Serie
                    </td>
                    <td style="width:140px;text-align: center;<s:if test='%{desNumCha_f!=""}'> background-color: #B5CCED; </s:if>">
                        Nº Chasis
                    </td>
                    <td style="width: 100px;text-align: center;<s:if test='%{desNumMot_f!=""}'> background-color: #B5CCED; </s:if>">
                        Nº Motor
                    </td>
                    <td style="width: 350px;<s:if test='%{desVeh_f!=""}'> background-color: #B5CCED; </s:if>">
                        Modelo
                    </td>
                    <td style="<s:if test='%{desCli_f!=""}'> background-color: #B5CCED; </s:if>">
                        Cliente
                    </td>
                    <td style="text-align: center; width: 126px;<s:if test='%{codSitVeh_f!=""}'> background-color: #B5CCED; </s:if>">
                        Situación
                    </td>
                    <td style="text-align: center; width: 126px;<s:if test='%{codSitVen_f!=""}'> background-color: #B5CCED; </s:if>">
                        Situación de venta
                    </td>
                    <td style="width: 110px;text-align: center;<s:if test='%{desNumPla_f!=""}'> background-color: #B5CCED; </s:if>">
                        Placa
                    </td>
                </tr>
                <tr class="tr-head">
                    <td style="width: 24px;"></td>
                    <td style="width:100px; text-align: center;<s:if test='%{idVeh_f!=""}'> background-color: #B5CCED; </s:if>"  >
                        <s:textfield name="idVeh_f" id="idVeh_f" cssClass="element-form-grid" cssStyle="width: 70px;" maxLength="10" />
                    </td>
                    <td style="width:140px;<s:if test='%{desNumCha_f!=""}'> background-color: #B5CCED; </s:if>">
                        <s:textfield name="desNumCha_f" id="desNumCha_f" cssClass="element-form-grid" cssStyle="width: 130px;" maxLength="20" />
                    </td>
                    <td style="width: 100px; text-align: center;<s:if test='%{desNumMot_f!=""}'> background-color: #B5CCED; </s:if>">
                        <s:textfield name="desNumMot_f" id="desNumMot_f" cssClass="element-form-grid" cssStyle="width: 80px;" maxLength="15" />
                    </td>
                    <td style="width: 350px; <s:if test='%{desVeh_f!=""}'> background-color: #B5CCED; </s:if>">
                        <s:textfield name="desVeh_f" id="desVeh_f" cssClass="element-form-grid" cssStyle="width: 270px;" maxLength="40" />
                    </td>
                    <td style="<s:if test='%{desCli_f!=""}'> background-color: #B5CCED; </s:if>">
                       <s:textfield name="desCli_f" cssClass="element-form-grid" cssStyle="width: 350px;" maxLength="80" />
                    </td>
                    <td style="width: 126px; text-align: center;<s:if test='%{codSitVeh_f!=""}'> background-color: #B5CCED; </s:if>">
                        <s:select name="codSitVeh_f" id="codSitVeh_f" list="listSituacionVehiculo" listKey="codSitVeh" listValue="desSitVeh" 
                                  headerKey="" headerValue="-Seleccione-" cssClass="element-form-grid" cssStyle="width: 120px;" />
                    </td>
                    <td style="width: 126px; text-align: center;<s:if test='%{codSitVen_f!=""}'> background-color: #B5CCED; </s:if>">
                        <s:select name="codSitVen_f" id="codSitVen_f" list="listSituacionVentaVehiculo" listKey="codSitVen" listValue="desSitVen" 
                                  headerKey="" headerValue="-Seleccione-" cssClass="element-form-grid" cssStyle="width: 120px;" />
                    </td>
                    <td style="width: 110px;text-align: center;<s:if test='%{desNumPla_f!=""}'> background-color: #B5CCED; </s:if>">
                        <s:textfield name="desNumPla_f" cssClass="element-form-grid" cssStyle="width: 70px;" maxLength="10" />
                    </td>
                </tr>
            </table>
        </div>
        <div class="d-content-grilla-body">
            <table border="0" cellpadding="0" cellspacing="0" style="width: 1720px">
                <s:iterator value="listVehiculos">
                <tr>
                    <td style="width: 24px;">
                        <input type="radio" name="idVeh" id="rbt_idVeh" value="<s:property value="idVeh" />" class="select_rec" />
                    </td>
                    <td style="width:100px; text-align: center;"  >
                     <s:property value="idVeh" />
                    </td>
                    <td style="width:140px;text-align: center">
                        <s:property value="desNumCha" />
                    </td>
                    <td style="width: 100px;text-align: center">
                        <s:property value="desNumMot" />
                    </td>
                    <td style="width: 350px;">
                        <s:property value="desVeh" />
                    </td>
                    <td style="">
                        <s:property value="desCli" />
                    </td>
                    <td style="width: 126px; text-align: center">
                        <s:property value="desSitVeh" />
                    </td>
                    <td style="width: 126px;text-align: center">
                        <s:property value="desSitVen"  />
                    </td>
                    <td style="width: 110px;text-align: center">
                        <s:property value="desNumPla"  />
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

<s:iterator value="errores">
    <s:property />
</s:iterator>
<script type="text/javascript">
    $(document).ready(function() {
        /*Paginacion de grilla*/
        <s:property value="jsPaginacion" escape="false" />
        
         //<----- DatePicker's  ------>
        $('#fecFacPrv_f,#fecEmiDocVen_f,#fecEntCli_f').datepicker();       
        
        $('#btn-edit').click(function() {
            post(
                '<s:property value="baseURL" /><s:url namespace="vehiculos" includeContext="false" action="vrfSeleccionVehiculo" />',
                $('#frm_princ').serialize(),
                function(resultado){
                    var _error = validaRespuestaAjax(resultado);
                    
                    if(_error != -1) {
                        $('#DIVverif').html(resultado);
                        $('#DIVverif').dialog('open');
                    } else {
                        post(
                            '<s:property value="baseURL" /><s:url namespace="vehiculos" includeContext="false" action="verificaSituacionVentaVehiculo" />',
                            $('#frm_princ').serialize(),
                            function(resultado1) {
                                var _error1 = validaRespuestaAjax(resultado1);
                                
                                if(_error1 != -1) {
                                    $('#DIVverif').html(resultado1);
                                    $('#DIVverif').dialog('open');
                                } else {
                                    $('#opcion_h1').val('M');
                                    var href = $(location).attr('href');

                                    var _varret = $('#nivBandeja_f').val()+'%'+href+'%'+$('#mtu_h1').val()+'%'+$('#mmo_h1').val()+'%'+$('#mop_h1').val()+'%'+$('#mni_h1').val()+'%'+$('#mod_h1').val()+'%'+$('#curPag_f').val()+'%'+$('#idVeh_f').val()+'%'+$('#desNumCha_f').val()+'%'+$('#desNumMot_f').val()+'%'+$('#desVeh_f').val()+'%'+$('#desCli_f').val()+'%'+$('#codSitVeh_f').val()+'%'+$('#codSitVen_f').val()+'%'+$('#desNumPla_f').val()+'|';

                                    $('#varReturn_f').val($('#varReturn_f').val()+_varret);

                                    $('#frm_princ').attr('action','<s:property value="baseURL" /><s:url namespace="vehiculos" includeContext="false" action="modificarVehiculo" />');
                                    $('#frm_princ').submit();   
                                }
                            },
                            4
                        );        
                    }
                },
                2
            );
        });
        
        $('#btn-detail').click(function() {
            post(
                '<s:property value="baseURL" /><s:url namespace="vehiculos" includeContext="false" action="vrfSeleccionVehiculo" />',
                $('#frm_princ').serialize(),
                function(resultado){
                    var _error = validaRespuestaAjax(resultado);
                    
                    if(_error != -1)
                    {
                        $('#DIVverif').html(resultado);
                        $('#DIVverif').dialog('open');
                    }
                    else
                    {
                        $('#opcion_h1').val('D');
                        var href = $(location).attr('href');

                        var _varret = $('#nivBandeja_f').val()+'%'+href+'%'+$('#mtu_h1').val()+'%'+$('#mmo_h1').val()+'%'+$('#mop_h1').val()+'%'+$('#mni_h1').val()+'%'+$('#mod_h1').val()+'%'+$('#curPag_f').val()+'%'+$('#idVeh_f').val()+'%'+$('#desNumCha_f').val()+'%'+$('#desNumMot_f').val()+'%'+$('#desVeh_f').val()+'%'+$('#desCli_f').val()+'%'+$('#codSitVeh_f').val()+'%'+$('#codSitVen_f').val()+'%'+$('#desNumPla_f').val()+'|';
                         
                        $('#varReturn_f').val($('#varReturn_f').val()+_varret);

                        $('#frm_princ').attr('action','<s:property value="baseURL" /><s:url namespace="vehiculos" includeContext="false" action="detalleVehiculo" />');
                        $('#frm_princ').submit();           
                    }
                },
                1
            );
        });
    });
    
    //Redimensionar el alto del contenedor de grilla si fuese necesario
    resizeContGrilla(<s:property value="regPag" />);    
</script>