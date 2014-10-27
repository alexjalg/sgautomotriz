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
    <div class="d-header-labels">
        <table>
            <tr>
                <td class="lbl-concept">Marca: </td>
                <td class="lbl-value"><span><s:property value="desMar" /><span></td>
                <td class="lbl-concept">Modelo: </td>
                <td class="lbl-value"><span><s:property value="desModMar" /><span></td>
            </tr>
        </table>
    </div>
</div>
            
<form method="POST" id="frm_colorMod_back" action="<s:property value="backURL" />">
    <s:hidden name="varReturn" />
</form>
        
<div class="d-content-form">
    <s:form id="frm_colorMod" action='javascript:void(0)' theme="simple">
        <s:hidden name="idMar" />
        <s:hidden name="idModMar" />
        <table border="0" cellpadding="0" cellspacing="0" class="table-form">
            <tr>
                <td style="width: 50px;">Color<span class="required">*</span></td>
                <td>
                    <s:hidden name="idColExt" id="idColExt_h" />
                    <input type="text" name="idColExt" id="colorIdColExt" class="element-form" style="width: 40px; text-align: center;" 
                           max="3" disabled="true" />
                    <input type="text" id="colorDesColExt" class="element-form" style="width:400px;" maxLength="50" 
                           disabled="true" />
                    <button id="btn_colores">...</button>    
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
<div id="DIVcolores" title="<s:property value="titleDialog" />" class="alerta"></div>
                    
                    
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
        
        $('#DIVcolores').dialog({
            autoOpen: false,
            width: 580,
            height: 500,
            modal: true,
            closeOnEscape: false,
            open: function(event, ui) { $(".ui-dialog-titlebar-close").hide(); },
            draggable: false,
            resizable: false
        });
        
        $('a.back').click(function(){
            $('#frm_colorMod_back').submit();
        });
        
        $('#btn_colores').click(function(){
            post(
                '<s:property value="baseURL" /><s:url namespace="coloresExtModelo" includeContext="false" action="listColoresExteriorColorExtModelo" />',
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
                        $('#DIVcolores').dialog({
                            buttons:{
                                "Aceptar":function(){
                                    $('#colorIdColExt').val($('.select_rec:checked').val());
                                    $('#idColExt_h').val($('.select_rec:checked').val());
                                    $('#colorDesColExt').val($.trim($('.select_rec:checked').parent().next().next().html()));
                                    $('#DIVcolores').dialog("close");
                                    hideOverlay(function(){});
                                },
                                "Cancelar":function(){
                                    $('#DIVcolores').dialog("close");
                                    hideOverlay(function(){});
                                }
                            }
                        });
                        $('#DIVcolores').html(resultado);
                        $('#DIVcolores').dialog('open');
                    }
                },
                2       
            );
        });
        
        $('#btn_grabar').click(function(){
            post(
                '<s:property value="formURL" />',
                $('#frm_colorMod').serialize(),
                function(resultado){
                    var _error = validaRespuestaAjax(resultado);
                    
                    if(_error != -1)
                    {
                        $('#DIVgrabar').html(resultado);
                        $('#DIVgrabar').dialog('open');
                    }
                    else
                    {
                        $('#frm_colorMod_back').submit();
                    }
                },
                1
            );
        });
    });
</script>