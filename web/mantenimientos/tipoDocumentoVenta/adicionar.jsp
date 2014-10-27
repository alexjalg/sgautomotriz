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
            
<form method="POST" id="frm_tipoDocumentoVenta_back" action="<s:property value="backURL" />">
    <s:hidden name="varReturn" id="varReturn_f" />
</form>
        
<div class="d-content-form">
    <s:form id="frm_tipoDocumentoVenta" action='javascript:void(0)' theme="simple">
        <s:if test='%{opcion=="M"}'>
            <s:hidden name="idTipDocVen" />
        </s:if>        
        <table border="0" cellpadding="0" cellspacing="0" class="table-form">
            <tr>
                <td style="width: 120px;">C�digo<span class="required">*</span></td>
                <td>
                    <s:if test='%{opcion=="M"}'>
                        <s:textfield name="idTipDocVen" cssClass="element-form" cssStyle="width:30px;" maxLength="2" disabled="true"/>
                    </s:if>
                    <s:else>
                        <s:textfield name="idTipDocVen" cssClass="element-form" cssStyle="width:30px;" maxLength="2" />
                    </s:else>                    
                </td>
            </tr>
            <tr>
                <td style="width: 258px;">Descripcion del Tipo de Documento de Venta<span class="required">*</span></td>
                <td>
                    <s:textfield name="desTipDocVen" cssClass="element-form" cssStyle="width:285px;" maxLength="40" />
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
        resizeContForm();
    
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
            $('#frm_tipoDocumentoVenta_back').submit();
        });
        
        $('#btn_grabar').click(function(){
            post(
                '<s:property value="formURL" />',
                $('#frm_tipoDocumentoVenta').serialize(),
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
                        $('#frm_tipoDocumentoVenta_back').submit();
                    }
                },
                1
            );
        });
    });
</script>