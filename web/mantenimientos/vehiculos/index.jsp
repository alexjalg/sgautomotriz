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
        <button id="btn-edit">
            Modificar
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
            <table border="0" cellpadding="0" cellspacing="0" style="width: 1400px">
                <tr class="tr-head">
                    <td style="width: 24px;"></td>
                    <td style="width:100px; text-align: center;<s:if test='%{idVeh_f!=""}'> background-color: #B5CCED; </s:if>"  >
                        Serie
                    </td>
                    <td style="width:130px;<s:if test='%{desNumCha_f!=""}'> background-color: #B5CCED; </s:if>">
                        Num Chasis
                    </td>
                    <td style="width: 100px;<s:if test='%{desNumMot_f!=""}'> background-color: #B5CCED; </s:if>">
                        Num Motor
                    </td>
                    <td style="width: 90px;<s:if test='%{fecFacPrv_f!=""}'> background-color: #B5CCED; </s:if>">
                        Fec Fac Prov
                    </td>
                    <td style="<s:if test='%{desCli_f!=""}'> background-color: #B5CCED; </s:if>">
                        Cliente
                    </td>
                    <td style="width: 90px;<s:if test='%{fecVenVeh_f!=""}'> background-color: #B5CCED; </s:if>">
                        Fec Venta
                    </td>
                    <td style="width: 140px;<s:if test='%{idTipDocVen_f!="0"}'> background-color: #B5CCED; </s:if>">
                        Documento
                    </td>
                    <td style="width: 110px;<s:if test='%{desNumDocVen_f!=""}'> background-color: #B5CCED; </s:if>">
                       Nro Doc
                    </td>
                    <td style="width: 110px;<s:if test='%{desNumPla_f!=""}'> background-color: #B5CCED; </s:if>">
                        PLACA
                    </td>
                    <td style="width: 90px;<s:if test='%{fecEntCli_f!=""}'> background-color: #B5CCED; </s:if>">
                        FEC ENT
                    </td>
                </tr>
                <tr class="tr-head">
                    <td style="width: 24px;">
                        
                    </td>
                    <td style="width:100px; text-align: center;<s:if test='%{idVeh_f!=""}'> background-color: #B5CCED; </s:if>"  >
                    <s:textfield name="idVeh_f" id="idVeh_f" cssClass="element-form-grid" cssStyle="width: 70px;" maxLength="10" />
                    </td>
                    <td style="width:130px;<s:if test='%{desNumCha_f!=""}'> background-color: #B5CCED; </s:if>">
                        <s:textfield name="desNumCha_f" id="desNumCha_f" cssClass="element-form-grid" cssStyle="width: 94px;" maxLength="20" />
                    </td>
                    <td style="width: 100px;<s:if test='%{desNumMot_f!=""}'> background-color: #B5CCED; </s:if>">
                        <s:textfield name="desNumMot_f" id="desNumMot_f" cssClass="element-form-grid" cssStyle="width: 80px;" maxLength="15" />
                    </td>
                    <td style="width: 90px;text-align: center;<s:if test='%{fecFacPrv_f!=""}'> background-color: #B5CCED; </s:if>">
                        <s:textfield name="fecFacPrv_f" id="fecFacPrv_f" cssClass="element-form-grid" cssStyle="width: 70px;" maxLength="10" />
                    </td>
                    <td style="<s:if test='%{desCli_f!=""}'> background-color: #B5CCED; </s:if>">
                       <s:textfield name="desCli_f" cssClass="element-form-grid" cssStyle="width: auto;" maxLength="80" />
                    </td>
                    <td style="width: 90px;text-align: center;<s:if test='%{fecVenVeh_f!=""}'> background-color: #B5CCED; </s:if>">
                        <s:textfield name="fecVenVeh_f" id="fecVenVeh_f" cssClass="element-form-grid" cssStyle="width: 70px;" maxLength="10" />
                    </td>
                    <td style="width: 140px;<s:if test='%{idTipDocVen_f!="0"}'> background-color: #B5CCED; </s:if>">
                        <s:select list="#{'0':'Seleccione','':'Sin Documento','FA':'Factura','BV':'Boleta'}"
                                  name="idTipDocVen_f" id="idTipDocVen_f" cssStyle="" cssClass="element-form-grid" />
                    </td>
                    <td style="width: 110px;<s:if test='%{desNumDocVen_f!=""}'> background-color: #B5CCED; </s:if>">
                        <s:textfield name="desNumDocVen" cssClass="element-form-grid" cssStyle="width: 70px;" maxLength="10" />
                    </td>
                    <td style="width: 110px;<s:if test='%{desNumPla_f!=""}'> background-color: #B5CCED; </s:if>">
                        <s:textfield name="desNumPla_f" cssClass="element-form-grid" cssStyle="width: 70px;" maxLength="10" />
                    </td>
                    <td style="width: 90px;text-align: center;<s:if test='%{fecEntCli_f!=""}'> background-color: #B5CCED; </s:if>">
                        <s:textfield name="fecEntCli_f" id="fecEntCli_f" cssClass="element-form-grid" cssStyle="width: 70px;" maxLength="10" /> 
                    </td>
                    
                </tr>
            </table>
        </div>
        <div class="d-content-grilla-body">
            <table border="0" cellpadding="0" cellspacing="0" style="width: 1400px">
                <s:iterator value="listvehiculos">
                <tr>
                    <td style="width: 24px;">
                        <input type="radio" name="idVeh" id="rbt_idVeh" value="<s:property value="idVeh" />" class="select_rec" />
                    </td>
                    <td style="width:100px; text-align: center;"  >
                     <s:property value="idVeh" />
                    </td>
                    <td style="width:130px;">
                        <s:property value="desNumCha" />
                    </td>
                    <td style="width: 100px;">
                        <s:property value="desNumMot" />
                    </td>
                    <td style="width: 90px;text-align: center">
                        <s:property value="fecFacPrv" />
                    </td>
                    <td style="">
                        <s:property value="desCli" />
                    </td>
                    <td style="width: 90px;text-align: center">
                        <s:property value="fecVenVeh_f" />
                    </td>
                    <td style="width: 140px">
                        <s:property value="desTipDocVen"  />
                    </td>
                     <td style="width: 110px">
                        <s:property value="desNumDocVen"  />
                    </td>
                    <td style="width: 110px;">
                        <s:property value="desNumPla"  />
                    </td>
                    <td style="width: 90px;text-align: center">
                        <s:property value="fecEntCli"  /> 
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
        
         //<----- DatePicker's  ------>
        $('#fecFacPrv_f,#fecVenVeh_f,#fecEntCli_f').datepicker({ 
            monthNames: ["Enero","Febrero","Marzo","Abril","Mayo","Junio","Julio","Agosto","Septiembre","Octubre","Noviembre","Diciembre"], 
            monthNamesShort: ["Ene","Feb","Mar","Abr","May","Jun","Jul","Ago","Sep","Oct","Nov","Dic"],
            dayNamesMin: ["Do", "Lu", "Ma", "Mi", "Ju", "Vi", "Sa"],
            firstDay: 1,
            dateFormat: "yy-mm-dd"
        });       
        
        $('#btn-add').button();
        $('#btn-edit').button();
        $('#btn-delete').button();
    
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

        $('#btn-add').click(function() {
            $('#opcion_h1').val('A');
            var href = $(location).attr('href');

            var _varret = $('#nivBandeja_f').val()+'%'+href+'%'+$('#mtu_h1').val()+'%'+$('#mmo_h1').val()+'%'+$('#mop_h1').val()+'%'+$('#mni_h1').val()+'%'+$('#mod_h1').val()+'%'+$('#curPag_f').val()+'%'+$('#idVeh_f').val()+'%'+$('#desNumCha_f').val()+'%'+$('#desNumMot_f').val()+'|';
            $('#varReturn_f').val($('#varReturn_f').val()+_varret);

            $('#frm_princ').attr('action','<s:property value="baseURL" /><s:url namespace="vehiculos" includeContext="false" action="adicionarVehiculo" />');
            $('#frm_princ').submit();
        });
        
        $('#btn-edit').click(function() {
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
                        $('#opcion_h1').val('M');
                        var href = $(location).attr('href');

                        var _varret = $('#nivBandeja_f').val()+'%'+href+'%'+$('#mtu_h1').val()+'%'+$('#mmo_h1').val()+'%'+$('#mop_h1').val()+'%'+$('#mni_h1').val()+'%'+$('#mod_h1').val()+'%'+$('#curPag_f').val()+'%'+$('#idVeh_f').val()+'%'+$('#desNumCha_f').val()+'%'+$('#desNumMot_f').val()+'|';
                         
                        $('#varReturn_f').val($('#varReturn_f').val()+_varret);

                        $('#frm_princ').attr('action','<s:property value="baseURL" /><s:url namespace="vehiculos" includeContext="false" action="modificarVehiculo" />');
                        $('#frm_princ').submit();           
                    }
                },
                2
            );
        });
        
        $('#btn-delete').click(function(){
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
                        $('#opcion_h1').val('C');
                        post(
                            '<s:property value="baseURL" /><s:url namespace="vehiculos" includeContext="false" action="eliminarVehiculo" />',
                            $('#frm_princ').serialize(),
                            function(resultado1)
                            {
                                var _error1 = validaRespuestaAjax(resultado1);
                                
                                if(_error1 != -1)
                                {
                                    $('#DIVerroresGen').html(resultado1);
                                    $('#DIVerroresGen').dialog('open');
                                }
                                else
                                {
                                    $('#DIVeliminar').dialog({
                                        buttons:{
                                            "Confirmar":function(){
                                                $('#DIVeliminar').dialog('close');
                                                $('#opcion_h1').val('E');
                                                post(
                                                    '<s:property value="baseURL" /><s:url namespace="vehiculos" includeContext="false" action="eliminarVehiculo" />',
                                                    $('#frm_princ').serialize(),
                                                    function(resultado2)
                                                    {
                                                        var _error2 = validaRespuestaAjax(resultado2);
                                                        
                                                        if(_error2 != -1)
                                                        {
                                                            $('#DIVerroresGen').html(resultado2);
                                                            $('#DIVerroresGen').dialog('open');
                                                        }
                                                        else
                                                        {
                                                            $('#DIVeliminar').dialog('close');
                                                            $('#frm_princ').attr('action','<s:property value="baseURL" /><s:url namespace="vehiculos" includeContext="false" action="Vehiculo" />');
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
    });
    
    //Redimensionar el alto del contenedor de grilla si fuese necesario
    resizeContGrilla(<s:property value="regPag" />);    
</script>