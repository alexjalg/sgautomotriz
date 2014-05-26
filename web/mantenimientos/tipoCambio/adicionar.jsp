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
            
<form method="POST" id="frm_tipoCambio_back" action="<s:property value="backURL" />">
    <s:hidden name="varReturn" id="varReturn_f" />
</form>
        
<div class="d-content-form">
    <s:form id="frm_tipoCambio" action='javascript:void(0)' theme="simple">
        <table border="0" cellpadding="0" cellspacing="0" class="table-form">
            <tr>
                <td style="width: 140px;">
                    Fecha
                </td>
                <td style="">
                    <s:hidden name="fecTipCam" />
                    <s:textfield name="fecTipCam" id="fecTipCam" disabled="true" cssClass="element-form" cssStyle="width:80px;" />
                </td>
            </tr>
            <tr>
                <td style="width: 50px;">Tipo de Cambio Interno<span class="required">*</span></td>
                <td>
                    <s:textfield name="impTipCamInt" cssClass="element-form" cssStyle="width:60px;" maxLength="7"
                                 onkeypress="return isNumberKey(event)" />
                </td>
            </tr>
            <tr>
                <td style="width: 50px;">Tipo de Cambio Legal<span class="required">*</span></td>
                <td>
                    <s:textfield name="impTipCamLeg" cssClass="element-form" cssStyle="width:60px;" maxLength="7" 
                                 onkeypress="return isNumberKey(event)" />
                </td>
            </tr>
            <tr>
                <td></td>
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
        $('#btn_grabar').button();
        
        $('#fecTipCam').datepicker('destroy').datepicker({
            monthNames: ["Enero","Febrero","Marzo","Abril","Mayo","Junio","Julio","Agosto","Septiembre","Octubre","Noviembre","Diciembre"], 
            monthNamesShort: ["Ene","Feb","Mar","Abr","May","Jun","Jul","Ago","Sep","Oct","Nov","Dic"],
            dayNamesMin: ["Do", "Lu", "Ma", "Mi", "Ju", "Vi", "Sa"],
            firstDay: 1,
            inline:true,
            dateFormat: "dd-mm-yy"
        });
        $("#fecTipCam").datepicker("option", "showAnim", "drop");
    
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
            $('#frm_tipoCambio_back').submit();
        });
        
        $('#btn_grabar').click(function(){
            post(
                '<s:property value="formURL" />',
                $('#frm_tipoCambio').serialize(),
                function(resultado){
                    resultado = $.trim(resultado);
                    var _error = resultado.indexOf("error");
                    
                    if(_error == -1)
                        _error = resultado.indexOf('exception');
                    
                    if(_error != -1)
                    {
                        $('#DIVgrabar').html(resultado);
                        $('#DIVgrabar').dialog('open');
                    }
                    else
                    {
                        $('#frm_tipoCambio_back').submit();
                    }
                },
                1
            );
        });
    });
</script>