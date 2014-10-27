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
            <form method="POST" id="frm_modelo_back" action="<s:property value="backURL" />">
                <s:hidden name="varReturn" />
            </form>
        </div>
    </div>
    <div class="d-header-labels">
        <table>
            <tr>
                <td class="lbl-concept">Marca: </td>
                <td class="lbl-value"><span><s:property value="desMar" /><span></td>
            </tr>
        </table>
    </div>
</div>
        
<div class="d-content-form">
    <s:form id="frm_modelo" action='javascript:void(0)' theme="simple">
        <s:hidden name="idMar" />
        <table border="0" cellpadding="0" cellspacing="0" class="table-form">
            <tr>
                <td style="width: 60px;">
                    Código<s:if test='%{opcion=="A"}'><span class="required">*</span></s:if>
                </td>
                <td style="">
                    <s:if test='%{opcion=="A"}'>
                        <s:textfield name="idModMar" cssClass="element-form" cssStyle="width:40px;" maxlength="4" />
                    </s:if>
                    <s:elseif test='%{opcion=="M"}'>
                        <s:textfield name="idModMar" disabled="true" cssClass="element-form" cssStyle="width:40px;" />
                        <s:hidden name="idModMar" />
                    </s:elseif>
                </td>
            </tr>
            <tr>
                <td style="width: 60px;">Modelo<span class="required">*</span></td>
                <td>
                    <s:textfield name="desModMar" cssClass="element-form" cssStyle="width:350px;" maxLength="40" />
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
            $('#frm_modelo_back').submit();
        });
        
        $('#btn_grabar').click(function(){
            post(
                '<s:property value="formURL" />',
                $('#frm_modelo').serialize(),
                function(resultado){
                    var _error = validaRespuestaAjax(resultado);
                    
                    if(_error != -1)
                    {
                        $('#DIVgrabar').html(resultado);
                        $('#DIVgrabar').dialog('open');
                    }
                    else
                    {
                        $('#frm_modelo_back').submit();
                    }
                },
                1
            );
        });
    });
</script>